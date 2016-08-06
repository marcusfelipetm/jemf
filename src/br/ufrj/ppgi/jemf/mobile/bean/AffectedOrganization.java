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
import br.ufrj.ppgi.jemf.core.cooperation.AbstractAffectedOrganization;
import br.ufrj.ppgi.jemf.core.cooperation.EnumOrganizationStatus;
import br.ufrj.ppgi.jemf.core.coordination.EnumLevel;

/**
 * @author Marcus Machado
 *
 */
public class AffectedOrganization extends AbstractAffectedOrganization implements Parcelable, Comparable<AffectedOrganization> {

	/* Relationships - Future work */
	/**
	 * Location ID.
	 */
	private int idLocation;

	/**
	 * Witness ID.
	 */
	private int idWitness;

	/**
	 * Interest Point ID.
	 */
	private int idInterestPoint;



	/**
	 * Constructor.
	 */
	public AffectedOrganization() {
		super();
		this.setId(0);
	}

	/**
	 * Constructor.
	 * @param name
	 * @param description
	 * @param level
	 * @param status
	 */
	public AffectedOrganization(String name, String description, String level, String status) {
		super();
		this.setName(name);
		this.setDescription(description);
		this.setLevelString(level);
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
		setStatus(EnumOrganizationStatus.valueOf(status.toUpperCase(Locale.ROOT)));
	}



	/**
	 * AffectedOrganization Parcelable Constructor.
	 * Parcelable to get passed among activities through Intent (similar to Serialization).
	 * Attention to class members order an this use FIFO method.
	 */
	private AffectedOrganization(Parcel in) {
		setId(in.readInt());
		setName(in.readString());
		setDescription(in.readString());
		setLevelString(in.readString());
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
	 * Write AffectedOrganization data to Parcelable. 
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(getId());
		dest.writeString(getName());
		dest.writeString(getDescription());
		dest.writeString(getLevelString());
		dest.writeString(getStatusString());
	}

	/**
	 * Public AffectedOrganization Parcelable Constructor.
	 */
	public static final Parcelable.Creator<AffectedOrganization> CREATOR = new Parcelable.Creator<AffectedOrganization>() {
		/**
		 * Create a AffectedOrganization from Parcel.
		 * @return AffectedOrganization
		 */
		@Override
		public AffectedOrganization createFromParcel(Parcel source) {
			// Use Parcelable constructor.
			return new AffectedOrganization(source);
		}

		/**
		 * Create a AffectedOrganization Array.
		 * @return Array
		 */
		@Override
		public AffectedOrganization[] newArray(int size) {
			return new AffectedOrganization[size];
		}
	};



	/**
	 * Compare AffectedOrganization objects.
	 * @param another
	 */
	@Override
	public int compareTo(AffectedOrganization another) {
		return this.getName().compareTo(another.getName());
	}

	/**
	 * AffectedOrganization Object to String.
	 * @return String
	 */
	@Override
	public String toString() {
	    try {
			return new StringBuilder("AffectedOrganization [")
				.append("id=" + String.valueOf(getId()) + ", ")
				.append("name=" + getName() + ", ")
				.append("description=" + getDescription() + ", ")
				.append("level=" + getLevelString() + ", ")
				.append("status=" + getStatusString())
				.append("]") 
			.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
        return null;
	}

	/**
	 * AffectedOrganization Object to JSON.
	 * @return String
	 */
	public String toJSON() {
	    try {
		    JSONObject data = new JSONObject();
		    data.put("id", String.valueOf(getId()));
		    data.put("name", getName());
		    data.put("description", getDescription());
		    data.put("level", getLevelString());
		    data.put("status", getStatusString());
		    JSONObject object = new JSONObject();
		    object.put("AffectedOrganization", data);
	        return object.toString();
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
        return null;
	}

}
