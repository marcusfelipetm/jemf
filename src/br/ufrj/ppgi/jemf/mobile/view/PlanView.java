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
import br.ufrj.ppgi.jemf.utils.CustomLogger;
import br.ufrj.ppgi.jemf.utils.DateTimeFormat;

/**
 * @author Marcus Machado
 *
 */
public class PlanView extends CustomLinearView implements CustomView {

	/**
	 * Labels.
	 */
	private TextView lblPlanObjective, lblPlanPriority;



	/**
	 * Inputs Fields.
	 */
	/**
	 * Plan CreationDate.
	 */
	private TextView txtPlanCreationDate;
	private Calendar calendarCreationDate;
	private DatePickerDialog.OnDateSetListener creationDatePickerDialogListener;
	private TimePickerDialog.OnTimeSetListener creationTimePickerDialogListener;
	/**
	 * Plan LastModificationDate.
	 */
	private TextView txtPlanLastModificationDate;
	private Calendar calendarLastModificationDate;
	private DatePickerDialog.OnDateSetListener lastModificationDatePickerDialogListener;
	private TimePickerDialog.OnTimeSetListener lastModificationTimePickerDialogListener;
	/**
	 * Plan Priority.
	 */
	private Spinner sprPlanPriority;
	/**
	 * Plan Description.
	 */
	private EditText txtPlanDescription;
	/**
	 * Plan Observation.
	 */
	private EditText txtPlanObservation;
	/**
	 * Plan Objective.
	 */
	private EditText txtPlanObjective;
	/**
	 * Plan Risk.
	 */
	private EditText txtPlanRisk;
	/**
	 * Plan CheckList.
	 */
	private EditText txtPlanCheckList;
	


	/**
	 * Parent Constructor.
	 * @param context
	 */
	private PlanView(Context context) {
		super(context);
    	CustomLogger.getInstance().infoLog(PlanView.class.getName(), "Class started.");
	}

	/**
	 * Constructor.
	 * @param context
	 * @param isListViewItem
	 */
	public PlanView(Context context, boolean isListViewItem) {
		// Super constructor.
		this(context);
		// Set default orientation.
		this.setOrientation(LinearLayout.VERTICAL);

		// If PlanView is to be an Item of ListView, as a List Row.
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
    	CustomLogger.getInstance().infoLog(PlanView.class.getName(), "Master layout retrieved.");
		// Field Objective.
    	lblPlanObjective = new TextView(getContext());
    	lblPlanObjective.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
    	lblPlanObjective.setTextColor(Color.BLACK);
    	lblPlanObjective.setTypeface(Typeface.DEFAULT_BOLD);

		// Field Activated.
    	lblPlanPriority = new TextView(getContext());
    	lblPlanPriority.setTextAppearance(getContext(), android.R.style.TextAppearance_Small);
    	lblPlanPriority.setTextColor(Color.BLACK);
    	lblPlanPriority.setTypeface(null, Typeface.ITALIC);

		// Set to main layout.
		this.addView(lblPlanObjective, newWrapLayoutParams());
		this.addView(lblPlanPriority, newWrapLayoutParams());
	}

