package ship.client;

import com.vimensa.ship.client.APIStart;
import com.vimensa.ship.client.dao.Client;
import com.vimensa.ship.client.dao.Shipper;
import com.vimensa.ship.client.data.DataProcess;
import com.vimensa.ship.client.model.Destination;
import com.vimensa.ship.client.model.ItemType;
import com.vimensa.ship.client.request.UrgentOrderRequest;
import com.vimensa.ship.client.request.WaitOrderRequest;
import com.vimensa.ship.client.response.GetShipperRes;
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
    @Test
    public void addUrgentOrderSystemTest(){
        String od_id = Calendar.getInstance().getTimeInMillis() + "OD";
        dao.addUrgentOrderSystem(od_id);
    }
    @Test
    public void addWaitOrderSystemTest(){
        String od_id = "1521516074699OD";
        dao.addWaitOrderSystem(od_id);
    }
    @Test
    public void addNewWaitOrderTest(){
        String od_id = Calendar.getInstance().getTimeInMillis() + "OD";
        Destination d = new Destination(21.1083502, 105.7869331, "backinh");
        List<Destination> dli = new ArrayList<>();
        dli.add(d);
        WaitOrderRequest o = new WaitOrderRequest("1520580562945cli", "20000", ItemType.OTHERS, "", "saigon", 20.63236, 105.56985,
                "06935623154", dli, 2.5,"1521522660000");
        dao.addNewWaitOrder(o,od_id);
    }
    @Test
    public void addUrgentOrderDestinationTest(){
        List<Destination> dli = new ArrayList<>();
        Destination d1 = new Destination(20.9666026, 105.7785264, "133-135 To Hieu,Ha Cau, Ha Dong, Hn");
        Destination d2 = new Destination(20.9239655, 105.8339645, "Ca kho Vu Dai, Ngoc Hoi,Thanh Tri, Hn");
        Destination d3 = new Destination(21.0477467, 105.800241, "Nghia Do, Cau Giay, Hn");
        dli.add(d1);
        dli.add(d2);
        dli.add(d3);
        dao.addDestinations(dli,"1520910987984OD");
    }
    @Test
    public void getShipperAcceptedOrderTest(){
        GetShipperRes r = dao.getShipperAceptedOrder("1520911956238OD");
        System.out.println(r.getPhone()+" "+r.getName()+" "+r.getShp_id()+" "+r.getStar()+" "+r.getAvatar());
    }
}
