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
package br.ufrj.ppgi.jemf.mobile.factory;

import android.content.Context;
import br.ufrj.ppgi.jemf.mobile.CustomBuildConfig;
import br.ufrj.ppgi.jemf.mobile.provider.CustomProviderChecker;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 * Future work!
 */
public class OptionalFactory {

	/**
	 * Parent Context.
	 */
	private Context parentContext;
	/**
	 * Current Sensor.
	 */
	private static Object sensor = null;



	/**
	 * Constructor.
	 * Optional Factory instantiation.
	 */
	public OptionalFactory(Context context) {
    	CustomLogger.getInstance().infoLog(MainFactory.class.getName(), "Class started.");
		this.parentContext = context;
		// Check if a JEMF provider was declared in application's manifest, otherwise application will not read/write JEMF database.
		if (CustomBuildConfig.TEST) {
			// Note: Do NOT check when is running tests.
		} else {
			CustomProviderChecker customProviderChecker = CustomProviderChecker.check(this.parentContext);
			// Content Provider was found?
			if (!(customProviderChecker.jemfProviderFound())) {
				String errorMsg = "Provider not declared in application's manisfet.";
		    	CustomLogger.getInstance().errorLog(MainFactory.class.getName(), errorMsg);
				throw new RuntimeException(errorMsg);
			}
    	}
	}



	/**
	 * Get a new Sensor.
	 * @return Sensor
	 */
	public Object getSensor() {
		return OptionalFactory.sensor;
	}

	/**
	 * Set the current Sensor.
	 * @param sensor
	 * 			The sensor to set.
	 */
	@SuppressWarnings("unused")
	private static void setSensor(final Object sensor) {
		OptionalFactory.sensor = sensor;
	}

}
