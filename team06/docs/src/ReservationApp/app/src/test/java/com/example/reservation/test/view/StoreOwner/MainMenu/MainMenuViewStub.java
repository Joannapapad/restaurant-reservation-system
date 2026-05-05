package com.example.reservation.test.view.StoreOwner.MainMenu;

import com.example.reservation.domain.Store;
import com.example.reservation.view.StoreOwner.MainMenu.MainMenuView;

import java.util.List;

public class MainMenuViewStub implements MainMenuView {


        private boolean noStoresMessageShown;
        private boolean storesDisplayed;
        private List<Store> stores;


    @Override
    public void displayStores(List<Store> stores) {
        this.stores = stores;
        storesDisplayed = true;
    }

    public void showNoStoresMessage() {
            noStoresMessageShown = true;
        }


        public boolean isNoStoresMessageShown() {
            return noStoresMessageShown;
        }

        public boolean isStoresDisplayed() {
            return storesDisplayed;
        }

        public List<Store> getStores() {
            return stores;
        }
}

