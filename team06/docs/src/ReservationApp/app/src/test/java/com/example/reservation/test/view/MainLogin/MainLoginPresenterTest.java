package com.example.reservation.test.view.MainLogin;

import com.example.reservation.view.MainLogin.MainLoginPresenter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainLoginPresenterTest {

    private MainLoginPresenter presenter;
    private MainLoginStub viewStub;

    @Before
    public void setUp() {
        viewStub = new MainLoginStub();
        presenter = new MainLoginPresenter();
        presenter.setView(viewStub);
    }

    @Test
    public void testManageStoreOwnerLogin() {
        presenter.onManageStoreOwnerLogin();

        assertEquals(1, viewStub.getStoreOwnerLoginCount());
    }

    @Test
    public void testManageStoreOwnerSignUp() {
        presenter.onManageStoreOwnerSignUp();

        assertEquals(1, viewStub.getStoreOwnerSignUpCount());
    }

    @Test
    public void testManageCustomerLogin() {
        presenter.onManageCustomerLogin();

        assertEquals(1, viewStub.getCustomerLoginCount());
    }

    @Test
    public void testManageCustomerSignUp() {
        presenter.onManageCustomerSignUp();

        assertEquals(1, viewStub.getCustomerSignUpCount());
    }
}
