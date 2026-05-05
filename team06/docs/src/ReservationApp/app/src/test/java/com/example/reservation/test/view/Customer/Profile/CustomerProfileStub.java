package com.example.reservation.test.view.Customer.Profile;

import com.example.reservation.view.Customer.Profile.CustomerProfileView;

public class CustomerProfileStub implements CustomerProfileView {

    public String id;
    public String name;
    public String email;
    public String phone;
    public String password;

    private int editStartCount = 0;
    private int deleteStartCount = 0;
    private int deleteFinishCount = 0;
    private String deleteMessage;
    private String toastMessage;

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void startEditActivity(int customerId, String name, String email, String phone, String password) {
        editStartCount++;
    }

    @Override
    public void startDelete(String title, String message) {
        deleteStartCount++;
    }

    @Override
    public void doDeleteAndFinish(String message) {
        deleteFinishCount++;
        deleteMessage = message;
    }

    @Override
    public void showToast(String message) {
        toastMessage = message;
    }

    // Getters for assertions in tests
    public int getEditStartCount() {
        return editStartCount;
    }

    public int getDeleteStartCount() {
        return deleteStartCount;
    }

    public int getDeleteFinishCount() {
        return deleteFinishCount;
    }

    public String getDeleteMessage() {
        return deleteMessage;
    }

    public String getToastMessage() {
        return toastMessage;
    }
}
