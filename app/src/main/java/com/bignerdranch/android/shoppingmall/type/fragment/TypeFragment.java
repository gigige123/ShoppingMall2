package com.bignerdranch.android.shoppingmall.type.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.android.shoppingmall.base.BaseFragment;

/**
 * Created by ericchen on 2017/1/6.
 * 购物车的Fragment
 */
public class TypeFragment extends BaseFragment {
    private TextView mTextView;
    private  static final  String TAG = "TAG";
    @Override
    public View initView() {
        Log.e(TAG,"分类的Fragment的UI被初始化");
        mTextView = new TextView(mContext);

        mTextView.setGravity(Gravity.CENTER);
        mTextView.setTextSize(25);
        return mTextView;
    }

    @Override
    public void initData() {
        Log.e(TAG,"分类的Fragment的数据被初始化");
        super.initData();
        mTextView.setText("分类内容");
    }
}
