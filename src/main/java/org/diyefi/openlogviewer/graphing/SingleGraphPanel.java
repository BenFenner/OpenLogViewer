/* OpenLogViewer
 *
 * Copyright 2011
 *
 * This file is part of the OpenLogViewer project.
 *
 * OpenLogViewer software is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenLogViewer software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with any OpenLogViewer software.  If not, see http://www.gnu.org/licenses/
 *
 * I ask that if you make any changes to this file you fork the code on github.com!
 *
 */
package org.diyefi.openlogviewer.graphing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import org.diyefi.openlogviewer.OpenLogViewer;
import org.diyefi.openlogviewer.genericlog.GenericDataElement;
import org.diyefi.openlogviewer.utils.SigFigUtils;

/**
 * SingleGraphPanel is a JPanel that uses a transparent background.
 * The graph trace is drawn to this panel and used in conjunction with a JLayeredPane
 * to give the appearance of all the graph traces drawn together.
 *
 * This layer listens for window resizes and property changes.
 * @author Bryan Harris and Ben Fenner
 */
public class SingleGraphPanel extends JPanel implements HierarchyBoundsListener, PropertyChangeListener {
	private static final long serialVersionUID = 1L;

	private static final int SHOW_DATA_POINT_ZOOM_THRESHOLD = 5;
	private static final int DATA_POINT_WIDTH = 3;
	private static final int DATA_POINT_HEIGHT = DATA_POINT_WIDTH;
	private static final double GRAPH_TRACE_SIZE_AS_PERCENTAGE_OF_TOTAL_GRAPH_SIZE = 0.95;
	private GenericDataElement gde;
	private double[] dataPointsToDisplay;
	private double[][] dataPointRangeInfo;
	private int availableDataRecords;
	private int graphBeginningIndex;
	private int graphEndingIndex;

	public static final int DECIMAL_PLACES = 3;

	public SingleGraphPanel() {
		setOpaque(false);
		setLayout(null);

		graphBeginningIndex = Integer.MIN_VALUE;
		graphEndingIndex = Integer.MIN_VALUE;
	}

	@Override
	public void ancestorMoved(final HierarchyEvent e) {
	}

	@Override
	public final void ancestorResized(final HierarchyEvent e) {
		if (e.getID() == HierarchyEvent.ANCESTOR_RESIZED) {
			sizeGraph();
		}
	}

	@Override
	public final void propertyChange(final PropertyChangeEvent evt) {
		if ("Split".equalsIgnoreCase(evt.getPropertyName())) {
			sizeGraph();
		}
	}

	@Override
	public final void paintComponent(final Graphics g) {
		super.paintComponent(g);

		if (!this.getSize().equals(this.getParent().getSize())) {
			this.setSize(this.getParent().getSize());
		}

		initGraph();

		if (hasDataPointToDisplay()) {
			paintDataPointsAndTraces(g);
		}
	}

