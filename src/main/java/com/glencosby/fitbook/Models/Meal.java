package com.glencosby.fitbook.Models;

public class Meal {

    // Fields
    private int mealID;
    private String mealName;
    private String mealInfo;
    private String mealPrep;

    // Constructors
    public Meal() {}

    public Meal(String name, String info, String prep) {
        this.mealName = name;
        this.mealInfo = info;
        this.mealPrep = prep;
    }

    // Properties
    public int getMealID() {
        return mealID;
    }

    public void setMealID(int id) {
        this.mealID = id;
    }

    public String getMealName() {
        return this.mealName;
    }

    public void setMealName(String name) {
        this.mealName = name;
    }

    public String getMealInfo() {
        return this.mealInfo;
    }

    public void setMealInfo(String info) {
        this.mealInfo = info;
    }

    public String getMealPrep() {
        return this.mealPrep;
    }

    public void setMealPrep(String prep) {
        this.mealPrep = prep;
    }

}