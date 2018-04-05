package com.example.shangui.shangui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.adapter.DueInAdapter;
import com.example.shangui.shangui.base.BaseFragment;
import com.example.shangui.shangui.bean.DueInBean;
import com.example.shangui.shangui.contract.DueInContract;
import com.example.shangui.shangui.presenter.DueInPresenter;
import com.example.shangui.shangui.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DueInFragment extends BaseFragment implements DueInContract.View {

    @BindView(R.id.due_in_recycler)
    RecyclerView dueInRecycler;
    @BindView(R.id.load_text)
    TextView loadText;
    @BindView(R.id.load)
    LinearLayout load;

    private DueInAdapter adapter;
    private List<DueInBean> list;
    private DueInPresenter presenter;

    public DueInFragment() {
        // Required empty public constructor
    }

    public static DueInFragment newInstance() {
        return new DueInFragment();
    }

    @Override
    protected void initData() {
        new DueInPresenter(this);
        DueInBean dueInBean = new DueInBean("8125AA1234", "张三", "12345678970", 0.0, 0.0, "棠德小区北", "这是水果哦！");
        dueInBean.setStatus(false);
        list.add(dueInBean);
        list.add(dueInBean);
        dueInBean.setStatus(true);
        list.add(dueInBean);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        dueInRecycler.setLayoutManager(linearLayoutManager);
        adapter = new DueInAdapter(getActivity(), list,this);
        dueInRecycler.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_due_in;
    }

    @Override
    public void setPresenter(DueInContract.Presenter presenter) {
        this.presenter = (DueInPresenter)presenter;
    }

    public DueInPresenter getPresenter() {
        return presenter;
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
        ToastUtils.shortTime(getActivity(),"接收成功");
    }

    @Override
    public void receiveFailure() {
        ToastUtils.shortTime(getActivity(),"接收失败");

    }

    @Override
    public void rejectSucceed() {
        ToastUtils.shortTime(getActivity(),"拒收成功");

    }

    @Override
    public void rejectFailure() {
        ToastUtils.shortTime(getActivity(),"拒收失败");

    }
}
