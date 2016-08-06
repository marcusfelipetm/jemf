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

import br.ufrj.ppgi.jemf.core.coordination.EnumEmergencyType;

/**
 * @author Marcus Machado
 *
 */
public abstract class AbstractPlaceIdentifier extends AbstractBase {

	/**
	 * Place Identifier Identification Code.
	 */
	private int identification;
	/**
	 * Place Identifier Name.
	 */
	private String name;
	/**
	 * Place Identifier Description.
	 */
	private String description;
	/**
	 * Place Identifier Type.
	 */
	private EnumEmergencyType type;
	/**
	 * Place Identifier Symbol.
	 */
	private String symbol;



	/**
	 * Constructor.
	 */
	public AbstractPlaceIdentifier() {

	}

	/**
	 * Constructor.
	 * @param identification
	 * @param name
	 * @param description
	 * @param type
	 * @param symbol
	 */
	public AbstractPlaceIdentifier(int identification, String name, String description, EnumEmergencyType type, String symbol) {
		this.identification = identification;
		this.name = name;
		this.description = description;
		this.type = type;
		this.symbol = symbol;
	}



	/**
	 * @return the identifications
	 */
	public int getIdentification() {
		return this.identification;
	}

	/**
	 * @param identification the identification to set
	 */
	public void setIdentification(int identification) {
		this.identification = identification;
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
	 * @return the type
	 */
	public EnumEmergencyType getType() {
		return this.type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(EnumEmergencyType type) {
		this.type = type;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return this.symbol;
	}

	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
