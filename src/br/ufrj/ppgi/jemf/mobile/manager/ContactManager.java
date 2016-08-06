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
import br.ufrj.ppgi.jemf.mobile.bean.Contact;
import br.ufrj.ppgi.jemf.mobile.database.DBStatement;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderParams;
import br.ufrj.ppgi.jemf.mobile.provider.ProviderStatement;
import br.ufrj.ppgi.jemf.utils.Constraint;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class ContactManager extends Manager {

	/**
	 * Constructor.
	 */
	public ContactManager() {
    	CustomLogger.getInstance().infoLog(ContactManager.class.getName(), "Class started.");
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
					((Contact) result).setId(Integer.valueOf(newUri.getLastPathSegment()));
			    	CustomLogger.getInstance().infoLog(ContactManager.class.getName(), "New Contact Object ID retrieved.");
				} else {
					// Failed to get the Bean Object.
					String errorMsg = "Failed to set the new ID to the Bean Object.";
			    	CustomLogger.getInstance().errorLog(ContactManager.class.getName(), errorMsg);
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
						result.put(BaseColumns._ID, ((Contact) getBean()).getId());
				    	CustomLogger.getInstance().infoLog(ContactManager.class.getName(), "Detail Record ID updated.");
					} else {
						// Failed to update the Detail content values.
						String errorMsg = "Failed to update the Detail content values.";
				    	CustomLogger.getInstance().errorLog(ContactManager.class.getName(), errorMsg);
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
				// Get Contact Object by the cursor deserialization.
				result = this.deserializeFromCursor(cursor);
		    	CustomLogger.getInstance().infoLog(ContactManager.class.getName(), "Single record fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(ContactManager.class.getName(), "Failed to fetch single record.");
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
					// Add new Contact Object by the cursor deserialization.
					result.add(this.deserializeFromCursor(cursor));
					// Move cursor to next element.
					cursor.moveToNext();
				}
		    	CustomLogger.getInstance().infoLog(ContactManager.class.getName(), "All records fetched.");
			} catch (Exception e) {
		    	CustomLogger.getInstance().errorLog(ContactManager.class.getName(), "Failed to fetch all records.");
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
		// Check if Bean Object is an Contact Object.
		if (!(bean instanceof Contact)) {
			// Failed to get the Contact Object.
			String errorMsg = "Failed to get the Contact Object. The given Bean Object is not an Contact Object.";
	    	CustomLogger.getInstance().errorLog(ContactManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
    	CustomLogger.getInstance().infoLog(ContactManager.class.getName(), "Bean Object checked.");
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
		ProviderParams contactParams = new ProviderParams();
		// Get the parameters by the operation type. 
		switch (operationType) {
		case CREATE:
			contactParams.setURI(ProviderStatement.CONTACT.CONTENT_URI);
			params.add(contactParams);
			break;
		case UPDATE:
		case DELETE:
			contactParams.setURI(ProviderStatement.CONTACT.CONTENT_URI);
			contactParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			contactParams.setSelectionArgs(new String[]{String.valueOf(((Contact) getBean()).getId())});
			params.add(contactParams);
			break;
		case READ:
			contactParams.setURI(ProviderStatement.CONTACT.CONTENT_URI_READ);
			contactParams.setProjection(ProviderStatement.CONTACT.PROJECTION_ALL);
			contactParams.setSelection(BaseColumns._ID + DBStatement.SELECTION_STATEMENT);
			contactParams.setSelectionArgs(new String[]{String.valueOf(((Contact) getBean()).getId())});
			params.add(contactParams);
		case READ_ALL:
			contactParams.setURI(ProviderStatement.CONTACT.CONTENT_URI_READ);
			contactParams.setProjection(ProviderStatement.CONTACT.PROJECTION_ALL);
			params.add(contactParams);
			break;
		default:
			// Failed to get the Provider Parameters.
			String errorMsg = "Failed to get the Provider Parameters. Invalid operation type.";
	    	CustomLogger.getInstance().errorLog(ContactManager.class.getName(), errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
		CustomLogger.getInstance().infoLog(ContactManager.class.getName(), "Contact Provider Parameters retrieved.");
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
			ContentValues contactValues = new ContentValues();
			// Note that ID Column is NOT included here because it is auto-incremented.
			contactValues.put(DBStatement.CONTACT.COL_PHONE, ((Contact) getBean()).getPhone());
			contactValues.put(DBStatement.CONTACT.COL_EMAIL, ((Contact) getBean()).getEmail());
			contactValues.put(DBStatement.CONTACT.COL_RADIO, ((Contact) getBean()).getRadio());
			contactValues.put(DBStatement.CONTACT.COL_LANGUAGE, ((Contact) getBean()).getLanguageString());
			contactValues.put(DBStatement.CONTACT.COL_ENTITY_ID, String.valueOf(((Contact) getBean()).getEntityId()));
			contentValues.add(contactValues);
			CustomLogger.getInstance().infoLog(ContactManager.class.getName(), "Contact Content Values retrieved.");
		} catch (Exception e) {
			// Failed to get the Content Values.
			String errorMsg = "Failed to get the Contact Content Values.";
	    	CustomLogger.getInstance().errorLog(ContactManager.class.getName(), errorMsg);
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
			// Create a temporary Contact Object.
			Contact contactInfo = null;
			try {
				// Check if Object is already created.
				// Case affirmative, use it.
				if ((Contact) getBean() != null) {
					contactInfo = ((Contact) getBean());
				} else {
				// Otherwise, create a new one.
					contactInfo = new Contact();
				}
				// Get Contact Object ID from cursor.
				contactInfo.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
				// Get Contact Object phone from cursor.
				contactInfo.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.CONTACT.COL_PHONE)));
				// Get Contact Object email from cursor.
				contactInfo.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.CONTACT.COL_EMAIL)));
				// Get Contact Object radio from cursor.
				contactInfo.setRadio(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.CONTACT.COL_RADIO)));
				// Get Contact Object language from cursor.
				contactInfo.setLanguageString(cursor.getString(cursor.getColumnIndexOrThrow(DBStatement.CONTACT.COL_LANGUAGE)));
				// Get Contact Object Entity ID from cursor.
				contactInfo.setEntityId(cursor.getInt(cursor.getColumnIndexOrThrow(DBStatement.CONTACT.COL_ENTITY_ID)));
				objOut = contactInfo;
				CustomLogger.getInstance().infoLog(ContactManager.class.getName(), "Contact Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(ContactManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary Contact Object.
				contactInfo = null;
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
			String[] contactInfo = new String[cursor.getColumnCount()];
			// Create a temporary Contact Object.
			Contact contactObject = null;
			try {
				contactObject = (Contact) this.deserializeFromCursor(cursor);
				// Get Contact ID from Contact Object.
				contactInfo[cursor.getColumnIndexOrThrow(BaseColumns._ID)] = String.valueOf(contactObject.getId());			
				// Get Contact phone from Contact Object.
				contactInfo[cursor.getColumnIndexOrThrow(DBStatement.CONTACT.COL_PHONE)] = contactObject.getPhone();
				// Get Contact email from Contact Object.
				contactInfo[cursor.getColumnIndexOrThrow(DBStatement.CONTACT.COL_EMAIL)] = contactObject.getEmail();
				// Get Contact radio from Contact Object.
				contactInfo[cursor.getColumnIndexOrThrow(DBStatement.CONTACT.COL_RADIO)] = contactObject.getRadio();
				// Get Contact language from Contact Object.
				contactInfo[cursor.getColumnIndexOrThrow(DBStatement.CONTACT.COL_LANGUAGE)] = contactObject.getLanguageString();
				// Get Contact Entity ID from Contact Object.
				contactInfo[cursor.getColumnIndexOrThrow(DBStatement.CONTACT.COL_ENTITY_ID)] = String.valueOf(contactObject.getEntityId());
				StringArrayResult = contactInfo;
				CustomLogger.getInstance().infoLog(ContactManager.class.getName(), "Contact Object deserialized from Cursor.");
			} catch (IllegalArgumentException e) {
		    	CustomLogger.getInstance().errorLog(ContactManager.class.getName(), "Failed to deserialize cursor.");
				e.printStackTrace();
			} finally {
				// Destroy the temporary Contact Object.
				contactObject = null;
				// Destroy the temporary StringArray Object.
				contactInfo = null;
			}
		}
		return StringArrayResult;
	}

}
