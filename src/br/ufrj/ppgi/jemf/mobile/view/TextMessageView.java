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
public class TextMessageView extends CustomLinearView implements CustomView {

	/**
	 * Labels.
	 */
	private TextView lblTextMessageSubject, lblTextMessagePriority;



	/**
	 * Inputs Fields.
	 */
	/**
	 * TextMessage CreationDate.
	 */
	private TextView txtTextMessageCreationDate;
	private Calendar calendarCreationDate;
	private DatePickerDialog.OnDateSetListener creationDatePickerDialogListener;
	private TimePickerDialog.OnTimeSetListener creationTimePickerDialogListener;
	/**
	 * TextMessage LastModificationDate.
	 */
	private TextView txtTextMessageLastModificationDate;
	private Calendar calendarLastModificationDate;
	private DatePickerDialog.OnDateSetListener lastModificationDatePickerDialogListener;
	private TimePickerDialog.OnTimeSetListener lastModificationTimePickerDialogListener;
	/**
	 * TextMessage Priority.
	 */
	private Spinner sprTextMessagePriority;
	/**
	 * TextMessage Subject.
	 */
	private EditText txtTextMessageSubject;
	/**
	 * TextMessage Content.
	 */
	private EditText txtTextMessageContent;

	


	/**
	 * Parent Constructor.
	 * @param context
	 */
	private TextMessageView(Context context) {
		super(context);
    	CustomLogger.getInstance().infoLog(TextMessageView.class.getName(), "Class started.");
	}

	/**
	 * Constructor.
	 * @param context
	 * @param isListViewItem
	 */
	public TextMessageView(Context context, boolean isListViewItem) {
		// Super constructor.
		this(context);
		// Set default orientation.
		this.setOrientation(LinearLayout.VERTICAL);

		// If TextMessageView is to be an Item of ListView, as a List Row.
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
    	CustomLogger.getInstance().infoLog(TextMessageView.class.getName(), "Master layout retrieved.");
		// Field Subject.
    	lblTextMessageSubject = new TextView(getContext());
    	lblTextMessageSubject.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
    	lblTextMessageSubject.setTextColor(Color.BLACK);
    	lblTextMessageSubject.setTypeface(Typeface.DEFAULT_BOLD);

		// Field Priority.
    	lblTextMessagePriority = new TextView(getContext());
    	lblTextMessagePriority.setTextAppearance(getContext(), android.R.style.TextAppearance_Small);
    	lblTextMessagePriority.setTextColor(Color.BLACK);
    	lblTextMessagePriority.setTypeface(null, Typeface.ITALIC);

		// Set to main layout.
		this.addView(lblTextMessageSubject, newWrapLayoutParams());
		this.addView(lblTextMessagePriority, newWrapLayoutParams());
	}

