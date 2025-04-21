import java.util.Scanner;

public class EnhancedCalculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n----- Enhanced Calculator -----");
            System.out.println("1. Basic Arithmetic");
            System.out.println("2. Scientific Calculations");
            System.out.println("3. Unit Conversions");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    basicArithmetic(sc);
                    break;
                case 2:
                    scientificCalc(sc);
                    break;
                case 3:
                    unitConversion(sc);
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }

    // Basic Arithmetic Operations
    public static void basicArithmetic(Scanner sc) {
        System.out.print("Enter first number: ");
        double a = sc.nextDouble();
        System.out.print("Enter second number: ");
        double b = sc.nextDouble();

        System.out.println("Choose operation: + - * /");
        char op = sc.next().charAt(0);

        switch (op) {
            case '+':
                System.out.println("Result: " + (a + b));
                break;
            case '-':
                System.out.println("Result: " + (a - b));
                break;
            case '*':
                System.out.println("Result: " + (a * b));
                break;
            case '/':
                if (b != 0)
                    System.out.println("Result: " + (a / b));
                else
                    System.out.println("Error: Division by zero");
                break;
            default:
                System.out.println("Invalid operator.");
        }
    }

    // Scientific Calculations
    public static void scientificCalc(Scanner sc) {
        System.out.println("1. Square Root");
        System.out.println("2. Exponentiation (a^b)");
        System.out.print("Choose operation: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter number: ");
                double num = sc.nextDouble();
                if (num >= 0)
                    System.out.println("Square Root: " + Math.sqrt(num));
                else
                    System.out.println("Error: Negative number");
                break;
            case 2:
                System.out.print("Enter base: ");
                double base = sc.nextDouble();
                System.out.print("Enter exponent: ");
                double exp = sc.nextDouble();
                System.out.println("Result: " + Math.pow(base, exp));
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Unit Conversions
    public static void unitConversion(Scanner sc) {
        System.out.println("1. Temperature (Celsius <-> Fahrenheit)");
        System.out.println("2. Currency (INR <-> USD)");
        System.out.print("Choose conversion: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("1. Celsius to Fahrenheit");
                System.out.println("2. Fahrenheit to Celsius");
                int tempChoice = sc.nextInt();
                System.out.print("Enter temperature: ");
                double temp = sc.nextDouble();
                if (tempChoice == 1)
                    System.out.println("Result: " + (temp * 9 / 5 + 32) + " °F");
                else if (tempChoice == 2)
                    System.out.println("Result: " + ((temp - 32) * 5 / 9) + " °C");
                else
                    System.out.println("Invalid option.");
                break;
            case 2:
                System.out.println("1. INR to USD");
                System.out.println("2. USD to INR");
                int currChoice = sc.nextInt();
                System.out.print("Enter amount: ");
                double amount = sc.nextDouble();
                double rate = 83.0; // Assume 1 USD = 83 INR
                if (currChoice == 1)
                    System.out.println("Result: $" + (amount / rate));
                else if (currChoice == 2)
                    System.out.println("Result: ₹" + (amount * rate));
                else
                    System.out.println("Invalid option.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}
