package com.project.flashcards.infrastructure.persistence.repository;

import com.project.flashcards.domain.model.Flashcard;
import com.project.flashcards.domain.repository.FlashcardRepository;
import com.project.flashcards.infrastructure.persistence.entity.FlashcardEntity;
import com.project.flashcards.infrastructure.persistence.entity.FlashcardMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
public class FlashcardRepositoryImpl implements FlashcardRepository {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public Flashcard save(Flashcard flashcard) {

        FlashcardEntity entity = FlashcardMapper.toEntity(flashcard);
        em.merge(entity);

        return flashcard;
    }

    @Override
    public Optional<Flashcard> findById(UUID id) {

        FlashcardEntity entity = em.find(FlashcardEntity.class, id);
        return Optional.ofNullable(entity)
                .map(FlashcardMapper::toDomain);

    }

    @Override
    public List<Flashcard> findAll() {
        List<FlashcardEntity> entities =
                em.createQuery("FROM FlashcardEntity", FlashcardEntity.class)
                        .getResultList();

        return entities.stream().map(FlashcardMapper::toDomain).toList();
    }

    @Override
    public List<Flashcard> findDueCards(LocalDate today) {
        List<FlashcardEntity> entities = em.createQuery(
                            "SELECT f FROM FlashcardEntity f WHERE f.reviewStats.nextReviewDate <= :today"
                            , FlashcardEntity.class)
                    .setParameter("today", today)
                    .getResultList();

        return entities.stream()
                    .map(FlashcardMapper::toDomain)
                    .toList();

    }

    @Override
    @Transactional
    public void delete(UUID id) {

        FlashcardEntity entity = em.find(FlashcardEntity.class, id);

        if (entity != null) {
            em.remove(entity);
        }
    }

    @Override
    public List<Flashcard> findByTag(String tag) {

        List<FlashcardEntity> entities = em.createQuery(
                            "SELECT DISTINCT f FROM FlashcardEntity f JOIN f.tags t WHERE t = :tag"
                            , FlashcardEntity.class)
                    .setParameter("tag", tag)
                    .getResultList();

        return entities.stream()
                    .map(FlashcardMapper::toDomain)
                    .toList();

    }
}
