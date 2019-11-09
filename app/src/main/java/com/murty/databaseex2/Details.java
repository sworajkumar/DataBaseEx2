package com.murty.databaseex2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Details extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ArrayList al=new ArrayList();
        RecyclerView rv=findViewById(R.id.rcv);

        MyDataBase mdb=new MyDataBase(this);
        SQLiteDatabase sdb=mdb.getWritableDatabase();
        Cursor c=sdb.query(MyDataBase.table_name,null,null,null,null,null,null);

        if (c.moveToFirst())
        {
            do {
               int no=c.getInt(0);
               String name=c.getString(1);
               double cost=c.getDouble(2);

                HashMap hm=new HashMap();
                hm.put(MyDataBase.one,no);
                hm.put(MyDataBase.two,name);
                hm.put(MyDataBase.three,cost);
                al.add(hm);

            }while (c.moveToNext());

            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(new MyDataAdapter(this,al));
        }
        else
        {
            Toast.makeText(this, "No Products Available", Toast.LENGTH_SHORT).show();
        }

    }
}
