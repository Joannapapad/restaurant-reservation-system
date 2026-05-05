package com.example.reservation.view.StoreOwner.Edit;

import com.example.reservation.contact.EmailAddress;
import com.example.reservation.contact.TelephoneNumber;
import com.example.reservation.dao.CurrentUserDAO;
import com.example.reservation.dao.StoreOwnerDAO;
import com.example.reservation.domain.StoreOwner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Presenter class that handles the business logic for editing a store owner's details.
 * It interacts with the StoreOwner data model and updates the store owner's information.
 */
public class StoreOwnerEditPresenter {

    private StoreOwnerEditView view;
    private StoreOwnerDAO storeOwnerDAO;
    private StoreOwner storeOwner;
    private CurrentUserDAO currentUserDAO;

    /**
     * Validates if the given email matches the standard email pattern.
     *
     * @param email The email string to be validated.
     * @return true if the email is valid, false otherwise.
     */
    private boolean validateEmail(String email)
    {
        Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * Constructor to initialize the presenter with the view and current user data access object.
     * The storeOwner object is retrieved from the current user DAO.
     *
     * @param view           The view interface used to interact with the user.
     * @param currentUserDAO The DAO for retrieving the current store owner.
     */
    public StoreOwnerEditPresenter(StoreOwnerEditView view, CurrentUserDAO currentUserDAO) {
        this.view = view;
        this.currentUserDAO = currentUserDAO;

        storeOwner = (StoreOwner) currentUserDAO.retrieve();
    }

    /**
     * Handles the save action when the store owner updates their details.
     * It validates the input data and updates the store owner's information.
     */
    public void onSaveBorrower() {

        String name = view.getName();
        String phone = view.getPhone();
        String email = view.getEmail();
        String password = view.getPassword();
        String afm = view.getAfm();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || afm.isEmpty() || password.isEmpty()) {
            view.showErrorMessage("All fields must be filled.");
            return;
        }

        if (!validateEmail(email)) {
            view.showErrorMessage("The email is not in a correct form.");
            return;
        }

        // Update the storeOwner object
        storeOwner.setUserName(name);
        storeOwner.setAFM(afm);
        storeOwner.setEmail(new EmailAddress(email));
        storeOwner.setNumber(new TelephoneNumber(phone));
        storeOwner.setPassword(password);

        // Here you should persist the changes, e.g., storeOwnerDAO.update(storeOwner);

        view.successfullyFinishActivity("Successfully updated store owner '" + name + "'!");
    }

}
