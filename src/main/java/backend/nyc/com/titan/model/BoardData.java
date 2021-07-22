package backend.nyc.com.titan.model;

import backend.nyc.com.titan.model.enums.PlayerSide;

import java.util.HashSet;
import java.util.Set;

public class BoardData {

    PlayerSide playerSide;
    Set<PlayerSide> visibleBy = new HashSet<>();
    boolean isTerrain;

    public BoardData(PlayerSide playerSide, Set<PlayerSide> visibleBy, boolean isTerrain) {
        this.visibleBy.add(playerSide);
        this.visibleBy.addAll(visibleBy);

        this.playerSide = playerSide;
        this.isTerrain = isTerrain;
    }

    public void resetLocationToEmpty() {
        this.playerSide = PlayerSide.NON_PLAYER;
        this.visibleBy = new HashSet<>();
        this.visibleBy.add(playerSide);
        this.isTerrain = false;
    }

    public void setNewPlayerSide(PlayerSide playerSide) {
        this.playerSide = playerSide;
        this.visibleBy.add(playerSide);
    }

}
