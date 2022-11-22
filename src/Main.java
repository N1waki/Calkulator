import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BigDecimal ans = BigDecimal.valueOf(0);
        Scanner reader = new Scanner(System.in);
        System.out.print("\nEnter first numbers: ");
        BigDecimal num1 = reader.nextBigDecimal();
        while (true) {
            System.out.print("\nEnter an operator (+, -, *, /, ^, mod, log, f(finish), n(new), gr(groups): ");
            String op = reader.next();
            switch (op) {
                case "n" -> {
                    System.out.print("\nEnter first numbers: ");
                    num1 = reader.nextBigDecimal();
                }
                case "f" -> System.exit(0);
                case "gr" -> {
                    System.out.print("\nEnter add or mul: ");
                    op = reader.next();
                    int dec = num1.subtract(BigDecimal.valueOf(1)).toString().length();
                    for (BigDecimal up = BigDecimal.valueOf(0); up.compareTo(num1) < 0; up = up.add(BigDecimal.valueOf(1))) {
                        for (BigDecimal left = BigDecimal.valueOf(0); left.compareTo(num1) < 0; left = left.add(BigDecimal.valueOf(1))) {
                            switch (op) {
                                case "add" -> System.out.format("%0" + dec + "d ", (ans = left.add(up)).remainder(num1).intValue());
                                case "mul" -> System.out.format("%0" + dec + "d ", (ans = left.multiply(up)).remainder(num1).intValue());
                                default -> System.exit(0);
                            }
                        }
                        System.out.print("\n");
                    }
                    System.out.print("\nEnter first numbers: ");
                    num1 = reader.nextBigDecimal();
                }
                default -> {
                    System.out.print("\nEnter second numbers: ");
                    BigDecimal num2 = reader.nextBigDecimal();
                    switch (op) {
                        case "+" -> ans = num1.add(num2);
                        case "-" -> ans = num1.subtract(num2);
                        case "*" -> ans = num1.multiply(num2);
                        case "/" -> ans = num1.divide(num2, 100, RoundingMode.HALF_UP);
                        case "^" -> ans = num1.pow(num2.intValue());
                        case "mod" -> ans = num1.remainder(num2);
                        case "log" -> {
                            ans = new BigDecimal(1);
                            ans = ans.divide(BigDecimal.valueOf(Math.log(num2.doubleValue()) / Math.log(num1.doubleValue())), 100, RoundingMode.HALF_UP);
                        }
                        default -> System.exit(0);
                    }
                    System.out.print("\n" + Clear(num1.toString()) + " " + op + " " + Clear(num2.toString()) + " = " + Clear(ans.toString()));
                    num1 = ans;
                }
            }
        }
    }
    public static String Clear(String noNulls) {
        while (((noNulls.endsWith("0")) |  (noNulls.endsWith("."))) & noNulls.contains("."))
        {
            noNulls = noNulls.substring(0, noNulls.length()-1);
        }
        return noNulls;
    }
    /**public static BigDecimal Calculation(BigDecimal num1, BigDecimal num2, String op){
     BigDecimal ans = BigDecimal.valueOf(0);
     Scanner reader = new Scanner(System.in);
     switch (op) {
     case "+" -> ans = num1.add(num2);
     case "-" -> ans = num1.subtract(num2);
     case "*" -> ans = num1.multiply(num2);
     case "/" -> ans = num1.divide(num2, 100, RoundingMode.HALF_UP);
     case "^" -> ans = num1.pow(num2.intValue());
     case "mod" -> ans = num1.remainder(num2);
     case "log" -> {
     ans = new BigDecimal(1);
     ans = ans.divide(BigDecimal.valueOf(Math.log(num2.doubleValue()) / Math.log(num1.doubleValue())), 100, RoundingMode.HALF_UP);
     }
     }
     return ans;
     }*/
}