package backend.nyc.com.titan.model;

import java.util.HashSet;
import java.util.Set;

public class Session {

    Board board;
    Set<Player> playerList;

    public Session(String serializedBoard) {
        Player spectator = new Player(0);

        this.board = new Board(serializedBoard);
        this.playerList = new HashSet<>();
        this.playerList.add(spectator);
    }

    public Session(String serializedBoard, Set<Player> playerList) {
        Player spectator = new Player(0);
        this.playerList = new HashSet<>();
        this.playerList.add(spectator);

        this.board = new Board(serializedBoard);

        if (playerList != null && !playerList.isEmpty()) {
            for (Player player : playerList) {
                this.playerList.add(player);
            }
        }
    }

    public Board getBoard() {
        return this.board;
    }

}
