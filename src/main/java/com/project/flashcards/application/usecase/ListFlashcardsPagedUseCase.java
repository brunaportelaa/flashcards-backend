package com.project.flashcards.application.usecase;

import com.project.flashcards.application.dto.FlashcardResponse;
import com.project.flashcards.application.dto.PagedResponse;
import com.project.flashcards.application.mapper.FlashcardDtoMapper;
import com.project.flashcards.application.mapper.FlashcardSortMapper;
import com.project.flashcards.application.query.FlashcardSearchQuery;
import com.project.flashcards.domain.repository.FlashcardRepository;
import com.project.flashcards.domain.repository.FlashcardSearchCriteria;

import java.util.List;

public class ListFlashcardsPagedUseCase {

    private final FlashcardRepository repository;

    public ListFlashcardsPagedUseCase(FlashcardRepository repository) {
        this.repository = repository;
    }

    public PagedResponse<FlashcardResponse> execute(FlashcardSearchQuery query){

        FlashcardSearchCriteria criteria = new FlashcardSearchCriteria(
                query.getPage(),
                query.getSize(),
                query.getSortField(),
                query.getSortDirection(),
                query.getTag(),
                query.isDueToday());

        List<FlashcardResponse> items = repository.search(criteria)
                .stream()
                .map(FlashcardDtoMapper::toResponse)
                .toList();

        long totalItems = repository.count(criteria);

        return new PagedResponse<FlashcardResponse>(items, query.getPage(), query.getSize(), totalItems);
    }


}
