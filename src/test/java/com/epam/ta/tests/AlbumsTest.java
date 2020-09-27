package com.epam.ta.tests;

import com.epam.ta.model.album.Album;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class AlbumsTest {

    @BeforeEach
    public void initTest() {
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
    }

    @Test
    public void checkStatusCode() {
        Response response = RestAssured.when()
                .get("/albums")
                .andReturn();
        assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void checkResponseHeader() {
        Response response = RestAssured.when()
                .get("/albums")
                .andReturn();
        String rpContentTypeHeader = response.getHeader("Content-Type");
        assertEquals(rpContentTypeHeader, "application/json; charset=utf-8");
    }

    @Test
    public void checkResponseBody() {
        Response response = RestAssured.when()
                .get("/albums")
                .andReturn();
        ResponseBody<?> responseBody = response.getBody();
        Album[] albums = responseBody.as(Album[].class);
        assertEquals(100, albums.length);

    }
}
