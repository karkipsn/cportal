package com.example.colors2web.clientportal.Activities.Navigation.Inventories;

import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.clientportal.Adapters.AllItemsAdapter;
import com.example.colors2web.clientportal.Adapters.InActiveAdapter;
import com.example.colors2web.clientportal.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.clientportal.POJO.CommonUtilities.Country;
import com.example.colors2web.clientportal.POJO.Inventories.AllItems.CustomerItems.CustomerItem;
import com.example.colors2web.clientportal.POJO.Inventories.AllItems.CustomerItems.ResponseCustomerItems;
import com.example.colors2web.clientportal.POJO.Inventories.AllItems.Customers.Customer;
import com.example.colors2web.clientportal.POJO.Inventories.AllItems.Customers.ResponseCustomer;
import com.example.colors2web.clientportal.POJO.Inventories.AllItems.UOM.ResponseUOM;
import com.example.colors2web.clientportal.POJO.Inventories.AllItems.UOM.UOM;
import com.example.colors2web.clientportal.R;
import com.example.colors2web.clientportal.api.APIClient;
import com.example.colors2web.clientportal.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_AllItems extends Fragment {

    APIInterface apiInterface;
    RecyclerView mrecycleView;
    AllItemsAdapter padapter;
    List<CustomerItem>CList = new ArrayList<>();

    String company_name;
    TextView company;
    Button create_item;
    Spinner spinner_uom,spinner_country;
    private String uom, country;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory_allitems, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        apiInterface = APIClient.getClient().create(APIInterface.class);

//        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
//        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);


        mrecycleView = view.findViewById(R.id.recycler_view_all);
        company =view.findViewById(R.id.all_company_name);
        create_item =view.findViewById(R.id.btn_all_create);

        padapter = new AllItemsAdapter(CList,getContext());

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


        loadCustomer(email,password,customer_id);
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

        create_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View popupView = inflater.inflate(R.layout.modal_create_customer_items, null);

                WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();

                int height = display.getHeight();
                int width = display.getWidth();

                final PopupWindow popup = new PopupWindow(popupView,
                        (int) (width), WindowManager.LayoutParams.WRAP_CONTENT);
                popup.setFocusable(true);
                popup.setOutsideTouchable(true);
                popup.setAnimationStyle(android.R.style.Animation_Dialog);

                popup.showAtLocation(create_item, Gravity.CENTER, 0, 0); //Displaying popup

                final View container = (View) popup.getContentView().getParent();
                WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
                p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                p.dimAmount = 0.7f;
                wm.updateViewLayout(container, p);

                Button submit,cancle;
                final EditText pname,psku,pweight,pcost;


                spinner_uom =popupView.findViewById(R.id.modal_all_uom_spinner);
                spinner_country =popupView.findViewById(R.id.modal_all_country_spinner);


                loadUOMspinner(email, password);
                loadCountrySpinner();
                
                spinner_uom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        uom = parent.getItemAtPosition(position).toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        uom = parent.getItemAtPosition(0).toString();

                    }
                });


                spinner_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        Country sp = (Country) parent.getItemAtPosition(position);
                        country = sp.getCode();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        Country sp = (Country) parent.getItemAtPosition(0);
                        country = sp.getCode();
                    }
                });

                pname =popupView.findViewById(R.id.modal_all_name);
                psku =popupView.findViewById(R.id.modal_all_sku);
                pweight =popupView.findViewById(R.id.modal_all_weight);
                pcost =popupView.findViewById(R.id.modal_all_price);
                
//                buttons
                cancle = popupView.findViewById(R.id.modal_all_btn_submit);
                submit = popupView.findViewById(R.id.modal_all_btn_cancel);

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String qname =pname.getText().toString();
                        String qsku =psku.getText().toString();
                        String qweight =pweight.getText().toString();
                        String qcost =pcost.getText().toString();

                        if (!Validation(qname)) {
                            if (!Validation(qsku)) {
                                if (!Validation(qweight)) {
                                                if (!Validation(qcost)) {
                                                    pcost.setError("Invalid Cost");
                                                }
                                          }
                                    pweight.setError("Invalid Weight");
                                }
                                psku.setError("Invalid Sku");
                            }
                            pname.setError("Invalid Name");
                        }

