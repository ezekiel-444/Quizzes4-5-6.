package Quiz5;

public class DummyCommunicationManager extends Quiz5.CommunicationManager {

    @Override
    public String sendMessage(String message, String history) {
        // Simulate a response from the chatbot
        if (message.equalsIgnoreCase("Hi there!")) {
            return "Hello! How can I assist you today?";
        } else if (message.equalsIgnoreCase("Can you tell me a joke?")) {
            return "Why don't scientists trust atoms? Because they make up everything!";
        } else if (message.equalsIgnoreCase("That's funny! Tell me another one.")) {
            return "What do you call fake spaghetti? An impasta!";
        } else {
            return "I'm not sure how to respond to that.";
        }
    }
}
