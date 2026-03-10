package com.project.flashcards.api.exception;

import com.project.flashcards.application.dto.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class FlashcardNotFoundExceptionMapper implements ExceptionMapper<FlashcardNotFoundException> {

    @Override
    public Response toResponse(FlashcardNotFoundException exception) {

        ErrorResponse error = new ErrorResponse(exception.getMessage());

        return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }
}
