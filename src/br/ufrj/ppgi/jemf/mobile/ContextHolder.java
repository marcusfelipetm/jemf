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
package br.ufrj.ppgi.jemf.mobile;

import br.ufrj.ppgi.jemf.utils.Constraint;
import android.content.Context;

/**
 * @author Marcus Machado
 *
 */
public class ContextHolder {

	/**
	 * Parent context, holds application context.
	 */
	private static Context parentContext = null;



	/**
	 * Constructor.
	 * This should be private to prevent direct instantiation.
	 */
	private ContextHolder() {
	}



	/**
	 * Get parent context.
	 * @return the context
	 */
	public static Context getContext() {
		if (ContextHolder.parentContext == null) {
			throw new IllegalArgumentException("No context held.");
		}
		return ContextHolder.parentContext;
	}

	/**
	 * Set parent context.
	 * @param context the context to set
	 */
	public static void setContext(Context context) {
    	Constraint.checkNullParam(context);
		ContextHolder.parentContext = context.getApplicationContext();
	}

}
