package com.project.flashcards.application.mapper;

import com.project.flashcards.application.dto.StudyCardResponse;
import com.project.flashcards.domain.model.Flashcard;

public class StudyCardMapper {

    public static StudyCardResponse toResponse(Flashcard card) {
        StudyCardResponse r = new StudyCardResponse();
        r.id = card.getId();
        r.front = card.getFront();
        r.back = card.getBack();
        return r;
    }
}
