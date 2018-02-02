package ship.client;

import com.vimensa.ship.client.APIStart;
import com.vimensa.ship.client.dao.Client;
import com.vimensa.ship.client.data.DataProcess;
import com.vimensa.ship.client.service.LoginCode;
import com.vimensa.ship.client.service.Tasks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = APIStart.class)
public class ControllerTest {
    @Autowired
    private DataProcess dao;

    @Autowired
    private Tasks tasks;

    @Test
    public void testRegister() {
        String phone = "09365989865";
        String code = LoginCode.getCode();
        dao.registerClient(phone,code);
        Client client = dao.getClientByPhone(phone);
        assertEquals(phone, client.getPhone());
    }

}
