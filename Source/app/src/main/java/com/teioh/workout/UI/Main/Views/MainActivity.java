package com.teioh.workout.UI.Main.Views;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.teioh.workout.R;
import com.teioh.workout.Enums.FragmentType;
import com.teioh.workout.UI.Main.IMain;
import com.teioh.workout.UI.Main.MainPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IMain.MainActivityMap
{
    @Bind(R.id.tool_bar) Toolbar mToolbar;
    @Bind(R.id.workout_text) TextView mWorkoutText;
    @Bind(R.id.profile_text) TextView mProfileText;
    @Bind(R.id.history_text) TextView mHistoryText;

    private IMain.MainPresenter mMainPresenter;
    private Fragment mTemplateFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMainPresenter = new MainPresenter(this);

        if (savedInstanceState != null)
        {
            mMainPresenter.onRestoreState(savedInstanceState);
        }

        mMainPresenter.init(getIntent().getExtras());
        Snackbar.make(findViewById(R.id.main_container), "Welcome to HIIT Workout", Snackbar.LENGTH_LONG).show();

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mMainPresenter.onResume();
    }


    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mMainPresenter.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Context getContext()
    {
        return this;
    }

    @Override
    public void registerAdapter(RecyclerView.Adapter mAdapter, RecyclerView.LayoutManager layout, boolean needItemDecoration)
    {
//        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setLayoutManager(layout);
//        if (needItemDecoration)
//        {
//            mRecyclerView.addItemDecoration(new SessionListAdapter.SpacesItemDecoration(20));
//        }
    }

    @OnClick(R.id.workout_select)
    public void onWorkoutSelected()
    {
        initFragmentContainer(FragmentType.WORKOUT);
    }

    @OnClick(R.id.history_select)
    public void onHistorySelected()
    {
        initFragmentContainer(FragmentType.HISTORY);
    }

    @OnClick(R.id.profile_select)
    public void onProfileSelected()
    {
        initFragmentContainer(FragmentType.PROFILE);
    }

    @Override
    public void initFragmentContainer(FragmentType aType)
    {
        int textColor = getResources().getColor(R.color.button_text_color);
        int textColorSelected = getResources().getColor(R.color.button_text_color_selected);

        switch (aType)
        {
            case PROFILE:
                mToolbar.setTitle("Profile");
                mProfileText.setTextColor(textColorSelected);
                mWorkoutText.setTextColor(textColor);
                mHistoryText.setTextColor(textColor);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, ProfileFragment.getNewInstance(), null)
                        .addToBackStack(null).commit();
                break;
            case WORKOUT:
                mToolbar.setTitle("Workout");
                mProfileText.setTextColor(textColor);
                mWorkoutText.setTextColor(textColorSelected);
                mHistoryText.setTextColor(textColor);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, WorkoutFragment.getNewInstance(), null)
                        .addToBackStack(null).commit();
                break;
            case HISTORY:
                mToolbar.setTitle("History");
                mProfileText.setTextColor(textColor);
                mWorkoutText.setTextColor(textColor);
                mHistoryText.setTextColor(textColorSelected);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, HistoryFragment.getNewInstance(), null)
                        .addToBackStack(null).commit();
                break;
        }
    }
}
