package backend.nyc.com.titan.model;

import backend.nyc.com.titan.model.enums.PieceType;

import java.util.Objects;

public abstract class Piece {

    private Player owner;
    private PieceType type;
    private int power;
    private boolean isVisible = false;

    public abstract void move();
    public abstract PieceType getType();
    public abstract boolean canMove();
    public abstract int getPower();

    public int moveLength() {
        return 1;
    };

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public void toggleIsVisible() {
        this.isVisible = !this.isVisible;
    }

    public boolean survivesAttackOn(Piece defender) {
        if (this.getType() == PieceType.SPY) {
            return defender.getType() == PieceType.MARSHALL;
        }
        if (this.getType() == PieceType.MINER) {
            return defender.getType() == PieceType.BOMB;
        }
        if (defender.getType() == PieceType.BOMB) {
            return false;
        }
        if (defender.getType() == PieceType.FLAG) {
            return true;
        }
        return this.getPower() > defender.getPower();
    }

    public boolean survivesDefenseFrom(Piece attacker) {
        return this.getPower() > attacker.getPower();
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