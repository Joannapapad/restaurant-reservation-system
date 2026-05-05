package com.example.reservation.MemoryDao;

import com.example.reservation.dao.CategoryDAO;

import java.util.ArrayList;
import java.util.Arrays; // For initializing the list with predefined values
import java.util.List;

public class CategoryDAOMemory implements CategoryDAO {

    /**
     * A pre-initialized list of categories that support table reservations.
     * Categories are stored in Greek and include common venue types.
     */
    private final List<String> categories = new ArrayList<>(Arrays.asList(
            "Εστιατόριο",
            "Καφέ",
            "Μπαρ",
            "Παμπ",
            "Χώρος Εκδηλώσεων",
            "Αίθουσα Δεξιώσεων",
            "Σαλόνι",
            "Ψαροταβέρνα",
            "Ταβέρνα"
    ));

    /**
     * Finds a category by its name (case-insensitive).
     *
     * @param categoryName The name of the category to find.
     * @return The category if found, or null if no matching category exists.
     */
    @Override
    public String find(String categoryName) {
        for (String category : categories) {
            if (category.equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        return null;
    }

    /**
     * Saves a new category to the in-memory list.
     *
     * If the category already exists, it will not be added again.
     *
     * @param category The name of the category to save.
     */

    @Override
    public void save(String category) {
        if (!categories.contains(category)) {
            categories.add(category);
        }
    }

    /**
     * Deletes a category from the in-memory list.
     *
     * If the category does not exist, no action is taken.
     *
     * @param category The name of the category to delete.
     */

    @Override
    public void delete(String category) {
        categories.remove(category);
    }

    /**
     * Retrieves all categories in the in-memory list.
     *
     * @return A new list containing all categories.
     */
    @Override
    public List<String> findAll() {
        return new ArrayList<>(categories);
    }
}
