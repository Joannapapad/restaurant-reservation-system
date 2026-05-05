package com.example.reservation.view.Customer.LogIn;

import com.example.reservation.dao.CustomerDAO;
import com.example.reservation.dao.CurrentUserDAO;
import com.example.reservation.domain.User;

public class CustomerLogInPresenter {
    private CustomerLoginView view;
    private CustomerDAO customerDAO;
    private CurrentUserDAO currentUserDAO;

    /**
     * Sets the view for the presenter.
     *
     * @param view The view interface that the presenter will interact with.
     */
    public void setView(CustomerLoginView view){
        this.view = view;
    }

    /**
     * Gets the view associated with the presenter.
     *
     * @return The view interface that the presenter is interacting with.
     */
    public CustomerLoginView getView(){
        return view;
    }

    /**
     * Sets the CustomerDAO instance to access customer data.
     *
     * @param customerDAO The CustomerDAO instance to set.
     */
    public void setCustomerDAO (CustomerDAO customerDAO){
        this.customerDAO = customerDAO;
    }

    /**
     * Sets the CurrentUserDAO instance to manage the current logged-in user.
     *
     * @param currentUserDAO The CurrentUserDAO instance to set.
     */
    public void setCurrentUserDAO(CurrentUserDAO currentUserDAO){
        this.currentUserDAO = currentUserDAO;
    }

    /**
     * Handles the login process by verifying the user's credentials.
     *
     * @param email    The email of the user attempting to log in.
     * @param password The password of the user attempting to log in.
     */
    public void LogIn(String email, String password){

        // Check if the email or password is empty
        if (email == null || password.isEmpty()){
            view.showErrorMessage("You have to complete all the required fields");
            return;
        }

        // Check if the email is registered in the system
        if (customerDAO.find(email) == null){
            view.showErrorMessage("You don't have an account. Please select the Sign Up button");
        }
        else{
            User customer = customerDAO.find(email);
            currentUserDAO.save(customer);
            view.succesfullyLogedIn("The log in was successful");
        }
    }
}
