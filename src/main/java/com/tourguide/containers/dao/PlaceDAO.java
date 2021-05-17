package com.tourguide.containers.dao;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity(name = "Places")
@Table(name = "Places")
public class PlaceDAO {
    @Id
    public int id;
    @Column(name = "name")
    public String name;
    @Column(name = "price")
    public int price;
    @Column(name = "timeStart")
    public double timeStart;
    @Column(name = "timeEnd")
    public double timeEnd;
    @Column(name = "timeSpend")
    public double timeSpend;
    @Column(name = "stringLocation")
    public String stringLocation;
    @Column(name = "latitude")
    public double latitude;
    @Column(name = "longitude")
    public double longitude;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public PlaceDAO(){}
    public PlaceDAO(String name, int price, double timeStart, double timeEnd, double timeSpend, String stringLocation, double latitude, double longitude, double grade, int type) {
        this.name = name;
        this.price = price;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.timeSpend = timeSpend;
        this.stringLocation = stringLocation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.grade = grade;
        this.type = type;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double grade;
    public int type;
}
