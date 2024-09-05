import java.rmi.Naming;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            int port = 1099;
            Adder adder = (Adder) Naming.lookup("rmi://localhost:" + port + "/AdderService");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the first number: ");
            int num1 = scanner.nextInt();

            System.out.print("Enter the second number: ");
            int num2 = scanner.nextInt();

            int result = adder.add(num1, num2);
            System.out.println("Result: " + result);

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
