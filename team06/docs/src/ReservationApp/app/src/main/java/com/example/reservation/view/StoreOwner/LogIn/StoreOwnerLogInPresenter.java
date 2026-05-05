package com.example.reservation.view.StoreOwner.LogIn;

import com.example.reservation.dao.CurrentUserDAO;
import com.example.reservation.dao.StoreOwnerDAO;
import com.example.reservation.domain.User;

/**
 * Presenter for handling the logic of the Store Owner Log In process.
 * This presenter validates login credentials, manages interactions with the model layer (StoreOwnerDAO, CurrentUserDAO),
 * and communicates the results to the StoreOwnerLogInView.
 */
public class StoreOwnerLogInPresenter {
    private StoreOwnerLogInView view;
    private CurrentUserDAO currentUserDAO;

    private StoreOwnerDAO storeOwnerDAO;

    /**
     * Sets the view interface for communication between the Presenter and View.
     *
     * @param view The StoreOwnerLogInView interface.
     */
    public void setView (StoreOwnerLogInView view) {this.view = view;}

    /**
     * Gets the view interface.
     *
     * @return The StoreOwnerLogInView interface.
     */
    public StoreOwnerLogInView getView() { return view;}

    /**
     * Sets the StoreOwnerDAO instance to interact with the store owner data.
     *
     * @param storeOwnerDAO The StoreOwnerDAO instance.
     */
    public void setStoreOwnerDAO (StoreOwnerDAO storeOwnerDAO) {this.storeOwnerDAO = storeOwnerDAO;}

    /**
     * Sets the CurrentUserDAO instance to handle the current logged-in user.
     *
     * @param currentUserDAO The CurrentUserDAO instance.
     */
    public void setCurrentUserDAO (CurrentUserDAO currentUserDAO) {this.currentUserDAO = currentUserDAO;}

    /**
     * Handles the logic for logging in a store owner.
     * It validates the input fields, checks the existence of the user in the database,
     * and either logs the user in or displays an error message.
     *
     * @param name     The username entered by the store owner.
     * @param password The password entered by the store owner.
     */
    public void LogIn(String name,  String password) {


        if(name.isEmpty() || password.isEmpty( )){
            view.showErrorMessage("You have to complete all the required fields");
            return;
        }

        if(storeOwnerDAO.find(name) == null){
            view.showErrorMessage("You don't have an account. Please select the Sign Up button");
        }else{
            User storeOwner = (User) storeOwnerDAO.find(name);
            currentUserDAO.save(storeOwner);
            view.succesfullyLogedIn("The log in was successful");
        }
    }
}

