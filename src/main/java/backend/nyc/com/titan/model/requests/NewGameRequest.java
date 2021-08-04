package backend.nyc.com.titan.model.requests;

public class NewGameRequest {

    private String playerName;
    private String board;

    public NewGameRequest() {}

    public NewGameRequest(String playerName) {
        this.playerName = playerName;
        this.board = "";
    }

    public NewGameRequest(String playerName, String board) {
        this.playerName = playerName;
        this.board = board;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getBoard() { return board; }
}
