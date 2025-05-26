package prog5121_a1.part1;


public class Message {

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

    private Message(MessageBuilder builder) {
        this.messageID = builder.messageID;
        this.recipient = builder.recipient;
        this.content = builder.content;
        this.messageHash = "";
    }

    public String getMessageID() { return messageID; }
    public void setMessageID(String messageID) { this.messageID = messageID; }

    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getMessageHash() { return messageHash; }
    public void setMessageHash(String messageHash) { this.messageHash = messageHash; }

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
