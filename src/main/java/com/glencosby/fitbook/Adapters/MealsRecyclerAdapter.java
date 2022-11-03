package com.glencosby.fitbook.Adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glencosby.fitbook.R;
import com.glencosby.fitbook.Models.Meal;

import java.util.List;

public class MealsRecyclerAdapter extends RecyclerView.Adapter<MealsRecyclerAdapter.MealViewHolder> {

    private List<Meal> listMeals;

    public MealsRecyclerAdapter(List<Meal> listMeals) {
        this.listMeals = listMeals;
    }

    @Override
    public MealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meals_recycler, parent, false);

        return new MealViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MealViewHolder holder, int position) {
        holder.textViewMealName.setText(listMeals.get(position).getMealName());
        holder.textViewMealInfo.setText(listMeals.get(position).getMealInfo());
        holder.textViewMealPrep.setText(listMeals.get(position).getMealInfo());
    }

    @Override
    public int getItemCount() {
        Log.v(MealsRecyclerAdapter.class.getSimpleName(),""+listMeals.size());
        return listMeals.size();
    }


    /**
     * ViewHolder class
     */
    public class MealViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewMealName;
        public AppCompatTextView textViewMealInfo;
        public AppCompatTextView textViewMealPrep;

        public MealViewHolder(View view) {
            super(view);
            textViewMealName = (AppCompatTextView) view.findViewById(R.id.textViewMealName);
            textViewMealInfo = (AppCompatTextView) view.findViewById(R.id.textViewMealInfo);
            textViewMealPrep = (AppCompatTextView) view.findViewById(R.id.textViewMealPrep);
        }
    }

}
