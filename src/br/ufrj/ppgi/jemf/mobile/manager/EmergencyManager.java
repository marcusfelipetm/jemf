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
import br.ufrj.ppgi.jemf.mobile.bean.Emergency;
import br.ufrj.ppgi.jemf.mobile.database.DBStatement;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderParams;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderStatement;
import br.ufrj.ppgi.jemf.utils.Constraint;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class EmergencyManager extends Manager {

	/**
	 * Constructor.
	 */
	public EmergencyManager() {
    	CustomLogger.getInstance().infoLog(EmergencyManager.class.getName(), "Class started.");
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
					((Emergency) result).setId(Integer.valueOf(newUri.getLastPathSegment()));
			    	CustomLogger.getInstance().infoLog(EmergencyManager.class.getName(), "New Emergency Object ID retrieved.");
				} else {
					// Failed to get the Bean Object.
					String errorMsg = "Failed to set the new ID to the Bean Object.";
			    	CustomLogger.getInstance().errorLog(EmergencyManager.class.getName(), errorMsg);
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
		// Failed to update the Detail content values.
		String errorMsg = "Failed to update the Detail content values. This Manager do not have Detail content values.";
    	CustomLogger.getInstance().errorLog(EmergencyManager.class.getName(), errorMsg);
		throw new IllegalAccessError(errorMsg);
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
				// Get Emergency Object by the cursor deserialization.
				result = this.deserializeFromCursor(cursor);
		    	CustomLogger.getInstance().infoLog(EmergencyManager.class.getName(), "Single record fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(EmergencyManager.class.getName(), "Failed to fetch single record.");
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
					// Add new Emergency Object by the cursor deserialization.
					result.add(this.deserializeFromCursor(cursor));
					// Move cursor to next element.
					cursor.moveToNext();
				}
		    	CustomLogger.getInstance().infoLog(EmergencyManager.class.getName(), "All records fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(EmergencyManager.class.getName(), "Failed to fetch all records.");
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
		// Check if Bean Object is an Emergency Object.
		if (!(bean instanceof Emergency)) {
			// Failed to get the Emergency Object.
			String errorMsg = "Failed to get the Emergency Object. The given Bean Object is not an Emergency Object.";
	    	CustomLogger.getInstance().errorLog(EmergencyManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
    	CustomLogger.getInstance().infoLog(EmergencyManager.class.getName(), "Bean Object checked.");
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
		ProviderParams emergencyParams = new ProviderParams();
		// Get the parameters by the operation type. 
		switch (operationType) {
		case CREATE:
			emergencyParams.setURI(ProviderStatement.EMERGENCY.CONTENT_URI);
			params.add(emergencyParams);
			break;
		case UPDATE:
		case DELETE:
			emergencyParams.setURI(ProviderStatement.EMERGENCY.CONTENT_URI);
			emergencyParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			emergencyParams.setSelectionArgs(new String[]{String.valueOf(((Emergency) getBean()).getId())});
			params.add(emergencyParams);
			break;
		case READ:
			emergencyParams.setURI(ProviderStatement.EMERGENCY.CONTENT_URI_READ);
			emergencyParams.setProjection(ProviderStatement.EMERGENCY.PROJECTION_ALL);
			emergencyParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			emergencyParams.setSelectionArgs(new String[]{String.valueOf(((Emergency) getBean()).getId())});
			params.add(emergencyParams);
			break;
		case READ_ALL:
			emergencyParams.setURI(ProviderStatement.EMERGENCY.CONTENT_URI_READ);
			emergencyParams.setProjection(ProviderStatement.EMERGENCY.PROJECTION_ALL);
			params.add(emergencyParams);
			break;
		default:
			// Failed to get the Provider Parameters.
			String errorMsg = "Failed to get the Provider Parameters. Invalid operation type.";
	    	CustomLogger.getInstance().errorLog(EmergencyManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
		CustomLogger.getInstance().infoLog(EmergencyManager.class.getName(), "Emergency Provider Parameters retrieved.");
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
			ContentValues emergencyValues = new ContentValues();
			// Note that ID Column is NOT included here because it is auto-incremented.
			emergencyValues.put(DBStatement.EMERGENCY.COL_NAME, ((Emergency) getBean()).getName());
			emergencyValues.put(DBStatement.EMERGENCY.COL_ACTIVATED, ((Emergency) getBean()).getActivatedInt());
			emergencyValues.put(DBStatement.EMERGENCY.COL_LEVEL, ((Emergency) getBean()).getLevelString());
			emergencyValues.put(DBStatement.EMERGENCY.COL_START_DATE, ((Emergency) getBean()).getStartDateString());
			emergencyValues.put(DBStatement.EMERGENCY.COL_END_DATE, ((Emergency) getBean()).getEndDateString());
			emergencyValues.put(DBStatement.EMERGENCY.COL_TYPE, ((Emergency) getBean()).getTypeString());
			contentValues.add(emergencyValues);
			CustomLogger.getInstance().infoLog(EmergencyManager.class.getName(), "Emergency Content Values retrieved.");
		} catch (Exception e) {
			// Failed to get the Content Values.
			String errorMsg = "Failed to get the Emergency Content Values.";
	    	CustomLogger.getInstance().errorLog(EmergencyManager.class.getName(), errorMsg);
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
			// Create a temporary Emergency Object.
			Emergency emergencyInfo = null;
			try {
				// Check if Object is already created.
				// Case affirmative, use it.
				if ((Emergency) getBean() != null) {
					emergencyInfo = ((Emergency) getBean());
				} else {
				// Otherwise, create a new one.
					emergencyInfo = new Emergency();
				}
				// Get Emergency Object ID from cursor.
				emergencyInfo.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
				// Get Emergency Object name from cursor.
				emergencyInfo.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.EMERGENCY.COL_NAME)));
				// Get Emergency Object activated from cursor.
				emergencyInfo.setActivatedInt(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.EMERGENCY.COL_ACTIVATED)));
				// Get Emergency Object level from cursor.
				emergencyInfo.setLevelString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.EMERGENCY.COL_LEVEL)));
				// Get Emergency Object start date from cursor.
				emergencyInfo.setStartDateString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.EMERGENCY.COL_START_DATE)));
				// Get Emergency Object end date from cursor.
				emergencyInfo.setEndDateString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.EMERGENCY.COL_END_DATE)));
				// Get Emergency Object type from cursor.
				emergencyInfo.setTypeString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.EMERGENCY.COL_TYPE)));
				objOut = emergencyInfo;
				CustomLogger.getInstance().infoLog(EmergencyManager.class.getName(), "Emergency Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(EmergencyManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary Emergency Object.
				emergencyInfo = null;
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
			String[] emergencyInfo = new String[cursor.getColumnCount()];
			// Create a temporary Emergency Object.
			Emergency emergencyObject = null;
			try {
				emergencyObject = (Emergency) this.deserializeFromCursor(cursor);
				// Get Emergency ID from Emergency Object.
				emergencyInfo[cursor.getColumnIndexOrThrow(BaseColumns._ID)] = String.valueOf(emergencyObject.getId());
				// Get Emergency name from Emergency Object.
				emergencyInfo[cursor.getColumnIndexOrThrow(DBStatement.EMERGENCY.COL_NAME)] = emergencyObject.getName();
				// Get Emergency activated from Emergency Object.
				emergencyInfo[cursor.getColumnIndexOrThrow(DBStatement.EMERGENCY.COL_ACTIVATED)] = String.valueOf(emergencyObject.getActivatedInt());
				// Get Emergency level from Emergency Object.
				emergencyInfo[cursor.getColumnIndexOrThrow(DBStatement.EMERGENCY.COL_LEVEL)] = emergencyObject.getLevelString();
				// Get Emergency start date from Emergency Object.
				emergencyInfo[cursor.getColumnIndexOrThrow(DBStatement.EMERGENCY.COL_START_DATE)] = emergencyObject.getStartDateString();
				// Get Emergency end date from Emergency Object.
				emergencyInfo[cursor.getColumnIndexOrThrow(DBStatement.EMERGENCY.COL_END_DATE)] = emergencyObject.getEndDateString();
				// Get Emergency type from Emergency Object.
				emergencyInfo[cursor.getColumnIndexOrThrow(DBStatement.EMERGENCY.COL_TYPE)] = emergencyObject.getTypeString();
				StringArrayResult = emergencyInfo;
				CustomLogger.getInstance().infoLog(EmergencyManager.class.getName(), "Emergency Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(EmergencyManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary Emergency Object.
				emergencyObject = null;
				// Destroy the temporary StringArray Object.
				emergencyInfo = null;
			}
		}
		return StringArrayResult;
	}

}
