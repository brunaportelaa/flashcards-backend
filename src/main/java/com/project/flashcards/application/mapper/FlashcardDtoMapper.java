package com.project.flashcards.application.mapper;

import com.project.flashcards.application.dto.FlashcardRequestDTO;
import com.project.flashcards.application.dto.FlashcardResponseDTO;
import com.project.flashcards.domain.model.Flashcard;

public class FlashcardDtoMapper {

    public static Flashcard toDomain(FlashcardRequestDTO request) {
        Flashcard card = new Flashcard(request.front, request.back);
        return card;
    }

    public static FlashcardResponseDTO toResponse(Flashcard card) {

        FlashcardResponseDTO response = new FlashcardResponseDTO();

        response.id = card.getId();
        response.front = card.getFront();
        response.back = card.getBack();

        return response;
    }
}
