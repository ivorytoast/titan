package backend.nyc.com.titan.model;

import backend.nyc.com.titan.model.enums.PieceType;
import backend.nyc.com.titan.model.pieces.Empty;
import backend.nyc.com.titan.serializer.Serializer;

import java.util.HashSet;

public class Board {

    Piece[][] pieces;
    BoardData[][] data;

    public Board(String serializedBoard) {
        this.pieces = Serializer.deserializeBoard(serializedBoard);

        if (pieces == null) {
            System.out.println("Was not able to serialize the board. Please check serialized board string");
        }

        this.data = new BoardData[pieces.length][pieces[0].length];
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[0].length; j++) {
                if (pieces[i][j].getType() == PieceType.TERRAIN) {
                    this.data[i][j] = new BoardData(new Player(0), new HashSet<>(), true);
                } else {
                    this.data[i][j] = new BoardData(pieces[i][j].getOwner(), new HashSet<>(), false);
                }
            }
        }
    }

    public void printBoard() {
        for (Piece[] row : pieces) {
            for (Piece piece : row) {
                StringBuilder output = new StringBuilder("(" + piece.getOwner().getId() + ") " + piece.getType());
                for (int i = output.length(); i < 25; i++) {
                    output.append(" ");
                }
                System.out.print(output);
            }
            System.out.println();
        }
    }

    public void printBoard(Player player) {
        for (Piece[] row : pieces) {
            for (Piece piece : row) {
                PieceType pieceType = piece.getType();
                if (piece.getOwner().getId() != player.getId() && piece.getOwner().getId() != 0 && !piece.isVisible()) {
                    pieceType = PieceType.UNKNOWN;
                }
                StringBuilder output = new StringBuilder("(" + piece.getOwner().getId() + ") " + pieceType);
                for (int i = output.length(); i < 25; i++) {
                    output.append(" ");
                }
                System.out.print(output);
            }
            System.out.println();
        }
    }

    public boolean movePiece(Player player, int fromX, int fromY, int toX, int toY) {
        if (!areCoordinatesInBounds(toX, toY)) {
            System.out.println("The coordinates (" + toX + "," + toY + " are not valid coordinates");
            return false;
        }

        Piece fromPiece = pieces[fromX][fromY];
        Piece toPiece = pieces[toX][toY];

        if (!fromPiece.canMove()) {
            System.out.println("Cannot move a " + fromPiece.getType());
            return false;
        }

        int moveDistance = Math.abs(toX - fromX) + Math.abs(toY - fromY);

        if (fromPiece.moveLength() < moveDistance) {
            System.out.println("Invalid move for a " + fromPiece.getType());
            return false;
        }

        int fromOwner = fromPiece.getOwner().getId();
        int toOwner = toPiece.getOwner().getId();

        if (fromOwner != player.getId()) {
            System.out.println("Player " + player.getId() + " cannot move someone else's piece");
            return false;
        }

        if (fromOwner == toOwner) {
            System.out.println("A player cannot attack their own piece " + fromPiece.getType());
            return false;
        }

        if (data[fromX][fromY].isTerrain || data[toX][toY].isTerrain) {
            System.out.println("A player cannot move or move their piece on terrain " + fromPiece.getType());
            return false;
        }

        if (toPiece.getType() == PieceType.EMPTY) {
            // move
            pieces[toX][toY] = fromPiece;
            data[toX][toY].setNewOwner(player);
            resetBoardLocation(fromX, fromY);
        } else {
            // attack
            Piece attacker = pieces[fromX][fromY];
            Piece defender = pieces[toX][toY];
            if (defender.getType() == PieceType.FLAG) {
                System.out.println(player.getId() + " won!");
            }
            System.out.println(data[fromX][fromY].owner.getId() + "'s " + attacker.getType() + " is attacking " + data[toX][toY].owner.getId() + "'s " + defender.getType());
            if (defender.survivesDefenseFrom(attacker)) {
                System.out.println("Defender Won");
                // continue -- nothing happened -- except make that piece now visible
                defender.toggleIsVisible();
            } else {
                System.out.println("Defender lost");
                resetBoardLocation(toX, toY);
            }
            if (attacker.survivesAttackOn(defender)) {
                System.out.println("Attacker won");
                pieces[toX][toY] = attacker;
                data[toX][toY].setNewOwner(player);
                attacker.toggleIsVisible();
                resetBoardLocation(fromX, fromY);
            } else {
                System.out.println("Attacker lost");
                resetBoardLocation(fromX, fromY);
            }
        }

        return true;
    }

    private void resetBoardLocation(int x, int y) {
        pieces[x][y] = new Empty(new Player(0));
        data[x][y].resetLocationToEmpty();
    }

    private boolean areCoordinatesInBounds(int x, int y) {
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

    public Piece[][] getPieces() {
        return this.pieces;
    }

}
