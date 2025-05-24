package prog5121_a1.part1;

/**
 *
 * @author Silvestre Firmino ST10453122
*/

import javax.swing.JOptionPane;
import java.io.*; // Needed for JSON operations
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

        String username = "";
        String password = "";
        String cellphoneNumber = "";

        JOptionPane.showMessageDialog(null, "************ User Registration *************");

        String firstName = JOptionPane.showInputDialog("Enter your First name:");
        String lastName = JOptionPane.showInputDialog("Enter your Last name:");

        // Username
        while (true) {
            username = JOptionPane.showInputDialog("Enter your username (must contain an underscore and no more than 5 characters):");
            if (tempUser.checkUserName(username)) {
                JOptionPane.showMessageDialog(null, "Username successfully captured.");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Username is not correctly formatted.\nPlease ensure it contains an underscore and is no more than five characters.");
            }
        }

        // Password
        while (true) {
            password = JOptionPane.showInputDialog("Enter your Password (at least 8 characters, one uppercase, one number, one special character):");
            if (tempUser.checkPasswordComplexity(password)) {
                JOptionPane.showMessageDialog(null, "Password successfully captured.");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Password is not correctly formatted. Try again.");
            }
        }

        // Cellphone Number
        while (true) {
            cellphoneNumber = JOptionPane.showInputDialog("Enter your South African cellphone number (+27xxxxxxxxx):");
            if (tempUser.checkCellPhoneNumber(cellphoneNumber)) {
                JOptionPane.showMessageDialog(null, "Cell phone number successfully added.");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Cell phone number incorrectly formatted or missing international code.");
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

        JOptionPane.showMessageDialog(null, "Maximum login attempts reached.");
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
        boolean isRunning = true;

        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        String input = JOptionPane.showInputDialog("How many messages would you like to enter?");
        int maxMessages;

        try {
            maxMessages = Integer.parseInt(input);
            if (maxMessages <= 0) {
                JOptionPane.showMessageDialog(null, "Invalid number. Setting to default of 5 messages.");
                maxMessages = 5;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Setting to default of 5 messages.");
            maxMessages = 5;
        }

        // `messagesSent` tracks messages successfully sent in this session
        int messagesSentThisSession = 0;

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
                        isRunning = false;
                        break;
                    }
                    // Call the now static method from PROG5121_A1PART1
                    boolean messageSent = createAndSendMessage();
                    if (messageSent) {
                        messagesSentThisSession++;
                    }
                    break;

                case 1:  //coming soon message
                    JOptionPane.showMessageDialog(null, "Coming Soon.");
                    break;

                case 2: // Quit
                    isRunning = false;
                    // Call the now static method from PROG5121_A1PART1
                    JOptionPane.showMessageDialog(null, "Goodbye " + user.getFirstName() + "!\n" + getTotalSentMessages());
                    break;

                default:
                    isRunning = false;
                    break;
            }
        }

        if (messagesSentThisSession >= maxMessages) {
            JOptionPane.showMessageDialog(null, "You have reached your message limit (" + maxMessages + ").");
            // Call the now static method from PROG5121_A1PART1
            JOptionPane.showMessageDialog(null, printSentMessages());
        }
    }

    private static boolean createAndSendMessage() {
        // Generate message ID using the new static method
        String messageID = generateMessageID();

        String recipient;
        int recipientStatus;

        do {
            recipient = JOptionPane.showInputDialog("Enter recipient's cellphone number (e.g., +27123456789 - 12 characters):");
            if (recipient == null) {
                return false; // User cancelled
            }
            // Use the new static validation method
            recipientStatus = checkRecipientCell(recipient);

            if (recipientStatus == -1) {
                JOptionPane.showMessageDialog(null, "Cell number is too long. Maximum 12 characters (e.g., +27xxxxxxxxx).");
            } else if (recipientStatus == 0) {
                JOptionPane.showMessageDialog(null, "Cell number must be in format +27xxxxxxxxx and contain only digits after +27.");
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

        // Create the Message object using its Builder
        Message newMessage = new Message.MessageBuilder(messageID)
                .recipient(recipient)
                .content(messageContent)
                .build();

        // Calculate and set the message hash
        String hash = createMessageHash(newMessage);
        newMessage.setMessageHash(hash); // Set the hash on the Message object

        JOptionPane.showMessageDialog(null, "Message Hash: " + newMessage.getMessageHash());

        // Call the new static method to handle sending/storing/disregarding
        String result = handleMessageAction(newMessage);

        JOptionPane.showMessageDialog(null, result);

        // Return true only if the message was actually sent
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
            return 0;
        }

        if (recipient.length() > 12) {
            return -1; // Too long
        }

        if (recipient.startsWith("+27") && recipient.length() == 12) {
            try {
                Long.parseLong(recipient.substring(3)); // Check if the 9 digits are numbers
                return 1; // Valid
            } catch (NumberFormatException e) {
                return 0; // Invalid format (contains non-digits after +27)
            }
        }

        return 0; // Invalid format (doesn't start with +27 or incorrect length)
    }

    public static String createMessageHash(Message message) {
        if (message.getMessageID() == null || message.getContent() == null || message.getContent().trim().isEmpty()) {
            return "";
        }

        String messageID = message.getMessageID();
        String content = message.getContent();

        // Extract first two digits from message ID
        String firstTwoDigits = "";
        int digitCount = 0;
        for (char c : messageID.toCharArray()) {
            if (Character.isDigit(c)) {
                firstTwoDigits += c;
                digitCount++;
                if (digitCount == 2) break;
            }
        }

        // Pad with zeros if less than 2 digits
        while (firstTwoDigits.length() < 2) {
            firstTwoDigits = "0" + firstTwoDigits;
        }

        // Get message number (current total sent messages)
        // This is sentCount *before* the current message is added to the array.
        // If you want N to be the *next* sequential number (1-indexed), use sentCount + 1.
        int messageNumber = sentCount; // Using sentCount for 'N' based on current total

        // Extract first and last words from content
        String[] words = content.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;

        String hash = firstTwoDigits + ":" + messageNumber + ":" +
                (firstWord + lastWord).toUpperCase().replaceAll("[^A-Z0-9]", "");

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
                options[0]);

        switch (choice) {
            case 0: // Send Message
                if (sentCount < sentMessages.length) {
                    sentMessages[sentCount] = message; // Add the message to the array
                    sentCount++; // Increment the count
                    storeAllMessagesToJSON(); // Persist changes
                    return "Message sent successfully!";
                } else {
                    return "Cannot send message - sent messages storage is full.";
                }

            case 1: // Store Message to send later
                if (storedCount < storedMessages.length) {
                    storedMessages[storedCount] = message; // Add the message to the array
                    storedCount++; // Increment the count
                    storeAllMessagesToJSON(); // Persist changes
                    return "Message stored for later sending.";
                } else {
                    return "Cannot store message - stored messages storage is full.";
                }

            case 2: // Disregard Message
                return "Message discarded."; // Simply don't add it to any array

            default: // If dialog is closed without selection
                return "Operation cancelled. Message discarded.";
        }
    }

    public static String printSentMessages() {
        if (sentCount == 0) {
            return "No messages have been sent yet.";
        }

        StringBuilder output = new StringBuilder("--- QuickChat Messages ---\n\n");
        for (int i = 0; i < sentCount; i++) {
            Message msg = sentMessages[i];
            output.append("#Message ").append(i + 1).append(":\n");
            output.append("  ID: ").append(msg.getMessageID()).append("\n"); // Use getter
            output.append("  Recipient: ").append(msg.getRecipient()).append("\n"); // Use getter
            output.append("  Content: ").append(msg.getContent()).append("\n"); // Use getter
            output.append("  Hash: ").append(msg.getMessageHash()).append("\n"); // Use getter
            output.append("  ------------End of messages-------------\n\n");
        }
        return output.toString();
    }

    public static String getTotalSentMessages() {
        return "Total number of sent messages: " + sentCount;
    }

    /**
     * Custom method to store all messages (sent and stored) in JSON format.
     */
    public static void storeAllMessagesToJSON() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(JSON_FILE_PATH))) {
            writer.write("{\n");
            writer.write("  \"sentMessages\": [\n");

            for (int i = 0; i < sentCount; i++) {
                Message msg = sentMessages[i];
                writer.write("    {\n");
                writer.write("      \"messageID\": \"" + escapeJson(msg.getMessageID()) + "\",\n");
                writer.write("      \"recipient\": \"" + escapeJson(msg.getRecipient()) + "\",\n");
                writer.write("      \"content\": \"" + escapeJson(msg.getContent()) + "\",\n");
                writer.write("      \"messageHash\": \"" + msg.getMessageHash() + "\",\n");
                writer.write("      \"status\": \"sent\"\n");
                writer.write("    }" + (i < sentCount - 1 ? "," : "") + "\n");
            }

            writer.write("  ],\n");
            writer.write("  \"storedMessages\": [\n");

            for (int i = 0; i < storedCount; i++) {
                Message msg = storedMessages[i];
                writer.write("    {\n");
                writer.write("      \"messageID\": \"" + escapeJson(msg.getMessageID()) + "\",\n");
                writer.write("      \"recipient\": \"" + escapeJson(msg.getRecipient()) + "\",\n");
                writer.write("      \"content\": \"" + escapeJson(msg.getContent()) + "\",\n");
                writer.write("      \"messageHash\": \"" + msg.getMessageHash() + "\",\n");
                writer.write("      \"status\": \"stored\"\n");
                writer.write("    }" + (i < storedCount - 1 ? "," : "") + "\n");
            }

            writer.write("  ],\n");
            writer.write("  \"totalSent\": " + sentCount + ",\n");
            writer.write("  \"totalStored\": " + storedCount + "\n");
            writer.write("}\n");

        } catch (IOException e) {
            System.err.println("Error storing messages to JSON: " + e.getMessage());
        }
    }

    /**
     * Helper method to escape JSON special characters
     */
    private static String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
    }

    /**
     * Method to load messages from JSON file
     */
    public static void loadMessagesFromJSON() {
        File file = new File(JSON_FILE_PATH);
        if (!file.exists()) {
            System.out.println("No existing JSON file found.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonContent.append(line);
            }

            String json = jsonContent.toString();
            parseMessagesFromJSONSection(json, "sentMessages", sentMessages, true);
            parseMessagesFromJSONSection(json, "storedMessages", storedMessages, false);

            // Update counts from JSON if available
            sentCount = extractJsonInt(json, "totalSent");
            storedCount = extractJsonInt(json, "totalStored");


            System.out.println("Loaded " + sentCount + " sent messages and " + storedCount + " stored messages from JSON.");

        } catch (IOException e) {
            System.err.println("Error loading messages from JSON: " + e.getMessage());
        }
    }

    /**
     * Helper method to parse messages from a specific JSON section (e.g., "sentMessages" or "storedMessages").
     */
    private static void parseMessagesFromJSONSection(String json, String sectionKey, Message[] targetArray, boolean isSent) {
        int startIndex = json.indexOf("\"" + sectionKey + "\": [");
        if (startIndex == -1) return;

        int endIndex = json.indexOf("]", startIndex); // Find closing bracket for the array
        if (endIndex == -1) return;

        // Ensure we capture the array content correctly, including its curly braces
        String sectionContent = json.substring(startIndex + ("\"" + sectionKey + "\": [").length(), endIndex);

        // Reset the corresponding count before parsing
        if (isSent) {
            sentCount = 0;
        } else {
            storedCount = 0;
        }

        // Split by object delimiters '{' and '}'
        // This is a basic parser. For robust JSON parsing, consider Gson or Jackson libraries.
        String[] parts = sectionContent.split("\\{");

        for (String part : parts) {
            if (part.contains("messageID")) {
                String msgID = extractJsonValue(part, "messageID");
                String recipient = extractJsonValue(part, "recipient");
                String content = extractJsonValue(part, "content");
                String messageHash = extractJsonValue(part, "messageHash"); // Extract hash too

                if (msgID != null && recipient != null && content != null && messageHash != null) {
                    Message msg = new Message.MessageBuilder(msgID)
                            .recipient(recipient)
                            .content(content)
                            .build();
                    msg.setMessageHash(messageHash); // Set the loaded hash

                    if (isSent) {
                        if (sentCount < targetArray.length) { // Ensure capacity
                            targetArray[sentCount] = msg;
                            sentCount++;
                        }
                    } else {
                        if (storedCount < targetArray.length) { // Ensure capacity
                            targetArray[storedCount] = msg;
                            storedCount++;
                        }
                    }
                }
            }
        }
    }


    /**
     * Helper method to extract string values from JSON string (moved from Message.java)
     */
    private static String extractJsonValue(String json, String key) {
        String pattern = "\"" + key + "\": \"";
        int start = json.indexOf(pattern);
        if (start < 0) return null;
        start += pattern.length();
        int end = json.indexOf("\"", start);
        if (end < 0) return null;
        String value = json.substring(start, end);
        return value.replace("\\\"", "\"").replace("\\\\", "\\").replace("\\n", "\n").replace("\\r", "\r");
    }

    /**
     * Helper method to extract integer values from JSON string (new helper)
     */
    private static int extractJsonInt(String json, String key) {
        String pattern = "\"" + key + "\": ";
        int start = json.indexOf(pattern);
        if (start < 0) return 0;
        start += pattern.length();
        int end = json.indexOf(",", start); // Look for comma after number
        if (end == -1) { // If no comma, check for closing brace
            end = json.indexOf("}", start);
        }
        if (end < 0) return 0; // Should not happen if JSON is well-formed
        try {
            return Integer.parseInt(json.substring(start, end).trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}