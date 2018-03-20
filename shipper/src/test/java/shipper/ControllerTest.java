package shipper;

import com.vimensa.ship.shipper.APIStart;
import com.vimensa.ship.shipper.data.DataProcess;
import com.vimensa.ship.shipper.model.Destination;
import com.vimensa.ship.shipper.model.Order;
import com.vimensa.ship.shipper.response.GetOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = APIStart.class)
public class ControllerTest {
    @Autowired
    private DataProcess dao;

    @Test
    public void registerTest(){
        String phone = "09658752120";
        dao.registerNewShipper(phone);

    }
    @Test
    public void countOrderShipperSystemTest(){
        int count = dao.countWaitAcceptingOrderShipperSystem("1521193393807shp");
        System.out.println(count);
    }
    @Test
    public void shipperLoginLogTest(){
        dao.shipperLoginLog("01296538687");
    }
    @Test
    public void getDetailOrderSystemTest(){
        String shp_id = "1521193393807shp";
        Order ord = dao.shipperGetDetailOrderByShipperID(shp_id);
        assertEquals("06935623154",ord.getCustm_phone());
    }
    @Test
    public void getDestinationsByOrderIDTest(){
        String shp_id = "1521193393807shp";
        Order ord = dao.shipperGetDetailOrderByShipperID(shp_id);
        List<Destination> li = dao.getDestinationsByODID(ord.getOd_id());
        assertEquals(3,li.size());
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
    /*
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
    */
}

