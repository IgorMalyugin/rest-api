package in.reqres.tests;

import in.reqres.models.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static in.reqres.models.specs.LoginSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("api")
public class HomeWork {

    @Test
    public void listUsersTest() {
        List<Integer> actualSize = step("Получение списка пользователей", () ->
                given(loginRequestSpec)
                        .when()
                        .get("/users?page=2")
                        .then()
                        .spec(listResponseSpec)
                        .extract().path("data.id")
        );
        step("Проверка ответа", () ->
                assertEquals(6, actualSize.size())
        );
    }

    @Test
    public void createTest() {
        CreateBodyModel createBodyModel = new CreateBodyModel();
        createBodyModel.setName("morpheus");
        createBodyModel.setJob("leader");

        CreateResponseModel createResponse = step("Создание пользователя", () ->
                given(loginRequestSpec)
                        .body(createBodyModel)
                        .when()
                        .post("/users")
                        .then()
                        .spec(createResponseSpec)
                        .extract().as(CreateResponseModel.class)

        );

        step("Проверка создания пользователя", () ->
                assertEquals("leader", createResponse.getJob())

        );

    }

    @Test
    public void updateTest() {
        UpdateBodyModel updateBody = new UpdateBodyModel();
        updateBody.setName("Igor");
        updateBody.setJob("zion resident");

        UpdateResponseModel updateResponse = step("Редактирование пользователя", () ->
                given(loginRequestSpec)
                        .body(updateBody)
                        .when()
                        .put("/users/2")
                        .then()
                        .spec(updateResponseSpec)
                        .extract().as(UpdateResponseModel.class)
        );
        step("Проверка ответа", () ->
                assertEquals("Igor", updateResponse.getName())
        );
    }

    @Test
    public void pathTest() {
        PathBodyModel pathBody = new PathBodyModel();
        pathBody.setName("morpheus");
        pathBody.setJob("IT");

        PathResponseModel pathResponse = step("Частичное редактирование пользователя", () ->
                given(loginRequestSpec)
                        .body(pathBody)
                        .when()
                        .patch("/users/2")
                        .then()
                        .spec(pathResponseSpec)
                        .extract().as(PathResponseModel.class)
        );
        step("Проверка ответа", () ->
                assertEquals("morpheus", pathResponse.getName())
        );
    }

    @Test
    public void deleteTest() {
        step("Удаление пользователя", () ->
                given(loginRequestSpec)
                        .when()
                        .delete("/users/2")
                        .then()
                        .spec(deleteResponseSpec)
        );
    }
}
