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
public class OperationHolder {

	/**
	 * Enumeration responsible to control the current operation of JEMF (CRUD).
	 */
	public enum EnumOperationType {

		CREATE,
		READ,
		READ_ALL,
		UPDATE,
		UPDATE_ALL,
		DELETE,
		DELETE_ALL;



		/*
		 * Return the Enumeration as String Array.
		 */
		public static final String[] names = new String[values().length];
	    static {
	    	EnumOperationType[] values = values();
	        for (int i = 0; i < values.length ; i++)
	            names[i] = values[i].name();
	    }

	}



	/**
	 * Current EnumOperationType.
	 */
	private static EnumOperationType operationType = null;



	/**
	 * Constructor.
	 * This should be private to prevent direct instantiation.
	 */
	private OperationHolder() {
	}



	/**
	 * Get current operation.
	 * @return the managerType
	 */
	public static EnumOperationType getOperation() {
		return OperationHolder.operationType;
	}

	/**
	 * Set current operation.
	 * @param type the type to set
	 */
	public static void setOperation(EnumOperationType type) {
		OperationHolder.operationType = type;
	}

}