package com.project.flashcards.api.resource;

import com.project.flashcards.application.dto.CreateFlashcardRequest;
import com.project.flashcards.application.dto.FlashcardResponse;
import com.project.flashcards.application.dto.PagedResponse;
import com.project.flashcards.application.usecase.*;
import com.project.flashcards.domain.repository.FlashcardRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Tag(name = "Flashcards", description = "Operações relacionadas aos flashcards")
@Path("/flashcards")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FlashcardResource {

    private final CreateFlashcardUseCase createFlashcardUseCase;
    private final ListFlashcardUseCase listFlashcardsUseCase;
    private final FindFlashcardByTagUseCase findFlashcardByTagUseCase;
    private final DeleteFlashcardUseCase deleteFlashcardUseCase;
    private final ListFlashcardsPagedUseCase listFlashcardsPagedUseCase;

    @Inject
    public FlashcardResource(FlashcardRepository repository) {
        this.createFlashcardUseCase = new CreateFlashcardUseCase(repository);
        this.listFlashcardsUseCase = new ListFlashcardUseCase(repository);
        this.findFlashcardByTagUseCase = new FindFlashcardByTagUseCase(repository);
        this.deleteFlashcardUseCase = new DeleteFlashcardUseCase(repository);
        this.listFlashcardsPagedUseCase = new ListFlashcardsPagedUseCase(repository);
    }

    @POST
    @Operation(summary = "Criar um novo flashcard")
    @APIResponse(
            responseCode = "201",
            description = "Flashcard criado com sucesso"
    )
    @APIResponse(
            responseCode = "400",
            description = "Requisição inválida"
    )
    public Response create(CreateFlashcardRequest request) {
        FlashcardResponse response = createFlashcardUseCase.execute(request);
        return Response
                .status(Response.Status.CREATED)
                .entity(response)
                .build();
    }

    @GET
    @Operation(summary = "Listar todos os flashcards paginados")
    public PagedResponse<FlashcardResponse> list(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size
    ) {
        return listFlashcardsPagedUseCase.execute(page, size);
    }

    @GET
    @Path("/tag/{tag}")
    public List<FlashcardResponse> findByTag(@PathParam("tag") String tag) {
        return findFlashcardByTagUseCase.execute(tag);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deletar um flashcard")
    public Response deleteById(@PathParam("id") UUID id) {
        deleteFlashcardUseCase.execute(id);
        return Response.noContent().build();
    }

}
