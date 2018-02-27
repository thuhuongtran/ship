package ship.admin;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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
    @Test
    public void decodeJwtTest(){
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwOTY4Njg1NzQ1MWFEbSIsImV4cCI6MTUyMDU3OTM5M30.tf9V618pVHPbNdPTnk6-oyCNGrHM3gxG6Z78AhgO_GfoMzs6Mg2LF-T6GHypT0gOZ4gXl2gYvItJ1ycJ6ym5xQ";
        Claims claims = Jwts.parser().setSigningKey("ThisIsASecret").parseClaimsJws(token).getBody();
        String body = claims.getSubject();
        System.out.println(body);
        assertEquals(true,body.contains("aDm"));
    }
}
