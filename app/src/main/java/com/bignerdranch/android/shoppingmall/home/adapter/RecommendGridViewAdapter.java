package com.bignerdranch.android.shoppingmall.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.shoppingmall.R;
import com.bignerdranch.android.shoppingmall.home.bean.ResultBeanData;
import com.bignerdranch.android.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by ericchen on 2017/1/10.
 */
public class RecommendGridViewAdapter  extends BaseAdapter{
    private final Context mContext;
    private final  List<ResultBeanData.ResultBean.RecommendInfoBean> datas;
    public RecommendGridViewAdapter(Context context, List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
        this.mContext = context;
        this.datas = recommend_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
       if(convertView == null){
           convertView = View.inflate(mContext, R.layout.item_recommend_grid_view,null);
           viewHolder = new ViewHolder();
           viewHolder.iv_recommend = (ImageView) convertView.findViewById(R.id.iv_recommend);
           viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
           viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
           convertView.setTag(viewHolder);
       }else{
           viewHolder = (ViewHolder)convertView.getTag();
       }
        //根据位置得到对应的数据
        ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean = datas.get(position);
        String imageUrl = Constants.BASE_URL_IMAGE+recommendInfoBean.getFigure();
        Glide.with(mContext).load(imageUrl).into(viewHolder.iv_recommend);

        viewHolder.tv_name.setText(recommendInfoBean.getName());
        viewHolder.tv_price.setText("￥"+recommendInfoBean.getCover_price());

        return convertView;
    }

    static class ViewHolder{
        ImageView iv_recommend;
        TextView tv_name;
        TextView tv_price;
    }
}
