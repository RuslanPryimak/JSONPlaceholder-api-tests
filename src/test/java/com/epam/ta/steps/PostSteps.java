package com.epam.ta.steps;

import com.epam.ta.model.post.Post;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;

public class PostSteps extends BaseSteps {

    @Step("Verify Post with id={id}")
    public void verifyPost(Post actualPost, Integer id, Integer userId, String title, String body) {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualPost.getId()).isEqualTo(id);
        softly.assertThat(actualPost.getUserId()).isEqualTo(userId);
        softly.assertThat(actualPost.getTitle()).isEqualTo(title);
        softly.assertThat(actualPost.getBody()).isEqualTo(body);
        softly.assertAll();

    }

    @Step("Verify Post with id={id}")
    public void verifyPost(Post actualPost, Integer id, Integer userId) {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualPost.getId()).isEqualTo(id);
        softly.assertThat(actualPost.getUserId()).isEqualTo(userId);
        softly.assertThat(actualPost.getTitle()).isNotNull();
        softly.assertThat(actualPost.getBody()).isNotNull();
        softly.assertAll();

    }

    @Step("Verify Post with id={id} after title changed")
    public void verifyPostAfterTitleChanged(Post actualPost, int id, String title) {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualPost.getId()).isEqualTo(id);
        softly.assertThat(actualPost.getUserId()).isNotNull();
        softly.assertThat(actualPost.getTitle()).isEqualTo(title);
        softly.assertThat(actualPost.getBody()).isNotNull();
        softly.assertAll();

    }
}
