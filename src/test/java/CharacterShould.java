import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
}
