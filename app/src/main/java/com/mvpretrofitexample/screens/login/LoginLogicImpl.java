package com.mvpretrofitexample.screens.login;

/**
 * Created by SukamalD on 27-12-2017.
 */

public class LoginLogicImpl implements LoginLogicInterface{

    private LoginViewInterface viewInterface;

    public LoginLogicImpl(LoginViewInterface viewInterface){
        this.viewInterface = viewInterface;
    }

    @Override
    public void doLogin(String userName, String password) {
        viewInterface.gotoNextScreen();
    }
}
