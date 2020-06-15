package com.lrb.dashixunkuang.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lrb.dashixunkuang.R;
import com.lrb.dashixunkuang.view.design.RoundImage;
import com.lrb.infobean.BannerLiveInfo;
import com.lrb.infobean.VipBean;
import com.yiyatech.utils.newAdd.GlideUtil;
import com.yiyatech.utils.newAdd.TimeUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VipLiveAdapter extends RecyclerView.Adapter<VipLiveAdapter.ViewHolder> {



    private List<VipBean.LiveBeanX.LiveBean> vipLive;
    private Context mContext;

    public VipLiveAdapter(List<VipBean.LiveBeanX.LiveBean> vipLive, Context pContext) {
            this.vipLive = vipLive;
            mContext = pContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            return new VipLiveAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.live_adapter_layout, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VipBean.LiveBeanX.LiveBean liveBean = vipLive.get(position);
        GlideUtil.loadImage(holder.roundPhoto,liveBean.getTeacher_photo());
        holder.title.setText(liveBean.getActivityName());
        if (!TextUtils.isEmpty(liveBean.getStartTime()))holder.time.setText(TimeUtil.formatLiveTime(Long.parseLong(liveBean.getStartTime())));
    }

    @Override
    public int getItemCount() {
        return vipLive != null ? vipLive.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.round_photo)
        RoundImage roundPhoto;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.time_image)
        ImageView timeImage;
        @BindView(R.id.iv_living)
        ImageView ivLiving;
        @BindView(R.id.ll_living)
        LinearLayout llLiving;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
