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

/**
 * @author Marcus Machado
 *
 */
public class CustomFormat {

	/**
	 * Format Integer to Boolean.
	 * @param integer
	 * @return Boolean
	 * 			Integer represented by a Boolean - 1: true, 0: false.
	 */
	public static Boolean IntToBool(Integer integer) {
		Boolean result = false;
		switch (integer) {
		case 1:
			result = true;
			break;
		default:
			break;
		}
		return result;		
	}

	/**
	 * Format Boolean to Integer.
	 * @param bool
	 * @return Integer
	 * 			Boolean represented by an Integer - 1: true, 0: false.
	 */
	public static Integer BoolToInt(Boolean bool) {
		Integer result = 0;
		if (bool) {
			result = 1;
		}
		return result;		
	}

}
