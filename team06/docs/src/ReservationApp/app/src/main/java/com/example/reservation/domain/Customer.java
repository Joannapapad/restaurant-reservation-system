package com.example.reservation.domain;

import com.example.reservation.contact.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a Customer in the reservation system.
 * A Customer can sign up, sign in, create requests, rate stores, and manage reservations.
 */
public class Customer extends User {
    private int customerId;
    private String name;
    // private String comment;
    private List<Reservation> reservations;

    private List<Request> requests;

    private ArrayList<Rate> ratings;

    private static int reservationCounter = 0;

    /**
     * Constructs a new Customer with the given attributes.
     *
     * @param customerId The unique identifier for the customer.
     * @param name       The name of the customer.
     * @param userName   The username of the customer.
     * @param email      The email address of the customer.
     * @param password   The password for the customer's account.
     * @param number     The telephone number of the customer.
     */
    public Customer(int customerId, String name, String userName, EmailAddress email, String password, TelephoneNumber number) {
        super(userName, email, password, number);
        this.customerId = customerId;
        this.name = name;
        requests = new ArrayList();
        ratings = new ArrayList();
        reservations = new ArrayList<>();
    }

    private  HashMap<Integer,Customer> customerData = new HashMap();


    // Getters and Setters
    /**
     * @return The unique identifier of the customer.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the unique identifier for the customer.
     *
     * @param customerId The unique ID to be set.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return The name of the customer.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The name of the customer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds a request to the customer's list of requests.
     *
     * @param request The request to be added.
     */
    public void addRequest(Request request){
        requests.add(request);
    }

    /**
     * @return The list of requests made by the customer.
     */
    public List<Request> getRequests(){
        return requests;
    }

    /**
     * @return The list of reservations associated with the customer.
     */
    public List<Reservation> getReservations(){
        return reservations;
    }

    /**
     * Sets customer data as a mapping of customer IDs to customer objects.
     *
     * @param customerData A map containing customer data.
     */
    public void setCustomerData(HashMap<Integer,Customer> customerData){
        this.customerData = customerData;
    }

    /**
     * Signs up a new customer.
     *
     * @param customer The customer to be signed up.
     * @return True if the sign-up is successful, false otherwise.
     */
    public boolean signUp(Customer customer) {
        if (!check(customer)) {
            customerData.put(customer.getCustomerId(), customer);
            System.out.println("Customer with id :" + customer.getCustomerId()+ "successfully singed in !!");
            return true;
        } else {
            System.out.println("Something went wrong... Please sing up again");
            return false;
        }
    }

    /**
     * Checks if a customer with the given ID exists.
     *
     * @param customer The customer to check.
     * @return True if the customer exists, false otherwise.
     */
    public Boolean checksExistenceOfId(Customer customer){
        return customerData.containsKey(customer.getCustomerId());
    }

    /**
     * Checks if a given username is already taken.
     *
     * @param userName The username to check.
     * @return True if the username exists, false otherwise.
     */
    public boolean checkExistenceOfUserName(String userName){
        for(Customer customer : customerData.values()){
            if(customer.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }

    /**
     * Validates if a customer can be added to the system.
     *
     * @param customer The customer to validate.
     * @return True if the customer exists or username is taken, false otherwise.
     */
    public boolean check(Customer customer){
        if(checksExistenceOfId(customer)){
            System.out.println("Customer with id :" + customer.getCustomerId() + "already exists in the system. You have to select Sign up button");
            signIn(customer.getUserName() , customer.getPassword());
            return true;
        }
        if (checkExistenceOfUserName(customer.getUserName())){
            System.out.println("This username : " + customer.getUserName() + "is taken. Please try another.");
            return true;
        }
        return false;
    }

    /**
     * Signs in a customer with the given username and password.
     *
     * @param name     The username of the customer.
     * @param password The password of the customer.
     * @return True if the sign-in is successful, false otherwise.
     */
    public boolean signIn(String name , String password){
        for(Customer customer : customerData.values()){
            if(customerData.containsKey(customer.getCustomerId())){
                if(customer.getUserName(). equals(name) && customer.getPassword().equals(password)){
                    System.out.println("You have successfully signed in!!");
                    return true;
                } else{
                    System.out.println("You gave wrong data");
                    return false;
                }
            }else{
                signUp(customer);
                return false;
            }
        }
        return true;
    }

    /**
     * Rates a store.
     *
     * @param storeId  The ID of the store to rate.
     * @param rating   The rating value.
     * @param feedback The feedback for the store.
     */
    public void rateStore(int storeId, int rating, String feedback) {
        Rate newRating = new Rate(storeId, this.customerId, rating, feedback);
        ratings.add(newRating);
        System.out.println("Thank you for rating this store!");
    }

    /**
     * Creates a new reservation request for the customer.
     *
     * @param store      The store for which the reservation is requested.
     * @param customer   The customer making the request.
     * @param numOfPeople The number of people for the reservation.
     * @param comment    Additional comments for the request.
     * @return A new Request object.
     */
    public Request createRequest(Store store, Customer customer, int numOfPeople, String comment) {
        int reservationId = generateRequestId(store);
        Request newRequest = new Request(
                reservationId,
                store.getStoreId(),
                customer.getCustomerId(),
                numOfPeople,
                ReservationStatus.PENDING,
                comment
        );

        customer.addRequest(newRequest); // Add request to the customer's reservation list
        return newRequest;
    }

    /**
     * Cancels a request made by the customer.
     *
     * @param request The request to cancel.
     */
    public void cancelRequest(Request request) {
        requests.remove(request);
    }

    /**
     * Generates a unique request ID based on the store and reservation counter.
     *
     * @param store The store for which the reservation is made.
     * @return A unique request ID.
     */
    private int generateRequestId(Store store) {
        reservationCounter++;
        return (store.getStoreId() + "" + reservationCounter).hashCode();
    }

    /**
     * Adds a reservation to the customer's list.
     *
     * @param reservation The reservation to add.
     */
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }
}