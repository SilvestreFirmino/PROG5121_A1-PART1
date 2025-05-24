package prog5121_a1.part1;

// No imports for Swing or IO needed here anymore, as functionality is moved
// import javax.swing.*; // Removed
// import java.io.*; // Removed
// import java.util.Random; // Removed

public class Message {

    private String messageID;
    private String recipient;
    private String content;
    private String messageHash; // This will still be set by the builder, but its creation logic is external

    // Default constructor (can be removed if only builder is used, but kept for flexibility)
    public Message() {
        this.messageID = "";
        this.recipient = "";
        this.content = "";
        this.messageHash = "";
    }

    // Private constructor for the Builder
    private Message(MessageBuilder builder) {
        this.messageID = builder.messageID;
        this.recipient = builder.recipient;
        this.content = builder.content;
        // The hash is now set externally by PROG5121_A1PART1, as its logic is there.
        // So, we don't call createMessageHash() here anymore.
        this.messageHash = ""; // Initialize, will be set by external logic
    }

    // Getters and setters (these are essential for the main class to access/set data)
    public String getMessageID() { return messageID; }
    public void setMessageID(String messageID) { this.messageID = messageID; }

    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getMessageHash() { return messageHash; }
    public void setMessageHash(String messageHash) { this.messageHash = messageHash; } // Setter for hash

    // The Builder class remains as it's part of the Message object's construction
    public static class MessageBuilder {
        private String messageID;
        private String recipient;
        private String content;

        public MessageBuilder(String messageID) {
            this.messageID = messageID;
        }

        public MessageBuilder recipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public MessageBuilder content(String content) {
            this.content = content;
            return this;
        }

        public Message build() {
            return new Message(this);
        }

        
    }

}