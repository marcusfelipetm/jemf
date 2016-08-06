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
public class FeatureHolder {

	/**
	 * Enumeration responsible to control the features instantiation of JEMF.
	 */
	public enum EnumFeatureType {

		EMERGENCY,
		MISSION,
		TASK,
		EQUIPMENT,
		HEALTH_CARE_UNIT,
		AFFECTED_ORGANIZATION,
		COMMANDER,
		RESPONDER,
		MEDICAL,
		VOLUNTEER,
		WITNESS,
		VICTIM,
		TEAM,
		LOCATION,
		INTEREST_POINT,
		TEXT_MESSAGE,
		PLAN,
		CONTACT;



		/*
		 * Return the Enumeration as String Array.
		 */
		public static final String[] names = new String[values().length];
	    static {
	    	EnumFeatureType[] values = values();
	        for (int i = 0; i < values.length ; i++)
	            names[i] = values[i].name();
	    }

	}



	/**
	 * Current EnumFeatureType.
	 */
	private static EnumFeatureType managerType = null;



	/**
	 * Constructor.
	 * This should be private to prevent direct instantiation.
	 */
	private FeatureHolder() {
	}



	/**
	 * Get current feature.
	 * @return the managerType
	 */
	public static EnumFeatureType getFeature() {
		return FeatureHolder.managerType;
	}

	/**
	 * Set current feature.
	 * @param type the type to set
	 */
	public static void setFeature(EnumFeatureType type) {
		FeatureHolder.managerType = type;
	}

}
