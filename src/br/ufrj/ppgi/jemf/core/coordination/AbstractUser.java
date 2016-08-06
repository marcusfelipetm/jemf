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
package br.ufrj.ppgi.jemf.core.coordination;

import java.util.List;

import br.ufrj.ppgi.jemf.core.AbstractPerson;
import br.ufrj.ppgi.jemf.core.communication.AbstractPlan;
import br.ufrj.ppgi.jemf.core.communication.AbstractVictimReport;
import br.ufrj.ppgi.jemf.core.communication.AbstractWitnessReport;
import br.ufrj.ppgi.jemf.core.cooperation.AbstractVictim;
import br.ufrj.ppgi.jemf.core.cooperation.AbstractWitness;

/**
 * @author Marcus Machado
 *
 */
public abstract class AbstractUser extends AbstractPerson {

	/**
	 * User Login.
	 */
	private String login;
	/**
	 * User Passord.
	 */
	private String password;



	/* Relationships - Future work */
	private List<AbstractPlan> plans;
	private List<AbstractVictimReport> victimReports;
	private List<AbstractWitnessReport> witnessReports;
	private List<AbstractVictim> victims;
	private List<AbstractWitness> witnesses;



	/**
	 * Constructor.
	 */
	public AbstractUser() {

	}

	/**
	 * Constructor.
	 * @param login
	 * @param password
	 */
	public AbstractUser(String login, String password) {
		this.login = login;
		this.password = password;
	}



	/**
	 * @return the login
	 */
	public String getLogin() {
		return this.login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the plans
	 */
	public List<AbstractPlan> getPlans() {
		return this.plans;
	}

	/**
	 * @param plans the plans to set
	 */
	public void setPlans(List<AbstractPlan> plans) {
		this.plans = plans;
	}

	/**
	 * @return the victimReports
	 */
	public List<AbstractVictimReport> getVictimReports() {
		return this.victimReports;
	}

	/**
	 * @param victimReports the victimReports to set
	 */
	public void setVictimReports(List<AbstractVictimReport> victimReports) {
		this.victimReports = victimReports;
	}

	/**
	 * @return the witnessReports
	 */
	public List<AbstractWitnessReport> getWitnessReports() {
		return this.witnessReports;
	}

	/**
	 * @param witnessReports the witnessReports to set
	 */
	public void setWitnessReports(List<AbstractWitnessReport> witnessReports) {
		this.witnessReports = witnessReports;
	}

	/**
	 * @return the victims
	 */
	public List<AbstractVictim> getVictims() {
		return this.victims;
	}

	/**
	 * @param victims the victims to set
	 */
	public void setVictims(List<AbstractVictim> victims) {
		this.victims = victims;
	}

	/**
	 * @return the witnesses
	 */
	public List<AbstractWitness> getWitnesses() {
		return this.witnesses;
	}

	/**
	 * @param witnesses the witnesses to set
	 */
	public void setWitnesses(List<AbstractWitness> witnesses) {
		this.witnesses = witnesses;
	}

}
