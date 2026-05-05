package com.example.reservation.view.StoreOwner.SignUp;


import com.example.reservation.contact.EmailAddress;
import com.example.reservation.contact.TelephoneNumber;
import com.example.reservation.dao.CurrentUserDAO;
import com.example.reservation.dao.StoreOwnerDAO;
import com.example.reservation.domain.StoreOwner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Presenter responsible for handling the logic of the Store Owner sign-up process.
 * It validates user input, checks for existing accounts, and saves a new StoreOwner.
 */
public class StoreOwnerSignUpPresenter {

    private StoreOwnerSignUpView view;

    private CurrentUserDAO currentUserDAO;

    private StoreOwnerDAO storeOwnerDAO;
    private int latestId = 0;

    /**
     * Validates the provided email address using a regular expression.
     *
     * @param email The email address to validate.
     * @return True if the email is valid, false otherwise.
     */
    private boolean validateEmail(String email)
    {
        Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * Sets the view associated with the presenter.
     *
     * @param view The view to associate with this presenter.
     */
    public void setView (StoreOwnerSignUpView view) {this.view = view;}

    /**
     * Gets the view associated with the presenter.
     *
     * @return The current view associated with this presenter.
     */
    public StoreOwnerSignUpView getView() { return view;}

    /**
     * Sets the StoreOwnerDAO that is used to interact with store owners data.
     *
     * @param storeOwnerDAO The StoreOwnerDAO to set.
     */
    public void setStoreOwnerDAO (StoreOwnerDAO storeOwnerDAO) {this.storeOwnerDAO = storeOwnerDAO;}

    /**
     * Sets the CurrentUserDAO that is used to manage the current logged-in user.
     *
     * @param currentUserDAO The CurrentUserDAO to set.
     */
    public void setCurrentUserDAO (CurrentUserDAO currentUserDAO) {this.currentUserDAO = currentUserDAO;}

    /**
     * Handles the sign-up logic for a new store owner.
     * Validates the input fields, checks if the email is already registered,
     * and creates a new StoreOwner if the email is not already in use.
     *
     * @param name     The name of the store owner.
     * @param email    The email address of the store owner.
     * @param password The password chosen by the store owner.
     * @param tel      The telephone number of the store owner.
     * @param afm      The AFM (tax identification number) of the store owner.
     */
    public void SignUp(String name, String email, String password, String tel, String afm ) {
        if (view == null) {
            throw new IllegalStateException("View is not set in presenter.");
        }

        if(name.isEmpty() || email.isEmpty() || password.isEmpty() || tel.isEmpty() || afm.isEmpty()) {
            view.showErrorMessage("You have to complete all the required fields");
            return;
        }

        if (!validateEmail(email)) {
            view.showErrorMessage("The provided email is wrong. Please provide a correct email");
            return;
        }

        if (storeOwnerDAO == null) {
            throw new IllegalStateException("StoreOwnerDAO is not initialized.");
        }

        if (storeOwnerDAO.find(email) == null) {
            int id = latestId + 1;
            StoreOwner storeown =new StoreOwner(id ,afm, name, new EmailAddress(email), password, new TelephoneNumber(tel));
            storeOwnerDAO.save(storeown);
            currentUserDAO.save(storeown);
            StoreOwner storew = (StoreOwner) currentUserDAO.retrieve();
            view.successfullySignUp("The Sign up is successfully done!!" +  storew.getOwnerId());
        } else {
            view.showErrorMessage("You already have an account. Please select the Log in button");
        }
    }
}
