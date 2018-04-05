package com.example.shangui.shangui.activity;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RejectActivity extends BaseActivity {

    @BindView(R.id.reject_toolbar)
    Toolbar rejectToolbar;
    @BindView(R.id.reject_title)
    TextView rejectTitle;
    @BindView(R.id.reject_edit)
    EditText rejectEdit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reject;
    }

    @Override
    protected void initView() {
        setSupportActionBar(rejectToolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_arrow_left);
        }
        rejectTitle.setText(getIntent().getStringExtra("id"));
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.reject_btn)
    public void onViewClicked() {
        Toast.makeText(getApplicationContext(),rejectEdit.getText().toString(),Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
