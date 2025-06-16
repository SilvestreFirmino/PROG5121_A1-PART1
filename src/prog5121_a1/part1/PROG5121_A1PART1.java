package prog5121_a1.part1;

/**
 *
 * @author Silvestre Firmino ST10453122
 */

import org.json.JSONObject;
import org.json.JSONArray; // Import for JSON arrays
import org.json.JSONException; // Import for JSON exceptions
import javax.swing.JOptionPane;
import java.io.BufferedReader; // For reading file
import java.io.File; // For file existence check
import java.io.FileReader; // For reading file
import java.io.IOException; // For file I/O exceptions
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption; // For controlling file write behavior
import java.util.Random; // Needed for message ID generation

public class PROG5121_A1PART1 {


    // Parallel arrays for storing messages
    public static String[] storedMessageIDs = new String[100];
    public static String[] storedRecipients = new String[100];
    public static String[] storedContents = new String[100];
    public static String[] storedMessageHashes = new String[100];
    private static int storedCount = 0;


    private static final String JSON_FILE_PATH = "messages.json"; // Path to the JSON file for storing messages

    public static void main(String[] args) {
        /*
         Load messages at the start of the application
         Method to load messages from a JSON file
        */
        loadMessagesFromJSON();

        VerifyUser registeredUser = registerUser();
        boolean loginSuccess = loginUser(registeredUser);

        if (loginSuccess) {
            returnLoginStatus(true, registeredUser);
            showChatMenu(registeredUser);
        } else {
            returnLoginStatus(false, registeredUser);
        }
    }

    public static VerifyUser registerUser() {
        VerifyUser tempUser = new VerifyUser();

        JOptionPane.showMessageDialog(null, "************ User Registration *************");

        String firstName = JOptionPane.showInputDialog("Enter your First name:");
        String lastName = JOptionPane.showInputDialog("Enter your Last name:");
        String username, password, cellphoneNumber;

        // Username validation
        while (true) {
            username = JOptionPane.showInputDialog("Enter your username (must contain an underscore and no more than 5 characters):");
            if (tempUser.checkUserName(username)) {
                JOptionPane.showMessageDialog(null, "Username successfully captured.");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Username is not correctly formatted.\nPlease ensure it contains an underscore and is no more than five characters.");
            }
        }

        // Password validation
        while (true) {
            password = JOptionPane.showInputDialog("Enter your Password (at least 8 characters, one uppercase, one number, one special character):");
            if (tempUser.checkPasswordComplexity(password)) {
                JOptionPane.showMessageDialog(null, "Password successfully captured.");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Password is not correctly formatted.\nPassword must be at least 8 characters long, contain at least one uppercase letter, one digit, and one special character.");
            }
        }

        // Cellphone Number validation
        while (true) {
            cellphoneNumber = JOptionPane.showInputDialog("Enter your South African cellphone number (+27xxxxxxxxx):");
            if (tempUser.checkCellPhoneNumber(cellphoneNumber)) {
                JOptionPane.showMessageDialog(null, "Cell phone number successfully added.");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Cell phone number incorrectly formatted or missing international code.\nPlease use the format +27xxxxxxxxx (12 characters).");
            }
        }

        JOptionPane.showMessageDialog(null, "************ Registration Complete *************");

        return new VerifyUser(username, password, cellphoneNumber, firstName, lastName);
    }

    public static boolean loginUser(VerifyUser registeredUser) {
        int attempts = 0;

        JOptionPane.showMessageDialog(null, "************ User Login *************");

        while (attempts < 3) {
            String enteredUsername = JOptionPane.showInputDialog("Enter your Username:");
            String enteredPassword = JOptionPane.showInputDialog("Enter your Password:");
            String enteredCell = JOptionPane.showInputDialog("Enter your Cellphone Number:");

            if (registeredUser.authenticateUser(enteredUsername, enteredPassword, enteredCell)) {
                return true;
            } else {
                attempts++;
                JOptionPane.showMessageDialog(null, "Login failed!\nUsername or password incorrect, please try again.");
            }
        }

        JOptionPane.showMessageDialog(null, "Maximum login attempts reached. Application will exit.");
        return false;
    }

