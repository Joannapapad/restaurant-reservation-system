package com.example.reservation.test.view.StoreOwner.SignUp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.example.reservation.MemoryDao.CurrentUserDAOMemory;
import com.example.reservation.MemoryDao.MemoryInitializer;
import com.example.reservation.MemoryDao.StoreOwnerDAOMemory;
import com.example.reservation.dao.Initializer;
import com.example.reservation.view.StoreOwner.SignUp.StoreOwnerSignUpPresenter;

public class StoreOwnerSignUpPresenterTest {

    StoreOwnerSignUpViewStub stub;
    StoreOwnerSignUpPresenter presenter;

    @Before
    public void setUp() {
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        // Initialize the stub and presenter
        stub = new StoreOwnerSignUpViewStub();
        presenter = new StoreOwnerSignUpPresenter();
        presenter.setView(stub);
        presenter.setStoreOwnerDAO(new StoreOwnerDAOMemory());
        presenter.setCurrentUserDAO(CurrentUserDAOMemory.getInstance());
    }

    @Test
    public void showErrorForWrongEmail() {
        // Test for wrong email format
        presenter.SignUp("Fani", "fanitsok21322222", "fani12345", "5667884435", "23456");

        // Assert that the correct error message is shown
        assertEquals(1, stub.getErrorCount());
        assertEquals("The provided email is wrong. Please provide a correct email", stub.getErrorMsg());
    }

    @Test
    public void showSuccessfulForSignUp() {
        // Test for successful sign up
        presenter.SignUp("Fani", "fanitsiok@gmail.com", "fani12345", "5667884435", "23456");

        // Assert that the sign-up was successful
        assertEquals(1, stub.getSuccessCount());
        assertEquals("The Sign up is successfully done!!1", stub.getSuccessMsg());
    }


    @Test
    public void showErrorMsgForEmpty() {
        // Test for empty fields
        presenter.SignUp("", "fanitsiok@gmail.com", "fani12345", "5667884435", "23456");

        // Assert that the error message is shown for empty fields
        assertEquals(1, stub.getErrorCount());
        assertEquals("You have to complete all the required fields", stub.getErrorMsg());
    }
}
