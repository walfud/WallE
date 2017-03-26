package com.walfud.walle.datetime;

import android.util.Pair;

import java.util.Calendar;

/**
 * Created by walfud on 2016/5/28.
 */
public class TimeRange {

    public static final long MS_PER_S = 1000;
    public static final long MS_PER_M = 60 * MS_PER_S;
    public static final long MS_PER_H = 60 * MS_PER_M;
    public static final long MS_PER_D = 24 * MS_PER_H;
    public static final long MS_PER_W = 7 * MS_PER_D;

    public static Pair<Long, Long> getDay() {
        return getDay(System.currentTimeMillis());
    }

    public static Pair<Long, Long> getDay(long ms) {
        long begin = CleanerCalendar.getDay(ms).getTimeInMillis();
        return new Pair<>(begin, begin + MS_PER_D);
    }

    public static Pair<Long, Long> getWeek() {
        return getWeek(System.currentTimeMillis());
    }

    public static Pair<Long, Long> getWeek(long ms) {
        long begin = CleanerCalendar.getWeek(ms).getTimeInMillis();
        return new Pair<>(begin, begin + MS_PER_W);
    }

    public static Pair<Long, Long> getMonth() {
        return getMonth(System.currentTimeMillis());
    }
    public static Pair<Long, Long> getMonth(long ms) {
        long begin = CleanerCalendar.getMonth(ms).getTimeInMillis();
        Calendar end = Calendar.getInstance();
        end.setTimeInMillis(begin);
        end.add(Calendar.MONTH, 1);

        return new Pair<>(begin, end.getTimeInMillis());
    }

    /**
     *
     * @param year
     * @param month
     * @return
     */
    public static Pair<Long, Long> getMonth(int year, int month) {
        return new Pair<>(CleanerCalendar.getMonth(year, month).getTimeInMillis(),
                CleanerCalendar.getMonth(year, month+1).getTimeInMillis());
    }

    public static int getTotalDayOfMonth(long ms) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    // internal
}
