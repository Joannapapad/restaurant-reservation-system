package com.example.reservation.test.view.StoreOwner.AddStore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.reservation.MemoryDao.CurrentUserDAOMemory;
import com.example.reservation.MemoryDao.MemoryInitializer;
import com.example.reservation.MemoryDao.StoreDAOMemory;
import com.example.reservation.view.StoreOwner.AddStore.AddStorePresenter;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class AddStorePresenterTest {
    private MemoryInitializer dataHelper;
    private AddStorePresenter presenter;
    private AddStoreViewStub view;

    @Before
    public void setUp() {
        // Initialize data and prepare test environment
        dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new AddStoreViewStub();

        view.setCityOptions(Arrays.asList("Athens", "Thessaloniki"));
        view.setCategoryOptions(Arrays.asList("Restaurant", "Bar"));

    }

//    @Test
//    public void testAddNewStore() {
//        // Initialize presenter with view, DAO, and options
//        presenter = new AddStorePresenter(
//                view,
//                CurrentUserDAOMemory.getInstance(),
//               new StoreDAOMemory(),
//                Arrays.asList("Athens", "Thessaloniki"), Arrays.asList("Restaurant", "Bar")
//        );
//
//
//        // Simulate user input by setting fields in the view
//        view.setName("Zark");
//        view.setStreet("Kyprou");
//        view.setNumber("61");
//        view.setCity("Athens");
//        view.setCountry("Country2");
//        view.setPostalCode("54431");
//        view.setType("Bar");
//        view.setCapacity(340);
//        view.setTableNum(54);
//
//        // Call the saveStore method to process the input
//        presenter.saveStore();
//
//        assertEquals("Store saved successfully for StoreOwner", view.getSuccMsg());
//        assertEquals("Zark", dataHelper.getStoreDAO().findAll().get(0).getName());
//    }

    @Test
    public void testSaveStoreValidationFailure() {

        presenter = new AddStorePresenter(
                view,
                CurrentUserDAOMemory.getInstance(),
                new StoreDAOMemory(),
                Arrays.asList("Athens", "Thessaloniki"), Arrays.asList("Restaurant", "Bar")
        );
        // Simulate missing store name (validation failure)
        view.setName("");
        view.setStreet("Zarkk");
        view.setNumber("123");
        view.setCity("Athens");
        view.setCountry("Greece");
        view.setPostalCode("12345");
        view.setType("Restaurant");
        view.setCapacity(100);
        view.setTableNum(20);

        // Call saveStore method
        presenter.saveStore();

        // Verify that error message is shown
        assertEquals("Error", view.errorTitle);
        assertEquals("All fields must be filled", view.errorMessage);
    }
}
