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
package br.ufrj.ppgi.jemf.mobile.database;

import android.provider.BaseColumns;
import br.ufrj.ppgi.jemf.core.cooperation.EnumOrganizationStatus;
import br.ufrj.ppgi.jemf.core.cooperation.EnumVictimStatus;
import br.ufrj.ppgi.jemf.core.coordination.EnumEmergencyType;
import br.ufrj.ppgi.jemf.core.coordination.EnumLevel;
import br.ufrj.ppgi.jemf.core.coordination.EnumPriority;
import br.ufrj.ppgi.jemf.core.coordination.EnumResourceStatus;
import br.ufrj.ppgi.jemf.core.coordination.EnumServiceStatus;
import br.ufrj.ppgi.jemf.core.communication.EnumLanguage;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public final class DBStatement {

	/**********************************************************************/
	/************************ Base Statements *****************************/
	/**********************************************************************/
	/**
	 * Selection statement.
	 */
	public static final String SELECTION_STATEMENT = "=?";
	/**
	 * Concatenate statement.
	 */
	public static final String CONCATENATE_STATEMENT = " AND ";
	/**
	 * Separator statement.
	 */
	public static final String SEPARATOR_STATEMENT = ",";




	/**********************************************************************/
	/************************ Table Base Statements ***********************/
	/**********************************************************************/
	/**
	 * Table Emergency
	 */
    public static final class EMERGENCY implements BaseColumns {
    	public static final String TABLE = "emergency";
    	public static final String VIEW = "emergencies";
    	public static final String COL_NAME = "name";
    	public static final String COL_ACTIVATED = "activated";
    	public static final String COL_LEVEL = "level";
    	public static final String COL_START_DATE = "startdate";
    	public static final String COL_END_DATE = "enddate";
    	public static final String COL_TYPE = "type";
    }

	/**
	 * Table Emergency Type
	 */
    public static final class EMERGENCY_TYPE implements BaseColumns {
    	public static final String TABLE = "emergencytype";
    	public static final String COL_TYPE = "type";
    	public static final String COL_SEQ = "seq";
    }

	/**
	 * Table Level
	 */
    public static final class LEVEL implements BaseColumns {
    	public static final String TABLE = "level";
    	public static final String COL_LEVEL = "level";
    	public static final String COL_SEQ = "seq";
    }

	/**
	 * Table Service
	 */
    public static final class SERVICE implements BaseColumns {
    	public static final String TABLE = "service";
    	public static final String COL_TITLE = "title";
   	 	public static final String COL_DESCRIPTION = "description";
   	 	public static final String COL_STATUS = "status";
   	 	public static final String COL_START_DATE = "startdate";
   	 	public static final String COL_END_DATE = "enddate";
    	public static final String COL_PRIORITY = "priority";
    	public static final String COL_EMERGENCY_ID = "idemergency";
    }

	/**
	 * Table Service Status
	 */
    public static final class SERVICE_STATUS implements BaseColumns {
    	public static final String TABLE = "servicestatus";
    	public static final String COL_STATUS = "status";
    	public static final String COL_SEQ = "seq";
    }

	/**
	 * Table Priority
	 */
    public static final class PRIORITY implements BaseColumns {
    	public static final String TABLE = "priority";
    	public static final String COL_PRIORITY = "priority";
    	public static final String COL_SEQ = "seq";
    }

	/**
	 * Table Mission
	 */
    public static final class MISSION implements BaseColumns {
    	public static final String TABLE = "mission";
    	public static final String VIEW = "missions";
    }

	/**
	 * Table Task
	 */
    public static final class TASK implements BaseColumns {
    	public static final String TABLE = "task";
    	public static final String VIEW = "tasks";
    	public static final String COL_USER_ID = "iduser";
    }

	/**
	 * Table Resource
	 */
    public static final class RESOURCE implements BaseColumns {
    	public static final String TABLE = "resource";
    	public static final String COL_NAME = "name";
    	public static final String COL_STATUS = "status";
    	public static final String COL_DESCRIPTION = "description";
    }

	/**
	 * Table Resource Status
	 */
    public static final class RESOURCE_STATUS implements BaseColumns {
    	public static final String TABLE = "resourcestatus";
    	public static final String COL_STATUS = "status";
    	public static final String COL_SEQ = "seq";
    }

	/**
	 * Table Equipment
	 */
    public static final class EQUIPMENT implements BaseColumns {
    	public static final String TABLE = "equipment";
    	public static final String VIEW = "equipments";
    }

	/**
	 * Table Service and Resource (Relationship)
	 */
    public static final class SERVICE__RESOURCE implements BaseColumns {
    	public static final String TABLE = "service_resource";
    	public static final String VIEW = "service_resources";
    	public static final String COL_SERVICE_ID = "idservice";
    	public static final String COL_RESOURCE_ID = "idresource";
    }

	/**
	 * Table Entity
	 */
    public static final class ENTITY implements BaseColumns {
    	public static final String TABLE = "entity";
    	public static final String COL_NAME = "name";
    	public static final String COL_LOCATION_ID = "idlocation";
    }

	/**
	 * Table Organization
	 */
    public static final class ORGANIZATION implements BaseColumns {
    	public static final String TABLE = "organization";
    	public static final String COL_DESCRIPTION = "description";
    	public static final String COL_LEVEL = "level";
    }

	/**
	 * Table Health Care Unit
	 */
    public static final class HEALTH_CARE_UNIT implements BaseColumns {
    	public static final String TABLE = "healthcareunit";
    	public static final String VIEW = "healthcareunits";
    	public static final String COL_CAPACITY = "capacity";
    	public static final String COL_COMMANDER_ID = "idcommander";
    	public static final String COL_MEDICAL_ID = "idmedical";
    	public static final String COL_INTERESTPOINT_ID = "idinterestpoint";
    }

	/**
	 * Table Affected Organization
	 */
    public static final class AFFECTED_ORGANIZATION implements BaseColumns {
    	public static final String TABLE = "affectedorganization";
    	public static final String VIEW = "affectedorganizations";
    	public static final String COL_STATUS = "status";
    	public static final String COL_WITNESS_ID = "idwitness";
    	public static final String COL_INTERESTPOINT_ID = "idinterestpoint";
    }

	/**
	 * Table Organization Status
	 */
    public static final class ORGANIZATION_STATUS implements BaseColumns {
    	public static final String TABLE = "organizationstatus";
    	public static final String COL_STATUS = "status";
    	public static final String COL_SEQ = "seq";
    }

	/**
	 * Table Person
	 */
    public static final class PERSON implements BaseColumns {
    	public static final String TABLE = "person";
    	public static final String COL_GENDER = "gender";
    	public static final String COL_BIRTHDATE = "birthdate";
    	public static final String COL_AGE = "age";
    }

	/**
	 * Table User
	 */
    public static final class USER implements BaseColumns {
    	public static final String TABLE = "user";
    	public static final String COL_LOGIN = "login";
    	public static final String COL_PASSWORD = "password";
    }

	/**
	 * Table Commander
	 */
    public static final class COMMANDER implements BaseColumns {
    	public static final String TABLE = "commander";
    	public static final String VIEW = "commanders";
    }

	/**
	 * Table Responder
	 */
    public static final class RESPONDER implements BaseColumns {
    	public static final String TABLE = "responder";
    	public static final String VIEW = "responders";
    }

	/**
	 * Table Medical
	 */
    public static final class MEDICAL implements BaseColumns {
    	public static final String TABLE = "medical";
    	public static final String VIEW = "medicals";
    }

	/**
	 * Table Volunteer
	 */
    public static final class VOLUNTEER implements BaseColumns {
    	public static final String TABLE = "volunteer";
    	public static final String VIEW = "volunteers";
    }

	/**
	 * Table Witness
	 */
    public static final class WITNESS implements BaseColumns {
    	public static final String TABLE = "witness";
    	public static final String VIEW = "witnesses";
    }

	/**
	 * Table Victim
	 */
    public static final class VICTIM implements BaseColumns {
    	public static final String TABLE = "victim";
    	public static final String VIEW = "victims";
    	public static final String COL_STATUS = "status";
    	public static final String COL_HEALTHCAREUNIT_ID = "idhealthcareunit";
    	public static final String COL_AFFECTEDORGANIZATION_ID = "idaffectedorganization";
    	public static final String COL_WITNESS_ID = "idwitness";
    }

	/**
	 * Table Victim Status
	 */
    public static final class VICTIM_STATUS implements BaseColumns {
    	public static final String TABLE = "victimstatus";
    	public static final String COL_STATUS = "status";
    	public static final String COL_SEQ = "seq";
    }

	/**
	 * Table Team
	 */
    public static final class TEAM implements BaseColumns {
    	public static final String TABLE = "team";
    	public static final String VIEW = "teams";
    	public static final String COL_NAME = "name";
    }

	/**
	 * Table Team and User (Relationship)
	 */
    public static final class TEAM__USER implements BaseColumns {
    	public static final String TABLE = "team_user";
    	public static final String VIEW = "team_users";
    	public static final String COL_TEAM_ID = "idteam";
    	public static final String COL_LEADER_ID = "idleader";
    	public static final String COL_MEMBER_ID = "idmember";
    }

	/**
	 * Table Mission and Team (Relationship)
	 */
    public static final class MISSION__TEAM implements BaseColumns {
    	public static final String TABLE = "mission_team";
    	public static final String VIEW = "mission_teams";
    	public static final String COL_MISSION_ID = "idmission";
    	public static final String COL_TEAM_ID = "idteam";
    }

	/**
	 * Table Location
	 */
    public static final class LOCATION implements BaseColumns {
    	public static final String TABLE = "location";
    	public static final String VIEW = "locations";
    	public static final String COL_TIMESTAMP = "timestamp";
    	public static final String COL_INTERVAL = "interval";
    	public static final String COL_STATUS = "status";
    }

	/**
	 * Table Place Identifier
	 */
    public static final class PLACE_IDENTIFIER implements BaseColumns {
    	public static final String TABLE = "placeidentifier";
    	public static final String VIEW = "placeidentifiers";
    	public static final String COL_NAME = "name";
    	public static final String COL_DESCRIPTION = "description";
    	public static final String COL_TYPE = "type";
    	public static final String COL_SYMBOL = "symbol";
    	public static final String COL_LOCATION_ID = "idlocation";
    }

	/**
	 * Table Address
	 */
    public static final class ADDRESS implements BaseColumns {
    	public static final String TABLE = "address";
    	public static final String VIEW = "addresses";
    	public static final String COL_STREET = "street";
    	public static final String COL_NUMBER = "number";
    	public static final String COL_COMPLEMENT = "complement";
    	public static final String COL_DISTRICT = "district";
    	public static final String COL_CITY = "city";
    	public static final String COL_REGION = "region";
    	public static final String COL_COUNTRY = "country";
    	public static final String COL_POSTALCODE = "postalcode";
    	public static final String COL_LOCATION_ID = "idlocation";
    }

	/**
	 * Table Position
	 */
    public static final class POSITION implements BaseColumns {
    	public static final String TABLE = "position";
    	public static final String VIEW = "positions";
    	public static final String COL_LATITUDE = "latidude";
    	public static final String COL_LONGITUDE = "longitude";
    	public static final String COL_LOCATION_ID = "idlocation";
    }

	/**
	 * Table Interest Point
	 */
    public static final class INTEREST_POINT implements BaseColumns {
    	public static final String TABLE = "interestpoint";
    	public static final String VIEW = "interestpoints";
    	public static final String COL_NAME = "name";
    }

	/**
	 * Table Information
	 */
    public static final class INFORMATION implements BaseColumns {
    	public static final String TABLE = "information";
    	public static final String COL_CREATIONDATE = "creationdate";
    	public static final String COL_LASTMODIFICATIONDATE = "lastmodificationdate";
    	public static final String COL_PRIORITY = "priority";
    	public static final String COL_EMERGENCY_ID = "idemergency";
    }

    /**
	 * Table Message
	 */
    public static final class MESSAGE implements BaseColumns {
    	public static final String TABLE = "message";
    	public static final String COL_SUBJECT = "subject";
    	public static final String COL_CONTENT = "content";
    }

    /**
	 * Table Text Message
	 */
    public static final class TEXT_MESSAGE implements BaseColumns {
    	public static final String TABLE = "textmessage";
    	public static final String VIEW = "textmessages";
    }

    /**
	 * Table Audio Message
	 */
    public static final class AUDIO_MESSAGE implements BaseColumns {
    	public static final String TABLE = "audiomessage";
    	public static final String VIEW = "audiomessages";
    }

    /**
	 * Table Video Message
	 */
    public static final class VIDEO_MESSAGE implements BaseColumns {
    	public static final String TABLE = "videomessage";
    	public static final String VIEW = "videomessages";
    }

    /**
	 * Table Shared Document
	 */
    public static final class SHARED_DOCUMENT implements BaseColumns {
    	public static final String TABLE = "shareddocument";
    	public static final String COL_DESCRIPTION = "description";
    	public static final String COL_OBSERVATION = "observation";
    }

    /**
	 * Table Plan
	 */
    public static final class PLAN implements BaseColumns {
    	public static final String TABLE = "plan";
    	public static final String VIEW = "plans";
    	public static final String COL_OBJECTIVE = "objective";
    	public static final String COL_RISK = "risk";
    	public static final String COL_CHECKLIST = "checklist";    	
    }

    /**
	 * Table Contact
	 */
    public static final class CONTACT implements BaseColumns {
    	public static final String TABLE = "contact";
    	public static final String VIEW = "contacts";
    	public static final String COL_PHONE = "phone";
    	public static final String COL_EMAIL = "email";
    	public static final String COL_RADIO = "radio";
    	public static final String COL_LANGUAGE = "language";
    	public static final String COL_ENTITY_ID = "identity";
    }

	/**
	 * Table Language
	 */
    public static final class LANGUAGE implements BaseColumns {
    	public static final String TABLE = "language";
    	public static final String COL_LANGUAGE = "language";
    	public static final String COL_SEQ = "seq";
    }

	/**
	 * Table Information and Contact (Relationship)
	 */
    public static final class INFORMATION__CONTACT implements BaseColumns {
    	public static final String TABLE = "information_contact";
    	public static final String VIEW = "information_contacts";
    	public static final String COL_INFORMATION_ID = "idinformation";
    	public static final String COL_CONTACT_ID = "idcontact";
    }

	/**
	 * Table Entity and Information (Relationship)
	 */
    public static final class ENTITY__INFORMATION implements BaseColumns {
    	public static final String TABLE = "entity_information";
    	public static final String VIEW = "entity_informations";
    	public static final String COL_ENTITY_ID = "identity";
    	public static final String COL_INFORMATION_ID = "idinformation";
    }

	/**
	 * Table Service and Information (Relationship)
	 */
    public static final class SERVICE__INFORMATION implements BaseColumns {
    	public static final String TABLE = "service_information";
    	public static final String VIEW = "service_informations";
    	public static final String COL_SERVICE_ID = "idservice";
    	public static final String COL_INFORMATION_ID = "idinformation";
    }





    /**********************************************************************/
	/************************ SQL Base Statements *************************/
	/**********************************************************************/
    /**
     * Create table statement.
     */
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
    /**
     * Delete table statement.
     */
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS ";
    /**
     * Create table view statement.
     */
    public static final String CREATE_VIEW = "CREATE VIEW IF NOT EXISTS ";
    /**
     * Delete table view statement.
     */
    public static final String DROP_VIEW = "DROP VIEW IF EXISTS ";
    /**
     * View pre-select statement.
     */
    public static final String VIEW_AS = " AS ";
    /**
     * Insert into table statement.
     */
    public static final String INSERT_INTO = "INSERT INTO ";
    /**
     * Integer Primary key column statement.
     */
    public static final String PRIMARY_KEY_INTEGER = " INTEGER PRIMARY KEY AUTOINCREMENT, ";
    /**
     * String Primary key column statement.
     */
    public static final String PRIMARY_KEY_TEXT = " TEXT PRIMARY KEY NOT NULL, ";
    /**
     * Selection column statement.
     */
    public static final String SELECT = " SELECT ";
    /**
     * Selection origin statement.
     */
    public static final String FROM = " FROM ";
    /**
     * Selection criteria statement.
     */
    public static final String WHERE = " WHERE ";
    /**
     * Concatenation criteria statement.
     */
    public static final String AND = " AND ";
    /**
     * Union ALL statement.
     */
    public static final String UNION_ALL_SELECT = " UNION ALL SELECT ";
    /**
     * Sort Order ASC direction.
     */
    public static final String ASC_ORDER = " ASC ";
    /**
     * Sort Order DESC direction.
     */
    public static final String DESC_ORDER = " DESC ";
    /**
     * Column default sort order statement.
     */
    public static final String DEFAULT_SORT_ORDER = " COLLATE NOCASE ";
    /**
     * Column localized sort order statement.
     */
    public static final String LOCALIZED_SORT_ORDER = " COLLATE LOCALIZED ";





	/**********************************************************************/
	/************************ DB Versions *********************************/
	/**********************************************************************/

	/************************ Version 1 ***********************************/
	/**
	 * Emergency Table
	 */
    public static final class EMERGENCY_STATEMENT implements BaseStatement {
		/**
		 * Create
		 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(EMERGENCY.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + PRIMARY_KEY_INTEGER);
				// Name Column.
				baseQuery.append(EMERGENCY.COL_NAME + " TEXT NOT NULL, ");
				// Activated Column.
				baseQuery.append(EMERGENCY.COL_ACTIVATED + " INTEGER NOT NULL, ");
				// Level Column.
				baseQuery.append(EMERGENCY.COL_LEVEL + " TEXT NOT NULL DEFAULT ('");
					baseQuery.append(EnumLevel.MUNICIPAL.name()+"') REFERENCES ");
					baseQuery.append(LEVEL.TABLE + "(" + LEVEL.COL_LEVEL + "), ");
				// StartDate Column.
				baseQuery.append(EMERGENCY.COL_START_DATE + " TEXT NOT NULL, ");
				// EndDate Column.
				baseQuery.append(EMERGENCY.COL_END_DATE + " TEXT NOT NULL, ");
				// Type Column.
				baseQuery.append(EMERGENCY.COL_TYPE + " TEXT NOT NULL DEFAULT ('");
					baseQuery.append(EnumEmergencyType.FIRE.name()+"') REFERENCES ");
					baseQuery.append(EMERGENCY_TYPE.TABLE + "(" + EMERGENCY_TYPE.COL_TYPE + ") ");
			baseQuery.append("); ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Emergency create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(EMERGENCY.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Emergency destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Emergency populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(EMERGENCY.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Column.
				baseQuery.append(BaseColumns._ID + ", ");
				// Name Column.
				baseQuery.append(EMERGENCY.COL_NAME + ", ");
				// Activated Column.
				baseQuery.append(EMERGENCY.COL_ACTIVATED + ", ");
				// Level Column.
				baseQuery.append(EMERGENCY.COL_LEVEL + ", ");
				// StartDate Column.
				baseQuery.append(EMERGENCY.COL_START_DATE + ", ");
				// EndDate Column.
				baseQuery.append(EMERGENCY.COL_END_DATE + ", ");
				// Type Column.
				baseQuery.append(EMERGENCY.COL_TYPE + " ");
			baseQuery.append(FROM + EMERGENCY.TABLE + "; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Emergency view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(EMERGENCY.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Emergency view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
    }





	/**
	 * Emergency Type Table
	 */
    public static final class EMERGENCY_TYPE_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(EMERGENCY_TYPE.TABLE + " (");
				// ID Column.
				baseQuery.append(EMERGENCY_TYPE.COL_TYPE + PRIMARY_KEY_TEXT);
				// Sequence Column.
				baseQuery.append(EMERGENCY_TYPE.COL_SEQ + " INTEGER UNIQUE");
			baseQuery.append("); ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Emergency Type create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(EMERGENCY_TYPE.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Emergency Type destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
			StringBuffer baseQuery = new StringBuffer(INSERT_INTO);
			baseQuery.append(EMERGENCY_TYPE.TABLE + " (");
			baseQuery.append(EMERGENCY_TYPE.COL_TYPE + ", " + EMERGENCY_TYPE.COL_SEQ + ") ");
				baseQuery.append(SELECT);
					baseQuery.append("'" + EnumEmergencyType.FIRE.name() + "' AS " + EMERGENCY_TYPE.COL_TYPE + ", ");
					baseQuery.append(EnumEmergencyType.FIRE.ordinal() + " AS " + EMERGENCY_TYPE.COL_SEQ + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumEmergencyType.GEOLOGIC.name() + "', " + EnumEmergencyType.GEOLOGIC.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumEmergencyType.HYDRO_METEREOLOGICAL.name() + "', " + EnumEmergencyType.HYDRO_METEREOLOGICAL.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumEmergencyType.HAZARDOUS_MATERIAL.name() + "', " + EnumEmergencyType.HAZARDOUS_MATERIAL.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumEmergencyType.INFESTATION.name() + "', " + EnumEmergencyType.INFESTATION.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumEmergencyType.CIVIL_DISTURBANCE.name() + "', " + EnumEmergencyType.CIVIL_DISTURBANCE.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumEmergencyType.CRIMINAL_ACTIVITY.name() + "', " + EnumEmergencyType.CRIMINAL_ACTIVITY.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumEmergencyType.AIR_INCIDENT.name() + "', " + EnumEmergencyType.AIR_INCIDENT.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumEmergencyType.MARINE_INCIDENT.name() + "', " + EnumEmergencyType.MARINE_INCIDENT.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumEmergencyType.VEHICLE_INCIDENT.name() + "', " + EnumEmergencyType.VEHICLE_INCIDENT.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumEmergencyType.RAIL_INCIDENT.name() + "', " + EnumEmergencyType.RAIL_INCIDENT.ordinal() + " ");	
			baseQuery.append("; ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Emergency Type populate: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Emergency Type view create: invalid operation.");
			return null;
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Emergency Type view destroy: invalid operation.");
			return null;
		}
    }





	/**
	 * Level Table
	 */
    public static final class LEVEL_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(LEVEL.TABLE + " (");
				// ID Column.
				baseQuery.append(LEVEL.COL_LEVEL + PRIMARY_KEY_TEXT);
				// Sequence Column.
				baseQuery.append(LEVEL.COL_SEQ + " INTEGER UNIQUE");
			baseQuery.append("); ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Level create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(LEVEL.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Level destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
			StringBuffer baseQuery = new StringBuffer(INSERT_INTO);
			baseQuery.append(LEVEL.TABLE + " (");
			baseQuery.append(LEVEL.COL_LEVEL + ", " + LEVEL.COL_SEQ + ") ");
				baseQuery.append(SELECT);
					baseQuery.append("'" + EnumLevel.MUNICIPAL.name() + "' AS " + LEVEL.COL_LEVEL + ", ");
					baseQuery.append(EnumLevel.MUNICIPAL.ordinal() + " AS " + LEVEL.COL_SEQ + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumLevel.STATE.name() + "', " + EnumLevel.STATE.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumLevel.FEDERAL.name() + "', " + EnumLevel.FEDERAL.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumLevel.INTERNATIONAL.name() + "', " + EnumLevel.INTERNATIONAL.ordinal() + " ");
			baseQuery.append("; ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Level populate: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Level view create: invalid operation.");
			return null;
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Level view destroy: invalid operation.");
			return null;
		}
    }





	/**
	 * Service Table
	 */
    public static final class SERVICE_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(SERVICE.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + PRIMARY_KEY_INTEGER);
				// Title Column.
				baseQuery.append(SERVICE.COL_TITLE + " TEXT NOT NULL, ");
				// Description Column.
				baseQuery.append(SERVICE.COL_DESCRIPTION + " TEXT NOT NULL, ");
				// Status Column.
				baseQuery.append(SERVICE.COL_STATUS + " TEXT NOT NULL DEFAULT ('");
					baseQuery.append(EnumServiceStatus.OPEN.name()+"') REFERENCES ");
					baseQuery.append(SERVICE_STATUS.TABLE + "(" + SERVICE_STATUS.COL_STATUS + "), ");
				// StartDate Column.
				baseQuery.append(SERVICE.COL_START_DATE + " TEXT NOT NULL, ");
				// EndDate Column.
				baseQuery.append(SERVICE.COL_END_DATE + " TEXT NOT NULL, ");
				// Priority Column.
				baseQuery.append(SERVICE.COL_PRIORITY + " TEXT NOT NULL DEFAULT ('");
					baseQuery.append(EnumPriority.LOW.name()+"') REFERENCES ");
					baseQuery.append(PRIORITY.TABLE + "(" + PRIORITY.COL_PRIORITY + "), ");
				// Emergency ID Column.
				baseQuery.append(SERVICE.COL_EMERGENCY_ID + " INTEGER NOT NULL REFERENCES ");
					baseQuery.append(EMERGENCY.TABLE + "(" + BaseColumns._ID + ") ");
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Service create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(SERVICE.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Service destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Service populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Service view create: invalid operation.");
			return null;
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Service view destroy: invalid operation.");
			return null;
		}
    }





	/**
	 * Service Status Table
	 */
    public static final class SERVICE_STATUS_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(SERVICE_STATUS.TABLE + " (");
				// Table ID Column.
				baseQuery.append(SERVICE_STATUS.COL_STATUS + PRIMARY_KEY_TEXT);
				// Table Sequence Column.
				baseQuery.append(SERVICE_STATUS.COL_SEQ + " INTEGER UNIQUE");
			baseQuery.append("); ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Service Status create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(SERVICE_STATUS.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Service Status destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
			StringBuffer baseQuery = new StringBuffer(INSERT_INTO);
			baseQuery.append(SERVICE_STATUS.TABLE + " (");
			baseQuery.append(SERVICE_STATUS.COL_STATUS + ", " + SERVICE_STATUS.COL_SEQ + ") ");
				baseQuery.append(SELECT);
					baseQuery.append("'" + EnumServiceStatus.OPEN.name() + "' AS " + SERVICE_STATUS.COL_STATUS + ", ");
					baseQuery.append(EnumServiceStatus.OPEN.ordinal() + " AS " + SERVICE_STATUS.COL_SEQ + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumServiceStatus.RUNNING.name() + "', " + EnumServiceStatus.RUNNING.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumServiceStatus.CLOSED.name() + "', " + EnumServiceStatus.CLOSED.ordinal() + " ");
			baseQuery.append("; ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Service Status populate: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Service Status view create: invalid operation.");
			return null;
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Service Status view destroy: invalid operation.");
			return null;
		}
    }





	/**
	 * Priority Table
	 */
    public static final class PRIORITY_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(PRIORITY.TABLE + " (");
				// ID Column.
				baseQuery.append(PRIORITY.COL_PRIORITY + PRIMARY_KEY_TEXT);
				// Sequence Column.
				baseQuery.append(PRIORITY.COL_SEQ + " INTEGER UNIQUE");
			baseQuery.append("); ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Priority create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(PRIORITY.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Priority destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
			StringBuffer baseQuery = new StringBuffer(INSERT_INTO);
			baseQuery.append(PRIORITY.TABLE + " (");
			baseQuery.append(PRIORITY.COL_PRIORITY + ", " + PRIORITY.COL_SEQ + ") ");
				baseQuery.append(SELECT);
					baseQuery.append("'" + EnumPriority.HIGH.name() + "' AS " + PRIORITY.COL_PRIORITY + ", ");
					baseQuery.append(EnumPriority.HIGH.ordinal() + " AS " + PRIORITY.COL_SEQ + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumPriority.MEDIUM.name() + "', " + EnumPriority.MEDIUM.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumPriority.LOW.name() + "', " + EnumPriority.LOW.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumPriority.NONE.name() + "', " + EnumPriority.NONE.ordinal() + " ");
			baseQuery.append("; ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Priority populate: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Priority view create: invalid operation.");
			return null;
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Priority view destroy: invalid operation.");
			return null;
		}
    }





	/**
	 * Mission Table
	 */
    public static final class MISSION_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(MISSION.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + " INTEGER PRIMARY KEY NOT NULL REFERENCES ");
				baseQuery.append(SERVICE.TABLE + "(" + BaseColumns._ID + ") ");
				// New Columns here.
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Mission create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(MISSION.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Mission destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Mission populate: invalid operation.");
			return null;
		}
		/**
		 * View
		 */
		@Override
		public String getCreateView() {
			final String serviceAlias = " S.";
			final String missionAlias = " M.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(MISSION.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Column.
				baseQuery.append(missionAlias + BaseColumns._ID + ", ");
				// Title Column.
				baseQuery.append(serviceAlias + SERVICE.COL_TITLE + ", ");
				// Description Column.
				baseQuery.append(serviceAlias + SERVICE.COL_DESCRIPTION + ", ");
				// Status Column.
				baseQuery.append(serviceAlias + SERVICE.COL_STATUS + ", ");
				// StartDate Column.
				baseQuery.append(serviceAlias + SERVICE.COL_START_DATE + ", ");
				// EndDate Column.
				baseQuery.append(serviceAlias + SERVICE.COL_END_DATE + ", ");
				// Priority Column.
				baseQuery.append(serviceAlias + SERVICE.COL_PRIORITY + ", ");
				// Emergency ID Column.
				baseQuery.append(serviceAlias + SERVICE.COL_EMERGENCY_ID + " ");
			baseQuery.append(FROM);
			baseQuery.append(SERVICE.TABLE + " S, ");
			baseQuery.append(MISSION.TABLE + " M ");
			baseQuery.append(WHERE);
			baseQuery.append(serviceAlias + BaseColumns._ID + " = " + missionAlias + BaseColumns._ID);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Mission view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(MISSION.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Mission view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
    }





	/**
	 * Task Table
	 */
    public static final class TASK_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(TASK.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + " INTEGER PRIMARY KEY NOT NULL REFERENCES ");
					baseQuery.append(SERVICE.TABLE + "(" + BaseColumns._ID + "), ");
				// User ID Column.
				baseQuery.append(TASK.COL_USER_ID + " INTEGER NOT NULL REFERENCES ");
					baseQuery.append(USER.TABLE + "(" + BaseColumns._ID + ") ");
				// New Columns here.
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Task create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(TASK.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Task destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Task populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String serviceAlias = " S.";
			final String taskAlias = " T.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(TASK.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Column.
				baseQuery.append(taskAlias + BaseColumns._ID + ", ");
				// Title Column.
				baseQuery.append(serviceAlias + SERVICE.COL_TITLE + ", ");
				// Description Column.
				baseQuery.append(serviceAlias + SERVICE.COL_DESCRIPTION + ", ");
				// Status Column.
				baseQuery.append(serviceAlias + SERVICE.COL_STATUS + ", ");
				// StartDate Column.
				baseQuery.append(serviceAlias + SERVICE.COL_START_DATE + ", ");
				// EndDate Column.
				baseQuery.append(serviceAlias + SERVICE.COL_END_DATE + ", ");
				// Priority Column.
				baseQuery.append(serviceAlias + SERVICE.COL_PRIORITY + ", ");
				// Emergency ID Column.
				baseQuery.append(serviceAlias + SERVICE.COL_EMERGENCY_ID + ", ");
				// User ID Column.
				baseQuery.append(taskAlias + TASK.COL_USER_ID + " ");
			baseQuery.append(FROM);
			baseQuery.append(SERVICE.TABLE + " S, ");
			baseQuery.append(TASK.TABLE + " T ");
			baseQuery.append(WHERE);
			baseQuery.append(serviceAlias + BaseColumns._ID + " = " + taskAlias + BaseColumns._ID);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Task view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(TASK.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Task view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
    }





	/**
	 * Resource Table
	 */
    public static final class RESOURCE_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(RESOURCE.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + PRIMARY_KEY_INTEGER);
				// Name Column.
				baseQuery.append(RESOURCE.COL_NAME + " TEXT NOT NULL, ");
				// Status Column.
				baseQuery.append(RESOURCE.COL_STATUS + " TEXT NOT NULL DEFAULT ('");
					baseQuery.append(EnumResourceStatus.AVALIABLE.name()+"') REFERENCES ");
					baseQuery.append(RESOURCE_STATUS.TABLE + "(" + RESOURCE_STATUS.COL_STATUS + "), ");
				// Description Column.
				baseQuery.append(RESOURCE.COL_DESCRIPTION + " TEXT NOT NULL ");
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Resource create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(RESOURCE.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Resource destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Resource populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Resource view create: invalid operation.");
			return null;
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Resource view destroy: invalid operation.");
			return null;
		}
    }





	/**
	 * Resource Status Table
	 */
    public static final class RESOURCE_STATUS_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(RESOURCE_STATUS.TABLE + " (");
			// ID Column.
			baseQuery.append(RESOURCE_STATUS.COL_STATUS + PRIMARY_KEY_TEXT);
			// Sequence Column.
			baseQuery.append(RESOURCE_STATUS.COL_SEQ + " INTEGER UNIQUE");
			baseQuery.append("); ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Resource Status create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(RESOURCE_STATUS.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Resource Status destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
			StringBuffer baseQuery = new StringBuffer(INSERT_INTO);
			baseQuery.append(RESOURCE_STATUS.TABLE + " (");
			baseQuery.append(RESOURCE_STATUS.COL_STATUS + ", " + RESOURCE_STATUS.COL_SEQ + ") ");
				baseQuery.append(SELECT);
					baseQuery.append("'" + EnumResourceStatus.AVALIABLE.name() + "' AS " + RESOURCE_STATUS.COL_STATUS + ", ");
					baseQuery.append(EnumResourceStatus.AVALIABLE.ordinal() + " AS " + RESOURCE_STATUS.COL_SEQ + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumResourceStatus.ALLOCATED.name() + "', " + EnumResourceStatus.ALLOCATED.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumResourceStatus.DAMAGED.name() + "', " + EnumResourceStatus.DAMAGED.ordinal() + " ");
				baseQuery.append("; ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Resource Status populate: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Resource Status view create: invalid operation.");
			return null;
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Resource Status view destroy: invalid operation.");
			return null;
		}	
    }





	/**
	 * Equipment Table
	 */
    public static final class EQUIPMENT_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(EQUIPMENT.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + " INTEGER PRIMARY KEY NOT NULL REFERENCES ");
				baseQuery.append(RESOURCE.TABLE + "(" + BaseColumns._ID + ") ");
				// New Columns here.
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Equipment create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(EQUIPMENT.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Equipment destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Equipment populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String resourceAlias = " R.";
			final String equipmentAlias = " E.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(EQUIPMENT.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Column.
				baseQuery.append(equipmentAlias + BaseColumns._ID + ", ");
				// Name Column.
				baseQuery.append(resourceAlias + RESOURCE.COL_NAME + ", ");
				// Status Column.
				baseQuery.append(resourceAlias + RESOURCE.COL_STATUS + ", ");
				// Description Column.
				baseQuery.append(resourceAlias + RESOURCE.COL_DESCRIPTION + " ");
			baseQuery.append(FROM);
			baseQuery.append(RESOURCE.TABLE + " R, ");
			baseQuery.append(EQUIPMENT.TABLE + " E ");
			baseQuery.append(WHERE);
			baseQuery.append(resourceAlias + BaseColumns._ID + " = " + equipmentAlias + BaseColumns._ID);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Equipment view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(EQUIPMENT.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Equipment view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}	
    }





	/**
	 * Service Resource Table (Relationship)
	 */
    public static final class SERVICE_RESOURCE_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(SERVICE__RESOURCE.TABLE + " (");
				// Service ID Column.
				baseQuery.append(SERVICE__RESOURCE.COL_SERVICE_ID + " INTEGER NOT NULL REFERENCES ");
				baseQuery.append(SERVICE.TABLE + "(" + BaseColumns._ID + "), ");
				// Resource ID Column.
				baseQuery.append(SERVICE__RESOURCE.COL_RESOURCE_ID + " INTEGER NOT NULL REFERENCES ");
				baseQuery.append(RESOURCE.TABLE + "(" + BaseColumns._ID + ") ");
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "ServiceResource create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(SERVICE__RESOURCE.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "ServiceResource destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "ServiceResource populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String serviceAlias = " S.";
			final String serviceResourceAlias = " SR.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(SERVICE__RESOURCE.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Service Column.
				baseQuery.append(serviceAlias + BaseColumns._ID + ", ");
				// ID Resource Column.
				baseQuery.append(serviceResourceAlias + SERVICE__RESOURCE.COL_RESOURCE_ID + " ");
			baseQuery.append(FROM);
			baseQuery.append(SERVICE__RESOURCE.TABLE + " SR, ");
			baseQuery.append(SERVICE.TABLE + " S ");
			baseQuery.append(WHERE);
			baseQuery.append(serviceAlias + BaseColumns._ID + " = " + serviceResourceAlias + SERVICE__RESOURCE.COL_RESOURCE_ID);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "ServiceResource view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(SERVICE__RESOURCE.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "ServiceResource view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
    }





	/**
	 * Entity Table
	 */
    public static final class ENTITY_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(ENTITY.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + PRIMARY_KEY_INTEGER);
				// Name Column.
				baseQuery.append(ENTITY.COL_NAME + " TEXT NOT NULL, ");
				// Location ID Column.
				baseQuery.append(ENTITY.COL_LOCATION_ID + " INTEGER NULL REFERENCES ");
					baseQuery.append(LOCATION.TABLE + "(" + BaseColumns._ID + ") ");
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Entity create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(ENTITY.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Entity destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Entity populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Entity view create: invalid operation.");
			return null;
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Entity view destroy: invalid operation.");
			return null;
		}
    }





	/**
	 * Organization Table
	 */
    public static final class ORGANIZATION_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(ORGANIZATION.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + " INTEGER PRIMARY KEY NOT NULL REFERENCES ");
					baseQuery.append(ENTITY.TABLE + "(" + BaseColumns._ID + "), ");
				// Description Column.
				baseQuery.append(ORGANIZATION.COL_DESCRIPTION + " TEXT NOT NULL, ");
				// Level Column.
				baseQuery.append(ORGANIZATION.COL_LEVEL + " TEXT NOT NULL DEFAULT ('");
					baseQuery.append(EnumLevel.MUNICIPAL.name()+"') REFERENCES ");
					baseQuery.append(LEVEL.TABLE + "(" + LEVEL.COL_LEVEL + ") ");
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Organization create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(ORGANIZATION.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Organization destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Organization populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Organization view create: invalid operation.");
			return null;
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Organization view destroy: invalid operation.");
			return null;
		}
    }





	/**
	 * Health Care Unit Table
	 */
    public static final class HEALTH_CARE_UNIT_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(HEALTH_CARE_UNIT.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + " INTEGER PRIMARY KEY NOT NULL REFERENCES ");
					baseQuery.append(ORGANIZATION.TABLE + "(" + BaseColumns._ID + "), ");
				// Capacity Column.
				baseQuery.append(HEALTH_CARE_UNIT.COL_CAPACITY + " TEXT NOT NULL, ");
				// Commander ID Column.
				baseQuery.append(HEALTH_CARE_UNIT.COL_COMMANDER_ID + " INTEGER NOT NULL REFERENCES ");
					baseQuery.append(COMMANDER.TABLE + "(" + BaseColumns._ID + "), ");
				// Medical ID Column.
				baseQuery.append(HEALTH_CARE_UNIT.COL_MEDICAL_ID + " INTEGER NULL REFERENCES ");
					baseQuery.append(MEDICAL.TABLE + "(" + BaseColumns._ID + "), ");
				// Interest Point ID Column.
				baseQuery.append(HEALTH_CARE_UNIT.COL_INTERESTPOINT_ID + " INTEGER NULL REFERENCES ");
					baseQuery.append(INTEREST_POINT.TABLE + "(" + BaseColumns._ID + ") ");
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Health Care Unit create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(HEALTH_CARE_UNIT.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Health Care Unit destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Health Care Unit populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String entityAlias = " E.";
			final String organizationAlias = " O.";
			final String healthcareunitAlias = " H.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(HEALTH_CARE_UNIT.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Column.
				baseQuery.append(healthcareunitAlias + BaseColumns._ID + ", ");
				// Name Column.
				baseQuery.append(entityAlias + ENTITY.COL_NAME + ", ");
				// Location ID Column.
				baseQuery.append(entityAlias + ENTITY.COL_LOCATION_ID + ", ");
				// Description Column.
				baseQuery.append(organizationAlias + ORGANIZATION.COL_DESCRIPTION + ", ");
				// Level Column.
				baseQuery.append(organizationAlias + ORGANIZATION.COL_LEVEL + ", ");
				// Capacity Column.
				baseQuery.append(healthcareunitAlias + HEALTH_CARE_UNIT.COL_CAPACITY + ", ");
				// Commander ID Column.
				baseQuery.append(healthcareunitAlias + HEALTH_CARE_UNIT.COL_COMMANDER_ID + ", ");
				// Medical ID Column.
				baseQuery.append(healthcareunitAlias + HEALTH_CARE_UNIT.COL_MEDICAL_ID + ", ");
				// Interest Point ID Column.
				baseQuery.append(healthcareunitAlias + HEALTH_CARE_UNIT.COL_INTERESTPOINT_ID + " ");
			baseQuery.append(FROM);
			baseQuery.append(ENTITY.TABLE + " E, ");
			baseQuery.append(ORGANIZATION.TABLE + " O, ");
			baseQuery.append(HEALTH_CARE_UNIT.TABLE + " H ");
			baseQuery.append(WHERE);
			baseQuery.append(entityAlias + BaseColumns._ID + " = " + organizationAlias + BaseColumns._ID);
			baseQuery.append(AND);
			baseQuery.append(organizationAlias + BaseColumns._ID + " = " + healthcareunitAlias + BaseColumns._ID);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Health Care Unit view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(HEALTH_CARE_UNIT.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Health Care Unit view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
    }





	/**
	 * Affected Organization Table
	 */
    public static final class AFFECTED_ORGANIZATION_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(AFFECTED_ORGANIZATION.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + " INTEGER PRIMARY KEY NOT NULL REFERENCES ");
					baseQuery.append(ORGANIZATION.TABLE + "(" + BaseColumns._ID + "), ");
				// Status Column.
				baseQuery.append(AFFECTED_ORGANIZATION.COL_STATUS + " TEXT NOT NULL DEFAULT ('");
					baseQuery.append(EnumOrganizationStatus.INVOLVED.name()+"') REFERENCES ");
					baseQuery.append(ORGANIZATION_STATUS.TABLE + "(" + ORGANIZATION_STATUS.COL_STATUS + "), ");
				// Witness ID Column.
				baseQuery.append(AFFECTED_ORGANIZATION.COL_WITNESS_ID + " INTEGER NULL REFERENCES ");
					baseQuery.append(WITNESS.TABLE + "(" + BaseColumns._ID + "), ");
				// Interest Point ID Column.
				baseQuery.append(AFFECTED_ORGANIZATION.COL_INTERESTPOINT_ID + " INTEGER NULL REFERENCES ");
					baseQuery.append(INTEREST_POINT.TABLE + "(" + BaseColumns._ID + ") ");
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Affected Organization create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(AFFECTED_ORGANIZATION.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Affected Organization destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Affected Organization populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String entityAlias = " E.";
			final String organizationAlias = " O.";
			final String affectedorganizationAlias = " A.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(AFFECTED_ORGANIZATION.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Column.
				baseQuery.append(affectedorganizationAlias + BaseColumns._ID + ", ");
				// Name Column.
				baseQuery.append(entityAlias + ENTITY.COL_NAME + ", ");
				// Location ID Column.
				baseQuery.append(entityAlias + ENTITY.COL_LOCATION_ID + ", ");
				// Description Column.
				baseQuery.append(organizationAlias + ORGANIZATION.COL_DESCRIPTION + ", ");
				// Level Column.
				baseQuery.append(organizationAlias + ORGANIZATION.COL_LEVEL + ", ");
				// Status Column.
				baseQuery.append(affectedorganizationAlias + AFFECTED_ORGANIZATION.COL_STATUS + ", ");
				// Witness ID Column.
				baseQuery.append(affectedorganizationAlias + AFFECTED_ORGANIZATION.COL_WITNESS_ID + ", ");
				// Interest Point ID Column.
				baseQuery.append(affectedorganizationAlias + AFFECTED_ORGANIZATION.COL_INTERESTPOINT_ID + " ");
			baseQuery.append(FROM);
			baseQuery.append(ENTITY.TABLE + " E, ");
			baseQuery.append(ORGANIZATION.TABLE + " O, ");
			baseQuery.append(AFFECTED_ORGANIZATION.TABLE + " A ");
			baseQuery.append(WHERE);
			baseQuery.append(entityAlias + BaseColumns._ID + " = " + organizationAlias + BaseColumns._ID);
			baseQuery.append(AND);
			baseQuery.append(organizationAlias + BaseColumns._ID + " = " + affectedorganizationAlias + BaseColumns._ID);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Affected Organization view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(AFFECTED_ORGANIZATION.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Affected Organization view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
    }





	/**
	 * Organization Status Table
	 */
    public static final class ORGANIZATION_STATUS_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(ORGANIZATION_STATUS.TABLE + " (");
				// Table ID Column.
				baseQuery.append(ORGANIZATION_STATUS.COL_STATUS + PRIMARY_KEY_TEXT);
				// Table Sequence Column.
				baseQuery.append(ORGANIZATION_STATUS.COL_SEQ + " INTEGER UNIQUE");
			baseQuery.append("); ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Organization Status create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(ORGANIZATION_STATUS.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Organization Status destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
			StringBuffer baseQuery = new StringBuffer(INSERT_INTO);
			baseQuery.append(ORGANIZATION_STATUS.TABLE + " (");
			baseQuery.append(ORGANIZATION_STATUS.COL_STATUS + ", " + ORGANIZATION_STATUS.COL_SEQ + ") ");
				baseQuery.append(SELECT);
					baseQuery.append("'" + EnumOrganizationStatus.INVOLVED.name() + "' AS " + ORGANIZATION_STATUS.COL_STATUS + ", ");
					baseQuery.append(EnumOrganizationStatus.INVOLVED.ordinal() + " AS " + ORGANIZATION_STATUS.COL_SEQ + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumOrganizationStatus.LOW_DAMAGED.name() + "', " + EnumOrganizationStatus.LOW_DAMAGED.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumOrganizationStatus.MEDIUM_DAMAGED.name() + "', " + EnumOrganizationStatus.MEDIUM_DAMAGED.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumOrganizationStatus.HIGH_DAMAGED.name() + "', " + EnumOrganizationStatus.HIGH_DAMAGED.ordinal() + " ");
			baseQuery.append("; ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Organization Status populate: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Organization Status view create: invalid operation.");
			return null;
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Organization Status view destroy: invalid operation.");
			return null;
		}
    }





	/**
	 * Person Table
	 */
    public static final class PERSON_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(PERSON.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + " INTEGER PRIMARY KEY NOT NULL REFERENCES ");
					baseQuery.append(ENTITY.TABLE + "(" + BaseColumns._ID + "), ");
				// Gender Column.
				baseQuery.append(PERSON.COL_GENDER + " TEXT NOT NULL, ");
				// Birth Date Column.
				baseQuery.append(PERSON.COL_BIRTHDATE + " TEXT NULL, ");
				// Age Column.
				baseQuery.append(PERSON.COL_AGE + " INTEGER NULL ");
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Person create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(PERSON.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Person destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Person populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Person view create: invalid operation.");
			return null;
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Person view destroy: invalid operation.");
			return null;
		}
    }





	/**
	 * User Table
	 */
    public static final class USER_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(USER.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + " INTEGER PRIMARY KEY NOT NULL REFERENCES ");
					baseQuery.append(PERSON.TABLE + "(" + BaseColumns._ID + "), ");
				// Login Column.
				baseQuery.append(USER.COL_LOGIN + " TEXT NOT NULL, ");
				// Password Column.
				baseQuery.append(USER.COL_PASSWORD + " TEXT NOT NULL ");
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "User create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(USER.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "User destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "User populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "User view create: invalid operation.");
			return null;
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "User view destroy: invalid operation.");
			return null;
		}
    }





	/**
	 * Commander Table
	 */
    public static final class COMMANDER_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(COMMANDER.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + " INTEGER PRIMARY KEY NOT NULL REFERENCES ");
					baseQuery.append(USER.TABLE + "(" + BaseColumns._ID + ") ");
				// New Columns here.
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Commander create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(COMMANDER.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Commander destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Commander populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String entityAlias = " E.";
			final String personAlias = " P.";
			final String userAlias = " U.";
			final String commaderAlias = " C.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(COMMANDER.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Column.
				baseQuery.append(commaderAlias + BaseColumns._ID + ", ");
				// Name Column.
				baseQuery.append(entityAlias + ENTITY.COL_NAME + ", ");
				// Location ID Column.
				baseQuery.append(entityAlias + ENTITY.COL_LOCATION_ID + ", ");
				// Gender Column.
				baseQuery.append(personAlias + PERSON.COL_GENDER + ", ");
				// Birth Date Column.
				baseQuery.append(personAlias + PERSON.COL_BIRTHDATE + ", ");
				// Age Column.
				baseQuery.append(personAlias + PERSON.COL_AGE + ", ");
				// Login Column.
				baseQuery.append(userAlias + USER.COL_LOGIN + ", ");
				// Password Column.
				baseQuery.append(userAlias + USER.COL_PASSWORD + " ");
			baseQuery.append(FROM);
			baseQuery.append(ENTITY.TABLE + " E, ");
			baseQuery.append(PERSON.TABLE + " P, ");
			baseQuery.append(USER.TABLE + " U, ");
			baseQuery.append(COMMANDER.TABLE + " C ");
			baseQuery.append(WHERE);
			baseQuery.append(entityAlias + BaseColumns._ID + " = " + personAlias + BaseColumns._ID);
			baseQuery.append(AND);
			baseQuery.append(personAlias + BaseColumns._ID + " = " + userAlias + BaseColumns._ID);
			baseQuery.append(AND);
			baseQuery.append(userAlias + BaseColumns._ID + " = " + commaderAlias + BaseColumns._ID);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Commander view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(COMMANDER.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Commander view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}	
    }





	/**
	 * Responder Table
	 */
    public static final class RESPONDER_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(RESPONDER.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + " INTEGER PRIMARY KEY NOT NULL REFERENCES ");
					baseQuery.append(USER.TABLE + "(" + BaseColumns._ID + ") ");
				// New Columns here.
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Responder create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(RESPONDER.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Responder destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Responder populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String entityAlias = " E.";
			final String personAlias = " P.";
			final String userAlias = " U.";
			final String responderAlias = " R.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(RESPONDER.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Column.
				baseQuery.append(responderAlias + BaseColumns._ID + ", ");
				// Name Column.
				baseQuery.append(entityAlias + ENTITY.COL_NAME + ", ");
				// Location ID Column.
				baseQuery.append(entityAlias + ENTITY.COL_LOCATION_ID + ", ");
				// Gender Column.
				baseQuery.append(personAlias + PERSON.COL_GENDER + ", ");
				// Birth Date Column.
				baseQuery.append(personAlias + PERSON.COL_BIRTHDATE + ", ");
				// Age Column.
				baseQuery.append(personAlias + PERSON.COL_AGE + ", ");
				// Login Column.
				baseQuery.append(userAlias + USER.COL_LOGIN + ", ");
				// Password Column.
				baseQuery.append(userAlias + USER.COL_PASSWORD + " ");
			baseQuery.append(FROM);
			baseQuery.append(ENTITY.TABLE + " E, ");
			baseQuery.append(PERSON.TABLE + " P, ");
			baseQuery.append(USER.TABLE + " U, ");
			baseQuery.append(RESPONDER.TABLE + " R ");
			baseQuery.append(WHERE);
			baseQuery.append(entityAlias + BaseColumns._ID + " = " + personAlias + BaseColumns._ID);
			baseQuery.append(AND);
			baseQuery.append(personAlias + BaseColumns._ID + " = " + userAlias + BaseColumns._ID);
			baseQuery.append(AND);
			baseQuery.append(userAlias + BaseColumns._ID + " = " + responderAlias + BaseColumns._ID);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Responder view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(RESPONDER.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Responder view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}	
    }





	/**
	 * Medical Table
	 */
    public static final class MEDICAL_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(MEDICAL.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + " INTEGER PRIMARY KEY NOT NULL REFERENCES ");
					baseQuery.append(USER.TABLE + "(" + BaseColumns._ID + ") ");
				// New Columns here.
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Medical create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(MEDICAL.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Medical destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Medical populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String entityAlias = " E.";
			final String personAlias = " P.";
			final String userAlias = " U.";
			final String medicalAlias = " M.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(MEDICAL.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Column.
				baseQuery.append(medicalAlias + BaseColumns._ID + ", ");
				// Name Column.
				baseQuery.append(entityAlias + ENTITY.COL_NAME + ", ");
				// Location ID Column.
				baseQuery.append(entityAlias + ENTITY.COL_LOCATION_ID + ", ");
				// Gender Column.
				baseQuery.append(personAlias + PERSON.COL_GENDER + ", ");
				// Birth Date Column.
				baseQuery.append(personAlias + PERSON.COL_BIRTHDATE + ", ");
				// Age Column.
				baseQuery.append(personAlias + PERSON.COL_AGE + ", ");
				// Login Column.
				baseQuery.append(userAlias + USER.COL_LOGIN + ", ");
				// Password Column.
				baseQuery.append(userAlias + USER.COL_PASSWORD + " ");
			baseQuery.append(FROM);
			baseQuery.append(ENTITY.TABLE + " E, ");
			baseQuery.append(PERSON.TABLE + " P, ");
			baseQuery.append(USER.TABLE + " U, ");
			baseQuery.append(MEDICAL.TABLE + " M ");
			baseQuery.append(WHERE);
			baseQuery.append(entityAlias + BaseColumns._ID + " = " + personAlias + BaseColumns._ID);
			baseQuery.append(AND);
			baseQuery.append(personAlias + BaseColumns._ID + " = " + userAlias + BaseColumns._ID);
			baseQuery.append(AND);
			baseQuery.append(userAlias + BaseColumns._ID + " = " + medicalAlias + BaseColumns._ID);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Medical view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(MEDICAL.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Medical view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}	
    }





	/**
	 * Volunteer Table
	 */
    public static final class VOLUNTEER_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(VOLUNTEER.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + " INTEGER PRIMARY KEY NOT NULL REFERENCES ");
					baseQuery.append(USER.TABLE + "(" + BaseColumns._ID + ") ");
				// New Columns here.
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Volunteer create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(VOLUNTEER.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Volunteer destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Volunteer populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String entityAlias = " E.";
			final String personAlias = " P.";
			final String userAlias = " U.";
			final String volunteerAlias = " V.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(VOLUNTEER.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Column.
				baseQuery.append(volunteerAlias + BaseColumns._ID + ", ");
				// Name Column.
				baseQuery.append(entityAlias + ENTITY.COL_NAME + ", ");
				// Location ID Column.
				baseQuery.append(entityAlias + ENTITY.COL_LOCATION_ID + ", ");
				// Gender Column.
				baseQuery.append(personAlias + PERSON.COL_GENDER + ", ");
				// Birth Date Column.
				baseQuery.append(personAlias + PERSON.COL_BIRTHDATE + ", ");
				// Age Column.
				baseQuery.append(personAlias + PERSON.COL_AGE + ", ");
				// Login Column.
				baseQuery.append(userAlias + USER.COL_LOGIN + ", ");
				// Password Column.
				baseQuery.append(userAlias + USER.COL_PASSWORD + " ");
			baseQuery.append(FROM);
			baseQuery.append(ENTITY.TABLE + " E, ");
			baseQuery.append(PERSON.TABLE + " P, ");
			baseQuery.append(USER.TABLE + " U, ");
			baseQuery.append(VOLUNTEER.TABLE + " V ");
			baseQuery.append(WHERE);
			baseQuery.append(entityAlias + BaseColumns._ID + " = " + personAlias + BaseColumns._ID);
			baseQuery.append(AND);
			baseQuery.append(personAlias + BaseColumns._ID + " = " + userAlias + BaseColumns._ID);
			baseQuery.append(AND);
			baseQuery.append(userAlias + BaseColumns._ID + " = " + volunteerAlias + BaseColumns._ID);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Volunteer view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(VOLUNTEER.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Volunteer view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}	
    }





	/**
	 * Witness Table
	 */
    public static final class WITNESS_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(WITNESS.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + " INTEGER PRIMARY KEY NOT NULL REFERENCES ");
					baseQuery.append(PERSON.TABLE + "(" + BaseColumns._ID + ") ");
				// New Columns here.
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Witness create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(WITNESS.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Witness destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Witness populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String entityAlias = " E.";
			final String personAlias = " P.";
			final String witnessAlias = " T.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(WITNESS.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Column.
				baseQuery.append(witnessAlias + BaseColumns._ID + ", ");
				// Name Column.
				baseQuery.append(entityAlias + ENTITY.COL_NAME + ", ");
				// Location ID Column.
				baseQuery.append(entityAlias + ENTITY.COL_LOCATION_ID + ", ");
				// Gender Column.
				baseQuery.append(personAlias + PERSON.COL_GENDER + ", ");
				// Birth Date Column.
				baseQuery.append(personAlias + PERSON.COL_BIRTHDATE + ", ");
				// Age Column.
				baseQuery.append(personAlias + PERSON.COL_AGE + " ");
			baseQuery.append(FROM);
			baseQuery.append(ENTITY.TABLE + " E, ");
			baseQuery.append(PERSON.TABLE + " P, ");
			baseQuery.append(WITNESS.TABLE + " T ");
			baseQuery.append(WHERE);
			baseQuery.append(entityAlias + BaseColumns._ID + " = " + personAlias + BaseColumns._ID);
			baseQuery.append(AND);
			baseQuery.append(personAlias + BaseColumns._ID + " = " + witnessAlias + BaseColumns._ID);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Witness view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(WITNESS.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Witness view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}	
    }





	/**
	 * Victim Table
	 */
    public static final class VICTIM_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(VICTIM.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + " INTEGER PRIMARY KEY NOT NULL REFERENCES ");
					baseQuery.append(PERSON.TABLE + "(" + BaseColumns._ID + "), ");
				// Status Column.
				baseQuery.append(VICTIM.COL_STATUS + " TEXT NOT NULL DEFAULT ('");
					baseQuery.append(EnumVictimStatus.CONCIOUS.name()+"') REFERENCES ");
					baseQuery.append(VICTIM_STATUS.TABLE + "(" + VICTIM_STATUS.COL_STATUS + "), ");
				// Affected Organization ID Column.
				baseQuery.append(VICTIM.COL_AFFECTEDORGANIZATION_ID + " INTEGER NULL REFERENCES ");
					baseQuery.append(AFFECTED_ORGANIZATION.TABLE + "(" + BaseColumns._ID + "), ");
				// Health Care Unit ID Column.
				baseQuery.append(VICTIM.COL_HEALTHCAREUNIT_ID + " INTEGER NULL REFERENCES ");
					baseQuery.append(HEALTH_CARE_UNIT.TABLE + "(" + BaseColumns._ID + "), ");
				// Witness ID Column.
				baseQuery.append(VICTIM.COL_WITNESS_ID + " INTEGER NULL REFERENCES ");
					baseQuery.append(WITNESS.TABLE + "(" + BaseColumns._ID + ") ");
				// New Columns here.
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Victim create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(VICTIM.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Victim destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Victim populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String entityAlias = " E.";
			final String personAlias = " P.";
			final String victimAlias = " V.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(VICTIM.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Column.
				baseQuery.append(victimAlias + BaseColumns._ID + ", ");
				// Name Column.
				baseQuery.append(entityAlias + ENTITY.COL_NAME + ", ");
				// Location ID Column.
				baseQuery.append(entityAlias + ENTITY.COL_LOCATION_ID + ", ");
				// Gender Column.
				baseQuery.append(personAlias + PERSON.COL_GENDER + ", ");
				// Birth Date Column.
				baseQuery.append(personAlias + PERSON.COL_BIRTHDATE + ", ");
				// Age Column.
				baseQuery.append(personAlias + PERSON.COL_AGE + ", ");
				// Status Column.
				baseQuery.append(victimAlias + VICTIM.COL_STATUS + ", ");
				// Affected Organization ID Column.
				baseQuery.append(victimAlias + VICTIM.COL_AFFECTEDORGANIZATION_ID + ", ");
				// Health Care Unit ID Column.
				baseQuery.append(victimAlias + VICTIM.COL_HEALTHCAREUNIT_ID + ", ");
				// Witness ID Column.
				baseQuery.append(victimAlias + VICTIM.COL_WITNESS_ID + " ");
			baseQuery.append(FROM);
			baseQuery.append(ENTITY.TABLE + " E, ");
			baseQuery.append(PERSON.TABLE + " P, ");
			baseQuery.append(VICTIM.TABLE + " V ");
			baseQuery.append(WHERE);
			baseQuery.append(entityAlias + BaseColumns._ID + " = " + personAlias + BaseColumns._ID);
			baseQuery.append(AND);
			baseQuery.append(personAlias + BaseColumns._ID + " = " + victimAlias + BaseColumns._ID);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Victim view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(VICTIM.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Victim view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}	
    }





	/**
	 * Victim Status Table
	 */
    public static final class VICTIM_STATUS_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(VICTIM_STATUS.TABLE + " (");
				// Table ID Column.
				baseQuery.append(VICTIM_STATUS.COL_STATUS + PRIMARY_KEY_TEXT);
				// Table Sequence Column.
				baseQuery.append(VICTIM_STATUS.COL_SEQ + " INTEGER UNIQUE");
			baseQuery.append("); ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Victim Status create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(VICTIM_STATUS.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Victim Status destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
			StringBuffer baseQuery = new StringBuffer(INSERT_INTO);
			baseQuery.append(VICTIM_STATUS.TABLE + " (");
			baseQuery.append(VICTIM_STATUS.COL_STATUS + ", " + VICTIM_STATUS.COL_SEQ + ") ");
				baseQuery.append(SELECT);
					baseQuery.append("'" + EnumVictimStatus.BURNT.name() + "' AS " + VICTIM_STATUS.COL_STATUS + ", ");
					baseQuery.append(EnumVictimStatus.BURNT.ordinal() + " AS " + VICTIM_STATUS.COL_SEQ + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumVictimStatus.CONCIOUS.name() + "', " + EnumVictimStatus.CONCIOUS.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumVictimStatus.DECEASED.name() + "', " + EnumVictimStatus.DECEASED.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumVictimStatus.INJURED.name() + "', " + EnumVictimStatus.INJURED.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumVictimStatus.MOBILE.name() + "', " + EnumVictimStatus.MOBILE.ordinal() + " ");
			baseQuery.append("; ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Victim Status populate: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Victim Status view create: invalid operation.");
			return null;
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Victim Status view destroy: invalid operation.");
			return null;
		}
    }





	/**
	 * Team Table
	 */
    public static final class TEAM_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(TEAM.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + PRIMARY_KEY_INTEGER);
				// Name Column.
				baseQuery.append(TEAM.COL_NAME + " TEXT NOT NULL ");
				// New Columns here.
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Team create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(TEAM.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Team destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Team populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String teamAlias = " T.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(TEAM.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Column.
				baseQuery.append(teamAlias + BaseColumns._ID + ", ");
				// Name Column.
				baseQuery.append(teamAlias + TEAM.COL_NAME + " ");
			baseQuery.append(FROM);
			baseQuery.append(TEAM.TABLE + " T ");
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Team view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(TEAM.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Team view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}	
    }





	/**
	 * Team User Table (Relationship)
	 */
    public static final class TEAM_USER_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(TEAM__USER.TABLE + " (");
				// Team ID Column.
				baseQuery.append(TEAM__USER.COL_TEAM_ID + " INTEGER NOT NULL REFERENCES ");
					baseQuery.append(TEAM.TABLE + "(" + BaseColumns._ID + "), ");
				// Leader ID Column.
				baseQuery.append(TEAM__USER.COL_LEADER_ID + " INTEGER NOT NULL REFERENCES ");
					baseQuery.append(USER.TABLE + "(" + BaseColumns._ID + "), ");
				// Member ID Column.
				baseQuery.append(TEAM__USER.COL_MEMBER_ID + " INTEGER NOT NULL REFERENCES ");
					baseQuery.append(USER.TABLE + "(" + BaseColumns._ID + ") ");
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "TeamUser create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(TEAM__USER.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "TeamUser destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "TeamUser populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String teamAlias = " T.";
			final String teamUserAlias = " TU.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(TEAM__USER.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Column.
				baseQuery.append(teamAlias + BaseColumns._ID + ", ");
				// Name Column.
				baseQuery.append(teamAlias + TEAM.COL_NAME + ", ");
				// ID Team Column.
				baseQuery.append(teamUserAlias + TEAM__USER.COL_TEAM_ID + ", ");
				// ID Leader Column.
				baseQuery.append(teamUserAlias + TEAM__USER.COL_LEADER_ID + ", ");
				// ID Member Column.
				baseQuery.append(teamUserAlias + TEAM__USER.COL_MEMBER_ID + " ");
			baseQuery.append(FROM);
			baseQuery.append(TEAM__USER.TABLE + " TU, ");
			baseQuery.append(TEAM.TABLE + " T ");
			baseQuery.append(WHERE);
			baseQuery.append(teamAlias + BaseColumns._ID + " = " + teamUserAlias + TEAM__USER.COL_TEAM_ID);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "TeamUser view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(TEAM__USER.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "TeamUser view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
    }





	/**
	 * Mission Team Table (Relationship)
	 */
    public static final class MISSION_TEAM_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(MISSION__TEAM.TABLE + " (");
				// Mission ID Column.
				baseQuery.append(MISSION__TEAM.COL_MISSION_ID + " INTEGER NOT NULL REFERENCES ");
					baseQuery.append(MISSION.TABLE + "(" + BaseColumns._ID + "), ");
				// Team ID Column.
				baseQuery.append(MISSION__TEAM.COL_TEAM_ID + " INTEGER NOT NULL REFERENCES ");
					baseQuery.append(TEAM.TABLE + "(" + BaseColumns._ID + ") ");
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "MissionTeam create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(MISSION__TEAM.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "MissionTeam destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "MissionTeam populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String missionAlias = " M.";
			final String missionTeamAlias = " MT.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(MISSION__TEAM.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Mission Column.
				baseQuery.append(missionAlias + BaseColumns._ID + ", ");
				// ID Team Column.
				baseQuery.append(missionTeamAlias + MISSION__TEAM.COL_TEAM_ID + " ");
			baseQuery.append(FROM);
			baseQuery.append(MISSION__TEAM.TABLE + " MT, ");
			baseQuery.append(MISSION.TABLE + " M ");
			baseQuery.append(WHERE);
			baseQuery.append(missionAlias + BaseColumns._ID + " = " + missionTeamAlias + MISSION__TEAM.COL_TEAM_ID);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "MissionTeam view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(MISSION__TEAM.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "MissionTeam view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
    }





	/**
	 * Position Table
	 */
    public static final class POSITION_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(POSITION.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + PRIMARY_KEY_INTEGER);
				// Latitude Column.
				baseQuery.append(POSITION.COL_LATITUDE + " INTEGER NOT NULL, ");
				// Longitude Column.
				baseQuery.append(POSITION.COL_LONGITUDE + " INTEGER NOT NULL, ");
				// Location ID Column.
				baseQuery.append(POSITION.COL_LOCATION_ID + " INTEGER NULL REFERENCES ");
					baseQuery.append(LOCATION.TABLE + "(" + BaseColumns._ID + ") ");
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Position create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(POSITION.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Position destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Position populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String positionAlias = " P.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(POSITION.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Column.
				baseQuery.append(positionAlias + BaseColumns._ID + ", ");
				// Latitude Column.
				baseQuery.append(positionAlias + POSITION.COL_LATITUDE + ", ");
				// Longitude Column.
				baseQuery.append(positionAlias + POSITION.COL_LONGITUDE + ", ");
				// Location ID Column.
				baseQuery.append(positionAlias + POSITION.COL_LOCATION_ID + " ");
			baseQuery.append(FROM);
			baseQuery.append(POSITION.TABLE + " P ");
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Position view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(POSITION.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Position view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
    }





	/**
	 * Address Table
	 */
    public static final class ADDRESS_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(ADDRESS.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + PRIMARY_KEY_INTEGER);
				// Street Column.
				baseQuery.append(ADDRESS.COL_STREET + " TEXT NULL, ");
				// Number Column.
				baseQuery.append(ADDRESS.COL_NUMBER + " TEXT NULL, ");
				// Complement Column.
				baseQuery.append(ADDRESS.COL_COMPLEMENT + " TEXT NULL, ");
				// District Column.
				baseQuery.append(ADDRESS.COL_DISTRICT + " TEXT NULL, ");
				// City Column.
				baseQuery.append(ADDRESS.COL_CITY + " TEXT NULL, ");
				// Region Column.
				baseQuery.append(ADDRESS.COL_REGION + " TEXT NULL, ");
				// Country Column.
				baseQuery.append(ADDRESS.COL_COUNTRY + " TEXT NULL, ");
				// Postal Code Column.
				baseQuery.append(ADDRESS.COL_POSTALCODE + " TEXT NULL, ");
				// Location ID Column.
				baseQuery.append(ADDRESS.COL_LOCATION_ID + " INTEGER NULL REFERENCES ");
					baseQuery.append(LOCATION.TABLE + "(" + BaseColumns._ID + ") ");
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Address create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(ADDRESS.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Address destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Address populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String addressAlias = " A.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(ADDRESS.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Column.
				baseQuery.append(addressAlias + BaseColumns._ID + ", ");
				// Street Column.
				baseQuery.append(addressAlias + ADDRESS.COL_STREET + ", ");
				// Number Column.
				baseQuery.append(addressAlias + ADDRESS.COL_NUMBER + ", ");
				// Complement Column.
				baseQuery.append(addressAlias + ADDRESS.COL_COMPLEMENT + ", ");
				// District Column.
				baseQuery.append(addressAlias + ADDRESS.COL_DISTRICT + ", ");
				// City Column.
				baseQuery.append(addressAlias + ADDRESS.COL_CITY + ", ");
				// Region Column.
				baseQuery.append(addressAlias + ADDRESS.COL_REGION + ", ");
				// Country Column.
				baseQuery.append(addressAlias + ADDRESS.COL_COUNTRY + ", ");
				// Postal Code Column.
				baseQuery.append(addressAlias + ADDRESS.COL_POSTALCODE + ", ");
				// Location ID Column.
				baseQuery.append(addressAlias + ADDRESS.COL_LOCATION_ID + " ");
			baseQuery.append(FROM);
			baseQuery.append(ADDRESS.TABLE + " A ");
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Address view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(ADDRESS.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Address view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
    }





	/**
	 * Place Identifier Table
	 */
    public static final class PLACE_IDENTIFIER_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(PLACE_IDENTIFIER.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + PRIMARY_KEY_INTEGER);
				// Name Column.
				baseQuery.append(PLACE_IDENTIFIER.COL_NAME + " TEXT NOT NULL, ");
				// Description Column.
				baseQuery.append(PLACE_IDENTIFIER.COL_DESCRIPTION + " TEXT NOT NULL, ");
				// Type Column.
				baseQuery.append(PLACE_IDENTIFIER.COL_TYPE + " TEXT NOT NULL, ");
				// Symbol Column.
				baseQuery.append(PLACE_IDENTIFIER.COL_SYMBOL + " TEXT NULL, ");
				// Location ID Column.
				baseQuery.append(ADDRESS.COL_LOCATION_ID + " INTEGER NULL REFERENCES ");
					baseQuery.append(LOCATION.TABLE + "(" + BaseColumns._ID + ") ");
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Place Identifier create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(PLACE_IDENTIFIER.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Place Identifier destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Place Identifier populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String placeidentifierAlias = " I.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(PLACE_IDENTIFIER.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Column.
				baseQuery.append(placeidentifierAlias + BaseColumns._ID + ", ");
				// Place Identifier Name Column.
				baseQuery.append(placeidentifierAlias + PLACE_IDENTIFIER.COL_NAME + ", ");
				// Description Column.
				baseQuery.append(placeidentifierAlias + PLACE_IDENTIFIER.COL_DESCRIPTION + ", ");
				// Type Column.
				baseQuery.append(placeidentifierAlias + PLACE_IDENTIFIER.COL_TYPE + ", ");
				// Symbol Column.
				baseQuery.append(placeidentifierAlias + PLACE_IDENTIFIER.COL_SYMBOL + ", ");
				// Location ID Column.
				baseQuery.append(placeidentifierAlias + PLACE_IDENTIFIER.COL_LOCATION_ID + " ");
			baseQuery.append(FROM);
			baseQuery.append(PLACE_IDENTIFIER.TABLE + " I ");
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Place Identifier view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(PLACE_IDENTIFIER.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Place Identifier view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
    }





	/**
	 * Location Table
	 */
    public static final class LOCATION_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(LOCATION.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + PRIMARY_KEY_INTEGER);
				// Time Stamp Column.
				baseQuery.append(LOCATION.COL_TIMESTAMP + " INTEGER NOT NULL, ");
				// Interval Column.
				baseQuery.append(LOCATION.COL_INTERVAL + " INTEGER NOT NULL, ");
				// Status Column.
				baseQuery.append(LOCATION.COL_STATUS + " TEXT NOT NULL ");
				// New Columns here.
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Location create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(LOCATION.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Location destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Location populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String positionAlias = " P.";
			final String addressAlias = " A.";
			final String placeidentifierAlias = " I.";
			final String locationAlias = " L.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(LOCATION.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Column.
				baseQuery.append(locationAlias + BaseColumns._ID + ", ");
				// Time Stamp Column.
				baseQuery.append(locationAlias + LOCATION.COL_TIMESTAMP + ", ");
				// Interval Column.
				baseQuery.append(locationAlias + LOCATION.COL_INTERVAL + ", ");
				// Status Column.
				baseQuery.append(locationAlias + LOCATION.COL_STATUS + ", ");
				// Latitude Column.
				baseQuery.append(positionAlias + POSITION.COL_LATITUDE + ", ");
				// Longitude Column.
				baseQuery.append(positionAlias + POSITION.COL_LONGITUDE + ", ");
				// Street Column.
				baseQuery.append(addressAlias + ADDRESS.COL_STREET + ", ");
				// Number Column.
				baseQuery.append(addressAlias + ADDRESS.COL_NUMBER + ", ");
				// Complement Column.
				baseQuery.append(addressAlias + ADDRESS.COL_COMPLEMENT + ", ");
				// District Column.
				baseQuery.append(addressAlias + ADDRESS.COL_DISTRICT + ", ");
				// City Column.
				baseQuery.append(addressAlias + ADDRESS.COL_CITY + ", ");
				// Region Column.
				baseQuery.append(addressAlias + ADDRESS.COL_REGION + ", ");
				// Country Column.
				baseQuery.append(addressAlias + ADDRESS.COL_COUNTRY + ", ");
				// Postal Code Column.
				baseQuery.append(addressAlias + ADDRESS.COL_POSTALCODE + ", ");
				// Place Identifier Name Column.
				baseQuery.append(placeidentifierAlias + PLACE_IDENTIFIER.COL_NAME + " AS pi_name, ");
				// Description Column.
				baseQuery.append(placeidentifierAlias + PLACE_IDENTIFIER.COL_DESCRIPTION + " AS pi_description, ");
				// Type Column.
				baseQuery.append(placeidentifierAlias + PLACE_IDENTIFIER.COL_TYPE + " AS pi_type, ");
				// Symbol Column.
				baseQuery.append(placeidentifierAlias + PLACE_IDENTIFIER.COL_SYMBOL + " AS pi_symbol ");
			baseQuery.append(FROM);
			baseQuery.append(LOCATION.TABLE + " L ");
			baseQuery.append(" LEFT OUTER JOIN " + POSITION.TABLE + " P " + " ON " +  locationAlias + BaseColumns._ID + " = " + positionAlias + POSITION.COL_LOCATION_ID);
			baseQuery.append(" LEFT OUTER JOIN " + ADDRESS.TABLE + " A " + " ON " +  locationAlias + BaseColumns._ID + " = " + addressAlias + ADDRESS.COL_LOCATION_ID);
			baseQuery.append(" LEFT OUTER JOIN " + PLACE_IDENTIFIER.TABLE + " I " + " ON " +  locationAlias + BaseColumns._ID + " = " + placeidentifierAlias + PLACE_IDENTIFIER.COL_LOCATION_ID);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Location view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(LOCATION.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Location view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}	
    }





	/**
	 * Interest Point Table
	 */
    public static final class INTEREST_POINT_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(INTEREST_POINT.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + " INTEGER PRIMARY KEY NOT NULL REFERENCES ");
					baseQuery.append(LOCATION.TABLE + "(" + BaseColumns._ID + "), ");
				// Name Column.
				baseQuery.append(INTEREST_POINT.COL_NAME + " TEXT NOT NULL ");
				// New Columns here.
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Interest Point create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(INTEREST_POINT.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Interest Point destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Interest Point populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String positionAlias = " P.";
			final String addressAlias = " A.";
			final String placeidentifierAlias = " I.";
			final String locationAlias = " L.";
			final String interestpointAlias = " IP.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(INTEREST_POINT.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Column.
				baseQuery.append(interestpointAlias + BaseColumns._ID + ", ");
				// Time Stamp Column.
				baseQuery.append(locationAlias + LOCATION.COL_TIMESTAMP + ", ");
				// Interval Column.
				baseQuery.append(locationAlias + LOCATION.COL_INTERVAL + ", ");
				// Status Column.
				baseQuery.append(locationAlias + LOCATION.COL_STATUS + ", ");
				// Interest Point Name Column.
				baseQuery.append(interestpointAlias + INTEREST_POINT.COL_NAME + ", ");
				// Latitude Column.
				baseQuery.append(positionAlias + POSITION.COL_LATITUDE + ", ");
				// Longitude Column.
				baseQuery.append(positionAlias + POSITION.COL_LONGITUDE + ", ");
				// Street Column.
				baseQuery.append(addressAlias + ADDRESS.COL_STREET + ", ");
				// Number Column.
				baseQuery.append(addressAlias + ADDRESS.COL_NUMBER + ", ");
				// Complement Column.
				baseQuery.append(addressAlias + ADDRESS.COL_COMPLEMENT + ", ");
				// District Column.
				baseQuery.append(addressAlias + ADDRESS.COL_DISTRICT + ", ");
				// City Column.
				baseQuery.append(addressAlias + ADDRESS.COL_CITY + ", ");
				// Region Column.
				baseQuery.append(addressAlias + ADDRESS.COL_REGION + ", ");
				// Country Column.
				baseQuery.append(addressAlias + ADDRESS.COL_COUNTRY + ", ");
				// Postal Code Column.
				baseQuery.append(addressAlias + ADDRESS.COL_POSTALCODE + ", ");
				// Place Identifier Name Column.
				baseQuery.append(placeidentifierAlias + PLACE_IDENTIFIER.COL_NAME + " AS piname, ");
				// Description Column.
				baseQuery.append(placeidentifierAlias + PLACE_IDENTIFIER.COL_DESCRIPTION + " AS pidescription, ");
				// Type Column.
				baseQuery.append(placeidentifierAlias + PLACE_IDENTIFIER.COL_TYPE + " AS pitype, ");
				// Symbol Column.
				baseQuery.append(placeidentifierAlias + PLACE_IDENTIFIER.COL_SYMBOL + " AS pisymbol ");
			baseQuery.append(FROM);
			baseQuery.append(INTEREST_POINT.TABLE + " IP ");
			baseQuery.append(" INNER JOIN " + LOCATION.TABLE + " L " + " ON " +  interestpointAlias + BaseColumns._ID + " = " + locationAlias + BaseColumns._ID);
			baseQuery.append(" LEFT OUTER JOIN " + POSITION.TABLE + " P " + " ON " +  locationAlias + BaseColumns._ID + " = " + positionAlias + POSITION.COL_LOCATION_ID);
			baseQuery.append(" LEFT OUTER JOIN " + ADDRESS.TABLE + " A " + " ON " +  locationAlias + BaseColumns._ID + " = " + addressAlias + ADDRESS.COL_LOCATION_ID);
			baseQuery.append(" LEFT OUTER JOIN " + PLACE_IDENTIFIER.TABLE + " I " + " ON " +  locationAlias + BaseColumns._ID + " = " + placeidentifierAlias + PLACE_IDENTIFIER.COL_LOCATION_ID);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Interest Point view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(INTEREST_POINT.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Interest Point view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}	
    }





	/**
	 * Information Table
	 */
    public static final class INFORMATION_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(INFORMATION.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + PRIMARY_KEY_INTEGER);
				// CreationDate Column.
				baseQuery.append(INFORMATION.COL_CREATIONDATE + " TEXT NOT NULL, ");
				// LastMofificationDate Column.
				baseQuery.append(INFORMATION.COL_LASTMODIFICATIONDATE + " TEXT NOT NULL, ");
				// Priority Column.
				baseQuery.append(INFORMATION.COL_PRIORITY + " TEXT NOT NULL DEFAULT ('");
					baseQuery.append(EnumPriority.LOW.name()+"') REFERENCES ");
					baseQuery.append(PRIORITY.TABLE + "(" + PRIORITY.COL_PRIORITY + "), ");
				// Emergency ID Column.
				baseQuery.append(INFORMATION.COL_EMERGENCY_ID + " INTEGER NULL REFERENCES ");
					baseQuery.append(EMERGENCY.TABLE + "(" + BaseColumns._ID + ") ");
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Information create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(INFORMATION.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Information destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Information populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Information view create: invalid operation.");
			return null;
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Information view destroy: invalid operation.");
			return null;
		}
    }





	/**
	 * Message Table
	 */
    public static final class MESSAGE_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(MESSAGE.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + " INTEGER PRIMARY KEY NOT NULL REFERENCES ");
					baseQuery.append(INFORMATION.TABLE + "(" + BaseColumns._ID + "), ");
				// Subject Column.
				baseQuery.append(MESSAGE.COL_SUBJECT + " TEXT NULL, ");
				// Content Column.
				baseQuery.append(MESSAGE.COL_CONTENT + " TEXT NULL ");
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Message create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(MESSAGE.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Message destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Message populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Message view create: invalid operation.");
			return null;
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Message view destroy: invalid operation.");
			return null;
		}
    }





	/**
	 * Text Message Table
	 */
    public static final class TEXT_MESSAGE_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(TEXT_MESSAGE.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + " INTEGER PRIMARY KEY NOT NULL REFERENCES ");
					baseQuery.append(MESSAGE.TABLE + "(" + BaseColumns._ID + ") ");
				// New Columns here.
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Text Message create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(TEXT_MESSAGE.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Text Message destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Text Message populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String informationAlias = " I.";
			final String messageAlias = " M.";
			final String textmessageAlias = " T.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(TEXT_MESSAGE.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Column.
				baseQuery.append(textmessageAlias + BaseColumns._ID + ", ");
				// CreationDate Column.
				baseQuery.append(informationAlias + INFORMATION.COL_CREATIONDATE + ", ");
				// LastMofificationDate Column.
				baseQuery.append(informationAlias + INFORMATION.COL_LASTMODIFICATIONDATE + ", ");
				// Priority Column.
				baseQuery.append(informationAlias + INFORMATION.COL_PRIORITY + ", ");
				// Emergency ID Column.
				baseQuery.append(informationAlias + INFORMATION.COL_EMERGENCY_ID + ", ");
				// Subject Column.
				baseQuery.append(messageAlias + MESSAGE.COL_SUBJECT + ", ");
				// Content Column.
				baseQuery.append(messageAlias + MESSAGE.COL_CONTENT + " ");
			baseQuery.append(FROM);
			baseQuery.append(INFORMATION.TABLE + " I, ");
			baseQuery.append(MESSAGE.TABLE + " M, ");
			baseQuery.append(TEXT_MESSAGE.TABLE + " T ");
			baseQuery.append(WHERE);
			baseQuery.append(informationAlias + BaseColumns._ID + " = " + messageAlias + BaseColumns._ID);
			baseQuery.append(AND);
			baseQuery.append(messageAlias + BaseColumns._ID + " = " + textmessageAlias + BaseColumns._ID);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Text Message view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(TEXT_MESSAGE.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Text Message view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
    }





	/**
	 * Shared Document Table
	 */
    public static final class SHARED_DOCUMENT_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(SHARED_DOCUMENT.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + " INTEGER PRIMARY KEY NOT NULL REFERENCES ");
					baseQuery.append(INFORMATION.TABLE + "(" + BaseColumns._ID + "), ");
				// Description Column.
				baseQuery.append(SHARED_DOCUMENT.COL_DESCRIPTION + " TEXT NULL, ");
				// Observation Column.
				baseQuery.append(SHARED_DOCUMENT.COL_OBSERVATION + " TEXT NULL ");
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Shared Document create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(SHARED_DOCUMENT.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Shared Document destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Shared Document populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {		
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Shared Document view create: invalid operation.");
			return null;
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Shared Document view destroy: invalid operation.");
			return null;
		}
    }





	/**
	 * Plan Table
	 */
    public static final class PLAN_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(PLAN.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + " INTEGER PRIMARY KEY NOT NULL REFERENCES ");
					baseQuery.append(SHARED_DOCUMENT.TABLE + "(" + BaseColumns._ID + "), ");
				// Objective Column.
				baseQuery.append(PLAN.COL_OBJECTIVE + " TEXT NOT NULL, ");
				// Risk Column.
				baseQuery.append(PLAN.COL_RISK + " TEXT NOT NULL, ");
				// CheckList Column.
				baseQuery.append(PLAN.COL_CHECKLIST + " TEXT NULL ");
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Plan create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(PLAN.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Plan destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Plan populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String informationAlias = " I.";
			final String shareddocumentAlias = " S.";
			final String planAlias = " P.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(PLAN.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Column.
				baseQuery.append(planAlias + BaseColumns._ID + ", ");
				// CreationDate Column.
				baseQuery.append(informationAlias + INFORMATION.COL_CREATIONDATE + ", ");
				// LastMofificationDate Column.
				baseQuery.append(informationAlias + INFORMATION.COL_LASTMODIFICATIONDATE + ", ");
				// Priority Column.
				baseQuery.append(informationAlias + INFORMATION.COL_PRIORITY + ", ");
				// Emergency ID Column.
				baseQuery.append(informationAlias + INFORMATION.COL_EMERGENCY_ID + ", ");
				// Description Column.
				baseQuery.append(shareddocumentAlias + SHARED_DOCUMENT.COL_DESCRIPTION + ", ");
				// Observation Column.
				baseQuery.append(shareddocumentAlias + SHARED_DOCUMENT.COL_OBSERVATION + ", ");
				// Objective Column.
				baseQuery.append(planAlias + PLAN.COL_OBJECTIVE + ", ");
				// Risk Column.
				baseQuery.append(planAlias + PLAN.COL_RISK + ", ");
				// CheckList Column.
				baseQuery.append(planAlias + PLAN.COL_CHECKLIST + " ");
			baseQuery.append(FROM);
			baseQuery.append(INFORMATION.TABLE + " I, ");
			baseQuery.append(SHARED_DOCUMENT.TABLE + " S, ");
			baseQuery.append(PLAN.TABLE + " P ");
			baseQuery.append(WHERE);
			baseQuery.append(informationAlias + BaseColumns._ID + " = " + shareddocumentAlias + BaseColumns._ID);
			baseQuery.append(AND);
			baseQuery.append(shareddocumentAlias + BaseColumns._ID + " = " + planAlias + BaseColumns._ID);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Plan view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(PLAN.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Plan view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
    }





	/**
	 * Contact Table
	 */
    public static final class CONTACT_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(CONTACT.TABLE + " (");
				// ID Column.
				baseQuery.append(BaseColumns._ID + PRIMARY_KEY_INTEGER);
				// Entity ID Column.
				baseQuery.append(CONTACT.COL_ENTITY_ID + " INTEGER NOT NULL REFERENCES ");
					baseQuery.append(ENTITY.TABLE + "(" + BaseColumns._ID + "), ");
				// Phone Column.
				baseQuery.append(CONTACT.COL_PHONE + " TEXT NULL, ");
				// Email Column.
				baseQuery.append(CONTACT.COL_EMAIL + " TEXT NULL, ");
				// Radio Column.
				baseQuery.append(CONTACT.COL_RADIO + " TEXT NULL, ");
				// Language Column.
				baseQuery.append(CONTACT.COL_LANGUAGE + " TEXT NOT NULL DEFAULT ('");
					baseQuery.append(EnumLanguage.ENGLISH.name()+"') REFERENCES ");
					baseQuery.append(LANGUAGE.TABLE + "(" + LANGUAGE.COL_LANGUAGE + ") ");
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Contact create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(CONTACT.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Contact destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Contact populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String contactAlias = " C.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(CONTACT.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Column.
				baseQuery.append(contactAlias + BaseColumns._ID + ", ");
				// Entity ID Column.
				baseQuery.append(contactAlias + CONTACT.COL_ENTITY_ID + ", ");
				// Phone Column.
				baseQuery.append(contactAlias + CONTACT.COL_PHONE + ", ");
				// Email Column.
				baseQuery.append(contactAlias + CONTACT.COL_EMAIL + ", ");
				// Radio Column.
				baseQuery.append(contactAlias + CONTACT.COL_RADIO + ", ");
				// Language Column.
				baseQuery.append(contactAlias + CONTACT.COL_LANGUAGE + " ");
			baseQuery.append(FROM);
			baseQuery.append(CONTACT.TABLE + " C ");
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Contact view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(CONTACT.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Contact view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
    }





	/**
	 * Language Table
	 */
    public static final class LANGUAGE_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(LANGUAGE.TABLE + " (");
				// ID Column.
				baseQuery.append(LANGUAGE.COL_LANGUAGE + PRIMARY_KEY_TEXT);
				// Sequence Column.
				baseQuery.append(LANGUAGE.COL_SEQ + " INTEGER UNIQUE");
			baseQuery.append("); ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Language create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(LANGUAGE.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Language destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
			StringBuffer baseQuery = new StringBuffer(INSERT_INTO);
			baseQuery.append(LANGUAGE.TABLE + " (");
			baseQuery.append(LANGUAGE.COL_LANGUAGE + ", " + LANGUAGE.COL_SEQ + ") ");
				baseQuery.append(SELECT);
					baseQuery.append("'" + EnumLanguage.ARABIC.name() + "' AS " + LANGUAGE.COL_LANGUAGE + ", ");
					baseQuery.append(EnumLanguage.ARABIC.ordinal() + " AS " + LANGUAGE.COL_SEQ + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumLanguage.CHINESE.name() + "', " + EnumLanguage.CHINESE.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumLanguage.DEUTSCH.name() + "', " + EnumLanguage.DEUTSCH.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumLanguage.ENGLISH.name() + "', " + EnumLanguage.ENGLISH.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumLanguage.FRENCH.name() + "', " + EnumLanguage.FRENCH.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumLanguage.ITALIAN.name() + "', " + EnumLanguage.ITALIAN.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumLanguage.PORTUGUESE.name() + "', " + EnumLanguage.PORTUGUESE.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumLanguage.RUSSIAN.name() + "', " + EnumLanguage.RUSSIAN.ordinal() + " ");
				baseQuery.append(UNION_ALL_SELECT);
					baseQuery.append("'" + EnumLanguage.SPANISH.name() + "', " + EnumLanguage.SPANISH.ordinal() + " ");
			baseQuery.append("; ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Language populate: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Language view create: invalid operation.");
			return null;
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "Language view destroy: invalid operation.");
			return null;
		}
    }





	/**
	 * Information Contact Table (Relationship)
	 */
    public static final class INFORMATION_CONTACT_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(INFORMATION__CONTACT.TABLE + " (");
				// Information ID Column.
				baseQuery.append(INFORMATION__CONTACT.COL_INFORMATION_ID + " INTEGER NOT NULL REFERENCES ");
					baseQuery.append(INFORMATION.TABLE + "(" + BaseColumns._ID + "), ");
				// Contact ID Column.
				baseQuery.append(INFORMATION__CONTACT.COL_CONTACT_ID + " INTEGER NOT NULL REFERENCES ");
					baseQuery.append(CONTACT.TABLE + "(" + BaseColumns._ID + ") ");
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "InformationContact create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(INFORMATION__CONTACT.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "InformationContact destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "InformationContact populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String informationAlias = " I.";
			final String informationContactAlias = " IC.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(INFORMATION__CONTACT.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Information Column.
				baseQuery.append(informationAlias + BaseColumns._ID + ", ");
				// ID Contact Column.
				baseQuery.append(informationContactAlias + INFORMATION__CONTACT.COL_CONTACT_ID + " ");
			baseQuery.append(FROM);
			baseQuery.append(INFORMATION__CONTACT.TABLE + " IC, ");
			baseQuery.append(INFORMATION.TABLE + " I ");
			baseQuery.append(WHERE);
			baseQuery.append(informationAlias + BaseColumns._ID + " = " + informationContactAlias + INFORMATION__CONTACT.COL_INFORMATION_ID);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "InformationContact view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(INFORMATION__CONTACT.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "InformationContact view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
    }





	/**
	 * Entity Information Table (Relationship)
	 */
    public static final class ENTITY_INFORMATION_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(ENTITY__INFORMATION.TABLE + " (");
				// Entity ID Column.
				baseQuery.append(ENTITY__INFORMATION.COL_ENTITY_ID + " INTEGER NOT NULL REFERENCES ");
					baseQuery.append(ENTITY.TABLE + "(" + BaseColumns._ID + "), ");
				// Information ID Column.
				baseQuery.append(ENTITY__INFORMATION.COL_INFORMATION_ID + " INTEGER NOT NULL REFERENCES ");
					baseQuery.append(INFORMATION.TABLE + "(" + BaseColumns._ID + ") ");
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "EntityInformation create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(ENTITY__INFORMATION.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "EntityInformation destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "EntityInformation populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String entityAlias = " E.";
			final String entityInformationAlias = " EI.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(ENTITY__INFORMATION.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Entity Column.
				baseQuery.append(entityAlias + BaseColumns._ID + ", ");
				// ID Information Column.
				baseQuery.append(entityInformationAlias + ENTITY__INFORMATION.COL_INFORMATION_ID + " ");
			baseQuery.append(FROM);
			baseQuery.append(ENTITY__INFORMATION.TABLE + " EI, ");
			baseQuery.append(ENTITY.TABLE + " E ");
			baseQuery.append(WHERE);
			baseQuery.append(entityAlias + BaseColumns._ID + " = " + entityInformationAlias + ENTITY__INFORMATION.COL_INFORMATION_ID);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "EntityInformation view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(ENTITY__INFORMATION.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "EntityInformation view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
    }





	/**
	 * Service Information Table (Relationship)
	 */
    public static final class SERVICE_INFORMATION_STATEMENT implements BaseStatement {
    	/**
    	 * Create
    	 */
		@Override
		public String getCreate() {
			StringBuffer baseQuery = new StringBuffer(CREATE_TABLE);
			baseQuery.append(SERVICE__INFORMATION.TABLE + " (");
				// Service ID Column.
				baseQuery.append(SERVICE__INFORMATION.COL_SERVICE_ID + " INTEGER NOT NULL REFERENCES ");
					baseQuery.append(SERVICE.TABLE + "(" + BaseColumns._ID + "), ");
				// Information ID Column.
				baseQuery.append(SERVICE__INFORMATION.COL_INFORMATION_ID + " INTEGER NOT NULL REFERENCES ");
					baseQuery.append(INFORMATION.TABLE + "(" + BaseColumns._ID + ") ");
			baseQuery.append("); ");
			CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "ServiceInformation create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy
		 */
		@Override
		public String getDestroy() {
			StringBuffer baseQuery = new StringBuffer(DROP_TABLE);
			baseQuery.append(SERVICE__INFORMATION.TABLE);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "ServiceInformation destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Populate
		 */
		@Override
		public String getPopulate() {
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "ServiceInformation populate: invalid operation.");
			return null;
		}
		/**
		 * Create View
		 */
		@Override
		public String getCreateView() {
			final String serviceAlias = " S.";
			final String serviceInformationAlias = " SI.";
			StringBuffer baseQuery = new StringBuffer(CREATE_VIEW);
			baseQuery.append(SERVICE__INFORMATION.VIEW + VIEW_AS);
			baseQuery.append(SELECT);
				// ID Service Column.
				baseQuery.append(serviceAlias + BaseColumns._ID + ", ");
				// ID Information Column.
				baseQuery.append(serviceInformationAlias + SERVICE__INFORMATION.COL_INFORMATION_ID + " ");
			baseQuery.append(FROM);
			baseQuery.append(SERVICE__INFORMATION.TABLE + " SI, ");
			baseQuery.append(SERVICE.TABLE + " S ");
			baseQuery.append(WHERE);
			baseQuery.append(serviceAlias + BaseColumns._ID + " = " + serviceInformationAlias + SERVICE__INFORMATION.COL_INFORMATION_ID);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "ServiceInformation view create: " + baseQuery.toString());
			return baseQuery.toString();
		}
		/**
		 * Destroy View
		 */
		@Override
		public String getDestroyView() {
			StringBuffer baseQuery = new StringBuffer(DROP_VIEW);
			baseQuery.append(SERVICE__INFORMATION.VIEW);
			baseQuery.append("; ");
		    CustomLogger.getInstance().infoLog(DBStatement.class.getName(), "ServiceInformation view destroy: " + baseQuery.toString());
			return baseQuery.toString();
		}
    }










    /**********************************************************************/
    /************************ Version 2 ***********************************/
    /**********************************************************************/

}
