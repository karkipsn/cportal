package com.example.colors2web.clientportal.Activities.Navigation.Inventories;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.colors2web.clientportal.Adapters.InActiveAdapter;
import com.example.colors2web.clientportal.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.clientportal.POJO.Inventories.InActiveItems.ResponseInActive;
import com.example.colors2web.clientportal.POJO.Inventories.InActiveItems.StoreInactiveItem;
import com.example.colors2web.clientportal.R;
import com.example.colors2web.clientportal.api.APIClient;
import com.example.colors2web.clientportal.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_InActiveItems extends Fragment {

    APIInterface apiInterface;
    RecyclerView mrecycleView;
    InActiveAdapter padapter;
    List<StoreInactiveItem> CList = new ArrayList<>();
    //    Toolbar toolbar;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common_recycleview, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    apiInterface = APIClient.getClient().create(APIInterface.class);

//        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
//        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);


    mrecycleView = view.findViewById(R.id.recycler_view_common);
    padapter = new InActiveAdapter(CList,getContext());

    RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getContext());
//        mrecycleView.setHasFixedSize(true);
        mrecycleView.setLayoutManager(mlayoutManager);

        mrecycleView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 16));

        mrecycleView.setItemAnimator(new DefaultItemAnimator());
        mrecycleView.setAdapter(padapter);

    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
    final String email = preferences.getString("email", "");
    final String password = preferences.getString("password", "");
    final String customer_id = preferences.getString("customer_id", "");
    final String special_program = preferences.getString("special_program", "");

    loadAdapter(email, password,customer_id,special_program );
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                CList.clear();
//                padapter.notifyDataSetChanged();
//                loadAdapter(email, password,customer_id,special_program );
//                mSwipeRefreshLayout.setRefreshing(false);
//            }
//        });

}

    private void loadAdapter(String email, String password, String customer_id, String special_program) {
//        check the special program ans set two api calling functions for each one
        Log.d("special_program",special_program);
//TODO: null condition checking of special program and swiperefresh layout and inserting status bar in status condition
        if(special_program!=null && !special_program.equals("") && !special_program.isEmpty()){

            apicalling2(email,password,customer_id,special_program);
        }else{

            apicalling1(email,password,customer_id);
        }
    }

    private void apicalling2(String email, String password, String customer_id, String special_program) {
        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<ResponseInActive>call = apiInterface.getInActiveItems2(email,password,customer_id,special_program);
        call.enqueue(new Callback<ResponseInActive>() {
            @Override
            public void onResponse(Call<ResponseInActive> call, Response<ResponseInActive> response) {
                if(response.isSuccessful()){
                    Log.d("Success",response.body().getMessage());
//                    Toast.makeText(getContext(), "Network Success", Toast.LENGTH_SHORT).show();
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    ResponseInActive responseInActive = response.body();
                    String message = responseInActive.getReturnType();

                    List<StoreInactiveItem>list =responseInActive.getStoreInactiveItems();
                    for(int i =0; i<list.size();i++){

                        StoreInactiveItem inactiveItem = new StoreInactiveItem();

                        String sku = list.get(i).getItemSkuNumber();
                        String item_name = list.get(i).getItemName();
                        String status = list.get(i).getItemStatus();
                        String replnish = list.get(i).getReplenish();
                        String pick = list.get(i).getPick();
                        String ord = list.get(i).getOrderedQuantity();
//                        $A2S = $replenish + $pick - $requestedQuantity - $orderedQuantity;
                        Long pick_balance = list.get(i).getPickBalance();
                        String committed = (String) list.get(i).getRequestedQuantity();
                        String updateat = list.get(i).getUpdatedAt();


                        inactiveItem.setItemSkuNumber(sku);
                        inactiveItem.setItemName(item_name);
                        inactiveItem.setItemStatus(status);
                        inactiveItem.setReplenish(replnish);
                        inactiveItem.setPick(pick);
                        inactiveItem.setOrderedQuantity(ord);
                        inactiveItem.setPickBalance(pick_balance);
                        inactiveItem.setRequestedQuantity(committed);
                        inactiveItem.setUpdatedAt(updateat);

                        CList.add(inactiveItem);
                    }

                    padapter.updateAdapter(CList);
                }
            }

            @Override
            public void onFailure(Call<ResponseInActive> call, Throwable t) {
                call.cancel();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void apicalling1(String email, String password, String customer_id) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<ResponseInActive>call = apiInterface.getInActiveItems1(email,password,customer_id);
        call.enqueue(new Callback<ResponseInActive>() {
            @Override
            public void onResponse(Call<ResponseInActive> call, Response<ResponseInActive> response) {
                if(response.isSuccessful()){
                    Log.d("Success",response.body().getMessage());
//                    Toast.makeText(getContext(), "Network Success", Toast.LENGTH_SHORT).show();
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    ResponseInActive responseInActive = response.body();
                    String message = responseInActive.getReturnType();

                    List<StoreInactiveItem>list =responseInActive.getStoreInactiveItems();
                    for(int i =0; i<list.size();i++){

                        StoreInactiveItem inactiveItem = new StoreInactiveItem();

                        String sku = list.get(i).getItemSkuNumber();
                        String item_name = list.get(i).getItemName();
                        String status = list.get(i).getItemStatus();
                        String replnish = list.get(i).getReplenish();
                        String pick = list.get(i).getPick();
                        String ord = list.get(i).getOrderedQuantity();
//                        $A2S = $replenish + $pick - $requestedQuantity - $orderedQuantity;
                        Long pick_balance = list.get(i).getPickBalance();
                        String committed = (String) list.get(i).getRequestedQuantity();
                        String updateat = list.get(i).getUpdatedAt();


                        inactiveItem.setItemSkuNumber(sku);
                        inactiveItem.setItemName(item_name);
                        inactiveItem.setItemStatus(status);
                        inactiveItem.setReplenish(replnish);
                        inactiveItem.setPick(pick);
                        inactiveItem.setOrderedQuantity(ord);
                        inactiveItem.setPickBalance(pick_balance);
                        inactiveItem.setRequestedQuantity(committed);
                        inactiveItem.setUpdatedAt(updateat);


                        CList.add(inactiveItem);

                    }

                    padapter.updateAdapter(CList);
                }
            }

            @Override
            public void onFailure(Call<ResponseInActive> call, Throwable t) {

                call.cancel();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
