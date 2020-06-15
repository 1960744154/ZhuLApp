package com.lrb.dashixunkuang.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lrb.dashixunkuang.R;
import com.lrb.dashixunkuang.view.design.BannerLayout;
import com.lrb.infobean.BannerLiveInfo;
import com.lrb.infobean.LiveBean;
import com.lrb.infobean.VipBean;
import com.lrb.infobean.VipListBean;

import java.util.List;

public class VipHomeAdapter extends RecyclerView.Adapter<VipHomeAdapter.ViewHolder> {

    private List<VipListBean.ListBean> bottomList;
    private List<String> bannerData;
    private List<VipBean.LiveBeanX.LiveBean> liveData;
    private Activity mContext;


    public VipHomeAdapter(List<VipListBean.ListBean> pBottomList, List<String> pBannerData, List<VipBean.LiveBeanX.LiveBean> pLiveData, Activity pContext) {
        bottomList = pBottomList;
        bannerData = pBannerData;
        liveData = pLiveData;
        mContext = pContext;
    }

    private final int BANNER = 1, LIVE = 2, GRID = 3;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @LayoutRes int layoutId = 0;
        if (viewType == BANNER) {
            layoutId = R.layout.item_homepage_ad;
        } else if (viewType == LIVE) {
            layoutId = R.layout.live_recycler_item;
        } else if(viewType==GRID){
            layoutId = R.layout.ite_vip_grid_list;
        }
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false), viewType);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            holder.banner.attachActivity(mContext);
            if (bannerData.size() != 0) holder.banner.setViewUrls(bannerData);
        } else if (getItemViewType(position) == LIVE) {
            LinearLayoutManager manager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
            holder.recyclerView.setLayoutManager(manager);
            VipLiveAdapter vipLiveAdapter = new VipLiveAdapter(liveData, mContext);
            holder.recyclerView.setAdapter(vipLiveAdapter);
        } else {
            if (bottomList.size() == 0) return;
             bottomList.get(liveData == null || liveData.size() == 0 ? position - 1 : position - 2);
            holder.gridRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
            VipListAdapter vipListAdapter = new VipListAdapter(bottomList, mContext);
            holder.gridRecyclerView.setAdapter(vipListAdapter);
        }
    }

    @Override
    public int getItemViewType(int position) {
        int type = GRID;
        if (position == 0) type = BANNER;
        else if (liveData != null && liveData.size() != 0 && position == 1) type = LIVE;
        else {
            if (bottomList != null && bottomList.size() != 0) type = GRID;
        }

        return type;
    }


    @Override
    public int getItemCount() {
        return liveData != null && liveData.size() != 0 ?  3 : 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        BannerLayout banner;

        RecyclerView recyclerView;

        RecyclerView gridRecyclerView;
        public ViewHolder(@NonNull View itemView, int type) {
            super(itemView);
            if (type == BANNER) {
                banner = itemView.findViewById(R.id.banner_main);
            } else if (type == LIVE) {
                recyclerView = itemView.findViewById(R.id.recyclerView);
            } else if (type == GRID) {
                gridRecyclerView = itemView.findViewById(R.id.vip_recycler);
            }
        }
    }
}
