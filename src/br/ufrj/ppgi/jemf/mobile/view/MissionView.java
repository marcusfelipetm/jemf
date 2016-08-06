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

import br.ufrj.ppgi.jemf.core.coordination.EnumPriority;
import br.ufrj.ppgi.jemf.core.coordination.EnumServiceStatus;
import br.ufrj.ppgi.jemf.utils.CustomLogger;
import br.ufrj.ppgi.jemf.utils.DateTimeFormat;

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

/**
 * @author Marcus Machado
 *
 */
public class MissionView extends CustomLinearView implements CustomView {

	/**
	 * Labels.
	 */
	private TextView lblMissionTitle, lblMissionPriority;



	/**
	 * Inputs Fields.
	 */
	/**
	 * Mission Title.
	 */
	private EditText txtMissionTitle;
	/**
	 * Mission Description.
	 */
	private EditText txtMissionDescription;
	/**
	 * Mission Status.
	 */
	private Spinner sprMissionStatus;
	/**
	 * Mission Priority.
	 */
	private Spinner sprMissionPriority;
	/**
	 * Mission StartDate.
	 */
	private TextView txtMissionStartDate;
	private Calendar calendarStartDate;
	private DatePickerDialog.OnDateSetListener startDatePickerDialogListener;
	private TimePickerDialog.OnTimeSetListener startTimePickerDialogListener;
	/**
	 * Mission EndDate.
	 */
	private TextView txtMissionEndDate;
	private Calendar calendarEndDate;
	private DatePickerDialog.OnDateSetListener endDatePickerDialogListener;
	private TimePickerDialog.OnTimeSetListener endTimePickerDialogListener;



	/**
	 * Parent Constructor.
	 * @param context
	 */
	private MissionView(Context context) {
		super(context);
    	CustomLogger.getInstance().infoLog(MissionView.class.getName(), "Class started.");
	}

	/**
	 * Constructor.
	 * @param context
	 * @param isListViewItem
	 */
	public MissionView(Context context, boolean isListViewItem) {
		// Super constructor.
		this(context);
		// Set default orientation.
		this.setOrientation(LinearLayout.VERTICAL);

		// If MissionView is to be an Item of ListView, as a List Row.
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
    	CustomLogger.getInstance().infoLog(MissionView.class.getName(), "Master layout retrieved.");
		// Field Title.
		lblMissionTitle = new TextView(getContext());
		lblMissionTitle.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
		lblMissionTitle.setTextColor(Color.BLACK);
		lblMissionTitle.setTypeface(Typeface.DEFAULT_BOLD);

		// Field Priority.
		lblMissionPriority = new TextView(getContext());
		lblMissionPriority.setTextAppearance(getContext(), android.R.style.TextAppearance_Small);
		lblMissionPriority.setTextColor(Color.BLACK);
		lblMissionPriority.setTypeface(null, Typeface.ITALIC);

		// Set to main layout.
		this.addView(lblMissionTitle, newWrapLayoutParams());
		this.addView(lblMissionPriority, newWrapLayoutParams());
	}

