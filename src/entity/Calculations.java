package entity;

import java.util.ArrayList;

public class Calculations {

    private String idealweight;
    private ArrayList<String> BMIinfo;
    private String bmr;
    private ArrayList<String> weightgoals;
    public Calculations(String IdealWeight, ArrayList<String> BMIinfo, String BMR, ArrayList<String> weightgoals) {
        this.idealweight = IdealWeight;
        this.BMIinfo = BMIinfo;
        this.bmr = BMR;
        this.weightgoals = weightgoals;
    }

    public String getIdealWeight() {return idealweight;}
    public ArrayList<String> getBMI() {return BMIinfo;}
    public String getBMR() {return bmr;}
    public ArrayList<String> getWeightGoals() {return weightgoals;}


}
