package backend.nyc.com.titan.model.pieces;

import backend.nyc.com.titan.model.Piece;
import backend.nyc.com.titan.model.Player;
import backend.nyc.com.titan.model.enums.PieceType;
import backend.nyc.com.titan.model.enums.PlayerSide;

public class Colonel extends Piece {

    public Colonel(PlayerSide playerSide) {
        setPlayerSide(playerSide);
    }

    @Override
    public void move() {

    }

    @Override
    public PieceType getType() {
        return PieceType.COLONEL;
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public int getPower() {
        return 8;
    }

}
