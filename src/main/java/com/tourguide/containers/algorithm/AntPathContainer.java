package com.tourguide.containers.algorithm;


import java.util.ArrayList;

public class AntPathContainer {
    private double sumExperience;
    private double timeSpent;
    private double moveTimeSpent;
    private double moneySpent;
    private double moveMoneySpent;
    private double totalTimeSpent;
    private double totalMoveTimeSpent;
    private double totalMoneySpent;
    private double totalMoveMoneySpent;
    private Ant ant;
    private final int startingPointID;
    private double mark;

    public AntPathContainer(int startingPointID) {
        sumExperience = 0;
        this.startingPointID = startingPointID;
        timeSpent = 0;
        moneySpent = 0;
        moveMoneySpent = 0;
        moveTimeSpent = 0;
        totalTimeSpent = 0;
        totalMoveTimeSpent = 0;
        totalMoneySpent = 0;
        totalMoveMoneySpent = 0;
        ant = new Ant(startingPointID);
    }
    public void clearMemory(){
        sumExperience = 0;
        timeSpent = 0;
        moneySpent = 0;
        moveMoneySpent = 0;
        moveTimeSpent = 0;
        totalTimeSpent = 0;
        totalMoveTimeSpent = 0;
        totalMoneySpent = 0;
        totalMoveMoneySpent = 0;
        ant = new Ant(startingPointID);
    }

    public ArrayList<Integer> getPath() {
        return this.ant.getPath();
    }

    @Override
    public String toString() {
        return "AntPathContainer{" +
                "sumExperience=" + sumExperience +
                ", totalTimeSpent=" + totalTimeSpent +
                ", totalMoveTimeSpent=" + totalMoveTimeSpent +
                ", totalMoneySpent=" + totalMoneySpent +
                ", totalMoveMoneySpent=" + totalMoveMoneySpent +
                ", ant=" + ant +
                ", mark=" + mark +
                '}';
    }

    public void addPlace(int placeID){
        this.ant.addPlace(placeID);
    }
    public void setPath(ArrayList<Integer> path) {
        this.ant.setPath(path);
    }

    public double getSumExperience() {
        return sumExperience;
    }

    public void addSumExperience(double sumExperience) {
        this.sumExperience += sumExperience;
    }

    public double getTimeSpent() {
        return timeSpent;
    }

    public void addTimeSpent(double timeSpent) {
        this.timeSpent += timeSpent;
    }

    public double getMoveTimeSpent() {
        return moveTimeSpent;
    }

    public void addMoveTimeSpent(double moveTimeSpent) {
        this.moveTimeSpent += moveTimeSpent;
    }

    public double getMoneySpent() {
        return moneySpent;
    }

    public void addMoneySpent(double moneySpent) {
        this.moneySpent += moneySpent;
    }

    public double getMoveMoneySpent() {
        return moveMoneySpent;
    }

    public void addMoveMoneySpent(double moveMoneySpent) {
        this.moveMoneySpent += moveMoneySpent;
    }

    public Ant getAnt() {
        return ant;
    }

    public void setAnt(Ant ant) {
        this.ant = ant;
    }
    public void setMark(double mark) {
        this.mark = mark;
    }

    public double getMark() {
        return mark;
    }
    public void finalizeDay(){
        this.totalTimeSpent += this.timeSpent;
        this.totalMoveTimeSpent += this.moveTimeSpent;
        this.totalMoneySpent += this.moneySpent;
        this.totalMoveMoneySpent += this.moveMoneySpent;
        timeSpent = 0;
        moneySpent = 0;
        moveMoneySpent = 0;
        moveTimeSpent = 0;
    }
    public double getTotalTimeSpent() {
        return totalTimeSpent;
    }
    public double getTotalMoveTimeSpent() {
        return totalMoveTimeSpent;
    }
    public double getTotalMoneySpent() {
        return totalMoneySpent;
    }
    public double getTotalMoveMoneySpent() {
        return totalMoveMoneySpent;
    }

}
