package shipper;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceTest {
    @Test
    public void getTimeTest(){
        System.out.println( new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date()));
    }
}