	/* (non-Javadoc)
	 * @see br.ufrj.ppgi.jemf.mobile.view.CustomView#getDetailLayout()
	 */
	@Override
	public void getDetailLayout() {
    	CustomLogger.getInstance().infoLog(PlanView.class.getName(), "Detail layout retrieved.");
		// In this case, main layout will group all Plan Fields inside itself, becoming a Parent View.
		this.setLayoutParams(newMatchLayoutParams());

		// Field Rows.
		// Plan Objective Field - Row 1.
		txtPlanObjective = new EditText(getContext());
		txtPlanObjective.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtPlanObjective.setLines(5);
		this.addView(newLinearRow("OBJECTIVE", txtPlanObjective, true));

		// Plan Description Field - Row 2.
		txtPlanDescription = new EditText(getContext());
		txtPlanDescription.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtPlanDescription.setLines(5);
		this.addView(newLinearRow("DESCRIPTION", txtPlanDescription, true));

		// Plan Risk Field - Row 3.
		txtPlanRisk = new EditText(getContext());
		txtPlanRisk.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtPlanRisk.setLines(5);
		this.addView(newLinearRow("RISK", txtPlanRisk, true));

		// Plan CheckList Field - Row 4.
		txtPlanCheckList = new EditText(getContext());
		txtPlanCheckList.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtPlanCheckList.setLines(5);
		this.addView(newLinearRow("CHECKLIST", txtPlanCheckList, true));

		// Plan Observation Field - Row 5.
		txtPlanObservation = new EditText(getContext());
		txtPlanObservation.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtPlanObservation.setLines(5);
		this.addView(newLinearRow("OBSERVATION", txtPlanObservation, true));

		// Plan Priority Field - Row 6.
		sprPlanPriority = new Spinner(getContext());
		// Create the item list of spinner.
		ArrayAdapter<String> spinnerArrayAdapterPriority = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item);
		spinnerArrayAdapterPriority.addAll(EnumPriority.names);
		// Set the drop down view of spinner.
		spinnerArrayAdapterPriority.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);		
		sprPlanPriority.setAdapter(spinnerArrayAdapterPriority);
		this.addView(newLinearRow("PRIORITY", sprPlanPriority, true));

		// Plan CreationDate Field - Row 7.
		txtPlanCreationDate = new TextView(getContext());
		txtPlanCreationDate.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtPlanCreationDate.setSingleLine();
		txtPlanCreationDate.setPadding(0, 10, 0, 5);
		// Get current date.
		calendarCreationDate = Calendar.getInstance();
		// Create a DatePicker Listener.
		creationDatePickerDialogListener = new DatePickerDialog.OnDateSetListener() {
		    @Override
		    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		    	calendarCreationDate.set(Calendar.YEAR, year);
		    	calendarCreationDate.set(Calendar.MONTH, monthOfYear);
		    	calendarCreationDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		    	// Update Date chosen by user.
		    	updateTxtCreationDate();
		    }
		};
		// Create a TimePicker Listener.
		creationTimePickerDialogListener = new TimePickerDialog.OnTimeSetListener() {
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				calendarCreationDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
				calendarCreationDate.set(Calendar.MINUTE, minute);
		    	// Update Time chosen by user.
		    	updateTxtCreationDate();
			}
		};
		// When the TextView is clicked then POPUP the DatePicker dialog to enable user choose the date.
		txtPlanCreationDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Start the Date Picker.
				DatePickerDialog date = new DatePickerDialog(
						getContext(),
		        		creationDatePickerDialogListener,
		        		calendarCreationDate.get(Calendar.YEAR),
		        		calendarCreationDate.get(Calendar.MONTH),
		        		calendarCreationDate.get(Calendar.DAY_OF_MONTH));
				date.setOnDismissListener(new OnDismissListener() {
					@Override
					public void onDismiss(DialogInterface dialog) {
						// When close Date Picker, then Start the Time Picker.
				 		new TimePickerDialog(
				 				getContext(),
				 				creationTimePickerDialogListener,
				 				calendarCreationDate.get(Calendar.HOUR_OF_DAY),
				 				calendarCreationDate.get(Calendar.MINUTE),
				 				true).show();
					}
				});
				date.show();
		     }
		 });
		this.addView(newLinearRow("CREATION DATE", txtPlanCreationDate, true));

		// Plan LastModificationDate Field - Row 8.
		txtPlanLastModificationDate = new TextView(getContext());
		txtPlanLastModificationDate.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtPlanLastModificationDate.setSingleLine();
		txtPlanLastModificationDate.setPadding(0, 10, 0, 5);
		// Get current date.
		calendarLastModificationDate = Calendar.getInstance();
		// Create a DatePicker Listener.
		lastModificationDatePickerDialogListener = new DatePickerDialog.OnDateSetListener() {
		    @Override
		    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		    	calendarLastModificationDate.set(Calendar.YEAR, year);
		    	calendarLastModificationDate.set(Calendar.MONTH, monthOfYear);
		    	calendarLastModificationDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		    	// Update Date chosen by user.
		    	updateTxtLastModificationDate();
		    }
		};
		// Create a TimePicker Listener.
		lastModificationTimePickerDialogListener = new TimePickerDialog.OnTimeSetListener() {
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		    	calendarLastModificationDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
		    	calendarLastModificationDate.set(Calendar.MINUTE, minute);
		    	// Update Time chosen by user.
		    	updateTxtLastModificationDate();
			}
		};
		// When the TextView is clicked then POPUP the DatePicker dialog to enable user choose the date.
		txtPlanLastModificationDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Start the Date Picker.
				DatePickerDialog date = new DatePickerDialog(
						getContext(),
		        		lastModificationDatePickerDialogListener,
		        		calendarLastModificationDate.get(Calendar.YEAR),
		        		calendarLastModificationDate.get(Calendar.MONTH),
		        		calendarLastModificationDate.get(Calendar.DAY_OF_MONTH));
				date.setOnDismissListener(new OnDismissListener() {
					@Override
					public void onDismiss(DialogInterface dialog) {
						// When close Date Picker, then Start the Time Picker.
				 		new TimePickerDialog(
				 				getContext(),
				 				lastModificationTimePickerDialogListener,
				 				calendarLastModificationDate.get(Calendar.HOUR_OF_DAY),
				 				calendarLastModificationDate.get(Calendar.MINUTE),
				 				true).show();
					}
				});
				date.show();
		     }
		 });
		this.addView(newLinearRow("MODIFICATION DATE", txtPlanLastModificationDate, true));
	}

	
	/**
	 * Labels Getters and Setters.
	 */
    public String getPlanObjectiveLabel() {
        return this.lblPlanObjective.getText().toString();
    }
    public void setPlanObjectiveLabel(String objective) {
            this.lblPlanObjective.setText(objective);
    }
    public String getPlanPriorityLabel() {
        return this.lblPlanPriority.getText().toString();
    }
    public void setPlanPriorityLabel(String priority) {
            this.lblPlanPriority.setText(priority);
    }

	/**
	 * Fields Getters and Setters.
	 */
    /**
     * Plan Observation Field.
     */
	public String getPlanObservationText() {
	    return this.txtPlanObservation.getText().toString();
	}
	public void setPlanObservationText(String observation) {
		this.txtPlanObservation.setText(observation);
	}
    /**
     * Plan Description Field.
     */
	public String getPlanDescriptionText() {
	    return this.txtPlanDescription.getText().toString();
	}
	public void setPlanDescriptionText(String description) {
		this.txtPlanDescription.setText(description);
	}
    /**
     * Plan Objective Field.
     */
	public String getPlanObjectiveText() {
	    return this.txtPlanDescription.getText().toString();
	}
	public void setPlanObjectiveText(String objective) {
		this.txtPlanObjective.setText(objective);
	}
    /**
     * Plan Risk Field.
     */
	public String getPlanRiskText() {
	    return this.txtPlanRisk.getText().toString();
	}
	public void setPlanRiskText(String risk) {
		this.txtPlanRisk.setText(risk);
	}
    /**
     * Plan CheckList Field.
     */
	public String getPlanCheckListText() {
	    return this.txtPlanCheckList.getText().toString();
	}
	public void setPlanCheckListText(String checkList) {
		this.txtPlanCheckList.setText(checkList);
	}
    /**
     * Plan Priority Field.
     */
	public String getPlanPriorityText() {
		return this.sprPlanPriority.getSelectedItem().toString();
	}
	@SuppressWarnings("unchecked")
	public void setPlanPriorityText(String priority) {
		this.sprPlanPriority.setSelection(((ArrayAdapter<String>) this.sprPlanPriority.getAdapter()).getPosition(priority));
	}
    /**
     * Plan CreationDate Field.
     */
	public String getPlanCreationDateText() {
	    return this.txtPlanCreationDate.getText().toString();
	}
	public void setPlanCreationDateText(String creationDate) {
		try {
			calendarCreationDate.setTime(DateTimeFormat.StringToDateTime(creationDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.txtPlanCreationDate.setText(creationDate);
	}
    /**
     * Plan LastModificationDate Field.
     */
	public String getPlanLastModificationDateText() {
	    return this.txtPlanLastModificationDate.getText().toString();
	}
	public void setPlanLastModificationDateText(String lastModificationDate) {
		try {
			calendarLastModificationDate.setTime(DateTimeFormat.StringToDateTime(lastModificationDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.txtPlanLastModificationDate.setText(lastModificationDate);
	}



	/**
	 * Update CreationDate Field with new date chosen by user.
	 */
	private void updateTxtCreationDate() {
		this.txtPlanCreationDate.setText(DateTimeFormat.DateTimeToString(calendarCreationDate.getTime()));
	}
	/**
	 * Update LastModificationDate Field with new date chosen by user.
	 */
	private void updateTxtLastModificationDate() {
		this.txtPlanLastModificationDate.setText(DateTimeFormat.DateTimeToString(calendarLastModificationDate.getTime()));
	}

}
