package backend.nyc.com.titan.model.pieces;

import backend.nyc.com.titan.model.Piece;
import backend.nyc.com.titan.model.Player;
import backend.nyc.com.titan.model.enums.PieceType;

public class Bomb extends Piece {

    public Bomb(Player owner) {
        setOwner(owner);
    }

    @Override
    public void move() {

    }

    @Override
    public PieceType getType() {
        return PieceType.BOMB;
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public int getPower() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int moveLength() {
        return 0;
    }

}