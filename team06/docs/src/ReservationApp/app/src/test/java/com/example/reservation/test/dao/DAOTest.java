package com.example.reservation.test.dao;

import com.example.reservation.MemoryDao.CustomerDAOMemory;
import com.example.reservation.MemoryDao.MemoryInitializer;
import com.example.reservation.MemoryDao.StoreDAOMemory;
import com.example.reservation.MemoryDao.StoreOwnerDAOMemory;
import com.example.reservation.contact.EmailAddress;
import com.example.reservation.contact.StoreAddress;
import com.example.reservation.contact.TelephoneNumber;
import com.example.reservation.contact.ZipCode;
import com.example.reservation.dao.CustomerDAO;
import com.example.reservation.dao.StoreDAO;
import com.example.reservation.dao.StoreOwnerDAO;
import com.example.reservation.domain.Customer;
import com.example.reservation.domain.Store;
import com.example.reservation.domain.StoreOwner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Κλάση ελέγχου για τις βασικές πράξεις των αντικειμένων πρόσβασης δεδομένων
 * @author Φανη Τσιοκανη
 *
 */
public class DAOTest {


        private CustomerDAO customerDAO;

        private StoreDAO storeDAO;
        private StoreOwnerDAO storeOwnerDAO;

        private static final int INITIAL_CUSTOMER_COUNT = 2;
        private static final int INITIAL_STORE_COUNT = 2;
        private static final int INITIAL_STORE_OWNER_COUNT = 2;


        @Before
        public void setUp() {
            MemoryInitializer dataHelper = new MemoryInitializer();
            dataHelper.prepareData();

            customerDAO = new CustomerDAOMemory();
            storeDAO = new StoreDAOMemory();
            storeOwnerDAO = new StoreOwnerDAOMemory();

        }

        /**
         * Αναζήτηση πελάτη που υπάρχει στη βάση δεδομένων
         */
        @Test
        public void findExistingCustomer() {
            Customer customer = customerDAO.find("Username");
            Assert.assertEquals("Username", customer.getUserName());
        }

        /**
         * Αναζήτηση πελάτη που δεν υπάρχει στη βάση δεδομένων
         */
        @Test
        public void findNonExistingCustomer() {
            Customer customer = customerDAO.find("fani");
            Assert.assertNull(customer);
        }

        @Test
        public void findExistingStoreOwner() {
            StoreOwner storeOwner = storeOwnerDAO.find("Owner2");
            Assert.assertEquals("Owner2", storeOwner.getUserName());
        }

        @Test
        public void findNonExistingStoreOwner() {
            Assert.assertNull(storeOwnerDAO.find("eyaaaa"));
        }

        @Test
        public void findExistingStore() {
            Store store = storeDAO.find(1001);
            Assert.assertEquals(1001, store.getStoreId());
        }

        @Test
        public void findNonExistingStore() {
            Assert.assertNull(storeDAO.find(4711));
        }

        /**
         * Αποθήκευση πελάτη
         */
        @Test
        public void saveCustomer() {

            Customer customer = new Customer(customerDAO.nextId(), "fani", "fani",
                    new EmailAddress("fani@example.com"), "23221", new TelephoneNumber("123456"));

            customerDAO.save(customer);
            Assert.assertEquals(INITIAL_CUSTOMER_COUNT + 1, customerDAO.findAll().size());
            Assert.assertNotNull(customerDAO.find(customer.getUserName()));
            Assert.assertTrue(customerDAO.findAll().contains(customer));
        }

        /**
         * Διαγραφή πελάτη
         */
        @Test
        public void deleteCustomer() {
            List<Customer> allCustomers = customerDAO.findAll();
            Customer customer = allCustomers.get(0);
            customerDAO.delete(customer);
            allCustomers = customerDAO.findAll();
            Assert.assertEquals(INITIAL_CUSTOMER_COUNT - 1, allCustomers.size());
            Assert.assertNull(customerDAO.find(customer.getUserName()));
        }

        @Test
        public void listAllStores() {
            List<Store> allStores = storeDAO.findAll();
            Assert.assertEquals(INITIAL_STORE_COUNT, allStores.size());
        }


        @Test
        public void savesStore() {
            Store store = new Store(storeDAO.nextId(),3001,"Fisher", "Μπαρ",new StoreAddress("patision","78",new ZipCode("877666"),"Αθήνα","Ελλάδα") , 50, 10);
            storeDAO.save(store);
            Assert.assertEquals(INITIAL_STORE_COUNT + 1, storeDAO.findAll().size());
            Assert.assertNotNull(storeDAO.find(store.getStoreId()));
            Assert.assertTrue(storeDAO.findAll().contains(store));
        }


        @Test
        public void deleteStore() {
            List<Store> allStores = storeDAO.findAll();
            Store store = allStores.get(0);
            storeDAO.delete(store);
            allStores = storeDAO.findAll();
            Assert.assertEquals(INITIAL_STORE_COUNT - 1, allStores.size());
            Assert.assertNull(storeDAO.find(store.getStoreId()));
        }

    @Test
    public void savesStoreOwner() {
        StoreOwner storeOwner = new StoreOwner(storeOwnerDAO.nextId(),"AFM12", "danai", new EmailAddress("dan1@example.com"), "1221221", new TelephoneNumber("335234"));
        storeOwnerDAO.save(storeOwner);
        Assert.assertEquals(INITIAL_STORE_OWNER_COUNT + 1, storeOwnerDAO.findAll().size());
        Assert.assertNotNull(storeOwnerDAO.find(storeOwner.getUserName()));
        Assert.assertTrue(storeOwnerDAO.findAll().contains(storeOwner));
    }

    }

