package com.example.colors2web.clientportal.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.example.colors2web.clientportal.Adapters.Dr_history_Adapter;
import com.example.colors2web.clientportal.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.clientportal.POJO.InventoriesDeleveries.DeliveryHistory.DrShipment;
import com.example.colors2web.clientportal.R;
import com.example.colors2web.clientportal.SearchFragment;
import com.example.colors2web.clientportal.api.APIClient;
import com.example.colors2web.clientportal.api.APIInterface;

import java.util.List;

public class View_dr_historyActivity extends AppCompatActivity {
    Toolbar toolbar;
    APIInterface apiInterface;
    RecyclerView mrecycleView;
    Dr_history_Adapter dr_history_adapter;
    List<DrShipment> DrList;
    private final static String TAG_FRAGMENT = "SEARCH_FRAGMENT";
    FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_history_display);


        apiInterface = APIClient.getClient().create(APIInterface.class);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View_dr_historyActivity.super.onBackPressed();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

            }
        });

        mrecycleView = findViewById(R.id.recycleview_post);
        dr_history_adapter = new Dr_history_Adapter(DrList,getApplicationContext());



        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(this);
//        mrecycleView.setHasFixedSize(true);
        mrecycleView.setLayoutManager(mlayoutManager);

        mrecycleView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayout.HORIZONTAL,16));
        mrecycleView.setItemAnimator(new DefaultItemAnimator());
        mrecycleView.setAdapter(dr_history_adapter);
        loadAdapter();
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
        moveTaskToBack(true);
    }



    private void loadAdapter() {
        Intent i = getIntent();
        if(i!=null){

            List<DrShipment>list = (List<DrShipment>) i.getSerializableExtra("DrList");
            Log.d("DrList2",list.toString());
            dr_history_adapter.updateAnswers(list);
        }
    }

}

