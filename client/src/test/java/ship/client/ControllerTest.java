package ship.client;

import com.vimensa.ship.client.dao.Client;
import com.vimensa.ship.client.data.DataProcessImp;
import com.vimensa.ship.client.service.LoginCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {
    @Autowired
    private DataProcessImp dao;

    @Test
    public void testRegister() {
        String phone = "09365989865";
        String code = LoginCode.getCode();
        dao.registerClient(phone,code);
        Client client = dao.getClientByPhone(phone);
        assertEquals(phone, client.getPhone());
    }
}
