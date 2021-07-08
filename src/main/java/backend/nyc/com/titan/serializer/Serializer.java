package backend.nyc.com.titan.serializer;

import backend.nyc.com.titan.model.Piece;
import backend.nyc.com.titan.model.Player;
import backend.nyc.com.titan.model.pieces.*;

/*
<!10~10~
10~9~8~8~7~7~7~6~6~6~
6~5~5~5~5~4~4~4~4~3~
3~3~3~3~2~2~2~2~2~2~
2~2~S~B~B~B~B~B~B~F~
E~E~E~E~E~E~E~E~E~E~
E~E~E~E~E~E~E~E~E~E~
10~9~8~8~7~7~7~6~6~6~
6~5~5~5~5~4~4~4~4~3~
3~3~3~3~2~2~2~2~2~2~
2~2~S~B~B~B~B~B~B~F!>
@<!1~1~1~1~1~1~1~1~1~1~
1~1~1~1~1~1~1~1~1~1~
1~1~1~1~1~1~1~1~1~1~
1~1~1~1~1~1~1~1~1~1~
0~0~0~0~0~0~0~0~0~0~
0~0~0~0~0~0~0~0~0~0~
2~2~2~2~2~2~2~2~2~2~
2~2~2~2~2~2~2~2~2~2~
2~2~2~2~2~2~2~2~2~2~
2~2~2~2~2~2~2~2~2~2!>
 */

public class Serializer {

    public static Piece[][] deserializeBoard(String boardString) {
        if (boardString.isEmpty()) {
            System.out.println("Board String was empty. Could not deserialize.");
            return new Piece[0][0];
        }
        if (boardString.length() <= 2) {
            System.out.println("Board string is not long enough to be valid");
            return new Piece[0][0];
        }
        if (!boardString.substring(0, 2).equalsIgnoreCase("<!")) {
            System.out.println("Invalid end to the string. Serialized board must start with '<!'");
            return new Piece[0][0];
        }
        if (!boardString.substring(boardString.length() - 2).equalsIgnoreCase("!>")) {
            System.out.println("Invalid start to the string. Serialized board must end with '!>'");
            return new Piece[0][0];
        }
        String[] components = boardString.split("@");

        String[] dimensions = components[0].substring(2, components[0].length() - 2).split("~");
        int rows = Integer.parseInt(dimensions[0]);
        int columns = Integer.parseInt(dimensions[1]);
        int totalSpaces = rows * columns;

        String piecesString = components[0].substring(components[0].indexOf("~", components[0].indexOf("~") + 1) + 1);
        piecesString = "<!" + piecesString;

        String[] pieces = transformToStringArray(piecesString, rows, columns);
        if (pieces.length != totalSpaces) {
            System.out.println("Invalid amount of pieces. The board says it has " + rows + " rows and " + columns + " columns. " +
                    "Therefore, a total of " + totalSpaces + " total spaces." + "But there were only " + (pieces.length - 2) + " pieces provided.");
            return null;
        }

        String[] players = transformToStringArray(components[1], rows, columns);

        if (pieces.length != players.length) {
            System.out.println("The amount of pieces and players are not matching up. Number of pieces: " + pieces.length + " compared to: " + players.length);
            return new Piece[0][0];
        }

        Piece[][] board = new Piece[rows][columns];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int singleArrayPosition = j + (i * columns);
                Piece newPiece = createPiece(pieces[singleArrayPosition], new Player(Integer.parseInt(players[singleArrayPosition])));
                if (newPiece == null) {
                    System.out.println("There was an invalid piece on the board. The piece was: " +  pieces[j + (i * columns)]);
                    return new Piece[0][0];
                }
                board[i][j] = createPiece(pieces[j + (i * columns)], new Player(Integer.parseInt(players[singleArrayPosition])));
            }
        }
        return board;
    }

    private static String[] transformToStringArray(String input, int rows, int columns) {
        return input.substring(2, input.length() - 2).split("~");
    }

    public static String serializeBoard(Piece[][] board) {
        return "";
    }

    private static Piece createPiece(String id, Player owner) {
        if (owner == null) {
            System.out.println("When creating a piece, the owner cannot be null");
            return null;
        }
        switch(id) {
            case "10":
                return new Marshall(owner);
            case "9":
                return new General(owner);
            case "8":
                return new Colonel(owner);
            case "7":
                return new Major(owner);
            case "6":
                return new Captain(owner);
            case "5":
                return new Lieutenant(owner);
            case "4":
                return new Sergeant(owner);
            case "3":
                return new Miner(owner);
            case "2":
                return new Scout(owner);
            case "S":
                return new Spy(owner);
            case "B":
                return new Bomb(owner);
            case "F":
                return new Flag(owner);
            case "E":
                return new Empty(owner);
            case "T":
                return new Terrain(owner);
            default:
                return null;
        }
    }

}
