package com.example.colors2web.clientportal.Activities.BarCodeActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.colors2web.clientportal.R;
import com.example.colors2web.clientportal.SearchFragment;
import com.example.colors2web.clientportal.api.APIClient;
import com.example.colors2web.clientportal.api.APIInterface;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarcodeMain extends AppCompatActivity {

    private static final String TAG_FRAGMENT = "SEARCH_FRAGMENT";
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // making toolbar transparent
        transparentToolbar();

        setContentView(R.layout.barcode_main_activity);

        Toolbar toolbar = findViewById(R.id.toolbar_barcode);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BarcodeMain.super.onBackPressed();
                overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        new IntentIntegrator(BarcodeMain.this).setCaptureActivity(ScannerActivity.class).initiateScan();

//        findViewById(R.id.btn_scan).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////            its for mobileVision_Library
////            startActivity(new Intent(BarcodeMain.this, Barcode_ScanActivity.class));
//
//                new IntentIntegrator(BarcodeMain.this).setCaptureActivity(ScannerActivity.class).initiateScan();
//            }
//        });
    }

    private void transparentToolbar() {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //We will get scan results here
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {

            if (result.getContents() == null) {
                Toast.makeText(this, "Scan Cancelled", Toast.LENGTH_LONG).show();

            } else {
                //show dialogue with result// This is used to copy the contents from the clip
//                showResultDialogue(result.getContents());
                String OPath = result.getContents();
                Log.d("Barcode:", result.getContents());
                Toast.makeText(this, "Scanned result is :" + result.getContents(), Toast.LENGTH_LONG).show();

                final Intent i = getIntent();

                if (i != null) {

                    final String categories = i.getExtras().getString("categories");

                    if (categories != null) {

                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                        final String email = preferences.getString("email", "");
                        final String password = preferences.getString("password", "");

                        switch (categories) {

                            case "Order":
//                                call_order2(email, password, OPath);
                                break;

                            case "Product":
//                                call_item(email, password, OPath);
                                break;

                            case "Tracking Number":
//                                call_tracking_number(email, password, OPath);
                                break;

                            case "Box/Master Box":
//                                call_box(email,password,OPath);
                                break;
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Error in Process", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu2, menu);
        ImageView img;

        img = (ImageView) menu.findItem(R.id.image).getActionView();
        img.setImageResource(android.R.drawable.ic_menu_search);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSupportFragmentManager().beginTransaction().
                        replace(R.id.frame_toolbar, new SearchFragment()).
                        addToBackStack(TAG_FRAGMENT).commit();
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.image:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
//       moveTaskToBack(true);
        overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
        super.onBackPressed();
    }


//    private void call_box(String email, String password, String oPath) {
////        TODO: BoxOrderSearch Implementation is left
//        Toast.makeText(getApplicationContext(),"Box not found",Toast.LENGTH_LONG).show();
//
////        Intent intent3 = new Intent(getApplicationContext(), OrderSearch2Activity.class);
////        intent3.putExtra("OPath", OPath);
////        startActivity(intent3);
//    }
//
//    private void call_tracking_number(String email, String password, String oPath) {
//
//        final ProgressDialog progressDialog = new ProgressDialog(BarcodeMain.this, R.style.AppTheme_Dark_Dialog);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();
//        final String Path = oPath;
//
//        Call<OrdertrackResponse> call = apiInterface.getOrderByTrack(email, password, oPath);
//
//        call.enqueue(new Callback<OrdertrackResponse>() {
//            @Override
//            public void onResponse(Call<OrdertrackResponse> call, Response<OrdertrackResponse> response) {
//
//                if (response.isSuccessful()) {
//
//                    OrdertrackResponse resp1 = response.body();
//                    List<OrderDetail> List = resp1.getOrderDetails();
//
//                    if (List != null) {
//
//                        Intent intent2 = new Intent(getApplicationContext(), BarcodeMain.class);
//                        intent2.putExtra("OPath", Path);
//                        startActivity(intent2);
//
//                        if (progressDialog.isShowing()) {
//                            progressDialog.dismiss();
//                        }
//
//                    } else {
//                        Toast.makeText(getApplicationContext(), resp1.getMessage(), Toast.LENGTH_SHORT).show();
//                        if (progressDialog.isShowing()) {
//                            progressDialog.dismiss();
//                        }
////                        head.setText(d);
//                    }
//
//                } else if (response.code() == 401) {
//
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//
//                    Toast.makeText(getApplicationContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
//                    Log.d("Error", response.errorBody().toString());
//
//                } else if (response.code() == 404) {
//
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//
//                    Toast.makeText(getApplicationContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
//                    Log.d("Error", response.errorBody().toString());
//                }
//                else if (response.code() == 500) {
//
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//
//                    Toast.makeText(getApplicationContext(), "Internal Server Error", Toast.LENGTH_SHORT).show();
//                    Log.d("Error", response.errorBody().toString());
//                }
//                else {
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//                    Toast.makeText(BarcodeMain.this, "Operation Failed", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<OrdertrackResponse> call, Throwable t) {
//                call.cancel();
//                Log.e("response-failure", t.toString());
//                if (progressDialog.isShowing()) {
//                    progressDialog.dismiss();
//                }
//                Toast.makeText(BarcodeMain.this, "Network Error", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//    }
//
//    private void call_item(String email, String password, String oPath) {
//
//        final ProgressDialog progressDialog = new ProgressDialog(BarcodeMain.this,
//                R.style.AppTheme_Dark_Dialog);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();
//        final String Path = oPath;
//
//        Call<ProSearchRes> call = apiInterface.getProduct1(email, password, Path);
//        call.enqueue(new Callback<ProSearchRes>() {
//            @Override
//            public void onResponse(Call<ProSearchRes> call, Response<ProSearchRes> response) {
//
//                if (response.isSuccessful()) {
//
//                    ProSearchRes resp = response.body();
//
//                    List<CustomerItem> items = resp.getCustomerItems();
//
//                    if (items != null) {
//
//                        Intent intent1 = new Intent(getApplicationContext(), ProductSearchActivity.class);
//                        intent1.putExtra("OPath", Path);
//                        startActivity(intent1);
//
//                        if (progressDialog.isShowing()) {
//                            progressDialog.dismiss();
//                        }
//                    } else {
//                        Toast.makeText(getApplicationContext(), resp.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//
//                } else if (response.code() == 401) {
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//
//                    Toast.makeText(getApplicationContext(), "Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
//                    Log.d("Error", response.errorBody().toString());
//
//
//                }
//                else if (response.code() == 500) {
//
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//
//                    Toast.makeText(getApplicationContext(), "Internal Server Error", Toast.LENGTH_SHORT).show();
//                    Log.d("Error", response.errorBody().toString());
//                }
//                else {
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//                    Toast.makeText(BarcodeMain.this, "Operation Failed", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ProSearchRes> call, Throwable t) {
//                call.cancel();
//                Log.e("response-failure", t.toString());
//                if (progressDialog.isShowing()) {
//                    progressDialog.dismiss();
//                }
//                Toast.makeText(BarcodeMain.this, "Network Error", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//    }
//
//    private void call_order2(String email, String password, String oPath) {
//
//        final ProgressDialog progressDialog = new ProgressDialog(BarcodeMain.this,
//                R.style.AppTheme_Dark_Dialog);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();
//        final String Path = oPath;
//
//
//        Call<OrderSearchResponse> call = apiInterface.getOrder1(email, password, Path);
//        call.enqueue(new Callback<OrderSearchResponse>() {
//            @Override
//            public void onResponse(Call<OrderSearchResponse> call, Response<OrderSearchResponse> response) {
//
//                if (response.isSuccessful()) {
//
//                    OrderDetails order = response.body().getOrderDetails();
//
//                    if (order != null) {
//
//                        Intent intent = new Intent(getApplicationContext(), OrderSearch2Activity.class);
//                        intent.putExtra("OPath", Path);
//                        Log.d("path_barcode", Path);
//                        startActivity(intent);
//
//                        if (progressDialog.isShowing()) {
//                            progressDialog.dismiss();
//                        }
//
//                    } else {
//                        String d = response.body().getMessage();
//
//                        if (progressDialog.isShowing()) {
//                            progressDialog.dismiss();
//                        }
//                        Toast.makeText(getApplicationContext(), d, Toast.LENGTH_LONG).show();
//
//                    }
//
//                } else if (response.code() == 401) {
//
//                    Toast.makeText(getApplicationContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
//                    Log.d("Error", response.errorBody().toString());
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//
//
//                } else if (response.code() == 404) {
//
//                    Toast.makeText(getApplicationContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
//                    Log.d("Error", response.errorBody().toString());
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//                }
//                else if (response.code() == 500) {
//
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//
//                    Toast.makeText(getApplicationContext(), "Internal Server Error", Toast.LENGTH_SHORT).show();
//                    Log.d("Error", response.errorBody().toString());
//                }
//                else {
//                    Toast.makeText(BarcodeMain.this, "Operation Failed", Toast.LENGTH_SHORT).show();
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<OrderSearchResponse> call, Throwable t) {
//                call.cancel();
//                Log.e("response-failure", t.toString());
//                if (progressDialog.isShowing()) {
//                    progressDialog.dismiss();
//                }
//                Toast.makeText(BarcodeMain.this, "Network Error", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }




}
