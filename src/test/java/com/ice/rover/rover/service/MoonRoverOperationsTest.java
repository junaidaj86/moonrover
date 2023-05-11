package com.ice.rover.rover.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ice.rover.rover.model.Coordinates;
import com.ice.rover.rover.model.Direction;
import com.ice.rover.rover.util.Constants;
import com.ice.rover.rover.util.Response;

@SpringBootTest
public class MoonRoverOperationsTest {

    @Autowired
    private MoonRoverOperations moonRoverOperations;


    @Test
    public void shouldPlaceTheRoverWithValidCoordinates(){
        Coordinates coordinates = new Coordinates(0,1,Direction.NORTH);
        Response response = moonRoverOperations.place(coordinates);
        assertEquals(Constants.APPLICATION_REQUEST_SUCCESS, response.getStatus());
        assertEquals(200, response.getResponseCode());
        assertEquals(Constants.API_ROVER_PLACE_REQUEST_SUCCESS, response.getMessage());
    }

    @Test
    public void shouldNotPlaceTheRoverWithInValidCoordinates(){
        Coordinates coordinates = new Coordinates(10,1,Direction.NORTH);
        Response response = moonRoverOperations.place(coordinates);
        assertEquals(Constants.APPLICATION_REQUEST_FAILURE, response.getStatus());
        assertEquals(200, response.getResponseCode());
        assertEquals(Constants.API_ROVER_PLACE_REQUEST_FAILURE, response.getMessage());
    }
}
