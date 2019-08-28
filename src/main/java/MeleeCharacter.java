public class MeleeCharacter extends Character {

    public static final int MAX_MELEE_RANGE = 2;

    @Override
    public void damage(Character victim, int damagePoints) {
        Character attacker = this;

        if (attacker != victim && isInRangeToVictim(victim)) {

            double finalDamagePoints = damagePoints;

            if (hasLevelForReduceDamage(victim)) {
                finalDamagePoints = damagePoints / 2;
            } else if (canDealExtraDamageTo(victim)) {
                finalDamagePoints = damagePoints * 1.5;
            }

            victim.setHealth(victim.getHealth() - finalDamagePoints);
        }
    }

    private boolean isInRangeToVictim(Character victim) {
        return Math.floorMod(this.getPosition(), victim.getPosition()) <= MAX_MELEE_RANGE;
    }
}