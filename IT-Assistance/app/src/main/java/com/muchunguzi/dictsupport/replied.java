package com.muchunguzi.dictsupport;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
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

public class replied extends AppCompatActivity {

    JSONObject jsonObject;
    JSONArray jsonArray;
    String rows;
    String json;
    String rows2;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replied);

        progressDialog = new ProgressDialog(replied.this);

        final SharedPreferences sharedPreferences = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final String nameVer = sharedPreferences.getString("name", "");

        rows = getIntent().getExtras().getString("rows").toString().trim();
        json = getIntent().getExtras().getString("json").toString();


        String[] message1 = new String[Integer.parseInt(String.valueOf(rows))];
        final String[] from1 = new String[Integer.parseInt(String.valueOf(rows))];


        try {


            jsonObject = new JSONObject(json);
            jsonArray = jsonObject.getJSONArray("server_response");


            String id, from, message, id_message;
            int count = 0;
            while (count < jsonArray.length()) {
                JSONObject jo = jsonArray.getJSONObject(count);
                from = jo.getString("from1");
                message = jo.getString("message");

                from1[count] = from;
                message1[count] = message;


                count++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        ListView chart_list = (ListView) findViewById(R.id.chart_chart);

        ListAdapter chart_lsit_Adapter2 = new replied_list_Adapter(this, message1, from1);
        chart_list.setAdapter(chart_lsit_Adapter2);

        chart_list.setSelection(chart_lsit_Adapter2.getCount()-1);


        Button return_chart = (Button) findViewById(R.id.return_chart);
        return_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.setMessage("Plese wait .....");
                progressDialog.show();

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
                            AlertDialog.Builder error_dialog = new AlertDialog.Builder(replied.this);
                            View error_view = getLayoutInflater().inflate(R.layout.network_error, null);
                            error_dialog.setView(error_view);
                            AlertDialog dialog_error = error_dialog.create();
                            dialog_error.show();

                            Button retry = (Button) error_view.findViewById(R.id.error_retry);
                            retry.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent retry_launch = new Intent(replied.this, Welcome_Screen.class);
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




            }
        });



    }
}
