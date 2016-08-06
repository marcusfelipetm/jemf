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
package br.ufrj.ppgi.jemf.mobile.provider;

import android.net.Uri;
import br.ufrj.ppgi.jemf.mobile.database.DBStatement;
import br.ufrj.ppgi.jemf.utils.StringFormat;

/**
 * @author Marcus Machado
 *
 */
public class ProviderStatement {

	/**********************************************************************/
	/************************ URI Base Statements *************************/
	/**********************************************************************/
	/**
	 * Default scheme.
	 */
	public static final String SCHEME = "content://";
	/**
	 * Uri single record identifier.
	 */
	public static final String URI_SINGLE_STATEMENT = "/#";





	/**********************************************************************/
	/************************ JEMF Statements *****************************/
	/**********************************************************************/
	/**
	 * JEMF content provider signature.
	 */
	public static final String PROVIDER_SIGNATURE = "br.ufrj.ppgi.jemf.mobile.provider.ACCESS_DATA";
    /**
     * Authority is the symbolic name of the provider.
     * To avoid conflicts with other providers, this should use Internet domain ownership (in reverse) as the basis of the provider authority.
     */
	public static final String AUTHORITY = "br.ufrj.ppgi.jemf.mobile.provider";
	/**
	 * The MIME type of a directory of collections.
	 */
	public static final String CONTENT_COLLECTION_TYPE_STATEMENT = "vnd.android.cursor.dir/" + AUTHORITY;
	/**
	 * The MIME type of a single item.
	 */
	public static final String CONTENT_ITEM_TYPE_STATEMENT = "vnd.android.cursor.item/"+ AUTHORITY;





	/**********************************************************************/
	/************************ Messages ************************************/
	/**********************************************************************/
	/**
	 * Unknown Uri message.
	 */
	public static final String MESSAGE_UNKNOWN_URI = "Unknown URI: ";
	/**
	 * Null Content Values message.
	 */
	public static final String MESSAGE_NULL_VALUES = "ContentValues is null or empty for the URI: ";
	/**
	 * Insert fail message.
	 */
	public static final String MESSAGE_INSERT_FAIL = "Failed to insert a row into URI: ";
	/**
	 * Update fail message.
	 */
	public static final String MESSAGE_UPDATE_FAIL = "Failed to update a row into URI: ";
	/**
	 * Delete fail message.
	 */
	public static final String MESSAGE_DELETE_FAIL = "Failed to delete a row into URI: ";
	/**
	 * Unknown Projection message.
	 */
	public static final String MESSAGE_UNKNOWN_PROJECTION = "Unknown columns in projection: ";





	/**********************************************************************/
	/************************ Provider Statements *************************/
	/**********************************************************************/
	/**
	 * Emergency Content Provider.
	 */
    public static final class EMERGENCY {
    	/**
    	 * Emergency Collection ID.
    	 */
    	public static final int ALL_EMERGENCIES = 100;
    	/**
    	 * Emergency Single ID.
    	 */
    	public static final int SINGLE_EMERGENCY = 101;
    	/**
    	 * Emergency Collection ID for READ operation, using the view.
    	 */
    	public static final int ALL_EMERGENCIES_READ = 102;
    	/**
    	 * Emergency Single ID for READ operation, using the view.
    	 */
    	public static final int SINGLE_EMERGENCY_READ = 103;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.EMERGENCY.TABLE;
    	/**
    	 * Emergency MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    	/**
    	 * Read Path (VIEW).
    	 */
    	public static final String PATH_READ = DBStatement.EMERGENCY.VIEW;
    	/**
    	 * Emergency READ URI.
    	 * Create content URIs from the authority by appending path to database view.
    	 */
        public static final Uri CONTENT_URI_READ = Uri.parse("content://" + AUTHORITY + "/" + PATH_READ);
        /**
         * Emergency projection.
         */
        public static final String[] PROJECTION_ALL =  new String[] {
        	DBStatement.EMERGENCY._ID,
    		DBStatement.EMERGENCY.COL_NAME,
    		DBStatement.EMERGENCY.COL_ACTIVATED,
    		DBStatement.EMERGENCY.COL_LEVEL,
    		DBStatement.EMERGENCY.COL_START_DATE,
    		DBStatement.EMERGENCY.COL_END_DATE,
    		DBStatement.EMERGENCY.COL_TYPE
    	};
        /**
         * Emergency projection by index: Column ID.
         */
        public static final int INDEX_ID = 0;
        /**
         * Emergency projection by index: Column Name.
         */
        public static final int INDEX_NAME = 1;
        /**
         * Emergency projection by index: Column Activated.
         */
        public static final int INDEX_ACTIVATED = 2;
        /**
         * Emergency projection by index: Column Level.
         */
        public static final int INDEX_LEVEL = 3;
        /**
         * Emergency projection by index: Column Start Date.
         */
        public static final int INDEX_START_DATE = 4;
        /**
         * Emergency projection by index: Column End Date.
         */
        public static final int INDEX_END_DATE = 5;
        /**
         * Emergency projection by index: Column Type.
         */
        public static final int INDEX_TYPE = 6;
        /**
         * Emergency default check column.
         */
        public static final String CHECK_COLUMN = DBStatement.EMERGENCY.COL_NAME;
        /**
         * Emergency default sort order.
         */
        public static final String SORT_ORDER =
        		DBStatement.EMERGENCY.COL_ACTIVATED + DBStatement.DEFAULT_SORT_ORDER + DBStatement.DESC_ORDER + DBStatement.SEPARATOR_STATEMENT +
        		DBStatement.EMERGENCY.COL_END_DATE + DBStatement.DEFAULT_SORT_ORDER + DBStatement.DESC_ORDER;
    }





	/**
	 * Service Content Provider.
	 */
    public static final class SERVICE {
    	/**
    	 * Service Collection ID.
    	 */
    	public static final int ALL_SERVICES = 104;
    	/**
    	 * Service Single ID.
    	 */
    	public static final int SINGLE_SERVICE = 105;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.SERVICE.TABLE;
    	/**
    	 * Service MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
        /**
         * Service projection.
         */
        public static final String[] PROJECTION_ALL =  new String[] {
        	DBStatement.SERVICE._ID,
    		DBStatement.SERVICE.COL_TITLE,
    		DBStatement.SERVICE.COL_DESCRIPTION,
    		DBStatement.SERVICE.COL_STATUS,
    		DBStatement.SERVICE.COL_START_DATE,
    		DBStatement.SERVICE.COL_END_DATE,
    		DBStatement.SERVICE.COL_PRIORITY,
    		DBStatement.SERVICE.COL_EMERGENCY_ID
    	};
        /**
         * Service projection by index: Column ID.
         */
        public static final int INDEX_ID = 0;
        /**
         * Service projection by index: Column Title.
         */
        public static final int INDEX_TITLE = 1;
        /**
         * Service projection by index: Column Description.
         */
        public static final int INDEX_DESCRIPTION = 2;
        /**
         * Service projection by index: Column Status.
         */
        public static final int INDEX_STATUS = 3;
        /**
         * Service projection by index: Column Start Date.
         */
        public static final int INDEX_START_DATE = 4;
        /**
         * Service projection by index: Column End Date.
         */
        public static final int INDEX_END_DATE = 5;
        /**
         * Service projection by index: Column Priority.
         */
        public static final int INDEX_PRIORITY = 6;
        /**
         * Service projection by index: Column ID Emergency.
         */
        public static final int INDEX_EMERGENCY_ID = 7;
        /**
         * Service default check column.
         */
        public static final String CHECK_COLUMN = DBStatement.SERVICE.COL_TITLE;
        /**
         * Service default sort order.
         */
        public static final String SORT_ORDER =
        		DBStatement.SERVICE.COL_END_DATE + DBStatement.DEFAULT_SORT_ORDER + DBStatement.DESC_ORDER;
    }





	/**
	 * Mission Content Provider.
	 */
    public static final class MISSION {
    	/**
    	 * Mission Collection ID.
    	 */
    	public static final int ALL_MISSIONS = 108;
    	/**
    	 * Mission Single ID.
    	 */
    	public static final int SINGLE_MISSION = 109;
    	/**
    	 * Mission Collection ID for READ operation, using the view.
    	 */
    	public static final int ALL_MISSIONS_READ = 110;
    	/**
    	 * Mission Single ID for READ operation, using the view.
    	 */
    	public static final int SINGLE_MISSION_READ = 111;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.MISSION.TABLE;
    	/**
    	 * Mission MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    	/**
    	 * Read Path (VIEW).
    	 */
    	public static final String PATH_READ = DBStatement.MISSION.VIEW;
    	/**
    	 * Mission READ URI.
    	 * Create content URIs from the authority by appending path to database view.
    	 */
        public static final Uri CONTENT_URI_READ = Uri.parse("content://" + AUTHORITY + "/" + PATH_READ);
        /**
         * Mission projection.
         */
        public static final String[] PROJECTION_ALL =
        		StringFormat.duplicateRemove(
        			StringFormat.concatenateArrays(
        				SERVICE.PROJECTION_ALL,
        				new String[] {
        					DBStatement.MISSION._ID
        				}
        			)
        		);
        /**
         * Mission projection by index: Column ID.
         */
        public static final int INDEX_ID = SERVICE.INDEX_ID;
        /**
         * Mission projection by index: Column Title.
         */
        public static final int INDEX_TITLE = SERVICE.INDEX_TITLE;
        /**
         * Mission projection by index: Column Description.
         */
        public static final int INDEX_DESCRIPTION = SERVICE.INDEX_DESCRIPTION;
        /**
         * Mission projection by index: Column Status.
         */
        public static final int INDEX_STATUS = SERVICE.INDEX_STATUS;
        /**
         * Mission projection by index: Column Start Date.
         */
        public static final int INDEX_START_DATE = SERVICE.INDEX_START_DATE;
        /**
         * Mission projection by index: Column End Date.
         */
        public static final int INDEX_END_DATE = SERVICE.INDEX_END_DATE;
        /**
         * Mission projection by index: Column Priority.
         */
        public static final int INDEX_PRIORITY = SERVICE.INDEX_PRIORITY;
        /**
         * Mission projection by index: Column ID Emergency.
         */
        public static final int INDEX_EMERGENCY_ID = SERVICE.INDEX_EMERGENCY_ID;
        /**
         * Mission default check column.
         */
        public static final String CHECK_COLUMN = SERVICE.CHECK_COLUMN;
        /**
         * Mission default sort order.
         */
        public static final String SORT_ORDER =
        		SERVICE.SORT_ORDER;
    }





