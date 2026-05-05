package com.example.reservation.view.StoreOwner.SignUp;


import androidx.lifecycle.ViewModel;

import com.example.reservation.MemoryDao.CurrentUserDAOMemory;
import com.example.reservation.MemoryDao.StoreOwnerDAOMemory;

/**
 * ViewModel for the Store Owner Sign-Up feature.
 * Acts as a bridge between the View (Activity or Fragment) and the Presenter,
 * ensuring proper initialization and separation of concerns.
 */
public class StoreOwnerSignUpViewModel extends ViewModel {
    private  StoreOwnerSignUpPresenter presenter;
    /**
     * Constructor for StoreOwnerSignUpViewModel.
     * Initializes the Presenter and sets its dependencies, including the DAOs
     * for StoreOwner and CurrentUser.
     */
    public StoreOwnerSignUpViewModel() {
        presenter = new StoreOwnerSignUpPresenter();
        presenter.setStoreOwnerDAO(new StoreOwnerDAOMemory());
        presenter.setCurrentUserDAO(CurrentUserDAOMemory.getInstance());
    }
    /**
     * Retrieves the Presenter associated with this ViewModel.
     *
     * @return The StoreOwnerSignUpPresenter instance.
     */
    public StoreOwnerSignUpPresenter getPresenter() {
        return presenter;
    }
}


