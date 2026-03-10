package com.project.flashcards.api.resource;

import com.project.flashcards.application.dto.ReviewFlashcardRequest;
import com.project.flashcards.application.dto.StudyCardResponse;
import com.project.flashcards.application.usecase.GetStudyCardsUseCase;
import com.project.flashcards.application.usecase.ReviewFlashcardUseCase;
import com.project.flashcards.domain.repository.FlashcardRepository;
import com.project.flashcards.infrastructure.persistence.repository.FlashcardRepositoryImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.UUID;

@Path("/study")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudyResource {

    private final FlashcardRepository repository = new FlashcardRepositoryImpl();

    private final GetStudyCardsUseCase getStudyCardsUseCase;
    private final ReviewFlashcardUseCase reviewFlashcardUseCase;

    @Inject
    public StudyResource(FlashcardRepository repository) {
        this.getStudyCardsUseCase = new GetStudyCardsUseCase(repository);
        this.reviewFlashcardUseCase = new ReviewFlashcardUseCase(repository);
    }

    @GET
    @Path("/today")
    public List<StudyCardResponse> getStudyCards() {
        return getStudyCardsUseCase.execute();
    }

    @POST
    @Path("/{id}/review")
    public void review(@PathParam("id") UUID id, ReviewFlashcardRequest request) {
        reviewFlashcardUseCase.execute(id, request);
    }
}
