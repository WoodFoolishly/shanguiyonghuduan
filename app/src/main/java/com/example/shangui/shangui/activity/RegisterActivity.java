package com.example.shangui.shangui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.base.BaseActivity;
import com.example.shangui.shangui.contract.RegisterContract;
import com.example.shangui.shangui.presenter.RegisterPresenter;
import com.example.shangui.shangui.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View {

    @BindView(R.id.register_text_phone_num)
    TextView registerTextPhoneNum;
    @BindView(R.id.register_text_name)
    TextView registerTextName;
    @BindView(R.id.register_edit_name)
    EditText registerEditName;
    @BindView(R.id.register_text_id)
    TextView registerTextId;
    @BindView(R.id.register_edit_id)
    EditText registerEditId;
    @BindView(R.id.register_password_image)
    CheckBox registerPasswordImage;
    @BindView(R.id.register_text_phone)
    TextView registerTextPhone;
    @BindView(R.id.register_edit_phone)
    EditText registerEditPhone;
    @BindView(R.id.register_confirm_password_image)
    CheckBox registerConfirmPasswordImage;
    @BindView(R.id.register_btn_next)
    Button registerBtnNext;
    @BindView(R.id.register_btn_login)
    TextView registerBtnLogin;
    @BindView(R.id.register_btn_forget)
    TextView registerBtnForget;
    @BindView(R.id.register_check_protocol)
    CheckBox registerCheckProtocol;
    @BindView(R.id.register_text_protocol)
    TextView registerTextProtocol;
    @BindView(R.id.register_password_tip)
    TextView registerPasswordTip;
    @BindView(R.id.register_toolbar)
    Toolbar registerToolbar;
    @BindView(R.id.load_text)
    TextView loadText;
    @BindView(R.id.load)
    LinearLayout load;
    @BindView(R.id.register_phone)
    View registerPhone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        setSupportActionBar(registerToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_arrow_left);
        }
        SpannableStringBuilder spannable = new SpannableStringBuilder(getResources().getString(R.string.text_protocol1));
        spannable.setSpan(new Clickable(),10,16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        registerTextProtocol.setText(spannable);
        registerTextProtocol.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    protected void initData() {
        new RegisterPresenter(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if(registerBtnNext.getText().toString().equals("下一步")){
                finish();
            }else{
                back();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private class Clickable extends ClickableSpan{

        @Override
        public void onClick(View view) {
            ToastUtils.shortTime(getApplicationContext(),"服务协议");
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(getResources().getColor(R.color.colorTitleRightBlue));
        }
    }

    @OnClick({R.id.register_password_image, R.id.register_confirm_password_image, R.id.register_btn_next, R.id.register_btn_login, R.id.register_btn_forget})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.register_password_image://是否显示注册密码
                if (registerPasswordImage.isChecked()) {
                    registerEditId.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    registerEditId.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                registerEditId.setSelection(registerEditId.getText().length());
                break;
            case R.id.register_confirm_password_image://是否显示注册确认密码
                if (registerConfirmPasswordImage.isChecked()) {
                    registerEditName.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    registerEditName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                registerEditName.setSelection(registerEditName.getText().length());
                break;
            case R.id.register_btn_next://下一步按钮，点击后验证身份成功变为注册按钮
                if(!registerCheckProtocol.isChecked()){
                    ToastUtils.shortTime(getApplicationContext(),"请同意闪柜服务协议");
                    break;
                }
                if(registerBtnNext.getText().toString().equals("下一步")){
                    presenter.register(registerEditName.getText().toString(),registerEditId.getText().toString(),registerEditPhone.getText().toString());
                }else{
                    presenter.setPassword("1234",registerEditId.getText().toString(),registerEditPhone.getText().toString());
                }
                break;
            case R.id.register_btn_login://返回登录按钮
                intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.register_btn_forget://忘记密码按钮
                intent = new Intent(RegisterActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        this.presenter = (RegisterPresenter) presenter;
    }

    @Override
    public void showLoad() {
        load.setVisibility(View.VISIBLE);
        loadText.setText("登录中...");
    }

    @Override
    public void hideLoad() {
        load.setVisibility(View.GONE);
    }

    @Override
    public void verificationSucceed(String string) {
        Toast.makeText(this,string,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void verificationFailure() {
        ToastUtils.shortTime(this,"验证失败");
    }

    @Override
    public void registerSucceed(String string) {
        ToastUtils.shortTime(this,string);
    }

    @Override
    public void registerFailure() {
        ToastUtils.shortTime(this,"注册失败");
    }

    @Override
    public void next() {
        registerEditName.setFocusable(true);
        registerEditName.setFocusableInTouchMode(true);
        registerEditName.requestFocus();
        registerPhone.setVisibility(View.VISIBLE);
        registerTextPhoneNum.setText(registerEditPhone.getText().toString());
        registerTextName.setText("验  证  码：");
        registerTextId.setText(getResources().getString(R.string.text_setting_password));
        registerTextPhone.setText(getResources().getString(R.string.text_confirm_password));
        registerEditName.setText("");
        registerEditId.setText("");
        registerEditPhone.setText("");
        registerPasswordImage.setVisibility(View.VISIBLE);
        registerBtnNext.setText(getResources().getString(R.string.text_countersign));
        registerConfirmPasswordImage.setVisibility(View.VISIBLE);
        registerCheckProtocol.setVisibility(View.GONE);
        registerTextProtocol.setVisibility(View.GONE);
        registerPasswordTip.setVisibility(View.VISIBLE);
        registerEditId.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        registerEditPhone.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    @Override
    public void back() {
        registerEditName.setFocusable(true);
        registerEditName.setFocusableInTouchMode(true);
        registerEditName.requestFocus();
        registerTextPhoneNum.setText("");
        registerPhone.setVisibility(View.GONE);
        registerTextName.setText(getResources().getString(R.string.text_name));
        registerTextId.setText(getResources().getString(R.string.text_id));
        registerTextPhone.setText(getResources().getString(R.string.text_phone_num));
        registerEditName.setText("");
        registerEditId.setText("");
        registerEditPhone.setText("");
        registerPasswordImage.setChecked(false);
        registerPasswordImage.setVisibility(View.GONE);
        registerConfirmPasswordImage.setChecked(false);
        registerConfirmPasswordImage.setVisibility(View.GONE);
        registerCheckProtocol.setVisibility(View.VISIBLE);
        registerTextProtocol.setVisibility(View.VISIBLE);
        registerBtnNext.setText(getResources().getString(R.string.text_next));
        registerPasswordTip.setVisibility(View.GONE);
        registerEditId.setInputType(InputType.TYPE_CLASS_TEXT);
        registerEditPhone.setInputType(InputType.TYPE_CLASS_PHONE);
    }

    @Override
    public void checkNull(String string) {
        ToastUtils.shortTime(this,string);
    }

    @Override
    public void onBackPressed() {
        if(registerBtnNext.getText().toString().equals("下一步")){
            super.onBackPressed();
        }else{
            back();
        }
    }
}
