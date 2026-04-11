package com.project.flashcards.infrastructure.persistence.repository;

import com.project.flashcards.domain.model.Flashcard;
import com.project.flashcards.domain.repository.FlashcardRepository;
import com.project.flashcards.domain.repository.FlashcardSearchCriteria;
import com.project.flashcards.infrastructure.persistence.entity.FlashcardEntity;
import com.project.flashcards.infrastructure.persistence.entity.FlashcardEntityMapper;
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

        FlashcardEntity entity = FlashcardEntityMapper.toEntity(flashcard);
        em.merge(entity);

        return flashcard;
    }

    @Override
    public Optional<Flashcard> findById(UUID id) {

        FlashcardEntity entity = em.find(FlashcardEntity.class, id);
        return Optional.ofNullable(entity)
                .map(FlashcardEntityMapper::toDomain);

    }

    @Override
    public List<Flashcard> findAll() {
        List<FlashcardEntity> entities =
                em.createQuery("FROM FlashcardEntity", FlashcardEntity.class)
                        .getResultList();

        return entities.stream().map(FlashcardEntityMapper::toDomain).toList();
    }

    @Override
    public List<Flashcard> search(FlashcardSearchCriteria criteria) {
        StringBuilder jpql  = new StringBuilder("""
                SELECT DISTINCT f
                FROM FlashcardEntity f
                LEFT JOIN f.tags t
                WHERE 1 = 1
                """);

        if (criteria.hasTagFilter()) {
            jpql.append(" AND t = :tag");
        }

        if (criteria.isDueToday()) {
            jpql.append(" AND f.reviewStats.nextReviewDate <= :today");
        }

        jpql.append(" ORDER BY ")
                .append(criteria.getSortField())
                .append(" ")
                .append(criteria.getSortDirection());

        var query = em.createQuery(jpql.toString(), FlashcardEntity.class);

        if (criteria.hasTagFilter()) {
            query.setParameter("tag", criteria.getTag());
        }

        if (criteria.isDueToday()) {
            query.setParameter("today", LocalDate.now());
        }

        return query.setFirstResult(criteria.getPage() * criteria.getSize())
                .setMaxResults(criteria.getSize())
                .getResultList()
                .stream()
                .map(FlashcardEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Flashcard> findDueCards(LocalDate today) {
        List<FlashcardEntity> entities = em.createQuery(
                            "SELECT f FROM FlashcardEntity f WHERE f.reviewStats.nextReviewDate <= :today"
                            , FlashcardEntity.class)
                    .setParameter("today", today)
                    .getResultList();

        return entities.stream()
                    .map(FlashcardEntityMapper::toDomain)
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
                    .map(FlashcardEntityMapper::toDomain)
                    .toList();

    }

    @Override
    public List<Flashcard> findAllPaged(int page, int size, String sortField, String direction) {
        String jpql = """
                SELECT f
                FROM FlashcardEntity f
                ORDER BY %s %s
                """.formatted(sortField, direction);

        return em.createQuery(jpql, FlashcardEntity.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList()
                .stream()
                .map(FlashcardEntityMapper::toDomain)
                .toList();
    }

    @Override
    public long count(FlashcardSearchCriteria criteria) {

        StringBuilder jpql = new StringBuilder("""
                SELECT COUNT(DISTINCT f)
                FROM FlashcardEntity f
                LEFT JOIN f.tags t
                WHERE 1 = 1
                """);

        if (criteria.hasTagFilter()) {
            jpql.append(" AND t = :tag");
        }

        if (criteria.isDueToday()) {
            jpql.append(" AND f.reviewStats.nextReviewDate <= :today");
        }

        var query = em.createQuery(jpql.toString(), Long.class);

        if (criteria.hasTagFilter()) {
            query.setParameter("tag", criteria.getTag());
        }

        if (criteria.isDueToday()) {
            query.setParameter("today", LocalDate.now());
        }

        return query.getSingleResult();

    }

    @Override
    public List<Flashcard> findAllSortedByPriority() {
        List<FlashcardEntity> entities = em.createQuery("""
                        SELECT f
                        FROM FlashcardEntity f
                        ORDER BY
                            f.reviewStats.repetitions ASC,
                            f.reviewStats.failCount DESC,
                            f.reviewStats.easeFactor ASC,
                            f.reviewStats.nextReviewDate ASC
                    """, FlashcardEntity.class)
                .getResultList();

        return entities.stream()
                .map(FlashcardEntityMapper::toDomain)
                .toList();

    }
}
