package com.teioh.workout.UI.Main.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.afollestad.sectionedrecyclerview.SectionedViewHolder;
import com.teioh.workout.R;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class WorkoutAdapter extends SectionedRecyclerViewAdapter<WorkoutAdapter.WorkoutViewHolder>
{
    public final static String TAG = WorkoutAdapter.class.getSimpleName();

    private HashMap<Integer, List<String>> mSectionData;
    private List<String> mSections;


    public WorkoutAdapter()
    {
//        mSectionData = Arrays.asList(new String[]{"StrongLift 5x5 A", "StrongLift 5x5 B", "StrongLift 5x5 A"});
        mSectionData = new HashMap<>();
        mSections = Arrays.asList(new String[]{"New Workout", "Workouts", "HIIT", "Examples"});
        mSectionData.put(0, Arrays.asList(new String[]{"New Workout", "New HIIT Workout"}));
        mSectionData.put(1, Arrays.asList(new String[]{"My Shit", "Chest day duh", "Bicep bro"}));
        mSectionData.put(2, Arrays.asList(new String[]{"Routine 1", "Super abs", "HARDCORE"}));
        mSectionData.put(3, Arrays.asList(new String[]{"StrongLift 5x5 A", "StrongLift 5x5 B", "Chest", "Legs", "Back/Bicep"}));
    }


    @Override
    public int getSectionCount()
    {
        return mSections.size(); // number of sections, you would probably base this on a data set such as a map
    }

    @Override
    public int getItemCount(int sectionIndex)
    {
        return mSectionData.get(sectionIndex).size(); // number of items in section, you could also pull this from a map of lists
    }

    @Override
    public void onBindHeaderViewHolder(WorkoutViewHolder holder, int section, boolean expanded)
    {
        holder.mTitle.setText(mSections.get(section));
    }

    @Override
    public void onBindFooterViewHolder(WorkoutViewHolder holder, int section)
    {

    }

    @Override
    public void onBindViewHolder(WorkoutViewHolder holder, int section, int relativePosition, int absolutePosition)
    {
        holder.mTitle.setText(mSectionData.get(section).get(relativePosition));
    }

    @Override
    public WorkoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // Change inflated layout based on type
        int layoutRes;
        switch (viewType)
        {
            case VIEW_TYPE_HEADER:
            case VIEW_TYPE_FOOTER:
                layoutRes = R.layout.list_item_header;
                View headerView = LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);
                return new WorkoutViewHolder(headerView, viewType, true);
            default:
                layoutRes = R.layout.list_item_workout;
                View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);
                return new WorkoutViewHolder(itemView, viewType);
        }

    }

    /***
     * Class to hold relevant history item views and interactions
     */
    public class WorkoutViewHolder extends SectionedViewHolder
    {
        private int mViewType;

        private TextView mTitle;
        private TextView mDescription;
        private LinearLayout mContainer;

        public WorkoutViewHolder(View itemView, int aViewType)
        {
            super(itemView);
            mViewType = aViewType;

            mDescription = (TextView) itemView.findViewById(R.id.workout_description);
            mTitle = (TextView) itemView.findViewById(R.id.workout_title);
            mContainer = (LinearLayout) itemView.findViewById(R.id.workout_container);

        }

        public WorkoutViewHolder(View itemView, int aViewType, boolean isHeader)
        {
            super(itemView);
            mViewType = aViewType;

            mTitle = (TextView) itemView.findViewById(R.id.item_header);
            mContainer = (LinearLayout) itemView.findViewById(R.id.container);
            mContainer.setOnClickListener(v -> verifyOneSectionExpanded());

        }

        private void verifyOneSectionExpanded()
        {
            if (getRelativePosition().section() != 0)
            {
                if (isSectionExpanded(getRelativePosition().section()))
                {
                    Log.e(TAG, "Adapter Position: " + getRelativePosition().section());
                    collapseSection(getRelativePosition().section());
                }
                else expandSection(getRelativePosition().section());
            }
        }
    }
}



