package com.example.reservation.view.StoreOwner.RequestDetails;

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

    public RequestAdapter() {
        this.requestList = new ArrayList<>();
    }

    public void updateData(List<Request> newRequestList) {
        this.requestList = newRequestList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item, parent, false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        Request request = requestList.get(position);
        holder.txtRequestId.setText(String.valueOf(request.getReservationID()));
        holder.txtRequestDetails.setText("Customer ID: " + request.getCustomerId() + " - People: " + request.getNumofpeople());
    }

    @Override
    public int getItemCount() {
        return requestList.size();
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
