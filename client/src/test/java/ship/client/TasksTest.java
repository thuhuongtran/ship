package ship.client;

import com.vimensa.ship.client.model.Edge;
import com.vimensa.ship.client.model.Point;
import com.vimensa.ship.client.service.FindingRoute;
import com.vimensa.ship.client.service.Tasks;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TasksTest {
    @Test
    public void parseJWTTest() throws UnsupportedEncodingException {
        String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMjM1NjIzMTIzMSIsImV4cCI6MTUxNzU1NzI2OX0.0kFS_Jfea2cCqymDOyK5Wm8cGUSdG2sE0vdbTxtuxsEmzPlucq0zGhkxUpXQf6vwKTC4nGdn39x7iQyFVVXMOQ\n";
        String decodeJwt = getDecodedJwt(jwt);
//        System.out.println(decodeJwt);
//        String phone = decodeJwt.substring(decodeJwt.indexOf("sub") + 6, decodeJwt.indexOf(",")-1);
        System.out.println(decodeJwt);
    }

    public String getDecodedJwt(String jwt)
    {
        String result = "";

        String[] parts = jwt.split("[.]");
        try
        {
            int index = 0;
            for(String part: parts)
            {
                if (index >= 2)
                    break;

                index++;
                byte[] partAsBytes = part.getBytes("UTF-8");
                String decodedPart = new String(java.util.Base64.getUrlDecoder().decode(partAsBytes), "UTF-8");

                result += decodedPart;
            }
        }
        catch(Exception e)
        {
            throw new RuntimeException("Couldn't decode jwt", e);
        }
        result = result.substring(result.indexOf("sub") + 6, result.indexOf(",") - 1);
        return result;
    }
    @Test
    public void getFeeTest(){
        double dis1 = Tasks.getFee(15.2);
        assertEquals("55000",(int)dis1);
    }
//    @Test
//    public void getDriverTest() throws IOException {
//        UrgentOrderRequest order = new UrgentOrderRequest("09653562321",20.9857814,105.8327653,21.0253647,105.8332973);
//        OrderResponse shipper = Tasks.getDriver(order,"orderID","eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMjM1Njk4NzUiLCJleHAiOjE1MTgxNTc4MTN9.14m06D9RUZmfllI0iIFbqTQwNTYwwnNai-wy1-ngHOCbTSVqeuNlEc8GuPCksPTaoDUvIP4iogP5OEPXHUkHFQ");
//
//        assertEquals("09653265322",shipper.getShipper_phone());
//    }

    @Test
    public void getGglDistanceTest() throws IOException, InterruptedException {
        Point p1 = new Point("2", 21.0000281, 105.9072344);
        Point p2 = new Point("3", 20.9980975, 105.8752846);
        Point p5 = new Point("6", 21.0462132, 105.7927858);
        Point p6 = new Point("7", 21.0420076, 105.7901894);
        List<Point> ps = new ArrayList<>();
        ps.add(p1);
        ps.add(p5);
//        ps.add(p2);
        ps.add(p6);
        List<Edge> li = FindingRoute.getGglDistances(ps, p2);
        for(int i=0;i<li.size();i++){
            System.out.println(li.get(i).getFrom_id()+" "+li.get(i).getTo_id()+" "+li.get(i).getDist());
        }
        assertEquals(3,li.size());
    }
    @Test
    public void getSmallestEdgeTest() throws IOException, InterruptedException {
        Point p1 = new Point("2", 21.0000281, 105.9072344);
        Point p2 = new Point("3", 20.9980975, 105.8752846);
        Point p5 = new Point("6", 21.0462132, 105.7927858);
        Point p6 = new Point("7", 21.0420076, 105.7901894);
        List<Point> ps = new ArrayList<>();
        ps.add(p1);
        ps.add(p5);
//        ps.add(p2);
        ps.add(p6);
        List<Edge> li = FindingRoute.getGglDistances(ps, p2);
        for(int i=0;i<li.size();i++){
            System.out.println(li.get(i).getFrom_id()+" "+li.get(i).getTo_id()+" "+li.get(i).getDist());
        }
        System.out.println(FindingRoute.getSmallestEdge(li).getDist());
    }
    @Test
    public void getBestRouteTest() throws IOException, InterruptedException {
        Point p = new Point("11", 20.9666026, 105.7785264);
        Point p1 = new Point("1", 21.0000281, 105.9072344);
        Point p2 = new Point("2", 21.0253647, 105.8752846);
        Point p3 = new Point("3", 20.9980975, 105.8332973);
        Point p4 = new Point("4", 20.979500, 105.850780);
        Point p5 = new Point("5", 21.0462132, 105.7927858);
        Point p6 = new Point("6", 21.0420076, 105.7901894);
        Point p7 = new Point("7", 20.992693, 105.8496953);
        Point p8 = new Point("8", 21.0287234, 105.8496317);
        Point p9 = new Point("9", 20.9710905, 105.7485069);
        Point p10 = new Point("10", 21.0187822, 105.809644);

        List<Point> ps = new ArrayList<>();
        ps.add(p1);
        ps.add(p2);
        ps.add(p3);
        ps.add(p4);
        ps.add(p5);
        ps.add(p6);
        ps.add(p7);
        ps.add(p8);
        ps.add(p9);
        ps.add(p10);
        String r = FindingRoute.getBestRoute(ps, p);
        System.out.println(r);
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
//        Point newp = new Point("2", 21.0000281, 105.9072344);
        Point np = ps.get(Integer.parseInt(p2.getId())-1);
        ps.remove(np);
        assertEquals(5,ps.size());
    }
}
