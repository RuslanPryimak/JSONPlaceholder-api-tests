package com.epam.ta.endpoints;

import com.epam.ta.model.post.Post;
import com.google.gson.Gson;
import io.restassured.response.Response;

public class PostEndpoint extends BaseEndpoint {

    private static final String POST_URI = "posts/";

    private static final String COMMENT_URI = "/comments";

    private static final String POST_URI_FILTER = "posts?userId=";

    private static final Gson GSON = new Gson();

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

    public static Response getPostComments(int postId) {
        return given()
                .get(POST_URI + postId + COMMENT_URI)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public static Response addPost(int userId, String title, String body) {
        var post = new Post(userId, title, body);
        var requestBody = GSON.toJson(post);
        return given()
                .body(requestBody)
                .post(POST_URI)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public static Response deletePost(int postId) {
        return given()
                .delete(POST_URI + postId)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public static Response updatePostWithPut(int userId, int postId, String title, String body) {
        var post = new Post(userId, postId, title, body);
        var requestBody = GSON.toJson(post);
        return given()
                .body(requestBody)
                .put(POST_URI + postId)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public static Response updatePostTitleWithPatch(int postId, String title) {
        var post = new Post(postId, title);
        var requestBody = GSON.toJson(post);
        return given()
                .body(requestBody)
                .patch(POST_URI + postId)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public static Response getPostsFilterByUserId(int userId) {
        return given()
                .get(POST_URI_FILTER + userId)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }
}
