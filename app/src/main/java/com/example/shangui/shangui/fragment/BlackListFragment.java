package com.example.shangui.shangui.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.ListView;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.adapter.ChatListAdapter;
import com.example.shangui.shangui.base.BaseFragment;
import com.example.shangui.shangui.bean.ItemBean;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlackListFragment extends BaseFragment {

    @BindView(R.id.lv_main)
    ListView lvMain;
    private List<ItemBean> datas = new ArrayList<>();
    private List<String> BlackList = new ArrayList<>();
    public static final int UPDATE_TEXT = 3;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    ChatListAdapter swipeAdapter = new ChatListAdapter(getActivity(), datas, 3);
                    lvMain.setAdapter(swipeAdapter);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BlackList = EMClient.getInstance().contactManager().getBlackListFromServer();
                    if (BlackList != null) {
                        for (String BlackListName : BlackList) {
                            ItemBean itemBean = new ItemBean();
                            itemBean.setNickName(BlackListName);
                            itemBean.setMsg("Message");
                            datas.add(itemBean);
                            Message message = new Message();
                            message.what = UPDATE_TEXT;
                            // 将Message对象发送出去
                            handler.sendMessage(message);
                        }
                    }
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_black_list;
    }
}
