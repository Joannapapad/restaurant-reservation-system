package com.example.reservation.view.Customer.SignUp;

import androidx.lifecycle.ViewModel;
import com.example.reservation.MemoryDao.CurrentUserDAOMemory;
import com.example.reservation.MemoryDao.CustomerDAOMemory;
/**
 * ViewModel for managing the customer sign-up process.
 * This class communicates between the UI (Activity/Fragment) and the presenter.
 */
public class CustomerSignUpViewModel extends ViewModel{

    private  CustomerSignUpPresenter presenter;

    /**
     * Constructor that initializes the presenter and its dependencies.
     */
    public CustomerSignUpViewModel(){

        presenter = new CustomerSignUpPresenter();
        presenter.setCustomerDAO(new CustomerDAOMemory());
        presenter.setCurrentUserDAO(CurrentUserDAOMemory.getInstance());

    }

    /**
     * Gets the presenter instance.
     *
     * @return The instance of the CustomerSignUpPresenter.
     */
    public CustomerSignUpPresenter getPresenter(){
        return presenter;
    }

}
