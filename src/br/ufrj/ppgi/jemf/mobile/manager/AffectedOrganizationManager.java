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
import br.ufrj.ppgi.jemf.mobile.bean.AffectedOrganization;
import br.ufrj.ppgi.jemf.mobile.database.DBStatement;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderParams;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderStatement;
import br.ufrj.ppgi.jemf.utils.Constraint;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class AffectedOrganizationManager extends Manager {

	/**
	 * Constructor.
	 */
	public AffectedOrganizationManager() {
    	CustomLogger.getInstance().infoLog(AffectedOrganizationManager.class.getName(), "Class started.");
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
					((AffectedOrganization) result).setId(Integer.valueOf(newUri.getLastPathSegment()));
			    	CustomLogger.getInstance().infoLog(AffectedOrganizationManager.class.getName(), "New AffectedOrganization Object ID retrieved.");
				} else {
					// Failed to get the Bean Object.
					String errorMsg = "Failed to set the new ID to the Bean Object.";
			    	CustomLogger.getInstance().errorLog(AffectedOrganizationManager.class.getName(), errorMsg);
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
						result.put(BaseColumns._ID, ((AffectedOrganization) getBean()).getId());
				    	CustomLogger.getInstance().infoLog(AffectedOrganizationManager.class.getName(), "Detail Record ID updated.");
					} else {
						// Failed to update the Detail content values.
						String errorMsg = "Failed to update the Detail content values.";
				    	CustomLogger.getInstance().errorLog(AffectedOrganizationManager.class.getName(), errorMsg);
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
				// Get AffectedOrganization Object by the cursor deserialization.
				result = this.deserializeFromCursor(cursor);
		    	CustomLogger.getInstance().infoLog(AffectedOrganizationManager.class.getName(), "Single record fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(AffectedOrganizationManager.class.getName(), "Failed to fetch single record.");
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
					// Add new AffectedOrganization Object by the cursor deserialization.
					result.add(this.deserializeFromCursor(cursor));
					// Move cursor to next element.
					cursor.moveToNext();
				}
		    	CustomLogger.getInstance().infoLog(AffectedOrganizationManager.class.getName(), "All records fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(AffectedOrganizationManager.class.getName(), "Failed to fetch all records.");
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
		// Check if Bean Object is an AffectedOrganization Object.
		if (!(bean instanceof AffectedOrganization)) {
			// Failed to get the AffectedOrganization Object.
			String errorMsg = "Failed to get the AffectedOrganization Object. The given Bean Object is not an AffectedOrganization Object.";
	    	CustomLogger.getInstance().errorLog(AffectedOrganizationManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
    	CustomLogger.getInstance().infoLog(AffectedOrganizationManager.class.getName(), "Bean Object checked.");
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
		ProviderParams affectedorganizationParams = new ProviderParams();
		// Get the parameters by the operation type. 
		switch (operationType) {
		case CREATE:
			entityParams.setURI(ProviderStatement.ENTITY.CONTENT_URI);
			params.add(entityParams);
			organizationParams.setURI(ProviderStatement.ORGANIZATION.CONTENT_URI);
			params.add(organizationParams);
			affectedorganizationParams.setURI(ProviderStatement.AFFECTED_ORGANIZATION.CONTENT_URI);
			params.add(affectedorganizationParams);
			break;
		case UPDATE:
		case DELETE:
			entityParams.setURI(ProviderStatement.ENTITY.CONTENT_URI);
			entityParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			entityParams.setSelectionArgs(new String[]{String.valueOf(((AffectedOrganization) getBean()).getId())});
			params.add(entityParams);
			organizationParams.setURI(ProviderStatement.ORGANIZATION.CONTENT_URI);
			organizationParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			organizationParams.setSelectionArgs(new String[]{String.valueOf(((AffectedOrganization) getBean()).getId())});
			params.add(organizationParams);
			affectedorganizationParams.setURI(ProviderStatement.AFFECTED_ORGANIZATION.CONTENT_URI);
			affectedorganizationParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			affectedorganizationParams.setSelectionArgs(new String[]{String.valueOf(((AffectedOrganization) getBean()).getId())});
			params.add(affectedorganizationParams);
			break;
		case READ:
			affectedorganizationParams.setURI(ProviderStatement.AFFECTED_ORGANIZATION.CONTENT_URI_READ);
			affectedorganizationParams.setProjection(ProviderStatement.AFFECTED_ORGANIZATION.PROJECTION_ALL);
			affectedorganizationParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			affectedorganizationParams.setSelectionArgs(new String[]{String.valueOf(((AffectedOrganization) getBean()).getId())});
			params.add(affectedorganizationParams);
			break;
		case READ_ALL:
			affectedorganizationParams.setURI(ProviderStatement.AFFECTED_ORGANIZATION.CONTENT_URI_READ);
			affectedorganizationParams.setProjection(ProviderStatement.AFFECTED_ORGANIZATION.PROJECTION_ALL);
			params.add(affectedorganizationParams);
			break;
		default:
			// Failed to get the Provider Parameters.
			String errorMsg = "Failed to get the Provider Parameters. Invalid operation type.";
	    	CustomLogger.getInstance().errorLog(AffectedOrganizationManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
		CustomLogger.getInstance().infoLog(AffectedOrganizationManager.class.getName(), "AffectedOrganization Provider Parameters retrieved.");
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
			entityValues.put(DBStatement.ENTITY.COL_NAME, ((AffectedOrganization) getBean()).getName());
			entityValues.put(DBStatement.ENTITY.COL_LOCATION_ID, String.valueOf(((AffectedOrganization) getBean()).getLocationId()));
			contentValues.add(entityValues);

			ContentValues organizationValues = new ContentValues();
			organizationValues.put(BaseColumns._ID, ((AffectedOrganization) getBean()).getId());
			organizationValues.put(DBStatement.ORGANIZATION.COL_DESCRIPTION, ((AffectedOrganization) getBean()).getDescription());
			organizationValues.put(DBStatement.ORGANIZATION.COL_LEVEL, ((AffectedOrganization) getBean()).getLevelString());
			contentValues.add(organizationValues);

			ContentValues affectedorganizationValues = new ContentValues();
			affectedorganizationValues.put(BaseColumns._ID, ((AffectedOrganization) getBean()).getId());
			affectedorganizationValues.put(DBStatement.AFFECTED_ORGANIZATION.COL_STATUS, ((AffectedOrganization) getBean()).getStatusString());
			affectedorganizationValues.put(DBStatement.AFFECTED_ORGANIZATION.COL_WITNESS_ID, String.valueOf(((AffectedOrganization) getBean()).getWitnessId()));
			affectedorganizationValues.put(DBStatement.AFFECTED_ORGANIZATION.COL_INTERESTPOINT_ID, String.valueOf(((AffectedOrganization) getBean()).getInterestPointId()));
			contentValues.add(affectedorganizationValues);
			CustomLogger.getInstance().infoLog(AffectedOrganizationManager.class.getName(), "AffectedOrganization Content Values retrieved.");
		} catch (Exception e) {
			// Failed to get the Content Values.
			String errorMsg = "Failed to get the AffectedOrganization Content Values.";
	    	CustomLogger.getInstance().errorLog(AffectedOrganizationManager.class.getName(), errorMsg);
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
			// Create a temporary AffectedOrganization Object.
			AffectedOrganization affectedorganizationInfo = null;
			try {
				// Check if Object is already created.
				// Case affirmative, use it.
				if ((AffectedOrganization) getBean() != null) {
					affectedorganizationInfo = ((AffectedOrganization) getBean());
				} else {
				// Otherwise, create a new one.
					affectedorganizationInfo = new AffectedOrganization();
				}
				// Get AffectedOrganization Object ID from cursor.
				affectedorganizationInfo.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
				// Get AffectedOrganization Object name from cursor.
				affectedorganizationInfo.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_NAME)));
				// Get AffectedOrganization Object Location ID from cursor.
				affectedorganizationInfo.setLocationId(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_LOCATION_ID)));
				// Get AffectedOrganization Object description from cursor.
				affectedorganizationInfo.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.ORGANIZATION.COL_DESCRIPTION)));
				// Get AffectedOrganization Object level from cursor.
				affectedorganizationInfo.setLevelString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.ORGANIZATION.COL_LEVEL)));
				// Get AffectedOrganization Object status from cursor.
				affectedorganizationInfo.setStatusString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.AFFECTED_ORGANIZATION.COL_STATUS)));
				// Get AffectedOrganization Object Witness ID from cursor.
				affectedorganizationInfo.setWitnessId(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.AFFECTED_ORGANIZATION.COL_WITNESS_ID)));
				// Get AffectedOrganization Object InterestPoint ID from cursor.
				affectedorganizationInfo.setInterestPointId(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.AFFECTED_ORGANIZATION.COL_INTERESTPOINT_ID)));
				objOut = affectedorganizationInfo;
				CustomLogger.getInstance().infoLog(AffectedOrganizationManager.class.getName(), "AffectedOrganization Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(AffectedOrganizationManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary AffectedOrganization Object.
				affectedorganizationInfo = null;
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
			String[] affectedorganizationInfo = new String[cursor.getColumnCount()];
			// Create a temporary AffectedOrganization Object.
			AffectedOrganization affectedorganizationObject = null;
			try {
				affectedorganizationObject = (AffectedOrganization) this.deserializeFromCursor(cursor);
				// Get AffectedOrganization ID from AffectedOrganization Object.
				affectedorganizationInfo[cursor.getColumnIndexOrThrow(BaseColumns._ID)] = String.valueOf(affectedorganizationObject.getId());
				// Get AffectedOrganization name from AffectedOrganization Object.
				affectedorganizationInfo[cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_NAME)] = affectedorganizationObject.getName();
				// Get AffectedOrganization Location ID from AffectedOrganization Object.
				affectedorganizationInfo[cursor.getColumnIndexOrThrow(DBStatement.ENTITY.COL_LOCATION_ID)] = String.valueOf(affectedorganizationObject.getLocationId());
				// Get AffectedOrganization description from AffectedOrganization Object.
				affectedorganizationInfo[cursor.getColumnIndexOrThrow(DBStatement.ORGANIZATION.COL_DESCRIPTION)] = affectedorganizationObject.getDescription();
				// Get AffectedOrganization level from AffectedOrganization Object.
				affectedorganizationInfo[cursor.getColumnIndexOrThrow(DBStatement.ORGANIZATION.COL_LEVEL)] = affectedorganizationObject.getLevelString();
				// Get AffectedOrganization status from AffectedOrganization Object.
				affectedorganizationInfo[cursor.getColumnIndexOrThrow(DBStatement.AFFECTED_ORGANIZATION.COL_STATUS)] = String.valueOf(affectedorganizationObject.getStatusString());
				// Get AffectedOrganization Witness ID from AffectedOrganization Object.
				affectedorganizationInfo[cursor.getColumnIndexOrThrow(DBStatement.AFFECTED_ORGANIZATION.COL_WITNESS_ID)] = String.valueOf(affectedorganizationObject.getWitnessId());
				// Get AffectedOrganization InterestPoint ID from AffectedOrganization Object.
				affectedorganizationInfo[cursor.getColumnIndexOrThrow(DBStatement.AFFECTED_ORGANIZATION.COL_INTERESTPOINT_ID)] = String.valueOf(affectedorganizationObject.getInterestPointId());
				StringArrayResult = affectedorganizationInfo;
				CustomLogger.getInstance().infoLog(AffectedOrganizationManager.class.getName(), "AffectedOrganization Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(AffectedOrganizationManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary AffectedOrganization Object.
				affectedorganizationObject = null;
				// Destroy the temporary StringArray Object.
				affectedorganizationInfo = null;
			}
		}
		return StringArrayResult;
	}

}
