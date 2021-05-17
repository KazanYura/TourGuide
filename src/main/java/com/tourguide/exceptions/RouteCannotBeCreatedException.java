package com.tourguide.exceptions;

public class RouteCannotBeCreatedException extends Exception {
        public RouteCannotBeCreatedException(String errorMessage) {
            super(errorMessage);
        }
}
