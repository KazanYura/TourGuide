package com.tourguide.containers.locations;

import com.tourguide.containers.general.Place;
import com.tourguide.containers.general.Point;

public class Park extends Place {
    public Park(String name, String stringLocation, Point point, double grade) {
        super(name, 0, 0, 0, 2, stringLocation, point, grade, 3);
    }
}
