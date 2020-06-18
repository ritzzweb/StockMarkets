package com.mann.stockmarket.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.mann.stockmarket.Model.Data;
import com.mann.stockmarket.Model.IntradayTips;
import com.mann.stockmarket.R;

public class IntradayTipsAdapter extends FirebaseRecyclerAdapter<IntradayTips, IntradayTipsAdapter.ViewHolder> {

    public IntradayTipsAdapter(@NonNull FirebaseRecyclerOptions<IntradayTips> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int i, @NonNull IntradayTips data) {
        holder.stockname.setText(data.getStockname());
        holder.buyabove.setText(data.getBuyabove());
        holder.stoploss.setText(data.getStoploss());
        holder.tgt.setText(data.getTgt());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.intradaytipsitem, parent, false);
        return new IntradayTipsAdapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stockname, stoploss, buyabove, tgt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            stockname = itemView.findViewById(R.id.txt1);
            buyabove=itemView.findViewById(R.id.txt2);
            stoploss = itemView.findViewById(R.id.txt3);
            tgt = itemView.findViewById(R.id.txt4);

        }
    }
}
