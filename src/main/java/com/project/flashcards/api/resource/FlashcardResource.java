package com.project.flashcards.api.resource;

import com.project.flashcards.application.dto.CreateFlashcardRequest;
import com.project.flashcards.application.dto.FlashcardResponse;
import com.project.flashcards.application.usecase.CreateFlashcardUseCase;
import com.project.flashcards.application.usecase.ListFlashcardUseCase;
import com.project.flashcards.infrastructure.persistence.repository.FlashcardRepositoryImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/flashcards")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FlashcardResource {

    private final FlashcardRepositoryImpl repository = new FlashcardRepositoryImpl();

    private final CreateFlashcardUseCase createFlashcardUseCase = new CreateFlashcardUseCase(repository);
    private final ListFlashcardUseCase listFlashcardsUseCase = new ListFlashcardUseCase(repository);

    @POST
    public FlashcardResponse create(CreateFlashcardRequest request) {
        return createFlashcardUseCase.execute(request);
    }

    @GET
    public List<FlashcardResponse> list() {
        return listFlashcardsUseCase.execute();
    }
}
