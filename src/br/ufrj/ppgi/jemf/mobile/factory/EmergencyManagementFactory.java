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
import br.ufrj.ppgi.jemf.mobile.FeatureHolder;
import br.ufrj.ppgi.jemf.mobile.FeatureHolder.EnumFeatureType;
import br.ufrj.ppgi.jemf.mobile.provider.CustomProvider;
import br.ufrj.ppgi.jemf.utils.Constraint;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class EmergencyManagementFactory {

	/**
	 * Parent Context.
	 */
	private static Context parentContext;



	/**
	 * Constructor.
	 * Singleton - This method ensures that there exists only one Factory instance at any time.
	 * Creates a helper object to access Emergency Management Framework
	 * @param context
	 */
    public static EmergencyManagementFactory newInstance(Context context) {
		// Checking context parameter is not null.
    	Constraint.checkNullParam(context);
    	// Checking if is Test project building, otherwise verify if CustomProvider is created.
    	checkTestConfig(context);
    	// Initialize Logger
    	initLogger(getContext());
    	return SingletonHolder.INSTANCE;
    }

    /**
     * Singleton - Until we need an instance, the SingletonHolder class will not be initialized until required.
     */
	private static class SingletonHolder {
		private static final EmergencyManagementFactory INSTANCE = new EmergencyManagementFactory();
	}



	/**
	 * Constructor.
	 * Emergency Management Factory instantiation.
	 */
	private EmergencyManagementFactory() {
		CustomLogger.getInstance().infoLog(EmergencyManagementFactory.class.getName(), "Class started.");
	}



	/**
	 * Get the parent context.
	 * @return Context
	 */
	public static Context getContext() {
		return parentContext;
	}

	/**
	 * Get Main Factory.
	 * @return MainFactory
	 */
	public MainFactory getMainFactory(EnumFeatureType type) {
		// Checking type parameter is not null.
		Constraint.checkNullParam(type);
		FeatureHolder.setFeature(type);
	   	CustomLogger.getInstance().infoLog(EmergencyManagementFactory.class.getName(), "New MainFactory created.");
		return new MainFactory(getContext());
	}

	/**
	 * Get Optional Factory.
	 * @return OptionalFactory
	 */
	public OptionalFactory getOptionalFactory() {
	   	CustomLogger.getInstance().infoLog(EmergencyManagementFactory.class.getName(), "New OptionalFactory created.");
		return new OptionalFactory(getContext());
	}



	/**
	 * Check if the project is configured for running Tests (jemf-test subproject).
	 * @param context
	 */
	private static void checkTestConfig(Context context) {
    	// Use context when is running tests.
    	if (CustomBuildConfig.TEST) {
    		parentContext = context;
        	CustomLogger.getInstance().infoLog(EmergencyManagementFactory.class.getName(), "Unit test configuration started.");
    	} else {
        	// Or when normal build, use the application context to ensure that it don't accidentally leak an activity context.
        	parentContext = context.getApplicationContext();
    	}		
	}

	/**
	 * Custom Logger initialization.
	 * This method creates a new instance of Custom Logger.
	 * @param context
	 */
	private static void initLogger(Context context) {
		CustomLogger.newInstance(context).startLog(CustomBuildConfig.SAVE_LOG_EXTERNAL_DIR);
    	CustomLogger.getInstance().infoLog(CustomProvider.class.getName(), "Class started.");
	}

}
