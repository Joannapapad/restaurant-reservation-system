package com.example.reservation.view.Customer.Edit;

/**
 * Interface representing the view for editing customer details.
 * It defines the methods that the Presenter will interact with.
 */
public interface CustomerEditView {

    /**
     * Retrieves the name entered by the user.
     *
     * @return The name of the customer.
     */
    String getName();

    /**
     * Retrieves the username entered by the user.
     *
     * @return The username of the customer.
     */

    String getUsername();

    /**
     * Retrieves the telephone number entered by the user.
     *
     * @return The telephone number of the customer.
     */

    String getTel();

    /**
     * Retrieves the email address entered by the user.
     *
     * @return The email address of the customer.
     */

    String getEmail();

    /**
     * Retrieves the password entered by the user.
     *
     * @return The password of the customer.
     */

    String getPassword();

    /**
     * Sets the username on the view (e.g., text field or label).
     *
     * @param value The username to set.
     */
    void setUsername(String value);

    /**
     * Sets the name on the view (e.g., text field or label).
     *
     * @param value The name to set.
     */

    void setName(String value);

    /**
     * Sets the telephone number on the view (e.g., text field or label).
     *
     * @param value The telephone number to set.
     */

    void setTel(String value);

    /**
     * Sets the email address on the view (e.g., text field or label).
     *
     * @param value The email address to set.
     */
    void setEmail(String value);

    /**
     * Sets the password on the view (e.g., text field or label).
     *
     * @param value The password to set.
     */
    void setPassword(String value);

    /**
     * Displays a success message indicating that the activity was completed successfully.
     *
     * @param message The success message to display.
     */
    void successfullyFinishActivity(String message);

    /**
     * Displays an error message indicating that something went wrong.
     *
     * @param message The error message to display.
     */
    void showErrorMessage( String message);
}
