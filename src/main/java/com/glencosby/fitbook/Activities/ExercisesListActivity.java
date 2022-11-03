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
import com.glencosby.fitbook.Adapters.ExercisesRecyclerAdapter;
import com.glencosby.fitbook.Models.Exercise;
import com.glencosby.fitbook.SQL.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ExercisesListActivity extends AppCompatActivity {

    private AppCompatActivity activity = ExercisesListActivity.this;
    private AppCompatTextView textViewName;
    private RecyclerView recyclerViewExercises;
    private List<Exercise> listExercises;
    private ExercisesRecyclerAdapter exercisesRecyclerAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_list);
        getSupportActionBar().setTitle("");
        initViews();
        initObjects();

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        recyclerViewExercises = (RecyclerView) findViewById(R.id.recyclerViewExercises);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listExercises = new ArrayList<>();
        exercisesRecyclerAdapter = new ExercisesRecyclerAdapter(listExercises);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewExercises.setLayoutManager(mLayoutManager);
        recyclerViewExercises.setItemAnimator(new DefaultItemAnimator());
        recyclerViewExercises.setHasFixedSize(true);
        recyclerViewExercises.setAdapter(exercisesRecyclerAdapter);
        databaseHelper = new DatabaseHelper(activity);

        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText(emailFromIntent);

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
                listExercises.clear();
                listExercises.addAll(databaseHelper.getAllExercises());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                exercisesRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

}
