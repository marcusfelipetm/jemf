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

import br.ufrj.ppgi.jemf.core.communication.EnumContactStatus;
import br.ufrj.ppgi.jemf.core.communication.EnumLanguage;

/**
 * @author Marcus Machado
 *
 */
public abstract class AbstractContact extends AbstractBase {

	/**
	 * Contact Phone.
	 */
	private String phone;
	/**
	 * Contact Email.
	 */
	private String email;
	/**
	 * Contact Radio.
	 */
	private String radio;
	/**
	 * Contact Language.
	 */
	private EnumLanguage language;
	/**
	 * Contact Status.
	 */
	private EnumContactStatus status;



	/**
	 * Constructor. 
	 */
	public AbstractContact() {

	}

	/**
	 * Constructor.
	 * @param status
	 * @param phone
	 * @param email
	 * @param radio
	 * @param language
	 */
	public AbstractContact(EnumContactStatus status, String phone, String email, String radio, EnumLanguage language) {
		this.status = status;
		this.phone = phone;
		this.email = email;
		this.radio = radio;
		this.language = language;
	}



	/**
	 * @return the phone
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the radio
	 */
	public String getRadio() {
		return this.radio;
	}

	/**
	 * @param radio the radio to set
	 */
	public void setRadio(String radio) {
		this.radio = radio;
	}

	/**
	 * @return the language
	 */
	public EnumLanguage getLanguage() {
		return this.language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(EnumLanguage language) {
		this.language = language;
	}

	/**
	 * @return the status
	 */
	public EnumContactStatus getStatus() {
		return this.status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(EnumContactStatus status) {
		this.status = status;
	}

}
