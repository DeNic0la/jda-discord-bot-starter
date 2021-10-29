import ch.yth2021.charjar.API.model.APIRespondedBullshitException;
import ch.yth2021.charjar.discord.module.swear.SwearModule;
import org.junit.Test;

import java.io.IOException;

public class UserServiceTest {
    @Test
    public void test01() throws APIRespondedBullshitException, IOException {
        SwearModule sm = new SwearModule();
        int i = sm.countSwearwords("how many fu*king wear words does this shit have ? i dont fucking know");
        System.out.println(i);
    }
}
