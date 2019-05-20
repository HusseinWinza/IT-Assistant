package com.muchunguzi.dictsupport;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
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


class Chart_list_Adapter extends ArrayAdapter<String> {
     Context context;
    String message[];
    String from[];
    String itN[];
    String date[];
    String time[];
    String id_message2[];


    public Chart_list_Adapter(Chart context, String[] message1, String[] from1, String[] id_message1) {
        super(context, R.layout.chart_custom_view, message1);
        this.context = context;
        this.message = message1;
        this.from = from1;
        this.id_message2 = id_message1;
//        this.itN = it_name;
        this.date = date;
        this.time = time;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater generalAnnouncementInflater = LayoutInflater.from(getContext());
        View view = generalAnnouncementInflater.inflate(R.layout.chart_custom_view,parent, false);



        String chart = getItem(position);
        final TextView messageC = (TextView) view.findViewById(R.id.it_text);
        TextView date1 = (TextView) view.findViewById(R.id.date1);
        TextView time1 = (TextView) view.findViewById(R.id.time1);
        final TextView reply_toss = (TextView) view.findViewById(R.id.reply_toss);


        messageC.setText(message[position]);
        date1.setText("date:");
        time1.setText("time");




        StringRequest stringRequest7 = new StringRequest(Request.Method.POST, Constants.REPLIED_ROWS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.contains("zing")){
                    reply_toss.setText("0");
                }else{
                    reply_toss.setText(String.valueOf(response.trim()));
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof TimeoutError) {
                    Toast.makeText(getContext(), "Timeout Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    Toast.makeText(getContext(), "No Connection Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(getContext(), "Authenticatiin Faliure Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(getContext(), "Server Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(getContext(), "JSON Parse Error", Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> data = new HashMap<String, String>();
                data.put(Constants.KEY_ID_MASSAGE, String.valueOf(id_message2[position]));
                return data;

            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("User-Agent", "Notes Board");
                return headers;
            }
        };
        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest7);



//                  check.setVisibility(View.VISIBLE);
//                  img.setBackgroundResource(R.drawable.ic_person_black_24dp);



        return   view;
    }


}
