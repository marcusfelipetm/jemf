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
package br.ufrj.ppgi.jemf.mobile.adapter;

import br.ufrj.ppgi.jemf.utils.CustomLogger;
import android.content.Context;
import android.database.Cursor;
import android.widget.CursorAdapter;

/**
 * @author Marcus Machado
 *
 */
public abstract class CustomCursorAdapter extends CursorAdapter {

	/**
	 * Constructor.
	 * @param context
	 * @param cursor
	 * @param flags
	 */
	public CustomCursorAdapter(Context context, Cursor cursor, int flags) {
		super(context, cursor, flags);
    	CustomLogger.getInstance().infoLog(CustomCursorAdapter.class.getName(), "Class started.");
	}

	/**
	 * Check if cursor data is NULL.
	 * @param position
	 * @param columnIndex
	 * @return boolean
	 */
    public boolean isNull(int position, int columnIndex) {
        Cursor c = getCursor();
        boolean result = true;
        if (c != null && c.moveToPosition(position)) {
            result = c.isNull(columnIndex);
        }
        return result;
    }

    /**
	 * Get cursor data as Boolean.
     * @param position
     * @param columnIndex
     * @return boolean
     */
    public boolean getBoolean(int position, int columnIndex) {
        Cursor c = getCursor();
        boolean result = false;
        if (c != null && c.moveToPosition(position)) {
        	result = c.getInt(columnIndex) != 0;
        }
        return result;
    }

    /**
	 * Get cursor data as Integer.
     * @param position
     * @param columnIndex
     * @return int
     */
    public int getInt(int position, int columnIndex) {
        Cursor c = getCursor();
        int result = -1;
        if (c != null && c.moveToPosition(position)) {
        	result = c.getInt(columnIndex);
        }
        return result;
    }

    /**
	 * Get cursor data as Long.
     * @param position
     * @param columnIndex
     * @return long
     */
    public long getLong(int position, int columnIndex) {
        Cursor c = getCursor();
        long result = -1;
        if (c != null && c.moveToPosition(position)) {
        	result = c.getLong(columnIndex);
        }
        return result;
    }

    /**
	 * Get cursor data as String.
     * @param position
     * @param columnIndex
     * @return String
     */
    public String getString(int position, int columnIndex) {
        Cursor c = getCursor();
        String result = null;
        if (c != null && c.moveToPosition(position)) {
        	result = c.getString(columnIndex);
        }
        return result;
    }

}
