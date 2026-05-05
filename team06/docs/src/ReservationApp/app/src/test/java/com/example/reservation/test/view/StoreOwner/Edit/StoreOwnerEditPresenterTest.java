package com.example.reservation.test.view.StoreOwner.Edit;

import static org.junit.Assert.assertEquals;

import com.example.reservation.MemoryDao.MemoryInitializer;
import com.example.reservation.contact.EmailAddress;
import com.example.reservation.contact.TelephoneNumber;
import com.example.reservation.dao.CurrentUserDAO;
import com.example.reservation.dao.CustomerDAO;
import com.example.reservation.dao.StoreOwnerDAO;
import com.example.reservation.domain.Customer;
import com.example.reservation.domain.StoreOwner;
import com.example.reservation.test.view.Customer.Edit.CustomerEditStub;
import com.example.reservation.view.Customer.Edit.CustomerEditPresenter;
import com.example.reservation.view.StoreOwner.Edit.StoreOwnerEditPresenter;

import org.junit.Before;
import org.junit.Test;

public class StoreOwnerEditPresenterTest {

    private StoreOwnerEditPresenter presenter;
    private StoreOwnerEditViewStub viewStub;
    private StoreOwnerDAO storeOwnerDAO;
    private CurrentUserDAO currentUserDAO;
    private MemoryInitializer dataHelper; // Use MemoryInitializer (dataHelper)

    @Before
    public void setUp() {
        // Initialize dataHelper
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData(); // Prepare test data

        // Initialize stub and DAOs from the dataHelper
        viewStub = new StoreOwnerEditViewStub();
        storeOwnerDAO = dataHelper.getStoreOwnerDAO(); // Get the CustomerDAO from the dataHelper
        currentUserDAO = dataHelper.getCurrentUserDAO(); // Get the CurrentUserDAO from the dataHelper

        // Initialize presenter
        presenter = new StoreOwnerEditPresenter(viewStub, currentUserDAO);

        // Set up initial customer data (simulated with MemoryInitializer)
//        StoreOwner owner = new StoreOwner( 3002, "654321", "Owner2", new EmailAddress("owner2@example.com"), "ownerpass2", new TelephoneNumber("0987654321"));
//        currentUserDAO.save(owner); // Save customer to the CurrentUserDAO
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
        assertEquals("All fields must be filled.", viewStub.getErrorMessage());
    }

    @Test
    public void testSaveWithInvalidEmail() {
        // Simulate invalid email
        viewStub.setName("Owner2");
        viewStub.setTel("0987654321");
        viewStub.setEmail("invalid_email");
        viewStub.setPassword("ownerpass2");
        viewStub.setAfm("654321");

        presenter.onSaveBorrower();

        assertEquals(1, viewStub.getErrorCount());
        assertEquals("The email is not in a correct form.", viewStub.getErrorMessage());
    }

//    @Test
//    public void testSuccessfulSave() {
//
//
//        // Simulate valid input for customer edit
//        viewStub.setName("Owner2");
//        viewStub.setTel("0987654321");
//        viewStub.setEmail("owner2@example.com");
//        viewStub.setPassword("ownerpass2");
//        viewStub.setAfm("654321");
//
//        presenter.onSaveBorrower();
//
//        assertEquals(1, viewStub.getSuccessCount());
//
//    }

}