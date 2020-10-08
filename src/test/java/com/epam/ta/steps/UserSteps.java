package com.epam.ta.steps;

import com.epam.ta.model.user.Address;
import com.epam.ta.model.user.Company;
import com.epam.ta.model.user.User;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;

public class UserSteps extends BaseSteps {

    @Step("Verify User with username {username}")
    public void verifyUser(User actualUser, Integer id, String name, String username, String email, Address address, String phone, String website, Company company) {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualUser.getId()).isEqualTo(id);
        softly.assertThat(actualUser.getName()).isEqualTo(name);
        softly.assertThat(actualUser.getUsername()).isEqualTo(username);
        softly.assertThat(actualUser.getEmail()).isEqualTo(email);
        softly.assertThat(actualUser.getAddress()).isEqualTo(address);
        softly.assertThat(actualUser.getPhone()).isEqualTo(phone);
        softly.assertThat(actualUser.getCompany()).isEqualTo(company);
        softly.assertAll();

    }

    @Step("Verify User with username {username}")
    public void verifyUser(User actualUser, Integer id, String name, String username) {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualUser.getId()).isEqualTo(id);
        softly.assertThat(actualUser.getName()).isEqualTo(name);
        softly.assertThat(actualUser.getUsername()).isEqualTo(username);
        softly.assertThat(actualUser.getEmail()).isNotNull();
        softly.assertThat(actualUser.getAddress()).isNotNull();
        softly.assertThat(actualUser.getPhone()).isNotNull();
        softly.assertThat(actualUser.getCompany()).isNotNull();
        softly.assertAll();

    }

    @Step("Verify User after username changed")
    public void verifyUserAfterUsernameChanged(User actualUser, int userId, String username) {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualUser.getId()).isEqualTo(userId);
        softly.assertThat(actualUser.getUsername()).isEqualTo(username);
        softly.assertThat(actualUser.getName()).isNotNull();
        softly.assertThat(actualUser.getEmail()).isNotNull();
        softly.assertThat(actualUser.getAddress()).isNotNull();
        softly.assertThat(actualUser.getPhone()).isNotNull();
        softly.assertThat(actualUser.getCompany()).isNotNull();
        softly.assertAll();
    }
}
