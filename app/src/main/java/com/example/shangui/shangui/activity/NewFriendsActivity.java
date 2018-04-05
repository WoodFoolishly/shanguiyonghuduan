package com.example.shangui.shangui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.base.BaseActivity;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import butterknife.BindView;

public class NewFriendsActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.return_im)
    ImageView returnIm;
    @BindView(R.id.search_ed)
    EditText searchEd;
    @BindView(R.id.contacts_ly)
    LinearLayout contactsLy;
    @BindView(R.id.liyou_ed)
    EditText liyouEd;
    @BindView(R.id.queren_bt)
    Button querenBt;

    /* 常量 */
    private final int CODE_ADD_FRIEND = 0x00001;
    protected Handler mHandler;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                switch (msg.what) {
                    case CODE_ADD_FRIEND:
                        Toast.makeText(NewFriendsActivity.this, "请求发送成功，等待对方验证", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }

        };
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_friends;
    }

    @Override
    protected void initView() {
        querenBt.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        String toAddUsername = searchEd.getText().toString().trim();
        String reason = liyouEd.getText().toString().trim();
        switch (view.getId()) {
            case R.id.queren_bt:
                try {
                    //参数为要添加的好友的username和添加理由
                    EMClient.getInstance().contactManager().addContact(toAddUsername, reason);
                    mHandler.sendEmptyMessage(CODE_ADD_FRIEND);
                    Log.i("toAddUsername", toAddUsername + "reason" + reason);
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    Log.i("TAG", "addContact-Errcode==>" + e.getErrorCode());
                }
                break;
        }
    }
}
