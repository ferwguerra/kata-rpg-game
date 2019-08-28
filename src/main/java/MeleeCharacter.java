public class MeleeCharacter extends Character {

    public static final int MAX_MELEE_RANGE = 2;

    @Override
    protected boolean isInRangeToVictim(Character victim) {
        return Math.floorMod(this.getPosition(), victim.getPosition()) <= MAX_MELEE_RANGE;
    }
}
