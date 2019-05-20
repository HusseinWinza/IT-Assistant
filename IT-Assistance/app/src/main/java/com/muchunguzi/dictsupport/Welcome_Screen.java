package com.muchunguzi.dictsupport;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Welcome_Screen extends AppCompatActivity {

    public static int TIME_OUT = 5000;
    ProgressDialog progressDialog;
    final String TAG = this.getClass().getName();
    JSONObject jsonObject;
    JSONArray jsonArray;
    String json;
    String rows2;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        progressDialog = new ProgressDialog(Welcome_Screen.this);


        final SharedPreferences sharedPreferences = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        final String nameVer = sharedPreferences.getString("name", "");
        final String usernameVer = sharedPreferences.getString("username", "");
        final String passwordVer = sharedPreferences.getString("password", "");
        final String departmentVer = sharedPreferences.getString("department", "");
        final String roomVer = sharedPreferences.getString("room", "");
        final String buildingVer = sharedPreferences.getString("building", "");
        final String recover_qnVer = sharedPreferences.getString("qn", "");
        final String recover_ansVer = sharedPreferences.getString("ans", "");
        final String status_ver = sharedPreferences.getString("status", "");
        final String login_logout = sharedPreferences.getString("login_logout", "");




        /// handler that handle the welcomescrreen for about 3 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {




                if (login_logout.equals("logout")) {


//                    if (!(nameVer.isEmpty() || usernameVer.isEmpty() || passwordVer.isEmpty())) {

                    AlertDialog.Builder login = new AlertDialog.Builder(Welcome_Screen.this);
                    View view = getLayoutInflater().inflate(R.layout.login, null);
                    login.setView(view);
                    final AlertDialog dialog = login.create();
                    dialog.show();

                    final EditText username = (EditText) view.findViewById(R.id.username_login);
                    final EditText password = (EditText) view.findViewById(R.id.password_login);
                    final TextView err_display = (TextView) view.findViewById(R.id.err_display);
                    final TextView recover_display = (TextView) view.findViewById(R.id.recover_display);
                    final TextView sign_up_display1 = (TextView) view.findViewById(R.id.sign_up_login);
                    Button login_btn = (Button) view.findViewById(R.id.Login_buttton);
                    Button close_btn_login = (Button) view.findViewById(R.id.close_btn_login);
                    recover_display.setVisibility(View.INVISIBLE);
                    sign_up_display1.setVisibility(View.INVISIBLE);


                    close_btn_login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            progressDialog.dismiss();
                            dialog.dismiss();
                            System.exit(0);
//
                        }
                    });

                    login_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            progressDialog.setMessage("Log in ......");
                            progressDialog.show();

                            err_display.setVisibility(View.INVISIBLE);
                            sign_up_display1.setVisibility(View.INVISIBLE);

                            if (!(username.getText().toString().isEmpty() || password.getText().toString().isEmpty())) {


                                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.UPLOAD_URL, new Response.Listener<String>() {


                                    @Override
                                    public void onResponse(String response) {
                                        progressDialog.dismiss();
                                        if (response.contains("0")) {

                                            progressDialog.dismiss();
                                            err_display.setVisibility(View.VISIBLE);
                                            err_display.setText("Wrong username or passwd");
                                            recover_display.setVisibility(View.VISIBLE);
                                            sign_up_display1.setVisibility(View.VISIBLE);

                                            sign_up_display1.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Intent launch_sign_up = new Intent(Welcome_Screen.this, Authentication.class);
                                                    startActivity(launch_sign_up);
                                                    finish();
                                                }
                                            });

                                            recover_display.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {

                                                    progressDialog.setMessage("Please wait ......");
                                                    progressDialog.show();

                                                    StringRequest stringRequest2 = new StringRequest(Request.Method.POST, Constants.RECOVER_PASS_URL, new Response.Listener<String>() {


                                                        @Override
                                                        public void onResponse(String response) {
                                                            progressDialog.dismiss();
                                                            if (response.equals("")) {
                                                                Toast.makeText(Welcome_Screen.this, "User Does not Exist", Toast.LENGTH_SHORT).show();
                                                            } else if (response.contains("*0*")) {
                                                                Toast.makeText(Welcome_Screen.this, "User Does not Exist", Toast.LENGTH_SHORT).show();

                                                            } else {


                                                                try {

                                                                    jsonObject = new JSONObject(response);
                                                                    jsonArray = jsonObject.getJSONArray("server_response");


                                                                    String recovery_qn;
                                                                    String recovery_ans;
                                                                    int count = 0;
                                                                    while (count < jsonArray.length()) {
                                                                        JSONObject jo = jsonArray.getJSONObject(count);
                                                                        recovery_qn = jo.getString("recovery_qn");
                                                                        recovery_ans = jo.getString("recovery_ans");

                                                                        progressDialog.dismiss();

                                                                        AlertDialog.Builder recover_dialog = new AlertDialog.Builder(Welcome_Screen.this);
                                                                        View recovery_view = getLayoutInflater().inflate(R.layout.recovery, null);
                                                                        recover_dialog.setView(recovery_view);
                                                                        final AlertDialog dialog2 = recover_dialog.create();
                                                                        dialog2.show();

                                                                        final TextView rec_err = (TextView) recovery_view.findViewById(R.id.err_rec_display);
                                                                        TextView rec_qn = (TextView) recovery_view.findViewById(R.id.recover_qn_disp);
                                                                        final EditText rec_ans = (EditText) recovery_view.findViewById(R.id.recover_ans_disp);
                                                                        final TextView sign_up = (TextView) recovery_view.findViewById(R.id.sign_up_display);
                                                                        sign_up.setVisibility(View.INVISIBLE);
                                                                        Button rec_btn = (Button) recovery_view.findViewById(R.id.recover_btn1);
                                                                        Button close_btn = (Button) recovery_view.findViewById(R.id.close_btn);

                                                                        rec_qn.setText(recovery_qn);

                                                                        final String finalRecovery_ans = recovery_ans;
                                                                        rec_btn.setOnClickListener(new View.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(View view) {
                                                                                rec_err.setVisibility(View.INVISIBLE);

                                                                                if (!(rec_ans.getText().toString().isEmpty())) {


                                                                                    if (rec_ans.getText().toString().toLowerCase().equals(finalRecovery_ans)) {

                                                                                        progressDialog.setMessage("Recovering ......");
                                                                                        progressDialog.show();


                                                                                        StringRequest stringRequest3 = new StringRequest(Request.Method.POST, Constants.RECOVER_UPDATE_URL, new Response.Listener<String>() {

                                                                                            @Override
                                                                                            public void onResponse(String response) {

                                                                                                if (response.contains("succesfull")) {
                                                                                                    progressDialog.dismiss();
                                                                                                    editor.putString("username", username.getText().toString().trim());
                                                                                                    editor.putString("password", username.getText().toString().trim());

                                                                                                    Log.d(TAG, sharedPreferences.getString("username", ""));
                                                                                                    Log.d(TAG, sharedPreferences.getString("password", ""));

                                                                                                    editor.apply();

                                                                                                    rec_err.setVisibility(View.VISIBLE);
                                                                                                    rec_err.setTextColor(Color.parseColor("#0e970c"));
                                                                                                    rec_err.setText("username = " + username.getText().toString() + "\npasswprd = " + username.getText().toString());

                                                                                                } else {
                                                                                                    progressDialog.dismiss();
                                                                                                    rec_err.setVisibility(View.VISIBLE);
                                                                                                    rec_err.setText("Password recover fail");

                                                                                                }

                                                                                            }

                                                                                        }, new Response.ErrorListener() {
                                                                                            @Override
                                                                                            public void onErrorResponse(VolleyError error) {
                                                                                                progressDialog.dismiss();
                                                                                                if (error instanceof TimeoutError) {
                                                                                                    Toast.makeText(Welcome_Screen.this, "Timeout Error", Toast.LENGTH_SHORT).show();
                                                                                                } else if (error instanceof NoConnectionError) {
                                                                                                    Toast.makeText(Welcome_Screen.this, "No Connection Error", Toast.LENGTH_SHORT).show();
                                                                                                } else if (error instanceof AuthFailureError) {
                                                                                                    Toast.makeText(Welcome_Screen.this, "Authenticatiin Faliure Error", Toast.LENGTH_SHORT).show();
                                                                                                } else if (error instanceof NetworkError) {
                                                                                                    Toast.makeText(Welcome_Screen.this, "Network Error", Toast.LENGTH_SHORT).show();
                                                                                                } else if (error instanceof ServerError) {
                                                                                                    Toast.makeText(Welcome_Screen.this, "Server Error", Toast.LENGTH_SHORT).show();
                                                                                                } else if (error instanceof ParseError) {
                                                                                                    Toast.makeText(Welcome_Screen.this, "JSON Parse Error", Toast.LENGTH_SHORT).show();
                                                                                                }
                                                                                            }
                                                                                        }) {
                                                                                            @Override
                                                                                            protected Map<String, String> getParams() throws AuthFailureError {
                                                                                                Map<String, String> data = new HashMap<String, String>();
                                                                                                data.put(Constants.KEY_REG_NUMBER, username.getText().toString().trim());
                                                                                                data.put(Constants.KEY_NEW_PASS, username.getText().toString().trim());

                                                                                                return data;
                                                                                            }

                                                                                            @Override
                                                                                            public Map<String, String> getHeaders() throws AuthFailureError {
                                                                                                Map<String, String> headers = new HashMap<String, String>();
                                                                                                headers.put("User-Agent", "Notes Board");
                                                                                                return headers;
                                                                                            }
                                                                                        };
                                                                                        MySingleton.getInstance(Welcome_Screen.this).addToRequestQueue(stringRequest3);


                                                                                    } else {
                                                                                        rec_err.setVisibility(View.VISIBLE);
                                                                                        rec_err.setText("Wrong Recovery answer");
                                                                                    }


                                                                                } else {
                                                                                    Toast.makeText(Welcome_Screen.this, "Enter the recover answer", Toast.LENGTH_LONG).show();
                                                                                }

                                                                            }
                                                                        });


                                                                        sign_up.setOnClickListener(new View.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(View view) {
                                                                                Intent launch_sign_up = new Intent(Welcome_Screen.this, Authentication.class);
                                                                                startActivity(launch_sign_up);
                                                                                finish();
                                                                            }
                                                                        });

                                                                        close_btn.setOnClickListener(new View.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(View view) {
                                                                                dialog2.dismiss();
                                                                                Intent launch_welcomesreen = new Intent(Welcome_Screen.this, Welcome_Screen.class);
                                                                                startActivity(launch_welcomesreen);
                                                                                finish();
                                                                            }
                                                                        });


                                                                        count++;
                                                                    }


                                                                } catch (JSONException e) {
                                                                    e.printStackTrace();
                                                                }

                                                            }

                                                        }
                                                    }, new Response.ErrorListener() {
                                                        @Override
                                                        public void onErrorResponse(VolleyError error) {
                                                            progressDialog.dismiss();
                                                            if (error instanceof TimeoutError) {
                                                                Toast.makeText(Welcome_Screen.this, "Timeout Error", Toast.LENGTH_SHORT).show();
                                                            } else if (error instanceof NoConnectionError) {
                                                                Toast.makeText(Welcome_Screen.this, "No Connection Error", Toast.LENGTH_SHORT).show();
                                                            } else if (error instanceof AuthFailureError) {
                                                                Toast.makeText(Welcome_Screen.this, "Authenticatiin Faliure Error", Toast.LENGTH_SHORT).show();
                                                            } else if (error instanceof NetworkError) {
                                                                Toast.makeText(Welcome_Screen.this, "Network Error", Toast.LENGTH_SHORT).show();
                                                            } else if (error instanceof ServerError) {
                                                                Toast.makeText(Welcome_Screen.this, "Server Error", Toast.LENGTH_SHORT).show();
                                                            } else if (error instanceof ParseError) {
                                                                Toast.makeText(Welcome_Screen.this, "JSON Parse Error", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    }) {
                                                        @Override
                                                        protected Map<String, String> getParams() throws AuthFailureError {
                                                            Map<String, String> data = new HashMap<String, String>();
                                                            data.put(Constants.KEY_REG_NUMBER, username.getText().toString().trim());
                                                            return data;
                                                        }

                                                        @Override
                                                        public Map<String, String> getHeaders() throws AuthFailureError {
                                                            Map<String, String> headers = new HashMap<String, String>();
                                                            headers.put("User-Agent", "Notes Board");
                                                            return headers;
                                                        }
                                                    };
                                                    MySingleton.getInstance(Welcome_Screen.this).addToRequestQueue(stringRequest2);


                                                }
                                            });


                                        } else {


                                            try {


                                                jsonObject = new JSONObject(response);
                                                jsonArray = jsonObject.getJSONArray("server_response");

                                                String nameAc, department, room, block, status;
                                                int count = 0;
                                                while (count < jsonArray.length()) {
                                                    JSONObject jo = jsonArray.getJSONObject(count);
                                                    nameAc = jo.getString("name");
                                                    department = jo.getString("department");
                                                    room = jo.getString("room");
                                                    block = jo.getString("block");
                                                    status = jo.getString("status");


                                                    final String finalNameAc = nameAc;
                                                    final String finalNameAc1 = nameAc;
                                                    final String finalStatus = status;


                         if (finalStatus.contains("user")) {

                             StringRequest stringRequest7 = new StringRequest(Request.Method.POST, Constants.ROWS, new Response.Listener<String>() {
                                 @Override
                                 public void onResponse(String response) {


                                     rows2 = response;

                                 }
                             }, new Response.ErrorListener() {
                                 @Override
                                 public void onErrorResponse(VolleyError error) {

                                     if (error instanceof TimeoutError) {
                                         Toast.makeText(getBaseContext(), "Timeout Error", Toast.LENGTH_SHORT).show();
                                     } else if (error instanceof NoConnectionError) {
                                         Toast.makeText(getBaseContext(), "No Connection Error", Toast.LENGTH_SHORT).show();
                                     } else if (error instanceof AuthFailureError) {
                                         Toast.makeText(getBaseContext(), "Authenticatiin Faliure Error", Toast.LENGTH_SHORT).show();
                                     } else if (error instanceof NetworkError) {
                                         Toast.makeText(getBaseContext(), "Network Error", Toast.LENGTH_SHORT).show();
                                     } else if (error instanceof ServerError) {
                                         Toast.makeText(getBaseContext(), "Server Error", Toast.LENGTH_SHORT).show();
                                     } else if (error instanceof ParseError) {
                                         Toast.makeText(getBaseContext(), "JSON Parse Error", Toast.LENGTH_SHORT).show();
                                     }
                                 }
                             }) {
                                 @Override
                                 protected Map<String, String> getParams() throws AuthFailureError {

                                     Map<String, String> data = new HashMap<String, String>();
                                     data.put(Constants.KEY_FROM, nameVer.toString().trim());
                                     data.put(Constants.KEY_STATUS_REPLY, "send");
                                     return data;

                                 }

                                 @Override
                                 public Map<String, String> getHeaders() throws AuthFailureError {
                                     Map<String, String> headers = new HashMap<String, String>();
                                     headers.put("User-Agent", "Notes Board");
                                     return headers;
                                 }
                             };
                             MySingleton.getInstance(getBaseContext()).addToRequestQueue(stringRequest7);


                             final String finalNameAc2 = nameAc;
                             final String finalDepartment = department;
                             final String finalRoom = room;
                             final String finalBlock = block;
                             final String finalStatus1 = status;
                             StringRequest stringRequest6 = new StringRequest(Request.Method.POST, Constants.MY_CHART, new Response.Listener<String>() {
                                 @Override
                                 public void onResponse(String response) {
                                     progressDialog.dismiss();


                                     Toast.makeText(getBaseContext(), response, Toast.LENGTH_LONG).show();
                                     json = response;


                                     if (rows2 == null || response == null) {
                                         progressDialog.dismiss();
                                         AlertDialog.Builder error_dialog = new AlertDialog.Builder(Welcome_Screen.this);
                                         View error_view = getLayoutInflater().inflate(R.layout.network_error, null);
                                         error_dialog.setView(error_view);
                                         AlertDialog dialog_error = error_dialog.create();
                                         dialog_error.show();

                                         Button retry = (Button) error_view.findViewById(R.id.error_retry);
                                         retry.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 Intent retry_launch = new Intent(Welcome_Screen.this, Welcome_Screen.class);
                                                 startActivity(retry_launch);
                                                 finish();
                                             }
                                         });

                                     } else {

                                             progressDialog.dismiss();
                                             Intent launch_chart = new Intent(getBaseContext(), Chart.class);
                                             launch_chart.putExtra("account_name", finalNameAc1.toString().toLowerCase().trim());
                                             launch_chart.putExtra("rows", rows2);
                                             launch_chart.putExtra("json", response);
                                             startActivity(launch_chart);
                                             finish();


                                         editor.putString("name", finalNameAc2.toString().toLowerCase());
                                         editor.putString("username", username.getText().toString().trim());
                                         editor.putString("password", password.getText().toString().trim());
                                         editor.putString("department", finalDepartment.toString().trim());
                                         editor.putString("room", finalRoom.toString().trim());
                                         editor.putString("building", finalBlock.toString().trim());
                                         editor.putString("status", finalStatus1.toString().trim());
                                         editor.putString("login_logout", "login");

                                         Log.d(TAG, sharedPreferences.getString("name", ""));
                                         Log.d(TAG, sharedPreferences.getString("username", ""));
                                         Log.d(TAG, sharedPreferences.getString("password", ""));
                                         Log.d(TAG, sharedPreferences.getString("department", ""));
                                         Log.d(TAG, sharedPreferences.getString("room", ""));
                                         Log.d(TAG, sharedPreferences.getString("building", ""));
                                         Log.d(TAG, sharedPreferences.getString("status", ""));
                                         Log.d(TAG, sharedPreferences.getString("login_logout", ""));

                                         editor.apply();


                                     }


                                 }
                             }, new Response.ErrorListener() {
                                 @Override
                                 public void onErrorResponse(VolleyError error) {
                                     progressDialog.dismiss();
                                     if (error instanceof TimeoutError) {
                                         Toast.makeText(getBaseContext(), "Timeout Error", Toast.LENGTH_SHORT).show();
                                     } else if (error instanceof NoConnectionError) {
                                         Toast.makeText(getBaseContext(), "No Connection Error", Toast.LENGTH_SHORT).show();
                                     } else if (error instanceof AuthFailureError) {
                                         Toast.makeText(getBaseContext(), "Authenticatiin Faliure Error", Toast.LENGTH_SHORT).show();
                                     } else if (error instanceof NetworkError) {
                                         Toast.makeText(getBaseContext(), "Network Error", Toast.LENGTH_SHORT).show();
                                     } else if (error instanceof ServerError) {
                                         Toast.makeText(getBaseContext(), "Server Error", Toast.LENGTH_SHORT).show();
                                     } else if (error instanceof ParseError) {
                                         Toast.makeText(getBaseContext(), "JSON Parse Error", Toast.LENGTH_SHORT).show();
                                     }
                                 }
                             }) {
                                 @Override
                                 protected Map<String, String> getParams() throws AuthFailureError {

                                     Map<String, String> data = new HashMap<String, String>();
                                     data.put(Constants.KEY_FROM, finalNameAc.toString().trim());
                                     data.put(Constants.KEY_STATUS_REPLY, "send");
                                     return data;


                                 }

                                 @Override
                                 public Map<String, String> getHeaders() throws AuthFailureError {
                                     Map<String, String> headers = new HashMap<String, String>();
                                     headers.put("User-Agent", "Notes Board");
                                     return headers;
                                 }
                             };
                             MySingleton.getInstance(getBaseContext()).addToRequestQueue(stringRequest6);

                         }else  if (finalStatus.contains("it_expert")) {


                             StringRequest stringRequest8 = new StringRequest(Request.Method.POST, Constants.ROWS_IT, new Response.Listener<String>() {
                                 @Override
                                 public void onResponse(String response) {


                                     rows2 = response;


                                 }
                             }, new Response.ErrorListener() {
                                 @Override
                                 public void onErrorResponse(VolleyError error) {

                                     if (error instanceof TimeoutError) {
                                         Toast.makeText(getBaseContext(), "Timeout Error", Toast.LENGTH_SHORT).show();
                                     } else if (error instanceof NoConnectionError) {
                                         Toast.makeText(getBaseContext(), "No Connection Error", Toast.LENGTH_SHORT).show();
                                     } else if (error instanceof AuthFailureError) {
                                         Toast.makeText(getBaseContext(), "Authenticatiin Faliure Error", Toast.LENGTH_SHORT).show();
                                     } else if (error instanceof NetworkError) {
                                         Toast.makeText(getBaseContext(), "Network Error", Toast.LENGTH_SHORT).show();
                                     } else if (error instanceof ServerError) {
                                         Toast.makeText(getBaseContext(), "Server Error", Toast.LENGTH_SHORT).show();
                                     } else if (error instanceof ParseError) {
                                         Toast.makeText(getBaseContext(), "JSON Parse Error", Toast.LENGTH_SHORT).show();
                                     }
                                 }
                             }) {
                                 @Override
                                 protected Map<String, String> getParams() throws AuthFailureError {

                                     Map<String, String> data = new HashMap<String, String>();
                                     data.put(Constants.KEY_STATUS_REPLY, "send");
                                     return data;

                                 }

                                 @Override
                                 public Map<String, String> getHeaders() throws AuthFailureError {
                                     Map<String, String> headers = new HashMap<String, String>();
                                     headers.put("User-Agent", "Notes Board");
                                     return headers;
                                 }
                             };
                             MySingleton.getInstance(getBaseContext()).addToRequestQueue(stringRequest8);


                             final String finalNameAc3 = nameAc;
                             final String finalDepartment1 = department;
                             final String finalRoom1 = room;
                             final String finalBlock1 = block;
                             final String finalStatus2 = status;
                             StringRequest stringRequest9 = new StringRequest(Request.Method.POST, Constants.MY_CHART_IT, new Response.Listener<String>() {
                                 @Override
                                 public void onResponse(String response) {


                                     if (rows2 == null || response == null) {
                                         progressDialog.dismiss();
                                         AlertDialog.Builder error_dialog = new AlertDialog.Builder(Welcome_Screen.this);
                                         View error_view = getLayoutInflater().inflate(R.layout.network_error, null);
                                         error_dialog.setView(error_view);
                                         AlertDialog dialog_error = error_dialog.create();
                                         dialog_error.show();

                                         Button retry = (Button) error_view.findViewById(R.id.error_retry);
                                         retry.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 Intent retry_launch = new Intent(Welcome_Screen.this, Welcome_Screen.class);
                                                 startActivity(retry_launch);
                                                 finish();
                                             }
                                         });

                                     } else {

                                         progressDialog.dismiss();
                                         Intent launch_chart = new Intent(getBaseContext(), It_expert.class);
                                         launch_chart.putExtra("account_name", nameVer.toString().toLowerCase().trim());
                                         launch_chart.putExtra("rows", rows2);
                                         launch_chart.putExtra("json", response);
                                         startActivity(launch_chart);
                                         finish();


                                         editor.putString("name", finalNameAc3.toString().toLowerCase());
                                         editor.putString("username", username.getText().toString().trim());
                                         editor.putString("password", password.getText().toString().trim());
                                         editor.putString("department", finalDepartment1.toString().trim());
                                         editor.putString("room", finalRoom1.toString().trim());
                                         editor.putString("building", finalBlock1.toString().trim());
                                         editor.putString("status", finalStatus2.toString().trim());
                                         editor.putString("login_logout", "login");

                                         Log.d(TAG, sharedPreferences.getString("name", ""));
                                         Log.d(TAG, sharedPreferences.getString("username", ""));
                                         Log.d(TAG, sharedPreferences.getString("password", ""));
                                         Log.d(TAG, sharedPreferences.getString("department", ""));
                                         Log.d(TAG, sharedPreferences.getString("room", ""));
                                         Log.d(TAG, sharedPreferences.getString("building", ""));
                                         Log.d(TAG, sharedPreferences.getString("status", ""));
                                         Log.d(TAG, sharedPreferences.getString("login_logout", ""));

                                         editor.apply();


                                     }

                                 }
                             }, new Response.ErrorListener() {
                                 @Override
                                 public void onErrorResponse(VolleyError error) {

                                     if (error instanceof TimeoutError) {
                                         Toast.makeText(getBaseContext(), "Timeout Error", Toast.LENGTH_SHORT).show();
                                     } else if (error instanceof NoConnectionError) {
                                         Toast.makeText(getBaseContext(), "No Connection Error", Toast.LENGTH_SHORT).show();
                                     } else if (error instanceof AuthFailureError) {
                                         Toast.makeText(getBaseContext(), "Authenticatiin Faliure Error", Toast.LENGTH_SHORT).show();
                                     } else if (error instanceof NetworkError) {
                                         Toast.makeText(getBaseContext(), "Network Error", Toast.LENGTH_SHORT).show();
                                     } else if (error instanceof ServerError) {
                                         Toast.makeText(getBaseContext(), "Server Error", Toast.LENGTH_SHORT).show();
                                     } else if (error instanceof ParseError) {
                                         Toast.makeText(getBaseContext(), "JSON Parse Error", Toast.LENGTH_SHORT).show();
                                     }
                                 }
                             }) {
                                 @Override
                                 protected Map<String, String> getParams() throws AuthFailureError {

                                     Map<String, String> data = new HashMap<String, String>();
                                     data.put(Constants.KEY_STATUS_REPLY, "send");
                                     return data;


                                 }

                                 @Override
                                 public Map<String, String> getHeaders() throws AuthFailureError {
                                     Map<String, String> headers = new HashMap<String, String>();
                                     headers.put("User-Agent", "Notes Board");
                                     return headers;
                                 }
                             };
                             MySingleton.getInstance(getBaseContext()).addToRequestQueue(stringRequest9);


                         }else if (finalStatus.contains("admin")) {

                             progressDialog.dismiss();
                             Intent launch_admin = new Intent(Welcome_Screen.this, Admin.class);
                             launch_admin.putExtra("account_name", nameVer.toString().toLowerCase().trim());
                             startActivity(launch_admin);
                             finish();


                             editor.putString("name", nameAc.toString().toLowerCase());
                             editor.putString("username", username.getText().toString().trim());
                             editor.putString("password", password.getText().toString().trim());
                             editor.putString("department", department.toString().trim());
                             editor.putString("room", room.toString().trim());
                             editor.putString("building", block.toString().trim());
                             editor.putString("status", status.toString().trim());
                             editor.putString("login_logout", "logout");

                             Log.d(TAG, sharedPreferences.getString("name", ""));
                             Log.d(TAG, sharedPreferences.getString("username", ""));
                             Log.d(TAG, sharedPreferences.getString("password", ""));
                             Log.d(TAG, sharedPreferences.getString("department", ""));
                             Log.d(TAG, sharedPreferences.getString("room", ""));
                             Log.d(TAG, sharedPreferences.getString("building", ""));
                             Log.d(TAG, sharedPreferences.getString("status", ""));
                             Log.d(TAG, sharedPreferences.getString("login_logout", ""));

                             editor.apply();


                         }
