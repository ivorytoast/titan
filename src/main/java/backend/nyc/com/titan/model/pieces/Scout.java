package backend.nyc.com.titan.model.pieces;

import backend.nyc.com.titan.model.Piece;
import backend.nyc.com.titan.model.Player;
import backend.nyc.com.titan.model.enums.PieceType;
import backend.nyc.com.titan.model.enums.PlayerSide;

public class Scout extends Piece {

    public Scout(PlayerSide playerSide) {
        setPlayerSide(playerSide);
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

    @Override
    public int getPower() {
        return 2;
    }

}
