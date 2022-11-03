package com.glencosby.fitbook.Models;

public class Exercise {

    // Fields
    private int exerciseID;
    private String exerciseName;
    private String exerciseInfo;

    // Constructors
    public Exercise() {}
    public Exercise(String name, String info) {
        this.exerciseName = name;
        this.exerciseInfo = info;
    }

    // Properties
    public int getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(int id) {
        this.exerciseID = id;
    }

    public void setExerciseName(String name) {
        this.exerciseName = name;
    }

    public String getExerciseName() {
        return this.exerciseName;
    }

    public void setExerciseInfo(String info) {
        this.exerciseInfo = info;
    }

    public String getExerciseInfo() {
        return this.exerciseInfo;
    }

}