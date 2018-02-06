import com.vimensa.get_shipper.RunAPI;
import com.vimensa.get_shipper.dao.Shipper;
import com.vimensa.get_shipper.data.DataProcess;
import com.vimensa.get_shipper.model.Distance;
import com.vimensa.get_shipper.model.Driver;
import com.vimensa.get_shipper.model.Order;
import com.vimensa.get_shipper.service.OrderProcess;
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
        Order order = new Order("O23", 21.0240197, 105.8787244, 21.0826459, 105.7722861);
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
        Order order = new Order("O16", 21.1107264, 105.781135, 21.1066025, 105.8447063);

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
        dao.newOrderShipperSystem("01236598562","15296584561OD");
    }
}

