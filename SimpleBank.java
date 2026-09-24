import java.util.Scanner;

public class SimpleBank {
    public static void clearTerminal() {
        try {
            new ProcessBuilder("cmd", "/c", "cls")
                    .inheritIO()
                    .start()
                    .waitFor();
        } catch (Exception e) {
            System.out.println("\n".repeat(30));
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Create an account");

        System.out.print("Username: ");
        String savedUser = input.nextLine();

        System.out.print("PIN: ");
        String savedPin = input.nextLine();

        clearTerminal();

        int attempts = 0;
        boolean loggedIn = false;

        while (attempts < 3) {
            System.out.println("Enter your account Details.");

            System.out.print("Username: ");
            String enteredUser = input.nextLine();

            System.out.print("Pin: ");
            String enteredPin = input.nextLine();

            if (enteredUser.equals(savedUser) && enteredPin.equals(savedPin)) {
                System.out.println("Access granted!");
                loggedIn = true;
                break;
            }

            attempts++;
            System.out.println("Invalid username or PIN!");
            System.out.println("Attemps remaining: " + (3 - attempts));
        }

        if (!loggedIn) {
            System.out.println("Too many attempts. Account locked!");
            input.close();
            return;

        }
        double balance = 0.0;
        boolean running = true;

        clearTerminal();

        while (running) {
            System.out.println("Access granted!\n");
            System.out.println("Show menu: ");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choices = input.nextLine().trim();

            if (choices.equals("1")) {
                System.out.printf("Your balance is: $%.2f%n", balance);

            } else if (choices.equals("2")) {
                System.out.print("Enter amount to deposit: ");
                String depositText = input.nextLine().trim();

                try {
                    double defAmount = Double.parseDouble(depositText);

                    if (defAmount > 0) {
                        balance += defAmount;
                        System.out.println("Deposit successful.");
                    } else {
                        System.out.println("Invalid amount.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount.");
                }

            } else if (choices.equals("3")) {
                System.out.print("Withdraw amount: ");
                String withdrawText = input.nextLine().trim();

                try {
                    double withAmount = Double.parseDouble(withdrawText);
                    if (withAmount > 0 && withAmount <= balance) {
                        balance -= withAmount;
                        System.out.println("Withdraw successful.");
                    } else {
                        System.out.println("Invalid amount or insufficient balance.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount.");
                }
            } else if (choices.equals("4")) {
                running = false;
                System.out.println("Goodbye!");

            } else {
                System.out.println("Invalid option. Please try again.");
            }

            if (running) {
                System.out.println();
                System.out.println("Press Enter to return to the menu...");
                input.nextLine();
            }
        }

        input.close();
    }
}
