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

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import br.ufrj.ppgi.jemf.mobile.OperationHolder.EnumOperationType;
import br.ufrj.ppgi.jemf.mobile.bean.Team;
import br.ufrj.ppgi.jemf.mobile.database.DBStatement;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderParams;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderStatement;
import br.ufrj.ppgi.jemf.utils.Constraint;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class TeamManager extends RelatedManager {

	/**
	 * Constructor.
	 */
	public TeamManager() {
    	CustomLogger.getInstance().infoLog(TeamManager.class.getName(), "Class started.");
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
					List<String> currentUriPath = newUri.getPathSegments();
					List<String> defaultUriPath = ProviderStatement.TEAM.CONTENT_URI.getPathSegments();
					// Check if Uri is correct for this Manager.
					if (currentUriPath.get(0).compareTo(defaultUriPath.get(0)) == 0) {
						// Set the new ID to the Bean Object.
						((Team) result).setId(Integer.valueOf(newUri.getLastPathSegment()));
					}
			    	CustomLogger.getInstance().infoLog(TeamManager.class.getName(), "New Team Object ID retrieved.");
				} else {
					// Failed to get the Bean Object.
					String errorMsg = "Failed to set the new ID to the Bean Object.";
			    	CustomLogger.getInstance().errorLog(TeamManager.class.getName(), errorMsg);
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
    	CustomLogger.getInstance().errorLog(TeamManager.class.getName(), errorMsg);
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
				// Get Team Object by the cursor deserialization.
				result = this.deserializeFromCursor(cursor);
		    	CustomLogger.getInstance().infoLog(TeamManager.class.getName(), "Single record fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(TeamManager.class.getName(), "Failed to fetch single record.");
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
					// Add new Team Object by the cursor deserialization.
					result.add(this.deserializeFromCursor(cursor));
					// Move cursor to next element.
					cursor.moveToNext();
				}
		    	CustomLogger.getInstance().infoLog(TeamManager.class.getName(), "All records fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(TeamManager.class.getName(), "Failed to fetch all records.");
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
		// Check if Bean Object is an Team Object.
		if (!(bean instanceof Team)) {
			// Failed to get the Team Object.
			String errorMsg = "Failed to get the Team Object. The given Bean Object is not an Team Object.";
	    	CustomLogger.getInstance().errorLog(TeamManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
    	CustomLogger.getInstance().infoLog(TeamManager.class.getName(), "Bean Object checked.");
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
		ProviderParams teamParams = new ProviderParams();
		// Get the parameters by the operation type. 
		switch (operationType) {
		case READ:
			teamParams.setURI(ProviderStatement.TEAM.CONTENT_URI_READ);
			teamParams.setProjection(ProviderStatement.TEAM.PROJECTION_ALL);
			teamParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			teamParams.setSelectionArgs(new String[]{String.valueOf(((Team) getBean()).getId())});
			params.add(teamParams);
			break;
		case READ_ALL:
			teamParams.setURI(ProviderStatement.TEAM.CONTENT_URI_READ);
			teamParams.setProjection(ProviderStatement.TEAM.PROJECTION_ALL);
			params.add(teamParams);
			break;
		default:
			// Failed to get the Provider Parameters.
			String errorMsg = "Failed to get the Provider Parameters. Invalid operation type.";
	    	CustomLogger.getInstance().errorLog(TeamManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
		CustomLogger.getInstance().infoLog(TeamManager.class.getName(), "Team Provider Parameters retrieved.");
		return params;
	}

	/**
	 * Create ContentValues Object to use for database transaction.
	 * @return ContentValues
	 * 			Returns formatted ContentValues data from Bean Object. 
	 */
	@Override
	protected ArrayList<ContentValues> getContentValues() {
		// Failed to get the Content values.
		String errorMsg = "Failed to get the Content values. This Manager do not have Content values.";
    	CustomLogger.getInstance().errorLog(TeamManager.class.getName(), errorMsg);
		throw new IllegalAccessError(errorMsg);
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
			// Create a temporary Team Object.
			Team teamInfo = null;
			try {
				// Check if Object is already created.
				// Case affirmative, use it.
				if ((Team) getBean() != null) {
					teamInfo = ((Team) getBean());
				} else {
				// Otherwise, create a new one.
				// Future work - Need check how to load members. 
					teamInfo = new Team(null, null);
				}
				// Get Team Object ID from cursor.
				teamInfo.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
				// Get Team Object name from cursor.
				teamInfo.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.TEAM.COL_NAME)));
				objOut = teamInfo;
				CustomLogger.getInstance().infoLog(TeamManager.class.getName(), "Team Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(TeamManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary Team Object.
				teamInfo = null;
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
			String[] teamInfo = new String[cursor.getColumnCount()];
			// Create a temporary Team Object.
			Team teamObject = null;
			try {
				teamObject = (Team) this.deserializeFromCursor(cursor);
				// Get Team ID from Team Object.
				teamInfo[cursor.getColumnIndexOrThrow(BaseColumns._ID)] = String.valueOf(teamObject.getId());			
				// Get Team name from Team Object.
				teamInfo[cursor.getColumnIndexOrThrow(DBStatement.TEAM.COL_NAME)] = teamObject.getName();
				StringArrayResult = teamInfo;
				CustomLogger.getInstance().infoLog(TeamManager.class.getName(), "Team Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(TeamManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary Team Object.
				teamObject = null;
				// Destroy the temporary StringArray Object.
				teamInfo = null;
			}
		}
		return StringArrayResult;
	}

	@Override
	protected ArrayList<ContentProviderOperation> getOperations(EnumOperationType operationType) {
		ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
		// Index to get the master ID after insert operation.
		int masterID,
		// Index to get the leader ID.
		leaderID,
		// Index to get the member ID.
		memberCount;
		// Uri of Master record.
		Uri uriMaster = ProviderStatement.TEAM.CONTENT_URI;
		// Uri of Detail record.
		Uri uriDetail = ProviderStatement.TEAM__USER.CONTENT_URI;

		// Get the parameters by the operation type. 
		switch (operationType) {
		case CREATE:
			// Master Operation: Insert Team.
			operations.add(
					ContentProviderOperation.newInsert(uriMaster)
						.withValue(DBStatement.TEAM.COL_NAME, ((Team) getBean()).getName())
						.build()
			);

			/*
			 * Save the relationship records after Master Record Insert operation.
			 */
			// Detail Operation: Insert TeamUser.
			masterID = operations.size()-1;
			if ( ( ((Team) getBean()).getLeader() != null ) || ( ((Team) getBean()).getMembers() != null ) ) {
				// Leader.
				leaderID = ((Team) getBean()).getLeader().getId();
				operations.add(
						ContentProviderOperation.newInsert(uriDetail)
							.withValueBackReference(DBStatement.TEAM__USER.COL_TEAM_ID, masterID)
							.withValue(DBStatement.TEAM__USER.COL_LEADER_ID, leaderID)
							.withValue(DBStatement.TEAM__USER.COL_MEMBER_ID, leaderID)
							.build()
				);
				// Members.
				memberCount = ((Team) getBean()).getMembers().size();
				for (int i = 0; i < memberCount; i++) {
					int memberID = ((Team) getBean()).getMembers().get(i).getId();
					operations.add(
						ContentProviderOperation.newInsert(uriDetail)
							.withValueBackReference(DBStatement.TEAM__USER.COL_TEAM_ID, masterID)
							.withValue(DBStatement.TEAM__USER.COL_LEADER_ID, leaderID)
							.withValue(DBStatement.TEAM__USER.COL_MEMBER_ID, memberID)
							.build()
					);
				}
			}
			break;



		case UPDATE:
			// Master Operation: Update Team.
			masterID = ((Team) getBean()).getId();
			operations.add(
					ContentProviderOperation.newUpdate(uriMaster)
						.withValue(DBStatement.TEAM.COL_NAME, ((Team) getBean()).getName())
						.withSelection(BaseColumns._ID + "=?", new String[]{String.valueOf(masterID)})
						.build()
			);

			/*
			 * Remove the relationship records before Master Record Update operation.
			 */
			// Detail Operation: Delete TeamUser.
			// Delete all records associated with the Team ID.
			operations.add(
					ContentProviderOperation.newDelete(uriDetail)
						.withSelection(DBStatement.TEAM__USER.COL_TEAM_ID + "=?", new String[]{String.valueOf(masterID)})
						.build()
			);

			/*
			 * Save the relationship records after Master Record Update operation.
			 */
			// Detail Operation: Update TeamUser.
			if ( ((Team) getBean()).getLeader() != null ) {
				// Leader.
				leaderID = ((Team) getBean()).getLeader().getId();
				operations.add(
						ContentProviderOperation.newInsert(uriDetail)
							.withValue(DBStatement.TEAM__USER.COL_TEAM_ID, masterID)
							.withValue(DBStatement.TEAM__USER.COL_LEADER_ID, leaderID)
							.withValue(DBStatement.TEAM__USER.COL_MEMBER_ID, leaderID)
							.build()
				);

				// Members.
				if (! ((Team) getBean()).getMembers().isEmpty()) {
					memberCount = ((Team) getBean()).getMembers().size();
					for (int i = 0; i < memberCount; i++) {
						int memberID = ((Team) getBean()).getMembers().get(i).getId();
						operations.add(
							ContentProviderOperation.newInsert(uriDetail)
								.withValue(DBStatement.TEAM__USER.COL_TEAM_ID, masterID)
								.withValue(DBStatement.TEAM__USER.COL_LEADER_ID, leaderID)
								.withValue(DBStatement.TEAM__USER.COL_MEMBER_ID, memberID)
								.build()
						);
					}
				}
			}
			break;



		case DELETE:
			// Master Operation: Delete Team.
			masterID = ((Team) getBean()).getId();
			operations.add(
					ContentProviderOperation.newDelete(uriMaster)
						.withSelection(BaseColumns._ID + "=?", new String[]{String.valueOf(masterID)})
						.build()
			);

			/*
			 * Remove the relationship records after Master Record Delete operation.
			 */
			// Detail Operation: Delete TeamUser.
			// Delete all records associated with the Team ID.
			operations.add(
					ContentProviderOperation.newDelete(uriDetail)
						.withSelection(DBStatement.TEAM__USER.COL_TEAM_ID + "=?", new String[]{String.valueOf(masterID)})
						.build()
			);
			break;


		default:
			// Failed to get the Operations.
			String errorMsg = "Failed to get the Operations. Invalid operation type.";
	    	CustomLogger.getInstance().errorLog(TeamManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
		CustomLogger.getInstance().infoLog(TeamManager.class.getName(), "Team Provider Parameters retrieved.");
		return operations;
	}

}
