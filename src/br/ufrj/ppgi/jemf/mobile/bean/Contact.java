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
package br.ufrj.ppgi.jemf.mobile.bean;

import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;
import br.ufrj.ppgi.jemf.core.AbstractContact;
import br.ufrj.ppgi.jemf.core.communication.EnumLanguage;

/**
 * @author Marcus Machado
 *
 */
public class Contact extends AbstractContact implements Parcelable, Comparable<Contact> {

	/* Relationships - Future work */
	/**
	 * Entity ID.
	 */
	private int idEntity;



	/**
	 * Constructor.
	 */
	public Contact() {
		super();
		this.setId(0);
	}

	/**
	 * Constructor.
	 * @param phone
	 * @param email
	 * @param radio
	 * @param language 
	 */
	public Contact(String phone, String email, String radio, String language) {
		super();
		this.setPhone(phone);
		this.setEmail(email);
		this.setRadio(radio);
		this.setLanguageString(language);
	}	



	/**
	 * Set the Entity ID.
	 * @param idEntity
	 */
	public void setEntityId(int idEntity) {
		this.idEntity = idEntity;
	}

	/**
	 * Get the Entity ID.
	 * @return int
	 */
	public int getEntityId() {
		return this.idEntity;
	}

	/**
	 * Get the language as String value.
	 * @return String
	 */
	public String getLanguageString() {
		return getLanguage().name();
	}

	/**
	 * Set the language as String value.
	 * @param language
	 */
	public void setLanguageString(String language) {
		setLanguage(EnumLanguage.valueOf(language.toUpperCase(Locale.ROOT)));
	}



	/**
	 * Contact Parcelable Constructor.
	 * Parcelable to get passed among activities through Intent (similar to Serialization).
	 * Attention to class members order an this use FIFO method.
	 */
	private Contact(Parcel in) {
		setId(in.readInt());
		setPhone(in.readString());
		setEmail(in.readString());
		setRadio(in.readString());
		setLanguageString(in.readString());
	}

	/**
	 * Describe Parcelable contents.
	 * @return int
	 */
	@Override
	public int describeContents() {
		return 0;
	}

	/**
	 * Write Contact data to Parcelable. 
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(getId());
		dest.writeString(getPhone());
		dest.writeString(getEmail());
		dest.writeString(getRadio());
		dest.writeString(getLanguageString());
	}

	/**
	 * Public Contact Parcelable Constructor.
	 */
	public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
		/**
		 * Create a Contact from Parcel.
		 * @return Contact
		 */
		@Override
		public Contact createFromParcel(Parcel source) {
			// Use Parcelable constructor.
			return new Contact(source);
		}

		/**
		 * Create a Contact Array.
		 * @return Array
		 */
		@Override
		public Contact[] newArray(int size) {
			return new Contact[size];
		}
	};



	/**
	 * Compare Contact objects.
	 * @param another
	 */
	@Override
	public int compareTo(Contact another) {
		return this.getPhone().compareTo(another.getPhone());
	}

	/**
	 * Contact Object to String.
	 * @return String
	 */
	@Override
	public String toString() {
		try {
			return new StringBuilder("Contact [")
				.append("id=" + String.valueOf(getId()) + ", ")
				.append("phone=" + getPhone() + ", ")
				.append("email=" + getEmail() + ", ")
				.append("radio=" + getRadio() + ", ")
				.append("language=" + getLanguageString())
				.append("]") 
			.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	/**
	 * Contact Object to JSON.
	 * @return String
	 */
	public String toJSON() {
	    try {
		    JSONObject data = new JSONObject();
	        data.put("id", String.valueOf(getId()));
	        data.put("phone", getPhone());
	        data.put("email", getEmail());
	        data.put("radio", getRadio());
	        data.put("language", getLanguageString());
		    JSONObject object = new JSONObject();
		    object.put("Contact", data);
	        return object.toString();
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
        return null;
	}

}
