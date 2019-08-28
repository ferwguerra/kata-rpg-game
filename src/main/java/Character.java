public class Character {
    public static final int MAX_HEALTH_POINTS = 1000;
    public static final int INITIAL_LEVEL = 1;

    private double health = MAX_HEALTH_POINTS;
    private int level = INITIAL_LEVEL;


    public void damage(Character victim, int damagePoints) {
        Character attacker = this;

        if (attacker != victim) {

            if (victim.getLevel() - attacker.getLevel() >= 5) {
                victim.setHealth(victim.getHealth() - damagePoints / 2);
            } else if (attacker.getLevel() - victim.getLevel() >= 5) {
                victim.setHealth(victim.getHealth() - damagePoints * 1.5);
            } else {
                victim.setHealth(victim.getHealth() - damagePoints);
            }
        }

    }


    private void setHealth(double healthPoints) {
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
}