	private void paintDataPointsAndTraces(final Graphics g) {
		// Setup graphics stuff
		final Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(gde.getDisplayColor());

		// Initialize current, previous and next graph trace data points
		double leftOfTraceData = -Double.MAX_VALUE;
		double traceData = -Double.MAX_VALUE;
		double rightOfTraceData = dataPointsToDisplay[0];

		// Initialize designated visible graph trace status markers
		boolean atTraceBeginning = false;
		boolean insideTrace = false;
		boolean atTraceEnd = false;

		// Initialize and setup data point screen location stuff
		final boolean zoomedOut = OpenLogViewer.getInstance().getEntireGraphingPanel().isZoomedOutBeyondOneToOne();
		int zoom = OpenLogViewer.getInstance().getEntireGraphingPanel().getZoom();
		if (zoomedOut) {
			zoom = 1;
		}
		final double graphPosition = OpenLogViewer.getInstance().getEntireGraphingPanel().getGraphPosition();
		final double offset = (graphPosition % 1) * zoom;
		int screenPositionXCoord = -EntireGraphingPanel.LEFT_OFFSCREEN_POINTS_ZOOMED_OUT;
		if (!zoomedOut) {
			screenPositionXCoord = -(int) Math.round(offset) - (EntireGraphingPanel.LEFT_OFFSCREEN_POINTS_ZOOMED_IN * zoom); // Ugly cast/invert here too
		}
		int screenPositionYCoord = Integer.MIN_VALUE;
		int nextScreenPositionYCoord = getScreenPositionYCoord(rightOfTraceData, gde.getDisplayMinValue(), gde.getDisplayMaxValue());

		// Draw data points and trace lines from left to right including one off screen to the right
		for (int i = 0; i < dataPointsToDisplay.length; i++) {
			// Setup current, previous and next graph trace data points
			if (i > 0) {
				leftOfTraceData = dataPointsToDisplay[i - 1];
			} else {
				leftOfTraceData = -Double.MAX_VALUE;
			}

			traceData = dataPointsToDisplay[i];

			if (i + 1 < dataPointsToDisplay.length) {
				rightOfTraceData = dataPointsToDisplay[i + 1];
			} else {
				rightOfTraceData = -Double.MAX_VALUE;
			}

			// Setup data point screen location stuff
			screenPositionYCoord = nextScreenPositionYCoord;
			nextScreenPositionYCoord = getScreenPositionYCoord(rightOfTraceData, gde.getDisplayMinValue(), gde.getDisplayMaxValue());

			// Draw beginning and end markers
			if (i == graphBeginningIndex || i == graphEndingIndex) {
				g2d.drawLine(screenPositionXCoord - 2, screenPositionYCoord - 1, screenPositionXCoord - 2, screenPositionYCoord + 1);
				g2d.drawLine(screenPositionXCoord - 1, screenPositionYCoord + 2, screenPositionXCoord + 1, screenPositionYCoord + 2);
				g2d.drawLine(screenPositionXCoord - 1, screenPositionYCoord - 2, screenPositionXCoord + 1, screenPositionYCoord - 2);
				g2d.drawLine(screenPositionXCoord + 2, screenPositionYCoord - 1, screenPositionXCoord + 2, screenPositionYCoord + 1);
			}

			// Setup graph states
			if (leftOfTraceData == -Double.MAX_VALUE && traceData != -Double.MAX_VALUE) {
				// At the beginning of the portion of the graph trace designated for display
				atTraceBeginning = true;
				insideTrace = true;
			}

			if (traceData != -Double.MAX_VALUE && rightOfTraceData == -Double.MAX_VALUE) {
				// At the end of the portion of the graph trace designated for display
				atTraceEnd = true;
			}

			// Draw data point
			if (!zoomedOut && zoom > SHOW_DATA_POINT_ZOOM_THRESHOLD) {

				// Draw fat data point
				if (atTraceBeginning && atTraceEnd) {
					// Special case to determine if fat dot is needed if scrolled to the end
					if (availableDataRecords >= 2 && gde.get(availableDataRecords - 2) != traceData) {
						// fillRect() is 95% faster than fillOval() for a 3x3 square on Ben's dev machine
						g2d.fillRect(screenPositionXCoord - 1, screenPositionYCoord - 1, DATA_POINT_WIDTH, DATA_POINT_HEIGHT);
					} else {
						// Draw small data point
						// drawLine() is 33% faster than fillRect() for a single pixel on Ben's dev machine
						g2d.drawLine(screenPositionXCoord, screenPositionYCoord, screenPositionXCoord, screenPositionYCoord);
					}
				} else if (atTraceBeginning) {
					if (traceData != rightOfTraceData) {
						// fillRect() is 95% faster than fillOval() for a 3x3 square on Ben's dev machine
						g2d.fillRect(screenPositionXCoord - 1, screenPositionYCoord - 1, DATA_POINT_WIDTH, DATA_POINT_HEIGHT);
					}
				} else if (atTraceEnd) {
					if (traceData != leftOfTraceData) {
						// fillRect() is 95% faster than fillOval() for a 3x3 square on Ben's dev machine
						g2d.fillRect(screenPositionXCoord - 1, screenPositionYCoord - 1, DATA_POINT_WIDTH, DATA_POINT_HEIGHT);
					}
				} else if (insideTrace) {
					if (traceData != leftOfTraceData || traceData != rightOfTraceData) {
						// fillRect() is 95% faster than fillOval() for a 3x3 square on Ben's dev machine
						g2d.fillRect(screenPositionXCoord - 1, screenPositionYCoord - 1, DATA_POINT_WIDTH, DATA_POINT_HEIGHT);
					}
				}
			} else if (insideTrace) {
				// Draw small data point
				// drawLine() is 33% faster than fillRect() for a single pixel on Ben's dev machine
				g2d.drawLine(screenPositionXCoord, screenPositionYCoord, screenPositionXCoord, screenPositionYCoord);
			}

			// Draw graph trace line
			if (insideTrace && !atTraceEnd) {
				g2d.drawLine(screenPositionXCoord, screenPositionYCoord, screenPositionXCoord + zoom, nextScreenPositionYCoord);
			}

			// Reset graph states
			if (atTraceEnd) {
				insideTrace = false;
				atTraceEnd = false;
			}
			atTraceBeginning = false;

			// Move to the right in preparation of drawing more
			screenPositionXCoord += zoom;
		}
	}

