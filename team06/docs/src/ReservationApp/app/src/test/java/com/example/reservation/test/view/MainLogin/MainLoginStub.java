package com.example.reservation.test.view.MainLogin;


import com.example.reservation.view.MainLogin.MainLoginView;

public class MainLoginStub implements MainLoginView {

    private int storeOwnerLoginCount;
    private int storeOwnerSignUpCount;
    private int customerLoginCount;
    private int customerSignUpCount;

    @Override
    public void ManageStoreOwnerLogin() {
        storeOwnerLoginCount++;
    }

    @Override
    public void ManageStoreOwnerSignUp() {
        storeOwnerSignUpCount++;
    }

    @Override
    public void ManageCustomerLogin() {
        customerLoginCount++;
    }

    @Override
    public void ManageCustomerSignUp() {
        customerSignUpCount++;
    }

    public int getStoreOwnerLoginCount() {
        return storeOwnerLoginCount;
    }

    public int getStoreOwnerSignUpCount() {
        return storeOwnerSignUpCount;
    }

    public int getCustomerLoginCount() {
        return customerLoginCount;
    }

    public int getCustomerSignUpCount() {
        return customerSignUpCount;
    }
}
