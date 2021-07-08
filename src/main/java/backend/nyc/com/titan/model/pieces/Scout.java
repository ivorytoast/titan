package backend.nyc.com.titan.model.pieces;

import backend.nyc.com.titan.model.Piece;
import backend.nyc.com.titan.model.Player;
import backend.nyc.com.titan.model.enums.PieceType;

public class Scout extends Piece {

    public Scout(Player owner) {
        setOwner(owner);
    }

    @Override
    public void move() {

    }

    @Override
    public PieceType getType() {
        return PieceType.SCOUT;
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public int moveLength() {
        return Integer.MAX_VALUE;
    }

}
