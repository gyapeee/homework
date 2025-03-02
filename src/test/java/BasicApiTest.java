import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static java.net.HttpURLConnection.HTTP_OK;

public class BasicApiTest {

  private final OkHttpClient client = new OkHttpClient();

  @Test
  public void getRequest() throws IOException {
    Response response = makeGetRequest("https://jsonplaceholder.typicode.com/users");

    Assert.assertNotNull(response, "Response is null");
    Assert.assertEquals(response.code(), HTTP_OK, "Response code is not " + HTTP_OK + " but " + response.code());
  }

  private Response makeGetRequest(String url) throws IOException {
    Request request = new Request.Builder().url(url)
                                           .get()
                                           .build();
    return client.newCall(request)
                 .execute();
  }
}
