package com.ice.rover.rover.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ice.rover.rover.util.Constants;


@SpringBootTest
@AutoConfigureMockMvc
public class MoonRoverControllerTest {

    @Autowired
    private MockMvc mockMvc;

   

    @Test
    public void shouldPlaceTheRoverIfValidCoordinatesAreProvided() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/rover/place")
        .content("{\"xAxis\":4,\"yAxis\":3,\"direction\":\"west\"}")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode").value(200))
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Constants.APPLICATION_REQUEST_SUCCESS))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.API_ROVER_PLACE_REQUEST_SUCCESS));
    }


    @Test
    public void shouldNotPlaceTheRoverIfInValidCoordinatesAreProvided() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/rover/place")
        .content("{\"xAxis\":4,\"yAxis\":13,\"direction\":\"west\"}")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode").value(200))
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Constants.APPLICATION_REQUEST_FAILURE))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.API_ROVER_PLACE_REQUEST_FAILURE));
    }


    @Test
    public void shouldThrowBadRequestWhenPlacingWIthInvalidData() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/rover/place")
        .content("{\"xAxis\":4,\"yAxis\":13,\"direction\":\"westwe\"}")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode").value(400))
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Constants.APPLICATION_REQUEST_FAILURE))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("JSON parse error: Invalid Direction westwe"));
    }


    @Test
    public void shouldMoveTheRoverByOneUnitWithValidInput() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/rover/place")
        .content("{\"xAxis\":2,\"yAxis\":1,\"direction\":\"west\"}")
        .contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/rover/move")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode").value(200))
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Constants.APPLICATION_REQUEST_SUCCESS))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.API_ROVER_MOVE_REQUEST_SUCCESS));

    }


    @Test
    public void shouldNotMoveTheRoverByOneUnitWhenRoverIsAtTheEdgeAndMoveWillDestroyTheRover() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/rover/place")
        .content("{\"xAxis\":0,\"yAxis\":0,\"direction\":\"west\"}")
        .contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/rover/move")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode").value(400))
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Constants.APPLICATION_REQUEST_FAILURE))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.API_ROVER_MOVE_REQUEST_FAILURE));
    }



    @Test
    public void shouldTurnTheRoverToWestIfCommandIsRightAndFAcingSouth() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/rover/place")
        .content("{\"xAxis\":2,\"yAxis\":1,\"direction\":\"south\"}")
        .contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/rover/turn?direction=Right")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode").value(200))
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Constants.APPLICATION_REQUEST_SUCCESS))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.API_ROVER_TURN_REQUEST_SUCCESS));

    }

    @Test
    public void shouldGenerateReportOfRoverLocation() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/rover/place")
        .content("{\"xAxis\":2,\"yAxis\":1,\"direction\":\"south\"}")
        .contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/rover/"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode").value(200))
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Constants.APPLICATION_REQUEST_SUCCESS))
        .andExpect(MockMvcResultMatchers.jsonPath("$.coordinates.xAxis").value("2"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.coordinates.yAxis").value("1"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.coordinates.direction").value("SOUTH"));

    }

   
    
}
