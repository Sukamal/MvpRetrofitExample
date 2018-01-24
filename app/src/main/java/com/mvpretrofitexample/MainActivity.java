package com.mvpretrofitexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.mvpretrofitexample.appinterface.DataInterface;
import com.mvpretrofitexample.model.PostsModel;
import com.mvpretrofitexample.network.retrofit.ApiClient;
import com.mvpretrofitexample.network.retrofit.ApiInterface;
import com.mvpretrofitexample.screens.posts.PostLogicImpl;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String API_URL = "https://jsonplaceholder.typicode.com/";

    @BindView(R.id.tv_url)
    TextView Url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Url.setText(API_URL);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
//                callRetrofit();
                getRawDataFromRetroFit();
//                getServerResponse();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void callRetrofit(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<PostsModel>> call = apiService.getPosts();
        call.enqueue(new Callback<List<PostsModel>>() {
            @Override
            public void onResponse(Call<List<PostsModel>> call, Response<List<PostsModel>> response) {

                List<PostsModel> postsModels = null;
                if(response != null){
                    postsModels = response.body();
                    if(postsModels != null){
                        for(PostsModel postsModel : postsModels){
                            System.out.println("TITLE : "+postsModel.getTitle());
                            System.out.println("BODY : "+postsModel.getBody());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PostsModel>> call, Throwable t) {
                System.out.println("ERROR : "+t.getMessage().toString());
            }
        });
    }

    private void getRawDataFromRetroFit(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiService.getRawPostsData();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String rawRespons = response.body().string();
                    System.out.println("Response : "+rawRespons);
                } catch (IOException e) {
                    System.out.println("ERROR : "+e.getMessage().toString());

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("ERROR : "+t.getMessage().toString());

            }
        });
    }

//    private void getServerResponse(){
//        PostLogicImpl postDataimpl = new PostLogicImpl();
//        postDataimpl.loadData(new DataInterface.Callback<PostsModel>() {
//            @Override
//            public void onResponse(PostsModel response) {
//
//                if(response != null){
//                        System.out.println("TITLE : "+response.getTitle());
//                        System.out.println("BODY : "+response.getBody());
//                }
//            }
//
//            @Override
//            public void onFailureWithMessage(String message) {
//
//            }
//
//            @Override
//            public void onFailure() {
//
//            }
//
//            @Override
//            public void onNoNetworkFailure() {
//
//            }
//        });
//    }
}
