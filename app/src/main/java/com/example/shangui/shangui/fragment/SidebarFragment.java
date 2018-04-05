package com.example.shangui.shangui.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SidebarFragment extends BaseFragment {

    @BindView(R.id.sidebar_layout)
    LinearLayout sidebarLayout;
    private List<String> list;
//    private List<TextView> titles;

    public static final String ARGUMENT = "argument";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            list = bundle.getStringArrayList(ARGUMENT);
        }
    }

    public static SidebarFragment newInstance(List<String> list) {
        SidebarFragment sidebarFragment = new SidebarFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(ARGUMENT, (ArrayList<String>) list);
        return sidebarFragment;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        if (list != null) {
//            titles = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                sidebarLayout.addView(getTextView(list.get(i)));
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sidebar;
    }

    private TextView getTextView(String title) {
        TextView textView = new TextView(getContext());
        textView.setText(title);
        textView.setBackgroundResource(R.drawable.btn_red);
        textView.setEms(title.equals("+")?1:2);
        textView.setTextSize(title.equals("+")?61:50);
        textView.setTypeface(Typeface.defaultFromStyle(title.equals("+")? Typeface.BOLD:Typeface.NORMAL));
        textView.setTextColor(getResources().getColor(R.color.colorBlack));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,30,0,0);
        textView.setLayoutParams(params);
        return textView;
    }

}
