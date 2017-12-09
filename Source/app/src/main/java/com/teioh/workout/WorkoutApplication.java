package com.teioh.workout;

import android.app.Application;

import com.teioh.workout.Utils.WorkoutDBUtil;

public class WorkoutApplication extends Application
{
    private static WorkoutApplication aInstance;

    public WorkoutApplication()
    {
        aInstance = this;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        //creates database if fresh install
        WorkoutDBUtil.getInstance().createDatabase();
    }


    public static synchronized WorkoutApplication getInstance()
    {
        return aInstance;
    }

}
