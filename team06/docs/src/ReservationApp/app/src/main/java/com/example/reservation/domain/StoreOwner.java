package com.example.reservation.domain;
import com.example.reservation.contact.TelephoneNumber;
import com.example.reservation.contact.EmailAddress;

import java.util.HashMap;

/**
 * Represents a store owner user in the system.
 * Extends the User class to include specific attributes and methods related to store owners.
 */
public class StoreOwner extends User {

    private int ownerId;
    private String AFM;
    private  HashMap<Integer,StoreOwner> storeOwnerData;

    public StoreOwner() {
        super();
    }

    /**
     * Constructor to create a StoreOwner with specific attributes.
     *
     * @param ownerId  The unique ID of the store owner
     * @param AFM      The AFM of the store owner
     * @param userName The username for the store owner
     * @param email    The email address of the store owner
     * @param password The password for the store owner
     * @param number   The telephone number of the store owner
     */
    public StoreOwner(int ownerId, String AFM, String userName, EmailAddress email, String password, TelephoneNumber number) {
        super(userName, email, password, number);
        this.ownerId = ownerId;
        this.AFM= AFM;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }


    public void setAFM(String AFM) {
        this.AFM= AFM;
    }


    public int getOwnerId() {
        return this.ownerId;
    }

    public String getAFM() {
        return this.AFM;
    }

    /**
     * Sets the store owner data.
     *
     * @param storeOwnerData A map of store owners by their unique ID
     */
    public void setStoreOwnerData(HashMap<Integer, StoreOwner> storeOwnerData) {
        this.storeOwnerData = storeOwnerData;
    }

    /**
     * Registers a new store owner in the system if the validation passes.
     *
     * @param owner The store owner to sign up
     * @return true if signup is successful, false otherwise
     */
    public boolean signUp(StoreOwner owner){
        if(!check(owner)){
            storeOwnerData.put(owner.getOwnerId(),owner);
            System.out.println("Store owner with id :" + owner.getOwnerId() + "successfully singed in !!");
            return true;
        } else{
            System.out.println("Something went wrong... Please sing up again");
            return false;
        }
    }

    /**
     * Validates the AFM (Tax Identification Number) of the store owner.
     *
     * @param AFM The AFM to validate
     * @return true if AFM is invalid, false otherwise
     */
    public boolean checkValidation(String AFM){
        if (Integer.parseInt(AFM) < 0){
            System.out.println("AFM cant be a negative number.");
            return true;
        }
        int length = String.valueOf(AFM).length();
        return length != 9;
    }

    /**
     * Checks if the store owner ID already exists in the system.
     *
     * @param owner The store owner to check
     * @return true if the store owner ID already exists, false otherwise
     */
    public Boolean checksExistenceOfId(StoreOwner owner){
        return storeOwnerData.containsKey(owner.getOwnerId());
    }

    /**
     * Checks if the username already exists in the system.
     *
     * @param userName The username to check
     * @return true if the username is taken, false otherwise
     */
    public boolean checkExistenceOfUserName(String userName){
        for(StoreOwner owner : storeOwnerData.values()){
            if(owner.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the store owner meets all necessary criteria for sign-up.
     * It checks for ID existence, AFM validity, and username availability.
     *
     * @param owner The store owner to validate
     * @return true if any check fails, false otherwise
     */
    public boolean check(StoreOwner owner){
        if(checksExistenceOfId(owner)){
            System.out.println("Store owner with id :" + owner.getOwnerId() + "already exists in the system. You have to select Sign in button");
            signIn(owner.getUserName() , owner.getPassword());
            return true;
        }
        if (checkValidation(owner.getAFM())){
            System.out.println("Wrong AFM. Sign in again!");
            return true;
        }
        if (checkExistenceOfUserName(owner.getUserName())){
            System.out.println("This username : " + owner.getUserName() + "is taken. Please try another.");
            return true;
        }
        return false;
    }

    /**
     * Signs the store owner in if the provided username and password match the existing data.
     *
     * @param name     The username of the store owner
     * @param password The password of the store owner
     * @return true if sign-in is successful, false otherwise
     */
    public boolean signIn(String name , String password){
        for(StoreOwner owner : storeOwnerData.values()){
            if(storeOwnerData.containsKey(owner.getOwnerId())){
                if(owner.getUserName(). equals(name) && owner.getPassword().equals(password)){
                    System.out.println("You have successfully signed in!!");
                    return true;
                } else{
                    System.out.println("You gave wrong data");
                    return false;
                }
            }else{
                signUp(owner);
                return false;
            }
        }
        return true;
    }
}

