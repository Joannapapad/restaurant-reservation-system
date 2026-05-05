package com.example.reservation.view.Customer.Edit;

import com.example.reservation.contact.EmailAddress;
import com.example.reservation.contact.TelephoneNumber;
import com.example.reservation.dao.CurrentUserDAO;
import com.example.reservation.dao.CustomerDAO;
import com.example.reservation.domain.Customer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Presenter class for managing the logic of editing customer details.
 */
public class CustomerEditPresenter {

    private CustomerEditView view;
    private CustomerDAO customerDAO;
    private Customer customer;
    private CurrentUserDAO currentUserDAO;

    /**
     * Validates the format of an email address using a regular expression.
     *
     * @param email The email address to validate.
     * @return True if the email format is valid, false otherwise.
     */
    private boolean validateEmail(String email){
        Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * Constructor for the presenter.
     *
     * @param view           The associated view for editing customer details.
     * @param currentUserDAO DAO for managing the current user.
     */
    public CustomerEditPresenter(CustomerEditView view, CurrentUserDAO currentUserDAO){
        this.view = view;
        this.currentUserDAO = currentUserDAO;
        customer = (Customer) currentUserDAO.retrieve();
    }

    /**
     * Handles the save action when editing a customer's details.
     * Validates input fields and updates the customer object if all validations pass.
     */
    public void onSaveBorrower(){
        String name = view.getName();
        String username = view.getUsername();
        String tel = view.getTel();
        String email = view.getEmail();
        String password = view.getPassword();

        if (name.isEmpty() || username.isEmpty() || tel.isEmpty() || email.isEmpty() || password.isEmpty()){
            view.showErrorMessage("all fields must be filled");
            return;
        }

        if (!validateEmail(email)) {
            view.showErrorMessage("The email is not in a correct form.");
            return;
        }

        //update customer obj

        customer.setName(name);
        customer.setUserName(username);
        customer.setNumber(new TelephoneNumber(tel));
        customer.setEmail(new EmailAddress(email));
        customer.setPassword(password);

        view.successfullyFinishActivity("Successfully updated customer '" + name + "'!");
    }

}
