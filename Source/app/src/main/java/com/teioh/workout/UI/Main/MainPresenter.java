package com.teioh.workout.UI.Main;

import android.os.Bundle;

import com.teioh.workout.Enums.FragmentType;

public class MainPresenter implements IMain.MainPresenter
{
    public final static String TAG = MainPresenter.class.getSimpleName();
    public final static String RECENT_FRAGMENT = TAG + ":RECENT_FRAGMENT";

    private IMain.MainActivityMap mMainMapper;

    private FragmentType mCurrentFragment = FragmentType.WORKOUT;

    public MainPresenter(IMain.MainActivityMap map)
    {
        mMainMapper = map;
    }

    @Override
    public void init(Bundle bundle)
    {
        mMainMapper.initFragmentContainer(mCurrentFragment);
    }

    @Override
    public void onSaveState(Bundle save)
    {
        save.putString(RECENT_FRAGMENT, mCurrentFragment.name());
    }

    @Override
    public void onRestoreState(Bundle restore)
    {
        if (restore.containsKey(RECENT_FRAGMENT))
            mCurrentFragment = FragmentType.valueOf(restore.getString(RECENT_FRAGMENT));
    }

    @Override
    public void onPause()
    {

    }

    @Override
    public void onResume()
    {

    }

    @Override
    public void onDestroy()
    {

    }
}
