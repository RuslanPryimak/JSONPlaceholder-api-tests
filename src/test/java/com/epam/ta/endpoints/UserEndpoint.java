package com.epam.ta.endpoints;

import com.epam.ta.model.user.Address;
import com.epam.ta.model.user.Company;
import com.epam.ta.model.user.User;
import com.google.gson.Gson;
import io.restassured.response.Response;

public class UserEndpoint extends BaseEndpoint {

    private static final String USER_URI = "users/";

    private static final Gson GSON = new Gson();

    public Response getUser(int userId) {
        return given()
                .get(USER_URI + userId)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public Response getUsers() {
        return given()
                .get(USER_URI)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public Response addUser(String name, String username, String email, Address address, String phone, String website, Company company) {
        var user = new User(name, username, email, address, phone, website, company);
        var requestBody = GSON.toJson(user);
        return given()
                .body(requestBody)
                .post(USER_URI)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public Response deleteUser(int userId) {
        return given()
                .delete(USER_URI + userId)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public Response updateUserWithPut(int userId, String name, String username, String email, Address address, String phone, String website, Company company) {
        var user = new User(userId, name, username, email, address, phone, website, company);
        var requestBody = GSON.toJson(user);
        return given()
                .body(requestBody)
                .put(USER_URI + userId)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public Response updateUsernameWithPatch(int userId, String username) {
        var user = new User(userId, username);
        var requestBody = GSON.toJson(user);
        return given()
                .body(requestBody)
                .patch(USER_URI + userId)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }
}
