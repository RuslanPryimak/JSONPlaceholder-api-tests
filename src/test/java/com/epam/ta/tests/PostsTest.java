package com.epam.ta.tests;

import com.epam.ta.endpoints.PostEndpoint;
import com.epam.ta.model.comment.Comment;
import com.epam.ta.model.post.Post;
import com.epam.ta.steps.PostSteps;
import com.epam.ta.utils.JsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DisplayName("Verify Posts")
public class PostsTest {
    private BaseTest baseTest = new BaseTest();

    private PostEndpoint postEndpoint = new PostEndpoint();

    private PostSteps postSteps = new PostSteps();

    private static final int TEST_POST_ID = 70;

    private static final int TEST_USER_ID = 7;

    @Test
    @DisplayName("Get all Posts")
    public void verifyAllPosts() {
        var response = postEndpoint.getPosts();

        baseTest.assertStatusOk(response);

        var posts = Arrays.asList(JsonConverter.convertToJson(response, Post[].class));
        assertThat(posts.size()).isEqualTo(100);

    }

    @Test
    @DisplayName("Get one Post")
    public void verifyPost() {
        var response = postEndpoint.getPost(TEST_POST_ID);

        baseTest.assertStatusOk(response);

        var post = JsonConverter.convertToJson(response, Post.class);
        postSteps.verifyPost(post, TEST_POST_ID, TEST_USER_ID);
    }

    @Test
    @DisplayName("Get all comments for Post")
    public void verifyPostComments() {
        var response = postEndpoint.getPostComments(TEST_POST_ID);

        baseTest.assertStatusOk(response);

        var comments = Arrays.asList(JsonConverter.convertToJson(response, Comment[].class));
        assertThat(comments.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("Get all Posts filter by UserId")
    public void verifyPostsFilterByUserId() {
        var response = postEndpoint.getPostsFilterByUserId(TEST_USER_ID);

        baseTest.assertStatusOk(response);

        var posts = Arrays.asList(JsonConverter.convertToJson(response, Post[].class));
        assertThat(posts.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("Add new Post")
    public void verifyAddPost() {
        var testTitle = "title#1";
        var testBody = "body#1";

        var response = postEndpoint.addPost(TEST_USER_ID, testTitle, testBody);

        baseTest.assertStatusCreated(response);

        var post = JsonConverter.convertToJson(response, Post.class);
        postSteps.verifyPost(post, 101, TEST_USER_ID, testTitle, testBody);
    }

    @Test
    @DisplayName("Delete existing Post")
    public void verifyDeletePost() {
        var response = postEndpoint.deletePost(TEST_POST_ID);

        baseTest.assertStatusOk(response);

    }

    @Test
    @DisplayName("Update existing Post with PUT")
    public void verifyUpdatePostWithPut() {
        var testTitle = "updated title";
        var testBody = "updated body";

        var response = postEndpoint.updatePostWithPut(TEST_USER_ID, TEST_POST_ID, testTitle, testBody);

        baseTest.assertStatusOk(response);

        var post = JsonConverter.convertToJson(response, Post.class);
        postSteps.verifyPost(post, TEST_POST_ID, TEST_USER_ID, testTitle, testBody);
    }

    @Test
    @DisplayName("Update Post Title with PATCH")
    public void verifyUpdatePostTitleWithPatch() {
        var testTitle = "updated title with PATCH";

        var response = postEndpoint.updatePostTitleWithPatch(TEST_POST_ID, testTitle);

        baseTest.assertStatusOk(response);

        var post = JsonConverter.convertToJson(response, Post.class);
        postSteps.verifyPostAfterTitleChanged(post, TEST_POST_ID, testTitle);
    }

}