	/**
	 * Task Content Provider.
	 */
    public static final class TASK {
    	/**
    	 * Task Collection ID.
    	 */
    	public static final int ALL_TASKS = 112;
    	/**
    	 * Task Single ID.
    	 */
    	public static final int SINGLE_TASK = 113;
    	/**
    	 * Task Collection ID for READ operation, using the view.
    	 */
    	public static final int ALL_TASKS_READ = 114;
    	/**
    	 * Task Single ID for READ operation, using the view.
    	 */
    	public static final int SINGLE_TASK_READ = 115;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.TASK.TABLE;
    	/**
    	 * Task MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    	/**
    	 * Read Path (VIEW).
    	 */
    	public static final String PATH_READ = DBStatement.TASK.VIEW;
    	/**
    	 * Task READ URI.
    	 * Create content URIs from the authority by appending path to database view.
    	 */
        public static final Uri CONTENT_URI_READ = Uri.parse("content://" + AUTHORITY + "/" + PATH_READ);
        /**
         * Task projection.
         */
        public static final String[] PROJECTION_ALL =
        		StringFormat.duplicateRemove(
        			StringFormat.concatenateArrays(
        				SERVICE.PROJECTION_ALL,
        				new String[] {
        					DBStatement.TASK._ID,
        					DBStatement.TASK.COL_USER_ID
        				}
        			)
        		);
        /**
         * Task projection by index: Column ID.
         */
        public static final int INDEX_ID = SERVICE.INDEX_ID;
        /**
         * Task projection by index: Column Title.
         */
        public static final int INDEX_TITLE = SERVICE.INDEX_TITLE;
        /**
         * Task projection by index: Column Description.
         */
        public static final int INDEX_DESCRIPTION = SERVICE.INDEX_DESCRIPTION;
        /**
         * Task projection by index: Column Status.
         */
        public static final int INDEX_STATUS = SERVICE.INDEX_STATUS;
        /**
         * Task projection by index: Column Start Date.
         */
        public static final int INDEX_START_DATE = SERVICE.INDEX_START_DATE;
        /**
         * Task projection by index: Column End Date.
         */
        public static final int INDEX_END_DATE = SERVICE.INDEX_END_DATE;
        /**
         * Task projection by index: Column Priority.
         */
        public static final int INDEX_PRIORITY = SERVICE.INDEX_PRIORITY;
        /**
         * Task projection by index: Column ID Emergency.
         */
        public static final int INDEX_EMERGENCY_ID = SERVICE.INDEX_EMERGENCY_ID;
        /**
         * Task projection by index: Column ID User.
         */
        public static final int INDEX_USER_ID = 8;
        /**
         * Task default check column.
         */
        public static final String CHECK_COLUMN = SERVICE.CHECK_COLUMN;
        /**
         * Task default sort order.
         */
        public static final String SORT_ORDER =
        		SERVICE.SORT_ORDER;
    }





	/**
	 * Resource Content Provider.
	 */
    public static final class RESOURCE {
    	/**
    	 * Resource Collection ID.
    	 */
    	public static final int ALL_RESOURCES = 116;
    	/**
    	 * Resource Single ID.
    	 */
    	public static final int SINGLE_RESOURCE = 117;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.RESOURCE.TABLE;
    	/**
    	 * Resource MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
        /**
         * Resource projection.
         */
        public static final String[] PROJECTION_ALL =  new String[] {
        	DBStatement.RESOURCE._ID,
    		DBStatement.RESOURCE.COL_NAME,
    		DBStatement.RESOURCE.COL_STATUS,
    		DBStatement.RESOURCE.COL_DESCRIPTION
    	};
        /**
         * Resource projection by index: Column ID.
         */
        public static final int INDEX_ID = 0;
        /**
         * Resource projection by index: Column Name.
         */
        public static final int INDEX_NAME = 1;
        /**
         * Resource projection by index: Column Status.
         */
        public static final int INDEX_STATUS = 2;
        /**
         * Resource projection by index: Column Description.
         */
        public static final int INDEX_DESCRIPTION = 3;
        /**
         * Resource default check column.
         */
        public static final String CHECK_COLUMN = DBStatement.RESOURCE.COL_NAME;
        /**
         * Resource default sort order.
         */
        public static final String SORT_ORDER =
        		DBStatement.RESOURCE.COL_NAME + DBStatement.DEFAULT_SORT_ORDER + DBStatement.ASC_ORDER;
    }





	/**
	 * Equipment Content Provider.
	 */
    public static final class EQUIPMENT {
    	/**
    	 * Equipment Collection ID.
    	 */
    	public static final int ALL_EQUIPMENTS = 120;
    	/**
    	 * Equipment Single ID.
    	 */
    	public static final int SINGLE_EQUIPMENT = 121;
    	/**
    	 * Equipment Collection ID for READ operation, using the view.
    	 */
    	public static final int ALL_EQUIPMENTS_READ = 122;
    	/**
    	 * Equipment Single ID for READ operation, using the view.
    	 */
    	public static final int SINGLE_EQUIPMENT_READ = 123;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.EQUIPMENT.TABLE;
    	/**
    	 * Equipment MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    	/**
    	 * Read Path (VIEW).
    	 */
    	public static final String PATH_READ = DBStatement.EQUIPMENT.VIEW;
    	/**
    	 * Equipment READ URI.
    	 * Create content URIs from the authority by appending path to database view.
    	 */
        public static final Uri CONTENT_URI_READ = Uri.parse("content://" + AUTHORITY + "/" + PATH_READ);
        /**
         * Equipment projection.
         */
        public static final String[] PROJECTION_ALL =
        		StringFormat.duplicateRemove(
            			StringFormat.concatenateArrays(
            				RESOURCE.PROJECTION_ALL,
            				new String[] {
            					DBStatement.EQUIPMENT._ID
            				}
            			)
            		);
        /**
         * Resource projection by index: Column ID.
         */
        public static final int INDEX_ID = RESOURCE.INDEX_ID;
        /**
         * Resource projection by index: Column Name.
         */
        public static final int INDEX_NAME = RESOURCE.INDEX_NAME;
        /**
         * Resource projection by index: Column Status.
         */
        public static final int INDEX_STATUS = RESOURCE.INDEX_STATUS;
        /**
         * Resource projection by index: Column Description.
         */
        public static final int INDEX_DESCRIPTION = RESOURCE.INDEX_DESCRIPTION;
        /**
         * Equipment default check column.
         */
        public static final String CHECK_COLUMN = RESOURCE.CHECK_COLUMN;
        /**
         * Equipment default sort order.
         */
        public static final String SORT_ORDER =
        		RESOURCE.SORT_ORDER;
    }





	/**
	 * Entity Content Provider.
	 */
    public static final class ENTITY {
    	/**
    	 * Entity Collection ID.
    	 */
    	public static final int ALL_ENTITIES = 124;
    	/**
    	 * Entity Single ID.
    	 */
    	public static final int SINGLE_ENTITY = 125;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.ENTITY.TABLE;
    	/**
    	 * Entity MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
        /**
         * Entity projection.
         */
        public static final String[] PROJECTION_ALL =  new String[] {
        	DBStatement.ENTITY._ID,
    		DBStatement.ENTITY.COL_NAME,
    		DBStatement.ENTITY.COL_LOCATION_ID
    	};
        /**
         * Entity projection by index: Column ID.
         */
        public static final int INDEX_ID = 0;
        /**
         * Entity projection by index: Column Name.
         */
        public static final int INDEX_NAME = 1;
        /**
         * Entity projection by index: Column ID Location.
         */
        public static final int INDEX_LOCATION_ID = 2;
        /**
         * Entity default check column.
         */
        public static final String CHECK_COLUMN = DBStatement.ENTITY.COL_NAME;
        /**
         * Entity default sort order.
         */
        public static final String SORT_ORDER =
        		DBStatement.ENTITY.COL_NAME + DBStatement.DEFAULT_SORT_ORDER + DBStatement.ASC_ORDER;
    }





	/**
	 * Organization Content Provider.
	 */
    public static final class ORGANIZATION {
    	/**
    	 * Organization Collection ID.
    	 */
    	public static final int ALL_ORGANIZATIONS = 128;
    	/**
    	 * Organization Single ID.
    	 */
    	public static final int SINGLE_ORGANIZATION = 129;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.ORGANIZATION.TABLE;
    	/**
    	 * Organization MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
        /**
         * Organization projection.
         */
        public static final String[] PROJECTION_ALL =
        		StringFormat.duplicateRemove(
            			StringFormat.concatenateArrays(
            				ENTITY.PROJECTION_ALL,
            				new String[] {
            					DBStatement.ORGANIZATION._ID,
            			    	DBStatement.ORGANIZATION.COL_DESCRIPTION,
            			    	DBStatement.ORGANIZATION.COL_LEVEL
            				}
            			)
            		);
        /**
         * Organization projection by index: Column ID.
         */
        public static final int INDEX_ID = ENTITY.INDEX_ID;
        /**
         * Organization projection by index: Column Name.
         */
        public static final int INDEX_NAME = ENTITY.INDEX_NAME;
        /**
         * Organization projection by index: Column ID Location.
         */
        public static final int INDEX_LOCATION_ID = ENTITY.INDEX_LOCATION_ID;
        /**
         * Organization projection by index: Column Description.
         */
        public static final int INDEX_DESCRIPTION = 3;
        /**
         * Organization projection by index: Column Level.
         */
        public static final int INDEX_LEVEL = 4;
        /**
         * Organization default check column.
         */
        public static final String CHECK_COLUMN = ENTITY.CHECK_COLUMN;
        /**
         * Organization default sort order.
         */
        public static final String SORT_ORDER =
        		ENTITY.SORT_ORDER;
    }





