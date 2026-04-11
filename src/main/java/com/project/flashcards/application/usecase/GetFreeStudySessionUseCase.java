package com.project.flashcards.application.usecase;

import com.project.flashcards.application.dto.StudyCardResponse;
import com.project.flashcards.application.dto.StudySessionResponse;
import com.project.flashcards.application.mapper.StudyCardMapper;
import com.project.flashcards.domain.repository.FlashcardRepository;

import java.util.List;

public class GetFreeStudySessionUseCase {
    private final FlashcardRepository repository;

    public GetFreeStudySessionUseCase(FlashcardRepository repository) {
        this.repository = repository;
    }

    public StudySessionResponse execute() {
        List<StudyCardResponse> items = repository.findAllSortedByPriority()
                .stream()
                .map(StudyCardMapper::toResponse)
                .toList();

        return new StudySessionResponse(items);
    }
}