	/**
	 * Cases:
	 *
	 * data > max = don't display
	 * data < min = don't display
	 * data ==- min == max = top
	 * otherwise = proportional
	 *
	 * @param traceData The value of the actual data at this point.
	 * @param minValue The minimum value to display before being cut off.
	 * @param maxValue The maximum value to display before being cut off.
	 * @return
	 */
	private int getScreenPositionYCoord(final Double traceData, final double minValue, final double maxValue) {
		final double divs = OpenLogViewer.getInstance().getMultiGraphLayeredPane().getTotalSplits();
		final int height = (int) ((getHeight() / divs) * GRAPH_TRACE_SIZE_AS_PERCENTAGE_OF_TOTAL_GRAPH_SIZE);

		if (traceData >= minValue && traceData <= maxValue) {
			if (maxValue == minValue) {
				return height; // min/max/data all equal
			} else {
				return (int) (height - (height * ((traceData - minValue) / (maxValue - minValue))));
			}
		} else {
			return -1; // data outside min/max range
		}
	}

	private boolean hasDataPointToDisplay() {
		boolean result = false;
		if ((dataPointsToDisplay != null) && (dataPointsToDisplay.length > 0)) {
			result = true;
		}
		return result;
	}

	/**
	 * this is where the GDE is referenced and the graph gets initialized for the first time
	 * @param GDE
	 */
	public final void setData(final GenericDataElement newGDE) {
		gde = newGDE;
		availableDataRecords = newGDE.size() + 1; // Size is currently position, this will need cleaning up later, leave it to me.
		// The main thing is to take away 10 calls to the GDE per view on something that is fairly static and cache it internally
		sizeGraph();
	}

	public final GenericDataElement getData() {
		return gde;
	}

	/**
	 * Used for InfoLayer to get the data from the single graphs for data under the mouse
	 *
	 * @param pointerDistanceFromCenter
	 * @return Double representation of info at the mouse cursor line which snaps to data points or null if no data under cursor
	 */
	public final String getMouseInfo(final int cursorPosition, final int requiredWidth) {
		final boolean zoomedOut = OpenLogViewer.getInstance().getEntireGraphingPanel().isZoomedOutBeyondOneToOne();
		String info = "-.-";
		if (zoomedOut) {
			info = getMouseInfoZoomedOut(cursorPosition, requiredWidth);
		} else {
			info = getMouseInfoZoomed(cursorPosition, requiredWidth);
		}

		return info;
	}

	/**
	 * Used for InfoLayer to get the data from the single graphs for data under the mouse when not zoomed out
	 *
	 * @param pointerDistanceFromCenter
	 * @return Double representation of info at the mouse cursor line which snaps to data points or null if no data under cursor
	 */
	private String getMouseInfoZoomed(final int cursorPosition, final int requiredWidth) {
		String result = "-.-";
		final double graphPosition = OpenLogViewer.getInstance().getEntireGraphingPanel().getGraphPosition();
		final int zoom = OpenLogViewer.getInstance().getEntireGraphingPanel().getZoom();
		final double offset = (graphPosition % 1) * zoom;
		final int cursorPositionPlusOffset = cursorPosition + (int) offset;
		double numSnapsFromLeft = ((double) cursorPositionPlusOffset / (double) zoom);
		numSnapsFromLeft = Math.round(numSnapsFromLeft);
		final int dataLocation = (int) graphPosition + (int) numSnapsFromLeft;
		if ((dataLocation >= 0) && (dataLocation < availableDataRecords)) {
			result = SigFigUtils.roundDecimalPlaces(gde.get(dataLocation), DECIMAL_PLACES);
			result = padWithLeadingSpaces(result, requiredWidth);
			result = replaceDecimalZerosWithSpaces(result);
		}
		return result;
	}

