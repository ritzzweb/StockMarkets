package com.mann.stockmarket.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mann.stockmarket.Adapter.IntradayAdapter;
import com.mann.stockmarket.Adapter.IntradayTipsAdapter;
import com.mann.stockmarket.Model.Data;
import com.mann.stockmarket.R;

public class IntradayTips extends AppCompatActivity {
    RecyclerView mBlogList;
    IntradayTipsAdapter intradayAdapter;
    DatabaseReference mDatabase;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intraday_tips);
        ImageView imgback = findViewById(R.id.imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(IntradayTips.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        MobileAds.initialize(this,getString(R.string.app_ads_id));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mBlogList = findViewById(R.id.myrecyclerView);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));
        mDatabase = FirebaseDatabase.getInstance().getReference().child("IntradayTips");
        mDatabase.keepSynced(true);

        FirebaseRecyclerOptions<com.mann.stockmarket.Model.IntradayTips> options = new FirebaseRecyclerOptions.Builder<com.mann.stockmarket.Model.IntradayTips>()
                .setQuery(mDatabase, com.mann.stockmarket.Model.IntradayTips.class).build();

        intradayAdapter = new IntradayTipsAdapter(options);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
