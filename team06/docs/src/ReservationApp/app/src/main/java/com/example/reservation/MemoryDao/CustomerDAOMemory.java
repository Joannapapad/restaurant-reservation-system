package com.example.reservation.MemoryDao;

import com.example.reservation.contact.EmailAddress;
import com.example.reservation.dao.CustomerDAO;
import com.example.reservation.domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAOMemory implements CustomerDAO {

    // Static list to act as the in-memory database
    protected static List<Customer> customers = new ArrayList<>();

    /**
     * Deletes a customer from the in-memory list.
     *
     * @param customer The customer to delete.
     */
    @Override
    public void delete(Customer customer) {
        customers.remove(customer);
    }

    /**
     * Finds all customers in the in-memory list.
     *
     * @return A list of all customers.
     */
    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers);
    }

    @Override
    public int nextId() {
        return (customers.size() > 0 ? customers.get(customers.size()-1).getCustomerId()+1 : 1);
    }

    /**
     * Saves a customer to the in-memory list if it doesn't already exist.
     *
     * @param customer The customer to save.
     */
    @Override
    public void save(Customer customer) {
        if (!customers.contains(customer)) {
            customers.add(customer);
        }
    }

    /**
     * Finds a customer by their email.
     *
     * @param username The username of the customer to find.
     * @return The customer with the given email, or null if not found.
     */
    @Override
    public Customer find(String username) {
        for (Customer customer : customers) {
            if (customer.getUserName().equals(username)) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public Customer findbyid(int id) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == id) {
                return customer;
            }
        }
        return null;
    }
}
