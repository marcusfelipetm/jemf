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
package br.ufrj.ppgi.jemf.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import br.ufrj.ppgi.jemf.mobile.CustomBuildConfig;

/**
 * @author Marcus Machado
 *
 */
public class CustomLogger {

	/**
	 * A string constant to use in calls to the "log" methods. 
	 * Its value is often given by the name of the class, as this will allow you to easily determine
	 * where log methods are coming from when you analyze your logcat output.
	 */
	public static final String LOG_TAG = "JEMF";
	/**
	 * Separator.
	 */
	public static final String SEPARATOR = ":::";
	/**
	 * Line break.
	 */
	public static final String LINE_BREAK = "\n";
	/**
	 * Save log file on external directory.
	 */
	private static boolean isToSaveExternal;
	/**
	 * Log file.
	 */
	private static String logfile;
	/**
	 * Log file name.
	 */
	private static String fileName = "jemf_log";
	/**
	 * Log message type.
	 */
	private static enum EnumMessageType {ERROR, INFORMATION, WARNING};
	/**
	 * Log message.
	 */
	private static String log;
	/**
	 * Parent Context.
	 */
	private static Context parentContext;



	/**
	 * Create CustomLogger instance.
	 * Singleton - This method ensures that there exists only one CustomLogger instance at any time.
	 * @param context
	 */
    public static CustomLogger newInstance(Context context) {
		// Checking context param.
    	Constraint.checkNullParam(context);
    	parentContext = context;
    	return SingletonHolder.INSTANCE;
    }

	/**
	 * Get CustomLogger instance.
	 * Use newInstance() method to initialize log, then get logger instance with getInstance() method.
	 */
    public static CustomLogger getInstance() {
		// Checking context variable, if null then logger was not initialized properly.
    	Constraint.checkNullParam(parentContext);
    	return SingletonHolder.INSTANCE;
    }

    /**
     * Singleton - Until we need an instance, the SingletonHolder class will not be initialized until required.
     */
	private static class SingletonHolder {
		private static final CustomLogger INSTANCE = new CustomLogger();
	}



	/**
	 * Constructor.
	 */
	private CustomLogger() {
	}



	/**
	 * Get value if is to save external directory. 
	 * @return the isToSaveExternal
	 */
	public static boolean isToSaveExternal() {
		return isToSaveExternal;
	}

	/**
	 * Set value if is to save external directory. 
	 * @param isToSaveExternal the isToSaveExternal to set
	 */
	private static void setToSaveExternal(boolean isToSaveExternal) {
		CustomLogger.isToSaveExternal = isToSaveExternal;
	}



	/**
	 * Log initializer.
	 * @param isToSaveExternal
	 */
	public void startLog(boolean isToSaveExternal) {
		try {
			if (CustomBuildConfig.VERBOSE) {
				infoLog(CustomLogger.class.getName(), SEPARATOR+SEPARATOR+" LOG STARTED. "+SEPARATOR+SEPARATOR);
				setToSaveExternal(isToSaveExternal);
				// File name customization.
				logfile = fileName +" "+ StringFormat.slashToUnderscore(DateTimeFormat.DateToString(new Date(System.currentTimeMillis())));
				logfile = logfile.replace(" ", "_");
				logfile = logfile.replace(":", "_");
				// Get Device info
				PackageManager pm = parentContext.getPackageManager();
				PackageInfo pinfo = pm.getPackageInfo(parentContext.getPackageName(), 0);
				ApplicationInfo ai;
				pinfo = pm.getPackageInfo(parentContext.getPackageName(), 0);
				ai = pm.getApplicationInfo(pinfo.packageName, 0);
				String appName = (String)pm.getApplicationLabel(ai);
				String versionName = pinfo.versionName;
				String versionCode = String.valueOf(pinfo.versionCode);
				infoLog(CustomLogger.class.getName(), "Application: " + appName);
				infoLog(CustomLogger.class.getName(), "Package: " + pinfo.packageName);
				infoLog(CustomLogger.class.getName(), "Version Code: " + versionCode);
				infoLog(CustomLogger.class.getName(), "Version Name: " + versionName);
				infoLog(CustomLogger.class.getName(), "Android Version: " + Device.androidVersion());
				infoLog(CustomLogger.class.getName(), "Android Codename: " + Device.androidCodename());
				infoLog(CustomLogger.class.getName(), "Kernel Version: " + Device.kernelVersion());
				infoLog(CustomLogger.class.getName(), "Device Manufacturer: " + Device.deviceManufacturer());
				infoLog(CustomLogger.class.getName(), "Device Model: " + Device.deviceModel());
				infoLog(CustomLogger.class.getName(), "Device Name: " + Device.deviceName());
				infoLog(CustomLogger.class.getName(), "Device Product: " + Device.deviceProduct());
				infoLog(CustomLogger.class.getName(), "Device Brand: " + Device.deviceBrand());
			}
		} catch (Exception e) {
			Log.i(LOG_TAG, SEPARATOR+SEPARATOR+" Log failed: "+ logfile +" "+SEPARATOR+SEPARATOR);
		}
	}



