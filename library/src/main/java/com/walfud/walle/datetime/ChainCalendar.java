package com.walfud.walle.datetime;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by walfud on 2016/6/21.
 */
public class ChainCalendar {
    Calendar mCalendar;

    public ChainCalendar(Calendar calendar) {
        mCalendar = calendar;
    }

    public ChainCalendar add(int field, int value) {
        mCalendar.add(field, value);
        return this;
    }

    public int getMaximum(int field) {
        return mCalendar.getMaximum(field);
    }

    public ChainCalendar set(int year, int month, int day, int hourOfDay, int minute, int second) {
        mCalendar.set(year, month, day, hourOfDay, minute, second);
        return this;
    }

    public ChainCalendar set(int field, int value) {
        mCalendar.set(field, value);
        return this;
    }

    public int compareTo(Calendar anotherCalendar) {
        return mCalendar.compareTo(anotherCalendar);
    }

    public Date getTime() {
        return mCalendar.getTime();
    }

    public ChainCalendar roll(int field, int value) {
        mCalendar.roll(field, value);
        return this;
    }

    public ChainCalendar set(int year, int month, int day, int hourOfDay, int minute) {
        mCalendar.set(year, month, day, hourOfDay, minute);
        return this;
    }

    public int getActualMaximum(int field) {
        return mCalendar.getActualMaximum(field);
    }

    public ChainCalendar setMinimalDaysInFirstWeek(int value) {
        mCalendar.setMinimalDaysInFirstWeek(value);
        return this;
    }

    public boolean isSet(int field) {
        return mCalendar.isSet(field);
    }

    public int getLeastMaximum(int field) {
        return mCalendar.getLeastMaximum(field);
    }

    public boolean isLenient() {
        return mCalendar.isLenient();
    }

    public int getActualMinimum(int field) {
        return mCalendar.getActualMinimum(field);
    }

    public int getGreatestMinimum(int field) {
        return mCalendar.getGreatestMinimum(field);
    }

    public int getMinimum(int field) {
        return mCalendar.getMinimum(field);
    }

    public ChainCalendar setLenient(boolean value) {
        mCalendar.setLenient(value);
        return this;
    }

    public ChainCalendar setTime(Date date) {
        mCalendar.setTime(date);
        return this;
    }

    public long getTimeInMillis() {
        return mCalendar.getTimeInMillis();
    }

    public ChainCalendar clear() {
        mCalendar.clear();
        return this;
    }

    public ChainCalendar setTimeZone(TimeZone timezone) {
        mCalendar.setTimeZone(timezone);
        return this;
    }

    public static ChainCalendar getInstance(Locale locale) {
        return new ChainCalendar(Calendar.getInstance(locale));
    }

    public ChainCalendar clear(int field) {
        mCalendar.clear(field);
        return this;
    }

    public static ChainCalendar getInstance(TimeZone timezone, Locale locale) {
        return new ChainCalendar(Calendar.getInstance(timezone, locale));
    }

    public ChainCalendar setFirstDayOfWeek(int value) {
        mCalendar.setFirstDayOfWeek(value);
        return this;
    }

    public int getFirstDayOfWeek() {
        return mCalendar.getFirstDayOfWeek();
    }

    public ChainCalendar roll(int field, boolean increment) {
        mCalendar.roll(field, increment);
        return this;
    }

    public boolean before(Object calendar) {
        return mCalendar.before(calendar);
    }

    public static Locale[] getAvailableLocales() {
        return Calendar.getAvailableLocales();
    }

    public boolean after(Object calendar) {
        return mCalendar.after(calendar);
    }

    public static ChainCalendar getInstance() {
        return new ChainCalendar(Calendar.getInstance());
    }

    public static ChainCalendar getInstance(long ms) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);
        return new ChainCalendar(calendar);
    }

    public static ChainCalendar getInstance(Calendar calendar) {
        return new ChainCalendar((Calendar) calendar.clone());
    }

    public String getDisplayName(int field, int style, Locale locale) {
        return mCalendar.getDisplayName(field, style, locale);
    }

    public int get(int field) {
        return mCalendar.get(field);
    }

    public int getMinimalDaysInFirstWeek() {
        return mCalendar.getMinimalDaysInFirstWeek();
    }

    public static ChainCalendar getInstance(TimeZone timezone) {
        return new ChainCalendar(Calendar.getInstance(timezone));
    }

    public ChainCalendar set(int year, int month, int day) {
        mCalendar.set(year, month, day);
        return this;
    }

    public TimeZone getTimeZone() {
        return mCalendar.getTimeZone();
    }

    public Map<String, Integer> getDisplayNames(int field, int style, Locale locale) {
        return mCalendar.getDisplayNames(field, style, locale);
    }

    public ChainCalendar setTimeInMillis(long milliseconds) {
        mCalendar.setTimeInMillis(milliseconds);
        return this;
    }

    public Calendar get() {
        return mCalendar;
    }
}
