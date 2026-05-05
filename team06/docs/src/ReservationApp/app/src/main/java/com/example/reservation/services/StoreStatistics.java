//package com.example.reservation.services;
//import com.example.reservation.domain.Request;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.List;
//import java.util.ArrayList;
//public class StoreStatistics {
//
//    private int totalCapacity;
//    private Map<String, Integer> weeklyReservations;
//
//    public int getTotalCapacity() {
//        return totalCapacity;
//    }
//
//    public StoreStatistics(int totalCapacity) {
//        this.totalCapacity = totalCapacity;
//        this.weeklyReservations = new HashMap<>();
//
//    }
//
//    public boolean hasAvailableCapacity(Request request) {
//        String timeKey = request.getReservationTime().getDateTimeString();
//        int currentCapacity = weeklyReservations.getOrDefault(timeKey, totalCapacity);
//        return currentCapacity >= request.getNumofpeople();
//    }
//
//    public void reduceCapacity(Request request) {
//        String timeKey = request.getReservationTime().getDateTimeString();
//        int currentCapacity = weeklyReservations.getOrDefault(timeKey, totalCapacity);
//        weeklyReservations.put(timeKey, currentCapacity - request.getNumofpeople());
//}
//}
