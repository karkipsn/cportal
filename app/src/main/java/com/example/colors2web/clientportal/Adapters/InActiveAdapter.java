package com.example.colors2web.clientportal.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.colors2web.clientportal.POJO.Inventories.InActiveItems.StoreInactiveItem;
import com.example.colors2web.clientportal.R;

import java.util.ArrayList;
import java.util.List;

public class InActiveAdapter extends RecyclerView.Adapter<InActiveAdapter.InActiveHolder> {

    List<StoreInactiveItem> CList ;
    Context context;

    public InActiveAdapter(List<StoreInactiveItem> CList, Context context) {
        this.CList = CList;
        this.context = context;
    }

    @NonNull
    @Override
    public InActiveAdapter.InActiveHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_inactive_items, parent, false);
        return new InActiveHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InActiveAdapter.InActiveHolder holder, int position) {
        StoreInactiveItem items = CList.get(position);

        holder.sku.setText(items.getItemSkuNumber());
        holder.name.setText(items.getItemName());
        holder.status.setText(items.getItemStatus());
        holder.replinsh.setText(items.getReplenish());
        holder.pick.setText(items.getPick());
//        holder.qoh.setText(items.ge);
//        pick+replnish =qoh
        holder.pickb.setText(String.valueOf(items.getPickBalance()));
        holder.oquantity.setText(items.getOrderedQuantity());
        holder.committed.setText(String.valueOf(items.getRequestedQuantity()));
//        holder.a2s.setText(items.get);
        holder.last.setText(items.getUpdatedAt());

        String c_status =items.getItemStatus();

        if (c_status != null) {
                            switch (c_status) {
                                case "1":
                                    holder.s_status.setChecked(true);
                                    break;

                                case "0":
                                    holder.s_status.setChecked(false);
                                    break;
                            }
                        }
    }

    @Override
    public int getItemCount() {
        return CList.size();
    }

    public void updateAdapter(List<StoreInactiveItem> cList) {
        CList =cList;
        notifyDataSetChanged();
    }

    public class InActiveHolder extends RecyclerView.ViewHolder {
        TextView sku, name, status, replinsh, pick,qoh,pickb,oquantity,committed,a2s,last;
        Switch s_status;
        public InActiveHolder(View itemView) {
            super(itemView);

            sku = itemView.findViewById(R.id.adpt_pick_sku);
            name = itemView.findViewById(R.id.adpt_pick_item_name);
            status = itemView.findViewById(R.id.adpt_pick_status);
            s_status = itemView.findViewById(R.id.adpt_pick_switch_status);
            replinsh = itemView.findViewById(R.id.adpt_pick_replnish);
            pick = itemView.findViewById(R.id.adpt_pick_pick);
            qoh = itemView.findViewById(R.id.adptr_pick_qoh);
            pickb = itemView.findViewById(R.id.adptr_pick_pick_balance);
            oquantity = itemView.findViewById(R.id.adptr_pick_ordered_quantity);
            committed = itemView.findViewById(R.id.adptr_pick_committed);
            a2s = itemView.findViewById(R.id.adptr_pick_a2s);
            last = itemView.findViewById(R.id.adptr_pick_last_updated);

        }
    }
}
