package com.example.colors2web.clientportal.Activities.Navigation;

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
import android.widget.Toast;

import com.example.colors2web.clientportal.Adapters.CityBinsAdapter;
import com.example.colors2web.clientportal.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.clientportal.POJO.Offices.CityBin;
import com.example.colors2web.clientportal.POJO.Offices.ResponseOffice;
import com.example.colors2web.clientportal.R;
import com.example.colors2web.clientportal.api.APIClient;
import com.example.colors2web.clientportal.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Offices extends Fragment {
    APIInterface apiInterface;
    RecyclerView mrecycleView;
    CityBinsAdapter padapter;
    List<CityBin> CList = new ArrayList<>();
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


        padapter = new CityBinsAdapter(CList, getActivity());

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

        loadAdapter(email, password, customer_id);
    }

    private void loadAdapter(String email, String password, String customer_id) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<ResponseOffice> call = apiInterface.getCityBins(email, password, customer_id);
        call.enqueue(new Callback<ResponseOffice>() {
            @Override
            public void onResponse(Call<ResponseOffice> call, Response<ResponseOffice> response) {

                if (response.isSuccessful()) {
                    Log.d("Success", response.body().getMessage());
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    ResponseOffice responseInActive = response.body();
                    String message = responseInActive.getReturnType();

                    List<CityBin> list = responseInActive.getCityBins();

                    if (list.size() > 0) {
                        for (int i = 0; i < list.size(); i++) {

                            CityBin inactiveItem = new CityBin();


                            String bin = list.get(i).getBin();
                            Long id = list.get(i).getId();
                            String c_date = (String) list.get(i).getCreatedAt();

                            inactiveItem.setBin(bin);
                            inactiveItem.setId(id);
                            inactiveItem.setCreatedAt(c_date);

                            CList.add(inactiveItem);
                        }

                        padapter.updateAdapter(CList);
                    }else {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Log.e("response-failure", "fail");
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
                Toast.makeText(getContext(), "Server Broken", Toast.LENGTH_LONG).show();
                Log.d("Error", response.errorBody().toString());

            } else {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                Toast.makeText(getContext(), "Operation Failed", Toast.LENGTH_SHORT).show();
            }
            }

            @Override
            public void onFailure(Call<ResponseOffice> call, Throwable t) {
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
