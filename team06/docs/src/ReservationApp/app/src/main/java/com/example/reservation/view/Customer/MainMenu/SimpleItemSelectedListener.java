package com.example.reservation.view.Customer.MainMenu;

import android.view.View;
import android.widget.AdapterView;

/**
 * SimpleItemSelectedListener is an abstract class that implements the AdapterView.OnItemSelectedListener interface.
 * It provides a simplified approach for handling item selection events in AdapterView components like Spinners.
 * <p>
 * Instead of implementing both methods of the AdapterView.OnItemSelectedListener interface,
 * this class only requires you to implement the {@link #onItemSelected(int)} method.
 * The {@link #onItemSelected(AdapterView, View, int, long)} method is already implemented
 * to call the {@link #onItemSelected(int)} method with the position of the selected item.
 * </p>
 */
public abstract class SimpleItemSelectedListener implements AdapterView.OnItemSelectedListener {

    /**
     * This method is called when no item is selected.
     * It is overridden with an empty body because it is not needed for this simple listener.
     *
     * @param parent The AdapterView where the selection was made.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    /**
     * This method is called when an item is selected in the AdapterView.
     * It automatically calls the {@link #onItemSelected(int)} method with the position of the selected item.
     *
     * @param parent The AdapterView where the selection was made.
     * @param view The view within the AdapterView that was clicked.
     * @param position The position of the selected item.
     * @param id The row id of the selected item.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        onItemSelected(position);
    }

    /**
     * This abstract method is called when an item is selected.
     * You need to implement this method to define the behavior for handling the item selection.
     *
     * @param position The position of the selected item in the AdapterView.
     */
    public abstract void onItemSelected(int position);
}
