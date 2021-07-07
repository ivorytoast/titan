package backend.nyc.com.titan.model;

import java.util.HashSet;
import java.util.Set;

public class BoardData {

    Player owner;
    Set<Player> visibleBy = new HashSet<>();
    boolean isTerrain;

    public BoardData(Player owner, Set<Player> visibleBy, boolean isTerrain) {
        this.visibleBy.add(new Player(0));
        this.visibleBy.addAll(visibleBy);

        this.owner = owner;
        this.isTerrain = isTerrain;
    }

}
