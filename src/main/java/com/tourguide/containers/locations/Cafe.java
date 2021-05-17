package com.tourguide.containers.locations;

import com.tourguide.containers.general.Place;
import com.tourguide.containers.general.Point;

public class Cafe extends Place {
    public Cafe(String name, int price, double timeStart, double timeEnd, String stringLocation, Point point, double grade) {
        super(name, price, timeStart, timeEnd, 0.5, stringLocation, point, grade, 1);
    }
}
