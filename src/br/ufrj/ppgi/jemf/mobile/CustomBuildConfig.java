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
package br.ufrj.ppgi.jemf.mobile;

/**
 * @author Marcus Machado
 *
 */
public class CustomBuildConfig {

	/**
	 * Toggle this boolean constant's value to turn on/off logging within the project.
	 */
	public static final boolean VERBOSE = false;

	/**
	 * Toggle this boolean constant's value to turn on/off saving log files at External Directory.
	 */
	public static final boolean SAVE_LOG_EXTERNAL_DIR = false;

	/**
	 * Toggle this boolean constant's value to turn on/off custom Unit Test configuration within the project.
	 * Note: 1- Case TEST is TRUE then VERBOSE MUST BE FALSE, because test build uses Mock Context and Log can not run.
	 * 		 2- RUN TEST ON AN BRAND NEW EMULATOR AND WITHOUT JEMF EXAMPLE APPS.
	 */
	public static final boolean TEST = false;

}
