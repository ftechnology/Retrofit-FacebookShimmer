/**
 * @author Faruk Hossain
 * <p>
 * Useful for projects, when need some conversion. Update the file as required
 */
package com.faruk.retrofitshimmer.appfrm;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Convert {

    /**
     * Return the integer from string.
     *
     * @param value
     * @return
     */
    public static int toInt(String value) {
        try {
            return NumberFormat.getInstance().parse(value).intValue();
        } catch (ParseException e) {

        }
        return 0;
    }

    /**
     * Return the long from string.
     *
     * @param value
     * @return
     */
    public static long toLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {

        }
        return 0;
    }

    public static String getYearDay(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("MMMM dd, EEE", Locale.US);
        return format.format(new Date(timestamp * 1000L));
    }


}
