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
public class MessageIT {
    
    public MessageIT() {
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
     * Test of searchMessageID method, of class Message.
     */
    @Test
    public void testSearchMessageID() {
        System.out.println("searchMessageID");
        String messageID = "";
        Message instance = new Message();
        instance.searchMessageID(messageID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessageID method, of class Message.
     */
    @Test
    public void testGetMessageID() {
        System.out.println("getMessageID");
        Message instance = new Message();
        String expResult = "";
        String result = instance.getMessageID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMessageID method, of class Message.
     */
    @Test
    public void testSetMessageID() {
        System.out.println("setMessageID");
        String messageID = "";
        Message instance = new Message();
        instance.setMessageID(messageID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRecipient method, of class Message.
     */
    @Test
    public void testGetRecipient() {
        System.out.println("getRecipient");
        Message instance = new Message();
        String expResult = "";
        String result = instance.getRecipient();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRecipient method, of class Message.
     */
    @Test
    public void testSetRecipient() {
        System.out.println("setRecipient");
        String recipient = "";
        Message instance = new Message();
        instance.setRecipient(recipient);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContent method, of class Message.
     */
    @Test
    public void testGetContent() {
        System.out.println("getContent");
        Message instance = new Message();
        String expResult = "";
        String result = instance.getContent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setContent method, of class Message.
     */
    @Test
    public void testSetContent() {
        System.out.println("setContent");
        String content = "";
        Message instance = new Message();
        instance.setContent(content);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessageHash method, of class Message.
     */
    @Test
    public void testGetMessageHash() {
        System.out.println("getMessageHash");
        Message instance = new Message();
        String expResult = "";
        String result = instance.getMessageHash();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMessageHash method, of class Message.
     */
    @Test
    public void testSetMessageHash() {
        System.out.println("setMessageHash");
        String messageHash = "";
        Message instance = new Message();
        instance.setMessageHash(messageHash);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displaySenderAndRecipient method, of class Message.
     */
    @Test
    public void testDisplaySenderAndRecipient() {
        System.out.println("displaySenderAndRecipient");
        String expResult = "";
        String result = Message.displaySenderAndRecipient();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayLongestMessage method, of class Message.
     */
    @Test
    public void testDisplayLongestMessage() {
        System.out.println("displayLongestMessage");
        String expResult = "";
        String result = Message.displayLongestMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchForMessageByID method, of class Message.
     */
    @Test
    public void testSearchForMessageByID() {
        System.out.println("searchForMessageByID");
        String messageID = "";
        String expResult = "";
        String result = Message.searchForMessageByID(messageID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchMessagesByRecipient method, of class Message.
     */
    @Test
    public void testSearchMessagesByRecipient() {
        System.out.println("searchMessagesByRecipient");
        String recipient = "";
        String expResult = "";
        String result = Message.searchMessagesByRecipient(recipient);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteMessageByID method, of class Message.
     */
    @Test
    public void testDeleteMessageByID() {
        System.out.println("deleteMessageByID");
        String messageID = "";
        String expResult = "";
        String result = Message.deleteMessageByID(messageID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayFullMessageReport method, of class Message.
     */
    @Test
    public void testDisplayFullMessageReport() {
        System.out.println("displayFullMessageReport");
        String expResult = "";
        String result = Message.displayFullMessageReport();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessageContent method, of class Message.
     */
    @Test
    public void testGetMessageContent() {
        System.out.println("getMessageContent");
        String messageID = "";
        String expResult = "";
        String result = Message.getMessageContent(messageID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRecipientContent method, of class Message.
     */
    @Test
    public void testGetRecipientContent() {
        System.out.println("getRecipientContent");
        String recipient = "";
        String expResult = "";
        String result = Message.getRecipientContent(recipient);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSentMessage method, of class Message.
     */
    @Test
    public void testAddSentMessage() {
        System.out.println("addSentMessage");
        String messageID = "";
        String recipient = "";
        String content = "";
        String hash = "";
        boolean expResult = false;
        boolean result = Message.addSentMessage(messageID, recipient, content, hash);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSentCount method, of class Message.
     */
    @Test
    public void testGetSentCount() {
        System.out.println("getSentCount");
        int expResult = 0;
        int result = Message.getSentCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSentMessageIDs method, of class Message.
     */
    @Test
    public void testGetSentMessageIDs() {
        System.out.println("getSentMessageIDs");
        String[] expResult = null;
        String[] result = Message.getSentMessageIDs();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSentRecipients method, of class Message.
     */
    @Test
    public void testGetSentRecipients() {
        System.out.println("getSentRecipients");
        String[] expResult = null;
        String[] result = Message.getSentRecipients();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSentContents method, of class Message.
     */
    @Test
    public void testGetSentContents() {
        System.out.println("getSentContents");
        String[] expResult = null;
        String[] result = Message.getSentContents();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSentMessageHashes method, of class Message.
     */
    @Test
    public void testGetSentMessageHashes() {
        System.out.println("getSentMessageHashes");
        String[] expResult = null;
        String[] result = Message.getSentMessageHashes();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSentCount method, of class Message.
     */
    @Test
    public void testSetSentCount() {
        System.out.println("setSentCount");
        int count = 0;
        Message.setSentCount(count);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
