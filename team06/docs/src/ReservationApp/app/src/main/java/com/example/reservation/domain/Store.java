package com.example.reservation.domain;

import com.example.reservation.contact.StoreAddress;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private int storeId;
    private int ownerId;
    private String name;
    private StoreAddress address;
    private int capacity;
    private int tableNumber;
    private String category ;

    private int usedCapacity = 0 ;
    private final List<Request> requests;
    private final List<Reservation> reservations;


    public Store(int storeId,int ownerId, String name,String category, StoreAddress address, int capacity , int tableNumber){
        this.storeId = storeId;
        this.name = name;
        this.ownerId = ownerId;
        this.address = address;
        this.category = category;
        this.capacity = capacity;
        this.tableNumber = tableNumber;
        this.requests = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public void setStoreId(int storeId){
        this.storeId = storeId;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setAddress(StoreAddress address){
        this.address = address;
    }
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    public void setTableNumber(int tableNumber){
        this.tableNumber = tableNumber;
    }

    public void setOwnerId(int ownerId){
        this.ownerId = ownerId;
    }
    public int getOwnerId(){
        return this.ownerId;
    }
    public String getCategory(){
        return this.category;
    }
    public void setCategory(String category){
        this.category = category;
    }

    public int getStoreId(){
        return this.storeId;
    }
    public String getName(){
        return this.name;
    }
    public StoreAddress getAddress(){
        return this.address;
    }
    public int getCapacity(){
        return this.capacity;
    }
    public int getTableNumber(){
        return this.tableNumber;
    }
    public int getUsedCapacity(){
        return this.usedCapacity;
    }

    public void addRequest(Request request){
        requests.add(request);
    }
    public List<Request> getRequests(){
       return requests;
    }

    public void addReservation(Reservation reservation){
        reservations.add(reservation);
    }

    public List<Reservation> getReservation(){
        return reservations;
    }
    public boolean hasCapacity(int numberOfPeople) {
        return (this.usedCapacity + numberOfPeople) <= this.capacity;
    }


    /**
     * Accept a reservation request by the store and cancel conflicting requests.
     */
    public void acceptRequest(Customer customer, Store store, Request request) {
            request.setReservationStatus(ReservationStatus.APPROVED);
            Reservation reservation = new Reservation(request);
            reservations.add(reservation);
            customer.getReservations().add(reservation);
            customer.getRequests().remove(request);
            requests.remove(request);
            usedCapacity += request.getNumofpeople(); // Update the used capacity
            cancelConflictingRequests(store, customer, request);

    }


    /**
     * Reject a reservation request.
     */
    public void rejectRequest(Request request,Store store, Customer customer) {
        request.setReservationStatus(ReservationStatus.REJECTED);
        requests.remove(request);
        customer.getRequests().remove(request);

    }

    /**
     * Cancel any conflicting requests for the same store, date, and time.
     */
    public void cancelConflictingRequests(Store store, Customer customer, Request approvedRequest) {
        List<Request> customerRequests = customer.getRequests();

        // Reject all conflicting requests in the customer's reservations
        for (Request request : new ArrayList<>(customerRequests)) {
            if (isConflict(request, approvedRequest)) {
                rejectRequest(request,this,customer);
            }
        }
    }

    /**
     * Checks if an existing request conflicts with an approved request.
     */
    private boolean isConflict(Request existingRequest, Request approvedRequest) {
        return existingRequest.getReservationStatus() == ReservationStatus.PENDING &&
                existingRequest.getRequestDate().equals(approvedRequest.getRequestDate()) &&
                existingRequest.getRequestTime().equals(approvedRequest.getRequestTime()) &&
                existingRequest.getStoreID() == approvedRequest.getStoreID();
    }

    /**
     * Manages a customer's reservation request, either accepting or rejecting it based on availability and conditions.
     */
    public boolean manageRequest(Customer customer, Request request, boolean accept) {
        if (accept && hasCapacity(request.getNumofpeople())) {
            // Accept the request and handle the reservation
            this.acceptRequest(customer, this, request);

            // Remove the accepted request from the store's requests list
            this.requests.remove(request);

            return true;
        } else {
            // Reject the request
            this.rejectRequest(request, this, customer);
            return false;
        }
    }

    /**
     * Manages a customer's reservation request, either accepting or rejecting it based on availability and conditions.
     */
    @Override
    public String toString() {
        return "Store{name='" + name + "', address=" + address + "'}";
    }
}
