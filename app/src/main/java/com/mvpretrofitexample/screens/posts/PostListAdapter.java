package com.mvpretrofitexample.screens.posts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvpretrofitexample.R;
import com.mvpretrofitexample.model.PostsModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SukamalD on 28-12-2017.
 */

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostViewHolder>{

    private ClickListener listener;
    private List<PostsModel> dataList;

    public interface ClickListener {
        void onAdapterItemClick(View view, int position, Object selectedItem);
    }

    public PostListAdapter(List<PostsModel> userPostList){
        this.dataList = userPostList;
    }

    public PostListAdapter(ClickListener listener, List<PostsModel> userPostList){
        this.listener = listener;
        this.dataList = userPostList;
    }

    public void setAdapterItemClickListner(ClickListener listener){
        this.listener = listener;
    }

    @Override
    public PostListAdapter.PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user_posts, parent, false);
        PostViewHolder holder = new PostViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PostListAdapter.PostViewHolder holder, int position) {
        PostsModel postsModel = dataList.get(position);
        holder.tvTitle.setText(postsModel.getTitle());
        holder.tvMessage.setText(postsModel.getBody());

    }

    @Override
    public int getItemCount() {
        if(dataList != null){
            return dataList.size();
        }else{
            return 0;
        }
    }


    class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.tv_Title) TextView tvTitle;
        @BindView(R.id.tv_Message) TextView tvMessage;

        public PostViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }

        @Override
        public void onClick(View v) {
            if(listener != null){
                listener.onAdapterItemClick(v,getAdapterPosition(),dataList.get(getAdapterPosition()));
            }
        }
    }

}


