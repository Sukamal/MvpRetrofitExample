package com.mvpretrofitexample.network.retrofit;

import com.mvpretrofitexample.model.PostsModel;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
//import rx.Observable;

/**
 * Created by SukamalD on 27-12-2017.
 */

public interface ApiInterface {

//    @GET("posts/{user}")
//    Call<PostsModel>getUserPosts(@Path("user") int user);

    @GET("posts")
    Call<ResponseBody> getRawPostsData();

    @GET("posts")
    Call<List<PostsModel>> getPosts();

    @GET("posts/{user}")
    Call<ResponseBody> getUserPosts(@Path("user") int user);

    @GET("posts/{user}")
    Observable<PostsModel> grtUserPost(@Path("user") int user);

//    @GET("posts/{user}")
//    Observable<ResponseBody> getUserPostRaw(@Path("user") int user);

    @GET("posts")
    Observable<ResponseBody> getUserPostRaw();



}
