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

    public static String getBoardFromDatabase() {
        Request request = new Request.Builder()
                .url("http://localhost:8080/game/db/board")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                String board = response.body().string();
                System.out.println(board);
                return board;
            } else {
                return "No returned response...";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String createNewSession() {
        Request request = new Request.Builder()
                .url("http://localhost:8080/game/new/session")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                String output = response.body().string();
                System.out.println(output);
                return output;
            } else {
                return "No returned response...";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String updateBoard() throws IOException {
        System.out.println("Updating board!");
        RequestBody body = RequestBody.create(JSON, "{\"newBoard\":\"<!5~2~F~B~5~5~E~T~5~5~B~F!>@<!B~B~B~B~0~0~R~R~R~R!>\"}");
        Request request = new Request.Builder()
                .url("http://localhost:8080/game/update/board")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
