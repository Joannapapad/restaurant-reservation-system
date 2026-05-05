package com.example.reservation.test.view.Customer.Login;

import com.example.reservation.dao.CustomerDAO;
import com.example.reservation.dao.CurrentUserDAO;
import com.example.reservation.domain.User;
import com.example.reservation.view.Customer.LogIn.CustomerLogInPresenter;
import com.example.reservation.MemoryDao.MemoryInitializer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerLoginPresenterTest {

    private CustomerLogInPresenter presenter;
    private CustomerLoginStub viewStub;
    private CustomerDAO customerDAO;
    private CurrentUserDAO currentUserDAO;
    private MemoryInitializer dataHelper;

    @Before
    public void setUp() {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        // Get DAOs from dataHelper
        customerDAO = dataHelper.getCustomerDAO();
        currentUserDAO = dataHelper.getCurrentUserDAO();

        // Initialize the stub and presenter
        viewStub = new CustomerLoginStub();
        presenter = new CustomerLogInPresenter();
        presenter.setView(viewStub);
        presenter.setCustomerDAO(customerDAO);
        presenter.setCurrentUserDAO(currentUserDAO);
    }

    @Test
    public void testLoginWithEmptyFields() {
        presenter.LogIn("", "");
        assertEquals(1, viewStub.getErrorCount());
        assertEquals("You have to complete all the required fields", viewStub.getErrorMessage());
    }

    @Test
    public void testLoginWithNonExistentAccount() {
        presenter.LogIn("nonexistent@email.com", "password123");
        assertEquals(1, viewStub.getErrorCount());
        assertEquals("You don't have an account. Please select the Sign Up button", viewStub.getErrorMessage());
    }

    @Test
    public void testLoginSuccess() {
        // Use an email and password that exist in the dataHelper's mock data
        presenter.LogIn("existing@email.com", "password123");
        assertEquals(1, viewStub.getSuccessCount());
        assertEquals("The log in was successful", viewStub.getSuccessMessage());
    }

}