	/* (non-Javadoc)
	 * @see br.ufrj.ppgi.jemf.mobile.view.CustomView#getDetailLayout()
	 */
	@Override
	public void getDetailLayout() {
    	CustomLogger.getInstance().infoLog(MissionView.class.getName(), "Detail layout retrieved.");
		// In this case, main layout will group all Mission Fields inside itself, becoming a Parent View.
		this.setLayoutParams(newMatchLayoutParams());

		// Field Rows.
		// Mission Title Field - Row 1.
		txtMissionTitle = new EditText(getContext());
		txtMissionTitle.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtMissionTitle.setSingleLine();
		this.addView(newLinearRow("TITLE", txtMissionTitle, true));

		// Mission Description Field - Row 2.
		txtMissionDescription = new EditText(getContext());
		txtMissionDescription.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtMissionDescription.setLines(5);
		this.addView(newLinearRow("DESCRIPTION", txtMissionDescription, true));

		// Mission Status Field - Row 3.
		sprMissionStatus = new Spinner(getContext());
		// Create the item list of spinner.
		ArrayAdapter<String> spinnerArrayAdapterStatus = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item);
		spinnerArrayAdapterStatus.addAll(EnumServiceStatus.names);
		// Set the drop down view of spinner.
		spinnerArrayAdapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);		
		sprMissionStatus.setAdapter(spinnerArrayAdapterStatus);
		this.addView(newLinearRow("STATUS", sprMissionStatus, true));

		// Mission Priority Field - Row 4.
		sprMissionPriority = new Spinner(getContext());
		// Create the item list of spinner.
		ArrayAdapter<String> spinnerArrayAdapterPriority = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item);
		spinnerArrayAdapterPriority.addAll(EnumPriority.names);
		// Set the drop down view of spinner.
		spinnerArrayAdapterPriority.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sprMissionPriority.setAdapter(spinnerArrayAdapterPriority);
		this.addView(newLinearRow("PRIORITY", sprMissionPriority, true));

		// Mission StartDate Field - Row 5.
		txtMissionStartDate = new TextView(getContext());
		txtMissionStartDate.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtMissionStartDate.setSingleLine();
		txtMissionStartDate.setPadding(0, 10, 0, 5);
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
		txtMissionStartDate.setOnClickListener(new OnClickListener() {
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
		this.addView(newLinearRow("START DATE", txtMissionStartDate, true));

		// Mission EndDate Field - Row 6.
		txtMissionEndDate = new TextView(getContext());
		txtMissionEndDate.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtMissionEndDate.setSingleLine();
		txtMissionEndDate.setPadding(0, 10, 0, 5);
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
		txtMissionEndDate.setOnClickListener(new OnClickListener() {
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
		this.addView(newLinearRow("END DATE", txtMissionEndDate, true));
	}

	/**
	 * Labels Getters and Setters.
	 */
    public String getMissionTitleLabel() {
        return this.lblMissionTitle.getText().toString();
    }
    public void setMissionTitleLabel(String title) {
            this.lblMissionTitle.setText(title);
    }

    public String getMissionPriorityLabel() {
        return this.lblMissionPriority.getText().toString();
    }
    public void setMissionPriorityLabel(String priority) {
            this.lblMissionPriority.setText(priority);
    }

	/**
	 * Fields Getters and Setters.
	 */
    /**
     * Mission Title Field.
     */
	public String getMissionTitleText() {
	    return this.txtMissionTitle.getText().toString();
	}
	public void setMissionTitleText(String title) {
		this.txtMissionTitle.setText(title);
	}
    /**
     * Mission Description Field.
     */
	public String getMissionDescriptionText() {
		return this.txtMissionDescription.getText().toString();
	}	 	 
	public void setMissionDescriptionText(String description) {
		this.txtMissionDescription.setText(description);
	}
    /**
     * Mission Status Field.
     */
	public String getMissionStatusText() {
		return this.sprMissionStatus.getSelectedItem().toString();
	}
	@SuppressWarnings("unchecked")
	public void setMissionStatusText(String status) {
		this.sprMissionStatus.setSelection(((ArrayAdapter<String>) this.sprMissionStatus.getAdapter()).getPosition(status));
	}
    /**
     * Mission Priority Field.
     */
	public String getMissionPriorityText() {
		return this.sprMissionPriority.getSelectedItem().toString();
	}
	@SuppressWarnings("unchecked")
	public void setMissionPriorityText(String priority) {
		this.sprMissionPriority.setSelection(((ArrayAdapter<String>) this.sprMissionPriority.getAdapter()).getPosition(priority));
	}
    /**
     * Mission StartDate Field.
     */
	public String getMissionStartDateText() {
	    return this.txtMissionStartDate.getText().toString();
	}
	public void setMissionStartDateText(String startDate) {
		try {
			calendarStartDate.setTime(DateTimeFormat.StringToDateTime(startDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.txtMissionStartDate.setText(startDate);
	}
    /**
     * Mission EndDate Field.
     */
	public String getMissionEndDateText() {
	    return this.txtMissionEndDate.getText().toString();
	}
	public void setMissionEndDateText(String endDate) {
		try {
			calendarEndDate.setTime(DateTimeFormat.StringToDateTime(endDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.txtMissionEndDate.setText(endDate);
	}



	/**
	 * Update StartDate Field with new date chosen by user.
	 */
	private void updateTxtStartDate() {
		this.txtMissionStartDate.setText(DateTimeFormat.DateTimeToString(calendarStartDate.getTime()));
	}
	/**
	 * Update EndDate Field with new date chosen by user.
	 */
	private void updateTxtEndDate() {
		this.txtMissionEndDate.setText(DateTimeFormat.DateTimeToString(calendarEndDate.getTime()));
	}

}
