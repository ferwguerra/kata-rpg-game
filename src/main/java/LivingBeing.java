public class LivingBeing {

    private double health;
    private boolean destroyed;

    public LivingBeing(int initialHealth) {
        this.health = initialHealth;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public boolean isDestroyed() {
        return health <= 0;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
}
