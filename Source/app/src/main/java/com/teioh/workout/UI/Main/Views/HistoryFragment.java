package com.teioh.workout.UI.Main.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teioh.workout.R;
import com.teioh.workout.UI.Main.Adapter.HistoryAdapter;
import com.teioh.workout.UI.Main.Adapter.WorkoutAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HistoryFragment extends Fragment
{
    @Bind(R.id.history_recycler_view) RecyclerView mHistoryRecyclerView;

    public static Fragment getNewInstance()
    {
        return new HistoryFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View lView = inflater.inflate(R.layout.fragment_main_history, container, false);
        ButterKnife.bind(this, lView);

        setupHistory();
        return lView;
    }

    private void setupHistory()
    {
        HistoryAdapter lAdapter = new HistoryAdapter();
        mHistoryRecyclerView.setAdapter(lAdapter);
        mHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mHistoryRecyclerView.addItemDecoration(new HistoryAdapter.SpacesItemDecoration(3));

    }
}
