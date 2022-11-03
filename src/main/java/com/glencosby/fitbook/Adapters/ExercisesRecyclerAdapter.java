package com.glencosby.fitbook.Adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glencosby.fitbook.R;
import com.glencosby.fitbook.Models.Exercise;

import java.util.List;

public class ExercisesRecyclerAdapter extends RecyclerView.Adapter<ExercisesRecyclerAdapter.ExerciseViewHolder> {

    private List<Exercise> listExercises;

    public ExercisesRecyclerAdapter(List<Exercise> listExercises) {
        this.listExercises = listExercises;
    }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exercises_recycler, parent, false);

        return new ExerciseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position) {
        holder.textViewName.setText(listExercises.get(position).getExerciseName());
        holder.textViewInfo.setText(listExercises.get(position).getExerciseInfo());
    }

    @Override
    public int getItemCount() {
        Log.v(ExercisesRecyclerAdapter.class.getSimpleName(),""+listExercises.size());
        return listExercises.size();
    }


    /**
     * ViewHolder class
     */
    public class ExerciseViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewName;
        public AppCompatTextView textViewInfo;

        public ExerciseViewHolder(View view) {
            super(view);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewExerciseName);
            textViewInfo = (AppCompatTextView) view.findViewById(R.id.textViewExerciseInfo);
        }
    }

}
