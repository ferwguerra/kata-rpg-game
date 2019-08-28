public class Character {
    public static final int MAX_HEALTH_POINTS = 1000;
    public static final int INITIAL_LEVEL = 1;

    private int health = MAX_HEALTH_POINTS;
    private int level = INITIAL_LEVEL;


    public void damage(Character character, int damagePoints) {
        character.setHealth(character.getHealth() - damagePoints);
    }

    private void setHealth(int healthPoints) {
        this.health = healthPoints;
    }

    public void heal(Character character, int healPoints) {
        if (!character.isAlive()) {
            return;
        }

        if (areHealingPointsExceedingMaxHealth(character, healPoints)) {
            character.setHealth(MAX_HEALTH_POINTS);
        } else {
            int currentHealth = character.getHealth();
            character.setHealth(currentHealth + healPoints);
        }
    }

    private boolean areHealingPointsExceedingMaxHealth(Character character, int healPoints) {
        return character.getHealth() + healPoints > MAX_HEALTH_POINTS;
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