	/**
	 * Used for InfoLayer to get the data from the single graphs for data under the mouse when zoomed out
	 *
	 * @param pointerDistanceFromCenter
	 * @return Double representation of info at the mouse cursor line which snaps to data points or null if no data under cursor
	 */
	private String getMouseInfoZoomedOut(final int cursorPosition, final int requiredWidth) {
		String result = "-.- | -.- | -.-";
		if ((cursorPosition >= 0) && (cursorPosition < dataPointRangeInfo.length)) {
			double minData = dataPointRangeInfo[cursorPosition + EntireGraphingPanel.LEFT_OFFSCREEN_POINTS_ZOOMED_OUT][0];
			double meanData = dataPointRangeInfo[cursorPosition + EntireGraphingPanel.LEFT_OFFSCREEN_POINTS_ZOOMED_OUT][1];
			double maxData = dataPointRangeInfo[cursorPosition + EntireGraphingPanel.LEFT_OFFSCREEN_POINTS_ZOOMED_OUT][2];
			if (minData != -Double.MAX_VALUE) {
				String resultMin = SigFigUtils.roundDecimalPlaces(minData, DECIMAL_PLACES);
				String resultMax = SigFigUtils.roundDecimalPlaces(maxData, DECIMAL_PLACES);
				String resultMean = SigFigUtils.roundDecimalPlaces(meanData, DECIMAL_PLACES);

				resultMin = padWithLeadingSpaces(resultMin, requiredWidth);
				resultMax = padWithLeadingSpaces(resultMax, requiredWidth);
				resultMean = padWithLeadingSpaces(resultMean, requiredWidth);

				resultMin = replaceDecimalZerosWithSpaces(resultMin);
				resultMax = replaceDecimalZerosWithSpaces(resultMax);
				resultMean = replaceDecimalZerosWithSpaces(resultMean);

				result = resultMin + " | " + resultMean + " | " + resultMax;
			}
		}
		return result;
	}

	private String padWithLeadingSpaces(final String input, final int requiredWidth) {
		final StringBuilder padded = new StringBuilder(input);
		while (padded.length() < requiredWidth) {
			padded.insert(0, ' ');
		}
		return padded.toString();
	}

	private String replaceDecimalZerosWithSpaces(final String input) {
		final StringBuilder stripped = new StringBuilder(input);
		for (int i = stripped.length() - 1; stripped.charAt(i - 1) != '.'; i--) {
			if (stripped.charAt(i) == '0'){
				stripped.setCharAt(i, ' ');
			}
		}
		return stripped.toString();
	}

	public final Color getColor() {
		return gde.getDisplayColor();
	}

	public final void setColor(final Color c) {
		gde.setDisplayColor(c);
	}

	public final void initGraph() {
		if (OpenLogViewer.getInstance().getEntireGraphingPanel().isZoomedOutBeyondOneToOne()) {
			initGraphZoomedOut();
		} else {
			initGraphZoomed();
		}
	}

	/**
	 * initialize the graph any time you need to paint
	 */
	public final void initGraphZoomed() {
		if (gde != null) {
			final int graphPosition = (int) OpenLogViewer.getInstance().getEntireGraphingPanel().getGraphPosition();
			final int graphWindowWidth = OpenLogViewer.getInstance().getEntireGraphingPanel().getWidth();
			final int zoom = OpenLogViewer.getInstance().getEntireGraphingPanel().getZoom();
			int numberOfPointsThatFitInDisplay = graphWindowWidth / zoom;
			numberOfPointsThatFitInDisplay += EntireGraphingPanel.LEFT_OFFSCREEN_POINTS_ZOOMED_IN;
			numberOfPointsThatFitInDisplay += EntireGraphingPanel.RIGHT_OFFSCREEN_POINTS_ZOOMED_IN;
			dataPointsToDisplay = new double[numberOfPointsThatFitInDisplay];
			int position = graphPosition - EntireGraphingPanel.LEFT_OFFSCREEN_POINTS_ZOOMED_IN;

			// Reset start/end indices.
			graphBeginningIndex = Integer.MIN_VALUE;
			graphEndingIndex = Integer.MIN_VALUE;

			// Setup data points.
			for (int i = 0; i < numberOfPointsThatFitInDisplay; i++) {
				if (position >= 0 && position < availableDataRecords) {
					dataPointsToDisplay[i] = gde.get(position);

					// Set start/end indices.
					if (position == 0) {
						graphBeginningIndex = i;
					}

					if (position == availableDataRecords - 1) {
						graphEndingIndex = i;
					}

				} else {
					dataPointsToDisplay[i] = -Double.MAX_VALUE;
				}
				position++;
			}
		}
	}

