package com.glencosby.fitbook.Models;

public class WorkoutPlan {

    // Fields
    private int workoutPlanID;
    private String workoutPlanName;
    private String workoutPlanInfo;

    // Constructors
    public WorkoutPlan() {}
    public WorkoutPlan(String name, String info) {
        this.workoutPlanName = name;
        this.workoutPlanInfo = info;
    }

    // Properties
    public int getWorkoutPlanID() {
        return workoutPlanID;
    }

    public void setWorkoutPlanID(int id) {
        this.workoutPlanID = id;
    }

    public void setWorkoutPlanName(String name) {
        this.workoutPlanName = name;
    }

    public String getWorkoutPlanName() {
        return this.workoutPlanName;
    }

    public void setWorkoutPlanInfo(String info) {
        this.workoutPlanInfo = info;
    }

    public String getWorkoutPlanInfo() {
        return this.workoutPlanInfo;
    }

}