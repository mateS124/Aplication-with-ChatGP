package main;


import java.util.List;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import  com.theokanning.openai.completion.chat.ChatMessage;



public class ChatGPTHelper {
    OpenAiService service;
    public ChatGPTHelper(){
        service = new OpenAiService("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");
    }

    public String getBreakfastIdea(List<String> products){
        String allProducts = String.join(", ", products);
        String question = "Mam w lodówce " + allProducts + ". Co mogę zjeść na śniadanie? Daj mi trzy pomysły";

       return askChatGPT(question);
    }

    public String getLunchIdea(List<String> products){
        String allProducts = String.join(", ", products);
        String question = "Mam w lodówce " + allProducts + ". Co mogę zjeść na obiad? Daj mi trzy pomysły";

        return askChatGPT(question);
    }

    public String getHealthyProductRecomendation(List<String> products) {
        String allProducts = String.join(", ", products);
        String question = "Mam w lodówce " + allProducts + ". Jakie inne zdrowe produkty mogę kupić?";

        return askChatGPT(question);
    }

    private String askChatGPT(String question){
        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .messages(List.of(new ChatMessage("user", question )))
                .model("gpt-3.5-turbo")
                .build();
        List<ChatCompletionChoice> choice = service.createChatCompletion(completionRequest).getChoices();

        StringBuilder stringBuilder = new StringBuilder();

        choice.stream()
                .map(ChatCompletionChoice::getMessage)
                .map(ChatMessage::getContent)
                .forEach(stringBuilder::append);

        return stringBuilder.toString();
    }

}
