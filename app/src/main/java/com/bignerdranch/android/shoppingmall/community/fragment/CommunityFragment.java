package com.bignerdranch.android.shoppingmall.community.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.android.shoppingmall.base.BaseFragment;

/**
 * Created by ericchen on 2017/1/6.
 * 发现的Fragment
 */
public class CommunityFragment extends BaseFragment {
    private TextView mTextView;
    private  static final  String TAG = "TAG";
    @Override
    public View initView() {
        Log.e(TAG,"发现的Fragment的UI被初始化");
        mTextView = new TextView(mContext);

        mTextView.setGravity(Gravity.CENTER);
        mTextView.setTextSize(25);
        return mTextView;
    }

    @Override
    public void initData() {
        Log.e(TAG,"发现的Fragment的数据被初始化");
        super.initData();
        mTextView.setText("发现内容");
    }
}
