package com.example.reservation.MemoryDao;

import com.example.reservation.dao.CurrentUserDAO;
import com.example.reservation.domain.Customer;
import com.example.reservation.domain.StoreOwner;
import com.example.reservation.domain.User;

import java.util.ArrayList;
import java.util.List;

public class CurrentUserDAOMemory implements CurrentUserDAO{
    private static CurrentUserDAOMemory instance;
    private User currentUser; // Or a list if needed

    protected static List<Customer> customers  = new ArrayList<>();

    protected static List<StoreOwner> owners  = new ArrayList<>();
    // Private constructor to prevent direct instantiation
    private CurrentUserDAOMemory() {}

    /**
     * Retrieves the singleton instance of CurrentUserDAOMemory.
     *
     * @return The singleton instance.
     */
    public static CurrentUserDAOMemory getInstance() {
        if (instance == null) {
            instance = new CurrentUserDAOMemory();
        }
        return instance;
    }

    /**
     * Saves the current user in memory.
     *
     * @param user The user to save as the current user.
     */
    public void save(User user) {
        this.currentUser = user;
    }

    /**
     * Deletes the specified user from memory. Currently, this method is not implemented.
     *
     * @param user The user to delete.
     */

    @Override
    public void delete(User user) {

    }

    /**
     * Retrieves the current user from memory.
     *
     * @return The current user, or null if no user is saved.
     */

    public User retrieve() {
        return this.currentUser;
    }
}

