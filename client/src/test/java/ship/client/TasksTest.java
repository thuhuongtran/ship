package ship.client;

import com.vimensa.ship.client.service.Tasks;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

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
}
