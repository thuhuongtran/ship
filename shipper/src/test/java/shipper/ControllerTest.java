package shipper;

import com.vimensa.ship.shipper.APIStart;
import com.vimensa.ship.shipper.dao.OrderSystem;
import com.vimensa.ship.shipper.data.DataProcess;
import com.vimensa.ship.shipper.response.GetOrder;
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

    @Test
    public void countOrderShipperSystemTest(){
        int count = dao.countWaitAcceptingOrderShipperSystem("0123659856");
        System.out.println(count);
    }
    @Test
    public void getDetailOrderSystem(){
        String orderID = "1518079433653OD";
        GetOrder ord = dao.getDetailOrderSystem(orderID);
        assertEquals("0965325685",ord.getClient_phone());
    }
    @Test
    public void shipperLoginLogTest(){
        dao.shipperLoginLog("01296538687");
    }
    @Test
    public void changeStatusToAcceptedInOrderShipper(){
        String order_id = "test1OD";
        dao.changeStatusInOrderShipperToAccepted(order_id);
    }
    @Test
    public void changeShipperStatusToOnWayShipperSystem(){
        String phone = "01232323232";
        dao.changeShipperStatusToOnWayInShipperSystem(phone);
    }
    @Test
    public void getOrderSystemByIDTest(){
        String orderID = "1520324183829OD";
        OrderSystem o = dao.getOrderSystemByOrderID(orderID);
        assertEquals(orderID,o.getOrder_id());
    }
    @Test
    public void addNewOrderLogTest(){
        OrderSystem o = dao.getOrderSystemByOrderID("1520324183829OD");
        dao.addNewOrderLog(o,"01232323232");
    }
    @Test
    public void registerTest(){
        String phone = "08965326532";
        dao.registerNewShipper(phone);

    }
}

