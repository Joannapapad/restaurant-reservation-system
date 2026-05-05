package com.example.reservation.dao;

import com.example.reservation.contact.EmailAddress;
import com.example.reservation.contact.StoreAddress;
import com.example.reservation.contact.TelephoneNumber;
import com.example.reservation.contact.ZipCode;
import com.example.reservation.domain.*;

import java.util.List;

/**
 * Abstract class to initialize data for the DAOs. Provides helper methods to erase and populate
 * data for testing and demonstrations.
 */
public abstract class Initializer {
    public static final int CUSTOMER_ID_1 = 1;
    public static final int CUSTOMER_ID_2 = 2;

    // Store IDs
    public static final int STORE_ID_1 = 1001;
    public static final int STORE_ID_2 = 1002;
    public static final int STORE_ID_3 = 1003;
    public static final int STORE_ID_4 = 1004;
    public static final int STORE_ID_5 = 1005;
    public static final int STORE_ID_6 = 1006;
    public static final int STORE_ID_7 = 1007;
    public static final int STORE_ID_8 = 1008;
    public static final int STORE_ID_9 = 1009;
    public static final int STORE_ID_10 = 1010;

    // Store Owner IDs
    public static final int OWNER_ID_1 = 3001;
    public static final int OWNER_ID_2 = 3002;

    // Request IDs
    public static final int REQUEST_ID_1 = 2001;
    public static final int REQUEST_ID_2 = 2002;
    public static final int REQUEST_ID_3 = 2003;
    public static final int REQUEST_ID_4 = 2004;
    public static final int REQUEST_ID_5 = 2005;
    public static final int REQUEST_ID_6 = 2006;
    public static final int REQUEST_ID_7 = 2007;
    public static final int REQUEST_ID_8 = 2008;
    public static final int REQUEST_ID_9 = 2009;
    public static final int REQUEST_ID_10 = 2010;


    /**
     * Abstract method to erase all existing data from DAOs.
     */
    protected abstract void eraseData();

