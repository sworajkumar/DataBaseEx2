package com.murty.databaseex2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2,et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=findViewById(R.id.et_product_no);
        et2=findViewById(R.id.et_product_name);
        et3=findViewById(R.id.et_product_cost);
    }

    public void insertData(View view) {
        String no=et1.getText().toString().trim();
        String product_name=et2.getText().toString().trim();
        String cost=et3.getText().toString().trim();

        if (no.isEmpty()) {
            et1.setError("Empty");
            et1.requestFocus();
        } else if(product_name.isEmpty()) {
            et2.setError("Empty");
            et2.requestFocus();
        } else if(cost.isEmpty()) {
            et3.setError("Empty");
            et3.requestFocus();
        } else {
            int product_no=Integer.parseInt(no);
            double product_cost=Double.parseDouble(cost);

            ContentValues cv=new ContentValues();
            cv.put(MyDataBase.one,product_no);
            cv.put(MyDataBase.two,product_name);
            cv.put(MyDataBase.three,product_cost);

            MyDataBase mdb=new MyDataBase(this);
            SQLiteDatabase sd=mdb.getWritableDatabase();
            long data=sd.insert(MyDataBase.table_name,null,cv);

            if (data!=-1) {
                et1.setText("");
                et2.setText("");
                et3.setText("");
                et1.requestFocus();
                Toast.makeText(this, "Product Inserted Successfully", Toast.LENGTH_SHORT).show();
            } else {
                et1.setText("");
                et2.setText("");
                et3.setText("");
                et1.requestFocus();
                Toast.makeText(this, "Invalid Product Details", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void viewData(View view) {
        Intent i=new Intent(this,Details.class);
        startActivity(i);
    }

    public void updateData(View view) {
        String no=et1.getText().toString().trim();
        String product_name=et2.getText().toString().trim();
        String cost=et3.getText().toString().trim();

        if (no.isEmpty()) {
            et1.setError("Empty");
            et1.requestFocus();
        } else if(product_name.isEmpty()) {
            et2.setError("Empty");
            et2.requestFocus();
        } else if(cost.isEmpty()) {
            et3.setError("Empty");
            et3.requestFocus();
        } else {
            int product_no=Integer.parseInt(no);
            double product_cost=Double.parseDouble(cost);

            ContentValues cv=new ContentValues();
            cv.put(MyDataBase.two,product_name);
            cv.put(MyDataBase.three,product_cost);

            MyDataBase mdb=new MyDataBase(this);
            SQLiteDatabase sdb=mdb.getWritableDatabase();

            String where=MyDataBase.one+"=?";

            int data=sdb.update(MyDataBase.table_name,cv,where,new String[]{String.valueOf(product_no)});

            if (data!=0) {
                et1.setText("");
                et2.setText("");
                et3.setText("");
                et1.requestFocus();
                Toast.makeText(this, "Product Updated Successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Invalid Product Details", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
