package com.example.reservation.test.view.Customer.Login;

import com.example.reservation.view.Customer.LogIn.CustomerLoginView;

public class CustomerLoginStub implements CustomerLoginView {

    private String username;
    private String password;
    private String errorMessage;
    private String successMessage;
    private int errorCount = 0;
    private int successCount = 0;

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void succesfullyLogedIn(String message) {
        this.successMessage = message;
        successCount++;
    }

    @Override
    public void showErrorMessage(String message) {
        this.errorMessage = message;
        errorCount++;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public int getSuccessCount() {
        return successCount;
    }
}
