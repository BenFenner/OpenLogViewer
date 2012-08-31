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

import java.util.HashMap;

import org.diyefi.openlogviewer.OpenLogViewer;

/**
 * DataPointCache is used to store the calculated data points used to
 * display single graph traces when zoomed out beyond 1:1 to prevent
 * having to calculate the data points to display every time. Each
 * zoom level has its own cache. The cache should grow as new points 
 * are calculated.
 * 
 * The zoom level is used as the first array dimension index.
 * The length of the second array will be equal to the total number of 
 * points that could ever need to be stored (which turns out to be half
 * of the overall graph trace length).
 * 
 * The cache is guaranteed to contain no gaps in coverage except at the 
 * beginning or at the end of the cache. There will be contiguous data
 * from the start of the true cache data to the end. This is accomplished
 * by rejecting requests to cache data that doesn't overlap existing 
 * cached data (unless the cache request is the first one).
 * 
 * @author Ben Fenner
 */
public class DataPointCache {
	
	private HashMap<Integer, Double>[] cache;
	
	@SuppressWarnings("unchecked")
	public DataPointCache() {
		final int widestZoom = OpenLogViewer.getInstance().getEntireGraphingPanel().getWidestZoom();
		cache = new HashMap[widestZoom + 1];
	}
	
	public boolean containsAll(final int zoom,
			final int startPosition,
			final int endPosition) {
		boolean result = false;
		if (cache[zoom] != null) {
			result = cache[zoom].get(Math.round(startPosition / zoom)) != null
					&& cache[zoom].get(Math.round(endPosition / zoom)) != null;
		}
		return result;
	}
	
	private boolean containsSome(final int zoom,
			final int startPosition,
			final int endPosition) {
		boolean result = false;
		if (cache[zoom] != null) {
			result = cache[zoom].get(Math.round(startPosition / zoom)) != null
					|| cache[zoom].get(Math.round(endPosition / zoom)) != null;
		}
		return result;
	}
	
	public double[] getAll(final int zoom,
			final int startPosistion,
			final int endPosition) {
		final int arraySize = Math.round((endPosition - startPosistion) / zoom);
		final double[] result = new double[arraySize];
		int cacheIndex = Math.round(startPosistion / zoom);
		for (int i = 0; i < result.length; i++) {
			result[i] = cache[zoom].get(cacheIndex);
			cacheIndex++;
		}
		return result;
	}
	
	public double[] getSome(final int zoom,
			final int startPosistion,
			final int endPosition){
		return new double[0];
	}
	
	public boolean cache(final double[] newData,
			final int zoom,
			final int startPosistion,
			final int endPosition) {
		boolean cached = false;
		int cacheIndex = Math.round(startPosistion / zoom);
		if (cache[zoom] == null) {
			cache[zoom] = new HashMap<Integer, Double>();
			for (int i = 0; i < newData.length; i++) {
				cache[zoom].put(cacheIndex, newData[i]);
				cacheIndex++;
			}
			cached = true;
		} else if (containsSome(zoom, startPosistion, endPosition)) {
			for (int i = 0; i < newData.length; i++) {
				cache[zoom].put(cacheIndex, newData[i]);
				cacheIndex++;
			}
			cached = true;
		}
		return cached;
	}
}