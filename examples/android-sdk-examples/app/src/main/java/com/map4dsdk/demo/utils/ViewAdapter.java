package com.map4dsdk.demo.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.map4dsdk.demo.R;

public final class ViewAdapter extends FrameLayout {
    /**
     * Constructs a View Adapter item by inflating layout/feature.xml.
     */
    public ViewAdapter(Context context) {
        super(context);

        LayoutInflater layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.feature, this);
    }

    /**
     * Set resource id Icon item of the demo.
     *
     * @param drawableId the resource id of the description of the demo
     */
    public synchronized void setIcon(int drawableId) {
        ((ImageView) (findViewById(R.id.icon))).setImageResource(drawableId);
    }

    /**
     * Set resource id title Item of the deme.
     *
     * @param titleId the resource id
     */
    public synchronized void setTitleId(int titleId) {
        ((TextView) (findViewById(R.id.titleItem))).setText(titleId);
    }
}
