package com.example.reservation.view.StoreOwner.LogIn;
import androidx.lifecycle.ViewModel;

import com.example.reservation.MemoryDao.CurrentUserDAOMemory;
import com.example.reservation.MemoryDao.StoreOwnerDAOMemory;

/**
 * ViewModel class for handling the login process of a store owner.
 * This class acts as a bridge between the UI (StoreOwnerLogInView) and the business logic (StoreOwnerLogInPresenter).
 */
public class StoreOwnerLogInViewModel extends ViewModel {

    private StoreOwnerLogInPresenter presenter;

    /**
     * Constructor that initializes the presenter and sets up necessary dependencies.
     * It also configures the DAOs for store owner and current user.
     */
    public StoreOwnerLogInViewModel() {
        presenter = new StoreOwnerLogInPresenter();
        presenter.setStoreOwnerDAO(new StoreOwnerDAOMemory());
        presenter.setCurrentUserDAO(CurrentUserDAOMemory.getInstance());

    }

    /**
     * Gets the presenter that handles the store owner login logic.
     *
     * @return The StoreOwnerLogInPresenter instance.
     */
    public StoreOwnerLogInPresenter getPresenter() {
        return presenter;
    }
}
