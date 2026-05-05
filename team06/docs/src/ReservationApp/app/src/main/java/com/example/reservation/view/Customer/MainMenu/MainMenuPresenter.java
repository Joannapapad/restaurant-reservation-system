package com.example.reservation.view.Customer.MainMenu;

import com.example.reservation.domain.Store;
import com.example.reservation.domain.SearchStore;

import java.util.List;
import java.util.ArrayList;

/**
 * MainMenuPresenter handles the business logic for the MainMenu view.
 * It processes search queries and filters stores based on location, category, and name.
 */
public class MainMenuPresenter {
    private MainMenuView view;
    private SearchStore searchStore;

    /**
     * Constructs a MainMenuPresenter with a given view and list of stores.
     *
     * @param view   The MainMenuView interface to communicate with the UI.
     * @param stores The list of stores to be used for search and filtering.
     */
    public MainMenuPresenter(MainMenuView view, List<Store> stores) {
        this.view = view;
        this.searchStore = new SearchStore(stores);
    }

    /**
     * Sets the MainMenuView instance for the presenter.
     *
     * @param view The MainMenuView interface to communicate with the UI.
     */
    public void setView(MainMenuView view) {
        this.view = view;
    }

    /**
     * Performs a search operation based on the provided query, location filter, and category filter.
     * It retrieves filtered stores and updates the view with the results.
     *
     * @param query          The search query to match store names.
     * @param locationFilter The location filter to match store locations.
     * @param categoryFilter The category filter to match store types.
     */
    public void search(String query, String locationFilter, String categoryFilter) {
        // Start with the full list of stores
        List<Store> filteredStores = searchStore.getAllStores();

        // Apply filters in sequence
        if (!query.isEmpty()) {
            filteredStores = searchStore.searchByName(query, filteredStores);
        }
        if (!locationFilter.isEmpty()) {
            filteredStores = searchStore.filterByLocation(locationFilter, filteredStores);
        }
        if (!categoryFilter.isEmpty()) {
            filteredStores = searchStore.filterByType(categoryFilter, filteredStores);
        }

        // Convert the filtered stores to their names for updating the view
        List<String> results = new ArrayList<>();
        for (Store store : filteredStores) {
            results.add(store.getName());
        }

        view.updateResults(results);
    }

}
