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
    @Test
    public void insertNewOrderTest(){
        String from = "20.13365";
        String to = "102.365";
        String adv_paym = "0";
        String mass = "1";
        String note = "";
        String status = "1";
        String client_phone = "0236598745";
        String timestamp = "10235698741";
        dao.addNewOrder(timestamp, from, to, mass, adv_paym, status, client_phone, note);
        assertEquals(0,0);
    }
    @Test
    public void insertNewOrderTaskTest(){
        tasks.addNewOrder("20.5666","101.236","1","0","",
                "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMjM1NjIzMTIzMSIsImV4cCI6MTUxNzgxNzU3OX0.yMcYIxn3Y9gFXTDdYCIAXT9MvY-R8l_auG7vRp_neyGgH1K-Jx5FrAhOuOE5XFzlpBQgrJ8bSVJgiausnPYIEw");
    }
}
