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

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.OperationApplicationException;
import android.os.RemoteException;
import br.ufrj.ppgi.jemf.mobile.OperationHolder.EnumOperationType;
import br.ufrj.ppgi.jemf.utils.CustomLogger;


/**
 * @author Marcus Machado
 *
 */
public abstract class RelatedManager extends Manager {

	/**
	 * Insert or Update a record by Content Provider.
	 * @param <T>
	 * @param bean
	 * @return Object
	 * 			Returns saved Bean Object.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public synchronized <T> T save(Object bean) {
		Object saveResult = null;
		// Check if the Bean Object is valid.
		this.setBean(this.checkBean(bean));
		ArrayList<ContentProviderOperation> operations;

		// Check if Bean Object already have an valid ID.
		// Case Bean Object do NOT have an valid ID, then try to insert a record. 
		if (this.isInsertOperation()) {
			// Try to INSERT the Bean Object.
			try {
				// Get batch operations to realize the insert.
				operations = getOperations(EnumOperationType.CREATE);
				ContentProviderResult[] results = this.getContext().getContentResolver().applyBatch(operations.get(0).getUri().getAuthority(), operations);
				// Check if operation result is valid.
				if (results[0] != null) {
					// Record inserted successfully.
					// Get the new record ID to set in Bean Object.
					saveResult = this.getNewIDFromUri(results[0].uri);
			    	CustomLogger.getInstance().infoLog(RelatedManager.class.getName(), "Record inserted.");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (OperationApplicationException e) {
				e.printStackTrace();
			}



		// Otherwise, case Bean Object do have an valid ID, then it is to update a record instead.
		} else {
			// Try to UPDATE the Bean Object.
			try {
				// Get batch operations to realize the update .
				operations = getOperations(EnumOperationType.UPDATE);
				ContentProviderResult[] results = this.getContext().getContentResolver().applyBatch(operations.get(0).getUri().getAuthority(), operations);
				// Check if operation result is valid.
				if (results[0] != null) {
					saveResult = bean;
			    	CustomLogger.getInstance().infoLog(RelatedManager.class.getName(), "Record updated.");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (OperationApplicationException e) {
				e.printStackTrace();
			}

		}
		return ((T) saveResult);
	}

	/**
	 * Delete a record by Content Provider.
	 * @param <T>
	 * @param bean
	 * @return Object
	 * 			Returns Null if Bean Object was removed properly.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public synchronized <T> T remove(Object bean) {
		Object removeResult = bean;
		// Check if the Bean Object is valid.
		this.setBean(this.checkBean(bean));
		ArrayList<ContentProviderOperation> operations;

		try {
			// Get batch operations to realize the delete.
			operations = getOperations(EnumOperationType.DELETE);
			ContentProviderResult[] results = this.getContext().getContentResolver().applyBatch(operations.get(0).getUri().getAuthority(), operations);
			// Check if operation result is valid. 
			if (results[0] != null) {
				// Delete Bean Object data.
				this.setBean(null);
				// Return NULL if record was deleted successfully.
				removeResult = null;
		    	CustomLogger.getInstance().infoLog(RelatedManager.class.getName(), "Record deleted.");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (OperationApplicationException e) {
			e.printStackTrace();
		}

		return ((T) removeResult);
	}

	/**
	 * Create ContentValues Object to use for database transaction.
	 * Template Method - This methods will be implemented by its subclass.
	 * @return ContentValues
	 * 			Returns formatted ContentValues data from Bean Object. 
	 */
	protected abstract ArrayList<ContentProviderOperation> getOperations(EnumOperationType operationType);

}
