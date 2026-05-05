package com.example.reservation.test.util;

import com.example.reservation.util.*;
public class SystemDateStub {

    public static void setStub(SimpleCalendar stub) {
        SystemDate.setStub(stub);
    }

    public static void reset() {
        SystemDate.removeStub();
    }
}
