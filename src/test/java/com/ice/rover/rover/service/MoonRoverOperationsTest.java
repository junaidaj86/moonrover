package com.ice.rover.rover.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ice.rover.rover.model.Coordinates;
import com.ice.rover.rover.model.Direction;
import com.ice.rover.rover.util.Constants;
import com.ice.rover.rover.util.MoonRoverHelper;
import com.ice.rover.rover.util.Response;

@SpringBootTest
public class MoonRoverOperationsTest {

    @Autowired
    private MoonRoverOperations moonRoverOperations;

    @Mock
    MoonRoverHelper moonRoverHelper;


    @Test
    public void shouldPlaceTheRoverWithValidCoordinates(){
        Coordinates coordinates = new Coordinates(0,1,Direction.NORTH);
        Mockito.when(moonRoverHelper.validateCoordinates(coordinates)).thenReturn(true);
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

    @Test
    public void shouldTurnTheRoverToEastWhenCommandIsLeftIfCurrentFacingIsNorth(){
        Coordinates coordinates = new Coordinates(0, 0, Direction.NORTH);
        moonRoverOperations.place(coordinates);
        coordinates.setDirection(Direction.NORTH);
        Response response = moonRoverOperations.turn("LEFT");
        assertEquals(Constants.APPLICATION_REQUEST_SUCCESS, response.getStatus());
        assertEquals(200, response.getResponseCode());
        assertEquals(Constants.API_ROVER_TURN_REQUEST_SUCCESS, response.getMessage());
    }

    @Test
    public void shouldTurnTheRoverToWestWhenCommandIsRightIfCurrentFacingIsSouth(){
        Coordinates coordinates = new Coordinates(0, 0, Direction.SOUTH);
        moonRoverOperations.place(coordinates);
        coordinates.setDirection(Direction.SOUTH);
        Response response = moonRoverOperations.turn("RIGHT");
        assertEquals(Constants.APPLICATION_REQUEST_SUCCESS, response.getStatus());
        assertEquals(200, response.getResponseCode());
        assertEquals(Constants.API_ROVER_TURN_REQUEST_SUCCESS, response.getMessage());
    }

    @Test
    public void shouldMoveTheRoverOneUnitIfWithinDimensions(){
        Coordinates coordinates = new Coordinates(2, 2, Direction.SOUTH);
        moonRoverOperations.place(coordinates);
        coordinates.setDirection(Direction.SOUTH);
        Response response = moonRoverOperations.move();
        assertEquals(Constants.APPLICATION_REQUEST_SUCCESS, response.getStatus());
        assertEquals(200, response.getResponseCode());
        assertEquals(Constants.API_ROVER_MOVE_REQUEST_SUCCESS, response.getMessage());
    }


    @Test
    public void shouldNotMoveTheRoverIfEdgeIsReached(){
        Coordinates coordinates = new Coordinates(5,5,Direction.NORTH);
        moonRoverOperations.place(coordinates);
        coordinates.setDirection(Direction.NORTH);
        Response response = moonRoverOperations.move();
        assertEquals(Constants.APPLICATION_REQUEST_FAILURE, response.getStatus());
        assertEquals(400, response.getResponseCode());
        assertEquals(Constants.API_ROVER_MOVE_REQUEST_FAILURE, response.getMessage());
    }

   

    @Test
    public void shouldReportRoverCurrentPosition(){
        Coordinates coordinates = new Coordinates(5,5,Direction.NORTH);
        moonRoverOperations.place(coordinates);
        coordinates.setDirection(Direction.NORTH);
        Response response = moonRoverOperations.report();
        assertEquals(Constants.APPLICATION_REQUEST_SUCCESS, response.getStatus());
        assertEquals(200, response.getResponseCode());
        assertEquals(Constants.API_ROVER_GENERATE_REQUEST_SUCCESS, response.getMessage());
    }

    
}
