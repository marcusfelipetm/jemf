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

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;
import br.ufrj.ppgi.jemf.core.coordination.AbstractTeam;
import br.ufrj.ppgi.jemf.core.coordination.AbstractUser;

/**
 * @author Marcus Machado
 *
 */
public class Team extends AbstractTeam implements Parcelable, Comparable<Team> {

	/**
	 * Constructor.
	 */
	public Team(AbstractUser leader, ArrayList<AbstractUser> members) {
		super(leader, members);
		this.setId(0);
	}

	/**
	 * Constructor.
	 * @param name
	 * @param leader
	 * @param members
	 */
	public Team(String name, AbstractUser leader, ArrayList<AbstractUser> members) {
		super(leader, members);
		this.setName(name);
	}	



	/**
	 * Team Parcelable Constructor.
	 * Parcelable to get passed among activities through Intent (similar to Serialization).
	 * Attention to class members order an this use FIFO method.
	 */
	private Team(Parcel in) {
		super(extractedLeader(in), extractedMembers(in));
		setId(in.readInt());
		setName(in.readString());
	}

	private static AbstractUser extractedLeader(Parcel in) {
		return (AbstractUser) in.readValue(AbstractUser.class.getClassLoader());
	}

	@SuppressWarnings("unchecked")
	private static ArrayList<AbstractUser> extractedMembers(Parcel in) {
		return in.readArrayList(AbstractUser.class.getClassLoader());
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
	 * Write Team data to Parcelable. 
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(getLeader());
		dest.writeList(getMembers());
		dest.writeInt(getId());
		dest.writeString(getName());
	}

	/**
	 * Public Team Parcelable Constructor.
	 */
	public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>() {
		/**
		 * Create a Team from Parcel.
		 * @return Team
		 */
		@Override
		public Team createFromParcel(Parcel source) {
			// Use Parcelable constructor.
			return new Team(source);
		}

		/**
		 * Create a Team Array.
		 * @return Array
		 */
		@Override
		public Team[] newArray(int size) {
			return new Team[size];
		}
	};



	/**
	 * Compare Team objects.
	 * @param another
	 */
	@Override
	public int compareTo(Team another) {
		return this.getName().compareTo(another.getName());
	}

	/**
	 * Team Object to String.
	 * @return String
	 */
	@Override
	public String toString() {
		try {
			return new StringBuilder("Team [")
				.append("id=" + String.valueOf(getId()) + ", ")
				.append("name=" + getName())
				.append("]") 
			.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	/**
	 * Team Object to JSON.
	 * @return String
	 */
	public String toJSON() {
	    try {
		    JSONObject data = new JSONObject();
		    data.put("id", String.valueOf(getId()));
		    data.put("name", getName());
		    JSONObject object = new JSONObject();
		    object.put("Team", data);
	        return object.toString();
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
        return null;
	}

}
