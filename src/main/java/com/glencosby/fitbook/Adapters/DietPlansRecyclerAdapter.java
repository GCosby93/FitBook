package com.glencosby.fitbook.Adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glencosby.fitbook.R;
import com.glencosby.fitbook.Models.DietPlan;

import java.util.List;

public class DietPlansRecyclerAdapter extends RecyclerView.Adapter<DietPlansRecyclerAdapter.DietPlanViewHolder> {

    private List<DietPlan> listDietPlans;

    public DietPlansRecyclerAdapter(List<DietPlan> listDietPlans) {
        this.listDietPlans = listDietPlans;
    }

    @Override
    public DietPlanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_diet_plan_recycler, parent, false);

        return new DietPlanViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DietPlanViewHolder holder, int position) {
        holder.textViewName.setText(listDietPlans.get(position).getDietPlanName());
        holder.textViewBreakfast.setText(listDietPlans.get(position).getBreakfastInfo());
        holder.textViewLunch.setText(listDietPlans.get(position).getLunchInfo());
        holder.textViewDinner.setText(listDietPlans.get(position).getDinnerInfo());
        holder.textViewSnack.setText(listDietPlans.get(position).getSnackInfo());
    }

    @Override
    public int getItemCount() {
        Log.v(DietPlansRecyclerAdapter.class.getSimpleName(),""+listDietPlans.size());
        return listDietPlans.size();
    }


    /**
     * ViewHolder class
     */
    public class DietPlanViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewName;
        public AppCompatTextView textViewBreakfast;
        public AppCompatTextView textViewLunch;
        public AppCompatTextView textViewDinner;
        public AppCompatTextView textViewSnack;

        public DietPlanViewHolder(View view) {
            super(view);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName);
            textViewBreakfast = (AppCompatTextView) view.findViewById(R.id.textViewBreakfast);
            textViewLunch = (AppCompatTextView) view.findViewById(R.id.textViewLunch);
            textViewDinner = (AppCompatTextView) view.findViewById(R.id.textViewDinner);
            textViewSnack = (AppCompatTextView) view.findViewById(R.id.textViewSnack);

        }
    }

}
