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
package br.ufrj.ppgi.jemf.mobile.manager;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import br.ufrj.ppgi.jemf.mobile.OperationHolder.EnumOperationType;
import br.ufrj.ppgi.jemf.mobile.bean.Volunteer;
import br.ufrj.ppgi.jemf.mobile.database.DBStatement;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderParams;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderStatement;
import br.ufrj.ppgi.jemf.utils.Constraint;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class VolunteerManager extends Manager {

	/**
	 * Constructor.
	 */
	public VolunteerManager() {
    	CustomLogger.getInstance().infoLog(VolunteerManager.class.getName(), "Class started.");
	}



	/**
	 * Get the new ID from Uri after Insert operation.
	 * Set the new ID to the Bean Object.
	 * @param newUri
	 * @return Object
	 * 			Returns saved Bean Object.
	 */
	@Override
	protected Object getNewIDFromUri(Uri newUri) {
		Object result = null;
		// Check if Uri is valid.
		if (newUri != null) {
			try {
				// Get current Bean Object.
				result = getBean();
				// Check if Bean Object is valid.
				if (result != null) {
					// Set the new ID to the Bean Object.
					((Volunteer) result).setId(Integer.valueOf(newUri.getLastPathSegment()));
			    	CustomLogger.getInstance().infoLog(VolunteerManager.class.getName(), "New Volunteer Object ID retrieved.");
				} else {
					// Failed to get the Bean Object.
					String errorMsg = "Failed to set the new ID to the Bean Object.";
			    	CustomLogger.getInstance().errorLog(VolunteerManager.class.getName(), errorMsg);
					throw new IllegalAccessError(errorMsg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Set the new Parent ID to the Detail Record ContentValues.
	 * @param parentValues
	 * @return ContentValues
	 * 			Returns updated ContentValues Object.
	 */
	@Override
	protected ContentValues setParentIDToDetail(ContentValues parentValues) {
		ContentValues result = null;
		// Check if ContentValues is valid.
		if (parentValues != null) {
			try {
				result = parentValues;
				// Check if Bean Object is valid.
				if (getBean() != null) {
					// Check if ContentValues Object have ID column.
					if (result.containsKey(BaseColumns._ID)) {
						// Get current Bean Object ID.
						result.put(BaseColumns._ID, ((Volunteer) getBean()).getId());
				    	CustomLogger.getInstance().infoLog(VolunteerManager.class.getName(), "Detail Record ID updated.");
					} else {
						// Failed to update the Detail content values.
						String errorMsg = "Failed to update the Detail content values.";
				    	CustomLogger.getInstance().errorLog(VolunteerManager.class.getName(), errorMsg);
						throw new IllegalAccessError(errorMsg);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Load a single record from database to a Object.
	 * @param cursor
	 * @return Object
	 * 			Returns Bean Object loaded from database cursor.
	 */
	@Override
	protected Object fetchSingleRecord(Cursor cursor) {
		// Initialize an Object to hold result.
		Object result = null;
		// Check cursor is valid and open.
		if (cursor != null && !cursor.isClosed() && cursor.getCount() > 0) {
			try {
				// Get Volunteer Object by the cursor deserialization.
				result = this.deserializeFromCursor(cursor);
		    	CustomLogger.getInstance().infoLog(VolunteerManager.class.getName(), "Single record fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(VolunteerManager.class.getName(), "Failed to fetch single record.");
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Load all records from database to a Object List.
	 * @param cursor
	 * @return List
	 * 			Returns List with all records found in database.
	 */
	@Override
	protected List<Object> fetchAllRecords(Cursor cursor) {
		List<Object> result = null;
		// Check cursor is valid and open.
		if (cursor != null && !cursor.isClosed() && cursor.getCount() > 0) {
			// Initialize an ArrayList Object to hold result.
			result = new ArrayList<Object>();
			try {
				// Check if cursor is not at last element.
				while(cursor.isAfterLast() == false) {
					// Add new Volunteer Object by the cursor deserialization.
					result.add(this.deserializeFromCursor(cursor));
					// Move cursor to next element.
					cursor.moveToNext();
				}
		    	CustomLogger.getInstance().infoLog(VolunteerManager.class.getName(), "All records fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(VolunteerManager.class.getName(), "Failed to fetch all records.");
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Check instance of current Bean Object.
	 * @param bean
	 * @return Object
	 * 			Returns validated Bean Object to realize CRUD operations by Content Provider.
	 */
	@Override
	protected Object checkBean(Object bean) {
		// Check if Bean Object parameter is valid.
		Constraint.checkNullParam(bean);
		// Check if Bean Object is an Volunteer Object.
		if (!(bean instanceof Volunteer)) {
			// Failed to get the Volunteer Object.
			String errorMsg = "Failed to get the Volunteer Object. The given Bean Object is not an Volunteer Object.";
	    	CustomLogger.getInstance().errorLog(VolunteerManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
    	CustomLogger.getInstance().infoLog(VolunteerManager.class.getName(), "Bean Object checked.");
		return bean;
	}

	/**
	 * Get parameters for CRUD operations by Content Provider. 
	 * @param operationType
	 * @return ProviderParams
	 * 			Returns customized Parameters from Bean Object to realize CRUD operations by Content Provider.
	 */
	@Override
	protected ArrayList<ProviderParams> getParams(EnumOperationType operationType) {
		// Initialize an Provider Parameters Object to hold result.
		ArrayList<ProviderParams> params = new ArrayList<ProviderParams>();
		ProviderParams entityParams = new ProviderParams();
		ProviderParams personParams = new ProviderParams();
		ProviderParams userParams = new ProviderParams();
		ProviderParams volunteerParams = new ProviderParams();
		// Get the parameters by the operation type. 
		switch (operationType) {
		case CREATE:
			entityParams.setURI(ProviderStatement.ENTITY.CONTENT_URI);
			params.add(entityParams);
			personParams.setURI(ProviderStatement.PERSON.CONTENT_URI);
			params.add(personParams);
			userParams.setURI(ProviderStatement.USER.CONTENT_URI);
			params.add(userParams);
			volunteerParams.setURI(ProviderStatement.VOLUNTEER.CONTENT_URI);
			params.add(volunteerParams);
			break;
		case UPDATE:
		case DELETE:
			entityParams.setURI(ProviderStatement.ENTITY.CONTENT_URI);
			entityParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			entityParams.setSelectionArgs(new String[]{String.valueOf(((Volunteer) getBean()).getId())});
			params.add(entityParams);
			personParams.setURI(ProviderStatement.PERSON.CONTENT_URI);
			personParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			personParams.setSelectionArgs(new String[]{String.valueOf(((Volunteer) getBean()).getId())});
			params.add(personParams);
			userParams.setURI(ProviderStatement.USER.CONTENT_URI);
			userParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			userParams.setSelectionArgs(new String[]{String.valueOf(((Volunteer) getBean()).getId())});
			params.add(userParams);
			volunteerParams.setURI(ProviderStatement.VOLUNTEER.CONTENT_URI);
			volunteerParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			volunteerParams.setSelectionArgs(new String[]{String.valueOf(((Volunteer) getBean()).getId())});
			params.add(volunteerParams);
			break;
		case READ:
			volunteerParams.setURI(ProviderStatement.VOLUNTEER.CONTENT_URI_READ);
			volunteerParams.setProjection(ProviderStatement.VOLUNTEER.PROJECTION_ALL);
			volunteerParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			volunteerParams.setSelectionArgs(new String[]{String.valueOf(((Volunteer) getBean()).getId())});
			params.add(volunteerParams);
			break;
		case READ_ALL:
			volunteerParams.setURI(ProviderStatement.VOLUNTEER.CONTENT_URI_READ);
			volunteerParams.setProjection(ProviderStatement.VOLUNTEER.PROJECTION_ALL);
			params.add(volunteerParams);
			break;
		default:
			// Failed to get the Provider Parameters.
			String errorMsg = "Failed to get the Provider Parameters. Invalid operation type.";
	    	CustomLogger.getInstance().errorLog(VolunteerManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
		CustomLogger.getInstance().infoLog(VolunteerManager.class.getName(), "Volunteer Provider Parameters retrieved.");
		return params;
	}

	/**
	 * Create ContentValues Object to use for database transaction.
	 * @return ContentValues
	 * 			Returns formatted ContentValues data from Bean Object. 
	 */
	@Override
	protected ArrayList<ContentValues> getContentValues() {
		// Initialize an ContentValues Object to hold result.
		ArrayList<ContentValues> contentValues = new ArrayList<ContentValues>();
		try {
			ContentValues entityValues = new ContentValues();
			// Note that ID Column is NOT included here because it is auto-incremented.
			entityValues.put(DBStatement.ENTITY.COL_NAME, ((Volunteer) getBean()).getName());
			entityValues.put(DBStatement.ENTITY.COL_LOCATION_ID, String.valueOf(((Volunteer) getBean()).getLocationId()));
			contentValues.add(entityValues);

			ContentValues personValues = new ContentValues();
			personValues.put(BaseColumns._ID, ((Volunteer) getBean()).getId());
			personValues.put(DBStatement.PERSON.COL_GENDER, ((Volunteer) getBean()).getGender());
			personValues.put(DBStatement.PERSON.COL_BIRTHDATE, ((Volunteer) getBean()).getBirthDateString());
			personValues.put(DBStatement.PERSON.COL_AGE, ((Volunteer) getBean()).getAge());
			contentValues.add(personValues);

			ContentValues userValues = new ContentValues();
			userValues.put(BaseColumns._ID, ((Volunteer) getBean()).getId());
			userValues.put(DBStatement.USER.COL_LOGIN, ((Volunteer) getBean()).getLogin());
			userValues.put(DBStatement.USER.COL_PASSWORD, ((Volunteer) getBean()).getPassword());
			contentValues.add(userValues);

			ContentValues volunteerValues = new ContentValues();
			volunteerValues.put(BaseColumns._ID, ((Volunteer) getBean()).getId());
			contentValues.add(volunteerValues);
			CustomLogger.getInstance().infoLog(VolunteerManager.class.getName(), "Volunteer Content Values retrieved.");
		} catch (Exception e) {
			// Failed to get the Content Values.
			String errorMsg = "Failed to get the Volunteer Content Values.";
	    	CustomLogger.getInstance().errorLog(VolunteerManager.class.getName(), errorMsg);
			throw new IllegalAccessError(errorMsg);
		}
		return contentValues;
	}

	/**
	 * Deserialize one record.
	 * Deserialization is the process of turning a stream of bytes or a cursor into an object in memory.
	 * @param cursor
	 * @return Object
	 * 			Returns Bean Object from Cursor.
	 */
	@Override
	protected Object deserializeFromCursor(Cursor cursor) {
		Object objOut = null;
		// Check cursor is valid and open.
		if (cursor != null && !cursor.isClosed() && cursor.getCount() > 0) {
			// Create a temporary Volunteer Object.
			Volunteer volunteerInfo = null;
			try {
				// Check if Object is already created.
				// Case affirmative, use it.
				if ((Volunteer) getBean() != null) {
					volunteerInfo = ((Volunteer) getBean());
				} else {
				// Otherwise, create a new one.
					volunteerInfo = new Volunteer();
				}
				// Get Volunteer Object ID from cursor.
				volunteerInfo.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
				// Get Volunteer Object name from cursor.
				volunteerInfo.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_NAME)));
				// Get Volunteer Object Location ID from cursor.
				volunteerInfo.setLocationId(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_LOCATION_ID)));
				// Get Volunteer Object gender from cursor.
				volunteerInfo.setGender(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_GENDER)));
				// Get Volunteer Object birth date from cursor.
				volunteerInfo.setBirthDateString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_BIRTHDATE)));
				// Get Volunteer Object age from cursor.
				volunteerInfo.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_AGE)));
				// Get Volunteer Object login from cursor.
				volunteerInfo.setLogin(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.USER.COL_LOGIN)));
				// Get Volunteer Object password from cursor.
				volunteerInfo.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.USER.COL_PASSWORD)));
				objOut = volunteerInfo;
				CustomLogger.getInstance().infoLog(VolunteerManager.class.getName(), "Volunteer Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(VolunteerManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary Volunteer Object.
				volunteerInfo = null;
			}
		}
		return objOut;
	}

	/**
	 * Deserialize one record.
	 * Deserialize method overload to return Cursor data as StringArray.
	 * Deserialization is the process of turning a stream of bytes or a cursor into an object in memory.
	 * @param cursor
	 * @return String[]
	 * 			Returns Bean Object from Cursor as StringArray.
	 */
	@Override
	protected String[] deserializeFromCursorToStringArray(Cursor cursor) {
		String[] StringArrayResult = null;
		// Check cursor is valid and open.
		if (cursor != null && !cursor.isClosed() && cursor.getCount() > 0) {
			// Create a temporary StringArray Object.
			String[] volunteerInfo = new String[cursor.getColumnCount()];
			// Create a temporary Volunteer Object.
			Volunteer volunteerObject = null;
			try {
				volunteerObject = (Volunteer) this.deserializeFromCursor(cursor);
				// Get Volunteer ID from Volunteer Object.
				volunteerInfo[cursor.getColumnIndexOrThrow(BaseColumns._ID)] = String.valueOf(volunteerObject.getId());
				// Get Volunteer name from Volunteer Object.
				volunteerInfo[cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_NAME)] = volunteerObject.getName();
				// Get Volunteer Location ID from Volunteer Object.
				volunteerInfo[cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_LOCATION_ID)] = String.valueOf(volunteerObject.getLocationId());
				// Get Volunteer gender from Volunteer Object.
				volunteerInfo[cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_GENDER)] = String.valueOf(volunteerObject.getGender());
				// Get Volunteer birth date from Volunteer Object.
				volunteerInfo[cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_BIRTHDATE)] = volunteerObject.getBirthDateString();
				// Get Volunteer age from Volunteer Object.
				volunteerInfo[cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_AGE)] = String.valueOf(volunteerObject.getAge());
				// Get Volunteer login from Volunteer Object.
				volunteerInfo[cursor.getColumnIndexOrThrow(DBStatement.USER.COL_LOGIN)] = volunteerObject.getLogin();
				// Get Volunteer password from Volunteer Object.
				volunteerInfo[cursor.getColumnIndexOrThrow(DBStatement.USER.COL_PASSWORD)] = volunteerObject.getPassword();
				StringArrayResult = volunteerInfo;
				CustomLogger.getInstance().infoLog(VolunteerManager.class.getName(), "Volunteer Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(VolunteerManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary Volunteer Object.
				volunteerObject = null;
				// Destroy the temporary StringArray Object.
				volunteerInfo = null;
			}
		}
		return StringArrayResult;
	}

}
