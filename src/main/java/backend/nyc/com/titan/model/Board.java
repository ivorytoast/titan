package backend.nyc.com.titan.model;

import backend.nyc.com.titan.model.enums.PieceType;
import backend.nyc.com.titan.model.enums.PlayerSide;
import backend.nyc.com.titan.model.pieces.Empty;
import backend.nyc.com.titan.serializer.Serializer;

public class Board {

    public Board(String serializedBoard) {
        Piece[][] pieces = Serializer.deserializeBoard(serializedBoard);

        if (pieces == null) {
            System.out.println("Was not able to serialize the board. Please check serialized board string");
        }
    }

    public void printBoard(Piece[][] pieces, Player player) {
        for (Piece[] row : pieces) {
            for (Piece piece : row) {
                PieceType pieceType = piece.getType();
                if (piece.getPlayerSide() != player.getPlayerSide() && piece.getPlayerSide() != PlayerSide.SPECTATOR && !piece.isVisible()) {
                    pieceType = PieceType.UNKNOWN;
                }
                StringBuilder output = new StringBuilder("(" + piece.getPlayerSide() + ") " + pieceType);
                for (int i = output.length(); i < 25; i++) {
                    output.append(" ");
                }
                System.out.print(output);
            }
            System.out.println();
        }
    }

    public void printBoard(Piece[][] pieces, PlayerSide playerSide) {
        for (Piece[] row : pieces) {
            for (Piece piece : row) {
                PieceType pieceType = piece.getType();
                if (piece.getPlayerSide() != playerSide && playerSide != PlayerSide.SPECTATOR && piece.getPlayerSide() != PlayerSide.NON_PLAYER && !piece.isVisible()) {
                    pieceType = PieceType.UNKNOWN;
                }
                StringBuilder output = new StringBuilder("(" + piece.getPlayerSide() + ") " + pieceType);
                for (int i = output.length(); i < 25; i++) {
                    output.append(" ");
                }
                System.out.print(output);
            }
            System.out.println();
        }
    }

    public boolean movePiece(Piece[][] pieces, PlayerSide playerSide, int fromX, int fromY, int toX, int toY) {
        if (!areCoordinatesInBounds(pieces, toX, toY)) {
            System.out.println("The coordinates (" + toX + "," + toY + " are not valid coordinates");
            return false;
        }

        Piece fromPiece = pieces[fromX][fromY];
        Piece toPiece = pieces[toX][toY];

        printBoard(pieces, PlayerSide.SPECTATOR);

        if (!fromPiece.canMove()) {
            System.out.println("Cannot move a " + fromPiece.getType());
            return false;
        }

        int moveDistance = Math.abs(toX - fromX) + Math.abs(toY - fromY);

        if (fromPiece.moveLength() < moveDistance) {
            System.out.println("Invalid move for a " + fromPiece.getType());
            return false;
        }

        PlayerSide fromOwner = fromPiece.getPlayerSide();
        PlayerSide toOwner = toPiece.getPlayerSide();

        System.out.println("(Should be equal) Comparing fromOwner: " + fromOwner + " to playerSide: " + playerSide);
        if (fromOwner != playerSide) {
            System.out.println("Player " + playerSide + " cannot move someone else's piece");
            return false;
        }

        System.out.println("(Should not be equal) Comparing fromOwner: " + fromOwner + " to toOwner: " + toOwner);
        if (fromOwner == toOwner) {
            System.out.println("A player cannot attack their own piece " + fromPiece.getType());
            return false;
        }

        if (pieces[fromX][fromY].getType() == PieceType.TERRAIN || pieces[toX][toY].getType() == PieceType.TERRAIN) {
            System.out.println("A player cannot move or move their piece on terrain " + fromPiece.getType());
            return false;
        }

        if (toPiece.getType() == PieceType.EMPTY) {
            // move
            pieces[toX][toY] = fromPiece;
            pieces[toX][toY].setPlayerSide(playerSide);
            resetBoardLocation(pieces, fromX, fromY);
        } else {
            // attack
            Piece attacker = pieces[fromX][fromY];
            Piece defender = pieces[toX][toY];
            if (defender.getType() == PieceType.FLAG) {
                System.out.println(playerSide + " won!");
            }
            System.out.println(pieces[fromX][fromY].getPlayerSide() + "'s " + attacker.getType() + " is attacking " + pieces[toX][toY].getPlayerSide() + "'s " + defender.getType());
            if (defender.survivesDefenseFrom(attacker)) {
                System.out.println("Defender Won");
                // continue -- nothing happened -- except make that piece now visible
                defender.toggleIsVisible();
            } else {
                System.out.println("Defender lost");
                resetBoardLocation(pieces, toX, toY);
            }
            if (attacker.survivesAttackOn(defender)) {
                System.out.println("Attacker won");
                pieces[toX][toY] = attacker;
                pieces[toX][toY].setPlayerSide(playerSide);
                attacker.toggleIsVisible();
                resetBoardLocation(pieces, fromX, fromY);
            } else {
                System.out.println("Attacker lost");
                resetBoardLocation(pieces, fromX, fromY);
            }
        }

        return true;
    }

    private void resetBoardLocation(Piece[][] pieces, int x, int y) {
        pieces[x][y] = new Empty(PlayerSide.NON_PLAYER);
    }

    private boolean areCoordinatesInBounds(Piece[][] pieces, int x, int y) {
        int rows = pieces.length;
        int columns = pieces[0].length;

        if (x < 0 || x >= rows) {
            System.out.println("X out of bounds");
            return false;
        }
        if (y < 0 || y >= columns) {
            System.out.println("Y out of bounds");
            return false;
        }

        return true;
    }

}
