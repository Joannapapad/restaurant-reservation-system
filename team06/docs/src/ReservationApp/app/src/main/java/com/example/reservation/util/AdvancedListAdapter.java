package com.example.reservation.util;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reservation.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdvancedListAdapter extends BaseAdapter implements Filterable {
    private static final String TAG = "AdvancedListAdapter";

    private Context context;
    private LayoutInflater inflater;
    private List<Quadruple> dataSource, rawDataSource;
    private ItemFilter mFilter = new ItemFilter();
    private String locationFilter = "";
    private String categoryFilter = "";
    private String nameFilter = "";

    public AdvancedListAdapter(Context context) {
        this.context = context;
        this.dataSource = new ArrayList<>();
        this.rawDataSource = new ArrayList<>();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Quadruple quadruple = (Quadruple) getItem(position);
        Log.d(TAG, "getView: Position: " + position + ", Data: " + quadruple);

        View rowView = inflater.inflate(R.layout.adapter_item, parent, false);

        ((TextView) rowView.findViewById(R.id.item_name)).setText(quadruple.getFirst());
        ((TextView) rowView.findViewById(R.id.item_lastname)).setText(quadruple.getSecond());
        ((TextView) rowView.findViewById(R.id.item_details)).setText(quadruple.getThird());

        return rowView;
    }


    public void loadSource(List<Quadruple> dataSource) {
        Log.d(TAG, "loadSource: Loading " + dataSource.size() + " items into the adapter");
        this.dataSource = dataSource;
        Collections.reverse(this.dataSource);
        this.rawDataSource = new ArrayList<>(dataSource); // Create a shallow copy
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String filterString = constraint.toString().toLowerCase().trim();
            Log.d(TAG, "performFiltering: Filtering with constraint: " + filterString);

            FilterResults results = new FilterResults();
            List<Quadruple> matches = new ArrayList<>();

            for (Quadruple quadruple : rawDataSource) {
                boolean matchesName = quadruple.getFirst().toLowerCase().contains(filterString);
                if (matchesName) {
                    matches.add(quadruple);
                    Log.d(TAG, "performFiltering: Match found: " + quadruple.getFirst());
                }
            }

            results.values = matches;
            results.count = matches.size();
            Log.d(TAG, "performFiltering: Found " + matches.size() + " matching items");
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataSource = (List<Quadruple>) results.values;
            Log.d(TAG, "publishResults: Filtered " + dataSource.size() + " items");
            notifyDataSetChanged();
        }
        }

    }
