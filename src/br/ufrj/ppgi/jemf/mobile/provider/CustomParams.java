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
package br.ufrj.ppgi.jemf.mobile.provider;

import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class CustomParams {

	/**
	 * Selection statement to get data through Content Provider.
	 */
	private String selection = null;
	/**
	 * Selection arguments used to get data through Content Provider.
	 */
	private String[] selectionArgs = null;
	/**
	 * Sort Order statement to get data through Content Provider.
	 */
	private String sortOrder = null;



	/**
	 * Constructor.
	 */
	public CustomParams() {
    	CustomLogger.getInstance().infoLog(CustomParams.class.getName(), "Class started.");
	}

	/**
	 * Constructor.
	 * @param selection
	 * @param selectionArgs
	 * @param sortOrder
	 */
	public CustomParams(String selection, String[] selectionArgs, String sortOrder) {
    	CustomLogger.getInstance().infoLog(CustomParams.class.getName(), "Class started.");
		this.selection = selection;
		this.selectionArgs = selectionArgs;
		this.sortOrder = sortOrder;
	}



	/**
	 * Get the current Selection.
	 * @return String
	 * 			The current Selection param.
	 */
	public String getSelection() {
		return this.selection;
	}

	/**
	 * Set the current Selection.
	 * @param selection
	 */
	public void setSelection(String selection) {
		this.selection = selection;
	}

	/**
	 * Get the current Selection Arguments.
	 * @return String[]
	 * 			The current SelectionArgs param.
	 */
	public String[] getSelectionArgs() {
		return this.selectionArgs;
	}

	/**
	 * Set the current Selection Arguments.
	 * @param selectionArgs
	 */
	public void setSelectionArgs(String[] selectionArgs) {
		this.selectionArgs = selectionArgs;
	}

	/**
	 * Get the current Sort Order.
	 * @return String
	 * 			The current Sort Order param.
	 */
	public String getSortOrder() {
		return this.sortOrder;
	}

	/**
	 * Set the current Sort Order.
	 * @param sortOrder
	 */
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

}
