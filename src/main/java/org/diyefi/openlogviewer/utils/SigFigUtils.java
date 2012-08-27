/* Open Log Viewer
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
package org.diyefi.openlogviewer.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Math is used to provide math functions specific to the project.
 * @author Ben Fenner
 */
public final class SigFigUtils {

	private SigFigUtils() {
	}

	public static String round(final double inputNum, final int sigFigs) {
		// Deal with negative or zero sig fig request
		if (sigFigs <= 0){
			return "";
		}

		final StringBuilder input = new StringBuilder(new BigDecimal(inputNum).toPlainString());
		// Deal with zero
		if ("0".equals(input.toString())){
			return "0.0";
		}

		final StringBuilder inputNoSign = new StringBuilder();
		inputNoSign.append(input);
		final StringBuilder inputNoDec = new StringBuilder();
		StringBuilder roundedNoSign = new StringBuilder();
		final StringBuilder roundedNoDec = new StringBuilder();
		StringBuilder output = new StringBuilder();
		//System.out.println("Input: " + input);

		// Deal with negative
		final boolean sign = input.charAt(0) == '-';
		if (sign){
			inputNoSign.deleteCharAt(0);
		}

		// Get total digits contained in input
		inputNoDec.append(inputNoSign);
		int decimalIndex = inputNoDec.indexOf(".");
		if (decimalIndex != -1){
			inputNoDec.deleteCharAt(decimalIndex);
		}

		// Is rounding possibly required?
		if (sigFigs < inputNoDec.length()) {
			roundedNoSign = roundMe(inputNoSign, sigFigs);
		} else {
			roundedNoSign = inputNoSign;
		}

		// Get total digits contained in rounded excluding leading decimal zero
		roundedNoDec.append(roundedNoSign);
		if (roundedNoDec.indexOf("0.") == 0){
			roundedNoDec.deleteCharAt(0);
			roundedNoDec.deleteCharAt(0);
		} else {
			decimalIndex = roundedNoDec.indexOf(".");
			if (decimalIndex != -1){
				roundedNoDec.deleteCharAt(decimalIndex);
			}
		}

		// Is truncating or padding required?
		if (sigFigs < roundedNoDec.length()) {
			// Truncate that shit.
			output = truncateMe(roundedNoSign, sigFigs);
		} else if (sigFigs > roundedNoDec.length()) {
			// Pad that shit.
			output = padMe(roundedNoSign, sigFigs);
		} else {
			output = roundedNoSign;
		}

		// Put sign back
		if (sign) {
			output.insert(0, '-');
		}

		//System.out.println("Output: " + output);
		return output.toString();
	}


	/**
	 *
	 * @param input - StringBuilder representation of a double to round. No negatives allowed.
	 * @param numDigits - Number of digits in the resulting rounded output
	 * @return
	 */
	private static StringBuilder roundMe(final StringBuilder input, final int numDigits){
		StringBuilder output = new StringBuilder();
		//System.out.println("Possible rounding: " + input);
		StringBuilder inputNoDec = new StringBuilder();
		int decimalIndex = input.indexOf(".");
		if (decimalIndex == -1) {
			inputNoDec.append(input);
		} else {
			inputNoDec.append(input);
			inputNoDec.deleteCharAt(decimalIndex);
		}

		// Find most significant digit index
		int mostSigIndex = -1;
		for (int i = 0; i < input.length() && mostSigIndex < 0; i++){
			if (input.charAt(i) != '0' && input.charAt(i) != '.'){
				mostSigIndex = i;
			}
		}

		// Check to see if no rounding is needed
		if (input.length() <= mostSigIndex + numDigits) {
			// No rounding needed
			//System.out.println("No rounding needed 1");
			output.append(input);
		} else {
			// Possible rounding needed

			// Find one digit past the least significant digit we want to keep
			int onePast = Character.getNumericValue(inputNoDec.charAt(mostSigIndex + numDigits));
			//System.out.println("Most sigificant digit index: " + mostSigIndex);
			//System.out.println("Number of requested digits: " + numDigits);
			//System.out.println("One past: " + onePast);


			if (onePast < 5){
				// No rounding needed
				//System.out.println("No rounding needed 2");
				output.append(input);
			} else {
				// Rounding definitely needed
				//System.out.println("Rounding being performed!");
				StringBuilder addMe = new StringBuilder();
				boolean fived = false;
				for (int i = 0; i < input.length(); i++){
					if (input.charAt(i) == '.'){
						addMe.append('.');
					} else if (!fived && i >= mostSigIndex + numDigits) {
						addMe.append('5');
						fived = true;
					} else {
						addMe.append('0');
					}
				}
				Double in = new Double(input.toString());
				Double add = new Double(addMe.toString());
				Double out = in + add;
				//System.out.println("In: " + in);
				//System.out.println("Add: " + add);
				//System.out.println("Out: " + out);
				output.append(new BigDecimal(out).toPlainString());;
			}
		}
		return output;
	}

