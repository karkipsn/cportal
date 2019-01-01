package com.example.colors2web.clientportal.Adapters;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
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


import com.example.colors2web.clientportal.Activities.Navigation.Fragment_Users;
import com.example.colors2web.clientportal.POJO.CommonUtilities.SpinnerPojo;
import com.example.colors2web.clientportal.POJO.Users.User;
import com.example.colors2web.clientportal.POJO.Users.UserCreatePOJO;
import com.example.colors2web.clientportal.R;
import com.example.colors2web.clientportal.api.APIClient;
import com.example.colors2web.clientportal.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private List<User> UserList;
    private Activity mContext;
    Fragment_Users user_fragment;


    Context context;

    private APIInterface apiInterface;

    private String s_customer, s_program, s_activity, s_groups;
    private String s_cus1, s_pr1, s_act1, fname1, lname1, s_email1, pass1, cpass1;
    private Long cus_id;


    private Spinner s_cus, s_act, s_prg, s_grp;

    ArrayList<SpinnerPojo> groupList = new ArrayList<>();

    private EditText e_fname, e_lname, e_pass, e_cpass, e_email;

    public UserAdapter(List<User> userList, Activity mContext) {
        UserList = userList;
        this.mContext = mContext;
    }

    public UserAdapter(List<User> uList, FragmentActivity activity) {
        UserList = uList;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public UserAdapter.UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_users, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserAdapter.UserHolder holder, int position) {

        User user = UserList.get(position);

        String name1 = user.getFirstName() + " " + user.getLastName();
        String email1 = user.getEmail();
        String c1 = user.getCreatedAt();

        holder.name.setText(name1);
        holder.email.setText(email1);
        holder.created.setText(c1);


        final String ufname = user.getFirstName();
        final String ulname = user.getFirstName();
        final String uemail = user.getFirstName();


        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View popupView = inflater.inflate(R.layout.modal_user_create, null);

                WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();

                int height = display.getHeight();
                int width = display.getWidth();

                final PopupWindow popup = new PopupWindow(popupView,
                        (int) (width * 0.9), WindowManager.LayoutParams.WRAP_CONTENT);
                popup.setFocusable(true);
                popup.setOutsideTouchable(true);
                popup.setAnimationStyle(android.R.style.Animation_Dialog);

                popup.showAtLocation(holder.update, Gravity.CENTER, 0, 0); //Displaying popup

                final View container = (View) popup.getContentView().getParent();
                final WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
                p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                p.dimAmount = 0.7f;
                wm.updateViewLayout(container, p);

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
                final String email = preferences.getString("email", "");
                final String password = preferences.getString("password", "");
                apiInterface = APIClient.getClient().create(APIInterface.class);


                Button submit, cancle;


                s_prg = popupView.findViewById(R.id.user_modal_special_program);
                s_act = popupView.findViewById(R.id.user_modal_user_activated);
                e_fname = popupView.findViewById(R.id.user_modal_first_name);
                e_fname.setText(ufname);
                e_lname = popupView.findViewById(R.id.user_modal_last_name);
                e_lname.setText(ulname);

                e_pass = popupView.findViewById(R.id.user_modal_password);
                e_cpass = popupView.findViewById(R.id.user_modal_confirm_password);

                e_email = popupView.findViewById(R.id.user_modal_email);
                e_email.setText(uemail);

                submit = popupView.findViewById(R.id.user_modal_submit);
                cancle = popupView.findViewById(R.id.user_modal_cancel);

                s_prg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        SpinnerPojo sp = (SpinnerPojo) parent.getItemAtPosition(position);
                        s_program = sp.getName();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
//                        s_program = null;

                    }
                });

                loadactivation();
                s_act.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        s_activity = parent.getItemAtPosition(position).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
//                        s_activity = parent.getItemAtPosition(0).toString();

                    }
                });


                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popup.dismiss();
                    }
                });


                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        s_pr1 = s_program;

                        if (s_activity.equals("Yes")) {
                            s_act1 = String.valueOf(1);
                        } else {
                            s_act1 = String.valueOf(0);
                        }
                        fname1 = e_fname.getText().toString();
                        lname1 = e_lname.getText().toString();

                        s_email1 = e_email.getText().toString();
                        pass1 = e_cpass.getText().toString();
                        cpass1 = e_pass.getText().toString();


                        UserCreatePOJO pojo = new UserCreatePOJO(s_pr1, s_act1, fname1,
                                lname1, s_email1, pass1, cpass1);

//                        update_users(email, password, pojo, popup, u_id);
                    }

                });
            }
        });

    }

    private void loadactivation() {

        List<String>active = new ArrayList<>();
        active.add("Yes");
        active.add("No");

        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_spinner_dropdown_item,active );
        adp1.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        s_act.setAdapter(adp1);
        s_act.setPrompt("Select Activation");
    }



