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

public class It_expert extends AppCompatActivity {

    final String TAG = this.getClass().getName();
    String account_name;
    ProgressDialog progressDialog;
    JSONObject jsonObject;
    JSONArray jsonArray;
    String rows;
    String json;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.it_expert);

        progressDialog = new ProgressDialog(It_expert.this);

        account_name = getIntent().getExtras().getString("account_name");

        json = getIntent().getExtras().getString("json").toString();
        rows = getIntent().getExtras().getString("rows").toString().trim();

        TextView account_name1 = (TextView) findViewById(R.id.it_account_name);
        account_name1.setText(account_name.toString().toLowerCase().trim());


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
        final String[] it_name ={"julio", "zakar", "muchu", "julio", "zakar", "muchu", "julio", "zakar", "muchu", "julio", "zakar", "muchu"};
        final String[] date = {"Friday 2", "Friday 2", "Fridady 3", "Friday 2", "Friday 2", "Fridady 3", "Friday 2", "Friday 2", "Fridady 3", "Friday 2", "Friday 2", "Fridady 3"};
        final String[] time = {"2:00", "2:03", "3:5", "2:00", "2:03", "3:5", "2:00", "2:03", "3:5", "2:00", "2:03", "3:5"};





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
                from = jo.getString("from1");
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


        ListView chart_list = (ListView) findViewById(R.id.it_chart_list);

        ListAdapter chart_lsit_Adapter = new It_chart_list_Adapter(this, message1, from1);
        chart_list.setAdapter(chart_lsit_Adapter);
        chart_list.setSelection(chart_lsit_Adapter.getCount()-1);





//
//        ListView chart_list = (ListView) findViewById(R.id.it_chart_list);
//
//        ListAdapter chart_lsit_Adapter = new It_chart_list_Adapter(this, it_chart, user_chart, it_name);
//        chart_list.setAdapter(chart_lsit_Adapter);

        chart_list.setOnItemClickListener(

                new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView <?> parent, View view, final int position, long l) {
//

//                        String title = String.valueOf(parent.getItemAtPosition(position));
//                        String data_position = String.valueOf(position);
//                        String content = String.valueOf(small[Integer.parseInt(data_position)]);


                                final AlertDialog.Builder reply_dialog = new AlertDialog.Builder(It_expert.this);
                                final View it_reply_view = getLayoutInflater().inflate(R.layout.it_reply_contents, null);
                                reply_dialog.setView(it_reply_view);
                                final AlertDialog dialog_mendez = reply_dialog.create();
                                dialog_mendez.show();

                                final EditText content = (EditText) it_reply_view.findViewById(R.id.editText_to);
                                final TextView to_text = (TextView) it_reply_view.findViewById(R.id.it_to);
                                to_text.setText(String.valueOf(from1[Integer.parseInt(String.valueOf(position))]));

                                Button reply_btn = (Button) it_reply_view.findViewById(R.id.it_change_btn);
                                Button close_btn = (Button) it_reply_view.findViewById(R.id.it_close_change_btn);
                                close_btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog_mendez.dismiss();

                                    }
                                });

                                reply_btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        progressDialog.setTitle("Sending");
                                        progressDialog.setMessage("Plesae wait .....");
                                        progressDialog.show();



                                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.REPLY_SMS, new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                   progressDialog.dismiss();
                                                Toast.makeText(It_expert.this, response, Toast.LENGTH_LONG).show();


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

                                                data.put(Constants.KEY_TO,  String.valueOf(from1[Integer.parseInt(String.valueOf(position))]));
                                                data.put(Constants.KEY_FROM, account_name.toString().trim());
                                                data.put(Constants.KEY_MESSAGE, content.getText().toString());
                                                data.put(Constants.KEY_STATUS_REPLY,  "reply");
                                                data.put(Constants.KEY_ID_MASSAGE,  String.valueOf(id_message1[Integer.parseInt(String.valueOf(position))]));
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



                    }
                }
        );

        final Spinner more_option = (Spinner) findViewById(R.id.it_spiner_more);
        ArrayAdapter<CharSequence> spinerAdapter = ArrayAdapter.createFromResource(this, R.array.home_more, android.R.layout.simple_spinner_item);
        more_option.setAdapter(spinerAdapter);
        more_option.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String res = adapterView.getSelectedItem().toString();
                if(res.contains("passwords")){

                    AlertDialog.Builder change_dialog = new AlertDialog.Builder(It_expert.this);
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
                                    Toast.makeText(It_expert.this, "Password did not match", Toast.LENGTH_LONG).show();

                                }
                            }else{
                                Toast.makeText(It_expert.this, "Fill all fields", Toast.LENGTH_SHORT).show();

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

                    Intent launch_welcome = new Intent(It_expert.this, Welcome_Screen.class);
                    startActivity(launch_welcome);
                    finish();


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
