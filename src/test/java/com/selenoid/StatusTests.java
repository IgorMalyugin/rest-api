package com.selenoid;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

public class StatusTests {

    @Test
    void checkTotal(){
                    get("https://selenoid.autotests.cloud/status")
                .then()
                    .body("total", is(20));
    }

    @Test
    void checkTotal1(){
            given()
                .when().
                    get("https://selenoid.autotests.cloud/status")
                .then()
                    .body("total", is(20));
    }

    @Test
    void checkTotal2(){
        given()
                .log().all()
                .when().
                    get("https://selenoid.autotests.cloud/status")
                .then()
                    .log().all()
                    .body("total", is(20));
    }

    @Test
    void checkTotal3(){
        given()
                .log().method()
                .log().uri()
                .log().body()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(20))
                .body("browsers.chrome", hasKey("100.0"));
    }

    @Test
    void checkTotalSchema(){
        given()
                .log().method()
                .log().uri()
                .log().body()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/status-response-schema.json"))
                .body("total", is(20))
                .body("browsers.chrome", hasKey("100.0"));
    }
}
