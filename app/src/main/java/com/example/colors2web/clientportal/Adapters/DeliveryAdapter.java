package com.example.colors2web.clientportal.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.clientportal.POJO.InventoriesDeleveries.Deleveries.DrShipment;
import com.example.colors2web.clientportal.R;

import java.util.List;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.DeliverHolder> {

    List<DrShipment> DrList;
    Context context;

    public DeliveryAdapter(List<DrShipment> drList, Context context) {
        DrList = drList;
        this.context = context;
    }

    @NonNull
    @Override
    public DeliverHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_deleveries, parent, false);
        return new DeliverHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliverHolder holder, int position) {
        DrShipment ship = DrList.get(position);

        holder.delivery.setText(String.valueOf(ship.getDrNumber()));
        holder.pallet.setText(String.valueOf(ship.getSaidPalletCount()));
        holder.box.setText(String.valueOf(ship.getSaidBoxCount()));
        holder.edate.setText(String.valueOf(ship.getEstDeliveryDate()));
        holder.cdate.setText(String.valueOf(ship.getCreatedAt()));
        holder.description.setText(String.valueOf(ship.getDrDescription()));

        String statuss = ship.getServiceStatus();

        if(statuss.equals("2")){
            holder.ststus.setText("Completed");
            holder.ststus.setBackground(holder.ststus.getResources().getDrawable(R.drawable.flag_gardient));

        }
        else if(statuss.equals("1")){
            holder.ststus.setText("Received");
            holder.ststus.setBackground(holder.ststus.getResources().getDrawable(R.drawable.flag_gradient_red));
        }
        else{
            holder.ststus.setText("Created");
            holder.ststus.setBackground(holder.ststus.getResources().getDrawable(R.drawable.flag_gradient_primary));
        }
    }

    @Override
    public int getItemCount() {
        return DrList.size();
    }

    public void updateAdapter(List<DrShipment> cList) {
        DrList = cList;
        notifyDataSetChanged();
    }

    public class DeliverHolder extends RecyclerView.ViewHolder {

        TextView delivery,pallet,box,edate,cdate,ststus,description;
        public DeliverHolder(View itemView) {
            super(itemView);
            delivery = itemView.findViewById(R.id.adpt_delivery_delivery);
            pallet = itemView.findViewById(R.id.adpt_delivery_paller_count);
            box = itemView.findViewById(R.id.adpt_delivery_box_count);
            edate = itemView.findViewById(R.id.adpt_delivery_date);
            cdate = itemView.findViewById(R.id.adpt_delivery_created);
            description = itemView.findViewById(R.id.adpt_delivery_description);
            ststus = itemView.findViewById(R.id.adpt_delivery_status);
        }
    }
}
