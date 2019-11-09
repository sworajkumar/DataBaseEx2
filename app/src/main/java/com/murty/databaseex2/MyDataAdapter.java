package com.murty.databaseex2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MyDataAdapter extends RecyclerView.Adapter<MyDataAdapter.MyHolder>
{
    ArrayList al;
    Context context;

    public MyDataAdapter(Context context,ArrayList al)
    {
      this.al=al;
      this.context=context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        LayoutInflater li=(LayoutInflater)viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=li.inflate(R.layout.data_style,viewGroup,false);

        MyHolder my=new MyHolder(v);
        return my;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int position)
    {
        final HashMap hm=(HashMap)al.get(position);

        final int no=(int)hm.get(MyDataBase.one);
        String name=(String)hm.get(MyDataBase.two);
        double cost=(double)hm.get(MyDataBase.three);

        myHolder.tv1.setText(""+no);
        myHolder.tv2.setText(name);
        myHolder.tv3.setText(""+cost);

        myHolder.b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
             MyDataBase my=new MyDataBase(context);
             SQLiteDatabase sdb=my.getWritableDatabase();
             String where=MyDataBase.one+"=?";

             int del=sdb.delete(MyDataBase.table_name,where,new String[]{String.valueOf(no)});

             if (del!=0)
             {
                 Toast.makeText(context, del+" rows deleted", Toast.LENGTH_SHORT).show();
             }
             else
             {
                 Toast.makeText(context, "No rows deleted", Toast.LENGTH_SHORT).show();
             }
            }
        });

    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder
    {
        TextView tv1,tv2,tv3;
        Button b1,b2;
        public MyHolder(@NonNull View v) {
            super(v);
            tv1=v.findViewById(R.id.tv_product_no);
            tv2=v.findViewById(R.id.tv_product_name);
            tv3=v.findViewById(R.id.tv_product_cost);
            b2=v.findViewById(R.id.delete);
        }
    }
}
