package com.example.colors2web.clientportal.Activities.Navigation.Inventory_Deleveries;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.clientportal.Activities.View_dr_historyActivity;
import com.example.colors2web.clientportal.POJO.InventoriesDeleveries.Deleveries.ResponseDelivery;
import com.example.colors2web.clientportal.POJO.InventoriesDeleveries.DeliveryHistory.DrShipment;
import com.example.colors2web.clientportal.POJO.InventoriesDeleveries.DeliveryHistory.ResponseDrHistory;
import com.example.colors2web.clientportal.POJO.InventoriesDeleveries.PostItem;
import com.example.colors2web.clientportal.R;
import com.example.colors2web.clientportal.api.APIClient;
import com.example.colors2web.clientportal.api.APIInterface;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Deleveries_BY_Item extends Fragment {

    APIInterface apiInterface;
    Context mContext;

    List<DrShipment> DrList = new ArrayList<>();

    TextView msku, mform, mto;
    Button btn_submit;

    Integer mYear, mMonth, mDay;
    String mform1, mto1;
    ImageView img;
    Long cus_id;
    SwipeRefreshLayout mSwipeRefreshLayout;

    public Fragment_Deleveries_BY_Item() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inf = inflater.inflate(R.layout.fragment_item_dr_history, container, false);
        return inf;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        msku = getActivity().findViewById(R.id.item_dr_sku);
        msku.setVisibility(View.VISIBLE);

        mform = getActivity().findViewById(R.id.item_dr_from);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);


        mto = getActivity().findViewById(R.id.item_dr_to);

        btn_submit = getActivity().findViewById(R.id.btn_item_dr_submit);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");
        final String customer_id = preferences.getString("customer_id", "");
        final String special_program = preferences.getString("special_program", "");


        mform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        R.style.AppTheme_Light_DatePicker, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mform.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }

                }, mYear, mMonth, mDay);

                DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                mform1 = format.format(c.getTime());
                datePickerDialog.show();
            }
        });

        mto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        R.style.AppTheme_Light_DatePicker, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mto.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }

                }, mYear, mMonth, mDay);

                DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                mto1 = format.format(c.getTime());
                datePickerDialog.show();
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                msku.setText(null);
                mform.setText(null);
                mto.setText(null);

                Toast.makeText(getContext(), "Refreshed", Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item_sku = msku.getText().toString();
                String from = mform.getText().toString();
                String to = mto.getText().toString();
                call_item_dr_history(email, password, item_sku, from, to, customer_id, special_program);
            }
        });

    }

    private void call_item_dr_history(String email, String password, String item_sku, String from,
                                      String to, String customer_id, String special_program) {


        PostItem post = new PostItem(item_sku, from, to, customer_id, special_program);

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<ResponseDrHistory> call = apiInterface.postItemDrHistory(email, password, post);
        call.enqueue(new Callback<ResponseDrHistory>() {
                         @Override
                         public void onResponse(Call<ResponseDrHistory> call, Response<ResponseDrHistory> response) {
                             if (response.isSuccessful()) {
                                 ResponseDrHistory res = response.body();


                                 List<DrShipment> shipments = res.getDrShipments();

                                 if (shipments != null) {

                                     for (int i = 0; i < shipments.size(); i++) {

                                         DrShipment drShipment = new DrShipment();

                                         String sku = shipments.get(i).getItemSkuNumber();
                                         Long id = shipments.get(i).getId();
                                         String item_name = shipments.get(i).getItemName();
                                         Long dr_no = shipments.get(i).getDrNumber();
                                         Long pallets = shipments.get(i).getSaidPalletCount();
                                         Long palleta = shipments.get(i).getActualPalletCount();
                                         Long boxs = shipments.get(i).getSaidBoxCount();
                                         Long boxa = shipments.get(i).getActualBoxCount();
                                         Long items = shipments.get(i).getSaidItemCount();
                                         Long itema = shipments.get(i).getActualItemCount();
                                         String delivery = shipments.get(i).getEstDeliveryDate();

                                         drShipment.setItemSkuNumber(sku);
                                         drShipment.setId(id);
                                         drShipment.setItemName(item_name);
                                         drShipment.setDrNumber(dr_no);
                                         drShipment.setSaidPalletCount(pallets);
                                         drShipment.setActualPalletCount(palleta);
                                         drShipment.setSaidBoxCount(boxs);
                                         drShipment.setActualBoxCount(boxa);
                                         drShipment.setActualItemCount(itema);
                                         drShipment.setSaidItemCount(items);
                                         drShipment.setEstDeliveryDate(delivery);

                                         DrList.add(drShipment);

                                     }
                                     Intent intent = new Intent(getContext(), View_dr_historyActivity.class);
                                     intent.putExtra("DrList", (Serializable) DrList);
                                     startActivity(intent);

                                     Log.d("DrList", DrList.toString());
                                     if (progressDialog.isShowing()) {
                                         progressDialog.dismiss();
                                     }
                                 } else {
                                     Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                                     if (progressDialog.isShowing()) {
                                         progressDialog.dismiss();
                                     }
                                 }

                             } else if (response.code() == 401) {
                                 if (progressDialog.isShowing()) {
                                     progressDialog.dismiss();
                                 }
                                 Toast.makeText(getContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                                 Log.d("Error", response.errorBody().toString());

                             } else if (response.code() == 404) {

                                 if (progressDialog.isShowing()) {
                                     progressDialog.dismiss();
                                 }

                                 Toast.makeText(getContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                                 Log.d("Error", response.errorBody().toString());
                             } else if (response.code() == 500) {

                                 if (progressDialog.isShowing()) {
                                     progressDialog.dismiss();
                                 }

                                 Toast.makeText(getContext(), "Internal Server Error", Toast.LENGTH_SHORT).show();
                                 Log.d("Error", response.errorBody().toString());
                             } else {
                                 if (progressDialog.isShowing()) {
                                     progressDialog.dismiss();
                                 }
                                 Toast.makeText(getContext(), "Operation Failed", Toast.LENGTH_SHORT).show();

                             }
                         }

                         @Override
                         public void onFailure(Call<ResponseDrHistory> call, Throwable t) {

                             call.cancel();
                             Log.e("response-failure", t.toString());
                             Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
                             if (progressDialog.isShowing()) {
                                 progressDialog.dismiss();
                             }

                         }
                     }

        );

    }
}

