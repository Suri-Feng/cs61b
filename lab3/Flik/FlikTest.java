

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FlikTest {

    @Test
    public void testFlik(){
        int a = 128;
        int b = 128;
        int c = 500;
        boolean actual = Flik.isSameNumber(a, b);
        boolean actual2 = Flik.isSameNumber(a, c);
        assertTrue(actual);
        assertFalse(actual2);
    }


}
