package com.example.reservation.view.Customer.Profile;

/**
 * Interface for the Customer Profile View.
 * Defines the methods that the presenter uses to interact with the view.
 * Implemented by activities or fragments that display the customer's profile.
 */
public interface CustomerProfileView {

    /**
     * Retrieves the customer's name from the view.
     *
     * @return The customer's name as a String.
     */
    String getName();

    /**
     * Updates the customer's name displayed in the view.
     *
     * @param value The name to be displayed.
     */
    void setName(String value);

    /**
     * Updates the customer's email displayed in the view.
     *
     * @param value The email to be displayed.
     */
    void setEmail(String value);

    /**
     * Updates the customer's phone number displayed in the view.
     *
     * @param value The phone number to be displayed.
     */
    void setPhone(String value);

    /**
     * Updates the customer's password displayed in the view.
     *
     * @param value The password to be displayed.
     */
    void setPassword(String value);

    /**
     * Updates the customer's ID displayed in the view.
     *
     * @param value The ID to be displayed.
     */
    void setId(String value);

    /**
     * Displays a toast message to the user.
     *
     * @param value The message to be displayed in the toast.
     */
    void showToast(String value);

    /**
     * Starts the activity for editing the customer's profile.
     *
     * @param customerId The customer's ID.
     * @param name       The customer's name.
     * @param email      The customer's email address.
     * @param phone      The customer's phone number.
     * @param password   The customer's password.
     */
    void startEditActivity(int customerId, String name, String email, String phone, String password);

    /**
     * Prompts the user with a confirmation dialog to delete the account.
     *
     * @param title   The title of the confirmation dialog.
     * @param message The message displayed in the confirmation dialog.
     */
    void startDelete(String title, String message);

    /**
     * Displays a message indicating successful account deletion and navigates back to the login screen.
     *
     * @param message The message to be displayed to the user.
     */
    void doDeleteAndFinish(String message);
}
