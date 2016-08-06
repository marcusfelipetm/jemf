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
package br.ufrj.ppgi.jemf.mobile.database;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author Marcus Machado
 *
 */
public class DBAdapter {

	/**
	 * SQLite database.
	 */
	private SQLiteDatabase db = null;
	/**
	 * DB Helper.
	 */
	private DBHelper dbHelper;



	/**
	 * Constructor.
	 */
	public DBAdapter() {
	}

	/**
	 * Get database.
	 * @return SQLiteDatabase
	 * 			The Db.
	 */
	public SQLiteDatabase getDB() {
		return this.db;
	}

	/**
	 * Set database.
	 * @param db
	 * 		The database to set.
	 */
	private void setDB(SQLiteDatabase db) {
		this.db = db;
	}

	/**
	 * Open database connection.
	 */
	public DBAdapter connectDB() throws SQLException {
		// this.dbHelper = DBHelper.getInstance();
		// Writable database handles both reads and writes.
		this.setDB(this.dbHelper.getWritableDatabase());
		return this;
	}

	/**
	 * Close database connection.
	 */
	public void disconnectDB() throws SQLException {
		this.dbHelper.close();
		this.dbHelper = null;
        this.db = null;
	}

}
