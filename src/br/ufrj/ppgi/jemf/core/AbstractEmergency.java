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

import java.util.Date;
import java.util.List;

import br.ufrj.ppgi.jemf.core.coordination.EnumEmergencyType;
import br.ufrj.ppgi.jemf.core.coordination.EnumLevel;

/**
 * @author Marcus Machado
 *
 */
public abstract class AbstractEmergency extends AbstractBase {

	/**
	 * Emergency Name.
	 */
	private String name;
	/**
	 * Emergency Activated.
	 */
	private Boolean activated;
	/**
	 * Emergency Level.
	 */
	private EnumLevel level;
	/**
	 * Emergency Start Date.
	 */
	private Date startDate;
	/**
	 * Emergency End Date.
	 */
	private Date endDate;
	/**
	 * Emergency Type.
	 */
	private EnumEmergencyType type;



	/* Relationships - Future work */
	private List<AbstractEntity> entities;
	private List<AbstractService> services;
	private AbstractLocation location;
	private List<AbstractInformation> informations;



	/**
	 * Constructor.
	 */
	public AbstractEmergency() {

	}

	/**
	 * Constructor.
	 * @param name
	 * @param activated
	 * @param level
	 * @param startDate
	 * @param endDate
	 * @param type
	 */
	public AbstractEmergency(String name, Boolean activated, EnumLevel level, Date startDate, Date endDate, EnumEmergencyType type) {
		this.setName(name);
		this.setActivated(activated);
		this.setLevel(level);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setType(type);
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
	 * @return the activated
	 */
	public Boolean getActivated() {
		return this.activated;
	}

	/**
	 * @param activated the activated to set
	 */
	public void setActivated(Boolean activated) {
		this.activated = activated;
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

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	 * @return the entities
	 */
	public List<AbstractEntity> getEntities() {
		return this.entities;
	}

	/**
	 * @param entities the entities to set
	 */
	public void setEntities(List<AbstractEntity> entities) {
		this.entities = entities;
	}

	/**
	 * @return the service
	 */
	public List<AbstractService> getServices() {
		return this.services;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(List<AbstractService> services) {
		this.services = services;
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
	 * @return the informations
	 */
	public List<AbstractInformation> getInformations() {
		return this.informations;
	}

	/**
	 * @param informations the informations to set
	 */
	public void setInformations(List<AbstractInformation> informations) {
		this.informations = informations;
	}

	/**
	 * Abstract Emergency to String.
	 */
	@Override
	public String toString() {
		return (new StringBuilder("Emergency - Name: ")).append(this.name)
				.append(", Activated: ").append(this.activated)
				.append(", Type: ").append(this.type.name())
				.append(", StartDate: ").append(this.startDate)
				.append(", EndDate: ").append(this.endDate)
				.append(", Level: ").append(this.level.name())
				.toString();
	}

}
