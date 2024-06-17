package Quiz6;



import java.util.ArrayList;
import java.util.List;

public class TestSpecialCommunicationManager {
    public static void main(String[] args) {
        String commonServiceUrl = "http://example.com/commonChatbot";
        String specialServiceUrl = "http://example.com/specialChatbot";

        SpecialCommunicationManager manager = new DummySpecialCommunicationManager(commonServiceUrl, specialServiceUrl);

        List<String> chatHistory = new ArrayList<>();
        chatHistory.add("Hello");
        chatHistory.add("How are you?");

        String response1 = manager.sendMessage("Hi there", chatHistory);
        System.out.println("Response1: " + response1);

        chatHistory.add("Hi there");
        String response2 = manager.sendMessage("I need help", chatHistory);
        System.out.println("Response2: " + response2);
    }
}

