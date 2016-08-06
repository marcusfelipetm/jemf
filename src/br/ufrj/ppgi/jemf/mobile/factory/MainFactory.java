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
import android.database.Cursor;
import android.widget.CursorAdapter;
import br.ufrj.ppgi.jemf.mobile.CustomBuildConfig;
import br.ufrj.ppgi.jemf.mobile.adapter.CustomCursorAdapter;
import br.ufrj.ppgi.jemf.mobile.manager.Manager;
import br.ufrj.ppgi.jemf.mobile.provider.CustomParams;
import br.ufrj.ppgi.jemf.mobile.provider.CustomProviderChecker;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class MainFactory {

	/**
	 * Parent Context.
	 */
	private Context parentContext;
	/**
	 * Current Manager.
	 */
	private static Manager manager = null;
	/**
	 * Current Adapter.
	 */
	private static CursorAdapter adapter = null;
	/**
	 * Current Loader.
	 */
	private static LoaderCallbacks<Cursor> loader = null;



	/**
	 * Constructor.
	 * Main Factory instantiation.
	 */
	public MainFactory(Context context) {
    	CustomLogger.getInstance().infoLog(MainFactory.class.getName(), "Class started.");
		this.parentContext = context;
		// Check if a JEMF provider was declared in application's manifest, otherwise application will not read/write JEMF database.
		if (CustomBuildConfig.TEST) {
			// Note: Do NOT check when is running tests.
		} else {
			CustomProviderChecker customProviderChecker = CustomProviderChecker.check(this.parentContext);
			// Content Provider was found?
			if (!(customProviderChecker.jemfProviderFound())) {
				String errorMsg = "Provider not declared in application's manisfet.";
		    	CustomLogger.getInstance().errorLog(MainFactory.class.getName(), errorMsg);
				throw new RuntimeException(errorMsg);
			}
    	}
	}



	/**
	 * Get a new Manager.
	 * @return Manager
	 */
	public Manager getManager() {
		try {
			MainFactory.setManager(ManagerFactory.newManager(parentContext));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	CustomLogger.getInstance().infoLog(MainFactory.class.getName(), "New Manager created.");
		return MainFactory.manager;
	}

	/**
	 * Get a new Adapter.
	 * @return CursorAdapter
	 */
	public CursorAdapter getAdapter() {
		try {
			MainFactory.setAdapter(AdapterFactory.newAdapter(parentContext, null, 0));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	CustomLogger.getInstance().infoLog(MainFactory.class.getName(), "New Adapter created.");
		return MainFactory.adapter;	
	}

	/**
	 * Get a new Loader.
	 * @return LoaderCallbacks
	 */
	public LoaderCallbacks<Cursor> getLoader(final String selection, final String[] selectionArgs, final String sortOrder) {
		try {
			MainFactory.setLoader(AdapterFactory.newLoader(parentContext, new CustomParams(selection, selectionArgs, sortOrder)));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	CustomLogger.getInstance().infoLog(MainFactory.class.getName(), "New Loader created.");
		return MainFactory.loader;
	}



	/**
	 * Set the current Manager.
	 * @param manager
	 * 			The manager to set.
	 */
	private static void setManager(Manager manager) {
		MainFactory.manager = manager;
	}

	/**
	 * Set the current Adapter.
	 * @param adapter
	 * 			The adapter to set.
	 */
	private static void setAdapter(CustomCursorAdapter adapter) {
		MainFactory.adapter = adapter;
	}
	
	/**
	 * Set the current Loader.
	 * @param loader
	 * 			The loader to set.
	 */
	private static void setLoader(LoaderCallbacks<Cursor> loader) {
		MainFactory.loader = loader;
	}

}
