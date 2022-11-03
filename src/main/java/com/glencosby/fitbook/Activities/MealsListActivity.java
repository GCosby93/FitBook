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
import com.glencosby.fitbook.Adapters.MealsRecyclerAdapter;
import com.glencosby.fitbook.Models.Meal;
import com.glencosby.fitbook.SQL.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MealsListActivity extends AppCompatActivity {

    private AppCompatActivity activity = MealsListActivity.this;
    private AppCompatTextView textViewMealName;
    private RecyclerView recyclerViewMeals;
    private List<Meal> listMeals;
    private MealsRecyclerAdapter mealsRecyclerAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_list);
        getSupportActionBar().setTitle("");
        initViews();
        initObjects();

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        textViewMealName = (AppCompatTextView) findViewById(R.id.textViewMealName);
        recyclerViewMeals = (RecyclerView) findViewById(R.id.recyclerViewMeals);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listMeals = new ArrayList<>();
        mealsRecyclerAdapter = new MealsRecyclerAdapter(listMeals);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewMeals.setLayoutManager(mLayoutManager);
        recyclerViewMeals.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMeals.setHasFixedSize(true);
        recyclerViewMeals.setAdapter(mealsRecyclerAdapter);
        databaseHelper = new DatabaseHelper(activity);

        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        textViewMealName.setText(emailFromIntent);

        getDataFromSQLite();
    }

    /**
     * This method is to fetch all meal records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listMeals.clear();
                listMeals.addAll(databaseHelper.getAllMeals());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mealsRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

}
