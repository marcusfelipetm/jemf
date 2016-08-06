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

import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import br.ufrj.ppgi.jemf.mobile.FeatureHolder;
import br.ufrj.ppgi.jemf.mobile.adapter.AffectedOrganizationAdapter;
import br.ufrj.ppgi.jemf.mobile.adapter.CommanderAdapter;
import br.ufrj.ppgi.jemf.mobile.adapter.ContactAdapter;
import br.ufrj.ppgi.jemf.mobile.adapter.CustomCursorAdapter;
import br.ufrj.ppgi.jemf.mobile.adapter.EmergencyAdapter;
import br.ufrj.ppgi.jemf.mobile.adapter.EquipmentAdapter;
import br.ufrj.ppgi.jemf.mobile.adapter.HealthCareUnitAdapter;
import br.ufrj.ppgi.jemf.mobile.adapter.InterestPointAdapter;
import br.ufrj.ppgi.jemf.mobile.adapter.LocationAdapter;
import br.ufrj.ppgi.jemf.mobile.adapter.MedicalAdapter;
import br.ufrj.ppgi.jemf.mobile.adapter.MissionAdapter;
import br.ufrj.ppgi.jemf.mobile.adapter.PlanAdapter;
import br.ufrj.ppgi.jemf.mobile.adapter.ResponderAdapter;
import br.ufrj.ppgi.jemf.mobile.adapter.TaskAdapter;
import br.ufrj.ppgi.jemf.mobile.adapter.TeamAdapter;
import br.ufrj.ppgi.jemf.mobile.adapter.TextMessageAdapter;
import br.ufrj.ppgi.jemf.mobile.adapter.VictimAdapter;
import br.ufrj.ppgi.jemf.mobile.adapter.VolunteerAdapter;
import br.ufrj.ppgi.jemf.mobile.adapter.WitnessAdapter;
import br.ufrj.ppgi.jemf.mobile.loader.AffectedOrganizationLoader;
import br.ufrj.ppgi.jemf.mobile.loader.CommanderLoader;
import br.ufrj.ppgi.jemf.mobile.loader.ContactLoader;
import br.ufrj.ppgi.jemf.mobile.loader.CustomLoader;
import br.ufrj.ppgi.jemf.mobile.loader.EmergencyLoader;
import br.ufrj.ppgi.jemf.mobile.loader.EquipmentLoader;
import br.ufrj.ppgi.jemf.mobile.loader.HealthCareUnitLoader;
import br.ufrj.ppgi.jemf.mobile.loader.InterestPointLoader;
import br.ufrj.ppgi.jemf.mobile.loader.LocationLoader;
import br.ufrj.ppgi.jemf.mobile.loader.MedicalLoader;
import br.ufrj.ppgi.jemf.mobile.loader.MissionLoader;
import br.ufrj.ppgi.jemf.mobile.loader.PlanLoader;
import br.ufrj.ppgi.jemf.mobile.loader.ResponderLoader;
import br.ufrj.ppgi.jemf.mobile.loader.TaskLoader;
import br.ufrj.ppgi.jemf.mobile.loader.TeamLoader;
import br.ufrj.ppgi.jemf.mobile.loader.TextMessageLoader;
import br.ufrj.ppgi.jemf.mobile.loader.VictimLoader;
import br.ufrj.ppgi.jemf.mobile.loader.VolunteerLoader;
import br.ufrj.ppgi.jemf.mobile.loader.WitnessLoader;
import br.ufrj.ppgi.jemf.mobile.provider.CustomParams;
import br.ufrj.ppgi.jemf.mobile.provider.CustomProvider;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class AdapterFactory {

	/**
	 * Current Adapter.
	 */
	private static CustomCursorAdapter adapter = null;
	/**
	 * Current Content Loader.
	 */
	private static android.app.LoaderManager.LoaderCallbacks<Cursor> loader = null;



	/**********************************************************************************
	 ******************************* Adapter Methods **********************************
	 *********************************************************************************/
	/**
	 * Constructor.
	 * This should be private to prevent direct instantiation.
     * Make call to static factory method "newLayout()" instead.
	 */
	private AdapterFactory() {
    	CustomLogger.getInstance().infoLog(AdapterFactory.class.getName(), "Class started.");
	}

	/**
	 * Factory Method.
	 * @return CustomCursorAdapter
	 * 			New adapter by requested feature.
	 */
	public static CustomCursorAdapter newAdapter(Context context, Cursor cursor, int flags) {
		// Create a Adapter by the feature type.
		switch (FeatureHolder.getFeature()) {
			case EMERGENCY:
				adapter = new EmergencyAdapter(context, cursor, flags);
				break;
			case MISSION:
				adapter = new MissionAdapter(context, cursor, flags);
				break;
			case TASK:
				adapter = new TaskAdapter(context, cursor, flags);
				break;
			case EQUIPMENT:
				adapter = new EquipmentAdapter(context, cursor, flags);
				break;
			case HEALTH_CARE_UNIT:
				adapter = new HealthCareUnitAdapter(context, cursor, flags);
				break;
			case AFFECTED_ORGANIZATION:
				adapter = new AffectedOrganizationAdapter(context, cursor, flags);
				break;
			case COMMANDER:
				adapter = new CommanderAdapter(context, cursor, flags);
				break;
			case RESPONDER:
				adapter = new ResponderAdapter(context, cursor, flags);
				break;
			case MEDICAL:
				adapter = new MedicalAdapter(context, cursor, flags);
				break;
			case VOLUNTEER:
				adapter = new VolunteerAdapter(context, cursor, flags);
				break;
			case WITNESS:
				adapter = new WitnessAdapter(context, cursor, flags);
				break;
			case VICTIM:
				adapter = new VictimAdapter(context, cursor, flags);
				break;
			case TEAM:
				adapter = new TeamAdapter(context, cursor, flags);
				break;
			case LOCATION:
				adapter = new LocationAdapter(context, cursor, flags);
				break;
			case INTEREST_POINT:
				adapter = new InterestPointAdapter(context, cursor, flags);
				break;
			case TEXT_MESSAGE:
				adapter = new TextMessageAdapter(context, cursor, flags);
				break;
			case PLAN:
				adapter = new PlanAdapter(context, cursor, flags);
				break;
			case CONTACT:
				adapter = new ContactAdapter(context, cursor, flags);
				break;
			default:
				String errorMsg = "Unknown Feautre Type.";
	  	    	CustomLogger.getInstance().errorLog(CustomProvider.class.getName(), errorMsg);
				throw new IllegalArgumentException(errorMsg);
		}
    	CustomLogger.getInstance().infoLog(AdapterFactory.class.getName(), "New Adapter created.");
		return adapter;
	}



	/**********************************************************************************
	 ******************************* Loader Methods ***********************************
	 *********************************************************************************/
	/**
	 * Load data through a new Loader.
	 * Factory Method.
	 * @return LoaderCallbacks
	 * 			New loader.
	 */
	public static LoaderCallbacks<Cursor> newLoader(final Context context, final CustomParams customParams) {
		// Check if Adapter is created.
		if (adapter == null) {
			String errorMsg = "A new Adapter must be created before a Loader instantiation.";
  	    	CustomLogger.getInstance().errorLog(CustomProvider.class.getName(), errorMsg);
			throw new RuntimeException(errorMsg);
		}	

		loader = new LoaderCallbacks<Cursor>() {
			@Override
			public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
				return getNewLoader(context, customParams);	
			}

			@Override
			public void onLoadFinished(Loader<Cursor> load, Cursor data) {
		        // Swap the new cursor in. 
				// This will take care of closing the old cursor once we return.
		        adapter.swapCursor(data);
		        adapter.notifyDataSetChanged();
			}

			@Override
			public void onLoaderReset(Loader<Cursor> data) {
		        // This is called when the last Cursor provided to onLoadFinished() above is about to be closed.
				// We need to make sure we are no longer using it.
				adapter.swapCursor(null);
			}
		};
		return loader;
	}

	/**
	 * Get a new Loader accordingly to the feature.  
	 * @param context
	 * @return Loader
	 * 			New loader by requested feature.
	 */
	private static Loader<Cursor> getNewLoader(final Context context, final CustomParams customParams) {
		CustomLoader cursorLoader = null;
		try {
			// Create a Loader by the feature type.
			switch (FeatureHolder.getFeature()) {
				case EMERGENCY:
					cursorLoader = new EmergencyLoader(context, customParams);
					break;
				case MISSION:
					cursorLoader = new MissionLoader(context, customParams);
					break;
				case TASK:
					cursorLoader = new TaskLoader(context, customParams);
					break;
				case EQUIPMENT:
					cursorLoader = new EquipmentLoader(context, customParams);
					break;
				case HEALTH_CARE_UNIT:
					cursorLoader = new HealthCareUnitLoader(context, customParams);
					break;
				case AFFECTED_ORGANIZATION:
					cursorLoader = new AffectedOrganizationLoader(context, customParams);
					break;
				case COMMANDER:
					cursorLoader = new CommanderLoader(context, customParams);
					break;
				case RESPONDER:
					cursorLoader = new ResponderLoader(context, customParams);
					break;
				case MEDICAL:
					cursorLoader = new MedicalLoader(context, customParams);
					break;
				case VOLUNTEER:
					cursorLoader = new VolunteerLoader(context, customParams);
					break;
				case WITNESS:
					cursorLoader = new WitnessLoader(context, customParams);
					break;
				case VICTIM:
					cursorLoader = new VictimLoader(context, customParams);
					break;
				case TEAM:
					cursorLoader = new TeamLoader(context, customParams);
					break;
				case LOCATION:
					cursorLoader = new LocationLoader(context, customParams);
					break;
				case INTEREST_POINT:
					cursorLoader = new InterestPointLoader(context, customParams);
					break;
				case TEXT_MESSAGE:
					cursorLoader = new TextMessageLoader(context, customParams);
					break;
				case PLAN:
					cursorLoader = new PlanLoader(context, customParams);
					break;
				case CONTACT:
					cursorLoader = new ContactLoader(context, customParams);
					break;
				default:
					String errorMsg = "Unknown Feautre Type.";
		  	    	CustomLogger.getInstance().errorLog(CustomProvider.class.getName(), errorMsg);
					throw new IllegalArgumentException(errorMsg);
			}
		} catch (Exception e) {
			cursorLoader.rethrow();
			e.printStackTrace();
		}
		return cursorLoader;
	}

}