//



                                                    count++;
                                                }


                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        progressDialog.dismiss();
                                        if (error instanceof TimeoutError) {
                                            Toast.makeText(Welcome_Screen.this, "Timeout Error", Toast.LENGTH_SHORT).show();
                                        } else if (error instanceof NoConnectionError) {
                                            Toast.makeText(Welcome_Screen.this, "No Connection Error", Toast.LENGTH_SHORT).show();
                                        } else if (error instanceof AuthFailureError) {
                                            Toast.makeText(Welcome_Screen.this, "Authenticatiin Faliure Error", Toast.LENGTH_SHORT).show();
                                        } else if (error instanceof NetworkError) {
                                            Toast.makeText(Welcome_Screen.this, "Network Error", Toast.LENGTH_SHORT).show();
                                        } else if (error instanceof ServerError) {
                                            Toast.makeText(Welcome_Screen.this, "Server Error", Toast.LENGTH_SHORT).show();
                                        } else if (error instanceof ParseError) {
                                            Toast.makeText(Welcome_Screen.this, "JSON Parse Error", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }) {
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        Map<String, String> data = new HashMap<String, String>();
                                        data.put(Constants.KEY_REG_NUMBER, username.getText().toString().trim());
                                        data.put(Constants.KEY_PASS, password.getText().toString().trim());
                                        return data;
                                    }

                                    @Override
                                    public Map<String, String> getHeaders() throws AuthFailureError {
                                        Map<String, String> headers = new HashMap<String, String>();
                                        headers.put("User-Agent", "Notes Board");
                                        return headers;
                                    }
                                };
                                MySingleton.getInstance(Welcome_Screen.this).addToRequestQueue(stringRequest);


                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(Welcome_Screen.this, "Fill all fields", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

//                    } else {
//                        Intent launch_sign_up = new Intent(getBaseContext(), Authentication.class);
//                        startActivity(launch_sign_up);
//                        finish();
//                    }

                } else if (login_logout.equals("login")) {

                          if (status_ver.contains("user")) {



                        StringRequest stringRequest7 = new StringRequest(Request.Method.POST, Constants.ROWS, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {


                                rows2 = response;

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                if (error instanceof TimeoutError) {
                                    Toast.makeText(getBaseContext(), "Timeout Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof NoConnectionError) {
                                    Toast.makeText(getBaseContext(), "No Connection Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof AuthFailureError) {
                                    Toast.makeText(getBaseContext(), "Authenticatiin Faliure Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof NetworkError) {
                                    Toast.makeText(getBaseContext(), "Network Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof ServerError) {
                                    Toast.makeText(getBaseContext(), "Server Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof ParseError) {
                                    Toast.makeText(getBaseContext(), "JSON Parse Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {

                                Map<String, String> data = new HashMap<String, String>();
                                data.put(Constants.KEY_FROM, nameVer.toString().trim());
                                data.put(Constants.KEY_STATUS_REPLY, "send");
                                return data;

                            }

                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                                Map<String, String> headers = new HashMap<String, String>();
                                headers.put("User-Agent", "Notes Board");
                                return headers;
                            }
                        };
                        MySingleton.getInstance(getBaseContext()).addToRequestQueue(stringRequest7);



                        StringRequest stringRequest6 = new StringRequest(Request.Method.POST, Constants.MY_CHART, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {


                                if (rows2 == null || response == null) {
                                    progressDialog.dismiss();
                                    AlertDialog.Builder error_dialog = new AlertDialog.Builder(Welcome_Screen.this);
                                    View error_view = getLayoutInflater().inflate(R.layout.network_error, null);
                                    error_dialog.setView(error_view);
                                    AlertDialog dialog_error = error_dialog.create();
                                    dialog_error.show();

                                    Button retry = (Button) error_view.findViewById(R.id.error_retry);
                                    retry.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent retry_launch = new Intent(Welcome_Screen.this, Welcome_Screen.class);
                                            startActivity(retry_launch);
                                            finish();
                                        }
                                    });

                                } else {

                                    progressDialog.dismiss();
                                    Intent launch_chart = new Intent(getBaseContext(), Chart.class);
                                    launch_chart.putExtra("account_name", nameVer.toString().toLowerCase().trim());
                                    launch_chart.putExtra("rows", rows2);
                                    launch_chart.putExtra("json", response);
                                    startActivity(launch_chart);
                                    finish();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                if (error instanceof TimeoutError) {
                                    Toast.makeText(getBaseContext(), "Timeout Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof NoConnectionError) {
                                    Toast.makeText(getBaseContext(), "No Connection Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof AuthFailureError) {
                                    Toast.makeText(getBaseContext(), "Authenticatiin Faliure Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof NetworkError) {
                                    Toast.makeText(getBaseContext(), "Network Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof ServerError) {
                                    Toast.makeText(getBaseContext(), "Server Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof ParseError) {
                                    Toast.makeText(getBaseContext(), "JSON Parse Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {

                                Map<String, String> data = new HashMap<String, String>();
                                data.put(Constants.KEY_FROM, nameVer.toString().trim());
                                data.put(Constants.KEY_STATUS_REPLY, "send");
                                return data;


                            }

                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                                Map<String, String> headers = new HashMap<String, String>();
                                headers.put("User-Agent", "Notes Board");
                                return headers;
                            }
                        };
                        MySingleton.getInstance(getBaseContext()).addToRequestQueue(stringRequest6);

//                       } else if (status_ver.contains("admin")) {
//
//                              Intent launch_admin = new Intent(Welcome_Screen.this, Admin.class);
//                              launch_admin.putExtra("account_name", nameVer.toString().toLowerCase().trim());
//                              startActivity(launch_admin);
//                              finish();

                    }else if(status_ver.contains("it_expert")){

                        StringRequest stringRequest7 = new StringRequest(Request.Method.POST, Constants.ROWS_IT, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {


                                rows2 = response;

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                if (error instanceof TimeoutError) {
                                    Toast.makeText(getBaseContext(), "Timeout Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof NoConnectionError) {
                                    Toast.makeText(getBaseContext(), "No Connection Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof AuthFailureError) {
                                    Toast.makeText(getBaseContext(), "Authenticatiin Faliure Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof NetworkError) {
                                    Toast.makeText(getBaseContext(), "Network Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof ServerError) {
                                    Toast.makeText(getBaseContext(), "Server Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof ParseError) {
                                    Toast.makeText(getBaseContext(), "JSON Parse Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {

                                Map<String, String> data = new HashMap<String, String>();
                                data.put(Constants.KEY_STATUS_REPLY, "send");
                                return data;

                            }

                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                                Map<String, String> headers = new HashMap<String, String>();
                                headers.put("User-Agent", "Notes Board");
                                return headers;
                            }
                        };
                        MySingleton.getInstance(getBaseContext()).addToRequestQueue(stringRequest7);



                        StringRequest stringRequest6 = new StringRequest(Request.Method.POST, Constants.MY_CHART_IT, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {


                                if (rows2 == null || response == null) {
                                    progressDialog.dismiss();
                                    AlertDialog.Builder error_dialog = new AlertDialog.Builder(Welcome_Screen.this);
                                    View error_view = getLayoutInflater().inflate(R.layout.network_error, null);
                                    error_dialog.setView(error_view);
                                    AlertDialog dialog_error = error_dialog.create();
                                    dialog_error.show();

                                    Button retry = (Button) error_view.findViewById(R.id.error_retry);
                                    retry.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent retry_launch = new Intent(Welcome_Screen.this, Welcome_Screen.class);
                                            startActivity(retry_launch);
                                            finish();
                                        }
                                    });

                                } else {
                                    progressDialog.dismiss();
                                    Intent launch_chart = new Intent(getBaseContext(), It_expert.class);
                                    launch_chart.putExtra("account_name", nameVer.toString().toLowerCase().trim());
                                    launch_chart.putExtra("rows", rows2);
                                    launch_chart.putExtra("json", response);
                                    startActivity(launch_chart);
                                    finish();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                if (error instanceof TimeoutError) {
                                    Toast.makeText(getBaseContext(), "Timeout Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof NoConnectionError) {
                                    Toast.makeText(getBaseContext(), "No Connection Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof AuthFailureError) {
                                    Toast.makeText(getBaseContext(), "Authenticatiin Faliure Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof NetworkError) {
                                    Toast.makeText(getBaseContext(), "Network Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof ServerError) {
                                    Toast.makeText(getBaseContext(), "Server Error", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof ParseError) {
                                    Toast.makeText(getBaseContext(), "JSON Parse Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {

                                Map<String, String> data = new HashMap<String, String>();
                                data.put(Constants.KEY_STATUS_REPLY, "send");
                                return data;


                            }

                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                                Map<String, String> headers = new HashMap<String, String>();
                                headers.put("User-Agent", "Notes Board");
                                return headers;
                            }
                        };
                        MySingleton.getInstance(getBaseContext()).addToRequestQueue(stringRequest6);




                    }

                    editor.putString("login_logout", "login");
                    editor.apply();
                    Log.d(TAG, sharedPreferences.getString("login_logout", ""));

                } else {
                    Intent launch_sign_up = new Intent(Welcome_Screen.this, Authentication.class);
                    startActivity(launch_sign_up);
                    finish();
                }


            }

        },TIME_OUT);


    }
}
