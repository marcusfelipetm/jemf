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
package br.ufrj.ppgi.jemf.core.communication;

import br.ufrj.ppgi.jemf.core.AbstractInformation;

/**
 * @author Marcus Machado
 *
 */
public abstract class AbstractSharedDocument extends AbstractInformation {

	/**
	 * Shared Document Description.
	 */
	private String description;
	/**
	 * Shared Document Observation.
	 */
	private String observation;



	/**
	 * Constructor.
	 */
	public AbstractSharedDocument() {
		super();
	}

	/**
	 * Constructor.
	 * @param description
	 * @param observation
	 */
	public AbstractSharedDocument(String description, String observation) {
		super();
		this.description = description;
		this.observation = observation;
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
	 * @return the observation
	 */
	public String getObservation() {
		return this.observation;
	}

	/**
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

}
