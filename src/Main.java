import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    public static boolean scanDouble(String input){
        for (int i = 0; i < input.length(); i++)
            if ((input.charAt(i) < '0' || input.charAt(i) > '9') && input.charAt(i) != '.')
                return false;
        return true;
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
    public static void Group(int num, String op){
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
    public static BigDecimal getInputNumber(String message, Scanner scanner){
        boolean ifInputIsValid = true;
        String input ="";
        while (ifInputIsValid) {
            System.out.print(message);
            input = scanner.next();
            ifInputIsValid = !scanDouble(input);
            if (ifInputIsValid) {
                System.out.println("You're fucking peace of shit");
            }
        }
        return BigDecimal.valueOf(Double.parseDouble(input));
    }
    public static void main(String[] args) {
        boolean finish = false;
        Scanner reader = new Scanner(System.in);
        BigDecimal num1 = getInputNumber("Enter first number: ", reader);
        while (!finish) {
            System.out.print("\nEnter an operator (+, -, *, /, ^, mod, log, f(finish), n(new), gr(groups): ");
            String op = reader.next();
            switch (op) {
                case "n" -> {
                    num1 = getInputNumber("Enter first number: ", reader);
                }
                case "f" -> {
                    finish = true;
                    reader.close();
                }
                case "gr" -> {
                    boolean check = true;
                    while (check) {
                        System.out.print("\nEnter add or mul: ");
                        op = reader.next();
                        if (op.equals("add") || op.equals("mul")) {
                            Group(num1.intValue(), op);
                            check = false;
                        } else {
                            System.out.println("Syntax.error");
                        }
                    }
                }
                case "+", "-", "*", "/", "^", "mod", "log" -> {
                    BigDecimal num2 = getInputNumber("Enter second number: ", reader);
                    BigDecimal result = Calculate(num1, num2, op);
                    System.out.print("\n" + Clear(num1.toString()) + " " + op + " " + Clear(num2.toString()) + " = " + Clear(result.toString()));
                    num1 = result;
                }
                default -> System.out.println("Syntax.Error");
            }
        }
    }
}