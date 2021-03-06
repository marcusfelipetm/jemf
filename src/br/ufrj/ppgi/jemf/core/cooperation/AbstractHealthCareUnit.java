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
package br.ufrj.ppgi.jemf.core.cooperation;

import java.util.List;

import br.ufrj.ppgi.jemf.core.AbstractOrganization;

/**
 * @author Marcus Machado
 *
 */
public class AbstractHealthCareUnit extends AbstractOrganization {

	/**
	 * Health Care Unit Capacity.
	 */
	private int capacity;



	/* Relationships - Future work */
	private List<AbstractVictim> victims;



	/**
	 * Constructor.
	 */
	public AbstractHealthCareUnit() {
		super();
	}

	/**
	 * Constructor.
	 * @param capacity
	 */
	public AbstractHealthCareUnit(int capacity) {
		super();
		this.capacity = capacity;
	}



	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return this.capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the victims
	 */
	public List<AbstractVictim> getVictims() {
		return this.victims;
	}

	/**
	 * @param victims the victims to set
	 */
	public void setVictims(List<AbstractVictim> victims) {
		this.victims = victims;
	}

}
