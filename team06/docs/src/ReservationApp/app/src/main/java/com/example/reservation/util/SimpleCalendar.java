package com.example.reservation.util;

import java.util.Calendar;

public class SimpleCalendar implements Comparable<SimpleCalendar> {
    private static final long MILLIS_PER_DAY = 86400000;
    private Calendar date;

    // Constructor that accepts year, month, and day
    public SimpleCalendar(int year, int month, int day) {
        date = Calendar.getInstance();
        date.set(year, month - 1, day);
        trimToDays(this.date);
    }

    // New constructor that accepts year, month, day, hour, and minute
    public SimpleCalendar(int year, int month, int day, int hour, int minute) {
        date = Calendar.getInstance();
        date.set(year, month - 1, day, hour, minute);
        trimToMinutes(this.date);
    }

    // Constructor that accepts a Calendar object
    public SimpleCalendar(Calendar date) {
        this.date = Calendar.getInstance();
        this.date.setTimeInMillis(date.getTimeInMillis());
        trimToDays(this.date);
    }

    // Helper method to remove time components
    private void trimToDays(Calendar javaDate) {
        javaDate.set(Calendar.HOUR_OF_DAY, 0);
        javaDate.set(Calendar.MINUTE, 0);
        javaDate.set(Calendar.SECOND, 0);
        javaDate.set(Calendar.MILLISECOND, 0);
    }

    // Helper method to trim to minutes (used for the time constructor)
    private void trimToMinutes(Calendar javaDate) {
        javaDate.set(Calendar.SECOND, 0);
        javaDate.set(Calendar.MILLISECOND, 0);
    }

    // Duration in days compared to another SimpleCalendar
    public long durationInDays(SimpleCalendar other) {
        long timeDiff = other.date.getTimeInMillis() - date.getTimeInMillis();
        return timeDiff / MILLIS_PER_DAY;
    }

    public int getYear() {
        return date.get(Calendar.YEAR);
    }

    public int getMonth() {
        return date.get(Calendar.MONTH) + 1;
    }

    public int getDayOfMonth() {
        return date.get(Calendar.DAY_OF_MONTH);
    }

    public int getDayOfWeek() {
        return date.get(Calendar.DAY_OF_WEEK);
    }

    // Get the hour of the day (24-hour format)
    public int getHour() {
        return date.get(Calendar.HOUR_OF_DAY);
    }

    // Get the minute of the hour
    public int getMinute() {
        return date.get(Calendar.MINUTE);
    }

    public Calendar getJavaCalendar() {
        Calendar javaCalendar = Calendar.getInstance();
        javaCalendar.setTimeInMillis(date.getTimeInMillis());
        trimToDays(javaCalendar);
        return javaCalendar;
    }
    public boolean after(SimpleCalendar other) {
        if (equals(other)) {
            return false;
        }
        return date.after(other.date);
    }

    // Check if this date is before another SimpleCalendar
    public boolean before(SimpleCalendar other) {
        if (equals(other)) {
            return false;
        }
        return date.before(other.date);
    }

    // Add a number of days to this SimpleCalendar
    public SimpleCalendar addDays(int days) {
        Calendar newDate = Calendar.getInstance();
        newDate.setTimeInMillis(date.getTimeInMillis());
        newDate.add(Calendar.DAY_OF_MONTH, days);
        return new SimpleCalendar(newDate);
    }

    @Override
    public int compareTo(SimpleCalendar other) {
        return date.compareTo(other.date);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimpleCalendar)) {
            return false;
        }
        SimpleCalendar theDate = (SimpleCalendar) other;
        return getYear() == theDate.getYear() && getMonth() == theDate.getMonth() && getDayOfMonth() == theDate.getDayOfMonth();
    }

    @Override
    public int hashCode() {
        return date == null ? 0 : date.hashCode();
    }
}
