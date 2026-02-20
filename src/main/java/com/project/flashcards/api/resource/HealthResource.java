package com.project.flashcards.api.resource;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/health")
public class HealthResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HealthResponse health() {
        return new HealthResponse("UP");
    }

    public static class HealthResponse {

        public String status;

        public HealthResponse(String status){
            this.status = status;
        }
    }
}
