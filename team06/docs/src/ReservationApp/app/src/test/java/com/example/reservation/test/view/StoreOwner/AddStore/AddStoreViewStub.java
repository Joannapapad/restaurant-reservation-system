package com.example.reservation.test.view.StoreOwner.AddStore;

import com.example.reservation.view.StoreOwner.AddStore.AddStorePresenter;
import com.example.reservation.view.StoreOwner.AddStore.AddStoreView;

import java.util.ArrayList;
import java.util.List;

public class AddStoreViewStub implements AddStoreView {

    public String errorTitle,errorMessage,succMsg,name, street,number, city,country,postalCode,type;
    public List<String> citiesNumber, categoryNumber;
    public Integer capacity,tableNum;
    public boolean successMessageShown;

    private AddStorePresenter presenter;

    public void setPresenter(AddStorePresenter presenter) {
        this.presenter = presenter;
    }

    public AddStorePresenter getPresenter() {
        return presenter;
    }

    public AddStoreViewStub() {
        errorTitle = errorMessage = succMsg = name = street = number = city = country = postalCode = type = "";
        citiesNumber = new ArrayList<>();
        categoryNumber = new ArrayList<>();
    }

    @Override
    public void showErrorMessage(String title, String message) {
        errorTitle = title;
        errorMessage = message;
    }

    @Override
    public void showSuccessMessage(String message) {
        successMessageShown = true;
        succMsg = message;
    }

    @Override
    public void setCityOptions(List<String> cities) {
        citiesNumber = cities;
    }

    @Override
    public void setCategoryOptions(List<String> categories) {
        categoryNumber = categories;
    }

    @Override
    public String getStoreName() {
        return name;
    }

    @Override
    public String getStreet() {
        return street;
    }

    @Override
    public String getStreetNumber() {
        return number;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public String getStoreType() {
        return type;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getTableNumber() {
        return tableNum;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
    public void setCitiesNumber(List<String> citiesNumber) {
        this.citiesNumber = citiesNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTableNum(Integer tableNum) {
        this.tableNum = tableNum;
    }

    public String getSuccMsg() {
        return succMsg;
    }

    public boolean successMessageShown() {
        return successMessageShown;
    }
}

