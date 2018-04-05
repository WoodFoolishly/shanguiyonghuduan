package com.example.shangui.shangui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.activity.RecordActivity;
import com.example.shangui.shangui.bean.RecordBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/26.
 */

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {

    private Context context;
    private List<RecordBean> list;
    private View view;

    public RecordAdapter(Context context, List<RecordBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.recordEndLocation.setText(list.get(position).getEndLocation());
        holder.recordEndTime.setText(list.get(position).getEndTime());
        holder.recordId.setText(list.get(position).getId());
        holder.recordStartLocation.setText(list.get(position).getStartLocation());
        holder.recordStartTime.setText(list.get(position).getStartTime());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RecordActivity.class);
                intent.putExtra("id",list.get(holder.getAdapterPosition()).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.record_id)
        TextView recordId;
        @BindView(R.id.record_start_time)
        TextView recordStartTime;
        @BindView(R.id.record_start_location)
        TextView recordStartLocation;
        @BindView(R.id.record_end_time)
        TextView recordEndTime;
        @BindView(R.id.record_end_location)
        TextView recordEndLocation;

        View content;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            content = itemView;
        }
    }
}
