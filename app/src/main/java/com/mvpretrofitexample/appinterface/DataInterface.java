package com.mvpretrofitexample.appinterface;

/**
 * Created by SukamalD on 28-12-2017.
 */

public interface DataInterface {

    interface Callback<T>{
        /** Called when response is received */
        void onResponse(T response);

        /**A response from the server with a message*/
        void onFailureWithMessage(String message);

        /** Response has not been received from server */
        void onFailure();

        /** Response has not been received because of network error*/
        void onNoNetworkFailure();

    }
}
