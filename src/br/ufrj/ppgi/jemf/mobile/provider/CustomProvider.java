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

import java.util.Arrays;
import java.util.HashSet;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import br.ufrj.ppgi.jemf.mobile.CustomBuildConfig;
import br.ufrj.ppgi.jemf.mobile.database.DBHelper;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class CustomProvider extends ContentProvider {

	/**
	 * Database Helper.
	 */
	private DBHelper dbHelper;
	/**
	 * Uri Matcher.
	 */
    private static final UriMatcher uriMatcher;



    /**
     * Constructor.
     * A content URI pattern matches content URIs using wildcard characters:
     * (*): Matches a string of any valid characters of any length.
     * (#): Matches a string of numeric characters of any length.
     */
    static {
    	// Start Uri Matcher.
    	uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    	// Emergency.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.EMERGENCY.PATH, ProviderStatement.EMERGENCY.ALL_EMERGENCIES);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.EMERGENCY.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.EMERGENCY.SINGLE_EMERGENCY);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.EMERGENCY.PATH_READ, ProviderStatement.EMERGENCY.ALL_EMERGENCIES_READ);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.EMERGENCY.PATH_READ + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.EMERGENCY.SINGLE_EMERGENCY_READ);
    	// Service.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.SERVICE.PATH, ProviderStatement.SERVICE.ALL_SERVICES);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.SERVICE.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.SERVICE.SINGLE_SERVICE);
    	// Mission.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.MISSION.PATH, ProviderStatement.MISSION.ALL_MISSIONS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.MISSION.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.MISSION.SINGLE_MISSION);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.MISSION.PATH_READ, ProviderStatement.MISSION.ALL_MISSIONS_READ);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.MISSION.PATH_READ + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.MISSION.SINGLE_MISSION_READ);
    	// Task.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.TASK.PATH, ProviderStatement.TASK.ALL_TASKS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.TASK.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.TASK.SINGLE_TASK);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.TASK.PATH_READ, ProviderStatement.TASK.ALL_TASKS_READ);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.TASK.PATH_READ + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.TASK.SINGLE_TASK_READ);
    	// Resource.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.RESOURCE.PATH, ProviderStatement.RESOURCE.ALL_RESOURCES);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.RESOURCE.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.RESOURCE.SINGLE_RESOURCE);
    	// Equipment.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.EQUIPMENT.PATH, ProviderStatement.EQUIPMENT.ALL_EQUIPMENTS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.EQUIPMENT.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.EQUIPMENT.SINGLE_EQUIPMENT);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.EQUIPMENT.PATH_READ, ProviderStatement.EQUIPMENT.ALL_EQUIPMENTS_READ);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.EQUIPMENT.PATH_READ + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.EQUIPMENT.SINGLE_EQUIPMENT_READ);
    	// Entity.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.ENTITY.PATH, ProviderStatement.ENTITY.ALL_ENTITIES);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.ENTITY.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.ENTITY.SINGLE_ENTITY);
    	// Organization.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.ORGANIZATION.PATH, ProviderStatement.ORGANIZATION.ALL_ORGANIZATIONS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.ORGANIZATION.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.ORGANIZATION.SINGLE_ORGANIZATION);
    	// Health Care Unit.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.HEALTH_CARE_UNIT.PATH, ProviderStatement.HEALTH_CARE_UNIT.ALL_HEALTH_CARE_UNITS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.HEALTH_CARE_UNIT.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.HEALTH_CARE_UNIT.SINGLE_HEALTH_CARE_UNIT);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.HEALTH_CARE_UNIT.PATH_READ, ProviderStatement.HEALTH_CARE_UNIT.ALL_HEALTH_CARE_UNITS_READ);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.HEALTH_CARE_UNIT.PATH_READ + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.HEALTH_CARE_UNIT.SINGLE_HEALTH_CARE_UNIT_READ);
    	// Affected Organization.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.AFFECTED_ORGANIZATION.PATH, ProviderStatement.AFFECTED_ORGANIZATION.ALL_AFFECTED_ORGANIZATIONS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.AFFECTED_ORGANIZATION.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.AFFECTED_ORGANIZATION.SINGLE_AFFECTED_ORGANIZATION);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.AFFECTED_ORGANIZATION.PATH_READ, ProviderStatement.AFFECTED_ORGANIZATION.ALL_AFFECTED_ORGANIZATIONS_READ);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.AFFECTED_ORGANIZATION.PATH_READ + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.AFFECTED_ORGANIZATION.SINGLE_AFFECTED_ORGANIZATION_READ);
    	// Person.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.PERSON.PATH, ProviderStatement.PERSON.ALL_PEOPLE);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.PERSON.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.PERSON.SINGLE_PERSON);
    	// User.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.USER.PATH, ProviderStatement.USER.ALL_USERS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.USER.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.USER.SINGLE_USER);
    	// Commander.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.COMMANDER.PATH, ProviderStatement.COMMANDER.ALL_COMMANDERS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.COMMANDER.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.COMMANDER.SINGLE_COMMANDER);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.COMMANDER.PATH_READ, ProviderStatement.COMMANDER.ALL_COMMANDERS_READ);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.COMMANDER.PATH_READ + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.COMMANDER.SINGLE_COMMANDER_READ);
    	// Responder.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.RESPONDER.PATH, ProviderStatement.RESPONDER.ALL_RESPONDERS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.RESPONDER.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.RESPONDER.SINGLE_RESPONDER);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.RESPONDER.PATH_READ, ProviderStatement.RESPONDER.ALL_RESPONDERS_READ);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.RESPONDER.PATH_READ + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.RESPONDER.SINGLE_RESPONDER_READ);
    	// Medical.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.MEDICAL.PATH, ProviderStatement.MEDICAL.ALL_MEDICALS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.MEDICAL.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.MEDICAL.SINGLE_MEDICAL);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.MEDICAL.PATH_READ, ProviderStatement.MEDICAL.ALL_MEDICALS_READ);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.MEDICAL.PATH_READ + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.MEDICAL.SINGLE_MEDICAL_READ);
    	// Volunteer.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.VOLUNTEER.PATH, ProviderStatement.VOLUNTEER.ALL_VOLUNTEERS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.VOLUNTEER.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.VOLUNTEER.SINGLE_VOLUNTEER);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.VOLUNTEER.PATH_READ, ProviderStatement.VOLUNTEER.ALL_VOLUNTEERS_READ);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.VOLUNTEER.PATH_READ + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.VOLUNTEER.SINGLE_VOLUNTEER_READ);
    	// Witness.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.WITNESS.PATH, ProviderStatement.WITNESS.ALL_WITNESSES);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.WITNESS.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.WITNESS.SINGLE_WITNESS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.WITNESS.PATH_READ, ProviderStatement.WITNESS.ALL_WITNESSES_READ);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.WITNESS.PATH_READ + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.WITNESS.SINGLE_WITNESS_READ);
    	// Victim.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.VICTIM.PATH, ProviderStatement.VICTIM.ALL_VICTIMS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.VICTIM.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.VICTIM.SINGLE_VICTIM);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.VICTIM.PATH_READ, ProviderStatement.VICTIM.ALL_VICTIMS_READ);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.VICTIM.PATH_READ + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.VICTIM.SINGLE_VICTIM_READ);
    	// Team.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.TEAM.PATH, ProviderStatement.TEAM.ALL_TEAMS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.TEAM.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.TEAM.SINGLE_TEAM);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.TEAM.PATH_READ, ProviderStatement.TEAM.ALL_TEAMS_READ);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.TEAM.PATH_READ + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.TEAM.SINGLE_TEAM_READ);
    	// Position.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.POSITION.PATH, ProviderStatement.POSITION.ALL_POSITIONS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.POSITION.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.POSITION.SINGLE_POSITION);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.POSITION.PATH_READ, ProviderStatement.POSITION.ALL_POSITIONS_READ);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.POSITION.PATH_READ + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.POSITION.SINGLE_POSITION_READ);
    	// Address.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.ADDRESS.PATH, ProviderStatement.ADDRESS.ALL_ADDRESSES);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.ADDRESS.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.ADDRESS.SINGLE_ADDRESS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.ADDRESS.PATH_READ, ProviderStatement.ADDRESS.ALL_ADDRESSES_READ);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.ADDRESS.PATH_READ + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.ADDRESS.SINGLE_ADDRESS_READ);
    	// Place Identifier.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.PLACE_IDENTIFIER.PATH, ProviderStatement.PLACE_IDENTIFIER.ALL_PLACE_IDENTIFIERS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.PLACE_IDENTIFIER.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.PLACE_IDENTIFIER.SINGLE_PLACE_IDENTIFIER);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.PLACE_IDENTIFIER.PATH_READ, ProviderStatement.PLACE_IDENTIFIER.ALL_PLACE_IDENTIFIERS_READ);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.PLACE_IDENTIFIER.PATH_READ + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.PLACE_IDENTIFIER.SINGLE_PLACE_IDENTIFIER_READ);
    	// Location.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.LOCATION.PATH, ProviderStatement.LOCATION.ALL_LOCATIONS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.LOCATION.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.LOCATION.SINGLE_LOCATION);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.LOCATION.PATH_READ, ProviderStatement.LOCATION.ALL_LOCATIONS_READ);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.LOCATION.PATH_READ + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.LOCATION.SINGLE_LOCATION_READ);
    	// Interest Point.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.INTEREST_POINT.PATH, ProviderStatement.INTEREST_POINT.ALL_INTEREST_POINTS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.INTEREST_POINT.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.INTEREST_POINT.SINGLE_INTEREST_POINT);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.INTEREST_POINT.PATH_READ, ProviderStatement.INTEREST_POINT.ALL_INTEREST_POINTS_READ);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.INTEREST_POINT.PATH_READ + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.INTEREST_POINT.SINGLE_INTEREST_POINT_READ);
    	// Information.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.INFORMATION.PATH, ProviderStatement.INFORMATION.ALL_INFORMATIONS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.INFORMATION.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.INFORMATION.SINGLE_INFORMATION);
    	// Message.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.MESSAGE.PATH, ProviderStatement.MESSAGE.ALL_MESSAGES);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.MESSAGE.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.MESSAGE.SINGLE_MESSAGE);
    	// Text Message.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.TEXT_MESSAGE.PATH, ProviderStatement.TEXT_MESSAGE.ALL_TEXT_MESSAGES);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.TEXT_MESSAGE.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.TEXT_MESSAGE.SINGLE_TEXT_MESSAGE);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.TEXT_MESSAGE.PATH_READ, ProviderStatement.TEXT_MESSAGE.ALL_TEXT_MESSAGES_READ);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.TEXT_MESSAGE.PATH_READ + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.TEXT_MESSAGE.SINGLE_TEXT_MESSAGE_READ);
    	// Shared Document.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.SHARED_DOCUMENT.PATH, ProviderStatement.SHARED_DOCUMENT.ALL_SHARED_DOCUMENTS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.SHARED_DOCUMENT.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.SHARED_DOCUMENT.SINGLE_SHARED_DOCUMENT);
    	// Plan.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.PLAN.PATH, ProviderStatement.PLAN.ALL_PLANS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.PLAN.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.PLAN.SINGLE_PLAN);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.PLAN.PATH_READ, ProviderStatement.PLAN.ALL_PLANS_READ);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.PLAN.PATH_READ + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.PLAN.SINGLE_PLAN_READ);
    	// Contact.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.CONTACT.PATH, ProviderStatement.CONTACT.ALL_CONTACTS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.CONTACT.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.CONTACT.SINGLE_CONTACT);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.CONTACT.PATH_READ, ProviderStatement.CONTACT.ALL_CONTACTS_READ);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.CONTACT.PATH_READ + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.CONTACT.SINGLE_CONTACT_READ);
    	// TeamUser.
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.TEAM__USER.PATH, ProviderStatement.TEAM__USER.ALL_TEAM_USERS);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.TEAM__USER.PATH + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.TEAM__USER.SINGLE_TEAM_USER);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.TEAM__USER.PATH_READ, ProviderStatement.TEAM__USER.ALL_TEAM_USERS_READ);
    	uriMatcher.addURI(ProviderStatement.AUTHORITY, ProviderStatement.TEAM__USER.PATH_READ + ProviderStatement.URI_SINGLE_STATEMENT, ProviderStatement.TEAM__USER.SINGLE_TEAM_USER_READ);
    }



	/**
	 * System calls onCreate() when it starts up the provider.
	 * Start up Provider and get the DB.
	 * @return boolean
	 * 			Notify ContentProvider construction.
	 */
	@Override
	public boolean onCreate() {
    	// Initialize Logger
    	initLogger(getContext());
    	// Create DBHelper instance.
		dbHelper = DBHelper.getInstance(getContext());
		return true;
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

	/**
	 * Implement this to insert a new row.
	 * This method adds a new row to the appropriate table, using the values in the ContentValues argument.
	 * Once the row is inserted it notify the URI which is observing the object. 
	 * @param uri
	 *			The content:// URI of the insertion request.
	 * @param values
	 *			A set of column_name/value pairs to add to the database.
	 * @return Uri
	 * 			The URI for the newly inserted item.
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		Uri result = null;
		Uri resultRead = null;
		Uri contentUri = null;
		Uri contentUriRead = null;
		String path = null;
		// Check what Uri was provided and initialize custom properties.
		switch (uriMatcher.match(uri)) {
		// Emergency.
			case ProviderStatement.EMERGENCY.ALL_EMERGENCIES:
		// Service.
			case ProviderStatement.SERVICE.ALL_SERVICES:
		// Mission.
			case ProviderStatement.MISSION.ALL_MISSIONS:
		// Task.
			case ProviderStatement.TASK.ALL_TASKS:
		// Resource.
			case ProviderStatement.RESOURCE.ALL_RESOURCES:					
		// Equipment.
			case ProviderStatement.EQUIPMENT.ALL_EQUIPMENTS:					
		// Entity.
			case ProviderStatement.ENTITY.ALL_ENTITIES:					
		// Organization.
			case ProviderStatement.ORGANIZATION.ALL_ORGANIZATIONS:					
		// Health Care Unit.
			case ProviderStatement.HEALTH_CARE_UNIT.ALL_HEALTH_CARE_UNITS:
		// Affected Organization.
			case ProviderStatement.AFFECTED_ORGANIZATION.ALL_AFFECTED_ORGANIZATIONS:
		// Person.
			case ProviderStatement.PERSON.ALL_PEOPLE:
		// User.
			case ProviderStatement.USER.ALL_USERS:
		// Commander.
			case ProviderStatement.COMMANDER.ALL_COMMANDERS:
		// Responder.
			case ProviderStatement.RESPONDER.ALL_RESPONDERS:
		// Medical.
			case ProviderStatement.MEDICAL.ALL_MEDICALS:
		// Volunteer.
			case ProviderStatement.VOLUNTEER.ALL_VOLUNTEERS:
		// Witness.
			case ProviderStatement.WITNESS.ALL_WITNESSES:
		// Victim.
			case ProviderStatement.VICTIM.ALL_VICTIMS:
		// Team.
			case ProviderStatement.TEAM.ALL_TEAMS:
		// Position.
			case ProviderStatement.POSITION.ALL_POSITIONS:
		// Address.
			case ProviderStatement.ADDRESS.ALL_ADDRESSES:
		// Place Identifier.
			case ProviderStatement.PLACE_IDENTIFIER.ALL_PLACE_IDENTIFIERS:
		// Location.
			case ProviderStatement.LOCATION.ALL_LOCATIONS:
		// Interest Point.
			case ProviderStatement.INTEREST_POINT.ALL_INTEREST_POINTS:
		// Information.
			case ProviderStatement.INFORMATION.ALL_INFORMATIONS:
		// Message.
			case ProviderStatement.MESSAGE.ALL_MESSAGES:
		// Text Message.
			case ProviderStatement.TEXT_MESSAGE.ALL_TEXT_MESSAGES:
		// Shared Document.
			case ProviderStatement.SHARED_DOCUMENT.ALL_SHARED_DOCUMENTS:
		// Plan.
			case ProviderStatement.PLAN.ALL_PLANS:
		// Contact.
			case ProviderStatement.CONTACT.ALL_CONTACTS:
		// TeamUser.
			case ProviderStatement.TEAM__USER.ALL_TEAM_USERS:

				path = getPath(uri);
				contentUri = getContentUri(uri);
				contentUriRead = getContentUriRead(uri);
				break;

			default:
				String errorMsg = ProviderStatement.MESSAGE_UNKNOWN_URI + uri;
	  	    	CustomLogger.getInstance().errorLog(CustomProvider.class.getName(), errorMsg);
				throw new IllegalArgumentException(errorMsg);
		}



		// Get database.
		SQLiteDatabase db = dbHelper.getWritableDatabase();
  		try {
  			// Check properties values.
  			if ( path.isEmpty() || (contentUri == null) ) {
				String errorMsg = ProviderStatement.MESSAGE_UNKNOWN_URI + uri;
	  	    	CustomLogger.getInstance().errorLog(CustomProvider.class.getName(), errorMsg);
				throw new IllegalArgumentException(errorMsg);
  			}
  			if ( (values == null) || (values.size() == 0) ) {
				String errorMsg = ProviderStatement.MESSAGE_NULL_VALUES + uri;
	  	    	CustomLogger.getInstance().errorLog(CustomProvider.class.getName(), errorMsg);
				throw new IllegalArgumentException(errorMsg);
			}
			// Insert a record into database.
  			long newId = db.insertOrThrow(path, null, values);
  			// Verify if result is valid.
  			if (newId > 0) {
				// Notify any watchers of the change.
				result = ContentUris.withAppendedId(contentUri, newId);
				this.notifyUriChange(result);
				if (contentUriRead != null) {
					resultRead = ContentUris.withAppendedId(contentUriRead, newId);
					this.notifyUriChange(resultRead);
				}
			} else {
				String errorMsg = ProviderStatement.MESSAGE_INSERT_FAIL + uri;
	  	    	CustomLogger.getInstance().errorLog(CustomProvider.class.getName(), errorMsg);
				throw new SQLException(errorMsg);
			}
  	    	CustomLogger.getInstance().infoLog(CustomProvider.class.getName(), "Inserted new record into Uri: " + uri.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



	/**
	 * Update a content URI.
	 * This method updates multiple rows based on the selection or a single row if the row id was provided.
	 * The update method returns the number of updated rows.
	 * All rows matching the optionally provided selection will have their columns listed as the keys in the values map with the values of those keys.
	 * Once the row is updated, notifyChange change update to the observer.
	 * @param uri
	 *			The URI to query.
	 *			This can potentially have a record ID if this is an update request for a specific record.
	 * @param values
	 *			A Bundle mapping from column names to new column values (NULL is a valid value).
	 * @param selection
	 *			An optional filter to match rows to update.
	 * @param selectionArgs
	 * 			An optional arguments to match rows to update.
	 * @return int
	 * 			The number of rows affected.
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		int result = 0;
		Uri contentUriRead = null;
		String path = null;
		// Check what Uri was provided and initialize custom properties.
		switch (uriMatcher.match(uri)) {
		// Emergency.
			case ProviderStatement.EMERGENCY.ALL_EMERGENCIES:
			case ProviderStatement.EMERGENCY.SINGLE_EMERGENCY:
		// Service.
			case ProviderStatement.SERVICE.ALL_SERVICES:
			case ProviderStatement.SERVICE.SINGLE_SERVICE:
		// Mission.
			case ProviderStatement.MISSION.ALL_MISSIONS:
			case ProviderStatement.MISSION.SINGLE_MISSION:
		// Task.
			case ProviderStatement.TASK.ALL_TASKS:
			case ProviderStatement.TASK.SINGLE_TASK:
		// Resource.
			case ProviderStatement.RESOURCE.ALL_RESOURCES:
			case ProviderStatement.RESOURCE.SINGLE_RESOURCE:
		// Equipment.
			case ProviderStatement.EQUIPMENT.ALL_EQUIPMENTS:
			case ProviderStatement.EQUIPMENT.SINGLE_EQUIPMENT:
		// Entity.
			case ProviderStatement.ENTITY.ALL_ENTITIES:
			case ProviderStatement.ENTITY.SINGLE_ENTITY:
		// Organization.
			case ProviderStatement.ORGANIZATION.ALL_ORGANIZATIONS:
			case ProviderStatement.ORGANIZATION.SINGLE_ORGANIZATION:
	    // Health Care Unit.
			case ProviderStatement.HEALTH_CARE_UNIT.ALL_HEALTH_CARE_UNITS:
			case ProviderStatement.HEALTH_CARE_UNIT.SINGLE_HEALTH_CARE_UNIT:
	    // Affected Organization.
			case ProviderStatement.AFFECTED_ORGANIZATION.ALL_AFFECTED_ORGANIZATIONS:
			case ProviderStatement.AFFECTED_ORGANIZATION.SINGLE_AFFECTED_ORGANIZATION:
		// Person.
			case ProviderStatement.PERSON.ALL_PEOPLE:
			case ProviderStatement.PERSON.SINGLE_PERSON:
		// User.
			case ProviderStatement.USER.ALL_USERS:
			case ProviderStatement.USER.SINGLE_USER:
		// Commander.
			case ProviderStatement.COMMANDER.ALL_COMMANDERS:
			case ProviderStatement.COMMANDER.SINGLE_COMMANDER:
    	// Responder.
			case ProviderStatement.RESPONDER.ALL_RESPONDERS:
			case ProviderStatement.RESPONDER.SINGLE_RESPONDER:
	    // Medical.
			case ProviderStatement.MEDICAL.ALL_MEDICALS:
			case ProviderStatement.MEDICAL.SINGLE_MEDICAL:
	    // Volunteer.
			case ProviderStatement.VOLUNTEER.ALL_VOLUNTEERS:
			case ProviderStatement.VOLUNTEER.SINGLE_VOLUNTEER:
	    // Witness.
			case ProviderStatement.WITNESS.ALL_WITNESSES:
			case ProviderStatement.WITNESS.SINGLE_WITNESS:
	    // Victim.
			case ProviderStatement.VICTIM.ALL_VICTIMS:
			case ProviderStatement.VICTIM.SINGLE_VICTIM:
	    // Team.
			case ProviderStatement.TEAM.ALL_TEAMS:
			case ProviderStatement.TEAM.SINGLE_TEAM:
		// Position.
			case ProviderStatement.POSITION.ALL_POSITIONS:
			case ProviderStatement.POSITION.SINGLE_POSITION:
		// Address.
			case ProviderStatement.ADDRESS.ALL_ADDRESSES:
			case ProviderStatement.ADDRESS.SINGLE_ADDRESS:
		// Place Identifier.
			case ProviderStatement.PLACE_IDENTIFIER.ALL_PLACE_IDENTIFIERS:
			case ProviderStatement.PLACE_IDENTIFIER.SINGLE_PLACE_IDENTIFIER:
		// Location.
			case ProviderStatement.LOCATION.ALL_LOCATIONS:
			case ProviderStatement.LOCATION.SINGLE_LOCATION:
		// Interest Point.
			case ProviderStatement.INTEREST_POINT.ALL_INTEREST_POINTS:
			case ProviderStatement.INTEREST_POINT.SINGLE_INTEREST_POINT:
		// Information.
			case ProviderStatement.INFORMATION.ALL_INFORMATIONS:
			case ProviderStatement.INFORMATION.SINGLE_INFORMATION:
		// Message.
			case ProviderStatement.MESSAGE.ALL_MESSAGES:
			case ProviderStatement.MESSAGE.SINGLE_MESSAGE:
		// Text Message.
			case ProviderStatement.TEXT_MESSAGE.ALL_TEXT_MESSAGES:
			case ProviderStatement.TEXT_MESSAGE.SINGLE_TEXT_MESSAGE:
		// Shared Document.
			case ProviderStatement.SHARED_DOCUMENT.ALL_SHARED_DOCUMENTS:
			case ProviderStatement.SHARED_DOCUMENT.SINGLE_SHARED_DOCUMENT:
		// Plan.
			case ProviderStatement.PLAN.ALL_PLANS:
			case ProviderStatement.PLAN.SINGLE_PLAN:
		// Contact.
			case ProviderStatement.CONTACT.ALL_CONTACTS:
			case ProviderStatement.CONTACT.SINGLE_CONTACT:
		// TeamUser.
			case ProviderStatement.TEAM__USER.ALL_TEAM_USERS:
			case ProviderStatement.TEAM__USER.SINGLE_TEAM_USER:

				path = getPath(uri);
				contentUriRead = getContentUriRead(uri);
				break;

			default:
				String errorMsg = ProviderStatement.MESSAGE_UNKNOWN_URI + uri;
	  	    	CustomLogger.getInstance().errorLog(CustomProvider.class.getName(), errorMsg);
				throw new IllegalArgumentException(errorMsg);
		}



		// Get database.
  		SQLiteDatabase db = dbHelper.getWritableDatabase();
  		try {
  			// Check properties values.
  			if ( path.isEmpty() ) {
				String errorMsg = ProviderStatement.MESSAGE_UNKNOWN_URI + uri;
	  	    	CustomLogger.getInstance().errorLog(CustomProvider.class.getName(), errorMsg);
				throw new IllegalArgumentException(errorMsg);
  			}
  			if ( (values == null) || (values.size() == 0) ) {
				String errorMsg = ProviderStatement.MESSAGE_NULL_VALUES + uri;
	  	    	CustomLogger.getInstance().errorLog(CustomProvider.class.getName(), errorMsg);
				throw new IllegalArgumentException(errorMsg);
			}
			// Update a record into database.
			result = db.update(path, values, selection, selectionArgs);
  			// Verify if result is valid.
  			if (result > 0) {
				// Notify any watchers of the change.
  				this.notifyUriChange(uri);
				if (contentUriRead != null) {
					this.notifyUriChange(contentUriRead);
				}
			} else {
				String errorMsg = ProviderStatement.MESSAGE_UPDATE_FAIL + uri;
	  	    	CustomLogger.getInstance().errorLog(CustomProvider.class.getName(), errorMsg);
				throw new SQLException(errorMsg);
			}
  	    	CustomLogger.getInstance().infoLog(CustomProvider.class.getName(), "Updated a record into Uri: " + uri.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



	/**
	 * A request to delete one or more rows.
	 * This method deletes rows based on the selection or if an id was provided then it deleted a single row.
	 * The methods returns the numbers of records delete from the database.
	 * The selection clause is applied when performing the deletion, allowing the operation to affect multiple rows in a directory.
	 * Once the row is deleted, it notify the URI which is observing the object.
	 * @param uri
	 *			The full URI to query, including a row ID (if a specific record is requested).
	 * @param selection
	 *			An optional restriction to apply to rows when deleting.
	 * @param selectionArgs
	 * 			An optional arguments to match rows to update.
	 * @return int
	 * 			The number of rows affected.
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int result = -1;
		Uri contentUriRead = null;
		String path = null;
		// Check what Uri provided and initialize custom properties.
		switch (uriMatcher.match(uri)) {
		// Emergency.
			case ProviderStatement.EMERGENCY.ALL_EMERGENCIES:
			case ProviderStatement.EMERGENCY.SINGLE_EMERGENCY:
		// Service.
			case ProviderStatement.SERVICE.ALL_SERVICES:
			case ProviderStatement.SERVICE.SINGLE_SERVICE:
		// Mission.
			case ProviderStatement.MISSION.ALL_MISSIONS:
			case ProviderStatement.MISSION.SINGLE_MISSION:
		// Task.
			case ProviderStatement.TASK.ALL_TASKS:
			case ProviderStatement.TASK.SINGLE_TASK:
		// Resource.
			case ProviderStatement.RESOURCE.ALL_RESOURCES:
			case ProviderStatement.RESOURCE.SINGLE_RESOURCE:
		// Equipment.
			case ProviderStatement.EQUIPMENT.ALL_EQUIPMENTS:
			case ProviderStatement.EQUIPMENT.SINGLE_EQUIPMENT:
		// Entity.
			case ProviderStatement.ENTITY.ALL_ENTITIES:
			case ProviderStatement.ENTITY.SINGLE_ENTITY:
		// Organization.
			case ProviderStatement.ORGANIZATION.ALL_ORGANIZATIONS:
			case ProviderStatement.ORGANIZATION.SINGLE_ORGANIZATION:
	    // Health Care Unit.
			case ProviderStatement.HEALTH_CARE_UNIT.ALL_HEALTH_CARE_UNITS:
			case ProviderStatement.HEALTH_CARE_UNIT.SINGLE_HEALTH_CARE_UNIT:
	    // Affected Organization.
			case ProviderStatement.AFFECTED_ORGANIZATION.ALL_AFFECTED_ORGANIZATIONS:
			case ProviderStatement.AFFECTED_ORGANIZATION.SINGLE_AFFECTED_ORGANIZATION:
		// Person.
			case ProviderStatement.PERSON.ALL_PEOPLE:
			case ProviderStatement.PERSON.SINGLE_PERSON:
		// User.
			case ProviderStatement.USER.ALL_USERS:
			case ProviderStatement.USER.SINGLE_USER:
		// Commander.
			case ProviderStatement.COMMANDER.ALL_COMMANDERS:
			case ProviderStatement.COMMANDER.SINGLE_COMMANDER:
		// Responder.
			case ProviderStatement.RESPONDER.ALL_RESPONDERS:
			case ProviderStatement.RESPONDER.SINGLE_RESPONDER:
	    // Medical.
			case ProviderStatement.MEDICAL.ALL_MEDICALS:
			case ProviderStatement.MEDICAL.SINGLE_MEDICAL:
	    // Volunteer.
			case ProviderStatement.VOLUNTEER.ALL_VOLUNTEERS:
			case ProviderStatement.VOLUNTEER.SINGLE_VOLUNTEER:
	    // Witness.
			case ProviderStatement.WITNESS.ALL_WITNESSES:
			case ProviderStatement.WITNESS.SINGLE_WITNESS:
	    // Victim.
			case ProviderStatement.VICTIM.ALL_VICTIMS:
			case ProviderStatement.VICTIM.SINGLE_VICTIM:
	    // Team.
			case ProviderStatement.TEAM.ALL_TEAMS:
			case ProviderStatement.TEAM.SINGLE_TEAM:
		// Position.
			case ProviderStatement.POSITION.ALL_POSITIONS:
			case ProviderStatement.POSITION.SINGLE_POSITION:
		// Address.
			case ProviderStatement.ADDRESS.ALL_ADDRESSES:
			case ProviderStatement.ADDRESS.SINGLE_ADDRESS:
		// Place Identifier.
			case ProviderStatement.PLACE_IDENTIFIER.ALL_PLACE_IDENTIFIERS:
			case ProviderStatement.PLACE_IDENTIFIER.SINGLE_PLACE_IDENTIFIER:
		// Location.
			case ProviderStatement.LOCATION.ALL_LOCATIONS:
			case ProviderStatement.LOCATION.SINGLE_LOCATION:
		// Interest Point.
			case ProviderStatement.INTEREST_POINT.ALL_INTEREST_POINTS:
			case ProviderStatement.INTEREST_POINT.SINGLE_INTEREST_POINT:
		// Information.
			case ProviderStatement.INFORMATION.ALL_INFORMATIONS:
			case ProviderStatement.INFORMATION.SINGLE_INFORMATION:
		// Message.
			case ProviderStatement.MESSAGE.ALL_MESSAGES:
			case ProviderStatement.MESSAGE.SINGLE_MESSAGE:
		// Text Message.
			case ProviderStatement.TEXT_MESSAGE.ALL_TEXT_MESSAGES:
			case ProviderStatement.TEXT_MESSAGE.SINGLE_TEXT_MESSAGE:
		// Shared Document.
			case ProviderStatement.SHARED_DOCUMENT.ALL_SHARED_DOCUMENTS:
			case ProviderStatement.SHARED_DOCUMENT.SINGLE_SHARED_DOCUMENT:
		// Plan.
			case ProviderStatement.PLAN.ALL_PLANS:
			case ProviderStatement.PLAN.SINGLE_PLAN:
		// Contact.
			case ProviderStatement.CONTACT.ALL_CONTACTS:
			case ProviderStatement.CONTACT.SINGLE_CONTACT:
		// TeamUser.
			case ProviderStatement.TEAM__USER.ALL_TEAM_USERS:
			case ProviderStatement.TEAM__USER.SINGLE_TEAM_USER:

				path = getPath(uri);
				contentUriRead = getContentUriRead(uri);
				break;

			default:
				String errorMsg = ProviderStatement.MESSAGE_UNKNOWN_URI + uri;
	  	    	CustomLogger.getInstance().errorLog(CustomProvider.class.getName(), errorMsg);
				throw new IllegalArgumentException(errorMsg);
		}



		// Get database.
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		try {
  			// Check properties values.
  			if ( path.isEmpty() ) {
				String errorMsg = ProviderStatement.MESSAGE_UNKNOWN_URI + uri;
	  	    	CustomLogger.getInstance().errorLog(CustomProvider.class.getName(), errorMsg);
				throw new IllegalArgumentException(errorMsg);
  			}
			// Delete a record into database.
			result = db.delete(path, selection, selectionArgs);
  			// Verify if result is valid.
  			if (result > 0) {
  				// Notify any watchers of the change.
  				this.notifyUriChange(uri);
				if (contentUriRead != null) {
					this.notifyUriChange(contentUriRead);
				}
			} else {
				String errorMsg = ProviderStatement.MESSAGE_DELETE_FAIL + uri;
	  	    	CustomLogger.getInstance().errorLog(CustomProvider.class.getName(), errorMsg);
				throw new SQLException(errorMsg);
			}
  	    	CustomLogger.getInstance().infoLog(CustomProvider.class.getName(), "Deleted a record into Uri: " + uri.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



	/**
	 * Retrieve data from provider.
	 * Use the arguments to select the table to query, the rows and columns to return, and the sort order of the result.
	 * Return the data as a Cursor object.
	 * @param uri
	 *			The URI to query.
	 * @param projection
	 *			The list of columns to put into the cursor.
	 *			If null all columns are included.
	 * @param selection
	 *			A selection criteria to apply when filtering rows.
	 *			If null then all rows are included.
	 * @param selectionArgs
	 *			We may include ?s in selection, which will be replaced by the values from selectionArgs, in order that they appear in the selection.
	 *			The values will be bound as Strings.
	 * @param sortOrder
	 *			How the rows in the cursor should be sorted. If null then the provider is free to define the sort order.
	 * @return Cursor
	 * 			A Cursor or null.
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		Cursor result = null;
		String path = null;
		String[] defaultProjection = null;
		// Check what Uri provided and initialize custom properties.
		switch (uriMatcher.match(uri)) {
		// Emergency.
			case ProviderStatement.EMERGENCY.ALL_EMERGENCIES_READ:
			case ProviderStatement.EMERGENCY.SINGLE_EMERGENCY_READ:
		// Service.
			case ProviderStatement.SERVICE.ALL_SERVICES:
			case ProviderStatement.SERVICE.SINGLE_SERVICE:
		// Mission.
			case ProviderStatement.MISSION.ALL_MISSIONS_READ:
			case ProviderStatement.MISSION.SINGLE_MISSION_READ:
		// Task.
			case ProviderStatement.TASK.ALL_TASKS_READ:
			case ProviderStatement.TASK.SINGLE_TASK_READ:
		// Resource.
			case ProviderStatement.RESOURCE.ALL_RESOURCES:
			case ProviderStatement.RESOURCE.SINGLE_RESOURCE:
		// Equipment.
			case ProviderStatement.EQUIPMENT.ALL_EQUIPMENTS_READ:
			case ProviderStatement.EQUIPMENT.SINGLE_EQUIPMENT_READ:
		// Entity.
			case ProviderStatement.ENTITY.ALL_ENTITIES:
			case ProviderStatement.ENTITY.SINGLE_ENTITY:
		// Organization.
			case ProviderStatement.ORGANIZATION.ALL_ORGANIZATIONS:
			case ProviderStatement.ORGANIZATION.SINGLE_ORGANIZATION:
		// Health Care Unit.
			case ProviderStatement.HEALTH_CARE_UNIT.ALL_HEALTH_CARE_UNITS_READ:
			case ProviderStatement.HEALTH_CARE_UNIT.SINGLE_HEALTH_CARE_UNIT_READ:
		// Affected Organization.
			case ProviderStatement.AFFECTED_ORGANIZATION.ALL_AFFECTED_ORGANIZATIONS_READ:
			case ProviderStatement.AFFECTED_ORGANIZATION.SINGLE_AFFECTED_ORGANIZATION_READ:
		// Person.
			case ProviderStatement.PERSON.ALL_PEOPLE:
			case ProviderStatement.PERSON.SINGLE_PERSON:
		// User.
			case ProviderStatement.USER.ALL_USERS:
			case ProviderStatement.USER.SINGLE_USER:
		// Commander.
			case ProviderStatement.COMMANDER.ALL_COMMANDERS_READ:
			case ProviderStatement.COMMANDER.SINGLE_COMMANDER_READ:
    	// Responder.
			case ProviderStatement.RESPONDER.ALL_RESPONDERS_READ:
			case ProviderStatement.RESPONDER.SINGLE_RESPONDER_READ:
	    // Medical.
			case ProviderStatement.MEDICAL.ALL_MEDICALS_READ:
			case ProviderStatement.MEDICAL.SINGLE_MEDICAL_READ:
	    // Volunteer.
			case ProviderStatement.VOLUNTEER.ALL_VOLUNTEERS_READ:
			case ProviderStatement.VOLUNTEER.SINGLE_VOLUNTEER_READ:
	    // Witness.
			case ProviderStatement.WITNESS.ALL_WITNESSES_READ:
			case ProviderStatement.WITNESS.SINGLE_WITNESS_READ:
	    // Victim.
			case ProviderStatement.VICTIM.ALL_VICTIMS_READ:
			case ProviderStatement.VICTIM.SINGLE_VICTIM_READ:
	    // Team.
			case ProviderStatement.TEAM.ALL_TEAMS_READ:
			case ProviderStatement.TEAM.SINGLE_TEAM_READ:
		// Position.
			case ProviderStatement.POSITION.ALL_POSITIONS_READ:
			case ProviderStatement.POSITION.SINGLE_POSITION_READ:
		// Address.
			case ProviderStatement.ADDRESS.ALL_ADDRESSES_READ:
			case ProviderStatement.ADDRESS.SINGLE_ADDRESS_READ:
		// Place Identifier.
			case ProviderStatement.PLACE_IDENTIFIER.ALL_PLACE_IDENTIFIERS_READ:
			case ProviderStatement.PLACE_IDENTIFIER.SINGLE_PLACE_IDENTIFIER_READ:
		// Location.
			case ProviderStatement.LOCATION.ALL_LOCATIONS_READ:
			case ProviderStatement.LOCATION.SINGLE_LOCATION_READ:
		// Interest Point.
			case ProviderStatement.INTEREST_POINT.ALL_INTEREST_POINTS_READ:
			case ProviderStatement.INTEREST_POINT.SINGLE_INTEREST_POINT_READ:
		// Information.
			case ProviderStatement.INFORMATION.ALL_INFORMATIONS:
			case ProviderStatement.INFORMATION.SINGLE_INFORMATION:
		// Message.
			case ProviderStatement.MESSAGE.ALL_MESSAGES:
			case ProviderStatement.MESSAGE.SINGLE_MESSAGE:
		// Text Message.
			case ProviderStatement.TEXT_MESSAGE.ALL_TEXT_MESSAGES_READ:
			case ProviderStatement.TEXT_MESSAGE.SINGLE_TEXT_MESSAGE_READ:
		// Shared Document.
			case ProviderStatement.SHARED_DOCUMENT.ALL_SHARED_DOCUMENTS:
			case ProviderStatement.SHARED_DOCUMENT.SINGLE_SHARED_DOCUMENT:
		// Plan.
			case ProviderStatement.PLAN.ALL_PLANS_READ:
			case ProviderStatement.PLAN.SINGLE_PLAN_READ:
		// Contact.
			case ProviderStatement.CONTACT.ALL_CONTACTS_READ:
			case ProviderStatement.CONTACT.SINGLE_CONTACT_READ:
		// TeamUser.
			case ProviderStatement.TEAM__USER.ALL_TEAM_USERS_READ:
			case ProviderStatement.TEAM__USER.SINGLE_TEAM_USER_READ:

				path = getPath(uri);
				defaultProjection = getProjection(uri);
				// Add default sort order if its empty.
				if ( sortOrder == null || TextUtils.isEmpty(sortOrder) ) {
					sortOrder = getSortOrder(uri);
				}
				break;

		// Not exist.
			default:
				String errorMsg = ProviderStatement.MESSAGE_UNKNOWN_URI + uri;
	  	    	CustomLogger.getInstance().errorLog(CustomProvider.class.getName(), errorMsg);
				throw new IllegalArgumentException(errorMsg);
		}



		// Get database.
  		SQLiteDatabase db = dbHelper.getWritableDatabase();
		try {
  			// Check properties values.
  			if ( path.isEmpty() ) {
  				throw new IllegalArgumentException(ProviderStatement.MESSAGE_UNKNOWN_URI + uri);
  			}
  			if (projection != null) {
  	  		    // Check if the caller has requested a column which does not exists.
  	  		    checkProjection(projection, defaultProjection);
			} else {
				projection = defaultProjection;
			}
		    // Use SQLiteQueryBuilder instead of Database query() method.
		    SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		    // Set the table.
		    queryBuilder.setTables(path);
			// Query a record or all records into database.
			// Future work - Extend parameters to include group by, having and limit.
			result = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder, null);
			// Notify the cursor what Uri to watch, so it knows when its source data changes.
			result.setNotificationUri(getContext().getContentResolver(), uri);
  	    	CustomLogger.getInstance().infoLog(CustomProvider.class.getName(), "Queried a record into Uri: " + uri.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



	/**
	 * Return the MIME type of the data at the given URI.
	 * This should start with <code>vnd.android.cursor.item</code> for a single record, or <code>vnd.android.cursor.dir/</code> for multiple items.
	 * This method can be called from multiple threads, as described in Application Fundamentals: Processes and Threads.
	 * @param uri
	 *			The URI to query.
	 * @return String
	 * 			A MIME type string, or null if there is no type.
	 */
	@Override
	public String getType(Uri uri) {
		CustomLogger.getInstance().infoLog(CustomProvider.class.getName(), "Get type of Uri: " + uri.toString());
		// Get the MIME type corresponding to a content URI.
		switch (uriMatcher.match(uri)) {
	    // Emergency.
			case ProviderStatement.EMERGENCY.ALL_EMERGENCIES:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.EMERGENCY.SINGLE_EMERGENCY:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
		// Service.
			case ProviderStatement.SERVICE.ALL_SERVICES:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.SERVICE.SINGLE_SERVICE:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
		// Mission.
			case ProviderStatement.MISSION.ALL_MISSIONS:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.MISSION.SINGLE_MISSION:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
		// Task.
			case ProviderStatement.TASK.ALL_TASKS:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.TASK.SINGLE_TASK:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
		// Resource.
			case ProviderStatement.RESOURCE.ALL_RESOURCES:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.RESOURCE.SINGLE_RESOURCE:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
		// Equipment.
			case ProviderStatement.EQUIPMENT.ALL_EQUIPMENTS:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.EQUIPMENT.SINGLE_EQUIPMENT:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
		// Entity.
			case ProviderStatement.ENTITY.ALL_ENTITIES:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.ENTITY.SINGLE_ENTITY:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
		// Organization.
			case ProviderStatement.ORGANIZATION.ALL_ORGANIZATIONS:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.ORGANIZATION.SINGLE_ORGANIZATION:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
    	// Health Care Unit.
			case ProviderStatement.HEALTH_CARE_UNIT.ALL_HEALTH_CARE_UNITS:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.HEALTH_CARE_UNIT.SINGLE_HEALTH_CARE_UNIT:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
	    // Affected Organization.
			case ProviderStatement.AFFECTED_ORGANIZATION.ALL_AFFECTED_ORGANIZATIONS:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.AFFECTED_ORGANIZATION.SINGLE_AFFECTED_ORGANIZATION:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
		// Person.
			case ProviderStatement.PERSON.ALL_PEOPLE:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.PERSON.SINGLE_PERSON:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
		// User.
			case ProviderStatement.USER.ALL_USERS:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.USER.SINGLE_USER:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
		// Commander.
			case ProviderStatement.COMMANDER.ALL_COMMANDERS:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.COMMANDER.SINGLE_COMMANDER:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
    	// Responder.
			case ProviderStatement.RESPONDER.ALL_RESPONDERS:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.RESPONDER.SINGLE_RESPONDER:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
	    // Medical.
			case ProviderStatement.MEDICAL.ALL_MEDICALS:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.MEDICAL.SINGLE_MEDICAL:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
	    // Volunteer.
			case ProviderStatement.VOLUNTEER.ALL_VOLUNTEERS:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.VOLUNTEER.SINGLE_VOLUNTEER:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
	    // Witness.
			case ProviderStatement.WITNESS.ALL_WITNESSES:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.WITNESS.SINGLE_WITNESS:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
	    // Victim.
			case ProviderStatement.VICTIM.ALL_VICTIMS:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.VICTIM.SINGLE_VICTIM:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
	    // Team.
			case ProviderStatement.TEAM.ALL_TEAMS:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.TEAM.SINGLE_TEAM:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
		// Position.
			case ProviderStatement.POSITION.ALL_POSITIONS:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.POSITION.SINGLE_POSITION:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
		// Address.
			case ProviderStatement.ADDRESS.ALL_ADDRESSES:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.ADDRESS.SINGLE_ADDRESS:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
		// Place Identifier.
			case ProviderStatement.PLACE_IDENTIFIER.ALL_PLACE_IDENTIFIERS:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.PLACE_IDENTIFIER.SINGLE_PLACE_IDENTIFIER:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
		// Location.
			case ProviderStatement.LOCATION.ALL_LOCATIONS:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.LOCATION.SINGLE_LOCATION:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
		// Interest Point.
			case ProviderStatement.INTEREST_POINT.ALL_INTEREST_POINTS:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.INTEREST_POINT.SINGLE_INTEREST_POINT:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
		// Information.
			case ProviderStatement.INFORMATION.ALL_INFORMATIONS:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.INFORMATION.SINGLE_INFORMATION:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
		// Message.
			case ProviderStatement.MESSAGE.ALL_MESSAGES:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.MESSAGE.SINGLE_MESSAGE:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
		// Text Message.
			case ProviderStatement.TEXT_MESSAGE.ALL_TEXT_MESSAGES:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.TEXT_MESSAGE.SINGLE_TEXT_MESSAGE:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
		// Shared Document.
			case ProviderStatement.SHARED_DOCUMENT.ALL_SHARED_DOCUMENTS:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.SHARED_DOCUMENT.SINGLE_SHARED_DOCUMENT:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
		// Plan.
			case ProviderStatement.PLAN.ALL_PLANS:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.PLAN.SINGLE_PLAN:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
		// Contact.
			case ProviderStatement.CONTACT.ALL_CONTACTS:
	    		return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.CONTACT.SINGLE_CONTACT:
	    		return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
	    // TeamUser.
			case ProviderStatement.TEAM__USER.ALL_TEAM_USERS:
				return ProviderStatement.CONTENT_COLLECTION_TYPE_STATEMENT;
			case ProviderStatement.TEAM__USER.SINGLE_TEAM_USER:
				return ProviderStatement.CONTENT_ITEM_TYPE_STATEMENT;
	    		
	    // Not exist.
	    	default:
				String errorMsg = ProviderStatement.MESSAGE_UNKNOWN_URI + uri;
	  	    	CustomLogger.getInstance().errorLog(CustomProvider.class.getName(), errorMsg);
				throw new IllegalArgumentException(errorMsg);
		}
	}



	/**
	 * Notify a ContentProvider update by the URI.
	 * Notify the cursor what Uri to watch, so it knows when its source data changes. 
	 * @param uri
	 */
	private void notifyUriChange(Uri uri) {		
		getContext().getContentResolver().notifyChange(uri, null);
		CustomLogger.getInstance().infoLog(CustomProvider.class.getName(), "Notify Uri change: " + uri.toString());
	}



	/**
	 * Get the path of URI.
	 * @param uri
	 * @return String
	 * 			The respective path of an URI.
	 */
	private String getPath(Uri uri) {
		String result;
		// Check what Uri provided and get the path.
		switch (uriMatcher.match(uri)) {
		// Emergency.
			case ProviderStatement.EMERGENCY.ALL_EMERGENCIES:
			case ProviderStatement.EMERGENCY.SINGLE_EMERGENCY:
				result = ProviderStatement.EMERGENCY.PATH;
				break;
			case ProviderStatement.EMERGENCY.ALL_EMERGENCIES_READ:
			case ProviderStatement.EMERGENCY.SINGLE_EMERGENCY_READ:
				result = ProviderStatement.EMERGENCY.PATH_READ;
				break;
		// Service.
			case ProviderStatement.SERVICE.ALL_SERVICES:
			case ProviderStatement.SERVICE.SINGLE_SERVICE:
				result = ProviderStatement.SERVICE.PATH;
				break;
		// Mission.
			case ProviderStatement.MISSION.ALL_MISSIONS:
			case ProviderStatement.MISSION.SINGLE_MISSION:
				result = ProviderStatement.MISSION.PATH;
				break;
			case ProviderStatement.MISSION.ALL_MISSIONS_READ:
			case ProviderStatement.MISSION.SINGLE_MISSION_READ:
				result = ProviderStatement.MISSION.PATH_READ;
				break;
		// Task.
			case ProviderStatement.TASK.ALL_TASKS:
			case ProviderStatement.TASK.SINGLE_TASK:
				result = ProviderStatement.TASK.PATH;
				break;
			case ProviderStatement.TASK.ALL_TASKS_READ:
			case ProviderStatement.TASK.SINGLE_TASK_READ:
				result = ProviderStatement.TASK.PATH_READ;
				break;
		// Resource.
			case ProviderStatement.RESOURCE.ALL_RESOURCES:
			case ProviderStatement.RESOURCE.SINGLE_RESOURCE:
				result = ProviderStatement.RESOURCE.PATH;
				break;
		// Equipment.
			case ProviderStatement.EQUIPMENT.ALL_EQUIPMENTS:
			case ProviderStatement.EQUIPMENT.SINGLE_EQUIPMENT:
				result = ProviderStatement.EQUIPMENT.PATH;
				break;
			case ProviderStatement.EQUIPMENT.ALL_EQUIPMENTS_READ:
			case ProviderStatement.EQUIPMENT.SINGLE_EQUIPMENT_READ:
				result = ProviderStatement.EQUIPMENT.PATH_READ;
				break;
		// Entity.
			case ProviderStatement.ENTITY.ALL_ENTITIES:
			case ProviderStatement.ENTITY.SINGLE_ENTITY:
				result = ProviderStatement.ENTITY.PATH;
				break;
		// Organization.
			case ProviderStatement.ORGANIZATION.ALL_ORGANIZATIONS:
			case ProviderStatement.ORGANIZATION.SINGLE_ORGANIZATION:
				result = ProviderStatement.ORGANIZATION.PATH;
				break;
    	// Health Care Unit.
			case ProviderStatement.HEALTH_CARE_UNIT.ALL_HEALTH_CARE_UNITS:
			case ProviderStatement.HEALTH_CARE_UNIT.SINGLE_HEALTH_CARE_UNIT:
				result = ProviderStatement.HEALTH_CARE_UNIT.PATH;
				break;
			case ProviderStatement.HEALTH_CARE_UNIT.ALL_HEALTH_CARE_UNITS_READ:
			case ProviderStatement.HEALTH_CARE_UNIT.SINGLE_HEALTH_CARE_UNIT_READ:
				result = ProviderStatement.HEALTH_CARE_UNIT.PATH_READ;
				break;
	    // Affected Organization.
			case ProviderStatement.AFFECTED_ORGANIZATION.ALL_AFFECTED_ORGANIZATIONS:
			case ProviderStatement.AFFECTED_ORGANIZATION.SINGLE_AFFECTED_ORGANIZATION:
				result = ProviderStatement.AFFECTED_ORGANIZATION.PATH;
				break;
			case ProviderStatement.AFFECTED_ORGANIZATION.ALL_AFFECTED_ORGANIZATIONS_READ:
			case ProviderStatement.AFFECTED_ORGANIZATION.SINGLE_AFFECTED_ORGANIZATION_READ:
				result = ProviderStatement.AFFECTED_ORGANIZATION.PATH_READ;
				break;
		// Person.
			case ProviderStatement.PERSON.ALL_PEOPLE:
			case ProviderStatement.PERSON.SINGLE_PERSON:
				result = ProviderStatement.PERSON.PATH;
				break;
		// User.
			case ProviderStatement.USER.ALL_USERS:
			case ProviderStatement.USER.SINGLE_USER:
				result = ProviderStatement.USER.PATH;
				break;
		// Commander.
			case ProviderStatement.COMMANDER.ALL_COMMANDERS:
			case ProviderStatement.COMMANDER.SINGLE_COMMANDER:
				result = ProviderStatement.COMMANDER.PATH;
				break;
			case ProviderStatement.COMMANDER.ALL_COMMANDERS_READ:
			case ProviderStatement.COMMANDER.SINGLE_COMMANDER_READ:
				result = ProviderStatement.COMMANDER.PATH_READ;
				break;
    	// Responder.
			case ProviderStatement.RESPONDER.ALL_RESPONDERS:
			case ProviderStatement.RESPONDER.SINGLE_RESPONDER:
				result = ProviderStatement.RESPONDER.PATH;
				break;
			case ProviderStatement.RESPONDER.ALL_RESPONDERS_READ:
			case ProviderStatement.RESPONDER.SINGLE_RESPONDER_READ:
				result = ProviderStatement.RESPONDER.PATH_READ;
				break;
	    // Medical.
			case ProviderStatement.MEDICAL.ALL_MEDICALS:
			case ProviderStatement.MEDICAL.SINGLE_MEDICAL:
				result = ProviderStatement.MEDICAL.PATH;
				break;
			case ProviderStatement.MEDICAL.ALL_MEDICALS_READ:
			case ProviderStatement.MEDICAL.SINGLE_MEDICAL_READ:
				result = ProviderStatement.MEDICAL.PATH_READ;
				break;
	    // Volunteer.
			case ProviderStatement.VOLUNTEER.ALL_VOLUNTEERS:
			case ProviderStatement.VOLUNTEER.SINGLE_VOLUNTEER:
				result = ProviderStatement.VOLUNTEER.PATH;
				break;
			case ProviderStatement.VOLUNTEER.ALL_VOLUNTEERS_READ:
			case ProviderStatement.VOLUNTEER.SINGLE_VOLUNTEER_READ:
				result = ProviderStatement.VOLUNTEER.PATH_READ;
				break;
	    // Witness.
			case ProviderStatement.WITNESS.ALL_WITNESSES:
			case ProviderStatement.WITNESS.SINGLE_WITNESS:
				result = ProviderStatement.WITNESS.PATH;
				break;
			case ProviderStatement.WITNESS.ALL_WITNESSES_READ:
			case ProviderStatement.WITNESS.SINGLE_WITNESS_READ:
				result = ProviderStatement.WITNESS.PATH_READ;
				break;
	    // Victim.
			case ProviderStatement.VICTIM.ALL_VICTIMS:
			case ProviderStatement.VICTIM.SINGLE_VICTIM:
				result = ProviderStatement.VICTIM.PATH;
				break;
			case ProviderStatement.VICTIM.ALL_VICTIMS_READ:
			case ProviderStatement.VICTIM.SINGLE_VICTIM_READ:
				result = ProviderStatement.VICTIM.PATH_READ;
				break;
	    // Team.
			case ProviderStatement.TEAM.ALL_TEAMS:
			case ProviderStatement.TEAM.SINGLE_TEAM:
				result = ProviderStatement.TEAM.PATH;
				break;
			case ProviderStatement.TEAM.ALL_TEAMS_READ:
			case ProviderStatement.TEAM.SINGLE_TEAM_READ:
				result = ProviderStatement.TEAM.PATH_READ;
				break;
		// Position.
			case ProviderStatement.POSITION.ALL_POSITIONS:
			case ProviderStatement.POSITION.SINGLE_POSITION:
				result = ProviderStatement.POSITION.PATH;
				break;
			case ProviderStatement.POSITION.ALL_POSITIONS_READ:
			case ProviderStatement.POSITION.SINGLE_POSITION_READ:
				result = ProviderStatement.POSITION.PATH_READ;
				break;
		// Address.
			case ProviderStatement.ADDRESS.ALL_ADDRESSES:
			case ProviderStatement.ADDRESS.SINGLE_ADDRESS:
				result = ProviderStatement.ADDRESS.PATH;
				break;
			case ProviderStatement.ADDRESS.ALL_ADDRESSES_READ:
			case ProviderStatement.ADDRESS.SINGLE_ADDRESS_READ:
				result = ProviderStatement.ADDRESS.PATH_READ;
				break;
		// Place Identifier.
			case ProviderStatement.PLACE_IDENTIFIER.ALL_PLACE_IDENTIFIERS:
			case ProviderStatement.PLACE_IDENTIFIER.SINGLE_PLACE_IDENTIFIER:
				result = ProviderStatement.PLACE_IDENTIFIER.PATH;
				break;
			case ProviderStatement.PLACE_IDENTIFIER.ALL_PLACE_IDENTIFIERS_READ:
			case ProviderStatement.PLACE_IDENTIFIER.SINGLE_PLACE_IDENTIFIER_READ:
				result = ProviderStatement.PLACE_IDENTIFIER.PATH_READ;
				break;
		// Location.
			case ProviderStatement.LOCATION.ALL_LOCATIONS:
			case ProviderStatement.LOCATION.SINGLE_LOCATION:
				result = ProviderStatement.LOCATION.PATH;
				break;
			case ProviderStatement.LOCATION.ALL_LOCATIONS_READ:
			case ProviderStatement.LOCATION.SINGLE_LOCATION_READ:
				result = ProviderStatement.LOCATION.PATH_READ;
				break;
		// Interest Point.
			case ProviderStatement.INTEREST_POINT.ALL_INTEREST_POINTS:
			case ProviderStatement.INTEREST_POINT.SINGLE_INTEREST_POINT:
				result = ProviderStatement.INTEREST_POINT.PATH;
				break;
			case ProviderStatement.INTEREST_POINT.ALL_INTEREST_POINTS_READ:
			case ProviderStatement.INTEREST_POINT.SINGLE_INTEREST_POINT_READ:
				result = ProviderStatement.INTEREST_POINT.PATH_READ;
				break;
		// Information.
			case ProviderStatement.INFORMATION.ALL_INFORMATIONS:
			case ProviderStatement.INFORMATION.SINGLE_INFORMATION:
				result = ProviderStatement.INFORMATION.PATH;
				break;
		// Message.
			case ProviderStatement.MESSAGE.ALL_MESSAGES:
			case ProviderStatement.MESSAGE.SINGLE_MESSAGE:
				result = ProviderStatement.MESSAGE.PATH;
				break;
		// Text Message.
			case ProviderStatement.TEXT_MESSAGE.ALL_TEXT_MESSAGES:
			case ProviderStatement.TEXT_MESSAGE.SINGLE_TEXT_MESSAGE:
				result = ProviderStatement.TEXT_MESSAGE.PATH;
				break;
			case ProviderStatement.TEXT_MESSAGE.ALL_TEXT_MESSAGES_READ:
			case ProviderStatement.TEXT_MESSAGE.SINGLE_TEXT_MESSAGE_READ:
				result = ProviderStatement.TEXT_MESSAGE.PATH_READ;
				break;
		// Shared Document.
			case ProviderStatement.SHARED_DOCUMENT.ALL_SHARED_DOCUMENTS:
			case ProviderStatement.SHARED_DOCUMENT.SINGLE_SHARED_DOCUMENT:
				result = ProviderStatement.SHARED_DOCUMENT.PATH;
				break;
		// Plan.
			case ProviderStatement.PLAN.ALL_PLANS:
			case ProviderStatement.PLAN.SINGLE_PLAN:
				result = ProviderStatement.PLAN.PATH;
				break;
			case ProviderStatement.PLAN.ALL_PLANS_READ:
			case ProviderStatement.PLAN.SINGLE_PLAN_READ:
				result = ProviderStatement.PLAN.PATH_READ;
				break;
		// Contact.
			case ProviderStatement.CONTACT.ALL_CONTACTS:
			case ProviderStatement.CONTACT.SINGLE_CONTACT:
				result = ProviderStatement.CONTACT.PATH;
				break;
			case ProviderStatement.CONTACT.ALL_CONTACTS_READ:
			case ProviderStatement.CONTACT.SINGLE_CONTACT_READ:
				result = ProviderStatement.CONTACT.PATH_READ;
				break;
	    // TeamUser.
			case ProviderStatement.TEAM__USER.ALL_TEAM_USERS:
			case ProviderStatement.TEAM__USER.SINGLE_TEAM_USER:
				result = ProviderStatement.TEAM__USER.PATH;
				break;
			case ProviderStatement.TEAM__USER.ALL_TEAM_USERS_READ:
			case ProviderStatement.TEAM__USER.SINGLE_TEAM_USER_READ:
				result = ProviderStatement.TEAM__USER.PATH_READ;
				break;

		// Not exist.
			default:
				String errorMsg = ProviderStatement.MESSAGE_UNKNOWN_URI + uri;
	  	    	CustomLogger.getInstance().errorLog(CustomProvider.class.getName(), errorMsg);
				throw new IllegalArgumentException(errorMsg);
		}
		return result;
	}



	
	/**
	 * Get the Content URI.
	 * @param uri
	 * @return String
	 * 			The respective Content of an URI.
	 */
	private Uri getContentUri(Uri uri) {
		Uri result;
		// Check what Uri provided and get the path.
		switch (uriMatcher.match(uri)) {
		// Emergency.
			case ProviderStatement.EMERGENCY.ALL_EMERGENCIES:
			case ProviderStatement.EMERGENCY.SINGLE_EMERGENCY:
				result = ProviderStatement.EMERGENCY.CONTENT_URI;
				break;
		// Service.
			case ProviderStatement.SERVICE.ALL_SERVICES:
			case ProviderStatement.SERVICE.SINGLE_SERVICE:
				result = ProviderStatement.SERVICE.CONTENT_URI;
				break;
		// Mission.
			case ProviderStatement.MISSION.ALL_MISSIONS:
			case ProviderStatement.MISSION.SINGLE_MISSION:
				result = ProviderStatement.MISSION.CONTENT_URI;
				break;
		// Task.
			case ProviderStatement.TASK.ALL_TASKS:
			case ProviderStatement.TASK.SINGLE_TASK:
				result = ProviderStatement.TASK.CONTENT_URI;
				break;
		// Resource.
			case ProviderStatement.RESOURCE.ALL_RESOURCES:
			case ProviderStatement.RESOURCE.SINGLE_RESOURCE:
				result = ProviderStatement.RESOURCE.CONTENT_URI;
				break;
		// Equipment.
			case ProviderStatement.EQUIPMENT.ALL_EQUIPMENTS:
			case ProviderStatement.EQUIPMENT.SINGLE_EQUIPMENT:
				result = ProviderStatement.EQUIPMENT.CONTENT_URI;
				break;
		// Entity.
			case ProviderStatement.ENTITY.ALL_ENTITIES:
			case ProviderStatement.ENTITY.SINGLE_ENTITY:
				result = ProviderStatement.ENTITY.CONTENT_URI;
				break;
		// Organization.
			case ProviderStatement.ORGANIZATION.ALL_ORGANIZATIONS:
			case ProviderStatement.ORGANIZATION.SINGLE_ORGANIZATION:
				result = ProviderStatement.ORGANIZATION.CONTENT_URI;
				break;
    	// Health Care Unit.
			case ProviderStatement.HEALTH_CARE_UNIT.ALL_HEALTH_CARE_UNITS:
			case ProviderStatement.HEALTH_CARE_UNIT.SINGLE_HEALTH_CARE_UNIT:
				result = ProviderStatement.HEALTH_CARE_UNIT.CONTENT_URI;
				break;
	    // Affected Organization.
			case ProviderStatement.AFFECTED_ORGANIZATION.ALL_AFFECTED_ORGANIZATIONS:
			case ProviderStatement.AFFECTED_ORGANIZATION.SINGLE_AFFECTED_ORGANIZATION:
				result = ProviderStatement.AFFECTED_ORGANIZATION.CONTENT_URI;
				break;
		// Person.
			case ProviderStatement.PERSON.ALL_PEOPLE:
			case ProviderStatement.PERSON.SINGLE_PERSON:
				result = ProviderStatement.PERSON.CONTENT_URI;
				break;
		// User.
			case ProviderStatement.USER.ALL_USERS:
			case ProviderStatement.USER.SINGLE_USER:
				result = ProviderStatement.USER.CONTENT_URI;
				break;
		// Commander.
			case ProviderStatement.COMMANDER.ALL_COMMANDERS:
			case ProviderStatement.COMMANDER.SINGLE_COMMANDER:
				result = ProviderStatement.COMMANDER.CONTENT_URI;
				break;
    	// Responder.
			case ProviderStatement.RESPONDER.ALL_RESPONDERS:
			case ProviderStatement.RESPONDER.SINGLE_RESPONDER:
				result = ProviderStatement.RESPONDER.CONTENT_URI;
				break;
	    // Medical.
			case ProviderStatement.MEDICAL.ALL_MEDICALS:
			case ProviderStatement.MEDICAL.SINGLE_MEDICAL:
				result = ProviderStatement.MEDICAL.CONTENT_URI;
				break;
	    // Volunteer.
			case ProviderStatement.VOLUNTEER.ALL_VOLUNTEERS:
			case ProviderStatement.VOLUNTEER.SINGLE_VOLUNTEER:
				result = ProviderStatement.VOLUNTEER.CONTENT_URI;
				break;
	    // Witness.
			case ProviderStatement.WITNESS.ALL_WITNESSES:
			case ProviderStatement.WITNESS.SINGLE_WITNESS:
				result = ProviderStatement.WITNESS.CONTENT_URI;
				break;
	    // Victim.
			case ProviderStatement.VICTIM.ALL_VICTIMS:
			case ProviderStatement.VICTIM.SINGLE_VICTIM:
				result = ProviderStatement.VICTIM.CONTENT_URI;
				break;
	    // Team.
			case ProviderStatement.TEAM.ALL_TEAMS:
			case ProviderStatement.TEAM.SINGLE_TEAM:
				result = ProviderStatement.TEAM.CONTENT_URI;
				break;
		// Position.
			case ProviderStatement.POSITION.ALL_POSITIONS:
			case ProviderStatement.POSITION.SINGLE_POSITION:
				result = ProviderStatement.POSITION.CONTENT_URI;
				break;
		// Address.
			case ProviderStatement.ADDRESS.ALL_ADDRESSES:
			case ProviderStatement.ADDRESS.SINGLE_ADDRESS:
				result = ProviderStatement.ADDRESS.CONTENT_URI;
				break;
		// Place Identifier.
			case ProviderStatement.PLACE_IDENTIFIER.ALL_PLACE_IDENTIFIERS:
			case ProviderStatement.PLACE_IDENTIFIER.SINGLE_PLACE_IDENTIFIER:
				result = ProviderStatement.PLACE_IDENTIFIER.CONTENT_URI;
				break;
		// Location.
			case ProviderStatement.LOCATION.ALL_LOCATIONS:
			case ProviderStatement.LOCATION.SINGLE_LOCATION:
				result = ProviderStatement.LOCATION.CONTENT_URI;
				break;
		// Interest Point.
			case ProviderStatement.INTEREST_POINT.ALL_INTEREST_POINTS:
			case ProviderStatement.INTEREST_POINT.SINGLE_INTEREST_POINT:
				result = ProviderStatement.INTEREST_POINT.CONTENT_URI;
				break;
		// Information.
			case ProviderStatement.INFORMATION.ALL_INFORMATIONS:
			case ProviderStatement.INFORMATION.SINGLE_INFORMATION:
				result = ProviderStatement.INFORMATION.CONTENT_URI;
				break;
		// Message.
			case ProviderStatement.MESSAGE.ALL_MESSAGES:
			case ProviderStatement.MESSAGE.SINGLE_MESSAGE:
				result = ProviderStatement.MESSAGE.CONTENT_URI;
				break;
		// Text Message.
			case ProviderStatement.TEXT_MESSAGE.ALL_TEXT_MESSAGES:
			case ProviderStatement.TEXT_MESSAGE.SINGLE_TEXT_MESSAGE:
				result = ProviderStatement.TEXT_MESSAGE.CONTENT_URI;
				break;
		// Shared Document.
			case ProviderStatement.SHARED_DOCUMENT.ALL_SHARED_DOCUMENTS:
			case ProviderStatement.SHARED_DOCUMENT.SINGLE_SHARED_DOCUMENT:
				result = ProviderStatement.SHARED_DOCUMENT.CONTENT_URI;
				break;
		// Plan.
			case ProviderStatement.PLAN.ALL_PLANS:
			case ProviderStatement.PLAN.SINGLE_PLAN:
				result = ProviderStatement.PLAN.CONTENT_URI;
				break;
		// Contact.
			case ProviderStatement.CONTACT.ALL_CONTACTS:
			case ProviderStatement.CONTACT.SINGLE_CONTACT:
				result = ProviderStatement.CONTACT.CONTENT_URI;
				break;
	    // TeamUser.
			case ProviderStatement.TEAM__USER.ALL_TEAM_USERS:
			case ProviderStatement.TEAM__USER.SINGLE_TEAM_USER:
				result = ProviderStatement.TEAM__USER.CONTENT_URI;
				break;

		// Not exist.
			default:
				String errorMsg = ProviderStatement.MESSAGE_UNKNOWN_URI + uri;
	  	    	CustomLogger.getInstance().errorLog(CustomProvider.class.getName(), errorMsg);
				throw new IllegalArgumentException(errorMsg);
		}
		return result;
	}



	/**
	 * Get the Content URI READ.
	 * @param uri
	 * @return String
	 * 			The respective Content READ of an URI.
	 */
	private Uri getContentUriRead(Uri uri) {
		Uri result;
		// Check what Uri provided and get the path.
		switch (uriMatcher.match(uri)) {
		// Emergency.
			case ProviderStatement.EMERGENCY.ALL_EMERGENCIES:
			case ProviderStatement.EMERGENCY.SINGLE_EMERGENCY:
			case ProviderStatement.EMERGENCY.ALL_EMERGENCIES_READ:
			case ProviderStatement.EMERGENCY.SINGLE_EMERGENCY_READ:
				result = ProviderStatement.EMERGENCY.CONTENT_URI_READ;
				break;
		// Mission.
			case ProviderStatement.MISSION.ALL_MISSIONS:
			case ProviderStatement.MISSION.SINGLE_MISSION:
			case ProviderStatement.MISSION.ALL_MISSIONS_READ:
			case ProviderStatement.MISSION.SINGLE_MISSION_READ:
				result = ProviderStatement.MISSION.CONTENT_URI_READ;
				break;
		// Task.
			case ProviderStatement.TASK.ALL_TASKS:
			case ProviderStatement.TASK.SINGLE_TASK:
			case ProviderStatement.TASK.ALL_TASKS_READ:
			case ProviderStatement.TASK.SINGLE_TASK_READ:
				result = ProviderStatement.TASK.CONTENT_URI_READ;
				break;
		// Equipment.
			case ProviderStatement.EQUIPMENT.ALL_EQUIPMENTS:
			case ProviderStatement.EQUIPMENT.SINGLE_EQUIPMENT:
			case ProviderStatement.EQUIPMENT.ALL_EQUIPMENTS_READ:
			case ProviderStatement.EQUIPMENT.SINGLE_EQUIPMENT_READ:
				result = ProviderStatement.EQUIPMENT.CONTENT_URI_READ;
				break;
    	// Health Care Unit.
			case ProviderStatement.HEALTH_CARE_UNIT.ALL_HEALTH_CARE_UNITS:
			case ProviderStatement.HEALTH_CARE_UNIT.SINGLE_HEALTH_CARE_UNIT:
			case ProviderStatement.HEALTH_CARE_UNIT.ALL_HEALTH_CARE_UNITS_READ:
			case ProviderStatement.HEALTH_CARE_UNIT.SINGLE_HEALTH_CARE_UNIT_READ:
				result = ProviderStatement.HEALTH_CARE_UNIT.CONTENT_URI_READ;
				break;
	    // Affected Organization.
			case ProviderStatement.AFFECTED_ORGANIZATION.ALL_AFFECTED_ORGANIZATIONS:
			case ProviderStatement.AFFECTED_ORGANIZATION.SINGLE_AFFECTED_ORGANIZATION:
			case ProviderStatement.AFFECTED_ORGANIZATION.ALL_AFFECTED_ORGANIZATIONS_READ:
			case ProviderStatement.AFFECTED_ORGANIZATION.SINGLE_AFFECTED_ORGANIZATION_READ:
				result = ProviderStatement.AFFECTED_ORGANIZATION.CONTENT_URI_READ;
				break;
		// Commander.
			case ProviderStatement.COMMANDER.ALL_COMMANDERS:
			case ProviderStatement.COMMANDER.SINGLE_COMMANDER:
			case ProviderStatement.COMMANDER.ALL_COMMANDERS_READ:
			case ProviderStatement.COMMANDER.SINGLE_COMMANDER_READ:
				result = ProviderStatement.COMMANDER.CONTENT_URI_READ;
				break;
    	// Responder.
			case ProviderStatement.RESPONDER.ALL_RESPONDERS:
			case ProviderStatement.RESPONDER.SINGLE_RESPONDER:
			case ProviderStatement.RESPONDER.ALL_RESPONDERS_READ:
			case ProviderStatement.RESPONDER.SINGLE_RESPONDER_READ:
				result = ProviderStatement.RESPONDER.CONTENT_URI_READ;
				break;
	    // Medical.
			case ProviderStatement.MEDICAL.ALL_MEDICALS:
			case ProviderStatement.MEDICAL.SINGLE_MEDICAL:
			case ProviderStatement.MEDICAL.ALL_MEDICALS_READ:
			case ProviderStatement.MEDICAL.SINGLE_MEDICAL_READ:
				result = ProviderStatement.MEDICAL.CONTENT_URI_READ;
				break;
	    // Volunteer.
			case ProviderStatement.VOLUNTEER.ALL_VOLUNTEERS:
			case ProviderStatement.VOLUNTEER.SINGLE_VOLUNTEER:
			case ProviderStatement.VOLUNTEER.ALL_VOLUNTEERS_READ:
			case ProviderStatement.VOLUNTEER.SINGLE_VOLUNTEER_READ:
				result = ProviderStatement.VOLUNTEER.CONTENT_URI_READ;
				break;
	    // Witness.
			case ProviderStatement.WITNESS.ALL_WITNESSES:
			case ProviderStatement.WITNESS.SINGLE_WITNESS:
			case ProviderStatement.WITNESS.ALL_WITNESSES_READ:
			case ProviderStatement.WITNESS.SINGLE_WITNESS_READ:
				result = ProviderStatement.WITNESS.CONTENT_URI_READ;
				break;
	    // Victim.
			case ProviderStatement.VICTIM.ALL_VICTIMS:
			case ProviderStatement.VICTIM.SINGLE_VICTIM:
			case ProviderStatement.VICTIM.ALL_VICTIMS_READ:
			case ProviderStatement.VICTIM.SINGLE_VICTIM_READ:
				result = ProviderStatement.VICTIM.CONTENT_URI_READ;
				break;
	    // Team.
			case ProviderStatement.TEAM.ALL_TEAMS:
			case ProviderStatement.TEAM.SINGLE_TEAM:
			case ProviderStatement.TEAM.ALL_TEAMS_READ:
			case ProviderStatement.TEAM.SINGLE_TEAM_READ:
				result = ProviderStatement.TEAM.CONTENT_URI_READ;
				break;
		// Position.
			case ProviderStatement.POSITION.ALL_POSITIONS:
			case ProviderStatement.POSITION.SINGLE_POSITION:				
			case ProviderStatement.POSITION.ALL_POSITIONS_READ:
			case ProviderStatement.POSITION.SINGLE_POSITION_READ:
				result = ProviderStatement.POSITION.CONTENT_URI_READ;
				break;
		// Address.
			case ProviderStatement.ADDRESS.ALL_ADDRESSES:
			case ProviderStatement.ADDRESS.SINGLE_ADDRESS:
			case ProviderStatement.ADDRESS.ALL_ADDRESSES_READ:
			case ProviderStatement.ADDRESS.SINGLE_ADDRESS_READ:
				result = ProviderStatement.ADDRESS.CONTENT_URI_READ;
				break;
		// Place Identifier.
			case ProviderStatement.PLACE_IDENTIFIER.ALL_PLACE_IDENTIFIERS:
			case ProviderStatement.PLACE_IDENTIFIER.SINGLE_PLACE_IDENTIFIER:
			case ProviderStatement.PLACE_IDENTIFIER.ALL_PLACE_IDENTIFIERS_READ:
			case ProviderStatement.PLACE_IDENTIFIER.SINGLE_PLACE_IDENTIFIER_READ:
				result = ProviderStatement.PLACE_IDENTIFIER.CONTENT_URI_READ;
				break;
		// Location.
			case ProviderStatement.LOCATION.ALL_LOCATIONS:
			case ProviderStatement.LOCATION.SINGLE_LOCATION:
			case ProviderStatement.LOCATION.ALL_LOCATIONS_READ:
			case ProviderStatement.LOCATION.SINGLE_LOCATION_READ:
				result = ProviderStatement.LOCATION.CONTENT_URI_READ;
				break;
		// Interest Point.
			case ProviderStatement.INTEREST_POINT.ALL_INTEREST_POINTS:
			case ProviderStatement.INTEREST_POINT.SINGLE_INTEREST_POINT:
			case ProviderStatement.INTEREST_POINT.ALL_INTEREST_POINTS_READ:
			case ProviderStatement.INTEREST_POINT.SINGLE_INTEREST_POINT_READ:
				result = ProviderStatement.INTEREST_POINT.CONTENT_URI_READ;
				break;
		// Text Message.
			case ProviderStatement.TEXT_MESSAGE.ALL_TEXT_MESSAGES:
			case ProviderStatement.TEXT_MESSAGE.SINGLE_TEXT_MESSAGE:
			case ProviderStatement.TEXT_MESSAGE.ALL_TEXT_MESSAGES_READ:
			case ProviderStatement.TEXT_MESSAGE.SINGLE_TEXT_MESSAGE_READ:
				result = ProviderStatement.TEXT_MESSAGE.CONTENT_URI_READ;
				break;
		// Plan.
			case ProviderStatement.PLAN.ALL_PLANS:
			case ProviderStatement.PLAN.SINGLE_PLAN:
			case ProviderStatement.PLAN.ALL_PLANS_READ:
			case ProviderStatement.PLAN.SINGLE_PLAN_READ:
				result = ProviderStatement.PLAN.CONTENT_URI_READ;
				break;
		// Contact.
			case ProviderStatement.CONTACT.ALL_CONTACTS:
			case ProviderStatement.CONTACT.SINGLE_CONTACT:
			case ProviderStatement.CONTACT.ALL_CONTACTS_READ:
			case ProviderStatement.CONTACT.SINGLE_CONTACT_READ:
				result = ProviderStatement.CONTACT.CONTENT_URI_READ;
				break;
	    // TeamUser.
			case ProviderStatement.TEAM__USER.ALL_TEAM_USERS:
			case ProviderStatement.TEAM__USER.SINGLE_TEAM_USER:
			case ProviderStatement.TEAM__USER.ALL_TEAM_USERS_READ:
			case ProviderStatement.TEAM__USER.SINGLE_TEAM_USER_READ:
				result = ProviderStatement.TEAM__USER.CONTENT_URI_READ;
				break;

		// Not exist.
			default:
				// Do not show error because Read (VIEW) option is not for all Providers.
				result = null;
		}
		return result;
	}



	/**
	 * Get the projection of URI.
	 * @param uri
	 * @return String Array
	 * 			The respective projection of an URI.
	 */
	private String[] getProjection(Uri uri) {
		String[] result;
		// Check what Uri was provided and get the path.
		switch (uriMatcher.match(uri)) {
		// Emergency.
			case ProviderStatement.EMERGENCY.ALL_EMERGENCIES_READ:
			case ProviderStatement.EMERGENCY.SINGLE_EMERGENCY_READ:
				result = ProviderStatement.EMERGENCY.PROJECTION_ALL;
				break;
		// Service.
			case ProviderStatement.SERVICE.ALL_SERVICES:
			case ProviderStatement.SERVICE.SINGLE_SERVICE:
				result = ProviderStatement.SERVICE.PROJECTION_ALL;
				break;
		// Mission.
			case ProviderStatement.MISSION.ALL_MISSIONS_READ:
			case ProviderStatement.MISSION.SINGLE_MISSION_READ:
				result = ProviderStatement.MISSION.PROJECTION_ALL;
				break;
		// Task.
			case ProviderStatement.TASK.ALL_TASKS_READ:
			case ProviderStatement.TASK.SINGLE_TASK_READ:
				result = ProviderStatement.TASK.PROJECTION_ALL;
				break;
		// Resource.
			case ProviderStatement.RESOURCE.ALL_RESOURCES:
			case ProviderStatement.RESOURCE.SINGLE_RESOURCE:
				result = ProviderStatement.RESOURCE.PROJECTION_ALL;
				break;
		// Equipment.
			case ProviderStatement.EQUIPMENT.ALL_EQUIPMENTS_READ:
			case ProviderStatement.EQUIPMENT.SINGLE_EQUIPMENT_READ:
				result = ProviderStatement.EQUIPMENT.PROJECTION_ALL;
				break;
		// Entity.
			case ProviderStatement.ENTITY.ALL_ENTITIES:
			case ProviderStatement.ENTITY.SINGLE_ENTITY:
				result = ProviderStatement.ENTITY.PROJECTION_ALL;
				break;
		// Organization.
			case ProviderStatement.ORGANIZATION.ALL_ORGANIZATIONS:
			case ProviderStatement.ORGANIZATION.SINGLE_ORGANIZATION:
				result = ProviderStatement.ORGANIZATION.PROJECTION_ALL;
				break;
    	// Health Care Unit.
			case ProviderStatement.HEALTH_CARE_UNIT.ALL_HEALTH_CARE_UNITS_READ:
			case ProviderStatement.HEALTH_CARE_UNIT.SINGLE_HEALTH_CARE_UNIT_READ:
				result = ProviderStatement.HEALTH_CARE_UNIT.PROJECTION_ALL;
				break;
	    // Affected Organization.
			case ProviderStatement.AFFECTED_ORGANIZATION.ALL_AFFECTED_ORGANIZATIONS_READ:
			case ProviderStatement.AFFECTED_ORGANIZATION.SINGLE_AFFECTED_ORGANIZATION_READ:
				result = ProviderStatement.AFFECTED_ORGANIZATION.PROJECTION_ALL;
				break;
		// Person.
			case ProviderStatement.PERSON.ALL_PEOPLE:
			case ProviderStatement.PERSON.SINGLE_PERSON:
				result = ProviderStatement.PERSON.PROJECTION_ALL;
				break;
		// User.
			case ProviderStatement.USER.ALL_USERS:
			case ProviderStatement.USER.SINGLE_USER:
				result = ProviderStatement.USER.PROJECTION_ALL;
				break;
		// Commander.
			case ProviderStatement.COMMANDER.ALL_COMMANDERS_READ:
			case ProviderStatement.COMMANDER.SINGLE_COMMANDER_READ:
				result = ProviderStatement.COMMANDER.PROJECTION_ALL;
				break;
    	// Responder.
			case ProviderStatement.RESPONDER.ALL_RESPONDERS_READ:
			case ProviderStatement.RESPONDER.SINGLE_RESPONDER_READ:
				result = ProviderStatement.RESPONDER.PROJECTION_ALL;
				break;
	    // Medical.
			case ProviderStatement.MEDICAL.ALL_MEDICALS_READ:
			case ProviderStatement.MEDICAL.SINGLE_MEDICAL_READ:
				result = ProviderStatement.MEDICAL.PROJECTION_ALL;
				break;
	    // Volunteer.
			case ProviderStatement.VOLUNTEER.ALL_VOLUNTEERS_READ:
			case ProviderStatement.VOLUNTEER.SINGLE_VOLUNTEER_READ:
				result = ProviderStatement.VOLUNTEER.PROJECTION_ALL;
				break;
	    // Witness.
			case ProviderStatement.WITNESS.ALL_WITNESSES_READ:
			case ProviderStatement.WITNESS.SINGLE_WITNESS_READ:
				result = ProviderStatement.WITNESS.PROJECTION_ALL;
				break;
	    // Victim.
			case ProviderStatement.VICTIM.ALL_VICTIMS_READ:
			case ProviderStatement.VICTIM.SINGLE_VICTIM_READ:
				result = ProviderStatement.VICTIM.PROJECTION_ALL;
				break;
	    // Team.
			case ProviderStatement.TEAM.ALL_TEAMS_READ:
			case ProviderStatement.TEAM.SINGLE_TEAM_READ:
				result = ProviderStatement.TEAM.PROJECTION_ALL;
				break;
		// Position.
			case ProviderStatement.POSITION.ALL_POSITIONS_READ:
			case ProviderStatement.POSITION.SINGLE_POSITION_READ:
				result = ProviderStatement.POSITION.PROJECTION_ALL;
				break;
		// Address.
			case ProviderStatement.ADDRESS.ALL_ADDRESSES_READ:
			case ProviderStatement.ADDRESS.SINGLE_ADDRESS_READ:
				result = ProviderStatement.ADDRESS.PROJECTION_ALL;
				break;
		// Place Identifier.
			case ProviderStatement.PLACE_IDENTIFIER.ALL_PLACE_IDENTIFIERS_READ:
			case ProviderStatement.PLACE_IDENTIFIER.SINGLE_PLACE_IDENTIFIER_READ:
				result = ProviderStatement.PLACE_IDENTIFIER.PROJECTION_ALL;
				break;
		// Location.
			case ProviderStatement.LOCATION.ALL_LOCATIONS_READ:
			case ProviderStatement.LOCATION.SINGLE_LOCATION_READ:
				result = ProviderStatement.LOCATION.PROJECTION_ALL;
				break;
		// Interest Point.
			case ProviderStatement.INTEREST_POINT.ALL_INTEREST_POINTS_READ:
			case ProviderStatement.INTEREST_POINT.SINGLE_INTEREST_POINT_READ:
				result = ProviderStatement.INTEREST_POINT.PROJECTION_ALL;
				break;
		// Information.
			case ProviderStatement.INFORMATION.ALL_INFORMATIONS:
			case ProviderStatement.INFORMATION.SINGLE_INFORMATION:
				result = ProviderStatement.INFORMATION.PROJECTION_ALL;
				break;
		// Message.
			case ProviderStatement.MESSAGE.ALL_MESSAGES:
			case ProviderStatement.MESSAGE.SINGLE_MESSAGE:
				result = ProviderStatement.MESSAGE.PROJECTION_ALL;
				break;
		// Text Message.
			case ProviderStatement.TEXT_MESSAGE.ALL_TEXT_MESSAGES_READ:
			case ProviderStatement.TEXT_MESSAGE.SINGLE_TEXT_MESSAGE_READ:
				result = ProviderStatement.TEXT_MESSAGE.PROJECTION_ALL;
				break;
		// Shared Document.
			case ProviderStatement.SHARED_DOCUMENT.ALL_SHARED_DOCUMENTS:
			case ProviderStatement.SHARED_DOCUMENT.SINGLE_SHARED_DOCUMENT:
				result = ProviderStatement.SHARED_DOCUMENT.PROJECTION_ALL;
				break;
		// Plan.
			case ProviderStatement.PLAN.ALL_PLANS_READ:
			case ProviderStatement.PLAN.SINGLE_PLAN_READ:
				result = ProviderStatement.PLAN.PROJECTION_ALL;
				break;
		// Contact.
			case ProviderStatement.CONTACT.ALL_CONTACTS_READ:
			case ProviderStatement.CONTACT.SINGLE_CONTACT_READ:
				result = ProviderStatement.CONTACT.PROJECTION_ALL;
				break;
	    // TeamUser.
			case ProviderStatement.TEAM__USER.ALL_TEAM_USERS_READ:
			case ProviderStatement.TEAM__USER.SINGLE_TEAM_USER_READ:
				result = ProviderStatement.TEAM__USER.PROJECTION_ALL;
				break;

		// Not exist.
			default:
				String errorMsg = ProviderStatement.MESSAGE_UNKNOWN_URI + uri;
	  	    	CustomLogger.getInstance().errorLog(CustomProvider.class.getName(), errorMsg);
				throw new IllegalArgumentException(errorMsg);
		}
		return result;
	}



	/**
	 * Get the sort order of URI.
	 * @param uri
	 * @return String
	 * 			The respective sort order of an URI.
	 */
	private String getSortOrder(Uri uri) {
		String result;
		// Check what Uri was provided and get the path.
		switch (uriMatcher.match(uri)) {
		// Emergency.
			case ProviderStatement.EMERGENCY.ALL_EMERGENCIES_READ:
			case ProviderStatement.EMERGENCY.SINGLE_EMERGENCY_READ:
				result = ProviderStatement.EMERGENCY.SORT_ORDER;
				break;
		// Service.
			case ProviderStatement.SERVICE.ALL_SERVICES:
			case ProviderStatement.SERVICE.SINGLE_SERVICE:
				result = ProviderStatement.SERVICE.SORT_ORDER;
				break;
		// Mission.
			case ProviderStatement.MISSION.ALL_MISSIONS_READ:
			case ProviderStatement.MISSION.SINGLE_MISSION_READ:
				result = ProviderStatement.MISSION.SORT_ORDER;
				break;
		// Task.
			case ProviderStatement.TASK.ALL_TASKS_READ:
			case ProviderStatement.TASK.SINGLE_TASK_READ:
				result = ProviderStatement.TASK.SORT_ORDER;
				break;
		// Resource.
			case ProviderStatement.RESOURCE.ALL_RESOURCES:
			case ProviderStatement.RESOURCE.SINGLE_RESOURCE:
				result = ProviderStatement.RESOURCE.SORT_ORDER;
				break;
		// Equipment.
			case ProviderStatement.EQUIPMENT.ALL_EQUIPMENTS_READ:
			case ProviderStatement.EQUIPMENT.SINGLE_EQUIPMENT_READ:
				result = ProviderStatement.EQUIPMENT.SORT_ORDER;
				break;
		// Entity.
			case ProviderStatement.ENTITY.ALL_ENTITIES:
			case ProviderStatement.ENTITY.SINGLE_ENTITY:
				result = ProviderStatement.ENTITY.SORT_ORDER;
				break;
		// Organization.
			case ProviderStatement.ORGANIZATION.ALL_ORGANIZATIONS:
			case ProviderStatement.ORGANIZATION.SINGLE_ORGANIZATION:
				result = ProviderStatement.ORGANIZATION.SORT_ORDER;
				break;
    	// Health Care Unit.
			case ProviderStatement.HEALTH_CARE_UNIT.ALL_HEALTH_CARE_UNITS_READ:
			case ProviderStatement.HEALTH_CARE_UNIT.SINGLE_HEALTH_CARE_UNIT_READ:
				result = ProviderStatement.HEALTH_CARE_UNIT.SORT_ORDER;
				break;
	    // Affected Organization.
			case ProviderStatement.AFFECTED_ORGANIZATION.ALL_AFFECTED_ORGANIZATIONS_READ:
			case ProviderStatement.AFFECTED_ORGANIZATION.SINGLE_AFFECTED_ORGANIZATION_READ:
				result = ProviderStatement.AFFECTED_ORGANIZATION.SORT_ORDER;
				break;
		// Person.
			case ProviderStatement.PERSON.ALL_PEOPLE:
			case ProviderStatement.PERSON.SINGLE_PERSON:
				result = ProviderStatement.PERSON.SORT_ORDER;
				break;
		// User.
			case ProviderStatement.USER.ALL_USERS:
			case ProviderStatement.USER.SINGLE_USER:
				result = ProviderStatement.USER.SORT_ORDER;
				break;
		// Commander.
			case ProviderStatement.COMMANDER.ALL_COMMANDERS_READ:
			case ProviderStatement.COMMANDER.SINGLE_COMMANDER_READ:
				result = ProviderStatement.COMMANDER.SORT_ORDER;
				break;
    	// Responder.
			case ProviderStatement.RESPONDER.ALL_RESPONDERS_READ:
			case ProviderStatement.RESPONDER.SINGLE_RESPONDER_READ:
				result = ProviderStatement.RESPONDER.SORT_ORDER;
				break;
	    // Medical.
			case ProviderStatement.MEDICAL.ALL_MEDICALS_READ:
			case ProviderStatement.MEDICAL.SINGLE_MEDICAL_READ:
				result = ProviderStatement.MEDICAL.SORT_ORDER;
				break;
	    // Volunteer.
			case ProviderStatement.VOLUNTEER.ALL_VOLUNTEERS_READ:
			case ProviderStatement.VOLUNTEER.SINGLE_VOLUNTEER_READ:
				result = ProviderStatement.VOLUNTEER.SORT_ORDER;
				break;
	    // Witness.
			case ProviderStatement.WITNESS.ALL_WITNESSES_READ:
			case ProviderStatement.WITNESS.SINGLE_WITNESS_READ:
				result = ProviderStatement.WITNESS.SORT_ORDER;
				break;
	    // Victim.
			case ProviderStatement.VICTIM.ALL_VICTIMS_READ:
			case ProviderStatement.VICTIM.SINGLE_VICTIM_READ:
				result = ProviderStatement.VICTIM.SORT_ORDER;
				break;
	    // Team.
			case ProviderStatement.TEAM.ALL_TEAMS_READ:
			case ProviderStatement.TEAM.SINGLE_TEAM_READ:
				result = ProviderStatement.TEAM.SORT_ORDER;
				break;
		// Position.
			case ProviderStatement.POSITION.ALL_POSITIONS_READ:
			case ProviderStatement.POSITION.SINGLE_POSITION_READ:
				result = ProviderStatement.POSITION.SORT_ORDER;
				break;
		// Address.
			case ProviderStatement.ADDRESS.ALL_ADDRESSES_READ:
			case ProviderStatement.ADDRESS.SINGLE_ADDRESS_READ:
				result = ProviderStatement.ADDRESS.SORT_ORDER;
				break;
		// Place Identifier.
			case ProviderStatement.PLACE_IDENTIFIER.ALL_PLACE_IDENTIFIERS_READ:
			case ProviderStatement.PLACE_IDENTIFIER.SINGLE_PLACE_IDENTIFIER_READ:
				result = ProviderStatement.PLACE_IDENTIFIER.SORT_ORDER;
				break;
		// Location.
			case ProviderStatement.LOCATION.ALL_LOCATIONS_READ:
			case ProviderStatement.LOCATION.SINGLE_LOCATION_READ:
				result = ProviderStatement.LOCATION.SORT_ORDER;
				break;
		// Interest Point.
			case ProviderStatement.INTEREST_POINT.ALL_INTEREST_POINTS_READ:
			case ProviderStatement.INTEREST_POINT.SINGLE_INTEREST_POINT_READ:
				result = ProviderStatement.INTEREST_POINT.SORT_ORDER;
				break;
		// Information.
			case ProviderStatement.INFORMATION.ALL_INFORMATIONS:
			case ProviderStatement.INFORMATION.SINGLE_INFORMATION:
				result = ProviderStatement.INFORMATION.SORT_ORDER;
				break;
		// Message.
			case ProviderStatement.MESSAGE.ALL_MESSAGES:
			case ProviderStatement.MESSAGE.SINGLE_MESSAGE:
				result = ProviderStatement.MESSAGE.SORT_ORDER;
				break;
		// Text Message.
			case ProviderStatement.TEXT_MESSAGE.ALL_TEXT_MESSAGES_READ:
			case ProviderStatement.TEXT_MESSAGE.SINGLE_TEXT_MESSAGE_READ:
				result = ProviderStatement.TEXT_MESSAGE.SORT_ORDER;
				break;
		// Shared Document.
			case ProviderStatement.SHARED_DOCUMENT.ALL_SHARED_DOCUMENTS:
			case ProviderStatement.SHARED_DOCUMENT.SINGLE_SHARED_DOCUMENT:
				result = ProviderStatement.SHARED_DOCUMENT.SORT_ORDER;
				break;
		// Plan.
			case ProviderStatement.PLAN.ALL_PLANS_READ:
			case ProviderStatement.PLAN.SINGLE_PLAN_READ:
				result = ProviderStatement.PLAN.SORT_ORDER;
				break;
		// Contact.
			case ProviderStatement.CONTACT.ALL_CONTACTS_READ:
			case ProviderStatement.CONTACT.SINGLE_CONTACT_READ:
				result = ProviderStatement.CONTACT.SORT_ORDER;
				break;
	    // TeamUser.
			case ProviderStatement.TEAM__USER.ALL_TEAM_USERS_READ:
			case ProviderStatement.TEAM__USER.SINGLE_TEAM_USER_READ:
				result = ProviderStatement.TEAM__USER.SORT_ORDER;
				break;

		// Not exist.
			default:
				String errorMsg = ProviderStatement.MESSAGE_UNKNOWN_URI + uri;
	  	    	CustomLogger.getInstance().errorLog(CustomProvider.class.getName(), errorMsg);
				throw new IllegalArgumentException(errorMsg);
		}
		return result;
	}



	/**
	 * Check if the caller has requested a column which does not exists.
	 * @param projection
	 * @param defaultProjection
	 */
	private void checkProjection(String[] projection, String[] defaultProjection) {
		try {
			if ( (projection != null) && (defaultProjection != null) ) {
				HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
				HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(defaultProjection));
				// Check if all columns which are requested are available.
				if (!availableColumns.containsAll(requestedColumns)) {
					String errorMsg = ProviderStatement.MESSAGE_UNKNOWN_PROJECTION + projection.toString();
		  	    	CustomLogger.getInstance().errorLog(CustomProvider.class.getName(), errorMsg);
					throw new IllegalArgumentException(errorMsg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		CustomLogger.getInstance().infoLog(CustomProvider.class.getName(), "Projection checked: " + projection.toString());
	}

}
