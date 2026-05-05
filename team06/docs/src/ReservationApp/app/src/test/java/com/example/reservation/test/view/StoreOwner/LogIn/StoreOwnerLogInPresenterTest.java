package com.example.reservation.test.view.StoreOwner.LogIn;

import static org.junit.Assert.assertEquals;

import com.example.reservation.MemoryDao.CurrentUserDAOMemory;
import com.example.reservation.MemoryDao.MemoryInitializer;
import com.example.reservation.MemoryDao.StoreOwnerDAOMemory;
import com.example.reservation.dao.Initializer;
import com.example.reservation.dao.StoreOwnerDAO;
import com.example.reservation.view.StoreOwner.LogIn.StoreOwnerLogInPresenter;

import org.junit.Before;
import org.junit.Test;

public class StoreOwnerLogInPresenterTest {

    private MemoryInitializer dataHelper;
    StoreOwnerLogInStub stub;
    StoreOwnerLogInPresenter presenter;

    @Before
    public void setUp() {
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        stub = new StoreOwnerLogInStub();
        presenter = new StoreOwnerLogInPresenter();
        presenter.setView(stub);
        presenter.setStoreOwnerDAO(dataHelper.getStoreOwnerDAO());
        presenter.setCurrentUserDAO(dataHelper.getCurrentUserDAO());

    }

    @Test
    public void testLoginWithEmptyFields() {

        presenter.LogIn("", "");

        assertEquals(1, stub.getErrorCount());
        assertEquals("You have to complete all the required fields", stub.getErrorMsg());
    }


    @Test
    public void testLoginWithNonExistentAccount() {
        presenter.LogIn("NonExistentUser", "password123");
        assertEquals(1, stub.getErrorCount());
        assertEquals("You don't have an account. Please select the Sign Up button", stub.getErrorMsg());
    }

    @Test
    public void testLogInSuccess() {
        presenter.LogIn("Owner2", "ownerpass2");
        assertEquals(1,stub.getSuccessCount());
        assertEquals("The log in was successful" , stub.getSuccessMsg());
    }


}
