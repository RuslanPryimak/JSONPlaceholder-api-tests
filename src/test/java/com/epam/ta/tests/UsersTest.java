package com.epam.ta.tests;

import com.epam.ta.endpoints.UserEndpoint;
import com.epam.ta.model.user.Address;
import com.epam.ta.model.user.Company;
import com.epam.ta.model.user.User;
import com.epam.ta.model.user.UserData;
import com.epam.ta.steps.UserSteps;
import com.epam.ta.utils.JsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DisplayName("Verify Users")
public class UsersTest {

    private BaseTest baseTest = new BaseTest();

    private UserEndpoint userEndpoint = new UserEndpoint();

    private UserSteps userSteps = new UserSteps();

    private static final int TEST_USER_ID = 7;

    private static final String TEST_NAME = "Kurtis Weissnat";
    private static final String TEST_USERNAME = "Elwyn.Skiles";

    @Test
    @DisplayName("Get all Users")
    public void verifyAllUsers() {
        var response = userEndpoint.getUsers();

        baseTest.assertStatusOk(response);

        var users = Arrays.asList(JsonConverter.convertToJson(response, User[].class));
        assertThat(users.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("Get one User")
    public void verifyUser() {
        var response = userEndpoint.getUser(TEST_USER_ID);

        baseTest.assertStatusOk(response);

        var user = JsonConverter.convertToJson(response, User.class);
        userSteps.verifyUser(user, TEST_USER_ID, TEST_NAME, TEST_USERNAME);
    }

    @ParameterizedTest
    @DisplayName("Get several Users")
    @EnumSource(UserData.class)
    public void verifyUsers(UserData userData) {
        var response = userEndpoint.getUser(userData.getUserId());

        baseTest.assertStatusOk(response);

        var user = JsonConverter.convertToJson(response, User.class);
        userSteps.verifyUser(user, userData.getUserId(), userData.getName(), userData.getUsername());
    }

    @ParameterizedTest
    @DisplayName("Check Users not found")
    @ValueSource(ints = {11, 12, 13})
    public void verifyUsersNotFound(int test_user_id) {
        var response = userEndpoint.getUser(test_user_id);

        baseTest.assertStatusNotFound(response);

    }

    @Test
    @DisplayName("Add new User")
    public void verifyAddUser() {
        var email = "testEmail";
        var address = new Address();
        var phone = "22334455";
        var website = "testWebsite";
        var company = new Company();

        var response = userEndpoint.addUser(TEST_NAME, TEST_USERNAME, email, address, phone, website, company);

        baseTest.assertStatusCreated(response);

        var user = JsonConverter.convertToJson(response, User.class);
        userSteps.verifyUser(user, 11, TEST_NAME, TEST_USERNAME, email, address, phone, website, company);
    }

    @Test
    @DisplayName("Delete existing User")
    public void verifyDeleteUser() {
        var response = userEndpoint.deleteUser(TEST_USER_ID);

        baseTest.assertStatusOk(response);

    }

    @Test
    @DisplayName("Update existing User with PUT")
    public void verifyUpdateUserWithPut() {
        var email = "updated@email.com";
        var address = new Address();
        var phone = "22334455";
        var website = "testWebsite";
        var company = new Company();

        var response = userEndpoint.updateUserWithPut(TEST_USER_ID, TEST_NAME, TEST_USERNAME, email, address, phone, website, company);

        baseTest.assertStatusOk(response);

        var user = JsonConverter.convertToJson(response, User.class);
        userSteps.verifyUser(user, TEST_USER_ID, TEST_NAME, TEST_USERNAME, email, address, phone, website, company);
    }

    @Test
    @DisplayName("Update Username with PATCH")
    public void verifyUpdateUsernameWithPatch() {
        var testUsername = "updated.Username";

        var response = userEndpoint.updateUsernameWithPatch(TEST_USER_ID, testUsername);

        baseTest.assertStatusOk(response);

        var user = JsonConverter.convertToJson(response, User.class);
        userSteps.verifyUserAfterUsernameChanged(user, TEST_USER_ID, testUsername);
    }
}
