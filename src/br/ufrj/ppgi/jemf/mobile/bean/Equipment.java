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
import br.ufrj.ppgi.jemf.core.coordination.AbstractEquipment;
import br.ufrj.ppgi.jemf.core.coordination.EnumResourceStatus;

/**
 * @author Marcus Machado
 *
 */
public class Equipment extends AbstractEquipment implements Parcelable, Comparable<Equipment> {

	/**
	 * Constructor.
	 */
	public Equipment() {
		super();
		this.setId(0);
	}
	
	/**
	 * Constructor.
	 * @param name
	 * @param status
	 * @param description
	 */
	public Equipment(String name, String status, String description) {
		super();
		this.setName(name);
		this.setStatusString(status);
		this.setDescription(description);
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
		setStatus(EnumResourceStatus.valueOf(status.toUpperCase(Locale.ROOT)));
	}



	/**
	 * Equipment Parcelable Constructor.
	 * Parcelable to get passed among activities through Intent (similar to Serialization).
	 * Attention to class members order an this use FIFO method.
	 */
	private Equipment(Parcel in) {
		setId(in.readInt());
		setName(in.readString());
		setStatusString(in.readString());
		setDescription(in.readString());
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
	 * Write Equipment data to Parcelable. 
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(getId());
		dest.writeString(getName());
		dest.writeString(getStatusString());
		dest.writeString(getDescription());
	}

	/**
	 * Public Equipment Parcelable Constructor.
	 */
	public static final Parcelable.Creator<Equipment> CREATOR = new Parcelable.Creator<Equipment>() {
		/**
		 * Create a Equipment from Parcel.
		 * @return Equipment
		 */
		@Override
		public Equipment createFromParcel(Parcel source) {
			// Use Parcelable constructor.
			return new Equipment(source);
		}

		/**
		 * Create a Equipment Array.
		 * @return Array
		 */
		@Override
		public Equipment[] newArray(int size) {
			return new Equipment[size];
		}
	};



	/**
	 * Compare Equipment objects.
	 * @param another
	 */
	@Override
	public int compareTo(Equipment another) {
		return this.getName().compareTo(another.getName());
	}

	/**
	 * Equipment Object to String.
	 * @return String
	 */
	@Override
	public String toString() {
		try {
			return new StringBuilder("Equipment [")
				.append("id=" + String.valueOf(getId()) + ", ")
				.append("name=" + getName() + ", ")
				.append("status=" + getStatusString() + ", ")
				.append("description=" + getDescription())
				.append("]") 
			.toString();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return null;
	}

	/**
	 * Equipment Object to JSON.
	 * @return String
	 */
	public String toJSON() {
	    try {
		    JSONObject data = new JSONObject();
	        data.put("id", String.valueOf(getId()));
	        data.put("name", getName());
	        data.put("status", getStatusString());
	        data.put("description", getDescription());
		    JSONObject object = new JSONObject();
		    object.put("Equipment", data);
	        return object.toString();
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
        return null;
	}

}
