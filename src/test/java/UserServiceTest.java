import ch.yth2021.charjar.API.User;
import ch.yth2021.charjar.API.model.APIRespondedBullshitException;
import org.junit.Test;

import java.io.IOException;

public class UserServiceTest {
    @Test
    public void test01() throws APIRespondedBullshitException, IOException {
        System.out.println("HALLO");
        User.BASE_URL = "http://localhost:8080";
        User u = new User("TEST");
        u.modPoints(1);
    }
}
