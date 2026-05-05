package com.example.reservation.test.view.StoreOwner.MainMenu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.reservation.MemoryDao.CurrentUserDAOMemory;
import com.example.reservation.MemoryDao.StoreDAOMemory;
import com.example.reservation.dao.CurrentUserDAO;
import com.example.reservation.dao.StoreDAO;
import com.example.reservation.domain.Store;
import com.example.reservation.domain.StoreOwner;
import com.example.reservation.view.StoreOwner.MainMenu.MainMenuPresenter;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MainMenuPresenterTest {
    private MainMenuPresenter presenter;
    private MainMenuViewStub viewStub;
    private StoreDAO storeDAO;
    private CurrentUserDAO currentUserDAO;

    @Before
    public void setUp() {
        // Initialize the stub and DAO
        viewStub = new MainMenuViewStub();
        storeDAO = new StoreDAOMemory(); // Mocking the StoreDAO
        currentUserDAO = CurrentUserDAOMemory.getInstance(); // Assuming it's correctly initialized in this context

        // Create mock StoreOwner to simulate logged-in owner
        StoreOwner owner = new StoreOwner(1, "ownerUsername", "Owner", null, "password", null);
        currentUserDAO.save(owner); // Saving owner to the DAO (simulating a logged-in user)

        // Initialize the presenter with the view and storeDAO
        presenter = new MainMenuPresenter(viewStub);
    }

    @Test
    public void testLoadStores_withNoStores() {
        // Simulate a case where the StoreOwner has no stores
        int ownerId = 1;

        // Simulate the empty list scenario
        storeDAO.findAllByOwnerId(ownerId).clear();

        // Load stores
        presenter.loadStores(ownerId);

        // Assert that no stores message is shown
        assertTrue(viewStub.isNoStoresMessageShown());
        assertFalse(viewStub.isStoresDisplayed());
    }

    @Test
    public void testLoadStores_withSomeStores() {
        // Simulate a case where the StoreOwner has some stores
        int ownerId = 1;

        // Creating some mock stores
        List<Store> stores = new ArrayList<>();
        stores.add(new Store(1, ownerId, "Store1", null, null, 100, 10));
        stores.add(new Store(2, ownerId, "Store2", null, null, 200, 20));

        // Setting mock stores in the DAO
        for (Store store : stores) {
            storeDAO.save(store); // Save one store at a time
        }
        // Load stores
        presenter.loadStores(ownerId);

        // Assert that stores are displayed
        assertTrue(viewStub.isStoresDisplayed());
        assertEquals(2, viewStub.getStores().size());
    }
}
