package com.example.reservation.dao;

import java.util.List;

public interface CityDAO {

    /**
     * Returns all the cities.
     * @return A list of cities.
     */
    List<String> findAll();

    /**
     * Finds a city by its name.
     * @param cityid The name of the city.
     * @return The city if found, otherwise null.
     */
    String find(String cityid);

    /**
     * Deletes a city.
     * @param city The name of the city to delete.
     */
    void delete(String city);

    /**
     * Initializes the cities list with default data.
     */
    void initializeCities();  // New method to initialize cities
}
