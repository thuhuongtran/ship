import com.vimensa.get_shipper.model.Order;
import com.vimensa.get_shipper.service.OrderProcess;
import org.junit.Test;

import java.io.IOException;

public class MainTest {
    @Test
    public void getDriverTest() throws IOException {
        Order order = new Order("O23", 21.0240197, 105.8787244, 21.0826459, 105.7722861);
        String driverID = OrderProcess.getDriver(order);
        System.out.println(driverID);
    }
}

