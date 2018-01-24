package com.mvpretrofitexample.screens.posts;

import android.app.Notification;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mvpretrofitexample.appinterface.DataInterface;
import com.mvpretrofitexample.model.MessageModel;
import com.mvpretrofitexample.model.PostsModel;
import com.mvpretrofitexample.network.AppServices;
import com.mvpretrofitexample.network.ServiceExecuter;
import com.mvpretrofitexample.screens.splash.SplashLogicImpl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by SukamalD on 29-12-2017.
 */

public class PostDataImpl implements PostDataInterface {


    @Override
    public void loadData(Callback<PostsModel> callback,int userId) {
        ServiceExecuter.execute(AppServices.getAllPosts(userId),callback, PostsModel.class);
    }

    @Override
    public void loadObservableData(final Callback<PostsModel> callback, int userId) {

        AppServices.getUserPosts(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PostsModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PostsModel postsModel) {
                        callback.onResponse(postsModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });


//        AppServices.getUserPosts(userId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<PostsModel>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(PostsModel responseBody) {
//                        callback.onResponse(responseBody);
//
////                        try {
////                            String rawRespons = responseBody.string();
////                            JSONObject jsonObject = new JSONObject(rawRespons);
////                            Gson gson = new Gson();
////                            PostsModel resPonse = (PostsModel) gson.fromJson(jsonObject.toString(),PostsModel.class);
////                            callback.onResponse(resPonse);
////                        } catch (IOException e) {
////                            e.printStackTrace();
////                        } catch (JSONException e) {
////                            e.printStackTrace();
////                        }
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

    }

    @Override
    public void loadObservableRawData(final Callback<PostsModel> callback, int userId) {
        AppServices.getRawUserPosts(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String rawRespons = responseBody.string();
//                            JSONObject jsonObject = new JSONObject(rawRespons);
//                            JSONArray jsonArray = new JSONArray(rawRespons);
                            Gson gson = new Gson();

                            Type listType = new TypeToken<ArrayList<PostsModel>>(){}.getType();
                            List<PostsModel> category = gson.fromJson(rawRespons,listType);

                            StringBuffer stringBuffer = new StringBuffer();
                            for(PostsModel model : category){
                                stringBuffer.append("\n");
                                stringBuffer.append(model.getTitle());
                                stringBuffer.append("\n");
                                stringBuffer.append(model.getBody());

                            }

                            System.out.println(stringBuffer.toString());
//                            PostsModel resPonse = (PostsModel) gson.fromJson(rawRespons,PostsModel.class);
                            callback.onResponse(category.get(0));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
