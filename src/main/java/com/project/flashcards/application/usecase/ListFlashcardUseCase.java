package com.project.flashcards.application.usecase;

import com.project.flashcards.application.dto.FlashcardResponseDTO;
import com.project.flashcards.application.mapper.FlashcardDtoMapper;
import com.project.flashcards.domain.repository.FlashcardRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ListFlashcardUseCase {

    private final FlashcardRepository repository;

    public ListFlashcardUseCase(FlashcardRepository repository){
        this.repository = repository;
    }

    public List<FlashcardResponseDTO> execute() {
        return repository.findAll()
                .stream()
                .map(FlashcardDtoMapper::toResponse)
                .collect(Collectors.toList());
    }

}