	/**
	 *
	 * @param input - StringBuilder representation of a double to truncate. No negatives allowed.
	 * @param numDigits - Number of digits in the resulting truncated output
	 * @return
	 */
	private static StringBuilder truncateMe(final StringBuilder input, final int numDigits){
		//System.out.println("Truncating: " + input);
		StringBuilder output = new StringBuilder();
		int charsNeeded = numDigits;

		// Find most significant digit index and pre-fill output
		boolean foundMostSigFig = false;
		boolean foundDecimal = false;
		int mostSigIndex = 0;
		for (int i = 0; i < input.length() && !foundMostSigFig; i++){
			if (input.charAt(i) != '0' && input.charAt(i) != '.'){
				foundMostSigFig = true;
				mostSigIndex = i;
			} else if (input.charAt(i) == '.'){
				foundDecimal = true;
				output.append("0.");
			} else if (foundDecimal){
				output.append('0');
			}
		}

		// Fill output with requested number of digits
		int digitindex = mostSigIndex;
		for (int i = 0; i < charsNeeded; i++) {
			char next = input.charAt(digitindex);
			if (next == '.') {
				foundDecimal = true;
				charsNeeded++;
			}
			output.append(next);
			digitindex++;
		}

		// Fill output with zeros until decimal point if not already reached
		while (!foundDecimal) {
			if (digitindex >= input.length() || input.charAt(digitindex) == '.') {
				foundDecimal = true;
			} else {
				output.append('0');
			}
			digitindex++;
		}

		return output;
	}

	/**
	 *
	 * @param input - StringBuilder representation of a double to pad with least significant digit zeros. No negatives allowed.
	 * @param numDigits - Number of digits in the resulting padded output
	 * @return
	 */
	private static StringBuilder padMe(final StringBuilder input, final int numDigits){
		//System.out.println("Padding: " + input);
		StringBuilder output = new StringBuilder();
		output.append(input);

		// Find out how many significant figures we already have
		StringBuilder inputSigFigs = new StringBuilder();
		inputSigFigs.append(input);
		if (inputSigFigs.indexOf("0.") == 0) {
			inputSigFigs.deleteCharAt(0);
		}
		int decimalIndex = input.indexOf(".");
		if (decimalIndex != -1) {
			inputSigFigs.deleteCharAt(decimalIndex);
		}

		// Number of sig figs missing that need to be added
		int numChars = numDigits - inputSigFigs.length();

		// Add decimal if none exists
		if (input.indexOf(".") == -1) {
			output.append('.');
		}

		for (int i = 0; i < numChars; i++) {
			output.append('0');
		}
		return output;
	}

	/**
	 *
	 * @param inputNum - The double you'd like to round the decimal places for
	 * @param sigDecFigs - The number of decimal places you'd like
	 * @return
	 */
	public static String roundDecimalPlaces(final double inputNum, final int sigDecFigs) {
		final StringBuilder format = new StringBuilder("###0.");
		final StringBuilder negativeZero = new StringBuilder("-0.");
		for (int i = 0; i < sigDecFigs; i++) {
			format.append('0');
			negativeZero.append('0');
		}
		final DecimalFormat df = new DecimalFormat(format.toString());
		final StringBuilder output = new StringBuilder(df.format(inputNum));

		// Deal with negative zero
		if (output.toString().equals(negativeZero.toString())){
			output.deleteCharAt(0);
		}
		return output.toString();
	}
}
