package backend.nyc.com.titan.client.okhttp;

import backend.nyc.com.titan.model.enums.PlayerSide;
import backend.nyc.com.titan.redis.RedisClient;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OkUtils {

    public static final String LOCAL_HOST = "http://localhost:8080";
    public static final String TITAN = "http://proxy.titan-backend-nyc.com:8080";

    public static OkHttpClient client = new OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build();
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

    public static String GetCurrentBoardFromDatabase(String id) {
        Request request = new Request.Builder()
                .url(TITAN + "/game/db/board/" + id)
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

    public static String PrintPlayersInSession(String sessionId) {
        Request request = new Request.Builder()
                .url(TITAN + "/game/sessions/players/" + sessionId)
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

    public static String updateBoard() throws IOException {
        System.out.println("Updating board!");
        RequestBody body = RequestBody.create(JSON, "{\"newBoard\":\"<!5~2~F~B~5~5~E~T~5~5~B~F!>@<!B~B~B~B~0~0~R~R~R~R!>\"}");
        Request request = new Request.Builder()
                .url(TITAN + "/game/update/board")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static String CreateNewBoard(String sessionId, String playerName) throws IOException {
        RequestBody body = RequestBody.create(JSON, "{\"sessionId\":\""+sessionId+"\",\"playerName\":\""+playerName+"\"}");
        Request request = new Request.Builder()
                .url(TITAN + "/game/new/redis")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static String JoinSession(String sessionId, String playerName) throws IOException {
        RequestBody body = RequestBody.create(JSON, "{\"sessionId\":\""+sessionId+"\",\"playerName\":\""+playerName+"\"}");
        Request request = new Request.Builder()
                .url(TITAN + "/game/join")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static String move(String sessionId, int fromX, int fromY, int toX, int toY, String playerSide) throws IOException {
        System.out.println("Move!");
        RequestBody body = RequestBody.create(JSON, "{\"sessionId\":\"" + sessionId +
                "\",\"playerSide\":\"" + playerSide +
                "\",\"fromX\":" + fromX +
                ",\"fromY\":" + fromY +
                ",\"toX\":" + toX +
                ",\"toY\":" + toY + "}");
        Request request = new Request.Builder()
                .url(TITAN + "/game/move")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
