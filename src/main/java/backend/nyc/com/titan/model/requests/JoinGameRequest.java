package backend.nyc.com.titan.model.requests;

public class JoinGameRequest {

    private final String sessionId;
    private final String playerName;

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
