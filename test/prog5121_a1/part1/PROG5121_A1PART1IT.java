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

    /**
     * Test of showChatMenu method, of class PROG5121_A1PART1.
     */
    @Test
    public void testShowChatMenu() {
        System.out.println("showChatMenu");
        VerifyUser user = null;
        PROG5121_A1PART1.showChatMenu(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateMessageID method, of class PROG5121_A1PART1.
     */
    @Test
    public void testGenerateMessageID() {
        System.out.println("generateMessageID");
        String expResult = "";
        String result = PROG5121_A1PART1.generateMessageID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkRecipientCell method, of class PROG5121_A1PART1.
     */
    @Test
    public void testCheckRecipientCell() {
        System.out.println("checkRecipientCell");
        String recipient = "";
        int expResult = 0;
        int result = PROG5121_A1PART1.checkRecipientCell(recipient);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMessageHash method, of class PROG5121_A1PART1.
     */
    @Test
    public void testCreateMessageHash() {
        System.out.println("createMessageHash");
        Message message = null;
        String expResult = "";
        String result = PROG5121_A1PART1.createMessageHash(message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleMessageAction method, of class PROG5121_A1PART1.
     */
    @Test
    public void testHandleMessageAction() {
        System.out.println("handleMessageAction");
        Message message = null;
        String expResult = "";
        String result = PROG5121_A1PART1.handleMessageAction(message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printSentMessages method, of class PROG5121_A1PART1.
     */
    @Test
    public void testPrintSentMessages() {
        System.out.println("printSentMessages");
        String expResult = "";
        String result = PROG5121_A1PART1.createMessageHash();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalSentMessages method, of class PROG5121_A1PART1.
     */
    @Test
    public void testGetTotalSentMessages() {
        System.out.println("getTotalSentMessages");
        String expResult = "";
        String result = PROG5121_A1PART1.getTotalSentMessages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of storeAllMessagesToJSON method, of class PROG5121_A1PART1.
     */
    @Test
    public void testStoreAllMessagesToJSON() {
        System.out.println("storeAllMessagesToJSON");
        PROG5121_A1PART1.storeAllMessagesToJSON();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadMessagesFromJSON method, of class PROG5121_A1PART1.
     */
    @Test
    public void testLoadMessagesFromJSON() {
        System.out.println("loadMessagesFromJSON");
        PROG5121_A1PART1.loadMessagesFromJSON();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printMessages method, of class PROG5121_A1PART1.
     */
    @Test
    public void testPrintMessages() {
        System.out.println("printMessages");
        String expResult = "";
        String result = PROG5121_A1PART1.printMessages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
