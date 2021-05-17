package com.tourguide.containers.algorithm;

import com.tourguide.containers.Constants;
import com.tourguide.containers.enums.TravelType;
import com.tourguide.containers.general.Place;
import com.tourguide.exceptions.RouteCannotBeCreatedException;
import com.tourguide.routino.RoutinoAdapter;

import java.util.ArrayList;

public class Graph {
    private final ArrayList<ArrayList<DoubleRouteContainer>> connectionMatrix = new ArrayList<>();
    public ArrayList<Place> places;

    public Graph(ArrayList<Place> places) {
        this.places = places;
    }
    public void createConnectionMatrix() {
        for (Place p: places) {
            ArrayList<DoubleRouteContainer> connections = new ArrayList<>();
            Route temporaryRouteFoot;
            Route temporaryRouteCar;
            for (Place q: places) {
                if (p != q){
                    try {
                        temporaryRouteFoot = RoutinoAdapter.buildRoute(p,q, TravelType.FOOT);
                        temporaryRouteCar = RoutinoAdapter.buildRoute(p,q, TravelType.CAR);
                    } catch (RouteCannotBeCreatedException e) {
                        temporaryRouteFoot = new Route(0,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE, null, null,TravelType.FOOT);
                        temporaryRouteCar = new Route(0,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE, null, null,TravelType.CAR);
                    }
                }
                else {
                    temporaryRouteFoot = new Route(0,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE, null, null,TravelType.FOOT);
                    temporaryRouteCar = new Route(0,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE, null, null,TravelType.CAR);
                }
                connections.add(new DoubleRouteContainer(temporaryRouteFoot,temporaryRouteCar));
            }
            this.connectionMatrix.add(connections);
        }
    }
    public void updateRoute(int from,int to,double additionalPheromone){
        ArrayList<DoubleRouteContainer> raw = this.connectionMatrix.get(Math.abs(from));
        Route r;
        if (to > 0) {
            r = raw.get(to).getFootRoute();
            r.updatePheromones(additionalPheromone);
            raw.set(to,new DoubleRouteContainer(r,raw.get(to).getCarRoute()));
        } else {
            r = raw.get(Math.abs(to)).getCarRoute();
            r.updatePheromones(additionalPheromone);
            raw.set(Math.abs(to),new DoubleRouteContainer(raw.get(Math.abs(to)).getFootRoute(),r));
        }
    }
    public void updateConnectionMatrix(ArrayList<Integer> path, double experience){
        double additionalPheromone = Constants.updatePheromonesConst * experience;
        for (int i = 1; i < path.size(); i++) {
            int from = path.get(i - 1);
            int to = path.get(i);
            this.updateRoute(from,to,additionalPheromone);
            this.updateRoute(to,from,additionalPheromone);
        }
    }
    public ArrayList<ArrayList<Double>> getProbabilitiesForSelectedAnt(Ant ant){
        ArrayList<DoubleRouteContainer> doubleRouteContainers = this.connectionMatrix.get(ant.getCurrentPoint());
        ArrayList<Route> routesFoot = new ArrayList<>();
        ArrayList<Route> routesCar = new ArrayList<>();
        for (DoubleRouteContainer d:doubleRouteContainers) {
            routesCar.add(d.carRoute);
            routesFoot.add(d.footRoute);
        }
        ArrayList<Double> probabilitiesFoot = new ArrayList<>();
        ArrayList<Double> probabilitiesCar = new ArrayList<>();
        for (int i = 0; i < doubleRouteContainers.size(); i++) {
            if (!ant.getPath().contains(i) && routesFoot.get(i).getLength() > 0){
                probabilitiesFoot.add(Math.pow(routesFoot.get(i).getPheromones(),Constants.alfa) *
                        Math.pow(routesFoot.get(i).getTo().grade,Constants.beta));
            }
            else {
                probabilitiesFoot.add(0.0);
            }
            if (!ant.getPath().contains(i) && routesCar.get(i).getLength() > 0){
                probabilitiesCar.add(Math.pow(routesCar.get(i).getPheromones(),Constants.alfa) *
                        Math.pow(routesCar.get(i).getTo().grade,Constants.beta));
            }
            else {
                probabilitiesCar.add(0.0);
            }
        }
        double divisor = probabilitiesCar.stream().mapToDouble(a -> a).sum() + probabilitiesFoot.stream().mapToDouble(a -> a).sum();
        for (int i = 0; i < probabilitiesCar.size(); i++) {
            probabilitiesCar.set(i,probabilitiesCar.get(i) / divisor);
            probabilitiesFoot.set(i,probabilitiesFoot.get(i) / divisor);
        }
        ArrayList<ArrayList<Double>> response = new ArrayList<>();
        response.add(probabilitiesCar);
        response.add(probabilitiesFoot);
        return response;
    }
    public ArrayList<ArrayList<DoubleRouteContainer>> getConnectionMatrix() {
        return connectionMatrix;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "connectionMatrix=" + connectionMatrix +
                '}';
    }
}