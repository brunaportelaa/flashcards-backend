import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class FlashcardResourceTest {

    @Test
    void shouldListFlashcards() {

        given()
                .when()
                    .get("/flashcards")
                .then()
                    .statusCode(200);
    }

    @Test
    void shouldCreateFlashcard() {

        String json = """
                {
                    "front": "chien",
                    "back": "cachorro"
                }
                """;

        given()
                .contentType("application/json")
                .body(json)
        .when()
                .post("/flashcards")
        .then()
                .statusCode(201);
    }

    @Test
    void shouldGetStudyCards() {

        given()
        .when()
                .get("/study/today")
        .then()
                .statusCode(200);
    }
}
