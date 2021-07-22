package backend.nyc.com.titan.client;

import backend.nyc.com.titan.Data;
import backend.nyc.com.titan.client.okhttp.OkUtils;
import backend.nyc.com.titan.model.Player;
import backend.nyc.com.titan.model.Session;
import backend.nyc.com.titan.model.enums.PlayerSide;

import java.io.IOException;
import java.util.Scanner;

public class ClientUtils {

    private static final Scanner scan = new Scanner(System.in);

    public static Session CreateNewSession(String playerName, String id) {
        Player player = new Player(playerName, PlayerSide.BLUE);
        OkUtils.createNewSession(id);
        Session session = new Session(player);
        Data.sessions.put(id, session);
        return session;
    }

    public static Session JoinSession(String sessionId, String playerName) {
        Player player = new Player(playerName, PlayerSide.RED);
        Data.sessions.get(sessionId).addPlayerToSession(player);
        return Data.sessions.get(sessionId);
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
        return OkUtils.getBoardFromDatabase(sessionId);
    }

    public static void UpdateBoard() {
        try {
            OkUtils.updateBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
