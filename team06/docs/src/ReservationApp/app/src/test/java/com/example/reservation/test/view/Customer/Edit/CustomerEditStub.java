package com.example.reservation.test.view.Customer.Edit;

import com.example.reservation.view.Customer.Edit.CustomerEditView;

public class CustomerEditStub implements CustomerEditView {

    private String name;
    private String username;
    private String tel;
    private String email;
    private String password;
    private String errorMessage;
    private String successMessage;

    private int errorCount = 0;
    private int successCount = 0;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getTel() {
        return tel;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void showErrorMessage(String message) {
        this.errorMessage = message;
        errorCount++;
    }

    @Override
    public void successfullyFinishActivity(String message) {
        this.successMessage = message;
        successCount++;
    }

    // Setters for simulating user input
    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getters for verifying results
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
