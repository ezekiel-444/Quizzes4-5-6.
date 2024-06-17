package Quiz6;

import java.util.List;

public class DummySpecialCommunicationManager extends SpecialCommunicationManager {

    public DummySpecialCommunicationManager(String commonServiceUrl, String specialServiceUrl) {
        super(commonServiceUrl, specialServiceUrl);
    }

    @Override
    public String sendMessage(String userMessage, List<String> chatHistory) {
        String serviceUrl = commonServiceUrl;

        // Check if the message or chat history contains the word "help"
        if (userMessage.contains("help") || chatHistory.stream().anyMatch(message -> message.contains("help"))) {
            serviceUrl = specialServiceUrl;
        }

        // Simulate responses based on the service URL
        if (serviceUrl.equals(commonServiceUrl)) {
            if (userMessage.equalsIgnoreCase("Hi there")) {
                return "Hello! How can I assist you today?";
            } else if (userMessage.equalsIgnoreCase("Can you tell me a joke")) {
                return "Sure! Why don't scientists trust atoms? Because they make up everything!";
            } else if (userMessage.equalsIgnoreCase("Thank you")) {
                return "You're welcome! Anything else I can help with?";
            } else if (userMessage.equalsIgnoreCase("Goodbye")) {
                return "Goodbye! Have a great day!";
            } else {
                return "I'm not sure how to respond to that.";
            }
        } else {
            return "This is a special response because you asked for help.";
        }
    }
}



