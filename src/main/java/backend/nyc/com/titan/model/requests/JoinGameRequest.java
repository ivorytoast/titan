package backend.nyc.com.titan.model.requests;

public class JoinGameRequest {

    private String sessionId;
    private String playerName;

    public JoinGameRequest() {}

    public JoinGameRequest(String sessionId, String playerName) {
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
