public class Character {
    public static final int MAX_HEALTH_POINTS = 1000;
    public static final int INITIAL_LEVEL = 1;
    public static final int LEVEL_DIFFERENCE_FOR_EXTRA_DAMAGE = 5;

    private double health = MAX_HEALTH_POINTS;
    private int level = INITIAL_LEVEL;
    private int position = 1;


    protected void damage(Character victim, int damagePoints) {
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
}
