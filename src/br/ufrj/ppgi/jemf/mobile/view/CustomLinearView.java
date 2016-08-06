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
package br.ufrj.ppgi.jemf.mobile.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.ufrj.ppgi.jemf.utils.CustomLogger;

/**
 * @author Marcus Machado
 *
 */
public class CustomLinearView extends LinearLayout {

	/**
	 * Private Constructor.
	 * @param context
	 */
	protected CustomLinearView(Context context) {
		super(context);
    	CustomLogger.getInstance().infoLog(CustomLinearView.class.getName(), "Class started.");
	}



	/**
	 * Get a Wrap LayoutParams.
	 * @return LayoutParams
	 */
	protected LayoutParams newWrapLayoutParams() {
		return new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
	}

	/**
	 * Get a Wrap LayoutParams.
	 * @return LayoutParams
	 */
	protected LayoutParams newMatchLayoutParams() {
		return new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
	}

	/**
	 * Get a Match/Wrap LayoutParams.
	 * @return LayoutParams
	 */
	protected LayoutParams newMatchWrapLayoutParams() {
		return new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
	}

	/**
	 * Get a Custom Field Separator.
	 * @param height
	 * @param color
	 * @return View
	 */
	protected View newLinearSeparator(int height, int color) {
		View separator = new View(getContext());
		separator.setPadding(0, 2, 0, 0);
		separator.setVisibility(VISIBLE);
		// Set a custom height for LayoutParam.
		separator.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, height));
		// Set a custom color.
		separator.setBackgroundColor(color);
		return separator;
	}

	/**
	 * Get a New Field Row.
	 * @param title
	 * @param child
	 * @param isCustomLayoutParams
	 * @return LinearLayout
	 */
	protected LinearLayout newLinearRow(String title, View child, boolean isCustomLayoutParams) {
		LinearLayout linearRow = new LinearLayout(getContext());
		// Set the custom Orientation.
		linearRow.setOrientation(LinearLayout.VERTICAL);
		// Set the custom Padding.
		linearRow.setPadding(15, 0, 15, 20);
		// Set the custom LayoutParam.
		linearRow.setLayoutParams(newMatchWrapLayoutParams());

		// Set a Title.
		TextView lblTitle = new TextView(getContext());
		lblTitle.setTextAppearance(getContext(), android.R.style.TextAppearance_Small);
		lblTitle.setTextColor(Color.BLACK);
		lblTitle.setTypeface(Typeface.DEFAULT_BOLD);
		lblTitle.setText(title);
		linearRow.addView(lblTitle, newMatchWrapLayoutParams());

		// Set a Separator.
		// Custom color example: int darkBlue = Color.argb(255, 25,25,112);
		linearRow.addView(newLinearSeparator(1, Color.BLACK));

		// Set a custom Child View with a custom layout.
		if (isCustomLayoutParams) {
			linearRow.addView(child, newMatchWrapLayoutParams());
		} else {
		// Or set a Default layout.
			linearRow.addView(child, newWrapLayoutParams());
		}
    	CustomLogger.getInstance().infoLog(CustomLinearView.class.getName(), "New Linear Row Created.");
		return linearRow;
	}

}
