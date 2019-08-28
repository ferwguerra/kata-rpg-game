public class RangedCharacter extends Character {

    public static final int MAX_RANGED_RANGE = 20;

    @Override
    public void damage(Character victim, int damagePoints) {
        Character attacker = this;

        if (attacker != victim && isInRangeToVictim(victim, MAX_RANGED_RANGE) && !isAlly(victim)) {

            double finalDamagePoints = damagePoints;

            if (hasLevelForReduceDamage(victim)) {
                finalDamagePoints = damagePoints / 2;
            } else if (canDealExtraDamageTo(victim)) {
                finalDamagePoints = damagePoints * 1.5;
            }

            victim.setHealth(victim.getHealth() - finalDamagePoints);
        }
    }
}
