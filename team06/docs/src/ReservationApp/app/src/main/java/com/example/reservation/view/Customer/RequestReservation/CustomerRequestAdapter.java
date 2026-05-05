package com.example.reservation.view.Customer.RequestReservation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.reservation.R;
import com.example.reservation.domain.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter class for displaying a list of customer requests in a RecyclerView.
 */
public class CustomerRequestAdapter extends RecyclerView.Adapter<CustomerRequestAdapter.RequestViewHolder> {

    private List<Request> requestList;
    private OnRequestClickListener onRequestClickListener;

    /**
     * Listener interface for handling request item click events.
     */
    public interface OnRequestClickListener {
        /**
         * Callback method invoked when a request item is clicked.
         *
         * @param request The clicked {@link Request} object.
         */
        void onRequestClick(Request request);
    }

    /**
     * Sets the listener for handling request item clicks.
     *
     * @param listener The listener to handle item clicks.
     */
    public void setOnRequestClickListener(OnRequestClickListener listener) {
        this.onRequestClickListener = listener;
    }

    /**
     * Constructor to initialize the adapter with an empty list.
     */
    public CustomerRequestAdapter() {
        this.requestList = new ArrayList<>();
    }

    @Override
    public RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request_customer, parent, false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RequestViewHolder holder, int position) {
        Request request = requestList.get(position);

        // Set the request details into the view
        holder.tvRequestDate.setText("Request Date: " + request.getRequestDate().getYear() + "-"
                + request.getRequestDate().getMonth() + "-"
                + request.getRequestDate().getDayOfMonth() + " "
                + request.getRequestTime().getHour() + ":"
                + request.getRequestTime().getMinute());

        if (request.getScheduledDate() != null && request.getScheduledTime() != null) {
            holder.tvScheduledDate.setText("Scheduled Date: " + request.getScheduledDate().getYear() + "-"
                    + request.getScheduledDate().getMonth() + "-"
                    + request.getScheduledDate().getDayOfMonth() + " "
                    + request.getScheduledTime().getHour() + ":"
                    + request.getScheduledTime().getMinute());
        }

        holder.tvStoreId.setText("Store ID: " + request.getStoreID());
        holder.tvComment.setText("Comment: " + request.getComment());

        // Set item click listener
        holder.itemView.setOnClickListener(v -> {
            if (onRequestClickListener != null) {
                onRequestClickListener.onRequestClick(request);
            }
        });
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }

    /**
     * ViewHolder class for holding and managing the views for a single request item.
     */
    public static class RequestViewHolder extends RecyclerView.ViewHolder {
        TextView tvRequestDate, tvScheduledDate, tvStoreId, tvComment;

        /**
         * Constructor to initialize the views for the request item.
         *
         * @param itemView The layout view for the request item.
         */
        public RequestViewHolder(View itemView) {
            super(itemView);
            tvRequestDate = itemView.findViewById(R.id.tv_request_date);
            tvScheduledDate = itemView.findViewById(R.id.tv_scheduled_date);
            tvStoreId = itemView.findViewById(R.id.tv_store_id);
            tvComment = itemView.findViewById(R.id.tv_comment);
        }
    }

    /**
     * Updates the adapter's data with a new list of requests and refreshes the view.
     *
     * @param requests The new list of {@link Request} objects to display.
     */
    public void updateData(List<Request> requests) {
        this.requestList = requests;
        notifyDataSetChanged();
    }
}
