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

import java.io.ObjectInputStream.GetField;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * Math is used to provide math functions specific to the project.
 * @author Ben Fenner
 */
public final class SigFigUtils {

	private SigFigUtils() {
	}

//	public static String round(final double inputNum, final int sigFigs) {
//		//Deal with request for zero or negative sig figs
//		if (sigFigs <= 0) {
//			return "";
//		}
//
//		BigDecimal inputBigDec = new BigDecimal(inputNum);
//		StringBuilder input = new StringBuilder(inputBigDec.toPlainString());
//		StringBuilder result = new StringBuilder();
//		String negativeSign = "";
//
//		System.out.println(input.toString());
//		// Deal with negative
//		if (input.charAt(0) == '-') {
//			negativeSign = "-";
//			input.deleteCharAt(0);
//		}
//
//		// Remove decimal point
//		int decimalIndex = input.indexOf(".");
//		if (decimalIndex != -1) { // Has fractional component so decimal point to deal with
//			input.deleteCharAt(decimalIndex); // Get rid of the decimal point for now
//			if (decimalIndex == 1 && input.charAt(0) == '0') {
//				decimalIndex--;
//			}
//		}
//
//		// Deal with input of zero
//		if (input.length() == 1 && input.charAt(0) == '0'){
//			return "0";
//		}
//
//		// Get input without leading zeros
//		BigInteger value = new BigInteger(input.toString());
//		final String stripped = value.toString();
//		System.out.println(stripped);
//		
//		// Do rounding
//		if (stripped.length() > sigFigs) { // Possible rounding needed
//			System.out.println("Possible rounding needed.");
//			int lastPlace = Character.getNumericValue(stripped.charAt(sigFigs)); // Get the digit after the last sig fig
//			if (lastPlace >= 5) { // Rounding is needed
//				StringBuilder addMe = new StringBuilder();
//				addMe.append("5");
//				for (int i = 0; i < stripped.length() - (sigFigs + 1); i++){
//					addMe.append("0");
//				}
//				System.out.println("Used to round: "  + addMe.toString());
//				final BigInteger addThis = new BigInteger(addMe.toString());
//				value = value.add(addThis);
//			}
//		}
//		result.append(value.toString());
//		
//		System.out.println("Rounded: "  + result.toString());
//
//		// Put the decimal point back if it was removed before
//		if (decimalIndex != -1) {
//			int offset = result.toString().length() - stripped.length();
//			decimalIndex += offset;
//			result.insert(decimalIndex, '.');
//			
//			// Put leading zero in for bare decimals
//			if (decimalIndex == 0) {
//				result.insert(decimalIndex, '0');
//			}
//		}
//
//		System.out.println(negativeSign + result.toString());
//
//		return truncate(new Double(negativeSign + result.toString()), sigFigs);
//	}
	
	public static String round(final double inputNum, final int sigFigs) {
		return "";
	}

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

	
	public static String truncate(final double inputNum, final int sigFigs) {

		//Deal with request for zero or negative sig figs
		if (sigFigs <= 0) {
			return "";
		}

		BigDecimal bd = new BigDecimal(inputNum);
		StringBuilder input = new StringBuilder(bd.toPlainString());
		StringBuilder result = new StringBuilder();
		int digitsNeeded = sigFigs;
		boolean foundDecimal = false;

		// Deal with negative
		if (input.charAt(0) == '-') {
			result.append('-');
			input.deleteCharAt(0);
		}

		// Deal with leading zero for fractions
		if (input.length() > 1 && input.charAt(0) == '0' && input.charAt(1) == '.') {
			foundDecimal = true;
			input.deleteCharAt(0);
			input.deleteCharAt(0);
			result.append("0.");
		}

		// Deal with whole number digits if we need to
		while (!foundDecimal && input.length() > 0) {
			if (input.charAt(0) == '.'){ // We are at the decimal point
				foundDecimal = true;
				if (digitsNeeded > 0) {
					result.append('.');
					input.deleteCharAt(0);
				}
			} else if (digitsNeeded > 0) { // Digit is wanted
				result.append(input.charAt(0));
				input.deleteCharAt(0);
				digitsNeeded--;
			} else {  // Digit is not wanted but place holder is needed
				result.append('0');
				input.deleteCharAt(0);
			}
		}

		// Deal with fraction digits if we have to
		while (input.length() > 0) {
			if (digitsNeeded > 0) { // Digit is wanted
				result.append(input.charAt(0));
				input.deleteCharAt(0);
				digitsNeeded--;
			} else {
				input.deleteCharAt(0);
			}
		}

		return result.toString();
	}
}
