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
import br.ufrj.ppgi.jemf.core.coordination.EnumPriority;
import br.ufrj.ppgi.jemf.core.coordination.EnumServiceStatus;
import br.ufrj.ppgi.jemf.utils.CustomLogger;
import br.ufrj.ppgi.jemf.utils.DateTimeFormat;

/**
 * @author Marcus Machado
 *
 */
public class TaskView extends CustomLinearView implements CustomView {

	/**
	 * Labels.
	 */
	private TextView lblTaskTitle, lblTaskPriority;



	/**
	 * Inputs Fields.
	 */
	/**
	 * Task Title.
	 */
	private EditText txtTaskTitle;
	/**
	 * Task Description.
	 */
	private EditText txtTaskDescription;
	/**
	 * Task Status.
	 */
	private Spinner sprTaskStatus;
	/**
	 * Task Priority.
	 */
	private Spinner sprTaskPriority;
	/**
	 * Task StartDate.
	 */
	private TextView txtTaskStartDate;
	private Calendar calendarStartDate;
	private DatePickerDialog.OnDateSetListener startDatePickerDialogListener;
	private TimePickerDialog.OnTimeSetListener startTimePickerDialogListener;
	/**
	 * Task EndDate.
	 */
	private TextView txtTaskEndDate;
	private Calendar calendarEndDate;
	private DatePickerDialog.OnDateSetListener endDatePickerDialogListener;
	private TimePickerDialog.OnTimeSetListener endTimePickerDialogListener;



	/**
	 * Parent Constructor.
	 * @param context
	 */
	private TaskView(Context context) {
		super(context);
    	CustomLogger.getInstance().infoLog(TaskView.class.getName(), "Class started.");
	}

	/**
	 * Constructor.
	 * @param context
	 * @param isListViewItem
	 */
	public TaskView(Context context, boolean isListViewItem) {
		// Super constructor.
		this(context);
		// Set default orientation.
		this.setOrientation(LinearLayout.VERTICAL);

		// If TaskView is to be an Item of ListView, as a List Row.
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
    	CustomLogger.getInstance().infoLog(TaskView.class.getName(), "Master layout retrieved.");
		// Field Title.
		lblTaskTitle = new TextView(getContext());
		lblTaskTitle.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
		lblTaskTitle.setTextColor(Color.BLACK);
		lblTaskTitle.setTypeface(Typeface.DEFAULT_BOLD);

		// Field Priority.
		lblTaskPriority = new TextView(getContext());
		lblTaskPriority.setTextAppearance(getContext(), android.R.style.TextAppearance_Small);
		lblTaskPriority.setTextColor(Color.BLACK);
		lblTaskPriority.setTypeface(null, Typeface.ITALIC);

		// Set to main layout.
		this.addView(lblTaskTitle, newWrapLayoutParams());
		this.addView(lblTaskPriority, newWrapLayoutParams());
	}

