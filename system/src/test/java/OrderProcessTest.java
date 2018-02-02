import com.vimensa.get_shipper.data.DriverData;
import com.vimensa.get_shipper.model.Distance;
import com.vimensa.get_shipper.model.Driver;
import com.vimensa.get_shipper.model.Order;
import com.vimensa.get_shipper.service.OrderProcess;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderProcessTest {
    @Test
    public void getGglDistanceTest(){
        Order order = new Order("O16", 21.1107264, 105.781135, 21.1066025, 105.8447063);
        DriverData.getDrivers();
        List<Driver> drivers = OrderProcess.getNearDrivers(DriverData.drivers,order);
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
        assertEquals(5, diss.size());
    }
}
