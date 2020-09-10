package com.epam.ta.steps;

import com.epam.ta.model.post.Post;
import org.assertj.core.api.AutoCloseableSoftAssertions;

public class PostSteps {

    public static void verifyPost(Post actualPost, Integer id, Integer userId, String title, String body) {
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(actualPost.getId())
                    .isEqualTo(id);
            softly.assertThat(actualPost.getUserId())
                    .isEqualTo(userId);
            softly.assertThat(actualPost.getTitle())
                    .isEqualTo(title);
            softly.assertThat(actualPost.getBody())
                    .isEqualTo(body);
        }
    }

    public static void verifyPost(Post actualPost, Integer id, Integer userId) {
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(actualPost.getId())
                    .isEqualTo(id);
            softly.assertThat(actualPost.getUserId())
                    .isEqualTo(userId);
            softly.assertThat(actualPost.getTitle())
                    .isNotNull();
            softly.assertThat(actualPost.getBody())
                    .isNotNull();
        }
    }

    public static void verifyPostDeleted(Post actualPost) {
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(actualPost.getId())
                    .isNull();
            softly.assertThat(actualPost.getUserId())
                    .isNull();
            softly.assertThat(actualPost.getTitle())
                    .isNull();
            softly.assertThat(actualPost.getBody())
                    .isNull();
        }
    }

    public static void verifyPostTitle(Post actualPost, int id, String testTitle) {
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(actualPost.getId())
                    .isEqualTo(id);
            softly.assertThat(actualPost.getUserId())
                    .isNotNull();
            softly.assertThat(actualPost.getTitle())
                    .isEqualTo(testTitle);
            softly.assertThat(actualPost.getBody())
                    .isNotNull();
        }

    }
}
