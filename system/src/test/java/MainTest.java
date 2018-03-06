import com.vimensa.system.RunAPI;
import com.vimensa.system.dao.Order;
import com.vimensa.system.dao.Shipper;
import com.vimensa.system.data.DataProcess;
import com.vimensa.system.model.Distance;
import com.vimensa.system.model.Driver;
import com.vimensa.system.service.OrderProcess;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunAPI.class)
public class MainTest {
    @Autowired
    private DataProcess dao;
    @Test
    public void getDriverTest() throws IOException {
        Order order = new Order("O23", 21.0240197, 105.8787244, 21.0826459, 105.7722861,0);
        String driverID = OrderProcess.getDriver(order, OrderProcess.toDrivers(dao.findAllDrivers()));
        System.out.println(driverID);
    }
    @Test
    public void getAllDriversDBTest(){
        List<Shipper> drivers = dao.findAllDrivers();
        for(int i=0; i<drivers.size();i++){
            System.out.println(drivers.get(i).getPhone()+" "+drivers.get(i).getLat()+" "+drivers.get(i).getLog()+" "+drivers.get(i).getStatus());
        }
        assertEquals("8", String.valueOf(drivers.size()));
    }
    @Test
    public void getGglDistanceTest(){
        Order order = new Order("O16", 21.1107264, 105.781135, 21.1066025, 105.8447063,0);

        List<Driver> drivers = OrderProcess.getNearDrivers(OrderProcess.toDrivers(dao.findAllDrivers()),order);
        List<Distance> diss = new ArrayList();
        try {
            diss = OrderProcess.getRealDistances(drivers, order);
            for(Distance dis : diss){
                System.out.println(dis.getId()+" "+dis.getDistance());
            }
        } catch (IOException e) {
            System.out.println("io exception......");
            e.printStackTrace();
        }
        assertEquals(3, diss.size());
    }
    @Test
    public void toDriversTest(){
        List<Driver> drivers = OrderProcess.toDrivers(dao.findAllDrivers());
        assertEquals(4,drivers.size());
    }
    @Test
    public void getObjectByElementInListTest(){
        List<Driver> drivers = OrderProcess.toDrivers(dao.findAllDrivers());
        Driver driver = new Driver();
        driver.setId("01232323232");
        int ind = drivers.indexOf(driver);
        System.out.println(ind);
        Driver driv = drivers.get(ind);
        System.out.println(driv.getLatitude()+ " "+driv.getLongitude());
        assertEquals("21.0000281", String.valueOf(driv.getLatitude()));
    }
    @Test
    public void newOrderShipperSystemTest(){
        dao.addNewOrderShipperSystem("test1","test1OD");
    }
    @Test
    public void getAllUrgentOrders(){
        List<com.vimensa.system.dao.Order> ords = dao.getAllUrgentOrders();
        System.out.println(ords.get(0).getOrder_id()+" "+ords.get(0).getFrom_lat()+" "+ords.get(0).getFrom_log()+" "+ords.get(0).getTo_lat()+" "+ords.get(0).getTo_log()+" "+ords.get(0).getWait_time());
        assertEquals(2,ords.size());
    }
    @Test
    public void getEarliestDeliveryNeededUrgentOrder(){
        Order o = dao.getEarliestDeliveryNeededUrgentOrder();
        assertEquals("1520307682080", String.valueOf(o.getWait_time()));
    }
    @Test
    public void deleteHandledOrder(){
        String order_id = "1520307682080OD";
        dao.deleteHandledOrderSystem(order_id);
        Order o = dao.getEarliestDeliveryNeededUrgentOrder();
        assertEquals(false,o.getOrder_id().contains(order_id));
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

