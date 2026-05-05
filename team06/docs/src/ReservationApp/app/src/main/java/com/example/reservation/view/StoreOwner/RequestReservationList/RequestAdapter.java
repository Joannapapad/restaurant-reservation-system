package com.example.reservation.view.StoreOwner.RequestReservationList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.reservation.R;
import com.example.reservation.domain.Request;

import java.util.ArrayList;
import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {

    private List<Request> requestList;
    private OnRequestClickListener onRequestClickListener;

    public RequestAdapter() {
        this.requestList = new ArrayList<>();
    }

    public interface OnRequestClickListener {
        void onRequestClick(Request request);
    }

    public void setOnRequestClickListener(OnRequestClickListener listener) {
        this.onRequestClickListener = listener;
    }

    public void updateData(List<Request> newRequestList) {
        if (newRequestList != null) {
            System.out.println("Debug: Received new request list with size = " + newRequestList.size());

            // Log the details of each request
            for (Request request : newRequestList) {
                System.out.println("Debug: Request ID = " + request.getReservationID() +
                        ", Customer ID = " + request.getCustomerId() +
                        ", Number of People = " + request.getNumofpeople());
            }

            requestList.clear();  // Ensure old data is removed
            requestList.addAll(newRequestList);
            notifyDataSetChanged();  // Notify RecyclerView to update the entire dataset

            System.out.println("Debug: Adapter data size after update = " + requestList.size());
        } else {
            System.out.println("Debug: Received null or empty request list");
            requestList.clear();
            notifyDataSetChanged();  // Clear the adapter data
        }
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item, parent, false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        if (position >= requestList.size()) {
            System.out.println("Debug: Invalid binding position: " + position);
            return;  // Prevent invalid binding
        }

        Request request = requestList.get(position);
        System.out.println("Debug: Binding request at position " + position + ", Request ID = " + request.getReservationID());

        holder.txtRequestId.setText(String.valueOf(request.getReservationID()));
        holder.txtRequestDetails.setText("Customer ID: " + request.getCustomerId() + " - People: " + request.getNumofpeople() + "this is request");

        holder.itemView.setOnClickListener(v -> {
            if (onRequestClickListener != null) {
                onRequestClickListener.onRequestClick(request);
            }
        });
    }

    @Override
    public int getItemCount() {
        return requestList == null ? 0 : requestList.size();
    }

    static class RequestViewHolder extends RecyclerView.ViewHolder {

        TextView txtRequestId, txtRequestDetails;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRequestId = itemView.findViewById(R.id.txtRequestId);
            txtRequestDetails = itemView.findViewById(R.id.txtRequestDetails);
        }
    }
}
