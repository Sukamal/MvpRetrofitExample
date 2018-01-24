package com.mvpretrofitexample.screens.posts;

import com.mvpretrofitexample.appinterface.DataInterface;
import com.mvpretrofitexample.model.PostsModel;
import com.mvpretrofitexample.network.AppServices;
import com.mvpretrofitexample.network.ServiceExecuter;
import com.mvpretrofitexample.screens.posts.PostDataInterface;

/**
 * Created by SukamalD on 29-12-2017.
 */

public class PostLogicImpl implements PostLogicInterface{

    private PostViewInterface viewInterface;
    private PostDataImpl postData;

    PostLogicImpl(PostViewInterface viewInterface){
        this.viewInterface = viewInterface;
        postData = new PostDataImpl();
    }


    @Override
    public void doSomeServerTask() {
        postData.loadData(new DataInterface.Callback<PostsModel>() {
            @Override
            public void onResponse(PostsModel response) {
                if(response != null){
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("TITLE : "+response.getTitle());
                    stringBuffer.append("\nBODY : "+response.getBody());
                    System.out.println(stringBuffer.toString());
                    viewInterface.displayServerResponse(stringBuffer.toString());
                }else{
                    viewInterface.displayServerResponse("Error");

                }
            }

            @Override
            public void onFailureWithMessage(String message) {

            }

            @Override
            public void onFailure() {

            }

            @Override
            public void onNoNetworkFailure() {

            }

        },1);
    }

    @Override
    public void doSomeObservableServerTask() {
        postData.loadObservableData(new DataInterface.Callback<PostsModel>() {
            @Override
            public void onResponse(PostsModel response) {
                if(response != null){
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("TITLE : "+response.getTitle());
                    stringBuffer.append("\nBODY : "+response.getBody());
                    System.out.println(stringBuffer.toString());
                    viewInterface.displayServerResponse(stringBuffer.toString());
                }else{
                    viewInterface.displayServerResponse("Error");

                }
            }

            @Override
            public void onFailureWithMessage(String message) {

            }

            @Override
            public void onFailure() {

            }

            @Override
            public void onNoNetworkFailure() {

            }
        },1);
    }

    @Override
    public void doSomeObservableServerRawTask() {
        postData.loadObservableRawData(new DataInterface.Callback<PostsModel>() {
            @Override
            public void onResponse(PostsModel response) {

                if(response != null){
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("TITLE : "+response.getTitle());
                    stringBuffer.append("\nBODY : "+response.getBody());
                    System.out.println(stringBuffer.toString());
                    viewInterface.displayServerResponse(stringBuffer.toString());
                }else{
                    viewInterface.displayServerResponse("Error");

                }
            }

            @Override
            public void onFailureWithMessage(String message) {

            }

            @Override
            public void onFailure() {

            }

            @Override
            public void onNoNetworkFailure() {

            }
        },1);
    }


}
