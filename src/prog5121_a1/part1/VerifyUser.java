
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
 
    //contractor
    public VerifyUser(String lastName ,String firstName ,String username, String password , String cellPhoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public boolean checkUserName(String username) {
        /* returns true if username is correctly formatted */
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        /* returns true if password is correctly formatted  and contains regex values*/
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z0-9@$!%*#?&]{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean checkCellPhoneNumber(String cellPhone) {
        /* returns true if phone number is correctly formatted and if the phone number is South African */
        
        String regex = ("^\\+27\\d{9}$");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cellPhone);
        return matcher.matches();

    }

    public boolean authenticateUser(String enteredUsername, String enteredPassword , String enteredCellPhoneNumber) {
        /* returns true if username, password and cellphone number are correct */
        return this.username.equals(enteredUsername) && this.password.equals(enteredPassword) && this.cellPhoneNumber.equals(enteredCellPhoneNumber);
    }

    
    //Getter values returned to print out the login success 
    public String getUsername() {
        return username;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }
    
    
    public String getFirstName(){
     
        return firstName;

}
    
    public String getLastName(){
    
        return lastName;
    }
}

