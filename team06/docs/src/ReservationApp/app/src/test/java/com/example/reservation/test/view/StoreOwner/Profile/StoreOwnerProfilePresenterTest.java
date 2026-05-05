package com.example.reservation.test.view.StoreOwner.Profile;

import static org.junit.Assert.assertEquals;

import com.example.reservation.MemoryDao.MemoryInitializer;
import com.example.reservation.contact.EmailAddress;
import com.example.reservation.contact.TelephoneNumber;
import com.example.reservation.dao.CurrentUserDAO;
import com.example.reservation.dao.StoreOwnerDAO;
import com.example.reservation.domain.StoreOwner;
import com.example.reservation.view.StoreOwner.Profile.StoreOwnerProfilePresenter;
import org.junit.Before;
import org.junit.Test;

public class StoreOwnerProfilePresenterTest {

        private StoreOwnerProfilePresenter presenter;
        private StoreOwnerProfileStub viewStub;
        private CurrentUserDAO currentUserDAO;
        private MemoryInitializer dataHelper;
        private StoreOwnerDAO storeOwnerDAO;

        @Before
        public void setUp() {
            dataHelper = new MemoryInitializer();
            dataHelper.prepareData(); // Prepare test data

            storeOwnerDAO = dataHelper.getStoreOwnerDAO(); // Get the CustomerDAO from the dataHelper
            currentUserDAO = dataHelper.getCurrentUserDAO(); // Get the CurrentUserDAO from the dataHelper

            // Set up initial customer data (simulated with MemoryInitializer)
            StoreOwner owner = new StoreOwner(3002, "654321", "Owner2", new EmailAddress("owner2@example.com"), "ownerpass2", new TelephoneNumber("0987654321"));

            currentUserDAO.save(owner); // Save customer to the CurrentUserDAO
            // Initialize the viewStub and presenter
            viewStub = new StoreOwnerProfileStub();
            presenter = new StoreOwnerProfilePresenter(viewStub,currentUserDAO);

            // Optionally, set initial values in the viewStub to simulate the state
            viewStub.setName(owner.getUserName());
            viewStub.id = String.valueOf(owner.getOwnerId());
            viewStub.email = owner.getEmail().getAddress();
            viewStub.phone = owner.getNumber().getTelephoneNumber();
            viewStub.password = owner.getPassword();
        }

        @Test
        public void testViewProfileData() {
            // Ensure that the profile data is correctly passed to the view
            assertEquals("Owner2", viewStub.getName());  // Check customer name
            assertEquals("3002", viewStub.id);  // Check customer ID
            assertEquals("owner2@example.com", viewStub.email);  // Check customer email
            assertEquals("0987654321", viewStub.phone);  // Check customer phone
            assertEquals("ownerpass2", viewStub.password);  // Check customer password
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
            assertEquals("Successful delete 'Owner2'!", viewStub.getDeleteMessage());
        }


        @Test
        public void testOnShowToast() {
            presenter.onShowToast("Profile updated");

            assertEquals("Profile updated", viewStub.getToastMessage());
        }
    }


