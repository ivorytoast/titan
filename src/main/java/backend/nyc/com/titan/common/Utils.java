package backend.nyc.com.titan.common;

import backend.nyc.com.titan.model.Piece;
import backend.nyc.com.titan.model.enums.PlayerSide;

public class Utils {

    public static final String SAMPLE_BOARD = "<!5~2~F~B~5~5~E~T~5~5~B~F!>@<!B~B~B~B~0~0~R~R~R~R!>";

    public static void printBoard(Piece[][] pieces) {
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
