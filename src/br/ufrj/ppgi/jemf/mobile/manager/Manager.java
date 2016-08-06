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
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import br.ufrj.ppgi.jemf.core.AbstractBase;
import br.ufrj.ppgi.jemf.mobile.OperationHolder.EnumOperationType;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderParams;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public abstract class Manager {

	/**
	 * Parent Context.
	 */
	protected Context parentContext;
	/**
	 * Bean Object.
	 */
	private Object bean;
	/**
	 * Bean Object List.
	 */
	private List<Object> list;



	/**
	 * Constructor.
	 */
	public Manager() {
    	CustomLogger.getInstance().infoLog(Manager.class.getName(), "Class started.");
	}



	/**
	 * Get the parent Context.
	 * @return Context
	 */
	public Context getContext() {
		return this.parentContext;
	}

	/**
	 * Set the parent Context.
	 * @param context
	 */
	public void setContext(Context context) {
		this.parentContext = context;
	}

	/**
	 * Get the current Bean Object.
	 * @return Object
	 */
	protected Object getBean() {
		return this.bean;
	}

	/**
	 * Set the current Bean Object.
	 * @param bean
	 */
	protected void setBean(Object bean) {
		this.bean = bean;
	}

	/**
	 * Get the current Bean Object list.
	 * @return List
	 */
	protected List<Object> getBeanList() {
		return this.list;
	}

	/**
	 * Get the current Bean Object list.
	 * @param objectList
	 */
	protected void setBeanList(List<Object> objectList) {
		this.list = objectList;
	}



	/**********************************************************************************
	 ******************************* Manager Methods **********************************
	 *********************************************************************************/
	/**
	 * Insert or Update a record by Content Provider.
	 * @param <T>
	 * @param bean
	 * @return Object
	 * 			Returns saved Bean Object.
	 */
	@SuppressWarnings("unchecked")
	public synchronized <T> T save(final Object bean) {
		Object saveResult = null;
		// Check if the Bean Object is valid.
		this.setBean(this.checkBean(bean));
		ArrayList<ProviderParams> params;
		// Get Content Values from Bean Object.
		final ArrayList<ContentValues> values = getContentValues();

		// Case Bean Object do not have an valid ID or update operation failed, then try to insert a record instead.
		if (isInsertOperation()) {
			// Try to INSERT the Bean Object.
			// Get parameters to realize the insert operation.
			params = this.getParams(EnumOperationType.CREATE);
			Uri insertResult = null;
			for (int i = 0; i < values.size(); i++) {
				// INSERT OPERATION.
				insertResult = this.getContext().getContentResolver().insert(
						// Uri.
						params.get(i).getURI(),
						// Values.
						values.get(i)
				);
				// Check if operation result is valid.
				if (insertResult != null) {
					// Record inserted successfully.
					// Get the new record ID to set in Bean Object.
					saveResult = this.getNewIDFromUri(insertResult);
			    	CustomLogger.getInstance().infoLog(Manager.class.getName(), "Record inserted.");
			    	// After the first record was inserted, we must updated the ContentValues (relationship ID) of second record.
			    	// Check if Bean hold more than 1 value (then is to save Master and Detail) and if it is the last value.
			    	if ( (values.size() > 1) && (i < (values.size()-1)) ) {
			    		// Get the next Values to update the Detail ID.
			    		// Set the result at original position.
			    		values.set(i+1, this.setParentIDToDetail(values.get(i+1)));
			    	}
				}
			}



		// Otherwise, if Bean Object already have an valid ID, then it is to update a record.
		} else {
			// Try to UPDATE the Bean Object.
			int updateResult = 0;
			// Get parameters to realize the update operation.
			params = this.getParams(EnumOperationType.UPDATE);
			for (int i = 0; i < values.size(); i++) {
				// UPDATE OPERATION.
				updateResult = this.getContext().getContentResolver().update(
						// Uri.
						params.get(i).getURI(),
						// Values.
						values.get(i),
						// Selection statement.
						params.get(i).getSelection(),
						// Get the ID row requested to update.
						params.get(i).getSelectionArgs()
				);
				// Check if operation result is valid.
				if (updateResult > 0) {
					// Record updated successfully.
					saveResult = bean;
			    	CustomLogger.getInstance().infoLog(Manager.class.getName(), "Record updated.");
				}
			}
		}
		return ((T) saveResult);
	}

	/**
	 * Get the new ID from Uri after Insert operation.
	 * Set the new ID to the Bean Object.
	 * Template Method - This methods will be implemented by its subclass.
	 * @param newUri
	 * @return Object
	 * 			Returns saved Bean Object.
	 */
	protected abstract Object getNewIDFromUri(final Uri newUri);

	/**
	 * Update the relationship ID of Detail Record after Master Record Insert operation.
	 * Set the new Parent ID to the Detail ContentValues.
	 * Template Method - This methods will be implemented by its subclass.
	 * @param parentValues
	 * @return ContentValues
	 * 			Returns updated ContentValues Object.
	 */
	protected abstract ContentValues setParentIDToDetail(final ContentValues parentValues);



	/**
	 * Delete a record by Content Provider.
	 * @param <T>
	 * @param bean
	 * @return Object
	 * 			Returns Null if Bean Object was removed properly.
	 */
	@SuppressWarnings("unchecked")
	public synchronized <T> T remove(final Object bean) {
		Object removeResult = bean;
		// Check if the Bean Object is valid.
		this.setBean(this.checkBean(bean));
		// Get parameters to realize the delete operation.
		ArrayList<ProviderParams> params = this.getParams(EnumOperationType.DELETE);
		for (int i = 0; i < params.size(); i++) {
			// DELETE OPERATION.
			final int result = this.getContext().getContentResolver().delete(
					// Uri.
					params.get(i).getURI(),
					// Selection statement.
					params.get(i).getSelection(),
					// Get the ID row requested to delete.
					params.get(i).getSelectionArgs()
			);
			// Check if operation result is valid. 
			if (result > 0) {
				// Delete Bean Object data.
				this.setBean(null);
				// Return NULL if record was deleted successfully.
				removeResult = null;
		    	CustomLogger.getInstance().infoLog(Manager.class.getName(), "Record deleted.");
			}
		}
		return ((T) removeResult);
	}



	/**
	 * Load a single record to a Bean Object by Content Provider.
	 * @param <T>
	 * @param bean
	 * @return Object
	 * 			Returns Bean Object loaded from database cursor.
	 */
	@SuppressWarnings("unchecked")
	public synchronized <T> T load(Object bean) {
		Object loadResult = null;
		// Check if the Bean Object is valid.
		this.setBean(this.checkBean(bean));
		// Get parameters to realize the read operation.
		ArrayList<ProviderParams> params = this.getParams(EnumOperationType.READ);
		// READ OPERATION.
		final Cursor cursor = this.getContext().getContentResolver().query(
				// Uri.
				params.get(0).getURI(),
				// Projection.
				params.get(0).getProjection(),
				// Selection.
				params.get(0).getSelection(),
				// Selection arguments.
				params.get(0).getSelectionArgs(),
				// Sort order. When NULL automatically adds the FIRST column after ID column.
				params.get(0).getSortOrder()
		);
		// Check cursor result is valid and open.
		if (cursor != null && !cursor.isClosed() && cursor.getCount() > 0) {
			// Move cursor to the first element.
			cursor.moveToFirst();
			// Fetch a single record.
			loadResult = this.fetchSingleRecord(cursor);
			// Check if cursor still open.
			if(cursor != null && !cursor.isClosed()) {
				// Close it to avoid memory leak.
				cursor.close();
			}
	    	CustomLogger.getInstance().infoLog(Manager.class.getName(), "Record loaded.");
		}
		return ((T) loadResult);
	}

	/**
	 * Load a single record from database to a Object.
	 * Template Method - This methods will be implemented by its subclass.
	 * @param cursor
	 * @return Object
	 * 			Returns Bean Object loaded from database cursor.
	 */
	protected abstract Object fetchSingleRecord(final Cursor cursor);



	/**
	 * Load all records to a cursor by Content Provider.
	 * Access result on List format by method getBeanList().
	 * @param <T>
	 * @return Cursor
	 * 			Returns Cursor with all records found in database.
	 */
	@SuppressWarnings("unchecked")
	public synchronized <T> ArrayList<T> loadAll(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		ArrayList<T> resultLoad = null;
		// Get parameters to realize the read all operation.
		ArrayList<ProviderParams> params = this.getParams(EnumOperationType.READ_ALL);
		// READ OPERATION.
		final Cursor cursor = this.getContext().getContentResolver().query(
				// Uri.
				params.get(0).getURI(),
				// Projection. When NULL automatically adds ALL columns projection.
				projection,
				// Selection.
				selection,
				// Selection arguments.
				selectionArgs,
				// Sort order. When NULL automatically adds FIRST column after ID column.
				sortOrder
		);
		// Check cursor result is valid and open.
		if (cursor != null && !cursor.isClosed() && cursor.getCount() > 0) {
			// Move cursor to the first element.
			cursor.moveToFirst();
			// Save result at local Bean Object list by fetching all records.
			this.setBeanList(this.fetchAllRecords(cursor));
			// Check if cursor still opened.
			if(cursor != null && !cursor.isClosed()) {
				// Close it to avoid memory leak.
				cursor.close();
			}
			// Check if local Bean Object list has items, then return result.
			if (this.getBeanList() != null) {
				resultLoad = ((ArrayList<T>) this.getBeanList()); 
			}
	    	CustomLogger.getInstance().infoLog(Manager.class.getName(), "All records loaded.");
		}
		return resultLoad;
	}

	/**
	 * Load all records from database to a Object List.
	 * Template Method - This methods will be implemented by its subclass.
	 * @param cursor
	 * @return List
	 * 			Returns List with all records found in database.
	 */
	protected abstract List<Object> fetchAllRecords(final Cursor cursor);



	/**********************************************************************************
	 ******************************* Support Methods **********************************
	 *********************************************************************************/
	/**
	 * Check instance of current Bean Object.
	 * Template Method - This methods will be implemented by its subclass.
	 * @param bean
	 * @return Object
	 * 			Returns validated Bean Object to realize CRUD operations by Content Provider.
	 */
	protected abstract Object checkBean(Object bean);

	/**
	 * Get parameters for CRUD operations by Content Provider.
	 * Template Method - This methods will be implemented by its subclass. 
	 * @param operationType
	 * @return ProviderParams
	 * 			Returns customized Parameters from Bean Object to realize CRUD operations by Content Provider.
	 */
	protected abstract ArrayList<ProviderParams> getParams(final EnumOperationType operationType);

	/**
	 * Create ContentValues Object to use for database transaction.
	 * Template Method - This methods will be implemented by its subclass.
	 * @return ContentValues
	 * 			Returns formatted ContentValues data from Bean Object. 
	 */
	protected abstract ArrayList<ContentValues> getContentValues();

	/**
	 * Deserialize one record.
	 * Deserialization is the process of turning a stream of bytes or a cursor into an object in memory.
	 * Template Method - This methods will be implemented by its subclass.
	 * @param cursor
	 * @return Object
	 * 			Returns Bean Object from Cursor.
	 */
	protected abstract Object deserializeFromCursor(final Cursor cursor);

	/**
	 * Deserialize one record.
	 * Deserialize method overload to return Cursor data as StringArray.
	 * Deserialization is the process of turning a stream of bytes or a cursor into an object in memory.
	 * Template Method - This methods will be implemented by its subclass.
	 * @param cursor
	 * @return String[]
	 * 			Returns Bean Object from Cursor as StringArray.
	 */
	protected abstract String[] deserializeFromCursorToStringArray(final Cursor cursor);

	/**
	 * Deserialize a row cursor.
	 * Deserialization is the process of turning a stream of bytes or a cursor into an object in memory. 
	 * @param <T>
	 * @param cursor
	 * @return Object
	 * 			Returns Bean Object from Cursor.
	 */
	@SuppressWarnings("unchecked")
	public <T> T deserialize(final Cursor cursor) {
		// Deserialize from cursor.
		return ((T) this.deserializeFromCursor(cursor));
	}

	/**
	 * Check if the save operation is to insert or update data.
	 * Initially a manager do not have a valid ID, so this property is checked ("0" equals insert). 
	 * @return boolean
	 * 			Returns TRUE representing a insert operation, otherwise is a update operation.
	 */
	protected boolean isInsertOperation() {
		return ( (((AbstractBase) getBean()).getId() == 0) || (((AbstractBase) getBean()).getId() < 0) );
	}

}
