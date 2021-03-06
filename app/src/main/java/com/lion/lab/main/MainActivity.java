package com.lion.lab.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lion.lab.R;
import com.lion.lab.base.BaseActivity;
import com.lion.lab.binder.BinderActivity;
import com.lion.lab.common.adapter.CommonRecyclerViewAdapter;
import com.lion.lab.common.viewholder.ViewHolder;
import com.lion.lab.custom_view.CustomViewActivity;
import com.lion.lab.fresco.FrescoActivity;
import com.lion.lab.http.HttpActivity;
import com.lion.lab.screen_support.ScreenSupportActivity;
import com.lion.lab.scroll_conflict.ScrollConflictActivity;
import com.lion.lab.service.ServiceActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewHolder viewHolder = new ViewHolder(getWindow().getDecorView());

        List<Lab> labs = new ArrayList<>();
        labs.add(new Lab("service", ServiceActivity.class));
        labs.add(new Lab("binder", BinderActivity.class));
        labs.add(new Lab("fresco", FrescoActivity.class));
        labs.add(new Lab("customView", CustomViewActivity.class));
        labs.add(new Lab("scrollConflict", ScrollConflictActivity.class));
        labs.add(new Lab("http", HttpActivity.class));
        labs.add(new Lab("screenSupport", ScreenSupportActivity.class));

        View.OnClickListener onClickListener =
                v -> startActivity(new Intent(MainActivity.this, ((Lab) v.getTag()).getClazz()));
        CommonRecyclerViewAdapter<Lab> commonRecyclerViewAdapter = new CommonRecyclerViewAdapter<Lab>(labs,
                R.layout.item_lab) {
            @Override
            public void onBindViewHolder(ViewHolder holder, Lab item) {
                holder.setText(R.id.tvLab, item.getTopic())
                        .setTag(R.id.llRoot, item)
                        .setOnClickListener(R.id.llRoot, onClickListener);
            }
        };
        commonRecyclerViewAdapter.bindRecyclerView((RecyclerView) viewHolder.getView(R.id.rvLabs),
                new LinearLayoutManager(this));
    }
}
