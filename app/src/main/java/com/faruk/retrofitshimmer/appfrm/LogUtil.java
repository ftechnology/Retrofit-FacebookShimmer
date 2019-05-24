/**
 * 
 * @author Md Faruk Hossain
 * The logger of the application, please provide the APPNAME and also the pacakge name.
 * Set the LOGLEVEL also.
 *
 */
package com.faruk.retrofitshimmer.appfrm;

import android.util.Log;

/**
 * Log to Android logging system.
 *
 * Log message can be a string or a printf formatted string with arguments.
 * See http://developer.android.com/reference/java/util/Formatter.html
 */
public class LogUtil {

    public static final int VERBOSE = Log.VERBOSE;
    public static final int DEBUG = Log.DEBUG;
    public static final int INFO = Log.INFO;
    public static final int WARN = Log.WARN;
    public static final int ERROR = Log.ERROR;
    
    /**
     * TAG for Application.
     * TODO place the name of the application here
     */
    static final String TAG = "EasyReader->";
    // Current log level
    public static int LOGLEVEL = Log.DEBUG;

    /**
     * Set the current log level.
     *
     * @param logLevel
     */
    public static void setLogLevel(int logLevel) {
        LOGLEVEL = logLevel;
        Log.i("CordovaLog", "Changing log level to " + logLevel);
    }

    /**
     * Set the current log level.
     *
     * @param logLevel
     */
    public static void setLogLevel(String logLevel) {
        if ("VERBOSE".equals(logLevel)) LOGLEVEL = VERBOSE;
        else if ("DEBUG".equals(logLevel)) LOGLEVEL = DEBUG;
        else if ("INFO".equals(logLevel)) LOGLEVEL = INFO;
        else if ("WARN".equals(logLevel)) LOGLEVEL = WARN;
        else if ("ERROR".equals(logLevel)) LOGLEVEL = ERROR;
        Log.i("CordovaLog", "Changing log level to " + logLevel + "(" + LOGLEVEL + ")");
    }

    /**
     * Determine if log level will be logged
     *
     * @param logLevel
     * @return true if the parameter passed in is greater than or equal to the current log level
     */
    public static boolean isLoggable(int logLevel) {
        return (logLevel >= LOGLEVEL);
    }

    /**
     * Verbose log message.
     *
     * @param s
     */
    public static void v(String s) {
        if (LogUtil.VERBOSE >= LOGLEVEL) Log.v(TAG, s);
    }

    /**
     * Send a DEBUG log message and log the exception.<BR>
     * <P>
     * See Android SDK.
     */
    public static void d(String msg) {
    	 if (LogUtil.DEBUG >= LOGLEVEL) {
            Log.d(TAG, msg);
        }
    }

    /**
     * Info log message.
     *
     * @param s
     */
    public static void i(String s) {
        if (LogUtil.INFO >= LOGLEVEL) Log.i(TAG, s);
    }

    /**
     * Warning log message.
     *
     * @param s
     */
    public static void w(String s) {
        if (LogUtil.WARN >= LOGLEVEL) Log.w(TAG, s);
    }

    /**
     * Error log message.
     *
     * @param s
     */
    public static void e(String s) {
        if (LogUtil.ERROR >= LOGLEVEL) Log.e(TAG, s);
    }

    /**
     * Verbose log message.
     *
     * @param s
     * @param e
     */
    public static void v(String s, Throwable e) {
        if (LogUtil.VERBOSE >= LOGLEVEL) Log.v(TAG, s, e);
    }

    /**
     * Debug log message.
     *
     * @param s
     * @param e
     */
    public static void d(String s, Throwable e) {
        if (LogUtil.DEBUG >= LOGLEVEL) Log.d(TAG, s, e);
    }

    /**
     * Info log message.
     *
     * @param s
     * @param e
     */
    public static void i(String s, Throwable e) {
        if (LogUtil.INFO >= LOGLEVEL) Log.i(TAG, s, e);
    }

    /**
     * Warning log message.
     *
     * @param s
     * @param e
     */
    public static void w(String s, Throwable e) {
        if (LogUtil.WARN >= LOGLEVEL) Log.w(TAG, s, e);
    }

    /**
     * Error log message.
     *
     * @param s
     * @param e
     */
    public static void e(String s, Throwable e) {
        if (LogUtil.ERROR >= LOGLEVEL) Log.e(TAG, s, e);
    }

    /**
     * Verbose log message with printf formatting.
     *
     * @param s
     * @param args
     */
    public static void v(String s, Object... args) {
        if (LogUtil.VERBOSE >= LOGLEVEL) Log.v(TAG, String.format(s, args));
    }

    /**
     * Debug log message with printf formatting.
     *
     * @param s
     * @param args
     */
    public static void d(String s, Object... args) {
        if (LogUtil.DEBUG >= LOGLEVEL) Log.d(TAG,String.format(s, args));
    }

    /**
     * Info log message with printf formatting.
     *
     * @param s
     * @param args
     */
    public static void i(String s, Object... args) {
        if (LogUtil.INFO >= LOGLEVEL) Log.i(TAG, String.format(s, args));
    }

    /**
     * Warning log message with printf formatting.
     *
     * @param s
     * @param args
     */
    public static void w(String s, Object... args) {
        if (LogUtil.WARN >= LOGLEVEL) Log.w(TAG, String.format(s, args));
    }

    /**
     * Error log message with printf formatting.
     *
     * @param s
     * @param args
     */
    public static void e(String s, Object... args) {
        if (LogUtil.ERROR >= LOGLEVEL) Log.e(TAG, String.format(s, args));
    }

}
