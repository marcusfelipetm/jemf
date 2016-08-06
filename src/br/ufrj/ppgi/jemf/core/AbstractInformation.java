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

/**
 * @author Marcus Machado
 *
 */
public abstract class AbstractInformation extends AbstractBase {

	/**
	 * Information Creation date.
	 */
	private Date creationDate;
	/**
	 * Information Last Modification Date.
	 */
	private Date lastModificationDate;
	/**
	 * Information Priority.
	 */
	private EnumPriority priority;



	/* Relationships - Future work */
	private List<AbstractContact> contacts;



	/**
	 * Constructor.
	 */
	public AbstractInformation() {

	}

	/**
	 * Constructor.
	 * @param creationDate
	 * @param lastModificationDate
	 * @param priority
	 */
	public AbstractInformation(Date creationDate, Date lastModificationDate, EnumPriority priority) {
		this.creationDate = creationDate;
		this.lastModificationDate = lastModificationDate;
		this.priority = priority;
	}



	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return this.creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the lastModificationDate
	 */
	public Date getLastModificationDate() {
		return this.lastModificationDate;
	}

	/**
	 * @param lastModificationDate the lastModificationDate to set
	 */
	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
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
	 * @return the contacts
	 */
	public List<AbstractContact> getContacts() {
		return this.contacts;
	}

	/**
	 * @param contacts the contacts to set
	 */
	public void setContacts(List<AbstractContact> contacts) {
		this.contacts = contacts;
	}

}
