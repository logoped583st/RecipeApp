package com.example.stanislau_bushuk.foodhealth.presentantion.splashPresentatiom;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.stanislau_bushuk.foodhealth.ActivityManager;
import com.example.stanislau_bushuk.foodhealth.Constants;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Context context = this;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ActivityManager.startMainActivity(context);
                finish();
            }
        }, Constants.WAIT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
