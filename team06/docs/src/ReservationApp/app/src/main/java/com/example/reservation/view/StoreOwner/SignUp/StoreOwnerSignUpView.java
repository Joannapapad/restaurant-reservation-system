package com.example.reservation.view.StoreOwner.SignUp;

/**
 * Interface representing the View in the MVP architecture for the Store Owner Sign-Up feature.
 * Defines the methods that the View (e.g., Activity or Fragment) must implement to interact with the Presenter.
 */
public interface StoreOwnerSignUpView {

    /**
     * Sets the username field in the view.
     *
     * @param name The username to display in the view.
     */
    void setUserName(String name);

    /**
     * Sets the email field in the view.
     *
     * @param email The email address to display in the view.
     */
    void setEmail(String email);

    /**
     * Sets the password field in the view.
     *
     * @param password The password to display in the view.
     */
    void setPassword(String password);

    /**
     * Sets the telephone number field in the view.
     *
     * @param tel The telephone number to display in the view.
     */
    void setTel (String tel);

    /**
     * Sets the AFM (tax identification number) field in the view.
     *
     * @param afm The AFM to display in the view.
     */
    void setAFM(String afm);

    /**
     * Notifies the user that the sign-up process was successful.
     *
     * @param message The success message to display to the user.
     */
    void successfullySignUp(String message);

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    void showErrorMessage(String message);
}