	/**
	 * Health Care Unit Content Provider.
	 */
    public static final class HEALTH_CARE_UNIT {
    	/**
    	 * Health Care Unit Collection ID.
    	 */
    	public static final int ALL_HEALTH_CARE_UNITS = 132;
    	/**
    	 * Health Care Unit Single ID.
    	 */
    	public static final int SINGLE_HEALTH_CARE_UNIT = 133;
    	/**
    	 * Health Care Unit Collection ID for READ operation, using the view.
    	 */
    	public static final int ALL_HEALTH_CARE_UNITS_READ = 134;
    	/**
    	 * Health Care Unit Single ID for READ operation, using the view.
    	 */
    	public static final int SINGLE_HEALTH_CARE_UNIT_READ = 135;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.HEALTH_CARE_UNIT.TABLE;
    	/**
    	 * Health Care Unit MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    	/**
    	 * Read Path (VIEW).
    	 */
    	public static final String PATH_READ = DBStatement.HEALTH_CARE_UNIT.VIEW;
    	/**
    	 * Health Care Unit READ URI.
    	 * Create content URIs from the authority by appending path to database view.
    	 */
        public static final Uri CONTENT_URI_READ = Uri.parse("content://" + AUTHORITY + "/" + PATH_READ);
        /**
         * Health Care Unit projection.
         */
        public static final String[] PROJECTION_ALL =
        		StringFormat.duplicateRemove(
            			StringFormat.concatenateArrays(
            				ORGANIZATION.PROJECTION_ALL,
            				new String[] {
            					DBStatement.HEALTH_CARE_UNIT._ID,
        			    		DBStatement.HEALTH_CARE_UNIT.COL_CAPACITY,
        			    		DBStatement.HEALTH_CARE_UNIT.COL_COMMANDER_ID,
        			    		DBStatement.HEALTH_CARE_UNIT.COL_MEDICAL_ID,
        			    		DBStatement.HEALTH_CARE_UNIT.COL_INTERESTPOINT_ID
            				}
            			)
            		);
        /**
         * Health Care Unit projection by index: Column ID.
         */
        public static final int INDEX_ID = ENTITY.INDEX_ID;
        /**
         * Health Care Unit projection by index: Column Name.
         */
        public static final int INDEX_NAME = ENTITY.INDEX_NAME;
        /**
         * Health Care Unit projection by index: Column ID Location.
         */
        public static final int INDEX_LOCATION_ID = ENTITY.INDEX_LOCATION_ID;
        /**
         * Health Care Unit projection by index: Column Description.
         */
        public static final int INDEX_DESCRIPTION = ORGANIZATION.INDEX_DESCRIPTION;
        /**
         * Health Care Unit projection by index: Column Level.
         */
        public static final int INDEX_LEVEL = ORGANIZATION.INDEX_LEVEL;
        /**
         * Health Care Unit projection by index: Column Capacity.
         */
        public static final int INDEX_CAPACITY = 5;
        /**
         * Health Care Unit projection by index: Column ID Commander.
         */
        public static final int INDEX_COMMANDER_ID = 6;
        /**
         * Health Care Unit projection by index: Column ID Medical.
         */
        public static final int INDEX_MEDICAL_ID = 7;
        /**
         * Health Care Unit projection by index: Column ID Interest Point.
         */
        public static final int INDEX_INTERESTPOINT_ID = 8;
        /**
         * Health Care Unit default check column.
         */
        public static final String CHECK_COLUMN = ENTITY.CHECK_COLUMN;
        /**
         * Health Care Unit default sort order.
         */
        public static final String SORT_ORDER =
        		ENTITY.SORT_ORDER;
    }





	/**
	 * Affected Organization Content Provider.
	 */
    public static final class AFFECTED_ORGANIZATION {
    	/**
    	 * Affected Organization Collection ID.
    	 */
    	public static final int ALL_AFFECTED_ORGANIZATIONS = 136;
    	/**
    	 * Affected Organization Single ID.
    	 */
    	public static final int SINGLE_AFFECTED_ORGANIZATION = 137;
    	/**
    	 * Affected Organization Collection ID for READ operation, using the view.
    	 */
    	public static final int ALL_AFFECTED_ORGANIZATIONS_READ = 138;
    	/**
    	 * Affected Organization Single ID for READ operation, using the view.
    	 */
    	public static final int SINGLE_AFFECTED_ORGANIZATION_READ = 139;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.AFFECTED_ORGANIZATION.TABLE;
    	/**
    	 * Affected Organization MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    	/**
    	 * Read Path (VIEW).
    	 */
    	public static final String PATH_READ = DBStatement.AFFECTED_ORGANIZATION.VIEW;
    	/**
    	 * Affected Organization READ URI.
    	 * Create content URIs from the authority by appending path to database view.
    	 */
        public static final Uri CONTENT_URI_READ = Uri.parse("content://" + AUTHORITY + "/" + PATH_READ);
        /**
         * Affected Organization projection.
         */
        public static final String[] PROJECTION_ALL =
        		StringFormat.duplicateRemove(
            			StringFormat.concatenateArrays(
            				ORGANIZATION.PROJECTION_ALL,
            				new String[] {
            					DBStatement.AFFECTED_ORGANIZATION._ID,
        			    		DBStatement.AFFECTED_ORGANIZATION.COL_STATUS,
        			    		DBStatement.AFFECTED_ORGANIZATION.COL_WITNESS_ID,
        			    		DBStatement.AFFECTED_ORGANIZATION.COL_INTERESTPOINT_ID
            				}
            			)
            		);
        /**
         * Health Care Unit projection by index: Column ID.
         */
        public static final int INDEX_ID = ENTITY.INDEX_ID;
        /**
         * Health Care Unit projection by index: Column Name.
         */
        public static final int INDEX_NAME = ENTITY.INDEX_NAME;
        /**
         * Health Care Unit projection by index: Column ID Location.
         */
        public static final int INDEX_LOCATION_ID = ENTITY.INDEX_LOCATION_ID;
        /**
         * Health Care Unit projection by index: Column Description.
         */
        public static final int INDEX_DESCRIPTION = ORGANIZATION.INDEX_DESCRIPTION;
        /**
         * Health Care Unit projection by index: Column Level.
         */
        public static final int INDEX_LEVEL = ORGANIZATION.INDEX_LEVEL;
        /**
         * Health Care Unit projection by index: Column Status.
         */
        public static final int INDEX_STATUS = 5;
        /**
         * Health Care Unit projection by index: Column ID Witness.
         */
        public static final int INDEX_WITNESS_ID = 6;
        /**
         * Health Care Unit projection by index: Column ID Interest Point.
         */
        public static final int INDEX_INTERESTPOINT_ID = 7;
        /**
         * Affected Organization default check column.
         */
        public static final String CHECK_COLUMN = ENTITY.CHECK_COLUMN;
        /**
         * Affected Organization default sort order.
         */
        public static final String SORT_ORDER =
        		ENTITY.SORT_ORDER;
    }





	/**
	 * Person Content Provider.
	 */
    public static final class PERSON {
    	/**
    	 * Person Collection ID.
    	 */
    	public static final int ALL_PEOPLE = 140;
    	/**
    	 * Person Single ID.
    	 */
    	public static final int SINGLE_PERSON = 141;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.PERSON.TABLE;
    	/**
    	 * Person MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
        /**
         * Person projection.
         */
        public static final String[] PROJECTION_ALL =
        		StringFormat.duplicateRemove(
            			StringFormat.concatenateArrays(
            				ENTITY.PROJECTION_ALL,
            				new String[] {
            					DBStatement.PERSON._ID,
            			    	DBStatement.PERSON.COL_GENDER,
            			    	DBStatement.PERSON.COL_BIRTHDATE,
            			    	DBStatement.PERSON.COL_AGE
            				}
            			)
            		);
        /**
         * Person projection by index: Column ID.
         */
        public static final int INDEX_ID = ENTITY.INDEX_ID;
        /**
         * Person projection by index: Column Name.
         */
        public static final int INDEX_NAME = ENTITY.INDEX_NAME;
        /**
         * Person projection by index: Column ID Location.
         */
        public static final int INDEX_LOCATION_ID = ENTITY.INDEX_LOCATION_ID;
        /**
         * Person projection by index: Column Gender.
         */
        public static final int INDEX_GENDER = 3;
        /**
         * Person projection by index: Column Birth Date.
         */
        public static final int INDEX_BIRTHDATE = 4;
        /**
         * Person projection by index: Column Age.
         */
        public static final int INDEX_AGE = 5;
        /**
         * Person default check column.
         */
        public static final String CHECK_COLUMN = ENTITY.CHECK_COLUMN;
        /**
         * Person default sort order.
         */
        public static final String SORT_ORDER =
        		ENTITY.SORT_ORDER;
    }





	/**
	 * User Content Provider.
	 */
    public static final class USER {
    	/**
    	 * User Collection ID.
    	 */
    	public static final int ALL_USERS = 144;
    	/**
    	 * User Single ID.
    	 */
    	public static final int SINGLE_USER = 145;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.USER.TABLE;
    	/**
    	 * User MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
        /**
         * User projection.
         */
        public static final String[] PROJECTION_ALL =
        		StringFormat.duplicateRemove(
            			StringFormat.concatenateArrays(
            				PERSON.PROJECTION_ALL,
            				new String[] {
            					DBStatement.USER._ID,
            			    	DBStatement.USER.COL_LOGIN,
            			    	DBStatement.USER.COL_PASSWORD
            				}
            			)
            		);
        /**
         * User projection by index: Column ID.
         */
        public static final int INDEX_ID = ENTITY.INDEX_ID;
        /**
         * User projection by index: Column Name.
         */
        public static final int INDEX_NAME = ENTITY.INDEX_NAME;
        /**
         * User projection by index: Column ID Location.
         */
        public static final int INDEX_LOCATION_ID = ENTITY.INDEX_LOCATION_ID;
        /**
         * User projection by index: Column Gender.
         */
        public static final int INDEX_GENDER = PERSON.INDEX_GENDER;
        /**
         * User projection by index: Column Birth Date.
         */
        public static final int INDEX_BIRTHDATE = PERSON.INDEX_BIRTHDATE;
        /**
         * User projection by index: Column Age.
         */
        public static final int INDEX_AGE = PERSON.INDEX_AGE;
        /**
         * User projection by index: Column Login.
         */
        public static final int INDEX_LOGIN = 6;
        /**
         * User projection by index: Column Password.
         */
        public static final int INDEX_PASSWORD = 7;
        /**
         * User default check column.
         */
        public static final String CHECK_COLUMN = ENTITY.CHECK_COLUMN;
        /**
         * User default sort order.
         */
        public static final String SORT_ORDER =
        		ENTITY.SORT_ORDER;
    }





