package com.glencosby.fitbook.Models;

public class DietPlan {

    // Fields
    private int dietPlanID;
    private String dietPlanName;
    private String breakfastInfo;
    private String lunchInfo;
    private String dinnerInfo;
    private String snackInfo;

    // Constructors
    public DietPlan() {}
    public DietPlan(String name, String breakfast, String lunch, String dinner, String snack) {
        this.dietPlanName = name;
        this.breakfastInfo = breakfast;
        this.lunchInfo = lunch;
        this.dinnerInfo = dinner;
        this.snackInfo = snack;
    }

    // Properties
    public int getDietPlanID() {
        return dietPlanID;
    }

    public void setDietPlanID(int id) {
        this.dietPlanID = id;
    }

    public void setDietPlanName(String name) {
        this.dietPlanName = name;
    }

    public String getDietPlanName() {
        return this.dietPlanName;
    }

    public void setBreakfastInfo(String breakfast) {
        this.breakfastInfo = breakfast;
    }

    public String getBreakfastInfo() {
        return this.breakfastInfo;
    }

    public void setLunchInfo(String lunch) {
        this.lunchInfo = lunch;
    }

    public String getLunchInfo() {
        return this.lunchInfo;
    }

    public void setDinnerInfo(String dinner) {
        this.dinnerInfo = dinner;
    }

    public String getDinnerInfo() {
        return this.dinnerInfo;
    }

    public void setSnackInfo(String snack) {
        this.snackInfo = snack;
    }

    public String getSnackInfo() {
        return this.snackInfo;
    }


}