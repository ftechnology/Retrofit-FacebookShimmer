package com.faruk.retrofitshimmer.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.faruk.retrofitshimmer.R;

import com.faruk.retrofitshimmer.appfrm.AbsRecycleViewBaseAdapter;
import com.faruk.retrofitshimmer.appfrm.Constants;
import com.faruk.retrofitshimmer.appfrm.NotifyObserver;
import com.faruk.retrofitshimmer.appfrm.ResponseObject;
import com.faruk.retrofitshimmer.appfrm.ViewHolder;
import com.faruk.retrofitshimmer.data.PetsData;
import com.faruk.retrofitshimmer.model.PetsDataModel;
import com.faruk.retrofitshimmer.utility.Utils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PetsListAdapter extends AbsRecycleViewBaseAdapter {

    private ArrayList<PetsData> mDataList;
    private Context mContext;

    public static final int LIST_IMAGE_WIDTH = 150;
    public static final int LIST_IMAGE_HEIGHT = 150;

    // Constructor with required parameter
    public PetsListAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    protected void init() {

    }

    public void setmNotifyObserver(NotifyObserver notifyObserver) {
        this.mNotifyObserver = notifyObserver;
    }

    public void setDataList(ArrayList<PetsData> dataList) {
        this.mDataList = dataList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_layout, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        PetsData data = mDataList.get(i);

        holder.title.setText(data.getTitle());

        Glide.with(mContext)
                .load(data.getImage_url())
                .placeholder(Utils.getProgressBarIndeterminate(mContext))
                .error(R.drawable.ic_error_image)
                .override(LIST_IMAGE_WIDTH, LIST_IMAGE_HEIGHT)
                .into(holder.imageView);
    }


    @Override
    public ResponseObject loadData() {

        PetsDataModel.getInstance(mContext).executeAsyn(new NotifyObserver() {
            @Override
            public void update(ResponseObject response) {
                if (response != null && mNotifyObserver != null) {
                    int code = response.getResponseCode();
                    mNotifyObserver.update(new ResponseObject(response.getResponseCode(), "", null));
                    if (code == Constants.CODE_SUCCESS) {
                        setDataList(PetsDataModel.getInstance(mContext).getPetsArrayList());
                        notifyDataSetChanged();
                    }

                }
            }
        });
        return null;
    }

    // Return the size of dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }


}