package com.example.reservation.view.Customer.LogIn;

import androidx.lifecycle.ViewModel;
import com.example.reservation.MemoryDao.CustomerDAOMemory;
import com.example.reservation.MemoryDao.CurrentUserDAOMemory;

/**
 * CustomerLogInViewModel serves as the ViewModel for the customer login functionality.
 * It initializes the presenter and sets up the required DAOs.
 */
public class CustomerLogInViewModel extends ViewModel{

    private CustomerLogInPresenter presenter;
    /**
     * Constructor for CustomerLogInViewModel.
     * Initializes the CustomerLogInPresenter and sets the required DAOs.
     */
    public CustomerLogInViewModel(){
        presenter = new CustomerLogInPresenter();
        presenter.setCustomerDAO(new CustomerDAOMemory());
        presenter.setCurrentUserDAO(CurrentUserDAOMemory.getInstance());
    }

    /**
     * Retrieves the CustomerLogInPresenter associated with this ViewModel.
     *
     * @return The instance of CustomerLogInPresenter.
     */
    public CustomerLogInPresenter getPresenter() {
        return presenter;
    }
}
