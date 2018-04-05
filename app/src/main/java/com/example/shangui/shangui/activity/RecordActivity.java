package com.example.shangui.shangui.activity;

import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.base.BaseActivity;
import com.example.shangui.shangui.bean.BoxInfoBean;
import com.example.shangui.shangui.bean.TimeAndLocationBean;
import com.example.shangui.shangui.view.CircleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RecordActivity extends BaseActivity {

    @BindView(R.id.record_toolbar)
    Toolbar recordToolbar;
    @BindView(R.id.record_title)
    TextView recordTitle;
    @BindView(R.id.record_content)
    RelativeLayout recordContent;
    @BindView(R.id.record_time_location)
    LinearLayout recordTimeLocation;

    private BoxInfoBean boxInfoBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_record;
    }

    @Override
    protected void initView() {
        setSupportActionBar(recordToolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_arrow_left);
        }
        recordTitle.setText(getIntent().getStringExtra("id"));
    }

    @Override
    protected void initData() {
        List<TimeAndLocationBean> list = new ArrayList<>();
        list.add(new TimeAndLocationBean("2018-03-27 13:45:00", "华润万家"));
        list.add(new TimeAndLocationBean("2018-03-27 13:56:00", "华润万家"));
        list.add(new TimeAndLocationBean("2018-03-27 13:57:00", "万达广场"));
        boxInfoBean = new BoxInfoBean(list, "0510Aa01", "万达电影院", "小黄人", getIntent().getIntExtra("status", 0));
        boxInfoBean.setEndName("小灰灰");
        boxInfoBean.setStartName("小黄人");
        for (int i = 0; i < boxInfoBean.getTLlist().size(); i++) {
            recordTimeLocation.addView(getInfo(i));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //获取时间地点布局
    private LinearLayout getInfo(int i) {
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
        params.setMargins(dp2px(10), 0, dp2px(10), 0);
        circleView.setLayoutParams(params);
        //地点文本
        TextView locationText = new TextView(this);
        locationText.setTextColor(getResources().getColor(i==boxInfoBean.getTLlist().size()-1?R.color.colorTitleRightBlue:R.color.colorInGray));
        locationText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        locationText.setGravity(Gravity.CENTER_VERTICAL);
        locationText.setText(boxInfoBean.getTLlist().get(i).getLocation());
        //添加控件
        linearLayout.addView(time);
        linearLayout.addView(circleView);
        linearLayout.addView(locationText);
        if(i==0){//一开始时持有人的名字文本
            TextView startName = new TextView(this);
            startName.setTextColor(getResources().getColor(R.color.colorBlack));
            startName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            startName.setGravity(Gravity.CENTER_VERTICAL);
            startName.setText(boxInfoBean.getStartName());
            startName.setGravity(Gravity.END);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            startName.setLayoutParams(layoutParams);
            linearLayout.addView(startName);
        }else if(i==boxInfoBean.getTLlist().size()-1){//最后是持有人的名字文本
            TextView endName = new TextView(this);
            endName.setTextColor(getResources().getColor(R.color.colorBlack));
            endName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            endName.setGravity(Gravity.CENTER_VERTICAL);
            String string = "转交"+boxInfoBean.getEndName();
            endName.setText(string);
            endName.setGravity(Gravity.END);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            endName.setLayoutParams(layoutParams);
            linearLayout.addView(endName);
        }
        //设置每行布局的间距
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.setMargins(dp2px(20), dp2px(20), dp2px(20), dp2px(15));
        linearLayout.setLayoutParams(layoutParams);
        return linearLayout;
    }

    private int dp2px(float dpValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
