package com.bignerdranch.android.shoppingmall.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.shoppingmall.R;
import com.bignerdranch.android.shoppingmall.home.adapter.HomeFragmentAdapter;
import com.bignerdranch.android.shoppingmall.home.bean.GoodsBean;


/**
 * Created by ericchen on 2017/1/10.
 */
public class GoodsInfoActivity extends Activity implements View.OnClickListener {

    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoStore;
    private TextView tvGoodInfoStyle;
    private WebView wbGoodInfoMore;
    private LinearLayout llGoodsRoot;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;
    private TextView tvMoreShare;
    private TextView tvMoreSearch;
    private TextView tvMoreHome;
    private Button btnMore;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-01-10 16:10:31 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        tvMoreShare =(TextView)findViewById( R.id.tv_more_share );
        tvMoreSearch = (TextView)findViewById( R.id.tv_more_search );
        tvMoreHome = (TextView)findViewById( R.id.tv_more_home );
        btnMore = (Button)findViewById( R.id.btn_more );
        ibGoodInfoBack = (ImageButton)findViewById( R.id.ib_good_info_back );
        ibGoodInfoMore = (ImageButton)findViewById( R.id.ib_good_info_more );
        ivGoodInfoImage = (ImageView)findViewById( R.id.iv_good_info_image );
        tvGoodInfoName = (TextView)findViewById( R.id.tv_good_info_name );
        tvGoodInfoDesc = (TextView)findViewById( R.id.tv_good_info_desc );
        tvGoodInfoPrice = (TextView)findViewById( R.id.tv_good_info_price );
        tvGoodInfoStore = (TextView)findViewById( R.id.tv_good_info_store );
        tvGoodInfoStyle = (TextView)findViewById( R.id.tv_good_info_style );
        wbGoodInfoMore = (WebView)findViewById( R.id.wb_good_info_more );
        llGoodsRoot = (LinearLayout)findViewById( R.id.ll_goods_root );
        tvGoodInfoCallcenter = (TextView)findViewById( R.id.tv_good_info_callcenter );
        tvGoodInfoCollection = (TextView)findViewById( R.id.tv_good_info_collection );
        tvGoodInfoCart = (TextView)findViewById( R.id.tv_good_info_cart );
        btnGoodInfoAddcart = (Button)findViewById( R.id.btn_good_info_addcart );

        ibGoodInfoBack.setOnClickListener( this );
        ibGoodInfoMore.setOnClickListener( this );
        btnGoodInfoAddcart.setOnClickListener( this );
        tvGoodInfoCallcenter.setOnClickListener( this );
        tvGoodInfoCart.setOnClickListener( this );
        tvGoodInfoCollection.setOnClickListener( this );
        tvMoreShare.setOnClickListener( this );
        tvMoreSearch.setOnClickListener( this );
        tvMoreHome.setOnClickListener( this );
        btnMore.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-01-10 16:10:31 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == ibGoodInfoBack ) {
            // Handle clicks for ibGoodInfoBack
            Log.e("TAG","View : "+ ibGoodInfoBack.toString());
            finish();
        } else if ( v == ibGoodInfoMore ) {
            // Handle clicks for ibGoodInfoMore
            Toast.makeText(this,"更多:",Toast.LENGTH_SHORT).show();

        } else if ( v == btnGoodInfoAddcart ) {
            // Handle clicks for btnGoodInfoAddcart
            Toast.makeText(this,"添加到购物车:",Toast.LENGTH_SHORT).show();
        }else if ( v == tvGoodInfoCallcenter ) {
            // Handle clicks for btnGoodInfoAddcart
            Toast.makeText(this,"客户中心:",Toast.LENGTH_SHORT).show();
        }else if ( v == tvGoodInfoCart ) {
            // Handle clicks for btnGoodInfoAddcart
            Toast.makeText(this,"收藏:",Toast.LENGTH_SHORT).show();
        }else if ( v == tvGoodInfoCollection ) {
            // Handle clicks for btnGoodInfoAddcart
            Toast.makeText(this,"购物车:",Toast.LENGTH_SHORT).show();
        }else if ( v == tvMoreShare ) {
            // Handle clicks for btnGoodInfoAddcart
            Toast.makeText(this,"更多分享:",Toast.LENGTH_SHORT).show();
        }else if ( v == tvMoreSearch ) {
            // Handle clicks for btnGoodInfoAddcart
            Toast.makeText(this,"更多搜索:",Toast.LENGTH_SHORT).show();
        }else if ( v == tvMoreHome ) {
            // Handle clicks for btnGoodInfoAddcart
            Toast.makeText(this,"更多主页:",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        findViews();

        //接收数据
        GoodsBean goodsBean = (GoodsBean)getIntent().getSerializableExtra("goodsBean");
        if(goodsBean != null){
            Toast.makeText(this,"goodsBean=="+goodsBean.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
