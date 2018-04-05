package com.example.shangui.shangui.activity;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.base.BaseActivity;
import com.example.shangui.shangui.bean.DueInBean;
import com.example.shangui.shangui.contract.DueInContract;
import com.example.shangui.shangui.presenter.DueInPresenter;
import com.example.shangui.shangui.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class DueInActivity extends BaseActivity<DueInPresenter> implements DueInContract.View {

    @BindView(R.id.due_in_toolbar)
    Toolbar dueInToolbar;
    @BindView(R.id.due_in_title)
    TextView dueInTitle;
    @BindView(R.id.item_due_in_name)
    TextView itemDueInName;
    @BindView(R.id.item_due_in_phone)
    TextView itemDueInPhone;
    @BindView(R.id.item_due_in_consume)
    TextView itemDueInConsume;
    @BindView(R.id.item_due_in_advance)
    TextView itemDueInAdvance;
    @BindView(R.id.item_due_in_location)
    TextView itemDueInLocation;
    @BindView(R.id.item_due_in_message)
    TextView itemDueInMessage;
    @BindView(R.id.load_text)
    TextView loadText;
    @BindView(R.id.load)
    LinearLayout load;

    private DueInBean dueInBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_due_in;
    }

    @Override
    protected void initView() {
        setSupportActionBar(dueInToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_arrow_left);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        dueInBean = (DueInBean) getIntent().getSerializableExtra("info");
        dueInTitle.setText(dueInBean.getId());
    }

    @Override
    protected void initData() {
        itemDueInName.setText(dueInBean.getName());
        itemDueInPhone.setText(dueInBean.getPhone());
        String consume = dueInBean.getConsume() + "元";
        itemDueInConsume.setText(consume);
        String advance = dueInBean.getAdvance() + "元";
        itemDueInAdvance.setText(advance);
        itemDueInLocation.setText(dueInBean.getLocation());
        itemDueInMessage.setText(dueInBean.getMessage());
        new DueInPresenter(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.due_in_receive, R.id.due_in_reject})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.due_in_receive:
                presenter.receive();
                break;
            case R.id.due_in_reject:
                presenter.reject();
                break;
        }
    }

    @Override
    public void setPresenter(DueInContract.Presenter presenter) {
        this.presenter = (DueInPresenter) presenter;
    }

    @Override
    public void showLoad() {
        load.setVisibility(View.VISIBLE);
        loadText.setText("加载中...");
    }

    @Override
    public void hideLoad() {
        load.setVisibility(View.GONE);
    }

    @Override
    public void receiveSucceed() {
        ToastUtils.shortTime(this,"接收成功");
    }

    @Override
    public void receiveFailure() {
        ToastUtils.shortTime(this,"接收失败");

    }

    @Override
    public void rejectSucceed() {
        ToastUtils.shortTime(this,"拒收成功");
    }

    @Override
    public void rejectFailure() {
        ToastUtils.shortTime(this,"拒收失败");
    }
}
