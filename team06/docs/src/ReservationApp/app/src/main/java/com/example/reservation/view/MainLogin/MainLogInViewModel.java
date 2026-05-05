package com.example.reservation.view.MainLogin;

import androidx.lifecycle.ViewModel;

/**
 * ViewModel for the Main Login functionality.
 * It interacts with the `MainLoginPresenter` to handle the logic behind user login.
 */
public class MainLogInViewModel extends ViewModel {
    private MainLoginPresenter presenter;

    /**
     * Default constructor. Initializes the `MainLoginPresenter` instance.
     */
    public MainLogInViewModel () {
        presenter = new MainLoginPresenter();
    }

    /**
     * Gets the presenter for managing the login logic.
     *
     * @return The `MainLoginPresenter` instance responsible for login logic.
     */
    public MainLoginPresenter getPresenter() {
        return presenter;
    }

}

