package com.example.shangui.shangui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.base.BaseActivity;
import com.example.shangui.shangui.contract.ForgetPasswordContract;
import com.example.shangui.shangui.presenter.ForgetPasswordPresenter;
import com.example.shangui.shangui.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgetPasswordActivity extends BaseActivity<ForgetPasswordPresenter> implements ForgetPasswordContract.View {

    @BindView(R.id.forget_password_toolbar)
    Toolbar forgetPasswordToolbar;
    @BindView(R.id.forget_text_phone)
    TextView forgetTextPhone;
    @BindView(R.id.forget_edit_phone)
    EditText forgetEditPhone;
    @BindView(R.id.forget_password_image)
    CheckBox forgetPasswordImage;
    @BindView(R.id.forget_text_code)
    TextView forgetTextCode;
    @BindView(R.id.forget_edit_code)
    EditText forgetEditCode;
    @BindView(R.id.forget_confirm_password_image)
    CheckBox forgetConfirmPasswordImage;
    @BindView(R.id.forget_btn_code)
    EditText forgetBtnCode;
    @BindView(R.id.forget_btn_next)
    Button forgetBtnNext;
    @BindView(R.id.foeget_btn_login)
    TextView foegetBtnLogin;
    @BindView(R.id.forget_btn_forget)
    TextView forgetBtnForget;
    @BindView(R.id.load_text)
    TextView loadText;
    @BindView(R.id.load)
    LinearLayout load;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initView() {
        setSupportActionBar(forgetPasswordToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_arrow_left);
        }
    }

    @Override
    protected void initData() {
        new ForgetPasswordPresenter(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if(forgetBtnNext.getText().toString().equals("下一步")){
                finish();
            }else{
                back();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.forget_password_image, R.id.forget_confirm_password_image, R.id.forget_btn_code, R.id.forget_btn_next, R.id.foeget_btn_login, R.id.forget_btn_forget})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.forget_password_image://密码是否可见
                if (forgetPasswordImage.isChecked()) {
                    forgetEditPhone.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    forgetEditPhone.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                }
                forgetEditPhone.setSelection(forgetEditPhone.getText().length());
                break;
            case R.id.forget_confirm_password_image://确认密码是否可见
                if (forgetConfirmPasswordImage.isChecked()) {
                    forgetEditCode.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    forgetEditCode.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                }
                forgetEditCode.setSelection(forgetEditCode.getText().length());
                break;
            case R.id.forget_btn_code://获取验证码
                presenter.getCode();
                break;
            case R.id.forget_btn_next://下一步按钮操作，点击后变成确认按钮操作
                if(forgetBtnNext.getText().toString().equals("下一步")){
                    presenter.verification(forgetEditPhone.getText().toString(),forgetEditCode.getText().toString());
                }else{
                    presenter.alter(forgetEditPhone.getText().toString(),forgetEditCode.getText().toString());
                }
                break;
            case R.id.foeget_btn_login://登录按钮的操作
                intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.forget_btn_forget://注册按钮的操作
                intent = new Intent(ForgetPasswordActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void setPresenter(ForgetPasswordContract.Presenter presenter) {
        this.presenter = (ForgetPasswordPresenter) presenter;
    }

    @Override
    public void showLoad() {
        load.setVisibility(View.VISIBLE);
        loadText.setText("");
    }

    @Override
    public void hideLoad() {
        load.setVisibility(View.GONE);
    }

    @Override
    public void verificationSucceed(String string) {
        ToastUtils.shortTime(this,string);
    }

    @Override
    public void verificationFailure() {
        ToastUtils.shortTime(this,"验证失败");
    }

    @Override
    public void alterSucceed(String string) {
        ToastUtils.shortTime(this,string);
    }

    @Override
    public void alterFailure() {
        ToastUtils.shortTime(this,"修改密码失败");
    }

    @Override
    public void next() {
        forgetEditPhone.setFocusable(true);
        forgetEditPhone.setFocusableInTouchMode(true);
        forgetEditPhone.requestFocus();
        forgetBtnCode.setVisibility(View.GONE);
        forgetBtnNext.setText(getResources().getString(R.string.text_countersign));
        forgetTextPhone.setText(getResources().getString(R.string.text_setting_password));
        forgetTextCode.setText(getResources().getString(R.string.text_confirm_password));
        forgetEditPhone.setText("");
        forgetEditCode.setText("");
        forgetPasswordImage.setVisibility(View.VISIBLE);
        forgetConfirmPasswordImage.setVisibility(View.VISIBLE);
        forgetEditPhone.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        forgetEditCode.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
    }

    @Override
    public void back() {
        forgetEditPhone.setFocusable(true);
        forgetEditPhone.setFocusableInTouchMode(true);
        forgetEditPhone.requestFocus();
        forgetBtnCode.setVisibility(View.VISIBLE);
        forgetBtnNext.setText(getResources().getString(R.string.text_next));
        forgetTextPhone.setText(getResources().getString(R.string.text_phone));
        forgetTextCode.setText(getResources().getString(R.string.text_code));
        forgetEditPhone.setText("");
        forgetEditCode.setText("");
        forgetPasswordImage.setChecked(false);
        forgetPasswordImage.setVisibility(View.GONE);
        forgetConfirmPasswordImage.setChecked(false);
        forgetConfirmPasswordImage.setVisibility(View.GONE);
    }

    @Override
    public void checkNull(String string) {
        ToastUtils.shortTime(this,string);
    }

    @Override
    public void onBackPressed() {
        if(forgetBtnNext.getText().toString().equals("下一步")){
            super.onBackPressed();
        }else{
            back();
        }
    }
}
