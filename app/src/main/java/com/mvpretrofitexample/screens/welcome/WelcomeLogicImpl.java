package com.mvpretrofitexample.screens.welcome;

/**
 * Created by SukamalD on 02-01-2018.
 */

public class WelcomeLogicImpl implements WelcomeLogicInterface{

    private WelcomeViewInterface viewInterface;
    public WelcomeLogicImpl(WelcomeViewInterface viewInterface){
        this.viewInterface = viewInterface;
    }

    @Override
    public void displayNextScreen() {
        viewInterface.gotoNextScreen();
    }
}
