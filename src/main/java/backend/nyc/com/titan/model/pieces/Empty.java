package backend.nyc.com.titan.model.pieces;

import backend.nyc.com.titan.model.Piece;
import backend.nyc.com.titan.model.Player;
import backend.nyc.com.titan.model.enums.PieceType;
import backend.nyc.com.titan.model.enums.PlayerSide;

public class Empty extends Piece {

    public Empty(PlayerSide playerSide) {
        setPlayerSide(playerSide);
    }

    @Override
    public void move() {

    }

    @Override
    public PieceType getType() {
        return PieceType.EMPTY;
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public int getPower() {
        return -1;
    }

    @Override
    public int moveLength() {
        return 0;
    }

}
