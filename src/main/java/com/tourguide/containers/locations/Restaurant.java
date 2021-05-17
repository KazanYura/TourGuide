package com.tourguide.containers.locations;

import com.tourguide.containers.general.Place;
import com.tourguide.containers.general.Point;

public class Restaurant extends Place {
    public Restaurant(String name, int price, double timeStart, double timeEnd, String stringLocation, Point point, double grade) {
        super(name, price, timeStart, timeEnd, 2, stringLocation, point, grade, 5);
    }
}
