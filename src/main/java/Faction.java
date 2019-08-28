import java.util.Objects;

public class Faction {
    private final String name;

    public Faction(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faction faction = (Faction) o;
        return Objects.equals(name, faction.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
