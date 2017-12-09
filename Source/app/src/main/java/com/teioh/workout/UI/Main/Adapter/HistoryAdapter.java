package com.teioh.workout.UI.Main.Adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teioh.workout.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>
{
    public final static String TAG = HistoryAdapter.class.getSimpleName();

    //    private HashMap<Integer, List<String>> mSectionData;
    private List<String> mSectionData;

    public HistoryAdapter()
    {
        mSectionData = Arrays.asList(new String[]{"New Workout", "Workouts", "HIIT", "Examples"});
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_history, parent, false);
        return new HistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position)
    {
        holder.mTitle.setText(mSectionData.get(position));
        holder.setupExerciseList();
    }

    @Override
    public int getItemCount()
    {
        return mSectionData.size();
    }

    /***
     * Class to hold relevant history item views and interactions
     */
    public class HistoryViewHolder extends RecyclerView.ViewHolder
    {
        private TextView mTotalWeight;
        private TextView mTotalTime;
        private TextView mTitle;
        private TextView mDate;
        private LinearLayout mContainer;
        private LinearLayout mExerciseList;

        private Context mContext;

        public HistoryViewHolder(View itemView)
        {
            super(itemView);
            mContext = itemView.getContext();

            mTotalTime = (TextView) itemView.findViewById(R.id.history_workout_time);
            mTotalWeight = (TextView) itemView.findViewById(R.id.history_workout_total_weight);
            mTitle = (TextView) itemView.findViewById(R.id.history_workout_title);
            mDate = (TextView) itemView.findViewById(R.id.history_workout_date);
            mContainer = (LinearLayout) itemView.findViewById(R.id.container);
            mExerciseList = (LinearLayout) itemView.findViewById(R.id.history_workout_list_container);
        }

        public void setupExerciseList()
        {
            List<String> lExercises = new ArrayList<>();
            lExercises.add("Bench");
            lExercises.add("Squat");
            lExercises.add("Deadlift");

            for (String s : lExercises)
            {
                addExerciseToList(s);
            }

        }

        private void addExerciseToList(String ex)
        {
            LinearLayout lTemp = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.list_item_history_exercise, mContainer);
            TextView lExCount = (TextView) lTemp.findViewById(R.id.exercise_set_count);
            TextView lName = (TextView) lTemp.findViewById(R.id.exercise_item_name);
            TextView lPr = (TextView) lTemp.findViewById(R.id.exercise_item_pr);

            lName.setText(ex);

            mExerciseList.addView(lTemp);
        }
    }

    public static class SpacesItemDecoration extends RecyclerView.ItemDecoration
    {

        private int lHalfSpace;

        /***
         * TODO..
         *
         * @param space
         */
        public SpacesItemDecoration(int space)
        {
            this.lHalfSpace = space / 2;
        }

        /***
         * TODO..
         *
         * @param aOutRect
         * @param aView
         * @param aParent
         * @param aState
         */
        @Override
        public void getItemOffsets(Rect aOutRect, View aView, RecyclerView aParent, RecyclerView.State aState)
        {

            if (aParent.getPaddingLeft() != lHalfSpace)
            {
                aParent.setPadding(lHalfSpace, lHalfSpace, lHalfSpace, lHalfSpace);
                aParent.setClipToPadding(false);
            }

            aOutRect.top = lHalfSpace;
            aOutRect.bottom = lHalfSpace;
            aOutRect.left = lHalfSpace;
            aOutRect.right = lHalfSpace;
        }
    }
}



