package ship.client;

import com.vimensa.ship.client.APIStart;
import com.vimensa.ship.client.dao.Client;
import com.vimensa.ship.client.dao.Shipper;
import com.vimensa.ship.client.data.DataProcess;
import com.vimensa.ship.client.model.Destination;
import com.vimensa.ship.client.request.UrgentOrderRequest;
import com.vimensa.ship.client.service.LoginCode;
import com.vimensa.ship.client.service.Tasks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
        dao.registerClient(phone);
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
    public void addUrgentOrderTest(){
        String od_id = Calendar.getInstance().getTimeInMillis() + "OD";
        Destination d = new Destination(21.56326, 104.3659, "Nha B");
        List<Destination> dli = new ArrayList<>();
        dli.add(d);
        UrgentOrderRequest ord = new UrgentOrderRequest("1520580562945cli","20000",5,"","nha A",20.63236,105.56985,
                "09658326541",dli,3.5);
        dao.addNewUrgentOrder(ord,od_id);
    }

}