	/**
	 * Commander Content Provider.
	 */
    public static final class COMMANDER {
    	/**
    	 * Commander Collection ID.
    	 */
    	public static final int ALL_COMMANDERS = 148;
    	/**
    	 * Commander Single ID.
    	 */
    	public static final int SINGLE_COMMANDER = 149;
    	/**
    	 * Commander Collection ID for READ operation, using the view.
    	 */
    	public static final int ALL_COMMANDERS_READ = 150;
    	/**
    	 * Commander Single ID for READ operation, using the view.
    	 */
    	public static final int SINGLE_COMMANDER_READ = 151;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.COMMANDER.TABLE;
    	/**
    	 * Commander MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    	/**
    	 * Read Path (VIEW).
    	 */
    	public static final String PATH_READ = DBStatement.COMMANDER.VIEW;
    	/**
    	 * Commander READ URI.
    	 * Create content URIs from the authority by appending path to database view.
    	 */
        public static final Uri CONTENT_URI_READ = Uri.parse("content://" + AUTHORITY + "/" + PATH_READ);
        /**
         * Commander projection.
         */
        public static final String[] PROJECTION_ALL =
        		StringFormat.duplicateRemove(
            			StringFormat.concatenateArrays(
            				USER.PROJECTION_ALL,
            				new String[] {
            					DBStatement.COMMANDER._ID
            				}
            			)
            		);
        /**
         * Commander projection by index: Column ID.
         */
        public static final int INDEX_ID = ENTITY.INDEX_ID;
        /**
         * Commander projection by index: Column Name.
         */
        public static final int INDEX_NAME = ENTITY.INDEX_NAME;
        /**
         * Commander projection by index: Column ID Location.
         */
        public static final int INDEX_LOCATION_ID = ENTITY.INDEX_LOCATION_ID;
        /**
         * Commander projection by index: Column Gender.
         */
        public static final int INDEX_GENDER = PERSON.INDEX_GENDER;
        /**
         * Commander projection by index: Column Birth Date.
         */
        public static final int INDEX_BIRTHDATE = PERSON.INDEX_BIRTHDATE;
        /**
         * Commander projection by index: Column Age.
         */
        public static final int INDEX_AGE = PERSON.INDEX_AGE;
        /**
         * Commander projection by index: Column Login.
         */
        public static final int INDEX_LOGIN = USER.INDEX_LOGIN;
        /**
         * Commander projection by index: Column Password.
         */
        public static final int INDEX_PASSWORD = USER.INDEX_PASSWORD;
        /**
         * Commander default check column.
         */
        public static final String CHECK_COLUMN = ENTITY.CHECK_COLUMN;
        /**
         * Commander default sort order.
         */
        public static final String SORT_ORDER =
        		ENTITY.SORT_ORDER;
    }







	/**
	 * Responder Content Provider.
	 */
    public static final class RESPONDER {
    	/**
    	 * Responder Collection ID.
    	 */
    	public static final int ALL_RESPONDERS = 152;
    	/**
    	 * Responder Single ID.
    	 */
    	public static final int SINGLE_RESPONDER = 153;
    	/**
    	 * Responder Collection ID for READ operation, using the view.
    	 */
    	public static final int ALL_RESPONDERS_READ = 154;
    	/**
    	 * Responder Single ID for READ operation, using the view.
    	 */
    	public static final int SINGLE_RESPONDER_READ = 155;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.RESPONDER.TABLE;
    	/**
    	 * Responder MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    	/**
    	 * Read Path (VIEW).
    	 */
    	public static final String PATH_READ = DBStatement.RESPONDER.VIEW;
    	/**
    	 * Responder READ URI.
    	 * Create content URIs from the authority by appending path to database view.
    	 */
        public static final Uri CONTENT_URI_READ = Uri.parse("content://" + AUTHORITY + "/" + PATH_READ);
        /**
         * Responder projection.
         */
        public static final String[] PROJECTION_ALL =
        		StringFormat.duplicateRemove(
            			StringFormat.concatenateArrays(
            				USER.PROJECTION_ALL,
            				new String[] {
            					DBStatement.RESPONDER._ID
            				}
            			)
            		);
        /**
         * Responder projection by index: Column ID.
         */
        public static final int INDEX_ID = ENTITY.INDEX_ID;
        /**
         * Responder projection by index: Column Name.
         */
        public static final int INDEX_NAME = ENTITY.INDEX_NAME;
        /**
         * Responder projection by index: Column ID Location.
         */
        public static final int INDEX_LOCATION_ID = ENTITY.INDEX_LOCATION_ID;
        /**
         * Responder projection by index: Column Gender.
         */
        public static final int INDEX_GENDER = PERSON.INDEX_GENDER;
        /**
         * Responder projection by index: Column Birth Date.
         */
        public static final int INDEX_BIRTHDATE = PERSON.INDEX_BIRTHDATE;
        /**
         * Responder projection by index: Column Age.
         */
        public static final int INDEX_AGE = PERSON.INDEX_AGE;
        /**
         * Responder projection by index: Column Login.
         */
        public static final int INDEX_LOGIN = USER.INDEX_LOGIN;
        /**
         * Responder projection by index: Column Password.
         */
        public static final int INDEX_PASSWORD = USER.INDEX_PASSWORD;
        /**
         * Responder default check column.
         */
        public static final String CHECK_COLUMN = ENTITY.CHECK_COLUMN;
        /**
         * Responder default sort order.
         */
        public static final String SORT_ORDER =
        		ENTITY.SORT_ORDER;
    }





	/**
	 * Medical Content Provider.
	 */
    public static final class MEDICAL {
    	/**
    	 * Medical Collection ID.
    	 */
    	public static final int ALL_MEDICALS = 156;
    	/**
    	 * Medical Single ID.
    	 */
    	public static final int SINGLE_MEDICAL = 157;
    	/**
    	 * Medical Collection ID for READ operation, using the view.
    	 */
    	public static final int ALL_MEDICALS_READ = 158;
    	/**
    	 * Medical Single ID for READ operation, using the view.
    	 */
    	public static final int SINGLE_MEDICAL_READ = 159;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.MEDICAL.TABLE;
    	/**
    	 * Medical MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    	/**
    	 * Read Path (VIEW).
    	 */
    	public static final String PATH_READ = DBStatement.MEDICAL.VIEW;
    	/**
    	 * Medical READ URI.
    	 * Create content URIs from the authority by appending path to database view.
    	 */
        public static final Uri CONTENT_URI_READ = Uri.parse("content://" + AUTHORITY + "/" + PATH_READ);
        /**
         * Medical projection.
         */
        public static final String[] PROJECTION_ALL =
        		StringFormat.duplicateRemove(
            			StringFormat.concatenateArrays(
            				USER.PROJECTION_ALL,
            				new String[] {
            					DBStatement.MEDICAL._ID
            				}
            			)
            		);
        /**
         * Medical projection by index: Column ID.
         */
        public static final int INDEX_ID = ENTITY.INDEX_ID;
        /**
         * Medical projection by index: Column Name.
         */
        public static final int INDEX_NAME = ENTITY.INDEX_NAME;
        /**
         * Medical projection by index: Column ID Location.
         */
        public static final int INDEX_LOCATION_ID = ENTITY.INDEX_LOCATION_ID;
        /**
         * Medical projection by index: Column Gender.
         */
        public static final int INDEX_GENDER = PERSON.INDEX_GENDER;
        /**
         * Medical projection by index: Column Birth Date.
         */
        public static final int INDEX_BIRTHDATE = PERSON.INDEX_BIRTHDATE;
        /**
         * Medical projection by index: Column Age.
         */
        public static final int INDEX_AGE = PERSON.INDEX_AGE;
        /**
         * Medical projection by index: Column Login.
         */
        public static final int INDEX_LOGIN = USER.INDEX_LOGIN;
        /**
         * Medical projection by index: Column Password.
         */
        public static final int INDEX_PASSWORD = USER.INDEX_PASSWORD;
        /**
         * Medical default check column.
         */
        public static final String CHECK_COLUMN = ENTITY.CHECK_COLUMN;
        /**
         * Medical default sort order.
         */
        public static final String SORT_ORDER =
        		ENTITY.SORT_ORDER;
    }





