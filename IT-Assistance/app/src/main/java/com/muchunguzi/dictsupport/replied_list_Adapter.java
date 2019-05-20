package com.muchunguzi.dictsupport;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


class replied_list_Adapter extends ArrayAdapter<String> {
     Context context;
    String message[];
    String from[];
    String itN[];
    String date[];
    String time[];


    public replied_list_Adapter(replied context, String[] message1, String[] from1) {
        super(context, R.layout.it_chart_custom_view, message1);
        this.context = context;
        this.message = message1;
        this.from = from1;
//        this.itN = it_name;
        this.date = date;
        this.time = time;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater generalAnnouncementInflater = LayoutInflater.from(getContext());
        View view = generalAnnouncementInflater.inflate(R.layout.it_chart_custom_view,parent, false);



        String chart = getItem(position);
        TextView messageC = (TextView) view.findViewById(R.id.it_text2);
        TextView from22 = (TextView) view.findViewById(R.id.uer_from_name);


        messageC.setText(message[position]);
        from22.setText(from[position]);




//                  check.setVisibility(View.VISIBLE);
//                  img.setBackgroundResource(R.drawable.ic_person_black_24dp);



        return   view;
    }


}
