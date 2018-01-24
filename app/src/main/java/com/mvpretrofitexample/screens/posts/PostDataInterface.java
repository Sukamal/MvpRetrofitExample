package com.mvpretrofitexample.screens.posts;

import com.mvpretrofitexample.appinterface.DataInterface;
import com.mvpretrofitexample.model.PostsModel;

/**
 * Created by SukamalD on 29-12-2017.
 */

public interface PostDataInterface extends DataInterface {
    public void loadData(Callback<PostsModel> callback,int userId);
    public void loadObservableData(Callback<PostsModel> callback,int userId);
    public void loadObservableRawData(Callback<PostsModel> callback,int userId);
}
