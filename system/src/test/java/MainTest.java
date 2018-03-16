import com.vimensa.system.RunAPI;
import com.vimensa.system.dao.Shipper;
import com.vimensa.system.data.DataProcess;
import com.vimensa.system.model.Edge;
import com.vimensa.system.model.Order;
import com.vimensa.system.service.FindingBestShipper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunAPI.class)
public class MainTest {
    @Autowired
    private DataProcess dao;
    @Test
    public void findingClosestShipperTest() throws IOException {
        Order o = dao.getEarliestDeliveryNeededUrgentOrder();
        List<Shipper> sli = dao.getAllAwakeShippers();
        Edge e = FindingBestShipper.getClosestShipper(o, sli);
        System.out.println(e.getOd_id()+" "+e.getShp_id()+" "+e.getDistance());
    }
    @Test
    public void getAllDriversDBTest(){
        List<Shipper> shps = dao.getAllAwakeShippers();
        for(int i=0; i<shps.size();i++){
            System.out.println(shps.get(i).getShp_id()+" "+shps.get(i).getLat()+" "+shps.get(i).getLog()+" "+shps.get(i).getStatus());
        }
        assertEquals("2", String.valueOf(shps.size()));
    }

    @Test
    public void newOrderShipperSystemTest(){
        dao.addNewOrderShipperSystem("test1","test1OD");
    }

    @Test
    public void getEarliestDeliveryNeededUrgentOrderIDTest(){
        Order o = dao.getEarliestDeliveryNeededUrgentOrder();
        System.out.println(o.getOd_id()+" "+o.getFrom_lat()+" "+o.getFrom_log()+" "+o.getWait_time());
        assertEquals("1520910987984OD",o.getOd_id());
    }

    @Test
    public void changeStatusInOrderSystemTest(){
        dao.changeStatusToWaitShipperDecisionOrderSystem("1520317605858OD");
    }
    @Test
    public void changeWaitOrderStatusToUrgentTest(){
        dao.changeWaitOrderStatusToUrgentOrderSystem();
    }

}

