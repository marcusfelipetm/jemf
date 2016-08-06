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

import br.ufrj.ppgi.jemf.core.AbstractLocation;

/**
 * @author Marcus Machado
 *
 */
public abstract class AbstractInterestPoint extends AbstractLocation {

	/**
	 * Interest Point Name.
	 */
	private String name;



	/* Relationships - Future work */
	private List<AbstractHealthCareUnit> healthCareUnits;
	private List<AbstractAffectedOrganization> affectedOrganizations;



	/**
	 * Constructor.
	 */
	public AbstractInterestPoint() {

	}

	/**
	 * Constructor.
	 * @param name
	 */
	public AbstractInterestPoint(String name) {
		this.name = name;
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
	 * @return the healthCareUnits
	 */
	public List<AbstractHealthCareUnit> getHealthCareUnits() {
		return this.healthCareUnits;
	}

	/**
	 * @param healthCareUnits the healthCareUnits to set
	 */
	public void setHealthCareUnits(List<AbstractHealthCareUnit> healthCareUnits) {
		this.healthCareUnits = healthCareUnits;
	}

	/**
	 * @return the affectedOrganizations
	 */
	public List<AbstractAffectedOrganization> getAffectedOrganizations() {
		return this.affectedOrganizations;
	}

	/**
	 * @param affectedOrganizations the affectedOrganizations to set
	 */
	public void setAffectedOrganizations(List<AbstractAffectedOrganization> affectedOrganizations) {
		this.affectedOrganizations = affectedOrganizations;
	}

}
