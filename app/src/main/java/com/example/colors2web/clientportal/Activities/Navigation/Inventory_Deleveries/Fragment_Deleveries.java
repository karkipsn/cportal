package com.example.colors2web.clientportal.Activities.Navigation.Inventory_Deleveries;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.clientportal.Adapters.DeliveryAdapter;
import com.example.colors2web.clientportal.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.clientportal.POJO.InventoriesDeleveries.Deleveries.DrShipment;
import com.example.colors2web.clientportal.POJO.InventoriesDeleveries.Deleveries.ResponseDelivery;
import com.example.colors2web.clientportal.R;
import com.example.colors2web.clientportal.api.APIClient;
import com.example.colors2web.clientportal.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Deleveries extends Fragment {
    APIInterface apiInterface;
    RecyclerView mrecycleView;
    DeliveryAdapter padapter;
    List<DrShipment> CList = new ArrayList<>();
    Button create;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delivery, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiInterface = APIClient.getClient().create(APIInterface.class);

//        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
//        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);


        mrecycleView = view.findViewById(R.id.recycler_view_delivery);
        create = view.findViewById(R.id.btn_delivery_create);


        padapter = new DeliveryAdapter(CList,getContext());

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
        
        loadAdapter(email,password,customer_id);
}

    private void loadAdapter(String email, String password, String customer_id) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<ResponseDelivery> call = apiInterface.getDeleveries(email,password,customer_id);
        call.enqueue(new Callback<ResponseDelivery>() {
            @Override
            public void onResponse(Call<ResponseDelivery> call, Response<ResponseDelivery> response) {

                if(response.isSuccessful()){
                    Log.d("Success",response.body().getMessage());
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    ResponseDelivery responseInActive = response.body();
                    String message = responseInActive.getReturnType();

                    List<DrShipment>list =responseInActive.getDrShipments();

                    if(list.size()>0){
                    for(int i =0; i<list.size();i++){

                        DrShipment inactiveItem = new DrShipment();

                        Long delivery = list.get(i).getDrNumber();
                        String des = list.get(i).getDrDescription();
                        String status = list.get(i).getServiceStatus();
                        Long box = list.get(i).getSaidBoxCount();
                        Long pallet = list.get(i).getSaidPalletCount();
                        String es_date = list.get(i).getEstDeliveryDate();
                        String c_date = (String) list.get(i).getCreatedAt();


                        inactiveItem.setDrNumber(delivery);
                        inactiveItem.setDrDescription(des);
                        inactiveItem.setServiceStatus(status);
                        inactiveItem.setSaidBoxCount(box);
                        inactiveItem.setSaidPalletCount(pallet);
                        inactiveItem.setEstDeliveryDate(es_date);
                        inactiveItem.setCreatedAt(c_date);


                        CList.add(inactiveItem);

                    }

                    padapter.updateAdapter(CList);
                }}else{

                }
            }

            @Override
            public void onFailure(Call<ResponseDelivery> call, Throwable t) {
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
