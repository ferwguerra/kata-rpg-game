public class Character {
    public static final int MAX_HEALTH_POINTS = 1000;
    public static final int INITIAL_LEVEL = 1;

    private int health = MAX_HEALTH_POINTS;
    private int level = INITIAL_LEVEL;


    public void damage(Character character, int damagePoints) {
        if (character != this) {
            character.setHealth(character.getHealth() - damagePoints);
        }

    }

    private void setHealth(int healthPoints) {
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


    public int getHealth() {
        return this.health;
    }

    public int getLevel() {
        return this.level;
    }

    public boolean isAlive() {
        return health > 0;
    }
}
