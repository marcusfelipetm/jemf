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
public class MedicalView extends CustomLinearView implements CustomView {

	/**
	 * Labels.
	 */
	private TextView lblMedicalName, lblMedicalGender;



	/**
	 * Inputs Fields.
	 */
	/**
	 * Medical Name.
	 */
	private EditText txtMedicalName;
	/**
	 * Medical Gender.
	 */
	private Switch swtGender;
	/**
	 * Medical BirthDate.
	 */
	private TextView txtMedicalBirthDate;
	private Calendar calendarBirthDate;
	private DatePickerDialog.OnDateSetListener birthDatePickerDialogListener;
	/**
	 * Medical Age.
	 */
	private EditText txtMedicalAge;
	/**
	 * Medical Login.
	 */
	private EditText txtMedicalLogin;
	/**
	 * Medical Name.
	 */
	private EditText txtMedicalPassword;



	/**
	 * Parent Constructor.
	 * @param context
	 */
	private MedicalView(Context context) {
		super(context);
    	CustomLogger.getInstance().infoLog(MedicalView.class.getName(), "Class started.");
	}

	/**
	 * Constructor.
	 * @param context
	 * @param isListViewItem
	 */
	public MedicalView(Context context, boolean isListViewItem) {
		// Super constructor.
		this(context);
		// Set default orientation.
		this.setOrientation(LinearLayout.VERTICAL);

		// If MedicalView is to be an Item of ListView, as a List Row.
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
    	CustomLogger.getInstance().infoLog(MedicalView.class.getName(), "Master layout retrieved.");
		// Field Name.
		lblMedicalName = new TextView(getContext());
		lblMedicalName.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
		lblMedicalName.setTextColor(Color.BLACK);
		lblMedicalName.setTypeface(Typeface.DEFAULT_BOLD);

		// Field Gender.
		lblMedicalGender = new TextView(getContext());
		lblMedicalGender.setTextAppearance(getContext(), android.R.style.TextAppearance_Small);
		lblMedicalGender.setTextColor(Color.BLACK);
		lblMedicalGender.setTypeface(null, Typeface.ITALIC);

		// Set to main layout.
		this.addView(lblMedicalName, newWrapLayoutParams());
		this.addView(lblMedicalGender, newWrapLayoutParams());
	}

	/* (non-Javadoc)
	 * @see br.ufrj.ppgi.jemf.mobile.view.CustomView#getDetailLayout()
	 */
	@Override
	public void getDetailLayout() {
    	CustomLogger.getInstance().infoLog(MedicalView.class.getName(), "Detail layout retrieved.");
		// In this case, main layout will group all Medical Fields inside itself, becoming a Parent View.
		this.setLayoutParams(newMatchLayoutParams());

		// Field Rows.
		// Medical Name Field - Row 1.
		txtMedicalName = new EditText(getContext());
		txtMedicalName.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtMedicalName.setSingleLine();
		this.addView(newLinearRow("NAME", txtMedicalName, true));

		// Medical Gender Field - Row 2.
		swtGender = new Switch(getContext());
		swtGender.setTextOn("FEMALE");
		swtGender.setTextOff("MALE");
		swtGender.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
		swtGender.setTypeface(Typeface.DEFAULT_BOLD);
		this.addView(newLinearRow("GENDER", swtGender, false));

		// Medical BirthDate Field - Row 3.
		txtMedicalBirthDate = new TextView(getContext());
		txtMedicalBirthDate.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtMedicalBirthDate.setSingleLine();
		txtMedicalBirthDate.setPadding(0, 10, 0, 5);
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
		txtMedicalBirthDate.setOnClickListener(new OnClickListener() {
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
		this.addView(newLinearRow("BIRTH DATE", txtMedicalBirthDate, true));

		// Medical Age Field - Row 4.
		txtMedicalAge = new EditText(getContext());
		txtMedicalAge.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtMedicalAge.setSingleLine();
		this.addView(newLinearRow("AGE", txtMedicalAge, true));

		// Medical Login Field - Row 5.
		txtMedicalLogin = new EditText(getContext());
		txtMedicalLogin.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtMedicalLogin.setSingleLine();
		this.addView(newLinearRow("LOGIN", txtMedicalLogin, true));

		// Medical Password Field - Row 6.
		txtMedicalPassword = new EditText(getContext());
		txtMedicalPassword.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtMedicalPassword.setSingleLine();
		this.addView(newLinearRow("PASSWORD", txtMedicalPassword, true));
	}
	
	/**
	 * Labels Getters and Setters.
	 */
    public String getMedicalNameLabel() {
        return this.lblMedicalName.getText().toString();
    }
    public void setMedicalNameLabel(String name) {
            this.lblMedicalName.setText(name);
    }
    public String getMedicalGenderLabel() {
        return this.lblMedicalGender.getText().toString();
    }
    public void setMedicalGenderLabel(String gender) {
            this.lblMedicalGender.setText(gender);
    }

	/**
	 * Fields Getters and Setters.
	 */
    /**
     * Medical Name Field.
     */
	public String getMedicalNameText() {
	    return this.txtMedicalName.getText().toString();
	}
	public void setMedicalNameText(String name) {
		this.txtMedicalName.setText(name);
	}
    /**
     * Medical Gender Field.
     */
	public boolean getMedicalGenderBool() {
		return this.swtGender.isChecked();
	}	 	 
	public void setMedicalGenderBool(boolean gender) {
		this.swtGender.setChecked(gender);
	}
    /**
     * Medical BirthDate Field.
     */
	public String getMedicalBirthDateText() {
	    return this.txtMedicalBirthDate.getText().toString();
	}
	public void setMedicalBirthDateText(String birthDate) {
		try {
			calendarBirthDate.setTime(DateTimeFormat.StringToDateTime(birthDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.txtMedicalBirthDate.setText(birthDate);
	}
    /**
     * Medical Age Field.
     */
	public String getMedicalAgeText() {
	    return this.txtMedicalAge.getText().toString();
	}
	public void setMedicalAgeText(String age) {
		this.txtMedicalAge.setText(age);
	}
    /**
     * Medical Login Field.
     */
	public String getMedicalLoginText() {
	    return this.txtMedicalLogin.getText().toString();
	}
	public void setMedicalLoginText(String login) {
		this.txtMedicalLogin.setText(login);
	}
    /**
     * Medical Password Field.
     */
	public String getMedicalPasswordText() {
	    return this.txtMedicalPassword.getText().toString();
	}
	public void setMedicalPasswordText(String password) {
		this.txtMedicalPassword.setText(password);
	}




	/**
	 * Update BirthDate Field with new date chosen by user.
	 */
	private void updateTxtBirthDate() {
		this.txtMedicalBirthDate.setText(DateTimeFormat.DateTimeToString(calendarBirthDate.getTime()));
	}

}
