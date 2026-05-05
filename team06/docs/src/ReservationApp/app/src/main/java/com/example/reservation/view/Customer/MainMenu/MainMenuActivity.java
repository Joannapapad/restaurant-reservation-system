package com.example.reservation.view.Customer.MainMenu;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.reservation.MemoryDao.CategoryDAOMemory;
import com.example.reservation.MemoryDao.CityDAOMemory;
import com.example.reservation.MemoryDao.StoreDAOMemory;
import com.example.reservation.R;
import com.example.reservation.domain.Store;
import com.example.reservation.util.AdvancedListAdapter;
import com.example.reservation.util.Quadruple;
import com.example.reservation.view.Customer.RequestReservation.CustomerRequestsActivity;
import com.example.reservation.view.Customer.StoreDetail.CustomerStoreDetailActivity;
import com.example.reservation.view.Customer.Profile.CustomerProfileActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * MainMenuActivity handles the main menu interface for customers.
 * It provides a searchable, filterable list of stores, navigation to other activities,
 * and options to select categories and locations.
 */
public class MainMenuActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final String TAG = "MainMenuActivity";

    private SearchView searchBar;
    private ListView itemListView;
    private AdvancedListAdapter adapter;
    private StoreDAOMemory storeDAO;
    private List<Store> stores;
    private Spinner locationFilterSpinner;
    private List<String> cityList = new ArrayList<>();
    private List<String> selectedCategories = new ArrayList<>();
    private List<String> categories = new CategoryDAOMemory().findAll();
    private String selectedLocation = "All Locations"; // Default to "All Locations"
    private String searchQuery = ""; // Default to empty search query

    /**
     * Initializes the activity, setting up the UI components, adapters, and event listeners.
     *
     * @param savedInstanceState If the activity is being re-initialized, this contains the previous state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage_customer);

        Button chooseCategoryButton = findViewById(R.id.choose_category_button);
        chooseCategoryButton.setOnClickListener(v -> openCategorySelectionDialog());
        locationFilterSpinner = findViewById(R.id.spinner_location_filter);
        populateLocationSpinner();

        // Initialize the adapter and list view
        adapter = new AdvancedListAdapter(this);

        itemListView = findViewById(R.id.item_list_view);
        itemListView.setAdapter(adapter);
        itemListView.setTextFilterEnabled(true);

        // Load stores from DAO
        storeDAO = new StoreDAOMemory();
        stores = storeDAO.findAll();

        // Get unique categories from the stores
        for (Store store : stores) {
            if (!categories.contains(store.getCategory())) {
                categories.add(store.getCategory());
            }
        }
        // Load stores into the adapter
        adapter.loadSource(convertStoresToQuadruples(stores));

        // Initialize the search bar
        searchBar = findViewById(R.id.search_bar);
        searchBar.setIconifiedByDefault(false);
        searchBar.setOnQueryTextListener(this);

        // Set up bottom navigation
        ImageButton bottomProfile = findViewById(R.id.bottom_profile);
        ImageButton bottomHome = findViewById(R.id.bottom_home);
        ImageButton bottomRequests = findViewById(R.id.bottom_requests);

        bottomProfile.setOnClickListener(v -> handleProfileClick());
        bottomHome.setOnClickListener(v -> handleHomeClick());
        bottomRequests.setOnClickListener(v -> handleRequestsClick());

        // Inside onCreate(), after initializing the itemListView
        itemListView.setOnItemClickListener((parent, view, position, id) -> {
            Quadruple selectedItem = (Quadruple) adapter.getItem(position);
            int storeId = selectedItem.getUID(); // Assuming the first field in Quadruple is the store ID

            // Navigate to the detail activity
            Intent intent = new Intent(MainMenuActivity.this, CustomerStoreDetailActivity.class);
            intent.putExtra("STORE_ID", storeId); // Pass the store ID to the detail activity
            startActivity(intent);
        });


    }

    /**
     * Populates the location filter spinner with cities and "All Locations".
     * Sets the selected location to the user's choice and triggers filtering.
     */
    private void populateLocationSpinner() {
        List<String> cities = new CityDAOMemory().findAll();

        cityList.add("All Locations");
        cityList.addAll(cities);

        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cityList);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationFilterSpinner.setAdapter(locationAdapter);

        // Handle location selection change (Spinner)
        locationFilterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedLocation = (String) parentView.getItemAtPosition(position);
                applyFilters();  // Apply filters when location changes
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing if no item is selected
            }
        });
    }

    /**
     * Opens a dialog for selecting categories and updates the filters based on user selection.
     */
    private void openCategorySelectionDialog() {
        // Create a list of categories to display
        CharSequence[] categoryArray = categories.toArray(new CharSequence[categories.size()]);

        boolean[] checkedItems = new boolean[categories.size()];
        // Mark the items as checked based on selected categories
        for (int i = 0; i < categories.size(); i++) {
            checkedItems[i] = selectedCategories.contains(categories.get(i));
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Categories")
                .setMultiChoiceItems(categoryArray, checkedItems, (dialog, which, isChecked) -> {
                    String selectedCategory = categories.get(which);
                    if (isChecked) {
                        selectedCategories.add(selectedCategory);
                    } else {
                        selectedCategories.remove(selectedCategory);
                    }
                })
                .setPositiveButton("OK", (dialog, which) -> applyFilters())
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    /**
     * Applies all filters (location, category, and search query) to the list of stores
     * and updates the displayed list in the adapter.
     */
    private void applyFilters() {
        List<Quadruple> filteredList = new ArrayList<>();

        for (Store store : stores) {
            boolean matchesLocation = selectedLocation.equals("All Locations") || store.getAddress().getCity().equalsIgnoreCase(selectedLocation);
            boolean matchesCategory = selectedCategories.isEmpty() || selectedCategories.contains(store.getCategory());
            boolean matchesText = TextUtils.isEmpty(searchQuery) || store.getName().toLowerCase().contains(searchQuery.toLowerCase());

            if (matchesLocation && matchesCategory && matchesText) {
                filteredList.add(new Quadruple(store.getStoreId(), store.getName(), store.getAddress().getCity(), store.getCategory()));
            }
        }

        // Update the adapter with the filtered list
        adapter.loadSource(filteredList);
    }

    /**
     * Updates the search query whenever the user types a new text.
     * Applies filters when the search text changes.
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        searchQuery = newText;  // Update search query
        applyFilters();  // Apply filters whenever search text changes
        return true;
    }

    /**
     * Handles the search query submission (not used).
     *
     * @param query The submitted query.
     * @return False to indicate no action is required.
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    /**
     * Handles the profile button click and navigates to the CustomerProfileActivity.
     */
    private void handleProfileClick() {
        Intent intent = new Intent(MainMenuActivity.this, CustomerProfileActivity.class);
        startActivity(intent);
    }

    /**
     * Handles the home button click and reloads the MainMenuActivity.
     */
    private void handleHomeClick() {
        Intent intent = new Intent(MainMenuActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }

    /**
     * Handles the requests button click and navigates to the CustomerRequestsActivity.
     */
    private void handleRequestsClick() {
        Intent intent = new Intent(MainMenuActivity.this, CustomerRequestsActivity.class);
        startActivity(intent);
    }

    /**
     * Converts a list of stores into a list of Quadruple objects for the adapter.
     *
     * @param stores The list of stores to convert.
     * @return A list of Quadruple objects.
     */
    private List<Quadruple> convertStoresToQuadruples(List<Store> stores) {
        List<Quadruple> quadruples = new ArrayList<>();
        for (Store store : stores) {
            Quadruple quadruple = new Quadruple(
                    store.getStoreId(),
                    store.getName(),
                    store.getAddress().getCity(),
                    store.getCategory()
            );
            quadruples.add(quadruple);
        }
        return quadruples;
    }
}
