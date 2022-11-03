package com.glencosby.fitbook.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.glencosby.fitbook.R;
import com.glencosby.fitbook.Adapters.WorkoutPlansRecyclerAdapter;
import com.glencosby.fitbook.Models.WorkoutPlan;
import com.glencosby.fitbook.SQL.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class WorkoutPlansListActivity extends AppCompatActivity {

    private AppCompatActivity activity = WorkoutPlansListActivity.this;
    private AppCompatTextView textViewWorkoutPlanName;
    private RecyclerView recyclerViewWorkoutPlans;
    private List<WorkoutPlan> listWorkoutPlans;
    private WorkoutPlansRecyclerAdapter workoutPlansRecyclerAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plans_list);
        getSupportActionBar().setTitle("");
        initViews();
        initObjects();

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        textViewWorkoutPlanName = (AppCompatTextView) findViewById(R.id.textViewWorkoutPlanName);
        recyclerViewWorkoutPlans = (RecyclerView) findViewById(R.id.recyclerViewWorkoutPlans);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listWorkoutPlans = new ArrayList<>();
        workoutPlansRecyclerAdapter = new WorkoutPlansRecyclerAdapter(listWorkoutPlans);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewWorkoutPlans.setLayoutManager(mLayoutManager);
        recyclerViewWorkoutPlans.setItemAnimator(new DefaultItemAnimator());
        recyclerViewWorkoutPlans.setHasFixedSize(true);
        recyclerViewWorkoutPlans.setAdapter(workoutPlansRecyclerAdapter);
        databaseHelper = new DatabaseHelper(activity);

        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        textViewWorkoutPlanName.setText(emailFromIntent);

        getDataFromSQLite();
    }

    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listWorkoutPlans.clear();
                listWorkoutPlans.addAll(databaseHelper.getAllWorkoutPlans());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                workoutPlansRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

}
