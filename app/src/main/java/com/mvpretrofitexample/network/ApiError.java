package com.mvpretrofitexample.network;

import com.mvpretrofitexample.appinterface.DataInterface;

import java.io.IOException;

/**
 * Created by SukamalD on 17-01-2018.
 */

public class ApiError {

    public void onApiError(DataInterface.Callback callback, Throwable error){
        if(error instanceof NullPointerException){
            //DO NOTHING
        }

        else {

            if (error instanceof IOException) {
                callback.onNoNetworkFailure();
            }
            else if (error instanceof IllegalStateException) {
                callback.onFailure();
            } else {
                callback.onFailureWithMessage(String.valueOf(error.getLocalizedMessage()));
            }

        }
    }
}
