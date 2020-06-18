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
import com.mann.stockmarket.Adapter.PositionalTipsAdapter;
import com.mann.stockmarket.R;

public class PositionalTips extends AppCompatActivity {
    private AdView mAdView;


    RecyclerView mBlogList;
    PositionalTipsAdapter intradayAdapter;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_positional_tips);

        ImageView imgback = findViewById(R.id.imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(PositionalTips.this, MainActivity.class);
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
        mDatabase = FirebaseDatabase.getInstance().getReference().child("PositionalTips");
        mDatabase.keepSynced(true);

        FirebaseRecyclerOptions<com.mann.stockmarket.Model.PositionalTips> options = new FirebaseRecyclerOptions.Builder<com.mann.stockmarket.Model.PositionalTips>()
                .setQuery(mDatabase, com.mann.stockmarket.Model.PositionalTips.class).build();

        intradayAdapter = new PositionalTipsAdapter(options);
        mBlogList.setAdapter(intradayAdapter);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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
