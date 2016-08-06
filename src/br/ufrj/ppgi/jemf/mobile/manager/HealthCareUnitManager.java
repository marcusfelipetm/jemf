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
import br.ufrj.ppgi.jemf.mobile.bean.HealthCareUnit;
import br.ufrj.ppgi.jemf.mobile.database.DBStatement;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderParams;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderStatement;
import br.ufrj.ppgi.jemf.utils.Constraint;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class HealthCareUnitManager extends Manager {

	/**
	 * Constructor.
	 */
	public HealthCareUnitManager() {
    	CustomLogger.getInstance().infoLog(HealthCareUnitManager.class.getName(), "Class started.");
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
					((HealthCareUnit) result).setId(Integer.valueOf(newUri.getLastPathSegment()));
			    	CustomLogger.getInstance().infoLog(HealthCareUnitManager.class.getName(), "New HealthCareUnit Object ID retrieved.");
				} else {
					// Failed to get the Bean Object.
					String errorMsg = "Failed to set the new ID to the Bean Object.";
			    	CustomLogger.getInstance().errorLog(HealthCareUnitManager.class.getName(), errorMsg);
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
						result.put(BaseColumns._ID, ((HealthCareUnit) getBean()).getId());
				    	CustomLogger.getInstance().infoLog(HealthCareUnitManager.class.getName(), "Detail Record ID updated.");
					} else {
						// Failed to update the Detail content values.
						String errorMsg = "Failed to update the Detail content values.";
				    	CustomLogger.getInstance().errorLog(HealthCareUnitManager.class.getName(), errorMsg);
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
				// Get HealthCareUnit Object by the cursor deserialization.
				result = this.deserializeFromCursor(cursor);
		    	CustomLogger.getInstance().infoLog(HealthCareUnitManager.class.getName(), "Single record fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(HealthCareUnitManager.class.getName(), "Failed to fetch single record.");
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
					// Add new HealthCareUnit Object by the cursor deserialization.
					result.add(this.deserializeFromCursor(cursor));
					// Move cursor to next element.
					cursor.moveToNext();
				}
		    	CustomLogger.getInstance().infoLog(HealthCareUnitManager.class.getName(), "All records fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(HealthCareUnitManager.class.getName(), "Failed to fetch all records.");
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
		// Check if Bean Object is an HealthCareUnit Object.
		if (!(bean instanceof HealthCareUnit)) {
			// Failed to get the HealthCareUnit Object.
			String errorMsg = "Failed to get the HealthCareUnit Object. The given Bean Object is not an HealthCareUnit Object.";
	    	CustomLogger.getInstance().errorLog(HealthCareUnitManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
    	CustomLogger.getInstance().infoLog(HealthCareUnitManager.class.getName(), "Bean Object checked.");
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
		ProviderParams organizationParams = new ProviderParams();
		ProviderParams healthcareunitParams = new ProviderParams();
		// Get the parameters by the operation type. 
		switch (operationType) {
		case CREATE:
			entityParams.setURI(ProviderStatement.ENTITY.CONTENT_URI);
			params.add(entityParams);
			organizationParams.setURI(ProviderStatement.ORGANIZATION.CONTENT_URI);
			params.add(organizationParams);
			healthcareunitParams.setURI(ProviderStatement.HEALTH_CARE_UNIT.CONTENT_URI);
			params.add(healthcareunitParams);
			break;
		case UPDATE:
		case DELETE:
			entityParams.setURI(ProviderStatement.ENTITY.CONTENT_URI);
			entityParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			entityParams.setSelectionArgs(new String[]{String.valueOf(((HealthCareUnit) getBean()).getId())});
			params.add(entityParams);
			organizationParams.setURI(ProviderStatement.ORGANIZATION.CONTENT_URI);
			organizationParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			organizationParams.setSelectionArgs(new String[]{String.valueOf(((HealthCareUnit) getBean()).getId())});
			params.add(organizationParams);
			healthcareunitParams.setURI(ProviderStatement.HEALTH_CARE_UNIT.CONTENT_URI);
			healthcareunitParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			healthcareunitParams.setSelectionArgs(new String[]{String.valueOf(((HealthCareUnit) getBean()).getId())});
			params.add(healthcareunitParams);
			break;
		case READ:	
			healthcareunitParams.setURI(ProviderStatement.HEALTH_CARE_UNIT.CONTENT_URI_READ);
			healthcareunitParams.setProjection(ProviderStatement.HEALTH_CARE_UNIT.PROJECTION_ALL);
			healthcareunitParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			healthcareunitParams.setSelectionArgs(new String[]{String.valueOf(((HealthCareUnit) getBean()).getId())});
			params.add(healthcareunitParams);
			break;
		case READ_ALL:
			healthcareunitParams.setURI(ProviderStatement.HEALTH_CARE_UNIT.CONTENT_URI_READ);
			healthcareunitParams.setProjection(ProviderStatement.HEALTH_CARE_UNIT.PROJECTION_ALL);
			params.add(healthcareunitParams);
			break;
		default:
			// Failed to get the Provider Parameters.
			String errorMsg = "Failed to get the Provider Parameters. Invalid operation type.";
	    	CustomLogger.getInstance().errorLog(HealthCareUnitManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
		CustomLogger.getInstance().infoLog(HealthCareUnitManager.class.getName(), "HealthCareUnit Provider Parameters retrieved.");
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
			entityValues.put(DBStatement.ENTITY.COL_NAME, ((HealthCareUnit) getBean()).getName());
			entityValues.put(DBStatement.ENTITY.COL_LOCATION_ID, String.valueOf(((HealthCareUnit) getBean()).getLocationId()));
			contentValues.add(entityValues);

			ContentValues organizationValues = new ContentValues();
			organizationValues.put(BaseColumns._ID, ((HealthCareUnit) getBean()).getId());
			organizationValues.put(DBStatement.ORGANIZATION.COL_DESCRIPTION, ((HealthCareUnit) getBean()).getDescription());
			organizationValues.put(DBStatement.ORGANIZATION.COL_LEVEL, ((HealthCareUnit) getBean()).getLevelString());
			contentValues.add(organizationValues);

			ContentValues healthcareunitValues = new ContentValues();
			healthcareunitValues.put(BaseColumns._ID, ((HealthCareUnit) getBean()).getId());
			healthcareunitValues.put(DBStatement.HEALTH_CARE_UNIT.COL_CAPACITY, ((HealthCareUnit) getBean()).getCapacity());
			healthcareunitValues.put(DBStatement.HEALTH_CARE_UNIT.COL_COMMANDER_ID, String.valueOf(((HealthCareUnit) getBean()).getCommanderId()));
			healthcareunitValues.put(DBStatement.HEALTH_CARE_UNIT.COL_MEDICAL_ID, String.valueOf(((HealthCareUnit) getBean()).getMedicalId()));
			healthcareunitValues.put(DBStatement.HEALTH_CARE_UNIT.COL_INTERESTPOINT_ID, String.valueOf(((HealthCareUnit) getBean()).getInterestPointId()));
			contentValues.add(healthcareunitValues);
			CustomLogger.getInstance().infoLog(HealthCareUnitManager.class.getName(), "HealthCareUnit Content Values retrieved.");
		} catch (Exception e) {
			// Failed to get the Content Values.
			String errorMsg = "Failed to get the HealthCareUnit Content Values.";
	    	CustomLogger.getInstance().errorLog(HealthCareUnitManager.class.getName(), errorMsg);
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
			// Create a temporary HealthCareUnit Object.
			HealthCareUnit healthcareunitInfo = null;
			try {
				// Check if Object is already created.
				// Case affirmative, use it.
				if ((HealthCareUnit) getBean() != null) {
					healthcareunitInfo = ((HealthCareUnit) getBean());
				} else {
				// Otherwise, create a new one.
					healthcareunitInfo = new HealthCareUnit();
				}
				// Get HealthCareUnit Object ID from cursor.
				healthcareunitInfo.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
				// Get HealthCareUnit Object name from cursor.
				healthcareunitInfo.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_NAME)));
				// Get HealthCareUnit Object Location ID from cursor.
				healthcareunitInfo.setLocationId(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_LOCATION_ID)));
				// Get HealthCareUnit Object description from cursor.
				healthcareunitInfo.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.ORGANIZATION.COL_DESCRIPTION)));
				// Get HealthCareUnit Object level from cursor.
				healthcareunitInfo.setLevelString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.ORGANIZATION.COL_LEVEL)));
				// Get HealthCareUnit Object capacity from cursor.
				healthcareunitInfo.setCapacity(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.HEALTH_CARE_UNIT.COL_CAPACITY)));
				// Get HealthCareUnit Object Commander ID from cursor.
				healthcareunitInfo.setCommanderId(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.HEALTH_CARE_UNIT.COL_COMMANDER_ID)));
				// Get HealthCareUnit Object Medical ID from cursor.
				healthcareunitInfo.setMedicalId(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.HEALTH_CARE_UNIT.COL_MEDICAL_ID)));
				// Get HealthCareUnit Object InterestPoint ID from cursor.
				healthcareunitInfo.setInterestPointId(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.HEALTH_CARE_UNIT.COL_INTERESTPOINT_ID)));
				objOut = healthcareunitInfo;
				CustomLogger.getInstance().infoLog(HealthCareUnitManager.class.getName(), "HealthCareUnit Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(HealthCareUnitManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary HealthCareUnit Object.
				healthcareunitInfo = null;
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
			String[] healthcareunitInfo = new String[cursor.getColumnCount()];
			// Create a temporary HealthCareUnit Object.
			HealthCareUnit healthcareunitObject = null;
			try {
				healthcareunitObject = (HealthCareUnit) this.deserializeFromCursor(cursor);
				// Get HealthCareUnit ID from HealthCareUnit Object.
				healthcareunitInfo[cursor.getColumnIndexOrThrow(BaseColumns._ID)] = String.valueOf(healthcareunitObject.getId());
				// Get HealthCareUnit name from HealthCareUnit Object.
				healthcareunitInfo[cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_NAME)] = healthcareunitObject.getName();
				// Get HealthCareUnit Location ID from HealthCareUnit Object.
				healthcareunitInfo[cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_LOCATION_ID)] = String.valueOf(healthcareunitObject.getLocationId());
				// Get HealthCareUnit description from HealthCareUnit Object.
				healthcareunitInfo[cursor.getColumnIndexOrThrow(DBStatement.ORGANIZATION.COL_DESCRIPTION)] = healthcareunitObject.getDescription();
				// Get HealthCareUnit level from HealthCareUnit Object.
				healthcareunitInfo[cursor.getColumnIndexOrThrow(DBStatement.ORGANIZATION.COL_LEVEL)] = healthcareunitObject.getLevelString();
				// Get HealthCareUnit capacity from HealthCareUnit Object.
				healthcareunitInfo[cursor.getColumnIndexOrThrow(DBStatement.HEALTH_CARE_UNIT.COL_CAPACITY)] = String.valueOf(healthcareunitObject.getCapacity());
				// Get HealthCareUnit Commander ID from HealthCareUnit Object.
				healthcareunitInfo[cursor.getColumnIndexOrThrow(DBStatement.HEALTH_CARE_UNIT.COL_COMMANDER_ID)] = String.valueOf(healthcareunitObject.getCommanderId());
				// Get HealthCareUnit Medical ID from HealthCareUnit Object.
				healthcareunitInfo[cursor.getColumnIndexOrThrow(DBStatement.HEALTH_CARE_UNIT.COL_MEDICAL_ID)] = String.valueOf(healthcareunitObject.getMedicalId());
				// Get HealthCareUnit InterestPoint ID from HealthCareUnit Object.
				healthcareunitInfo[cursor.getColumnIndexOrThrow(DBStatement.HEALTH_CARE_UNIT.COL_INTERESTPOINT_ID)] = String.valueOf(healthcareunitObject.getInterestPointId());
				StringArrayResult = healthcareunitInfo;
				CustomLogger.getInstance().infoLog(HealthCareUnitManager.class.getName(), "HealthCareUnit Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(HealthCareUnitManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary HealthCareUnit Object.
				healthcareunitObject = null;
				// Destroy the temporary StringArray Object.
				healthcareunitInfo = null;
			}
		}
		return StringArrayResult;
	}

}
