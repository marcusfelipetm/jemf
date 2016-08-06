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
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
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
import android.widget.TimePicker;
import br.ufrj.ppgi.jemf.core.coordination.EnumEmergencyType;
import br.ufrj.ppgi.jemf.core.coordination.EnumLevel;
import br.ufrj.ppgi.jemf.utils.CustomLogger;
import br.ufrj.ppgi.jemf.utils.DateTimeFormat;

/**
 * @author Marcus Machado
 *
 */
public class EmergencyView extends CustomLinearView implements CustomView {

	/**
	 * Labels.
	 */
	private TextView lblEmergencyName, lblEmergencyActivated;



	/**
	 * Inputs Fields.
	 */
	/**
	 * Emergency Name.
	 */
	private EditText txtEmergencyName;
	/**
	 * Emergency Activated.
	 */
	private Switch swtActivated;
	/**
	 * Emergency Type.
	 */
	private Spinner sprEmergencyType;
	/**
	 * Emergency Level.
	 */
	private Spinner sprEmergencyLevel;
	/**
	 * Emergency StartDate.
	 */
	private TextView txtEmergencyStartDate;
	private Calendar calendarStartDate;
	private DatePickerDialog.OnDateSetListener startDatePickerDialogListener;
	private TimePickerDialog.OnTimeSetListener startTimePickerDialogListener;
	/**
	 * Emergency EndDate.
	 */
	private TextView txtEmergencyEndDate;
	private Calendar calendarEndDate;
	private DatePickerDialog.OnDateSetListener endDatePickerDialogListener;
	private TimePickerDialog.OnTimeSetListener endTimePickerDialogListener;
	


	/**
	 * Parent Constructor.
	 * @param context
	 */
	private EmergencyView(Context context) {
		super(context);
    	CustomLogger.getInstance().infoLog(EmergencyView.class.getName(), "Class started.");
	}

	/**
	 * Constructor.
	 * @param context
	 * @param isListViewItem
	 */
	public EmergencyView(Context context, boolean isListViewItem) {
		// Super constructor.
		this(context);
		// Set default orientation.
		this.setOrientation(LinearLayout.VERTICAL);

		// If EmergencyView is to be an Item of ListView, as a List Row.
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
    	CustomLogger.getInstance().infoLog(EmergencyView.class.getName(), "Master layout retrieved.");
		// Field Emergency.
		lblEmergencyName = new TextView(getContext());
		lblEmergencyName.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
		lblEmergencyName.setTextColor(Color.BLACK);
		lblEmergencyName.setTypeface(Typeface.DEFAULT_BOLD);

		// Field Activated.
		lblEmergencyActivated = new TextView(getContext());
		lblEmergencyActivated.setTextAppearance(getContext(), android.R.style.TextAppearance_Small);
		lblEmergencyActivated.setTextColor(Color.BLACK);
		lblEmergencyActivated.setTypeface(null, Typeface.ITALIC);

		// Set to main layout.
		this.addView(lblEmergencyName, newWrapLayoutParams());
		this.addView(lblEmergencyActivated, newWrapLayoutParams());
	}

