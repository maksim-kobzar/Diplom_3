package org.example.MethodsApi;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiMethod {
    protected RequestSpecification getSpec(){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://stellarburgers.nomoreparties.site/")
                .build();
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse authorizationUser(User user){
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post("api/auth/login")
                .then();
    }

    @Step("Создание нового пользователя")
    public ValidatableResponse createCourier(User user){
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post("api/auth/register")
                .then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String token){
        return given()
                .spec(getSpec())
                .header("authorization", token)
                .when()
                .delete("api/auth/user" )
                .then();
    }
}
