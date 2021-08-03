package backend.nyc.com.titan.serializer;

import backend.nyc.com.titan.model.Piece;
import backend.nyc.com.titan.model.Player;
import backend.nyc.com.titan.model.enums.PieceType;
import backend.nyc.com.titan.model.enums.PlayerSide;
import backend.nyc.com.titan.model.pieces.*;

import java.util.StringJoiner;


// "5~2@F~B~E~5~5~T~5~5~B~F@B~B~E~B~B~E~R~R~R~R@B"

public class Serializer {

    public static Piece[][] deserializeBoard(String boardString) {
        if (boardString == null) {
            return new Piece[0][0];
        }
        if (boardString.isEmpty()) {
            System.out.println("Board String was empty. Could not deserialize.");
            return new Piece[0][0];
        }
        if (boardString.length() <= 2) {
            System.out.println("Board string is not long enough to be valid");
            return new Piece[0][0];
        }
        String[] components = boardString.split("@");

        String[] dimensions = components[0].split("~");
        int rows = Integer.parseInt(dimensions[0]);
        int columns = Integer.parseInt(dimensions[1]);
        int totalSpaces = rows * columns;

        String[] pieces = components[1].split("~");
        if (pieces.length != totalSpaces) {
            System.out.println("Invalid amount of pieces. The board says it has " + rows + " rows and " + columns + " columns. " +
                    "Therefore, a total of " + totalSpaces + " total spaces." + "But there were only " + (pieces.length - 2) + " pieces provided.");
            return null;
        }

        String[] players = components[2].split("~");

        if (pieces.length != players.length) {
            System.out.println("The amount of pieces and players are not matching up. Number of pieces: " + pieces.length + " compared to: " + players.length);
            return new Piece[0][0];
        }

        Piece[][] board = new Piece[rows][columns];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int singleArrayPosition = j + (i * columns);
                PlayerSide side;
                if (players[singleArrayPosition].equalsIgnoreCase("B")) {
                    side = PlayerSide.BLUE;
                } else if (players[singleArrayPosition].equalsIgnoreCase("R")) {
                    side = PlayerSide.RED;
                } else {
                    side = PlayerSide.NON_PLAYER;
                }
                Piece newPiece = createPiece(pieces[singleArrayPosition], side);
                if (newPiece == null) {
                    System.out.println("There was an invalid piece on the board. The piece was: " +  pieces[j + (i * columns)]);
                    return new Piece[0][0];
                }
                board[i][j] = newPiece;
            }
        }
        return board;
    }

    public static String serializeBoard(Piece[][] board, String currentPlayer) {
        if (board == null) {
            return "";
        }
        if (board.length == 0) {
            return "";
        }
        if (board[0].length == 0) {
            return "";
        }
        int rows = board.length;
        int columns = board[0].length;
        StringBuilder dimensions = new StringBuilder("");
        dimensions.append(rows);
        dimensions.append("~");
        dimensions.append(columns);
        dimensions.append("@");

        StringJoiner pieces = new StringJoiner("~","", "");
        StringJoiner owners = new StringJoiner("~","", "");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                pieces.add(getTypeChar(board[i][j]));
                owners.add(String.valueOf(getPlayerSideChar(board[i][j].getPlayerSide())));
            }
        }

        StringBuilder closedPieces = new StringBuilder();
        closedPieces.append(dimensions);
        closedPieces.append(pieces);
        closedPieces.append("@");
        closedPieces.append(owners);
        closedPieces.append("@");
        closedPieces.append(currentPlayer);

        return closedPieces.toString();
    }

    private static char getPlayerSideChar(PlayerSide playerSide) {
        if (playerSide == PlayerSide.RED) {
            return 'R';
        } else if (playerSide == PlayerSide.BLUE) {
            return 'B';
        } else {
            return 'E';
        }
    }

    public static String GetNextPlayer(String board) {
        String[] values = board.split("@");
        String player = values[3];
        if (player.equalsIgnoreCase("B")) {
            return "R";
        } else {
            return "B";
        }
    }

    private static String[] transformToStringArray(String input, int rows, int columns) {
        return input.substring(2, input.length() - 2).split("~");
    }

    public static String getTypeChar(Piece piece) {
        if (piece == null) {
            System.out.println("When creating a piece, the owner cannot be null");
            return "";
        }
        switch(piece.getType()) {
            case MARSHALL:
                return "10";
            case GENERAL:
                return "9";
            case COLONEL:
                return "8";
            case MAJOR:
                return "7";
            case CAPTAIN:
                return "6";
            case LIEUTENANT:
                return "5";
            case SERGEANT:
                return "4";
            case MINER:
                return "3";
            case SCOUT:
                return "2";
            case SPY:
                return "S";
            case BOMB:
                return "B";
            case FLAG:
                return "F";
            case EMPTY:
                return "E";
            case TERRAIN:
                return "T";
            default:
                return "";
        }
    }

    private static Piece createPiece(String id, PlayerSide playerSide) {
        if (playerSide == null) {
            System.out.println("When creating a piece, the owner cannot be null");
            return null;
        }
        switch(id) {
            case "10":
                return new Marshall(playerSide);
            case "9":
                return new General(playerSide);
            case "8":
                return new Colonel(playerSide);
            case "7":
                return new Major(playerSide);
            case "6":
                return new Captain(playerSide);
            case "5":
                return new Lieutenant(playerSide);
            case "4":
                return new Sergeant(playerSide);
            case "3":
                return new Miner(playerSide);
            case "2":
                return new Scout(playerSide);
            case "S":
                return new Spy(playerSide);
            case "B":
                return new Bomb(playerSide);
            case "F":
                return new Flag(playerSide);
            case "E":
                return new Empty(playerSide);
            case "T":
                return new Terrain(playerSide);
            default:
                return null;
        }
    }

}
