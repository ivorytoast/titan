package backend.nyc.com.titan.client;

import backend.nyc.com.titan.model.enums.PlayerSide;

import java.util.Scanner;

public class JoinGame {

    public Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        JoinGame game = new JoinGame();
        System.out.print("What is your name: ");
        String playerName = game.scan.next();
        if (playerName.equalsIgnoreCase("")) {
            System.out.println("Player name cannot be empty");
            return;
        }

        System.out.print("Please provide the game id of the game you want to join: ");
        String sessionId = game.scan.next();
        if (sessionId.equalsIgnoreCase("")) {
            System.out.println("Session ID cannot be empty");
            return;
        }

        System.out.println("Thank you : " + playerName + ". We are trying to connect to game: [" + sessionId + "]");
        PlayerSide playerSide = ClientUtils.PlayerSideStringToPlayerSide(ClientUtils.JoinSession(sessionId, playerName));
        while (true) {
            ClientUtils.Move(sessionId, playerSide);
            System.out.println("Move completed");
        }

    }

}
