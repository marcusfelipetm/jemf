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
package br.ufrj.ppgi.jemf.core.coordination;

import java.util.ArrayList;

import br.ufrj.ppgi.jemf.core.AbstractBase;

/**
 * @author Marcus Machado
 *
 */
public abstract class AbstractTeam extends AbstractBase {

	/**
	 * Team Name.
	 */
	private String name;



	/* Relationships */
	private AbstractUser leader;
	private ArrayList<AbstractUser> members;



	/**
	 * Constructor.
	 */
	public AbstractTeam(AbstractUser leader, ArrayList<AbstractUser> members) {
		this.leader = leader;
		this.members = members;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the leader
	 */
	public AbstractUser getLeader() {
		return this.leader;
	}

	/**
	 * @param leader the leader to set
	 */
	public void setLeader(AbstractUser leader) {
		this.leader = leader;
	}

	/**
	 * @return the members
	 */
	public ArrayList<AbstractUser> getMembers() {
		return this.members;
	}

	/**
	 * @param members the members to set
	 */
	public void setMembers(ArrayList<AbstractUser> members) {
		this.members = members;
	}

}
