package com.muchunguzi.dictsupport;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class Admin extends AppCompatActivity {

    String radioChacked;

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

    String account_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        progressDialog = new ProgressDialog(Admin.this);

        account_name = getIntent().getExtras().getString("account_name");



        // Imput text that takes data from registerd user
        name = (EditText) findViewById(R.id.nameIT);
        department = (EditText) findViewById(R.id.departmentIT);
        room = (EditText) findViewById(R.id.roomIT);
        building = (EditText) findViewById(R.id.buildingIT);
        username = (EditText) findViewById(R.id.usernameIT);
        password = (EditText) findViewById(R.id.passwordIT);
        password_comfirm = (EditText) findViewById(R.id.comfirm_passIT);
        recover_qn = (EditText) findViewById(R.id.recover_qnIT);
        recover_ans = (EditText) findViewById(R.id.recover_ansIT);



        // Buttorn that will handle registration issues
        Button register_btn = (Button) findViewById(R.id.register_btnIT);
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
                                    Toast.makeText(Admin.this, "User registered successful", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(Admin.this, response.toString().trim(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                if (error instanceof TimeoutError) {
                                    Toast.makeText(Admin.this, "Timeout Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof NoConnectionError) {
                                    Toast.makeText(Admin.this, "No Connection Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof AuthFailureError) {
                                    Toast.makeText(Admin.this, "Authenticatiin Faliure Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof NetworkError) {
                                    Toast.makeText(Admin.this, "Network Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof ServerError) {
                                    Toast.makeText(Admin.this, "Server Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof ParseError) {
                                    Toast.makeText(Admin.this, "JSON Parse Error", Toast.LENGTH_SHORT).show();
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
                                data.put(Constants.KEY_USER_STATUS,  radioChacked);
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
                        MySingleton.getInstance(Admin.this).addToRequestQueue(stringRequest);



                    }else{
                        Toast.makeText(Admin.this, "Password do not match", Toast.LENGTH_LONG).show();
                    }

                } else{
                    Toast.makeText(Admin.this, "Fill all fields", Toast.LENGTH_LONG).show();
                }


            }
        });

        Button close = (Button) findViewById(R.id.close_btn_sign_upIT);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launch_welcomesreen = new Intent(getBaseContext(), Welcome_Screen.class);
                startActivity(launch_welcomesreen);
                finish();
            }
        });





        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.group_it_admin);
        RadioButton admin = (RadioButton) findViewById(R.id.admin);
        RadioButton it = (RadioButton) findViewById(R.id.it_exp);

        admin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    radioChacked = "admin";
                    Toast.makeText(Admin.this, radioChacked, Toast.LENGTH_SHORT).show();
                }
            }
        });

        it.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    radioChacked = "it_expert";
                    Toast.makeText(Admin.this, radioChacked, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
