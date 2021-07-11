package backend.nyc.com.titan.client.okhttp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkUtils {

    public static OkHttpClient client = new OkHttpClient();

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

}
