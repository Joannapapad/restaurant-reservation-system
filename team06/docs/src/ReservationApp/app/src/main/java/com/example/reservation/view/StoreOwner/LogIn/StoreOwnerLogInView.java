package com.example.reservation.view.StoreOwner.LogIn;

/**
 * Interface representing the view for the store owner login process.
 * It contains methods to interact with the user interface during login.
 */
public interface StoreOwnerLogInView {

    /**
     * Sets the username in the login form.
     *
     * @param name The username to be displayed in the login form.
     */
    void setUserName(String name);

    /**
     * Sets the password in the login form.
     *
     * @param password The password to be displayed in the login form.
     */
    void setPassword(String password);

    /**
     * Called when the login is successful.
     * Displays a success message to the user and performs any necessary UI changes (e.g., navigate to another screen).
     *
     * @param message The success message to be shown to the user.
     */
    void succesfullyLogedIn(String message);

    /**
     * Displays an error message to the user.
     * This method is called when the login fails or when an error occurs.
     *
     * @param message The error message to be shown to the user.
     */
    void showErrorMessage(String message);

}
