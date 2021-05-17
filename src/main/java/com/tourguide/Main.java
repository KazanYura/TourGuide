package com.tourguide;

import com.tourguide.algorithm.AntAlgo;
import com.tourguide.containers.general.Place;
import com.tourguide.containers.general.Point;
import com.tourguide.database.DatabaseConnector;
import com.tourguide.containers.dao.PlaceDAO;
import com.tourguide.visual.Visualizator;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseConnector dataBaseConnector = DatabaseConnector.getInstance();
        List<PlaceDAO> placesDAO = dataBaseConnector.listPlaces().subList(0,100);
        Place nightSpend = new Place("Night",0,0,0,0,"",new Point(49.79312169002777466 ,24.05641539002778018),0,6);
        ArrayList<Place> places = new ArrayList<>();
        for (PlaceDAO dao: placesDAO) {
            places.add(new Place(dao));
        }
        AntAlgo antAlgo = new AntAlgo(places,2,1000,nightSpend);
        System.out.println("Build was completed");
        ArrayList<Place> placesRes = antAlgo.simulate();
        Visualizator.drawMap(placesRes);
    }
}
