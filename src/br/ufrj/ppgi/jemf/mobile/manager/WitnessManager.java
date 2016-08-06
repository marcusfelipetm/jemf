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
import br.ufrj.ppgi.jemf.mobile.bean.Witness;
import br.ufrj.ppgi.jemf.mobile.database.DBStatement;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderParams;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderStatement;
import br.ufrj.ppgi.jemf.utils.Constraint;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class WitnessManager extends Manager {

	/**
	 * Constructor.
	 */
	public WitnessManager() {
    	CustomLogger.getInstance().infoLog(WitnessManager.class.getName(), "Class started.");
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
					((Witness) result).setId(Integer.valueOf(newUri.getLastPathSegment()));
			    	CustomLogger.getInstance().infoLog(WitnessManager.class.getName(), "New Witness Object ID retrieved.");
				} else {
					// Failed to get the Bean Object.
					String errorMsg = "Failed to set the new ID to the Bean Object.";
			    	CustomLogger.getInstance().errorLog(WitnessManager.class.getName(), errorMsg);
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
						result.put(BaseColumns._ID, ((Witness) getBean()).getId());
				    	CustomLogger.getInstance().infoLog(WitnessManager.class.getName(), "Detail Record ID updated.");
					} else {
						// Failed to update the Detail content values.
						String errorMsg = "Failed to update the Detail content values.";
				    	CustomLogger.getInstance().errorLog(WitnessManager.class.getName(), errorMsg);
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
				// Get Witness Object by the cursor deserialization.
				result = this.deserializeFromCursor(cursor);
		    	CustomLogger.getInstance().infoLog(WitnessManager.class.getName(), "Single record fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(WitnessManager.class.getName(), "Failed to fetch single record.");
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
					// Add new Witness Object by the cursor deserialization.
					result.add(this.deserializeFromCursor(cursor));
					// Move cursor to next element.
					cursor.moveToNext();
				}
		    	CustomLogger.getInstance().infoLog(WitnessManager.class.getName(), "All records fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(WitnessManager.class.getName(), "Failed to fetch all records.");
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
		// Check if Bean Object is an Witness Object.
		if (!(bean instanceof Witness)) {
			// Failed to get the Witness Object.
			String errorMsg = "Failed to get the Witness Object. The given Bean Object is not an Witness Object.";
	    	CustomLogger.getInstance().errorLog(WitnessManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
    	CustomLogger.getInstance().infoLog(WitnessManager.class.getName(), "Bean Object checked.");
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
		ProviderParams witnessParams = new ProviderParams();
		// Get the parameters by the operation type. 
		switch (operationType) {
		case CREATE:
			entityParams.setURI(ProviderStatement.ENTITY.CONTENT_URI);
			params.add(entityParams);
			personParams.setURI(ProviderStatement.PERSON.CONTENT_URI);
			params.add(personParams);
			witnessParams.setURI(ProviderStatement.WITNESS.CONTENT_URI);
			params.add(witnessParams);
			break;
		case UPDATE:
		case DELETE:
			entityParams.setURI(ProviderStatement.ENTITY.CONTENT_URI);
			entityParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			entityParams.setSelectionArgs(new String[]{String.valueOf(((Witness) getBean()).getId())});
			params.add(entityParams);
			personParams.setURI(ProviderStatement.PERSON.CONTENT_URI);
			personParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			personParams.setSelectionArgs(new String[]{String.valueOf(((Witness) getBean()).getId())});
			params.add(personParams);
			witnessParams.setURI(ProviderStatement.WITNESS.CONTENT_URI);
			witnessParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			witnessParams.setSelectionArgs(new String[]{String.valueOf(((Witness) getBean()).getId())});
			params.add(witnessParams);
			break;
		case READ:
			witnessParams.setURI(ProviderStatement.WITNESS.CONTENT_URI_READ);
			witnessParams.setProjection(ProviderStatement.WITNESS.PROJECTION_ALL);
			witnessParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			witnessParams.setSelectionArgs(new String[]{String.valueOf(((Witness) getBean()).getId())});
			params.add(witnessParams);
			break;
		case READ_ALL:
			witnessParams.setURI(ProviderStatement.WITNESS.CONTENT_URI_READ);
			witnessParams.setProjection(ProviderStatement.WITNESS.PROJECTION_ALL);
			params.add(witnessParams);
			break;
		default:
			// Failed to get the Provider Parameters.
			String errorMsg = "Failed to get the Provider Parameters. Invalid operation type.";
	    	CustomLogger.getInstance().errorLog(WitnessManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
		CustomLogger.getInstance().infoLog(WitnessManager.class.getName(), "Witness Provider Parameters retrieved.");
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
			entityValues.put(DBStatement.ENTITY.COL_NAME, ((Witness) getBean()).getName());
			entityValues.put(DBStatement.ENTITY.COL_LOCATION_ID, String.valueOf(((Witness) getBean()).getLocationId()));
			contentValues.add(entityValues);

			ContentValues personValues = new ContentValues();
			personValues.put(BaseColumns._ID, ((Witness) getBean()).getId());
			personValues.put(DBStatement.PERSON.COL_GENDER, ((Witness) getBean()).getGender());
			personValues.put(DBStatement.PERSON.COL_BIRTHDATE, ((Witness) getBean()).getBirthDateString());
			personValues.put(DBStatement.PERSON.COL_AGE, ((Witness) getBean()).getAge());
			contentValues.add(personValues);

			ContentValues witnessValues = new ContentValues();
			witnessValues.put(BaseColumns._ID, ((Witness) getBean()).getId());
			contentValues.add(witnessValues);
			CustomLogger.getInstance().infoLog(WitnessManager.class.getName(), "Witness Content Values retrieved.");
		} catch (Exception e) {
			// Failed to get the Content Values.
			String errorMsg = "Failed to get the Witness Content Values.";
	    	CustomLogger.getInstance().errorLog(WitnessManager.class.getName(), errorMsg);
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
			// Create a temporary Witness Object.
			Witness witnessInfo = null;
			try {
				// Check if Object is already created.
				// Case affirmative, use it.
				if ((Witness) getBean() != null) {
					witnessInfo = ((Witness) getBean());
				} else {
				// Otherwise, create a new one.
					witnessInfo = new Witness();
				}
				// Get Witness Object ID from cursor.
				witnessInfo.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
				// Get Witness Object name from cursor.
				witnessInfo.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_NAME)));
				// Get Witness Object Location ID from cursor.
				witnessInfo.setLocationId(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_LOCATION_ID)));
				// Get Witness Object gender from cursor.
				witnessInfo.setGender(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_GENDER)));
				// Get Witness Object birth date from cursor.
				witnessInfo.setBirthDateString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_BIRTHDATE)));
				// Get Witness Object age from cursor.
				witnessInfo.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_AGE)));
				objOut = witnessInfo;
				CustomLogger.getInstance().infoLog(WitnessManager.class.getName(), "Witness Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(WitnessManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary Witness Object.
				witnessInfo = null;
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
			String[] witnessInfo = new String[cursor.getColumnCount()];
			// Create a temporary Witness Object.
			Witness witnessObject = null;
			try {
				witnessObject = (Witness) this.deserializeFromCursor(cursor);
				// Get Witness ID from Witness Object.
				witnessInfo[cursor.getColumnIndexOrThrow(BaseColumns._ID)] = String.valueOf(witnessObject.getId());
				// Get Witness name from Witness Object.
				witnessInfo[cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_NAME)] = witnessObject.getName();
				// Get Witness Location ID from Witness Object.
				witnessInfo[cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_LOCATION_ID)] = String.valueOf(witnessObject.getLocationId());
				// Get Witness gender from Witness Object.
				witnessInfo[cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_GENDER)] = String.valueOf(witnessObject.getGender());
				// Get Witness birth date from Witness Object.
				witnessInfo[cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_BIRTHDATE)] = witnessObject.getBirthDateString();
				// Get Witness age from Witness Object.
				witnessInfo[cursor.getColumnIndexOrThrow(DBStatement.PERSON.COL_AGE)] = String.valueOf(witnessObject.getAge());
				StringArrayResult = witnessInfo;
				CustomLogger.getInstance().infoLog(WitnessManager.class.getName(), "Witness Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(WitnessManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary Witness Object.
				witnessObject = null;
				// Destroy the temporary StringArray Object.
				witnessInfo = null;
			}
		}
		return StringArrayResult;
	}

}
