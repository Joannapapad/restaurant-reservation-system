package com.example.reservation.view.StoreOwner.RequestReservationList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.reservation.R;
import com.example.reservation.domain.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder> {

    private List<Reservation> reservationList;

    public ReservationAdapter() {
        this.reservationList = new ArrayList<>();
    }

    public void updateData(List<Reservation> newReservationList) {
        this.reservationList = newReservationList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_item, parent, false);
        return new ReservationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position) {
        Reservation reservation = reservationList.get(position);
        holder.txtReservationId.setText(String.valueOf(reservation.getReservationId()));
        holder.txtReservationDetails.setText("Customer ID: " + reservation.getCustomerId() + " - People: " + reservation.getNumOfPeople());
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    static class ReservationViewHolder extends RecyclerView.ViewHolder {

        TextView txtReservationId, txtReservationDetails;

        public ReservationViewHolder(@NonNull View itemView) {
            super(itemView);
            txtReservationId = itemView.findViewById(R.id.txtReservationId);
            txtReservationDetails = itemView.findViewById(R.id.txtReservationDetails);
        }
    }
}
