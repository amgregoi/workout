package com.teioh.workout.UI.Main.Views;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.teioh.workout.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment
{
    @Bind(R.id.profile_chart) BarChart mChart;
    @Bind(R.id.profile_username) TextView mUser;
    @Bind(R.id.profile_counter) TextView mWorkoutCounter;

    public static Fragment getNewInstance()
    {
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View lView = inflater.inflate(R.layout.fragment_main_profile, container, false);
        ButterKnife.bind(this, lView);

        setupChart();
        setupUser();

        return lView;
    }


    private void setupChart()
    {
        //Char setup
        mChart.getDescription().setEnabled(false);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(true);
        mChart.setDoubleTapToZoomEnabled(false);

        //Legend setup
        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.EMPTY);

        // y-axis right side setup
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(true);
        rightAxis.setGranularity(1f);

        //y-axis left side setup
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawLabels(false);

        //x-axis setup
        XAxis xAxis = mChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        String mDays[] = {"Mon", "Tues", "Wed", "Thurs", "Fri", "Sat", "Sun"};
        xAxis.setValueFormatter((value, axis) -> mDays[(int) value % mDays.length]);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        /*
         * Initialize bar data
         */
        ArrayList<BarEntry> entries1 = new ArrayList<>();

        for (int index = 0; index < 7; index++)
        {
            entries1.add(new BarEntry(index, 1));
            if (index == 3)
            {
                entries1.add(new BarEntry(index, new float[]{1, 1, 0, 0, 0}));
            }
        }

        BarDataSet set1 = new BarDataSet(entries1, "");
        set1.setColor(ColorTemplate.getHoloBlue());
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setDrawValues(false);
        set1.setStackLabels(new String[]{null});

        BarData d = new BarData(set1);
        d.setBarWidth(0.8f);

        mChart.setData(d);
        mChart.invalidate();
    }

    private void setupUser()
    {
        mUser.setText("John Doe");
        mWorkoutCounter.setText("8 Workouts");
    }
}
