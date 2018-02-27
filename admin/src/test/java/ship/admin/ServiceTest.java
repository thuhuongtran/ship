package ship.admin;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServiceTest {
    @Test
    public void jwtInsertCharactersTest(){
        String str = "hala.jifa";
        int ind = str.indexOf(".");
        str = new StringBuilder(str).insert(ind,"aDm").toString();
        assertEquals("halaaDm.jifa", str);
    }
}
