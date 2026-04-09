package com.project.flashcards.application.usecase;

import com.project.flashcards.application.dto.FlashcardResponse;
import com.project.flashcards.application.dto.PagedResponse;
import com.project.flashcards.application.mapper.FlashcardDtoMapper;
import com.project.flashcards.application.mapper.FlashcardSortMapper;
import com.project.flashcards.application.query.FlashcardSearchQuery;
import com.project.flashcards.domain.repository.FlashcardRepository;

import java.util.List;

public class ListFlashcardsPagedUseCase {

    private final FlashcardRepository repository;

    public ListFlashcardsPagedUseCase(FlashcardRepository repository) {
        this.repository = repository;
    }

    public PagedResponse<FlashcardResponse> execute(FlashcardSearchQuery query){

        String jpaField = FlashcardSortMapper.toJpaField(query.getSortField());
        String jpaDirection = FlashcardSortMapper.toJpaDirection(query.getSortDirection());

        List<FlashcardResponse> items = repository.findAllPaged(query.getPage(), query.getSize(),
                        jpaField, jpaDirection)
                .stream()
                .map(FlashcardDtoMapper::toResponse)
                .toList();

        long totalItems = repository.countAll();


        return new PagedResponse<FlashcardResponse>(items, query.getPage(), query.getSize(), totalItems);
    }


}
