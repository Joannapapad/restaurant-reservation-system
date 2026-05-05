package com.example.reservation.MemoryDao;

import com.example.reservation.dao.CityDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CityDAOMemory implements CityDAO {
    private static List<String> cities = new ArrayList<>();

    /**
     * Initializes the cities list with default values.
     */
    public void initializeCities() {
        /**
         * Initializes the cities list with default values.
         */
        cities = new ArrayList<>(Arrays.asList(
                "Αθήνα", "Θεσσαλονίκη", "Πάτρα", "Ηράκλειο", "Λάρισα", "Βόλος", "Ιωάννινα", "Χανιά", "Καβάλα",
                "Ρόδος", "Κέρκυρα", "Τρίκαλα", "Σέρρες", "Αγρίνιο", "Χαλκίδα", "Κατερίνη", "Αλεξανδρούπολη",
                "Ξάνθη", "Λαμία", "Κομοτηνή", "Πρέβεζα", "Καλαμάτα", "Ρέθυμνο", "Σπάρτη"
        ));
    }

    /**
     * Deletes a city from the list.
     */
    public void delete(String entity) {
        cities.remove(entity);
    }

    public List<String> findAll() {
        if (cities.isEmpty()) {
            initializeCities();
        }
        return new ArrayList<>(cities);
    }
    /**
     * Finds a specific city by its ID.
     *
     * @param cityid The ID of the city to find.
     * @return The city if found, or null if no matching city exists.
     */
    public String find(String cityid) {
        for (String city : cities) {
            if (city.equals(cityid)) {
                return city;
            }
        }
        return null;
    }
}
