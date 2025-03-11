package homework;

import data.User;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.api.Api;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.get;
import static java.net.HttpURLConnection.HTTP_OK;

@Slf4j
public class ApiTest {

    public static final String URL = "https://jsonplaceholder.typicode.com/users";
    public static final String MAKING_A_GET_REQUEST = "Making a GET request for " + URL;
    public static final String PARSING_RESPONSE_TO_JSON = "Parsing response to JSON";

    @Test
    @Feature("Homework")
    @Story("ApiTest")
    @Description("Gets users from an endpoint parses it and verifies that the first user has @ symbol in it's e-mail")
    public void getUsersAndVerifyAtInFirstEmail_Test_5() throws IOException {
        Allure.step(MAKING_A_GET_REQUEST);
        log.info(MAKING_A_GET_REQUEST);
        Response response = Api.makeGetRequest(URL);

        Assert.assertNotNull(response, "Response is null");
        Assert.assertEquals(response.code(), HTTP_OK, "Response code is not " + HTTP_OK + " but " + response.code());

        log.info(PARSING_RESPONSE_TO_JSON);
        List<User> users = Api.parseFromJson(response);
        Allure.step("Parsed users", step -> {
            users.forEach(user -> step.parameter(user.getName(), user.getEmail()));
        });
        users.forEach(user -> log.info(user.getName() + " | " + user.getEmail()));

        Assert.assertTrue(users.get(0).getEmail().contains("@"), "@ symbol is missing from the first user's email ");
    }

    @Test
    @Feature("Homework")
    @Story("ApiTest")
    @Description("More compact RestAssured demo")
    public void restAssuredDemo() {
        io.restassured.response.Response response = get(URL);

        Assert.assertNotNull(response, "Response is null");
        Assert.assertEquals(response.getStatusCode(), HTTP_OK,
                "Response code is not " + HTTP_OK + " but " + response.getStatusCode());

        List<User> users = Arrays.asList(response.getBody().as(User[].class));
        Assert.assertTrue(users.get(0).getEmail().contains("@"), "@ symbol is missing from the first user's email ");
    }

}
