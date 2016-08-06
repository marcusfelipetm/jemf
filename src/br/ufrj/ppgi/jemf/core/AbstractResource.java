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
package br.ufrj.ppgi.jemf.core;

import br.ufrj.ppgi.jemf.core.coordination.EnumResourceStatus;

/**
 * @author Marcus Machado
 *
 */
public abstract class AbstractResource extends AbstractBase {

	/**
	 * Equipment Name.
	 */
	private String name;
	/**
	 * Equipment Status.
	 */
	private EnumResourceStatus status;
	/**
	 * Equipment Description.
	 */
	private String description;



	/* Relationships - Future work */
	private AbstractLocation location;



	/**
	 * Constructor.
	 */
	public AbstractResource() {

	}

	/**
	 * Constructor.
	 * @param name
	 * @param status
	 * @param description
	 */
	public AbstractResource(String name, EnumResourceStatus status, String description) {
		this.name = name;
		this.status = status;
		this.description = description;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the status
	 */
	public EnumResourceStatus getStatus() {
		return this.status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(EnumResourceStatus status) {
		this.status = status;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the location
	 */
	public AbstractLocation getLocation() {
		return this.location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(AbstractLocation location) {
		this.location = location;
	}

	/**
	 * Abstract Resource to String.
	 */
	@Override
	public String toString() {
		return (new StringBuilder("Resource - Name: ")).append(this.name)
				.append(", Status: ").append(this.status.name())
				.append(", Description: ").append(this.description)
				.toString();
	}

}
