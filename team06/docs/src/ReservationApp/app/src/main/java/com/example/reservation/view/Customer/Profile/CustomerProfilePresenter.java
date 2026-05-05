package com.example.reservation.view.Customer.Profile;

import com.example.reservation.MemoryDao.CustomerDAOMemory;
import com.example.reservation.dao.CurrentUserDAO;
import com.example.reservation.dao.CustomerDAO;
import com.example.reservation.domain.Customer;

/**
 * Presenter class for the CustomerProfileActivity.
 * Handles interactions between the view (CustomerProfileView) and the model (Customer, CurrentUserDAO, CustomerDAO).
 */
public class CustomerProfilePresenter {

    private CustomerProfileView view;
    private CurrentUserDAO currentUserDAO;
    private CustomerDAO customerDAO;

    private Customer customer;

    /**
     * Constructor for the CustomerProfilePresenter.
     * Initializes the presenter with the view and the current user DAO.
     * Retrieves the current customer from the DAO and updates the view with the customer's details.
     *
     * @param view           The CustomerProfileView interface implemented by the activity.
     * @param currentUserDAO The DAO responsible for managing the currently logged-in user.
     */
    public CustomerProfilePresenter(CustomerProfileView view, CurrentUserDAO currentUserDAO) {
        this.view = view;
        this.currentUserDAO = currentUserDAO;
        customerDAO = new CustomerDAOMemory();
        // Retrieve the current customer from the DAO
        customer = (Customer) currentUserDAO.retrieve();

        if (customer != null) {
            view.setId(String.valueOf(customer.getCustomerId()));
            view.setName(customer.getName());
            view.setEmail(customer.getEmail().getAddress());
            view.setPhone(customer.getNumber().getTelephoneNumber());
            view.setPassword(customer.getPassword());
        }
    }

    /**
     * Handles the click event for the "Edit" button.
     * Passes the current customer's details to the view to start the edit activity.
     */
    public void onStartEditButtonClick() {
        view.startEditActivity(
                customer.getCustomerId(),
                customer.getName(),
                customer.getEmail().getAddress(),
                customer.getNumber().getTelephoneNumber(),
                customer.getPassword()
        );
    }

    /**
     * Handles the click event for the "Delete" button.
     * Prompts the user for confirmation before deleting the account.
     */
    public void onStartDeleteButtonClick() {
        view.startDelete("Delete Account?", "Your account will be permanently deleted!");
    }

    /**
     * Deletes the current customer's account.
     * Removes the customer from both the current user DAO and the customer DAO.
     * Informs the view to navigate back to the login screen with a success message.
     */
    public void onDoDeleteAndFinish() {
        String message = "Successfully deleted '" + customer.getName() + "'!";
        currentUserDAO.delete(customer);
        customerDAO.delete(customer);
        view.doDeleteAndFinish(message);
    }

    /**
     * Displays a toast message in the view.
     *
     * @param value The message to be displayed in the toast.
     */
    public void onShowToast(String value) {
        view.showToast(value);
    }
}
