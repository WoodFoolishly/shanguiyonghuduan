package com.example.shangui.shangui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.base.BaseActivity;
import com.example.shangui.shangui.contract.LoginContract;
import com.example.shangui.shangui.presenter.LoginPresenter;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    @BindView(R.id.login_text_phone)
    TextView loginTextPhone;
    @BindView(R.id.login_text_password)
    TextView loginTextPassword;
    @BindView(R.id.login_edit_phone)
    EditText loginEditPhone;//电话号码
    @BindView(R.id.login_edit_password)
    EditText loginEditPassword;//密码
    @BindView(R.id.login_password_image)
    CheckBox loginPasswordImage;//密码是都可见
    @BindView(R.id.login_save_password)
    CheckBox loginSavePassword;//是否保存密码
    @BindView(R.id.login_btn)
    Button loginBtn;//登录按钮
    @BindView(R.id.login_btn_register)
    TextView loginBtnRegister;//注册按钮
    @BindView(R.id.login_btn_forget)
    TextView loginBtnForget;//忘记密码按钮
    @BindView(R.id.load)
    View load;//加载布局
    @BindView(R.id.load_text)
    TextView loadText;//加载布局文字

//    private LoginPresenter presenter;//逻辑处理

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 判断sdk是否登录成功过
        if (EMClient.getInstance().isLoggedInBefore()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        new LoginPresenter(this);
    }

    @OnClick({R.id.login_password_image, R.id.login_btn, R.id.login_btn_register, R.id.login_btn_forget,})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.login_password_image://密码是否可见
                if (loginPasswordImage.isChecked()) {
                    loginEditPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    loginEditPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                loginEditPassword.setSelection(loginEditPassword.getText().length());
                break;
            case R.id.login_btn://登录
                login();
//                presenter.login(loginEditPhone.getText().toString(), loginEditPassword.getText().toString());
                break;

            case R.id.login_btn_register://注册

                registered();
                /* Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                
                finish();*/
                break;
            case R.id.login_btn_forget://忘记密码
                break;
        }
    }


    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = (LoginPresenter) presenter;
    }

    //显示加载框
    @Override
    public void showLoad() {
        load.setVisibility(View.VISIBLE);
        loadText.setText("登录中...");
    }

    //隐藏加载框
    @Override
    public void hideLoad() {
        load.setVisibility(View.GONE);
    }

    //登录成功时提示
    @Override
    public void showSucceed(String string) {
        Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
    }

    //登录失败时提示
    @Override
    public void showFailure() {
        Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
    }


    //用户注册
    private void registered() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String userName = loginEditPhone.getText().toString().trim();
                    String password = loginEditPassword.getText().toString().trim();
                    if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(password)) {
                        Toast.makeText(LoginActivity.this, "用户名和密码不能为空", Toast.LENGTH_LONG).show();
                        return;
                    }
                    EMClient.getInstance().createAccount(userName, password);//同步方法
                    Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                } catch (final HyphenateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int errorCode = e.getErrorCode();
                            String message = e.getMessage();
                            Log.d("lzan13", String.format("sign up - errorCode:%d, errorMsg:%s", errorCode, e.getMessage()));
                            switch (errorCode) {
                                // 网络错误
                                case EMError.NETWORK_ERROR:
                                    Toast.makeText(LoginActivity.this, "网络错误 code: " + errorCode + ", message:" + message, Toast.LENGTH_LONG).show();
                                    break;
                                // 用户已存在
                                case EMError.USER_ALREADY_EXIST:
                                    Toast.makeText(LoginActivity.this, "用户已存在 code: " + errorCode + ", message:" + message, Toast.LENGTH_LONG).show();
                                    break;
                                // 参数不合法，一般情况是username 使用了uuid导致，不能使用uuid注册
                                case EMError.USER_ILLEGAL_ARGUMENT:
                                    Toast.makeText(LoginActivity.this, "参数不合法，一般情况是username 使用了uuid导致，不能使用uuid注册 code: " + errorCode + ", message:" + message, Toast.LENGTH_LONG).show();
                                    break;
                                // 服务器未知错误
                                case EMError.SERVER_UNKNOWN_ERROR:
                                    Toast.makeText(LoginActivity.this, "服务器未知错误 code: " + errorCode + ", message:" + message, Toast.LENGTH_LONG).show();
                                    break;
                                case EMError.USER_REG_FAILED:
                                    Toast.makeText(LoginActivity.this, "账户注册失败 code: " + errorCode + ", message:" + message, Toast.LENGTH_LONG).show();
                                    break;
                                default:
                                    Toast.makeText(LoginActivity.this, "ml_sign_up_failed code: " + errorCode + ", message:" + message, Toast.LENGTH_LONG).show();
                                    break;
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //用户登录
    private void login() {
        String userName = loginEditPhone.getText().toString().trim();
        String password = loginEditPassword.getText().toString().trim();
        if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "用户名和密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        //回调
        EMClient.getInstance().login(userName, password, new EMCallBack() {
            @Override
            public void onSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        EMClient.getInstance().groupManager().loadAllGroups();
                        EMClient.getInstance().chatManager().loadAllConversations();
                        Intent logIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(logIntent);
                        finish();
                        Log.d("main", "登录聊天服务器成功！");
                    }
                });
            }


            @Override
            public void onProgress(int progress, String status) {
            }

            @Override
            public void onError(final int code, final String message) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        switch (code) {
                            // 网络异常 2
                            case EMError.NETWORK_ERROR:
                                Toast.makeText(LoginActivity.this, "网络错误 code: " + code + ", message:" + message, Toast.LENGTH_LONG).show();
                                break;
                            // 无效的用户名 101
                            case EMError.INVALID_USER_NAME:
                                Toast.makeText(LoginActivity.this, "无效的用户名 code: " + code + ", message:" + message, Toast.LENGTH_LONG).show();
                                break;
                            // 无效的密码 102
                            case EMError.INVALID_PASSWORD:
                                Toast.makeText(LoginActivity.this, "无效的密码 code: " + code + ", message:" + message, Toast.LENGTH_LONG).show();
                                break;
                            // 用户认证失败，用户名或密码错误 202
                            case EMError.USER_AUTHENTICATION_FAILED:
                                Toast.makeText(LoginActivity.this, "用户认证失败，用户名或密码错误 code: " + code + ", message:" + message, Toast.LENGTH_LONG).show();
                                break;
                            // 用户不存在 204
                            case EMError.USER_NOT_FOUND:
                                Toast.makeText(LoginActivity.this, "用户不存在 code: " + code + ", message:" + message, Toast.LENGTH_LONG).show();
                                break;
                            // 无法访问到服务器 300
                            case EMError.SERVER_NOT_REACHABLE:
                                Toast.makeText(LoginActivity.this, "无法访问到服务器 code: " + code + ", message:" + message, Toast.LENGTH_LONG).show();
                                break;
                            // 等待服务器响应超时 301
                            case EMError.SERVER_TIMEOUT:
                                Toast.makeText(LoginActivity.this, "等待服务器响应超时 code: " + code + ", message:" + message, Toast.LENGTH_LONG).show();
                                break;
                            // 服务器繁忙 302
                            case EMError.SERVER_BUSY:
                                Toast.makeText(LoginActivity.this, "服务器繁忙 code: " + code + ", message:" + message, Toast.LENGTH_LONG).show();
                                break;
                            // 未知 Server 异常 303 一般断网会出现这个错误
                            case EMError.SERVER_UNKNOWN_ERROR:
                                Toast.makeText(LoginActivity.this, "未知的服务器异常 code: " + code + ", message:" + message, Toast.LENGTH_LONG).show();
                                break;
                            default:
                                Toast.makeText(LoginActivity.this, "ml_sign_in_failed code: " + code + ", message:" + message, Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                });
            }
        });
    }
}