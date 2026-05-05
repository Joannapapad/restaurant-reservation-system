package com.example.reservation.view.Customer.SignUp;


import com.example.reservation.contact.EmailAddress;
import com.example.reservation.contact.TelephoneNumber;
import com.example.reservation.dao.CurrentUserDAO;
import com.example.reservation.dao.CustomerDAO;
import com.example.reservation.domain.Customer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CustomerSignUpPresenter {

    private CustomerSignUpView view;
    private CustomerDAO customerDAO;
    private CurrentUserDAO currentUserDAO;
    private int id = 0;


    private boolean validateEmail(String email){
        Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * Set the view for this presenter
     * @param view the view interface (CustomerSignUpActivity)
     */
    public void setView(CustomerSignUpView view){
        this.view = view;
    }

    /**
     * Get the current view associated with this presenter
     * @return the view interface (CustomerSignUpActivity)
     */
    public CustomerSignUpView getView(){
        return view;
    }

    /**
     * Set the CustomerDAO to access and store customer data
     * @param customerDAO the CustomerDAO instance
     */
    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    /**
     * Set the CurrentUserDAO to store the current logged-in user
     * @param currentUserDAO the CurrentUserDAO instance
     */
    public void setCurrentUserDAO (CurrentUserDAO currentUserDAO) {this.currentUserDAO = currentUserDAO;}

    /**
     * Perform the sign-up operation
     * @param name the customer's full name
     * @param username the customer's chosen username
     * @param email the customer's email address
     * @param password the customer's chosen password
     * @param number the customer's phone number
     */

    public void SignUp(  String name, String username, String email, String password, String number){

        if (view == null) {
            throw new IllegalStateException("View is not set in presenter.");
        }

        if (username.isEmpty() || name.isEmpty() || email.isEmpty() || password.isEmpty() || number.isEmpty()){
            view.showErrorMessage("You have to complete all the required fields");
            return;
        }

        if (!validateEmail(email)){
            view.showErrorMessage("the provided email is wrong. Please provide a correct email");
            return;
        }

        if (customerDAO == null) {
            throw new IllegalStateException("CustomerDAO is not initialized.");
        }

        if(customerDAO.find(email) == null){
            id ++;
            customerDAO.save(new Customer(id,name,username, new EmailAddress(email), password, new TelephoneNumber(number)));
            currentUserDAO.save(new Customer(id,name,username, new EmailAddress(email), password, new TelephoneNumber(number)));
            view.successfullySignUp("The Sign up is successfully done!!");
        }
        else{
            view.showErrorMessage("You already have an account. Please select the Log in button");
        }
    }

}
