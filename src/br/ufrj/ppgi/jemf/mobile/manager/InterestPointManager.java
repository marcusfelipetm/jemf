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
import br.ufrj.ppgi.jemf.mobile.bean.InterestPoint;
import br.ufrj.ppgi.jemf.mobile.database.DBStatement;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderParams;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderStatement;
import br.ufrj.ppgi.jemf.utils.Constraint;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class InterestPointManager extends Manager {

	/**
	 * Constructor.
	 */
	public InterestPointManager() {
    	CustomLogger.getInstance().infoLog(InterestPointManager.class.getName(), "Class started.");
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
					((InterestPoint) result).setId(Integer.valueOf(newUri.getLastPathSegment()));
			    	CustomLogger.getInstance().infoLog(InterestPointManager.class.getName(), "New InterestPoint Object ID retrieved.");
				} else {
					// Failed to get the Bean Object.
					String errorMsg = "Failed to set the new ID to the Bean Object.";
			    	CustomLogger.getInstance().errorLog(InterestPointManager.class.getName(), errorMsg);
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
						result.put(BaseColumns._ID, ((InterestPoint) getBean()).getId());
				    	CustomLogger.getInstance().infoLog(InterestPointManager.class.getName(), "Detail Record ID updated.");
					} else {
						// Failed to update the Detail content values.
						String errorMsg = "Failed to update the Detail content values.";
				    	CustomLogger.getInstance().errorLog(InterestPointManager.class.getName(), errorMsg);
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
				// Get InterestPoint Object by the cursor deserialization.
				result = this.deserializeFromCursor(cursor);
		    	CustomLogger.getInstance().infoLog(InterestPointManager.class.getName(), "Single record fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(InterestPointManager.class.getName(), "Failed to fetch single record.");
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
					// Add new InterestPoint Object by the cursor deserialization.
					result.add(this.deserializeFromCursor(cursor));
					// Move cursor to next element.
					cursor.moveToNext();
				}
		    	CustomLogger.getInstance().infoLog(InterestPointManager.class.getName(), "All records fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(InterestPointManager.class.getName(), "Failed to fetch all records.");
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
		// Check if Bean Object is an InterestPoint Object.
		if (!(bean instanceof InterestPoint)) {
			// Failed to get the InterestPoint Object.
			String errorMsg = "Failed to get the InterestPoint Object. The given Bean Object is not an InterestPoint Object.";
	    	CustomLogger.getInstance().errorLog(InterestPointManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
    	CustomLogger.getInstance().infoLog(InterestPointManager.class.getName(), "Bean Object checked.");
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
		ProviderParams locationParams = new ProviderParams();
		ProviderParams interestpointParams = new ProviderParams();
		// Get the parameters by the operation type. 
		switch (operationType) {
		case CREATE:
			locationParams.setURI(ProviderStatement.LOCATION.CONTENT_URI);
			params.add(locationParams);
			interestpointParams.setURI(ProviderStatement.INTEREST_POINT.CONTENT_URI);
			params.add(interestpointParams);
			break;
		case UPDATE:
		case DELETE:
			locationParams.setURI(ProviderStatement.LOCATION.CONTENT_URI);
			locationParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			locationParams.setSelectionArgs(new String[]{String.valueOf(((InterestPoint) getBean()).getId())});
			params.add(locationParams);
			interestpointParams.setURI(ProviderStatement.INTEREST_POINT.CONTENT_URI);
			interestpointParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			interestpointParams.setSelectionArgs(new String[]{String.valueOf(((InterestPoint) getBean()).getId())});
			params.add(interestpointParams);
			break;
		case READ:
			interestpointParams.setURI(ProviderStatement.INTEREST_POINT.CONTENT_URI_READ);
			interestpointParams.setProjection(ProviderStatement.INTEREST_POINT.PROJECTION_ALL);
			interestpointParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			interestpointParams.setSelectionArgs(new String[]{String.valueOf(((InterestPoint) getBean()).getId())});
			params.add(interestpointParams);
			break;
		case READ_ALL:
			interestpointParams.setURI(ProviderStatement.INTEREST_POINT.CONTENT_URI_READ);
			interestpointParams.setProjection(ProviderStatement.INTEREST_POINT.PROJECTION_ALL);
			params.add(interestpointParams);
			break;
		default:
			// Failed to get the Provider Parameters.
			String errorMsg = "Failed to get the Provider Parameters. Invalid operation type.";
	    	CustomLogger.getInstance().errorLog(InterestPointManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
		CustomLogger.getInstance().infoLog(InterestPointManager.class.getName(), "InterestPoint Provider Parameters retrieved.");
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
			ContentValues locationValues = new ContentValues();
			// Note that ID Column is NOT included here because it is auto-incremented.
			locationValues.put(DBStatement.LOCATION.COL_TIMESTAMP, ((InterestPoint) getBean()).getTimeStampString());
			locationValues.put(DBStatement.LOCATION.COL_INTERVAL, ((InterestPoint) getBean()).getInterval());
			locationValues.put(DBStatement.LOCATION.COL_STATUS, ((InterestPoint) getBean()).getStatusString());
			contentValues.add(locationValues);

			ContentValues interestpointValues = new ContentValues();
			interestpointValues.put(BaseColumns._ID, ((InterestPoint) getBean()).getId());
			interestpointValues.put(DBStatement.INTEREST_POINT.COL_NAME, ((InterestPoint) getBean()).getName());
			contentValues.add(interestpointValues);
			CustomLogger.getInstance().infoLog(InterestPointManager.class.getName(), "InterestPoint Content Values retrieved.");
		} catch (Exception e) {
			// Failed to get the Content Values.
			String errorMsg = "Failed to get the InterestPoint Content Values.";
	    	CustomLogger.getInstance().errorLog(InterestPointManager.class.getName(), errorMsg);
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
			// Create a temporary InterestPoint Object.
			InterestPoint interestpointInfo = null;
			try {
				// Check if Object is already created.
				// Case affirmative, use it.
				if ((InterestPoint) getBean() != null) {
					interestpointInfo = ((InterestPoint) getBean());
				} else {
				// Otherwise, create a new one.
					interestpointInfo = new InterestPoint();
				}
				// Get InterestPoint Object ID from cursor.
				interestpointInfo.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
				// Get InterestPoint Object timestamp from cursor.
				interestpointInfo.setTimeStampString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.LOCATION.COL_TIMESTAMP)));
				// Get InterestPoint Object interval from cursor.
				interestpointInfo.setInterval(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.LOCATION.COL_INTERVAL)));
				// Get InterestPoint Object status from cursor.
				interestpointInfo.setStatusString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.LOCATION.COL_STATUS)));
				// Get InterestPoint Object name from cursor.
				interestpointInfo.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.INTEREST_POINT.COL_NAME)));
				objOut = interestpointInfo;
				CustomLogger.getInstance().infoLog(InterestPointManager.class.getName(), "InterestPoint Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(InterestPointManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary InterestPoint Object.
				interestpointInfo = null;
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
			String[] interestpointInfo = new String[cursor.getColumnCount()];
			// Create a temporary InterestPoint Object.
			InterestPoint interestpointObject = null;
			try {
				interestpointObject = (InterestPoint) this.deserializeFromCursor(cursor);
				// Get InterestPoint ID from InterestPoint Object.
				interestpointInfo[cursor.getColumnIndexOrThrow(BaseColumns._ID)] = String.valueOf(interestpointObject.getId());
				// Get InterestPoint timestamp from InterestPoint Object.
				interestpointInfo[cursor.getColumnIndexOrThrow(DBStatement.LOCATION.COL_TIMESTAMP)] = interestpointObject.getTimeStampString();
				// Get InterestPoint interval from InterestPoint Object.
				interestpointInfo[cursor.getColumnIndexOrThrow(DBStatement.LOCATION.COL_INTERVAL)] = String.valueOf(interestpointObject.getInterval());
				// Get InterestPoint status from InterestPoint Object.				
				interestpointInfo[cursor.getColumnIndexOrThrow(DBStatement.LOCATION.COL_STATUS)] = interestpointObject.getStatusString();
				// Get InterestPoint name from InterestPoint Object.
				interestpointInfo[cursor.getColumnIndexOrThrow(DBStatement.INTEREST_POINT.COL_NAME)] = interestpointObject.getName();
				StringArrayResult = interestpointInfo;
				CustomLogger.getInstance().infoLog(InterestPointManager.class.getName(), "InterestPoint Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(InterestPointManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary InterestPoint Object.
				interestpointObject = null;
				// Destroy the temporary StringArray Object.
				interestpointInfo = null;
			}
		}
		return StringArrayResult;
	}

}
