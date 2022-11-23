import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
                    Group(num1);
                    System.out.print("\nEnter first numbers: ");
                    num1 = reader.nextBigDecimal();
                }
                default -> {
                    System.out.print("\nEnter second numbers: ");
                    BigDecimal num2 = reader.nextBigDecimal();
                    BigDecimal result = Calculate(num1, num2, op);
                    System.out.print("\n" + Clear(num1.toString()) + " " + op + " " + Clear(num2.toString()) + " = " + Clear(result.toString()));
                    num1 = result;
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
    public static void Group(BigDecimal num){
        System.out.print("\nEnter add or mul: ");
        String op = new Scanner(System.in).next();
        int numint = num.intValue();
        int dec = String.valueOf(numint - 1).length();
        for (int up = 0; up < numint; up++) {
            for (int left = 0; left < numint; left++) {
                switch (op) {
                    case "add" -> System.out.format("%0" + dec + "d ", (left + up) % numint);
                    case "mul" -> System.out.format("%0" + dec + "d ", (left * up) % numint);
                    default -> System.exit(0);
                }
            }
            System.out.print("\n");
        }
    }
    public static BigDecimal Calculate(BigDecimal num1, BigDecimal num2, String op){
        BigDecimal ans = BigDecimal.valueOf(1);
        switch (op) {
            case "+" -> ans = num1.add(num2);
            case "-" -> ans = num1.subtract(num2);
            case "*" -> ans = num1.multiply(num2);
            case "/" -> ans = num1.divide(num2, 100, RoundingMode.HALF_UP);
            case "^" -> ans = num1.pow(num2.intValue());
            case "mod" -> ans = num1.remainder(num2);
            case "log" -> ans = ans.divide(BigDecimal.valueOf(Math.log(num2.doubleValue()) / Math.log(num1.doubleValue())), 100, RoundingMode.HALF_UP);
            default -> System.exit(0);
        }
        return ans;
     }

}