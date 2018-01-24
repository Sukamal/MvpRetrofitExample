package com.mvpretrofitexample.screens.welcome;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mvpretrofitexample.R;
import com.mvpretrofitexample.screens.parent.ParentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends AppCompatActivity implements WelcomeViewInterface {

    private WelcomeLogicImpl welcomeLogic;
    @BindView(R.id.pager)
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        setupPagger();
        welcomeLogic = new WelcomeLogicImpl(this);
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
        startActivity(new Intent(WelcomeActivity.this, ParentActivity.class));
    }

    @OnClick(R.id.tv_GoNext)
    public void onNextButtonClick(){
        welcomeLogic.displayNextScreen();
    }

    private void setupPagger(){
        WelcomePagerAdapter pagerAdapter = new WelcomePagerAdapter(WelcomeActivity.this);
        viewPager.setAdapter(pagerAdapter);
    }
}
