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
import java.util.Calendar;
import java.util.Date;

/**
 * @author Marcus Machado
 *
 */
public class CustomConversions {

	/**
	 * Convert Meters Per Minute to Kilometers Per Hour.
	 * @param metersPerMinute
	 * @return double
	 */
	public static double mpmToKmH(double metersPerMinute) {
		return metersPerMinute * 3.6d;		
	}

	/**
	 * Convert Kilometers Per Hour to Meters Per Minute.
	 * @param kmh
	 * @return double
	 */
	public static double kmHToMpm(double kmh) {
		return kmh / 3.6d;		
	}

	/**
	 * Convert Meters Per Minute to Miles Per Hour.
	 * @param metersPerMinute
	 * @return double
	 */
	public static double mpmToMph(double metersPerMinute) {
		return metersPerMinute * 2.23693629d;		
	}

	/**
	 * Convert Miles Per Hour to Meters Per Minute.
	 * @param mph
	 * @return double
	 */
	public static double mphToMpm(double mph) {
		return mph / 2.23693629d;		
	}

	/**
	 * Convert Meters to Feets.
	 * @param meters
	 * @return double
	 */
	public static double meterToFeet(double meters) {
		return meters * 3.2808399d;
	}

	/**
	 * Convert Feets to Meters.
	 * @param feets
	 * @return double
	 */
	public static double feetToMeter(double feets) {
		return feets / 3.2808399d;
	}

	/**
	 * Convert Date of Birth to Age.
	 * @param dateOfBirth
	 * @return integer
	 */
	public static int birthDateToAge(Date dateOfBirth) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateOfBirth);
		Calendar today = Calendar.getInstance();
		int age = today.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
		if (today.get(Calendar.MONTH) < calendar.get(Calendar.MONTH)) {
		  age--;
		} else if (today.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)
					&& today.get(Calendar.DAY_OF_MONTH) < calendar.get(Calendar.DAY_OF_MONTH)) {
		  age--;
		}
		return age;
	}

	/**
	 * Convert Date of Birth to Age.
	 * @param dateOfBirth
	 * @return integer
	 */
	public static int birthDateToAge(String dateOfBirth) {
		Date birthDate;
		int age = 0;
		try {
			// Convert birth of date to Date.
			birthDate = DateTimeFormat.StringToDate(dateOfBirth);
			// Convert Date to Integer by finding the Age.
			age = Integer.valueOf(CustomConversions.birthDateToAge(birthDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return age;
	}

}
