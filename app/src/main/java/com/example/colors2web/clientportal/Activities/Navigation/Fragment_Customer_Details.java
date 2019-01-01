package com.example.colors2web.clientportal.Activities.Navigation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.clientportal.POJO.CommonUtilities.StatesPOJO;
import com.example.colors2web.clientportal.POJO.CustomerDetails.ResponseCustomers;
import com.example.colors2web.clientportal.POJO.Inventories.AllItems.Customers.Customer;
import com.example.colors2web.clientportal.POJO.Inventories.AllItems.Customers.PutCustomer;
import com.example.colors2web.clientportal.POJO.Inventories.AllItems.Customers.ResponseCustomer;
import com.example.colors2web.clientportal.R;
import com.example.colors2web.clientportal.api.APIClient;
import com.example.colors2web.clientportal.api.APIInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Customer_Details extends Fragment {

    TextView name, phone1, phone2, emails, cname, add1, add2, add3, fax, ofc_url, phone, edit;
    APIInterface apiInterface;
    LinearLayout lfax, ladd2, lofc_url, lphone2;
    String adds2, adds1, rcity, rstate, rzip, rcountry, rphone, scurl, s_state, cpname;
    Spinner us_state;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_details, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = view.findViewById(R.id.frag_cus_name);
        phone1 = view.findViewById(R.id.frag_cus_phone1);
        phone2 = view.findViewById(R.id.frag_cus_phone2);
        emails = view.findViewById(R.id.frag_cus_email);
        cname = view.findViewById(R.id.frag_cus_companyname);
        add1 = view.findViewById(R.id.afrag_cus_add1);
        add2 = view.findViewById(R.id.afrag_cus_add2);
        add3 = view.findViewById(R.id.afrag_cus_add3);
        phone = view.findViewById(R.id.frag_cus_phone);
        fax = view.findViewById(R.id.frag_cus_fax);
        ofc_url = view.findViewById(R.id.frag_cus_comapny_url);
        edit = view.findViewById(R.id.frag_cus_btn_edit);

        lfax = view.findViewById(R.id.linear_del_fax);
        ladd2 = view.findViewById(R.id.linear_del_add2);
        lofc_url = view.findViewById(R.id.linear_del_company_url);
        lphone2 = view.findViewById(R.id.linear_del_phone2);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");
        final String customer_id = preferences.getString("customer_id", "");
        final String group_type = preferences.getString("group_type", "");

        call_customer_api(email, password, customer_id, group_type);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                call_edit_modal(cpname, adds2, adds1, rcity, rstate, rzip, rcountry, rphone, scurl);
            }
        });

    }

    private void call_edit_modal(String cpname, String adds2, String adds1, String rcity, String rstate, String rzip,
                                 String rcountry, String rphone, String scurl) {

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.modal_edit_customers, null);

        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        int height = display.getHeight();
        int width = display.getWidth();

        final PopupWindow popup = new PopupWindow(popupView,
                (int) (width), WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setFocusable(true);
        popup.setOutsideTouchable(true);
        popup.setAnimationStyle(android.R.style.Animation_Dialog);

        popup.showAtLocation(edit, Gravity.CENTER, 0, 0); //Displaying popup

        final View container = (View) popup.getContentView().getParent();
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.7f;
        wm.updateViewLayout(container, p);

        Button submit, cancle;
        final EditText mcname, mcity, mzip, mcountry, madd1, madd2, murl, mphone;

        mcname = popupView.findViewById(R.id.cus_modal_cname);
        mcname.setText(cpname);
        mcity = popupView.findViewById(R.id.cus_modal_city);
        mcity.setText(rcity);
        mzip = popupView.findViewById(R.id.ccus_modal_zip);
        mzip.setText(rzip);
        mcountry = popupView.findViewById(R.id.cus_modal_country);
        mcountry.setText(rcountry);
        madd1 = popupView.findViewById(R.id.cus_modal_add1);
        madd1.setText(adds1);
        madd2 = popupView.findViewById(R.id.cus_modal_add2);
        madd2.setText(adds2);
        murl = popupView.findViewById(R.id.cus_modal_url);
        murl.setText(scurl);
        mphone = popupView.findViewById(R.id.cus_modal_phone);
        mphone.setText(rphone);

        us_state = popupView.findViewById(R.id.cus_modal_state_spinner);
        String comapre =rstate;

            load_us_stateSPinner();



        submit = popupView.findViewById(R.id.cus_modal_submit);
        cancle = popupView.findViewById(R.id.cus_modal_cancel);
        StatesPOJO pojo = new StatesPOJO();

        us_state.setSelection(getIndex(us_state, pojo,comapre));

        us_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                StatesPOJO sp = (StatesPOJO) parent.getItemAtPosition(position);
                s_state = sp.getCode();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                popup.dismiss();

                String mcname1 = mcname.getText().toString();
                String mcity1 = mcity.getText().toString();
                String mzip1 = mzip.getText().toString();
                String mcountry1 = mcountry.getText().toString();
                String madd11 = madd1.getText().toString();
                String madd21 = madd2.getText().toString();
                String murl1 = murl.getText().toString();
                String mphone1 = mphone.getText().toString();

                PutCustomer putc = new PutCustomer(mcname1,madd11,madd21,mcity1,s_state,mzip1,mcountry1,murl1,mphone1);
                edit_customer(putc,popup);

            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });


    }

    private int getIndex(Spinner us_state, StatesPOJO comapre, String s) {
//        TODO:: Display the comapred position of the spinner in state spinner

            for (int i=0;i<us_state.getCount();i++){
                Log.d("spinner_code",us_state.getItemAtPosition(i).toString());

                StatesPOJO pojo1 = comapre;
                if (us_state.getItemAtPosition(i).toString().equalsIgnoreCase(comapre.getCode())){

                    Log.d("spinner_code",us_state.getItemAtPosition(i).toString());
                    return i;
                }
            }

            return 0;
    }


    private void edit_customer(PutCustomer putc, final PopupWindow popup) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");
        final String customer_id = preferences.getString("customer_id", "");
        final String group_type = preferences.getString("group_type", "");

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<ResponseCustomer> call = apiInterface.editCustomerDetails(email,password,customer_id,putc);
        call.enqueue(new Callback<ResponseCustomer>() {
            @Override
            public void onResponse(Call<ResponseCustomer> call, Response<ResponseCustomer> response) {
                if (response.isSuccessful()) {


                    ResponseCustomer responseInActive = response.body();
                    String message = responseInActive.getReturnType();

                    if(message.equals("success")){
                        popup.dismiss();
                        Toast.makeText(getContext(), "Update Success", Toast.LENGTH_SHORT).show();
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }else{
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(getContext(), "Unsuccessful Attempt. Try Again", Toast.LENGTH_SHORT).show();
                    }
                    }

            }

            @Override
            public void onFailure(Call<ResponseCustomer> call, Throwable t) {

                call.cancel();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void load_us_stateSPinner() {

        ArrayList<StatesPOJO> stateList = new ArrayList<>();

        stateList.add(new StatesPOJO("Alabama", "AL"));
        stateList.add(new StatesPOJO("Alaska", "AK"));
        stateList.add(new StatesPOJO("Arizona", "AZ"));
        stateList.add(new StatesPOJO("Arkansas", "AR"));
        stateList.add(new StatesPOJO("California", "CA"));
        stateList.add(new StatesPOJO("Colorado", "CO"));
        stateList.add(new StatesPOJO("Connecticut", "CT"));
        stateList.add(new StatesPOJO("Delaware", "DE"));
        stateList.add(new StatesPOJO("District Of Columbia", "DC"));
        stateList.add(new StatesPOJO("Florida", "FL"));
        stateList.add(new StatesPOJO("Georgia", "GA"));
        stateList.add(new StatesPOJO("Hawaii", "HI"));
        stateList.add(new StatesPOJO("Idaho", "ID"));
        stateList.add(new StatesPOJO("Illinois", "IL"));
        stateList.add(new StatesPOJO("Indiana", "IN"));
        stateList.add(new StatesPOJO("Iowa", "IA"));
        stateList.add(new StatesPOJO("Kansas", "KS"));
        stateList.add(new StatesPOJO("Kentucky", "KY"));
        stateList.add(new StatesPOJO("Louisiana", "LA"));
        stateList.add(new StatesPOJO("Maine", "ME"));
        stateList.add(new StatesPOJO("Maryland", "MD"));
        stateList.add(new StatesPOJO("Massachusetts", "MA"));
        stateList.add(new StatesPOJO("Michigan", "MI"));
        stateList.add(new StatesPOJO("Minnesota", "MN"));
        stateList.add(new StatesPOJO("Mississippi", "MS"));
        stateList.add(new StatesPOJO("Missouri", "MO"));
        stateList.add(new StatesPOJO("Montana", "MT"));
        stateList.add(new StatesPOJO("Nebraska", "NE"));
        stateList.add(new StatesPOJO("Nevada", "NV"));
        stateList.add(new StatesPOJO("New Hampshire", "NH"));
        stateList.add(new StatesPOJO("New Jersey", "NJ"));
        stateList.add(new StatesPOJO("New Mexico", "NM"));
        stateList.add(new StatesPOJO("New York", "NY"));
        stateList.add(new StatesPOJO("North Carolina", "NC"));
        stateList.add(new StatesPOJO("North Dakota", "ND"));
        stateList.add(new StatesPOJO("Ohio", "OH"));
        stateList.add(new StatesPOJO("Oklahoma", "OK"));
        stateList.add(new StatesPOJO("Oregon", "OR"));
        stateList.add(new StatesPOJO("Pennsylvania", "PA"));
        stateList.add(new StatesPOJO("Puerto Rico", "PR"));
        stateList.add(new StatesPOJO("Rhode Island", "RI"));
        stateList.add(new StatesPOJO("South Carolina", "SC"));
        stateList.add(new StatesPOJO("South Dakota", "SD"));
        stateList.add(new StatesPOJO("Tennessee", "TN"));
        stateList.add(new StatesPOJO("Texas", "TX"));
        stateList.add(new StatesPOJO("Utah", "UT"));
        stateList.add(new StatesPOJO("Vermont", "VT"));
        stateList.add(new StatesPOJO("Virginia", "VA"));
        stateList.add(new StatesPOJO("Washington", "WA"));
        stateList.add(new StatesPOJO("West Virginia", "WV"));
        stateList.add(new StatesPOJO("Wisconsin", "WI"));
        stateList.add(new StatesPOJO("Wyoming", "WY"));

        ArrayAdapter<StatesPOJO> uadapter = new ArrayAdapter<StatesPOJO>(getActivity(),
                android.R.layout.simple_spinner_item, stateList);
        uadapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        us_state.setAdapter(uadapter);

//         String compareValue = "some value";
//        mSpinner.setAdapter(adapter);
//        if (compareValue != null) {
//            int spinnerPosition = adapter.getPosition(compareValue);
//            mSpinner.setSelection(spinnerPosition);
//        }

    }


    private void call_customer_api(String email, String password, final String customer_id, final String group_type) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        Call<ResponseCustomer> call = apiInterface.getCustomer(email, password, customer_id);
        call.enqueue(new Callback<ResponseCustomer>() {
            @Override
            public void onResponse(Call<ResponseCustomer> call, Response<ResponseCustomer> response) {
                if (response.isSuccessful()) {


                    ResponseCustomer responseInActive = response.body();
                    String message = responseInActive.getReturnType();

                    Customer cus = responseInActive.getCustomer();

                    if (cus != null) {

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        if (group_type.equals("Super Admin")) {
                            edit.setVisibility(View.VISIBLE);
                        }

                        String name1 = cus.getContactFname() + " " + cus.getContactLname();

                        String sphone = cus.getContactPhoneSecondary();
                        String fax1 = (String) cus.getContactFax();

                        cpname = cus.getCompanyName();

                        adds2 = cus.getReturnCompanyAddress2();
                        adds1 = cus.getReturnCompanyAddress1();
                        rcity = cus.getReturnCompanyCity();
                        rstate = cus.getReturnCompanyState();
                        rzip = cus.getReturnCompanyZip();
                        rcountry = cus.getReturnCompanyCountry();
                        rphone = cus.getReturnCompanyPhone();
                        scurl = (String) cus.getReturnCompanyUrl();

                        name.setText(name1);
                        phone1.setText(cus.getContactPhonePrimary());
                        emails.setText(cus.getContactEmail());

                        if (scurl != null && !scurl.equals("")) {
                            lofc_url.setVisibility(View.VISIBLE);
                            ofc_url.setText(scurl);
                        }

                        if (sphone != null && !sphone.equals("")) {
                            lphone2.setVisibility(View.VISIBLE);
                            phone2.setText(sphone);

                        }

                        if (adds2 != null && !adds2.equals("")) {
                            ladd2.setVisibility(View.VISIBLE);
                            add2.setText(adds2);

                        }

                        if (fax1 != null && !fax1.equals("")) {
                            lfax.setVisibility(View.VISIBLE);
                            fax.setText(fax1);
                        }


                        add1.setText(adds1);

                        cname.setText(cus.getCompanyName());
                        phone.setText(rphone);

                        String adds3 = rcity + " " + rstate + " " + rzip + " " + rcountry;

                        add3.setText(adds3);
                    } else {
//                      nodata display

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseCustomer> call, Throwable t) {

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
