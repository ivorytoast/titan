package backend.nyc.com.titan.model.pieces;

import backend.nyc.com.titan.model.Piece;
import backend.nyc.com.titan.model.Player;
import backend.nyc.com.titan.model.enums.PieceType;

public class Lieutenant extends Piece {

    public Lieutenant(Player owner) {
        setOwner(owner);
    }

    @Override
    public void move() {

    }

    @Override
    public PieceType getType() {
        return PieceType.LIEUTENANT;
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public int getPower() {
        return 5;
    }

}
