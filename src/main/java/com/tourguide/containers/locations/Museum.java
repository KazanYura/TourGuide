package com.tourguide.containers.locations;

import com.tourguide.containers.general.Place;
import com.tourguide.containers.general.Point;

public class Museum extends Place {
    public Museum(String name, double timeStart, double timeEnd, String stringLocation, Point point, double grade) {
        super(name, 50, timeStart, timeEnd, 1, stringLocation, point, grade, 2);
    }
}
