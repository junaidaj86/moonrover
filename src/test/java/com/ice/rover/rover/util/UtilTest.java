package com.ice.rover.rover.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.ice.rover.rover.model.Coordinates;
import com.ice.rover.rover.model.Direction;

@SpringBootTest
public class UtilTest {
    @Autowired
    MoonRoverHelper helper;

    @Test
    public void shouldReturnTrueForValidCoordinates(){
        Coordinates coordinates = new Coordinates(2, 3, Direction.EAST);
        assertTrue(helper.validateCoordinates(coordinates));
    }

    @Test
    public void shouldReturnFalseForInValidXCoordinates(){
        Coordinates coordinates = new Coordinates(21, 3, Direction.EAST);
        assertFalse(helper.validateCoordinates(coordinates));
    }

    @Test
    public void shouldReturnFalseForInValidYCoordinates(){
        Coordinates coordinates = new Coordinates(1, 13, Direction.EAST);
        assertFalse(helper.validateCoordinates(coordinates));
    }
}
