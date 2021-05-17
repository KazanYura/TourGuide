package com.tourguide.containers.algorithm;

public class DoubleRouteContainer {
    public Route footRoute;
    public Route carRoute;

    public DoubleRouteContainer(Route footRoute, Route carRoute) {
        this.footRoute = footRoute;
        this.carRoute = carRoute;
    }

    public Route getFootRoute() {
        return footRoute;
    }

    public void setFootRoute(Route footRoute) {
        this.footRoute = footRoute;
    }

    public Route getCarRoute() {
        return carRoute;
    }

    public void setCarRoute(Route carRoute) {
        this.carRoute = carRoute;
    }
}