	/* (non-Javadoc)
	 * @see br.ufrj.ppgi.jemf.mobile.view.CustomView#getDetailLayout()
	 */
	@Override
	public void getDetailLayout() {
    	CustomLogger.getInstance().infoLog(TextMessageView.class.getName(), "Detail layout retrieved.");
		// In this case, main layout will group all TextMessage Fields inside itself, becoming a Parent View.
		this.setLayoutParams(newMatchLayoutParams());

		// Field Rows.
		// TextMessage Subject Field - Row 1.
		txtTextMessageSubject = new EditText(getContext());
		txtTextMessageSubject.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtTextMessageSubject.setSingleLine();
		this.addView(newLinearRow("SUBJECT", txtTextMessageSubject, true));

		// TextMessage Content Field - Row 2.
		txtTextMessageContent = new EditText(getContext());
		txtTextMessageContent.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtTextMessageContent.setLines(5);
		this.addView(newLinearRow("MESSAGE", txtTextMessageContent, true));

		// TextMessage Priority Field - Row 3.
		sprTextMessagePriority = new Spinner(getContext());
		// Create the item list of spinner.
		ArrayAdapter<String> spinnerArrayAdapterPriority = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item);
		spinnerArrayAdapterPriority.addAll(EnumPriority.names);
		// Set the drop down view of spinner.
		spinnerArrayAdapterPriority.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);		
		sprTextMessagePriority.setAdapter(spinnerArrayAdapterPriority);
		this.addView(newLinearRow("PRIORITY", sprTextMessagePriority, true));

		// TextMessage CreationDate Field - Row 4.
		txtTextMessageCreationDate = new TextView(getContext());
		txtTextMessageCreationDate.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtTextMessageCreationDate.setSingleLine();
		txtTextMessageCreationDate.setPadding(0, 10, 0, 5);
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
		txtTextMessageCreationDate.setOnClickListener(new OnClickListener() {
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
		this.addView(newLinearRow("CREATION DATE", txtTextMessageCreationDate, true));

		// TextMessage LastModificationDate Field - Row 5.
		txtTextMessageLastModificationDate = new TextView(getContext());
		txtTextMessageLastModificationDate.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtTextMessageLastModificationDate.setSingleLine();
		txtTextMessageLastModificationDate.setPadding(0, 10, 0, 5);
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
		txtTextMessageLastModificationDate.setOnClickListener(new OnClickListener() {
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
		this.addView(newLinearRow("MODIFICATION DATE", txtTextMessageLastModificationDate, true));
	}

	
	/**
	 * Labels Getters and Setters.
	 */
    public String getTextMessageSubjectLabel() {
        return this.lblTextMessageSubject.getText().toString();
    }
    public void setTextMessageSubjectLabel(String subject) {
            this.lblTextMessageSubject.setText(subject);
    }
    public String getTextMessagePriorityLabel() {
        return this.lblTextMessagePriority.getText().toString();
    }
    public void setTextMessagePriorityLabel(String priority) {
            this.lblTextMessagePriority.setText(priority);
    }

	/**
	 * Fields Getters and Setters.
	 */
    /**
     * TextMessage Content Field.
     */
	public String getTextMessageContentText() {
	    return this.txtTextMessageContent.getText().toString();
	}
	public void setTextMessageContentText(String content) {
		this.txtTextMessageContent.setText(content);
	}
    /**
     * TextMessage Subject Field.
     */
	public String getTextMessageSubjectText() {
	    return this.txtTextMessageSubject.getText().toString();
	}
	public void setTextMessageSubjectText(String subject) {
		this.txtTextMessageSubject.setText(subject);
	}
    /**
     * TextMessage Priority Field.
     */
	public String getTextMessagePriorityText() {
		return this.sprTextMessagePriority.getSelectedItem().toString();
	}
	@SuppressWarnings("unchecked")
	public void setTextMessagePriorityText(String priority) {
		this.sprTextMessagePriority.setSelection(((ArrayAdapter<String>) this.sprTextMessagePriority.getAdapter()).getPosition(priority));
	}
    /**
     * TextMessage CreationDate Field.
     */
	public String getTextMessageCreationDateText() {
	    return this.txtTextMessageCreationDate.getText().toString();
	}
	public void setTextMessageCreationDateText(String creationDate) {
		try {
			calendarCreationDate.setTime(DateTimeFormat.StringToDateTime(creationDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.txtTextMessageCreationDate.setText(creationDate);
	}
    /**
     * TextMessage LastModificationDate Field.
     */
	public String getTextMessageLastModificationDateText() {
	    return this.txtTextMessageLastModificationDate.getText().toString();
	}
	public void setTextMessageLastModificationDateText(String lastModificationDate) {
		try {
			calendarLastModificationDate.setTime(DateTimeFormat.StringToDateTime(lastModificationDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.txtTextMessageLastModificationDate.setText(lastModificationDate);
	}



	/**
	 * Update CreationDate Field with new date chosen by user.
	 */
	private void updateTxtCreationDate() {
		this.txtTextMessageCreationDate.setText(DateTimeFormat.DateTimeToString(calendarCreationDate.getTime()));
	}
	/**
	 * Update LastModificationDate Field with new date chosen by user.
	 */
	private void updateTxtLastModificationDate() {
		this.txtTextMessageLastModificationDate.setText(DateTimeFormat.DateTimeToString(calendarLastModificationDate.getTime()));
	}

}
