package com.example.shangui.shangui.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.adapter.CommonFragmentPagerAdapter;
import com.example.shangui.shangui.enity.FullImageInfo;
import com.example.shangui.shangui.enity.MessageInfo;
import com.example.shangui.shangui.fragment.ChatEmotionFragment;
import com.example.shangui.shangui.fragment.ChatFunctionFragment;
import com.example.shangui.shangui.util.Constants;
import com.example.shangui.shangui.util.GlobalOnItemClickManagerUtils;
import com.example.shangui.shangui.util.MediaManager;
import com.example.shangui.shangui.view.NoScrollViewPager;
import com.example.shangui.shangui.widget.EmotionInputDetector;
import com.example.shangui.shangui.widget.StateButton;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ChatInterfaceActivity extends AppCompatActivity {

   /* private ImageView return_im;
    private TextView name_tv;
    private EasyRecyclerView chat_list;
    private ImageView emotion_voice;
    private EditText edit_text;
    private TextView voice_text;
    private ImageView emotion_button;
    private ImageView emotion_add;
    private StateButton emotion_send;
    private NoScrollViewPager viewpager;
    private RelativeLayout emotion_layout;
    private EmotionInputDetector mDetector;
    private ChatAdapter chatAdapter;
    private boolean successFailure;
    private List<MessageInfo> messageInfos = new ArrayList<>();
    protected Handler mHandler;

    //录音相关
    int animationRes = 0;
    int res = 0;
    AnimationDrawable animationDrawable = null;
    private ImageView animView;
    private String name;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_interface);
        EventBus.getDefault().register(this);
        initView();
        initData();
        initWidget();
        receiveMessage();
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0x00001:
                        chatAdapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }

        };
    }

    private void initView() {
        return_im = (ImageView) findViewById(R.id.return_im);
        name_tv = (TextView) findViewById(R.id.name_tv);
        chat_list = (EasyRecyclerView) findViewById(R.id.chat_list);
        emotion_voice = (ImageView) findViewById(R.id.emotion_voice);
        edit_text = (EditText) findViewById(R.id.edit_text);
        voice_text = (TextView) findViewById(R.id.voice_text);
        emotion_button = (ImageView) findViewById(R.id.emotion_button);
        emotion_add = (ImageView) findViewById(R.id.emotion_add);
        emotion_send = (StateButton) findViewById(R.id.emotion_send);
        viewpager = (NoScrollViewPager) findViewById(R.id.viewpager);
        emotion_layout = (RelativeLayout) findViewById(R.id.emotion_layout);
        return_im.setOnClickListener(this);
    }

    private void initData() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        Log.i("NAME", "-->" + name);
        name_tv.setText(name);
        MessageInfo message = new MessageInfo();
        message.setMsgId(name);
        //EventBus.getDefault().post(message);
    }

    private void initWidget() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        ChatEmotionFragment chatEmotionFragment = new ChatEmotionFragment();
        fragments.add(chatEmotionFragment);
        ChatFunctionFragment chatFunctionFragment = new ChatFunctionFragment();
        fragments.add(chatFunctionFragment);
        CommonFragmentPagerAdapter adapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);
        mDetector = EmotionInputDetector.with(this)
                .setEmotionView(emotion_layout)
                .setViewPager(viewpager)
                .bindToContent(chat_list)
                .bindToEditText(edit_text)
                .bindToEmotionButton(emotion_button)
                .bindToAddButton(emotion_add)
                .bindToSendButton(emotion_send)
                .bindToVoiceButton(emotion_voice)
                .bindToVoiceText(voice_text)
                .build();

        GlobalOnItemClickManagerUtils globalOnItemClickListener = GlobalOnItemClickManagerUtils.getInstance(this);
        globalOnItemClickListener.attachToEditText(edit_text);

        chatAdapter = new ChatAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        chat_list.setLayoutManager(layoutManager);
        chat_list.setAdapter(chatAdapter);
        chat_list.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        chatAdapter.handler.removeCallbacksAndMessages(null);
                        chatAdapter.notifyDataSetChanged();
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        chatAdapter.handler.removeCallbacksAndMessages(null);
                        mDetector.hideEmotionLayout(false);
                        mDetector.hideSoftInput();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        chatAdapter.addItemClickListener(itemClickListener);
    }

    *//**
     * item点击事件
     *//*
    private ChatAdapter.onItemClickListener itemClickListener = new ChatAdapter.onItemClickListener() {
        @Override
        public void onHeaderClick(int position) {
            //点击头像
            Toast.makeText(ChatInterfaceActivity.this, "onHeaderClick", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onImageClick(View view, int position) {
            int location[] = new int[2];
            view.getLocationOnScreen(location);
            FullImageInfo fullImageInfo = new FullImageInfo();
            fullImageInfo.setLocationX(location[0]);
            fullImageInfo.setLocationY(location[1]);
            fullImageInfo.setWidth(view.getWidth());
            fullImageInfo.setHeight(view.getHeight());
            fullImageInfo.setImageUrl(messageInfos.get(position).getImageUrl());
            EventBus.getDefault().postSticky(fullImageInfo);
            startActivity(new Intent(ChatInterfaceActivity.this, FullImageActivity.class));
            overridePendingTransition(0, 0);
        }

        @Override
        public void onVoiceClick(final ImageView imageView, final int position) {
            if (animView != null) {
                animView.setImageResource(res);
                animView = null;
            }
            switch (messageInfos.get(position).getType()) {
                case 1:
                    animationRes = R.drawable.voice_left;
                    res = R.mipmap.icon_voice_left3;
                    break;
                case 2:
                    animationRes = R.drawable.voice_right;
                    res = R.mipmap.icon_voice_right3;
                    break;
            }
            animView = imageView;
            animView.setImageResource(animationRes);
            animationDrawable = (AnimationDrawable) imageView.getDrawable();
            animationDrawable.start();
            MediaManager.playSound(messageInfos.get(position).getFilepath(), new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    animView.setImageResource(res);
                }
            });
        }
    };

    *//**
     * 构造聊天数据
     *//*
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(final MessageInfo messageInfo) {
        messageInfo.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
        messageInfo.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
        messageInfo.setSendState(Constants.CHAT_ITEM_SENDING);
        Log.i("MessageInfo", String.valueOf(messageInfo));
        //文本
        final String content = messageInfo.getContent();
        //图片
        String imageUrl = messageInfo.getImageUrl();
        if (!TextUtils.isEmpty(content) || !TextUtils.isEmpty(imageUrl)) {
            messageInfos.add(messageInfo);
            chatAdapter.add(messageInfo);
        }
        if (content != null) {
            //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
            EMMessage message = EMMessage.createTxtSendMessage(content, name);
            //发送消息
            EMClient.getInstance().chatManager().sendMessage(message);
            message.setMessageStatusCallback(new EMCallBack() {
                @Override
                public void onSuccess() {
                    Log.i("TAG", "消息发送成功");
                    successFailure = true;
                }

                @Override
                public void onError(int code, String error) {
                    Log.i("TAG", "消息发送失败");
                    successFailure = false;
                }

                @Override
                public void onProgress(int progress, String status) {

                }
            });
        } else if (imageUrl != null) {
            //imagePath为图片本地路径，false为不发送原图（默认超过100k的图片会压缩后发给对方），需要发送原图传true
            EMMessage imageSendMessage = EMMessage.createImageSendMessage(imageUrl, true, name);
            EMClient.getInstance().chatManager().sendMessage(imageSendMessage);
            imageSendMessage.setMessageStatusCallback(new EMCallBack() {
                @Override
                public void onSuccess() {
                    Log.i("TAG", "消息发送成功");
                    successFailure = true;
                }

                @Override
                public void onError(int code, String error) {
                    successFailure = false;
                }

                @Override
                public void onProgress(int progress, String status) {

                }
            });
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (successFailure) {
                    messageInfo.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
                } else {
                    messageInfo.setSendState(Constants.CHAT_ITEM_SEND_ERROR);
                }
                chatAdapter.notifyDataSetChanged();
            }
        }, 1000);
        chat_list.scrollToPosition(chatAdapter.getCount() - 1);
    }

    EMMessage emMessage = null;

    private void receiveMessage() {
        EMMessageListener msgListener = new EMMessageListener() {
            @Override
            public void onMessageReceived(final List<EMMessage> messages) {
                //收到消息
                for (int i = 0; i < messages.size(); i++) {
                    Log.i("收到消息", messages.get(i) + "");
                    emMessage = messages.get(i);
                }
                if (emMessage != null) {
                    if (emMessage.getFrom().equals(name)) {
                        MessageInfo message = new MessageInfo();
                        message.setType(Constants.CHAT_ITEM_TYPE_LEFT);
                        message.setContent(emMessage.getBody().toString().trim());
                        message.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/" +
                                "8MCTb0WBFG_thumb_1413805282863.jpg");
                        messageInfos.add(message);
                        chatAdapter.add(message);
                        chat_list.scrollToPosition(chatAdapter.getCount() - 1);
                        mHandler.sendEmptyMessage(0x00001);
                    }
                }
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                //收到透传消息
            }

            @Override
            public void onMessageRead(List<EMMessage> messages) {
                //收到已读回执
            }

            @Override
            public void onMessageDelivered(List<EMMessage> message) {
                //收到已送达回执
            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                //消息状态变动
            }
        };
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }


    @Override
    public void onBackPressed() {
        if (!mDetector.interceptBackPress()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeStickyEvent(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.return_im:
                break;
        }
    }*/
}
