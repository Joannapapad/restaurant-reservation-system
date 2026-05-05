package com.example.reservation.view.StoreOwner.MainMenu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reservation.R;
import com.example.reservation.domain.Store;

import java.util.List;

/**
 * RecyclerView Adapter for displaying a list of stores in a RecyclerView.
 * It binds store data to the corresponding views and handles item click events.
 */
public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {

    private final List<Store> storeList;
    private final StoreSelectionListener listener;

    /**
     * Constructor to initialize the adapter with the store list and selection listener.
     *
     * @param storeList The list of stores to be displayed in the RecyclerView.
     * @param listener  The listener for handling store selection events when an item is clicked.
     */
    public StoreAdapter(List<Store> storeList, StoreSelectionListener listener) {
        this.storeList = storeList;
        this.listener = listener;
    }

    /**
     * Called when a new ViewHolder is created to hold the store item view.
     *
     * @param parent   The parent ViewGroup that this new view will be added to.
     * @param viewType The view type of the new view.
     * @return A new instance of StoreViewHolder.
     */
    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_item, parent, false);
        return new StoreViewHolder(view);
    }

    /**
     * Binds the data of a store to the corresponding views in the ViewHolder.
     *
     * @param holder   The ViewHolder which will display the store data.
     * @param position The position of the store in the list.
     */
    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        final Store currentStore = storeList.get(position);
        holder.storeName.setText(currentStore.getName());
        holder.storeCategory.setText(currentStore.getCategory());

        // Set click listener to notify the listener interface
        holder.itemView.setOnClickListener(view -> listener.onStoreSelected(currentStore));
    }

    /**
     * Returns the total number of items in the store list.
     *
     * @return The number of stores in the list.
     */
    @Override
    public int getItemCount() {
        return storeList.size();
    }

    /**
     * ViewHolder for storing references to the store item views.
     */
    public static class StoreViewHolder extends RecyclerView.ViewHolder {
        public final TextView storeName;
        public final TextView storeCategory;

        /**
         * Constructor to initialize the ViewHolder with the store item views.
         *
         * @param itemView The store item view.
         */
        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);
            storeName = itemView.findViewById(R.id.store_name);
            storeCategory = itemView.findViewById(R.id.store_category);
        }

        /**
         * Returns a string representation of the StoreViewHolder.
         *
         * @return A string representing the store name.
         */
        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + storeName.getText() + "'";
        }
    }

    /**
     * Listener interface for handling store selection events.
     */
    public interface StoreSelectionListener {
        /**
         * Callback method when a store item is selected.
         *
         * @param store The selected store.
         */
        void onStoreSelected(Store store);
    }
}
