//package com.example.reservation.test.services;
//import com.example.reservation.domain.Request;
//import com.example.reservation.domain.ReservationStatus;
//import com.example.reservation.services.StoreStatistics;
//import com.example.reservation.util.SimpleCalendar;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//public class StoreStatisticsTest {
//    private StoreStatistics storeStatistics;
//    private Request request;
//    private SimpleCalendar resevationTime;
//
//    @Before
//    public void setUp(){
//        storeStatistics = new StoreStatistics(50);
//        resevationTime = new SimpleCalendar(2024,12,24,21,30);
//        request = new Request(3,232,54, 2, ReservationStatus.APPROVED,"christmas eve date");
//    }
//
//    @Test
//    public void testHasAvailableCapacityWhenCapacityIsSufficient() {
//        assertTrue(storeStatistics.hasAvailableCapacity(request));
//    }
//
//    @Test
//    public void testHasAvailableCapacityWhenCapacityIsNotSufficient() {
//        storeStatistics.reduceCapacity(request);
//        request = new Request(3,232,54, 2, ReservationStatus.APPROVED,"christmas eve date");
//        assertFalse(storeStatistics.hasAvailableCapacity(request));
//    }
//
//    @Test
//    public void testReduceCapacity() {
//        assertTrue(storeStatistics.hasAvailableCapacity(request));
//        storeStatistics.reduceCapacity(request);
//        assertFalse(storeStatistics.hasAvailableCapacity(new Request(3,232,54, 2, ReservationStatus.APPROVED,"christmas eve date")));
//    }
//}
