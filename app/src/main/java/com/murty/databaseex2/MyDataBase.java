package com.murty.databaseex2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDataBase extends SQLiteOpenHelper {

    public static final String dbname="myshop";
    public static final int version=1;
    public static final String table_name="product_details";
    public static final String one="product_no_col";
    public static final String two="product_name_col";
    public static final String three="product_cost_col";
    Context context;

    public MyDataBase(Context context) {
        super(context, dbname, null, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry="create table "+table_name+"("+one +" number PRIMARY KEY,"+two+" text,"+three+" real)";
        db.execSQL(qry);
        Toast.makeText(context, "Table Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