	/* (non-Javadoc)
	 * @see br.ufrj.ppgi.jemf.mobile.view.CustomView#getDetailLayout()
	 */
	@Override
	public void getDetailLayout() {
    	CustomLogger.getInstance().infoLog(EmergencyView.class.getName(), "Detail layout retrieved.");
		// In this case, main layout will group all Emergency Fields inside itself, becoming a Parent View.
		this.setLayoutParams(newMatchLayoutParams());

		// Field Rows.
		// Emergency Name Field - Row 1.
		txtEmergencyName = new EditText(getContext());
		txtEmergencyName.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtEmergencyName.setSingleLine();
		this.addView(newLinearRow("NAME", txtEmergencyName, true));

		// Emergency Activated Field - Row 2.
		swtActivated = new Switch(getContext());
		swtActivated.setTextOn("YES");
		swtActivated.setTextOff("NO");
		swtActivated.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
		swtActivated.setTypeface(Typeface.DEFAULT_BOLD);
		this.addView(newLinearRow("ACTIVATED", swtActivated, false));

		// Emergency Type Field - Row 3.
		sprEmergencyType = new Spinner(getContext());
		// Create the item list of spinner.
		ArrayAdapter<String> spinnerArrayAdapterType = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item);
		spinnerArrayAdapterType.addAll(EnumEmergencyType.names);
		// Set the drop down view of spinner.
		spinnerArrayAdapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);		
		sprEmergencyType.setAdapter(spinnerArrayAdapterType);
		this.addView(newLinearRow("TYPE", sprEmergencyType, true));

		// Emergency Level Field - Row 4.
		sprEmergencyLevel = new Spinner(getContext());
		// Create the item list of spinner.
		ArrayAdapter<String> spinnerArrayAdapterLevel = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item);
		spinnerArrayAdapterLevel.addAll(EnumLevel.names);
		// Set the drop down view of spinner.
		spinnerArrayAdapterLevel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sprEmergencyLevel.setAdapter(spinnerArrayAdapterLevel);
		this.addView(newLinearRow("LEVEL", sprEmergencyLevel, true));

		// Emergency StartDate Field - Row 5.
		txtEmergencyStartDate = new TextView(getContext());
		txtEmergencyStartDate.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtEmergencyStartDate.setSingleLine();
		txtEmergencyStartDate.setPadding(0, 10, 0, 5);
		// Get current date.
		calendarStartDate = Calendar.getInstance();
		// Create a DatePicker Listener.
		startDatePickerDialogListener = new DatePickerDialog.OnDateSetListener() {
		    @Override
		    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		    	calendarStartDate.set(Calendar.YEAR, year);
		    	calendarStartDate.set(Calendar.MONTH, monthOfYear);
		    	calendarStartDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		    	// Update Date chosen by user.
		    	updateTxtStartDate();
		    }
		};
		// Create a TimePicker Listener.
		startTimePickerDialogListener = new TimePickerDialog.OnTimeSetListener() {
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				calendarStartDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
				calendarStartDate.set(Calendar.MINUTE, minute);
		    	// Update Time chosen by user.
		    	updateTxtStartDate();
			}
		};
		// When the TextView is clicked then POPUP the DatePicker dialog to enable user choose the date.
		txtEmergencyStartDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Start the Date Picker.
				DatePickerDialog date = new DatePickerDialog(
						getContext(),
		        		startDatePickerDialogListener,
		        		calendarStartDate.get(Calendar.YEAR),
		        		calendarStartDate.get(Calendar.MONTH),
		        		calendarStartDate.get(Calendar.DAY_OF_MONTH));
				date.setOnDismissListener(new OnDismissListener() {
					@Override
					public void onDismiss(DialogInterface dialog) {
						// When close Date Picker, then Start the Time Picker.
				 		new TimePickerDialog(
				 				getContext(),
				 				startTimePickerDialogListener,
				 				calendarStartDate.get(Calendar.HOUR_OF_DAY),
				 				calendarStartDate.get(Calendar.MINUTE),
				 				true).show();
					}
				});
				date.show();
		     }
		 });
		this.addView(newLinearRow("START DATE", txtEmergencyStartDate, true));

		// Emergency EndDate Field - Row 6.
		txtEmergencyEndDate = new TextView(getContext());
		txtEmergencyEndDate.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtEmergencyEndDate.setSingleLine();
		txtEmergencyEndDate.setPadding(0, 10, 0, 5);
		// Get current date.
		calendarEndDate = Calendar.getInstance();
		// Create a DatePicker Listener.
		endDatePickerDialogListener = new DatePickerDialog.OnDateSetListener() {
		    @Override
		    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		    	calendarEndDate.set(Calendar.YEAR, year);
		    	calendarEndDate.set(Calendar.MONTH, monthOfYear);
		    	calendarEndDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		    	// Update Date chosen by user.
		    	updateTxtEndDate();
		    }
		};
		// Create a TimePicker Listener.
		endTimePickerDialogListener = new TimePickerDialog.OnTimeSetListener() {
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		    	calendarEndDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
		    	calendarEndDate.set(Calendar.MINUTE, minute);
		    	// Update Time chosen by user.
		    	updateTxtEndDate();
			}
		};
		// When the TextView is clicked then POPUP the DatePicker dialog to enable user choose the date.
		txtEmergencyEndDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Start the Date Picker.
				DatePickerDialog date = new DatePickerDialog(
						getContext(),
		        		endDatePickerDialogListener,
		        		calendarEndDate.get(Calendar.YEAR),
		        		calendarEndDate.get(Calendar.MONTH),
		        		calendarEndDate.get(Calendar.DAY_OF_MONTH));
				date.setOnDismissListener(new OnDismissListener() {
					@Override
					public void onDismiss(DialogInterface dialog) {
						// When close Date Picker, then Start the Time Picker.
				 		new TimePickerDialog(
				 				getContext(),
				 				endTimePickerDialogListener,
				 				calendarEndDate.get(Calendar.HOUR_OF_DAY),
				 				calendarEndDate.get(Calendar.MINUTE),
				 				true).show();
					}
				});
				date.show();
		     }
		 });
		this.addView(newLinearRow("END DATE", txtEmergencyEndDate, true));
	}

	
	/**
	 * Labels Getters and Setters.
	 */
    public String getEmergencyNameLabel() {
        return this.lblEmergencyName.getText().toString();
    }
    public void setEmergencyNameLabel(String name) {
            this.lblEmergencyName.setText(name);
    }

    public String getEmergencyactivatedLabel() {
        return this.lblEmergencyActivated.getText().toString();
    }
    public void setEmergencyActivatedLabel(String activated) {
            this.lblEmergencyActivated.setText(activated);
    }

	/**
	 * Fields Getters and Setters.
	 */
    /**
     * Emergency Name Field.
     */
	public String getEmergencyNameText() {
	    return this.txtEmergencyName.getText().toString();
	}
	public void setEmergencyNameText(String name) {
		this.txtEmergencyName.setText(name);
	}
    /**
     * Emergency Activated Field.
     */
	public boolean getEmergencyActivatedBool() {
		return this.swtActivated.isChecked();
	}	 	 
	public void setEmergencyActivatedBool(boolean activated) {
		this.swtActivated.setChecked(activated);
	}
    /**
     * Emergency Type Field.
     */
	public String getEmergencyTypeText() {
		return this.sprEmergencyType.getSelectedItem().toString();
	}
	@SuppressWarnings("unchecked")
	public void setEmergencyTypeText(String type) {
		this.sprEmergencyType.setSelection(((ArrayAdapter<String>) this.sprEmergencyType.getAdapter()).getPosition(type));
	}
    /**
     * Emergency Level Field.
     */
	public String getEmergencyLevelText() {
		return this.sprEmergencyLevel.getSelectedItem().toString();
	}
	@SuppressWarnings("unchecked")
	public void setEmergencyLevelText(String level) {
		this.sprEmergencyLevel.setSelection(((ArrayAdapter<String>) this.sprEmergencyLevel.getAdapter()).getPosition(level));
	}
    /**
     * Emergency StartDate Field.
     */
	public String getEmergencyStartDateText() {
	    return this.txtEmergencyStartDate.getText().toString();
	}
	public void setEmergencyStartDateText(String startDate) {
		try {
			calendarStartDate.setTime(DateTimeFormat.StringToDateTime(startDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.txtEmergencyStartDate.setText(startDate);
	}
    /**
     * Emergency EndDate Field.
     */
	public String getEmergencyEndDateText() {
	    return this.txtEmergencyEndDate.getText().toString();
	}
	public void setEmergencyEndDateText(String endDate) {
		try {
			calendarEndDate.setTime(DateTimeFormat.StringToDateTime(endDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.txtEmergencyEndDate.setText(endDate);
	}



	/**
	 * Update StartDate Field with new date chosen by user.
	 */
	private void updateTxtStartDate() {
		this.txtEmergencyStartDate.setText(DateTimeFormat.DateTimeToString(calendarStartDate.getTime()));
	}
	/**
	 * Update EndDate Field with new date chosen by user.
	 */
	private void updateTxtEndDate() {
		this.txtEmergencyEndDate.setText(DateTimeFormat.DateTimeToString(calendarEndDate.getTime()));
	}

}
