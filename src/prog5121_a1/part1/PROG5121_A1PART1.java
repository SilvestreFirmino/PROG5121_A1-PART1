package prog5121_a1.part1;
/**
 *
 * @author ST10453122 Silvestre Firmino
 */
import java.util.Scanner;

public class PROG5121_A1PART1 {

    public static void main(String[] args) {
        VerifyUser registeredUser = registerUser();
        boolean loginSuccess = loginUser(registeredUser);

        returnLoginStatus(loginSuccess, registeredUser);
    }

    public static VerifyUser registerUser() {
        Scanner check = new Scanner(System.in);
        String firstName = "";
        String lastName = "";
        String username = "";
        String password = "";
        String cellphoneNumber = "";

        VerifyUser tempUser = new VerifyUser("", "", "","","");

        System.out.println("************ User Registration *************");

        System.out.println("Enter your First name:>> ");
        firstName = check.nextLine();
        
        System.out.println("Enter your Last name:>> ");
        lastName = check.nextLine();
        
        // Username
        //The whlie loop ,loops until the conditions are met, through the checkUserName 
        while (true) {
            System.out.println("Enter your Username (must contain _ and be no more than 5 characters):>> ");
            username = check.nextLine();
            //validate the user input if the username meets the conditions of the correct username format
            if (tempUser.checkUserName(username)){ 
               System.out.println("Username successfully captured.");
               //ends the loop when true
               break;
        } else {
            System.out.println("Username is not correctly formatted, please ensure it contains an underscore and is no more than five characters.");
        }
        }

        // Password

        while (true) {
            System.out.println("Enter your Password (at least 8 characters, one uppercase, one number, one special character):>> ");
            password = check.nextLine();
            if (tempUser.checkPasswordComplexity(password)){
                    System.out.println("Password successfully captured.");
                    break;
                
            } else {
                System.out.println("Password is not correctly formatted. Try again.");
            }
            }
            
        // Cellphone
      
        while (true) {
            System.out.println("Enter your South African cellphone number (+27xxxxxxxxx):>> ");
            cellphoneNumber = check.nextLine();
            if (tempUser.checkCellPhoneNumber(cellphoneNumber)){
                 System.out.println("Cell phone number successfully added.");
                 break;
            } else {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            
            }

    }
        System.out.println("********************************************");
        //storing the input values into these variables
        return new VerifyUser(username, password, cellphoneNumber, firstName, lastName);
 }


    public static boolean loginUser(VerifyUser registeredUser) {
        Scanner check = new Scanner(System.in);
        //providing attempts for 3 chances to login into your account
        int attempts = 0;

        System.out.println("\n*************** User Login ***************");

        while (attempts < 3) {
            System.out.println("Enter your Username:>> ");
            String enteredUsername = check.nextLine();

            System.out.println("Enter your Password:>> ");
            String enteredPassword = check.nextLine();

            System.out.println("Enter your Cellphone Number:>> ");
            String enteredCell = check.nextLine();
            // calls the boolean authenticate method from verify user class to check if all login inputs are matching register inputs
            if (registeredUser.authenticateUser(enteredUsername, enteredPassword, enteredCell)) {
                return true;
            } else {
                //loops twice until you match your vaild details and decrements everytime you get it wrong and stops the program
                System.out.println("Incorrect details. Attempts left:>> " + (2 - attempts));
                System.out.println("Maximum login attempts reached.");
                attempts++;
            }
        }

        return false;
    }

    public static String returnLoginStatus(boolean loginSuccess, VerifyUser user) {
        VerifyUser tempUser = new VerifyUser("", "", "","","");
        System.out.println("********************************************");
        //when vaild, prints out this result
        if (loginSuccess) {
            System.out.println("Login successful!");
            System.out.println("Welcome "+ tempUser.getFirstName()+ " " + tempUser.getLastName() +  "it is great to see you again");
            return "Login successful!";
        } else {
            //Prints out this result when attempts reach maximum attempts
            System.out.println("Login failed!");
            System.err.println("Username or password incorrect , please try again");
            return "Login failed.";
        }
    }
}
