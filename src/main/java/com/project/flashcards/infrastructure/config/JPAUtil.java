package com.project.flashcards.infrastructure.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class JPAUtil {

    private static final EntityManagerFactory emf;

    static {

        Map<String, Object> props = new HashMap<>();

        props.put("jakarta.persistence.jdbc.user",
                System.getenv("DB_USER"));

        props.put("jakarta.persistence.jdbc.password",
                System.getenv("DB_PASSWORD"));

        emf = Persistence.createEntityManagerFactory("flashcardsPU", props);
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
