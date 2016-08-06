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

import br.ufrj.ppgi.jemf.core.AbstractService;

/**
 * @author Marcus Machado
 *
 */
public abstract class AbstractMission extends AbstractService {

	/* Relationships - Future work */
	private List<AbstractTeam> teams;
	private List<? extends AbstractTask> tasks;



	/**
	 * @return the teams
	 */
	public List<AbstractTeam> getTeams() {
		return this.teams;
	}

	/**
	 * @param teams the teams to set
	 */
	public void setTeams(List<AbstractTeam> teams) {
		this.teams = teams;
	}

	/**
	 * @return the tasks
	 */
	public List<? extends AbstractTask> getTasks() {
		return this.tasks;
	}

	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(List<? extends AbstractTask> tasks) {
		this.tasks = tasks;
	}

}
