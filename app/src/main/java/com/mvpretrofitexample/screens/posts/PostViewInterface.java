package com.mvpretrofitexample.screens.posts;

import com.mvpretrofitexample.appinterface.CommonViewInterface;

/**
 * Created by SukamalD on 29-12-2017.
 */

public interface PostViewInterface extends CommonViewInterface{
    void gotoNextScreen();
    void displayServerResponse(String response);
}
