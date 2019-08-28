import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CharacterShould {

    @Test
    public void be_created_with_1000_health_points() {
        Character character = new Character();
        assertThat(character.getHealth(), is(1000));
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
        Character character = new Character();
        character.damage(characterDamaged, 50);
        assertThat(characterDamaged.getHealth(), is(950));
    }

    @Test
    public void die_when_health_drops_less_than_0() {
        Character characterDamaged = new Character();
        Character character = new Character();
        character.damage(characterDamaged, 1050);
        assertFalse(characterDamaged.isAlive());
    }

    @Test
    public void be_healed_when_he_heals_himself() {
        Character characterDamaged = new Character();
        Character character = new Character();
        character.damage(characterDamaged, 500);

        characterDamaged.heal(200);

        assertThat(characterDamaged.getHealth(), is(700));
    }

    @Test
    public void not_be_healed_when_is_dead() {
        Character characterDamaged = new Character();
        Character character = new Character();
        character.damage(characterDamaged, 1050);

        character.heal(200);

        assertThat(characterDamaged.getHealth(), is(-50));
        assertFalse(characterDamaged.isAlive());
    }

    @Test
    public void be_healed_by_1000_when_is_healed_exceeds_1000() {
        Character characterDamaged = new Character();
        Character character = new Character();
        character.damage(characterDamaged, 500);

        characterDamaged.heal(10000);

        assertThat(characterDamaged.getHealth(), is(1000));
    }

    @Test
    public void have_same_health_points_when_he_tries_to_damage_himself() {
        Character character = new Character();
        character.damage(character, 50);
        assertThat(character.getHealth(), is(1000));
    }

}
