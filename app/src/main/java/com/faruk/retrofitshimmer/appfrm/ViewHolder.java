/**
 * @author Md Faruk Hossain
 * FIXME
 */
package com.faruk.retrofitshimmer.appfrm;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.faruk.retrofitshimmer.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public ImageView imageView;

    public ViewHolder(View view) {
        super(view);

        title = (TextView) view.findViewById(R.id.tv_title);
        imageView = (ImageView) view.findViewById(R.id.iv_icon);
        view.setTag(this);
    }
}
