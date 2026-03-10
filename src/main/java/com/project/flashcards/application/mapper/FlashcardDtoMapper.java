package com.project.flashcards.application.mapper;

import com.project.flashcards.application.dto.CreateFlashcardRequest;
import com.project.flashcards.application.dto.FlashcardResponse;
import com.project.flashcards.domain.model.Flashcard;

public class FlashcardDtoMapper {

    public static Flashcard toDomain(CreateFlashcardRequest request) {
        Flashcard card = new Flashcard(request.front, request.back);
        return card;
    }

    public static FlashcardResponse toResponse(Flashcard card) {

        FlashcardResponse response = new FlashcardResponse();

        response.id = card.getId();
        response.front = card.getFront();
        response.back = card.getBack();

        return response;
    }
}