    public static String returnLoginStatus(boolean loginSuccess, VerifyUser user) {
        if (loginSuccess) {
            String welcomeMsg = "Login successful!\nWelcome " + user.getFirstName() + " " + user.getLastName() + ", it is great to see you again!";
            JOptionPane.showMessageDialog(null, welcomeMsg);
            return "Login successful!";
        } else {
            JOptionPane.showMessageDialog(null, "Login failed!\nUsername or password incorrect, please try again.");
            return "Login failed.";
        }
    }




    public static void showChatMenu(VerifyUser user) {
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        String input = JOptionPane.showInputDialog("How many messages would you like to enter?");
        int maxMessages;

        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No input provided. Setting to default of 5 messages.");
            maxMessages = 5;
        } else {
            boolean isNumeric = true;
            for (char c : input.toCharArray()) {
                if (!Character.isDigit(c)) {
                    isNumeric = false;
                    break;
                }
            }

            if (isNumeric) {
                maxMessages = Integer.parseInt(input);
                if (maxMessages <= 0) {
                    JOptionPane.showMessageDialog(null, "Invalid number. Setting to default of 5 messages.");
                    maxMessages = 5;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number. Setting to default of 5 messages.");
                maxMessages = 5;
            }
        }

        int messagesSentThisSession = 0;
        boolean isRunning = true;

        // loops until the condition is met
        while (true) {
            String menuInput = JOptionPane.showInputDialog("Messages remaining: " + (maxMessages - messagesSentThisSession) + "\nChoose an option:\n" +
                    "1) Send Messages \n" +
                    "2) Show recently sent messages \n" +
                    "3) Quit \n");

            if (menuInput == null) {
                JOptionPane.showMessageDialog(null, "Goodbye " + user.getFirstName() + "!\n" + getTotalSentMessages());
                break;
            }

            int options;
            if (!menuInput.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
                continue; // Changed from break to continue
            } else {
                options = Integer.parseInt(menuInput);
            }

            switch (options) {
                // Send Messages
                case 1:
                    if (messagesSentThisSession >= maxMessages) {
                        JOptionPane.showMessageDialog(null, "You have reached your message limit (" + maxMessages + "). Cannot send more messages.");
                        isRunning = false;
                        break;
                    }
                    boolean messageSent = createAndSendMessage();
                    if (messageSent) {
                        messagesSentThisSession++;
                    }
                    break;

                // View Stored Messages
                case 2:
                    JOptionPane.showMessageDialog(null, Message.displayFullMessageReport());
                    String menuInput2 = JOptionPane.showInputDialog("Choose an option:\n" +
                            "1) Display the sender and recipient of all sent messages\n" +
                            "2) Display the longest sent message\n"+
                            "3) Search for message ID and display content\n"+
                            "4) Search for all messages for a particular recipient\n"+
                            "5) Delete a message using message ID\n"+
                            "6) Display a report that lists the full details of all sent messages\n");

                    if (menuInput2 == null) {
                        break;
                    }

                    int options2;

                    if (!menuInput2.matches("\\d+")) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
                        break;
                    }

                    if (Integer.parseInt(menuInput2) > 6) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number between 1 and 6.");
                        break;
                    }
                    options2 = Integer.parseInt(menuInput2);

                    switch (options2) {
                        case 1: // Display the sender and recipient of all sent messages
                            JOptionPane.showMessageDialog(null, Message.displaySenderAndRecipient());
                            break;
                        case 2: // Display the longest sent message
                            JOptionPane.showMessageDialog(null, Message.displayLongestMessage());
                            break;
                        case 3: // Search for message ID and display content
                            String messageID = JOptionPane.showInputDialog("Enter the message ID to search for:");
                            if (messageID != null && !messageID.trim().isEmpty()) {
                                String result = Message.searchForMessageByID(messageID);
                                JOptionPane.showMessageDialog(null, result);
                            } else {
                                JOptionPane.showMessageDialog(null, "No message ID provided.");
                            }
                            break;
                        case 4: // Search for a particular recipient
                            String recipient = JOptionPane.showInputDialog("Enter the recipient to search for:");
                            if (recipient != null && !recipient.trim().isEmpty()) {
                                String result = Message.searchMessagesByRecipient(recipient);
                                JOptionPane.showMessageDialog(null, result);
                            } else {
                                JOptionPane.showMessageDialog(null, "No recipient number provided.");
                            }
                            break;
                        case 5: // Delete a message using message ID
                            String messageID3 = JOptionPane.showInputDialog("Enter the message ID to delete:");
                            if (messageID3 != null && !messageID3.trim().isEmpty()) {
                                String result = Message.deleteMessageByID(messageID3);
                                JOptionPane.showMessageDialog(null, result);
                                storeAllMessagesToJSON(); // Save changes to file
                            } else {
                                JOptionPane.showMessageDialog(null, "No message ID provided.");
                            }
                            break;
                        case 6: // Display a report that lists the full details of all sent messages
                            JOptionPane.showMessageDialog(null, Message.displayFullMessageReport());
                            break;
                    }
                    break;

                case 3: // Quit
                    isRunning = false;
                    JOptionPane.showMessageDialog(null, "Goodbye " + user.getFirstName() + "!\n" + getTotalSentMessages());
                    break;

                default: // Invalid option
                    JOptionPane.showMessageDialog(null, "Invalid option. Please choose 1, 2, or 3.");
                    break;
            }

