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
}
