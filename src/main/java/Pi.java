import java.math.*;
import java.util.Scanner;

// Using Gauss-Legendre algorithm
public class Pi {

    static final BigDecimal TWO = new BigDecimal(2);
    static final BigDecimal FOUR = new BigDecimal(4);

    public static BigDecimal computePi(int nAfterPoint) {
            if (nAfterPoint < 0) {
                throw new NegativeException("Negative n's after comma");
            }

            BigDecimal a = BigDecimal.ONE;
            BigDecimal b = BigDecimal.ONE.divide(sqrt(TWO, nAfterPoint), nAfterPoint, RoundingMode.HALF_UP);
            BigDecimal t = BigDecimal.valueOf(0.25);
            BigDecimal p = BigDecimal.ONE;
            BigDecimal temp;

            while (!a.equals(b)) {
                temp = a;
                a = a.add(b).divide(TWO, nAfterPoint, RoundingMode.HALF_UP);
                b = sqrt(temp.multiply(b), nAfterPoint);
                t = t.subtract(p.multiply(temp.subtract(a).multiply(temp.subtract(a))));
                p = TWO.multiply(p);
            }

            return a.add(b).multiply(a.add(b)).divide(FOUR.multiply(t), BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal sqrt(BigDecimal value,  int nAfterValue) {
        BigDecimal a = new BigDecimal("0");
        BigDecimal b = new BigDecimal(Math.sqrt(value.doubleValue()));

        while (!a.equals(b)) {
            a = b;
            b = value.divide(a, nAfterValue, BigDecimal.ROUND_HALF_UP);
            b = b.add(a);
            b = b.divide(TWO, nAfterValue, BigDecimal.ROUND_HALF_UP);
        }

        return b;
    }

    public static void main(String[] args) {
        System.out.print("Digits of PI: ");
        Scanner digits = new Scanner(System.in);
        int digitsNumber = digits.nextInt();
        System.out.printf("%." + digitsNumber + "f", computePi(digitsNumber*2).subtract(new BigDecimal(subtractRounding(digitsNumber))));
    }

    public static double subtractRounding(int n) {
        return 1/Math.pow(10.0, n);
    }

}