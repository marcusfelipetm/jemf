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

import br.ufrj.ppgi.jemf.utils.Constraint;
import br.ufrj.ppgi.jemf.utils.CustomLogger;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Marcus Machado
 *
 */
public class DBHelper extends SQLiteOpenHelper{

	/**
	 * Database name.
	 */
	private static final String DATABASE_NAME = "jemf_db";
	/**
	 * Database current version.
	 */
	private static final int DATABASE_VERSION = 1;
	/**
	 * Parent context.
	 */
	private static Context parentContext;



	/**
	 * Singleton - This method ensures that there exists only one DBeHelper instance at any time.
	 * Creates a helper object to create, open, and/or manage a database.
	 */
    public static DBHelper getInstance(Context context) {
		// Checking context parameter is not null.
    	Constraint.checkNullParam(context);
    	parentContext = context;
        return SingletonHolder.INSTANCE;
    }

    /**
     * Singleton - Until we need an instance, the SingletonHolder class will not be initialized until required.
     * Use the application context to ensure that it don't accidentally leak an activity context.
     */
	private static class SingletonHolder {
		private static final DBHelper INSTANCE = new DBHelper(parentContext);
	}



	/**
	 * Constructor.
	 * This should be private to prevent direct instantiation.
     * Make call to static factory method "getInstance()" instead.
	 */
	private DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
    	CustomLogger.getInstance().infoLog(DBHelper.class.getName(), "Class started.");
	}



	/**
	 * Create database.
	 * Called when the database is created for the first time.
	 * This is where the creation of tables and the initial population of the tables should happen.
	 * @param db
	 *			The database.
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
        // Creating database version 1.
		this.runVersion1(db);
        // Creating database version 2.
		// this.runVersion2(db);
    	CustomLogger.getInstance().infoLog(DBHelper.class.getName(), "New database created.");
	}

	/**
	 * Upgrade database.
	 * Called when the database has to be upgraded.
	 * Where we can drop, alter tables or do anything else it needs to upgrade to the new schema version.
	 * @param db
	 *			The database.
	 * @param oldVersion
	 *			The old database version.
	 * @param newVersion
	 *			The new database version.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	CustomLogger.getInstance().infoLog(DBHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data.");
		// Delete old database.
		this.runDestroy(db);
        // Recreate the database.
        this.onCreate(db);
        // Update the database until the new version.
        int upgradeTo = oldVersion + 1;
        while (upgradeTo <= newVersion) {
            switch (upgradeTo) {
	            case 2:
	            	//this.runVersion2(db);
	            	break;
	            case 3:
	            	//this.runVersion3(db);
	            	break;
            }
            upgradeTo++;
        }
	}

	/**
	 * Destroy database.
	 * @param db
	 *			The database.
	 */
	private void runDestroy(SQLiteDatabase db) {
        try {
        	// Start database transaction.
			db.beginTransaction();
			// Run destroy tables statements.
			// Relationships.
			db.execSQL(new DBStatement.INFORMATION_CONTACT_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.INFORMATION_CONTACT_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.SERVICE_INFORMATION_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.SERVICE_INFORMATION_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.ENTITY_INFORMATION_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.ENTITY_INFORMATION_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.SERVICE_RESOURCE_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.SERVICE_RESOURCE_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.MISSION_TEAM_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.MISSION_TEAM_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.TEAM_USER_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.TEAM_USER_STATEMENT().getDestroy());
	    	// Emergency.
	    	db.execSQL(new DBStatement.EMERGENCY_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.EMERGENCY_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.EMERGENCY_TYPE_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.LEVEL_STATEMENT().getDestroy());
			// Service.
	    	db.execSQL(new DBStatement.MISSION_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.MISSION_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.TASK_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.TASK_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.SERVICE_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.SERVICE_STATUS_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.PRIORITY_STATEMENT().getDestroy());
			// Resource.
	    	db.execSQL(new DBStatement.EQUIPMENT_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.EQUIPMENT_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.RESOURCE_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.RESOURCE_STATUS_STATEMENT().getDestroy());
	    	// Entity.
	    	db.execSQL(new DBStatement.TEAM_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.TEAM_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.VICTIM_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.VICTIM_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.VICTIM_STATUS_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.WITNESS_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.WITNESS_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.VOLUNTEER_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.VOLUNTEER_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.MEDICAL_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.MEDICAL_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.RESPONDER_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.RESPONDER_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.COMMANDER_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.COMMANDER_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.USER_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.PERSON_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.AFFECTED_ORGANIZATION_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.AFFECTED_ORGANIZATION_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.ORGANIZATION_STATUS_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.HEALTH_CARE_UNIT_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.HEALTH_CARE_UNIT_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.ORGANIZATION_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.ENTITY_STATEMENT().getDestroy());
	    	// Position.
	    	db.execSQL(new DBStatement.INTEREST_POINT_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.INTEREST_POINT_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.LOCATION_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.LOCATION_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.PLACE_IDENTIFIER_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.PLACE_IDENTIFIER_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.ADDRESS_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.ADDRESS_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.POSITION_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.POSITION_STATEMENT().getDestroy());
	    	// Information.
	    	db.execSQL(new DBStatement.CONTACT_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.CONTACT_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.LANGUAGE_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.PLAN_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.PLAN_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.SHARED_DOCUMENT_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.TEXT_MESSAGE_STATEMENT().getDestroyView());
	    	db.execSQL(new DBStatement.TEXT_MESSAGE_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.MESSAGE_STATEMENT().getDestroy());
	    	db.execSQL(new DBStatement.INFORMATION_STATEMENT().getDestroy());
        	// End database transaction.
			db.setTransactionSuccessful();
	    	CustomLogger.getInstance().infoLog(DBHelper.class.getName(), "Database with all data destroyed.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
	}

	/**
	 * Database Version 1.
	 * @param db
	 *			The database.
	 */
	private void runVersion1(SQLiteDatabase db) {
        try {
        	// Start database transaction.
			db.beginTransaction();
			// Run create and population tables statements.
	    	// Information.
	    	db.execSQL(new DBStatement.INFORMATION_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.MESSAGE_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.TEXT_MESSAGE_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.TEXT_MESSAGE_STATEMENT().getCreateView());
	    	db.execSQL(new DBStatement.SHARED_DOCUMENT_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.PLAN_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.PLAN_STATEMENT().getCreateView());
	    	db.execSQL(new DBStatement.CONTACT_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.CONTACT_STATEMENT().getCreateView());
	    	db.execSQL(new DBStatement.LANGUAGE_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.LANGUAGE_STATEMENT().getPopulate());
	    	// Position.
	    	db.execSQL(new DBStatement.POSITION_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.POSITION_STATEMENT().getCreateView());
	    	db.execSQL(new DBStatement.ADDRESS_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.ADDRESS_STATEMENT().getCreateView());
	    	db.execSQL(new DBStatement.PLACE_IDENTIFIER_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.PLACE_IDENTIFIER_STATEMENT().getCreateView());	    	
	    	db.execSQL(new DBStatement.LOCATION_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.LOCATION_STATEMENT().getCreateView());
	    	db.execSQL(new DBStatement.INTEREST_POINT_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.INTEREST_POINT_STATEMENT().getCreateView());
	    	// Entity.
	    	db.execSQL(new DBStatement.ENTITY_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.ORGANIZATION_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.HEALTH_CARE_UNIT_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.HEALTH_CARE_UNIT_STATEMENT().getCreateView());
	    	db.execSQL(new DBStatement.ORGANIZATION_STATUS_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.ORGANIZATION_STATUS_STATEMENT().getPopulate());
	    	db.execSQL(new DBStatement.AFFECTED_ORGANIZATION_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.AFFECTED_ORGANIZATION_STATEMENT().getCreateView());
	    	db.execSQL(new DBStatement.PERSON_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.USER_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.COMMANDER_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.COMMANDER_STATEMENT().getCreateView());
	    	db.execSQL(new DBStatement.RESPONDER_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.RESPONDER_STATEMENT().getCreateView());
	    	db.execSQL(new DBStatement.MEDICAL_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.MEDICAL_STATEMENT().getCreateView());
	    	db.execSQL(new DBStatement.VOLUNTEER_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.VOLUNTEER_STATEMENT().getCreateView());
	    	db.execSQL(new DBStatement.WITNESS_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.WITNESS_STATEMENT().getCreateView());
	    	db.execSQL(new DBStatement.VICTIM_STATUS_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.VICTIM_STATUS_STATEMENT().getPopulate());
	    	db.execSQL(new DBStatement.VICTIM_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.VICTIM_STATEMENT().getCreateView());
	    	db.execSQL(new DBStatement.TEAM_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.TEAM_STATEMENT().getCreateView());
			// Resource.
			db.execSQL(new DBStatement.RESOURCE_STATUS_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.RESOURCE_STATUS_STATEMENT().getPopulate());
	    	db.execSQL(new DBStatement.RESOURCE_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.EQUIPMENT_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.EQUIPMENT_STATEMENT().getCreateView());
			// Service.
	    	db.execSQL(new DBStatement.SERVICE_STATUS_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.SERVICE_STATUS_STATEMENT().getPopulate());
	    	db.execSQL(new DBStatement.PRIORITY_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.PRIORITY_STATEMENT().getPopulate());
	    	db.execSQL(new DBStatement.SERVICE_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.MISSION_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.MISSION_STATEMENT().getCreateView());
	    	db.execSQL(new DBStatement.TASK_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.TASK_STATEMENT().getCreateView());
	    	// Emergency.
	    	db.execSQL(new DBStatement.LEVEL_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.LEVEL_STATEMENT().getPopulate());
	    	db.execSQL(new DBStatement.EMERGENCY_TYPE_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.EMERGENCY_TYPE_STATEMENT().getPopulate());
	    	db.execSQL(new DBStatement.EMERGENCY_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.EMERGENCY_STATEMENT().getCreateView());
			// Relationships.
	    	db.execSQL(new DBStatement.TEAM_USER_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.TEAM_USER_STATEMENT().getCreateView());
	    	db.execSQL(new DBStatement.MISSION_TEAM_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.MISSION_TEAM_STATEMENT().getCreateView());
	    	db.execSQL(new DBStatement.SERVICE_RESOURCE_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.SERVICE_RESOURCE_STATEMENT().getCreateView());
	    	db.execSQL(new DBStatement.ENTITY_INFORMATION_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.ENTITY_INFORMATION_STATEMENT().getCreateView());
	    	db.execSQL(new DBStatement.SERVICE_INFORMATION_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.SERVICE_INFORMATION_STATEMENT().getCreateView());
	    	db.execSQL(new DBStatement.INFORMATION_CONTACT_STATEMENT().getCreate());
	    	db.execSQL(new DBStatement.INFORMATION_CONTACT_STATEMENT().getCreateView());
        	// End database transaction.
			db.setTransactionSuccessful();
	    	CustomLogger.getInstance().infoLog(DBHelper.class.getName(), "Database version 1 created.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
	}

	/**
	 * Database Version 2.
	 * @param db
	 *			The database.
	 */
/*
	private void runVersion2(SQLiteDatabase db) {
        try {
        	// Start database transaction.
			db.beginTransaction();
			// Run create, alter and population tables statements.
			// Future work
        	// End database transaction.
			db.setTransactionSuccessful();
	    	CustomLogger.getInstance().infoLog(DBHelper.class.getName(), "Database version 2 created.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
	}
*/

}
