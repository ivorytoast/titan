package backend.nyc.com.titan.model;

public class BoardUpdate {

    private String sessionId;
    private String board;

    public BoardUpdate() {
    }

    public BoardUpdate(String sessionId, String board) {
        this.sessionId = sessionId;
        this.board = board;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

}
