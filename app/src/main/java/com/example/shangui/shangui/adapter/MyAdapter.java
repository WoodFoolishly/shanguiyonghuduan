package com.example.shangui.shangui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.activity.BoxInfoActivity;
import com.example.shangui.shangui.bean.MyBoxBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/26.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<MyBoxBean> list;

    public MyAdapter(Context context, List<MyBoxBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MyBoxBean.UNDISPOSED:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_undisposed, parent, false),viewType);
            case MyBoxBean.CONVEY:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_single, parent, false),viewType);
            case MyBoxBean.DELIVER:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_single, parent, false),viewType);
            case MyBoxBean.DELIVER_AND_CONVEY:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_double, parent, false),viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.myId.setText(list.get(position).getId());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BoxInfoActivity.class);
                intent.putExtra("status",getItemViewType(holder.getAdapterPosition()));
                intent.putExtra("id",list.get(holder.getAdapterPosition()).getId());
                context.startActivity(intent);
            }
        });
        switch (getItemViewType(position)) {
            case MyBoxBean.UNDISPOSED:
                holder.myUnlocking.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context,"开锁成功",Toast.LENGTH_SHORT).show();
                    }
                });
                holder.myFinish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context,"退箱成功",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case MyBoxBean.CONVEY:
                holder.mySingleStatus.setText("运送中");
                holder.mySingleCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context,"取消成功",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case MyBoxBean.DELIVER:
                holder.mySingleStatus.setText("转交中");
                holder.mySingleCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context,"取消成功",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case MyBoxBean.DELIVER_AND_CONVEY:
                holder.myDoubleCancelConvey.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context,"取消运送成功",Toast.LENGTH_SHORT).show();
                    }
                });
                holder.myDoubleCancelDeliver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context,"取消转交成功",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView myId;
//        @BindView(R.id.my_unlocking)
        Button myUnlocking;
//        @BindView(R.id.my_finish)
        Button myFinish;
//        @BindView(R.id.my_single_status)
        TextView mySingleStatus;
//        @BindView(R.id.my_single_cancel)
        Button mySingleCancel;
//        @BindView(R.id.my_double_cancel_convey)
        Button myDoubleCancelConvey;
//        @BindView(R.id.my_double_cancel_deliver)
        Button myDoubleCancelDeliver;
        View view;

        public ViewHolder(View itemView,int type) {
            super(itemView);
//            ButterKnife.bind(this,itemView);
            myId = (TextView)itemView.findViewById(R.id.my_id);
            view = itemView;
            switch (type) {
                case MyBoxBean.UNDISPOSED:
                    myUnlocking = (Button)itemView.findViewById(R.id.my_unlocking);
                    myFinish = (Button)itemView.findViewById(R.id.my_finish);
                    break;
                case MyBoxBean.CONVEY:
                case MyBoxBean.DELIVER:
                    mySingleStatus = (TextView)itemView.findViewById(R.id.my_single_status);
                    mySingleCancel = (Button)itemView.findViewById(R.id.my_single_cancel);
                    break;
                case MyBoxBean.DELIVER_AND_CONVEY:
                    myDoubleCancelConvey = (Button)itemView.findViewById(R.id.my_double_cancel_convey);
                    myDoubleCancelDeliver = (Button)itemView.findViewById(R.id.my_double_cancel_deliver);
                    break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getStatus();
    }
}