	/**
	 * Volunteer Content Provider.
	 */
    public static final class VOLUNTEER {
    	/**
    	 * Volunteer Collection ID.
    	 */
    	public static final int ALL_VOLUNTEERS = 160;
    	/**
    	 * Volunteer Single ID.
    	 */
    	public static final int SINGLE_VOLUNTEER = 161;
    	/**
    	 * Volunteer Collection ID for READ operation, using the view.
    	 */
    	public static final int ALL_VOLUNTEERS_READ = 162;
    	/**
    	 * Volunteer Single ID for READ operation, using the view.
    	 */
    	public static final int SINGLE_VOLUNTEER_READ = 163;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.VOLUNTEER.TABLE;
    	/**
    	 * Volunteer MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    	/**
    	 * Read Path (VIEW).
    	 */
    	public static final String PATH_READ = DBStatement.VOLUNTEER.VIEW;
    	/**
    	 * Volunteer READ URI.
    	 * Create content URIs from the authority by appending path to database view.
    	 */
        public static final Uri CONTENT_URI_READ = Uri.parse("content://" + AUTHORITY + "/" + PATH_READ);
        /**
         * Volunteer projection.
         */
        public static final String[] PROJECTION_ALL =
        		StringFormat.duplicateRemove(
            			StringFormat.concatenateArrays(
            				USER.PROJECTION_ALL,
            				new String[] {
            					DBStatement.VOLUNTEER._ID
            				}
            			)
            		);
        /**
         * Volunteer projection by index: Column ID.
         */
        public static final int INDEX_ID = ENTITY.INDEX_ID;
        /**
         * Volunteer projection by index: Column Name.
         */
        public static final int INDEX_NAME = ENTITY.INDEX_NAME;
        /**
         * Volunteer projection by index: Column ID Location.
         */
        public static final int INDEX_LOCATION_ID = ENTITY.INDEX_LOCATION_ID;
        /**
         * Volunteer projection by index: Column Gender.
         */
        public static final int INDEX_GENDER = PERSON.INDEX_GENDER;
        /**
         * Volunteer projection by index: Column Birth Date.
         */
        public static final int INDEX_BIRTHDATE = PERSON.INDEX_BIRTHDATE;
        /**
         * Volunteer projection by index: Column Age.
         */
        public static final int INDEX_AGE = PERSON.INDEX_AGE;
        /**
         * Volunteer projection by index: Column Login.
         */
        public static final int INDEX_LOGIN = USER.INDEX_LOGIN;
        /**
         * Volunteer projection by index: Column Password.
         */
        public static final int INDEX_PASSWORD = USER.INDEX_PASSWORD;
        /**
         * Volunteer default check column.
         */
        public static final String CHECK_COLUMN = ENTITY.CHECK_COLUMN;
        /**
         * Volunteer default sort order.
         */
        public static final String SORT_ORDER =
        		ENTITY.SORT_ORDER;
    }





	/**
	 * Witness Content Provider.
	 */
    public static final class WITNESS {
       	/**
    	 * Witness Collection ID.
    	 */
    	public static final int ALL_WITNESSES = 164;
    	/**
    	 * Witness Single ID.
    	 */
    	public static final int SINGLE_WITNESS = 165;
    	/**
    	 * Witness Collection ID for READ operation, using the view.
    	 */
    	public static final int ALL_WITNESSES_READ = 166;
    	/**
    	 * Witness Single ID for READ operation, using the view.
    	 */
    	public static final int SINGLE_WITNESS_READ = 167;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.WITNESS.TABLE;
    	/**
    	 * Witness MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    	/**
    	 * Read Path (VIEW).
    	 */
    	public static final String PATH_READ = DBStatement.WITNESS.VIEW;
    	/**
    	 * Witness READ URI.
    	 * Create content URIs from the authority by appending path to database view.
    	 */
        public static final Uri CONTENT_URI_READ = Uri.parse("content://" + AUTHORITY + "/" + PATH_READ);
        /**
         * Witness projection.
         */
        public static final String[] PROJECTION_ALL =
        		StringFormat.duplicateRemove(
            			StringFormat.concatenateArrays(
            				PERSON.PROJECTION_ALL,
            				new String[] {
            					DBStatement.WITNESS._ID
            				}
            			)
            		);
        /**
         * Witness projection by index: Column ID.
         */
        public static final int INDEX_ID = ENTITY.INDEX_ID;
        /**
         * Witness projection by index: Column Name.
         */
        public static final int INDEX_NAME = ENTITY.INDEX_NAME;
        /**
         * Witness projection by index: Column ID Location.
         */
        public static final int INDEX_LOCATION_ID = ENTITY.INDEX_LOCATION_ID;
        /**
         * Witness projection by index: Column Gender.
         */
        public static final int INDEX_GENDER = PERSON.INDEX_GENDER;
        /**
         * Witness projection by index: Column Birth Date.
         */
        public static final int INDEX_BIRTHDATE = PERSON.INDEX_BIRTHDATE;
        /**
         * Witness projection by index: Column Age.
         */
        public static final int INDEX_AGE = PERSON.INDEX_AGE;
        /**
         * Witness default check column.
         */
        public static final String CHECK_COLUMN = ENTITY.CHECK_COLUMN;
        /**
         * Witness default sort order.
         */
        public static final String SORT_ORDER =
        		ENTITY.SORT_ORDER;
    }





	/**
	 * Victim Content Provider.
	 */
    public static final class VICTIM {
       	/**
    	 * Victim Collection ID.
    	 */
    	public static final int ALL_VICTIMS = 168;
    	/**
    	 * Victim Single ID.
    	 */
    	public static final int SINGLE_VICTIM = 169;
    	/**
    	 * Victim Collection ID for READ operation, using the view.
    	 */
    	public static final int ALL_VICTIMS_READ = 170;
    	/**
    	 * Victim Single ID for READ operation, using the view.
    	 */
    	public static final int SINGLE_VICTIM_READ = 171;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.VICTIM.TABLE;
    	/**
    	 * Victim MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    	/**
    	 * Read Path (VIEW).
    	 */
    	public static final String PATH_READ = DBStatement.VICTIM.VIEW;
    	/**
    	 * Victim READ URI.
    	 * Create content URIs from the authority by appending path to database view.
    	 */
        public static final Uri CONTENT_URI_READ = Uri.parse("content://" + AUTHORITY + "/" + PATH_READ);
        /**
         * Victim projection.
         */
        public static final String[] PROJECTION_ALL =
        		StringFormat.duplicateRemove(
            			StringFormat.concatenateArrays(
            				PERSON.PROJECTION_ALL,
            				new String[] {
            					DBStatement.VICTIM._ID,
            			    	DBStatement.VICTIM.COL_STATUS,
            			    	DBStatement.VICTIM.COL_HEALTHCAREUNIT_ID,
            			    	DBStatement.VICTIM.COL_AFFECTEDORGANIZATION_ID,
            			    	DBStatement.VICTIM.COL_WITNESS_ID
            				}
            			)
            		);
        /**
         * Victim projection by index: Column ID.
         */
        public static final int INDEX_ID = ENTITY.INDEX_ID;
        /**
         * Victim projection by index: Column Name.
         */
        public static final int INDEX_NAME = ENTITY.INDEX_NAME;
        /**
         * Victim projection by index: Column ID Location.
         */
        public static final int INDEX_LOCATION_ID = ENTITY.INDEX_LOCATION_ID;
        /**
         * Victim projection by index: Column Gender.
         */
        public static final int INDEX_GENDER = PERSON.INDEX_GENDER;
        /**
         * Victim projection by index: Column Birth Date.
         */
        public static final int INDEX_BIRTHDATE = PERSON.INDEX_BIRTHDATE;
        /**
         * Victim projection by index: Column Age.
         */
        public static final int INDEX_AGE = PERSON.INDEX_AGE;
        /**
         * Victim projection by index: Column Status.
         */
        public static final int INDEX_STATUS = 6;
        /**
         * Victim projection by index: Column ID Health Care Unit.
         */
        public static final int INDEX_HEALTHCAREUNIT_ID = 7;
        /**
         * Victim projection by index: Column ID Affected Organization.
         */
        public static final int INDEX_AFFECTEDORGANIZATION_ID = 8;
        /**
         * Victim projection by index: Column ID Witness.
         */
        public static final int INDEX_WITNESS_ID = 9;
        /**
         * Victim default check column.
         */
        public static final String CHECK_COLUMN = ENTITY.CHECK_COLUMN;
        /**
         * Victim default sort order.
         */
        public static final String SORT_ORDER =
        		ENTITY.SORT_ORDER;
    }





	/**
	 * Team Content Provider.
	 */
    public static final class TEAM {
    	/**
    	 * Team Collection ID.
    	 */
    	public static final int ALL_TEAMS = 172;
    	/**
    	 * Team Single ID.
    	 */
    	public static final int SINGLE_TEAM = 173;
    	/**
    	 * Team Collection ID for READ operation, using the view.
    	 */
    	public static final int ALL_TEAMS_READ = 174;
    	/**
    	 * Team Single ID for READ operation, using the view.
    	 */
    	public static final int SINGLE_TEAM_READ = 175;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.TEAM.TABLE;
    	/**
    	 * Team MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    	/**
    	 * Read Path (VIEW).
    	 */
    	public static final String PATH_READ = DBStatement.TEAM.VIEW;
    	/**
    	 * Team READ URI.
    	 * Create content URIs from the authority by appending path to database view.
    	 */
        public static final Uri CONTENT_URI_READ = Uri.parse("content://" + AUTHORITY + "/" + PATH_READ);
        /**
         * Team projection.
         */
        public static final String[] PROJECTION_ALL =  new String[] {
        	DBStatement.TEAM._ID,
    		DBStatement.TEAM.COL_NAME
    	};
        /**
         * Team projection by index: Column ID.
         */
        public static final int INDEX_ID = 0;
        /**
         * Team projection by index: Column Name.
         */
        public static final int INDEX_NAME = 1;
        /**
         * Team default check column.
         */
        public static final String CHECK_COLUMN = DBStatement.TEAM.COL_NAME;
        /**
         * Team default sort order.
         */
        public static final String SORT_ORDER =
        		DBStatement.TEAM.COL_NAME + DBStatement.DEFAULT_SORT_ORDER + DBStatement.ASC_ORDER;
    }





