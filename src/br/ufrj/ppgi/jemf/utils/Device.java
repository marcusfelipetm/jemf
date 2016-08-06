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

import android.content.Context;
import android.content.res.Configuration;

/**
 * @author Marcus Machado
 *
 */
public class Device {

	/**
	 * Android SDK Level.
	 * @return int
	 * 			SDK Level.
	 */
	public static int androidLevel() {
		return android.os.Build.VERSION.SDK_INT;
	}

	/**
	 * Android Release.
	 * @return String
	 * 			Release.
	 */
	public static String androidVersion() {
		return android.os.Build.VERSION.RELEASE;
	}

	/**
	 * Android Version.
	 * @return String
	 * 			Version.
	 */
	public static String androidCodename() {
		return android.os.Build.VERSION.CODENAME;
	}

	/**
	 * Android Kernel Version.
	 * @return String
	 * 			Kernel Version.
	 */
	public static String kernelVersion() {
		return System.getProperty("os.version");
	}

	/**
	 * Device Name.
	 * @return String
	 * 			Device Name.
	 */
	public static String deviceName() {
		return android.os.Build.DEVICE;
	}

	/**
	 * Device Manufacturer.
	 * @return String
	 * 			Device Manufacturer.
	 */
	public static String deviceManufacturer() {
		return android.os.Build.MANUFACTURER;
	}

	/**
	 * Device Model.
	 * @return String
	 * 			Device Model.
	 */
	public static String deviceModel() {
		return android.os.Build.MODEL;
	}

	/**
	 * Device Brand.
	 * @return String
	 * 			Device Brand.
	 */
	public static String deviceBrand() {
		return android.os.Build.BRAND;
	}

	/**
	 * Device Product.
	 * @return String
	 * 			Device Product.
	 */
	public static String deviceProduct() {
		return android.os.Build.PRODUCT;
	}

	/**
	* Determine if the device is a tablet (i.e. it has a large screen).
	* @param context
	* 			The parent context.
	* @return boolean
	* 			Is Tablet Screen or not.
	*/
	public static boolean isTablet(Context context) {
		return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

}
