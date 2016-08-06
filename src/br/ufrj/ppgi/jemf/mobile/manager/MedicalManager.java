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
import br.ufrj.ppgi.jemf.mobile.bean.Medical;
import br.ufrj.ppgi.jemf.mobile.database.DBStatement;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderParams;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderStatement;
import br.ufrj.ppgi.jemf.utils.Constraint;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class MedicalManager extends Manager {

	/**
	 * Constructor.
	 */
	public MedicalManager() {
    	CustomLogger.getInstance().infoLog(MedicalManager.class.getName(), "Class started.");
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
					((Medical) result).setId(Integer.valueOf(newUri.getLastPathSegment()));
			    	CustomLogger.getInstance().infoLog(MedicalManager.class.getName(), "New Medical Object ID retrieved.");
				} else {
					// Failed to get the Bean Object.
					String errorMsg = "Failed to set the new ID to the Bean Object.";
			    	CustomLogger.getInstance().errorLog(MedicalManager.class.getName(), errorMsg);
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
						result.put(BaseColumns._ID, ((Medical) getBean()).getId());
				    	CustomLogger.getInstance().infoLog(MedicalManager.class.getName(), "Detail Record ID updated.");
					} else {
						// Failed to update the Detail content values.
						String errorMsg = "Failed to update the Detail content values.";
				    	CustomLogger.getInstance().errorLog(MedicalManager.class.getName(), errorMsg);
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
				// Get Medical Object by the cursor deserialization.
				result = this.deserializeFromCursor(cursor);
		    	CustomLogger.getInstance().infoLog(MedicalManager.class.getName(), "Single record fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(MedicalManager.class.getName(), "Failed to fetch single record.");
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
					// Add new Medical Object by the cursor deserialization.
					result.add(this.deserializeFromCursor(cursor));
					// Move cursor to next element.
					cursor.moveToNext();
				}
		    	CustomLogger.getInstance().infoLog(MedicalManager.class.getName(), "All records fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(MedicalManager.class.getName(), "Failed to fetch all records.");
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
		// Check if Bean Object is an Medical Object.
		if (!(bean instanceof Medical)) {
			// Failed to get the Medical Object.
			String errorMsg = "Failed to get the Medical Object. The given Bean Object is not an Medical Object.";
	    	CustomLogger.getInstance().errorLog(MedicalManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
    	CustomLogger.getInstance().infoLog(MedicalManager.class.getName(), "Bean Object checked.");
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
		ProviderParams medicalParams = new ProviderParams();
		// Get the parameters by the operation type. 
		switch (operationType) {
		case CREATE:
			entityParams.setURI(ProviderStatement.ENTITY.CONTENT_URI);
			params.add(entityParams);
			personParams.setURI(ProviderStatement.PERSON.CONTENT_URI);
			params.add(personParams);
			userParams.setURI(ProviderStatement.USER.CONTENT_URI);
			params.add(userParams);
			medicalParams.setURI(ProviderStatement.MEDICAL.CONTENT_URI);
			params.add(medicalParams);
			break;
		case UPDATE:
		case DELETE:
			entityParams.setURI(ProviderStatement.ENTITY.CONTENT_URI);
			entityParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			entityParams.setSelectionArgs(new String[]{String.valueOf(((Medical) getBean()).getId())});
			params.add(entityParams);
			personParams.setURI(ProviderStatement.PERSON.CONTENT_URI);
			personParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			personParams.setSelectionArgs(new String[]{String.valueOf(((Medical) getBean()).getId())});
			params.add(personParams);
			userParams.setURI(ProviderStatement.USER.CONTENT_URI);
			userParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			userParams.setSelectionArgs(new String[]{String.valueOf(((Medical) getBean()).getId())});
			params.add(userParams);
			medicalParams.setURI(ProviderStatement.MEDICAL.CONTENT_URI);
			medicalParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			medicalParams.setSelectionArgs(new String[]{String.valueOf(((Medical) getBean()).getId())});
			params.add(medicalParams);
			break;
		case READ:
			medicalParams.setURI(ProviderStatement.MEDICAL.CONTENT_URI_READ);
			medicalParams.setProjection(ProviderStatement.MEDICAL.PROJECTION_ALL);
			medicalParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			medicalParams.setSelectionArgs(new String[]{String.valueOf(((Medical) getBean()).getId())});
			params.add(medicalParams);
			break;
		case READ_ALL:
			medicalParams.setURI(ProviderStatement.MEDICAL.CONTENT_URI_READ);
			medicalParams.setProjection(ProviderStatement.MEDICAL.PROJECTION_ALL);
			params.add(medicalParams);
			break;
		default:
			// Failed to get the Provider Parameters.
			String errorMsg = "Failed to get the Provider Parameters. Invalid operation type.";
	    	CustomLogger.getInstance().errorLog(MedicalManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
		CustomLogger.getInstance().infoLog(MedicalManager.class.getName(), "Medical Provider Parameters retrieved.");
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
			entityValues.put(DBStatement.ENTITY.COL_NAME, ((Medical) getBean()).getName());
			entityValues.put(DBStatement.ENTITY.COL_LOCATION_ID, String.valueOf(((Medical) getBean()).getLocationId()));
			contentValues.add(entityValues);

			ContentValues personValues = new ContentValues();
			personValues.put(BaseColumns._ID, ((Medical) getBean()).getId());
			personValues.put(DBStatement.PERSON.COL_GENDER, ((Medical) getBean()).getGender());
			personValues.put(DBStatement.PERSON.COL_BIRTHDATE, ((Medical) getBean()).getBirthDateString());
			personValues.put(DBStatement.PERSON.COL_AGE, ((Medical) getBean()).getAge());
			contentValues.add(personValues);

			ContentValues userValues = new ContentValues();
			userValues.put(BaseColumns._ID, ((Medical) getBean()).getId());
			userValues.put(DBStatement.USER.COL_LOGIN, ((Medical) getBean()).getLogin());
			userValues.put(DBStatement.USER.COL_PASSWORD, ((Medical) getBean()).getPassword());
			contentValues.add(userValues);

			ContentValues medicalValues = new ContentValues();
			medicalValues.put(BaseColumns._ID, ((Medical) getBean()).getId());
			contentValues.add(medicalValues);
			CustomLogger.getInstance().infoLog(MedicalManager.class.getName(), "Medical Content Values retrieved.");
		} catch (Exception e) {
			// Failed to get the Content Values.
			String errorMsg = "Failed to get the Medical Content Values.";
	    	CustomLogger.getInstance().errorLog(MedicalManager.class.getName(), errorMsg);
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
			// Create a temporary Medical Object.
			Medical medicalInfo = null;
			try {
				// Check if Object is already created.
				// Case affirmative, use it.
				if ((Medical) getBean() != null) {
					medicalInfo = ((Medical) getBean());
				} else {
				// Otherwise, create a new one.
					medicalInfo = new Medical();
				}
				// Get Medical Object ID from cursor.
				medicalInfo.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
				// Get Medical Object name from cursor.
				medicalInfo.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_NAME)));
				// Get Medical Object Location ID from cursor.
				medicalInfo.setLocationId(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_LOCATION_ID)));
				// Get Medical Object gender from cursor.
				medicalInfo.setGender(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_GENDER)));
				// Get Medical Object birth date from cursor.
				medicalInfo.setBirthDateString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_BIRTHDATE)));
				// Get Medical Object age from cursor.
				medicalInfo.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_AGE)));
				// Get Medical Object login from cursor.
				medicalInfo.setLogin(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.USER.COL_LOGIN)));
				// Get Medical Object password from cursor.
				medicalInfo.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.USER.COL_PASSWORD)));
				objOut = medicalInfo;
				CustomLogger.getInstance().infoLog(MedicalManager.class.getName(), "Medical Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(MedicalManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary Medical Object.
				medicalInfo = null;
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
			String[] medicalInfo = new String[cursor.getColumnCount()];
			// Create a temporary Medical Object.
			Medical medicalObject = null;
			try {
				medicalObject = (Medical) this.deserializeFromCursor(cursor);
				// Get Medical ID from Medical Object.
				medicalInfo[cursor.getColumnIndexOrThrow(BaseColumns._ID)] = String.valueOf(medicalObject.getId());
				// Get Medical name from Medical Object.
				medicalInfo[cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_NAME)] = medicalObject.getName();
				// Get Medical Location ID from Medical Object.
				medicalInfo[cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_LOCATION_ID)] = String.valueOf(medicalObject.getLocationId());
				// Get Medical gender from Medical Object.
				medicalInfo[cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_GENDER)] = String.valueOf(medicalObject.getGender());
				// Get Medical birth date from Medical Object.
				medicalInfo[cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_BIRTHDATE)] = medicalObject.getBirthDateString();
				// Get Medical age from Medical Object.
				medicalInfo[cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_AGE)] = String.valueOf(medicalObject.getAge());
				// Get Medical login from Medical Object.
				medicalInfo[cursor.getColumnIndexOrThrow(DBStatement.USER.COL_LOGIN)] = medicalObject.getLogin();
				// Get Medical password from Medical Object.
				medicalInfo[cursor.getColumnIndexOrThrow(DBStatement.USER.COL_PASSWORD)] = medicalObject.getPassword();
				StringArrayResult = medicalInfo;
				CustomLogger.getInstance().infoLog(MedicalManager.class.getName(), "Medical Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(MedicalManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary Medical Object.
				medicalObject = null;
				// Destroy the temporary StringArray Object.
				medicalInfo = null;
			}
		}
		return StringArrayResult;
	}

}
