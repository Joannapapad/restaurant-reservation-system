package com.example.reservation.util;

import java.util.Calendar;
import com.example.reservation.util.*;

public class SystemDate {

    public SystemDate() { }

    private static SimpleCalendar stub;
    private static SimpleCalendar stubDate;


    public static void setStub(SimpleCalendar stubDate) {

        stub = stubDate;
    }


    public static void removeStub() {
        stub = null;
    }


    public static SimpleCalendar now() {
        return stub == null ? new SimpleCalendar(Calendar.getInstance()) : stub;
    }
}
