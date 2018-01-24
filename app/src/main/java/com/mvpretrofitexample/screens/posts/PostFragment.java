package com.mvpretrofitexample.screens.posts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvpretrofitexample.R;
import com.mvpretrofitexample.appinterface.DataInterface;
import com.mvpretrofitexample.model.PostsModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostFragment extends Fragment implements PostViewInterface{

    @BindView(R.id.rv_Posts)
    RecyclerView rvPosts;
    @BindView(R.id.tv_sampleText)
    TextView tvSampleText;
    private List userPosts;
    private PostListAdapter postListAdapter;
    private PostLogicImpl postLogic;


    public PostFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        ButterKnife.bind(this,view);
        setupRecycleView();
        postLogic = new PostLogicImpl(this);
//        postLogic.doSomeServerTask();
//        postLogic.doSomeObservableServerTask();
        postLogic.doSomeObservableServerRawTask();
        return view;
    }



    private void setupRecycleView(){
        rvPosts.setLayoutManager(new GridLayoutManager(getActivity(),3));
        setupList();
        postListAdapter = new PostListAdapter(userPosts);
        rvPosts.setAdapter(postListAdapter);
    }

    private void setupList(){
        userPosts  = new ArrayList();
        PostsModel postsModel = new PostsModel();
        for (int i = 0; i < 100; i++){
            postsModel.setTitle("TITLE "+(i+1));
            postsModel.setBody("This Is Detail Message "+(i+1));
            userPosts.add(postsModel);

        }
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void displayMessage(String msg) {

    }

    @Override
    public void gotoNextScreen() {

    }

    @Override
    public void displayServerResponse(String response) {
        tvSampleText.setText(response);
    }
}
