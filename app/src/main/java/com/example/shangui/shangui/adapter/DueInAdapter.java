package com.example.shangui.shangui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.activity.DueInActivity;
import com.example.shangui.shangui.bean.DueInBean;
import com.example.shangui.shangui.fragment.DueInFragment;
import com.example.shangui.shangui.view.RedPointView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/26.
 *
 */

public class DueInAdapter extends RecyclerView.Adapter<DueInAdapter.ViewHolder> {

    private List<DueInBean> list;//待收数据
    private Context context;
    private DueInFragment fragment;
//    private View view;

    public DueInAdapter(Context context, List<DueInBean> list,DueInFragment fragment) {
        this.list = list;
        this.context = context;
        this.fragment = fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_due_in, parent, false);
//        ViewHolder holder = ;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.dueInId.setText(list.get(position).getId());
        if(list.get(position).isStatus()){
            holder.dueInRed.setVisibility(View.VISIBLE);
        }else{
            holder.dueInRed.setVisibility(View.GONE);
        }
        holder.dueInReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.getPresenter().receive();
            }
        });
        holder.dueInReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.getPresenter().reject();
            }
        });
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DueInActivity.class);
                intent.putExtra("info",list.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

//    @Override
//    public void onBindViewHolder(ViewHolder holder, final int position) {
//        String advance = String.valueOf(list.get(position).getAdvance()) + context.getResources().getString(R.string.text_unit);
//        final String consume = String.valueOf(list.get(position).getConsume()) + context.getResources().getString(R.string.text_unit);
//        holder.itemDueInAdvance.setText(advance);
//        holder.itemDueInConsume.setText(consume);
//        holder.itemDueInId.setText(String.valueOf(list.get(position).getId()));
//        holder.itemDueInLocation.setText(String.valueOf(list.get(position).getLocation()));
//        holder.itemDueInMessage.setText(String.valueOf(list.get(position).getMessage()));
//        holder.itemDueInName.setText(String.valueOf(list.get(position).getName()));
//        holder.itemDueInPhone.setText(String.valueOf(list.get(position).getPhone()));
//        //接收按钮
//        holder.dueInReceive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context,"接收成功",Toast.LENGTH_SHORT).show();
//            }
//        });
//        //拒收按钮
//        holder.dueInReject.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, RejectActivity.class);
//                intent.putExtra("id",list.get(position).getId());
//                context.startActivity(intent);
//            }
//        });
//    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.due_in_id)
        TextView dueInId;
        @BindView(R.id.due_in_red)
        RedPointView dueInRed;
        @BindView(R.id.due_in_receive)
        Button dueInReceive;
        @BindView(R.id.due_in_reject)
        Button dueInReject;
        View view;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }

//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        @BindView(R.id.item_due_in_id)
//        TextView itemDueInId;
//        @BindView(R.id.item_due_in_name)
//        TextView itemDueInName;
//        @BindView(R.id.item_due_in_phone)
//        TextView itemDueInPhone;
//        @BindView(R.id.item_due_in_consume)
//        TextView itemDueInConsume;
//        @BindView(R.id.item_due_in_advance)
//        TextView itemDueInAdvance;
//        @BindView(R.id.item_due_in_location)
//        TextView itemDueInLocation;
//        @BindView(R.id.item_due_in_message)
//        TextView itemDueInMessage;
//        @BindView(R.id.due_in_receive)
//        Button dueInReceive;
//        @BindView(R.id.due_in_reject)
//        Button dueInReject;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//    }
}
