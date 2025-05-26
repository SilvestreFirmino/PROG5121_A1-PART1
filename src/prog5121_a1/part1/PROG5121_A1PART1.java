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
import java.io.File;         // For file existence check
import java.io.FileReader;     // For reading file
import java.io.FileWriter;     // For writing file
import java.io.IOException;    // For file I/O exceptions
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption; // For controlling file write behavior
import java.util.Random; // Needed for message ID generation

public class PROG5121_A1PART1 {

    private static Message[] sentMessages = new Message[100];
    private static Message[] storedMessages = new Message[100];
    private static int sentCount = 0;
    private static int storedCount = 0;

    private static final String JSON_FILE_PATH = "messages.json";

    public static void main(String[] args) {
        // Load messages at the start of the application
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
            JOptionPane.showMessageDialog(null, "Login failed!\nUsername or password incorrect, please try again."); // This should ideally not be reached if max attempts reached.
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

        while (isRunning && messagesSentThisSession < maxMessages) {
            String[] options = {"1) Send Messages", "2) Show recently sent messages", "3) Quit"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Messages remaining: " + (maxMessages - messagesSentThisSession) + "\nChoose an option:",
                    "QuickChat Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]);

            switch (choice) {
                case 0: // Send Messages
                    if (messagesSentThisSession >= maxMessages) {
                        JOptionPane.showMessageDialog(null, "You have reached your message limit (" + maxMessages + "). Cannot send more messages.");
                        isRunning = false; // Exit menu if limit reached
                        break;
                    }
                    boolean messageSent = createAndSendMessage();
                    if (messageSent) {
                        messagesSentThisSession++;
                    }
                    break;

                case 1: // View Stored Messages (Coming Soon)
                    JOptionPane.showMessageDialog(null, "Coming soon!");
                    break;

                case 2: // Quit
                    isRunning = false;
                    JOptionPane.showMessageDialog(null, "Goodbye " + user.getFirstName() + "!\n" + getTotalSentMessages());
                    break;

                default: // User closed dialog (e.g., pressed 'X')
                    isRunning = false;
                    JOptionPane.showMessageDialog(null, "Goodbye " + user.getFirstName() + "!\n" + getTotalSentMessages());
                    break;
            }
        }

        // Display messages sent this session if the loop exited due to message limit
        if (messagesSentThisSession >= maxMessages) {
            JOptionPane.showMessageDialog(null, "You have reached your message limit (" + maxMessages + ") for this session.");
            JOptionPane.showMessageDialog(null, printSentMessages()); // Show all sent messages
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

            if (recipientStatus == 0) { // checkRecipientCell now returns 0 for all invalid formats
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
            } else if (messageContent.length() > MAX_MESSAGE_LENGTH) {
                JOptionPane.showMessageDialog(null, "Please enter a message of no more than " + MAX_MESSAGE_LENGTH + " characters.");
            } else {
                validMessage = true;
            }
        } while (!validMessage);

        Message newMessage = new Message.MessageBuilder(messageID)
                .recipient(recipient)
                .content(messageContent)
                .build();

        String hash = createMessageHash(newMessage);
        newMessage.setMessageHash(hash);

        JOptionPane.showMessageDialog(null, "Message Hash: " + newMessage.getMessageHash());

        String result = handleMessageAction(newMessage);

        JOptionPane.showMessageDialog(null, result);

        return result.equals("Message sent successfully!");
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
            return 0; // Invalid (null input)
        }

        // Check for exact length of 12 characters
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


    public static String createMessageHash(Message message) {
        // Ensure message ID and content are not null or empty for hash creation
        if (message.getMessageID() == null || message.getContent() == null || message.getContent().trim().isEmpty()) {
            return ""; // Return empty hash for invalid message data
        }

        String messageID = message.getMessageID();
        String content = message.getContent();

        // Extract first two digits from message ID (even if ID is longer, take first 2 numeric)
        String firstTwoDigits = "";
        int digitCount = 0;
        for (char c : messageID.toCharArray()) {
            if (Character.isDigit(c)) {
                firstTwoDigits += c;
                digitCount++;
                if (digitCount == 2) break; // Take only the first two digits
            }
        }
        // Pad with leading zeros if fewer than 2 digits were found (unlikely with generateMessageID)
        while (firstTwoDigits.length() < 2) {
            firstTwoDigits = "0" + firstTwoDigits;
        }

        // Use the current sent count for the 'N' part of the hash
        int messageNumber = sentCount;

        // Extract first and last words from message content
        String[] words = content.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord; // If only one word, first and last are the same

        // Construct the hash: firstTwoDigits:N:(firstWord+lastWord in uppercase, no special chars)
        String hash = firstTwoDigits + ":" + messageNumber + ":" +
                (firstWord + lastWord).toUpperCase().replaceAll("[^A-Z0-9]", ""); // Remove non-alphanumeric

        return hash;
    }


    public static String handleMessageAction(Message message) {
        String[] options = {"Send Message", "Store Message to send later", "Disregard Message"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose an option for this message:",
                "Message Options",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]); // Default to "Send Message"

        switch (choice) {
            case 0: // Send Message
                if (sentCount < sentMessages.length) {
                    sentMessages[sentCount] = message; // Add message to sent array
                    sentCount++; // Increment sent count
                    storeAllMessagesToJSON(); // Persist changes to file
                    return "Message sent successfully!";
                } else {
                    return "Cannot send message - sent messages storage is full. Please clear some space.";
                }

            case 1: // Store Message to send later
                if (storedCount < storedMessages.length) {
                    storedMessages[storedCount] = message; // Add message to stored array
                    storedCount++; // Increment stored count
                    storeAllMessagesToJSON(); // Persist changes to file
                    return "Message stored for later sending.";
                } else {
                    return "Cannot store message - stored messages storage is full. Please clear some space.";
                }

            case 2: // Disregard Message
                return "Message discarded.";

            default:
                return "Operation cancelled. Message discarded.";
        }
    }


