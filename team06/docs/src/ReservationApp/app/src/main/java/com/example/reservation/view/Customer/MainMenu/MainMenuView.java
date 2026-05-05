package com.example.reservation.view.Customer.MainMenu;

import java.util.List;

/**
 * The MainMenuView interface defines the contract for the view in the MVP (Model-View-Presenter)
 * architecture pattern for the Main Menu screen in the application.
 *
 * The view is responsible for updating the displayed results based on the data provided
 * by the presenter.
 */
public interface MainMenuView {

    /**
     * Updates the results displayed on the Main Menu screen.
     *
     * @param results A list of strings representing the updated data to be shown on the screen.
     */
    void updateResults(List<String> results);
}
