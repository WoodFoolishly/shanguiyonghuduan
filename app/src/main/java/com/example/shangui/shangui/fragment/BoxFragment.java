package com.example.shangui.shangui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class BoxFragment extends BaseFragment implements ViewTreeObserver.OnGlobalLayoutListener {


    @BindView(R.id.box_sidebar_layout)
    RelativeLayout boxSidebarLayout;
    @BindView(R.id.sidebar_due_in)
    RadioButton radioButton;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    //    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment currentFragment;
    private DueInFragment dueInFragment;
    private RecordFragment recordFragment;
    private MyFragment myFragment;
    private boolean flag = true;

    public BoxFragment() {
        // Required empty public constructor
    }

    public static BoxFragment newInstance() {
//        BoxFragment fragment = new BoxFragment();
        return new BoxFragment();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        toolbarTitle.setText("箱子");
        fragmentTransaction = getFragmentManager().beginTransaction();
        dueInFragment = DueInFragment.newInstance();
        recordFragment = RecordFragment.newInstance();
        myFragment = MyFragment.newInstance();
        currentFragment = myFragment;
        fragmentTransaction.add(R.id.box_content, currentFragment).commit();
        radioButton.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_box;
    }

    @OnClick({R.id.sidebar_my, R.id.sidebar_due_in, R.id.sidebar_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sidebar_my:
                switchFragment(myFragment);
                break;
            case R.id.sidebar_due_in:
                switchFragment(dueInFragment);
                break;
            case R.id.sidebar_record:
                switchFragment(recordFragment);
                break;
        }
    }

    private void switchFragment(Fragment in) {
        if (in != currentFragment) {
            fragmentTransaction = getFragmentManager().beginTransaction();
            if (in.isAdded()) {
                fragmentTransaction.hide(currentFragment).show(in).commit();
            } else {
                fragmentTransaction.hide(currentFragment).add(R.id.box_content, in).commit();
            }
            currentFragment = in;
        }
    }

    @Override
    public void onGlobalLayout() {
        if(flag){
            TextView textView = new TextView(getActivity());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(radioButton.getRight()-15,radioButton.getTop()-15,0,0);
            textView.setLayoutParams(params);
            textView.setText("");
            boxSidebarLayout.addView(textView);
            flag = false;
        }
    }
}
