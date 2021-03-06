package ship.admin;

import com.vimensa.ship.admin.APIStart;
import com.vimensa.ship.admin.dao.Shipper;
import com.vimensa.ship.admin.data.DataProcess;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = APIStart.class)
public class ControllerTest {
    @Autowired
    private DataProcess dao;

    @Test
    public void enableRegisterShipperTest(){
        String name = "hi111";
        String mail = "hi111@mail.com";
        String phone = "09658752120";
        dao.enableNewRegisterShipper(phone, name, mail);
        Shipper shipper = dao.getShipperByPhone(phone);
        assertEquals(phone, shipper.getPhone());
    }
    @Test
    public void addIntoUserRole(){
        String phone = "09658752120";
        dao.addNewShipperInUserRole(dao.getShipperIDByPhone(phone), phone);
    }
    @Test
    public void getAllUnenabledShipper(){
        List<String> li = dao.getAllUnabledShippers();
        for(int i=0;i<li.size();i++){
            System.out.println(li.get(i));
        }
        assertEquals(16,li.size());
    }
    @Test
    public void getShipperIDTest(){
        String phone = "08965326532";
        String shp_id = dao.getShipperIDByPhone(phone);
        assertEquals("1520580745080shp",shp_id);
    }
}
