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

/**
 * @author Marcus Machado
 *
 */
public abstract class AbstractPerson extends AbstractEntity {

	/**
	 * Person Gender.
	 */
	private int gender; // 0 - Male; 1 - Female
	/**
	 * Person Birth Date.
	 */
	private Date birthDate;
	/**
	 * Person Age.
	 */
	private int age;



	/**
	 * Constructor.
	 */
	public AbstractPerson() {
		super();
	}

	/**
	 * Constructor.
	 * @param gender
	 * @param birthDate
	 * @param age
	 */
	public AbstractPerson(int gender, Date birthDate, int age) {
		super();
		this.gender = gender;
		this.birthDate = birthDate;
		this.age = age;
	}



	/**
	 * @return the gender
	 */
	public int getGender() {
		return this.gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return this.birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

}
