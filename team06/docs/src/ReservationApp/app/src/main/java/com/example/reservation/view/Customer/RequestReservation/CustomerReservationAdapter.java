package com.example.reservation.view.Customer.RequestReservation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.reservation.R;
import com.example.reservation.domain.Reservation;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for displaying a list of reservations in a RecyclerView.
 * This adapter binds reservation data to the view items (i.e., the reservation list item).
 * It uses the ViewHolder pattern for efficient view recycling and binding.
 */
public class CustomerReservationAdapter extends RecyclerView.Adapter<CustomerReservationAdapter.ReservationViewHolder> {

    private List<Reservation> reservations;

    /**
     * Constructor for initializing the adapter with an empty list of reservations.
     */
    public CustomerReservationAdapter() {
        this.reservations = new ArrayList<>();
    }

    /**
     * Called when a new ViewHolder is created to hold the reservation view.
     * It inflates the layout for each reservation item and returns a new ViewHolder.
     *
     * @param parent   The parent ViewGroup (RecyclerView) in which the new item will be added.
     * @param viewType The view type of the new item.
     * @return A new ReservationViewHolder that holds the view for a single reservation item.
     */
    @Override
    public ReservationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reservation_customer, parent, false);
        return new ReservationViewHolder(view);
    }

    /**
     * Called to bind the reservation data to the ViewHolder for a specific position in the list.
     *
     * @param holder   The ViewHolder which holds the reference to the views for each item.
     * @param position The position of the item in the data set.
     */
    @Override
    public void onBindViewHolder(ReservationViewHolder holder, int position) {
        Reservation reservation = reservations.get(position);
        holder.reservationTitle.setText(reservation.getComment());
    }

    /**
     * Returns the total number of items in the reservations list.
     *
     * @return The number of reservation items in the list.
     */
    @Override
    public int getItemCount() {
        return reservations.size();
    }

    /**
     * Updates the reservation data and refreshes the view.
     * This method is called to provide new data to the adapter.
     *
     * @param reservations A list of reservations to update the adapter with.
     */
    public void updateData(List<Reservation> reservations) {
        this.reservations = reservations;
        notifyDataSetChanged();
    }

    /**
     * ViewHolder for the reservation item. This holds references to the views for each reservation item
     * to optimize view recycling and data binding.
     */
    static class ReservationViewHolder extends RecyclerView.ViewHolder {
        TextView reservationTitle;

        /**
         * Constructor for creating a ViewHolder for a single reservation item.
         *
         * @param itemView The view for the single item in the RecyclerView.
         */
        public ReservationViewHolder(View itemView) {
            super(itemView);
            reservationTitle = itemView.findViewById(R.id.ReservationTitle);
        }
    }
}
