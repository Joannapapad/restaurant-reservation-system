package com.example.reservation.test.view.Customer.SignUp;

import com.example.reservation.MemoryDao.CurrentUserDAOMemory;
import com.example.reservation.MemoryDao.CustomerDAOMemory;
import com.example.reservation.dao.CustomerDAO;
import com.example.reservation.dao.CurrentUserDAO;
import com.example.reservation.domain.Customer;
import com.example.reservation.contact.EmailAddress;
import com.example.reservation.contact.TelephoneNumber;
import com.example.reservation.view.Customer.SignUp.CustomerSignUpPresenter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SignUpPresenterTest {

    private CustomerSignUpPresenter presenter;
    private CustomerSignUpStub viewStub;
    private CustomerDAO customerDAO;
    private CurrentUserDAO currentUserDAO;

    @Before
    public void setUp() {
        // Initialize the DAOs and view stub
        customerDAO = new CustomerDAOMemory();
        currentUserDAO = CurrentUserDAOMemory.getInstance();
        viewStub = new CustomerSignUpStub();

        // Initialize the presenter with dependencies
        presenter = new CustomerSignUpPresenter();
        presenter.setView(viewStub);
        presenter.setCustomerDAO(customerDAO);
        presenter.setCurrentUserDAO(currentUserDAO);
    }

    @Test
    public void testSignUp_Success() {

        presenter.SignUp("Μαρία Δημητροπούλου", "mariadim", "mardim@example.com", "password456", "1234567890");

        // Check for the success message
        assertEquals("The Sign up is successfully done!!", viewStub.getSuccessMessage());

        // Ensure the customer is saved in the DAO (simulate fetching the customer)
        Customer customer = customerDAO.find("john@example.com");
        assertNotNull(customer);
        assertEquals("Μαρία Δημητροπούλου", customer.getName());
        assertEquals("Μαρια", customer.getUserName());
        assertEquals("maria@example.com", customer.getEmail().getAddress());
    }

    @Test
    public void testSignUp_Failure_EmptyFields() {
        // Simulate a sign-up with empty fields
        presenter.SignUp("", "username", "email@example.com", "password123", "1234567890");

        // Check for the error message
        assertEquals("You have to complete all the required fields", viewStub.getErrorMessage());
    }

    @Test
    public void testSignUp_Failure_InvalidEmail() {
        // Simulate a sign-up with invalid email
        presenter.SignUp("John Doe", "johndoe", "invalid-email", "password123", "1234567890");

        // Check for the error message
        assertEquals("the provided email is wrong. Please provide a correct email", viewStub.getErrorMessage());
    }

    @Test
    public void testSignUp_Failure_DuplicateEmail() {
        // First sign up with the same email
        presenter.SignUp("John Doe", "johndoe", "john@example.com", "password123", "1234567890");

        // Try to sign up again with the same email
        presenter.SignUp("Jane Doe", "janedoe", "john@example.com", "newpassword", "0987654321");

        // Check for the error message
        assertEquals("You already have an account. Please select the Log in button", viewStub.getErrorMessage());
    }
}