    public static String printSentMessages() {
        if (sentCount == 0) {
            return "No messages have been sent yet.";
        }

        StringBuilder output = new StringBuilder("--- QuickChat Sent Messages ---\n\n");
        for (int i = 0; i < sentCount; i++) {
            Message msg = sentMessages[i];
            // Ensure the message object is not null in the array (important for robustness)
            if (msg != null) {
                output.append("#Message ").append(i + 1).append(":\n");
                output.append("  ID: ").append(msg.getMessageID()).append("\n");
                output.append("  Recipient: ").append(msg.getRecipient()).append("\n");
                output.append("  Content: ").append(msg.getContent()).append("\n");
                output.append("  Hash: ").append(msg.getMessageHash()).append("\n");
                output.append("  ------------End of messages-------------\n\n");
            }
        }
        return output.toString();
    }


    public static String getTotalSentMessages() {
        return "Total number of sent messages: " + sentCount;
    }


    public static void storeAllMessagesToJSON() {
        JSONObject rootObject = new JSONObject();
        JSONArray sentJsonArray = new JSONArray();
        JSONArray storedJsonArray = new JSONArray();

        // Populate sent messages array for JSON
        for (int i = 0; i < sentCount; i++) {
            Message msg = sentMessages[i];
            if (msg == null) continue; // Skip if array element is null
            JSONObject msgObject = new JSONObject();
            try {
                msgObject.put("messageID", msg.getMessageID());
                msgObject.put("recipient", msg.getRecipient());
                msgObject.put("content", msg.getContent());
                msgObject.put("messageHash", msg.getMessageHash());
                msgObject.put("status", "sent"); // Add status for clarity
                sentJsonArray.put(msgObject);
            } catch (JSONException e) {
                System.err.println("Error creating JSON object for sent message at index " + i + ": " + e.getMessage());
            }
        }

        // Populate stored messages array for JSON
        for (int i = 0; i < storedCount; i++) {
            Message msg = storedMessages[i];
            if (msg == null) continue; // Skip if array element is null
            JSONObject msgObject = new JSONObject();
            try {
                msgObject.put("messageID", msg.getMessageID());
                msgObject.put("recipient", msg.getRecipient());
                msgObject.put("content", msg.getContent());
                msgObject.put("messageHash", msg.getMessageHash());
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
        // Check if file exists and is not empty to avoid JSONException on empty content
        if (!file.exists() || file.length() == 0) {
            System.out.println("No existing or empty JSON file found at '" + JSON_FILE_PATH + "'. Starting with empty message lists.");
            sentCount = 0;
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

            // Load sent messages array
            if (rootObject.has("sentMessages")) {
                JSONArray sentJsonArray = rootObject.getJSONArray("sentMessages");
                sentCount = 0; // Reset count before loading
                for (int i = 0; i < sentJsonArray.length() && sentCount < sentMessages.length; i++) {
                    JSONObject msgObject = sentJsonArray.getJSONObject(i);
                    // Robustly check for key existence before accessing
                    if (msgObject.has("messageID") && msgObject.has("recipient") &&
                        msgObject.has("content") && msgObject.has("messageHash")) {
                        Message msg = new Message.MessageBuilder(msgObject.getString("messageID"))
                                .recipient(msgObject.getString("recipient"))
                                .content(msgObject.getString("content"))
                                .build();
                        msg.setMessageHash(msgObject.getString("messageHash"));
                        sentMessages[sentCount++] = msg;
                    } else {
                        System.err.println("Warning: Skipping malformed sent message object at index " + i + " in JSON.");
                    }
                }
            }

            // Load stored messages array
            if (rootObject.has("storedMessages")) {
                JSONArray storedJsonArray = rootObject.getJSONArray("storedMessages");
                storedCount = 0; // Reset count before loading
                for (int i = 0; i < storedJsonArray.length() && storedCount < storedMessages.length; i++) {
                    JSONObject msgObject = storedJsonArray.getJSONObject(i);
                     if (msgObject.has("messageID") && msgObject.has("recipient") &&
                        msgObject.has("content") && msgObject.has("messageHash")) {
                        Message msg = new Message.MessageBuilder(msgObject.getString("messageID"))
                                .recipient(msgObject.getString("recipient"))
                                .content(msgObject.getString("content"))
                                .build();
                        msg.setMessageHash(msgObject.getString("messageHash"));
                        storedMessages[storedCount++] = msg;
                    } else {
                        System.err.println("Warning: Skipping malformed stored message object at index " + i + " in JSON.");
                    }
                }
            }

            // Update internal counts from JSON if available, otherwise use populated counts
            if (rootObject.has("totalSent")) {
                sentCount = rootObject.getInt("totalSent");
            }
            if (rootObject.has("totalStored")) {
                storedCount = rootObject.getInt("totalStored");
            }

            System.out.println("Loaded " + sentCount + " sent messages and " + storedCount + " stored messages from JSON file: " + JSON_FILE_PATH);

        } catch (IOException e) {
            System.err.println("Error reading JSON file '" + JSON_FILE_PATH + "': " + e.getMessage());
            sentCount = 0; // Reset counts on read error to prevent partial data issues
            storedCount = 0;
        } catch (JSONException e) {
            System.err.println("Error parsing JSON content from '" + JSON_FILE_PATH + "': " + e.getMessage() + ". File might be corrupted or incorrectly formatted.");
            sentCount = 0; // Reset counts on parse error
            storedCount = 0;
        }
    }
}