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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class CustomProviderChecker {

	/**
	 * Parent Context.
	 */
	private static Context parentContext;
	/**
	 * Content Provider list retrieved from device.
	 */
	private static List<Map<String, String>> contentProviderList;
	/**
	 * JEMF provider was found in device.
	 */
	private static boolean jemfProviderFound;



	/**
	 * Constructor.
	 * Singleton - This method ensures that there exists only one CustomProviderChecker instance at any time.
	 * Creates a helper object to find the Custom Provider.
	 * @param context
	 */
    public static CustomProviderChecker check(Context context) {
    	parentContext = context;
    	return SingletonHolder.INSTANCE;
    }



    /**
     * Singleton - Until we need an instance, the SingletonHolder class will not be initialized until required.
     */
	private static class SingletonHolder {
		private static final CustomProviderChecker INSTANCE = new CustomProviderChecker(parentContext);
	}



	/**
	 * Constructor.
	 * Find all the content providers installed on the device and check if JEMF provider exists.
	 * Check if a JEMF provider was declared in application's manifest, otherwise application will not read/write JEMF database.
	 * @param context
	 */
	private CustomProviderChecker(Context context) {
    	CustomLogger.getInstance().infoLog(CustomProviderChecker.class.getName(), "Class started.");
		final String fieldLabel = "Label";
		final String fieldAuthority = "Authority";
		final String fieldPermission = "Permission";

		jemfProviderFound = false;
		// Create a data List Object.
		final List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
		try {
			// Get the current package manager.
			PackageManager packageManager = context.getPackageManager();
			// Get the installed providers.
			List<PackageInfo> packs = packageManager.getInstalledPackages(PackageManager.GET_PROVIDERS);
			// Check each provider info.
			for (PackageInfo pack : packs) {
				ProviderInfo[] providers = pack.providers;
				if (providers != null) {
					for (ProviderInfo provider : providers) {
						// Get the provider read permission.
						String permission = provider.readPermission;
						// Get the provider label.
						String label = provider.loadLabel(packageManager).toString();
						// Get the provider authority.
						String authority = provider.authority;
						// Save info into a Map Object.
						Map<String, String> group = new HashMap<String, String>();
						group.put(fieldPermission, permission);
						group.put(fieldLabel, label);
						group.put(fieldAuthority, authority);
						groupData.add(group);

						// Filtered the ones we can read (JEMF) by checking the permission.
						if ( (permission != null) &&
							 (permission.equals(ProviderStatement.PROVIDER_SIGNATURE)) &&
							 (authority.equals(ProviderStatement.AUTHORITY)) ) {
							jemfProviderFound = true;
						}
					}
				}
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	    contentProviderList = groupData;
	}



	/**
	 * Get the Content Providers found in device.
	 * @return List<Map<String, String>>
	 * 			Content Providers list.
	 */
	public List<Map<String, String>> getContentProviders() {
    	CustomLogger.getInstance().infoLog(CustomProviderChecker.class.getName(), "Content Providers list retrieved.");
		return contentProviderList;
	}

	/**
	 * Get the result of check method if JEMF provider was found.
	 * @return boolean
	 * 			JEMF provider was found or not.
	 */
	public boolean jemfProviderFound() {
    	CustomLogger.getInstance().infoLog(CustomProviderChecker.class.getName(), "JEMF Provider checked on device.");
		return jemfProviderFound;
	}

}
