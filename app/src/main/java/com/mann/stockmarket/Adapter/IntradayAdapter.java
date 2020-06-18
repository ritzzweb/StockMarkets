package com.mann.stockmarket.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.mann.stockmarket.Model.Data;
import com.mann.stockmarket.R;

public class IntradayAdapter extends FirebaseRecyclerAdapter<Data, IntradayAdapter.ViewHolder> {

    public IntradayAdapter(@NonNull FirebaseRecyclerOptions<Data> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int i, @NonNull Data blog) {
          String data = blog.getType();
          if (data.equalsIgnoreCase("sell below")){

              holder.linearLayout.setBackgroundResource(R.drawable.border_red);
              holder.type.setText(blog.getType());
          }
          else {
              holder.type.setText(blog.getType());
          }
//        holder.type.setText(blog.getType());
        holder.scriptname.setText(blog.getScriptname());
        holder.total1.setText("TGT 1  :  "+blog.getTotal1());
        holder.total2.setText("TGT 2  :  "+blog.getTotal2());
        holder.total3.setText("TGT 3  :  "+blog.getTotal3());
        holder.sl.setText("SL  :  "+blog.getSl());
        holder.date.setText("Date  :  "+blog.getTime());



    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.intradayrecycleritem, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView scriptname, type, total1,total2,total3,sl,date;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            scriptname= itemView.findViewById(R.id.txt1);
            type= itemView.findViewById(R.id.txt2);
            total1= itemView.findViewById(R.id.txt3);
            total2= itemView.findViewById(R.id.txt4);
            total3= itemView.findViewById(R.id.txt5);
            sl= itemView.findViewById(R.id.txt6);
            linearLayout = itemView.findViewById(R.id.linear);
            date = itemView.findViewById(R.id.txt7);

        }
    }
}
