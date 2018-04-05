package com.example.shangui.shangui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.adapter.ChatListAdapter;
import com.example.shangui.shangui.base.BaseFragment;
import com.example.shangui.shangui.bean.ItemBean;
import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*陌生人*/
public class StrangerFragment extends BaseFragment {

    @BindView(R.id.lv_main)
    ListView lvMain;
    private List<ItemBean> datas = new ArrayList<>();

    public static final int UPDATE_TEXT = 2;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    ChatListAdapter swipeAdapter = new ChatListAdapter(getActivity(), datas, 2);
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
        EMClient.getInstance().contactManager().setContactListener(new EMContactListener() {
            @Override
            public void onContactAdded(final String username) {
                //好友请求被同意
                Log.i("TAG", "onContactAgreed==>" + username);
            }

            @Override
            public void onContactDeleted(String username) {
                //好友请求被拒绝
            }

            @Override
            public void onContactInvited(final String username, final String reason) {
                //收到好友邀请
                Log.i("收到好友邀请", username + "onContactInvited==>" + reason);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ItemBean itemBean = new ItemBean();
                        itemBean.setNickName(username);
                        itemBean.setMsg(reason);
                        datas.add(itemBean);
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
            }

            @Override
            public void onFriendRequestAccepted(String username) {
                //被删除时回调此方法
            }

            @Override
            public void onFriendRequestDeclined(final String username) {
                Log.i("增加了联系人", username);
                //增加了联系人时回调此方法
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_stranger;
    }
}
