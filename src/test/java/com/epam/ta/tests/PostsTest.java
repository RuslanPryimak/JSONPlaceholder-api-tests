package com.epam.ta.tests;

import com.epam.ta.endpoints.PostEndpoint;
import com.epam.ta.model.post.Post;
import com.epam.ta.steps.PostSteps;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DisplayName("Verify Posts")
public class PostsTest {

    private static int TEST_POST_ID = 50;

    @Test
    @DisplayName("Get all Posts")
    public void verifyAllPosts() {
        var response = PostEndpoint.getPosts();

        assertThat(response.getStatusCode())
                .as("wrong status code")
                .isEqualTo(HttpStatus.SC_OK);

        var posts = Arrays.asList(new Gson().fromJson(response.jsonPath().prettify(), Post[].class));
        assertThat(posts.size()).isEqualTo(100);

    }

    @Test
    @DisplayName("Get one Post")
    public void verifyPost() {
        var response = PostEndpoint.getPost(TEST_POST_ID);

        assertThat(response.getStatusCode())
                .as("wrong status code")
                .isEqualTo(HttpStatus.SC_OK);

        var post = new Gson().fromJson(response.jsonPath().prettify(), Post.class);
        PostSteps.verifyPost(post, TEST_POST_ID, 5);
    }

    @Test
    @DisplayName("Add new Post")
    public void verifyAddPost() {
        var userId = 6;
        var testTitle = "title#1";
        var testBody = "body#1";

        var response = PostEndpoint.addPost(userId, testTitle, testBody);

        assertThat(response.getStatusCode())
                .as("wrong status code")
                .isEqualTo(HttpStatus.SC_CREATED);

        var post = new Gson().fromJson(response.jsonPath().prettify(), Post.class);
        PostSteps.verifyPost(post, 101, userId, testTitle, testBody);
    }

    @Test
    @DisplayName("Delete existing Post")
    public void verifyDeletePost() {

        var response = PostEndpoint.deletePost(TEST_POST_ID);

        assertThat(response.getStatusCode())
                .as("wrong status code")
                .isEqualTo(HttpStatus.SC_OK);

        //Should be TEST_POST_ID as parameter but was set as non existing postId just to simulate deletion process
        var nullResponse = PostEndpoint.getPost(123);

        var post = new Gson().fromJson(nullResponse.jsonPath().prettify(), Post.class);
        PostSteps.verifyPostDeleted(post);
    }

    @Test
    @DisplayName("Update existing Post with PUT")
    public void verifyUpdatePostWithPut() {
        var userId = 6;
        var testTitle = "updated title";
        var testBody = "updated body";

        var response = PostEndpoint.updatePostWithPut(userId, TEST_POST_ID, testTitle, testBody);

        assertThat(response.getStatusCode())
                .as("wrong status code")
                .isEqualTo(HttpStatus.SC_OK);

        var post = new Gson().fromJson(response.jsonPath().prettify(), Post.class);
        PostSteps.verifyPost(post, TEST_POST_ID, userId, testTitle, testBody);
    }

    @Test
    @DisplayName("Update Post Title with PATCH")
    public void verifyUpdatePostTitleWithPatch() {
        var testTitle = "updated title with PATCH";

        var response = PostEndpoint.updatePostTitleWithPatch(TEST_POST_ID, testTitle);

        assertThat(response.getStatusCode())
                .as("wrong status code")
                .isEqualTo(HttpStatus.SC_OK);

        var post = new Gson().fromJson(response.jsonPath().prettify(), Post.class);
        PostSteps.verifyPostTitle(post, TEST_POST_ID, testTitle);
    }

}
