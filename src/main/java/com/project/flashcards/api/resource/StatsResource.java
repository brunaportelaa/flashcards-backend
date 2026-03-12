package com.project.flashcards.api.resource;

import com.project.flashcards.application.dto.StudyStatsResponse;
import com.project.flashcards.application.usecase.GetStudyStatsUseCase;
import com.project.flashcards.domain.repository.FlashcardRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/stats")
@Produces(MediaType.APPLICATION_JSON)
public class StatsResource {

    private final GetStudyStatsUseCase useCase;

    @Inject
    public StatsResource(FlashcardRepository repository) {
        this.useCase = new GetStudyStatsUseCase(repository);
    }

    @GET
    public StudyStatsResponse stats() {
        return useCase.execute();
    }


}
