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

import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;
import br.ufrj.ppgi.jemf.core.coordination.AbstractVolunteer;
import br.ufrj.ppgi.jemf.utils.DateTimeFormat;

/**
 * @author Marcus Machado
 *
 */
public class Volunteer extends AbstractVolunteer implements Parcelable, Comparable<Volunteer> {

	/* Relationships - Future work */
	/**
	 * Location ID.
	 */
	private int idLocation;



	/**
	 * Constructor.
	 */
	public Volunteer() {
		super();
		this.setId(0);
	}

	/**
	 * Constructor.
	 * @param name
	 * @param gender
	 * @param birthDate
	 * @param age
	 * @param login
	 * @param password
	 * @throws ParseException
	 */
	public Volunteer(String name, int gender, String birthDate, int age, String login, String password) {
		super();
		this.setName(name);
		this.setGender(gender);
		this.setBirthDateString(birthDate);
		this.setAge(age);
		this.setLogin(login);
		this.setPassword(password);
	}	



	/**
	 * Set the Location ID.
	 * @param idLocation
	 */
	public void setLocationId(int idLocation) {
		this.idLocation = idLocation;
	}

	/**
	 * Get the Location ID.
	 * @return int
	 */
	public int getLocationId() {
		return this.idLocation;
	}

	/**
	 * Get the birth date as String value.
	 * @return String
	 */
	public String getBirthDateString() {
		return DateTimeFormat.DateTimeToString(getBirthDate());
	}

	/**
	 * Set the birth date as String value.
	 * @param birthDate
	 * @throws ParseException
	 */
	public void setBirthDateString(String birthDate) {
		try {
			setBirthDate(DateTimeFormat.StringToDateTime(birthDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}



	/**
	 * Volunteer Parcelable Constructor.
	 * Parcelable to get passed among activities through Intent (similar to Serialization).
	 * Attention to class members order an this use FIFO method.
	 */
	private Volunteer(Parcel in) {
		setId(in.readInt());
		setName(in.readString());
		setGender(in.readInt());
		setBirthDateString(in.readString());
		setAge(in.readInt());
		setLogin(in.readString());
		setPassword(in.readString());
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
	 * Write Volunteer data to Parcelable. 
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(getId());
		dest.writeString(getName());
		dest.writeInt(getGender());
		dest.writeString(getBirthDateString());
		dest.writeInt(getAge());
		dest.writeString(getLogin());
		dest.writeString(getPassword());
	}

	/**
	 * Public Volunteer Parcelable Constructor.
	 */
	public static final Parcelable.Creator<Volunteer> CREATOR = new Parcelable.Creator<Volunteer>() {
		/**
		 * Create a Volunteer from Parcel.
		 * @return Volunteer
		 */
		@Override
		public Volunteer createFromParcel(Parcel source) {
			// Use Parcelable constructor.
			return new Volunteer(source);
		}

		/**
		 * Create a Volunteer Array.
		 * @return Array
		 */
		@Override
		public Volunteer[] newArray(int size) {
			return new Volunteer[size];
		}
	};



	/**
	 * Compare Volunteer objects.
	 * @param another
	 */
	@Override
	public int compareTo(Volunteer another) {
		return this.getName().compareTo(another.getName());
	}

	/**
	 * Volunteer Object to String.
	 * @return String
	 */
	@Override
	public String toString() {
		try {
			return new StringBuilder("Volunteer [")
				.append("id=" + String.valueOf(getId()) + ", ")
				.append("name=" + getName() + ", ")
				.append("gender=" + String.valueOf(getGender()) + ", ")
				.append("birthDate=" + getBirthDateString() + ", ")
				.append("age=" + String.valueOf(getAge()))
				.append("]")
			.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	/**
	 * Volunteer Object to JSON.
	 * @return String
	 */
	public String toJSON() {
	    try {
		    JSONObject data = new JSONObject();
	        data.put("id", String.valueOf(getId()));
	        data.put("name", getName());
	        data.put("gender", String.valueOf(getGender()));
	        data.put("birthDate", getBirthDateString());
	        data.put("age", String.valueOf(getAge()));
		    JSONObject object = new JSONObject();
		    object.put("Volunteer", data);
	        return object.toString();
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
        return null;
	}

}
