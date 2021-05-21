package com.drasare.cancerscreening.ui.provider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.drasare.cancerscreening.R;

import java.util.ArrayList;
import java.util.List;

public class ProviderRecyclerAdapter extends RecyclerView.Adapter<ProviderRecyclerAdapter.MyViewHolder> implements Filterable{
    private List<Provider> providerList;
    private   ClickListener  clickListener;
    private List<Provider> providerListFiltered;
    private Context context;

   public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

       public TextView name;
       public TextView tel;
       public TextView city;
       public TextView address;

        public MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.name);
            tel = v.findViewById(R.id.tel);
            city = v.findViewById(R.id.city);
            address = v.findViewById(R.id.address);
            v.setOnClickListener(this);
        }

       @Override
       public void onClick(View v) {
           clickListener.onItemClick(getAdapterPosition(), v);
       }

       @Override
       public boolean onLongClick(View v) {
           clickListener.onItemLongClick(getAdapterPosition(), v);
           return false;
       }

   }

    public ProviderRecyclerAdapter(Context context, List<Provider> providerList) {
        this.providerList = providerList;
        this.providerListFiltered = providerList;
        this.context = context;
    }


    @Override
    public ProviderRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.provider_view, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.name.setText(providerListFiltered.get(position).getName());
        holder.tel.setText(providerListFiltered.get(position).getTelephone());
        holder.city.setText(providerListFiltered.get(position).getCity());
        holder.address.setText(providerListFiltered.get(position).getAddress());
    }


    @Override
    public int getItemCount() {
        return providerListFiltered.size();
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    providerListFiltered = providerList;
                } else {
                    List<Provider> filteredList = new ArrayList<>();
                    for (Provider row : providerList) {

                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getCity().contains(charSequence)
                                || row.getAddress().contains(charSequence) || row.getTelephone().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    providerListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = providerListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                providerListFiltered = (ArrayList<Provider>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}