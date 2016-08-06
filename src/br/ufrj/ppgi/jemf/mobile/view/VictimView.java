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

import java.text.ParseException;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import br.ufrj.ppgi.jemf.core.cooperation.EnumVictimStatus;
import br.ufrj.ppgi.jemf.utils.CustomLogger;
import br.ufrj.ppgi.jemf.utils.DateTimeFormat;

/**
 * @author Marcus Machado
 *
 */
public class VictimView extends CustomLinearView implements CustomView {

	/**
	 * Labels.
	 */
	private TextView lblVictimName, lblVictimStatus;



	/**
	 * Inputs Fields.
	 */
	/**
	 * Victim Name.
	 */
	private EditText txtVictimName;
	/**
	 * Victim Gender.
	 */
	private Switch swtGender;
	/**
	 * Victim BirthDate.
	 */
	private TextView txtVictimBirthDate;
	private Calendar calendarBirthDate;
	private DatePickerDialog.OnDateSetListener birthDatePickerDialogListener;
	/**
	 * Victim Age.
	 */
	private EditText txtVictimAge;
	/**
	 * Victim Status.
	 */
	private Spinner sprVictimStatus;
	


	/**
	 * Parent Constructor.
	 * @param context
	 */
	private VictimView(Context context) {
		super(context);
    	CustomLogger.getInstance().infoLog(VictimView.class.getName(), "Class started.");
	}

	/**
	 * Constructor.
	 * @param context
	 * @param isListViewItem
	 */
	public VictimView(Context context, boolean isListViewItem) {
		// Super constructor.
		this(context);
		// Set default orientation.
		this.setOrientation(LinearLayout.VERTICAL);

		// If VictimView is to be an Item of ListView, as a List Row.
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
    	CustomLogger.getInstance().infoLog(VictimView.class.getName(), "Master layout retrieved.");
		// Field Name.
		lblVictimName = new TextView(getContext());
		lblVictimName.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
		lblVictimName.setTextColor(Color.BLACK);
		lblVictimName.setTypeface(Typeface.DEFAULT_BOLD);

		// Field Status.
    	lblVictimStatus = new TextView(getContext());
    	lblVictimStatus.setTextAppearance(getContext(), android.R.style.TextAppearance_Small);
    	lblVictimStatus.setTextColor(Color.BLACK);
    	lblVictimStatus.setTypeface(null, Typeface.ITALIC);

		// Set to main layout.
		this.addView(lblVictimName, newWrapLayoutParams());
		this.addView(lblVictimStatus, newWrapLayoutParams());
	}

