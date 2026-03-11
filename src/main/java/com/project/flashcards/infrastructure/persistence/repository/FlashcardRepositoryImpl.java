package com.project.flashcards.infrastructure.persistence.repository;

import com.project.flashcards.domain.model.Flashcard;
import com.project.flashcards.domain.repository.FlashcardRepository;
import com.project.flashcards.infrastructure.config.JPAUtil;
import com.project.flashcards.infrastructure.persistence.entity.FlashcardEntity;
import com.project.flashcards.infrastructure.persistence.entity.FlashcardMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class FlashcardRepositoryImpl implements FlashcardRepository {

    @Override
    public Flashcard save(Flashcard flashcard) {

        EntityManager em = JPAUtil.getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            FlashcardEntity entity = FlashcardMapper.toEntity(flashcard);
            em.merge(entity);
            transaction.commit();

            return flashcard;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            em.close();
        }

    }

    @Override
    public Optional<Flashcard> findById(UUID id) {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            FlashcardEntity entity = em.find(FlashcardEntity.class, id);
            return Optional.ofNullable(entity)
                    .map(FlashcardMapper::toDomain);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Flashcard> findAll() {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            List<FlashcardEntity> entities =
                    em.createQuery("FROM FlashcardEntity", FlashcardEntity.class)
                            .getResultList();

            return entities.stream().map(FlashcardMapper::toDomain).collect(Collectors.toList());
        } finally {
            em.close();
        }

    }

    @Override
    public List<Flashcard> findDueCards(LocalDate today) {

        EntityManager em = JPAUtil.getEntityManager();

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
    public void delete(UUID id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            FlashcardEntity entity = em.find(FlashcardEntity.class, id);

            if(entity != null) {
                em.remove(entity);
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
