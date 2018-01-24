package com.mvpretrofitexample.screens.parent;

/**
 * Created by SukamalD on 28-12-2017.
 */

public class ParentLogicImpl implements ParentLogicInterface{
    private ParentViewInterface viewInterface;
    public ParentLogicImpl(ParentViewInterface viewInterface){

        this.viewInterface = viewInterface;

    }
}