    /**
     * Method to prepare data by erasing existing records and populating new ones.
     */
    public void prepareData() {
        // Clear existing dataΙ
        eraseData();

        // Create customers
        Customer customer1 = new Customer(CUSTOMER_ID_1, "Μαρία Δημητροπούλου", "mariadim", new EmailAddress("mardim@example.com"), "password456", new TelephoneNumber("1234567890"));
        Customer customer2 = new Customer(CUSTOMER_ID_2, "Γιάννης Παπαδόπουλος", "giannis", new EmailAddress("giannis@example.com"), "password123", new TelephoneNumber("9876543210"));

        // Create store owners
        StoreOwner owner1 = new StoreOwner(OWNER_ID_1, "123456", "Ιδιοκτήτης 1", new EmailAddress("owner1@example.com"), "password1", new TelephoneNumber("1234567890"));
        StoreOwner owner2 = new StoreOwner(OWNER_ID_2, "654321", "Ιδιοκτήτης 2", new EmailAddress("owner2@example.com"), "ownerpass2", new TelephoneNumber("0987654321"));

        // Create stores with Greek addresses
        ZipCode zip1 = new ZipCode("1234558");
        ZipCode zip2 = new ZipCode("7654222");
        StoreAddress address1 = new StoreAddress("Γκαρίτου", "123", zip1, "Λάρισα", "Ελλάδα");
        Store store1 = new Store(STORE_ID_1, OWNER_ID_1, "Κατάστημα Α", "Μπαρ", address1, 50, 10);

        StoreAddress address2 = new StoreAddress("Μεσογείων", "456", zip2, "Αθήνα", "Ελλάδα");
        Store store2 = new Store(STORE_ID_2, OWNER_ID_2, "Κατάστημα Β", "Καφέ", address2, 30, 5);

        // Additional stores for Owner 1
        StoreAddress address3 = new StoreAddress("Κεντρική Οδός", "789", new ZipCode("9991111"), "Θεσσαλονίκη", "Ελλάδα");
        Store store3 = new Store(STORE_ID_3, OWNER_ID_1, "Κατάστημα Γ", "Ταβέρνα", address3, 60, 15);

        StoreAddress address4 = new StoreAddress("Οδός Πατησίων", "321", new ZipCode("8520000"), "Πάτρα", "Ελλάδα");
        Store store4 = new Store(STORE_ID_4, OWNER_ID_1, "Κατάστημα Δ", "Σαλόνι", address4, 40, 8);

        StoreAddress address5 = new StoreAddress("Οδός Αριστοτέλους", "101", new ZipCode("4781325"), "Βόλος", "Ελλάδα");
        Store store5 = new Store(STORE_ID_5, OWNER_ID_1, "Κατάστημα Ε", "Χώρος Εκδηλώσεων", address5, 75, 20);

        // Additional stores for Owner 2
        StoreAddress address6 = new StoreAddress("Οδός Σταδίου", "555", new ZipCode("6543210"), "Κέρκυρα", "Ελλάδα");
        Store store6 = new Store(STORE_ID_6, OWNER_ID_2, "Κατάστημα Ζ", "Μπαρ", address6, 50, 10);

        StoreAddress address7 = new StoreAddress("Οδός Μακρυγιάννη", "202", new ZipCode("9837623"), "Ρόδος", "Ελλάδα");
        Store store7 = new Store(STORE_ID_7, OWNER_ID_2, "Κατάστημα Η", "Αίθουσα Δεξιώσεων", address7, 100, 25);

        StoreAddress address8 = new StoreAddress("Οδός Ελ. Βενιζέλου", "654", new ZipCode("5823711"), "Ιωάννινα", "Ελλάδα");
        Store store8 = new Store(STORE_ID_8, OWNER_ID_2, "Κατάστημα Θ", "Παμπ", address8, 40, 7);

        StoreAddress address9 = new StoreAddress("Οδός Κηφισίας", "333", new ZipCode("4652309"), "Χανιά", "Ελλάδα");
        Store store9 = new Store(STORE_ID_9, OWNER_ID_2, "Κατάστημα Ι", "Ψαροταβέρνα", address9, 60, 18);

        StoreAddress address10 = new StoreAddress("Οδός Αμαλίας", "222", new ZipCode("1029345"), "Καλαμάτα", "Ελλάδα");
        Store store10 = new Store(STORE_ID_10, OWNER_ID_2, "Κατάστημα Κ", "Καφέ", address10, 30, 5);

        Request request1 = new Request(REQUEST_ID_1, STORE_ID_1, CUSTOMER_ID_1, 4,ReservationStatus.PENDING, "Αίτηση για οικογενειακό δείπνο");
        Request request2 = new Request(REQUEST_ID_2, STORE_ID_2, CUSTOMER_ID_1, 2, ReservationStatus.PENDING, "Αίτηση για επαγγελματική συνάντηση");
        Request request3 = new Request(REQUEST_ID_3, STORE_ID_1, CUSTOMER_ID_2, 3, ReservationStatus.PENDING, "Αίτηση για δείπνο με φίλους");
        Request request4 = new Request(REQUEST_ID_4, STORE_ID_1, CUSTOMER_ID_2, 5, ReservationStatus.PENDING, "Αίτηση για εορταστική εκδήλωση");
        Request request5 = new Request(REQUEST_ID_5, STORE_ID_2, CUSTOMER_ID_1, 2, ReservationStatus.PENDING, "Αίτηση για επιβεβαίωση κράτησης");
        Request request6 = new Request(REQUEST_ID_6, STORE_ID_2, CUSTOMER_ID_1, 6, ReservationStatus.PENDING, "Αίτηση για συνέδριο");
        Request request7 = new Request(REQUEST_ID_7, STORE_ID_1, CUSTOMER_ID_2, 3, ReservationStatus.PENDING, "Αίτηση για δείπνο με οικογένεια");
        Request request8 = new Request(REQUEST_ID_8, STORE_ID_1, CUSTOMER_ID_1, 4, ReservationStatus.PENDING, "Αίτηση για γιορτή");
        Request request9 = new Request(REQUEST_ID_9, STORE_ID_2, CUSTOMER_ID_2, 3, ReservationStatus.PENDING, "Αίτηση για επαγγελματική δεξίωση");
        Request request10 = new Request(REQUEST_ID_10, STORE_ID_2, CUSTOMER_ID_1, 4, ReservationStatus.PENDING, "Αίτηση για οικογενειακή συγκέντρωση");

        Reservation reservation1 = new Reservation(request1);
        Reservation reservation2 = new Reservation(request2);
        Reservation reservation3 = new Reservation(request3);
        Reservation reservation4 = new Reservation(request4);
        Reservation reservation5 = new Reservation(request5);
        Reservation reservation6 = new Reservation(request6);

        // Add relationships between customers, requests, and reservations
        customer1.addRequest(request1);
        customer1.addRequest(request2);
        customer1.addRequest(request5);
        customer1.addRequest(request6);
        customer1.addRequest(request8);
        customer1.addRequest(request10);
        customer1.addReservation(reservation1);
        customer1.addReservation(reservation5);

        customer2.addRequest(request3);
        customer2.addRequest(request4);
        customer2.addRequest(request7);
        customer2.addRequest(request9);
        customer2.addReservation(reservation2);
        customer2.addReservation(reservation6);

        store1.addRequest(request1);
        store1.addRequest(request3);
        store1.addRequest(request7);
        store1.addRequest(request8);
        store1.addReservation(reservation1);
        store1.addReservation(reservation3);

        store2.addRequest(request2);
        store2.addRequest(request4);
        store2.addRequest(request5);
        store2.addRequest(request9);
        store2.addReservation(reservation2);
        store2.addReservation(reservation4);


        // Save entities to DAOs
        getCustomerDAO().save(customer1);
        getCustomerDAO().save(customer2);

        getStoreOwnerDAO().save(owner1);
        getStoreOwnerDAO().save(owner2);

        getStoreDAO().addStore(store1);
        getStoreDAO().addStore(store2);
        getStoreDAO().addStore(store3);
        getStoreDAO().addStore(store4);
        getStoreDAO().addStore(store5);
        getStoreDAO().addStore(store6);
        getStoreDAO().addStore(store7);
        getStoreDAO().addStore(store8);
        getStoreDAO().addStore(store9);
        getStoreDAO().addStore(store10);

        getRequestDAO().save(request1);
        getRequestDAO().save(request2);
        getRequestDAO().save(request3);
        getRequestDAO().save(request4);
        getRequestDAO().save(request5);
        getRequestDAO().save(request6);
        getRequestDAO().save(request7);
        getRequestDAO().save(request8);
        getRequestDAO().save(request9);
        getRequestDAO().save(request10);

        getReservationDAO().save(reservation1);
        getReservationDAO().save(reservation2);
        getReservationDAO().save(reservation3);
        getReservationDAO().save(reservation4);
        getReservationDAO().save(reservation5);
        getReservationDAO().save(reservation6);
    }


    // Abstract methods to retrieve DAO implementations

    /**
     * Retrieves an instance of the CustomerDAO.
     *
     * @return A concrete implementation of the CustomerDAO interface.
     */
    public abstract CustomerDAO getCustomerDAO();

    /**
     * Retrieves an instance of the StoreDAO.
     *
     * @return A concrete implementation of the StoreDAO interface.
     */
    public abstract StoreDAO getStoreDAO();

    /**
     * Retrieves an instance of the RequestDAO.
     *
     * @return A concrete implementation of the RequestDAO interface.
     */
    public abstract RequestDAO getRequestDAO();

    /**
     * Retrieves an instance of the ReservationDAO.
     *
     * @return A concrete implementation of the ReservationDAO interface.
     */
    public abstract ReservationDAO getReservationDAO();

    /**
     * Retrieves an instance of the StoreOwnerDAO.
     *
     * @return A concrete implementation of the StoreOwnerDAO interface.
     */
    public abstract StoreOwnerDAO getStoreOwnerDAO();
}