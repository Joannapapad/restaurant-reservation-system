package com.example.reservation.test.view.Customer.Edit;

import com.example.reservation.contact.EmailAddress;
import com.example.reservation.contact.TelephoneNumber;
import com.example.reservation.dao.CurrentUserDAO;
import com.example.reservation.dao.CustomerDAO;
import com.example.reservation.domain.Customer;
import com.example.reservation.view.Customer.Edit.CustomerEditPresenter;
import com.example.reservation.MemoryDao.MemoryInitializer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerEditPresenterTest {

    private CustomerEditPresenter presenter;
    private CustomerEditStub viewStub;
    private CustomerDAO customerDAO;
    private CurrentUserDAO currentUserDAO;
    private MemoryInitializer dataHelper; // Use MemoryInitializer (dataHelper)

    @Before
    public void setUp() {
        // Initialize dataHelper
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData(); // Prepare test data

        // Initialize stub and DAOs from the dataHelper
        viewStub = new CustomerEditStub();
        customerDAO = dataHelper.getCustomerDAO(); // Get the CustomerDAO from the dataHelper
        currentUserDAO = dataHelper.getCurrentUserDAO(); // Get the CurrentUserDAO from the dataHelper

        // Initialize presenter
        presenter = new CustomerEditPresenter(viewStub, currentUserDAO);

        // Set up initial customer data (simulated with MemoryInitializer)
        Customer customer = new Customer(1001, "John Doe", "john_doe", new EmailAddress("john.doe@example.com"), "password123", new TelephoneNumber("123456789"));
        currentUserDAO.save(customer); // Save customer to the CurrentUserDAO
    }

    @Test
    public void testSaveWithEmptyFields() {
        // Simulate empty fields
        viewStub.setName("");
        viewStub.setUsername("");
        viewStub.setTel("");
        viewStub.setEmail("");
        viewStub.setPassword("");

        presenter.onSaveBorrower();

        assertEquals(1, viewStub.getErrorCount());
        assertEquals("all fields must be filled", viewStub.getErrorMessage());
    }

    @Test
    public void testSaveWithInvalidEmail() {
        // Simulate invalid email
        viewStub.setName("John Doe");
        viewStub.setUsername("john_doe");
        viewStub.setTel("123456789");
        viewStub.setEmail("invalid_email");
        viewStub.setPassword("password123");

        presenter.onSaveBorrower();

        assertEquals(1, viewStub.getErrorCount());
        assertEquals("The email is not in a correct form.", viewStub.getErrorMessage());
    }

    @Test
    public void testSuccessfulSave() {
        // Simulate valid input for customer edit
        viewStub.setName("John Doe");
        viewStub.setUsername("john_doe");
        viewStub.setTel("123456789");
        viewStub.setEmail("john.doe@example.com");
        viewStub.setPassword("newpassword");

        presenter.onSaveBorrower();

        assertEquals(1, viewStub.getSuccessCount());
        assertEquals("Successfully updated customer 'John Doe'!", viewStub.getSuccessMessage());
    }

}
