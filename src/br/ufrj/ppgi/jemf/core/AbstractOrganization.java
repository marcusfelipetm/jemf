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

import br.ufrj.ppgi.jemf.core.coordination.EnumLevel;

/**
 * @author Marcus Machado
 *
 */
public abstract class AbstractOrganization extends AbstractEntity {

	/**
	 * Organization Description.
	 */
	private String description;
	/**
	 * Organization Level.
	 */
	private EnumLevel level;



	/**
	 * Constructor.
	 */
	public AbstractOrganization() {
		super();
	}

	/**
	 * Constructor.
	 * @param description
	 * @param level
	 */
	public AbstractOrganization(String description, EnumLevel level) {
		super();
		this.description = description;
		this.level = level;
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
	 * @return the level
	 */
	public EnumLevel getLevel() {
		return this.level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(EnumLevel level) {
		this.level = level;
	}

}
