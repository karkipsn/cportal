package com.example.colors2web.clientportal.Activities.Navigation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.colors2web.clientportal.Adapters.UserAdapter;
import com.example.colors2web.clientportal.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.clientportal.R;
import com.example.colors2web.clientportal.api.APIClient;
import com.example.colors2web.clientportal.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Users extends Fragment {
//    UserAdapter padapter;
//    RecyclerView mrecycleView;
//    List<User> UList = new ArrayList<>();
//    APIInterface apiInterface;
//    Context mContext;
//
//
//    public Fragment_Users() {
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
////        View view = inflater.inflate(R.layout.frag_cus_shipping_method, container, false);
////        return view;
//        return inflater.inflate(R.layout.fragment_users, container, false);
//
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        padapter = new UserAdapter(UList, getActivity());
//        apiInterface = APIClient.getClient().create(APIInterface.class);
//
//        mrecycleView = view.findViewById(R.id.recycler_view_shipping);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        mrecycleView.setHasFixedSize(true);
//        mrecycleView.setLayoutManager(mLayoutManager);
//
//        mrecycleView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 16));
//        mrecycleView.setItemAnimator(new DefaultItemAnimator());
//        mrecycleView.setAdapter(padapter);
//        loadAdapter();
//    }
//
//    private void loadAdapter() {
//
//        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
//                R.style.AppTheme_Dark_Dialog);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();
//
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
//        final String email = preferences.getString("email", "");
//        final String password = preferences.getString("password", "");
//        final String customer_id = preferences.getString("customer_id", "");
//
//
//        Call<UsersResponse> call = apiInterface.getUser(email, password,customer_id);
//
//        call.enqueue(new Callback<UsersResponse>() {
//            @Override
//            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
//                if(response.isSuccessful()){
//                    Log.d("Success",response.body().getMessage());
//                    Toast.makeText(getContext(), "Network Success", Toast.LENGTH_SHORT).show();
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UsersResponse> call, Throwable t) {
//                call.cancel();
//                if (progressDialog.isShowing()) {
//                    progressDialog.dismiss();
//                }
//                Log.e("response-failure", t.toString());
//                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
////        call.enqueue(new Callback<UsersResponse>() {
////            @Override
////            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
////
////                if (response.isSuccessful()) {
////
////                    UsersResponse resp1 = response.body();
////                    Log.d("Usersiii",resp1.toString());
////
////                    List<User> order = resp1.getUsers();
////
////                    if (order != null) {
////
////                        for (int i = 0; i < order.size(); i++) {
////
////                            User uom = new User();
//////                            Always needs to create fresh instance//if POJO instance is taken outside
////
////                            Long id = order.get(i).getUser().getId();
////                            String fname = order.get(i).getUser().getFirstName();
////                            String lname = order.get(i).getUser().getLastName();
////                            String memail = order.get(i).getUser().getEmail();
////                            String mcreated = order.get(i).getUser().getCreatedAt();
//////                            List<String> ug = order.get(i).getUserGroup();
////
////                            uom.setId(id);
////                            uom.setFirstName(fname);
////                            uom.setLastName(lname);
////                            uom.setEmail(memail);
////                            uom.setCreatedAt(mcreated);
//////                            uom.setUserGroup(ug);
////                            UList.add(uom);
////                        }
////
////
////                        if (progressDialog.isShowing()) {
////                            progressDialog.dismiss();
////                        }
////                        padapter.updateAdapter(UList);
////
////                    } else {
////                        String d = response.body().getMessage();
////                        if (progressDialog.isShowing()) {
////                            progressDialog.dismiss();
////                        }
////                    }
////                } else if (response.code() == 401) {
////                    if (progressDialog.isShowing()) {
////                        progressDialog.dismiss();
////                    }
////
////                    Toast.makeText(getContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
////                    Log.d("Error", response.errorBody().toString());
////
////
////                } else if (response.code() == 404) {
////
////                    if (progressDialog.isShowing()) {
////                        progressDialog.dismiss();
////                    }
////
////                    Toast.makeText(getContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
////                    Log.d("Error", response.errorBody().toString());
////                } else if (response.code() == 500) {
////
////                    Toast.makeText(getContext(), "Server Broken", Toast.LENGTH_LONG).show();
////                    Log.d("Error", response.errorBody().toString());
////
////                    if (progressDialog.isShowing()) {
////                        progressDialog.dismiss();
////                    }
////
////                } else {
////                    if (progressDialog.isShowing()) {
////                        progressDialog.dismiss();
////                    }
////                    Toast.makeText(getContext(), "Operation Failed", Toast.LENGTH_SHORT).show();
////
////                }
////            }
////
////            @Override
////            public void onFailure(Call<UsersResponse> call, Throwable t) {
////
////                call.cancel();
////                if (progressDialog.isShowing()) {
////                    progressDialog.dismiss();
////                }
////                Log.e("response-failure", t.toString());
////                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
////            }
////        });
//
//    }
//
//
//    public void refresh() {
//
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.detach(this).attach(this).commit();
//    }
}
