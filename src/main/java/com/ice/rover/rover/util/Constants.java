package com.ice.rover.rover.util;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

@Component
public class Constants {

    private static final String BASE_STRING = "messages/api_response_messages";
    private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

    private static String getApplicationMessage(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle(BASE_STRING, DEFAULT_LOCALE);
        return bundle.getString(key);
    }

    public static String APPLICATION_REQUEST_SUCCESS = Constants
            .getApplicationMessage("application.request.success.message");
    public static String APPLICATION_REQUEST_FAILURE = Constants
            .getApplicationMessage("application.request.failure.message");
    public static String APPLICATION_REQUEST_MOON_ROVER_PLACE_SUCCESS_MESSAGE = Constants
            .getApplicationMessage("application.rover.place.success.message");
    public static String APPLICATION_REQUEST_MOON_ROVER_PLACE_FAILURE_MESSAGE = Constants
            .getApplicationMessage("application.rover.place.failure.message");
    public static String APPLICATION_BAD_REQUEST = Constants.getApplicationMessage("application.bad.request");

    public static String API_ROVER_PLACE_REQUEST = Constants
            .getApplicationMessage("api.rover.place.request");
    public static String API_ROVER_PLACE_REQUEST_SUCCESS = Constants
            .getApplicationMessage("api.rover.place.request.success");
    public static String API_ROVER_PLACE_REQUEST_FAILURE = Constants
            .getApplicationMessage("api.rover.place.request.failure");

    public static String API_ROVER_MOVE_REQUEST = Constants.getApplicationMessage("api.rover.move.request");
    public static String API_ROVER_MOVE_REQUEST_SUCCESS = Constants
            .getApplicationMessage("api.rover.move.request.success");
    public static String API_ROVER_MOVE_REQUEST_ROVER_NOT_PLACED = Constants
            .getApplicationMessage("api.rover.move.request.rover.not.placed");
    public static String API_ROVER_MOVE_REQUEST_FAILURE = Constants
            .getApplicationMessage("api.rover.move.request.failure");

    public static String API_ROVER_TURN_REQUEST = Constants.getApplicationMessage("api.rover.turn.request");
    public static String API_ROVER_TURN_REQUEST_SUCCESS = Constants
            .getApplicationMessage("api.rover.turn.request.success");
    public static String API_ROVER_TURN_REQUEST_ROVER_NOT_PLACED = Constants
            .getApplicationMessage("api.rover.turn.request.river.not.placed");
    public static String API_ROVER_TURN_REQUEST_FAILURE = Constants
            .getApplicationMessage("api.rover.turn.request.failure");
    public static String API_ROVER_TURN_COMMAND_LEFT = Constants.getApplicationMessage("api.rover.turn.command");

    public static String API_ROVER_GENERATE_REQUEST = Constants.getApplicationMessage("api.rover.report.request");
    public static String API_ROVER_GENERATE_REQUEST_SUCCESS = Constants
            .getApplicationMessage("api.rover.turn.request.success");
    public static String API_ROVER_GENERATE_REQUEST_ROVER_NOT_PLACED = Constants
            .getApplicationMessage("api.rover.turn.request.rover.not.placed");
    public static String API_ROVER_GENERATE_REQUEST_FAILURE = Constants
            .getApplicationMessage("api.rover.turn.request.failure");

}
