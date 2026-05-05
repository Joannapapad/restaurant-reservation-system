package com.example.reservation.view.Customer.SignUp;

/**
 * Interface representing the view for customer sign-up functionality.
 * This is part of the MVP (Model-View-Presenter) architecture.
 */
public interface CustomerSignUpView {

    /**
     * Sets the name field in the sign-up form.
     *
     * @param name The name to be displayed in the name field.
     */
    void setName (String name);

    /**
     * Sets the username field in the sign-up form.
     *
     * @param userName The username to be displayed in the username field.
     */
    void setUserName(String userName);

    /**
     * Sets the email field in the sign-up form.
     *
     * @param email The email to be displayed in the email field.
     */
    void  setEmail(String email);

    /**
     * Sets the password field in the sign-up form.
     *
     * @param password The password to be displayed in the password field.
     */
    void setPassword(String password);

    /**
     * Sets the phone number field in the sign-up form.
     *
     * @param tel The phone number to be displayed in the phone number field.
     */
    void setTel (String tel);

    /**
     * Displays an error message to the user when validation fails or an error occurs.
     *
     * @param message The error message to be displayed.
     */
    void showErrorMessage(String message);
    /**
     * Displays a success message when the sign-up process is completed successfully.
     *
     * @param message The success message to be displayed.
     */
    void successfullySignUp(String message);
}



