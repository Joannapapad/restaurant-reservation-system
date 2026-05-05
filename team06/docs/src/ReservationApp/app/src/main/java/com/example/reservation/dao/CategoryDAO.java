package com.example.reservation.dao;

import java.util.List;

public interface CategoryDAO {

    /**
     * Finds a category by its name.
     * @param categoryName The name of the category.
     * @return The category name if found, or {@code null} if not found.
     */
    String find(String categoryName);

    /**
     * Saves a category to the data source.
     * @param category The category name to save.
     */
    void save(String category);

    /**
     * Deletes a category from the data source.
     * @param category The category name to delete.
     */
    void delete(String category);

    /**
     * Retrieves all categories from the data source.
     * @return A list of all category names.
     */
    List<String> findAll();
}
