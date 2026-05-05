package com.example.reservation.view.MainLogin;

/**
 * Interface representing the view for the main login screen.
 * This interface defines the methods that the view (e.g., Activity or Fragment) must implement
 * in order to handle user interactions with login and sign-up for different types of users.
 */
public interface MainLoginView
{
    /**
     * This method is called when the user clicks on the Store Owner Login button.
     * It should navigate the user to the `StoreOwnerLoginActivity`.
     */
    void ManageStoreOwnerLogin();

    /**
     * This method is called when the user clicks on the Store Owner Sign-Up button.
     * It should navigate the user to the `StoreOwnerSignUpActivity` for registration.
     */
    void ManageStoreOwnerSignUp();

    /**
     * This method is called when the user clicks on the Customer Login button.
     * It should navigate the user to the `CustomerLoginActivity`.
     */
    void ManageCustomerLogin();

    /**
     * This method is called when the user clicks on the Customer Sign-Up button.
     * It should navigate the user to the `CustomerSignUpActivity` for registration.
     */
    void ManageCustomerSignUp();

}

