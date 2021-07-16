package backend.nyc.com.titan.model.requests;

public class BoardUpdateRequest {

    private String newBoard;

    public BoardUpdateRequest() {}

    public BoardUpdateRequest(String newBoard) {
        this.newBoard = newBoard;
    }

    public String getNewBoard() {
        return this.newBoard;
    }

    public void setNewBoard(String newBoard) {
        this.newBoard = newBoard;
    }

}
