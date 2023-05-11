package com.ice.rover.rover.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ice.rover.rover.model.Coordinates;

@Component
public class MoonRoverHelper {

    @Value("${xaxis.of.board.dimension}")
    private int xaxisDimension;
    @Value("${yaxis.of.board.dimension}")
    private int yaxisDimension;

    public boolean validateCoordinates(Coordinates coordinates) {
        if (coordinates.getxAxis() < 0 || coordinates.getxAxis() > xaxisDimension
                || coordinates.getyAxis() < 0 || coordinates.getyAxis() > yaxisDimension) {
            return false;
        } else {
            return true;
        }
    }
}
