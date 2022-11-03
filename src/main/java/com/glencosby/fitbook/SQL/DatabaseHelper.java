package com.glencosby.fitbook.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.glencosby.fitbook.Models.User;
import com.glencosby.fitbook.Models.Meal;
import com.glencosby.fitbook.Models.DietPlan;
import com.glencosby.fitbook.Models.Exercise;
import com.glencosby.fitbook.Models.WorkoutPlan;

import java.util.ArrayList;
import java.util.List;



public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UserManager.db";

    // Table Names
    private static final String TABLE_USER = "user";
    private static final String TABLE_MEAL = "meal";
    private static final String TABLE_DIET_PLAN = "dietplan";
    private static final String TABLE_EXERCISE = "exercise";
    private static final String TABLE_WORKOUT_PLAN = "workoutplan";

    // User Table Columns
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    // Meal Column Fields
    public static final String COLUMN_MEAL_ID = "meal_id";
    public static final String COLUMN_MEAL_NAME = "meal_name";
    public static final String COLUMN_MEAL_INFO = "meal_info";
    public static final String COLUMN_MEAL_PREP = "meal_prep";

    // Diet Plan Column Fields
    public static final String COLUMN_DIET_PLAN_ID = "diet_plan_id";
    public static final String COLUMN_DIET_PLAN_NAME = "diet_plan_name";
    public static final String COLUMN_DIET_PLAN_BREAKFAST = "diet_plan_breakfast";
    public static final String COLUMN_DIET_PLAN_LUNCH = "diet_plan_lunch";
    public static final String COLUMN_DIET_PLAN_DINNER = "diet_plan_dinner";
    public static final String COLUMN_DIET_PLAN_SNACK = "diet_plan_snack";

    // Exercise Column Fields
    public static final String COLUMN_EXERCISE_ID = "exercise_id";
    public static final String COLUMN_EXERCISE_NAME = "exercise_name";
    public static final String COLUMN_EXERCISE_INFO = "exercise_info";

    // Workout Plan Column Fields
    public static final String COLUMN_WORKOUT_PLAN_ID = "workout_plan_id";
    public static final String COLUMN_WORKOUT_PLAN_NAME = "workout_plan_name";
    public static final String COLUMN_WORKOUT_PLAN_INFO = "workout_plan_info";

    // Create User Table
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";

    // Drop User Table
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;


    // Create Meal Table
    private String CREATE_MEAL_TABLE = "CREATE TABLE " + TABLE_MEAL + "("
            + COLUMN_MEAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_MEAL_NAME + " TEXT,"
            + COLUMN_MEAL_INFO + " TEXT," + COLUMN_MEAL_PREP + " TEXT" + ")";

    // Drop Meal Table
    private String DROP_MEAL_TABLE = "DROP TABLE IF EXISTS " + TABLE_MEAL;


    // Create Diet Plans Table
    private String CREATE_DIET_PLAN_TABLE = "CREATE TABLE " + TABLE_DIET_PLAN + "("
            + COLUMN_DIET_PLAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_DIET_PLAN_NAME + " TEXT PRIMARY KEY,"
            + COLUMN_DIET_PLAN_BREAKFAST + " TEXT,"
            + COLUMN_DIET_PLAN_LUNCH + " TEXT,"
            + COLUMN_DIET_PLAN_DINNER + " TEXT,"
            + COLUMN_DIET_PLAN_SNACK + " TEXT" + ")";

    // Drop Diet Plans Table
    private String DROP_DIET_PLAN_TABLE = "DROP TABLE IF EXISTS " + TABLE_DIET_PLAN;


    // Create Exercises Table
    private String CREATE_EXERCISE_TABLE = "CREATE TABLE " + TABLE_EXERCISE + "("
            + COLUMN_EXERCISE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EXERCISE_NAME + " TEXT PRIMARY KEY,"
            + COLUMN_EXERCISE_INFO + " TEXT" + ")";

    // Drop Exercises Table
    private String DROP_EXERCISE_TABLE = "DROP TABLE IF EXISTS " + TABLE_EXERCISE;


    // Create Workout Plans Table
    private String CREATE_WORKOUT_PLAN_TABLE = "CREATE TABLE " + TABLE_WORKOUT_PLAN + "("
            + COLUMN_WORKOUT_PLAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_WORKOUT_PLAN_NAME + " TEXT PRIMARY KEY,"
            + COLUMN_WORKOUT_PLAN_INFO + " TEXT" + ")";

    // Drop Workout Plans Table
    private String DROP_WORKOUT_PLAN_TABLE = "DROP TABLE IF EXISTS " + TABLE_WORKOUT_PLAN;


    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        //db.execSQL(CREATE_MEAL_TABLE);
        //db.execSQL(CREATE_DIET_PLAN_TABLE);
        //db.execSQL(CREATE_EXERCISE_TABLE);
        //db.execSQL(CREATE_WORKOUT_PLAN_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop Table if it exists
        db.execSQL(DROP_USER_TABLE);
        //db.execSQL(DROP_MEAL_TABLE);
        //db.execSQL(DROP_DIET_PLAN_TABLE);
        //db.execSQL(DROP_EXERCISE_TABLE);
        //db.execSQL(DROP_WORKOUT_PLAN_TABLE);

        // Create tables again
        onCreate(db);

    }


    //User Methods

    /**
     * Method for creating a user
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    /**
     * Method for fetching all users and returning a list
     *
     * @return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD
        };
        // Order of sorting
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // Query User Table
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));

                // Adding User to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // Return list of Users
        return userList;
    }

    /**
     * Method to Update a User
     *
     * @param user
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // Update Row
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * Method to Delete a User
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Delete User by ID
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * Method to Check User exists
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // Selection Criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // Selection Argument
        String[] selectionArgs = {email};

        // Query User Table
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // Query User Table
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }


    // Meal Methods

    // Method to Add a Meal
    public void addMeal(Meal meal) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_MEAL_NAME, meal.getMealName());
        values.put(COLUMN_MEAL_INFO, meal.getMealInfo());
        values.put(COLUMN_MEAL_PREP, meal.getMealPrep());

        // Inserting Row
        db.insert(TABLE_MEAL, null, values);
        db.close();
    }


    // Method to Find All Meals
    public List<Meal> getAllMeals() {
        // Array of columns that need to be retrieved
        String[] columns = {
                COLUMN_MEAL_ID,
                COLUMN_MEAL_NAME,
                COLUMN_MEAL_INFO,
                COLUMN_MEAL_PREP
        };

        // Order in which results are sorted
        String sortOrder =
                COLUMN_MEAL_ID + " ASC";
        List<Meal> mealList = new ArrayList<Meal>();

        SQLiteDatabase db = this.getReadableDatabase();


        // Query Meal Table
        Cursor cursor = db.query(TABLE_MEAL,
                columns,                 //columns to return
                null,           //columns for the WHERE clause
                null,       //The values for the WHERE clause
                null,           //group the rows
                null,            //filter by row groups
                sortOrder);             //The sort order


        // Traverse All Rows and Add to Meals List
        if (cursor.moveToFirst()) {
            do {
                Meal meal = new Meal();
                meal.setMealID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_MEAL_ID))));
                meal.setMealName(cursor.getString(cursor.getColumnIndex(COLUMN_MEAL_NAME)));
                meal.setMealInfo(cursor.getString(cursor.getColumnIndex(COLUMN_MEAL_INFO)));
                meal.setMealPrep(cursor.getString(cursor.getColumnIndex(COLUMN_MEAL_PREP)));

                // Adding Meal Record to List
                mealList.add(meal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // Return List for MealsActivity
        return mealList;
    }


    // Method to Update a Meal
    public void updateMeal(Meal meal) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_MEAL_NAME, meal.getMealName());
        values.put(COLUMN_MEAL_INFO, meal.getMealInfo());
        values.put(COLUMN_MEAL_PREP, meal.getMealPrep());

        // Update Row
        db.update(TABLE_MEAL, values, COLUMN_MEAL_ID + " = ?",
                new String[]{String.valueOf(meal.getMealID())});
        db.close();
    }


    // Method to Delete a Meal
    public void deleteMeal(Meal meal) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Delete Meal Record by ID
        db.delete(TABLE_MEAL, COLUMN_MEAL_ID + " = ?",
                new String[]{String.valueOf(meal.getMealID())});
        db.close();
    }


    // Method to Check Meal Exists
    public boolean checkMealName(String name) {

        // Array of Columns to Fetch
        String[] columns = {
                COLUMN_MEAL_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // Selection Criteria
        String selection = COLUMN_MEAL_NAME + " = ?";

        // Selection Argument
        String[] selectionArgs = {name};

        // Query Meal Table with Condition
        Cursor cursor = db.query(TABLE_MEAL, //Table to query
                columns,                      //columns to return
                selection,                    //columns for the WHERE clause
                selectionArgs,                //The values for the WHERE clause
                null,                //group the rows
                null,                 //filter by row groups
                null);               //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }




    // Diet Plan Methods

    // Method to Add a Diet Plan
    public void addDietPlan(DietPlan dietplan) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DIET_PLAN_NAME, dietplan.getDietPlanName());
        values.put(COLUMN_DIET_PLAN_BREAKFAST, dietplan.getBreakfastInfo());
        values.put(COLUMN_DIET_PLAN_LUNCH, dietplan.getLunchInfo());
        values.put(COLUMN_DIET_PLAN_DINNER, dietplan.getDinnerInfo());
        values.put(COLUMN_DIET_PLAN_SNACK, dietplan.getSnackInfo());

        // Inserting Row
        db.insert(TABLE_DIET_PLAN, null, values);
        db.close();
    }


    // Method to Find All Diet Plans
    public List<DietPlan> getAllDietPlans() {
        // Array of columns that need to be retrieved
        String[] columns = {
                COLUMN_DIET_PLAN_ID,
                COLUMN_DIET_PLAN_NAME,
                COLUMN_DIET_PLAN_BREAKFAST,
                COLUMN_DIET_PLAN_LUNCH,
                COLUMN_DIET_PLAN_DINNER,
                COLUMN_DIET_PLAN_SNACK
        };

        // Order in which results are sorted
        String sortOrder =
                COLUMN_DIET_PLAN_ID + " ASC";
        List<DietPlan> dietPlanList = new ArrayList<DietPlan>();

        SQLiteDatabase db = this.getReadableDatabase();


        // Query Diet Plan Table
        Cursor cursor = db.query(TABLE_DIET_PLAN,
                columns,                 //columns to return
                null,           //columns for the WHERE clause
                null,       //The values for the WHERE clause
                null,           //group the rows
                null,            //filter by row groups
                sortOrder);             //The sort order


        // Traverse All Rows and Add to Diet Plans List
        if (cursor.moveToFirst()) {
            do {
                DietPlan dietplan = new DietPlan();
                dietplan.setDietPlanID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_DIET_PLAN_ID))));
                dietplan.setDietPlanName(cursor.getString(cursor.getColumnIndex(COLUMN_DIET_PLAN_NAME)));
                dietplan.setBreakfastInfo(cursor.getString(cursor.getColumnIndex(COLUMN_DIET_PLAN_BREAKFAST)));
                dietplan.setLunchInfo(cursor.getString(cursor.getColumnIndex(COLUMN_DIET_PLAN_LUNCH)));
                dietplan.setDinnerInfo(cursor.getString(cursor.getColumnIndex(COLUMN_DIET_PLAN_DINNER)));
                dietplan.setSnackInfo(cursor.getString(cursor.getColumnIndex(COLUMN_DIET_PLAN_SNACK)));

                // Adding Diet Plan Record to List
                dietPlanList.add(dietplan);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // Return List for DietPlansActivity
        return dietPlanList;
    }


    // Method to Update a Diet Plan
    public void updateDietPlan(DietPlan dietplan) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DIET_PLAN_NAME, dietplan.getDietPlanName());
        values.put(COLUMN_DIET_PLAN_BREAKFAST, dietplan.getBreakfastInfo());
        values.put(COLUMN_DIET_PLAN_LUNCH, dietplan.getLunchInfo());
        values.put(COLUMN_DIET_PLAN_DINNER, dietplan.getDinnerInfo());
        values.put(COLUMN_DIET_PLAN_SNACK, dietplan.getSnackInfo());

        // Update Row
        db.update(TABLE_DIET_PLAN, values, COLUMN_DIET_PLAN_ID + " = ?",
                new String[]{String.valueOf(dietplan.getDietPlanID())});
        db.close();
    }


    // Method to Delete a Diet Plan
    public void deleteDietPlan(DietPlan dietplan) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Delete Diet Plan Record by ID
        db.delete(TABLE_DIET_PLAN, COLUMN_DIET_PLAN_ID + " = ?",
                new String[]{String.valueOf(dietplan.getDietPlanID())});
        db.close();
    }


    // Method to Check Diet Plan Exists
    public boolean checkDietPlanName(String name) {

        // Array of Columns to Fetch
        String[] columns = {
                COLUMN_DIET_PLAN_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // Selection Criteria
        String selection = COLUMN_DIET_PLAN_NAME + " = ?";

        // Selection Argument
        String[] selectionArgs = {name};

        // Query Diet Plan Table with Condition
        Cursor cursor = db.query(TABLE_DIET_PLAN, //Table to query
                columns,                      //columns to return
                selection,                    //columns for the WHERE clause
                selectionArgs,                //The values for the WHERE clause
                null,                //group the rows
                null,                 //filter by row groups
                null);               //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }





    // Exercise Methods

    // Method to Add an Exercise
    public void addExercise(Exercise exercise) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EXERCISE_NAME, exercise.getExerciseName());
        values.put(COLUMN_EXERCISE_INFO, exercise.getExerciseInfo());

        // Inserting Row
        db.insert(TABLE_EXERCISE, null, values);
        db.close();
    }


    // Method to Find All Exercises
    public List<Exercise> getAllExercises() {
        // Array of columns that need to be retrieved
        String[] columns = {
                COLUMN_EXERCISE_ID,
                COLUMN_EXERCISE_NAME,
                COLUMN_EXERCISE_INFO,
        };

        // Order in which results are sorted
        String sortOrder =
                COLUMN_EXERCISE_ID + " ASC";
        List<Exercise> exerciseList = new ArrayList<Exercise>();

        SQLiteDatabase db = this.getReadableDatabase();


        // Query Exercise Table
        Cursor cursor = db.query(TABLE_EXERCISE,
                columns,                 //columns to return
                null,           //columns for the WHERE clause
                null,       //The values for the WHERE clause
                null,           //group the rows
                null,            //filter by row groups
                sortOrder);             //The sort order


        // Traverse All Rows and Add to Exercises List
        if (cursor.moveToFirst()) {
            do {
                Exercise exercise = new Exercise();
                exercise.setExerciseID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_EXERCISE_ID))));
                exercise.setExerciseName(cursor.getString(cursor.getColumnIndex(COLUMN_EXERCISE_NAME)));
                exercise.setExerciseInfo(cursor.getString(cursor.getColumnIndex(COLUMN_EXERCISE_INFO)));

                // Adding Exercise Record to List
                exerciseList.add(exercise);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // Return List for ExercisesActivity
        return exerciseList;
    }


    // Method to Update an Exercise
    public void updateExercise(Exercise exercise) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EXERCISE_NAME, exercise.getExerciseName());
        values.put(COLUMN_EXERCISE_INFO, exercise.getExerciseInfo());

        // Update Row
        db.update(TABLE_EXERCISE, values, COLUMN_EXERCISE_ID + " = ?",
                new String[]{String.valueOf(exercise.getExerciseID())});
        db.close();
    }


    // Method to Delete an Exercise
    public void deleteExercise(Exercise exercise) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Delete Exercise Record by ID
        db.delete(TABLE_EXERCISE, COLUMN_EXERCISE_ID + " = ?",
                new String[]{String.valueOf(exercise.getExerciseID())});
        db.close();
    }


    // Method to Check Exercise Exists
    public boolean checkExerciseName(String name) {

        // Array of Columns to Fetch
        String[] columns = {
                COLUMN_EXERCISE_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // Selection Criteria
        String selection = COLUMN_EXERCISE_NAME + " = ?";

        // Selection Argument
        String[] selectionArgs = {name};

        // Query Exercise Table with Condition
        Cursor cursor = db.query(TABLE_EXERCISE, //Table to query
                columns,                      //columns to return
                selection,                    //columns for the WHERE clause
                selectionArgs,                //The values for the WHERE clause
                null,                //group the rows
                null,                 //filter by row groups
                null);               //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }





    // Workout Plan Methods

    // Method to Add a Workout Plan
    public void addWorkoutPlan(WorkoutPlan workoutplan) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_WORKOUT_PLAN_NAME, workoutplan.getWorkoutPlanName());
        values.put(COLUMN_WORKOUT_PLAN_INFO, workoutplan.getWorkoutPlanInfo());

        // Inserting Row
        db.insert(TABLE_WORKOUT_PLAN, null, values);
        db.close();
    }


    // Method to Find All WorkoutPlans
    public List<WorkoutPlan> getAllWorkoutPlans() {
        // Array of columns that need to be retrieved
        String[] columns = {
                COLUMN_WORKOUT_PLAN_ID,
                COLUMN_WORKOUT_PLAN_NAME,
                COLUMN_WORKOUT_PLAN_INFO,
        };

        // Order in which results are sorted
        String sortOrder =
                COLUMN_WORKOUT_PLAN_ID + " ASC";
        List<WorkoutPlan> workoutPlanList = new ArrayList<WorkoutPlan>();

        SQLiteDatabase db = this.getReadableDatabase();


        // Query WorkoutPlan Table
        Cursor cursor = db.query(TABLE_WORKOUT_PLAN,
                columns,                 //columns to return
                null,           //columns for the WHERE clause
                null,       //The values for the WHERE clause
                null,           //group the rows
                null,            //filter by row groups
                sortOrder);             //The sort order


        // Traverse All Rows and Add to WorkoutPlans List
        if (cursor.moveToFirst()) {
            do {
                WorkoutPlan workoutplan = new WorkoutPlan();
                workoutplan.setWorkoutPlanID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_WORKOUT_PLAN_ID))));
                workoutplan.setWorkoutPlanName(cursor.getString(cursor.getColumnIndex(COLUMN_WORKOUT_PLAN_NAME)));
                workoutplan.setWorkoutPlanInfo(cursor.getString(cursor.getColumnIndex(COLUMN_WORKOUT_PLAN_INFO)));

                // Adding WorkoutPlan Record to List
                workoutPlanList.add(workoutplan);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // Return List for WorkoutPlansActivity
        return workoutPlanList;
    }


    // Method to Update a WorkoutPlan
    public void updateWorkoutPlan(WorkoutPlan workoutplan) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_WORKOUT_PLAN_NAME, workoutplan.getWorkoutPlanName());
        values.put(COLUMN_WORKOUT_PLAN_INFO, workoutplan.getWorkoutPlanInfo());

        // Update Row
        db.update(TABLE_WORKOUT_PLAN, values, COLUMN_WORKOUT_PLAN_ID + " = ?",
                new String[]{String.valueOf(workoutplan.getWorkoutPlanID())});
        db.close();
    }


    // Method to Delete a WorkoutPlan
    public void deleteWorkoutPlan(WorkoutPlan workoutplan) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Delete WorkoutPlan Record by ID
        db.delete(TABLE_WORKOUT_PLAN, COLUMN_WORKOUT_PLAN_ID + " = ?",
                new String[]{String.valueOf(workoutplan.getWorkoutPlanID())});
        db.close();
    }


    // Method to Check WorkoutPlan Exists
    public boolean checkWorkoutPlanName(String name) {

        // Array of Columns to Fetch
        String[] columns = {
                COLUMN_WORKOUT_PLAN_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // Selection Criteria
        String selection = COLUMN_WORKOUT_PLAN_NAME + " = ?";

        // Selection Argument
        String[] selectionArgs = {name};

        // Query WorkoutPlan Table with Condition
        Cursor cursor = db.query(TABLE_WORKOUT_PLAN, //Table to query
                columns,                      //columns to return
                selection,                    //columns for the WHERE clause
                selectionArgs,                //The values for the WHERE clause
                null,                //group the rows
                null,                 //filter by row groups
                null);               //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }



}
