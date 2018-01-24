package com.mvpretrofitexample.network;

import com.google.gson.Gson;
import com.mvpretrofitexample.appinterface.DataInterface;
import com.mvpretrofitexample.network.retrofit.ApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by SukamalD on 29-12-2017.
 */

public class ServiceExecuter {


    public static<T> void execute(Call<ResponseBody> call, final DataInterface.Callback<T> serviceCallBack, final Class<T> c ){

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if(response != null) {
                        String rawRespons = response.body().string();
                        JSONObject jsonObject = new JSONObject(rawRespons);
                        Gson gson = new Gson();
                        T resPonse = (T) gson.fromJson(jsonObject.toString(),c);
                        serviceCallBack.onResponse(resPonse);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


}
