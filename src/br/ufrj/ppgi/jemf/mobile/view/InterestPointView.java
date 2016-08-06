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
import br.ufrj.ppgi.jemf.core.cooperation.EnumLocationStatus;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class InterestPointView extends CustomLinearView implements CustomView {

	/**
	 * Labels.
	 */
	private TextView lblInterestPointName, lblInterestPointStatus;



	/**
	 * Inputs Fields.
	 */
	/**
	 * InterestPoint Name.
	 */
	private EditText txtInterestPointName;
	/**
	 * InterestPoint Status.
	 */
	private Spinner sprInterestPointStatus;
	/**
	 * InterestPoint Time.
	 */
	private EditText txtInterestPointTime;



	/**
	 * Parent Constructor.
	 * @param context
	 */
	private InterestPointView(Context context) {
		super(context);
    	CustomLogger.getInstance().infoLog(InterestPointView.class.getName(), "Class started.");
	}

	/**
	 * Constructor.
	 * @param context
	 * @param isListViewItem
	 */
	public InterestPointView(Context context, boolean isListViewItem) {
		// Super constructor.
		this(context);
		// Set default orientation.
		this.setOrientation(LinearLayout.VERTICAL);

		// If InterestPointView is to be an Item of ListView, as a List Row.
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
    	CustomLogger.getInstance().infoLog(InterestPointView.class.getName(), "Master layout retrieved.");
		// Field Name.
    	lblInterestPointName = new TextView(getContext());
    	lblInterestPointName.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
    	lblInterestPointName.setTextColor(Color.BLACK);
    	lblInterestPointName.setTypeface(Typeface.DEFAULT_BOLD);

		// Field Status.
    	lblInterestPointStatus = new TextView(getContext());
    	lblInterestPointStatus.setTextAppearance(getContext(), android.R.style.TextAppearance_Small);
    	lblInterestPointStatus.setTextColor(Color.BLACK);
    	lblInterestPointStatus.setTypeface(null, Typeface.ITALIC);

		// Set to main layout.
		this.addView(lblInterestPointName, newWrapLayoutParams());
		this.addView(lblInterestPointStatus, newWrapLayoutParams());
	}

	/* (non-Javadoc)
	 * @see br.ufrj.ppgi.jemf.mobile.view.CustomView#getDetailLayout()
	 */
	@Override
	public void getDetailLayout() {
    	CustomLogger.getInstance().infoLog(InterestPointView.class.getName(), "Detail layout retrieved.");
		// In this case, main layout will group all InterestPoint Fields inside itself, becoming a Parent View.
		this.setLayoutParams(newMatchLayoutParams());

		// Field Rows.
		// InterestPoint Name Field - Row 1.
		txtInterestPointName = new EditText(getContext());
		txtInterestPointName.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtInterestPointName.setSingleLine();
		this.addView(newLinearRow("NAME", txtInterestPointName, true));

		// InterestPoint Status Field - Row 2.
		sprInterestPointStatus = new Spinner(getContext());
		// Create the item list of spinner.
		ArrayAdapter<String> spinnerArrayAdapterStatus = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item);
		spinnerArrayAdapterStatus.addAll(EnumLocationStatus.names);
		// Set the drop down view of spinner.
		spinnerArrayAdapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);		
		sprInterestPointStatus.setAdapter(spinnerArrayAdapterStatus);
		this.addView(newLinearRow("STATUS", sprInterestPointStatus, true));

		// InterestPoint Time Field - Row 3.
		txtInterestPointTime = new EditText(getContext());
		txtInterestPointTime.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtInterestPointTime.setSingleLine();
		this.addView(newLinearRow("TIME", txtInterestPointTime, true));
	}

	/**
	 * Labels Getters and Setters.
	 */
    public String getInterestPointNameLabel() {
        return this.lblInterestPointName.getText().toString();
    }
    public void setInterestPointNameLabel(String name) {
            this.lblInterestPointName.setText(name);
    }
    public String getInterestPointStatusLabel() {
        return this.lblInterestPointStatus.getText().toString();
    }
    public void setInterestPointStatusLabel(String status) {
            this.lblInterestPointStatus.setText(status);
    }

	/**
	 * Fields Getters and Setters.
	 */
    /**
     * InterestPoint Name Field.
     */
	public String getInterestPointNameText() {
	    return this.txtInterestPointName.getText().toString();
	}
	public void setInterestPointNameText(String name) {
		this.txtInterestPointName.setText(name);
	}
    /**
     * InterestPoint Status Field.
     */
	public String getInterestPointStatusText() {
		return this.sprInterestPointStatus.getSelectedItem().toString();
	}
	@SuppressWarnings("unchecked")
	public void setInterestPointStatusText(String status) {
		this.sprInterestPointStatus.setSelection(((ArrayAdapter<String>) this.sprInterestPointStatus.getAdapter()).getPosition(status));
	}
    /**
     * InterestPoint Time Field.
     */
	public String getInterestPointTimeText() {
		return this.txtInterestPointTime.getText().toString();
	}	 	 
	public void setInterestPointTimeText(String time) {
		this.txtInterestPointTime.setText(time);
	}

}
