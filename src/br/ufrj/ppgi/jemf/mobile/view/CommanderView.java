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
public class CommanderView extends CustomLinearView implements CustomView {

	/**
	 * Labels.
	 */
	private TextView lblCommanderName, lblCommanderGender;



	/**
	 * Inputs Fields.
	 */
	/**
	 * Commander Name.
	 */
	private EditText txtCommanderName;
	/**
	 * Commander Gender.
	 */
	private Switch swtGender;
	/**
	 * Commander BirthDate.
	 */
	private TextView txtCommanderBirthDate;
	private Calendar calendarBirthDate;
	private DatePickerDialog.OnDateSetListener birthDatePickerDialogListener;
	/**
	 * Commander Age.
	 */
	private EditText txtCommanderAge;
	/**
	 * Commander Login.
	 */
	private EditText txtCommanderLogin;
	/**
	 * Commander Name.
	 */
	private EditText txtCommanderPassword;
	


	/**
	 * Parent Constructor.
	 * @param context
	 */
	private CommanderView(Context context) {
		super(context);
    	CustomLogger.getInstance().infoLog(CommanderView.class.getName(), "Class started.");
	}

	/**
	 * Constructor.
	 * @param context
	 * @param isListViewItem
	 */
	public CommanderView(Context context, boolean isListViewItem) {
		// Super constructor.
		this(context);
		// Set default orientation.
		this.setOrientation(LinearLayout.VERTICAL);

		// If CommanderView is to be an Item of ListView, as a List Row.
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
    	CustomLogger.getInstance().infoLog(CommanderView.class.getName(), "Master layout retrieved.");
		// Field Name.
		lblCommanderName = new TextView(getContext());
		lblCommanderName.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
		lblCommanderName.setTextColor(Color.BLACK);
		lblCommanderName.setTypeface(Typeface.DEFAULT_BOLD);

		// Field Gender.
		lblCommanderGender = new TextView(getContext());
		lblCommanderGender.setTextAppearance(getContext(), android.R.style.TextAppearance_Small);
		lblCommanderGender.setTextColor(Color.BLACK);
		lblCommanderGender.setTypeface(null, Typeface.ITALIC);

		// Set to main layout.
		this.addView(lblCommanderName, newWrapLayoutParams());
		this.addView(lblCommanderGender, newWrapLayoutParams());
	}

	/* (non-Javadoc)
	 * @see br.ufrj.ppgi.jemf.mobile.view.CustomView#getDetailLayout()
	 */
	@Override
	public void getDetailLayout() {
    	CustomLogger.getInstance().infoLog(CommanderView.class.getName(), "Detail layout retrieved.");
		// In this case, main layout will group all Commander Fields inside itself, becoming a Parent View.
		this.setLayoutParams(newMatchLayoutParams());

		// Field Rows.
		// Commander Name Field - Row 1.
		txtCommanderName = new EditText(getContext());
		txtCommanderName.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtCommanderName.setSingleLine();
		this.addView(newLinearRow("NAME", txtCommanderName, true));

		// Commander Gender Field - Row 2.
		swtGender = new Switch(getContext());
		swtGender.setTextOn("FEMALE");
		swtGender.setTextOff("MALE");
		swtGender.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
		swtGender.setTypeface(Typeface.DEFAULT_BOLD);
		this.addView(newLinearRow("GENDER", swtGender, false));

		// Commander BirthDate Field - Row 3.
		txtCommanderBirthDate = new TextView(getContext());
		txtCommanderBirthDate.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtCommanderBirthDate.setSingleLine();
		txtCommanderBirthDate.setPadding(0, 10, 0, 5);
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
		txtCommanderBirthDate.setOnClickListener(new OnClickListener() {
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
		this.addView(newLinearRow("BIRTH DATE", txtCommanderBirthDate, true));

		// Commander Age Field - Row 4.
		txtCommanderAge = new EditText(getContext());
		txtCommanderAge.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtCommanderAge.setSingleLine();
		this.addView(newLinearRow("AGE", txtCommanderAge, true));

		// Commander Login Field - Row 5.
		txtCommanderLogin = new EditText(getContext());
		txtCommanderLogin.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtCommanderLogin.setSingleLine();
		this.addView(newLinearRow("LOGIN", txtCommanderLogin, true));

		// Commander Password Field - Row 6.
		txtCommanderPassword = new EditText(getContext());
		txtCommanderPassword.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtCommanderPassword.setSingleLine();
		this.addView(newLinearRow("PASSWORD", txtCommanderPassword, true));
	}
	
	/**
	 * Labels Getters and Setters.
	 */
    public String getCommanderNameLabel() {
        return this.lblCommanderName.getText().toString();
    }
    public void setCommanderNameLabel(String name) {
            this.lblCommanderName.setText(name);
    }
    public String getCommanderGenderLabel() {
        return this.lblCommanderGender.getText().toString();
    }
    public void setCommanderGenderLabel(String gender) {
            this.lblCommanderGender.setText(gender);
    }

	/**
	 * Fields Getters and Setters.
	 */
    /**
     * Commander Name Field.
     */
	public String getCommanderNameText() {
	    return this.txtCommanderName.getText().toString();
	}
	public void setCommanderNameText(String name) {
		this.txtCommanderName.setText(name);
	}
    /**
     * Commander Gender Field.
     */
	public boolean getCommanderGenderBool() {
		return this.swtGender.isChecked();
	}	 	 
	public void setCommanderGenderBool(boolean gender) {
		this.swtGender.setChecked(gender);
	}
    /**
     * Commander BirthDate Field.
     */
	public String getCommanderBirthDateText() {
	    return this.txtCommanderBirthDate.getText().toString();
	}
	public void setCommanderBirthDateText(String birthDate) {
		try {
			calendarBirthDate.setTime(DateTimeFormat.StringToDateTime(birthDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.txtCommanderBirthDate.setText(birthDate);
	}
    /**
     * Commander Age Field.
     */
	public String getCommanderAgeText() {
	    return this.txtCommanderAge.getText().toString();
	}
	public void setCommanderAgeText(String age) {
		this.txtCommanderAge.setText(age);
	}
    /**
     * Commander Login Field.
     */
	public String getCommanderLoginText() {
	    return this.txtCommanderLogin.getText().toString();
	}
	public void setCommanderLoginText(String login) {
		this.txtCommanderLogin.setText(login);
	}
    /**
     * Commander Password Field.
     */
	public String getCommanderPasswordText() {
	    return this.txtCommanderPassword.getText().toString();
	}
	public void setCommanderPasswordText(String password) {
		this.txtCommanderPassword.setText(password);
	}




	/**
	 * Update BirthDate Field with new date chosen by user.
	 */
	private void updateTxtBirthDate() {
		this.txtCommanderBirthDate.setText(DateTimeFormat.DateTimeToString(calendarBirthDate.getTime()));
	}

}
