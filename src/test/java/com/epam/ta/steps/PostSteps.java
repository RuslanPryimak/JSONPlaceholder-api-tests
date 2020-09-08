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

}
