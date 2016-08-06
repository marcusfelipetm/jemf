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
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;
import br.ufrj.ppgi.jemf.core.cooperation.AbstractVictim;
import br.ufrj.ppgi.jemf.core.cooperation.EnumVictimStatus;
import br.ufrj.ppgi.jemf.utils.DateTimeFormat;

/**
 * @author Marcus Machado
 *
 */
public class Victim extends AbstractVictim implements Parcelable, Comparable<Victim> {

	/* Relationships - Future work */
	/**
	 * Location ID.
	 */
	private int idLocation;

	/**
	 * HealthCareUnit ID.
	 */
	private int idHealthCareUnit;

	/**
	 * AffectedOrganization ID.
	 */
	private int idAffectedOrganization;

	/**
	 * Witness ID.
	 */
	private int idWitness;



	/**
	 * Constructor.
	 */
	public Victim() {
		super();
		this.setId(0);
	}

	/**
	 * Constructor.
	 * @param name
	 * @param gender
	 * @param birthDate
	 * @param age
	 * @param status
	 * @throws ParseException
	 */
	public Victim(String name, int gender, String birthDate, int age, String status) {
		super();
		this.setName(name);
		this.setGender(gender);
		this.setBirthDateString(birthDate);
		this.setAge(age);
		this.setStatusString(status);
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
	 * Set the HealthCareUnit ID.
	 * @param idHealthCareUnit
	 */
	public void setHealthCareUnitId(int idHealthCareUnit) {
		this.idHealthCareUnit = idHealthCareUnit;
	}

	/**
	 * Get the HealthCareUnit ID.
	 * @return int
	 */
	public int getHealthCareUnitId() {
		return this.idHealthCareUnit;
	}

	/**
	 * Set the AffectedOrganization ID.
	 * @param idAffectedOrganization
	 */
	public void setAffectedOrganizationId(int idAffectedOrganization) {
		this.idAffectedOrganization = idAffectedOrganization;
	}

	/**
	 * Get the AffectedOrganization ID.
	 * @return int
	 */
	public int getAffectedOrganizationId() {
		return this.idAffectedOrganization;
	}

	/**
	 * Set the Witness ID.
	 * @param idWitness
	 */
	public void setWitnessId(int idWitness) {
		this.idWitness = idWitness;
	}

	/**
	 * Get the Witness ID.
	 * @return int
	 */
	public int getWitnessId() {
		return this.idWitness;
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
	 * Get the status as String value.
	 * @return String
	 */
	public String getStatusString() {
		return getStatus().name();
	}

	/**
	 * Set the status as String value.
	 * @param status
	 */
	public void setStatusString(String status) {
		setStatus(EnumVictimStatus.valueOf(status.toUpperCase(Locale.ROOT)));
	}



	/**
	 * Victim Parcelable Constructor.
	 * Parcelable to get passed among activities through Intent (similar to Serialization).
	 * Attention to class members order an this use FIFO method.
	 */
	private Victim(Parcel in) {
		setId(in.readInt());
		setName(in.readString());
		setGender(in.readInt());
		setBirthDateString(in.readString());
		setAge(in.readInt());
		setStatusString(in.readString());
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
	 * Write Victim data to Parcelable. 
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(getId());
		dest.writeString(getName());
		dest.writeInt(getGender());
		dest.writeString(getBirthDateString());
		dest.writeInt(getAge());
		dest.writeString(getStatusString());
	}

	/**
	 * Public Victim Parcelable Constructor.
	 */
	public static final Parcelable.Creator<Victim> CREATOR = new Parcelable.Creator<Victim>() {
		/**
		 * Create a Victim from Parcel.
		 * @return Victim
		 */
		@Override
		public Victim createFromParcel(Parcel source) {
			// Use Parcelable constructor.
			return new Victim(source);
		}

		/**
		 * Create a Victim Array.
		 * @return Array
		 */
		@Override
		public Victim[] newArray(int size) {
			return new Victim[size];
		}
	};



	/**
	 * Compare Victim objects.
	 * @param another
	 */
	@Override
	public int compareTo(Victim another) {
		return this.getName().compareTo(another.getName());
	}

	/**
	 * Victim Object to String.
	 * @return String
	 */
	@Override
	public String toString() {
		try {
			return new StringBuilder("Victim [")
				.append("id=" + String.valueOf(getId()) + ", ")
				.append("name=" + getName() + ", ")
				.append("gender=" + String.valueOf(getGender()) + ", ")
				.append("birthDate=" + getBirthDateString() + ", ")
				.append("age=" + String.valueOf(getAge()) + ", ")
				.append("status=" + getStatusString())
				.append("]")
			.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	/**
	 * Victim Object to JSON.
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
	        data.put("status", getStatusString());
		    JSONObject object = new JSONObject();
		    object.put("Victim", data);
	        return object.toString();
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
        return null;
	}

}
