package com.mvpretrofitexample.screens.welcome;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mvpretrofitexample.R;

/**
 * Created by SukamalD on 02-01-2018.
 */

public class WelcomePagerAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int[] mResources = {
            R.layout.welcome_slide_one,
            R.layout.welcome_slide_two,
            R.layout.welcome_slide_three
    };

    public WelcomePagerAdapter(Context context){
        this.mContext = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View viewItem = mLayoutInflater.inflate(mResources[position],container,false);
        container.addView(viewItem);
        return viewItem;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
