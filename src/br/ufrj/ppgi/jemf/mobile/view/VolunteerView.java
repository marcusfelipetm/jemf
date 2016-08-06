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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import br.ufrj.ppgi.jemf.utils.CustomLogger;
import br.ufrj.ppgi.jemf.utils.DateTimeFormat;

/**
 * @author Marcus Machado
 *
 */
public class VolunteerView extends CustomLinearView implements CustomView {

	/**
	 * Labels.
	 */
	private TextView lblVolunteerName, lblVolunteerGender;



	/**
	 * Inputs Fields.
	 */
	/**
	 * Volunteer Name.
	 */
	private EditText txtVolunteerName;
	/**
	 * Volunteer Gender.
	 */
	private Switch swtGender;
	/**
	 * Volunteer BirthDate.
	 */
	private TextView txtVolunteerBirthDate;
	private Calendar calendarBirthDate;
	private DatePickerDialog.OnDateSetListener birthDatePickerDialogListener;
	/**
	 * Volunteer Age.
	 */
	private EditText txtVolunteerAge;
	/**
	 * Volunteer Login.
	 */
	private EditText txtVolunteerLogin;
	/**
	 * Volunteer Name.
	 */
	private EditText txtVolunteerPassword;
	


	/**
	 * Parent Constructor.
	 * @param context
	 */
	private VolunteerView(Context context) {
		super(context);
    	CustomLogger.getInstance().infoLog(VolunteerView.class.getName(), "Class started.");
	}

	/**
	 * Constructor.
	 * @param context
	 * @param isListViewItem
	 */
	public VolunteerView(Context context, boolean isListViewItem) {
		// Super constructor.
		this(context);
		// Set default orientation.
		this.setOrientation(LinearLayout.VERTICAL);

		// If VolunteerView is to be an Item of ListView, as a List Row.
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
    	CustomLogger.getInstance().infoLog(VolunteerView.class.getName(), "Master layout retrieved.");
		// Field Name.
		lblVolunteerName = new TextView(getContext());
		lblVolunteerName.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
		lblVolunteerName.setTextColor(Color.BLACK);
		lblVolunteerName.setTypeface(Typeface.DEFAULT_BOLD);

		// Field Gender.
		lblVolunteerGender = new TextView(getContext());
		lblVolunteerGender.setTextAppearance(getContext(), android.R.style.TextAppearance_Small);
		lblVolunteerGender.setTextColor(Color.BLACK);
		lblVolunteerGender.setTypeface(null, Typeface.ITALIC);

		// Set to main layout.
		this.addView(lblVolunteerName, newWrapLayoutParams());
		this.addView(lblVolunteerGender, newWrapLayoutParams());
	}

	/* (non-Javadoc)
	 * @see br.ufrj.ppgi.jemf.mobile.view.CustomView#getDetailLayout()
	 */
	@Override
	public void getDetailLayout() {
    	CustomLogger.getInstance().infoLog(VolunteerView.class.getName(), "Detail layout retrieved.");
		// In this case, main layout will group all Volunteer Fields inside itself, becoming a Parent View.
		this.setLayoutParams(newMatchLayoutParams());

		// Field Rows.
		// Volunteer Name Field - Row 1.
		txtVolunteerName = new EditText(getContext());
		txtVolunteerName.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtVolunteerName.setSingleLine();
		this.addView(newLinearRow("NAME", txtVolunteerName, true));

		// Volunteer Gender Field - Row 2.
		swtGender = new Switch(getContext());
		swtGender.setTextOn("FEMALE");
		swtGender.setTextOff("MALE");
		swtGender.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
		swtGender.setTypeface(Typeface.DEFAULT_BOLD);
		this.addView(newLinearRow("GENDER", swtGender, false));

		// Volunteer BirthDate Field - Row 3.
		txtVolunteerBirthDate = new TextView(getContext());
		txtVolunteerBirthDate.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtVolunteerBirthDate.setSingleLine();
		txtVolunteerBirthDate.setPadding(0, 10, 0, 5);
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
		txtVolunteerBirthDate.setOnClickListener(new OnClickListener() {
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
		this.addView(newLinearRow("BIRTH DATE", txtVolunteerBirthDate, true));

		// Volunteer Age Field - Row 4.
		txtVolunteerAge = new EditText(getContext());
		txtVolunteerAge.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtVolunteerAge.setSingleLine();
		this.addView(newLinearRow("AGE", txtVolunteerAge, true));

		// Volunteer Login Field - Row 5.
		txtVolunteerLogin = new EditText(getContext());
		txtVolunteerLogin.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtVolunteerLogin.setSingleLine();
		this.addView(newLinearRow("LOGIN", txtVolunteerLogin, true));

		// Volunteer Password Field - Row 6.
		txtVolunteerPassword = new EditText(getContext());
		txtVolunteerPassword.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtVolunteerPassword.setSingleLine();
		this.addView(newLinearRow("PASSWORD", txtVolunteerPassword, true));
	}
	
	/**
	 * Labels Getters and Setters.
	 */
    public String getVolunteerNameLabel() {
        return this.lblVolunteerName.getText().toString();
    }
    public void setVolunteerNameLabel(String name) {
            this.lblVolunteerName.setText(name);
    }
    public String getVolunteerGenderLabel() {
        return this.lblVolunteerGender.getText().toString();
    }
    public void setVolunteerGenderLabel(String gender) {
            this.lblVolunteerGender.setText(gender);
    }

	/**
	 * Fields Getters and Setters.
	 */
    /**
     * Volunteer Name Field.
     */
	public String getVolunteerNameText() {
	    return this.txtVolunteerName.getText().toString();
	}
	public void setVolunteerNameText(String name) {
		this.txtVolunteerName.setText(name);
	}
    /**
     * Volunteer Gender Field.
     */
	public boolean getVolunteerGenderBool() {
		return this.swtGender.isChecked();
	}	 	 
	public void setVolunteerGenderBool(boolean gender) {
		this.swtGender.setChecked(gender);
	}
    /**
     * Volunteer BirthDate Field.
     */
	public String getVolunteerBirthDateText() {
	    return this.txtVolunteerBirthDate.getText().toString();
	}
	public void setVolunteerBirthDateText(String birthDate) {
		try {
			calendarBirthDate.setTime(DateTimeFormat.StringToDateTime(birthDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.txtVolunteerBirthDate.setText(birthDate);
	}
    /**
     * Volunteer Age Field.
     */
	public String getVolunteerAgeText() {
	    return this.txtVolunteerAge.getText().toString();
	}
	public void setVolunteerAgeText(String age) {
		this.txtVolunteerAge.setText(age);
	}
    /**
     * Volunteer Login Field.
     */
	public String getVolunteerLoginText() {
	    return this.txtVolunteerLogin.getText().toString();
	}
	public void setVolunteerLoginText(String login) {
		this.txtVolunteerLogin.setText(login);
	}
    /**
     * Volunteer Password Field.
     */
	public String getVolunteerPasswordText() {
	    return this.txtVolunteerPassword.getText().toString();
	}
	public void setVolunteerPasswordText(String password) {
		this.txtVolunteerPassword.setText(password);
	}




	/**
	 * Update BirthDate Field with new date chosen by user.
	 */
	private void updateTxtBirthDate() {
		this.txtVolunteerBirthDate.setText(DateTimeFormat.DateTimeToString(calendarBirthDate.getTime()));
	}

}
