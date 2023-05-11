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
;    }
    
}
