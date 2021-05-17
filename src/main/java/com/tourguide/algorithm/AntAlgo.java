package com.tourguide.algorithm;

import com.tourguide.containers.algorithm.AntPathContainer;
import com.tourguide.containers.algorithm.Graph;
import com.tourguide.containers.general.Place;
import com.tourguide.exceptions.DayIsOverException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class AntAlgo {
    public Graph graph;
    private final Random pathSelector = new Random();
    public double totalBudget;
    public int totalDays;
    public ArrayList<AntPathContainer> ants = new ArrayList<>();
    Comparator<AntPathContainer> comparator = Comparator.comparingDouble(AntPathContainer::getMark);
    public AntAlgo(ArrayList<Place> places,int totalDays,double totalBudget, Place nightSpend){
        places.add(0,nightSpend);
        this.totalBudget = totalBudget;
        this.totalDays = totalDays;
        this.graph = new Graph(places);
        for (int i = 0; i < 100; i++) {
            ants.add(new AntPathContainer(0));
        }
        this.graph.createConnectionMatrix();
    }
    private void addNextPoint(int id, AntPathContainer ant){
        ant.addPlace(id);
        ant.addMoneySpent(graph.places.get(Math.abs(id)).price);
        ant.addTimeSpent(graph.places.get(Math.abs(id)).timeSpend);
        ant.addSumExperience(graph.places.get(Math.abs(id)).grade);
        ant.addMoveTimeSpent( id > 0 ?
                graph.getConnectionMatrix().get(ant.getAnt().getCurrentPoint()).get(Math.abs(id)).getFootRoute().getTimeSpent()
                :
                graph.getConnectionMatrix().get(ant.getAnt().getCurrentPoint()).get(Math.abs(id)).getCarRoute().getTimeSpent());
        ant.addMoveMoneySpent( id > 0 ?
                graph.getConnectionMatrix().get(ant.getAnt().getCurrentPoint()).get(Math.abs(id)).getFootRoute().getMoneySpend()
                :
                graph.getConnectionMatrix().get(ant.getAnt().getCurrentPoint()).get(Math.abs(id)).getCarRoute().getMoneySpend());
        ant.getAnt().setCurrentPoint(Math.abs(id));
    }
    public void selectNextPoint(AntPathContainer ant) throws DayIsOverException {
        ArrayList<ArrayList<Double>> probabilities = graph.getProbabilitiesForSelectedAnt(ant.getAnt());
        double p = pathSelector.nextDouble();
        ArrayList<Double> probFoot = probabilities.get(1);
        ArrayList<Double> probCar = probabilities.get(0);
        double sum = probFoot.get(0);
        int id = 1;
        while (sum < p){
            id++;
            sum += id % 2 == 1 ? probCar.get((id - 1) / 2) : probFoot.get(id / 2);
        }
        id = id % 2 == 1 ? -id / 2 : id / 2;
        if (PathValidator.isDayOver(ant,graph.places.get(Math.abs(id)).timeSpend,
                id > 0 ?
                        graph.getConnectionMatrix().get(ant.getAnt().getCurrentPoint()).get(Math.abs(id)).getFootRoute().getTimeSpent()
                        :
                        graph.getConnectionMatrix().get(ant.getAnt().getCurrentPoint()).get(Math.abs(id)).getCarRoute().getTimeSpent(),
                id > 0 ?
                        graph.getConnectionMatrix().get(0).get(Math.abs(id)).getFootRoute().getTimeSpent()
                        :
                        graph.getConnectionMatrix().get(0).get(Math.abs(id)).getCarRoute().getTimeSpent())) {

            addNextPoint(id,ant);
        } else {
            throw new DayIsOverException();
        }
    }

    public ArrayList<Place> simulate(){
        for (int i = 0; i < 2000; i++) {
            for (AntPathContainer ant: ants) {
                ant.clearMemory();
                for (int j = 0; j < totalDays; j++) {
                    while (true) {
                        try {
                            this.selectNextPoint(ant);
                        } catch (DayIsOverException ex) {
                            ant.finalizeDay();
                            break;
                        }
                    }
                    addNextPoint(0,ant);
                }
                ant.setMark(PathValidator.validatePath(ant,totalBudget,totalDays));
                graph.updateConnectionMatrix(ant.getPath(),ant.getMark());
            }
        }

        ants.sort(comparator);
        for (AntPathContainer ant: ants) {
            System.out.println(ant);
        }
        ArrayList<Place> places = new ArrayList<>();
        for (int placeID:ants.get(ants.size() - 1).getPath()) {
            places.add(graph.places.get(Math.abs(placeID)));
        }
        return places;
    }

    public double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }
}
