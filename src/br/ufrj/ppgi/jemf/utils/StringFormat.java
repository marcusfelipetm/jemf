/**************************************************************************************
 * Copyright (c) 2014, Marcus Machado, All rights reserved.
 * Copyright (c) 2014, Java Emergency Management Framework - JEMF, All rights reserved.
 * Graduate Program on Informatics (PPGI), Federal University of Rio de Janeiro (UFRJ).
 *  
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *   See the GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 **************************************************************************************/
package br.ufrj.ppgi.jemf.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Locale;

/**
 * @author Marcus Machado
 */
public class StringFormat {

	/**
	 * Accents chars.
	 */
	private static final String accents = "·ÈÌÛ˙‡ËÏÚ˘‚ÍÓÙ˚„ÒÁ‰ÎÔˆ¸¡…Õ”⁄¿»Ã“Ÿ¬ Œ‘€√—«ƒÀœ÷‹";
	/**
	 * Normal chars.
	 */
	private static final String normal  = "aeiouaeiouaeiouancaeiouAEIOUAEIOUAEIOUANCAEIOU";



	/**
	 * Format Comma chars to Point.
	 * @param value
	 * @return String
	 */
	public static String commaToPoint(String value) {
    	return value.replace(',', '.');		
	}

	/**
	 * Format Slash chars to Underscore.
	 * @param value
	 * @return String
	 */
	public static String slashToUnderscore(String value) {
    	return value.replace('/', '_');		
	}

	/**
	 * Format Double to String.
	 * @param value
	 * @param decimals
	 * @return String
	 */
	public static String doubleToString(double value, int decimals) {
		String result = format(value, decimals);
		DecimalFormat df = new DecimalFormat();
	    DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();	    
	    if (dfs.getDecimalSeparator() != '.')
	    	result = result.replace('.', dfs.getDecimalSeparator());
	    return result;
	}

	/**
	 * Format Double to String.
	 * @param value
	 * @return String
	 */
	public static String doubleToString(double value) {
		String result = format(value);
		DecimalFormat df = new DecimalFormat();
	    DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
	    if (dfs.getDecimalSeparator() != '.')
	    	result = result.replace('.', dfs.getDecimalSeparator());
	    return result;
	}

	/**
	 * Format Float to String.
	 * @param value
	 * @param decimals
	 * @return String
	 */
	public static String floatToString(float value, int decimals) {
		String result = format(value, decimals);
		DecimalFormat df = new DecimalFormat();
	    DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
	    if (dfs.getDecimalSeparator() != '.')
	    	result = result.replace('.', dfs.getDecimalSeparator());
	    return result;
	}

	/**
	 * Format Float to String.
	 * @param value
	 * @return String
	 */
	public static String floatToString(float value) {
		String result = format(value);
		DecimalFormat df = new DecimalFormat();
	    DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
	    if (dfs.getDecimalSeparator() != '.')
	    	result = result.replace('.', dfs.getDecimalSeparator());
	    return result;
	}

	/**
	 * Fill value with zeros.
	 * @param number
	 * @param length
	 * @return String
	 */
	public static String zeroFill(String number, int length) {
		String result = number;
		for (int i = result.length(); i < length; i++) {
			result = "0" + result;
		}		
		return result;
	}

	/**
	 * Fill value with zeros and format to String.
	 * @param number
	 * @param length
	 * @return String
	 */
	public static String zeroFill(int number, int length) {
		String result = String.valueOf(number);
		for (int i = result.length(); i < length; i++) {
			result = "0" + result;
		}
		return result;
	}

	/**
	 * Format Double to String.
	 * @param x
	 * @return String
	 */
	public static String format(double x) {
	    return String.format(Locale.ROOT, "%.2f", x);
	}

	/**
	 * Format Float to String.
	 * @param x
	 * @return String
	 */
	public static String format(float x) {
	    return String.format(Locale.ROOT, "%.2f", x);
	}

	/**
	 * Format Double to String.
	 * @param x
	 * @param decimals
	 * @return String
	 */
	public static String format(double x, int decimals) {
	    return String.format("%." + String.valueOf(decimals) + "f", x);
	}

	/**
	 * Format Float to String.
	 * @param x
	 * @param decimals
	 * @return String
	 */
	public static String format(float x, int decimals) {
	    return String.format("%." + String.valueOf(decimals) + "f", x);
	}

	/**
	 * Remove String accents. 
	 * @param phrase
	 * @return String
	 */
	public static String removeAccents (String phrase) {
		String result = "";
		if (phrase != null && !phrase.equals("")) {
			for (int i=0; i < phrase.length(); i++) {
				int x = accents.indexOf(phrase.substring(i,  i+1));		
				if (x == -1)
					result += phrase.substring(i, i+1);
				else
					result += normal.substring(x, x+1);
			}
		}
		return result;
	}

	/**
	 * Remove String spaces.
	 * @param phrase
	 * @return String
	 */
	public static String removeSpace (String phrase) {
		String result = "";
		if (phrase != null && !phrase.equals("")) {
			for (int i=0; i < phrase.length(); i++) {
				if (!phrase.substring(i, i+1).equals(" "))
					result += phrase.substring(i, i+1);
			}
		}
		return result;
	}

	/**
	 * Get only digits.
	 * @param number
	 * @param inclubePlusSignal
	 * @return String
	 */
	public static String onlyDigits(String number, boolean inclubePlusSignal) {
		String result = "";
		if (number != null && !number.equals("")) {
			for (int i=0; i < number.length(); i++) {
				char[] c = number.substring(i, i+1).toCharArray();
				if ((c[0] >= 48 && c[0] <= 71) || (inclubePlusSignal && number.substring(i, i+1).equals("+")))
					result += number.substring(i, i+1);
			}
		}
		return result;
	}

	/**
	 * Get only digits.
	 * @param number
	 * @return String
	 */
	public static String onlyDigits(String number) {
		return onlyDigits (number, false);
	}

	/**
	 * Concatenate 2 String Arrays.
	 * @param first
	 * @param second
	 * @return T
	 * 			One String Array.
	 */
	public static <T> T[] concatenateArrays(T[] first, T[] second) {
		T[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}

	/**
	 * Remove duplicate item in String Array.
	 * @param array
	 * @return String[]
	 * 			String Array without duplicate items.
	 */
	public static String[] duplicateRemove(String[] array) {
		return new LinkedHashSet<String>(Arrays.asList(array)).toArray(new String[0]);
	}

}
