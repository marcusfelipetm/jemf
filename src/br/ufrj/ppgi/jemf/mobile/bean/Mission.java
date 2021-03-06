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
import br.ufrj.ppgi.jemf.core.coordination.AbstractMission;
import br.ufrj.ppgi.jemf.core.coordination.EnumPriority;
import br.ufrj.ppgi.jemf.core.coordination.EnumServiceStatus;
import br.ufrj.ppgi.jemf.utils.DateTimeFormat;

/**
 * @author Marcus Machado
 *
 */
public class Mission extends AbstractMission implements Parcelable, Comparable<Mission> {

	/* Relationships - Future work */
	/**
	 * Emergency ID.
	 */
	private int idEmergency;



	/**
	 * Constructor.
	 */
	public Mission() {
		super();
		this.setId(0);
	}

	/**
	 * Constructor.
	 * @param title
	 * @param description
	 * @param status
	 * @param startDate
	 * @param endDate
	 * @param priority
	 * @throws ParseException 
	 */
	public Mission(String title, String description, String status, String startDate, String endDate, String priority) {
		super();
		this.setTitle(title);
		this.setDescription(description);
		this.setStatusString(status);
		this.setStartDateString(startDate);
		this.setEndDateString(endDate);
		this.setPriorityString(priority);
	}



	/**
	 * Set the Emergency ID.
	 * @param idEmergency
	 */
	public void setEmergencyId(int idEmergency) {
		this.idEmergency = idEmergency;
	}

	/**
	 * Get the Emergency ID.
	 * @return int
	 */
	public int getEmergencyId() {
		return this.idEmergency;
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
		setStatus(EnumServiceStatus.valueOf(status.toUpperCase(Locale.ROOT)));
	}

	/**
	 * Get the start date as String value.
	 * @return String
	 */
	public String getStartDateString() {
		return DateTimeFormat.DateTimeToString(getStartDate());
	}

	/**
	 * Set the start date as String value.
	 * @param startDate
	 * @throws ParseException
	 */
	public void setStartDateString(String startDate) {
		try {
			setStartDate(DateTimeFormat.StringToDateTime(startDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}	

	/**
	 * Get the end date as String value.
	 * @return String
	 */
	public String getEndDateString() {
		return DateTimeFormat.DateTimeToString(getEndDate());
	}

	/**
	 * Set the end date as String value.
	 * @param endDate
	 * @throws ParseException
	 */
	public void setEndDateString(String endDate) {
		try {
			setEndDate(DateTimeFormat.StringToDateTime(endDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the priority as String value.
	 * @return String
	 */
	public String getPriorityString() {
		return getPriority().name();
	}

	/**
	 * Set the priority as String value.
	 * @param priority
	 */
	public void setPriorityString(String priority) {
		setPriority(EnumPriority.valueOf(priority.toUpperCase(Locale.ROOT)));
	}



	/**
	 * Mission Parcelable Constructor.
	 * Parcelable to get passed among activities through Intent (similar to Serialization).
	 * Attention to class members order an this use FIFO method.
	 */
	private Mission(Parcel in) {
		setId(in.readInt());
		setTitle(in.readString());
		setDescription(in.readString());
		setStatusString(in.readString());
		setStartDateString(in.readString());
		setEndDateString(in.readString());
		setPriorityString(in.readString());
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
	 * Write Mission data to Parcelable. 
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(getId());
		dest.writeString(getTitle());
		dest.writeString(getDescription());
		dest.writeString(getStatusString());
		dest.writeString(getStartDateString());
		dest.writeString(getEndDateString());
		dest.writeString(getPriorityString());
	}

	/**
	 * Public Mission Parcelable Constructor.
	 */
	public static final Parcelable.Creator<Mission> CREATOR = new Parcelable.Creator<Mission>() {
		/**
		 * Create a Mission from Parcel.
		 * @return Mission
		 */
		@Override
		public Mission createFromParcel(Parcel source) {
			// Use Parcelable constructor.
			return new Mission(source);
		}

		/**
		 * Create a Mission Array.
		 * @return Array
		 */
		@Override
		public Mission[] newArray(int size) {
			return new Mission[size];
		}
	};



	/**
	 * Compare Mission objects.
	 * @param another
	 */
	@Override
	public int compareTo(Mission another) {
		return this.getTitle().compareTo(another.getTitle());
	}

	/**
	 * Mission Object to String.
	 * @return String
	 */
	@Override
	public String toString() {
		try {
			return new StringBuilder("Mission [")
				.append("id=" + String.valueOf(getId()) + ", ")
				.append("title=" + getTitle() + ", ")
				.append("description=" + getDescription() + ", ")
				.append("status=" + getStatusString() + ", ")
				.append("startDate=" + getStartDateString() + ", ")
				.append("endDate=" + getEndDateString() + ", ")
				.append("priority=" + getPriorityString())
				.append("]") 
			.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	/**
	 * Mission Object to JSON.
	 * @return String
	 */
	public String toJSON() {
	    try {
		    JSONObject data = new JSONObject();
	        data.put("id", String.valueOf(getId()));
	        data.put("title", getTitle());
	        data.put("description", getDescription());
	        data.put("status", getStatusString());
	        data.put("startDate", getStartDateString());
	        data.put("endDate", getEndDateString());
	        data.put("priority", getPriorityString());
		    JSONObject object = new JSONObject();
		    object.put("Mission", data);
	        return object.toString();
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
        return null;
	}

}
