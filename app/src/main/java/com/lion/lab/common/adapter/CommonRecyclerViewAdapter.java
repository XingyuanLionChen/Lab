package com.lion.lab.common.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lion.lab.common.viewholder.ViewHolder;

import java.util.List;

public abstract class CommonRecyclerViewAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private List<T> data;
    private int layout;

    protected CommonRecyclerViewAdapter(List<T> data, int layout) {
        this.data = data;
        this.layout = layout;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        onBindViewHolder(holder, data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public abstract void onBindViewHolder(ViewHolder holder, T item);

    public void bindRecyclerView(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(this);
    }
}
