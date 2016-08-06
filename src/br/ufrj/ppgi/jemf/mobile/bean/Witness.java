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
import br.ufrj.ppgi.jemf.core.cooperation.AbstractWitness;
import br.ufrj.ppgi.jemf.utils.DateTimeFormat;

/**
 * @author Marcus Machado
 *
 */
public class Witness extends AbstractWitness implements Parcelable, Comparable<Witness> {

	/* Relationships - Future work */
	/**
	 * Location ID.
	 */
	private int idLocation;



	/**
	 * Constructor.
	 */
	public Witness() {
		super();
		this.setId(0);
	}

	/**
	 * Constructor.
	 * @param name
	 * @param gender
	 * @param birthDate
	 * @param age
	 * @throws ParseException
	 */
	public Witness(String name, int gender, String birthDate, int age) {
		super();
		this.setName(name);
		this.setGender(gender);
		this.setBirthDateString(birthDate);
		this.setAge(age);
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
	 * Witness Parcelable Constructor.
	 * Parcelable to get passed among activities through Intent (similar to Serialization).
	 * Attention to class members order an this use FIFO method.
	 */
	private Witness(Parcel in) {
		setId(in.readInt());
		setName(in.readString());
		setGender(in.readInt());
		setBirthDateString(in.readString());
		setAge(in.readInt());
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
	 * Write Witness data to Parcelable. 
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(getId());
		dest.writeString(getName());
		dest.writeInt(getGender());
		dest.writeString(getBirthDateString());
		dest.writeInt(getAge());
	}

	/**
	 * Public Witness Parcelable Constructor.
	 */
	public static final Parcelable.Creator<Witness> CREATOR = new Parcelable.Creator<Witness>() {
		/**
		 * Create a Witness from Parcel.
		 * @return Witness
		 */
		@Override
		public Witness createFromParcel(Parcel source) {
			// Use Parcelable constructor.
			return new Witness(source);
		}

		/**
		 * Create a Witness Array.
		 * @return Array
		 */
		@Override
		public Witness[] newArray(int size) {
			return new Witness[size];
		}
	};



	/**
	 * Compare Witness objects.
	 * @param another
	 */
	@Override
	public int compareTo(Witness another) {
		return this.getName().compareTo(another.getName());
	}

	/**
	 * Witness Object to String.
	 * @return String
	 */
	@Override
	public String toString() {
		try {
			return new StringBuilder("Witness [")
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
	 * Witness Object to JSON.
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
		    object.put("Witness", data);
	        return object.toString();
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
        return null;
	}

}
