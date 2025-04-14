/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package prog5121_a1.part1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author SYLVESTER FIRMINO
 */
public class PROG5121_A1PART1IT {
    
    public PROG5121_A1PART1IT() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of main method, of class PROG5121_A1PART1.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        PROG5121_A1PART1.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerUser method, of class PROG5121_A1PART1.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        VerifyUser expResult = null;
        VerifyUser result = PROG5121_A1PART1.registerUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loginUser method, of class PROG5121_A1PART1.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        VerifyUser registeredUser = null;
        boolean expResult = false;
        boolean result = PROG5121_A1PART1.loginUser(registeredUser);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnLoginStatus method, of class PROG5121_A1PART1.
     */
    @Test
    public void testReturnLoginStatus() {
        System.out.println("returnLoginStatus");
        boolean loginSuccess = false;
        VerifyUser user = null;
        String expResult = "";
        String result = PROG5121_A1PART1.returnLoginStatus(loginSuccess, user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
