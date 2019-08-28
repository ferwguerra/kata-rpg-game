import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Character {
    public static final int MAX_HEALTH_POINTS = 1000;
    public static final int INITIAL_LEVEL = 1;
    public static final int LEVEL_DIFFERENCE_FOR_EXTRA_DAMAGE = 5;

    private double health = MAX_HEALTH_POINTS;
    private int level = INITIAL_LEVEL;
    private int position = 1;
    private List<String> factions = new ArrayList<>();


    protected void damage(Character victim, int damagePoints) {
    }

    protected boolean isInRangeToVictim(Character victim, int range) {
        return Math.floorMod(this.getPosition(), victim.getPosition()) <= range;
    }

    protected boolean canDealExtraDamageTo(Character victim) {
        return this.getLevel() - victim.getLevel() >= LEVEL_DIFFERENCE_FOR_EXTRA_DAMAGE;
    }

    protected boolean hasLevelForReduceDamage(Character victim) {
        return victim.getLevel() - this.getLevel() >= LEVEL_DIFFERENCE_FOR_EXTRA_DAMAGE;
    }


    public void setHealth(double healthPoints) {
        this.health = healthPoints;
    }

    public void heal(int healPoints) {
        if (!this.isAlive()) {
            return;
        }

        if (areHealingPointsExceedingMaxHealth(healPoints)) {
            this.setHealth(MAX_HEALTH_POINTS);
        } else {
            this.health += healPoints;
        }
    }

    private boolean areHealingPointsExceedingMaxHealth(int healPoints) {
        return this.health + healPoints > MAX_HEALTH_POINTS;
    }


    public double getHealth() {
        return this.health;
    }

    public int getLevel() {
        return this.level;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public List<String> getFactions() {
        return factions;
    }

    public void setFactions(List<String> factions) {
        this.factions = factions;
    }

    public void joinFaction(String factionName) {
        factions.add(factionName);
    }

    public void leftFaction(String factionName) {
        factions.remove(factionName);
    }
}
