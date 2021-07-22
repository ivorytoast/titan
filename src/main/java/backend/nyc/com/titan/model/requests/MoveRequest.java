package backend.nyc.com.titan.model.requests;

import backend.nyc.com.titan.model.enums.PlayerSide;

public class MoveRequest {

    private String sessionId;
    private Character playerSide;
    private int fromX;
    private int fromY;
    private int toX;
    private int toY;

    public MoveRequest() {

    }

    public MoveRequest(String sessionId, Character playerSide, int fromX, int fromY, int toX, int toY) {
        this.sessionId = sessionId;
        this.playerSide = playerSide;
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public PlayerSide getPlayerSide() {
        if (this.playerSide == 'B') {
            return PlayerSide.BLUE;
        } else {
            return PlayerSide.RED;
        }
    }

    public void setPlayerSide(Character playerSide) {
        this.playerSide = playerSide;
    }

    public int getFromX() {
        return fromX;
    }

    public void setFromX(int fromX) {
        this.fromX = fromX;
    }

    public int getFromY() {
        return fromY;
    }

    public void setFromY(int fromY) {
        this.fromY = fromY;
    }

    public int getToX() {
        return toX;
    }

    public void setToX(int toX) {
        this.toX = toX;
    }

    public int getToY() {
        return toY;
    }

    public void setToY(int toY) {
        this.toY = toY;
    }

}
