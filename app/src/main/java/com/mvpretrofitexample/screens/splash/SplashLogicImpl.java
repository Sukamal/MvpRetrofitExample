package com.mvpretrofitexample.screens.splash;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by SukamalD on 27-12-2017.
 */

public class SplashLogicImpl implements SplashLogicInterface{

    private SplashViewInterface viewInterface;
    public SplashLogicImpl(SplashViewInterface viewInterface){
        this.viewInterface = viewInterface;
    }

    @Override
    public void gotoNextScreen() {

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                viewInterface.gotoNextScreen();
            }
        },2000);

    }
}
