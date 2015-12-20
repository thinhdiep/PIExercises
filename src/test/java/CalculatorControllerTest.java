import junit.framework.Assert;
import junit.framework.TestSuite;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by blacktiger on 12/20/2015.
 */
public class CalculatorControllerTest {


    @Test
    public void testCompareValue() throws Exception {
        CalculatorController controller = new CalculatorController();

        assertEquals(true,controller.compareValue(2.122, 2.1234, 0.01));
        assertEquals(true,controller.compareValue(3.122142, 3.122566, 0.001));
        assertEquals(false,controller.compareValue(3.122142213, 3.122132566, 0.000001));
    }

    @Test
    public void testCalculate() throws Exception {

    }

    @Test
    public void testCalculate1() throws Exception {

    }

    public static void main(String []args) {
        junit.textui.TestRunner.run(new TestSuite(CalculatorController.class));
    }
}