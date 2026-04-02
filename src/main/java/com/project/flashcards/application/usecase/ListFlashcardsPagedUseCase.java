package com.project.flashcards.application.usecase;

import com.project.flashcards.application.dto.FlashcardResponse;
import com.project.flashcards.application.dto.PagedResponse;
import com.project.flashcards.application.mapper.FlashcardDtoMapper;
import com.project.flashcards.domain.repository.FlashcardRepository;

import java.util.List;

public class ListFlashcardsPagedUseCase {

    private final FlashcardRepository repository;

    public ListFlashcardsPagedUseCase(FlashcardRepository repository) {
        this.repository = repository;
    }

    public PagedResponse<FlashcardResponse> execute(int page, int size){

        List<FlashcardResponse> items = repository.findAllPaged(page, size)
                .stream()
                .map(FlashcardDtoMapper::toResponse)
                .toList();

        long totalItems = repository.countAll();


        return new PagedResponse<FlashcardResponse>(items, page, size, totalItems);
    }


}
