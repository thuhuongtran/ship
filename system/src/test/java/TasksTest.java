import com.vimensa.system.dao.Order;
import com.vimensa.system.service.Tasks;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TasksTest {
    @Test
    public void sortOrdersByWaitingTimeTest(){
        Order o1 = new Order(10);
        Order o2 = new Order(98);
        Order o3 = new Order(87);
        Order o4 = new Order(36);
        Order o5 = new Order(55);
        Order o6 = new Order(265);
        Order o7 = new Order(89665);
        Order o8 = new Order(3);
        Order o9 = new Order(21);
        Order o10 = new Order(895);
        Order o11 = new Order(56);

        List<Order> oli = new ArrayList<>();
        oli.add(o1);
        oli.add(o2);
        oli.add(o3);
        oli.add(o4);
        oli.add(o5);
        oli.add(o6);
        oli.add(o7);
        oli.add(o8);
        oli.add(o9);
        oli.add(o10);
        oli.add(o11);
        Tasks t = new Tasks();
        t.sortOrdersByWaittingTime(oli);
        for(int i=0;i<oli.size();i++){
            System.out.println(oli.get(i).getWait_time());
        }
        assertEquals(89665, oli.get(10).getWait_time());

    }
}
