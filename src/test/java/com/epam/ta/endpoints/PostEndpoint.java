package com.epam.ta.endpoints;

import com.epam.ta.model.post.Post;
import com.epam.ta.tests.BaseClass;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostEndpoint extends BaseClass {

    private static String baseUrl = getBaseUrl();

    private static String POST_URI = "posts/";

    public static Response getPost(int postId) {
        return given()
                .get(POST_URI + postId)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public static Response getPosts() {
        return given()
                .get(POST_URI)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public static Response addPost(int userId, String title, String body) {
        var post = new Post(userId, title, body);
        var requestBody = new Gson().toJson(post);
        return given()
                .body(requestBody)
                .post(POST_URI)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    private static RequestSpecification given() {
        return RestAssured.given().baseUri(baseUrl).port(-1).log().all().contentType(ContentType.JSON);
    }
}
