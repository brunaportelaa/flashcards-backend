package com.project.flashcards.application.usecase;

import com.project.flashcards.application.dto.StudyCardResponse;
import com.project.flashcards.application.dto.StudySessionResponse;
import com.project.flashcards.application.mapper.StudyCardMapper;
import com.project.flashcards.domain.model.Flashcard;
import com.project.flashcards.domain.repository.FlashcardRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class GetStudyCardsUseCase {

    private final FlashcardRepository repository;

    public GetStudyCardsUseCase(FlashcardRepository repository) {
        this.repository = repository;
    }

    public StudySessionResponse execute() {

        List<StudyCardResponse> items = repository.findDueCards(LocalDate.now())
                .stream()
                .map(flashcard -> {
                    StudyCardResponse response = new StudyCardResponse();
                    response.id = flashcard.getId();
                    response.front = flashcard.getFront();
                    response.back = flashcard.getBack();
                    return response;
                })
                .toList();

        return new StudySessionResponse(items);
    }
}
