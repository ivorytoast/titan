package backend.nyc.com.titan.model.requests;

public class BoardUpdateRequest {

    private String sessionId;
    private String newBoard;

    public BoardUpdateRequest() {}

    public BoardUpdateRequest(String sessionId, String newBoard) {
        this.sessionId = sessionId;
        this.newBoard = newBoard;
    }

    public String getNewBoard() {
        return this.newBoard;
    }

    public void setNewBoard(String newBoard) {
        this.newBoard = newBoard;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
