package backend.nyc.com.titan.client;

import backend.nyc.com.titan.Data;
import backend.nyc.com.titan.client.okhttp.OkUtils;
import backend.nyc.com.titan.common.Utils;
import backend.nyc.com.titan.model.Piece;
import backend.nyc.com.titan.model.Player;
import backend.nyc.com.titan.model.Session;
import backend.nyc.com.titan.model.enums.PlayerSide;
import backend.nyc.com.titan.serializer.Serializer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Scanner;

/*
    Whoever starts the game is BLUE
    Whoever joins the game is RED (or takes the value that is in playerList)
 */

@Service
public class NewGame {

    public static void main(String[] args) {
        NewGame game = new NewGame();
//        Scanner scan = new Scanner(System.in);
//        System.out.print("Please provide name: ");
//        String playerName = scan.next();
//        if (playerName.equalsIgnoreCase("")) {
//            System.out.println("Player name cannot be empty");
//            return;
//        }

//        System.out.print("Thank you : " + player.getName() + ". Do you want to create a new game (1) or join an existing game (2): ");
//        int startChoice = scan.nextInt();
//
//        Session session;
//        if (startChoice == 1) {
//            int sessionId = game.createNewSession(player);
//            session = Data.sessions.get(sessionId);
//            session.printOutDetails();
//        } else if (startChoice == 2) {
//            // Do not worry about this yet
//            System.out.print("What is the session ID: ");
//            int sessionId = scan.nextInt();
//            session = Data.sessions.get(sessionId);
//            game.joinSession(sessionId, "Luke");
//            session.printOutDetails();
//        } else {
//            // Neither this
//            System.out.println("Did not choose a valid choice. Please answer with numbers 1 (create new) or 2 (join existing)");
//            return;
//        }
//        game.updateBoard();
        String sessionId = "B1212356";
        Session session = game.createNewSession("Anthony", sessionId);
        game.joinSession(sessionId, "Luke");
        session.printPlayersInSession();
        session.getBoard().printBoard(PlayerSide.SPECTATOR);
//        session.getBoard().movePiece(PlayerSide.RED, 3, 0, 2, 0);
//        System.out.println("\n---\n");
//        session.getBoard().printBoard(PlayerSide.SPECTATOR);
//        session.getBoard().movePiece(PlayerSide.BLUE, 1, 0, 2, 0);
//        System.out.println("\n---\n");
//        session.getBoard().printBoard(PlayerSide.SPECTATOR);
        game.move(sessionId);
        System.out.println("\n---\n");
        session.getBoard().printBoard(PlayerSide.SPECTATOR);
//        Piece[][] board = Serializer.deserializeBoard(game.getDatabaseBoard());
//        System.out.println("--- Start of Printing Board ---");
//        Utils.printBoardFromPerspectiveOf(board, PlayerSide.SPECTATOR);
//        System.out.println("--- End of Printing Board ---");
    }

    public Session createNewSession(String playerName, String id) {
        Player player = new Player(playerName, PlayerSide.BLUE);
        OkUtils.createNewSession(id);
        Session session = new Session(player);
        Data.sessions.put(id, session);
        return session;
    }

    public void joinSession(String sessionId, String playerName) {
        Player player = new Player(playerName, PlayerSide.RED);
        Data.sessions.get(sessionId).addPlayerToSession(player);
    }

    public void move(String sessionId) {
        try {
            OkUtils.move(sessionId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDatabaseBoard(String sessionId) {
        return OkUtils.getBoardFromDatabase(sessionId);
    }

    public void updateBoard() {
        try {
            OkUtils.updateBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
