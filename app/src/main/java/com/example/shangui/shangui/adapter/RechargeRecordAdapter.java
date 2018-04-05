package com.example.shangui.shangui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.bean.RechargeRecordBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/30.
 */

public class RechargeRecordAdapter extends RecyclerView.Adapter<RechargeRecordAdapter.ViewHolder> {

    private List<RechargeRecordBean> list;
    private Context context;

    public RechargeRecordAdapter(List<RechargeRecordBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recharge_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String money = "+"+list.get(position).getMoney()+"元";
        holder.itemRechargeMoney.setText(money);
        holder.itemRechargeStatus.setText(list.get(position).getStatus()?"充值成功":"充值失败");
        holder.itemRechargeTime.setText(list.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_recharge_status)
        TextView itemRechargeStatus;
        @BindView(R.id.item_recharge_time)
        TextView itemRechargeTime;
        @BindView(R.id.item_recharge_money)
        TextView itemRechargeMoney;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
