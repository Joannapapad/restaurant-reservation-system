package com.example.reservation.view.Customer.LogIn;

import com.example.reservation.contact.EmailAddress;

public interface CustomerLoginView {


    /**
     * Sets the username in the login view.
     * This method is typically called when you need to pre-fill the username field.
     *
     * @param username The username to display in the view.
     */
    void setUsername(String username);

    /**
     * Sets the password in the login view.
     * This method is typically called when you need to pre-fill the password field.
     *
     * @param password The password to display in the view.
     */
    void setPassword(String password);

    /**
     * Displays a success message when the login is successful.
     *
     * @param message A success message to show to the user.
     */
    void succesfullyLogedIn(String message);

    /**
     * Displays an error message when there is a problem with the login process.
     *
     * @param message The error message to display to the user.
     */
    void showErrorMessage(String message);

}

