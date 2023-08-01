package in.reqres.tests;

import in.reqres.models.LoginBodyLombokModel;
import in.reqres.models.LoginBodyPogoModel;
import in.reqres.models.LoginResponseLombokModel;
import in.reqres.models.LoginResponsePogoModel;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static in.reqres.helpers.CustomAllureListener.withCustomTemplates;
import static in.reqres.models.specs.LoginSpec.loginRequestSpec;
import static in.reqres.models.specs.LoginSpec.loginResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginExtendedTest {

    @Test
    void loginPojoModelTest() {

        LoginBodyPogoModel loginBodyModel = new LoginBodyPogoModel();
        loginBodyModel.setEmail("eve.holt@reqres.in");
        loginBodyModel.setPassword("cityslicka");

        LoginResponsePogoModel loginResponse = given()
                .log().method()
                .log().uri()
                .log().body()
                .contentType(ContentType.JSON)
                .body(loginBodyModel)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponsePogoModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken());

    }

    @Test
    void loginLombokModelTest() {
        LoginBodyLombokModel loginBodyModel = new LoginBodyLombokModel();
        loginBodyModel.setEmail("eve.holt@reqres.in");
        loginBodyModel.setPassword("cityslicka");

        LoginResponseLombokModel loginResponse = given()
                .log().method()
                .log().uri()
                .log().body()
                .contentType(ContentType.JSON)
                .body(loginBodyModel)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken());

    }

    @Test
    void loginLombokAllureModelTest() {

        LoginBodyLombokModel loginBodyModel = new LoginBodyLombokModel();
        loginBodyModel.setEmail("eve.holt@reqres.in");
        loginBodyModel.setPassword("cityslicka");

        LoginResponseLombokModel loginResponse = given()
                .log().method()
                .log().uri()
                .log().body()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(loginBodyModel)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken());

    }

    @Test
    void loginLombokCustomAllureModelTest() {

        LoginBodyLombokModel loginBodyModel = new LoginBodyLombokModel();
        loginBodyModel.setEmail("eve.holt@reqres.in");
        loginBodyModel.setPassword("cityslicka");

        LoginResponseLombokModel loginResponse = step("Отправка запроса", () ->
                given()
                        .log().method()
                        .log().uri()
                        .log().body()
                        .filter(withCustomTemplates())
                        .contentType(ContentType.JSON)
                        .body(loginBodyModel)
                        .when()
                        .post("login")
                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginResponseLombokModel.class)

        );

        step("Проверка ответа", () ->
                assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken())

        );

    }

    @Test
    void loginSpecsTest() {

        LoginBodyLombokModel loginBodyModel = new LoginBodyLombokModel();
        loginBodyModel.setEmail("eve.holt@reqres.in");
        loginBodyModel.setPassword("cityslicka");

        LoginResponseLombokModel loginResponse = step("Отправка запроса", () ->
                given(loginRequestSpec)
                        .body(loginBodyModel)
                        .when()
                        .post("https://reqres.in/api/login")
                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(200)
                        .extract().as(LoginResponseLombokModel.class)

        );

        step("Проверка ответа", () ->
                assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken())

        );
    }

    @Test
    void loginSpecsJsonTest() {
        LoginBodyLombokModel loginBodyModel = new LoginBodyLombokModel();
        loginBodyModel.setEmail("eve.holt@reqres.in");
        loginBodyModel.setPassword("cityslicka");

        LoginResponseLombokModel loginResponse = step("Отправка запроса", () ->
                given(loginRequestSpec)
                        .body(loginBodyModel)
                        .when()
                        .post("login")
                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginResponseLombokModel.class)

        );
        step("Проверка ответа", () ->
                assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken())

        );
    }
}
