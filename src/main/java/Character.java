import java.util.ArrayList;
import java.util.List;

public class Character {
    public static final int MAX_HEALTH_POINTS = 1000;
    public static final int INITIAL_LEVEL = 1;
    public static final int LEVEL_DIFFERENCE_FOR_EXTRA_DAMAGE = 5;

    private double health = MAX_HEALTH_POINTS;
    private int level = INITIAL_LEVEL;
    private int position = 1;
    private List<Faction> factions = new ArrayList<>();

    public void setFactions(List<Faction> factions) {
        this.factions = factions;
    }

    protected void damage(Character victim, int damagePoints) {
    }

    protected boolean isInRangeToVictim(Character victim, int range) {
        return Math.floorMod(this.getPosition(), victim.getPosition()) <= range;
    }

    protected boolean isAlly(Character character) {
        return this.getFactions().stream()
                .anyMatch(faction -> character.getFactions().contains(faction));
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

    public void heal(Character damaged, int healPoints) {
        if (!damaged.isAlive()) {
            return;
        }

        if(isAlly(damaged)) {
            if (areHealingPointsExceedingMaxHealth(damaged, healPoints)) {
                damaged.setHealth(MAX_HEALTH_POINTS);
            } else {
                damaged.setHealth(damaged.getHealth() + healPoints);
            }
        }

    }

    private boolean areHealingPointsExceedingMaxHealth(Character character, int healPoints) {
        return character.getHealth() + healPoints > MAX_HEALTH_POINTS;
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

    public List<Faction> getFactions() {
        return factions;
    }

    public void join(Faction faction) {
        factions.add(faction);
    }

    public void left(Faction faction) {
        factions.remove(faction);
    }
}
