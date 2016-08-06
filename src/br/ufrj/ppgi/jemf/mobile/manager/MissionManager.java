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
import br.ufrj.ppgi.jemf.mobile.bean.Mission;
import br.ufrj.ppgi.jemf.mobile.database.DBStatement;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderParams;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderStatement;
import br.ufrj.ppgi.jemf.utils.Constraint;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class MissionManager extends Manager {

	/**
	 * Constructor.
	 */
	public MissionManager() {
    	CustomLogger.getInstance().infoLog(MissionManager.class.getName(), "Class started.");
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
					((Mission) result).setId(Integer.valueOf(newUri.getLastPathSegment()));
			    	CustomLogger.getInstance().infoLog(MissionManager.class.getName(), "New Mission Object ID retrieved.");
				} else {
					// Failed to get the Bean Object.
					String errorMsg = "Failed to set the new ID to the Bean Object.";
			    	CustomLogger.getInstance().errorLog(MissionManager.class.getName(), errorMsg);
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
						result.put(BaseColumns._ID, ((Mission) getBean()).getId());
				    	CustomLogger.getInstance().infoLog(MissionManager.class.getName(), "Detail Record ID updated.");
					} else {
						// Failed to update the Detail content values.
						String errorMsg = "Failed to update the Detail content values.";
				    	CustomLogger.getInstance().errorLog(MissionManager.class.getName(), errorMsg);
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
				// Get Mission Object by the cursor deserialization.
				result = this.deserializeFromCursor(cursor);
		    	CustomLogger.getInstance().infoLog(MissionManager.class.getName(), "Single record fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(MissionManager.class.getName(), "Failed to fetch single record.");
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
					// Add new Mission Object by the cursor deserialization.
					result.add(this.deserializeFromCursor(cursor));
					// Move cursor to next element.
					cursor.moveToNext();
				}
		    	CustomLogger.getInstance().infoLog(MissionManager.class.getName(), "All records fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(MissionManager.class.getName(), "Failed to fetch all records.");
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
		// Check if Bean Object is an Mission Object.
		if (!(bean instanceof Mission)) {
			// Failed to get the Mission Object.
			String errorMsg = "Failed to get the Mission Object. The given Bean Object is not an Mission Object.";
	    	CustomLogger.getInstance().errorLog(MissionManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
    	CustomLogger.getInstance().infoLog(MissionManager.class.getName(), "Bean Object checked.");
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
		// Initialize an Provider Parameters Object List to hold result.
		ArrayList<ProviderParams> params = new ArrayList<ProviderParams>();
		ProviderParams serviceParams = new ProviderParams();
		ProviderParams missionParams = new ProviderParams();
		// Get the parameters by the operation type. 
		switch (operationType) {
		case CREATE:
			serviceParams.setURI(ProviderStatement.SERVICE.CONTENT_URI);
			params.add(serviceParams);
			missionParams.setURI(ProviderStatement.MISSION.CONTENT_URI);
			params.add(missionParams);
			break;
		case UPDATE:
		case DELETE:
			serviceParams.setURI(ProviderStatement.SERVICE.CONTENT_URI);
			serviceParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			serviceParams.setSelectionArgs(new String[]{String.valueOf(((Mission) getBean()).getId())});
			params.add(serviceParams);
			missionParams.setURI(ProviderStatement.MISSION.CONTENT_URI);
			missionParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			missionParams.setSelectionArgs(new String[]{String.valueOf(((Mission) getBean()).getId())});
			params.add(missionParams);
			break;
		case READ:
			missionParams.setURI(ProviderStatement.MISSION.CONTENT_URI_READ);
			missionParams.setProjection(ProviderStatement.MISSION.PROJECTION_ALL);
			missionParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			missionParams.setSelectionArgs(new String[]{String.valueOf(((Mission) getBean()).getId())});
			params.add(missionParams);
			break;
		case READ_ALL:
			missionParams.setURI(ProviderStatement.MISSION.CONTENT_URI_READ);
			missionParams.setProjection(ProviderStatement.MISSION.PROJECTION_ALL);
			params.add(missionParams);
			break;
		default:
			// Failed to get the Provider Parameters.
			String errorMsg = "Failed to get the Provider Parameters. Invalid operation type.";
	    	CustomLogger.getInstance().errorLog(MissionManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
		CustomLogger.getInstance().infoLog(MissionManager.class.getName(), "Mission Provider Parameters retrieved.");
		return params;
	}

	/**
	 * Create ContentValues Object to use for database transaction.
	 * @return ContentValues
	 * 			Returns formatted ContentValues data from Bean Object. 
	 */
	@Override
	protected ArrayList<ContentValues> getContentValues() {
		// Initialize an ContentValues Object List to hold result.
		ArrayList<ContentValues> contentValues = new ArrayList<ContentValues>();
		try {
			ContentValues serviceValues = new ContentValues();
			// Note that ID Column is NOT included here because it is auto-incremented.
			serviceValues.put(DBStatement.SERVICE.COL_TITLE, ((Mission) getBean()).getTitle());
			serviceValues.put(DBStatement.SERVICE.COL_DESCRIPTION, ((Mission) getBean()).getDescription());
			serviceValues.put(DBStatement.SERVICE.COL_STATUS, ((Mission) getBean()).getStatusString());
			serviceValues.put(DBStatement.SERVICE.COL_START_DATE, ((Mission) getBean()).getStartDateString());
			serviceValues.put(DBStatement.SERVICE.COL_END_DATE, ((Mission) getBean()).getEndDateString());
			serviceValues.put(DBStatement.SERVICE.COL_PRIORITY, ((Mission) getBean()).getPriorityString());
			serviceValues.put(DBStatement.SERVICE.COL_EMERGENCY_ID, String.valueOf(((Mission) getBean()).getEmergencyId()));
			contentValues.add(serviceValues);

			ContentValues missionValues = new ContentValues();
			missionValues.put(BaseColumns._ID, ((Mission) getBean()).getId());
			contentValues.add(missionValues);
			CustomLogger.getInstance().infoLog(MissionManager.class.getName(), "Mission Content Values retrieved.");
		} catch (Exception e) {
			// Failed to get the Content Values.
			String errorMsg = "Failed to get the Mission Content Values.";
	    	CustomLogger.getInstance().errorLog(MissionManager.class.getName(), errorMsg);
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
			// Create a temporary Mission Object.
			Mission missionInfo = null;
			try {
				// Check if Object is already created.
				// Case affirmative, use it.
				if ((Mission) getBean() != null) {
					missionInfo = ((Mission) getBean());
				} else {
				// Otherwise, create a new one.
					missionInfo = new Mission();
				}
				// Get Mission Object ID from cursor.
				missionInfo.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
				// Get Mission Object title from cursor.
				missionInfo.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_TITLE)));
				// Get Mission Object description from cursor.
				missionInfo.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_DESCRIPTION)));
				// Get Mission Object status from cursor.
				missionInfo.setStatusString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_STATUS)));
				// Get Mission Object start date from cursor.
				missionInfo.setStartDateString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_START_DATE)));
				// Get Mission Object end date from cursor.
				missionInfo.setEndDateString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_END_DATE)));
				// Get Mission Object priority from cursor.
				missionInfo.setPriorityString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_PRIORITY)));
				// Get Mission Object Emergency ID from cursor.
				missionInfo.setEmergencyId(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_EMERGENCY_ID)));
				objOut = missionInfo;
				CustomLogger.getInstance().infoLog(MissionManager.class.getName(), "Mission Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(MissionManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary Mission Object.
				missionInfo = null;
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
			String[] missionInfo = new String[cursor.getColumnCount()];
			// Create a temporary Mission Object.
			Mission missionObject = null;
			try {
				missionObject = (Mission) this.deserializeFromCursor(cursor);
				// Get Mission ID from Mission Object.
				missionInfo[cursor.getColumnIndexOrThrow(BaseColumns._ID)] = String.valueOf(missionObject.getId());
				// Get Mission title from Mission Object.
				missionInfo[cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_TITLE)] = missionObject.getTitle();
				// Get Mission description from Mission Object.
				missionInfo[cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_DESCRIPTION)] = missionObject.getDescription();
				// Get Mission status from Mission Object.
				missionInfo[cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_STATUS)] = missionObject.getStatusString();
				// Get Mission start date from Mission Object.
				missionInfo[cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_START_DATE)] = missionObject.getStartDateString();
				// Get Mission end date from Mission Object.
				missionInfo[cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_END_DATE)] = missionObject.getEndDateString();
				// Get Mission priority from Mission Object.
				missionInfo[cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_PRIORITY)] = missionObject.getPriorityString();
				// Get Mission Object Emergency ID from cursor.
				missionInfo[cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_EMERGENCY_ID)] = String.valueOf(missionObject.getEmergencyId());
				StringArrayResult = missionInfo;
				CustomLogger.getInstance().infoLog(MissionManager.class.getName(), "Mission Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(MissionManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary Mission Object.
				missionObject = null;
				// Destroy the temporary StringArray Object.
				missionInfo = null;
			}
		}
		return StringArrayResult;
	}

}
