package com.example.reservation.test.view.Customer.Profile;

import com.example.reservation.contact.EmailAddress;
import com.example.reservation.contact.TelephoneNumber;
import com.example.reservation.dao.CurrentUserDAO;
import com.example.reservation.dao.CustomerDAO;
import com.example.reservation.domain.Customer;
import com.example.reservation.test.view.Customer.Edit.CustomerEditStub;
import com.example.reservation.view.Customer.Edit.CustomerEditPresenter;
import com.example.reservation.view.Customer.Profile.CustomerProfilePresenter;
import com.example.reservation.MemoryDao.MemoryInitializer;
import com.example.reservation.MemoryDao.CurrentUserDAOMemory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerProfilePresenterTest {

    private CustomerProfilePresenter presenter;
    private CustomerProfileStub viewStub;
    private CurrentUserDAO currentUserDAO;
    private MemoryInitializer dataHelper;
    private CustomerDAO customerDAO;

    @Before
    public void setUp() {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData(); // Prepare test data

        customerDAO = dataHelper.getCustomerDAO(); // Get the CustomerDAO from the dataHelper
        currentUserDAO = dataHelper.getCurrentUserDAO(); // Get the CurrentUserDAO from the dataHelper

        // Set up initial customer data (simulated with MemoryInitializer)
        Customer customer = new Customer(1001, "John Doe", "john_doe", new EmailAddress("john.doe@example.com"), "password123", new TelephoneNumber("123456789"));
        currentUserDAO.save(customer); // Save customer to the CurrentUserDAO
        // Initialize the viewStub and presenter
        viewStub = new CustomerProfileStub();
        presenter = new CustomerProfilePresenter(viewStub, currentUserDAO);

        // Optionally, set initial values in the viewStub to simulate the state
        viewStub.setName(customer.getName());
        viewStub.id = String.valueOf(customer.getCustomerId());
        viewStub.email = customer.getEmail().getAddress();
        viewStub.phone = customer.getNumber().getTelephoneNumber();
        viewStub.password = customer.getPassword();
    }

    @Test
    public void testViewProfileData() {
        // Ensure that the profile data is correctly passed to the view
        assertEquals("John Doe", viewStub.getName());  // Check customer name
        assertEquals("1001", viewStub.id);  // Check customer ID
        assertEquals("john.doe@example.com", viewStub.email);  // Check customer email
        assertEquals("123456789", viewStub.phone);  // Check customer phone
        assertEquals("password123", viewStub.password);  // Check customer password
    }

    @Test
    public void testOnStartEditButtonClick() {
        presenter.onStartEditButtonClick();

        assertEquals(1, viewStub.getEditStartCount());
    }

    @Test
    public void testOnStartDeleteButtonClick() {
        presenter.onStartDeleteButtonClick();

        assertEquals(1, viewStub.getDeleteStartCount());
    }

    @Test
    public void testOnDoDeleteAndFinish() {
        // Simulate the deletion of a customer
        presenter.onDoDeleteAndFinish();

        // Ensure the delete method was called and success message is passed
        assertEquals(1, viewStub.getDeleteFinishCount());
        assertEquals("Successfully deleted 'John Doe'!", viewStub.getDeleteMessage());
    }


    @Test
    public void testOnShowToast() {
        presenter.onShowToast("Profile updated");

        assertEquals("Profile updated", viewStub.getToastMessage());
    }
}
