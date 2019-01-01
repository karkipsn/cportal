package com.example.colors2web.clientportal.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.clientportal.POJO.Inventories.AllItems.CustomerItems.CustomerItem;
import com.example.colors2web.clientportal.R;

import java.util.List;

public class AllItemsAdapter extends RecyclerView.Adapter<AllItemsAdapter.AllHolder> {

    List<CustomerItem > AList;
    Context context;

    public AllItemsAdapter(List<CustomerItem> AList, Context context) {
        this.AList = AList;
        this.context = context;
    }

    @NonNull
    @Override
    public AllHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_allitems, parent, false);
        return new AllHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllHolder holder, int position) {
        CustomerItem citem = AList.get(position);

        holder.sku.setText(citem.getItemSkuNumber());
        holder.name.setText(citem.getItemName());
//        customerItem->replenish + $customerItem->pick = qoh
//        $A2S = replenish + pick - ordered_quantity - requested_quantity
//        inprocess = ordered_quantity + requested_quantity
//       if(($A2S > 0) && ($A2S != null))
//                {{$A2S}}
//              @else
//                0

        String replinsh = citem.getReplenish();
        String pick = citem.getPick();
        String ordered_quantity =citem.getOrderedQuantity();
        String requested_quantity = citem.getRequestedQuantity();

        Long replinsh1 ;
        Long pick1;
        Long oq1;
        Long rq1;



        if( replinsh ==null ) {
            replinsh1 = 0L;

        }else if(replinsh.equals("")&& replinsh.isEmpty()) {
            replinsh1 = 0L;
        }else {
            replinsh1 = Long.valueOf(replinsh);
        }

        if(pick == null){
            pick1 = 0L;
        }else if(pick.equals("")&& pick.isEmpty()) {
            pick1 = 0L;
        }else {
            pick1 = Long.valueOf(pick);
        }

        if( ordered_quantity == null ){
            oq1 = 0L;
        }else if(ordered_quantity.equals("")&&ordered_quantity.isEmpty()) {
            oq1 = 0L;
        }else {
            oq1 = Long.valueOf(ordered_quantity);
        }

        if( requested_quantity ==null ){
            rq1 = 0L;

        }else if (requested_quantity.equals("")&& requested_quantity.isEmpty())
        {
            rq1 = 0L;
        }else{
            rq1 = Long.valueOf(requested_quantity);
        }

        Long a2s1 = replinsh1 + pick1 - oq1 - rq1;
        Long qoh1 = replinsh1 + pick1 ;
        Long inp = oq1 + rq1 ;

        if(a2s1 >0 ){
            holder.a2s.setText(String.valueOf(a2s1));
        }else {
            holder.a2s.setText("0");
        }

        holder.qoh.setText(String.valueOf(qoh1));
        holder.process.setText(String.valueOf(inp));

        String status1 = citem.getItemStatus();

        if(status1.equals("1")) {
            holder.status.setText("Active");
            holder.status.setBackground(holder.status.getResources().getDrawable(R.drawable.flag_gardient));

        }else{
            holder.status.setText("InActive");
            holder.status.setBackground(holder.status.getResources().getDrawable(R.drawable.flag_gradient_red));
        }
    }

    @Override
    public int getItemCount() {
        return AList.size();
    }

    public void updateAdapter(List<CustomerItem> cList) {
        AList =cList;
        notifyDataSetChanged();
    }

    public class AllHolder extends RecyclerView.ViewHolder {

        TextView sku,name,a2s,qoh,process,status;
        public AllHolder(View itemView) {
            super(itemView);

            sku =itemView.findViewById( R.id.adpt_all_sku);
            name =itemView.findViewById( R.id.adpt_all_item_name);
            a2s =itemView.findViewById( R.id.adptr_all_a2s);
            qoh =itemView.findViewById( R.id.adptr_all_qoh);
            process =itemView.findViewById( R.id.adptr_all_inprocess);
            status =itemView.findViewById( R.id.adpt_all_status);
        }
    }
}
