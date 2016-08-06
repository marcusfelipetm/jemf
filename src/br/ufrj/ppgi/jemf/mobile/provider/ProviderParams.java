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

import android.net.Uri;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class ProviderParams extends CustomParams {

	/**
	 * Current URI.
	 */
	private Uri URI;
	/**
	 * Columns database projection used to get data by Content Provider.
	 */
	private String[] projection = null;



	/**
	 * Constructor.
	 */
	public ProviderParams() {
    	CustomLogger.getInstance().infoLog(ProviderParams.class.getName(), "Class started.");
	}

	/**
	 * Constructor.
	 * @param uri
	 * @param projection
	 * @param selection
	 * @param selectionArgs
	 * @param sortOrder
	 */
	public ProviderParams(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
    	CustomLogger.getInstance().infoLog(ProviderParams.class.getName(), "Class started.");
		setURI(uri);
		setProjection(projection);
		setSelection(selection);
		setSelectionArgs(selectionArgs);
		setSortOrder(sortOrder);
	}



	/**
	 * Get the current Uri.
	 * @return Uri
	 * 			The current Uri param.
	 */
	public Uri getURI() {
		return this.URI;
	}

	/**
	 * Set the current Uri.
	 * @param uri
	 */
	public void setURI(Uri uri) {
		this.URI = uri;
	}

	/**
	 * Get the current Projection.
	 * @return String[]
	 * 			The current Projection param.
	 */
	public String[] getProjection() {
		return this.projection;
	}

	/**
	 * Set the current Projection.
	 * @param projection
	 */
	public void setProjection(String[] projection) {
		this.projection = projection;
	}

}
