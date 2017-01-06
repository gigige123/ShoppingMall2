package com.bignerdranch.android.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bignerdranch.android.shoppingmall.R;
import com.bignerdranch.android.shoppingmall.home.bean.ResultBeanData;
import com.bignerdranch.android.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericchen on 2017/1/6.
 */
public class HomeFragmentAdapter extends RecyclerView.Adapter {

    //广告条幅类型
    public static final int BANNER = 0;
    //频道
    public static final int CHANNEL = 1;
    //活动
    public static final int ACTION = 2;
    //秒杀
    public static final int SECKILL = 3;
    //推荐
    public static final int RECOMMEND = 4;
    //热门
    public static final int HOT = 5;

    private int currentType = BANNER;

    private Context mContext;

    private ResultBeanData.ResultBean resultBean;

    private LayoutInflater mLayoutInflater;

    public HomeFragmentAdapter(Context context, ResultBeanData.ResultBean bean) {
        this.mContext = context;
        this.resultBean = bean;
        mLayoutInflater =  LayoutInflater.from(mContext);
    }

    /**
     * 相当于getView
     * 创建ViewHolder
     * */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       if(viewType == BANNER){
            return new BannerViewHolder(mContext,mLayoutInflater.inflate(R.layout.banner_viewpager,null));
       }
        return null;
    }

    class BannerViewHolder extends RecyclerView.ViewHolder{

        private Context mContext;
        private View itemView;
        private Banner banner;
        public BannerViewHolder(Context mContext,
                                View itemView){
                super(itemView);
            this.mContext = mContext;
            this.banner =(Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> info) {
           //设置Banner的数据
            List<String> imagesUrl = new ArrayList<>();
            for(int i = 0;i<info.size();i++){
                String imageUrl = info.get(i).getImage();
                imagesUrl.add(imageUrl);
            }
            //设置循环指示点
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //设置手风琴效果
            banner.setBannerAnimation(Transformer.Accordion);
            banner.setImages(imagesUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {

                    //联网请求图片-Glide
                    Glide.with(mContext).load(Constants.BASE_URL_IMAGE + url).into(view);

                }
            });

            //设置item的点击事件
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext,"position=="+position,Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
    /**
     * 得到类型
     * */
    @Override
    public int getItemViewType(int position) {
        switch(position){
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACTION:
                currentType = ACTION;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return super.getItemViewType(position);
    }

    /**
     * 相当于getView中的绑定数据模块
     * */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(getItemViewType(position) == BANNER){
                BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
                bannerViewHolder.setData(resultBean.getBanner_info());
            }
    }

    /**
     * 总共有多少个item
     *
     * */
    @Override
    public int getItemCount() {
        //开发过程中从1到6
        return 1;
    }
}
