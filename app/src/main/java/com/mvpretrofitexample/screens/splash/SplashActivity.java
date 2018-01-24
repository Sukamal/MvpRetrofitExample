package com.mvpretrofitexample.screens.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mvpretrofitexample.MainActivity;
import com.mvpretrofitexample.R;
import com.mvpretrofitexample.screens.login.LoginActivity;
import com.mvpretrofitexample.screens.parent.ParentActivity;
import com.mvpretrofitexample.screens.welcome.WelcomeActivity;

public class SplashActivity extends AppCompatActivity implements SplashViewInterface{

    private SplashLogicImpl splashLogic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashLogic = new SplashLogicImpl(SplashActivity.this);
        splashLogic.gotoNextScreen();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void displayMessage(String msg) {

    }

    @Override
    public void gotoNextScreen() {
        startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
    }


}
