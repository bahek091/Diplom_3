package org.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestAPI {
    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site/";


    public static final String REGISTER_USER_URI = "api/auth/register";
    public static final String LOGIN_USER_URI = "api/auth/login";
    public static final String LOGOUT_USER_URI = "api/auth/logout";
    public static final String DELETE_USER_URI = "api/auth/user";
    public static final String UPDATE_USER_URI = "api/auth/user";

    public static final String ACCESS_TOKEN_FIELD = "accessToken";
    public static final String REFRESH_TOKEN_FIELD = "refreshToken";

    public static final String DUMMY_ID = "-1";

    public RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .build()
                .filter(new AllureRestAssured());
    }

}
