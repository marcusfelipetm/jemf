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
import br.ufrj.ppgi.jemf.mobile.bean.Task;
import br.ufrj.ppgi.jemf.mobile.database.DBStatement;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderParams;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderStatement;
import br.ufrj.ppgi.jemf.utils.Constraint;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class TaskManager extends Manager {

	/**
	 * Constructor.
	 */
	public TaskManager() {
    	CustomLogger.getInstance().infoLog(TaskManager.class.getName(), "Class started.");
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
					((Task) result).setId(Integer.valueOf(newUri.getLastPathSegment()));
			    	CustomLogger.getInstance().infoLog(TaskManager.class.getName(), "New Task Object ID retrieved.");
				} else {
					// Failed to get the Bean Object.
					String errorMsg = "Failed to set the new ID to the Bean Object.";
			    	CustomLogger.getInstance().errorLog(TaskManager.class.getName(), errorMsg);
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
						result.put(BaseColumns._ID, ((Task) getBean()).getId());
				    	CustomLogger.getInstance().infoLog(TaskManager.class.getName(), "Detail Record ID updated.");
					} else {
						// Failed to update the Detail content values.
						String errorMsg = "Failed to update the Detail content values.";
				    	CustomLogger.getInstance().errorLog(TaskManager.class.getName(), errorMsg);
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
				// Get Task Object by the cursor deserialization.
				result = this.deserializeFromCursor(cursor);
		    	CustomLogger.getInstance().infoLog(TaskManager.class.getName(), "Single record fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(TaskManager.class.getName(), "Failed to fetch single record.");
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
					// Add new Task Object by the cursor deserialization.
					result.add(this.deserializeFromCursor(cursor));
					// Move cursor to next element.
					cursor.moveToNext();
				}
		    	CustomLogger.getInstance().infoLog(TaskManager.class.getName(), "All records fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(TaskManager.class.getName(), "Failed to fetch all records.");
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
		// Check if Bean Object is an Task Object.
		if (!(bean instanceof Task)) {
			// Failed to get the Task Object.
			String errorMsg = "Failed to get the Task Object. The given Bean Object is not an Task Object.";
	    	CustomLogger.getInstance().errorLog(TaskManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
    	CustomLogger.getInstance().infoLog(TaskManager.class.getName(), "Bean Object checked.");
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
		ProviderParams taskParams = new ProviderParams();
		// Get the parameters by the operation type. 
		switch (operationType) {
		case CREATE:
			serviceParams.setURI(ProviderStatement.SERVICE.CONTENT_URI);
			params.add(serviceParams);
			taskParams.setURI(ProviderStatement.TASK.CONTENT_URI);
			params.add(taskParams);
			break;
		case UPDATE:
		case DELETE:
			serviceParams.setURI(ProviderStatement.SERVICE.CONTENT_URI);
			serviceParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			serviceParams.setSelectionArgs(new String[]{String.valueOf(((Task) getBean()).getId())});
			params.add(serviceParams);
			taskParams.setURI(ProviderStatement.TASK.CONTENT_URI);
			taskParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			taskParams.setSelectionArgs(new String[]{String.valueOf(((Task) getBean()).getId())});
			params.add(taskParams);
			break;
		case READ:
			taskParams.setURI(ProviderStatement.TASK.CONTENT_URI_READ);
			taskParams.setProjection(ProviderStatement.TASK.PROJECTION_ALL);
			taskParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			taskParams.setSelectionArgs(new String[]{String.valueOf(((Task) getBean()).getId())});
			params.add(taskParams);
			break;
		case READ_ALL:
			taskParams.setURI(ProviderStatement.TASK.CONTENT_URI_READ);
			taskParams.setProjection(ProviderStatement.TASK.PROJECTION_ALL);
			params.add(taskParams);
			break;
		default:
			// Failed to get the Provider Parameters.
			String errorMsg = "Failed to get the Provider Parameters. Invalid operation type.";
	    	CustomLogger.getInstance().errorLog(TaskManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
		CustomLogger.getInstance().infoLog(TaskManager.class.getName(), "Task Provider Parameters retrieved.");
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
			serviceValues.put(DBStatement.SERVICE.COL_TITLE, ((Task) getBean()).getTitle());
			serviceValues.put(DBStatement.SERVICE.COL_DESCRIPTION, ((Task) getBean()).getDescription());
			serviceValues.put(DBStatement.SERVICE.COL_STATUS, ((Task) getBean()).getStatusString());
			serviceValues.put(DBStatement.SERVICE.COL_START_DATE, ((Task) getBean()).getStartDateString());
			serviceValues.put(DBStatement.SERVICE.COL_END_DATE, ((Task) getBean()).getEndDateString());
			serviceValues.put(DBStatement.SERVICE.COL_PRIORITY, ((Task) getBean()).getPriorityString());
			serviceValues.put(DBStatement.SERVICE.COL_EMERGENCY_ID, String.valueOf(((Task) getBean()).getEmergencyId()));
			contentValues.add(serviceValues);

			ContentValues taskValues = new ContentValues();
			taskValues.put(BaseColumns._ID, ((Task) getBean()).getId());
			taskValues.put(DBStatement.TASK.COL_USER_ID, String.valueOf(((Task) getBean()).getUserId()));
			contentValues.add(taskValues);
			CustomLogger.getInstance().infoLog(TaskManager.class.getName(), "Task Content Values retrieved.");
		} catch (Exception e) {
			// Failed to get the Content Values.
			String errorMsg = "Failed to get the Task Content Values.";
	    	CustomLogger.getInstance().errorLog(TaskManager.class.getName(), errorMsg);
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
			// Create a temporary Task Object.
			Task taskInfo = null;
			try {
				// Check if Object is already created.
				// Case affirmative, use it.
				if ((Task) getBean() != null) {
					taskInfo = ((Task) getBean());
				} else {
				// Otherwise, create a new one.
					taskInfo = new Task();
				}
				// Get Task Object ID from cursor.
				taskInfo.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
				// Get Task Object title from cursor.
				taskInfo.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_TITLE)));
				// Get Task Object description from cursor.
				taskInfo.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_DESCRIPTION)));
				// Get Task Object status from cursor.
				taskInfo.setStatusString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_STATUS)));
				// Get Task Object start date from cursor.
				taskInfo.setStartDateString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_START_DATE)));
				// Get Task Object end date from cursor.
				taskInfo.setEndDateString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_END_DATE)));
				// Get Task Object priority from cursor.
				taskInfo.setPriorityString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_PRIORITY)));
				// Get Task Object Emergency ID from cursor.
				taskInfo.setEmergencyId(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_EMERGENCY_ID)));
				// Get Task Object User ID from cursor.
				taskInfo.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.TASK.COL_USER_ID)));
				objOut = taskInfo;
				CustomLogger.getInstance().infoLog(TaskManager.class.getName(), "Task Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(TaskManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary Task Object.
				taskInfo = null;
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
			String[] taskInfo = new String[cursor.getColumnCount()];
			// Create a temporary Task Object.
			Task taskObject = null;
			try {
				taskObject = (Task) this.deserializeFromCursor(cursor);
				// Get Task ID from Task Object.
				taskInfo[cursor.getColumnIndexOrThrow(BaseColumns._ID)] = String.valueOf(taskObject.getId());
				// Get Task title from Task Object.
				taskInfo[cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_TITLE)] = taskObject.getTitle();
				// Get Task description from Task Object.
				taskInfo[cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_DESCRIPTION)] = taskObject.getDescription();
				// Get Task status from Task Object.
				taskInfo[cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_STATUS)] = taskObject.getStatusString();
				// Get Task start date from Task Object.
				taskInfo[cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_START_DATE)] = taskObject.getStartDateString();
				// Get Task end date from Task Object.
				taskInfo[cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_END_DATE)] = taskObject.getEndDateString();
				// Get Task priority from Task Object.
				taskInfo[cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_PRIORITY)] = taskObject.getPriorityString();
				// Get Task Object Emergency ID from cursor.
				taskInfo[cursor.getColumnIndexOrThrow(DBStatement.SERVICE.COL_EMERGENCY_ID)] = String.valueOf(taskObject.getEmergencyId());
				// Get Task Object User ID from cursor.
				taskInfo[cursor.getColumnIndexOrThrow(DBStatement.TASK.COL_USER_ID)] = String.valueOf(taskObject.getUserId());
				StringArrayResult = taskInfo;
				CustomLogger.getInstance().infoLog(TaskManager.class.getName(), "Task Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(TaskManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary Task Object.
				taskObject = null;
				// Destroy the temporary StringArray Object.
				taskInfo = null;
			}
		}
		return StringArrayResult;
	}

}

