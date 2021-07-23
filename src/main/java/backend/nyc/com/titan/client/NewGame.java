package backend.nyc.com.titan.client;

import backend.nyc.com.titan.common.BoardUtils;
import backend.nyc.com.titan.model.enums.PlayerSide;
import org.springframework.stereotype.Service;

import java.util.Scanner;

/*
    Whoever starts the game is BLUE
    Whoever joins the game is RED (or takes the value that is in playerList)
 */

@Service
public class NewGame {

    public Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        NewGame game = new NewGame();
        System.out.print("What is your name: ");
        String playerName = game.scan.next();
        if (playerName.equalsIgnoreCase("")) {
            System.out.println("Player name cannot be empty");
            return;
        }

        System.out.print("Please provide new game id: ");
        String sessionId = game.scan.next();
        if (sessionId.equalsIgnoreCase("")) {
            System.out.println("Session ID cannot be empty");
            return;
        }

        System.out.println("Thank you : " + playerName + ". We are creating a new game for you with an id of: [" + sessionId + "]");
        System.out.println("Since you created this game, you are in control of the BLUE army!");
        ClientUtils.StartRedisGame(sessionId, playerName);
        ClientUtils.PrintOutLatestSessionBoard(sessionId);
        PlayerSide playerSide = ClientUtils.PlayerSideStringToPlayerSide(ClientUtils.JoinSession(sessionId, playerName));
        while (true) {
            ClientUtils.Move(sessionId, playerSide);
            System.out.println("--- Updated Board ---");
            ClientUtils.PrintOutLatestSessionBoard(sessionId);
        }
    }

}
