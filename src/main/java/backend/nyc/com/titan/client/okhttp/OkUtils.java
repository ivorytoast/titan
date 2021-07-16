package backend.nyc.com.titan.client.okhttp;

import okhttp3.*;

import java.io.IOException;

public class OkUtils {

    public static OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static String getBoard(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                return response.body().string();
            } else {
                return "No returned response...";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String updateBoard() throws IOException {
        RequestBody body = RequestBody.create(JSON, "{\"newBoard\":\"<!5~2~F~B~4~4~E~T~5~5~B~F!>@<!2~2~2~2~0~0~1~1~1~1!>\"}");
        Request request = new Request.Builder()
                .url("http://localhost:8080/game/update/board")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
