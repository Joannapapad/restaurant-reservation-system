package com.example.reservation.dao;

import com.example.reservation.contact.EmailAddress;
import com.example.reservation.domain.StoreOwner;

import java.util.List;

/**
 * Interface for managing StoreOwner data access operations.
 * Provides methods to save, delete, find, and retrieve StoreOwner entities.
 */
public interface StoreOwnerDAO {

    /**
     * Saves a StoreOwner to the data source.
     *
     * @param storeOwner The StoreOwner object to save.
     */
    void save(StoreOwner storeOwner);

    /**
     * Deletes a StoreOwner from the data source.
     *
     * @param storeOwner The StoreOwner object to delete.
     */
    void delete(StoreOwner storeOwner);

    /**
     * Finds a StoreOwner by name.
     *
     * @param name The name of the StoreOwner to find.
     * @return The StoreOwner object if found, or null if no match is found.
     */
    StoreOwner find(String name);

    /**
     * Retrieves all StoreOwner entities from the data source.
     *
     * @return A list of all StoreOwner objects.
     */
    List<StoreOwner> findAll();

    /**
     * Retrieves all StoreOwner entities from the data source.
     *
     * @return A list of all StoreOwner objects.
     */
    int nextId();
}