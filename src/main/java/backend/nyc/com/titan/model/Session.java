package backend.nyc.com.titan.model;

import java.util.HashSet;
import java.util.Set;

public class Session {

    Board board;
    Set<Player> playerList;

    public Session(String playerName) {
        Player spectator = new Player("Spectator", 0);

        this.board = new Board(Boards.standardBoard);
        this.playerList = new HashSet<>();
        this.playerList.add(spectator);
        this.playerList.add(new Player(playerName, this.playerList.size()));
    }

    public Session(String serializedBoard, String playerName) {
        Player spectator = new Player("Spectator", 0);

        this.board = new Board(serializedBoard);
        this.playerList = new HashSet<>();
        this.playerList.add(spectator);
        this.playerList.add(new Player(playerName, this.playerList.size()));
    }

    public Session(String serializedBoard, Set<Player> playerList) {
        Player spectator = new Player("Spectator", 0);
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

    public void printPlayersInSession() {
        System.out.println("--- Player List ---");
        for (Player player : playerList) {
            System.out.println(player.toString());
        }
        System.out.println("-------------------");
    }

    public void printOutDetails() {
        printPlayersInSession();
        this.board.printBoard();
    }

    public void addPlayerToSession(String playerName) {
        this.playerList.add(new Player(playerName, this.playerList.size()));
    }

}
