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
public class WitnessView extends CustomLinearView implements CustomView {

	/**
	 * Labels.
	 */
	private TextView lblWitnessName, lblWitnessGender;



	/**
	 * Inputs Fields.
	 */
	/**
	 * Witness Name.
	 */
	private EditText txtWitnessName;
	/**
	 * Witness Gender.
	 */
	private Switch swtGender;
	/**
	 * Witness BirthDate.
	 */
	private TextView txtWitnessBirthDate;
	private Calendar calendarBirthDate;
	private DatePickerDialog.OnDateSetListener birthDatePickerDialogListener;
	/**
	 * Witness Age.
	 */
	private EditText txtWitnessAge;
	


	/**
	 * Parent Constructor.
	 * @param context
	 */
	private WitnessView(Context context) {
		super(context);
    	CustomLogger.getInstance().infoLog(WitnessView.class.getName(), "Class started.");
	}

	/**
	 * Constructor.
	 * @param context
	 * @param isListViewItem
	 */
	public WitnessView(Context context, boolean isListViewItem) {
		// Super constructor.
		this(context);
		// Set default orientation.
		this.setOrientation(LinearLayout.VERTICAL);

		// If WitnessView is to be an Item of ListView, as a List Row.
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
    	CustomLogger.getInstance().infoLog(WitnessView.class.getName(), "Master layout retrieved.");
		// Field Name.
		lblWitnessName = new TextView(getContext());
		lblWitnessName.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
		lblWitnessName.setTextColor(Color.BLACK);
		lblWitnessName.setTypeface(Typeface.DEFAULT_BOLD);

		// Field Gender.
		lblWitnessGender = new TextView(getContext());
		lblWitnessGender.setTextAppearance(getContext(), android.R.style.TextAppearance_Small);
		lblWitnessGender.setTextColor(Color.BLACK);
		lblWitnessGender.setTypeface(null, Typeface.ITALIC);

		// Set to main layout.
		this.addView(lblWitnessName, newWrapLayoutParams());
		this.addView(lblWitnessGender, newWrapLayoutParams());
	}

	/* (non-Javadoc)
	 * @see br.ufrj.ppgi.jemf.mobile.view.CustomView#getDetailLayout()
	 */
	@Override
	public void getDetailLayout() {
    	CustomLogger.getInstance().infoLog(WitnessView.class.getName(), "Detail layout retrieved.");
		// In this case, main layout will group all Witness Fields inside itself, becoming a Parent View.
		this.setLayoutParams(newMatchLayoutParams());

		// Field Rows.
		// Witness Name Field - Row 1.
		txtWitnessName = new EditText(getContext());
		txtWitnessName.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtWitnessName.setSingleLine();
		this.addView(newLinearRow("NAME", txtWitnessName, true));

		// Witness Gender Field - Row 2.
		swtGender = new Switch(getContext());
		swtGender.setTextOn("FEMALE");
		swtGender.setTextOff("MALE");
		swtGender.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
		swtGender.setTypeface(Typeface.DEFAULT_BOLD);
		this.addView(newLinearRow("GENDER", swtGender, false));

		// Witness BirthDate Field - Row 3.
		txtWitnessBirthDate = new TextView(getContext());
		txtWitnessBirthDate.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtWitnessBirthDate.setSingleLine();
		txtWitnessBirthDate.setPadding(0, 10, 0, 5);
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
		txtWitnessBirthDate.setOnClickListener(new OnClickListener() {
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
		this.addView(newLinearRow("BIRTH DATE", txtWitnessBirthDate, true));

		// Witness Age Field - Row 4.
		txtWitnessAge = new EditText(getContext());
		txtWitnessAge.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtWitnessAge.setSingleLine();
		this.addView(newLinearRow("AGE", txtWitnessAge, true));
	}
	
	/**
	 * Labels Getters and Setters.
	 */
    public String getWitnessNameLabel() {
        return this.lblWitnessName.getText().toString();
    }
    public void setWitnessNameLabel(String name) {
            this.lblWitnessName.setText(name);
    }
    public String getWitnessGenderLabel() {
        return this.lblWitnessGender.getText().toString();
    }
    public void setWitnessGenderLabel(String gender) {
            this.lblWitnessGender.setText(gender);
    }

	/**
	 * Fields Getters and Setters.
	 */
    /**
     * Witness Name Field.
     */
	public String getWitnessNameText() {
	    return this.txtWitnessName.getText().toString();
	}
	public void setWitnessNameText(String name) {
		this.txtWitnessName.setText(name);
	}
    /**
     * Witness Gender Field.
     */
	public boolean getWitnessGenderBool() {
		return this.swtGender.isChecked();
	}	 	 
	public void setWitnessGenderBool(boolean gender) {
		this.swtGender.setChecked(gender);
	}
    /**
     * Witness BirthDate Field.
     */
	public String getWitnessBirthDateText() {
	    return this.txtWitnessBirthDate.getText().toString();
	}
	public void setWitnessBirthDateText(String birthDate) {
		try {
			calendarBirthDate.setTime(DateTimeFormat.StringToDateTime(birthDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.txtWitnessBirthDate.setText(birthDate);
	}
    /**
     * Witness Age Field.
     */
	public String getWitnessAgeText() {
	    return this.txtWitnessAge.getText().toString();
	}
	public void setWitnessAgeText(String age) {
		this.txtWitnessAge.setText(age);
	}




	/**
	 * Update BirthDate Field with new date chosen by user.
	 */
	private void updateTxtBirthDate() {
		this.txtWitnessBirthDate.setText(DateTimeFormat.DateTimeToString(calendarBirthDate.getTime()));
	}

}
