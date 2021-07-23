package backend.nyc.com.titan.client;

import backend.nyc.com.titan.client.okhttp.OkUtils;
import backend.nyc.com.titan.common.BoardUtils;
import backend.nyc.com.titan.model.Player;
import backend.nyc.com.titan.model.Session;
import backend.nyc.com.titan.model.enums.PlayerSide;
import backend.nyc.com.titan.redis.RedisClient;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.Scanner;

@Log
public class ClientUtils {

    private static final Scanner scan = new Scanner(System.in);

    public static void StartRedisGame(String sessionId, String playerName) {
        try {
            System.out.println(OkUtils.CreateNewBoard(sessionId, playerName));
        } catch (Exception e) {
            System.out.println("Error creating a new board for session id: " + sessionId);
        }
    }

    public static String JoinSession(String sessionId, String playerName) {
        try {
            String playerSideString = OkUtils.JoinSession(sessionId, playerName);
            System.out.println("Assigned Player Side: " + playerSideString);
            return OkUtils.JoinSession(sessionId, playerName);
        } catch (Exception e) {
            System.out.println("Error joining session: " + sessionId);
            return "Error!";
        }
    }

    public static void PrintOutLatestSessionBoard(String sessionId) {
        String updatedBoard = ClientUtils.GetDatabaseBoard(sessionId);
        BoardUtils.PrintBoard(updatedBoard);
    }

    public static void PrintOutPlayersInSession(String sessionId) {
        try {
            System.out.println(OkUtils.PrintPlayersInSession(sessionId));
        } catch (Exception e) {
            System.out.println("Error printing players in session: " + sessionId);
        }
    }

    public static Session CreateNewSession(String playerName, String id) throws Exception {
        Player player = new Player(playerName, PlayerSide.BLUE);
        OkUtils.CreateNewBoard(id, playerName);
        Session session = new Session(player);
        RedisClient.AddNewSession(id);
        return session;
    }

    public static void Move(String sessionId, PlayerSide playerSide) {
        System.out.print("fromX: ");
        int fromX = scan.nextInt();
        System.out.print("fromY: ");
        int fromY = scan.nextInt();
        System.out.print("toX: ");
        int toX = scan.nextInt();
        System.out.print("toY: ");
        int toY = scan.nextInt();
        String playerSideChar = "";
        if (playerSide == PlayerSide.BLUE) {
            playerSideChar = "B";
        } else {
            playerSideChar = "R";
        }
        try {
            OkUtils.move(sessionId, fromX, fromY, toX, toY, playerSideChar);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String GetDatabaseBoard(String sessionId) {
        return OkUtils.GetCurrentBoardFromDatabase(sessionId);
    }

    public static void UpdateBoard() {
        try {
            OkUtils.updateBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PlayerSide PlayerSideStringToPlayerSide(String playerSideString) {
        if (playerSideString.equalsIgnoreCase("RED")) {
            return PlayerSide.RED;
        } else if (playerSideString.equalsIgnoreCase("BLUE")) {
            return PlayerSide.BLUE;
        } else {
            return PlayerSide.SPECTATOR;
        }
    }

}
