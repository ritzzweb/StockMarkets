package com.mann.stockmarket.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.mann.stockmarket.Adapter.ViewPagerAdapter;
import com.mann.stockmarket.R;

public class MainActivity extends AppCompatActivity {
    NavigationView navigationView;
    ImageView img;
    ViewPager viewPager;
    TabLayout tabLayout;

    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.naviagte);
        img = findViewById(R.id.nagivationicon);



        MobileAds.initialize(this,getString(R.string.app_ads_id));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        drawer.openDrawer(GravityCompat.START, false);
                        break;
                    case R.id.nav_intraday:
                        Intent intraday = new Intent(MainActivity.this, IntradayTips.class);
                        startActivity(intraday);
                        drawer.openDrawer(GravityCompat.START, false);
                        break;
                    case R.id.nav_positional:
                        Intent positionl = new Intent(MainActivity.this, PositionalTips.class);
                        startActivity(positionl);
                        drawer.openDrawer(GravityCompat.START, false);
                        break;
                    case R.id.nav_aboutus:
                        Intent aboutus = new Intent(MainActivity.this, Aboutus.class);
                        startActivity(aboutus);
                        drawer.openDrawer(GravityCompat.START, false);
                        break;
                    case R.id.nav_premiumplans:
                        Intent premium = new Intent(MainActivity.this, PremiumPlan.class);
                        startActivity(premium);
                        drawer.openDrawer(GravityCompat.START, false);
                        break;
                    case R.id.nav_livechat:
                        String smsNumber = "+91 8447434552";
                        String smsText = "Hello";
                        Uri uri = Uri.parse("smsto:" + smsNumber);
                        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                        i.putExtra("sms_body", smsText);
                        i.setPackage("com.whatsapp");
                        startActivity(i);
                        drawer.openDrawer(GravityCompat.START, false);
                        break;

                    case R.id.nav_rules:
                        Intent rules = new Intent(MainActivity.this, Rules.class);
                        startActivity(rules);
                        drawer.openDrawer(GravityCompat.START, false);
                        break;
                    case R.id.nav_logout:
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Alert!!!");
                        builder.setMessage("Are you sure to logout");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth.getInstance().signOut();
                                MainActivity.this.startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                finish();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }

                        });
                        builder.setCancelable(false);
                        builder.show();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
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
