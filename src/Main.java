import java.util.Scanner;

public class Main {
    private static final String ENTER_MESSAGE = "Enter mathematical operation, " +
            "two operands and one operator: -, +, /, *.\n" +
            "To exit the program, type \"exit\".";
    private static final NumeralConverter CONVERTER = new NumeralConverter();
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.out.println(ENTER_MESSAGE);

        while (true) {
            String line = SCANNER.nextLine();
            if (line.compareToIgnoreCase("exit") == 0) {
                System.exit(0);
            }

            checkAndExecute(line);
        }
    }

    private static void checkAndExecute(String s) throws Exception {
        s = s.replaceAll(" ", "");

        String operand1 = "";
        String operand2 = "";
        char operator = '0';

        for (int i = 0; i < s.length(); i++) {
            if (isOperator(s.charAt(i))) {
                if (operator == '0') {
                    operator = s.charAt(i);
                    operand1 = s.substring(0, i);
                    operand2 = s.substring(i + 1);
                } else {
                    throw new Exception();
                }
            }
        }

        if (operand1.length() == 0 || operand2.length() == 0) {
            throw new Exception();
        }

        boolean isDigit = Character.isDigit(operand1.charAt(0));

        for (int i = 0; i < operand1.length(); i++) {
            if ((!isDigit && Character.isDigit(operand1.charAt(i))) ||
                    (isDigit && !Character.isDigit(operand1.charAt(i)))) {
                throw new Exception();
            }
        }

        for (int i = 0; i < operand2.length(); i++) {
            if ((!isDigit && Character.isDigit(operand2.charAt(i))) ||
                    (isDigit && !Character.isDigit(operand2.charAt(i)))) {
                throw new Exception();
            }
        }

        int operandInt1;
        int operandInt2;

        if (!isDigit) {
            operandInt1 = CONVERTER.getArabicFromRoman(operand1);
            operandInt2 = CONVERTER.getArabicFromRoman(operand2);
        } else {
            operandInt1 = Integer.parseInt(operand1);
            operandInt2 = Integer.parseInt(operand2);
        }

        if (operandInt1 < 1 || operandInt1 > 10 || operandInt2 < 1 || operandInt2 > 10 ||
                (!isDigit && operator == '-' && operandInt1 <= operandInt2)) {
            throw new Exception();
        }

        executeMathematicalOperation(operandInt1, operandInt2, operator, isDigit);
    }

    private static boolean isOperator(char c) {
        return (c == '-' || c == '+' || c == '/' || c == '*');
    }

    private static void executeMathematicalOperation(int operandInt1, int operandInt2, char operator, boolean isDigit) {
        int result = 0;

        if (operator == '-') {
            result = operandInt1 - operandInt2;
        } else if (operator == '+') {
            result = operandInt1 + operandInt2;
        } else if (operator == '/') {
            result = operandInt1 / operandInt2;
        } else if (operator == '*') {
            result = operandInt1 * operandInt2;
        }

        if (!isDigit) {
            System.out.println(CONVERTER.getRomanFromArabic(result));
        } else {
            System.out.println(result);
        }
    }
}
