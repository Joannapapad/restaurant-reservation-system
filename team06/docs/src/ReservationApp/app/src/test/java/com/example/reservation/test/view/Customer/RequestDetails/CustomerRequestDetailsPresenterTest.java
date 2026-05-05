package com.example.reservation.test.view.Customer.RequestDetails;

import com.example.reservation.MemoryDao.CurrentUserDAOMemory;
import com.example.reservation.MemoryDao.RequestDAOMemory;
import com.example.reservation.domain.Customer;
import com.example.reservation.domain.Request;
import com.example.reservation.domain.ReservationStatus;
import com.example.reservation.view.Customer.RequestDetails.CustomerRequestDetailsPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerRequestDetailsPresenterTest {

    private CustomerRequestDetailsPresenter presenter;
    private CustomerRequestDetailsStub viewStub;
    private CurrentUserDAOMemory currentUserDAO;
    private RequestDAOMemory requestDAO;

    @Before
    public void setUp() {
        // Set up in-memory data storage
        currentUserDAO = CurrentUserDAOMemory.getInstance();
        requestDAO = new RequestDAOMemory();

        // Prepare mock customer and request
        Customer customer = new Customer(1001, "John Doe", "john_doe", null, null, null);
        currentUserDAO.save(customer);

        Request request = new Request(1,1002 ,customer.getCustomerId(), 5,ReservationStatus.PENDING, "Some request details");
        requestDAO.save(request);

        // Set up the view stub and presenter
        viewStub = new CustomerRequestDetailsStub();
        presenter = new CustomerRequestDetailsPresenter(viewStub, currentUserDAO, requestDAO);
    }

    @Test
    public void testLoadRequestDetails_success() {
        // Simulate loading the request details
        presenter.loadRequestDetails(1);

        // Verify that the request details were passed to the view
        assertNotNull(viewStub.getDisplayedRequest());
        assertEquals(1, viewStub.getDisplayedRequest().getReservationID());
        assertEquals("Some request details", viewStub.getDisplayedRequest().getComment());
    }

    @Test
    public void testLoadRequestDetails_requestNotFound() {
        // Simulate loading a non-existing request
        presenter.loadRequestDetails(999);

        // Verify that the error message is shown
        assertEquals("Error: Request not found.", viewStub.getErrorMessage());
    }

    @Test
    public void testCancelRequest_success() {
        // Simulate canceling the request
        presenter.loadRequestDetails(1);  // Load request first
        presenter.cancelRequest();

        // Verify that success message was shown
        assertEquals("Request canceled successfully.", viewStub.getSuccessMessage());

        assertNull(requestDAO.find(1));
    }

    @Test
    public void testCancelRequest_noRequestLoaded() {
        // Simulate canceling a request without loading it
        presenter.cancelRequest();

        // Verify that the error message is shown
        assertEquals("Error: Unable to cancel request.", viewStub.getErrorMessage());
    }
}
