package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import com.epam.ta.model.todo.Todo;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodosTest {

    @BeforeEach
    public void initTest() {
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
    }

    @Test
    public void checkStatusCode() {
        Response response = RestAssured.when()
                .get("/todos")
                .andReturn();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void checkResponseHeader() {
        Response response = RestAssured.when()
                .get("/todos")
                .andReturn();
        String rpContentTypeHeader = response.getHeader("Content-Type");
        Assert.assertEquals(rpContentTypeHeader, "application/json; charset=utf-8");
    }

    @Test
    public void checkResponseBody() {
        Response response = RestAssured.when()
                .get("/todos")
                .andReturn();
        ResponseBody<?> responseBody = response.getBody();
        Todo[] todos = responseBody.as(Todo[].class);
        Assert.assertEquals(200, todos.length);

    }
}
