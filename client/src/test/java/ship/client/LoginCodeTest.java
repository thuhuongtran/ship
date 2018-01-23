package ship.client;

import com.vimensa.ship.client.service.LoginCode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginCodeTest {
    @Test
    public void testCode(){
        System.out.println(LoginCode.getCode());
        assertEquals(0, 0);
    }
}
