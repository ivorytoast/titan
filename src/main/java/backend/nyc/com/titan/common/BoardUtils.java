package backend.nyc.com.titan.common;

import backend.nyc.com.titan.model.Piece;
import backend.nyc.com.titan.model.enums.PlayerSide;
import backend.nyc.com.titan.model.pieces.Sergeant;
import backend.nyc.com.titan.serializer.Serializer;

import java.io.Serializable;

public class BoardUtils {

    public static final String createSampleBoard(String sessionId) {
        return "10~10@4~B~3~3~B~3~F~3~B~4~B~6~7~B~3~7~7~6~2~8~2~5~8~S~2~2~8~2~5~2~5~4~6~9~2~2~10~6~4~5~E~E~T~T~E~E~T~T~E~E~E~E~T~T~E~E~T~T~E~E~5~4~6~9~2~2~10~6~4~5~2~5~8~S~2~2~8~2~5~2~B~6~7~B~3~7~7~6~2~8~4~B~3~3~B~3~F~3~B~4@B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~B~E~E~E~E~E~E~E~E~E~E~E~E~E~E~E~E~E~E~E~E~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R~R@B@" + sessionId;
    }

    public static final String SAMPLE_BOARD = "5~2@F~B~3~4~E~E~6~7~B~F@B~B~B~B~E~E~R~R~R~R@B";

    public static void PrintBoard(Piece[][] pieces) {
        if (pieces == null) {
            System.out.println("The board input was null...");
        }
        for (Piece[] row : pieces) {
            for (Piece piece : row) {
                StringBuilder output = new StringBuilder("(" + piece.getPlayerSide() + ") " + piece.getType());
                for (int i = output.length(); i < 25; i++) {
                    output.append(" ");
                }
                System.out.print(output);
            }
            System.out.println();
        }
    }

    public static void PrintBoard(String boardString) {
        Piece[][] pieces = Serializer.deserializeBoard(boardString);
        if (pieces == null) {
            System.out.println("The board input was null...");
        }
        for (Piece[] row : pieces) {
            for (Piece piece : row) {
                StringBuilder output = new StringBuilder("(" + piece.getPlayerSide() + ") " + piece.getType());
                for (int i = output.length(); i < 25; i++) {
                    output.append(" ");
                }
                System.out.print(output);
            }
            System.out.println();
        }
    }

    public static void printBoardFromPerspectiveOf(Piece[][] pieces, PlayerSide playerSide) {
        for (Piece[] row : pieces) {
            for (Piece piece : row) {
                StringBuilder output;
                if (piece.getPlayerSide() == PlayerSide.NON_PLAYER ||
                        playerSide == piece.getPlayerSide() ||
                        playerSide == PlayerSide.SPECTATOR)
                {
                    output = new StringBuilder("(" + piece.getPlayerSide() + ") " + piece.getType());
                } else {
                    output = new StringBuilder("(" + piece.getPlayerSide() + ") ?");
                }
                for (int i = output.length(); i < 25; i++) {
                    output.append(" ");
                }
                System.out.print(output);
            }
            System.out.println();
        }
    }

}
