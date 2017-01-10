package com.bignerdranch.android.shoppingmall.home.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.RotateDownPageTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
       }else if(viewType == CHANNEL){
            return new ChannelViewHolder(mContext,mLayoutInflater.inflate(R.layout.channel_item,null));
       }else if(viewType == ACTION){
           return new ActionViewHolder(mContext,mLayoutInflater.inflate(R.layout.act_item,null));
       }else if(viewType == SECKILL){
           return new SeckillViewHolder(mContext,mLayoutInflater.inflate(R.layout.seckill_item,null));
       }else if(viewType == RECOMMEND){
           return new RecommendViewHolder(mContext,mLayoutInflater.inflate(R.layout.recommend_item,null));
       }
        return null;
    }


    class RecommendViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        public RecommendViewHolder(final Context mContext,View itemView){
            this.mContext = mContext;
        }
    }
    class SeckillViewHolder extends RecyclerView.ViewHolder{
        private final Context mContext;
        private TextView tv_time_seckill;
        private TextView tv_more_seckill;
        private RecyclerView rv_seckill;
        private SeckillRecyclerViewAdapter adapter;

        //秒杀倒计时的相差秒数
        private long dt = 0;

        private Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                dt = dt - 1000;
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String time = sdf.format(new Date(dt));
                tv_time_seckill.setText(time);

                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0,1000);
                if(dt<=0){
                    //把消息移除
                    handler.removeCallbacksAndMessages(null);
                }
            }
        };


        public SeckillViewHolder(Context mContext,View itemView) {
            super(itemView);
            this.mContext = mContext;
            tv_time_seckill = (TextView) itemView.findViewById(R.id.tv_time_seckill);
            tv_more_seckill = (TextView) itemView.findViewById(R.id.tv_more_seckill);
            rv_seckill = (RecyclerView) itemView.findViewById(R.id.rv_seckill);
        }

        public void setData(ResultBeanData.ResultBean.SeckillInfoBean seckill_info) {
            //1.得到数据了
            //2.设置数据: 文本和RecyclerView的数据
            adapter = new SeckillRecyclerViewAdapter(mContext,seckill_info.getList());
            rv_seckill.setAdapter(adapter);

            //设置布局管理器
            rv_seckill.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            //设置item的点击事件
            adapter.setOnSeckillRecyclerView(new SeckillRecyclerViewAdapter.OnSeckillRecyclerView() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(mContext,"秒杀的位置:"+position,Toast.LENGTH_SHORT).show();
                }
            });

            //秒杀倒计时 - 毫秒

            dt = Long.valueOf(seckill_info.getEnd_time()) - Long.valueOf(seckill_info.getStart_time());
//            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//            String time = sdf.format(new Date(dt));
//            tv_time_seckill.setText(time);
            handler.sendEmptyMessageDelayed(0,1000);
        }
    }
    class ActionViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private ViewPager act_viewpager;
        public ActionViewHolder(final Context mContext,View itemView){
            super(itemView);
            this.mContext = mContext;
            act_viewpager = (ViewPager)itemView.findViewById(R.id.act_viewpager);
        }

        public void setData(final List<ResultBeanData.ResultBean.ActInfoBean> act_info) {
            act_viewpager.setPageMargin(20);
            act_viewpager.setOffscreenPageLimit(3);//>=3


//setPageTransformer 决定动画效果
            act_viewpager.setPageTransformer(true, new
                //    RotateDownPageTransformer());
                    AlphaPageTransformer(new ScaleInTransformer()) );
            //1.得到shuju
            //2.设置适配器
            act_viewpager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return act_info.size();
                }

                //view 页面 与 object instaniateItem方法返回的值是否是同一个对象
                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    ImageView imageView = new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    Glide.with(mContext).load(Constants.BASE_URL_IMAGE+act_info.get(position).getIcon_url()).into(imageView);
                    //添加到容器中 Container
                    container.addView(imageView);

                    //设置点击事件

                    return imageView;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View)object);
                }
            });
        }
    }
    class ChannelViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private GridView gv_channel;
        private ChannelAdapter adapter;

        public ChannelViewHolder(final Context mContext, View itemView){
            super(itemView);
            this.mContext = mContext;
            gv_channel = (GridView) itemView.findViewById(R.id.gv_channel);

            //设置item的点击事件
            gv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext,"position"+position,Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            //得到数据
            //设置GrideView的适配器
            adapter = new ChannelAdapter(mContext,channel_info);
            gv_channel.setAdapter(adapter);
        }
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
                    Log.e("SYS","Add");
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
        return currentType;
    }

    /**
     * 相当于getView中的绑定数据模块
     * */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(getItemViewType(position) == BANNER){
                BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
                bannerViewHolder.setData(resultBean.getBanner_info());
            }else if(getItemViewType(position) == CHANNEL){
                ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
                channelViewHolder.setData(resultBean.getChannel_info());
            }else if(getItemViewType(position) == ACTION){
                ActionViewHolder actionViewHolder = (ActionViewHolder) holder;
                actionViewHolder.setData(resultBean.getAct_info());
            }else if(getItemViewType(position) == SECKILL){
                SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
                seckillViewHolder.setData(resultBean.getSeckill_info());
            }
    }

    /**
     * 总共有多少个item
     *
     * */
    @Override
    public int getItemCount() {
        //开发过程中从1到6
        return 4;
    }
}
