package backend.nyc.com.titan.model.requests;

public class NewGameRequest {

    private String sessionId;
    private String playerName;

    public NewGameRequest() {}

    public NewGameRequest(String sessionId, String playerName) {
        this.sessionId = sessionId;
        this.playerName = playerName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getPlayerName() {
        return playerName;
    }

}
