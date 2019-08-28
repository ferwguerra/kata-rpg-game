import java.util.ArrayList;
import java.util.List;

public class Character {
    public static final int MAX_HEALTH_POINTS = 1000;
    public static final int INITIAL_LEVEL = 1;
    public static final int LEVEL_DIFFERENCE_FOR_EXTRA_DAMAGE = 5;
    public static final int INITIAL_POSITION = 1;

    private double health = MAX_HEALTH_POINTS;
    private int level = INITIAL_LEVEL;
    private int position = INITIAL_POSITION;
    private List<Faction> factions = new ArrayList<>();

    protected void damages(Character victim, int damagePoints) {
        Character attacker = this;

        if (attacker != victim && isInRangeToVictim(victim) && !isAlly(victim)) {

            double finalDamagePoints = damagePoints;

            if (hasLevelForReduceDamage(victim)) {
                finalDamagePoints = damagePoints / 2;
            } else if (canDealExtraDamageTo(victim)) {
                finalDamagePoints = damagePoints * 1.5;
            }

            victim.setHealth(victim.getHealth() - finalDamagePoints);
        }
    }

    public void damages(LivingBeing victim, int damagePoints) {
        victim.setHealth(victim.getHealth() - damagePoints);
    }

    protected boolean isInRangeToVictim(Character victim) {
        return false;
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

    public void heals(Character damaged, int healPoints) {
        if (!damaged.isAlive()) {
            return;
        }

        if (isAlly(damaged)) {
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

    public void joins(Faction faction) {
        factions.add(faction);
    }

    public void leaves(Faction faction) {
        factions.remove(faction);
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

    public void setFactions(List<Faction> factions) {
        this.factions = factions;
    }
}
