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
package br.ufrj.ppgi.jemf.mobile.loader;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public abstract class CustomLoader extends CursorLoader {

	/**
	 * Exception Object.
	 */
	private RuntimeException error;



	/**
	 * Constructor.
	 * @param context
	 * @param uri
	 * @param projection
	 * @param selection
	 * @param selectionArgs
	 * @param sortOrder
	 */
	public CustomLoader(Context context, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		super(context, uri, projection, selection, selectionArgs, sortOrder);
    	CustomLogger.getInstance().infoLog(CustomLoader.class.getName(), "Class started.");
	}



	/**
	 * Load data in background asynchronously.
	 * @return Cursor
	 */
    @Override
    public Cursor loadInBackground() {
    	CustomLogger.getInstance().infoLog(CustomLoader.class.getName(), "Data load in background.");
        try {
            this.error = null;
            return super.loadInBackground();
        } catch (RuntimeException e) {
        	this.error = e;
            return null;
        }
    }

    /**
     * Rethrow the exception.
     */
    public void rethrow() {
    	CustomLogger.getInstance().infoLog(CustomLoader.class.getName(), "Rethrow exception.");
        if (this.error != null) {
            throw this.error;
        }
    }

}