	/**
	 * initialize the graph any time you need to paint
	 */
	public final void initGraphZoomedOut() {
		if (gde != null) {
			final EntireGraphingPanel egp = OpenLogViewer.getInstance().getEntireGraphingPanel();
			final int graphPosition = (int) egp.getGraphPosition();
			final int graphWindowWidth = egp.getWidth();
			final int zoom = egp.getZoom();
			final int position = graphPosition - (EntireGraphingPanel.LEFT_OFFSCREEN_POINTS_ZOOMED_OUT * zoom);
			dataPointsToDisplay = new double[graphWindowWidth
			                                 + EntireGraphingPanel.LEFT_OFFSCREEN_POINTS_ZOOMED_OUT
			                                 + EntireGraphingPanel.RIGHT_OFFSCREEN_POINTS_ZOOMED_OUT];
			dataPointRangeInfo = new double[dataPointsToDisplay.length][3];
			final int numberOfRealPointsThatFitInDisplay = (graphWindowWidth * zoom)
					+ (EntireGraphingPanel.LEFT_OFFSCREEN_POINTS_ZOOMED_OUT * zoom)
					+ (EntireGraphingPanel.RIGHT_OFFSCREEN_POINTS_ZOOMED_OUT * zoom);
			final int rightGraphPosition = position + numberOfRealPointsThatFitInDisplay;

			// Reset start/end indices.
			graphBeginningIndex = Integer.MIN_VALUE;
			graphEndingIndex = Integer.MIN_VALUE;

			/*
			* Setup data points.
			*
			* The data point to display is calculated by taking the average of
			* the data point spread and comparing it to the previous calculated
			* data point. If the average is higher, then the highest value of
			* the data spread is used. If the average is lower, then the lowest
			* value of the data point spread is used.
			*
			* In other words, if the graph is trending upward, the peak is used.
			* If the graph is trending downward, the valley is used.
			* This keeps the peaks and valleys intact and the middle stuff is
			* lost. This maintains the general shape of the graph, and assumes
			* that local peaks and valleys are the most interesting parts of the
			* graph to display.
			*/
			int nextAarrayIndex = 0;
			double leftOfNewData = gde.get(0);
			if (position > 0 && position < availableDataRecords) {
				leftOfNewData = gde.get(position);
			}

			if (zoom < (availableDataRecords / 2)) {

				for (int i = position; i < rightGraphPosition; i += zoom) {

					if (i >= 0 && i < availableDataRecords) {
						double minData = Double.MAX_VALUE;
						double maxData = -Double.MAX_VALUE;
						double newData = 0.0;
						double acummulateData = 0.0;
						int divisor = 0;

						for (int j = 0; j < zoom; j++) {
							final int gdeIndex = i + j;
							if (gdeIndex >= 0 && gdeIndex < availableDataRecords) {
								newData = gde.get(gdeIndex);
								acummulateData += newData;
								divisor++;
								if (newData < minData) {
									minData = newData;
								}

								if (newData > maxData) {
									maxData = newData;
								}

								// Set start/end indices.
								if (graphBeginningIndex == Integer.MIN_VALUE && (gdeIndex >= 0 && gdeIndex < zoom)) {
									graphBeginningIndex = nextAarrayIndex;
								}

								if (gdeIndex == availableDataRecords - 1) {
									graphEndingIndex = nextAarrayIndex;
								}

							}
						}
						final double averageData = acummulateData / divisor;
						if (averageData > leftOfNewData) {
							dataPointsToDisplay[nextAarrayIndex] = maxData;
							leftOfNewData = maxData;
						} else if (averageData < leftOfNewData) {
							dataPointsToDisplay[nextAarrayIndex] = minData;
							leftOfNewData = minData;
						} else {
							dataPointsToDisplay[nextAarrayIndex] = averageData;
							leftOfNewData = averageData;
						}
						dataPointRangeInfo[nextAarrayIndex][0] = minData;
						dataPointRangeInfo[nextAarrayIndex][1] = averageData;
						dataPointRangeInfo[nextAarrayIndex][2] = maxData;
						nextAarrayIndex++;
					} else {
						dataPointsToDisplay[nextAarrayIndex] = -Double.MAX_VALUE;
						dataPointRangeInfo[nextAarrayIndex][0] = -Double.MAX_VALUE;
						dataPointRangeInfo[nextAarrayIndex][1] = -Double.MAX_VALUE;
						dataPointRangeInfo[nextAarrayIndex][2] = -Double.MAX_VALUE;
						nextAarrayIndex++;
					}
				}
			} else {

				/*
				* Setup data points when extremely zoomed out.
				*
				* If the zoom value is higher than the entire length of
				* available data then it is possible for the normal algorithm
				* to skip over the data completely when sweeping across the
				* screen from left to right in zoom steps. If zoom reaches
				* that high of a value, then use this alternative algorithm
				* instead.
				*
				*/

				// Fill in null data points until zero position is reached.
				for (int i = position; i < 0; i += zoom) {
					dataPointsToDisplay[nextAarrayIndex] = -Double.MAX_VALUE;
					dataPointRangeInfo[nextAarrayIndex][0] = -Double.MAX_VALUE;
					dataPointRangeInfo[nextAarrayIndex][1] = -Double.MAX_VALUE;
					dataPointRangeInfo[nextAarrayIndex][2] = -Double.MAX_VALUE;
					nextAarrayIndex++;
				}

				// Find min/mean/max of entire available data and place at position zero.
				double minData = Double.MAX_VALUE;
				double maxData = -Double.MAX_VALUE;
				double newData = 0.0;
				double acummulateData = 0.0;
				int divisor = 0;
				for (int i = 0; i < availableDataRecords; i++) {
					newData = gde.get(i);
					acummulateData += newData;
					divisor++;
					if (newData < minData) {
						minData = newData;
					}

					if (newData > maxData) {
						maxData = newData;
					}
				}
				final double averageData = acummulateData / divisor;
				dataPointsToDisplay[nextAarrayIndex] = averageData;
				dataPointRangeInfo[nextAarrayIndex][0] = minData;
				dataPointRangeInfo[nextAarrayIndex][1] = averageData;
				dataPointRangeInfo[nextAarrayIndex][2] = maxData;

				// Set start/end indices.
				graphBeginningIndex = nextAarrayIndex;
				graphEndingIndex = nextAarrayIndex;

				nextAarrayIndex++;

				// Fill in the rest of the array with null data points.
				for (int i = zoom * 2; i < rightGraphPosition; i += zoom) {
					dataPointsToDisplay[nextAarrayIndex] = -Double.MAX_VALUE;
					dataPointRangeInfo[nextAarrayIndex][0] = -Double.MAX_VALUE;
					dataPointRangeInfo[nextAarrayIndex][1] = -Double.MAX_VALUE;
					dataPointRangeInfo[nextAarrayIndex][2] = -Double.MAX_VALUE;
					nextAarrayIndex++;
				}
			}
		}
	}

	/**
	 * maintains the size of the graph when applying divisions
	 */
	public final void sizeGraph() {
		final MultiGraphLayeredPane lg = OpenLogViewer.getInstance().getMultiGraphLayeredPane();
		final double fractionalMargin = (1.0 - GRAPH_TRACE_SIZE_AS_PERCENTAGE_OF_TOTAL_GRAPH_SIZE) / 2.0;
		final double size = ((double) lg.getHeight() / lg.getTotalSplits());
		final double margin = fractionalMargin * size;
		final int wherePixel = (int) (margin + ((gde.getSplitNumber() - 1 /* Why not zero indexed? */) * size));
		setBounds(0, wherePixel, lg.getWidth(), (int) size);
		initGraph();
	}

	/**
	 * Graph total size
	 * @return GDE.size()
	 */
	public final int graphSize() {
		return availableDataRecords;
	}
}
