package com.example.reservation.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class for searching and filtering stores based on various criteria.
 */
public class SearchStore {
    private List<Store> allStores;

    /**
     * Constructor to initialize the SearchStore with a list of all stores.
     *
     * @param stores List of all available stores.
     */
    public SearchStore(List<Store> stores) {
        this.allStores = stores;
    }

    /**
     * Searches stores by their name, using a case-insensitive match for the query.
     *
     * @param query  The search query for store names.
     * @param stores The list of stores to search in.
     * @return A list of stores whose names contain the query string.
     */
    public List<Store> searchByName(String query, List<Store> stores) {
        List<Store> result = new ArrayList<>();
        for (Store store : stores) {
            if (store.getName().toLowerCase().contains(query.toLowerCase())) {
                result.add(store);
            }
        }
        return result;
    }

    /**
     * Filters stores by their location (city), using a case-insensitive match.
     *
     * @param location The city to filter stores by.
     * @param stores   The list of stores to filter.
     * @return A list of stores located in the specified city.
     */
    public List<Store> filterByLocation(String location, List<Store> stores) {
        List<Store> result = new ArrayList<>();
        for (Store store : stores) {
            if (store.getAddress().getCity().equalsIgnoreCase(location)) {
                result.add(store);
            }
        }
        return result;
    }

    /**
     * Filters stores by their type or category, using a case-insensitive match.
     *
     * @param category The category to filter stores by.
     * @param stores   The list of stores to filter.
     * @return A list of stores matching the specified category.
     */
    public List<Store> filterByType(String category, List<Store> stores) {
        List<Store> result = new ArrayList<>();
        for (Store store : stores) {
            if (store.getCategory().equalsIgnoreCase(category)) {
                result.add(store);
            }
        }
        return result;
    }

    /**
     * Retrieves the list of all stores managed by this class.
     *
     * @return A list of all available stores.
     */
    public List<Store> getAllStores() {
        return allStores;
    }

}
