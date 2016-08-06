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

import android.util.Log;

/**
 * @author Marcus Machado
 *
 */
public class Constraint {

	/**
	 * Check if a parameter is NULL and get the StackTrace.
	 * @param object
	 */
	public static void checkNullParam(Object object) {
		try {
			if (object == null) {
				// Invalid parameter error
				throw new NullPointerException(" Error: Unexpected null value for Object. Found error inside of: ");
			}
		} catch (Exception e) {
			Log.e(CustomLogger.LOG_TAG, CustomLogger.SEPARATOR+CustomLogger.SEPARATOR+ Log.getStackTraceString(e) +
					CustomLogger.LINE_BREAK+CustomLogger.SEPARATOR+CustomLogger.SEPARATOR+ "Object is NULL, checked by Constraint.checkNullParam() method.");
		}
	}

}
