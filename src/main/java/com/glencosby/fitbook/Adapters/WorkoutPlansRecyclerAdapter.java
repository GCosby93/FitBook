package com.glencosby.fitbook.Adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glencosby.fitbook.R;
import com.glencosby.fitbook.Models.WorkoutPlan;

import java.util.List;

public class WorkoutPlansRecyclerAdapter extends RecyclerView.Adapter<WorkoutPlansRecyclerAdapter.WorkoutPlanViewHolder> {

    private List<WorkoutPlan> listWorkoutPlans;

    public WorkoutPlansRecyclerAdapter(List<WorkoutPlan> listWorkoutPlans) {
        this.listWorkoutPlans = listWorkoutPlans;
    }

    @Override
    public WorkoutPlanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_workout_plans_recycler, parent, false);

        return new WorkoutPlanViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WorkoutPlanViewHolder holder, int position) {
        holder.textViewWorkoutPlanName.setText(listWorkoutPlans.get(position).getWorkoutPlanName());
        holder.textViewWorkoutPlanInfo.setText(listWorkoutPlans.get(position).getWorkoutPlanInfo());
    }

    @Override
    public int getItemCount() {
        Log.v(WorkoutPlansRecyclerAdapter.class.getSimpleName(),""+listWorkoutPlans.size());
        return listWorkoutPlans.size();
    }


    /**
     * ViewHolder class
     */
    public class WorkoutPlanViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewWorkoutPlanName;
        public AppCompatTextView textViewWorkoutPlanInfo;

        public WorkoutPlanViewHolder(View view) {
            super(view);
            textViewWorkoutPlanName = (AppCompatTextView) view.findViewById(R.id.textViewWorkoutPlanName);
            textViewWorkoutPlanInfo = (AppCompatTextView) view.findViewById(R.id.textViewWorkoutPlanInfo);
        }
    }
}
