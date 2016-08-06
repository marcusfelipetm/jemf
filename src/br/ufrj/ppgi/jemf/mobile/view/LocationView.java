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
import android.widget.TextView;
import android.widget.TimePicker;
import br.ufrj.ppgi.jemf.core.cooperation.EnumLocationStatus;
import br.ufrj.ppgi.jemf.utils.CustomLogger;
import br.ufrj.ppgi.jemf.utils.DateTimeFormat;

/**
 * @author Marcus Machado
 *
 */
public class LocationView extends CustomLinearView implements CustomView {

	/**
	 * Labels.
	 */
	private TextView lblLocationStatus, lblLocationTime;



	/**
	 * Inputs Fields.
	 */
	/**
	 * Location Status.
	 */
	private Spinner sprLocationStatus;
	/**
	 * Location Time.
	 */
	private TextView txtLocationTime;
	private Calendar calendarLocationTime;
	private DatePickerDialog.OnDateSetListener locationDatePickerDialogListener;
	private TimePickerDialog.OnTimeSetListener locationTimePickerDialogListener;
	/**
	 * Location Interval.
	 */
	private EditText txtLocationInterval;



	/**
	 * Parent Constructor.
	 * @param context
	 */
	private LocationView(Context context) {
		super(context);
    	CustomLogger.getInstance().infoLog(LocationView.class.getName(), "Class started.");
	}

	/**
	 * Constructor.
	 * @param context
	 * @param isListViewItem
	 */
	public LocationView(Context context, boolean isListViewItem) {
		// Super constructor.
		this(context);
		// Set default orientation.
		this.setOrientation(LinearLayout.VERTICAL);

		// If LocationView is to be an Item of ListView, as a List Row.
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
    	CustomLogger.getInstance().infoLog(LocationView.class.getName(), "Master layout retrieved.");
		// Field Status.
    	lblLocationStatus = new TextView(getContext());
    	lblLocationStatus.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
    	lblLocationStatus.setTextColor(Color.BLACK);
    	lblLocationStatus.setTypeface(Typeface.DEFAULT_BOLD);
    	
		// Field Time.
    	lblLocationTime = new TextView(getContext());
    	lblLocationTime.setTextAppearance(getContext(), android.R.style.TextAppearance_Small);
    	lblLocationTime.setTextColor(Color.BLACK);
    	lblLocationTime.setTypeface(null, Typeface.ITALIC);

		// Set to main layout.
		this.addView(lblLocationStatus, newWrapLayoutParams());
		this.addView(lblLocationTime, newWrapLayoutParams());
	}

	/* (non-Javadoc)
	 * @see br.ufrj.ppgi.jemf.mobile.view.CustomView#getDetailLayout()
	 */
	@Override
	public void getDetailLayout() {
    	CustomLogger.getInstance().infoLog(LocationView.class.getName(), "Detail layout retrieved.");
		// In this case, main layout will group all Location Fields inside itself, becoming a Parent View.
		this.setLayoutParams(newMatchLayoutParams());

		// Field Rows.
		// Location Status Field - Row 1.
		sprLocationStatus = new Spinner(getContext());
		// Create the item list of spinner.
		ArrayAdapter<String> spinnerArrayAdapterStatus = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item);
		spinnerArrayAdapterStatus.addAll(EnumLocationStatus.names);
		// Set the drop down view of spinner.
		spinnerArrayAdapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);		
		sprLocationStatus.setAdapter(spinnerArrayAdapterStatus);
		this.addView(newLinearRow("STATUS", sprLocationStatus, true));

		// Location Time Field - Row 2.
		txtLocationTime = new TextView(getContext());
		txtLocationTime.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtLocationTime.setSingleLine();
		txtLocationTime.setPadding(0, 10, 0, 5);
		// Get current date.
		calendarLocationTime = Calendar.getInstance();
		// Create a DatePicker Listener.
		locationDatePickerDialogListener = new DatePickerDialog.OnDateSetListener() {
		    @Override
		    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		    	calendarLocationTime.set(Calendar.YEAR, year);
		    	calendarLocationTime.set(Calendar.MONTH, monthOfYear);
		    	calendarLocationTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		    	// Update Date chosen by user.
		    	updateTxtTime();
		    }
		};
		// Create a TimePicker Listener.
		locationTimePickerDialogListener = new TimePickerDialog.OnTimeSetListener() {
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				calendarLocationTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
				calendarLocationTime.set(Calendar.MINUTE, minute);
		    	// Update Time chosen by user.
		    	updateTxtTime();
			}
		};
		// When the TextView is clicked then POPUP the DatePicker dialog to enable user choose the date.
		txtLocationTime.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Start the Date Picker.
				DatePickerDialog date = new DatePickerDialog(
						getContext(),
		        		locationDatePickerDialogListener,
		        		calendarLocationTime.get(Calendar.YEAR),
		        		calendarLocationTime.get(Calendar.MONTH),
		        		calendarLocationTime.get(Calendar.DAY_OF_MONTH));
				date.setOnDismissListener(new OnDismissListener() {
					@Override
					public void onDismiss(DialogInterface dialog) {
						// When close Date Picker, then Start the Time Picker.
				 		new TimePickerDialog(
				 				getContext(),
				 				locationTimePickerDialogListener,
				 				calendarLocationTime.get(Calendar.HOUR_OF_DAY),
				 				calendarLocationTime.get(Calendar.MINUTE),
				 				true).show();
					}
				});
				date.show();
		     }
		 });
		this.addView(newLinearRow("TIME", txtLocationTime, true));

		// Location Interval Field - Row 3.
		txtLocationInterval = new EditText(getContext());
		txtLocationInterval.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtLocationInterval.setSingleLine(true);
		this.addView(newLinearRow("INTERVAL", txtLocationInterval, true));
	}

	/**
	 * Labels Getters and Setters.
	 */
    public String getLocationStatusLabel() {
        return this.lblLocationStatus.getText().toString();
    }
    public void setLocationStatusLabel(String status) {
            this.lblLocationStatus.setText(status);
    }
    public String getLocationTimeLabel() {
        return this.lblLocationTime.getText().toString();
    }
    public void setLocationTimeLabel(String time) {
            this.lblLocationTime.setText(time);
    }

	/**
	 * Fields Getters and Setters.
	 */
    /**
     * Location Status Field.
     */
	public String getLocationStatusText() {
		return this.sprLocationStatus.getSelectedItem().toString();
	}
	@SuppressWarnings("unchecked")
	public void setLocationStatusText(String status) {
		this.sprLocationStatus.setSelection(((ArrayAdapter<String>) this.sprLocationStatus.getAdapter()).getPosition(status));
	}
    /**
     * Location Time Field.
     */
	public String getLocationTimeText() {
		return this.txtLocationTime.getText().toString();
	}	 	 
	public void setLocationTimeText(String time) {
		try {
			calendarLocationTime.setTime(DateTimeFormat.StringToDateTime(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.txtLocationTime.setText(time);
	}
    /**
     * Location Interval Field.
     */
	public String getLocationIntervalText() {
		return this.txtLocationInterval.getText().toString();
	}	 	 
	public void setLocationIntervalText(String interval) {
		this.txtLocationInterval.setText(interval);
	}



	/**
	 * Update StartDate Field with new date chosen by user.
	 */
	private void updateTxtTime() {
		this.txtLocationTime.setText(DateTimeFormat.DateTimeToString(calendarLocationTime.getTime()));
	}

}
