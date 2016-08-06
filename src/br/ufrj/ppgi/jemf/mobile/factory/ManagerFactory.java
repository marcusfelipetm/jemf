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
package br.ufrj.ppgi.jemf.mobile.factory;

import android.content.Context;
import br.ufrj.ppgi.jemf.mobile.FeatureHolder;
import br.ufrj.ppgi.jemf.mobile.manager.AffectedOrganizationManager;
import br.ufrj.ppgi.jemf.mobile.manager.CommanderManager;
import br.ufrj.ppgi.jemf.mobile.manager.ContactManager;
import br.ufrj.ppgi.jemf.mobile.manager.EmergencyManager;
import br.ufrj.ppgi.jemf.mobile.manager.EquipmentManager;
import br.ufrj.ppgi.jemf.mobile.manager.HealthCareUnitManager;
import br.ufrj.ppgi.jemf.mobile.manager.InterestPointManager;
import br.ufrj.ppgi.jemf.mobile.manager.LocationManager;
import br.ufrj.ppgi.jemf.mobile.manager.Manager;
import br.ufrj.ppgi.jemf.mobile.manager.MedicalManager;
import br.ufrj.ppgi.jemf.mobile.manager.MissionManager;
import br.ufrj.ppgi.jemf.mobile.manager.PlanManager;
import br.ufrj.ppgi.jemf.mobile.manager.ResponderManager;
import br.ufrj.ppgi.jemf.mobile.manager.TaskManager;
import br.ufrj.ppgi.jemf.mobile.manager.TeamManager;
import br.ufrj.ppgi.jemf.mobile.manager.TextMessageManager;
import br.ufrj.ppgi.jemf.mobile.manager.VictimManager;
import br.ufrj.ppgi.jemf.mobile.manager.VolunteerManager;
import br.ufrj.ppgi.jemf.mobile.manager.WitnessManager;
import br.ufrj.ppgi.jemf.mobile.provider.CustomProvider;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class ManagerFactory {

	/**
	 * Constructor.
	 * This should be private to prevent direct instantiation.
     * Make call to static factory method "newManager()" instead.
	 */
	private ManagerFactory() {
		CustomLogger.getInstance().infoLog(ManagerFactory.class.getName(), "Class started.");
	}



	/**
	 * Manager Factory instantiation.
	 * @param context
	 * @return Manager
	 * 			A specific Manager Object.
	 */
	public static Manager newManager(Context context) {
		Manager manager = null;
		// Create a Manager by the feature type.
		switch (FeatureHolder.getFeature()) {
			case EMERGENCY:
				manager = new EmergencyManager();
				break;
			case MISSION:
				manager = new MissionManager();
				break;
			case TASK:
				manager = new TaskManager();
				break;
			case EQUIPMENT:
				manager = new EquipmentManager();
				break;
			case HEALTH_CARE_UNIT:
				manager = new HealthCareUnitManager();
				break;
			case AFFECTED_ORGANIZATION:
				manager = new AffectedOrganizationManager();
				break;
			case COMMANDER:
				manager = new CommanderManager();
				break;
			case RESPONDER:
				manager = new ResponderManager();
				break;
			case MEDICAL:
				manager = new MedicalManager();
				break;
			case VOLUNTEER:
				manager = new VolunteerManager();
				break;
			case WITNESS:
				manager = new WitnessManager();
				break;
			case VICTIM:
				manager = new VictimManager();
				break;
			case TEAM:
				manager = new TeamManager();
				break;
			case LOCATION:
				manager = new LocationManager();
				break;
			case INTEREST_POINT:
				manager = new InterestPointManager();
				break;
			case TEXT_MESSAGE:
				manager = new TextMessageManager();
				break;
			case PLAN:
				manager = new PlanManager();
				break;
			case CONTACT:
				manager = new ContactManager();
				break;
			default:
				String errorMsg = "Unknown Feautre Type.";
	  	    	CustomLogger.getInstance().errorLog(CustomProvider.class.getName(), errorMsg);
				throw new IllegalArgumentException(errorMsg);
		}
		//********************************************************************************//
		// Set parent Context to Manager, allowing to execute the Content Provider methods.
		//********************************************************************************//
		manager.setContext(context);
		CustomLogger.getInstance().infoLog(ManagerFactory.class.getName(), "New Manager created.");
		return manager;
	}

}
