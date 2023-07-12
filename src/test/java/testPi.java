import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class testPi {

    @Test
    public void testPiEquals() {
        String pi = makeStringPi(4);

        assertEquals("3,1415", pi);

        pi = makeStringPi(0);
        assertEquals("3", pi);

        pi = makeStringPi(100);
        assertEquals("3,14159265358979323846264338327" +
                "95028841971693993751058209749445923" +
                "078164062862089986280348253421170679", pi);
    }

    @Test
    public void piException() {
        assertThrows(NegativeException.class, () -> Pi.computePi(-1));
    }

    public String makeStringPi(int n) {
        return String.format("%." + n + "f", Pi.computePi(n*2).subtract(new BigDecimal(subtractRounding(n))));
    }

    public double subtractRounding(int n) {
        return 1/Math.pow(10.0, n);
    }
}
