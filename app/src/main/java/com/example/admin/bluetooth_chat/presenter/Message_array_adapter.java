package com.example.admin.bluetooth_chat.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.bluetooth_chat.R;
import com.example.admin.bluetooth_chat.presenter.cMessage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 11/21/2017.
 */

public class Message_array_adapter extends ArrayAdapter<cMessage> {

    private LayoutInflater layoutInflater;
    private List<cMessage> Messages;Context ctx;

    //call constructor on creation
    public Message_array_adapter(Context context, int resource, ArrayList<cMessage> objects) {
        super(context, resource, objects);
        ctx= context;
        layoutInflater =LayoutInflater.from(context);
        this.Messages = objects;
    }


    public View getView(int position, View convertView, ViewGroup parent){
        cMessage Message = Messages.get(position); // Get Object Message
        TextView msg,tm; // message and time
        if(Message.getMe()==1) { convertView = layoutInflater.inflate(R.layout.mymessage,null);}
        else{convertView = layoutInflater.inflate(R.layout.othermessage, null);} //set the correct layout
        msg = (TextView) convertView.findViewById(R.id.message);
        msg.setText(Message.getMsg()); //Get Message
        tm=(TextView) convertView.findViewById(R.id.tm);
        String formattedDate = new SimpleDateFormat("HH:mm").format(new Date(Message.getRecu())); //Get Time
        tm.setText(formattedDate);
        return convertView;
    }
}
