package com.example.colors2web.clientportal.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.clientportal.POJO.Offices.CityBin;
import com.example.colors2web.clientportal.R;

import java.util.List;

public class CityBinsAdapter extends RecyclerView.Adapter<CityBinsAdapter.CityHolder> {

    List<CityBin>BinList;
    Activity context;

    public CityBinsAdapter(List<CityBin> binList, Activity context) {
        BinList = binList;
        this.context = context;
    }

    @NonNull
    @Override
    public CityBinsAdapter.CityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_citybins, parent, false);
        return new CityHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityBinsAdapter.CityHolder holder, int position) {
        CityBin bins = BinList.get(position);

        holder.name.setText(bins.getBin());
        holder.created.setText(bins.getCreatedAt());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Edit CLicked",Toast.LENGTH_SHORT).show();

            }
        });

        holder.edit_deadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Edit Deadline CLicked",Toast.LENGTH_SHORT).show();

            }
        });
        holder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Order CLicked",Toast.LENGTH_SHORT).show();

            }
        });

        holder.masterbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Masterbox CLicked",Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return BinList.size();
    }

    public void updateAdapter(List<CityBin> cList) {
        BinList = cList;
        notifyDataSetChanged();
    }

    public class CityHolder extends RecyclerView.ViewHolder {
        TextView name,created;
        Button edit,edit_deadline,order,masterbox;
        public CityHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.adptr_city_name);
            created = itemView.findViewById(R.id.adptr_city_created);
            edit = itemView.findViewById(R.id.adapt_city_btn_edit);
            edit_deadline = itemView.findViewById(R.id.adapt_city_btn_deadline);
            order = itemView.findViewById(R.id.adapt_city_btn_orders);
            masterbox = itemView.findViewById(R.id.adapt_city_btn_mbox);
        }
    }
}
