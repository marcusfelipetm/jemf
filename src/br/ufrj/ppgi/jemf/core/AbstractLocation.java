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

import br.ufrj.ppgi.jemf.core.cooperation.EnumLocationStatus;

/**
 * @author Marcus Machado
 *
 */
public abstract class AbstractLocation extends AbstractBase {

	/**
	 * Location Status.
	 */
	private EnumLocationStatus status;
	/**
	 * Location Time Stamp.
	 */
	private Date timeStamp;
	/**
	 * Location Time Interval.
	 */
	private int interval;



	/* Relationships - Future work */
	private List<AbstractAddress> addresses;
	private List<AbstractPosition> positions;
	private List<AbstractPlaceIdentifier> placeIdentifiers;



	/**
	 * Constructor.
	 */
	public AbstractLocation() {

	}

	/**
	 * Constructor.
	 * @param status
	 * @param timeStamp
	 * @param interval
	 */
	public AbstractLocation(EnumLocationStatus status, Date timeStamp, int interval) {
		this.status = status;
		this.timeStamp = timeStamp;
		this.interval = interval;
	}



	/**
	 * @return the status
	 */
	public EnumLocationStatus getStatus() {
		return this.status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(EnumLocationStatus status) {
		this.status = status;
	}

	/**
	 * @return the timeStamp
	 */
	public Date getTimeStamp() {
		return this.timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the interval
	 */
	public int getInterval() {
		return this.interval;
	}

	/**
	 * @param interval the interval to set
	 */
	public void setInterval(int interval) {
		this.interval = interval;
	}

	/**
	 * @return the addresses
	 */
	public List<AbstractAddress> getAddresses() {
		return this.addresses;
	}

	/**
	 * @param addresses the addresses to set
	 */
	public void setAddresses(List<AbstractAddress> addresses) {
		this.addresses = addresses;
	}

	/**
	 * @return the positions
	 */
	public List<AbstractPosition> getPositions() {
		return this.positions;
	}

	/**
	 * @param positions the positions to set
	 */
	public void setPositions(List<AbstractPosition> positions) {
		this.positions = positions;
	}

	/**
	 * @return the placeIdentifiers
	 */
	public List<AbstractPlaceIdentifier> getPlaceIdentifiers() {
		return this.placeIdentifiers;
	}

	/**
	 * @param placeIdentifiers the placeIdentifiers to set
	 */
	public void setPlaceIdentifiers(List<AbstractPlaceIdentifier> placeIdentifiers) {
		this.placeIdentifiers = placeIdentifiers;
	}

}
