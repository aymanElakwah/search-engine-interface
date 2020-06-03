package com.ayman.searchengine.model;

public class Trend {
    private String name;
    private Integer percentage;

    public Trend(String name, Integer percentage) {
        this.name = name;
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public Integer getPercentage() {
        return percentage;
    }
}
