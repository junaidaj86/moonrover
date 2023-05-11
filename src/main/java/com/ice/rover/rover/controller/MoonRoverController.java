package com.ice.rover.rover.controller;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ice.rover.rover.model.Coordinates;
import com.ice.rover.rover.service.MoonRoverOperations;
import com.ice.rover.rover.util.Constants;
import com.ice.rover.rover.util.Response;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/rover/")
public class MoonRoverController {

    Logger logger = LoggerFactory.getLogger(MoonRoverController.class);

    @Autowired
    private MoonRoverOperations moonRoverOperations;

    @PostMapping(value = "place")
    public Response placeTheRiver(@Valid @RequestBody Coordinates coordinates, BindingResult bindingResult) {
        logger.info(MessageFormat.format(Constants.API_ROVER_PLACE_REQUEST,
                coordinates.getxAxis(), coordinates.getyAxis()));

        if (bindingResult.hasErrors()) {
            logger.error(Constants.API_ROVER_PLACE_REQUEST_FAILURE, coordinates.getxAxis(),
                    coordinates.getyAxis());
            return new Response(450, 480, Constants.APPLICATION_REQUEST_FAILURE,
                    bindingResult.getAllErrors().toString());
        }
        return moonRoverOperations.place(coordinates);

    }

    @PostMapping(value = "move")
    public Response moveTheRover() {
        logger.info(Constants.API_ROVER_MOVE_REQUEST);
        return moonRoverOperations.move();
    }

    @PostMapping(value="turn")
    public Response turnTheRover(@RequestParam String direction){
        logger.info(Constants.API_ROVER_TURN_REQUEST);
        return moonRoverOperations.turn(direction);
    }


    @GetMapping
    public Response getRoverCoordinates(){
        logger.info(Constants.API_ROVER_GENERATE_REQUEST);
        return moonRoverOperations.report();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response handleInvalidRequest(HttpMessageNotReadableException ex) {
        logger.info(Constants.APPLICATION_BAD_REQUEST);
        Response response = new Response(400, 421, Constants.APPLICATION_REQUEST_FAILURE, ex.getMessage());
        return response;
    }
}
