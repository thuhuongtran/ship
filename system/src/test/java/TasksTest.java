import com.vimensa.system.dao.Order;
import com.vimensa.system.model.Point;
import com.vimensa.system.service.FindingRoute;
import com.vimensa.system.service.OrderProcess;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

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
        sortOrdersByWaittingTime(oli);
        for(int i=0;i<oli.size();i++){
            System.out.println(oli.get(i).getWait_time());
        }
        assertEquals(89665, oli.get(10).getWait_time());

    }
    @Test
    public void compareTimestampTest(){
        long t1 = Calendar.getInstance().getTimeInMillis();
        System.out.println(t1); //1520304548106
        //1520304574003
        assertEquals(true,"1520304574003".compareTo("1520304548106"));
    }
    public void sortOrdersByWaittingTime(List<Order> oli){
        oli.sort(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return (int) (o1.getWait_time()-o2.getWait_time());
            }
        });
    }


    @Test
    public void getRouteTest() throws IOException {
        Point p = new Point("1", 21.0000281, 105.9072344);
        Point p1 = new Point("2", 21.0000281, 105.9072344);
        Point p2 = new Point("3", 20.9980975, 105.8752846);
        Point p3 = new Point("4", 21.0000281, 105.9072344);
        Point p4 = new Point("5", 20.9980975, 105.8752846);
        Point p5 = new Point("6", 21.0462132, 105.7927858);
        Point p6 = new Point("7", 21.0420076, 105.7901894);
        List<Point> ps = new ArrayList<>();
        ps.add(p1);
        ps.add(p4);
        ps.add(p5);
        ps.add(p3);
        ps.add(p2);
        ps.add(p6);
        String res = FindingRoute.getRoute(ps, p);
        System.out.println(res);
    }
    @Test
    public void removeListTest(){
        Point p = new Point("1", 21.0000281, 105.9072344);
        Point p1 = new Point("2", 21.0000281, 105.9072344);
        Point p2 = new Point("3", 20.9980975, 105.8752846);
        Point p3 = new Point("4", 21.0000281, 105.9072344);
        Point p4 = new Point("5", 20.9980975, 105.8752846);
        Point p5 = new Point("6", 21.0462132, 105.7927858);
        Point p6 = new Point("7", 21.0420076, 105.7901894);
        List<Point> ps = new ArrayList<>();
        ps.add(p1);
        ps.add(p4);
        ps.add(p5);
        ps.add(p3);
        ps.add(p2);
        ps.add(p6);
        Point newp = new Point("2", 21.0000281, 105.9072344);

        assertEquals(true,ps.contains(newp));
    }
}
