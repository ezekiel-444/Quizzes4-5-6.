package Quiz6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class SpecialCommunicationManager {
    protected String commonServiceUrl;
    protected String specialServiceUrl;

    public SpecialCommunicationManager(String commonServiceUrl, String specialServiceUrl) {
        this.commonServiceUrl = commonServiceUrl;
        this.specialServiceUrl = specialServiceUrl;
    }

    public String sendMessage(String userMessage, List<String> chatHistory) {
        String serviceUrl = commonServiceUrl;

        // Check if the message or chat history contains the word "help"
        if (userMessage.contains("help") || chatHistory.stream().anyMatch(message -> message.contains("help"))) {
            serviceUrl = specialServiceUrl;
        }

        // Convert chat history to JSON format
        String jsonInputString = createJsonPayload(userMessage, chatHistory);

        try {
            // Create the connection
            URL url = new URL(serviceUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            // Send the JSON input
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Read the response
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                return response.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    private String createJsonPayload(String userMessage, List<String> chatHistory) {
        StringBuilder chatHistoryJson = new StringBuilder("[");
        for (int i = 0; i < chatHistory.size(); i++) {
            chatHistoryJson.append("\"").append(chatHistory.get(i)).append("\"");
            if (i < chatHistory.size() - 1) {
                chatHistoryJson.append(",");
            }
        }
        chatHistoryJson.append("]");

        return "{ \"message\": \"" + userMessage + "\", \"history\": " + chatHistoryJson.toString() + " }";
    }
}

