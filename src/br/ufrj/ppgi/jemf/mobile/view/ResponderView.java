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
public class ResponderView extends CustomLinearView implements CustomView {

	/**
	 * Labels.
	 */
	private TextView lblResponderName, lblResponderGender;



	/**
	 * Inputs Fields.
	 */
	/**
	 * Responder Name.
	 */
	private EditText txtResponderName;
	/**
	 * Responder Gender.
	 */
	private Switch swtGender;
	/**
	 * Responder BirthDate.
	 */
	private TextView txtResponderBirthDate;
	private Calendar calendarBirthDate;
	private DatePickerDialog.OnDateSetListener birthDatePickerDialogListener;
	/**
	 * Responder Age.
	 */
	private EditText txtResponderAge;
	/**
	 * Responder Login.
	 */
	private EditText txtResponderLogin;
	/**
	 * Responder Name.
	 */
	private EditText txtResponderPassword;
	


	/**
	 * Parent Constructor.
	 * @param context
	 */
	private ResponderView(Context context) {
		super(context);
    	CustomLogger.getInstance().infoLog(ResponderView.class.getName(), "Class started.");
	}

	/**
	 * Constructor.
	 * @param context
	 * @param isListViewItem
	 */
	public ResponderView(Context context, boolean isListViewItem) {
		// Super constructor.
		this(context);
		// Set default orientation.
		this.setOrientation(LinearLayout.VERTICAL);

		// If ResponderView is to be an Item of ListView, as a List Row.
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
    	CustomLogger.getInstance().infoLog(ResponderView.class.getName(), "Master layout retrieved.");
		// Field Name.
		lblResponderName = new TextView(getContext());
		lblResponderName.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
		lblResponderName.setTextColor(Color.BLACK);
		lblResponderName.setTypeface(Typeface.DEFAULT_BOLD);

		// Field Gender.
		lblResponderGender = new TextView(getContext());
		lblResponderGender.setTextAppearance(getContext(), android.R.style.TextAppearance_Small);
		lblResponderGender.setTextColor(Color.BLACK);
		lblResponderGender.setTypeface(null, Typeface.ITALIC);

		// Set to main layout.
		this.addView(lblResponderName, newWrapLayoutParams());
		this.addView(lblResponderGender, newWrapLayoutParams());
	}

	/* (non-Javadoc)
	 * @see br.ufrj.ppgi.jemf.mobile.view.CustomView#getDetailLayout()
	 */
	@Override
	public void getDetailLayout() {
    	CustomLogger.getInstance().infoLog(ResponderView.class.getName(), "Detail layout retrieved.");
		// In this case, main layout will group all Responder Fields inside itself, becoming a Parent View.
		this.setLayoutParams(newMatchLayoutParams());

		// Field Rows.
		// Responder Name Field - Row 1.
		txtResponderName = new EditText(getContext());
		txtResponderName.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtResponderName.setSingleLine();
		this.addView(newLinearRow("NAME", txtResponderName, true));

		// Responder Gender Field - Row 2.
		swtGender = new Switch(getContext());
		swtGender.setTextOn("FEMALE");
		swtGender.setTextOff("MALE");
		swtGender.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
		swtGender.setTypeface(Typeface.DEFAULT_BOLD);
		this.addView(newLinearRow("GENDER", swtGender, false));

		// Responder BirthDate Field - Row 3.
		txtResponderBirthDate = new TextView(getContext());
		txtResponderBirthDate.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtResponderBirthDate.setSingleLine();
		txtResponderBirthDate.setPadding(0, 10, 0, 5);
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
		txtResponderBirthDate.setOnClickListener(new OnClickListener() {
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
		this.addView(newLinearRow("BIRTH DATE", txtResponderBirthDate, true));

		// Responder Age Field - Row 4.
		txtResponderAge = new EditText(getContext());
		txtResponderAge.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtResponderAge.setSingleLine();
		this.addView(newLinearRow("AGE", txtResponderAge, true));

		// Responder Login Field - Row 5.
		txtResponderLogin = new EditText(getContext());
		txtResponderLogin.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtResponderLogin.setSingleLine();
		this.addView(newLinearRow("LOGIN", txtResponderLogin, true));

		// Responder Password Field - Row 6.
		txtResponderPassword = new EditText(getContext());
		txtResponderPassword.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtResponderPassword.setSingleLine();
		this.addView(newLinearRow("PASSWORD", txtResponderPassword, true));
	}
	
	/**
	 * Labels Getters and Setters.
	 */
    public String getResponderNameLabel() {
        return this.lblResponderName.getText().toString();
    }
    public void setResponderNameLabel(String name) {
            this.lblResponderName.setText(name);
    }
    public String getResponderGenderLabel() {
        return this.lblResponderGender.getText().toString();
    }
    public void setResponderGenderLabel(String gender) {
            this.lblResponderGender.setText(gender);
    }

	/**
	 * Fields Getters and Setters.
	 */
    /**
     * Responder Name Field.
     */
	public String getResponderNameText() {
	    return this.txtResponderName.getText().toString();
	}
	public void setResponderNameText(String name) {
		this.txtResponderName.setText(name);
	}
    /**
     * Responder Gender Field.
     */
	public boolean getResponderGenderBool() {
		return this.swtGender.isChecked();
	}	 	 
	public void setResponderGenderBool(boolean gender) {
		this.swtGender.setChecked(gender);
	}
    /**
     * Responder BirthDate Field.
     */
	public String getResponderBirthDateText() {
	    return this.txtResponderBirthDate.getText().toString();
	}
	public void setResponderBirthDateText(String birthDate) {
		try {
			calendarBirthDate.setTime(DateTimeFormat.StringToDateTime(birthDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.txtResponderBirthDate.setText(birthDate);
	}
    /**
     * Responder Age Field.
     */
	public String getResponderAgeText() {
	    return this.txtResponderAge.getText().toString();
	}
	public void setResponderAgeText(String age) {
		this.txtResponderAge.setText(age);
	}
    /**
     * Responder Login Field.
     */
	public String getResponderLoginText() {
	    return this.txtResponderLogin.getText().toString();
	}
	public void setResponderLoginText(String login) {
		this.txtResponderLogin.setText(login);
	}
    /**
     * Responder Password Field.
     */
	public String getResponderPasswordText() {
	    return this.txtResponderPassword.getText().toString();
	}
	public void setResponderPasswordText(String password) {
		this.txtResponderPassword.setText(password);
	}




	/**
	 * Update BirthDate Field with new date chosen by user.
	 */
	private void updateTxtBirthDate() {
		this.txtResponderBirthDate.setText(DateTimeFormat.DateTimeToString(calendarBirthDate.getTime()));
	}

}
