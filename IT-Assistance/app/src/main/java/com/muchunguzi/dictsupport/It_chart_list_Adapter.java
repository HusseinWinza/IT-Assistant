package com.muchunguzi.dictsupport;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


class It_chart_list_Adapter extends ArrayAdapter<String> {
     Context context;
    String message2[];
    String from2[];
    String itN[];
    String date[];
    String time[];


    public It_chart_list_Adapter(Context context, String[] message1, String[] from1) {
        super(context, R.layout.it_chart_custom_view, message1);
        this.context = context;
        this.message2 = message1;
        this.from2 = from1;
        this.date = date;
        this.time = time;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater generalAnnouncementInflater = LayoutInflater.from(getContext());
        View view = generalAnnouncementInflater.inflate(R.layout.it_chart_custom_view,parent, false);



        String chart = getItem(position);
        TextView it_chart = (TextView) view.findViewById(R.id.it_text2);
        TextView date1 = (TextView) view.findViewById(R.id.date2);
        TextView time1 = (TextView) view.findViewById(R.id.time2);
        TextView fromT = (TextView) view.findViewById(R.id.uer_from_name);


        it_chart.setText(message2[position]);
//        date1.setText(date[position]+":");
//        time1.setText(time[position]);
        fromT.setText(from2[position]);



//                  check.setVisibility(View.VISIBLE);
//                  img.setBackgroundResource(R.drawable.ic_person_black_24dp);



        return   view;
    }


}
