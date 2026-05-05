package com.example.reservation.dao;

import com.example.reservation.domain.StoreOwner;
import com.example.reservation.domain.User;

/**
 * Interface for managing the currently logged-in user's data.
 * Provides methods for saving, deleting, and retrieving the current user.
 */
public interface CurrentUserDAO {

    /**
     * Saves the currently logged-in user.
     *
     * @param user The user object to be saved.
     */
    void save(User user);

    /**
     * Deletes the currently logged-in user.
     *
     * @param user The user object to be deleted.
     */
    void delete(User user);

    /**
     * Retrieves the currently logged-in user.
     *
     * @return The user object representing the currently logged-in user, or null if no user is logged in.
     */
    User retrieve();

}
