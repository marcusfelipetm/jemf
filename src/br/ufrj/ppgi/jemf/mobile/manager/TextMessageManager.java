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
import br.ufrj.ppgi.jemf.mobile.bean.TextMessage;
import br.ufrj.ppgi.jemf.mobile.database.DBStatement;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderParams;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderStatement;
import br.ufrj.ppgi.jemf.utils.Constraint;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class TextMessageManager extends Manager {

	/**
	 * Constructor.
	 */
	public TextMessageManager() {
    	CustomLogger.getInstance().infoLog(TextMessageManager.class.getName(), "Class started.");
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
					((TextMessage) result).setId(Integer.valueOf(newUri.getLastPathSegment()));
			    	CustomLogger.getInstance().infoLog(TextMessageManager.class.getName(), "New TextMessage Object ID retrieved.");
				} else {
					// Failed to get the Bean Object.
					String errorMsg = "Failed to set the new ID to the Bean Object.";
			    	CustomLogger.getInstance().errorLog(TextMessageManager.class.getName(), errorMsg);
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
						result.put(BaseColumns._ID, ((TextMessage) getBean()).getId());
				    	CustomLogger.getInstance().infoLog(TextMessageManager.class.getName(), "Detail Record ID updated.");
					} else {
						// Failed to update the Detail content values.
						String errorMsg = "Failed to update the Detail content values.";
				    	CustomLogger.getInstance().errorLog(TextMessageManager.class.getName(), errorMsg);
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
				// Get TextMessage Object by the cursor deserialization.
				result = this.deserializeFromCursor(cursor);
		    	CustomLogger.getInstance().infoLog(TextMessageManager.class.getName(), "Single record fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(TextMessageManager.class.getName(), "Failed to fetch single record.");
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
					// Add new TextMessage Object by the cursor deserialization.
					result.add(this.deserializeFromCursor(cursor));
					// Move cursor to next element.
					cursor.moveToNext();
				}
		    	CustomLogger.getInstance().infoLog(TextMessageManager.class.getName(), "All records fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(TextMessageManager.class.getName(), "Failed to fetch all records.");
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
		// Check if Bean Object is an TextMessage Object.
		if (!(bean instanceof TextMessage)) {
			// Failed to get the TextMessage Object.
			String errorMsg = "Failed to get the TextMessage Object. The given Bean Object is not an TextMessage Object.";
	    	CustomLogger.getInstance().errorLog(TextMessageManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
    	CustomLogger.getInstance().infoLog(TextMessageManager.class.getName(), "Bean Object checked.");
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
		ProviderParams informationParams = new ProviderParams();
		ProviderParams messageParams = new ProviderParams();
		ProviderParams textmessageParams = new ProviderParams();
		// Get the parameters by the operation type. 
		switch (operationType) {
		case CREATE:
			informationParams.setURI(ProviderStatement.INFORMATION.CONTENT_URI);
			params.add(informationParams);
			messageParams.setURI(ProviderStatement.MESSAGE.CONTENT_URI);
			params.add(messageParams);
			textmessageParams.setURI(ProviderStatement.TEXT_MESSAGE.CONTENT_URI);
			params.add(textmessageParams);
			break;
		case UPDATE:
		case DELETE:
			informationParams.setURI(ProviderStatement.INFORMATION.CONTENT_URI);
			informationParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			informationParams.setSelectionArgs(new String[]{String.valueOf(((TextMessage) getBean()).getId())});
			params.add(informationParams);
			messageParams.setURI(ProviderStatement.MESSAGE.CONTENT_URI);
			messageParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			messageParams.setSelectionArgs(new String[]{String.valueOf(((TextMessage) getBean()).getId())});
			params.add(messageParams);
			textmessageParams.setURI(ProviderStatement.TEXT_MESSAGE.CONTENT_URI);
			textmessageParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			textmessageParams.setSelectionArgs(new String[]{String.valueOf(((TextMessage) getBean()).getId())});
			params.add(textmessageParams);
			break;
		case READ:
			textmessageParams.setURI(ProviderStatement.TEXT_MESSAGE.CONTENT_URI_READ);
			textmessageParams.setProjection(ProviderStatement.TEXT_MESSAGE.PROJECTION_ALL);
			textmessageParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			textmessageParams.setSelectionArgs(new String[]{String.valueOf(((TextMessage) getBean()).getId())});
			params.add(textmessageParams);
			break;
		case READ_ALL:
			textmessageParams.setURI(ProviderStatement.TEXT_MESSAGE.CONTENT_URI_READ);
			textmessageParams.setProjection(ProviderStatement.TEXT_MESSAGE.PROJECTION_ALL);
			params.add(textmessageParams);
			break;
		default:
			// Failed to get the Provider Parameters.
			String errorMsg = "Failed to get the Provider Parameters. Invalid operation type.";
	    	CustomLogger.getInstance().errorLog(TextMessageManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
		CustomLogger.getInstance().infoLog(TextMessageManager.class.getName(), "TextMessage Provider Parameters retrieved.");
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
			ContentValues informationValues = new ContentValues();
			// Note that ID Column is NOT included here because it is auto-incremented.
			informationValues.put(DBStatement.INFORMATION.COL_CREATIONDATE, ((TextMessage) getBean()).getCreationDateString());
			informationValues.put(DBStatement.INFORMATION.COL_LASTMODIFICATIONDATE, ((TextMessage) getBean()).getLastModificationDateString());
			informationValues.put(DBStatement.INFORMATION.COL_PRIORITY, ((TextMessage) getBean()).getPriorityString());
			informationValues.put(DBStatement.INFORMATION.COL_EMERGENCY_ID, String.valueOf(((TextMessage) getBean()).getEmergencyId()));
			contentValues.add(informationValues);

			ContentValues messageValues = new ContentValues();
			messageValues.put(BaseColumns._ID, ((TextMessage) getBean()).getId());
			messageValues.put(DBStatement.MESSAGE.COL_SUBJECT, ((TextMessage) getBean()).getSubject());
			messageValues.put(DBStatement.MESSAGE.COL_CONTENT, ((TextMessage) getBean()).getContent());
			contentValues.add(messageValues);

			ContentValues textmessageValues = new ContentValues();
			textmessageValues.put(BaseColumns._ID, ((TextMessage) getBean()).getId());
			contentValues.add(textmessageValues);
			CustomLogger.getInstance().infoLog(TextMessageManager.class.getName(), "TextMessage Content Values retrieved.");
		} catch (Exception e) {
			// Failed to get the Content Values.
			String errorMsg = "Failed to get the TextMessage Content Values.";
	    	CustomLogger.getInstance().errorLog(TextMessageManager.class.getName(), errorMsg);
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
			// Create a temporary TextMessage Object.
			TextMessage textmessageInfo = null;
			try {
				// Check if Object is already created.
				// Case affirmative, use it.
				if ((TextMessage) getBean() != null) {
					textmessageInfo = ((TextMessage) getBean());
				} else {
				// Otherwise, create a new one.
					textmessageInfo = new TextMessage();
				}
				// Get TextMessage Object ID from cursor.
				textmessageInfo.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
				// Get TextMessage Object creationDate from cursor.
				textmessageInfo.setCreationDateString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.INFORMATION.COL_CREATIONDATE)));
				// Get TextMessage Object lastModificationDate from cursor.
				textmessageInfo.setLastModificationDateString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.INFORMATION.COL_LASTMODIFICATIONDATE)));
				// Get TextMessage Object priority from cursor.
				textmessageInfo.setPriorityString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.INFORMATION.COL_PRIORITY)));
				// Get TextMessage Object Emergency ID from cursor.
				textmessageInfo.setEmergencyId(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.INFORMATION.COL_EMERGENCY_ID)));
				// Get TextMessage Object subject from cursor.
				textmessageInfo.setSubject(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.MESSAGE.COL_SUBJECT)));
				// Get TextMessage Object content from cursor.
				textmessageInfo.setContent(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.MESSAGE.COL_CONTENT)));
				objOut = textmessageInfo;
				CustomLogger.getInstance().infoLog(TextMessageManager.class.getName(), "TextMessage Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(TextMessageManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary TextMessage Object.
				textmessageInfo = null;
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
			String[] textmessageInfo = new String[cursor.getColumnCount()];
			// Create a temporary TextMessage Object.
			TextMessage textmessageObject = null;
			try {
				textmessageObject = (TextMessage) this.deserializeFromCursor(cursor);
				// Get TextMessage ID from TextMessage Object.
				textmessageInfo[cursor.getColumnIndexOrThrow(BaseColumns._ID)] = String.valueOf(textmessageObject.getId());
				// Get TextMessage creationDate from TextMessage Object.
				textmessageInfo[cursor.getColumnIndexOrThrow(DBStatement.INFORMATION.COL_CREATIONDATE)] = textmessageObject.getCreationDateString();
				// Get TextMessage lastModificationDate from TextMessage Object.
				textmessageInfo[cursor.getColumnIndexOrThrow(DBStatement.INFORMATION.COL_LASTMODIFICATIONDATE)] = textmessageObject.getLastModificationDateString();
				// Get TextMessage priority from TextMessage Object.
				textmessageInfo[cursor.getColumnIndexOrThrow(DBStatement.INFORMATION.COL_PRIORITY)] = textmessageObject.getPriorityString();
				// Get TextMessage Emergency ID from TextMessage Object.
				textmessageInfo[cursor.getColumnIndexOrThrow(DBStatement.INFORMATION.COL_EMERGENCY_ID)] = String.valueOf(textmessageObject.getEmergencyId());
				// Get TextMessage subject from TextMessage Object.
				textmessageInfo[cursor.getColumnIndexOrThrow(DBStatement.MESSAGE.COL_SUBJECT)] = textmessageObject.getSubject();
				// Get TextMessage content from TextMessage Object.
				textmessageInfo[cursor.getColumnIndexOrThrow(DBStatement.MESSAGE.COL_CONTENT)] = textmessageObject.getContent();
				StringArrayResult = textmessageInfo;
				CustomLogger.getInstance().infoLog(TextMessageManager.class.getName(), "TextMessage Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(TextMessageManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary TextMessage Object.
				textmessageObject = null;
				// Destroy the temporary StringArray Object.
				textmessageInfo = null;
			}
		}
		return StringArrayResult;
	}

}
