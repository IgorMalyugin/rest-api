package in.reqres;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class HomeWork extends BaseTest{


    @Test
    public void listUsersTest() {
        given()
                .log().method()
                .log().uri()
                .when()
                .get("/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("page", is(2));
    }

    @Test
    public void createTest() {
        String createUser = "{\n" + "\"name\": \"morpheus\",\n" + "\"job\": \"leader\"\n" + "}";

        given()
                .log().method()
                .log().uri()
                .contentType(JSON)
                .body(createUser)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"));
    }

    @Test
    public void updateTest() {
        String updateUser = "{\n" + "\"name\": \"igor\",\n" + "\"job\": \"zion resident\"\n" + "}";

        given()
                .log().method()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(updateUser)
                .when()
                .put("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("igor"));
    }

    @Test
    public void pathTest() {
        String pathUser = "{\n" + "\"name\": \"morpheus\",\n" + "\"job\": \"IT\"\n" + "}";

        given()
                .log().method()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(pathUser)
                .when()
                .patch("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("job", is("IT"));
    }

    @Test
    public void deleteTest() {
        given()
                .log().method()
                .log().uri()
                .when()
                .delete("/users/2")
                .then()
                .log().status()
                .statusCode(204);
    }
}
