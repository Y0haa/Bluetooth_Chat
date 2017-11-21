package com.example.admin.bluetooth_chat.presenter;

/**
 * Created by Admin on 11/21/2017.
 */

public class cMessage {

    int Me; // use binary to differentiate between sender (Me, 1) and receiver (Other, 0)
    String Msg; // the msgs
    long Recu; // Time sent
    String Address; // target address

    public cMessage (long recu, String msg, int me, String address){
        Recu = recu;
        Msg = msg;
        Me = me;
        Address = address;
    }

    public int getMe() {
        return Me;
    }

    public void setMe(int me) {
        Me = me;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public long getRecu() {
        return Recu;
    }

    public void setRecu(long recu) {
        Recu = recu;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