	/**
	 * Position Content Provider.
	 */
    public static final class POSITION {
    	/**
    	 * Position Collection ID.
    	 */
    	public static final int ALL_POSITIONS = 176;
    	/**
    	 * Position Single ID.
    	 */
    	public static final int SINGLE_POSITION = 177;
    	/**
    	 * Position Collection ID for READ operation, using the view.
    	 */
    	public static final int ALL_POSITIONS_READ = 178;
    	/**
    	 * Position Single ID for READ operation, using the view.
    	 */
    	public static final int SINGLE_POSITION_READ = 179;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.POSITION.TABLE;
    	/**
    	 * Position MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    	/**
    	 * Read Path (VIEW).
    	 */
    	public static final String PATH_READ = DBStatement.POSITION.VIEW;
    	/**
    	 * Position READ URI.
    	 * Create content URIs from the authority by appending path to database view.
    	 */
        public static final Uri CONTENT_URI_READ = Uri.parse("content://" + AUTHORITY + "/" + PATH_READ);
        /**
         * Position projection.
         */
        public static final String[] PROJECTION_ALL =  new String[] {
        	DBStatement.POSITION._ID,
    		DBStatement.POSITION.COL_LATITUDE,
    		DBStatement.POSITION.COL_LONGITUDE,
    		DBStatement.POSITION.COL_LOCATION_ID
    	};
        /**
         * Position projection by index: Column ID.
         */
        public static final int INDEX_ID = 0;
        /**
         * Position projection by index: Column Latitude.
         */
        public static final int INDEX_LATITUDE = 1;
        /**
         * Position projection by index: Column Longitude.
         */
        public static final int INDEX_LONGITUDE = 2;
        /**
         * Position projection by index: Column ID Location.
         */
        public static final int INDEX_LOCATION_ID = 3;
        /**
         * Position default check column.
         */
        public static final String CHECK_COLUMN = DBStatement.POSITION.COL_LOCATION_ID;
        /**
         * Position default sort order.
         */
        public static final String SORT_ORDER =
        		DBStatement.POSITION.COL_LOCATION_ID + DBStatement.DEFAULT_SORT_ORDER + DBStatement.ASC_ORDER;
    }





	/**
	 * Address Content Provider.
	 */
    public static final class ADDRESS {
    	/**
    	 * Address Collection ID.
    	 */
    	public static final int ALL_ADDRESSES = 180;
    	/**
    	 * Address Single ID.
    	 */
    	public static final int SINGLE_ADDRESS = 181;
    	/**
    	 * Address Collection ID for READ operation, using the view.
    	 */
    	public static final int ALL_ADDRESSES_READ = 182;
    	/**
    	 * Address Single ID for READ operation, using the view.
    	 */
    	public static final int SINGLE_ADDRESS_READ = 183;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.ADDRESS.TABLE;
    	/**
    	 * Address MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    	/**
    	 * Read Path (VIEW).
    	 */
    	public static final String PATH_READ = DBStatement.ADDRESS.VIEW;
    	/**
    	 * Address READ URI.
    	 * Create content URIs from the authority by appending path to database view.
    	 */
        public static final Uri CONTENT_URI_READ = Uri.parse("content://" + AUTHORITY + "/" + PATH_READ);
        /**
         * Address projection.
         */
        public static final String[] PROJECTION_ALL =  new String[] {
        	DBStatement.ADDRESS._ID,
    		DBStatement.ADDRESS.COL_STREET,
    		DBStatement.ADDRESS.COL_NUMBER,
    		DBStatement.ADDRESS.COL_COMPLEMENT,
    		DBStatement.ADDRESS.COL_DISTRICT,
    		DBStatement.ADDRESS.COL_CITY,
    		DBStatement.ADDRESS.COL_REGION,
    		DBStatement.ADDRESS.COL_COUNTRY,
    		DBStatement.ADDRESS.COL_POSTALCODE,
    		DBStatement.ADDRESS.COL_LOCATION_ID
    	};
        /**
         * Address projection by index: Column ID.
         */
        public static final int INDEX_ID = 0;
        /**
         * Address projection by index: Column Street.
         */
        public static final int INDEX_STREET = 1;
        /**
         * Address projection by index: Column Number.
         */
        public static final int INDEX_NUMBER = 2;
        /**
         * Address projection by index: Column Complement.
         */
        public static final int INDEX_COMPLEMENT = 3;
        /**
         * Address projection by index: Column District.
         */
        public static final int INDEX_DISTRICT = 4;
        /**
         * Address projection by index: Column City.
         */
        public static final int INDEX_CITY = 5;
        /**
         * Address projection by index: Column Region.
         */
        public static final int INDEX_REGION = 6;
        /**
         * Address projection by index: Column Country.
         */
        public static final int INDEX_COUNTRY = 7;
        /**
         * Address projection by index: Column Postal Code.
         */
        public static final int INDEX_POSTALCODE = 8;
        /**
         * Address projection by index: Column ID Location.
         */
        public static final int INDEX_LOCATION_ID = 9;
        /**
         * Address default check column.
         */
        public static final String CHECK_COLUMN = DBStatement.ADDRESS.COL_LOCATION_ID;
        /**
         * Address default sort order.
         */
        public static final String SORT_ORDER =
        		DBStatement.ADDRESS.COL_LOCATION_ID + DBStatement.DEFAULT_SORT_ORDER + DBStatement.ASC_ORDER;
    }





	/**
	 * Place Identifier Content Provider.
	 */
    public static final class PLACE_IDENTIFIER {
    	/**
    	 * Place Identifier Collection ID.
    	 */
    	public static final int ALL_PLACE_IDENTIFIERS = 184;
    	/**
    	 * Place Identifier Single ID.
    	 */
    	public static final int SINGLE_PLACE_IDENTIFIER = 185;
    	/**
    	 * Place Identifier Collection ID for READ operation, using the view.
    	 */
    	public static final int ALL_PLACE_IDENTIFIERS_READ = 186;
    	/**
    	 * Place Identifier Single ID for READ operation, using the view.
    	 */
    	public static final int SINGLE_PLACE_IDENTIFIER_READ = 187;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.PLACE_IDENTIFIER.TABLE;
    	/**
    	 * Place Identifier MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    	/**
    	 * Read Path (VIEW).
    	 */
    	public static final String PATH_READ = DBStatement.PLACE_IDENTIFIER.VIEW;
    	/**
    	 * Place Identifier READ URI.
    	 * Create content URIs from the authority by appending path to database view.
    	 */
        public static final Uri CONTENT_URI_READ = Uri.parse("content://" + AUTHORITY + "/" + PATH_READ);
        /**
         * Place Identifier projection.
         */
        public static final String[] PROJECTION_ALL =  new String[] {
        	DBStatement.PLACE_IDENTIFIER._ID,
    		DBStatement.PLACE_IDENTIFIER.COL_NAME,
    		DBStatement.PLACE_IDENTIFIER.COL_DESCRIPTION,
    		DBStatement.PLACE_IDENTIFIER.COL_TYPE,
    		DBStatement.PLACE_IDENTIFIER.COL_SYMBOL,
    		DBStatement.PLACE_IDENTIFIER.COL_LOCATION_ID
    	};
        /**
         * Place Identifier projection by index: Column ID.
         */
        public static final int INDEX_ID = 0;
        /**
         * Place Identifier projection by index: Column Name.
         */
        public static final int INDEX_NAME = 1;
        /**
         * Place Identifier projection by index: Column Description.
         */
        public static final int INDEX_DESCRIPTION = 2;
        /**
         * Place Identifier projection by index: Column Type.
         */
        public static final int INDEX_TYPE = 3;
        /**
         * Place Identifier projection by index: Column Symbol.
         */
        public static final int INDEX_SYMBOL = 4;
        /**
         * Place Identifier projection by index: Column ID Location.
         */
        public static final int INDEX_LOCATION_ID = 5;
        /**
         * Place Identifier default check column.
         */
        public static final String CHECK_COLUMN = DBStatement.PLACE_IDENTIFIER.COL_LOCATION_ID;
        /**
         * Place Identifier default sort order.
         */
        public static final String SORT_ORDER =
        		DBStatement.PLACE_IDENTIFIER.COL_LOCATION_ID + DBStatement.DEFAULT_SORT_ORDER + DBStatement.ASC_ORDER;
    }





	/**
	 * Location Content Provider.
	 */
    public static final class LOCATION {
    	/**
    	 * Location Collection ID.
    	 */
    	public static final int ALL_LOCATIONS = 188;
    	/**
    	 * Location Single ID.
    	 */
    	public static final int SINGLE_LOCATION = 189;
    	/**
    	 * Location Collection ID for READ operation, using the view.
    	 */
    	public static final int ALL_LOCATIONS_READ = 190;
    	/**
    	 * Location Single ID for READ operation, using the view.
    	 */
    	public static final int SINGLE_LOCATION_READ = 191;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.LOCATION.TABLE;
    	/**
    	 * Location MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    	/**
    	 * Read Path (VIEW).
    	 */
    	public static final String PATH_READ = DBStatement.LOCATION.VIEW;
    	/**
    	 * Location READ URI.
    	 * Create content URIs from the authority by appending path to database view.
    	 */
        public static final Uri CONTENT_URI_READ = Uri.parse("content://" + AUTHORITY + "/" + PATH_READ);
        /**
         * Location projection.
         */
        public static final String[] PROJECTION_ALL =  new String[] {
        	DBStatement.LOCATION._ID,
    		DBStatement.LOCATION.COL_TIMESTAMP,
    		DBStatement.LOCATION.COL_INTERVAL,
    		DBStatement.LOCATION.COL_STATUS
    	};
        /**
         * Location projection by index: Column ID.
         */
        public static final int INDEX_ID = 0;
        /**
         * Location projection by index: Column TimeStamp.
         */
        public static final int INDEX_TIMESTAMP = 1;
        /**
         * Location projection by index: Column Interval.
         */
        public static final int INDEX_INTERVAL = 2;
        /**
         * Location projection by index: Column Status.
         */
        public static final int INDEX_STATUS = 3;
        /**
         * Location default check column.
         */
        public static final String CHECK_COLUMN = DBStatement.LOCATION.COL_TIMESTAMP;
        /**
         * Location default sort order.
         */
        public static final String SORT_ORDER =
        		DBStatement.LOCATION.COL_TIMESTAMP + DBStatement.DEFAULT_SORT_ORDER + DBStatement.DESC_ORDER;
    }



