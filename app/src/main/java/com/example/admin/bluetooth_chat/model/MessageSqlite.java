package com.example.admin.bluetooth_chat.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.admin.bluetooth_chat.presenter.cMessage;

import java.util.ArrayList;

/**
 * Created by Admin on 11/21/2017.
 */

public class MessageSqlite extends SQLiteOpenHelper {

    private static final int DATABSE_VERSION = 2;
    public static final  String DATABASE_NAME = "MessageDB",
        TABLE_Messages = "Messages", Me_Tag="Me",Msg_Tag="Msg", Recu_Tag="Recu", Address_Tag="Address",
        CREATE_Messages_TABLE = "CREATE TABLE" + TABLE_Messages + " ( " +
                Recu_Tag+"INTEGER PRIMARY KEY,"+ Msg_Tag+" TEXT, " +Me_Tag+"INTEGER," + Address_Tag+" TEXT)";
    private Context c;

    public MessageSqlite(Context context) {
        super(context, DATABASE_NAME,null, DATABSE_VERSION); // query for creating the Messages DATABASE for the First Time.
        c=context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_Messages_TABLE);//query for creating the Messages table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+ TABLE_Messages);
        this.onCreate(db);
    }

    public void addMessage(cMessage Message){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Recu_Tag, Message.getRecu());
        values.put(Msg_Tag, Message.getMsg());
        values.put(Me_Tag,Message.getMe());
        values.put(Address_Tag,Message.getAddress());
        db.insert(TABLE_Messages,null,values);
        db.close();
    }

    public void updateMessage(cMessage Message){
        long date=Message.getRecu();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Recu_Tag, Message.getRecu());
        values.put(Msg_Tag, Message.getMsg());
        values.put(Me_Tag,Message.getMe());
        values.put(Address_Tag,Message.getAddress());
        db.update(TABLE_Messages, values, Recu_Tag+"= ? and "+Address_Tag+" = ?", new String[]{String.valueOf(date),Message.getAddress()});
        db.close();



    }

    public cMessage getMessage (long date, String address){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_Messages, new String []{Recu_Tag, Msg_Tag, Me_Tag,Address_Tag},
                Recu_Tag+"= ? and "+Address_Tag+ "= ?", new String[]{String.valueOf(date),address},
                null,null,null,null);
        if(cursor != null)
            cursor.moveToFirst();
        else
            return null;
        return new cMessage(cursor.getLong(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3));

    }

    public ArrayList<cMessage> getMessages(String address){ // get Messages with a specific address
        ArrayList<cMessage> CaracterizedMessages = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_Messages, new String []{Recu_Tag, Msg_Tag, Me_Tag,Address_Tag},
                Recu_Tag+"= ? and "+Address_Tag+ "= ?", new String[]{address},
                null,null,null,null);
        if(cursor != null && cursor.moveToFirst())
            do{
                CaracterizedMessages.add(new cMessage(cursor.getLong(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3)));
            } while (cursor.moveToNext());
            db.close();
        return CaracterizedMessages;


    }

}
