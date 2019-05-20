package com.muchunguzi.dictsupport;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
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

public class Chart extends AppCompatActivity {
    String account_name;
    final String TAG = this.getClass().getName();
    ProgressDialog progressDialog;

    JSONObject jsonObject;
    JSONArray jsonArray;
    String json;
    String rows;
    String rows22;
    String json22;
    String position2;
    String rowsxx;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart);


        rows = getIntent().getExtras().getString("rows").toString().trim();

        json = getIntent().getExtras().getString("json").toString();
        account_name = getIntent().getExtras().getString("account_name");
        progressDialog = new ProgressDialog(Chart.this);

//        Toast.makeText(this, json, Toast.LENGTH_LONG).show();

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

        //Text view that display the user mame
        TextView account_name1 = (TextView) findViewById(R.id.account_name);
        account_name1.setText(account_name.toString().toLowerCase().trim());


        final String[] it_chart = {"hey how u doing",
                "i think you need help on that",
                "and by now you need to follow my instructions", "hey how u doing",
                "i think you need help on that",
                "and by now you need to follow my instructions", "hey how u doing",
                "i think you need help on that",
                "and by now you need to follow my instructions", "hey how u doing",
                "i think you need help on that",
                "and by now you need to follow my instructions"};
        final String[] user_chart = {"hey",
                "i have a problem i need help",
                "it is about my smart phone it keeps on stucking on home buttona" +
                        "and i dont have anny idea what can i do",
                "hey",
                "i have a problem i need help",
                "it is about my smart phone it keeps on stucking on home buttona" +
                        "and i dont have anny idea what can i do", "hey",
                "i have a problem i need help",
                "it is about my smart phone it keeps on stucking on home buttona" +
                        "and i dont have anny idea what can i do",
                "hey",
                "i have a problem i need help",
                "it is about my smart phone it keeps on stucking on home buttona" +
                        "and i dont have anny idea what can i do"};
        final String[] it_name = {"julio", "zakar", "muchu", "julio", "zakar", "muchu", "julio", "zakar", "muchu", "julio", "zakar", "muchu"};
        final String[] date = {"Friday 2", "Friday 2", "Fridady 3", "Friday 2", "Friday 2", "Fridady 3", "Friday 2", "Friday 2", "Fridady 3", "Friday 2", "Friday 2", "Fridady 3"};
        final String[] time = {"2:00", "2:03", "3:5", "2:00", "2:03", "3:5", "2:00", "2:03", "3:5", "2:00", "2:03", "3:5"};

        final Button refresh = (Button) findViewById(R.id.refreshing);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                progressDialog.setMessage("Refreshing .....");
                progressDialog.show();

                StringRequest stringRequest7 = new StringRequest(Request.Method.POST, Constants.ROWS, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        rowsxx = response;

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



                final StringRequest stringRequest6 = new StringRequest(Request.Method.POST, Constants.MY_CHART, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        if (rowsxx == null || response == null) {
                            progressDialog.dismiss();
                            AlertDialog.Builder error_dialog = new AlertDialog.Builder(Chart.this);
                            View error_view = getLayoutInflater().inflate(R.layout.network_error2, null);
                            error_dialog.setView(error_view);
                            AlertDialog dialog_error = error_dialog.create();
                            dialog_error.show();

                        } else {

                            progressDialog.dismiss();
                            Intent launch_chart = new Intent(getBaseContext(), Chart.class);
                            launch_chart.putExtra("account_name", nameVer.toString().toLowerCase().trim());
                            launch_chart.putExtra("rows", rowsxx);
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









        String[] id1 = new String[Integer.parseInt(String.valueOf(rows))];
        String[] message1 = new String[Integer.parseInt(String.valueOf(rows))];
        final String[] from1 = new String[Integer.parseInt(String.valueOf(rows))];
        final String[] id_message1 = new String[Integer.parseInt(String.valueOf(rows))];


        try {


            jsonObject = new JSONObject(json);
            jsonArray = jsonObject.getJSONArray("server_response");


            String id, from, message;
            int count = 0;
            while (count < jsonArray.length()) {
                JSONObject jo = jsonArray.getJSONObject(count);
                id = jo.getString("id");
                from = jo.getString("to");
                message = jo.getString("message");
                id = jo.getString("id");

                id1[count] = id;
                message1[count] = message;
                from1[count] = from;
                id_message1[count] = id;


                count++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        ListView chart_list = (ListView) findViewById(R.id.chart_list);

        ListAdapter chart_lsit_Adapter = new Chart_list_Adapter(this, message1, from1, id_message1);
        chart_list.setAdapter(chart_lsit_Adapter);
        chart_list.setSelection(chart_lsit_Adapter.getCount()-1);

        chart_list.setOnItemClickListener(

                new AdapterView.OnItemClickListener() {



                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long l) {

                        progressDialog.setMessage("Please wait ......");
                        progressDialog.show();
//
                        String data_position = String.valueOf(position);
                        position2 = String.valueOf(id_message1[Integer.parseInt(data_position)]);

//                        final AlertDialog.Builder gen_dilaog = new AlertDialog.Builder(Chart.this);
//                        final View gen_view = getLayoutInflater().inflate(R.layout.reply_contents, null);
//                        gen_dilaog.setView(gen_view);
//                        final AlertDialog dialog = gen_dilaog.create();
//                        dialog.show();




                        StringRequest stringRequest7 = new StringRequest(Request.Method.POST, Constants.REPLIED_ROWS, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                rows22 = response;



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
                                data.put(Constants.KEY_TO, account_name);
                                data.put(Constants.KEY_ID_MASSAGE, position2);
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



                        StringRequest stringRequest6 = new StringRequest(Request.Method.POST, Constants.REPLIED_MY_CHART, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {


                                if (rows22 == null || response == null) {
                                    progressDialog.dismiss();
                                    AlertDialog.Builder error_dialog = new AlertDialog.Builder(Chart.this);
                                    View error_view = getLayoutInflater().inflate(R.layout.network_error2, null);
                                    error_dialog.setView(error_view);
                                    AlertDialog dialog_error = error_dialog.create();
                                    dialog_error.show();

                                } else {

                                          if(rows22.contains("zing")) {

                                              Toast.makeText(Chart.this, "No Replies", Toast.LENGTH_SHORT).show();
                                          }else {


                                    progressDialog.dismiss();
                                    Intent launch_chart = new Intent(getBaseContext(), replied.class);
                                    launch_chart.putExtra("account_name", nameVer.toString().toLowerCase().trim());
                                    launch_chart.putExtra("rows", rows22);
                                    launch_chart.putExtra("json", response);
                                    startActivity(launch_chart);
                                    finish();
                                          }

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
                                data.put(Constants.KEY_TO, account_name);
                                data.put(Constants.KEY_ID_MASSAGE, position2);
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
























//
//                        Button close_btn_content = (Button) gen_view.findViewById(R.id.close_btn_content);
//                        close_btn_content.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                dialog.dismiss();
//                            }
//                        });

                    }
                }
        );


//
//
        final Spinner more_option = (Spinner) findViewById(R.id.spiner_more);
        ArrayAdapter<CharSequence> spinerAdapter = ArrayAdapter.createFromResource(this, R.array.home_more, android.R.layout.simple_spinner_item);
        more_option.setAdapter(spinerAdapter);
        more_option.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String res = adapterView.getSelectedItem().toString();
                if(res.contains("passwords")){

                    AlertDialog.Builder change_dialog = new AlertDialog.Builder(Chart.this);
                    View Change_view = getLayoutInflater().inflate(R.layout.change_pass, null);
                    change_dialog.setView(Change_view);
                    final AlertDialog dialogC = change_dialog.create();
                    dialogC.show();

                   final TextView err_change_disp = (TextView) Change_view.findViewById(R.id.err_change_display);
                   final EditText current_pass = (EditText) Change_view.findViewById(R.id.current_pass);
                   final EditText new_username = (EditText) Change_view.findViewById(R.id.new_username1);
                   final EditText new_pass = (EditText) Change_view.findViewById(R.id.new_pass);
                   final EditText comfirm_pass = (EditText) Change_view.findViewById(R.id.pass_comfirm1);
                   final Button change = (Button) Change_view.findViewById(R.id.change_btn);
                    Button close_change_btn = (Button) Change_view.findViewById(R.id.close_change_btn);
                    change.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            err_change_disp.setVisibility(View.INVISIBLE);


                            if(!(current_pass.getText().toString().isEmpty() || new_pass.getText().toString().isEmpty()
                            || new_username.getText().toString().isEmpty() || comfirm_pass.getText().toString().isEmpty())){

                                if(new_pass.getText().toString().equals(comfirm_pass.getText().toString())){

                                    if(current_pass.getText().toString().equals(passwordVer)){

                                        editor.putString("username", new_username.getText().toString().trim());
                                        editor.putString("password", new_pass.getText().toString().trim());

                                        editor.apply();
                                        err_change_disp.setVisibility(View.VISIBLE);
                                        err_change_disp.setTextColor(Color.parseColor("#0e970c"));
                                        err_change_disp.setText("Password changed successful");

                                        Log.d(TAG, sharedPreferences.getString("username", ""));
                                        Log.d(TAG, sharedPreferences.getString("password", ""));
                                    }else{
                                        err_change_disp.setVisibility(View.VISIBLE);
                                       err_change_disp.setText("Password change failed");
                                    }
                                }else{
                                    Toast.makeText(Chart.this, "Password did not match", Toast.LENGTH_LONG).show();

                                }
                            }else{
                                Toast.makeText(Chart.this, "Fill all fields", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });

                    close_change_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialogC.dismiss();
                        }
                    });


                }else if(res.contains("Logout")){


                    final SharedPreferences sharedPreferences = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
                    final SharedPreferences.Editor editor = sharedPreferences.edit();


                    editor.putString("login_logout", "logout");

                    editor.apply();

                    Log.d(TAG, sharedPreferences.getString("login_logout", ""));

                    Intent launch_welcome = new Intent(Chart.this, Welcome_Screen.class);
                    startActivity(launch_welcome);
                    finish();


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        final Button ask = (Button) findViewById(R.id.ask_btn_chart);
        ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder ask_dialog = new AlertDialog.Builder(Chart.this);
                View ask_view = getLayoutInflater().inflate(R.layout.asking, null);
                ask_dialog.setView(ask_view);
                final AlertDialog dialogx = ask_dialog.create();
                dialogx.show();

                final EditText editText_ask = (EditText) ask_view.findViewById(R.id.editText_ask);
                Button close_ask = (Button) ask_view.findViewById(R.id.close_ask_btn);
                Button ask_btn = (Button) ask_view.findViewById(R.id.ask_btn);

                ask_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        progressDialog.setTitle("Sending");
                        progressDialog.setMessage("please wait ........");
                        progressDialog.show();

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.REPLY_SMS, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();

                                if(response.contains("sent")){
                                    Toast.makeText(Chart.this, "Sent", Toast.LENGTH_SHORT).show();
                                    dialogx.dismiss();
                                }else{
                                    Toast.makeText(Chart.this, "Failed", Toast.LENGTH_SHORT).show();
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

                                data.put(Constants.KEY_TO,  "it");
                                data.put(Constants.KEY_FROM, account_name.toString().trim());
                                data.put(Constants.KEY_MESSAGE, editText_ask.getText().toString());
                                data.put(Constants.KEY_STATUS_REPLY,  "send");
                                data.put(Constants.KEY_ID_MASSAGE,  "null");
                                return data;


                            }

                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                                Map<String, String> headers = new HashMap<String, String>();
                                headers.put("User-Agent", "Notes Board");
                                return headers;
                            }
                        };
                        MySingleton.getInstance(getBaseContext()).addToRequestQueue(stringRequest);



                    }
                });

                close_ask.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogx.dismiss();
                    }
                });

            }
        });


    }
}
