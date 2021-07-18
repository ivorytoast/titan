package backend.nyc.com.titan.model.pieces;

import backend.nyc.com.titan.model.Piece;
import backend.nyc.com.titan.model.Player;
import backend.nyc.com.titan.model.enums.PieceType;
import backend.nyc.com.titan.model.enums.PlayerSide;

public class Bomb extends Piece {

    public Bomb(PlayerSide playerSide) {
        setPlayerSide(playerSide);
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
