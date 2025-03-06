import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.User;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static java.net.HttpURLConnection.HTTP_OK;

@Slf4j
public class ApiTest {

    public static final String URL = "https://jsonplaceholder.typicode.com/users";

    @Test
    public void getUsersAndVerifyAtInFirstEmail_Test_5() throws IOException {
        log.info("Making a GET request for " + URL);
        Response response = makeGetRequest(URL);

        Assert.assertNotNull(response, "Response is null");
        Assert.assertEquals(response.code(), HTTP_OK, "Response code is not " + HTTP_OK + " but " + response.code());

        log.info("Parsing response to JSON ");
        List<User> users = parseFromJson(response);
        users.forEach(user -> log.info(user.getName() + " | " + user.getEmail()));

        Assert.assertTrue(users.get(0).getEmail().contains("@"), "@ symbol is missing from the first user's email ");
    }

    private static List<User> parseFromJson(Response response) throws IOException {
        return new Gson().fromJson(Objects.requireNonNull(response.body()).string(), new TypeToken<List<User>>() {
        }.getType());
    }

    private Response makeGetRequest(String url) throws IOException {
        Request request = new Request.Builder().url(url).get().build();
        return new OkHttpClient().newCall(request).execute();
    }
}
