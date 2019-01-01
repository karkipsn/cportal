package com.example.colors2web.clientportal.Activities.Navigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.colors2web.clientportal.Activities.Navigation.Billing.Fragment_Fulfilment_Services;
import com.example.colors2web.clientportal.Activities.Navigation.Billing.Fragment_Invoices;
import com.example.colors2web.clientportal.Activities.Navigation.Billing.Fragment_ReportByDateRange;
import com.example.colors2web.clientportal.Activities.Navigation.Billing.Fragment_Shipping;
import com.example.colors2web.clientportal.Activities.Navigation.Inventories.Fragment_AllItems;
import com.example.colors2web.clientportal.Activities.Navigation.Inventories.Fragment_InActiveItems;
import com.example.colors2web.clientportal.Activities.Navigation.Inventories.Fragment_InventoryLogs;
import com.example.colors2web.clientportal.Activities.Navigation.Inventory_Deleveries.Fragment_Deleveries;
import com.example.colors2web.clientportal.Activities.Navigation.Inventory_Deleveries.Fragment_Deleveries_BY_Item;
import com.example.colors2web.clientportal.Activities.Navigation.Orders.Fragment_Create_Order;
import com.example.colors2web.clientportal.Activities.Navigation.Orders.Fragment_FilterOrders;
import com.example.colors2web.clientportal.Activities.Navigation.Orders.Fragment_Orders;
import com.example.colors2web.clientportal.Activities.Navigation.Orders.Fragment_ProgramOrders;
import com.example.colors2web.clientportal.Activities.Navigation.Orders.Fragment_SpecialOrders;
import com.example.colors2web.clientportal.Activities.Navigation.Reports.Fragment_SalesOverviewByDate;
import com.example.colors2web.clientportal.Activities.Navigation.Reports.Fragment_SalesbyItem;
import com.example.colors2web.clientportal.LoginActivity;
import com.example.colors2web.clientportal.R;
import com.example.colors2web.clientportal.SearchFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private View navHeader;
    private FloatingActionButton fab;
    private TextView nav_email, post;
    private ImageView imgProfile;
    RelativeLayout layout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    private final static String TAG_FRAGMENT = "SEARCH_FRAGMENT";

    // index to identify current nav menu item
    public static int navItemIndex = 0;
    public static int tryIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "Dashboard";
    private static final String TAG_DELEVERIES = "Deleveries";
    private static final String TAG_DELEVERIESBYITEM = "Deleveries By Item";
    private static final String TAG_ALLITEMS = "AllItems";
    private static final String TAG_INACTIVEITEMS = "InActive Items";
    private static final String TAG_INVENTORYLOGS = "Inventory Logs ";

    private static final String TAG_ORDERS = "Orders";
    private static final String TAG_SPECIAL_PROGRAM = "Special Program";
    private static final String TAG_PROBLEM_ORDER = "Problem Order";
    private static final String TAG_CREATE_ORDER = "Create Order";
    private static final String TAG_FILTER_ORDER = "Filter Order";

    private static final String TAG_SALES_BY_ITEM ="Sales By Item";
    private static final String TAG_SALES_BY_DATE ="Sales By Date";
    private static final String TAG_INVOICES ="Invoices";
    private static final String TAG_FULFILMENT_SERVICES ="Fulfilment Services";
    private static final String TAG_SHIPPING ="Shipping";
    private static final String TAG_REPORT_BYDATE ="Report By Date";

    private static final String TAG_USERS = "Users";
    private static final String TAG_OFFICES = "Offices";
    private static final String TAG_CUSTOMERDETAILS = "Customer Details";
    private static final String TAG_PACKAGES = "Packages";

    public static String CURRENT_TAG = TAG_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navbar_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mHandler = new Handler();
        fab = findViewById(R.id.fab);

        navHeader = navigationView.getHeaderView(0);
