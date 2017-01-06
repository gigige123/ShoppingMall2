package com.bignerdranch.android.shoppingmall.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bignerdranch.android.shoppingmall.R;
import com.bignerdranch.android.shoppingmall.base.BaseFragment;
import com.bignerdranch.android.shoppingmall.community.fragment.CommunityFragment;
import com.bignerdranch.android.shoppingmall.home.fragment.HomeFragment;
import com.bignerdranch.android.shoppingmall.shoppingcart.fragment.CartFragment;
import com.bignerdranch.android.shoppingmall.type.fragment.TypeFragment;
import com.bignerdranch.android.shoppingmall.user.fragment.UserFragment;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;


import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {


    /*
    * 装多个Fragment的实例集合
    * **/
    private ArrayList<BaseFragment> fragments;
    //选中的Fragment位置
    private int position = 0;

    private BaseFragment tempFragment;

    @Bind(R.id.frameLayout)
    FrameLayout mFrameLayout;
    @Bind(R.id.rb_home)
    RadioButton mRbHome;
    @Bind(R.id.rb_type)
    RadioButton mRbType;
    @Bind(R.id.rb_community)
    RadioButton mRbCommunity;
    @Bind(R.id.rb_cart)
    RadioButton mRbCart;
    @Bind(R.id.rb_user)
    RadioButton mRbUser;
    @Bind(R.id.rg_main)
    RadioGroup mRgMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        /**
         * 初始化Fragment
         * */
        initFragment();
        //设置RadioGroup的监听
        initListener();
        mRgMain.check(R.id.rb_home);
      
    }

    private void  initListener(){
        mRgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home://主页
                        position = 0;
                        break;
                    case R.id.rb_type://分类
                        position = 1;
                        break;
                    case R.id.rb_community://发现
                        position = 2;
                        break;
                    case R.id.rb_cart://购物车
                        position = 3;
                        break;
                    case R.id.rb_user://用户中心
                        position = 4;
                        break;
                    default:
                        position=0;
                        break;
                }

                //根据位置取得不同，得到对应的Fragment
                BaseFragment baseFragment = getFragment(position);

                Log.e("TAG",baseFragment.toString());
                //第一个参数：上次显示的Fragment，第二个参数：当前要显示的Fragement
                switchFragment(tempFragment,baseFragment);


            }
        });
    }

    /***
     * 切换Fragement
     * **/
    private void switchFragment(Fragment fromFragment,BaseFragment nextFragment){
        if(tempFragment != nextFragment){
            tempFragment = nextFragment;
            if(nextFragment != null){
               FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if(!nextFragment.isAdded()){
                    //隐藏当前Fragment
                    if(fromFragment != null){
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    //隐藏当前Fragment
                    if(fromFragment != null){
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }
    private BaseFragment getFragment(int position){
        if(fragments != null && fragments.size() > 0){
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }
    /**
     * 添加的时候要按照顺序
     * */
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new CartFragment());
        fragments.add(new UserFragment());
    }
}
