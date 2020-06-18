package com.mann.stockmarket.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mann.stockmarket.Adapter.IntradayAdapter;
import com.mann.stockmarket.Adapter.PositionalTipsAdapter;
import com.mann.stockmarket.Model.PositionalTips;
import com.mann.stockmarket.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PositionalFragment extends Fragment {

    public PositionalFragment() {
        // Required empty public constructor
    }

    private View view;

    RecyclerView mBlogList;
    PositionalTipsAdapter intradayAdapter;
    DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_positional, container, false);


        mBlogList = view.findViewById(R.id.myrecyclerView);
        mBlogList.setLayoutManager(new LinearLayoutManager(getContext()));
        mDatabase = FirebaseDatabase.getInstance().getReference().child("PositionalTips");
        mDatabase.keepSynced(true);

        FirebaseRecyclerOptions<PositionalTips> options = new FirebaseRecyclerOptions.Builder<com.mann.stockmarket.Model.PositionalTips>()
                .setQuery(mDatabase, com.mann.stockmarket.Model.PositionalTips.class).build();

        intradayAdapter = new PositionalTipsAdapter(options);
        mBlogList.setAdapter(intradayAdapter);

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        intradayAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        intradayAdapter.stopListening();
    }

}
