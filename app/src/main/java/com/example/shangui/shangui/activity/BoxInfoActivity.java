package com.example.shangui.shangui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.base.BaseActivity;
import com.example.shangui.shangui.bean.BoxInfoBean;
import com.example.shangui.shangui.bean.MyBoxBean;
import com.example.shangui.shangui.bean.TimeAndLocationBean;
import com.example.shangui.shangui.view.CircleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BoxInfoActivity extends BaseActivity {

    @BindView(R.id.box_info_toolbar)
    Toolbar boxInfoToolbar;
    @BindView(R.id.box_info_title)
    TextView boxInfoTitle;
    @BindView(R.id.box_info_time_location)
    LinearLayout boxInfoTimeLocation;
    @BindView(R.id.box_info_content)
    RelativeLayout boxInfoContent;
    @BindView(R.id.box_info_location)
    TextView boxInfoLocation;
    @BindView(R.id.box_info_box_num)
    TextView boxInfoBoxNum;
    @BindView(R.id.box_bottom_btn)
    LinearLayout boxBottomBtn;
    @BindView(R.id.box_info_un_disposed)
    LinearLayout boxInfoUnDisposed;
    @BindView(R.id.box_info_deliver)
    LinearLayout boxInfoDeliver;
    @BindView(R.id.box_info_convey)
    LinearLayout boxInfoConvey;
    @BindView(R.id.box_info_convey_deliver)
    LinearLayout boxInfoConveyDeliver;
    @BindView(R.id.box_info_tip_name)
    TextView boxInfoTipName;
    @BindView(R.id.box_info_tip)
    LinearLayout boxInfoTip;
    @BindView(R.id.box_info_change_location)
    TextView boxInfoChangeLocation;

    private BoxInfoBean boxInfoBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_box_info;
    }

    @Override
    protected void initView() {
        setSupportActionBar(boxInfoToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_arrow_left);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        boxInfoTitle.setText(getIntent().getStringExtra("id"));
    }

    @Override
    protected void initData() {
        List<TimeAndLocationBean> list = new ArrayList<>();
        list.add(new TimeAndLocationBean("2018-03-27 13:45:00", "华润万家"));
        list.add(new TimeAndLocationBean("2018-03-27 13:56:00", "华润万家"));
        list.add(new TimeAndLocationBean("2018-03-27 13:57:00", "万达广场"));
        boxInfoBean = new BoxInfoBean(list, "0510Aa01", "万达电影院", "小黄人", getIntent().getIntExtra("status", 0));
        for (int i = 0; i < boxInfoBean.getTLlist().size(); i++) {
            boxInfoTimeLocation.addView(getInfo(i));
        }
        boxInfoLocation.setText(boxInfoBean.getLocation());
        boxInfoBoxNum.setText(boxInfoBean.getId());
        getStatusLayout();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //不同状态显示的按钮和控件
    private void getStatusLayout() {
        switch (boxInfoBean.getStatus()) {
            case MyBoxBean.UNDISPOSED://无处理状态
                boxInfoUnDisposed.setVisibility(View.VISIBLE);
                break;
            case MyBoxBean.CONVEY://运送
                boxInfoChangeLocation.setVisibility(View.VISIBLE);//显示更换布局的文字按钮
                boxInfoConvey.setVisibility(View.VISIBLE);
                break;
            case MyBoxBean.DELIVER://转交
                boxInfoTip.setVisibility(View.VISIBLE);//显示收件人的名字
                String name = " " + boxInfoBean.getName() + " ";//收件人的名字
                boxInfoTipName.setText(name);
                boxInfoDeliver.setVisibility(View.VISIBLE);
                break;
            case MyBoxBean.DELIVER_AND_CONVEY://转交和运送
                boxInfoChangeLocation.setVisibility(View.VISIBLE);//显示更换布局的文字按钮
                boxInfoConveyDeliver.setVisibility(View.VISIBLE);
                break;
        }
    }

    //获取时间地点布局
    private LinearLayout getInfo(final int i) {
        //时间地点的布局
        LinearLayout linearLayout = new LinearLayout(this);
        //时间
        TextView time = new TextView(this);
        time.setTextColor(getResources().getColor(i == boxInfoBean.getTLlist().size() - 1 ? R.color.colorTitleRightBlue : R.color.colorInGray));
        time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        time.setGravity(Gravity.CENTER);
        time.setText(boxInfoBean.getTLlist().get(i).getTime());
        //脉冲
        CircleView circleView = new CircleView(this, i == boxInfoBean.getTLlist().size() - 1 ? R.color.colorTitleRightBlue : R.color.colorInGray, i == boxInfoBean.getTLlist().size() - 1 ? R.color.colorThinBlue : R.color.colorOutGray);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dp2px(20), dp2px(20));
        params.setMargins(dp2px(10), /*i == boxInfoBean.getTLlist().size() - 1 ? dp2px(15) : */0, dp2px(10), 0);
        circleView.setLayoutParams(params);
        //地点文本
        TextView locationText = new TextView(this);
        locationText.setTextColor(getResources().getColor(i == boxInfoBean.getTLlist().size() - 1 ? R.color.colorTitleRightBlue : R.color.colorInGray));
        locationText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        locationText.setGravity(Gravity.CENTER_VERTICAL);
        locationText.setText(boxInfoBean.getTLlist().get(i).getLocation());
//        //地点按钮
//        Button locationBtn = new Button(this);
//        locationBtn.setTextColor(getResources().getColor(R.color.colorWhite));
//        locationBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
//        locationBtn.setGravity(Gravity.CENTER);
//        locationBtn.setText(boxInfoBean.getTLlist().get(i).getLocation());
//        locationBtn.setBackground(getResources().getDrawable(R.drawable.btn_rectangle_blue));
//        locationBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(BoxInfoActivity.this, BoxLocationActivity.class);
//                intent.putExtra("id",boxInfoBean.getId());
//                intent.putExtra("location",boxInfoBean.getTLlist().get(i).getLocation());
//                startActivity(intent);
//            }
//        });
        //添加控件
        linearLayout.addView(time);
        linearLayout.addView(circleView);
        linearLayout.addView(/*i == boxInfoBean.getTLlist().size() - 1 ? locationBtn : */locationText);
        //设置每行布局的间距
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.setMargins(dp2px(20), dp2px(i == boxInfoBean.getTLlist().size() - 1 ? 10 : 20), 0, dp2px(15));
        linearLayout.setLayoutParams(layoutParams);
        return linearLayout;
    }

    private int dp2px(float dpValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @OnClick({R.id.box_info_location_layout, R.id.box_info_change_location, R.id.un_disposed_unlocking, R.id.un_disposed_finish, R.id.un_disposed_deliver, R.id.un_disposed_convey, R.id.deliver_cancel, R.id.deliver_convey, R.id.convey_deliver, R.id.convey_cancel, R.id.cancel_deliver, R.id.cancel_convey})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.box_info_location_layout:
                intent = new Intent(BoxInfoActivity.this, BoxLocationActivity.class);
                intent.putExtra("id",boxInfoBean.getId());
                intent.putExtra("location",boxInfoBean.getLocation());
                startActivity(intent);
                break;
            case R.id.box_info_change_location://更换目的地
                intent = new Intent(BoxInfoActivity.this, ConveyAddressActivity.class);
                startActivity(intent);
                break;
            case R.id.un_disposed_unlocking://无状态时开锁按钮
                break;
            case R.id.un_disposed_finish://无状态时退箱关门按钮
                break;
            case R.id.un_disposed_deliver://无状态时转交按钮
                intent = new Intent(BoxInfoActivity.this, DeliverActivity.class);
                startActivity(intent);
                break;
            case R.id.un_disposed_convey://无状态时运送按钮
                intent = new Intent(BoxInfoActivity.this, ConveyActivity.class);
                intent.putExtra("id", boxInfoBean.getId());
                startActivity(intent);
                break;
            case R.id.deliver_cancel://转交时取消按钮
                break;
            case R.id.deliver_convey://转交时运送按钮
                intent = new Intent(BoxInfoActivity.this, ConveyActivity.class);
                intent.putExtra("id", boxInfoBean.getId());
                startActivity(intent);
                break;
            case R.id.convey_deliver://运送时转交按钮
                intent = new Intent(BoxInfoActivity.this, DeliverActivity.class);
                startActivity(intent);
                break;
            case R.id.convey_cancel://运送时取消按钮
                break;
            case R.id.cancel_deliver://运送和转交时的取消转交按钮
                break;
            case R.id.cancel_convey://运送和转交时的取消运送按钮
                break;
        }
    }

}
