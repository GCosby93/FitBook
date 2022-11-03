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
import com.glencosby.fitbook.Adapters.DietPlansRecyclerAdapter;
import com.glencosby.fitbook.Models.DietPlan;
import com.glencosby.fitbook.SQL.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class DietPlansListActivity extends AppCompatActivity {

    private AppCompatActivity activity = DietPlansListActivity.this;
    private AppCompatTextView textViewName;
    private RecyclerView recyclerViewDietPlans;
    private List<DietPlan> listDietPlans;
    private DietPlansRecyclerAdapter dietPlansRecyclerAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plans_list);
        getSupportActionBar().setTitle("");
        initViews();
        initObjects();

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        //recyclerViewDietPlans = (RecyclerView) findViewById(R.id.recyclerViewDietPlans);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listDietPlans = new ArrayList<>();
        dietPlansRecyclerAdapter = new DietPlansRecyclerAdapter(listDietPlans);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewDietPlans.setLayoutManager(mLayoutManager);
        recyclerViewDietPlans.setItemAnimator(new DefaultItemAnimator());
        recyclerViewDietPlans.setHasFixedSize(true);
        recyclerViewDietPlans.setAdapter(dietPlansRecyclerAdapter);
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
                listDietPlans.clear();
                listDietPlans.addAll(databaseHelper.getAllDietPlans());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                dietPlansRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

}
