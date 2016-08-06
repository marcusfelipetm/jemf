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

/**
 * @author Marcus Machado
 *
 */
public abstract class AbstractPlan extends AbstractSharedDocument {

	/**
	 * Plan Objective.
	 */
	private String objective;
	/**
	 * Plan Risk.
	 */
	private String risk;
	/**
	 * Plan Check List.
	 */
	private String checkList;



	/**
	 * Constructor.
	 */
	public AbstractPlan() {
		super();
	}

	/**
	 * Constructor.
	 * @param objective
	 * @param risk
	 * @param checkList
	 */
	public AbstractPlan(String objective, String risk, String checkList) {
		super();
		this.objective = objective;
		this.risk = risk;
		this.checkList = checkList;
	}



	/**
	 * @return the objective
	 */
	public String getObjective() {
		return this.objective;
	}

	/**
	 * @param objective the objective to set
	 */
	public void setObjective(String objective) {
		this.objective = objective;
	}

	/**
	 * @return the risk
	 */
	public String getRisk() {
		return this.risk;
	}

	/**
	 * @param risk the risk to set
	 */
	public void setRisk(String risk) {
		this.risk = risk;
	}

	/**
	 * @return the checkList
	 */
	public String getCheckList() {
		return this.checkList;
	}

	/**
	 * @param checkList the checkList to set
	 */
	public void setCheckList(String checkList) {
		this.checkList = checkList;
	}

}
