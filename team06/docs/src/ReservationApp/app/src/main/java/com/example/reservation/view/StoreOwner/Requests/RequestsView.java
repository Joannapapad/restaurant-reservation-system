package com.example.reservation.view.StoreOwner.Requests;

import com.example.reservation.domain.Store;

import java.util.List;

public interface RequestsView {
    void displayStores(List<Store> stores);

    void showNoStoresMessage();
}