//    private void update_users(String email, String password, UserCreatePOJO pojo, final PopupWindow popup, String u_id) {
//
//        final ProgressDialog progressDialog = new ProgressDialog(mContext,
//                R.style.AppTheme_Dark_Dialog);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();
//
//
//        Call<UsersResponse> call = apiInterface.updateusers(email, password, u_id, pojo);
//        call.enqueue(new Callback<UsersResponse>() {
//            @Override
//            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
//
//                if (response.isSuccessful()) {
//
//                    UsersResponse resp1 = response.body();
//
//                    if (resp1.getReturnType().equals("success")) {
//
//                        Toast.makeText(mContext, resp1.getMessage(), Toast.LENGTH_SHORT).show();
//
//                        if (progressDialog.isShowing()) {
//                            progressDialog.dismiss();
//
//                        }
//                        popup.dismiss();
//                        fragment.refresh();
////                            main_fragment.refresh1();
//
//
//                    } else {
//                        if (progressDialog.isShowing()) {
//                            progressDialog.dismiss();
//                        }
//                        Toast.makeText(mContext, resp1.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//                    Toast.makeText(mContext, " User Create operation Failed", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UsersResponse> call, Throwable t) {
//
//                call.cancel();
//                if (progressDialog.isShowing()) {
//                    progressDialog.dismiss();
//                }
//                Log.e("response-failure", t.toString());
//                Toast.makeText(mContext, "Network Error", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//    }


//    private void loadspinner_programs(String email, String password, Long cus_id1) {
//
//        final ProgressDialog progressDialog = new ProgressDialog(mContext,
//                R.style.AppTheme_Dark_Dialog);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();
//
//        Call<SProgramResponse> call = apiInterface.getSpecialPrograms(email, password, String.valueOf(cus_id1));
//
//        call.enqueue(new Callback<SProgramResponse>() {
//            @Override
//            public void onResponse(Call<SProgramResponse> call, Response<SProgramResponse> response) {
//
//                if (response.isSuccessful()) {
//
//                    SProgramResponse resp1 = response.body();
//
//                    List<SpecialProgram> cus = resp1.getSpecialPrograms();
//
////                    Toast.makeText(mContext, resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();
//
//                    if (cus != null) {
//
//                        for (int i = 0; i < cus.size(); i++) {
//
//                            SpinnerPojo order1 = new SpinnerPojo();
//
//                            String name = cus.get(i).getProgramName();
//                            Long cus_id = cus.get(i).getId();
//
//                            order1.setCus_id(cus_id);
//                            order1.setName(name);
//
//                            prgList.add(order1);// must be the object of empty list initiated
//
//                        }
//                    } else {
////                        String d = response.body().getMessage();
////                        prgList.add(null);
//
//                    }
//
//                    ArrayAdapter<SpinnerPojo> adp2 = new ArrayAdapter<SpinnerPojo>(mContext,
//                            android.R.layout.simple_spinner_dropdown_item, prgList);
//                    adp2.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
//                    s_prg.setAdapter(adp2);
//                    s_prg.setPrompt("Select Programs");
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//
//
//                } else if (response.code() == 401) {
//
//
//                    Toast.makeText(mContext, " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
//                    Log.d("Error", response.errorBody().toString());
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//
//                } else if (response.code() == 404) {
//
//
//                    Toast.makeText(mContext, "InValid Web Address", Toast.LENGTH_SHORT).show();
//                    Log.d("Error", response.errorBody().toString());
//                } else if (response.code() == 500) {
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//                    Toast.makeText(mContext, "Server Broken", Toast.LENGTH_LONG).show();
//                    Log.d("Error", response.errorBody().toString());
//
//
//                } else {
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//                    Toast.makeText(mContext, "Operation Failed", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SProgramResponse> call, Throwable t) {
//                call.cancel();
//
//                if (progressDialog.isShowing()) {
//                    progressDialog.dismiss();
//                }
//                Log.e("response-failure", t.toString());
//                Toast.makeText(mContext, "Network Error", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//    }


    @Override
    public int getItemCount() {
        return UserList.size();
    }


    public void reloadfragment() {
        UserList.clear();
        notifyDataSetChanged();
    }

    public void updateAdapter(List<User> uList) {
        UserList = uList;
        notifyDataSetChanged();
    }


    public class UserHolder extends RecyclerView.ViewHolder {
        TextView name, email, groups, created;
        Button update;

        public UserHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.user_adpt_name);
            email = itemView.findViewById(R.id.user_adpt_email);
            created = itemView.findViewById(R.id.user_adpt_created);
            update = itemView.findViewById(R.id.user_adpt_update);
        }
    }
}
