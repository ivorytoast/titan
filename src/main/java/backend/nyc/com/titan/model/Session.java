package backend.nyc.com.titan.model;

import backend.nyc.com.titan.common.Utils;

import java.util.HashSet;
import java.util.Set;

public class Session {

    Board board;
    Set<Player> playerList;

    public Session(Player player) {
        Player spectator = Player.createSpectator();

        this.board = new Board(Boards.standardBoard);
        this.playerList = new HashSet<>();
        this.playerList.add(spectator);
        this.playerList.add(player);
    }

    public Session(String serializedBoard, Player player) {
        Player spectator = Player.createSpectator();

        this.board = new Board(serializedBoard);
        this.playerList = new HashSet<>();
        this.playerList.add(spectator);
        this.playerList.add(player);
    }

    public Session(String serializedBoard, Set<Player> playerList) {
        Player spectator = Player.createSpectator();
        this.playerList = new HashSet<>();
        this.playerList.add(spectator);

        this.board = new Board(serializedBoard);

        if (playerList != null && !playerList.isEmpty()) {
            this.playerList.addAll(playerList);
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
        Utils.printBoard(this.board.pieces);
    }

    public void printOutDetails(Player player) {
        printPlayersInSession();
        this.board.printBoard(player);
    }

    public void addPlayerToSession(Player player) {
        this.playerList.add(player);
    }

}
