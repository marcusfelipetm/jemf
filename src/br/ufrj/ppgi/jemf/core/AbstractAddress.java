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

/**
 * @author Marcus Machado
 *
 */
public abstract class AbstractAddress {

	/**
	 * Address Street.
	 */
	private String street;
	/**
	 * Address Number.
	 */
	private int number;
	/**
	 * Address Complement.
	 */
	private String complement;
	/**
	 * Address District.
	 */
	private String district;
	/**
	 * Address City.
	 */
	private String city;
	/**
	 * Address Region.
	 */
	private String region;
	/**
	 * Address Country.
	 */
	private String country;
	/**
	 * Address Postal Code.
	 */
	private int postalCode;



	/**
	 * Constructor.
	 */
	public AbstractAddress() {

	}

	/**
	 * Constructor.
	 * @param street
	 * @param number
	 * @param complement
	 * @param district
	 * @param city
	 * @param region
	 * @param country
	 * @param postalCode
	 */
	public AbstractAddress(String street, int number, String complement, String district, String city, String region, String country, int postalCode) {
		this.street = street;
		this.number = number;
		this.complement = complement;
		this.district = district;
		this.city = city;
		this.region = region;
		this.country = country;
		this.postalCode = postalCode;
	}



	/**
	 * @return the street
	 */
	public String getStreet() {
		return this.street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the complement
	 */
	public String getComplement() {
		return this.complement;
	}

	/**
	 * @param complement the complement to set
	 */
	public void setComplement(String complement) {
		this.complement = complement;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return this.district;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return this.region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return this.country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the postalCode
	 */
	public int getPostalCode() {
		return this.postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

}
