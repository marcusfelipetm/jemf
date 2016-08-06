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
import br.ufrj.ppgi.jemf.core.cooperation.AbstractHealthCareUnit;
import br.ufrj.ppgi.jemf.core.coordination.EnumLevel;

/**
 * @author Marcus Machado
 *
 */
public class HealthCareUnit extends AbstractHealthCareUnit implements Parcelable, Comparable<HealthCareUnit> {

	/**
	 * HealthCareUnit ID.
	 */
	private int id;



	/* Relationships - Future work */
	/**
	 * Location ID.
	 */
	private int idLocation;

	/**
	 * Commander ID.
	 */
	private int idCommander;

	/**
	 * Medical ID.
	 */
	private int idMedical;

	/**
	 * Interest Point ID.
	 */
	private int idInterestPoint;



	/**
	 * Constructor.
	 */
	public HealthCareUnit() {
		super();
		this.setId(0);
	}

	/**
	 * Constructor.
	 * @param name
	 * @param description
	 * @param level
	 * @param capacity
	 */
	public HealthCareUnit(String name, String description, String level, int capacity) {
		super();
		this.setName(name);
		this.setDescription(description);
		this.setLevelString(level);
		this.setCapacity(capacity);
	}	



	/**
	 * Get the ID.
	 * @return int
	 */
	@Override
	public int getId() {
		return this.id;
	}

	/**
	 * Set the ID.
	 * @param id
	 */
	@Override
	public void setId(int id) {
		this.id = id;
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
	 * Set the Commander ID.
	 * @param idCommander
	 */
	public void setCommanderId(int idCommander) {
		this.idCommander = idCommander;
	}

	/**
	 * Get the Commander ID.
	 * @return int
	 */
	public int getCommanderId() {
		return this.idCommander;
	}

	/**
	 * Set the Medical ID.
	 * @param idMedical
	 */
	public void setMedicalId(int idMedical) {
		this.idMedical = idMedical;
	}

	/**
	 * Get the Medical ID.
	 * @return int
	 */
	public int getMedicalId() {
		return this.idMedical;
	}

	/**
	 * Set the InterestPoint ID.
	 * @param idInterestPoint
	 */
	public void setInterestPointId(int idInterestPoint) {
		this.idInterestPoint = idInterestPoint;
	}

	/**
	 * Get the InterestPoint ID.
	 * @return int
	 */
	public int getInterestPointId() {
		return this.idInterestPoint;
	}

	/**
	 * Get the level as String value.
	 * @return String
	 */
	public String getLevelString() {
		return getLevel().name();
	}

	/**
	 * Set the level as String value.
	 * @param level
	 */
	public void setLevelString(String level) {
		setLevel(EnumLevel.valueOf(level.toUpperCase(Locale.ROOT)));
	}



	/**
	 * HealthCareUnit Parcelable Constructor.
	 * Parcelable to get passed among activities through Intent (similar to Serialization).
	 * Attention to class members order an this use FIFO method.
	 */
	private HealthCareUnit(Parcel in) {
		setId(in.readInt());
		setName(in.readString());
		setDescription(in.readString());
		setLevelString(in.readString());
		setCapacity(in.readInt());
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
	 * Write HealthCareUnit data to Parcelable. 
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(getId());
		dest.writeString(getName());
		dest.writeString(getDescription());
		dest.writeString(getLevelString());
		dest.writeInt(getCapacity());
	}

	/**
	 * Public HealthCareUnit Parcelable Constructor.
	 */
	public static final Parcelable.Creator<HealthCareUnit> CREATOR = new Parcelable.Creator<HealthCareUnit>() {
		/**
		 * Create a HealthCareUnit from Parcel.
		 * @return HealthCareUnit
		 */
		@Override
		public HealthCareUnit createFromParcel(Parcel source) {
			// Use Parcelable constructor.
			return new HealthCareUnit(source);
		}

		/**
		 * Create a HealthCareUnit Array.
		 * @return Array
		 */
		@Override
		public HealthCareUnit[] newArray(int size) {
			return new HealthCareUnit[size];
		}
	};



	/**
	 * Compare HealthCareUnit objects.
	 * @param another
	 */
	@Override
	public int compareTo(HealthCareUnit another) {
		return this.getName().compareTo(another.getName());
	}

	/**
	 * HealthCareUnit Object to String.
	 * @return String
	 */
	@Override
	public String toString() {
		try {
			return new StringBuilder("HealthCareUnit [")
				.append("id=" + String.valueOf(getId()) + ", ")
				.append("name=" + getName() + ", ")
				.append("description=" + getDescription() + ", ")
				.append("level=" + getLevelString() + ", ")
				.append("capacity=" + String.valueOf(getCapacity()))
				.append("]") 
			.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	/**
	 * HealthCareUnit Object to JSON.
	 * @return String
	 */
	public String toJSON() {
	    try {
		    JSONObject data = new JSONObject();
	        data.put("id", String.valueOf(getId()));
	        data.put("name", getName());
	        data.put("description", getDescription());
	        data.put("level", getLevelString());
	        data.put("capacity", String.valueOf(getCapacity()));
		    JSONObject object = new JSONObject();
		    object.put("HealthCareUnit", data);
	        return object.toString();
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
        return null;
	}

}