	/**
	 * Interest Point Content Provider.
	 */
    public static final class INTEREST_POINT {
       	/**
    	 * Interest Point Collection ID.
    	 */
    	public static final int ALL_INTEREST_POINTS = 192;
    	/**
    	 * Interest Point Single ID.
    	 */
    	public static final int SINGLE_INTEREST_POINT = 193;
    	/**
    	 * Interest Point Collection ID for READ operation, using the view.
    	 */
    	public static final int ALL_INTEREST_POINTS_READ = 194;
    	/**
    	 * Interest Point Single ID for READ operation, using the view.
    	 */
    	public static final int SINGLE_INTEREST_POINT_READ = 195;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.INTEREST_POINT.TABLE;
    	/**
    	 * Interest Point MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    	/**
    	 * Read Path (VIEW).
    	 */
    	public static final String PATH_READ = DBStatement.INTEREST_POINT.VIEW;
    	/**
    	 * Interest Point READ URI.
    	 * Create content URIs from the authority by appending path to database view.
    	 */
        public static final Uri CONTENT_URI_READ = Uri.parse("content://" + AUTHORITY + "/" + PATH_READ);
        /**
         * Interest Point projection.
         */
        public static final String[] PROJECTION_ALL =
        		StringFormat.duplicateRemove(
            			StringFormat.concatenateArrays(
            				LOCATION.PROJECTION_ALL,
            				new String[] {
            					DBStatement.INTEREST_POINT._ID,
            			    	DBStatement.INTEREST_POINT.COL_NAME
            				}
            			)
            		);
        /**
         * Interest Point projection by index: Column ID.
         */
        public static final int INDEX_ID = LOCATION.INDEX_ID;
        /**
         * Interest Point projection by index: Column TimeStamp.
         */
        public static final int INDEX_TIMESTAMP = LOCATION.INDEX_TIMESTAMP;
        /**
         * Interest Point projection by index: Column Interval.
         */
        public static final int INDEX_INTERVAL = LOCATION.INDEX_INTERVAL;
        /**
         * Interest Point projection by index: Column Status.
         */
        public static final int INDEX_STATUS = LOCATION.INDEX_STATUS;
        /**
         * Interest Point projection by index: Column Name.
         */
        public static final int INDEX_NAME = 4;
        /**
         * Interest Point default check column.
         */
        public static final String CHECK_COLUMN = LOCATION.CHECK_COLUMN;
        /**
         * Interest Point default sort order.
         */
        public static final String SORT_ORDER =
        		LOCATION.SORT_ORDER;
    }





	/**
	 * Information Content Provider.
	 */
    public static final class INFORMATION {
    	/**
    	 * Information Collection ID.
    	 */
    	public static final int ALL_INFORMATIONS = 196;
    	/**
    	 * Information Single ID.
    	 */
    	public static final int SINGLE_INFORMATION = 197;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.INFORMATION.TABLE;
    	/**
    	 * Information MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
        /**
         * Information projection.
         */
        public static final String[] PROJECTION_ALL =  new String[] {
        	DBStatement.INFORMATION._ID,
    		DBStatement.INFORMATION.COL_CREATIONDATE,
    		DBStatement.INFORMATION.COL_LASTMODIFICATIONDATE,
    		DBStatement.INFORMATION.COL_PRIORITY,
    		DBStatement.INFORMATION.COL_EMERGENCY_ID
    	};
        /**
         * Information projection by index: Column ID.
         */
        public static final int INDEX_ID = 0;
        /**
         * Information projection by index: Column Creation Date.
         */
        public static final int INDEX_CREATIONDATE = 1;
        /**
         * Information projection by index: Column Last Modification Date.
         */
        public static final int INDEX_LASTMODIFICATIONDATE = 2;
        /**
         * Information projection by index: Column Priority.
         */
        public static final int INDEX_PRIORITY = 3;
        /**
         * Information projection by index: Column ID Emergency.
         */
        public static final int INDEX_EMERGENCY_ID = 4;
        /**
         * Information default check column.
         */
        public static final String CHECK_COLUMN = DBStatement.INFORMATION.COL_CREATIONDATE;
        /**
         * Information default sort order.
         */
        public static final String SORT_ORDER =
        		DBStatement.INFORMATION.COL_LASTMODIFICATIONDATE + DBStatement.DEFAULT_SORT_ORDER + DBStatement.DESC_ORDER;
    }





	/**
	 * Message Content Provider.
	 */
    public static final class MESSAGE {
    	/**
    	 * Message Collection ID.
    	 */
    	public static final int ALL_MESSAGES = 200;
    	/**
    	 * Message Single ID.
    	 */
    	public static final int SINGLE_MESSAGE = 201;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.MESSAGE.TABLE;
    	/**
    	 * Message MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
        /**
         * Message projection.
         */
        public static final String[] PROJECTION_ALL =
    		StringFormat.duplicateRemove(
        			StringFormat.concatenateArrays(
        				INFORMATION.PROJECTION_ALL,
        				new String[] {
        					DBStatement.MESSAGE._ID,
    			    		DBStatement.MESSAGE.COL_SUBJECT,
    			    		DBStatement.MESSAGE.COL_CONTENT
        				}
        			)
        		);
        /**
         * Message projection by index: Column ID.
         */
        public static final int INDEX_ID = INFORMATION.INDEX_ID;
        /**
         * Message projection by index: Column Creation Date.
         */
        public static final int INDEX_CREATIONDATE = INFORMATION.INDEX_CREATIONDATE;
        /**
         * Message projection by index: Column Last Modification Date.
         */
        public static final int INDEX_LASTMODIFICATIONDATE = INFORMATION.INDEX_LASTMODIFICATIONDATE;
        /**
         * Message projection by index: Column Priority.
         */
        public static final int INDEX_PRIORITY = INFORMATION.INDEX_PRIORITY;
        /**
         * Message projection by index: Column ID Emergency.
         */
        public static final int INDEX_EMERGENCY_ID = INFORMATION.INDEX_EMERGENCY_ID;
        /**
         * Message projection by index: Column Subject.
         */
        public static final int INDEX_SUBJECT = 5;
        /**
         * Message projection by index: Column Content.
         */
        public static final int INDEX_CONTENT = 6;
        /**
         * Message default check column.
         */
        public static final String CHECK_COLUMN = DBStatement.MESSAGE.COL_CONTENT;
        /**
         * Message default sort order.
         */
        public static final String SORT_ORDER =
        		INFORMATION.SORT_ORDER;
    }





	/**
	 * Text Message Content Provider.
	 */
    public static final class TEXT_MESSAGE {
    	/**
    	 * Text Message Collection ID.
    	 */
    	public static final int ALL_TEXT_MESSAGES = 204;
    	/**
    	 * Text Message Single ID.
    	 */
    	public static final int SINGLE_TEXT_MESSAGE = 205;
    	/**
    	 * Text Message Collection ID for READ operation, using the view.
    	 */
    	public static final int ALL_TEXT_MESSAGES_READ = 206;
    	/**
    	 * Text Message Single ID for READ operation, using the view.
    	 */
    	public static final int SINGLE_TEXT_MESSAGE_READ = 207;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.TEXT_MESSAGE.TABLE;
    	/**
    	 * Text Message MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    	/**
    	 * Read Path (VIEW).
    	 */
    	public static final String PATH_READ = DBStatement.TEXT_MESSAGE.VIEW;
    	/**
    	 * Text Message READ URI.
    	 * Create content URIs from the authority by appending path to database view.
    	 */
        public static final Uri CONTENT_URI_READ = Uri.parse("content://" + AUTHORITY + "/" + PATH_READ);
        /**
         * Text Message projection.
         */
        public static final String[] PROJECTION_ALL =
        		StringFormat.duplicateRemove(
            			StringFormat.concatenateArrays(
            				MESSAGE.PROJECTION_ALL,
            				new String[] {
            					DBStatement.TEXT_MESSAGE._ID
            				}
            			)
            		);
        /**
         * Text Message projection by index: Column ID.
         */
        public static final int INDEX_ID = INFORMATION.INDEX_ID;
        /**
         * Text Message projection by index: Column Creation Date.
         */
        public static final int INDEX_CREATIONDATE = INFORMATION.INDEX_CREATIONDATE;
        /**
         * Text Message projection by index: Column Last Modification Date.
         */
        public static final int INDEX_LASTMODIFICATIONDATE = INFORMATION.INDEX_LASTMODIFICATIONDATE;
        /**
         * Text Message projection by index: Column Priority.
         */
        public static final int INDEX_PRIORITY = INFORMATION.INDEX_PRIORITY;
        /**
         * Text Message projection by index: Column ID Emergency.
         */
        public static final int INDEX_EMERGENCY_ID = INFORMATION.INDEX_EMERGENCY_ID;
        /**
         * Text Message projection by index: Column Subject.
         */
        public static final int INDEX_SUBJECT = MESSAGE.INDEX_SUBJECT;
        /**
         * Text Message projection by index: Column Content.
         */
        public static final int INDEX_CONTENT = MESSAGE.INDEX_CONTENT;
        /**
         * Text Message default check column.
         */
        public static final String CHECK_COLUMN = MESSAGE.CHECK_COLUMN;
        /**
         * Text Message default sort order.
         */
        public static final String SORT_ORDER =
        		MESSAGE.SORT_ORDER;
    }





