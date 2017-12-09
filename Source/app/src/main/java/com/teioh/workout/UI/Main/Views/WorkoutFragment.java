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

public class WorkoutFragment extends Fragment
{

    @Bind(R.id.workout_recycler_view) RecyclerView mWorkoutRecyclerView;


    public static Fragment getNewInstance()
    {
        return new WorkoutFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View lView = inflater.inflate(R.layout.fragment_main_workout, container, false);
        ButterKnife.bind(this, lView);

        setupWorkoutViews();
        return lView;
    }

    private void setupWorkoutViews()
    {
        WorkoutAdapter lAdapter = new WorkoutAdapter();
        mWorkoutRecyclerView.setAdapter(lAdapter);
        mWorkoutRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
