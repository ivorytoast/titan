package backend.nyc.com.titan.model;

import java.util.HashSet;
import java.util.Set;

public class PieceData {

    public Player ownedBy;
    public Set<Player> isVisibleFor;

    public PieceData() {
        this.ownedBy = null;
        this.isVisibleFor = new HashSet<>();
    }

}
