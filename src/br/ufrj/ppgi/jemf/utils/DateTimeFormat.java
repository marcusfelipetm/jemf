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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Marcus Machado
 *
 */
public class DateTimeFormat {

	/**
	 * DateTime US pattern.
	 */
	private static final String DATETIME_PATTERN_US = "MM/dd/yyyy HH:mm:ss";
	/**
	 * DateTime US pattern, only digits. 
	 */
	private static final String DATETIME_DIGITS_PATTERN_US = "MMddyyyyHHmmss";
	/**
	 * Date US pattern.
	 */
	private static final String DATE_PATTERN_US = "MM/dd/yyyy";
	/**
	 * Time US pattern.
	 */
	private static final String TIME_PATTERN_US = "HH:mm:ss";

	/**
	 * Return a long diff time
	 * @param minorTime
	 * @param greaterTime
	 * @return Long
	 */
	public static long getLongDiffTime(Date minorTime, Date greaterTime) {
		long diff = greaterTime.getTime() - minorTime.getTime();
		long timeInSeconds = diff / 1000;
		long hours = timeInSeconds / 3600;
		timeInSeconds = timeInSeconds - (hours * 3600);
		long minutes = timeInSeconds / 60;
		timeInSeconds = timeInSeconds - (minutes * 60);
		return timeInSeconds;
	}

	/**
	 * Return a String diff time
	 * @param minorTime
	 * @param greaterTime
	 * @return String
	 */
	public static String getStringDiffTime(Date minorTime, Date greaterTime) {
		return secondsToString(getLongDiffTime(minorTime, greaterTime));
	}

	/**
	 * Format DateTime to String
	 * @param dateTime
	 * @return String
	 */
	public static String DateTimeToString (Date dateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATETIME_PATTERN_US, Locale.ROOT);
        return  dateFormat.format(dateTime);
	}

	/**
	 * Format String to DateTime
	 * @param dateTime
	 * @return Date
	 * @throws Exception
	 */
	public static Date StringToDateTime (String dateTime) throws ParseException {
        if (dateTime == null || dateTime.equals(""))
            return null;
        Date result = null;
        try {
        	SimpleDateFormat dateFormat = new SimpleDateFormat(DATETIME_PATTERN_US, Locale.ROOT);
            dateFormat.setLenient(false);
        	result = dateFormat.parse(dateTime);
        } catch (ParseException e) {
            throw e;
        }
        return result;
	}

	/**
	 * Format Date to String
	 * @param date
	 * @return String
	 */
	public static String DateToString (Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN_US, Locale.ROOT);
		return dateFormat.format(date);
	}

	/**
	 * Format String to Date
	 * @param date
	 * @return Date
	 * @throws ParseException
	 */
	public static Date StringToDate (String date) throws ParseException {
        if (date == null || date.equals(""))
            return null;
        Date result = null;
        try {
    		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN_US, Locale.ROOT);
            dateFormat.setLenient(false);
        	result = dateFormat.parse(date);
        } catch (ParseException e) {
            throw e;
        }
        return result;
	}

	/**
	 * Format Time to String
	 * @param time
	 * @return String
	 */
	public static String TimeToString (Date time) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_PATTERN_US, Locale.ROOT);
		return dateFormat.format(time);
	}

	/**
	 * Format String to Time
	 * @param time
	 * @return Date
	 * @throws ParseException
	 */
	public static Date StringToTime (String time) throws ParseException {
        if (time == null || time.equals(""))
            return null;
        Date result = null;
        try {
    		SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_PATTERN_US, Locale.ROOT);
            dateFormat.setLenient(false);
        	result = dateFormat.parse(time);
        } catch (ParseException e) {
            throw e;
        }
        return result;
	}

	/**
	 * Format DateTime to String only digits
	 * @param date
	 * @return String
	 */
	public static String DateTimeToStringOnlyDigits (Date date) {
 	    SimpleDateFormat dateFormat = new SimpleDateFormat(DATETIME_DIGITS_PATTERN_US, Locale.ROOT);
 	    return dateFormat.format(date);
	}

	/**
	 * Format DateTime to String only digits
	 * @param dateTime
	 * @return Date
	 */
	public static Date StringToDateTimeOnlyDigits (String dateTime) throws ParseException {
		if (dateTime == null || dateTime.equals(""))
		    return null;
		Date result = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATETIME_DIGITS_PATTERN_US, Locale.ROOT);
		    dateFormat.setLenient(false);
			result = dateFormat.parse(dateTime);
		} catch (ParseException e) {
		    throw e;
		}
		return result;
	}

	/**
	 * Format Seconds to String
	 * @param seconds
	 * @return String
	 */
	public static String secondsToString(int seconds) {
	    int hours = seconds / 3600;
	    int minutes = (seconds % 3600) / 60;
	    seconds = seconds % 60;
	    return twoDigitToString(hours) + " : " + twoDigitToString(minutes) + " : " + twoDigitToString(seconds);
	}

	/**
	 * Format Seconds to String
	 * @param seconds
	 * @return String
	 */
	public static String secondsToString(long seconds) {
	    long hours = seconds / 3600;
	    long minutes = (seconds % 3600) / 60;
	    seconds = seconds % 60;
	    return twoDigitToString(hours) + " : " + twoDigitToString(minutes) + " : " + twoDigitToString(seconds);
	}

	/**
	 * Format Digit to String
	 * @param number
	 * @return String
	 */
	public static String twoDigitToString(int number) {
	    if (number == 0) {
	        return "00";
	    }
	    if (number / 10 == 0) {
	        return "0" + number;
	    }
	    return String.valueOf(number);
	}

	/**
	 * Format Digit to String
	 * @param number
	 * @return String
	 */
	public static String twoDigitToString(long number) {
	    if (number == 0) {
	        return "00";
	    }
	    if (number / 10 == 0) {
	        return "0" + number;
	    }
	    return String.valueOf(number);
	}

}
