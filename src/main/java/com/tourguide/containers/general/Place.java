package com.tourguide.containers.general;

import com.tourguide.containers.dao.PlaceDAO;

import java.util.Objects;

public class Place {
    public long id;
    public String name;
    public int price;
    public double timeStart;
    public double timeEnd;
    public double timeSpend;
    public String stringLocation;
    public Point point;
    public double grade;
    public int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public PlaceDAO toDAO(){
        return new PlaceDAO(this.name,this.price,this.timeStart,this.timeEnd,this.timeSpend,this.stringLocation,
                this.point.latitude,this.point.longitude,this.grade,this.type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return price == place.price && Double.compare(place.timeStart, timeStart) == 0 && Double.compare(place.timeEnd, timeEnd) == 0 && timeSpend == place.timeSpend && Double.compare(place.grade, grade) == 0 && type == place.type && name.equals(place.name) &&  stringLocation.equals(place.stringLocation) && Objects.equals(point, place.point);
    }
    public static double angleToRadians(double angle){
        return angle*Math.PI / 180;
    }
    public double getPheromone(double budget,double distance){
        return this.grade * (this.price / budget) * (1 / distance);
    }
    public Place(String name, int price, double timeStart, double timeEnd, double timeSpend, String stringLocation, Point point, double grade, int type) {
        this.name = name;
        this.price = price;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.timeSpend = timeSpend;
        this.stringLocation = stringLocation;
        this.point = point;
        this.grade = grade;
        this.type = type;
    }
    public Place(PlaceDAO placeDAO){
        this.name = placeDAO.name;
        this.price = placeDAO.price;
        this.timeStart = placeDAO.timeStart;
        this.timeEnd = placeDAO.timeEnd;
        this.timeSpend = placeDAO.timeSpend;
        this.stringLocation = placeDAO.stringLocation;
        this.point = new Point(placeDAO.latitude,placeDAO.longitude);
        this.grade = placeDAO.grade;
        this.type = placeDAO.type;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(double timeStart) {
        this.timeStart = timeStart;
    }

    public double getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(double timeEnd) {
        this.timeEnd = timeEnd;
    }

    public double getTimeSpend() {
        return timeSpend;
    }

    public void setTimeSpend(double timeSpend) {
        this.timeSpend = timeSpend;
    }

    public String getStringLocation() {
        return stringLocation;
    }

    public void setStringLocation(String stringLocation) {
        this.stringLocation = stringLocation;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                ", timeSpend=" + timeSpend +
                ", stringLocation='" + stringLocation + '\'' +
                ", point=" + point +
                ", grade=" + grade +
                '}';
    }


    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

}
