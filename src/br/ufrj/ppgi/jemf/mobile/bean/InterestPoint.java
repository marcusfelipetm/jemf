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
import br.ufrj.ppgi.jemf.core.cooperation.AbstractInterestPoint;
import br.ufrj.ppgi.jemf.core.cooperation.EnumLocationStatus;
import br.ufrj.ppgi.jemf.utils.DateTimeFormat;

/**
 * @author Marcus Machado
 *
 */
public class InterestPoint extends AbstractInterestPoint implements Parcelable, Comparable<InterestPoint> {

	/**
	 * Constructor.
	 */
	public InterestPoint() {
		super();
		this.setId(0);
	}

	/**
	 * Constructor.
	 * @param status
	 * @param timeStamp
	 * @param interval
	 * @param name
	 * @throws ParseException 
	 */
	public InterestPoint(String status, String timeStamp, int interval, String name) {
		super();
		this.setStatusString(status);
		this.setTimeStampString(timeStamp);
		this.setInterval(interval);
		this.setName(name);
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
		setStatus(EnumLocationStatus.valueOf(status.toUpperCase(Locale.ROOT)));
	}

	/**
	 * Get the time stamp as String value.
	 * @return String
	 */
	public String getTimeStampString() {
		return DateTimeFormat.DateTimeToString(getTimeStamp());
	}

	/**
	 * Set the time stamp as String value.
	 * @param timeStamp
	 * @throws ParseException
	 */
	public void setTimeStampString(String timeStamp) {
		try {
			setTimeStamp(DateTimeFormat.StringToDateTime(timeStamp));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}



	/**
	 * InterestPoint Parcelable Constructor.
	 * Parcelable to get passed among activities through Intent (similar to Serialization).
	 * Attention to class members order an this use FIFO method.
	 */
	private InterestPoint(Parcel in) {
		setId(in.readInt());
		setStatusString(in.readString());
		setTimeStampString(in.readString());
		setInterval(in.readInt());
		setName(in.readString());
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
	 * Write InterestPoint data to Parcelable. 
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(getId());
		dest.writeString(getStatusString());
		dest.writeString(getTimeStampString());
		dest.writeInt(getInterval());
		dest.writeString(getName());
	}

	/**
	 * Public InterestPoint Parcelable Constructor.
	 */
	public static final Parcelable.Creator<InterestPoint> CREATOR = new Parcelable.Creator<InterestPoint>() {
		/**
		 * Create a InterestPoint from Parcel.
		 * @return InterestPoint
		 */
		@Override
		public InterestPoint createFromParcel(Parcel source) {
			// Use Parcelable constructor.
			return new InterestPoint(source);
		}

		/**
		 * Create a InterestPoint Array.
		 * @return Array
		 */
		@Override
		public InterestPoint[] newArray(int size) {
			return new InterestPoint[size];
		}
	};



	/**
	 * Compare InterestPoint objects.
	 * @param another
	 */
	@Override
	public int compareTo(InterestPoint another) {
		return this.getTimeStamp().compareTo(another.getTimeStamp());
	}

	/**
	 * InterestPoint Object to String.
	 * @return String
	 */
	@Override
	public String toString() {
		try {
			return new StringBuilder("InterestPoint [")
				.append("id=" + String.valueOf(getId()) + ", ")
				.append("status=" + getStatusString() + ", ")
				.append("timeStamp=" + getTimeStampString() + ", ")
				.append("interval=" + String.valueOf(getInterval()) + ", ")
				.append("name=" + getName())
				.append("]") 
			.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	/**
	 * InterestPoint Object to JSON.
	 * @return String
	 */
	public String toJSON() {
	    try {
		    JSONObject data = new JSONObject();
	        data.put("id", String.valueOf(getId()));
	        data.put("status", getStatusString());
	        data.put("timeStamp", getTimeStampString());
	        data.put("interval", String.valueOf(getInterval()));
	        data.put("name", getName());
		    JSONObject object = new JSONObject();
		    object.put("InterestPoint", data);
	        return object.toString();
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
        return null;
	}

}
