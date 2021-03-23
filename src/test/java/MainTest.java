import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import graph.Unit;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    public void testGettingFraction() {
        assertEquals("jane.doe@example.org / To Unit", Main.gettingFraction("jane.doe@example.org", "To Unit"));
        assertEquals(" /  * To Unit", Main.gettingFraction(" / ", "To Unit"));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Main.gettingFraction("/", "To Unit"));
        assertEquals("jane.doe@example.org *  / ", Main.gettingFraction("jane.doe@example.org", " / "));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Main.gettingFraction("jane.doe@example.org", "/"));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Main.gettingFraction(" / ", "/"));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Main.gettingFraction("/", " / "));
    }

    @Test
    public void testReadUnit() {
        assertEquals("400", Main.readUnit("jane.doe@example.org", "To Unit", new Unit(new ArrayList<>())));
        assertEquals("400", Main.readUnit("/convert", "To Unit", new Unit(new ArrayList<>())));
        assertEquals("400", Main.readUnit("", "To Unit", new Unit(new ArrayList<>())));
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> Main.readUnit("/", "To Unit", new Unit(new ArrayList<>())));
        assertEquals("400", Main.readUnit("jane.doe@example.org", "", new Unit(new ArrayList<>())));
        assertEquals("400", Main.readUnit("jane.doe@example.org", " / ", new Unit(new ArrayList<>())));
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> Main.readUnit("jane.doe@example.org", "/", new Unit(new ArrayList<>())));
        assertEquals("400", Main.readUnit("/convert", "", new Unit(new ArrayList<>())));
        assertEquals("1,000000000000000", Main.readUnit("/convert", " / ", new Unit(new ArrayList<>())));
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> Main.readUnit("/convert", "/", new Unit(new ArrayList<>())));
        assertEquals("404", Main.readUnit("", "", new Unit(new ArrayList<>())));
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> Main.readUnit("", "/", new Unit(new ArrayList<>())));
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> Main.readUnit("/", " / ", new Unit(new ArrayList<>())));
    }

    @Test
    public void testCalculation() {
        assertEquals("400", Main.calculation("Numerator", "Denominator", new Unit(new ArrayList<>())));
    }

    @Test
    public void testDoubleToString() {
        assertEquals("10,000000000000000", Main.doubleToString(10.0));
    }
}

