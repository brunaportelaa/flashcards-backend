package com.project.flashcards.application.usecase;

import com.project.flashcards.application.dto.FlashcardResponse;
import com.project.flashcards.application.dto.FlashcardSortField;
import com.project.flashcards.application.dto.PagedResponse;
import com.project.flashcards.application.dto.SortDirection;
import com.project.flashcards.application.mapper.FlashcardDtoMapper;
import com.project.flashcards.application.mapper.FlashcardSortMapper;
import com.project.flashcards.domain.repository.FlashcardRepository;

import java.util.List;

public class ListFlashcardsPagedUseCase {

    private final FlashcardRepository repository;

    public ListFlashcardsPagedUseCase(FlashcardRepository repository) {
        this.repository = repository;
    }

    public PagedResponse<FlashcardResponse> execute(
            int page,
            int size,
            String sort,
            String direction){

        if(page < 0) {
            throw new IllegalArgumentException("Page must be greater than or equal to 0");
        }

        if (size <= 0 || size > 100) {
            throw new IllegalArgumentException("Size must be between 1 and 100");
        }

        FlashcardSortField sortField = parseSortField(sort);
        SortDirection sortDirection = parseDirection(direction);

        String jpaField = FlashcardSortMapper.toJpaField(sortField);
        String jpaDirection = FlashcardSortMapper.toJpaDirection(sortDirection);

        List<FlashcardResponse> items = repository.findAllPaged(page, size,jpaField, jpaDirection)
                .stream()
                .map(FlashcardDtoMapper::toResponse)
                .toList();

        long totalItems = repository.countAll();


        return new PagedResponse<FlashcardResponse>(items, page, size, totalItems);
    }

    private FlashcardSortField parseSortField(String sort) {
        if (sort == null || sort.isBlank()) {
            return FlashcardSortField.FRONT;
        }

        return switch (sort.toLowerCase()) {
            case "front" -> FlashcardSortField.FRONT;
            case "back" -> FlashcardSortField.BACK;
            case "level" -> FlashcardSortField.LEVEL;
            case "nextreviewdate" -> FlashcardSortField.NEXT_REVIEW_DATE;
            default -> throw new IllegalArgumentException("Invalid sort field: " + sort);
        };
    }

    private SortDirection parseDirection(String direction) {
        if ( direction == null || direction.isBlank()) {
            return SortDirection.ASC;
        }

        return switch (direction.toLowerCase()) {
            case "asc" -> SortDirection.ASC;
            case "desc" -> SortDirection.DESC;
            default -> throw new IllegalArgumentException("Invalid sort direction: " + direction);
        };
    }


}
