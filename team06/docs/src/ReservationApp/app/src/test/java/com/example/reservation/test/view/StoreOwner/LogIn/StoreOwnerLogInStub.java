package com.example.reservation.test.view.StoreOwner.LogIn;

import com.example.reservation.view.StoreOwner.LogIn.StoreOwnerLogInView;

public class StoreOwnerLogInStub implements StoreOwnerLogInView {

    private int errorCount;
    private int successCount;
    private String errorMsg;
    private String successMsg;
    @Override
    public void setUserName(String name) {

    }

    @Override
    public void setPassword(String password) {

    }

    @Override
    public void succesfullyLogedIn(String message) {
        this.successMsg = message;
        this.successCount++;
    }

    @Override
    public void showErrorMessage(String message) {
        this.errorMsg = message;
        this.errorCount++;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

}


