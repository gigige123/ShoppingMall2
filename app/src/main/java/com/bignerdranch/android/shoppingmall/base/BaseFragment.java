package com.bignerdranch.android.shoppingmall.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ericchen on 2017/1/6.
 * 作用：基类Fragment
 * 被HomeFragment, TypeFragment,CommunityFragment,ShoppingCartFragment
 */
public abstract class BaseFragment extends Fragment {

    protected Context mContext;

    /**
     *
     * */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 抽象方法，有子类实现，实现不同的效果
     *
     * **/
    public abstract View initView() ;

    /**
     * 当Activity被创建时，回调该方法
     * */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 当子类需要联网请求数据的时候，可以重写该方法,
     * */
    public void initData(){

    }
}
