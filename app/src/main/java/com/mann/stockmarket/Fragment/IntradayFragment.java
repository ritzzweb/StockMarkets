package com.mann.stockmarket.Fragment;

import android.app.ProgressDialog;
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
import com.mann.stockmarket.Model.Data;
import com.mann.stockmarket.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class IntradayFragment extends Fragment {

    public IntradayFragment() {
        // Required empty public constructor
    }
    private View view;

     RecyclerView mBlogList;
    IntradayAdapter intradayAdapter;
    DatabaseReference mDatabase;

    ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_intraday, container, false);

        mBlogList = view.findViewById(R.id.myrecyclerView);
        mBlogList.setLayoutManager(new LinearLayoutManager(getContext()));
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Data");
        mDatabase.keepSynced(true);

        FirebaseRecyclerOptions<Data> options = new FirebaseRecyclerOptions.Builder<Data>()
                .setQuery(mDatabase, Data.class).build();

        intradayAdapter = new IntradayAdapter(options);
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

