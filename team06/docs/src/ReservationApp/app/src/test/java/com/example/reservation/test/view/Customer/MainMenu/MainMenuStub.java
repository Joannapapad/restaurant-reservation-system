package com.example.reservation.test.view.Customer.MainMenu;

import com.example.reservation.view.Customer.MainMenu.MainMenuView;

import java.util.ArrayList;
import java.util.List;

public class MainMenuStub implements MainMenuView {

    private List<String> results = new ArrayList<>();
    private int updateCount = 0;

    @Override
    public void updateResults(List<String> results) {
        this.results = results;
        updateCount++;
    }

    public List<String> getResults() {
        return results;
    }

    public int getUpdateCount() {
        return updateCount;
    }
}
