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
import br.ufrj.ppgi.jemf.mobile.bean.Plan;
import br.ufrj.ppgi.jemf.mobile.database.DBStatement;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderParams;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderStatement;
import br.ufrj.ppgi.jemf.utils.Constraint;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class PlanManager extends Manager {

	/**
	 * Constructor.
	 */
	public PlanManager() {
    	CustomLogger.getInstance().infoLog(PlanManager.class.getName(), "Class started.");
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
					((Plan) result).setId(Integer.valueOf(newUri.getLastPathSegment()));
			    	CustomLogger.getInstance().infoLog(PlanManager.class.getName(), "New Plan Object ID retrieved.");
				} else {
					// Failed to get the Bean Object.
					String errorMsg = "Failed to set the new ID to the Bean Object.";
			    	CustomLogger.getInstance().errorLog(PlanManager.class.getName(), errorMsg);
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
						result.put(BaseColumns._ID, ((Plan) getBean()).getId());
				    	CustomLogger.getInstance().infoLog(PlanManager.class.getName(), "Detail Record ID updated.");
					} else {
						// Failed to update the Detail content values.
						String errorMsg = "Failed to update the Detail content values.";
				    	CustomLogger.getInstance().errorLog(PlanManager.class.getName(), errorMsg);
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
				// Get Plan Object by the cursor deserialization.
				result = this.deserializeFromCursor(cursor);
		    	CustomLogger.getInstance().infoLog(PlanManager.class.getName(), "Single record fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(PlanManager.class.getName(), "Failed to fetch single record.");
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
					// Add new Plan Object by the cursor deserialization.
					result.add(this.deserializeFromCursor(cursor));
					// Move cursor to next element.
					cursor.moveToNext();
				}
		    	CustomLogger.getInstance().infoLog(PlanManager.class.getName(), "All records fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(PlanManager.class.getName(), "Failed to fetch all records.");
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
		// Check if Bean Object is an Plan Object.
		if (!(bean instanceof Plan)) {
			// Failed to get the Plan Object.
			String errorMsg = "Failed to get the Plan Object. The given Bean Object is not an Plan Object.";
	    	CustomLogger.getInstance().errorLog(PlanManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
    	CustomLogger.getInstance().infoLog(PlanManager.class.getName(), "Bean Object checked.");
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
		ProviderParams shareddocumentParams = new ProviderParams();
		ProviderParams planParams = new ProviderParams();
		// Get the parameters by the operation type. 
		switch (operationType) {
		case CREATE:
			informationParams.setURI(ProviderStatement.INFORMATION.CONTENT_URI);
			params.add(informationParams);
			shareddocumentParams.setURI(ProviderStatement.SHARED_DOCUMENT.CONTENT_URI);
			params.add(shareddocumentParams);
			planParams.setURI(ProviderStatement.PLAN.CONTENT_URI);
			params.add(planParams);
			break;
		case UPDATE:
		case DELETE:
			informationParams.setURI(ProviderStatement.INFORMATION.CONTENT_URI);
			informationParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			informationParams.setSelectionArgs(new String[]{String.valueOf(((Plan) getBean()).getId())});
			params.add(informationParams);
			shareddocumentParams.setURI(ProviderStatement.SHARED_DOCUMENT.CONTENT_URI);
			shareddocumentParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			shareddocumentParams.setSelectionArgs(new String[]{String.valueOf(((Plan) getBean()).getId())});
			params.add(shareddocumentParams);
			planParams.setURI(ProviderStatement.PLAN.CONTENT_URI);
			planParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			planParams.setSelectionArgs(new String[]{String.valueOf(((Plan) getBean()).getId())});
			params.add(planParams);
			break;
		case READ:
			planParams.setURI(ProviderStatement.PLAN.CONTENT_URI_READ);
			planParams.setProjection(ProviderStatement.PLAN.PROJECTION_ALL);
			planParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			planParams.setSelectionArgs(new String[]{String.valueOf(((Plan) getBean()).getId())});
			params.add(planParams);
			break;
		case READ_ALL:
			planParams.setURI(ProviderStatement.PLAN.CONTENT_URI_READ);
			planParams.setProjection(ProviderStatement.PLAN.PROJECTION_ALL);
			params.add(planParams);
			break;
		default:
			// Failed to get the Provider Parameters.
			String errorMsg = "Failed to get the Provider Parameters. Invalid operation type.";
	    	CustomLogger.getInstance().errorLog(PlanManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
		CustomLogger.getInstance().infoLog(PlanManager.class.getName(), "Plan Provider Parameters retrieved.");
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
			informationValues.put(DBStatement.INFORMATION.COL_CREATIONDATE, ((Plan) getBean()).getCreationDateString());
			informationValues.put(DBStatement.INFORMATION.COL_LASTMODIFICATIONDATE, ((Plan) getBean()).getLastModificationDateString());
			informationValues.put(DBStatement.INFORMATION.COL_PRIORITY, ((Plan) getBean()).getPriorityString());
			informationValues.put(DBStatement.INFORMATION.COL_EMERGENCY_ID, String.valueOf(((Plan) getBean()).getEmergencyId()));
			contentValues.add(informationValues);

			ContentValues shareddocumentValues = new ContentValues();
			shareddocumentValues.put(BaseColumns._ID, ((Plan) getBean()).getId());
			shareddocumentValues.put(DBStatement.SHARED_DOCUMENT.COL_DESCRIPTION, ((Plan) getBean()).getDescription());
			shareddocumentValues.put(DBStatement.SHARED_DOCUMENT.COL_OBSERVATION, ((Plan) getBean()).getObservation());
			contentValues.add(shareddocumentValues);

			ContentValues planValues = new ContentValues();
			planValues.put(BaseColumns._ID, ((Plan) getBean()).getId());
			planValues.put(DBStatement.PLAN.COL_OBJECTIVE, ((Plan) getBean()).getObjective());
			planValues.put(DBStatement.PLAN.COL_RISK, ((Plan) getBean()).getRisk());
			planValues.put(DBStatement.PLAN.COL_CHECKLIST, ((Plan) getBean()).getCheckList());
			contentValues.add(planValues);
			CustomLogger.getInstance().infoLog(PlanManager.class.getName(), "Plan Content Values retrieved.");
		} catch (Exception e) {
			// Failed to get the Content Values.
			String errorMsg = "Failed to get the Plan Content Values.";
	    	CustomLogger.getInstance().errorLog(PlanManager.class.getName(), errorMsg);
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
			// Create a temporary Plan Object.
			Plan planInfo = null;
			try {
				// Check if Object is already created.
				// Case affirmative, use it.
				if ((Plan) getBean() != null) {
					planInfo = ((Plan) getBean());
				} else {
				// Otherwise, create a new one.
					planInfo = new Plan();
				}
				// Get Plan Object ID from cursor.
				planInfo.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
				// Get Plan Object creationDate from cursor.
				planInfo.setCreationDateString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.INFORMATION.COL_CREATIONDATE)));
				// Get Plan Object lastModificationDate from cursor.
				planInfo.setLastModificationDateString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.INFORMATION.COL_LASTMODIFICATIONDATE)));
				// Get Plan Object priority from cursor.
				planInfo.setPriorityString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.INFORMATION.COL_PRIORITY)));
				// Get Plan Object Emergency ID from cursor.
				planInfo.setEmergencyId(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.INFORMATION.COL_EMERGENCY_ID)));
				// Get Plan Object description from cursor.
				planInfo.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.SHARED_DOCUMENT.COL_DESCRIPTION)));
				// Get Plan Object observation from cursor.
				planInfo.setObservation(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.SHARED_DOCUMENT.COL_OBSERVATION)));
				// Get Plan Object objective from cursor.
				planInfo.setObjective(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.PLAN.COL_OBJECTIVE)));
				// Get Plan Object risk from cursor.
				planInfo.setRisk(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.PLAN.COL_RISK)));
				// Get Plan Object checkList from cursor.
				planInfo.setCheckList(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.PLAN.COL_CHECKLIST)));
				objOut = planInfo;
				CustomLogger.getInstance().infoLog(PlanManager.class.getName(), "Plan Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(PlanManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary Plan Object.
				planInfo = null;
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
			String[] planInfo = new String[cursor.getColumnCount()];
			// Create a temporary Plan Object.
			Plan planObject = null;
			try {
				planObject = (Plan) this.deserializeFromCursor(cursor);
				// Get Plan ID from Plan Object.
				planInfo[cursor.getColumnIndexOrThrow(BaseColumns._ID)] = String.valueOf(planObject.getId());
				// Get Plan creationDate from Plan Object.
				planInfo[cursor.getColumnIndexOrThrow(DBStatement.INFORMATION.COL_CREATIONDATE)] = planObject.getCreationDateString();
				// Get Plan lastModificationDate from Plan Object.
				planInfo[cursor.getColumnIndexOrThrow(DBStatement.INFORMATION.COL_LASTMODIFICATIONDATE)] = planObject.getLastModificationDateString();
				// Get Plan priority from Plan Object.
				planInfo[cursor.getColumnIndexOrThrow(DBStatement.INFORMATION.COL_PRIORITY)] = planObject.getPriorityString();
				// Get Plan Emergency ID from Plan Object.
				planInfo[cursor.getColumnIndexOrThrow(DBStatement.INFORMATION.COL_EMERGENCY_ID)] = String.valueOf(planObject.getEmergencyId());
				// Get Plan description from Plan Object.
				planInfo[cursor.getColumnIndexOrThrow(DBStatement.SHARED_DOCUMENT.COL_DESCRIPTION)] = planObject.getDescription();
				// Get Plan observation from Plan Object.
				planInfo[cursor.getColumnIndexOrThrow(DBStatement.SHARED_DOCUMENT.COL_OBSERVATION)] = planObject.getObservation();
				// Get Plan objective from Plan Object.
				planInfo[cursor.getColumnIndexOrThrow(DBStatement.PLAN.COL_OBJECTIVE)] = planObject.getObjective();
				// Get Plan risk from Plan Object.
				planInfo[cursor.getColumnIndexOrThrow(DBStatement.PLAN.COL_RISK)] = planObject.getRisk();
				// Get Plan checkList from Plan Object.
				planInfo[cursor.getColumnIndexOrThrow(DBStatement.PLAN.COL_CHECKLIST)] = planObject.getCheckList();
				StringArrayResult = planInfo;
				CustomLogger.getInstance().infoLog(PlanManager.class.getName(), "Plan Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(PlanManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary Plan Object.
				planObject = null;
				// Destroy the temporary StringArray Object.
				planInfo = null;
			}
		}
		return StringArrayResult;
	}

}
