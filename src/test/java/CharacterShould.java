import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

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
        Character victim = new Character();
        Character attacker = new MeleeCharacter();
        attacker.damage(victim, 50);
        assertThat(victim.getHealth(), is(950.0));
    }

    @Test
    public void die_when_health_drops_less_than_0() {
        Character victim = new Character();
        Character attacker = new MeleeCharacter();
        attacker.damage(victim, 1050);
        assertFalse(victim.isAlive());
    }

    @Test
    public void be_healed_when_he_heals_himself() {
        Character victim = new Character();
        victim.setHealth(500);
        victim.setFactions(Arrays.asList(new Faction("Faction I")));

        victim.heal(victim,200);

        assertThat(victim.getHealth(), is(700.0));
    }

    @Test
    public void not_be_healed_when_is_dead() {
        Character victim = new Character();
        Character character = new MeleeCharacter();
        character.damage(victim, 1050);

        character.heal(victim, 200);

        assertThat(victim.getHealth(), is(-50.0));
        assertFalse(victim.isAlive());
    }

    @Test
    public void be_healed_by_1000_when_is_healed_exceeds_1000() {
        Character victim = new Character();
        victim.setHealth(500);
        victim.setFactions(Arrays.asList(new Faction("Faction I")));

        victim.heal(victim, 10000);

        assertThat(victim.getHealth(), is(1000.0));
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

    @Test
    public void be_created_with_no_faction() {
        Character character = new Character();
        assertTrue(character.getFactions().isEmpty());
    }

    @Test
    public void have_one_faction_when_he_joins_a_faction() {
        Character character = new Character();

        Faction faction = new Faction("Faction I");

        character.join(faction);

        assertTrue(character.getFactions().contains(faction));
    }

    @Test
    public void have_zero_faction_when_he_leaves_his_unique_faction() {
        Character character = new Character();

        Faction faction = new Faction("Faction I");

        character.join(faction);
        character.left(faction);

        assertFalse(character.getFactions().contains(faction));
    }

    @Test
    public void not_deal_damage_to_ally() {
        Character attacker = new RangedCharacter();
        Character victim = new Character();
        attacker.setFactions(Arrays.asList(new Faction("Faction I")));
        victim.setFactions(Arrays.asList(new Faction("Faction I")));

        attacker.damage(victim, 200);

        assertThat(victim.getHealth(), is(1000.0));
    }

    @Test
    public void heal_damage_to_ally() {
        Character healer = new RangedCharacter();
        Character damaged = new Character();
        healer.setFactions(Arrays.asList(new Faction("Faction I")));
        damaged.setFactions(Arrays.asList(new Faction("Faction I")));

        healer.heal(damaged, 200);

        assertThat(damaged.getHealth(), is(1000.0));
    }
}