	/**
	 * Log Error.
	 * @param message
	 */
	public void errorLog (String className, String message) {
		errorLog(className, message, null);
	}

	/**
	 * Log Error.
	 * @param message
	 * @param e
	 */
	public void errorLog (String className, String message, Throwable e) {
		saveLog(className, message, e, EnumMessageType.ERROR);
		if (CustomBuildConfig.VERBOSE)
			Log.e(LOG_TAG, className +SEPARATOR+ message, e);
	}

	/**
	 * Log Info.
	 * @param message
	 */
	public void infoLog (String className, String message) {
		infoLog(className, message, null);
	}

	/**
	 * Log Info.
	 * @param message
	 * @param e
	 */
	public void infoLog (String className, String message, Throwable e) {
		saveLog(className, message, e, EnumMessageType.INFORMATION);
		if (CustomBuildConfig.VERBOSE)
			Log.i(LOG_TAG, className +SEPARATOR+ message, e);
	}

	/**
	 * Log Warning.
	 * @param message
	 */
	public void warningLog (String className, String message) {
		warningLog(className, message, null);
	}

	/**
	 * Log Warning.
	 * @param message
	 * @param e
	 */
	public void warningLog (String className, String message, Throwable e) {
		saveLog(className, message, e, EnumMessageType.WARNING);
		if (CustomBuildConfig.VERBOSE)
			Log.w(LOG_TAG, className +SEPARATOR+ message, e);
	}



	/**
	 * Save Log message into file.
	 * @param message
	 * @param e
	 * @param type
	 */
	private static void saveLog (String className, String message, Throwable e, EnumMessageType type) {
		if (CustomBuildConfig.VERBOSE) {
			log = new String();
		    switch (type) {
				case ERROR:
			    	log += "ERROR";
					break;
				case INFORMATION:
			    	log += "INFORMATION";
					break;
				case WARNING:
			    	log += "WARNING";
					break;
				default:
					log += "WARNING";
					break;
			}
		    log += " "+SEPARATOR+" "+ LOG_TAG +" "+SEPARATOR+" "+ className + ":   "+ message;		    
		    try {
		    	if (e != null && !e.getMessage().equals(""))
		    		log += " "+SEPARATOR+" "+ e.getMessage();
		    	log += LINE_BREAK;
		    } catch (Exception e2) {
		    	log += " "+SEPARATOR+" "+ e2.getMessage();
		    	log += LINE_BREAK;
		    }
		    // Run the log thread.
		    new LoggerTask(log).execute();
		}
	}



	/**
	 * AsyncTask to write the log file.
	 */
	private static class LoggerTask extends AsyncTask<Void, Void, Void> {
		private String log;

		public LoggerTask(String message) {
			this.log = message;
		}

		@Override
		protected Void doInBackground(Void... params) {
			FileOutputStream out;
		    Date date = Calendar.getInstance().getTime();
		    String log = DateTimeFormat.DateTimeToString(date) +" "+SEPARATOR+" "+this.log;
	   	    File file;

	   	    // Check Log file name is null.
	   	    if (logfile == null) {
	   	    	Log.e(LOG_TAG, SEPARATOR+SEPARATOR+" Error writting log. Failed to get log file name. "+SEPARATOR+SEPARATOR);
	   	    	return null;
	   	    }
	   	    // Check Log message is null.
	   	    if (this.log == null) {
	   	    	Log.e(LOG_TAG, SEPARATOR+SEPARATOR+" Error writting log. Failed to get log message. "+SEPARATOR+SEPARATOR);
	   	    	return null;
	   	    }

	   	    // Check if is to save on External or Internal Storage Directory.
	   	    if (isToSaveExternal) {
			    File root = Environment.getExternalStorageDirectory();
	   	    	file = new File(root, logfile);
	   	    } else {
		   	    file = new File(parentContext.getFilesDir(), logfile);
	   	    }

	   	    try {
	   	    	out = new FileOutputStream(file, true);
	   	    	try {
	   	    		out.write(log.getBytes());
	   	    	}
	   	    	finally {
	   	    		out.flush();
	   				out.close();
	   	    	}
	   	    } catch (Exception e3) {
	   	    	Log.e(LOG_TAG, SEPARATOR+SEPARATOR+" Error writting log. Failed to save log file. "+SEPARATOR+SEPARATOR, e3);
	   	    }
			return null;
		}
	}

}
