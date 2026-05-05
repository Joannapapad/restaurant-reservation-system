package com.example.reservation.domain;

import com.example.reservation.contact.EmailAddress;
import com.example.reservation.contact.TelephoneNumber;
/**
 * Represents a user in the system.
 * This class contains basic user information such as username, email, password, and telephone number.
 */
public class User {
    private String userName;
    private EmailAddress email;
    private String password;
    private TelephoneNumber number;

    public User(){}
    /**
     * Constructor to create a User with specific attributes.
     *
     * @param userName The username of the user
     * @param email    The email address of the user
     * @param password The password for the user
     * @param number   The telephone number of the user
     */
    User(String userName, EmailAddress email, String password , TelephoneNumber number){
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.number = number;
    }

    /**
     * Constructor to create a User with a username and password.
     * This constructor may be used for login or simple identification.
     *
     * @param username The username of the user
     * @param password The password for the user
     */
    public User(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    /**
     * Sets the username for the user.
     *
     * @param userName The username to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Sets the email address for the user.
     *
     * @param email The email address to set
     */
    public void setEmail(EmailAddress email) {
        this.email = email;
    }

    /**
     * Sets the password for the user.
     *
     * @param password The password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the telephone number for the user.
     *
     * @param number The telephone number to set
     */
    public void setNumber(TelephoneNumber number) {
        this.number = number;
    }

    /**
     * Gets the username of the user.
     *
     * @return The username of the user
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Gets the email address of the user.
     *
     * @return The email address of the user
     */
    public EmailAddress getEmail() {
        return this.email;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Gets the telephone number of the user.
     *
     * @return The telephone number of the user
     */
    public TelephoneNumber getNumber() {
        return this.number;
    }
}
