package com.tourguide.containers.locations;

import com.tourguide.containers.general.Place;
import com.tourguide.containers.general.Point;

public class PointOfInterest extends Place {
    public PointOfInterest(String name, String stringLocation, Point point, double grade) {
        super(name, 0, 0, 0, 0.5, stringLocation, point, grade, 4);
    }
}
