package prog5121_a1.part1;

public class Message {

    // Constants for configuration
    private static final int MAX_MESSAGES = 100;

    // Static arrays for all messages sent
    private static final String[] sentMessageIDs = new String[MAX_MESSAGES];
    private static final String[] sentRecipients = new String[MAX_MESSAGES];
    private static final String[] sentContents = new String[MAX_MESSAGES];
    private static final String[] sentMessageHashes = new String[MAX_MESSAGES];
    private static int sentCount = 0;

    // Instance variables
    private String messageID;
    private String recipient;
    private String content;
    private String messageHash;

    public Message() {
        this.messageID = "";
        this.recipient = "";
        this.content = "";
        this.messageHash = "";
    }

    public static String printMessages() {
        if (sentCount == 0) {
            return "No messages have been sent yet.";
        }

        StringBuilder result = new StringBuilder("--- Messages ---\n\n");
        for (int i = 0; i < sentCount; i++) {
            result.append("Message ID: ").append(sentMessageIDs[i]).append("\n");
            result.append("Recipient: ").append(sentRecipients[i]).append("\n");
            result.append("Content: ").append(sentContents[i]).append("\n");
            result.append("Message Hash: ").append(sentMessageHashes[i]).append("\n\n");
        }
        return result.toString();
    }

    public void searchMessageID(String messageID) {
        this.messageID = messageID;
        this.recipient = "";
        this.content = "";
        this.messageHash = "";
    }

    private Message(MessageBuilder builder) {
        this.messageID = builder.messageID;
        this.recipient = builder.recipient;
        this.content = builder.content;
        this.messageHash = builder.messageHash;
    }

    // Getters and Setters
    public String getMessageID() { return messageID; }
    public void setMessageID(String messageID) { this.messageID = messageID; }

    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getMessageHash() { return messageHash; }
    public void setMessageHash(String messageHash) { this.messageHash = messageHash; }

    // Static methods for chat menu functionality

    /*
     * Display sender and recipient of all sent messages
     */
    public static String displaySenderAndRecipient() {
        if (sentCount == 0) {
            return "No messages have been sent yet.";
        }

        StringBuilder result = new StringBuilder("--- Sender and Recipients ---\n\n");
        for (int i = 0; i < sentCount; i++) {
            if (sentMessageIDs[i] != null) {
                result.append("Message ").append(i + 1).append(":\n");
                result.append("ID: ").append(sentMessageIDs[i]).append("\n");
                result.append("Recipient: ").append(sentRecipients[i]).append("\n\n");
            }
        }
        return result.toString();
    }

    /*
     * Find and display the longest sent message
     */
    public static String displayLongestMessage() {
        if (sentCount == 0) {
            return "No messages have been sent yet.";
        }

        String longestMessage = "";
        int longestIndex = -1;

        for (int i = 0; i < sentCount; i++) {
            if (sentContents[i] != null && sentContents[i].length() > longestMessage.length()) {
                longestMessage = sentContents[i];
                longestIndex = i;
            }
        }

        if (longestIndex == -1) {
            return "No valid messages found.";
        }

        return "Longest Message:\n" +
                "ID: " + sentMessageIDs[longestIndex] + "\n" +
                "Content: " + longestMessage + "\n" +
                "Length: " + longestMessage.length() + " characters";
    }

    /*
     * Search for a message by ID and return its content
     */
    public static String searchForMessageByID(String messageID) {
        if (messageID == null || messageID.trim().isEmpty()) {
            return "Please provide a valid message ID.";
        }

        for (int i = 0; i < sentCount; i++) {
            if (sentMessageIDs[i] != null && sentMessageIDs[i].equals(messageID.trim())) {
                return "Message Found:\n" +
                        "ID: " + sentMessageIDs[i] + "\n" +
                        "Recipient: " + sentRecipients[i] + "\n" +
                        "Content: " + sentContents[i] + "\n" +
                        "Hash: " + sentMessageHashes[i];
            }
        }
        return "Message not found for ID: " + messageID;
    }

    /*
     * Search for all messages sent to a specific recipient
     */
    public static String searchMessagesByRecipient(String recipient) {
        if (recipient == null || recipient.trim().isEmpty()) {
            return "Please provide a valid recipient.";
        }

        StringBuilder result = new StringBuilder();
        boolean found = false;

        for (int i = 0; i < sentCount; i++) {
            if (sentRecipients[i] != null && sentRecipients[i].equals(recipient.trim())) {
                if (found) {
                    result.append("\n").append("=".repeat(30)).append("\n");
                }
                result.append("Message ID: ").append(sentMessageIDs[i]).append("\n");
                result.append("Content: ").append(sentContents[i]).append("\n");
                result.append("Hash: ").append(sentMessageHashes[i]).append("\n");
                found = true;
            }
        }

        if (!found) {
            return "No messages found for recipient: " + recipient;
        }

        return "Messages for " + recipient + ":\n\n" + result;
    }

