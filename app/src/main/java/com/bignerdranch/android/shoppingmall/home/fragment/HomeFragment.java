package com.bignerdranch.android.shoppingmall.home.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bignerdranch.android.shoppingmall.R;
import com.bignerdranch.android.shoppingmall.base.BaseFragment;
import com.bignerdranch.android.shoppingmall.home.adapter.HomeFragmentAdapter;
import com.bignerdranch.android.shoppingmall.home.bean.ResultBeanData;
import com.bignerdranch.android.shoppingmall.utils.Constants;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.OkHttpRequestBuilder;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ericchen on 2017/1/6.
 * 主页面的Fragment
 */
public class HomeFragment extends BaseFragment {

    private  static final  String TAG = HomeFragment.class.getSimpleName();

    private RecyclerView rvHome;
    private ImageView ib_top;
    private TextView tv_search_home;
    private TextView tv_message_home;

    private ResultBeanData.ResultBean resultBean;
    HomeFragmentAdapter adapter ;

    @Override
    public View initView() {
        Log.e(TAG,"主页面的Fragment的UI被初始化");
        View view = View.inflate(mContext, R.layout.fragment_home,null);

        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        ib_top=(ImageView)view.findViewById(R.id.ib_top);
        tv_search_home = (TextView)view.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView)view.findViewById(R.id.tv_message_home);

        //设置点击事件
        initListener();
        return view;
    }

    private void initListener() {
        //置顶的监听
        ib_top.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                rvHome.scrollToPosition(0);
            }
        });
        //搜索的监听
        tv_search_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"搜索",Toast.LENGTH_SHORT).show();
            }
        });

        //消息的监听
        tv_message_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"进入消息中心",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void initData() {
        Log.e(TAG,"主页面的Fragment的数据被初始化");
        super.initData();
        //联网请求
        getDataFromNet();


    }

    private void getDataFromNet() {
        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build().execute(new MyStringCallback());
    }

    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {
            Log.e(TAG,"Request on Before");
            super.onBefore(request, id);
        }

        @Override
        public void onAfter(int id) {
            Log.e(TAG,"Request on After : " + new Integer(id).toString());
            super.onAfter(id);
        }

        /**
             * 当联网失败的时候回调
             * */
            @Override
            public void onError(Call call, Exception e, int id) {
            Log.e(TAG,"首页请求失败=="+e.getMessage());
        }

        @Override
        public String parseNetworkResponse(Response response, int id) throws IOException {

            return super.parseNetworkResponse(response, id);

        }

        @Override
        public void onResponse(String response, int id) {
            processData(response);
        }
    }

    private void processData(String json) {
        ResultBeanData resultBeanData = JSON.parseObject(json,ResultBeanData.class);
        resultBean = resultBeanData.getResult();
        if(resultBean != null ){
            //有数据，设置适配器
            adapter = new HomeFragmentAdapter(mContext,resultBean);
            rvHome.setAdapter(adapter);
            //设置一键回页首的监听
            GridLayoutManager manager = new GridLayoutManager(mContext,1);
            //设置跨度大小的监听
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(position <= 3){
                        //隐藏
                        ib_top.setVisibility(View.GONE);
                    }else{
                        //显示
                        ib_top.setVisibility(View.VISIBLE);
                    }
                    //返回跨度的大小
                    return 1;
                }
            });
            //设置布局管理者
            rvHome.setLayoutManager(manager);
        }else{

        }


        Log.e(TAG,"解析成功=="+  resultBean.getHot_info().get(0).getName());
    }
}
