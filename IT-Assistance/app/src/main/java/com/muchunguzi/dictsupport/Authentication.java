package com.muchunguzi.dictsupport;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Authentication extends AppCompatActivity {

    final String TAG = this.getClass().getName();
    ProgressDialog progressDialog;
    
    EditText name;
    EditText department;
    EditText room;
    EditText building;
    EditText username;
    EditText password;
    EditText password_comfirm;
    EditText recover_qn;
    EditText recover_ans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentication);

        progressDialog = new ProgressDialog(Authentication.this);

        // Imput text that takes data from registerd user
        name = (EditText) findViewById(R.id.name);
        department = (EditText) findViewById(R.id.department);
        room = (EditText) findViewById(R.id.room);
        building = (EditText) findViewById(R.id.building);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        password_comfirm = (EditText) findViewById(R.id.comfirm_pass);
        recover_qn = (EditText) findViewById(R.id.recover_qn);
        recover_ans = (EditText) findViewById(R.id.recover_ans);


        //Shared preferences

        final SharedPreferences sharedPreferences = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        final HashMap data = new HashMap();

        final String nameVer = sharedPreferences.getString("name", "");
        final String usernameVer = sharedPreferences.getString("username", "");
        final String passwordVer = sharedPreferences.getString("password", "");
        final String departmentVer = sharedPreferences.getString("department", "");
        final String roomVer = sharedPreferences.getString("room", "");
        final String buildingVer = sharedPreferences.getString("building", "");
        final String recover_qnVer = sharedPreferences.getString("qn", "");
        final String recover_ansVer = sharedPreferences.getString("ans", "");
        final String login_logout = sharedPreferences.getString("login_logout", "");

//        data.put(Constants.KEY_REG_NUMBER, username.getText().toString().trim());
//        data.put(Constants.KEY_PASS, password.getText().toString().trim());
//        data.put(reg_number, reg_number);
//        data.put(Constants.KEY_NAME_STD, name);
//        data.put(Constants.KEY_USER_STATUS, status);
//        data.put(Constants.KEY_USER_COURSE, course);
//        data.put(Constants.KEY_ACCESS, access);


//
       



        // Buttorn that will handle registration issues
        Button register_btn = (Button) findViewById(R.id.register_btn);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!(name.getText().toString().isEmpty() || department.getText().toString().isEmpty()
                        || room.getText().toString().isEmpty() || building.getText().toString().isEmpty()
                        || username.getText().toString().isEmpty() || password.getText().toString().isEmpty()
                        || password_comfirm.getText().toString().isEmpty() || recover_qn.getText().toString().isEmpty()
                        || recover_ans.getText().toString().isEmpty())){

                    if(password.getText().toString().equals(password_comfirm.getText().toString())){

                        progressDialog.setMessage("Registering ......");
                        progressDialog.show();

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.REGISTRE_URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                progressDialog.dismiss();
                                if (response.contains("added")) {

                                    Intent launch_chart = new Intent(Authentication.this, Chart.class);
                                    launch_chart.putExtra("account_name", name.getText().toString().trim());
                                    startActivity(launch_chart);
                                    finish();

                                    editor.putString("name", name.getText().toString().trim());
                                    editor.putString("username", username.getText().toString().trim());
                                    editor.putString("password", password.getText().toString().trim());
                                    editor.putString("department", department.getText().toString().trim());
                                    editor.putString("room", room.getText().toString().trim());
                                    editor.putString("building", building.getText().toString().trim());
                                    editor.putString("qn", recover_qn.getText().toString().trim());
                                    editor.putString("ans", recover_ans.getText().toString().trim());
                                    editor.putString("login_logout", "login");

                                    Log.d(TAG, sharedPreferences.getString("name", ""));
                                    Log.d(TAG, sharedPreferences.getString("username", ""));
                                    Log.d(TAG, sharedPreferences.getString("password", ""));
                                    Log.d(TAG, sharedPreferences.getString("department", ""));
                                    Log.d(TAG, sharedPreferences.getString("room", ""));
                                    Log.d(TAG, sharedPreferences.getString("building", ""));
                                    Log.d(TAG, sharedPreferences.getString("qn", ""));
                                    Log.d(TAG, sharedPreferences.getString("ans", ""));
                                    Log.d(TAG, sharedPreferences.getString("login_logout", ""));

                                    editor.apply();


                                } else {
                                    Toast.makeText(Authentication.this, response, Toast.LENGTH_SHORT).show();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                if (error instanceof TimeoutError) {
                                    Toast.makeText(Authentication.this, "Timeout Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof NoConnectionError) {
                                    Toast.makeText(Authentication.this, "No Connection Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof AuthFailureError) {
                                    Toast.makeText(Authentication.this, "Authenticatiin Faliure Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof NetworkError) {
                                    Toast.makeText(Authentication.this, "Network Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof ServerError) {
                                    Toast.makeText(Authentication.this, "Server Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof ParseError) {
                                    Toast.makeText(Authentication.this, "JSON Parse Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {

                                Map<String, String> data = new HashMap<String, String>();

                                data.put(Constants.KEY_NAME, name.getText().toString().trim());
                                data.put(Constants.KEY_DEPARTMENT, department.getText().toString().trim());
                                data.put(Constants.KEY_ROOM, room.getText().toString().trim());
                                data.put(Constants.KEY_BLOCK, building.getText().toString().trim());
                                data.put(Constants.KEY_USER_STATUS,  "user");
                                data.put(Constants.KEY_REG_NUMBER, username.getText().toString().trim());
                                data.put(Constants.KEY_PASS, password.getText().toString().trim());
                                data.put(Constants.KEY_REC_QN, recover_qn.getText().toString().trim());
                                data.put(Constants.KEY_REC_ANS, recover_ans.getText().toString().toLowerCase().trim());
                                return data;


                            }

                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                                Map<String, String> headers = new HashMap<String, String>();
                                headers.put("User-Agent", "Notes Board");
                                return headers;
                            }
                        };
                        MySingleton.getInstance(Authentication.this).addToRequestQueue(stringRequest);



                    }else{
                        Toast.makeText(Authentication.this, "Password do not match", Toast.LENGTH_LONG).show();
                    }

                } else{
                        Toast.makeText(Authentication.this, "Fill all fields", Toast.LENGTH_LONG).show();
                }


            }
        });

        Button close = (Button) findViewById(R.id.close_btn_sign_up);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launch_welcomesreen = new Intent(getBaseContext(), Welcome_Screen.class);
                startActivity(launch_welcomesreen);
                finish();
            }
        });

        TextView goto_login =(TextView) findViewById(R.id.goto_login_display);
        goto_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putString("login_logout", "logout");
                Log.d(TAG, sharedPreferences.getString("login_logout", ""));
                editor.apply();
                
                Intent lauch_login = new Intent(Authentication.this, Welcome_Screen.class);
                startActivity(lauch_login);
                finish();
                
            }
        });

    }
}
