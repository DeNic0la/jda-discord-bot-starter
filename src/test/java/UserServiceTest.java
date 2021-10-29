import ch.yth2021.charjar.discord.module.swear.SwearModule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserServiceTest {
    @Test
    public void test01() {
        SwearModule sm = new SwearModule();
        int i = sm.countSwearwords("how many fu*king wear words does this shit have ? i dont fucking know");
        assertEquals(3, i);
    }

    @Test
    public void test02() {
        SwearModule sm = new SwearModule();
        int i = sm.countSwearwords("fuckass");
        assertEquals(1, i);
    }

    @Test
    public void test03() {
        SwearModule sm = new SwearModule();
        int i = sm.countSwearwords("this fucking fuck");
        assertEquals(2, i);
    }

    @Test
    public void test04() {
        SwearModule sm = new SwearModule();
        int i = sm.countSwearwords("hi everyone!");
        assertEquals(0, i);
    }
}
