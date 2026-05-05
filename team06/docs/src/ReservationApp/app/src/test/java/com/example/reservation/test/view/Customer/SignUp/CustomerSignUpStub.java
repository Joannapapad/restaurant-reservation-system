package com.example.reservation.test.view.Customer.SignUp;

import com.example.reservation.view.Customer.SignUp.CustomerSignUpView;

public class CustomerSignUpStub implements CustomerSignUpView {

    private String successMessage;
    private String errorMessage;

    @Override
    public void setName(String name) {

    }

    @Override
    public void setUserName(String userName) {

    }

    @Override
    public void setEmail(String email) {

    }

    @Override
    public void setPassword(String password) {

    }

    @Override
    public void setTel(String tel) {

    }

    @Override
    public void showErrorMessage(String message) {
        errorMessage = message;
    }

    @Override
    public void successfullySignUp(String message) {
        successMessage = message;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
