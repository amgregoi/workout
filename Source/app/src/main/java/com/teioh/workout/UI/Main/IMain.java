package com.teioh.workout.UI.Main;

import com.teioh.workout.Enums.FragmentType;
import com.teioh.workout.UI.Maps.BaseContextMap;
import com.teioh.workout.UI.Maps.LifeCycleMap;
import com.teioh.workout.UI.Maps.RecyclerAdapterMap;

public interface IMain
{

    /**
     * Main Activity
     */
    interface MainActivityMap extends BaseContextMap, RecyclerAdapterMap
    {
        void initFragmentContainer(FragmentType aType);
    }

    interface MainPresenter extends LifeCycleMap
    {

    }

    /***
     * Template Fragment
     */
    interface TemplateFragmentMap
    {

    }

    interface TemplatePresenter
    {

    }
}
