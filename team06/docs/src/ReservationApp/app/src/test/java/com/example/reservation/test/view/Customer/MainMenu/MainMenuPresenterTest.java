package com.example.reservation.test.view.Customer.MainMenu;

import com.example.reservation.contact.StoreAddress;
import com.example.reservation.contact.ZipCode;
import com.example.reservation.domain.Store;
import com.example.reservation.view.Customer.MainMenu.MainMenuPresenter;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MainMenuPresenterTest {

    private MainMenuPresenter presenter;
    private MainMenuStub viewStub;
    private List<Store> stores;

    @Before
    public void setUp() {
        // Initialize the list of stores
        stores = new ArrayList<>();
// Define ZipCodes
        ZipCode zip1 = new ZipCode("1234558");
        ZipCode zip2 = new ZipCode("7654222");
        ZipCode zip3 = new ZipCode("4531234");
        ZipCode zip4 = new ZipCode("9821123");

// Define StoreAddresses
        StoreAddress address1 = new StoreAddress("Garritou", "123", zip1, "New York", "Country1");
        StoreAddress address2 = new StoreAddress("Sunset Blvd", "456", zip2, "Los Angeles", "Country2");
        StoreAddress address3 = new StoreAddress("Michigan Ave", "789", zip3, "Chicago", "Country3");
        StoreAddress address4 = new StoreAddress("Broadway", "101", zip4, "New York", "Country1");

// Add stores with complete details
        stores.add(new Store(1001, 2001, "Store1", "Electronics", address1, 50, 10));
        stores.add(new Store(1002, 2002, "Store2", "Grocery", address2, 75, 20));
        stores.add(new Store(1003, 2003, "Store3", "Clothing", address3, 100, 15));
        stores.add(new Store(1004, 2004, "Store4", "Grocery", address4, 60, 12));

        // Initialize the stub and presenter
        viewStub = new MainMenuStub();
        presenter = new MainMenuPresenter(viewStub, stores);
    }

    @Test
    public void testSearchByName() {
        presenter.search("Store1", "", "");
        assertEquals(1, viewStub.getUpdateCount());
        assertEquals(1, viewStub.getResults().size());
        assertEquals("Store1", viewStub.getResults().get(0));
    }

    @Test
    public void testFilterByLocation() {
        presenter.search("", "New York", "");
        assertEquals(1, viewStub.getUpdateCount());
        assertEquals(2, viewStub.getResults().size());
        assertEquals("Store1", viewStub.getResults().get(0));
        assertEquals("Store4", viewStub.getResults().get(1));
    }

    @Test
    public void testFilterByCategory() {
        presenter.search("", "", "Grocery");
        assertEquals(1, viewStub.getUpdateCount());
        assertEquals(2, viewStub.getResults().size());
        assertEquals("Store2", viewStub.getResults().get(0));
        assertEquals("Store4", viewStub.getResults().get(1));
    }

    @Test
    public void testCombinedFilters() {
        presenter.search("", "New York", "Grocery");
        assertEquals(1, viewStub.getUpdateCount());
        assertEquals(1, viewStub.getResults().size());
        assertEquals("Store4", viewStub.getResults().get(0));
    }

    @Test
    public void testNoResults() {
        presenter.search("NonExistentStore", "", "");
        assertEquals(1, viewStub.getUpdateCount());
        assertEquals(0, viewStub.getResults().size());
    }
}
