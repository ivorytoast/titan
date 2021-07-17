package backend.nyc.com.titan.common;

import backend.nyc.com.titan.model.Piece;

public class Utils {

    public static void printBoard(Piece[][] pieces) {
        for (Piece[] row : pieces) {
            for (Piece piece : row) {
                StringBuilder output = new StringBuilder("(" + piece.getOwner().getPlayerSide() + ") " + piece.getType());
                for (int i = output.length(); i < 25; i++) {
                    output.append(" ");
                }
                System.out.print(output);
            }
            System.out.println();
        }
    }

}
