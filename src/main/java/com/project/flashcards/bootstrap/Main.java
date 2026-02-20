package com.project.flashcards.bootstrap;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {

    public static final String BASE_URI = "http://localhost:8080/api";

    public static void main(String[] args) throws IOException {

        ResourceConfig config = new ResourceConfig()
                .packages("com.project.flashcards.api");

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);

        System.out.println("Servidor rodando em: " + BASE_URI);

        System.in.read();
        server.shutdownNow();
    }
}
