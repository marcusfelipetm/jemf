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
import br.ufrj.ppgi.jemf.mobile.bean.Equipment;
import br.ufrj.ppgi.jemf.mobile.database.DBStatement;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderParams;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderStatement;
import br.ufrj.ppgi.jemf.utils.Constraint;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class EquipmentManager extends Manager{

	/**
	 * Constructor.
	 */
	public EquipmentManager() {
    	CustomLogger.getInstance().infoLog(EquipmentManager.class.getName(), "Class started.");
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
					((Equipment) result).setId(Integer.valueOf(newUri.getLastPathSegment()));
			    	CustomLogger.getInstance().infoLog(EquipmentManager.class.getName(), "New Equipment Object ID retrieved.");
				} else {
					// Failed to get the Bean Object.
					String errorMsg = "Failed to set the new ID to the Bean Object.";
			    	CustomLogger.getInstance().errorLog(EquipmentManager.class.getName(), errorMsg);
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
						result.put(BaseColumns._ID, ((Equipment) getBean()).getId());
				    	CustomLogger.getInstance().infoLog(EquipmentManager.class.getName(), "Detail Record ID updated.");
					} else {
						// Failed to update the Detail content values.
						String errorMsg = "Failed to update the Detail content values.";
				    	CustomLogger.getInstance().errorLog(EquipmentManager.class.getName(), errorMsg);
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
				// Get Equipment Object by the cursor deserialization.
				result = this.deserializeFromCursor(cursor);
		    	CustomLogger.getInstance().infoLog(EquipmentManager.class.getName(), "Single record fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(EquipmentManager.class.getName(), "Failed to fetch single record.");
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
					// Add new Equipment Object by the cursor deserialization.
					result.add(this.deserializeFromCursor(cursor));
					// Move cursor to next element.
					cursor.moveToNext();
				}
		    	CustomLogger.getInstance().infoLog(EquipmentManager.class.getName(), "All records fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(EquipmentManager.class.getName(), "Failed to fetch all records.");
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
		// Check if Bean Object is an Equipment Object.
		if (!(bean instanceof Equipment)) {
			// Failed to get the Equipment Object.
			String errorMsg = "Failed to get the Equipment Object. The given Bean Object is not an Equipment Object.";
	    	CustomLogger.getInstance().errorLog(EquipmentManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
    	CustomLogger.getInstance().infoLog(EquipmentManager.class.getName(), "Bean Object checked.");
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
		ProviderParams resourceParams = new ProviderParams();
		ProviderParams equipmentParams = new ProviderParams();
		// Get the parameters by the operation type. 
		switch (operationType) {
		case CREATE:
			resourceParams.setURI(ProviderStatement.RESOURCE.CONTENT_URI);
			params.add(resourceParams);
			equipmentParams.setURI(ProviderStatement.EQUIPMENT.CONTENT_URI);
			params.add(equipmentParams);
			break;
		case UPDATE:
		case DELETE:
			resourceParams.setURI(ProviderStatement.RESOURCE.CONTENT_URI);
			resourceParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			resourceParams.setSelectionArgs(new String[]{String.valueOf(((Equipment) getBean()).getId())});
			params.add(resourceParams);
			equipmentParams.setURI(ProviderStatement.EQUIPMENT.CONTENT_URI);
			equipmentParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			equipmentParams.setSelectionArgs(new String[]{String.valueOf(((Equipment) getBean()).getId())});
			params.add(equipmentParams);
			break;
		case READ:
			equipmentParams.setURI(ProviderStatement.EQUIPMENT.CONTENT_URI_READ);
			equipmentParams.setProjection(ProviderStatement.EQUIPMENT.PROJECTION_ALL);
			equipmentParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			equipmentParams.setSelectionArgs(new String[]{String.valueOf(((Equipment) getBean()).getId())});
			params.add(equipmentParams);
			break;
		case READ_ALL:
			equipmentParams.setURI(ProviderStatement.EQUIPMENT.CONTENT_URI_READ);
			equipmentParams.setProjection(ProviderStatement.EQUIPMENT.PROJECTION_ALL);
			params.add(equipmentParams);
			break;
		default:
			// Failed to get the Provider Parameters.
			String errorMsg = "Failed to get the Provider Parameters. Invalid operation type.";
	    	CustomLogger.getInstance().errorLog(EquipmentManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
		CustomLogger.getInstance().infoLog(EquipmentManager.class.getName(), "Equipment Provider Parameters retrieved.");
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
			ContentValues resourceValues = new ContentValues();
			// Note that ID Column is NOT included here because it is auto-incremented.
			resourceValues.put(DBStatement.RESOURCE.COL_NAME, ((Equipment) getBean()).getName());
			resourceValues.put(DBStatement.RESOURCE.COL_DESCRIPTION, ((Equipment) getBean()).getDescription());
			resourceValues.put(DBStatement.RESOURCE.COL_STATUS, ((Equipment) getBean()).getStatusString());
			contentValues.add(resourceValues);

			ContentValues equipmentValues = new ContentValues();
			equipmentValues.put(BaseColumns._ID, ((Equipment) getBean()).getId());
			contentValues.add(equipmentValues);
			CustomLogger.getInstance().infoLog(EquipmentManager.class.getName(), "Equipment Content Values retrieved.");
		} catch (Exception e) {
			// Failed to get the Content Values.
			String errorMsg = "Failed to get the Equipment Content Values.";
	    	CustomLogger.getInstance().errorLog(EquipmentManager.class.getName(), errorMsg);
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
			// Create a temporary Equipment Object.
			Equipment equipmentInfo = null;
			try {
				// Check if Object is already created.
				// Case affirmative, use it.
				if ((Equipment) getBean() != null) {
					equipmentInfo = ((Equipment) getBean());
				} else {
				// Otherwise, create a new one.
					equipmentInfo = new Equipment();
				}
				// Get Equipment Object ID from cursor.
				equipmentInfo.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
				// Get Equipment Object name from cursor.
				equipmentInfo.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.RESOURCE.COL_NAME)));
				// Get Equipment Object description from cursor.
				equipmentInfo.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.RESOURCE.COL_DESCRIPTION)));
				// Get Equipment Object status from cursor.
				equipmentInfo.setStatusString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.RESOURCE.COL_STATUS)));
				objOut = equipmentInfo;
				CustomLogger.getInstance().infoLog(EquipmentManager.class.getName(), "Equipment Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(EquipmentManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary Equipment Object.
				equipmentInfo = null;
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
			String[] equipmentInfo = new String[cursor.getColumnCount()];
			// Create a temporary Equipment Object.
			Equipment equipmentObject = null;
			try {
				equipmentObject = (Equipment) this.deserializeFromCursor(cursor);
				// Get Equipment ID from Equipment Object.
				equipmentInfo[cursor.getColumnIndexOrThrow(BaseColumns._ID)] = String.valueOf(equipmentObject.getId());
				// Get Equipment name from Equipment Object.
				equipmentInfo[cursor.getColumnIndexOrThrow(DBStatement.RESOURCE.COL_NAME)] = equipmentObject.getName();
				// Get Equipment description from Equipment Object.
				equipmentInfo[cursor.getColumnIndexOrThrow(DBStatement.RESOURCE.COL_DESCRIPTION)] = equipmentObject.getDescription();
				// Get Equipment status from Equipment Object.
				equipmentInfo[cursor.getColumnIndexOrThrow(DBStatement.RESOURCE.COL_STATUS)] = equipmentObject.getStatusString();
				StringArrayResult = equipmentInfo;
				CustomLogger.getInstance().infoLog(EquipmentManager.class.getName(), "Equipment Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(EquipmentManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary Equipment Object.
				equipmentObject = null;
				// Destroy the temporary StringArray Object.
				equipmentInfo = null;
			}
		}
		return StringArrayResult;
	}

}