//                        PackageInput input = new PackageInput(customer_id,qname,qlength,qwidth,qheight,l_id,l_id,
//                                qweight,qsku,qcost,status,package_type);
//
//                        postActivity(email,password,group_type,l_id,input);

                });

                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popup.dismiss();
                    }
                });

            }
        });

    }

    private boolean Validation(String qname) {

        if (qname != null ) {
            return true;
        }
        return false;
    }

    private void loadCountrySpinner() {

        ArrayList<Country> countryList = new ArrayList<>();

            countryList.add(new Country("Afghanistan", "AF"));
            countryList.add(new Country("Åland Islands", "AX"));
            countryList.add(new Country("Albania", "AL"));
            countryList.add(new Country("Algeria", "DZ"));
            countryList.add(new Country("American Samoa", "AS"));
            countryList.add(new Country("Andorra", "AD"));
            countryList.add(new Country("Angola", "AO"));
            countryList.add(new Country("Anguilla", "AI"));
            countryList.add(new Country("Antarctica", "AQ"));
            countryList.add(new Country("Antigua and Barbuda", "AG"));
            countryList.add(new Country("Argentina", "AR"));
            countryList.add(new Country("Armenia", "AM"));
            countryList.add(new Country("Aruba", "AW"));
            countryList.add(new Country("Australia", "AU"));
            countryList.add(new Country("Austria", "AT"));
            countryList.add(new Country("Azerbaijan", "AZ"));
            countryList.add(new Country("Bahamas", "BS"));
            countryList.add(new Country("Bahrain", "BH"));
            countryList.add(new Country("Bangladesh", "BD"));
            countryList.add(new Country("Barbados", "BB"));
            countryList.add(new Country("Belarus", "BY"));
            countryList.add(new Country("Belgium", "BE"));
            countryList.add(new Country("Belize", "BZ"));
            countryList.add(new Country("Benin", "BJ"));
            countryList.add(new Country("Bermuda", "BM"));
            countryList.add(new Country("Bhutan", "BT"));
            countryList.add(new Country("Bolivia, Plurinational State of", "BO"));
            countryList.add(new Country("Bonaire, Sint Eustatius and Saba", "BQ"));
            countryList.add(new Country("Bosnia and Herzegovina", "BA"));
            countryList.add(new Country("Botswana", "BW"));
            countryList.add(new Country("Bouvet Island", "BV"));
            countryList.add(new Country("Brazil", "BR"));
            countryList.add(new Country("British Indian Ocean Territory", "IO"));
            countryList.add(new Country("Brunei Darussalam", "BN"));
            countryList.add(new Country("Bulgaria", "BG"));
            countryList.add(new Country("Burkina Faso", "BF"));
            countryList.add(new Country("Burundi", "BI"));
            countryList.add(new Country("Cambodia", "KH"));
            countryList.add(new Country("Cameroon", "CM"));
            countryList.add(new Country("Canada", "CA"));
            countryList.add(new Country("Cape Verde", "CV"));
            countryList.add(new Country("Cayman Islands", "KY"));
            countryList.add(new Country("Central African Republic", "CF"));
            countryList.add(new Country("Chad", "TD"));
            countryList.add(new Country("Chile", "CL"));
            countryList.add(new Country("China", "CN"));
            countryList.add(new Country("Christmas Island", "CX"));
            countryList.add(new Country("Cocos (Keeling) Islands", "CC"));
            countryList.add(new Country("Colombia", "CO"));
            countryList.add(new Country("Comoros", "KM"));
            countryList.add(new Country("Congo", "CG"));
            countryList.add(new Country("Congo, The Democratic Republic of the", "CD"));
            countryList.add(new Country("Cook Islands", "CK"));
            countryList.add(new Country("Costa Rica", "CR"));
            countryList.add(new Country("Cote D\'Ivoire", "CI"));
            countryList.add(new Country("Croatia", "HR"));
            countryList.add(new Country("Cuba", "CU"));
            countryList.add(new Country("Curaçao", "CW"));
            countryList.add(new Country("Cyprus", "CY"));
            countryList.add(new Country("Czech Republic", "CZ"));
            countryList.add(new Country("Denmark", "DK"));
            countryList.add(new Country("Djibouti", "DJ"));
            countryList.add(new Country("Dominica", "DM"));
            countryList.add(new Country("Dominican Republic", "DO"));
            countryList.add(new Country("Ecuador", "EC"));
            countryList.add(new Country("Egypt", "EG"));
            countryList.add(new Country("El Salvador", "SV"));
            countryList.add(new Country("Equatorial Guinea", "GQ"));
            countryList.add(new Country("Eritrea", "ER"));
            countryList.add(new Country("Estonia", "EE"));
            countryList.add(new Country("Ethiopia", "ET"));
            countryList.add(new Country("Falkland Islands (Malvinas)", "FK"));
            countryList.add(new Country("Faroe Islands", "FO"));
            countryList.add(new Country("Fiji", "FJ"));
            countryList.add(new Country("Finland", "FI"));
            countryList.add(new Country("France", "FR"));
            countryList.add(new Country("French Guiana", "GF"));
            countryList.add(new Country("French Polynesia", "PF"));
            countryList.add(new Country("French Southern Territories", "TF"));
            countryList.add(new Country("Gabon", "GA"));
            countryList.add(new Country("Gambia", "GM"));
            countryList.add(new Country("Georgia", "GE"));
            countryList.add(new Country("Germany", "DE"));
            countryList.add(new Country("Ghana", "GH"));
            countryList.add(new Country("Gibraltar", "GI"));
            countryList.add(new Country("Greece", "GR"));
            countryList.add(new Country("Greenland", "GL"));
            countryList.add(new Country("Grenada", "GD"));
            countryList.add(new Country("Guadeloupe", "GP"));
            countryList.add(new Country("Guam", "GU"));
            countryList.add(new Country("Guatemala", "GT"));
            countryList.add(new Country("Guernsey", "GG"));
            countryList.add(new Country("Guinea", "GN"));
            countryList.add(new Country("Guinea-Bissau", "GW"));
            countryList.add(new Country("Guyana", "GY"));
            countryList.add(new Country("Haiti", "HT"));
            countryList.add(new Country("Heard Island and Mcdonald Islands", "HM"));
            countryList.add(new Country("Holy See (Vatican City State)", "VA"));
            countryList.add(new Country("Honduras", "HN"));
            countryList.add(new Country("Hong Kong", "HK"));
            countryList.add(new Country("Hungary", "HU"));
            countryList.add(new Country("Iceland", "IS"));
            countryList.add(new Country("India", "IN"));
            countryList.add(new Country("Indonesia", "ID"));
            countryList.add(new Country("Iran, Islamic Republic Of", "IR"));
            countryList.add(new Country("Iraq", "IQ"));
            countryList.add(new Country("Ireland", "IE"));
            countryList.add(new Country("Isle of Man", "IM"));
            countryList.add(new Country("Israel", "IL"));
            countryList.add(new Country("Italy", "IT"));
            countryList.add(new Country("Jamaica", "JM"));
            countryList.add(new Country("Japan", "JP"));
            countryList.add(new Country("Jersey", "JE"));
            countryList.add(new Country("Jordan", "JO"));
            countryList.add(new Country("Kazakhstan", "KZ"));
            countryList.add(new Country("Kenya", "KE"));
            countryList.add(new Country("Kiribati", "KI"));
            countryList.add(new Country("Korea, Democratic People\'s Republic of", "KP"));
            countryList.add(new Country("Korea, Republic of", "KR"));
            countryList.add(new Country("Kuwait", "KW"));
            countryList.add(new Country("Kyrgyzstan", "KG"));
            countryList.add(new Country("Lao People\'s Democratic Republic", "LA"));
            countryList.add(new Country("Latvia", "LV"));
            countryList.add(new Country("Lebanon", "LB"));
            countryList.add(new Country("Lesotho", "LS"));
            countryList.add(new Country("Liberia", "LR"));
            countryList.add(new Country("Libya", "LY"));
            countryList.add(new Country("Liechtenstein", "LI"));
            countryList.add(new Country("Lithuania", "LT"));
            countryList.add(new Country("Luxembourg", "LU"));
            countryList.add(new Country("Macao", "MO"));
            countryList.add(new Country("Macedonia, The Former Yugoslav Republic of", "MK"));
            countryList.add(new Country("Madagascar", "MG"));
            countryList.add(new Country("Malawi", "MW"));
            countryList.add(new Country("Malaysia", "MY"));
            countryList.add(new Country("Maldives", "MV"));
            countryList.add(new Country("Mali", "ML"));
            countryList.add(new Country("Malta", "MT"));
            countryList.add(new Country("Marshall Islands", "MH"));
            countryList.add(new Country("Martinique", "MQ"));
            countryList.add(new Country("Mauritania", "MR"));
            countryList.add(new Country("Mauritius", "MU"));
            countryList.add(new Country("Mayotte", "YT"));
            countryList.add(new Country("Mexico", "MX"));
            countryList.add(new Country("Micronesia, Federated States of", "FM"));
            countryList.add(new Country("Moldova, Republic of", "MD"));
            countryList.add(new Country("Monaco", "MC"));
            countryList.add(new Country("Mongolia", "MN"));
            countryList.add(new Country("Montenegro", "ME"));
            countryList.add(new Country("Montserrat", "MS"));
            countryList.add(new Country("Morocco", "MA"));
            countryList.add(new Country("Mozambique", "MZ"));
            countryList.add(new Country("Myanmar", "MM"));
            countryList.add(new Country("Namibia", "NA"));
            countryList.add(new Country("Nauru", "NR"));
            countryList.add(new Country("Nepal", "NP"));
            countryList.add(new Country("Netherlands", "NL"));
            countryList.add(new Country("New Caledonia", "NC"));
            countryList.add(new Country("New Zealand", "NZ"));
            countryList.add(new Country("Nicaragua", "NI"));
            countryList.add(new Country("Niger", "NE"));
            countryList.add(new Country("Nigeria", "NG"));
            countryList.add(new Country("Niue", "NU"));
            countryList.add(new Country("Norfolk Island", "NF"));
            countryList.add(new Country("Northern Mariana Islands", "MP"));
            countryList.add(new Country("Norway", "NO"));
            countryList.add(new Country("Oman", "OM"));
            countryList.add(new Country("Pakistan", "PK"));
            countryList.add(new Country("Palau", "PW"));
            countryList.add(new Country("Palestinian Territory, Occupied", "PS"));
            countryList.add(new Country("Panama", "PA"));
            countryList.add(new Country("Papua New Guinea", "PG"));
            countryList.add(new Country("Paraguay", "PY"));
            countryList.add(new Country("Peru", "PE"));
            countryList.add(new Country("Philippines", "PH"));
            countryList.add(new Country("Pitcairn", "PN"));
            countryList.add(new Country("Poland", "PL"));
            countryList.add(new Country("Portugal", "PT"));
            countryList.add(new Country("Puerto Rico", "PR"));
            countryList.add(new Country("Qatar", "QA"));
            countryList.add(new Country("Reunion", "RE"));
            countryList.add(new Country("Romania", "RO"));
            countryList.add(new Country("Russian Federation", "RU"));
            countryList.add(new Country("Rwanda", "RW"));
            countryList.add(new Country("Saint Barthélemy", "BL"));
            countryList.add(new Country("Saint Helena, Ascension and Tristan da Cunha", "SH"));
            countryList.add(new Country("Saint Kitts and Nevis", "KN"));
            countryList.add(new Country("Saint Lucia", "LC"));
            countryList.add(new Country("Saint Martin (French part)", "MF"));
            countryList.add(new Country("Saint Pierre and Miquelon", "PM"));
            countryList.add(new Country("Saint Vincent and the Grenadines", "VC"));
            countryList.add(new Country("Samoa", "WS"));
            countryList.add(new Country("San Marino", "SM"));
            countryList.add(new Country("Sao Tome and Principe", "ST"));
            countryList.add(new Country("Saudi Arabia", "SA"));
            countryList.add(new Country("Senegal", "SN"));
            countryList.add(new Country("Serbia", "RS"));
            countryList.add(new Country("Seychelles", "SC"));
            countryList.add(new Country("Sierra Leone", "SL"));
            countryList.add(new Country("Singapore", "SG"));
            countryList.add(new Country("Sint Maarten (Dutch part)", "SX"));
            countryList.add(new Country("Slovakia", "SK"));
            countryList.add(new Country("Slovenia", "SI"));
            countryList.add(new Country("Solomon Islands", "SB"));
            countryList.add(new Country("Somalia", "SO"));
            countryList.add(new Country("South Africa", "ZA"));
            countryList.add(new Country("South Georgia and the South Sandwich Islands", "GS"));
            countryList.add(new Country("South Sudan", "SS"));
            countryList.add(new Country("Spain", "ES"));
            countryList.add(new Country("Sri Lanka", "LK"));
            countryList.add(new Country("Sudan", "SD"));
            countryList.add(new Country("Suriname", "SR"));
            countryList.add(new Country("Svalbard and Jan Mayen", "SJ"));
            countryList.add(new Country("Swaziland", "SZ"));
            countryList.add(new Country("Sweden", "SE"));
            countryList.add(new Country("Switzerland", "CH"));
            countryList.add(new Country("Syrian Arab Republic", "SY"));
            countryList.add(new Country("Taiwan, Province of China", "TW"));
            countryList.add(new Country("Tajikistan", "TJ"));
            countryList.add(new Country("Tanzania, United Republic of", "TZ"));
            countryList.add(new Country("Thailand", "TH"));
            countryList.add(new Country("Timor-Leste", "TL"));
            countryList.add(new Country("Togo", "TG"));
            countryList.add(new Country("Tokelau", "TK"));
            countryList.add(new Country("Tonga", "TO"));
            countryList.add(new Country("Trinidad and Tobago", "TT"));
            countryList.add(new Country("Tunisia", "TN"));
            countryList.add(new Country("Turkey", "TR"));
            countryList.add(new Country("Turkmenistan", "TM"));
            countryList.add(new Country("Turks and Caicos Islands", "TC"));
            countryList.add(new Country("Tuvalu", "TV"));
            countryList.add(new Country("Uganda", "UG"));
            countryList.add(new Country("Ukraine", "UA"));
            countryList.add(new Country("United Arab Emirates", "AE"));
            countryList.add(new Country("United Kingdom", "GB"));
            countryList.add(new Country("United States", "US"));
            countryList.add(new Country("United States Minor Outlying Islands", "UM"));
            countryList.add(new Country("Uruguay", "UY"));
            countryList.add(new Country("Uzbekistan", "UZ"));
            countryList.add(new Country("Vanuatu", "VU"));
            countryList.add(new Country("Venezuela", "VE"));
            countryList.add(new Country("Vietnam", "VN"));
            countryList.add(new Country("Virgin Islands, British", "VG"));
            countryList.add(new Country("Virgin Islands, U.S.", "VI"));
            countryList.add(new Country("Wallis and Futuna", "WF"));
            countryList.add(new Country("Western Sahara", "EH"));
            countryList.add(new Country("Yemen", "YE"));
            countryList.add(new Country("Zambia", "ZM"));
            countryList.add(new Country("Zimbabwe", "ZW"));

            ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(getContext(),
                    android.R.layout.simple_spinner_item, countryList);
            adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
            spinner_country.setAdapter(adapter);

            Log.d("spinner_country", countryList.toString());
    }

    private void loadUOMspinner(String email, String password) {


        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<ResponseUOM> call = apiInterface.getUOM(email, password);

        call.enqueue(new Callback<ResponseUOM>() {
            @Override
            public void onResponse(Call<ResponseUOM> call, Response<ResponseUOM> response) {

                if (response.isSuccessful()) {

                    ResponseUOM resp1 = response.body();
                    List<UOM> order = resp1.getUOMs();

                    List<String> uomlist = new ArrayList<String>();

                    if (order != null) {

                        for (int i = 0; i < order.size(); i++) {
                            String mUnit = order.get(i).getUnit();
                            uomlist.add(mUnit);
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_spinner_item, uomlist);

                        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                        spinner_uom.setAdapter(adapter);

                        Log.d("uom", uomlist.toString());
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }


                    } else {
                        String d = response.body().getMessage();
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                } else if (response.code() == 404) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getActivity(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else if (response.code() == 500) {

                    Toast.makeText(getActivity(), "Server Broken", Toast.LENGTH_LONG).show();
                    Log.d("Error", response.errorBody().toString());

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                } else {
                    Toast.makeText(getActivity(), "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseUOM> call, Throwable t) {
                call.cancel();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadCustomer(String email, String password, String customer_id) {


        Call<ResponseCustomer> call = apiInterface.getCustomer(email,password,customer_id);
        call.enqueue(new Callback<ResponseCustomer>() {
            @Override
            public void onResponse(Call<ResponseCustomer> call, Response<ResponseCustomer> response) {
                if(response.isSuccessful()){


                    ResponseCustomer responseInActive = response.body();
                    String message = responseInActive.getReturnType();

                    Customer cus =responseInActive.getCustomer();

                     company_name = cus.getCompanyName();
                     company.setText(company_name);
                }
            }

            @Override
            public void onFailure(Call<ResponseCustomer> call, Throwable t) {

                call.cancel();
                Log.e("response-failure", t.toString());
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void loadAdapter(String email, String password, String customer_id, String special_program) {
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

        Call<ResponseCustomerItems> call = apiInterface.getCustomerItems2(email,password,customer_id,special_program);
        call.enqueue(new Callback<ResponseCustomerItems>() {
            @Override
            public void onResponse(Call<ResponseCustomerItems> call, Response<ResponseCustomerItems> response) {
                if(response.isSuccessful()){
                    Log.d("Success",response.body().getMessage());
//                    Toast.makeText(getContext(), "Network Success", Toast.LENGTH_SHORT).show();
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    ResponseCustomerItems responseInActive = response.body();
                    String message = responseInActive.getReturnType();

                    List<CustomerItem>list =responseInActive.getCustomerItems();

                    for(int i =0; i<list.size();i++){

                        CustomerItem inactiveItem = new CustomerItem();

                        String sku = list.get(i).getItemSkuNumber();
                        String item_name = list.get(i).getItemName();
                        String status = list.get(i).getItemStatus();
                        String replnish = list.get(i).getReplenish();
                        String pick = list.get(i).getPick();
                        String ord = list.get(i).getOrderedQuantity();
//                        $A2S = $replenish + $pick - $requestedQuantity - $orderedQuantity;
                        String requested = (String) list.get(i).getRequestedQuantity();


                        inactiveItem.setItemSkuNumber(sku);
                        inactiveItem.setItemName(item_name);
                        inactiveItem.setItemStatus(status);
                        inactiveItem.setReplenish(replnish);
                        inactiveItem.setPick(pick);
                        inactiveItem.setOrderedQuantity(ord);
                        inactiveItem.setRequestedQuantity(requested);

                        CList.add(inactiveItem);

                    }

                    padapter.updateAdapter(CList);
                }
            }

            @Override
            public void onFailure(Call<ResponseCustomerItems> call, Throwable t) {

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

        Call<ResponseCustomerItems> call = apiInterface.getCustomerItems1(email,password,customer_id);
        call.enqueue(new Callback<ResponseCustomerItems>() {
            @Override
            public void onResponse(Call<ResponseCustomerItems> call, Response<ResponseCustomerItems> response) {
                if(response.isSuccessful()){
                    Log.d("Success",response.body().getMessage());
//                    Toast.makeText(getContext(), "Network Success", Toast.LENGTH_SHORT).show();
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    ResponseCustomerItems responseInActive = response.body();
                    String message = responseInActive.getReturnType();

                    List<CustomerItem>list =responseInActive.getCustomerItems();

                    for(int i =0; i<list.size();i++){

                        CustomerItem inactiveItem = new CustomerItem();

                        String sku = list.get(i).getItemSkuNumber();
                        String item_name = list.get(i).getItemName();
                        String status = list.get(i).getItemStatus();
                        String replnish = list.get(i).getReplenish();
                        String pick = list.get(i).getPick();
                        String ord = list.get(i).getOrderedQuantity();
//                        $A2S = $replenish + $pick - $requestedQuantity - $orderedQuantity;
                        String requested = (String) list.get(i).getRequestedQuantity();


                        inactiveItem.setItemSkuNumber(sku);
                        inactiveItem.setItemName(item_name);
                        inactiveItem.setItemStatus(status);
                        inactiveItem.setReplenish(replnish);
                        inactiveItem.setPick(pick);
                        inactiveItem.setOrderedQuantity(ord);
                        inactiveItem.setRequestedQuantity(requested);

                        CList.add(inactiveItem);
                    }

                    padapter.updateAdapter(CList);
                }
            }

            @Override
            public void onFailure(Call<ResponseCustomerItems> call, Throwable t) {

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

