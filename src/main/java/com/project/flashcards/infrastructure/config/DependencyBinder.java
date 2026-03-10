package com.project.flashcards.infrastructure.config;

import com.project.flashcards.domain.repository.FlashcardRepository;
import com.project.flashcards.infrastructure.persistence.repository.FlashcardRepositoryImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class DependencyBinder extends AbstractBinder  {

    @Override
    protected void configure() {
        bind(FlashcardRepositoryImpl.class)
                .to(FlashcardRepository.class);
    }
}
