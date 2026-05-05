package com.example.reservation.view.StoreOwner.Edit;

import java.util.List;

/**
 * View interface for the StoreOwnerEditPresenter.
 * This interface defines the methods that the presenter will interact with to get and set data.
 */
public interface StoreOwnerEditView {

        /**
         * Retrieves the store owner's ID from the view.
         *
         * @return The ID of the store owner.
         */
        int getId();

        /**
         * Retrieves the store owner's name from the view.
         *
         * @return The name of the store owner.
         */
        String getName();

        /**
         * Retrieves the store owner's phone number from the view.
         *
         * @return The phone number of the store owner.
         */
        String getPhone();

        /**
         * Retrieves the store owner's email from the view.
         *
         * @return The email of the store owner.
         */
        String getEmail();

        /**
         * Retrieves the store owner's AFM (tax identification number) from the view.
         *
         * @return The AFM of the store owner.
         */
        String getAfm();

        /**
         * Retrieves the store owner's password from the view.
         *
         * @return The password of the store owner.
         */
        String getPassword();

        /**
         * Sets the store owner's name in the view.
         *
         * @param value The name to be set.
         */
        void setName(String value);

        /**
         * Sets the store owner's phone number in the view.
         *
         * @param value The phone number to be set.
         */
        void setTel(String value);

        /**
         * Sets the store owner's email in the view.
         *
         * @param value The email to be set.
         */
        void setEmail(String value);

        /**
         * Sets the store owner's AFM (tax identification number) in the view.
         *
         * @param value The AFM to be set.
         */
        void setAfm(String value);

        /**
         * Sets the store owner's password in the view.
         *
         * @param value The password to be set.
         */
        void setPassword(String value);

        /**
         * Displays a success message and finishes the activity.
         *
         * @param message The success message to be displayed.
         */
        void successfullyFinishActivity(String message);

        /**
         * Displays an error message to the user.
         *
         * @param message The error message to be displayed.
         */
        void showErrorMessage( String message);
}


