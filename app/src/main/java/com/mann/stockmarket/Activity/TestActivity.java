package com.mann.stockmarket.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.mann.stockmarket.Adapter.IntradayAdapter;
import com.mann.stockmarket.Model.Data;
import com.mann.stockmarket.R;

public class TestActivity extends AppCompatActivity {
    RecyclerView mBlogList;
    IntradayAdapter intradayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mBlogList = findViewById(R.id.myrecyclerView);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Data> options = new FirebaseRecyclerOptions.Builder<Data>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Data"), Data.class).build();

        intradayAdapter = new IntradayAdapter(options);
        mBlogList.setAdapter(intradayAdapter);
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
