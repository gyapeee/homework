package util.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Api {
    public static List<User> parseFromJson(Response response) throws IOException {
        return new Gson().fromJson(Objects.requireNonNull(response.body()).string(), new TypeToken<List<User>>() {
        }.getType());
    }

    public static Response makeGetRequest(String url) throws IOException {
        Request request = new Request.Builder().url(url).get().build();
        return new OkHttpClient().newCall(request).execute();
    }
}
