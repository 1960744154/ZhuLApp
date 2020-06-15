package com.lrb.dashixunkuang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lrb.dashixunkuang.R;
import com.lrb.dashixunkuang.view.utils.ZLImageLoader;
import com.lrb.infobean.VipListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VipListAdapter extends RecyclerView.Adapter<VipListAdapter.ViewHolder> {
    private List<VipListBean.ListBean> data ;
    private Context context;

    public VipListAdapter(List<VipListBean.ListBean> bottomList,Context context) {
        this.data=bottomList;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_vip_list, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VipListBean.ListBean listBean = data.get(position);
        holder.tvVipCourseTitle.setText(listBean.getLesson_name());

        holder.tvVipCourseCount.setText( listBean.getStudentnum()+"人学习");
        holder.tvVipCourseRate.setText(listBean.getComment_rate());
        Glide.with(context).load(listBean.getVip_thumb()).into(holder.ivVipCourseThumb);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_vip_course_thumb)
        ImageView ivVipCourseThumb;
        @BindView(R.id.cardView)
        CardView cardView;
        @BindView(R.id.tv_vip_course_title)
        TextView tvVipCourseTitle;
        @BindView(R.id.tv_vip_course_author)
        TextView tvVipCourseAuthor;
        @BindView(R.id.tv_vip_course_count)
        TextView tvVipCourseCount;
        @BindView(R.id.tv_vip_course_rate)
        TextView tvVipCourseRate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
