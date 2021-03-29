import static org.junit.jupiter.api.Assertions.assertEquals;

import graph.Node;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class CalculateTest {
    @Test
    public void testConstructor() {
        Calculate actualCalculate = new Calculate("Numerator", "Denominator", new HashMap<String, Node>(1));
        assertEquals("200", actualCalculate.getAnswer());
        assertEquals(1, actualCalculate.getNUMERATOR_ELEMENTS());
        assertEquals(1.0, actualCalculate.getK());
        assertEquals(1, actualCalculate.getDENOMINATOR_ELEMENTS());
    }

    @Test
    public void testGetNEMERATOR_ELEMENTS() {
        assertEquals(1, (new Calculate("Numerator", "Denominator", new HashMap<String, Node>(1))).getNUMERATOR_ELEMENTS());
    }

    @Test
    public void testGetDENOMINATOR_ELEMENTS() {
        assertEquals(1,
                (new Calculate("Numerator", "Denominator", new HashMap<String, Node>(1))).getDENOMINATOR_ELEMENTS());
    }

    @Test
    public void testRun() {
        Calculate calculate = new Calculate("Numerator", "Denominator", new HashMap<String, Node>(1));
        calculate.run();
        assertEquals("400", calculate.getAnswer());
    }

    @Test
    public void testRun2() {
        HashMap<String, Node> stringNodeMap = new HashMap<String, Node>(1);
        stringNodeMap.put("400", new Node("Name"));
        Calculate calculate = new Calculate("400", "Denominator", stringNodeMap);
        calculate.run();
        assertEquals("400", calculate.getAnswer());
    }

    @Test
    public void testRun3() {
        HashMap<String, Node> stringNodeMap = new HashMap<String, Node>(1);
        stringNodeMap.put("400", new Node("Name"));
        Calculate calculate = new Calculate("400", "400", stringNodeMap);
        calculate.run();
        assertEquals("200", calculate.getAnswer());
        assertEquals(1.0, calculate.getK());
    }
}

