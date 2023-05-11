package com.ice.rover.rover.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.ice.rover.rover.model.Direction;

public class DirectionDeserializer extends StdDeserializer {

    public DirectionDeserializer(){
        super(Direction.class);
    }

    @Override
    public Direction deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
        String value = jsonParser.getValueAsString();
        if(value != null){
            try {
                return Direction.valueOf(value.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid Direction "+ value);
            }
        }
        return null;
    }
    
}