            if (!isRunning) {
                break;
            }
        }
    }

    private static boolean createAndSendMessage() {
        String messageID = generateMessageID();

        String recipient;
        int recipientStatus;

        do {
            recipient = JOptionPane.showInputDialog("Enter recipient's cellphone number (e.g., +27xxxxxxxxx - 12 characters):");
            if (recipient == null) {
                return false; // User cancelled
            }

            recipientStatus = checkRecipientCell(recipient);

            if (recipientStatus == 0) {
                JOptionPane.showMessageDialog(null, "Cell number must be in format +27xxxxxxxxx and contain only digits after +27 (total 12 characters).");
            }
        } while (recipientStatus != 1);

        String messageContent;
        boolean validMessage = false;
        final int MAX_MESSAGE_LENGTH = 250;

        do {
            messageContent = JOptionPane.showInputDialog("Enter your message (max " + MAX_MESSAGE_LENGTH + " characters):");

            if (messageContent == null) {
                return false; // User cancelled
            } else if (messageContent.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Message cannot be empty.");
            } else if (messageContent.length() > MAX_MESSAGE_LENGTH) { // checks length
                JOptionPane.showMessageDialog(null, "Please enter a message of no more than " + MAX_MESSAGE_LENGTH + " characters.");
            } else {
                validMessage = true;
            }
        } while (!validMessage);

        String hash = createMessageHash(messageID, messageContent);

        JOptionPane.showMessageDialog(null, "Message Hash: " + hash);


        String result = handleMessageAction(messageID, recipient, messageContent, hash);

        JOptionPane.showMessageDialog(null, result);

        return result.equals("Message sent successfully!");
    }


    private static String handleMessageAction(String messageID, String recipient, String content, String hash) {
        String menuInput = JOptionPane.showInputDialog("Do you want to:\n" +
                "1. Send Message\n" +
                "2. Store Message to send later\n" +
                "3. Disregard Message\n");

        if (menuInput == null) { // User cancelled
            return "Operation cancelled. Message discarded.";
        }

        int options;

        if (menuInput.matches("\\d+")) { // Check if input is a number
            options = Integer.parseInt(menuInput);
        } else {
            return "Invalid option. Message discarded.";
        }

        if (Integer.parseInt(menuInput) >= 1 && Integer.parseInt(menuInput) <= 3) {
            return "Invalid option. Message discarded, try picking 1 to 3.";
        }

        switch (options) {
            case 1: // Send Message

                if (Message.addSentMessage(messageID, recipient, content, hash)) {
                    storeAllMessagesToJSON(); // Persist changes to file
                    return "Message sent successfully!";
                } else {
                    return "Cannot send message - sent messages storage is full. Please clear some space.";
                }

            case 2: // Store Message to send later
                if (storedCount < storedMessageIDs.length) {
                    storedMessageIDs[storedCount] = messageID; // Store message ID
                    storedRecipients[storedCount] = recipient;
                    storedContents[storedCount] = content;
                    storedMessageHashes[storedCount] = hash;
                    storedCount++; // Increment stored count
                    storeAllMessagesToJSON(); // Persist changes to file
                    return "Message stored for later sending.";
                } else {
                    return "Cannot store message - stored messages storage is full. Please clear some space.";
                }

            case 3: // Disregard Message
                return "Message discarded.";

            default:
                return "Invalid option. Message discarded.";
        }
    }

    public static String generateMessageID() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }
        return sb.toString();
    }

    public static int checkRecipientCell(String recipient) {
        if (recipient == null) {
            return 0; // Invalid format
        }

        // Check for the exact length of 12 characters
        if (recipient.length() != 12) {
            return 0; // Invalid length
        }

        // Check if it starts with "+27"
        if (!recipient.startsWith("+27")) {
            return 0; // Does not start with +27
        }

        // Check if remaining characters are digits
        String digits = recipient.substring(3); // Get characters after "+27"
        for (char c : digits.toCharArray()) {
            if (!Character.isDigit(c)) {
                return 0; // Contains non-digit characters
            }
        }

        return 1; // Valid recipient number
    }

    public static String createMessageHash(String messageID, String content) {
        // Checking if message data is valid
        if (messageID == null || content == null || content.trim().isEmpty()) {
            return ""; // Return empty hash for invalid message data
        }

        // Extract first two digits from message ID
        String firstTwoDigits = "";
        int digitCount = 0;
        for (char c : messageID.toCharArray()) {
            if (Character.isDigit(c)) {
                firstTwoDigits += c;
                digitCount++;
                if (digitCount == 2) break; // Take only the first two digits
            }
        }
        // Ensure firstTwoDigits has at least 2 digits
        while (firstTwoDigits.length() < 2) {
            firstTwoDigits = "0" + firstTwoDigits;
        }

        // Get the number of sent messages

        int messageNumber = Message.getSentCount();

        // Extract first and last words from message content
        String[] words = content.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord; // If only one word, first and last are the same

        // Create the hash
        String hash = firstTwoDigits + ":" + messageNumber + ":" +
                (firstWord + lastWord).toUpperCase().replaceAll("[^A-Z0-9]", ""); // Remove non-alphanumeric

        return hash;
    }

    public static  String printMessages() {
        return Message.printMessages();
    }

    public static String getTotalSentMessages() {
        return "Total number of sent messages: " + Message.getSentCount();
    }

    public static void storeAllMessagesToJSON() {
        JSONObject rootObject = new JSONObject();
        JSONArray sentJsonArray = new JSONArray();
        JSONArray storedJsonArray = new JSONArray();

        /* Populate a sent message array for JSON using Message class arrays */
        String[] sentMessageIDs = Message.getSentMessageIDs();
        String[] sentRecipients = Message.getSentRecipients();
        String[] sentContents = Message.getSentContents();
        String[] sentMessageHashes = Message.getSentMessageHashes();
        int sentCount = Message.getSentCount();

        for (int i = 0; i < sentCount; i++) {
            if (sentMessageIDs[i] == null) continue; // Skip if array element is null
            JSONObject msgObject = new JSONObject();
            try {
                msgObject.put("messageID", sentMessageIDs[i]);
                msgObject.put("recipient", sentRecipients[i]);
                msgObject.put("content", sentContents[i]);
                msgObject.put("messageHash", sentMessageHashes[i]);
                msgObject.put("status", "sent"); // Add status for clarity
                sentJsonArray.put(msgObject);
            } catch (JSONException e) {
                System.err.println("Error creating JSON object for sent message at index " + i + ": " + e.getMessage());
            }
        }

        // Populate a stored messages array for JSON using parallel arrays

        for (int i = 0; i < storedCount; i++) {
            if (storedMessageIDs[i] == null) continue; // Skip if array element is null
            JSONObject msgObject = new JSONObject();
            try {
                msgObject.put("messageID", storedMessageIDs[i]); // Use storedMessageIDs
                msgObject.put("recipient", storedRecipients[i]);
                msgObject.put("content", storedContents[i]);
                msgObject.put("messageHash", storedMessageHashes[i]);
                msgObject.put("status", "stored"); // Add status for clarity
                storedJsonArray.put(msgObject);
            } catch (JSONException e) {
                System.err.println("Error creating JSON object for stored message at index " + i + ": " + e.getMessage());
            }
        }

        try {
            rootObject.put("sentMessages", sentJsonArray);
            rootObject.put("storedMessages", storedJsonArray);
            rootObject.put("totalSent", sentCount);
            rootObject.put("totalStored", storedCount);

            // Write the pretty-printed JSON to the file, creating/overwriting it
            Files.write(Paths.get(JSON_FILE_PATH), rootObject.toString(4).getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Messages successfully written to JSON file: " + JSON_FILE_PATH);
        } catch (JSONException e) {
            System.err.println("Error building root JSON object or putting data: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error writing JSON to file '" + JSON_FILE_PATH + "': " + e.getMessage());
        }
    }

    public static void loadMessagesFromJSON() {
        File file = new File(JSON_FILE_PATH);
        // Check if a file exists and is not empty to avoid JSONException on empty content
        if (!file.exists() || file.length() == 0) {
            System.out.println("No existing or empty JSON file found at '" + JSON_FILE_PATH + "'. Starting with empty message lists.");
            Message.setSentCount(0);
            storedCount = 0;
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonContent.append(line);
            }

            JSONObject rootObject = new JSONObject(jsonContent.toString());

            // Load sent messages array into Message class arrays
            if (rootObject.has("sentMessages")) {
                JSONArray sentJsonArray = rootObject.getJSONArray("sentMessages");
                Message.setSentCount(0); // Reset count before loading

                String[] sentMessageIDs = Message.getSentMessageIDs();
                String[] sentRecipients = Message.getSentRecipients();
                String[] sentContents = Message.getSentContents();
                String[] sentMessageHashes = Message.getSentMessageHashes();
                int sentCount = 0;

                for (int i = 0; i < sentJsonArray.length() && sentCount < 100; i++) {
                    JSONObject msgObject = sentJsonArray.getJSONObject(i);
                    // Robustly check for key existence before accessing
                    if (msgObject.has("messageID") && msgObject.has("recipient") &&
                            msgObject.has("content") && msgObject.has("messageHash")) {
                        sentMessageIDs[sentCount] = msgObject.getString("messageID");
                        sentRecipients[sentCount] = msgObject.getString("recipient");
                        sentContents[sentCount] = msgObject.getString("content");
                        sentMessageHashes[sentCount] = msgObject.getString("messageHash");
                        sentCount++;
                    } else {
                        System.err.println("Warning: Skipping malformed sent message object at index " + i + " in JSON.");
                    }
                }
                Message.setSentCount(sentCount);
            }

            // Load stored messages array into parallel arrays
            if (rootObject.has("storedMessages")) {
                JSONArray storedJsonArray = rootObject.getJSONArray("storedMessages");
                storedCount = 0; // Reset count before loading
                for (int i = 0; i < storedJsonArray.length() && storedCount < storedMessageIDs.length; i++) {
                    JSONObject msgObject = storedJsonArray.getJSONObject(i);
                    if (msgObject.has("messageID") && msgObject.has("recipient") &&
                            msgObject.has("content") && msgObject.has("messageHash")) {
                        storedMessageIDs[storedCount] = msgObject.getString("messageID"); // FIXED: 2D array access
                        storedRecipients[storedCount] = msgObject.getString("recipient");
                        storedContents[storedCount] = msgObject.getString("content");
                        storedMessageHashes[storedCount] = msgObject.getString("messageHash");
                        storedCount++;
                    } else {
                        System.err.println("Warning: Skipping malformed stored message object at index " + i + " in JSON.");
                    }
                }
            }

            // Update internal counts from JSON if available, otherwise use populated counts
            if (rootObject.has("totalSent")) {
                Message.setSentCount(rootObject.getInt("totalSent"));
            }
            if (rootObject.has("totalStored")) {
                storedCount = rootObject.getInt("totalStored");
            }
            System.out.println("Messages successfully loaded from JSON file: " + JSON_FILE_PATH);
        } catch (JSONException e) {
            System.err.println("Error parsing JSON file '" + JSON_FILE_PATH + "': " + e.getMessage());
            // If there's a parsing error, it's safer to clear existing data
            Message.setSentCount(0);
            storedCount = 0;
            JOptionPane.showMessageDialog(null, "Error loading messages from file. Starting with empty message lists.");
        } catch (IOException e) {
            System.err.println("Error reading JSON file '" + JSON_FILE_PATH + "': " + e.getMessage());
            Message.setSentCount(0);
            storedCount = 0;
            JOptionPane.showMessageDialog(null, "Error reading messages from file. Starting with empty message lists.");
        }
    }
}
