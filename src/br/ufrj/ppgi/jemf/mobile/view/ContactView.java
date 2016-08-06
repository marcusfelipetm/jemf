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

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import br.ufrj.ppgi.jemf.core.communication.EnumLanguage;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class ContactView extends CustomLinearView implements CustomView {

	/**
	 * Labels.
	 */
	private TextView lblContactPhone, lblContactLanguage;



	/**
	 * Inputs Fields.
	 */
	/**
	 * Contact Phone.
	 */
	private EditText txtContactPhone;
	/**
	 * Contact Email.
	 */
	private EditText txtContactEmail;
	/**
	 * Contact Radio.
	 */
	private EditText txtContactRadio;
	/**
	 * Contact Language.
	 */
	private Spinner sprContactLanguage;
	


	/**
	 * Parent Constructor.
	 * @param context
	 */
	private ContactView(Context context) {
		super(context);
    	CustomLogger.getInstance().infoLog(ContactView.class.getName(), "Class started.");
	}

	/**
	 * Constructor.
	 * @param context
	 * @param isListViewItem
	 */
	public ContactView(Context context, boolean isListViewItem) {
		// Super constructor.
		this(context);
		// Set default orientation.
		this.setOrientation(LinearLayout.VERTICAL);

		// If ContactView is to be an Item of ListView, as a List Row.
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
    	CustomLogger.getInstance().infoLog(ContactView.class.getName(), "Master layout retrieved.");
		// Field Name.
    	lblContactPhone = new TextView(getContext());
    	lblContactPhone.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
    	lblContactPhone.setTextColor(Color.BLACK);
    	lblContactPhone.setTypeface(Typeface.DEFAULT_BOLD);

		// Field Gender.
    	lblContactLanguage = new TextView(getContext());
    	lblContactLanguage.setTextAppearance(getContext(), android.R.style.TextAppearance_Small);
    	lblContactLanguage.setTextColor(Color.BLACK);
    	lblContactLanguage.setTypeface(null, Typeface.ITALIC);

		// Set to main layout.
		this.addView(lblContactPhone, newWrapLayoutParams());
		this.addView(lblContactLanguage, newWrapLayoutParams());
	}

	/* (non-Javadoc)
	 * @see br.ufrj.ppgi.jemf.mobile.view.CustomView#getDetailLayout()
	 */
	@Override
	public void getDetailLayout() {
    	CustomLogger.getInstance().infoLog(ContactView.class.getName(), "Detail layout retrieved.");
		// In this case, main layout will group all Contact Fields inside itself, becoming a Parent View.
		this.setLayoutParams(newMatchLayoutParams());

		// Field Rows.
		// Contact Phone Field - Row 1.
		txtContactPhone = new EditText(getContext());
		txtContactPhone.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtContactPhone.setSingleLine();
		this.addView(newLinearRow("PHONE", txtContactPhone, true));

		// Contact Email Field - Row 2.
		txtContactEmail = new EditText(getContext());
		txtContactEmail.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtContactEmail.setSingleLine();
		this.addView(newLinearRow("EMAIL", txtContactEmail, true));

		// Contact Radio Field - Row 3.
		txtContactRadio = new EditText(getContext());
		txtContactRadio.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
		txtContactRadio.setSingleLine();
		this.addView(newLinearRow("RADIO", txtContactRadio, true));

		// Contact Language Field - Row 4.
		sprContactLanguage = new Spinner(getContext());
		// Create the item list of spinner.
		ArrayAdapter<String> spinnerArrayAdapterLanguage = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item);
		spinnerArrayAdapterLanguage.addAll(EnumLanguage.names);
		// Set the drop down view of spinner.
		spinnerArrayAdapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sprContactLanguage.setAdapter(spinnerArrayAdapterLanguage);
		this.addView(newLinearRow("LANGUAGE", sprContactLanguage, true));
	}
	
	/**
	 * Labels Getters and Setters.
	 */
    public String getContactPhoneLabel() {
        return this.lblContactPhone.getText().toString();
    }
    public void setContactPhoneLabel(String phone) {
            this.lblContactPhone.setText(phone);
    }
    public String getContactLanguageLabel() {
        return this.lblContactLanguage.getText().toString();
    }
    public void setContactLanguageLabel(String language) {
            this.lblContactLanguage.setText(language);
    }

	/**
	 * Fields Getters and Setters.
	 */
    /**
     * Contact Phone Field.
     */
	public String getContactPhoneText() {
	    return this.txtContactPhone.getText().toString();
	}
	public void setContactPhoneText(String phone) {
		this.txtContactPhone.setText(phone);
	}
    /**
     * Contact Email Field.
     */
	public String getContactEmailText() {
	    return this.txtContactEmail.getText().toString();
	}
	public void setContactEmailText(String email) {
		this.txtContactEmail.setText(email);
	}
    /**
     * Contact Radio Field.
     */
	public String getContactRadioText() {
	    return this.txtContactRadio.getText().toString();
	}
	public void setContactRadioText(String radio) {
		this.txtContactRadio.setText(radio);
	}
    /**
     * Contact Language Field.
     */
	public String getContactLanguageText() {
		return this.sprContactLanguage.getSelectedItem().toString();
	}
	@SuppressWarnings("unchecked")
	public void setContactLanguageText(String language) {
		this.sprContactLanguage.setSelection(((ArrayAdapter<String>) this.sprContactLanguage.getAdapter()).getPosition(language));
	}

}
