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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import br.ufrj.ppgi.jemf.core.cooperation.EnumOrganizationStatus;
import br.ufrj.ppgi.jemf.core.coordination.EnumLevel;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class AffectedOrganizationView extends CustomLinearView implements CustomView {

	/**
	 * Labels.
	 */
	private TextView lblAffectedOrganizationName, lblAffectedOrganizationStatus;



	/**
	 * Inputs Fields.
	 */
	/**
	 * AffectedOrganization Name.
	 */
	private EditText txtAffectedOrganizationName;
	/**
	 * AffectedOrganization Description.
	 */
	private EditText txtAffectedOrganizationDescription;
	/**
	 * AffectedOrganization Status.
	 */
	private Spinner sprAffectedOrganizationStatus;
	/**
	 * AffectedOrganization Level.
	 */
	private Spinner sprAffectedOrganizationLevel;



	/**
	 * Parent Constructor.
	 * @param context
	 */
	private AffectedOrganizationView(Context context) {
		super(context);
    	CustomLogger.getInstance().infoLog(AffectedOrganizationView.class.getName(), "Class started.");
	}

	/**
	 * Constructor.
	 * @param context
	 * @param isListViewItem
	 */
	public AffectedOrganizationView(Context context, boolean isListViewItem) {
		// Super constructor.
		this(context);
		// Set default orientation.
		this.setOrientation(LinearLayout.VERTICAL);

		// If AffectedOrganizationview is to be an Item of ListView, as a List Row.
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
    	CustomLogger.getInstance().infoLog(AffectedOrganizationView.class.getName(), "Master layout retrieved.");
		// Field Name.
    	lblAffectedOrganizationName = new TextView(getContext());
    	lblAffectedOrganizationName.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
    	lblAffectedOrganizationName.setTextColor(Color.BLACK);
    	lblAffectedOrganizationName.setTypeface(Typeface.DEFAULT_BOLD);

		// Field Status.
    	lblAffectedOrganizationStatus = new TextView(getContext());
    	lblAffectedOrganizationStatus.setTextAppearance(getContext(), android.R.style.TextAppearance_Small);
    	lblAffectedOrganizationStatus.setTextColor(Color.BLACK);
    	lblAffectedOrganizationStatus.setTypeface(null, Typeface.ITALIC);

		// Set to main layout.
		this.addView(lblAffectedOrganizationName, newWrapLayoutParams());
		this.addView(lblAffectedOrganizationStatus, newWrapLayoutParams());
	}

	/* (non-Javadoc)
	 * @see br.ufrj.ppgi.jemf.mobile.view.CustomView#getDetailLayout()
	 */
	@Override
	public void getDetailLayout() {
    	CustomLogger.getInstance().infoLog(AffectedOrganizationView.class.getName(), "Detail layout retrieved.");
		// In this case, main layout will group all AffectedOrganization Fields inside itself, becoming a Parent View.
		this.setLayoutParams(newMatchLayoutParams());

		// Field Rows.
		// AffectedOrganization Name Field - Row 1.
		txtAffectedOrganizationName = new EditText(getContext());
		txtAffectedOrganizationName.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtAffectedOrganizationName.setSingleLine();
		this.addView(newLinearRow("NAME", txtAffectedOrganizationName, true));

		// AffectedOrganization Description Field - Row 2.
		txtAffectedOrganizationDescription = new EditText(getContext());
		txtAffectedOrganizationDescription.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtAffectedOrganizationDescription.setLines(5);
		this.addView(newLinearRow("DESCRIPTION", txtAffectedOrganizationDescription, true));

		// AffectedOrganization Status Field - Row 3.
		sprAffectedOrganizationStatus = new Spinner(getContext());
		// Create the item list of spinner.
		ArrayAdapter<String> spinnerArrayAdapterStatus = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item);
		spinnerArrayAdapterStatus.addAll(EnumOrganizationStatus.names);
		// Set the drop down view of spinner.
		spinnerArrayAdapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);		
		sprAffectedOrganizationStatus.setAdapter(spinnerArrayAdapterStatus);
		this.addView(newLinearRow("STATUS", sprAffectedOrganizationStatus, true));

		// AffectedOrganization Level Field - Row 4.
		sprAffectedOrganizationLevel = new Spinner(getContext());
		// Create the item list of spinner.
		ArrayAdapter<String> spinnerArrayAdapterLevel = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item);
		spinnerArrayAdapterLevel.addAll(EnumLevel.names);
		// Set the drop down view of spinner.
		spinnerArrayAdapterLevel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sprAffectedOrganizationLevel.setAdapter(spinnerArrayAdapterLevel);
		this.addView(newLinearRow("LEVEL", sprAffectedOrganizationLevel, true));
	}

	/**
	 * Labels Getters and Setters.
	 */
    public String getAffectedOrganizationNameLabel() {
        return this.lblAffectedOrganizationName.getText().toString();
    }
    public void setAffectedOrganizationNameLabel(String name) {
            this.lblAffectedOrganizationName.setText(name);
    }

    public String getAffectedOrganizationStatusLabel() {
        return this.lblAffectedOrganizationStatus.getText().toString();
    }
    public void setAffectedOrganizationStatusLabel(String status) {
            this.lblAffectedOrganizationStatus.setText(status);
    }

	/**
	 * Fields Getters and Setters.
	 */
    /**
     * AffectedOrganization Name Field.
     */
	public String getAffectedOrganizationNameText() {
	    return this.txtAffectedOrganizationName.getText().toString();
	}
	public void setAffectedOrganizationNameText(String name) {
		this.txtAffectedOrganizationName.setText(name);
	}
    /**
     * AffectedOrganization Description Field.
     */
	public String getAffectedOrganizationDescriptionText() {
		return this.txtAffectedOrganizationDescription.getText().toString();
	}	 	 
	public void setAffectedOrganizationDescriptionText(String description) {
		this.txtAffectedOrganizationDescription.setText(description);
	}
    /**
     * AffectedOrganization Status Field.
     */
	public String getAffectedOrganizationStatusText() {
		return this.sprAffectedOrganizationStatus.getSelectedItem().toString();
	}
	@SuppressWarnings("unchecked")
	public void setAffectedOrganizationStatusText(String status) {
		this.sprAffectedOrganizationStatus.setSelection(((ArrayAdapter<String>) this.sprAffectedOrganizationStatus.getAdapter()).getPosition(status));
	}
    /**
     * AffectedOrganization Level Field.
     */
	public String getAffectedOrganizationLevelText() {
		return this.sprAffectedOrganizationLevel.getSelectedItem().toString();
	}
	@SuppressWarnings("unchecked")
	public void setAffectedOrganizationLevelText(String level) {
		this.sprAffectedOrganizationLevel.setSelection(((ArrayAdapter<String>) this.sprAffectedOrganizationLevel.getAdapter()).getPosition(level));
	}

}
