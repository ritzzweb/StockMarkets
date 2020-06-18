package com.mann.stockmarket.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.mann.stockmarket.Model.PositionalTips;
import com.mann.stockmarket.R;

public class PositionalTipsAdapter extends FirebaseRecyclerAdapter<PositionalTips, PositionalTipsAdapter.ViewHolder> {

    public PositionalTipsAdapter(@NonNull FirebaseRecyclerOptions<PositionalTips> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int i, @NonNull PositionalTips data) {
        holder.stockname.setText(data.getStockname());
        holder.buyabove.setText(data.getBuyabove());
        holder.stoploss.setText(data.getStoploss());
        holder.tgt.setText(data.getTgt());
        holder.remarks.setText(data.getRemarks());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.positionaltips_item, parent, false);
        return new PositionalTipsAdapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stockname, stoploss, buyabove, tgt,remarks;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            stockname = itemView.findViewById(R.id.txt1);
            buyabove=itemView.findViewById(R.id.txt2);
            stoploss = itemView.findViewById(R.id.txt3);
            tgt = itemView.findViewById(R.id.txt4);
            remarks = itemView.findViewById(R.id.remarks);

        }
    }
}