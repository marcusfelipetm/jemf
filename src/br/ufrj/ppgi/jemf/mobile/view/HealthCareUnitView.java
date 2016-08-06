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
import br.ufrj.ppgi.jemf.core.coordination.EnumLevel;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class HealthCareUnitView extends CustomLinearView implements CustomView {

	/**
	 * Labels.
	 */
	private TextView lblHealthCareUnitName, lblHealthCareUnitCapacity;



	/**
	 * Inputs Fields.
	 */
	/**
	 * HealthCareUnit Name.
	 */
	private EditText txtHealthCareUnitName;
	/**
	 * HealthCareUnit Description.
	 */
	private EditText txtHealthCareUnitDescription;
	/**
	 * HealthCareUnit Description.
	 */
	private EditText txtHealthCareUnitCapacity;
	/**
	 * HealthCareUnit Level.
	 */
	private Spinner sprHealthCareUnitLevel;



	/**
	 * Parent Constructor.
	 * @param context
	 */
	private HealthCareUnitView(Context context) {
		super(context);
    	CustomLogger.getInstance().infoLog(HealthCareUnitView.class.getName(), "Class started.");
	}

	/**
	 * Constructor.
	 * @param context
	 * @param isListViewItem
	 */
	public HealthCareUnitView(Context context, boolean isListViewItem) {
		// Super constructor.
		this(context);
		// Set default orientation.
		this.setOrientation(LinearLayout.VERTICAL);

		// If HealthCareUnitview is to be an Item of ListView, as a List Row.
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
    	CustomLogger.getInstance().infoLog(HealthCareUnitView.class.getName(), "Master layout retrieved.");
		// Field Name.
    	lblHealthCareUnitName = new TextView(getContext());
    	lblHealthCareUnitName.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
    	lblHealthCareUnitName.setTextColor(Color.BLACK);
    	lblHealthCareUnitName.setTypeface(Typeface.DEFAULT_BOLD);

		// Field Status.
    	lblHealthCareUnitCapacity = new TextView(getContext());
    	lblHealthCareUnitCapacity.setTextAppearance(getContext(), android.R.style.TextAppearance_Small);
    	lblHealthCareUnitCapacity.setTextColor(Color.BLACK);
    	lblHealthCareUnitCapacity.setTypeface(null, Typeface.ITALIC);

		// Set to main layout.
		this.addView(lblHealthCareUnitName, newWrapLayoutParams());
		this.addView(lblHealthCareUnitCapacity, newWrapLayoutParams());
	}

	/* (non-Javadoc)
	 * @see br.ufrj.ppgi.jemf.mobile.view.CustomView#getDetailLayout()
	 */
	@Override
	public void getDetailLayout() {
    	CustomLogger.getInstance().infoLog(HealthCareUnitView.class.getName(), "Detail layout retrieved.");
		// In this case, main layout will group all HealthCareUnit Fields inside itself, becoming a Parent View.
		this.setLayoutParams(newMatchLayoutParams());

		// Field Rows.
		// HealthCareUnit Name Field - Row 1.
		txtHealthCareUnitName = new EditText(getContext());
		txtHealthCareUnitName.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtHealthCareUnitName.setSingleLine();
		this.addView(newLinearRow("NAME", txtHealthCareUnitName, true));

		// HealthCareUnit Description Field - Row 2.
		txtHealthCareUnitDescription = new EditText(getContext());
		txtHealthCareUnitDescription.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtHealthCareUnitDescription.setLines(5);
		this.addView(newLinearRow("DESCRIPTION", txtHealthCareUnitDescription, true));

		// HealthCareUnit Capacity Field - Row 3.
		txtHealthCareUnitCapacity = new EditText(getContext());
		txtHealthCareUnitCapacity.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtHealthCareUnitCapacity.setSingleLine();
		this.addView(newLinearRow("CAPACITY", txtHealthCareUnitCapacity, true));

		// HealthCareUnit Level Field - Row 4.
		sprHealthCareUnitLevel = new Spinner(getContext());
		// Create the item list of spinner.
		ArrayAdapter<String> spinnerArrayAdapterLevel = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item);
		spinnerArrayAdapterLevel.addAll(EnumLevel.names);
		// Set the drop down view of spinner.
		spinnerArrayAdapterLevel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sprHealthCareUnitLevel.setAdapter(spinnerArrayAdapterLevel);
		this.addView(newLinearRow("LEVEL", sprHealthCareUnitLevel, true));
	}

	/**
	 * Labels Getters and Setters.
	 */
    public String getHealthCareUnitNameLabel() {
        return this.lblHealthCareUnitName.getText().toString();
    }
    public void setHealthCareUnitNameLabel(String name) {
            this.lblHealthCareUnitName.setText(name);
    }
    public String getHealthCareUnitCapacityLabel() {
        return this.lblHealthCareUnitCapacity.getText().toString();
    }
    public void setHealthCareUnitCapacityLabel(String capacity) {
            this.lblHealthCareUnitCapacity.setText(capacity);
    }

	/**
	 * Fields Getters and Setters.
	 */
    /**
     * HealthCareUnit Name Field.
     */
	public String getHealthCareUnitNameText() {
	    return this.txtHealthCareUnitName.getText().toString();
	}
	public void setHealthCareUnitNameText(String name) {
		this.txtHealthCareUnitName.setText(name);
	}
    /**
     * HealthCareUnit Description Field.
     */
	public String getHealthCareUnitDescriptionText() {
		return this.txtHealthCareUnitDescription.getText().toString();
	}	 	 
	public void setHealthCareUnitDescriptionText(String description) {
		this.txtHealthCareUnitDescription.setText(description);
	}
    /**
     * HealthCareUnit Capacity Field.
     */
	public String getHealthCareUnitCapacityText() {
		return this.txtHealthCareUnitCapacity.getText().toString();
	}	 	 
	public void setHealthCareUnitCapacityText(String capacity) {
		this.txtHealthCareUnitCapacity.setText(capacity);
	}
    /**
     * HealthCareUnit Level Field.
     */
	public String getHealthCareUnitLevelText() {
		return this.sprHealthCareUnitLevel.getSelectedItem().toString();
	}
	@SuppressWarnings("unchecked")
	public void setHealthCareUnitLevelText(String level) {
		this.sprHealthCareUnitLevel.setSelection(((ArrayAdapter<String>) this.sprHealthCareUnitLevel.getAdapter()).getPosition(level));
	}

}
