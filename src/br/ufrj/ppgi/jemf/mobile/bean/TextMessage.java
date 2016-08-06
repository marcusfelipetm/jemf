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
import br.ufrj.ppgi.jemf.core.communication.AbstractTextMessage;
import br.ufrj.ppgi.jemf.core.coordination.EnumPriority;
import br.ufrj.ppgi.jemf.utils.DateTimeFormat;

/**
 * @author Marcus Machado
 *
 */
public class TextMessage extends AbstractTextMessage implements Parcelable, Comparable<TextMessage> {

	/* Relationships - Future work */
	/**
	 * Emergency ID.
	 */
	private int idEmergency;



	/**
	 * Constructor.
	 */
	public TextMessage() {
		super();
		this.setId(0);
	}

	/**
	 * Constructor.
	 * @param creationDate
	 * @param lastModificationDate
	 * @param priority
	 * @param subject
	 * @param content
	 * @throws ParseException 
	 */
	public TextMessage(String creationDate, String lastModificationDate, String priority,
						String subject, String content) {
		super();
		this.setCreationDateString(creationDate);
		this.setLastModificationDateString(lastModificationDate);
		this.setPriorityString(priority);
		this.setSubject(subject);
		this.setContent(content);
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
	 * Get the creation date as String value.
	 * @return String
	 */
	public String getCreationDateString() {
		return DateTimeFormat.DateTimeToString(getCreationDate());
	}

	/**
	 * Set the creation date as String value.
	 * @param creationDate
	 * @throws ParseException
	 */
	public void setCreationDateString(String creationDate) {
		try {
			setCreationDate(DateTimeFormat.StringToDateTime(creationDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the last modification date as String value.
	 * @return String
	 */
	public String getLastModificationDateString() {
		return DateTimeFormat.DateTimeToString(getLastModificationDate());
	}

	/**
	 * Set the last modification date as String value.
	 * @param lastModificationDate
	 * @throws ParseException
	 */
	public void setLastModificationDateString(String lastModificationDate) {
		try {
			setLastModificationDate(DateTimeFormat.StringToDateTime(lastModificationDate));
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
	 * TextMessage Parcelable Constructor.
	 * Parcelable to get passed among activities through Intent (similar to Serialization).
	 * Attention to class members order an this use FIFO method.
	 */
	private TextMessage(Parcel in) {
		setId(in.readInt());
		setCreationDateString(in.readString());
		setLastModificationDateString(in.readString());
		setPriorityString(in.readString());
		setSubject(in.readString());
		setContent(in.readString());
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
	 * Write TextMessage data to Parcelable. 
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(getId());
		dest.writeString(getCreationDateString());
		dest.writeString(getLastModificationDateString());
		dest.writeString(getPriorityString());
		dest.writeString(getSubject());
		dest.writeString(getContent());
	}

	/**
	 * Public TextMessage Parcelable Constructor.
	 */
	public static final Parcelable.Creator<TextMessage> CREATOR = new Parcelable.Creator<TextMessage>() {
		/**
		 * Create a TextMessage from Parcel.
		 * @return TextMessage
		 */
		@Override
		public TextMessage createFromParcel(Parcel source) {
			// Use Parcelable constructor.
			return new TextMessage(source);
		}

		/**
		 * Create a TextMessage Array.
		 * @return Array
		 */
		@Override
		public TextMessage[] newArray(int size) {
			return new TextMessage[size];
		}
	};



	/**
	 * Compare TextMessage objects.
	 * @param another
	 */
	@Override
	public int compareTo(TextMessage another) {
		return this.getContent().compareTo(another.getContent());
	}

	/**
	 * TextMessage Object to String.
	 * @return String
	 */
	@Override
	public String toString() {
		try {
			return new StringBuilder("TextMessage [")
				.append("id=" + String.valueOf(getId()) + ", ")
				.append("creationDate=" + getCreationDateString() + ", ")
				.append("lastModificationDate=" + getLastModificationDateString() + ", ")
				.append("priority=" + getPriorityString() + ", ")
				.append("subject=" + getSubject() + ", ")
				.append("content=" + getContent())
				.append("]")
			.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	/**
	 * TextMessage Object to JSON.
	 * @return String
	 */
	public String toJSON() {
	    try {
		    JSONObject data = new JSONObject();
	        data.put("id", String.valueOf(getId()));
	        data.put("creationDate", getCreationDateString());
	        data.put("lastModificationDate", getLastModificationDateString());
	        data.put("priority", getPriorityString());
	        data.put("subject", getSubject());
	        data.put("content", getContent());
		    JSONObject object = new JSONObject();
		    object.put("TextMessage", data);
	        return object.toString();
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
        return null;
	}

}
