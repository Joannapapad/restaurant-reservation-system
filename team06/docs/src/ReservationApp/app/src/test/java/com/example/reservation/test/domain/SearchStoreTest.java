//package com.example.reservation.test.domain;
//
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//import com.example.reservation.contact.*;
//import com.example.reservation.domain.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class SearchStoreTest {
//
//    public SearchStore searchStore;
//    public List<Store> stores;
//
//    @Before
//    public void setUp() {
//        stores = new ArrayList<>();
//        StoreAddress address1 = new StoreAddress("Mesogeivn", "123", new ZipCode("11475") ,"Agia Paraskeui", "Greece");
//        StoreAddress address2 = new StoreAddress("Vasilisis Sofias", "456", new ZipCode("11475"), "Center, Attica", "Greece");
//        StoreAddress address3 = new StoreAddress("Xolargos", "778", new ZipCode("15232"), "Xolargos, Attica", "Greece");
//        StoreAddress address4 = new StoreAddress("Chalandri", "101", new ZipCode("15234"), "Chalandri, Attica", "Greece");
//
//
//        stores.add(new Store(1001, "Benjamin",address1 , "Burger Restaurant", 50, 10));
//        stores.add(new Store(1002, "La Pasteria", address2, "Italian Restaurant", 30, 5));
//        stores.add(new Store(1003, "Juicy Grill",address3 , "Burger Restaurant", 60, 12));
//        stores.add(new Store(1004, "Alto Corto",address4 , "Cafe Restaurant", 40, 8));
//
//        searchStore = new SearchStore(stores);
//    }
//
//    @Test
//    public void testSearchByName() {
//        List<Store> result = searchStore.searchByName("Benjamin");
//        assertEquals(1, result.size());
//        assertEquals("Benjamin", result.get(0).getName());
//
//        result = searchStore.searchByName("Juicy");
//        assertEquals(1, result.size());
//        assertEquals("Juicy Grill", result.get(0).getName());
//
//        result = searchStore.searchByName("NotExist");
//        assertTrue(result.isEmpty());
//    }
//
//    @Test
//    public void testFilterByLocation() {
//        List<Store> result = searchStore.filterByLocation("Agia Paraskeui");
//        assertEquals(1, result.size());
//        assertEquals("Benjamin", result.get(0).getName());
//
//        result = searchStore.filterByLocation("Attica");
//        assertEquals(3, result.size());
//        assertTrue(result.stream().anyMatch(store -> store.getName().equals("La Pasteria")));
//        assertTrue(result.stream().anyMatch(store -> store.getName().equals("Juicy Grill")));
//        assertTrue(result.stream().anyMatch(store -> store.getName().equals("Alto Corto")));
//
//        result = searchStore.filterByLocation("NonExistent");
//        assertTrue(result.isEmpty());
//    }
//
//    @Test
//    public void testFilterByType() {
//        List<Store> result = searchStore.filterByType("Cafe Restaurant");
//        assertEquals(1, result.size());
//        assertEquals("Alto Corto", result.get(0).getName());
//
//        result = searchStore.filterByType("Burger Restaurant");
//        assertEquals(2, result.size());
//        assertTrue(result.stream().anyMatch(store -> store.getName().equalsIgnoreCase("Juicy Grill")));
//        assertTrue(result.stream().anyMatch(store -> store.getName().equalsIgnoreCase("Benjamin")));
//
//        result = searchStore.filterByType("Italian Restaurant");
//        assertEquals(1, result.size());
//        assertEquals("La Pasteria", result.get(0).getName());
//
//        result = searchStore.filterByType("NonExistent");
//        assertTrue(result.isEmpty());
//    }
//
//    @Test
//    public void testCaseInsensitiveSearch() {
//        List<Store> result = searchStore.searchByName("benjamin");
//        assertEquals(1, result.size());
//        assertEquals("Benjamin", result.get(0).getName());
//
//        result = searchStore.filterByLocation("agia paraskeui");
//        assertEquals(1, result.size());
//        assertEquals("Benjamin", result.get(0).getName());
//
//        result = searchStore.filterByType("burger restaurant");
//        assertEquals(2, result.size());
//        assertTrue(result.stream().anyMatch(store -> store.getName().equalsIgnoreCase("JUICY Grill")));
//        assertTrue(result.stream().anyMatch(store -> store.getName().equalsIgnoreCase("BENjamin")));
//
//    }
//
//    @Test
//    public void testEmptyStoreList() {
//        SearchStore emptySearchStore = new SearchStore(new ArrayList<>());
//
//        List<Store> result = emptySearchStore.searchByName("AnyName");
//        assertTrue(result.isEmpty());
//
//        result = emptySearchStore.filterByLocation("AnyLocation");
//        assertTrue(result.isEmpty());
//
//        result = emptySearchStore.filterByType("AnyType");
//        assertTrue(result.isEmpty());
//    }
//}
