package ship.client;

import com.vimensa.ship.client.APIStart;
import com.vimensa.ship.client.dao.Client;
import com.vimensa.ship.client.dao.Shipper;
import com.vimensa.ship.client.data.DataProcess;
import com.vimensa.ship.client.request.NewOrderRequest;
import com.vimensa.ship.client.service.LoginCode;
import com.vimensa.ship.client.service.Tasks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

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
    public void testGetDriverByPhone(){
        String phone = "09653874125";
        Shipper shipper=dao.getShipperByPhone(phone);
        assertEquals("158795463211",shipper.getTimestamp());
    }
    @Test
    public void addNewOrderSystemTest(){
        long time = Calendar.getInstance().getTimeInMillis();
        NewOrderRequest ord = new NewOrderRequest("0965325685","testting",200000,2,
                1,"","nha A test","nha B test",20.89654,104.36985,21.89544,105.23666,3.5);
        dao.newOrderSystem(time, time+"OD",35000,ord);

    }

}
