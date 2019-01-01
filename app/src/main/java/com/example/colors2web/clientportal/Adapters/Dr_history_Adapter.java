package com.example.colors2web.clientportal.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.clientportal.POJO.InventoriesDeleveries.DeliveryHistory.DrShipment;
import com.example.colors2web.clientportal.R;

import java.util.List;

    public class Dr_history_Adapter extends RecyclerView.Adapter<Dr_history_Adapter.DrHistoyHolder> {
        List<DrShipment> DrList;
        Context context;

        public Dr_history_Adapter(List<DrShipment> drList, Context context) {
            DrList = drList;
            this.context = context;
        }


        @NonNull
        @Override
        public Dr_history_Adapter.DrHistoyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.activity_post_dr_adapter,parent,false);
            return new DrHistoyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Dr_history_Adapter.DrHistoyHolder holder, int position) {
            DrShipment shipment =DrList.get(position);

            String palletc =String.valueOf(shipment.getSaidPalletCount())+"/"+String.valueOf(shipment.getActualPalletCount());
            String boxc =String.valueOf(shipment.getSaidBoxCount())+"/"+String.valueOf(shipment.getActualBoxCount());
            String itemc =String.valueOf(shipment.getSaidItemCount())+"/"+String.valueOf(shipment.getActualItemCount());


            holder.sku.setText(shipment.getItemSkuNumber());
            holder.item_name.setText(shipment.getItemName());
            holder.dr.setText(String.valueOf(shipment.getDrNumber()));
            holder.pallet.setText(palletc);
            holder.box.setText(boxc);
            holder.item.setText(itemc);
            holder.deliver.setText(shipment.getEstDeliveryDate());

        }

        @Override
        public int getItemCount() {
            return DrList.size();
        }
        public void updateAnswers(List<DrShipment> drList) {
            DrList=drList;
            notifyDataSetChanged();
        }

        public class DrHistoyHolder extends RecyclerView.ViewHolder {
            TextView sku,item_name,dr,pallet,box,item,deliver;
            public DrHistoyHolder(View itemView) {
                super(itemView);
                sku = itemView.findViewById(R.id.adpt_dr_sku);
                item_name = itemView.findViewById(R.id.adpt_dr_itemname);
                dr = itemView.findViewById(R.id.adpt_dr_dr);
                pallet = itemView.findViewById(R.id.adptr_dr_said_pallet);
                box = itemView.findViewById(R.id.adptr_dr_said_box);
                item = itemView.findViewById(R.id.adptr_dr_said_item);
                deliver = itemView.findViewById(R.id.adptr_dr_deldate);
            }
        }
    }

