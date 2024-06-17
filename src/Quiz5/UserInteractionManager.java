package Quiz5;

import Quiz6.DummySpecialCommunicationManager;
import Quiz6.SpecialCommunicationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInteractionManager {
    private SpecialCommunicationManager communicationManager;
    private List<String> chatHistory;

    public UserInteractionManager(SpecialCommunicationManager communicationManager) {
        this.communicationManager = communicationManager;
        this.chatHistory = new ArrayList<>();
    }

    public void startChat() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("User: ");
            String userMessage = scanner.nextLine();
            if (userMessage.equalsIgnoreCase("exit")) {
                break;
            }
            chatHistory.add("User: " + userMessage);
            String response = communicationManager.sendMessage(userMessage, chatHistory);
            chatHistory.add("Chatbot: " + response);
            System.out.println("Chatbot: " + response);
        }
        scanner.close();
    }

    public static void main(String[] args) {
        String commonServiceUrl = "http://example.com/commonChatbot";
        String specialServiceUrl = "http://example.com/specialChatbot";
        SpecialCommunicationManager manager = new DummySpecialCommunicationManager(commonServiceUrl, specialServiceUrl);
        UserInteractionManager uiManager = new UserInteractionManager(manager);
        uiManager.startChat();
    }
}


