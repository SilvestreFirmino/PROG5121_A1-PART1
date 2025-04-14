package prog5121_a1.part1;

import java.util.Scanner;

/**
 *
 * @author ST10453122 Silvestre Firmino
 */
public class PROG5121_A1PART1 {

    public static void main(String[] args) {
        VerifyUser registeredUser = registerUser();
        boolean loginSuccess = loginUser(registeredUser);
        returnLoginStatus(loginSuccess, registeredUser);
    }

    public static VerifyUser registerUser() {
        Scanner check = new Scanner(System.in);
        VerifyUser tempUser = new VerifyUser(); // Fixed constructor call

        String username = "";
        String password = "";
        String cellphoneNumber = "";

        System.out.println("************ User Registration *************");

        System.out.print("Enter your First name:>> ");
        String firstName = check.nextLine();
        tempUser.setFirstName(firstName);

        System.out.print("Enter your Last name:>> ");
        String lastName = check.nextLine();
        tempUser.setLastName(lastName);

        // Username
        while (true) {
            System.out.print("Enter your Username (must contain _ and be no more than 5 characters):>> ");
            username = check.nextLine();
            if (tempUser.checkUserName(username)) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Username is not correctly formatted, please ensure it contains an underscore and is no more than five characters.");
            }
        }

        // Password
        while (true) {
            System.out.print("Enter your Password (at least 8 characters, one uppercase, one number, one special character):>> ");
            password = check.nextLine();
            if (tempUser.checkPasswordComplexity(password)) {
                System.out.println("Password successfully captured.");
                break;
            } else {
                System.out.println("Password is not correctly formatted. Try again.");
            }
        }

        // Cellphone Number
        while (true) {
            System.out.print("Enter your South African cellphone number (+27xxxxxxxxx):>> ");
            cellphoneNumber = check.nextLine();
            if (tempUser.checkCellPhoneNumber(cellphoneNumber)) {
                System.out.println("Cell phone number successfully added.");
                break;
            } else {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            }
        }

        System.out.println("********************************************");

        return new VerifyUser(username, password, cellphoneNumber, firstName, lastName);
    }

    public static boolean loginUser(VerifyUser registeredUser) {
        Scanner check = new Scanner(System.in);
        int attempts = 0;

        System.out.println("\n*************** User Login ***************");

        while (attempts < 3) {
            System.out.print("Enter your Username:>> ");
            String enteredUsername = check.nextLine();

            System.out.print("Enter your Password:>> ");
            String enteredPassword = check.nextLine();

            System.out.print("Enter your Cellphone Number:>> ");
            String enteredCell = check.nextLine();

            if (registeredUser.authenticateUser(enteredUsername, enteredPassword, enteredCell)) {
                return true;
            } else {
                System.out.println("Incorrect details. Attempts left:>> " + (2 - attempts));
                attempts++;
            }
        }

        System.out.println("Maximum login attempts reached.");
        return false;
    }

    public static String returnLoginStatus(boolean loginSuccess, VerifyUser user) {
        System.out.println("********************************************");
        if (loginSuccess) {
            System.out.println("Login successful!");
            System.out.println("Welcome " + user.getFirstName() + " " + user.getLastName() + ", it is great to see you again!");
            return "Login successful!";
        } else {
            System.out.println("Login failed!");
            System.err.println("Username or password incorrect, please try again.");
            return "Login failed.";
        }
    }
}
