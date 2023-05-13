package com.ice.rover.rover.configuration;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;



@Configuration
@OpenAPIDefinition
public class SpringDocConfiguration {

    public OpenAPI basOpenAPI(){
        return new OpenAPI().info(new Info().title("Moon Rover Application")
        .version("1.0.0")
        .description("This documentation contains all API's developed for thsi application"));
        
    }
    
}