//        For Text color change
//       MenuItem menuitem = navigationView.getMenu().findItem(R.id.navig_group_logout);
//        Spannable spannable = new SpannableString(menuitem.getTitle());
//        spannable.setSpan(new BackgroundColorSpan(getResources().getColor(R.color.colorPrimaryDark)), 0, menuitem.getTitle().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        menuitem.setTitle(spannable);

        post = navHeader.findViewById(R.id.nav_post);
        imgProfile = navHeader.findViewById(R.id.img_profile);
        nav_email = navHeader.findViewById(R.id.nav_email_name);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
//                startActivity(new Intent(HomeActivity.this, TicketNavigActivity.class));
//                isDestroyed();
            }
        });

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }
//        } else {
//            navItemIndex = 4;
//            CURRENT_TAG = TAG_UOM;
//            loadHomeFragment();
//        }
    }

    private void loadNavHeader() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String email = preferences.getString("email", "");
        String postie = preferences.getString("group_type", "");

        if (postie != null) {
            post.setText(postie);
        }

        if (email != null) {
            nav_email.setText(email);
        }

        // Loading profile image
        Glide.with(this).load(R.mipmap.ic_launcher_voxship).apply(RequestOptions.circleCropTransform()).
                into(imgProfile);
//        Glide.with(this).load(R.drawable.logo).
//                apply(RequestOptions.bitmapTransform(new BrightnessFilterTransformation())).into(imgProfile);

    }

    private void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {


                    case R.id.nav_inventories_deliveries:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_DELEVERIES;
                        break;


                    case R.id.nav_inventories_deliveries_by_item:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_DELEVERIESBYITEM;
                        break;

                    case R.id.nav_inventories_all_items:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_ALLITEMS;
                        break;

                    case R.id.nav_inventories_inactive_items:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_INACTIVEITEMS;
                        break;

                    case R.id.nav_inventories_inventory_logs:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_INVENTORYLOGS;
                        break;

                    case R.id.nav_orders:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_ORDERS;
                        break;

                    case R.id.nav_special_program_orders:
                        navItemIndex = 6;
                        CURRENT_TAG = TAG_SPECIAL_PROGRAM;
                        break;

                    case R.id.nav_problem_order:
                        navItemIndex = 7;
                        CURRENT_TAG = TAG_PROBLEM_ORDER;
                        break;

                    case R.id.nav_create_order:
                        navItemIndex = 8;
                        CURRENT_TAG = TAG_CREATE_ORDER;
                        break;

                    case R.id.nav_filter_order:
                        navItemIndex = 9;
                        CURRENT_TAG = TAG_FILTER_ORDER;
                        break;

                    case R.id.nav_report_salesby_item:
                        navItemIndex = 10;
                        CURRENT_TAG = TAG_SALES_BY_ITEM;
                        break;

                    case R.id.nav_report_sales_date:
                        navItemIndex = 11;
                        CURRENT_TAG = TAG_SALES_BY_DATE;
                        break;

                    case R.id.nav_billing_invoices:
                        navItemIndex = 12;
                        CURRENT_TAG = TAG_INVOICES;
                        break;


                    case R.id.nav_billing_fulfilment_services:
                        navItemIndex = 13;
                        CURRENT_TAG = TAG_FULFILMENT_SERVICES;
                        break;

                    case R.id.nav_billing_shipping:
                        navItemIndex = 14;
                        CURRENT_TAG = TAG_SHIPPING;
                        break;

                    case R.id.nav_billing_report_by_date_range:
                        navItemIndex = 15;
                        CURRENT_TAG = TAG_REPORT_BYDATE;
                        break;


                    case R.id.nav_customer_details:
                        navItemIndex = 16;
                        CURRENT_TAG = TAG_CUSTOMERDETAILS;
                        break;

                    case R.id.nav_offices:
                        navItemIndex = 17;
                        CURRENT_TAG = TAG_OFFICES;
                        break;

                    case R.id.nav_users:
                        navItemIndex = 18;
                        CURRENT_TAG = TAG_USERS;
                        break;

                    case R.id.nav_packages:
                        navItemIndex = 19;
                        CURRENT_TAG = TAG_PACKAGES;
                        break;


                    case R.id.nav_logout:
                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        drawerLayout.closeDrawers();
                        SharedPreferences preferences =PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear().apply();
                        finish();
//                        Clearing the session data left !!! Call it to method and clear it
                        break;

                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                item.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

    }

    private void loadHomeFragment() {

        // selecting appropriate nav menu item
        selectNavMenu();
        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawerLayout.closeDrawers();

            toggleFab();

            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        //Closing drawer on item click
        drawerLayout.closeDrawers();

        toggleFab();

        // refresh toolbar menu
        invalidateOptionsMenu();

    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }


    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                //Inventories Deleveries
                Fragment_Deleveries d_fragment = new Fragment_Deleveries();
                return d_fragment;

            case 1:
                //Inventories Deleveries
                Fragment_Deleveries_BY_Item di_fragment = new Fragment_Deleveries_BY_Item();
                return di_fragment;

            case 2:
                //Inventories
                Fragment_AllItems all_fragment = new Fragment_AllItems();
                return  all_fragment;

            case 3:
                //Inventories
                Fragment_InActiveItems inactive_fragment = new Fragment_InActiveItems();
                return  inactive_fragment;

            case 4:
                //Inventories
                Fragment_InventoryLogs ilogs_fragment = new Fragment_InventoryLogs();
                return  ilogs_fragment;

            case 5:
                //Orders
                Fragment_Orders orders_fragment = new Fragment_Orders();
                return  orders_fragment;

            case 6:
                //Orders
                Fragment_SpecialOrders specialOrders_fragment = new Fragment_SpecialOrders();
                return  specialOrders_fragment;

            case 7:
                //Orders
                Fragment_ProgramOrders programOrders_fragment = new Fragment_ProgramOrders();
                return  programOrders_fragment;

            case 8:
