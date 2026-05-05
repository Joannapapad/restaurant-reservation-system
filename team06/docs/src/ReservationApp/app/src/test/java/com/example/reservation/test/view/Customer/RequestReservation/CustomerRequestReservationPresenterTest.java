package com.example.reservation.test.view.Customer.RequestReservation;

import com.example.reservation.MemoryDao.CustomerDAOMemory;
import com.example.reservation.contact.EmailAddress;
import com.example.reservation.contact.TelephoneNumber;
import com.example.reservation.domain.Customer;
import com.example.reservation.domain.Request;
import com.example.reservation.domain.Reservation;
import com.example.reservation.domain.ReservationStatus;
import com.example.reservation.view.Customer.RequestReservation.CustomerRequestReservationPresenter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerRequestReservationPresenterTest {

    private CustomerRequestReservationPresenter presenter;
    private CustomerRequestReservationStub viewStub;
    private CustomerDAOMemory customerDAO;

    private Customer customer;

    @Before
    public void setUp() {
        customerDAO = new CustomerDAOMemory();

        customer = new Customer(1001, "Μαρία Δημητροπούλου", "mariadim", new EmailAddress("mardim@example.com"), "password456", new TelephoneNumber("1234567890"));

        Request request1 = new Request(1,1001 ,customer.getCustomerId(),5 , ReservationStatus.PENDING, "Request details 1");
        Request request2 = new Request(2, 1001,customer.getCustomerId(), 6,ReservationStatus.PENDING, "Request details 2");

        Reservation reservation1 = new Reservation(request1);
        Reservation reservation2 = new Reservation(request2);

        customer.addRequest(request1);
        customer.addRequest(request2);
        customer.addReservation(reservation1);
        customer.addReservation(reservation2);

        customerDAO.save(customer);

        viewStub = new CustomerRequestReservationStub();
        presenter = new CustomerRequestReservationPresenter(viewStub, customerDAO, 1001);
    }

    @Test
    public void testLoadRequests_success() {
        presenter.loadRequests();

        assertNotNull(viewStub.getDisplayedRequests());
        assertEquals(2, viewStub.getDisplayedRequests().size());
        assertEquals("Request details 1", viewStub.getDisplayedRequests().get(0).getComment());
        assertEquals("Request details 2", viewStub.getDisplayedRequests().get(1).getComment());
    }

    @Test
    public void testLoadReservations_success() {
        presenter.loadReservations();

        assertNotNull(viewStub.getDisplayedReservations());
        assertEquals(2, viewStub.getDisplayedReservations().size());
        assertEquals("Request details 1", viewStub.getDisplayedReservations().get(0).getComment());
        assertEquals("Request details 2", viewStub.getDisplayedReservations().get(1).getComment());
    }

}
