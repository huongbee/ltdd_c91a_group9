package com.example.ltdd_c91a_group9.models;

import androidx.annotation.NonNull;

public class SaleOff {
    private int id;
    private String title;
    private double percent;

    public SaleOff(int id, String title, double percent) {
        this.id = id;
        this.title = title;
        this.percent = percent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }


}
