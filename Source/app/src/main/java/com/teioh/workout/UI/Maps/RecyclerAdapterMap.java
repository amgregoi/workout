package com.teioh.workout.UI.Maps;

import android.support.v7.widget.RecyclerView;

public interface RecyclerAdapterMap
{
    void registerAdapter(RecyclerView.Adapter mAdapter, RecyclerView.LayoutManager layout, boolean needItemDecoration);
}
