package com.example.shangui.shangui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.example.shangui.shangui.R;
import com.example.shangui.shangui.activity.ChatActivity;
import com.example.shangui.shangui.bean.ItemBean;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;

/**
 * Created by qq on 2018/3/30 030.
 */

public class ChatListAdapter extends BaseSwipeAdapter implements View.OnClickListener {

    private int signs;
    private Context context;
    private List<ItemBean> list;
    private String nickName;
    private final String TAG = "MySwipeAdapter";
    private ItemBean positions;
    private boolean click = false;

    public ChatListAdapter(Context context, List<ItemBean> list, int signs) {
        this.context = context;
        this.list = list;
        this.signs = signs;
    }

    /**
     * 返回SwipeLayout的ID
     *
     * @param position
     * @return
     */
    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    /**
     * 绑定布局
     *
     * @param position
     * @param parent
     * @return
     */
    @Override
    public View generateView(final int position, ViewGroup parent) {
        View itemView = View.inflate(context, R.layout.friends_list_item, null);
        SwipeLayout swipeLayout = itemView.findViewById(getSwipeLayoutResourceId(position));
        // 设置滑动是否可用,默认为true
        swipeLayout.setSwipeEnabled(true);

        /**
         * 打开时调用：循环调用onStartOpen,onUpdate,onHandRelease,onUpdate,onOpen,
         * 关闭时调用：onStartClose,onUpdate,onHandRelease,onHandRelease,onUpdate,onClose
         */
        swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {
                // Log.e(TAG, "onStartOpen: ");
                click = true;
            }

            @Override
            public void onOpen(SwipeLayout layout) {
                //Log.e(TAG, "onOpen: ");
            }

            @Override
            public void onStartClose(SwipeLayout layout) {
                // Log.e(TAG, "onStartClose: ");
            }

            @Override
            public void onClose(SwipeLayout layout) {
                //Log.e(TAG, "onClose: ");
                click = false;
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                //Log.e(TAG, "onUpdate: ");
            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                //Log.e(TAG, "onHandRelease: ");
            }
        });
        // 设置为true,在当前一条item(除侧滑以外部分)点击时,可收回侧滑出来部分,默认为false
        swipeLayout.setClickToClose(true);
        // SwipeLayout单击事件,可替代ListView的OnitemClickListener事件.
        swipeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nickName = list.get(position).getNickName();
                Log.e(TAG, "->" + list.get(position).getNickName());
                positions = list.get(position);
                if (!click && signs == 1) {
                    //点击进入会话
                    Intent intent = new Intent(context, ChatActivity.class);
                    intent.putExtra("name", nickName);
                    intent.putExtra(EaseConstant.EXTRA_USER_ID, nickName);
                    intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat);
                    context.startActivity(intent);

                }
            }
        });
        return itemView;
    }

    /**
     * 绑定数据
     *
     * @param position
     * @param convertView
     */
    @Override
    public void fillValues(int position, View convertView) {
        TextView tvNickName = convertView.findViewById(R.id.tv_nickname);
        TextView tvMsg = convertView.findViewById(R.id.tv_msg);
        TextView tvSwipeOpen = convertView.findViewById(R.id.swipe_shielding);
        TextView tvSwipeDelete = convertView.findViewById(R.id.swipe_delete);
        tvNickName.setText(list.get(position).getNickName());
        tvMsg.setText(list.get(position).getMsg());
        if (signs == 2) {
            tvSwipeOpen.setText("同意");
            tvSwipeDelete.setText("拒绝");
        } else if (signs == 3) {
            tvSwipeOpen.setVisibility(View.GONE);
            tvSwipeDelete.setText("解除");
        }
        tvSwipeDelete.setOnClickListener(this);
        tvSwipeOpen.setOnClickListener(this);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ItemBean getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        try {
            switch (id) {
                case R.id.swipe_delete:
                    this.closeAllItems();
                    if (signs == 1) {
                        //删除好友
                        EMClient.getInstance().contactManager().deleteContact(nickName);
                        Toast.makeText(context, "已删除", Toast.LENGTH_SHORT).show();
                    } else if (signs == 2) {
                        EMClient.getInstance().contactManager().declineInvitation(nickName);
                        Toast.makeText(context, "拒绝添加好友", Toast.LENGTH_SHORT).show();
                    } else if (signs == 3) {
                        //解除黑名单
                        EMClient.getInstance().contactManager().removeUserFromBlackList(nickName);
                    }
                    break;
                case R.id.swipe_shielding:
                    //拉黑好友
                    this.closeAllItems();
                    if (signs == 1) {
                        //第二个参数如果为true，则把用户加入到黑名单后双方发消息时对方都收不到
                        //false则我能给黑名单的中用户发消息，但是对方发给我时我是收不到的
                        EMClient.getInstance().contactManager().addUserToBlackList(nickName, true);
                        Toast.makeText(context, "已加入黑名单", Toast.LENGTH_SHORT).show();
                    } else if (signs == 2) {
                        //同意添加好友
                        EMClient.getInstance().contactManager().acceptInvitation(nickName);
                    }
                    break;
            }
            list.remove(positions);
            notifyDataSetChanged();
        } catch (HyphenateException e) {
            e.printStackTrace();
        }

    }
}
