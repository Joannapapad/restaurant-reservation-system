package com.example.reservation.test.domain;
import static org.junit.Assert.*;
import com.example.reservation.contact.EmailAddress;
import com.example.reservation.contact.TelephoneNumber;
import com.example.reservation.domain.Customer;
import com.example.reservation.domain.Rate;

import org.junit.Before;
import org.junit.Test;

public class RateTest {

    private Rate rate;
    private Rate rate2;
    private Rate rate3;

    @Before
    public void setUp(){
        rate = new Rate(1,101,5,"Excllent!");
        rate2 = new Rate(45,23,-1,"Terrible");
        rate3 = new Rate(456,97,10,"");
    }

    @Test
    public void testEqualRateCreation() {
        Rate rate = new Rate(1, 101, 5, "Excellent!");
         assertEquals(1, rate.getStoreId());
         assertEquals(101, rate.getCustomerId());
         assertEquals(5, rate.getRating());
         assertEquals("Excellent!", rate.getFeedback());
     }
 
     @Test
     public void testCustomerRating() {
         Customer customer = new Customer(101, "John", "john", new EmailAddress(), "j123123", new TelephoneNumber());
         customer.rateStore(1, 4, "Good experience!");
     }


    @Test
    public void testInvalidFeedback() {

        assertTrue(rate3.getFeedback().isEmpty());
    }

    @Test
    public void testTrueFeedback(){
        assertFalse(rate.getFeedback().isEmpty());
    }

    @Test
    public void testFalseRate(){
        assertFalse(rate2.checkRateScore(rate2.getRating()));
        assertFalse(rate3.checkRateScore(rate3.getRating()));
    }

    @Test
    public void testTrueRate(){
        assertTrue(rate.checkRateScore(rate.getRating()));
    }
 }
 