//                Orders
                Fragment_Create_Order create_order_fragment = new Fragment_Create_Order();
                return  create_order_fragment;

            case 9:
                Fragment_FilterOrders filterOrders_fragment = new Fragment_FilterOrders();
                return  filterOrders_fragment;


            case 10:
                //Reports
                Fragment_SalesbyItem salesbyItem_fragment = new Fragment_SalesbyItem();
                return salesbyItem_fragment;

            case 11:
                //Reports
                Fragment_SalesOverviewByDate salesOverviewByDate_fragment = new Fragment_SalesOverviewByDate();
                return  salesOverviewByDate_fragment;

            case 12:
                //billing
                Fragment_Invoices invoices_fragment = new Fragment_Invoices();
                return  invoices_fragment;

            case 13:
                //billing
                Fragment_Fulfilment_Services fulfilment_services_fragment = new Fragment_Fulfilment_Services();
                return  fulfilment_services_fragment;

            case 14:
                //billing
                Fragment_Shipping shipping_fragment = new Fragment_Shipping();
                return  shipping_fragment;

            case 15:
                //billing
                Fragment_ReportByDateRange reportByDateRange_fragment = new Fragment_ReportByDateRange();
                return reportByDateRange_fragment;


            case 16:
                // home// Customers Details
                Fragment_Customer_Details cd_fragment = new Fragment_Customer_Details();
                return cd_fragment;

            case 17:
                // Offices
                Fragment_Offices o_fragment = new Fragment_Offices();
                return  o_fragment;

            case 18:
                Fragment_Users u_fragment = new Fragment_Users();
                return  u_fragment;

            case 19:
                Fragment_Packages p_fragment = new Fragment_Packages();
                return  p_fragment;


            default:
                return new Fragment_Customer_Details();
        }

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }
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
                overridePendingTransition(R.anim.search_push_left_in, R.anim.search_push_left_out);

            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.image:
                return true;

            case R.id.image_try:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    // show or hide the fab
    private void toggleFab() {
//        if (navItemIndex == 0)
//            fab.show();
//        else
//            fab.hide();
        fab.show();
    }
}

