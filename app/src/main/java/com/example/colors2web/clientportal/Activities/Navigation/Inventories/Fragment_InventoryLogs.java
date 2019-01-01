package com.example.colors2web.clientportal.Activities.Navigation.Inventories;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.clientportal.POJO.CommonUtilities.SpinnerPojo;
import com.example.colors2web.clientportal.POJO.CommonUtilities.StatesPOJO;
import com.example.colors2web.clientportal.POJO.InventoriesDeleveries.DeliveryHistory.DrShipment;
import com.example.colors2web.clientportal.POJO.InventoriesDeleveries.PostItem;
import com.example.colors2web.clientportal.R;
import com.example.colors2web.clientportal.api.APIClient;
import com.example.colors2web.clientportal.api.APIInterface;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Fragment_InventoryLogs extends Fragment {

    APIInterface apiInterface;
    Context mContext;

    List<DrShipment> DrList = new ArrayList<>();

    TextView msku, mform, mto;
    Button btn_submit;
    Spinner event_type;

    Integer mYear, mMonth, mDay;
    String mform1, mto1,mevent;

    SwipeRefreshLayout mSwipeRefreshLayout;

    public Fragment_InventoryLogs() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inf = inflater.inflate(R.layout.fragment_inventory_logs, container, false);
        return inf;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        msku = getActivity().findViewById(R.id.item_logs_sku);
        msku.setVisibility(View.VISIBLE);

        mform = getActivity().findViewById(R.id.item_logs_from);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        event_type = getActivity().findViewById(R.id.item_logs_event_spinner);
        mto = getActivity().findViewById(R.id.item_logs_to);

        btn_submit = getActivity().findViewById(R.id.btn_item_logs_submit);

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

        spinner_event_type();

        event_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                StatesPOJO sp = (StatesPOJO) parent.getItemAtPosition(position);
                mevent = sp.getCode();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item_sku = msku.getText().toString();
                String from = mform.getText().toString();
                String to = mto.getText().toString();

                PostItem postItem = new PostItem(item_sku,mevent,from,to,customer_id,special_program);

                call_item_log_history(email,password,postItem);
            }
        });

    }

    private void call_item_log_history(String email, String password, PostItem postItem) {
//        call api fro here
    }


    private void spinner_event_type() {


        ArrayList<StatesPOJO> eventlist = new ArrayList<>();
        eventlist.add(new StatesPOJO("ALL","all"));
        eventlist.add(new StatesPOJO("Transfer To Pick","Transfer To Pick"));
        eventlist.add(new StatesPOJO("Relocate Inventory","Relocate Inventory"));
        eventlist.add(new StatesPOJO("Update Inventory","Update Inventory"));
        eventlist.add(new StatesPOJO("Pick Inventory Update","Pick Inventory Update"));
        eventlist.add(new StatesPOJO("Pick Location Update","Pick Location Update"));
        eventlist.add(new StatesPOJO("DR To Replenish","DR To Replenish"));
        eventlist.add(new StatesPOJO("DR To Pick","DR To Pick"));
        eventlist.add(new StatesPOJO("DR Received","DR Received"));
        eventlist.add(new StatesPOJO("Packed","Packed"));

    }
}
