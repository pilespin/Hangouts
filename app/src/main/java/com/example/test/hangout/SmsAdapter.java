package com.example.test.hangout;

import android.content.Context;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.text.format.DateUtils.FORMAT_ABBREV_RELATIVE;

/**
 * Created by pilespin on 2/23/17.
 */

public class SmsAdapter extends ArrayAdapter<String> {

    private final Context           context;
    private final ArrayList<String> values;
    private final List<sms>         allSms;

    public SmsAdapter(Context context, ArrayList<String> values, List<sms> allSms) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
        this.allSms = new ArrayList<sms>(allSms);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.sms_listview_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.text1);

        sms s = allSms.get(position);
        if (s.getDirection().compareTo("IN") == 0)
        {
            textView.setGravity(Gravity.LEFT);
            textView.setBackgroundResource(R.color.colorSmsIn);
            textView.setText(s.getContent() + "\n" + lib.getSmsTime(s.getTime()));
        }
        else
        {
            textView.setGravity(Gravity.RIGHT);
            textView.setBackgroundResource(R.color.colorSmsOut);
            textView.setText(s.getContent() + "\n" + lib.getSmsTime(s.getTime()));
        }
        return (rowView);
    }
}
