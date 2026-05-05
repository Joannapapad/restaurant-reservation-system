package com.example.reservation.dao;

import java.util.List;

import com.example.reservation.contact.EmailAddress;
import com.example.reservation.domain.Customer;

/**
 * DAO interface for the {@link Customer} class.
 */
public interface CustomerDAO {

    /**
     * Finds a customer by their username.
     * @param username The username of the customer.
     * @return The customer object or {@code null} if not found.
     */

    Customer find(String username);

    Customer findbyid(int customerid);

    /**
     * Saves a customer object to the data source. This can be used for both
     * creating new customers and updating existing ones.
     * @param customer The customer object to save.
     */
    void save(Customer customer);

    /**
     * Deletes a customer object from the data source.
     * @param customer The customer object to delete.
     */
    void delete(Customer customer);

    /**
     * Retrieves all customers from the data source.
     * @return A list of all customers.
     */
     List<Customer> findAll();

    int nextId();

}

