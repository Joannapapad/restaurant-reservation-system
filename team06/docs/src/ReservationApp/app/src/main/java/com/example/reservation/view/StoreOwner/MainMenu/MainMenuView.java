package com.example.reservation.view.StoreOwner.MainMenu;

import com.example.reservation.domain.Store;

import java.util.List;

/**
 * Interface representing the view in the MainMenu screen of the app.
 * This view is responsible for displaying the list of stores owned by the logged-in store owner,
 * as well as handling cases when no stores are available.
 */
public interface MainMenuView {
    /**
     * Displays a list of stores on the screen.
     *
     * @param stores A list of {@link Store} objects representing the stores owned by the logged-in store owner.
     *               The list will be shown in a RecyclerView or similar component.
     */
    void displayStores(List<Store> stores);

    /**
     * Displays a message to the user indicating that no stores are available for the logged-in store owner.
     * This message will be shown when the list of stores is empty.
     */
    void showNoStoresMessage();
}