	/**
	 *  Shared Document Content Provider.
	 */
    public static final class SHARED_DOCUMENT {
    	/**
    	 * Shared Document Collection ID.
    	 */
    	public static final int ALL_SHARED_DOCUMENTS = 208;
    	/**
    	 * Shared Document Single ID.
    	 */
    	public static final int SINGLE_SHARED_DOCUMENT = 209;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.SHARED_DOCUMENT.TABLE;
    	/**
    	 * Shared Document MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
        /**
         * Shared Document projection.
         */
        public static final String[] PROJECTION_ALL =
    		StringFormat.duplicateRemove(
        			StringFormat.concatenateArrays(
        				INFORMATION.PROJECTION_ALL,
        				new String[] {
        					DBStatement.SHARED_DOCUMENT._ID,
    			    		DBStatement.SHARED_DOCUMENT.COL_DESCRIPTION,
    			    		DBStatement.SHARED_DOCUMENT.COL_OBSERVATION
        				}
        			)
        		);
        /**
         * Shared Document projection by index: Column ID.
         */
        public static final int INDEX_ID = INFORMATION.INDEX_ID;
        /**
         * Shared Document projection by index: Column Creation Date.
         */
        public static final int INDEX_CREATIONDATE = INFORMATION.INDEX_CREATIONDATE;
        /**
         * Shared Document projection by index: Column Last Modification Date.
         */
        public static final int INDEX_LASTMODIFICATIONDATE = INFORMATION.INDEX_LASTMODIFICATIONDATE;
        /**
         * Shared Document projection by index: Column Priority.
         */
        public static final int INDEX_PRIORITY = INFORMATION.INDEX_PRIORITY;
        /**
         * Shared Document projection by index: Column ID Emergency.
         */
        public static final int INDEX_EMERGENCY_ID = INFORMATION.INDEX_EMERGENCY_ID;
        /**
         * Shared Document projection by index: Column Description.
         */
        public static final int INDEX_DESCRIPTION = 5;
        /**
         * Shared Document projection by index: Column Observation.
         */
        public static final int INDEX_OBSERVATION = 6;
        /**
         * Shared Document default check column.
         */
        public static final String CHECK_COLUMN = DBStatement.SHARED_DOCUMENT.COL_DESCRIPTION;
        /**
         * Shared Document default sort order.
         */
        public static final String SORT_ORDER =
        		INFORMATION.SORT_ORDER;
    }





	/**
	 * Plan Content Provider.
	 */
    public static final class PLAN {
    	/**
    	 * Plan Collection ID.
    	 */
    	public static final int ALL_PLANS = 212;
    	/**
    	 * Plan Single ID.
    	 */
    	public static final int SINGLE_PLAN = 213;
    	/**
    	 * Plan Collection ID for READ operation, using the view.
    	 */
    	public static final int ALL_PLANS_READ = 214;
    	/**
    	 * Plan Single ID for READ operation, using the view.
    	 */
    	public static final int SINGLE_PLAN_READ = 215;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.PLAN.TABLE;
    	/**
    	 * Plan MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    	/**
    	 * Read Path (VIEW).
    	 */
    	public static final String PATH_READ = DBStatement.PLAN.VIEW;
    	/**
    	 * Plan READ URI.
    	 * Create content URIs from the authority by appending path to database view.
    	 */
        public static final Uri CONTENT_URI_READ = Uri.parse("content://" + AUTHORITY + "/" + PATH_READ);
        /**
         * Plan projection.
         */
        public static final String[] PROJECTION_ALL =
        		StringFormat.duplicateRemove(
            			StringFormat.concatenateArrays(
            				SHARED_DOCUMENT.PROJECTION_ALL,
            				new String[] {
            					DBStatement.PLAN._ID,
        			    		DBStatement.PLAN.COL_OBJECTIVE,
        			    		DBStatement.PLAN.COL_RISK,
        			    		DBStatement.PLAN.COL_CHECKLIST
            				}
            			)
            		);
        /**
         * Plan projection by index: Column ID.
         */
        public static final int INDEX_ID = INFORMATION.INDEX_ID;
        /**
         * Plan projection by index: Column Creation Date.
         */
        public static final int INDEX_CREATIONDATE = INFORMATION.INDEX_CREATIONDATE;
        /**
         * Plan projection by index: Column Last Modification Date.
         */
        public static final int INDEX_LASTMODIFICATIONDATE = INFORMATION.INDEX_LASTMODIFICATIONDATE;
        /**
         * Plan projection by index: Column Priority.
         */
        public static final int INDEX_PRIORITY = INFORMATION.INDEX_PRIORITY;
        /**
         * Plan projection by index: Column ID Emergency.
         */
        public static final int INDEX_EMERGENCY_ID = INFORMATION.INDEX_EMERGENCY_ID;
        /**
         * Plan projection by index: Column Description.
         */
        public static final int INDEX_DESCRIPTION = SHARED_DOCUMENT.INDEX_DESCRIPTION;
        /**
         * Shared Document projection by index: Column Observation.
         */
        public static final int INDEX_OBSERVATION = SHARED_DOCUMENT.INDEX_OBSERVATION;
        /**
         * Plan projection by index: Column Objective.
         */
        public static final int INDEX_OBJECTIVE = 7;
        /**
         * Plan projection by index: Column Risk.
         */
        public static final int INDEX_RISK = 8;
        /**
         * Plan projection by index: Column CheckList.
         */
        public static final int INDEX_CHECKLIST = 9;
        /**
         * Plan default check column.
         */
        public static final String CHECK_COLUMN = DBStatement.PLAN.COL_OBJECTIVE;
        /**
         * Plan default sort order.
         */
        public static final String SORT_ORDER =
        		SHARED_DOCUMENT.SORT_ORDER;
    }





	/**
	 * Contact Content Provider.
	 */
    public static final class CONTACT {
    	/**
    	 * Contact Collection ID.
    	 */
    	public static final int ALL_CONTACTS = 216;
    	/**
    	 * Contact Single ID.
    	 */
    	public static final int SINGLE_CONTACT = 217;
    	/**
    	 * Contact Collection ID for READ operation, using the view.
    	 */
    	public static final int ALL_CONTACTS_READ = 218;
    	/**
    	 * Contact Single ID for READ operation, using the view.
    	 */
    	public static final int SINGLE_CONTACT_READ = 219;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.CONTACT.TABLE;
    	/**
    	 * Contact MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    	/**
    	 * Read Path (VIEW).
    	 */
    	public static final String PATH_READ = DBStatement.CONTACT.VIEW;
    	/**
    	 * Contact READ URI.
    	 * Create content URIs from the authority by appending path to database view.
    	 */
        public static final Uri CONTENT_URI_READ = Uri.parse("content://" + AUTHORITY + "/" + PATH_READ);
        /**
         * Contact projection.
         */
        public static final String[] PROJECTION_ALL =  new String[] {
        	DBStatement.CONTACT._ID,
    		DBStatement.CONTACT.COL_PHONE,
    		DBStatement.CONTACT.COL_EMAIL,
    		DBStatement.CONTACT.COL_RADIO,
    		DBStatement.CONTACT.COL_LANGUAGE,
    		DBStatement.CONTACT.COL_ENTITY_ID
    	};
        /**
         * Contact projection by index: Column ID.
         */
        public static final int INDEX_ID = 0;
        /**
         * Contact projection by index: Column Phone.
         */
        public static final int INDEX_PHONE = 1;
        /**
         * Contact projection by index: Column Email.
         */
        public static final int INDEX_EMAIL = 2;
        /**
         * Contact projection by index: Column Radio.
         */
        public static final int INDEX_RADIO = 3;
        /**
         * Contact projection by index: Column Language.
         */
        public static final int INDEX_LANGUAGE = 4;
        /**
         * Contact projection by index: Column ID Entity.
         */
        public static final int INDEX_ENTITY_ID = 5;
        /**
         * Contact default check column.
         */
        public static final String CHECK_COLUMN = DBStatement.CONTACT.COL_ENTITY_ID;
        /**
         * Contact default sort order.
         */
        public static final String SORT_ORDER =
        		DBStatement.CONTACT.COL_ENTITY_ID + DBStatement.DEFAULT_SORT_ORDER + DBStatement.DESC_ORDER;
    }





	/**
	 * Team User Content Provider.
	 */
    public static final class TEAM__USER {
    	/**
    	 * Team User Collection ID.
    	 */
    	public static final int ALL_TEAM_USERS = 220;
    	/**
    	 * Team User Single ID.
    	 */
    	public static final int SINGLE_TEAM_USER = 221;
    	/**
    	 * Team User Collection ID for READ operation, using the view.
    	 */
    	public static final int ALL_TEAM_USERS_READ = 222;
    	/**
    	 * Team User Single ID for READ operation, using the view.
    	 */
    	public static final int SINGLE_TEAM_USER_READ = 223;
    	/**
    	 * Path (Table).
    	 */
    	public static final String PATH = DBStatement.TEAM__USER.TABLE;
    	/**
    	 * Team User MAIN URI.
    	 * Create content URIs from the authority by appending path to database table.
    	 */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    	/**
    	 * Read Path (VIEW).
    	 */
    	public static final String PATH_READ = DBStatement.TEAM__USER.VIEW;
    	/**
    	 * Team User READ URI.
    	 * Create content URIs from the authority by appending path to database view.
    	 */
        public static final Uri CONTENT_URI_READ = Uri.parse("content://" + AUTHORITY + "/" + PATH_READ);
        /**
         * Team User projection.
         */
        public static final String[] PROJECTION_ALL =
    		StringFormat.duplicateRemove(
        			StringFormat.concatenateArrays(
        				TEAM.PROJECTION_ALL,
        				new String[] {
        					DBStatement.TEAM__USER.COL_TEAM_ID,
    			    		DBStatement.TEAM__USER.COL_LEADER_ID,
    			    		DBStatement.TEAM__USER.COL_MEMBER_ID
        				}
        			)
        		);
        /**
         * Team User projection by index: Column ID.
         */
        public static final int INDEX_ID = TEAM.INDEX_ID;
        /**
         * Team User projection by index: Column Name.
         */
        public static final int INDEX_NAME = TEAM.INDEX_NAME;
        /**
         * Team User projection by index: Column ID Team.
         */
        public static final int INDEX_TEAM_ID = 2;
        /**
         * Team User projection by index: Column ID Leader.
         */
        public static final int INDEX_LEADER_ID = 3;
        /**
         * Team User projection by index: Column ID Member.
         */
        public static final int INDEX_MEMBER_ID = 4;
        /**
         * Team User default check column.
         */
        public static final String CHECK_COLUMN = DBStatement.TEAM__USER.COL_TEAM_ID;
        /**
         * Team User default sort order.
         */
        public static final String SORT_ORDER =
        		TEAM.SORT_ORDER;
    }

}
