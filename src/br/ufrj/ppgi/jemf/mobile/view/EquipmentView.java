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
import br.ufrj.ppgi.jemf.core.coordination.EnumResourceStatus;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class EquipmentView extends CustomLinearView implements CustomView {

	/**
	 * Labels.
	 */
	private TextView lblEquipmentName, lblEquipmentStatus;



	/**
	 * Inputs Fields.
	 */
	/**
	 * Equipment Name.
	 */
	private EditText txtEquipmentName;
	/**
	 * Equipment Description.
	 */
	private EditText txtEquipmentDescription;
	/**
	 * Equipment Status.
	 */
	private Spinner sprEquipmentStatus;



	/**
	 * Parent Constructor.
	 * @param context
	 */
	private EquipmentView(Context context) {
		super(context);
    	CustomLogger.getInstance().infoLog(EquipmentView.class.getName(), "Class started.");
	}

	/**
	 * Constructor.
	 * @param context
	 * @param isListViewItem
	 */
	public EquipmentView(Context context, boolean isListViewItem) {
		// Super constructor.
		this(context);
		// Set default orientation.
		this.setOrientation(LinearLayout.VERTICAL);

		// If Equipmentview is to be an Item of ListView, as a List Row.
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
    	CustomLogger.getInstance().infoLog(EquipmentView.class.getName(), "Master layout retrieved.");
		// Field Name.
    	lblEquipmentName = new TextView(getContext());
    	lblEquipmentName.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
    	lblEquipmentName.setTextColor(Color.BLACK);
    	lblEquipmentName.setTypeface(Typeface.DEFAULT_BOLD);

		// Field Status.
    	lblEquipmentStatus = new TextView(getContext());
    	lblEquipmentStatus.setTextAppearance(getContext(), android.R.style.TextAppearance_Small);
    	lblEquipmentStatus.setTextColor(Color.BLACK);
    	lblEquipmentStatus.setTypeface(null, Typeface.ITALIC);

		// Set to main layout.
		this.addView(lblEquipmentName, newWrapLayoutParams());
		this.addView(lblEquipmentStatus, newWrapLayoutParams());
	}

	/* (non-Javadoc)
	 * @see br.ufrj.ppgi.jemf.mobile.view.CustomView#getDetailLayout()
	 */
	@Override
	public void getDetailLayout() {
    	CustomLogger.getInstance().infoLog(EquipmentView.class.getName(), "Detail layout retrieved.");
		// In this case, main layout will group all Equipment Fields inside itself, becoming a Parent View.
		this.setLayoutParams(newMatchLayoutParams());

		// Field Rows.
		// Equipment Name Field - Row 1.
		txtEquipmentName = new EditText(getContext());
		txtEquipmentName.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtEquipmentName.setSingleLine();
		this.addView(newLinearRow("NAME", txtEquipmentName, true));

		// Equipment Description Field - Row 2.
		txtEquipmentDescription = new EditText(getContext());
		txtEquipmentDescription.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtEquipmentDescription.setLines(5);
		this.addView(newLinearRow("DESCRIPTION", txtEquipmentDescription, true));

		// Equipment Status Field - Row 3.
		sprEquipmentStatus = new Spinner(getContext());
		// Create the item list of spinner.
		ArrayAdapter<String> spinnerArrayAdapterStatus = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item);
		spinnerArrayAdapterStatus.addAll(EnumResourceStatus.names);
		// Set the drop down view of spinner.
		spinnerArrayAdapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);		
		sprEquipmentStatus.setAdapter(spinnerArrayAdapterStatus);
		this.addView(newLinearRow("STATUS", sprEquipmentStatus, true));
	}

	/**
	 * Labels Getters and Setters.
	 */
    public String getEquipmentNameLabel() {
        return this.lblEquipmentName.getText().toString();
    }
    public void setEquipmentNameLabel(String name) {
            this.lblEquipmentName.setText(name);
    }
    public String getEquipmentStatusLabel() {
        return this.lblEquipmentStatus.getText().toString();
    }
    public void setEquipmentStatusLabel(String priority) {
            this.lblEquipmentStatus.setText(priority);
    }

	/**
	 * Fields Getters and Setters.
	 */
    /**
     * Equipment Name Field.
     */
	public String getEquipmentNameText() {
	    return this.txtEquipmentName.getText().toString();
	}
	public void setEquipmentNameText(String name) {
		this.txtEquipmentName.setText(name);
	}
    /**
     * Equipment Description Field.
     */
	public String getEquipmentDescriptionText() {
		return this.txtEquipmentDescription.getText().toString();
	}	 	 
	public void setEquipmentDescriptionText(String description) {
		this.txtEquipmentDescription.setText(description);
	}
    /**
     * Equipment Status Field.
     */
	public String getEquipmentStatusText() {
		return this.sprEquipmentStatus.getSelectedItem().toString();
	}
	@SuppressWarnings("unchecked")
	public void setEquipmentStatusText(String status) {
		this.sprEquipmentStatus.setSelection(((ArrayAdapter<String>) this.sprEquipmentStatus.getAdapter()).getPosition(status));
	}

}
