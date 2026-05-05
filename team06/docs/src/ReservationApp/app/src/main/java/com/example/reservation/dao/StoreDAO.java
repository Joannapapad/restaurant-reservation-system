package com.example.reservation.dao;

import com.example.reservation.domain.Store;

import java.util.List;
import java.util.Set;

/**
 * Interface for Store Data Access Object (DAO).
 * Provides methods to perform CRUD operations and additional queries on Store entities.
 */
public interface StoreDAO {

    /**
     * Retrieves all stores in the system.
     *
     * @return A list of all stores.
     */
    List<Store> findAll();

    /**
     * Finds a store by its unique identifier.
     *
     * @param storeId The ID of the store to be retrieved.
     * @return The store with the given ID, or null if no store is found.
     */
    Store find(int storeId);

    /**
     * Deletes a specified store from the system.
     *
     * @param storeId The store to be deleted.
     */
    void delete(Store storeId);

    /**
     * Saves a store to the system. This method can be used for both adding
     * a new store and updating an existing one.
     *
     * @param store The store entity to be saved.
     */
    void save(Store store);

    /**
     * Finds all stores owned by a specific owner.
     *
     * @param ownerId The ID of the store owner.
     * @return A list of stores owned by the specified owner.
     */
    List<Store> findAllByOwnerId(int ownerId);

    /**
     * Adds a new store to the system.
     *
     * @param store The store entity to be added.
     */
    void addStore(Store store);

    /**
     * Finds stores located at a specific address.
     *
     * @param address The location address to search for stores.
     * @return A set of stores located at the given address.
     */
    Set<Store> findByLocation(String address);

    /**
     * Finds stores belonging to a specific category.
     *
     * @param category The category of stores to search for.
     * @return A set of stores in the specified category.
     */
    Set<Store> findByCategory(String category);

    /**
     * Retrieves the next available unique identifier for a store.
     *
     * @return The next unique store ID.
     */
    int nextId();

}
