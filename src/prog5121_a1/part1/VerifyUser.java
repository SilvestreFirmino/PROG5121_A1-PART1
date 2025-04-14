package prog5121_a1.part1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ST10453122 Silvestre Firmino
 */

public class VerifyUser {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellPhoneNumber;

    // Constructor
    public VerifyUser(String username, String password, String cellPhoneNumber, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // No-arg constructor (to allow creating a temp user object for validation)
    public VerifyUser() {
        // No values initialized yet
    }

    // Username validation
    public boolean checkUserName(String username) {
        // Must contain underscore and be 5 characters or less
        return username.contains("_") && username.length() <= 5;
    }

    // Password validation
    public boolean checkPasswordComplexity(String password) {
        // At least 8 characters, one uppercase, one lowercase, one digit, one special character
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z0-9@$!%*#?&]{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    // Cellphone validation for South African format (+27 followed by 9 digits)
    public boolean checkCellPhoneNumber(String cellPhone) {
        String regex = "^\\+27\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cellPhone);
        return matcher.matches();
    }

    //Authenticate login
    public boolean authenticateUser(String enteredUsername, String enteredPassword, String enteredCellPhoneNumber) {
        return this.username.equals(enteredUsername)
            && this.password.equals(enteredPassword)
            && this.cellPhoneNumber.equals(enteredCellPhoneNumber);
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    //Getters
    public String getUsername() {
        return username;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
