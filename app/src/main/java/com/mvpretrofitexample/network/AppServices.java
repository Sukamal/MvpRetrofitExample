package com.mvpretrofitexample.network;

import com.mvpretrofitexample.model.PostsModel;
import com.mvpretrofitexample.network.retrofit.ApiClient;
import com.mvpretrofitexample.network.retrofit.ApiInterface;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by SukamalD on 29-12-2017.
 */

public class AppServices {

    private static Retrofit retrofitInstance;

    static{
        retrofitInstance = ApiClient.getClient();
    }

    public static Call<ResponseBody> getAllPosts(int userId){

        return retrofitInstance.create(ApiInterface.class).getUserPosts(userId);
    }

    public static Observable<PostsModel> getUserPosts(int userId){

        return retrofitInstance.create(ApiInterface.class).grtUserPost(userId);
    }

    public static Observable<ResponseBody> getRawUserPosts(int userId){

        return retrofitInstance.create(ApiInterface.class).getUserPostRaw();
    }
}
