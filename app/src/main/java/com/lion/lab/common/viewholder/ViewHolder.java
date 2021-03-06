package com.lion.lab.common.viewholder;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> sparseArray;
    private View rootView;

    public ViewHolder(View rootView) {
        super(rootView);
        this.rootView = rootView;
        this.sparseArray = new SparseArray<>();
    }

    public View getView(int id) {
        View view = sparseArray.get(id);
        if (view == null) {
            view = this.rootView.findViewById(id);
            sparseArray.put(id, view);
        }
        return view;
    }

    public ViewHolder setTag(int id, Object tag) {
        getView(id).setTag(tag);
        return this;
    }

    public ViewHolder setBackgroundColor(int id, int color) {
        getView(id).setBackgroundColor(color);
        return this;
    }

    public ViewHolder setOnClickListener(int id, View.OnClickListener onClickListener) {
        getView(id).setOnClickListener(onClickListener);
        return this;
    }

    public ViewHolder setText(int id, String text) {
        ((TextView) getView(id)).setText(text);
        return this;
    }

    public ViewHolder setImageURI(int id, String imageURI) {
        ((SimpleDraweeView) getView(id)).setImageURI(imageURI);
        return this;
    }
}
