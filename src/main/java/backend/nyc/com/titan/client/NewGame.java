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

    int counter = 1;

    public static void main(String[] args) {
        NewGame game = new NewGame();
//        Scanner scan = new Scanner(System.in);
//        System.out.print("Please provide name: ");
//        String playerName = scan.next();
//        if (playerName.equalsIgnoreCase("")) {
//            System.out.println("Player name cannot be empty");
//            return;
//        }
//        Player player = new Player(playerName, PlayerSide.BLUE);

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
        OkUtils.createNewSession();
//        Piece[][] board = Serializer.deserializeBoard(game.getDatabaseBoard());
//        System.out.println("--- Start of Printing Board ---");
//        Utils.printBoardFromPerspectiveOf(board, PlayerSide.SPECTATOR);
//        System.out.println("--- End of Printing Board ---");
    }

    public int createNewSession(Player player) {
        counter++;
        Session session = new Session(player);
        Data.sessions.put(counter, session);
        return counter;
    }

    public void joinSession(int sessionId, String playerName) {
//        Data.sessions.get(sessionId).addPlayerToSession(playerName);
    }

    public String getDatabaseBoard() {
        return OkUtils.getBoardFromDatabase();
    }

    public void updateBoard() {
        try {
            OkUtils.updateBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
