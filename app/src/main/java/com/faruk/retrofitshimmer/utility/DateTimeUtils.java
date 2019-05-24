package com.faruk.retrofitshimmer.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by farukhossain on 2019/04/25.
 */

public class DateTimeUtils {

    // format 24hre ex. 12:12 , 17:15
    private static String HOUR_FORMAT = "HH:mm";
    private static String START_TIME = "10:00"; //24 hours format
    private static String END_TIME = "18:00"; //24 hours format

    private DateTimeUtils() {
    }


    public static boolean isWithinWorkHour() {
        if (!isWeekEnd() && isNowInInterval(START_TIME, END_TIME))
            return true;

        return false;
    }

    //Weekend calculation. Weekend = SATURDAY & SUNDAY
    public static boolean isWeekEnd() {
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
            return true;

        return false;
    }

    public static String getCurrentHour() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdfHour = new SimpleDateFormat(HOUR_FORMAT);
        String hour = sdfHour.format(cal.getTime());
        return hour;
    }

    /**
     * @param target hour to check
     * @param start  interval start
     * @param end    interval end
     * @return true    true if the given hour is between
     */
    public static boolean isHourInInterval(String target, String start, String end) {
        return ((target.compareTo(start) >= 0)
                && (target.compareTo(end) <= 0));
    }

    /**
     * @param start interval start
     * @param end   interval end
     * @return true    true if the current hour is between
     */
    public static boolean isNowInInterval(String start, String end) {
        return DateTimeUtils.isHourInInterval
                (DateTimeUtils.getCurrentHour(), start, end);
    }
}
