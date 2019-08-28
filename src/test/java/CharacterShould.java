import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CharacterShould {

    @Test
    public void be_created_with_1000_health_points() {
        Character character = new Character();
        assertThat(character.getHealth(), is(1000.0));
    }

    @Test
    public void be_created_with_level_one() {
        Character character = new Character();
        assertThat(character.getLevel(), is(1));
    }

    @Test
    public void be_created_alive() {
        Character character = new Character();
        assertTrue(character.isAlive());
    }

    @Test
    public void reduce_health_to_other_characted_when_damaging_other_character() {
        Character characterDamaged = new Character();
        Character character = new MeleeCharacter();
        character.damage(characterDamaged, 50);
        assertThat(characterDamaged.getHealth(), is(950.0));
    }

    @Test
    public void die_when_health_drops_less_than_0() {
        Character characterDamaged = new Character();
        Character character = new MeleeCharacter();
        character.damage(characterDamaged, 1050);
        assertFalse(characterDamaged.isAlive());
    }

    @Test
    public void be_healed_when_he_heals_himself() {
        Character characterDamaged = new Character();
        Character character = new MeleeCharacter();
        character.damage(characterDamaged, 500);

        characterDamaged.heal(200);

        assertThat(characterDamaged.getHealth(), is(700.0));
    }

    @Test
    public void not_be_healed_when_is_dead() {
        Character characterDamaged = new Character();
        Character character = new MeleeCharacter();
        character.damage(characterDamaged, 1050);

        character.heal(200);

        assertThat(characterDamaged.getHealth(), is(-50.0));
        assertFalse(characterDamaged.isAlive());
    }

    @Test
    public void be_healed_by_1000_when_is_healed_exceeds_1000() {
        Character characterDamaged = new Character();
        Character character = new MeleeCharacter();
        character.damage(characterDamaged, 500);

        characterDamaged.heal(10000);

        assertThat(characterDamaged.getHealth(), is(1000.0));
    }

    @Test
    public void have_same_health_points_when_he_tries_to_damage_himself() {
        Character character = new Character();
        character.damage(character, 50);
        assertThat(character.getHealth(), is(1000.0));
    }

    @Test
    public void reduce_half_health_points_when_attacker_is_5_levels_or_more_below() {
        Character victim = new Character();
        victim.setLevel(10);

        Character attacker = new MeleeCharacter();
        attacker.setLevel(2);

        attacker.damage(victim, 1000);

        assertThat(victim.getHealth(), is(500.0));
    }

    @Test
    public void increase_half_health_points_when_attacker_is_5_levels_or_more_above() {
        Character victim = new Character();
        victim.setLevel(2);

        Character attacker = new MeleeCharacter();
        attacker.setLevel(10);

        attacker.damage(victim, 250);

        assertThat(victim.getHealth(), is(625.0));
    }

    @Test
    public void reduce_health_to_another_character_when_he_attacks_as_melee() {
        Character victim = new Character();
        victim.setPosition(50);
        Character attacker = new MeleeCharacter();
        attacker.setPosition(52);

        attacker.damage(victim, 500);

        assertThat(victim.getHealth(), is(500.0));
    }

    @Test
    public void reduce_health_to_another_character_when_he_attacks_as_range() {
        Character victim = new Character();
        victim.setPosition(50);
        Character attacker = new RangedCharacter();
        attacker.setPosition(70);

        attacker.damage(victim, 500);

        assertThat(victim.getHealth(), is(500.0));
    }
}
