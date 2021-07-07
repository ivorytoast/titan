package backend.nyc.com.titan.model;

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
                this.data[i][j] = new BoardData(null, new HashSet<>(), false);
            }
        }
    }

    public void makePieceVisibleToPlayer(Player player, int x, int y) {
        this.data[x][y].visibleBy.add(player);
    }

    public void addPieceToBoard(Piece piece, Player player, int x, int y) {
        pieces[x][y] = piece;
        BoardData pieceData = new BoardData(player, new HashSet<>(), false);
        this.data[x][y] = pieceData;
    }

    public void printBoard() {
        for (Piece[] row : pieces) {
            for (Piece piece : row) {
                System.out.print(piece.getType() + "   ");
            }
            System.out.println();
        }
    }

}
