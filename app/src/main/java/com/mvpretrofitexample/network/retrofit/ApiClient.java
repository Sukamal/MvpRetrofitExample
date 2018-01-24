package com.mvpretrofitexample.network.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SukamalD on 27-12-2017.
 */

public class ApiClient {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    public static Retrofit retrofit;
    private static Gson gson;
    private static OkHttpClient client;

    public static Retrofit getClient(){
        client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();

        gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
