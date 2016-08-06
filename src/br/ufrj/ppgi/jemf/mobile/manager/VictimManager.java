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
import br.ufrj.ppgi.jemf.mobile.bean.Victim;
import br.ufrj.ppgi.jemf.mobile.database.DBStatement;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderParams;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderStatement;
import br.ufrj.ppgi.jemf.utils.Constraint;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class VictimManager extends Manager {

	/**
	 * Constructor.
	 */
	public VictimManager() {
    	CustomLogger.getInstance().infoLog(VictimManager.class.getName(), "Class started.");
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
					((Victim) result).setId(Integer.valueOf(newUri.getLastPathSegment()));
			    	CustomLogger.getInstance().infoLog(VictimManager.class.getName(), "New Victim Object ID retrieved.");
				} else {
					// Failed to get the Bean Object.
					String errorMsg = "Failed to set the new ID to the Bean Object.";
			    	CustomLogger.getInstance().errorLog(VictimManager.class.getName(), errorMsg);
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
						result.put(BaseColumns._ID, ((Victim) getBean()).getId());
				    	CustomLogger.getInstance().infoLog(VictimManager.class.getName(), "Detail Record ID updated.");
					} else {
						// Failed to update the Detail content values.
						String errorMsg = "Failed to update the Detail content values.";
				    	CustomLogger.getInstance().errorLog(VictimManager.class.getName(), errorMsg);
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
				// Get Victim Object by the cursor deserialization.
				result = this.deserializeFromCursor(cursor);
		    	CustomLogger.getInstance().infoLog(VictimManager.class.getName(), "Single record fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(VictimManager.class.getName(), "Failed to fetch single record.");
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
					// Add new Victim Object by the cursor deserialization.
					result.add(this.deserializeFromCursor(cursor));
					// Move cursor to next element.
					cursor.moveToNext();
				}
		    	CustomLogger.getInstance().infoLog(VictimManager.class.getName(), "All records fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(VictimManager.class.getName(), "Failed to fetch all records.");
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
		// Check if Bean Object is an Victim Object.
		if (!(bean instanceof Victim)) {
			// Failed to get the Victim Object.
			String errorMsg = "Failed to get the Victim Object. The given Bean Object is not an Victim Object.";
	    	CustomLogger.getInstance().errorLog(VictimManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
    	CustomLogger.getInstance().infoLog(VictimManager.class.getName(), "Bean Object checked.");
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
		ProviderParams victimParams = new ProviderParams();
		// Get the parameters by the operation type. 
		switch (operationType) {
		case CREATE:
			entityParams.setURI(ProviderStatement.ENTITY.CONTENT_URI);
			params.add(entityParams);
			personParams.setURI(ProviderStatement.PERSON.CONTENT_URI);
			params.add(personParams);
			victimParams.setURI(ProviderStatement.VICTIM.CONTENT_URI);
			params.add(victimParams);
			break;
		case UPDATE:
		case DELETE:
			entityParams.setURI(ProviderStatement.ENTITY.CONTENT_URI);
			entityParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			entityParams.setSelectionArgs(new String[]{String.valueOf(((Victim) getBean()).getId())});
			params.add(entityParams);
			personParams.setURI(ProviderStatement.PERSON.CONTENT_URI);
			personParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			personParams.setSelectionArgs(new String[]{String.valueOf(((Victim) getBean()).getId())});
			params.add(personParams);
			victimParams.setURI(ProviderStatement.VICTIM.CONTENT_URI);
			victimParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			victimParams.setSelectionArgs(new String[]{String.valueOf(((Victim) getBean()).getId())});
			params.add(victimParams);
			break;
		case READ:
			victimParams.setURI(ProviderStatement.VICTIM.CONTENT_URI_READ);
			victimParams.setProjection(ProviderStatement.VICTIM.PROJECTION_ALL);
			victimParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			victimParams.setSelectionArgs(new String[]{String.valueOf(((Victim) getBean()).getId())});
			params.add(victimParams);
			break;
		case READ_ALL:
			victimParams.setURI(ProviderStatement.VICTIM.CONTENT_URI_READ);
			victimParams.setProjection(ProviderStatement.VICTIM.PROJECTION_ALL);
			params.add(victimParams);
			break;
		default:
			// Failed to get the Provider Parameters.
			String errorMsg = "Failed to get the Provider Parameters. Invalid operation type.";
	    	CustomLogger.getInstance().errorLog(VictimManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
		CustomLogger.getInstance().infoLog(VictimManager.class.getName(), "Victim Provider Parameters retrieved.");
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
			entityValues.put(DBStatement.ENTITY.COL_NAME, ((Victim) getBean()).getName());
			entityValues.put(DBStatement.ENTITY.COL_LOCATION_ID, String.valueOf(((Victim) getBean()).getLocationId()));
			contentValues.add(entityValues);

			ContentValues personValues = new ContentValues();
			personValues.put(BaseColumns._ID, ((Victim) getBean()).getId());
			personValues.put(DBStatement.PERSON.COL_GENDER, ((Victim) getBean()).getGender());
			personValues.put(DBStatement.PERSON.COL_BIRTHDATE, ((Victim) getBean()).getBirthDateString());
			personValues.put(DBStatement.PERSON.COL_AGE, ((Victim) getBean()).getAge());
			contentValues.add(personValues);

			ContentValues victimValues = new ContentValues();
			victimValues.put(BaseColumns._ID, ((Victim) getBean()).getId());
			victimValues.put(DBStatement.VICTIM.COL_STATUS, ((Victim) getBean()).getStatusString());
			victimValues.put(DBStatement.VICTIM.COL_HEALTHCAREUNIT_ID, ((Victim) getBean()).getHealthCareUnitId());
			victimValues.put(DBStatement.VICTIM.COL_AFFECTEDORGANIZATION_ID, ((Victim) getBean()).getAffectedOrganizationId());
			victimValues.put(DBStatement.VICTIM.COL_WITNESS_ID, ((Victim) getBean()).getWitnessId());
			contentValues.add(victimValues);
			CustomLogger.getInstance().infoLog(VictimManager.class.getName(), "Victim Content Values retrieved.");
		} catch (Exception e) {
			// Failed to get the Content Values.
			String errorMsg = "Failed to get the Victim Content Values.";
	    	CustomLogger.getInstance().errorLog(VictimManager.class.getName(), errorMsg);
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
			// Create a temporary Victim Object.
			Victim victimInfo = null;
			try {
				// Check if Object is already created.
				// Case affirmative, use it.
				if ((Victim) getBean() != null) {
					victimInfo = ((Victim) getBean());
				} else {
				// Otherwise, create a new one.
					victimInfo = new Victim();
				}				
				// Get Victim Object ID from cursor.
				victimInfo.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
				// Get Victim Object name from cursor.
				victimInfo.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_NAME)));
				// Get Victim Object Location ID from cursor.
				victimInfo.setLocationId(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_LOCATION_ID)));
				// Get Victim Object gender from cursor.
				victimInfo.setGender(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_GENDER)));
				// Get Victim Object birth date from cursor.
				victimInfo.setBirthDateString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_BIRTHDATE)));
				// Get Victim Object age from cursor.
				victimInfo.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_AGE)));
				// Get Victim Object status from cursor.
				victimInfo.setStatusString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.VICTIM.COL_STATUS)));
				// Get Victim Object HealthCareUnit ID from cursor.
				victimInfo.setHealthCareUnitId(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.VICTIM.COL_HEALTHCAREUNIT_ID)));
				// Get Victim Object AffectedOrganization ID from cursor.
				victimInfo.setAffectedOrganizationId(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.VICTIM.COL_AFFECTEDORGANIZATION_ID)));
				// Get Victim Object Witness ID from cursor.
				victimInfo.setWitnessId(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.VICTIM.COL_WITNESS_ID)));
				objOut = victimInfo;
				CustomLogger.getInstance().infoLog(VictimManager.class.getName(), "Victim Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(VictimManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary Victim Object.
				victimInfo = null;
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
			String[] victimInfo = new String[cursor.getColumnCount()];
			// Create a temporary Victim Object.
			Victim victimObject = null;
			try {
				victimObject = (Victim) this.deserializeFromCursor(cursor);
				// Get Victim ID from Victim Object.
				victimInfo[cursor.getColumnIndexOrThrow(BaseColumns._ID)] = String.valueOf(victimObject.getId());
				// Get Victim name from Victim Object.
				victimInfo[cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_NAME)] = victimObject.getName();
				// Get Victim Location ID from Victim Object.
				victimInfo[cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_LOCATION_ID)] = String.valueOf(victimObject.getLocationId());
				// Get Victim gender from Victim Object.
				victimInfo[cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_GENDER)] = String.valueOf(victimObject.getGender());
				// Get Victim birth date from Victim Object.
				victimInfo[cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_BIRTHDATE)] = victimObject.getBirthDateString();
				// Get Victim age from Victim Object.
				victimInfo[cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_AGE)] = String.valueOf(victimObject.getAge());
				// Get Victim status from Victim Object.
				victimInfo[cursor.getColumnIndexOrThrow(DBStatement.VICTIM.COL_STATUS)] = victimObject.getStatusString();
				// Get Victim HealthCareUnit ID from Victim Object.
				victimInfo[cursor.getColumnIndexOrThrow(DBStatement.VICTIM.COL_HEALTHCAREUNIT_ID)] = String.valueOf(victimObject.getHealthCareUnitId());
				// Get Victim AffectedOrganization ID from Victim Object.
				victimInfo[cursor.getColumnIndexOrThrow(DBStatement.VICTIM.COL_AFFECTEDORGANIZATION_ID)] = String.valueOf(victimObject.getAffectedOrganizationId());
				// Get Victim Witness ID from Victim Object.
				victimInfo[cursor.getColumnIndexOrThrow(DBStatement.VICTIM.COL_WITNESS_ID)] = String.valueOf(victimObject.getWitnessId());
				StringArrayResult = victimInfo;
				CustomLogger.getInstance().infoLog(VictimManager.class.getName(), "Victim Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(VictimManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary Victim Object.
				victimObject = null;
				// Destroy the temporary StringArray Object.
				victimInfo = null;
			}
		}
		return StringArrayResult;
	}

}