    /*
     * Delete a message by ID
     */
    public static String deleteMessageByID(String messageID) {
        if (messageID == null || messageID.trim().isEmpty()) {
            return "Please provide a valid message ID.";
        }

        for (int i = 0; i < sentCount; i++) {
            if (sentMessageIDs[i] != null && sentMessageIDs[i].equals(messageID.trim())) {
                String deletedContent = sentContents[i];

                // Shift all elements after the deleted element to the left
                for (int j = i; j < sentCount - 1; j++) {
                    sentMessageIDs[j] = sentMessageIDs[j + 1];
                    sentRecipients[j] = sentRecipients[j + 1];
                    sentContents[j] = sentContents[j + 1];
                    sentMessageHashes[j] = sentMessageHashes[j + 1];
                }

                // Clear the last elements
                sentMessageIDs[sentCount - 1] = null;
                sentRecipients[sentCount - 1] = null;
                sentContents[sentCount - 1] = null;
                sentMessageHashes[sentCount - 1] = null;

                sentCount--;

                return "Message deleted successfully:\n" + deletedContent;
            }
        }

        return "Message not found for ID: " + messageID;
    }


    public static String displayFullMessageReport() {
        if (sentCount == 0) {
            return "No messages have been sent yet.";
        }

        StringBuilder report = new StringBuilder("--- Full Message Report ---\n\n");
        for (int i = 0; i < sentCount; i++) {
            if (sentMessageIDs[i] != null) {
                report.append("Message #").append(i + 1).append(":\n");
                report.append("ID: ").append(sentMessageIDs[i]).append("\n");
                report.append("Recipient: ").append(sentRecipients[i]).append("\n");
                report.append("Content: ").append(sentContents[i]).append("\n");
                report.append("Hash: ").append(sentMessageHashes[i]).append("\n");
                report.append("Length: ").append(sentContents[i].length()).append(" characters\n");
                report.append("-".repeat(40)).append("\n\n");
            }
        }

        report.append("Total Messages: ").append(sentCount);
        return report.toString();
    }

    /*
     * Get content of a specific message by ID
     */
    public static String getMessageContent(String messageID) {
        for (int i = 0; i < sentCount; i++) {
            if (sentMessageIDs[i] != null && sentMessageIDs[i].equals(messageID)) {
                return sentContents[i];
            }
        }
        return null;
    }

    /*
     * Get recipient content for a specific recipient
     */
    public static String getRecipientContent(String recipient) {
        StringBuilder result = new StringBuilder();
        boolean found = false;

        for (int i = 0; i < sentCount; i++) {
            if (sentRecipients[i] != null && sentRecipients[i].equals(recipient)) {
                if (found) {
                    result.append("\n\n");
                }
                result.append("Message ID: ").append(sentMessageIDs[i])
                        .append("\nContent: ").append(sentContents[i])
                        .append("\nHash: ").append(sentMessageHashes[i]);
                found = true;
            }
        }

        return found ? result.toString() : null;
    }


    public static boolean addSentMessage(String messageID, String recipient, String content, String hash) {
        if (sentCount >= MAX_MESSAGES) {
            return false;
        }

        sentMessageIDs[sentCount] = messageID;
        sentRecipients[sentCount] = recipient;
        sentContents[sentCount] = content;
        sentMessageHashes[sentCount] = hash;
        sentCount++;

        return true;
    }

    /*
     * Get total number of sent messages
     */
    public static int getSentCount() {
        return sentCount;
    }

    /*
     * Get sent message arrays for external access (like JSON storage)
     */
    public static String[] getSentMessageIDs() { return sentMessageIDs; }
    public static String[] getSentRecipients() { return sentRecipients; }
    public static String[] getSentContents() { return sentContents; }
    public static String[] getSentMessageHashes() { return sentMessageHashes; }

    /*
     * Set sent count (for loading from JSON)
     */
    public static void setSentCount(int count) {
        sentCount = Math.min(count, MAX_MESSAGES);
    }

    // Builder Pattern - FIXED: Added missing methods and constructor
    public static class MessageBuilder {
        private String messageID;
        private String recipient;
        private String content;
        private String messageHash;

        public MessageBuilder() {
            // Default constructor
        }

        public MessageBuilder setMessageID(String messageID) {
            this.messageID = messageID;
            return this;
        }

        public MessageBuilder setRecipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public MessageBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public MessageBuilder setMessageHash(String messageHash) {
            this.messageHash = messageHash;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}