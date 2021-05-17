package com.tourguide.containers.algorithm;

import com.tourguide.containers.Constants;
import com.tourguide.containers.enums.TravelType;
import com.tourguide.containers.general.Place;

public class Route {
    private double length;
    private double timeSpent;
    private double moneySpend;
    private double pheromones;
    private Place from;
    private Place to;
    private TravelType type;
    public Place getFrom() {
        return from;
    }

    public void setFrom(Place from) {
        this.from = from;
    }

    public Place getTo() {
        return to;
    }

    public void setTo(Place to) {
        this.to = to;
    }

    public TravelType getType() {
        return type;
    }

    public void setType(TravelType type) {
        this.type = type;
    }

    public Route(double length, double timeSpent, double moneySpend, double pheromones, Place from, Place to, TravelType travelType) {
        this.length = length;
        this.timeSpent = timeSpent;
        this.moneySpend = moneySpend;
        this.pheromones = pheromones;
        this.from = from;
        this.to = to;
        this.type = travelType;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getTimeSpent() {
        return timeSpent;
    }

    @Override
    public String toString() {
        return "Route{" +
                "length=" + length +
                ", timeSpent=" + timeSpent +
                ", moneySpend=" + moneySpend +
                '}';
    }
    public double getPheromones() {
        return pheromones;
    }
    public void updatePheromones(double updatePheromoneLevel){
        this.pheromones = this.pheromones* Constants.removePercent + updatePheromoneLevel;
    }
    public void setPheromones(double pheromones) {
        this.pheromones = pheromones;
    }

    public void setTimeSpent(double timeSpent) {
        this.timeSpent = timeSpent;
    }

    public double getMoneySpend() {
        return moneySpend;
    }

    public void setMoneySpend(double moneySpend) {
        this.moneySpend = moneySpend;
    }
}
