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

import java.util.List;

/**
 * @author Marcus Machado
 *
 */
public abstract class AbstractEntity extends AbstractBase {

	/**
	 * Entity Name.
	 */
	private String name;



	/* Relationships - Future work */
	private List<AbstractService> servicesRequired;
	private List<AbstractService> servicesProvided;
	private List<AbstractInformation> informations;
	private List<AbstractInformation> infoSendReceive;
	private AbstractLocation location;
	private AbstractContact contact;
	private List<AbstractResource> resourcesOwner;



	/**
	 * Constructor.
	 */
	public AbstractEntity() {

	}

	/**
	 * Constructor.
	 * @param name
	 */
	public AbstractEntity(String name) {
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
	 * @return the servicesRequired
	 */
	public List<AbstractService> getServicesRequired() {
		return this.servicesRequired;
	}

	/**
	 * @param servicesRequired the servicesRequired to set
	 */
	public void setServicesRequired(List<AbstractService> servicesRequired) {
		this.servicesRequired = servicesRequired;
	}

	/**
	 * @return the servicesProvided
	 */
	public List<AbstractService> getServicesProvided() {
		return this.servicesProvided;
	}

	/**
	 * @param servicesProvided the servicesProvided to set
	 */
	public void setServicesProvided(List<AbstractService> servicesProvided) {
		this.servicesProvided = servicesProvided;
	}

	/**
	 * @return the information
	 */
	public List<AbstractInformation> getInformation() {
		return this.informations;
	}

	/**
	 * @param informations the informations to set
	 */
	public void setInformation(List<AbstractInformation> informations) {
		this.informations = informations;
	}

	/**
	 * @return the infoSendReceive
	 */
	public List<AbstractInformation> getInfoSendReceive() {
		return this.infoSendReceive;
	}

	/**
	 * @param infoSendReceive the infoSendReceive to set
	 */
	public void setInfoSendReceive(List<AbstractInformation> infoSendReceive) {
		this.infoSendReceive = infoSendReceive;
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
	 * @return the contact
	 */
	public AbstractContact getContact() {
		return this.contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(AbstractContact contact) {
		this.contact = contact;
	}

	/**
	 * @return the resourcesOwner
	 */
	public List<AbstractResource> getResourcesOwner() {
		return this.resourcesOwner;
	}

	/**
	 * @param resourcesOwner the resourcesOwner to set
	 */
	public void setResource(List<AbstractResource> resourcesOwner) {
		this.resourcesOwner = resourcesOwner;
	}

}
