package backend.nyc.com.titan.model.pieces;

import backend.nyc.com.titan.model.Piece;
import backend.nyc.com.titan.model.Player;
import backend.nyc.com.titan.model.enums.PieceType;

public class Miner extends Piece {

    public Miner(Player owner) {
        setOwner(owner);
    }

    @Override
    public void move() {

    }

    @Override
    public PieceType getType() {
        return PieceType.MINER;
    }

    @Override
    public boolean canMove() {
        return true;
    }

}
