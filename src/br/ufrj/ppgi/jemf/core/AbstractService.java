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

import br.ufrj.ppgi.jemf.core.coordination.EnumPriority;
import br.ufrj.ppgi.jemf.core.coordination.EnumServiceStatus;

/**
 * @author Marcus Machado
 *
 */
public abstract class AbstractService extends AbstractBase {

	/**
	 * Service Title.
	 */
	private String title;
	/**
	 * Service Description.
	 */
	private String description;
	/**
	 * Service Status.
	 */
	private EnumServiceStatus status;
	/**
	 * Service Start Date.
	 */
	private Date startDate;
	/**
	 * Service End Date.
	 */
	private Date endDate;
	/**
	 * Service Priority.
	 */
	private EnumPriority priority;



	/* Relationships - Future work */
	private List<AbstractResource> resourcesRequired;
	private AbstractLocation location;
	private List<AbstractInformation> informations;



	/**
	 * Constructor.
	 */
	public AbstractService() {

	}

	/**
	 * Constructor.
	 * @param title
	 * @param description
	 * @param status
	 * @param startDate
	 * @param endDate
	 * @param priority
	 */
	public AbstractService(String title, String description, EnumServiceStatus status, Date startDate, Date endDate, EnumPriority priority) {
		this.title = title;
		this.description = description;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority; 
	}



	/**
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the status
	 */
	public EnumServiceStatus getStatus() {
		return this.status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(EnumServiceStatus status) {
		this.status = status;
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
	 * @return the priority
	 */
	public EnumPriority getPriority() {
		return this.priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(EnumPriority priority) {
		this.priority = priority;
	}

	/**
	 * @return the resource
	 */
	public List<AbstractResource> getResources() {
		return this.resourcesRequired;
	}

	/**
	 * @param resources the resources to set
	 */
	public void setResources(List<AbstractResource> resources) {
		this.resourcesRequired = resources;
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
	 * Abstract Service to String.
	 */
	@Override
	public String toString() {
		return (new StringBuilder("Service - Title: ")).append(this.title)
				.append(", Description: ").append(this.description)
				.append(", Status: ").append(this.status.name())
				.append(", StartDate: ").append(this.startDate)
				.append(", endDate: ").append(this.endDate)
				.append(", Priority: ").append(this.priority.name())
				.toString();
	}

}
