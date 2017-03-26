package com.walfud.walle.datetime;

import java.util.Calendar;

/**
 * Created by walfud on 2016/5/30.
 */
public class CleanerCalendar {
    public static Calendar getSecond() {
        return getSecond(System.currentTimeMillis());
    }

    public static Calendar getSecond(long ms) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public static Calendar getMinute() {
        return getMinute(System.currentTimeMillis());
    }

    public static Calendar getMinute(long ms) {
        Calendar calendar = getSecond(ms);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }

    public static Calendar getHour() {
        return getHour(System.currentTimeMillis());
    }

    public static Calendar getHour(long ms) {
        Calendar calendar = getMinute(ms);
        calendar.set(Calendar.MINUTE, 0);
        return calendar;
    }

    public static Calendar getDay() {
        return getDay(System.currentTimeMillis());
    }

    public static Calendar getDay(long ms) {
        Calendar calendar = getHour(ms);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar;
    }

    public static Calendar getDay(int year, int month, int day) {
        Calendar calendar = getMonth(year, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        return calendar;
    }

    public static Calendar getWeek() {
        return getWeek(System.currentTimeMillis());
    }

    public static Calendar getWeek(long ms) {
        Calendar calendar = getDay(ms);
        calendar.set(Calendar.WEEK_OF_MONTH, Calendar.MONDAY);
        return calendar;
    }

    public static Calendar getMonth() {
        return getMonth(System.currentTimeMillis());
    }

    public static Calendar getMonth(long ms) {
        Calendar calendar = getDay(ms);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar;
    }

    public static Calendar getMonth(int year, int month) {
        Calendar calendar = getYear(year);
        calendar.set(Calendar.MONTH, month - 1);

        return calendar;
    }

    public static Calendar getYear() {
        return getYear(System.currentTimeMillis());
    }

    public static Calendar getYear(long ms) {
        Calendar calendar = getMonth(ms);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        return calendar;
    }

    public static Calendar getYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);

        return calendar;
    }
}
