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
import br.ufrj.ppgi.jemf.core.AbstractEmergency;
import br.ufrj.ppgi.jemf.core.coordination.EnumEmergencyType;
import br.ufrj.ppgi.jemf.core.coordination.EnumLevel;
import br.ufrj.ppgi.jemf.utils.CustomFormat;
import br.ufrj.ppgi.jemf.utils.DateTimeFormat;

/**
 * @author Marcus Machado
 *
 */
public class Emergency extends AbstractEmergency implements Parcelable, Comparable<Emergency> {

	/**
	 * Constructor.
	 */
	public Emergency() {
		super();
		this.setId(0);
	}

	/**
	 * Constructor.
	 * @param name
	 * @param activated
	 * @param level
	 * @param startDate
	 * @param endDate
	 * @param type
	 * @throws ParseException 
	 */
	public Emergency(String name, int activated, String level, String startDate, String endDate, String type) {
		super();
		this.setName(name);
		this.setActivatedInt(activated);
		this.setLevelString(level);
		this.setStartDateString(startDate);
		this.setEndDateString(endDate);
		this.setTypeString(type);
	}	



	/**
	 * Get the activated as int value.
	 * @return int
	 */
	public int getActivatedInt() {
		return CustomFormat.BoolToInt(getActivated());
	}

	/**
	 * Set the activated as int value.
	 * @param activated
	 */
	public void setActivatedInt(int activated) {
		setActivated(CustomFormat.IntToBool(activated));
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
	 * Get the type as String value.
	 * @return String
	 */
	public String getTypeString() {
		return getType().name();
	}

	/**
	 * Set the type as String value.
	 * @param type
	 */
	public void setTypeString(String type) {
		setType(EnumEmergencyType.valueOf(type.toUpperCase(Locale.ROOT)));
	}



	/**
	 * Emergency Parcelable Constructor.
	 * Parcelable to get passed among activities through Intent (similar to Serialization).
	 * Attention to class members order an this use FIFO method.
	 */
	private Emergency(Parcel in) {
		setId(in.readInt());
		setName(in.readString());
		setActivatedInt(in.readInt());
		setLevelString(in.readString());
		setStartDateString(in.readString());
		setEndDateString(in.readString());
		setTypeString(in.readString());
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
	 * Write Emergency data to Parcelable. 
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(getId());
		dest.writeString(getName());
		dest.writeInt(getActivatedInt());
		dest.writeString(getLevelString());
		dest.writeString(getStartDateString());
		dest.writeString(getEndDateString());
		dest.writeString(getTypeString());
	}

	/**
	 * Public Emergency Parcelable Constructor.
	 */
	public static final Parcelable.Creator<Emergency> CREATOR = new Parcelable.Creator<Emergency>() {
		/**
		 * Create a Emergency from Parcel.
		 * @return Emergency
		 */
		@Override
		public Emergency createFromParcel(Parcel source) {
			// Use Parcelable constructor.
			return new Emergency(source);
		}

		/**
		 * Create a Emergency Array.
		 * @return Array
		 */
		@Override
		public Emergency[] newArray(int size) {
			return new Emergency[size];
		}
	};



	/**
	 * Compare Emergency objects.
	 * @param another
	 */
	@Override
	public int compareTo(Emergency another) {
		if (another == null) return 1;
		return this.getName().compareTo(another.getName());
	}

	/**
	 * Emergency Object to String.
	 * @return String
	 */
	@Override
	public String toString() {
	    try {
		return new StringBuilder("Emergency [")
			.append("id=" + String.valueOf(getId()) + ", ")
			.append("name=" + getName() + ", ")
			.append("activated=" + String.valueOf(getActivated()) + ", ")
			.append("level=" + getLevelString() + ", ")
			.append("startDate=" + getStartDateString() + ", ")
			.append("endDate=" + getEndDateString() + ", ")
			.append("type=" + getTypeString())
			.append("]") 
		.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
        return null;
	}

	/**
	 * Emergency Object to JSON.
	 * @return String
	 */
	public String toJSON() {
	    try {
		    JSONObject data = new JSONObject();
	        data.put("id", String.valueOf(getId()));
	        data.put("name", getName());
	        data.put("activated", String.valueOf(getActivated()));
	        data.put("level", getLevelString());
	        data.put("startDate", getStartDateString());
	        data.put("endDate", getEndDateString());
	        data.put("type", getTypeString());
		    JSONObject object = new JSONObject();
		    object.put("Emergency", data);
	        return object.toString();
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
        return null;
	}

}
