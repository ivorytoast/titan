package backend.nyc.com.titan.ui;

import backend.nyc.com.titan.client.ClientUtils;
import backend.nyc.com.titan.model.Piece;
import backend.nyc.com.titan.serializer.Serializer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Scanner;

public class GameUI extends Application {

    public Scanner scan = new Scanner(System.in);

    public static GridPane gridpane = new GridPane();

    public static String playerName = "";
    public static String sessionId = "";
    public static String playerSide = null;

    public static Piece[][] pieces = new Piece[0][0];

    Stage window;

    @Override
    public void start(Stage primaryStage) {
        GameUI ui = new GameUI();
        window = primaryStage;
        window.setTitle("Titan");
        window.setMinWidth(650);
        window.setMinHeight(650);

        TextField playerNameField = new TextField("Player Name");
        gridpane.add(playerNameField, 14, 0);
        Button playerNameButton = new Button("Set Player Name");
        playerNameButton.setOnAction(value ->  {
            System.out.println(setPlayerName(playerNameField));
        });
        gridpane.add(playerNameButton, 14, 1);

        TextField sessionIdField = new TextField("Input Session ID");
        gridpane.add(sessionIdField, 14, 3);
        Button sessionIdButton = new Button("Set Session ID");
        sessionIdButton.setOnAction(value ->  {
            System.out.println(setSessionId(sessionIdField));
        });
        gridpane.add(sessionIdButton, 14, 4);

        Button startNewGame = new Button("Start New Game");
        startNewGame.setOnAction(value ->  {
            startNewGame();
        });
        gridpane.add(startNewGame, 14, 6);

        Button joinGame = new Button("Join Game");
        joinGame.setOnAction(value ->  {
            joinGame();
        });
        gridpane.add(joinGame, 14, 7);

        Button latestGameBoardButton = new Button("Get Latest Board");
        latestGameBoardButton.setOnAction(value ->  {
            getLatestGameBoard();
        });
        gridpane.add(latestGameBoardButton, 14, 8);

        Scene scene = new Scene(gridpane);
        window.setScene(scene);
        window.show();
    }

    public static void redrawBoard() {
        gridpane.getChildren().clear();
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[0].length; j++) {
                gridpane.add(new Button(Serializer.getTypeChar(pieces[i][j])), j, i);
            }
        }
    }

    public static void getLatestGameBoard() {
        String boardString = ClientUtils.GetDatabaseBoard(getSessionId());
        pieces = Serializer.deserializeBoard(boardString);
        redrawBoard();
    }

    private static void startNewGame() {
        ClientUtils.StartRedisGame(getSessionId(), getPlayerSide());
        ClientUtils.PrintOutLatestSessionBoard(getSessionId());
        playerSide = ClientUtils.PlayerSideStringToPlayerSide(ClientUtils.JoinSession(getSessionId(), getPlayerSide())).toString();
        System.out.println("Chosen player side is: " + getPlayerSide());
    }

    private static void joinGame() {
        playerSide = ClientUtils.PlayerSideStringToPlayerSide(ClientUtils.JoinSession(getSessionId(), getPlayerSide())).toString();
    }

    private static String setSessionId(TextField field) {
        sessionId = field.getText();

        return sessionId;
    }

    private static String setPlayerName(TextField field) {
        playerName = field.getText();

        return playerName;
    }

    private static String getPlayerSide() {
        return playerSide;
    }

    private static String getSessionId() {
        return sessionId;
    }

}
