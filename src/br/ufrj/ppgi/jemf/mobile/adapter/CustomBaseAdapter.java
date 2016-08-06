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
package br.ufrj.ppgi.jemf.mobile.adapter;

import java.util.List;

import br.ufrj.ppgi.jemf.utils.CustomLogger;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

/**
 * @author Marcus Machado
 *
 */
public abstract class CustomBaseAdapter extends BaseAdapter {

	/**
	 * Layout inflater.
	 */
	private LayoutInflater layoutInflater;
	/**
	 * List to hold data.
	 */
	private List<Object> list;



	/**
	 * Constructor.
	 * @param context
	 * @param list
	 */
	public CustomBaseAdapter(Context context, List<Object> list) {
    	CustomLogger.getInstance().infoLog(CustomBaseAdapter.class.getName(), "Class started.");
		this.setLayoutInflater(LayoutInflater.from(context));
		this.setList(list);
	}



	/**
	 * Get the Layout Inflater.
	 * @return the layoutInflater
	 */
	public LayoutInflater getLayoutInflater() {
		return this.layoutInflater;
	}

	/**
	 * Set the Layout Inflater.
	 * @param layoutInflater the layoutInflater to set
	 */
	private void setLayoutInflater(LayoutInflater layoutInflater) {
		this.layoutInflater = layoutInflater;
	}

	/**
	 * Get the List.
	 * @return the list
	 */
	public List<Object> getList() {
		return this.list;
	}

	/**
	 * Set the List.
	 * @param list the list to set
	 */
	private void setList(List<Object> list) {
		this.list = list;
	}

	/**
	 * Get List size.
	 */
	@Override
	public int getCount() {	
		return this.getList().size();
	}

	/**
	 * Get item by position.
	 */
	@Override
	public Object getItem(int position) {
		return this.getList().get(position);
	}

	/**
	 * Get item ID by position.
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}



	/**
	 * Class ViewHolder.
	 * Template Method - this methods will be implemented by its subclass.
	 * Hold data structure on view with field info.
	 * Inner Class for Optimization.
	 */
	public abstract static class ViewHolder {
	}

}
