package com.example.l180973assign1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;

public class NotesDB implements INoteDAO {
    private Context context;
    public NotesDB(Context ctx){
        context = ctx;
    }
    @Override
    public void save(Hashtable<String, String> attributes) {
        DB dbHelper = new DB(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues content = new ContentValues();
        Enumeration<String> keys = attributes.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            content.put(key, attributes.get(key));
        }

        long result = db.insert("Donors",null, content);
        if(result == -1)
        {
            Toast.makeText(context,"failed",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();
        }
    }

    @Override

    public void save(ArrayList<Hashtable<String, String>> objects) {
        for(Hashtable<String,String> obj : objects){
            save(obj);
        }
    }

    @SuppressLint("Range")
    @Override
    public ArrayList<Hashtable<String, String>> load() {
        DB dbHelper = new DB(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM Donors";
        Cursor cursor = db.rawQuery(query,null);
        ArrayList<Hashtable<String,String>> objects = new ArrayList<Hashtable<String, String>>();

        while(cursor.moveToNext()){
            Hashtable<String,String> obj = new Hashtable<String, String>();
            String [] columns = cursor.getColumnNames();
            for(String col : columns){
                if(!col.equals("Id")) {
                    obj.put(col, cursor.getString(cursor.getColumnIndex(col)));
                }
            }
            objects.add(obj);
        }
        return objects;
    }

    public Hashtable<String, String> load(String id) {
        return null;
    }
}
