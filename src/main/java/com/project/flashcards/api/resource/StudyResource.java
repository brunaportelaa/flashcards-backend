package com.project.flashcards.api.resource;

import com.project.flashcards.application.dto.ReviewFlashcardRequest;
import com.project.flashcards.application.dto.StudyCardResponse;
import com.project.flashcards.application.usecase.GetStudyCardsUseCase;
import com.project.flashcards.application.usecase.ReviewFlashcardUseCase;
import com.project.flashcards.domain.repository.FlashcardRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/study")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Study", description = "Operações relacionadas a sessão de estudos dos flashcards")
public class StudyResource {

    private final GetStudyCardsUseCase getStudyCardsUseCase;
    private final ReviewFlashcardUseCase reviewFlashcardUseCase;

    @Inject
    public StudyResource(FlashcardRepository repository) {
        this.getStudyCardsUseCase = new GetStudyCardsUseCase(repository);
        this.reviewFlashcardUseCase = new ReviewFlashcardUseCase(repository);
    }

    @GET
    @Path("/today")
    @Operation(summary = "Retorna os cards para a sessão de estudos de hoje")
    public List<StudyCardResponse> getStudyCards() {
        return getStudyCardsUseCase.execute();
    }

    @POST
    @Path("/{id}/review")
    @Operation(summary = "Registra a avaliação de um card")
    public void review(@PathParam("id") UUID id, ReviewFlashcardRequest request) {
        reviewFlashcardUseCase.execute(id, request);
    }
}
