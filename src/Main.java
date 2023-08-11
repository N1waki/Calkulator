import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    static class InvalidInputException extends Exception {

    }
    public static BigDecimal scanDouble(String input) throws InvalidInputException {
        for (int i = 0; i < input.length(); i++) {
            if ((input.charAt(i) < '0' || input.charAt(i) > '9') && input.charAt(i) != '.') {
                throw new InvalidInputException();
            }
        }
        return BigDecimal.valueOf(Double.parseDouble(input));
    }
        public static BigDecimal Calculate(BigDecimal num1, BigDecimal num2, String op){
        BigDecimal ans = BigDecimal.valueOf(0);
        switch (op) {
            case "+" -> ans = num1.add(num2);
            case "-" -> ans = num1.subtract(num2);
            case "*" -> ans = num1.multiply(num2);
            case "/" -> ans = num1.divide(num2, 100, RoundingMode.HALF_UP);
            case "^" -> ans = num1.pow(num2.intValue());
            case "mod" -> ans = num1.remainder(num2);
            case "log" -> ans = ans.divide(BigDecimal.valueOf(Math.log(num2.doubleValue()) / Math.log(num1.doubleValue())), 100, RoundingMode.HALF_UP);
        }
        return ans;
    }
    public static void Group(int num){
        System.out.print("\nEnter add or mul: ");
        String op = new Scanner(System.in).next();
        int dec = String.valueOf(num - 1).length();
        for (int up = 0; up < num; up++) {
            for (int left = 0; left < num; left++) {
                switch (op) {
                    case "add" -> System.out.format("%0" + dec + "d ", (left + up) % num);
                    case "mul" -> System.out.format("%0" + dec + "d ", (left * up) % num);
                }
            }
            System.out.print("\n");
        }
    }
    public static String Clear(String noNulls) {
        while (((noNulls.endsWith("0")) |  (noNulls.endsWith("."))) & noNulls.contains("."))
        {
            noNulls = noNulls.substring(0, noNulls.length()-1);
        }
        return noNulls;
    }
    public static void main(String[] args) throws InvalidInputException {
        Scanner reader = new Scanner(System.in);
        boolean ifInputIsInvalid = true;
        System.out.print("\nEnter first numbers: ");
        String input = reader.next();
        while (ifInputIsInvalid) {
            try {
                scanDouble(input);
                ifInputIsInvalid = false;
            } catch (InvalidInputException e) {
                System.out.println("Input Error");
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        BigDecimal num1 = scanDouble(input);
        while (true) {
            System.out.print("\nEnter an operator (+, -, *, /, ^, mod, log, f(finish), n(new), gr(groups): ");
            String op = reader.next();
            switch (op) {
                case "n" -> {
                    System.out.print("\nEnter first numbers: ");
                    input = reader.next();
                    num1 = scanDouble(input);
                }
                case "f" -> System.exit(0);
                case "gr" -> {
                    Group(num1.intValue());
                    System.out.print("\nEnter first numbers: ");
                    input = reader.next();
                    num1 = scanDouble(input);
                }
                case "+", "-", "*", "/", "^", "mod", "log" -> {
                        System.out.print("\nEnter second numbers: ");
                        input = reader.next();
                        BigDecimal num2 = scanDouble(input);
                        BigDecimal result = Calculate(num1, num2, op);
                        System.out.print("\n" + Clear(num1.toString()) + " " + op + " " + Clear(num2.toString()) + " = " + Clear(result.toString()));
                        num1 = result;
                }
                default -> System.out.println("Syntax.Error");
            }
        }
    }
}