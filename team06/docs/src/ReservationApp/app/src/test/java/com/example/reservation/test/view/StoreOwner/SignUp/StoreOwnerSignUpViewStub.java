package com.example.reservation.test.view.StoreOwner.SignUp;

import com.example.reservation.view.StoreOwner.SignUp.StoreOwnerSignUpView;

public class StoreOwnerSignUpViewStub implements StoreOwnerSignUpView {

    private int errorCount;
    private int successCount;
    private String errorMsg;
    private String successMsg;

    @Override
    public void setUserName(String name) {

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
    public void setAFM(String afm) {

    }

    @Override
    public void successfullySignUp(String message) {
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
