package backend.nyc.com.titan.client;

import backend.nyc.com.titan.Data;
import backend.nyc.com.titan.client.okhttp.OkUtils;
import backend.nyc.com.titan.model.Session;

import java.io.IOException;
import java.util.Scanner;

public class NewGame {

    int counter = 1;

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            throw new Exception("You have include your name!");
        }
        NewGame game = new NewGame();
        int sessionId = game.createNewSession(args[0]);
        Session session = Data.sessions.get(sessionId);
        session.printOutDetails();
        game.joinSession(sessionId, "Luke");
        session.printOutDetails();
        while (true) {
            Scanner scan = new Scanner(System.in);
            int i = scan.nextInt();
            if (i == 2) {
                game.updateBoard();
            }
            if (i == 3) {
                break;
            }
        }
    }

    public int createNewSession(String playerName) {
        counter++;
        Session session = new Session(playerName);
        Data.sessions.put(counter, session);
        return counter;
    }

    public void joinSession(int sessionId, String playerName) {
        Data.sessions.get(sessionId).addPlayerToSession(playerName);
    }

    public void updateBoard() {
        try {
            OkUtils.updateBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
