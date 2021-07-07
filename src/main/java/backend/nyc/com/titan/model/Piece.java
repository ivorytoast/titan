package backend.nyc.com.titan.model;

import backend.nyc.com.titan.model.enums.PieceType;

import java.util.Objects;

public abstract class Piece {

    private Player owner;
    private PieceType type;
    private int power;

    public abstract void move();
    public abstract PieceType getType();

    public int getPower() {
        return this.power;
    }

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return power == piece.power && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, power);
    }

}
