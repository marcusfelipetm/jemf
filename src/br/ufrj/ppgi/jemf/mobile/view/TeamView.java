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
package br.ufrj.ppgi.jemf.mobile.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class TeamView extends CustomLinearView implements CustomView {

	/**
	 * Labels.
	 */
	private TextView lblTeamName;

	/**
	 * Inputs Fields.
	 */
	/**
	 * Team Name.
	 */
	private EditText txtTeamName;



	/**
	 * Parent Constructor.
	 * @param context
	 */
	private TeamView(Context context) {
		super(context);
    	CustomLogger.getInstance().infoLog(TeamView.class.getName(), "Class started.");
	}

	/**
	 * Constructor.
	 * @param context
	 * @param isListViewItem
	 */
	public TeamView(Context context, boolean isListViewItem) {
		// Super constructor.
		this(context);
		// Set default orientation.
		this.setOrientation(LinearLayout.VERTICAL);

		// If TeamView is to be an Item of ListView, as a List Row.
		if (isListViewItem) {
			this.getMasterLayout();
		// Else configure it to show in a Fragment or Activity, as Detail Layout.
		} else {
			this.getDetailLayout();
		}
	}



	/* (non-Javadoc)
	 * @see br.ufrj.ppgi.jemf.mobile.view.CustomView#getMasterLayout()
	 */
	@Override
	public void getMasterLayout() {
    	CustomLogger.getInstance().infoLog(TeamView.class.getName(), "Master layout retrieved.");
		// Field Name.
    	lblTeamName = new TextView(getContext());
    	lblTeamName.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
    	lblTeamName.setTextColor(Color.BLACK);
    	lblTeamName.setTypeface(Typeface.DEFAULT_BOLD);

		// Set to main layout.
		this.addView(lblTeamName, newWrapLayoutParams());
	}

	/* (non-Javadoc)
	 * @see br.ufrj.ppgi.jemf.mobile.view.CustomView#getDetailLayout()
	 */
	@Override
	public void getDetailLayout() {
    	CustomLogger.getInstance().infoLog(TeamView.class.getName(), "Detail layout retrieved.");
		// In this case, main layout will group all Team Fields inside itself, becoming a Parent View.
		this.setLayoutParams(newMatchLayoutParams());

		// Field Rows.
		// Team Name Field - Row 1.
		txtTeamName = new EditText(getContext());
		txtTeamName.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtTeamName.setSingleLine();
		this.addView(newLinearRow("NAME", txtTeamName, true));
	}

	/**
	 * Labels Getters and Setters.
	 */
    public String getTeamNameLabel() {
        return this.lblTeamName.getText().toString();
    }
    public void setTeamNameLabel(String name) {
            this.lblTeamName.setText(name);
    }

	/**
	 * Fields Getters and Setters.
	 */
    /**
     * Team Name Field.
     */
	public String getTeamNameText() {
	    return this.txtTeamName.getText().toString();
	}
	public void setTeamNameText(String name) {
		this.txtTeamName.setText(name);
	}

}
