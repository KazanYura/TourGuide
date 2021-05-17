package com.tourguide.containers.algorithm;

import java.util.ArrayList;

public class Ant {
    private int currentPoint;
    private ArrayList<Integer> path = new ArrayList<>();

    @Override
    public String toString() {
        return "Ant{" +
                "path=" + path +
                '}';
    }

    public Ant(int currentPoint) {
        this.currentPoint = currentPoint;
        this.path.add(currentPoint);
    }

    public int getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(int currentPoint) {
        this.currentPoint = currentPoint;
    }

    public ArrayList<Integer> getPath() {
        return path;
    }

    public void setPath(ArrayList<Integer> path) {
        this.path = path;
    }
    public void addPlace(int placeID){
        this.path.add(placeID);
    }

}