	/* (non-Javadoc)
	 * @see br.ufrj.ppgi.jemf.mobile.view.CustomView#getDetailLayout()
	 */
	@Override
	public void getDetailLayout() {
    	CustomLogger.getInstance().infoLog(TaskView.class.getName(), "Detail layout retrieved.");
		// In this case, main layout will group all Task Fields inside itself, becoming a Parent View.
		this.setLayoutParams(newMatchLayoutParams());

		// Field Rows.
		// Task Title Field - Row 1.
		txtTaskTitle = new EditText(getContext());
		txtTaskTitle.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtTaskTitle.setSingleLine();
		this.addView(newLinearRow("TITLE", txtTaskTitle, true));

		// Task Description Field - Row 2.
		txtTaskDescription = new EditText(getContext());
		txtTaskDescription.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtTaskDescription.setLines(5);
		this.addView(newLinearRow("DESCRIPTION", txtTaskDescription, true));

		// Task Status Field - Row 3.
		sprTaskStatus = new Spinner(getContext());
		// Create the item list of spinner.
		ArrayAdapter<String> spinnerArrayAdapterStatus = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item);
		spinnerArrayAdapterStatus.addAll(EnumServiceStatus.names);
		// Set the drop down view of spinner.
		spinnerArrayAdapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);		
		sprTaskStatus.setAdapter(spinnerArrayAdapterStatus);
		this.addView(newLinearRow("STATUS", sprTaskStatus, true));

		// Task Priority Field - Row 4.
		sprTaskPriority = new Spinner(getContext());
		// Create the item list of spinner.
		ArrayAdapter<String> spinnerArrayAdapterPriority = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item);
		spinnerArrayAdapterPriority.addAll(EnumPriority.names);
		// Set the drop down view of spinner.
		spinnerArrayAdapterPriority.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sprTaskPriority.setAdapter(spinnerArrayAdapterPriority);
		this.addView(newLinearRow("PRIORITY", sprTaskPriority, true));

		// Task StartDate Field - Row 5.
		txtTaskStartDate = new TextView(getContext());
		txtTaskStartDate.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtTaskStartDate.setSingleLine();
		txtTaskStartDate.setPadding(0, 10, 0, 5);
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
		txtTaskStartDate.setOnClickListener(new OnClickListener() {
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
		this.addView(newLinearRow("START DATE", txtTaskStartDate, true));

		// Task EndDate Field - Row 6.
		txtTaskEndDate = new TextView(getContext());
		txtTaskEndDate.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtTaskEndDate.setSingleLine();
		txtTaskEndDate.setPadding(0, 10, 0, 5);
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
		txtTaskEndDate.setOnClickListener(new OnClickListener() {
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
		this.addView(newLinearRow("END DATE", txtTaskEndDate, true));
	}

	/**
	 * Labels Getters and Setters.
	 */
    public String getTaskTitleLabel() {
        return this.lblTaskTitle.getText().toString();
    }
    public void setTaskTitleLabel(String title) {
            this.lblTaskTitle.setText(title);
    }

    public String getTaskPriorityLabel() {
        return this.lblTaskPriority.getText().toString();
    }
    public void setTaskPriorityLabel(String priority) {
            this.lblTaskPriority.setText(priority);
    }

	/**
	 * Fields Getters and Setters.
	 */
    /**
     * Task Title Field.
     */
	public String getTaskTitleText() {
	    return this.txtTaskTitle.getText().toString();
	}
	public void setTaskTitleText(String title) {
		this.txtTaskTitle.setText(title);
	}
    /**
     * Task Description Field.
     */
	public String getTaskDescriptionText() {
		return this.txtTaskDescription.getText().toString();
	}	 	 
	public void setTaskDescriptionText(String description) {
		this.txtTaskDescription.setText(description);
	}
    /**
     * Task Status Field.
     */
	public String getTaskStatusText() {
		return this.sprTaskStatus.getSelectedItem().toString();
	}
	@SuppressWarnings("unchecked")
	public void setTaskStatusText(String status) {
		this.sprTaskStatus.setSelection(((ArrayAdapter<String>) this.sprTaskStatus.getAdapter()).getPosition(status));
	}
    /**
     * Task Priority Field.
     */
	public String getTaskPriorityText() {
		return this.sprTaskPriority.getSelectedItem().toString();
	}
	@SuppressWarnings("unchecked")
	public void setTaskPriorityText(String priority) {
		this.sprTaskPriority.setSelection(((ArrayAdapter<String>) this.sprTaskPriority.getAdapter()).getPosition(priority));
	}
    /**
     * Task StartDate Field.
     */
	public String getTaskStartDateText() {
	    return this.txtTaskStartDate.getText().toString();
	}
	public void setTaskStartDateText(String startDate) {
		try {
			calendarStartDate.setTime(DateTimeFormat.StringToDateTime(startDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.txtTaskStartDate.setText(startDate);
	}
    /**
     * Task EndDate Field.
     */
	public String getTaskEndDateText() {
	    return this.txtTaskEndDate.getText().toString();
	}
	public void setTaskEndDateText(String endDate) {
		try {
			calendarEndDate.setTime(DateTimeFormat.StringToDateTime(endDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.txtTaskEndDate.setText(endDate);
	}



	/**
	 * Update StartDate Field with new date chosen by user.
	 */
	private void updateTxtStartDate() {
		this.txtTaskStartDate.setText(DateTimeFormat.DateTimeToString(calendarStartDate.getTime()));
	}
	/**
	 * Update EndDate Field with new date chosen by user.
	 */
	private void updateTxtEndDate() {
		this.txtTaskEndDate.setText(DateTimeFormat.DateTimeToString(calendarEndDate.getTime()));
	}

}
