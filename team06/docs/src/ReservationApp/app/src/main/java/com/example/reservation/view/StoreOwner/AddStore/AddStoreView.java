package com.example.reservation.view.StoreOwner.AddStore;

import java.util.List;

public interface AddStoreView {
    void showErrorMessage(String title, String message);
    void showSuccessMessage(String message);
    void setCityOptions(List<String> cities);
    void setCategoryOptions(List<String> categories);
    String getStoreName();
    String getStreet();
    String getStreetNumber();
    String getCity();
    String getCountry();
    String getPostalCode();
    String getStoreType();
    int getCapacity();
    int getTableNumber();
}