	/* (non-Javadoc)
	 * @see br.ufrj.ppgi.jemf.mobile.view.CustomView#getDetailLayout()
	 */
	@Override
	public void getDetailLayout() {
    	CustomLogger.getInstance().infoLog(VictimView.class.getName(), "Detail layout retrieved.");
		// In this case, main layout will group all Victim Fields inside itself, becoming a Parent View.
		this.setLayoutParams(newMatchLayoutParams());

		// Field Rows.
		// Victim Name Field - Row 1.
		txtVictimName = new EditText(getContext());
		txtVictimName.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtVictimName.setSingleLine();
		this.addView(newLinearRow("NAME", txtVictimName, true));

		// Victim Gender Field - Row 2.
		swtGender = new Switch(getContext());
		swtGender.setTextOn("FEMALE");
		swtGender.setTextOff("MALE");
		swtGender.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
		swtGender.setTypeface(Typeface.DEFAULT_BOLD);
		this.addView(newLinearRow("GENDER", swtGender, false));

		// Victim BirthDate Field - Row 3.
		txtVictimBirthDate = new TextView(getContext());
		txtVictimBirthDate.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtVictimBirthDate.setSingleLine();
		txtVictimBirthDate.setPadding(0, 10, 0, 5);
		// Get current date.
		calendarBirthDate = Calendar.getInstance();
		// Create a DatePicker Listener.
		birthDatePickerDialogListener = new DatePickerDialog.OnDateSetListener() {
		    @Override
		    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		    	calendarBirthDate.set(Calendar.YEAR, year);
		    	calendarBirthDate.set(Calendar.MONTH, monthOfYear);
		    	calendarBirthDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		    	// Update Date chosen by user.
		    	updateTxtBirthDate();
		    }
		};
		// When the TextView is clicked then POPUP the DatePicker dialog to enable user choose the date.
		txtVictimBirthDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Start the Date Picker.
				DatePickerDialog date = new DatePickerDialog(
						getContext(),
		        		birthDatePickerDialogListener,
		        		calendarBirthDate.get(Calendar.YEAR),
		        		calendarBirthDate.get(Calendar.MONTH),
		        		calendarBirthDate.get(Calendar.DAY_OF_MONTH));
				date.show();
		     }
		 });
		this.addView(newLinearRow("BIRTH DATE", txtVictimBirthDate, true));

		// Victim Age Field - Row 4.
		txtVictimAge = new EditText(getContext());
		txtVictimAge.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtVictimAge.setSingleLine();
		this.addView(newLinearRow("AGE", txtVictimAge, true));

		// Victim Status Field - Row 5.
		sprVictimStatus = new Spinner(getContext());
		// Create the item list of spinner.
		ArrayAdapter<String> spinnerArrayAdapterStatus = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item);
		spinnerArrayAdapterStatus.addAll(EnumVictimStatus.names);
		// Set the drop down view of spinner.
		spinnerArrayAdapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);		
		sprVictimStatus.setAdapter(spinnerArrayAdapterStatus);
		this.addView(newLinearRow("STATUS", sprVictimStatus, true));
	}
	
	/**
	 * Labels Getters and Setters.
	 */
    public String getVictimNameLabel() {
        return this.lblVictimName.getText().toString();
    }
    public void setVictimNameLabel(String name) {
            this.lblVictimName.setText(name);
    }
    public String getVictimStatusLabel() {
        return this.lblVictimStatus.getText().toString();
    }
    public void setVictimStatusLabel(String status) {
            this.lblVictimStatus.setText(status);
    }

	/**
	 * Fields Getters and Setters.
	 */
    /**
     * Victim Name Field.
     */
	public String getVictimNameText() {
	    return this.txtVictimName.getText().toString();
	}
	public void setVictimNameText(String name) {
		this.txtVictimName.setText(name);
	}
    /**
     * Victim Gender Field.
     */
	public boolean getVictimGenderBool() {
		return this.swtGender.isChecked();
	}	 	 
	public void setVictimGenderBool(boolean gender) {
		this.swtGender.setChecked(gender);
	}
    /**
     * Victim BirthDate Field.
     */
	public String getVictimBirthDateText() {
	    return this.txtVictimBirthDate.getText().toString();
	}
	public void setVictimBirthDateText(String birthDate) {
		try {
			calendarBirthDate.setTime(DateTimeFormat.StringToDateTime(birthDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.txtVictimBirthDate.setText(birthDate);
	}
    /**
     * Victim Age Field.
     */
	public String getVictimAgeText() {
	    return this.txtVictimAge.getText().toString();
	}
	public void setVictimAgeText(String age) {
		this.txtVictimAge.setText(age);
	}
    /**
     * Victim Status Field.
     */
	public String getVictimStatusText() {
		return this.sprVictimStatus.getSelectedItem().toString();
	}
	@SuppressWarnings("unchecked")
	public void setVictimStatusText(String status) {
		this.sprVictimStatus.setSelection(((ArrayAdapter<String>) this.sprVictimStatus.getAdapter()).getPosition(status));
	}




	/**
	 * Update BirthDate Field with new date chosen by user.
	 */
	private void updateTxtBirthDate() {
		this.txtVictimBirthDate.setText(DateTimeFormat.DateTimeToString(calendarBirthDate.getTime()));
	}

}
