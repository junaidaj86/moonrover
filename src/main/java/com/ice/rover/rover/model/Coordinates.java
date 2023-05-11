package com.ice.rover.rover.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ice.rover.rover.util.DirectionDeserializer;

import jakarta.validation.constraints.NotNull;

public class Coordinates {
    @NotNull(message= "xAxis cannot be empty or null")
    private Integer xAxis;
    @NotNull(message= "yAxis cannot be empty or null")
    private Integer yAxis;
    @NotNull(message= "Direction cannot be empty or null")
    @JsonDeserialize(using = DirectionDeserializer.class)
    private Direction direction;
    
    public Integer getxAxis() {
        return xAxis;
    }
    public void setxAxis(Integer xAxis) {
        this.xAxis = xAxis;
    }
    public Integer getyAxis() {
        return yAxis;
    }
    public void setyAxis(Integer yAxis) {
        this.yAxis = yAxis;
    }
    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public Coordinates(Integer xAxis, Integer yAxis, Direction direction) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.direction = direction;
    }
    public Coordinates() {
    }

    @Override
    public String toString() {
        return "Coordinates [xAxis=" + xAxis + ", yAxis=" + yAxis + ", direction=" + direction + "]";
    } 
    
}
