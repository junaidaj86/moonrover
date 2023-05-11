package com.ice.rover.rover.service;

import com.ice.rover.rover.model.Coordinates;
import com.ice.rover.rover.util.Response;

public interface Operations {
    public Response place(Coordinates coordinates);

    public Response move();

    public Response turn(String direction);

    public Response report();

}
