package backend.nyc.com.titan.model;

import java.util.Objects;

public class Player {

    private final int id;

    public Player(int id) {
        this.id = id;
    }

    public int getName() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
