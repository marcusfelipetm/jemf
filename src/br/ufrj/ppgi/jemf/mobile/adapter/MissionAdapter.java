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
package br.ufrj.ppgi.jemf.mobile.adapter;

import br.ufrj.ppgi.jemf.mobile.provider.ProviderStatement;
import br.ufrj.ppgi.jemf.mobile.view.MissionView;
import br.ufrj.ppgi.jemf.utils.CustomLogger;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Marcus Machado
 *
 */
public class MissionAdapter extends CustomCursorAdapter {

	/**
	 * Constructor.
	 * @param context
	 * @param cursor
	 * @param flags
	 */
	public MissionAdapter(Context context, Cursor cursor, int flags) {
		super(context, cursor, flags);
    	CustomLogger.getInstance().infoLog(MissionAdapter.class.getName(), "Class started.");
	}

	/**
	 * Create a new View associated to Adapter.
	 * @param context
	 * @param cursor
	 * @param parent
	 * @return View
	 */
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
    	return new MissionView(context, true);
	}

	/**
	 * Bind a View associated to Adapter.
	 * @param view
	 * @param context
	 * @param cursor
	 */
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
        final String title = cursor.getString(ProviderStatement.MISSION.INDEX_TITLE);
        final String priority = cursor.getString(ProviderStatement.MISSION.INDEX_PRIORITY);

	    MissionView missionView = (MissionView) view;
		// Customize view.
	    if (missionView != null) {
	    	missionView.setMissionTitleLabel(title);
	    	missionView.setMissionPriorityLabel("Priority: " + priority);
	    }
	}

}
