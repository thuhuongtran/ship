import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class TasksTest {

    @Test
    public void compareTimestampTest(){
        long t1 = Calendar.getInstance().getTimeInMillis();
        System.out.println(t1); //1520304548106
        //1520304574003
        assertEquals(true,"1520304574003".compareTo("1520304548106"));
    }


}
