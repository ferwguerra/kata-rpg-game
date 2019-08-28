public class RangedCharacter extends Character {

    public static final int MAX_RANGED_RANGE = 20;

    @Override
    protected boolean isInRangeToVictim(Character victim) {
        return Math.floorMod(this.getPosition(), victim.getPosition()) <= MAX_RANGED_RANGE;
    }
}
