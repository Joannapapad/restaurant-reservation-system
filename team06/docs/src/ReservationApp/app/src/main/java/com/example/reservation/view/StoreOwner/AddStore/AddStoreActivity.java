package com.example.reservation.view.StoreOwner.AddStore;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reservation.MemoryDao.CurrentUserDAOMemory;
import com.example.reservation.MemoryDao.StoreDAOMemory;
import com.example.reservation.MemoryDao.CategoryDAOMemory;
import com.example.reservation.MemoryDao.CityDAOMemory;
import com.example.reservation.R;

import java.util.List;

public class AddStoreActivity extends AppCompatActivity implements AddStoreView {
    private AddStorePresenter presenter;

    private EditText editTextStoreName, editTextStreet, editTextStreetNumber, editTextCountry, editTextPostalCode, editTextCapacity, editTextTableNumber;
    private Spinner spinnerCity, spinnerStoreType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_store);

        // Use singleton instance for CurrentUserDAO
        CurrentUserDAOMemory currentUserDAO = CurrentUserDAOMemory.getInstance();

        // Initialize other DAOs
        StoreDAOMemory storeDAO = new StoreDAOMemory();
        CityDAOMemory cityDAO = new CityDAOMemory();
        CategoryDAOMemory categoryDAO = new CategoryDAOMemory();

        // Initialize Views
        editTextStoreName = findViewById(R.id.edit_text_store_name);
        editTextStreet = findViewById(R.id.edit_text_street);
        editTextStreetNumber = findViewById(R.id.edit_text_street_number);
        editTextCountry = findViewById(R.id.edit_text_country);
        editTextPostalCode = findViewById(R.id.edit_text_postal_code);
        editTextCapacity = findViewById(R.id.edit_text_capacity);
        editTextTableNumber = findViewById(R.id.edit_text_table_number);
        spinnerCity = findViewById(R.id.spinner_city);
        spinnerStoreType = findViewById(R.id.spinner_store_type);

        // Initialize Presenter
        presenter = new AddStorePresenter(this, currentUserDAO, storeDAO, cityDAO.findAll(), categoryDAO.findAll());

        // Save Button Listener
        findViewById(R.id.button_save_store).setOnClickListener(v -> presenter.saveStore());
    }

    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void setCityOptions(List<String> cities) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(adapter);
    }

    @Override
    public void setCategoryOptions(List<String> categories) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStoreType.setAdapter(adapter);
    }

    @Override
    public String getStoreName() {
        return editTextStoreName.getText().toString().trim();
    }

    @Override
    public String getStreet() {
        return editTextStreet.getText().toString().trim();
    }

    @Override
    public String getStreetNumber() {
        return editTextStreetNumber.getText().toString().trim();
    }

    @Override
    public String getCity() {
        return spinnerCity.getSelectedItem().toString();
    }

    @Override
    public String getCountry() {
        return editTextCountry.getText().toString().trim();
    }

    @Override
    public String getPostalCode() {
        return editTextPostalCode.getText().toString().trim();
    }

    @Override
    public String getStoreType() {
        return spinnerStoreType.getSelectedItem().toString();
    }

    @Override
    public int getCapacity() {
        String capacityStr = editTextCapacity.getText().toString().trim();
        return capacityStr.isEmpty() ? 0 : Integer.parseInt(capacityStr);
    }

    @Override
    public int getTableNumber() {
        String tableNumberStr = editTextTableNumber.getText().toString().trim();
        return tableNumberStr.isEmpty() ? 0 : Integer.parseInt(tableNumberStr);
    }
}
