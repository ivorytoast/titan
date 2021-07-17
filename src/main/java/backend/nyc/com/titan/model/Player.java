package backend.nyc.com.titan.model;

import backend.nyc.com.titan.model.enums.PlayerSide;

import java.util.Objects;

public class Player {

    private final String name;
    private final PlayerSide playerSide;

    public Player(String name, PlayerSide playerSide) {
        this.name = name;
        this.playerSide = playerSide;
    }

    public String getName() {
        return this.name;
    }

    public PlayerSide getPlayerSide() {
        return this.playerSide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(playerSide, player.playerSide);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerSide);
    }

    @Override
    public String toString() {
        return this.playerSide + " player named " + this.name;
    }

    public static Player createSpectator() {
        return new Player("Spectator", PlayerSide.SPECTATOR);
    }

    public static Player createTerrain() {
        return new Player("Terrain", PlayerSide.TERRAIN);
    }

}
